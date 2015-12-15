package com.lexmark.service.impl.real.delegate;

import static com.lexmark.service.impl.real.util.AmindGlobalConversionUtil.convertGlobalLegalEntityToGlobalAccount;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.ACCOUNT_MDMLEVEL;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.DOMESTIC_MDMLEVEL;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.GLOBAL_MDMLEVEL;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.LEGAL_MDMLEVEL;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.SIEBEL_MDMLEVEL;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.notNull;
import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.GlobalLegalEntityContract;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.service.impl.real.domain.GlobalLegalEntityDO;
import org.apache.logging.log4j.Logger;


public class GlobalLegalEntityService {
	//private static final Log logger = LogFactory.getLog(GlobalLegalEntityService.class);
	private static final Logger logger = LogManager.getLogger(GlobalLegalEntityService.class);
	private final String mdmId;
	private final String mdmLevel;
	private String searchExpression;
	private Session session;

	public GlobalLegalEntityService(GlobalLegalEntityContract contract) {
		if (contract == null) {
			throw new IllegalArgumentException("contract can not be null");
		}
		mdmId = contract.getMdmId();
		mdmLevel = contract.getMdmLevel();
	}

	public void checkRequiredFields() {
		if (isEmpty(mdmId)) {
			throw new IllegalArgumentException("mdm id is null or empty");
		}
		if (isEmpty(mdmLevel)) {
			throw new IllegalArgumentException("mdm level is null or empty");
		}
	}

	public void buildSearchExpression() {
		StringBuilder builder = new StringBuilder();

		if (GLOBAL_MDMLEVEL.equals(mdmLevel)) {
			builder.append("[mdmLevel1AccountId] = '");
			builder.append(mdmId);
			builder.append("' AND [accountLevel] = '");
			builder.append(mdmLevel);
			builder.append("'");
		} else if (DOMESTIC_MDMLEVEL.equals(mdmLevel)) {
			builder.append("[mdmLevel2AccountId] = '");
			builder.append(mdmId);
			builder.append("' AND [accountLevel] = '");
			builder.append(mdmLevel);
			builder.append("'");
		} else if (LEGAL_MDMLEVEL.equals(mdmLevel)) {
			builder.append("[mdmLevel3AccountId] = '");
			builder.append(mdmId);
			builder.append("' AND [accountLevel] = '");
			builder.append(mdmLevel);
			builder.append("'");
		} else if (ACCOUNT_MDMLEVEL.equals(mdmLevel)) {
			builder.append("[mdmLevel4AccountId] = '");
			builder.append(mdmId);
			builder.append("' AND [accountLevel] = '");
			builder.append(mdmLevel);
			builder.append("'");
		} else if (SIEBEL_MDMLEVEL.equals(mdmLevel)) {
			builder.append("[mdmLevel5AccountId] = '");
			builder.append(mdmId);
			builder.append("'");
		}

		searchExpression = builder.toString();
	}

	public GlobalAccount queryAndGetResult() {
		IDataManager dataManager = getSession().getDataManager();
		GlobalAccount account = new GlobalAccount() ;
		List<GlobalLegalEntityDO> globalList = queryGlobalLegalEntityList(dataManager);
		if (isNotEmpty(globalList)) {
			account =  convertGlobalLegalEntityToGlobalAccount(globalList.get(0), mdmId, dataManager);
		} else {
			logger.debug("Global Account with mdmId: '" + mdmId + "' and mdmLevel: '" + mdmLevel
					+ "' not found");
		
		}
		return account;
	}

	@SuppressWarnings("unchecked")
	private List<GlobalLegalEntityDO> queryGlobalLegalEntityList(IDataManager dataManager) {

		QueryObject criteria = new QueryObject(GlobalLegalEntityDO.class, ActionEvent.QUERY);
		criteria.setQueryString(searchExpression);

		List<GlobalLegalEntityDO> globalList = dataManager.query(criteria);
		return notNull(globalList);
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		if (session == null) {
			throw new IllegalArgumentException("session can not be null!");
		}
		this.session = session;
	}

}
