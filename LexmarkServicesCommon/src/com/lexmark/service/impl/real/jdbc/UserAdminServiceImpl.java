package com.lexmark.service.impl.real.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.DAUserListContract;
import com.lexmark.contract.SaveServicesUserContract;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.DAUserListResult;
import com.lexmark.result.SaveServicesUserResult;
import com.lexmark.service.api.UserAdminService;
import com.lexmark.util.DBUtil;

public class UserAdminServiceImpl implements UserAdminService {
	private static Logger logger = LogManager.getLogger(UserAdminServiceImpl.class);
	private static final String SQL_RETRIEVE_DAUSER_LIST = 
		"select usernumber, level_no, mdm_id, usermode from idmda.da_data_integration_tab " +
		"where seq_number = ? and jsessionid = ? " +
		"union all select usernumber, 'Account' as level_no, ddumait.user_mdm_acc_id as mdm_id, usermode " +
		"from idmda.da_data_integration_tab ddit, idmda.da_data_user_mdm_acc_int_tab ddumait " +
		"where ddit.seq_number = ddumait.seq_number and ddit.jsessionid = ddumait.jsessionid " +
		"and ddit.seq_number = ? and ddit.jsessionid= ?";
	private static final String HQL_GET_SERVICES_USER_BY_USER_NUMBER =
		"from ServicesUser where usernumber = :userNumber";
	
	private enum MDMLevel {
		GLOBAL("GLOBAL", "Global"),
		DOMESTIC("DOMESTIC", "Domestic"),
		LEGAL("LEGAL", "Legal"),
		ACCOUNT("ACCOUNT", "Account");
		
		private String value;
		private String displayName;
		MDMLevel(String value, String displayName) {
			this.value = value;
			this.displayName = displayName;
		}
		public String getValue() {
			return value;
		}
		
		public String getDisplayName() {
			return displayName;
		}
	}

	public DAUserListResult retrieveDAUserList(DAUserListContract contract)
			throws Exception {
		DAUserListResult result = new DAUserListResult();
		
		Integer SEQNumber = contract.getOperId();
		String jSessionId = contract.getJSessionNum();
		
		Connection conn = null;
		PreparedStatement stmtRetrieveDAUserList = null;
		try {
			conn = DBUtil.getIDMConnection();
			logger.debug("====================================================================" );
			logger.debug("conn: "   + conn);
			stmtRetrieveDAUserList = conn.prepareStatement(SQL_RETRIEVE_DAUSER_LIST,
					ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		
			logger.debug("SQL_RETRIEVE_DAUSER_LIST: " + SQL_RETRIEVE_DAUSER_LIST);
			logger.debug("stmtRetrieveDAUserList: "   + stmtRetrieveDAUserList);
			
			stmtRetrieveDAUserList.setInt(1, SEQNumber);
			stmtRetrieveDAUserList.setString(2, jSessionId);
			stmtRetrieveDAUserList.setInt(3, SEQNumber);
			stmtRetrieveDAUserList.setString(4, jSessionId);
			ResultSet rs = stmtRetrieveDAUserList.executeQuery();
			
			populateDAUserListResult(result, rs, SEQNumber, jSessionId);
		} catch (SQLException ex) {
			logger.error(ex.getMessage());
			throw new SQLException ("Failed to retrieveDAUserList.");
		}
		finally {
			if (stmtRetrieveDAUserList != null) {
				stmtRetrieveDAUserList.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return result;
	}

	/**
	 * Save or update ServicesUser.
	 * if newServicesUserFlag is true, then save, otherwise update.
	 */
	public SaveServicesUserResult saveServicesUser(
			SaveServicesUserContract contract) throws Exception {
		SaveServicesUserResult result = new SaveServicesUserResult();
		String mdmId = contract.getMdmId();
		String mdmLevel = contract.getMdmLevel();
		String chlNodeId = contract.getChlNodeId();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(HQL_GET_SERVICES_USER_BY_USER_NUMBER);
			for (String userNumber : contract.getUserNumberList()) {
				ServicesUser servicesUser = null;
				query.setParameter("userNumber", userNumber);
				Iterator it = query.iterate();
				// UserNumber already exists
				if (it.hasNext()) {
					servicesUser = (ServicesUser) it.next();
				} else {// UserNumber is new
					servicesUser = new ServicesUser();
					servicesUser.setUserNumber(userNumber);
				}
				servicesUser.setMdmId(mdmId);
				servicesUser.setMdmLevel(mdmLevel);
				servicesUser.setChlNodeId(chlNodeId);
				
				session.saveOrUpdate(servicesUser);
			}
			tx.commit();
		} catch (HibernateException ex) {
			logger.debug("Failed to save services user:" + contract.getUserNumberList().toString());
			result.setResult(false);
			return result;
		} finally {
			if(session != null) {
				session.close();
			}
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * Populate DAUSerListResult by ResultSet of querying DA user list(by SEQNumber
	 * and jSessionId).
	 */
	private void populateDAUserListResult(DAUserListResult result, ResultSet rs,
			Integer SEQNumber, String jSessionId) throws SQLException {
		List<String> mdmIdList = new ArrayList<String>();
		List<String> userNumberList = new ArrayList<String>();
		String mdmLevel = null;
		String userMode = null;
		
		if (!rs.next()) {
			if (logger.isDebugEnabled()) {
				logger.debug("No record found by jSessionId:" + SEQNumber + ", jSessionId:" + jSessionId);
			}
			throw new SQLException("Failed to retrieveDAUserList.");
		}
		userMode = rs.getString("usermode");
		
		boolean isAccountLevel = false;
		rs.beforeFirst();
		while(rs.next()) {
			if (rs.getString("level_no").equalsIgnoreCase(LexmarkConstants.
					MDM_LEVEL_ACCOUNT)) {
				isAccountLevel = true;
				break;
			}
		}
		
		// MDM Level other than "Account" is selected
		if (!isAccountLevel) {
			boolean firstRow = true;
			rs.beforeFirst();
			while(rs.next()) {
				if (firstRow) {
					mdmLevel = getMDMLevelDisplayValue(rs.getString("level_no"));
					mdmIdList.add(rs.getString("mdm_id"));
					firstRow = false;
				}
				userNumberList.add(rs.getString("usernumber"));
			}
		} else { // MDM Level "Account" is selected
			mdmLevel = MDMLevel.ACCOUNT.displayName;
			
			Set<String> userNumberSet = new HashSet<String>();
			Set<String> mdmIdSet = new HashSet<String>();
			rs.beforeFirst();
			while(rs.next()) {
				if (rs.getString("level_no").equalsIgnoreCase(LexmarkConstants.MDM_LEVEL_ACCOUNT)) {
    				userNumberSet.add(rs.getString("usernumber"));
    				mdmIdSet.add(rs.getString("mdm_id"));
				}
			}
			userNumberList.addAll(userNumberSet);
			mdmIdList.addAll(mdmIdSet);
		}
		
		result.setMdmIdList(mdmIdList);
		result.setMdmLevel(mdmLevel);
		result.setUserNumberList(userNumberList);
		result.setUserMode(userMode);
	}

	private String getMDMLevelDisplayValue(String mdmLevel) {
		for(MDMLevel e : MDMLevel.values()) {
			if(e.getValue().equals(mdmLevel)) {
				return e.getDisplayName();
			}
		}
		return "";
	}
}
