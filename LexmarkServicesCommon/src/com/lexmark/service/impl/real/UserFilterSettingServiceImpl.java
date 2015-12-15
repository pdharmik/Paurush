/**
 * 
 */
package com.lexmark.service.impl.real;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;



import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.lexmark.contract.UserFilterSettingContract;
import com.lexmark.domain.AccountFilter;
import com.lexmark.domain.AddressFilter;

import com.lexmark.domain.DeviceFilter;
import com.lexmark.domain.FilterPreferenceList;
import com.lexmark.domain.OPSUserFilterPopupSetting;
import com.lexmark.domain.RequestFilter;
import com.lexmark.domain.UserFieldsViewSetting;
import com.lexmark.domain.UserFieldsViewSettingOPS;
import com.lexmark.domain.UserFilterSetting;
import com.lexmark.domain.UserGridSetting;
import com.lexmark.service.api.UserFilterSettingService;
import com.lexmark.service.impl.real.jdbc.HibernateUtil;
import com.lexmark.service.impl.real.jdbc.InfrastructureException;



/**
 * @author wipro
 *
 */
public class UserFilterSettingServiceImpl implements UserFilterSettingService {
	private static Logger LOGGER = LogManager.getLogger(UserFilterSettingServiceImpl.class);
	@Override
	public List<UserFilterSetting> retrieveUserPreferences(
			UserFilterSettingContract contract) throws Exception {
		try {		
			String sql = "select fp.userid,fp.address,fp.device,fp.service_request,fp.pref_name,fp.isdefault,fp.filter_id,ofp.fields " +
					"from USER_FILTER_PREFERENCES fp left join USER_FIELD_PREFERENCES "+ 
					"ofp on fp.FILTER_ID=ofp.PREF_ID where fp.userId = :user_id";
			Query query = HibernateUtil.getSession().createSQLQuery(sql);
			query.setParameter("user_id",contract.getFilterSetting().getUserId());
			
			List results = query.list();
			List<UserFilterSetting> listPreferences=new ArrayList<UserFilterSetting>();
			if(results!=null && !results.isEmpty() && results.get(0)!=null){
				for(int i=0;i<results.size();i++){
					UserFilterSetting userFilterSetting=new UserFilterSetting();
					listPreferences.add(userFilterSetting);
					Object[] row = (Object[]) results.get(i);
					/*LOGGER.debug("row[0].toString()"+row[0].toString());
					LOGGER.debug("row[1].toString()"+row[1].toString());
					LOGGER.debug("row[2].toString()"+row[2].toString());
					LOGGER.debug("row[3].toString()"+row[3].toString());
					LOGGER.debug("row[4].toString()"+row[4].toString());
					LOGGER.debug("row[5].toString()"+row[5].toString());
					LOGGER.debug("row[6].toString()"+row[6].toString());*/
					
					userFilterSetting.setUserId(row[0].toString());
					userFilterSetting.setAddress(row[1].toString());
					userFilterSetting.setDevice(row[2].toString());
					userFilterSetting.setServiceRequest(row[3].toString());
					userFilterSetting.setPrefName(row[4].toString());
					userFilterSetting.setDefaultPref(row[5].toString().charAt(0));
					userFilterSetting.setRowId(row[6].toString());
					userFilterSetting.setFieldPrefernce(row[7]==null?"":row[7].toString());
					
				}
				
				
			}
			return listPreferences;
				
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
	}

	@Override
	public UserFilterSetting saveUserPreferences(
			UserFilterSettingContract contract) throws Exception {
		/*try{
			LOGGER.debug("[DB Insertion Starts ]");
			UserFilterSetting userFilterSetting = contract.getFilterSetting();
			Long beginTime=System.currentTimeMillis();
			HibernateUtil.beginTransaction();
			HibernateUtil.getSession().saveOrUpdate(userFilterSetting);			
			HibernateUtil.commitTransaction();
			LOGGER.debug(String.format("[DB Insertion Ends time Taken %s]",beginTime-System.currentTimeMillis()));
			return true;
		}catch(HibernateException ex){
			throw new InfrastructureException(ex);
			
		}finally{
			HibernateUtil.closeSession();
		}*/

			try{
				LOGGER.debug("[DB Insertion Starts ]");
				UserFilterSetting userFilterSetting = contract.getFilterSetting();
				Long beginTime=System.currentTimeMillis();			
				HibernateUtil.beginTransaction();
				HibernateUtil.getSession().saveOrUpdate(userFilterSetting);			
				HibernateUtil.commitTransaction();
				LOGGER.debug("id is =="+userFilterSetting.getRowId());
				LOGGER.debug(String.format("[DB Insertion Ends time Taken %s]",beginTime-System.currentTimeMillis()));
				return userFilterSetting;
			}catch(HibernateException ex){
				ex.printStackTrace();
				throw new InfrastructureException(ex);
				
			}finally{
				HibernateUtil.closeSession();
			}
			
		
	}

	@Override
	public boolean saveUserFieldPreferences(
			UserFilterSettingContract contract) throws Exception {
		try{
			LOGGER.debug("[DB Insertion Starts ]");
			UserFieldsViewSetting userFieldViewSetting = contract.getFieldsViewSetting();
			Long beginTime=System.currentTimeMillis();
			HibernateUtil.beginTransaction();
			HibernateUtil.getSession().saveOrUpdate(userFieldViewSetting);			
			HibernateUtil.commitTransaction();
			LOGGER.debug(String.format("[DB Insertion Ends time Taken %s]",beginTime-System.currentTimeMillis()));
			return true;
		}catch(HibernateException ex){			
			throw new InfrastructureException(ex);
		}finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public UserFieldsViewSetting retrieveUserFieldPreferences(
			UserFilterSettingContract contract) throws Exception {
		try {		
			String sql = "select * from USER_FIELD_PREFERENCES WHERE userId = :user_id and pref_id=-1 and portal_name=:portal_name";
			Query query = HibernateUtil.getSession().createSQLQuery(sql);
			query.setParameter("user_id",contract.getFieldsViewSetting().getUserNumber());
			query.setParameter("portal_name",contract.getFieldsViewSetting().getPortalName());
			
			List results = query.list();
				UserFieldsViewSetting fieldSetting=null;
				if(results!=null && !results.isEmpty() && results.get(0)!=null){
					fieldSetting=new UserFieldsViewSetting();
					Object[] row = (Object[]) results.get(0);				
					fieldSetting.setFieldsDisplayed(row[2].toString());
				}
				
				return fieldSetting;
				
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
	}

	@Override
	public OPSUserFilterPopupSetting saveOpsUserPreferencesPopup(
			UserFilterSettingContract contract) throws Exception {
		try{
			LOGGER.debug("[DB Insertion Starts ]");
			OPSUserFilterPopupSetting userFilterSetting = contract.getOpsFilterSetting();
			Long beginTime=System.currentTimeMillis();			
			HibernateUtil.beginTransaction();
			HibernateUtil.getSession().saveOrUpdate(userFilterSetting);			
			HibernateUtil.commitTransaction();
			LOGGER.debug("id is =="+userFilterSetting.getRowId());
			LOGGER.debug(String.format("[DB Insertion Ends time Taken %s]",beginTime-System.currentTimeMillis()));
			return userFilterSetting;
		}catch(HibernateException ex){
			ex.printStackTrace();
			throw new InfrastructureException(ex);
			
		}finally{
			HibernateUtil.closeSession();
		}
		
	}

	@Override
	public List<OPSUserFilterPopupSetting> retrieveOpsUserPreferencesPopup(
			UserFilterSettingContract contract) throws Exception {
		try {		
			String sql = "select fp.userid,fp.address,fp.device,fp.service_request,fp.pref_name,fp.isdefault,fp.filter_id,ofp.fields " +
					"from OPS_USER_FILTER_PREFERENCES fp left join OPS_USER_FIELD_PREFERENCES "+ 
					"ofp on fp.FILTER_ID=ofp.PREF_ID where fp.userId = :user_id";
			Query query = HibernateUtil.getSession().createSQLQuery(sql);
			query.setParameter("user_id",contract.getFilterSetting().getUserId());
			
			List results = query.list();
			List<OPSUserFilterPopupSetting> listPreferences=new ArrayList<OPSUserFilterPopupSetting>();
			if(results!=null && !results.isEmpty() && results.get(0)!=null){
				for(int i=0;i<results.size();i++){
					OPSUserFilterPopupSetting opsUserFilterSetting=new OPSUserFilterPopupSetting();
					listPreferences.add(opsUserFilterSetting);
					Object[] row = (Object[]) results.get(i);
					/*LOGGER.debug("row[0].toString()"+row[0].toString());
					LOGGER.debug("row[1].toString()"+row[1].toString());
					LOGGER.debug("row[2].toString()"+row[2].toString());
					LOGGER.debug("row[3].toString()"+row[3].toString());
					LOGGER.debug("row[4].toString()"+row[4].toString());
					LOGGER.debug("row[5].toString()"+row[5].toString());
					LOGGER.debug("row[6].toString()"+row[6].toString());*/
					
					opsUserFilterSetting.setUserId(row[0].toString());
					opsUserFilterSetting.setAddress(row[1].toString());
					opsUserFilterSetting.setDevice(row[2].toString());
					opsUserFilterSetting.setServiceRequest(row[3].toString());
					opsUserFilterSetting.setPrefName(row[4].toString());
					opsUserFilterSetting.setDefaultPref(row[5].toString().charAt(0));
					opsUserFilterSetting.setRowId(row[6].toString());
					opsUserFilterSetting.setFieldPrefernce(row[7]==null?"":row[7].toString());
					
				}
				
				
			}
			return listPreferences;
				
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean saveUserFieldPreferencesOPS(
			
		UserFilterSettingContract contract) throws Exception {
			try{
				LOGGER.debug("[DB Insertion Starts ]");
				UserFieldsViewSettingOPS userFieldViewSetting = contract.getOpsfieldsViewSetting();
				Long beginTime=System.currentTimeMillis();
				HibernateUtil.beginTransaction();
				HibernateUtil.getSession().saveOrUpdate(userFieldViewSetting);			
				HibernateUtil.commitTransaction();
				LOGGER.debug(String.format("[DB Insertion Ends time Taken %s]",beginTime-System.currentTimeMillis()));
				return true;
			}catch(HibernateException ex){			
				throw new InfrastructureException(ex);
			}finally{
				HibernateUtil.closeSession();
			}
		
	}

	@Override
	public UserFieldsViewSetting retrieveUserFieldPreferencesOPS(
			UserFilterSettingContract contract) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removePreference(String prefId) throws Exception {
		Connection conn = null;
		try{
			
			HibernateUtil.beginTransaction();	
			conn = HibernateUtil.getSession().connection();               
			PreparedStatement stmt = conn.prepareStatement("DELETE ops_user_filter_preferences o WHERE o.filter_id = ?");
			stmt.setString(1,prefId);
			
			PreparedStatement stmt2 = conn.prepareStatement("DELETE  OPS_USER_FIELD_PREFERENCES o WHERE o.pref_id = ?");
			stmt2.setString(1,prefId);
			
			stmt.executeUpdate();
			stmt2.executeUpdate();
			
			HibernateUtil.commitTransaction();
			LOGGER.debug("ops_user_filter_preferences filter_id  = "+prefId);
			return true;
		}catch(HibernateException ex){
			throw new InfrastructureException(ex);
		}finally{
			HibernateUtil.closeSession();
		}
		
	}
	@Override
	public boolean removeUserPreference(String prefId) throws Exception {
		Connection conn = null;
		try{
			
			HibernateUtil.beginTransaction();	
			conn = HibernateUtil.getSession().connection();               
			PreparedStatement stmt = conn.prepareStatement("DELETE user_filter_preferences o WHERE o.filter_id = ?");
			stmt.setString(1,prefId);
			
			PreparedStatement stmt2 = conn.prepareStatement("DELETE  USER_FIELD_PREFERENCES o WHERE o.pref_id = ?");
			stmt2.setString(1,prefId);
			
			stmt.executeUpdate();
			stmt2.executeUpdate();
			
			HibernateUtil.commitTransaction();
			LOGGER.debug("ops_user_filter_preferences filter_id  = "+prefId);
			return true;
		}catch(HibernateException ex){
			throw new InfrastructureException(ex);
		}finally{
			HibernateUtil.closeSession();
		}
		
	}
	
	public List<FilterPreferenceList> retrieveUserAllPreferences(
			UserFilterSettingContract contract) throws Exception {
		try {		
			//String sql = "select * from USER_FILTER_PREFERENCES WHERE userId = :user_id";
			String sql = "select * from USER_FILTER_PREFERENCES";
			Query query = HibernateUtil.getSession().createSQLQuery(sql);
			//query.setParameter("user_id",contract.getFilterSetting().getUserId());
			
			List results = query.list();
			List<FilterPreferenceList> listOfPreferences=new ArrayList<FilterPreferenceList>();
			Gson gsonObj=new Gson();
			if(results!=null && !results.isEmpty() && results.get(0)!=null){
				for(int i=0;i<results.size();i++){
					FilterPreferenceList filterPreference=new FilterPreferenceList();
					listOfPreferences.add(filterPreference);
					Object[] row = (Object[]) results.get(i);
					filterPreference.setUserId(row[0].toString());
					filterPreference.setAddressFilter(gsonObj.fromJson(row[1].toString(), AddressFilter.class));
					AddressFilter address=filterPreference.getAddressFilter();
					if(address!=null && address.getState()!=null){
						String state=address.getState();
						address.setState(state.substring(0,state.indexOf("^")));
					}
					
					filterPreference.setDeviceFilter(gsonObj.fromJson(row[2].toString(), DeviceFilter.class));
					filterPreference.setRequestFilter(gsonObj.fromJson(row[3].toString(), RequestFilter.class));
					//filterPreferenceList.setAccountFilter(gsonObj.fromJson(row[4].toString(), AccountFilter.class));
					filterPreference.setIsPreference(row[5].toString().charAt(0));
					
					
					}
				}
			
		
	          
			
			return listOfPreferences;
				
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		
	}

}
