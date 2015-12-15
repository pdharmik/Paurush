/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: EntitlementRoleMappingService
 * Package     		: com.lexmark.service.api
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
package com.lexmark.service.api;

import java.util.HashMap;
import java.util.List;

import com.googlecode.ehcache.annotations.Cacheable;
import com.lexmark.domain.EntitlementRoleMapping;


public interface EntitlementRoleMappingService {

	@Cacheable(cacheName="retrieveRoles4Customer", keyGeneratorName="myKeyGenerator")
	public List<String> retrieveRoles4Customer() throws Exception;
	@Cacheable(cacheName="retrieveRoles4Partner", keyGeneratorName="myKeyGenerator")
	public List<String> retrieveRoles4Partner() throws Exception;
	@Cacheable(cacheName="retrieveRoles4Entitlement", keyGeneratorName="myKeyGenerator")
	public HashMap<String, EntitlementRoleMapping> retrieveRoles4Entitlement() throws Exception;
}

