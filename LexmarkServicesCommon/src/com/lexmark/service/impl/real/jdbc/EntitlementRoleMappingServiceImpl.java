/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: EntitlementRoleMappingServiceImpl
 * Package     		: com.lexmark.service.impl.real.jdbc
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
package com.lexmark.service.impl.real.jdbc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.lexmark.domain.EntitlementRoleMapping;
import com.lexmark.domain.Role;
import com.lexmark.service.api.EntitlementRoleMappingService;

public class EntitlementRoleMappingServiceImpl implements
		EntitlementRoleMappingService {
	private static Logger LOGGER = LogManager.getLogger(EntitlementRoleMappingServiceImpl.class);
	private static final String QUERY_RETRIVE_CUSTOMER_ROLES = "select name  from role where target_portal =''Customer''";
	private static final String QUERY_RETRIVE_PARTNER_ROLES = "select name  from role where target_portal =''Partner''";
	private static final String QUERY_RETRIVE_ENTITLEMENT_ROLES = "select * from ENTITLEMENT_ROLE";
	

	/**
	 * This method is to retrieve customer roles 
	 * 
	 * @return List<String> 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> retrieveRoles4Customer()
			throws Exception {
		List<String> customerRoles= null;
				
		try {
			//HibernateUtil.beginTransaction();
			Query query = HibernateUtil.getSession().createSQLQuery(QUERY_RETRIVE_CUSTOMER_ROLES);
			customerRoles = query.list();
			//HibernateUtil.commitTransaction();
			LOGGER.info(">>>customerRoles:::"+customerRoles.size());
			
			
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return customerRoles;
	}
	
	/**
	 * This method is to retrieve partner roles 
	 * 
	 * @return List<String> 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> retrieveRoles4Partner()
			throws Exception {
		List<String> partnerRoles= null;
				
		try {
			//HibernateUtil.beginTransaction();
			Query query = HibernateUtil.getSession().createSQLQuery(QUERY_RETRIVE_PARTNER_ROLES);
			partnerRoles = query.list();
			//HibernateUtil.commitTransaction();
			LOGGER.info(">>>partnerRoles:::"+partnerRoles.size());
			
			
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return partnerRoles;
	}
	
	
	
	/**
	 * This method is to retrieve entitlement role mapping list
	 * 
	 * @return HashMap<String, EntitlementRoleMapping> 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, EntitlementRoleMapping> retrieveRoles4Entitlement()
			throws Exception {
		HashMap<String, EntitlementRoleMapping> entitlementRoleMap= null;
				
		try {
			//HibernateUtil.beginTransaction();
			Query query = HibernateUtil.getSession().createSQLQuery(QUERY_RETRIVE_ENTITLEMENT_ROLES);
			List list = query.list();
			//HibernateUtil.commitTransaction();
			LOGGER.info(">>>list:::"+list.size());
			entitlementRoleMap= retrieveEntitlement(list);
			
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return entitlementRoleMap;
	}
	
	/**
	 * This method is to do the following: a. GetentitlementRoleList which contains
	 * Entitlement name(Functionality)And roles supported for each entitlement.
	 * b. Create a map object with key as "Entitlement Name" and value as List of 
	 * "Role" objects
	 * 
	 * @param entitlementRoleList
	 * @return HashMap<String, EntitlementRoleMapping> 
	 * @throws Exception
	 */
	
	@SuppressWarnings("unchecked")
	private static HashMap<String, EntitlementRoleMapping>  retrieveEntitlement( List entitlementRoleList)throws Exception{
		HashMap<String, EntitlementRoleMapping> entitlementRoleMap = new LinkedHashMap<String, EntitlementRoleMapping>();
		if(entitlementRoleList != null && entitlementRoleList.size() > 0) {
		
			for(int i = 0; i < entitlementRoleList.size(); i++){
				Object[] row = (Object[]) entitlementRoleList.get(i);
				String entitlementShortDesc = (String)row[1];
				
				if(entitlementShortDesc != null && entitlementRoleMap.containsKey(entitlementShortDesc)){
					
					EntitlementRoleMapping entitlementRoleMapping = entitlementRoleMap.get(entitlementShortDesc);
					Role role = new Role();
					role.setId(Integer.valueOf(row[2].toString()));
					role.setName(row[3].toString());
					role.setTargetPortal(row[4].toString());
					role.setRoleType(row[5].toString());
					entitlementRoleMapping.getRoleList().add(role);
					
				}else{
					EntitlementRoleMapping entitlementRoleMapping = new EntitlementRoleMapping();
					entitlementRoleMapping.setEntitlementId(Integer.valueOf(row[0].toString()));
					entitlementRoleMapping.setShortDesc(row[1].toString());			
					Role role = new Role();
					role.setId(Integer.valueOf(row[2].toString()));
					role.setName(row[3].toString());
					role.setTargetPortal(row[4].toString());
					role.setRoleType(row[5].toString());
					entitlementRoleMapping.getRoleList().add(role);
					entitlementRoleMap.put((String)row[1], entitlementRoleMapping);
				}
			}
		}
		return entitlementRoleMap;
	}

}
