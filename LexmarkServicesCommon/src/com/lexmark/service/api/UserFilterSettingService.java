package com.lexmark.service.api;

import java.util.List;


import com.lexmark.contract.UserFilterSettingContract;
import com.lexmark.domain.FilterPreferenceList;
import com.lexmark.domain.OPSUserFilterPopupSetting;
import com.lexmark.domain.UserFieldsViewSetting;
import com.lexmark.domain.UserFilterSetting;

public interface UserFilterSettingService {
	public List<UserFilterSetting> retrieveUserPreferences(
			UserFilterSettingContract contract) throws Exception;

	public UserFilterSetting saveUserPreferences(
			UserFilterSettingContract contract) throws Exception;
	
	public boolean saveUserFieldPreferences(
			UserFilterSettingContract contract) throws Exception;
	
	public UserFieldsViewSetting retrieveUserFieldPreferences(
			UserFilterSettingContract contract) throws Exception;
	
	//Below are for OPS
	public boolean saveUserFieldPreferencesOPS(
			UserFilterSettingContract contract) throws Exception;
	
	public UserFieldsViewSetting retrieveUserFieldPreferencesOPS(
			UserFilterSettingContract contract) throws Exception;
	
	
	
	public OPSUserFilterPopupSetting saveOpsUserPreferencesPopup(
			UserFilterSettingContract contract) throws Exception;
	
	public List<OPSUserFilterPopupSetting> retrieveOpsUserPreferencesPopup(
			UserFilterSettingContract contract) throws Exception;
	
	public boolean removePreference(String prefId)throws Exception;
	public boolean removeUserPreference(String prefId) throws Exception ;

	public List<FilterPreferenceList> retrieveUserAllPreferences(
			UserFilterSettingContract contract) throws Exception;
}
