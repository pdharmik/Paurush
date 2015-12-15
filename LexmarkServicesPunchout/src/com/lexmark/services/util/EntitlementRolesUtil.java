/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: EntitlementRolesUtil
 * Package     		: com.lexmark.services.util
 * Creation Date 	: 16th April 2012
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Sandeep P		16th April 2012 		1.0             Initial Version
 *
 */
package com.lexmark.services.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.EntitlementRoleMapping;
import com.lexmark.domain.Role;

public class EntitlementRolesUtil {
	private static Logger LOGGER = LogManager.getLogger(EntitlementRolesUtil.class);

	
	/**
	 * @param userRoleList 
	 * @param roles4EntitlementMap 
	 * @return Map 
	 * @throws Exception 
	 */
	public static Map<String, String> retrieveEntitlement4User(List<String> userRoleList, HashMap<String, EntitlementRoleMapping>roles4EntitlementMap) throws Exception{
		
		Map<String, String>entitlementMap= new LinkedHashMap<String, String>();
		
		
		for (Iterator iterator = roles4EntitlementMap.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<String, EntitlementRoleMapping> enrty = (Map.Entry) iterator.next();
			String entitlementShortDesc = (String) enrty.getKey();
			EntitlementRoleMapping entitlementRoleMapping = (EntitlementRoleMapping)enrty.getValue();
			List<String> roleNameList = new ArrayList<String>();
			
			List<Role> roleList = entitlementRoleMapping.getRoleList();
			String roleName ="";
			for(Role role:roleList){
				roleName = role.getName();
				if(userRoleList.contains(roleName)){
					entitlementMap.put(entitlementShortDesc, "True");
					break;
				}else{
					entitlementMap.put(entitlementShortDesc, "False");
				}
			}
			//logger.info(entitlementShortDesc+"::"+">>>userRoleList:"+userRoleList+"::>>>roleName:"+roleName);
			//logger.info(">>>roleNameList:"+roleNameList);
			
		}
		
		for (Iterator iterator = entitlementMap.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<String, String> enrty = (Map.Entry) iterator.next();
			String entitlementShortDesc = (String) enrty.getKey();
			String status = (String) enrty.getValue();
			LOGGER.info(">>>entitlementShortDesc:"+entitlementShortDesc+"::"+status);
		}
		return entitlementMap;
	}
	
	
}
