package com.lexmark.service.api;

import com.lexmark.contract.LdapUserDataContract;
import com.lexmark.contract.ServicesUserContract;
import com.lexmark.result.LdapUserDataResult;
import com.lexmark.result.ServicesUserResult;

public interface UserService {

	public ServicesUserResult retrieveServicesUser(ServicesUserContract contract)
			throws Exception;
	
	public LdapUserDataResult retrieveLdapUserData(LdapUserDataContract contract)
			throws Exception;
}
