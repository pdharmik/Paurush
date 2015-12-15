package com.lexmark.service.impl.mock;

import java.util.ArrayList;
import java.util.List;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.LdapUserDataContract;
import com.lexmark.contract.ServicesUserContract;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.LdapUserDataResult;
import com.lexmark.result.ServicesUserResult;
import com.lexmark.service.api.UserService;


public class UserServiceImpl implements UserService  {

	
	public ServicesUserResult retrieveServicesUser(ServicesUserContract contract) throws Exception{
		ServicesUserResult result = new ServicesUserResult();
		ServicesUser servicesUser = new ServicesUser();
		servicesUser.setUserNumber(contract.getUserNumber());
		if (!"bruno@7cogs.com".equals(contract.getEmailAddress()) &&
				!"test@liferay.com".equals(contract.getEmailAddress()) && 
				!"test@lexmark.com".equals(contract.getEmailAddress()) &&
				!"portal1@lexmark.com".equals(contract.getEmailAddress())) {
			//servicesUser.setChlNodeId("1-3F3QGI");
		}
		servicesUser.setEmailAddress(contract.getEmailAddress());
//		servicesUser.setMdmId("L23456");
//		servicesUser.setMdmId("43210");//has service requests
//		servicesUser.setMdmLevel(LexmarkConstants.LEGAL);
		result.setServicesUser(servicesUser);
		return result;	
	}
	
	public LdapUserDataResult retrieveLdapUserData(LdapUserDataContract contract) throws Exception {
		for (LdapUserDataResult result : PartnerDomainMockDataGenerator.getLdapUserDataResultList()) {
			if (contract.getEmailAddress().equals(result.getEmailAddress())) {
				return result;
			}
		}
		// in case no LdapUserDataResult found in mock list.
		LdapUserDataResult result = new LdapUserDataResult();
		List<String> userRoles = new ArrayList();
//		result.setMdmId("L1-1DTJNV");
//		result.setMdmId("L1-L4BTZC");
//		result.setMdmId("12864");
		result.setMdmId("LE6789");
		result.setMdmLevel(LexmarkConstants.LEGAL);
//		result.setMdmLevel(LexmarkConstants.DOMESTIC);
		result.setShortId("bruno@7cogs.com");
		result.setContactId("1-QZ27F0");
		String userNumber;
		String emailAddress = contract.getEmailAddress();
		if (emailAddress.equals("bruno@7cogs.com") || emailAddress.equals("test@liferay.com")
				|| emailAddress.equals("test@lexmark.com") || emailAddress.equals("portal1@lexmark.com")
				|| emailAddress.equals("boeing_global@lexmark.com")) {
			result.setFirstName("Bruno");
			result.setLastName("Bruno");
			result.setUserSegment("partner");
			userNumber = "1-QZ27F0";
			userRoles.add(LexmarkConstants.ROLE_SERVICE_TECHNICIAN);
			userRoles.add(LexmarkConstants.ROLE_SERVICE_MANAGER);
			userRoles.add(LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR);
		} else if (emailAddress.equals("technician@7cogs.com")){
			userNumber = "BP12346";
			result.setFirstName("Technician");
			result.setLastName("Technician");
			result.setUserSegment("employee");
			userRoles.add(LexmarkConstants.ROLE_SERVICE_TECHNICIAN);
		} else {
			userNumber = "BP12347";
			result.setFirstName("Employee");
			result.setLastName("Employee");
			result.setUserSegment("employee");
			userRoles.add(LexmarkConstants.ROLE_PUBLISHING);
		}
		
		result.setLanguage("en");
		result.setCountry("USA");
		result.setEmailAddress(emailAddress);
	
		result.setWorkPhone("13588366666");
		result.setUserNumber(userNumber);
		userRoles.add(LexmarkConstants.ROLE_STANDARD_ACCESS);
		userRoles.add(LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT);
		userRoles.add(LexmarkConstants.ROLE_BILLING);
		userRoles.add(LexmarkConstants.ROLE_PUBLISHING);
		result.setUserRoles(userRoles);
		return result;
	}
	
}
