package com.lexmark.service.api;

import com.lexmark.contract.UserGridSettingContract;
import com.lexmark.result.UserGridSettingResult;

/**
 * This is interface for NotificationService. It defines all services
 * that related to Notification. 
 * @author Roger.Lin
 *
 */
public interface UserGridSettingService {

	public UserGridSettingResult retrieveUserGridSettings(
			UserGridSettingContract contract) throws Exception;

	public boolean deleteUserGridSettings(
			UserGridSettingContract contract) throws Exception;
	
	public boolean saveUserGridSettings(
			UserGridSettingContract contract) throws Exception;
}
