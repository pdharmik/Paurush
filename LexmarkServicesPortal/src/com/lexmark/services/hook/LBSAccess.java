package com.lexmark.services.hook;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.Account;
import com.lexmark.domain.UserDetails;
import com.lexmark.services.portlet.fleetmanagement.FleetManagementController;

public class LBSAccess {
	private static Logger LOGGER = LogManager.getLogger(LBSAccess.class);
	public static void setDeviceStatusAccess(UserDetails userDetails,
			List<Account> accountListCustomer, List<String> roles) {
		LOGGER.debug("[in setDeviceStatusAccess]");
		LOGGER.debug("acc mgmt ="+roles.contains(LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT));
		LOGGER.debug("service support ="+roles.contains(LexmarkConstants.ROLE_SERVICE_SUPPORT));
		if(roles.contains(LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT) && checkDeviceStatus(accountListCustomer,"deviceUtilization")){
			userDetails.setShowDeviceUtilizationTerms(true);
		}if((roles.contains(LexmarkConstants.ROLE_SERVICE_SUPPORT) || roles.contains(LexmarkConstants.ROLE_SERVICE_SUPPORT_DUP)) && 
				checkDeviceStatus(accountListCustomer,"deviceStatus")){
			userDetails.setShowDeviceStatus(true);
		}
		LOGGER.debug("show device status "+userDetails.isShowDeviceStatus());
		LOGGER.debug("show device status Utilization"+userDetails.isShowDeviceUtilizationTerms());
		LOGGER.debug("[out setDeviceStatusAccess]");
	}
	
	private static boolean checkDeviceStatus(List<Account> accountList,String flagName){
		LOGGER.debug("[in checkDeviceStatus]");
		for(Account acc:accountList){
			if(checkDeviceStatusFlag(acc,flagName)){
				return true;
			}			
		}
		LOGGER.debug("[OUT checkDeviceStatus]");
		return false;
	}
	
	public static boolean checkDeviceStatusFlag(Account acc,String flagName){
		if("deviceStatus".equalsIgnoreCase(flagName)){
			LOGGER.debug("account Device sTatus "+acc.getDeviceStatus());
			if("Y".equalsIgnoreCase(acc.getDeviceStatus())){
				return true;				
			}
		}else if("deviceUtilization".equalsIgnoreCase(flagName)){
			LOGGER.debug("account Device utilization "+acc.getLbsUtilization());
			if("Y".equalsIgnoreCase(acc.getLbsUtilization())){
				return true;				
			}
		}
		return false;
	}
	

}
