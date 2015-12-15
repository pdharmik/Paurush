package com.lexmark.service.api;

import com.lexmark.domain.ServicesUser;
/**
 * This interface is used to get the user information :MdmId, MdmLevel and ChlNodeId from Serivce_User table and
 * LDAP
 * @author Bruce.Wang
 *
 */
public interface UserInfoService {
	public ServicesUser getUserInfo(String userNumber, String userEmail);
}
