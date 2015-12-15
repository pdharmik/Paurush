package com.lexmark.reportScheduler.service;

import org.junit.Test;

import com.lexmark.domain.ServicesUser;
import com.lexmark.reportScheduler.service.impl.UserInfoServiceImpl;

public class UserInfoServiceTest  {

	@Test
	public void testGetUserInfo() throws Exception {
	    UserInfoServiceImpl  service = new UserInfoServiceImpl();
	    ServicesUser user = service.getUserInfo("BP10000591", "robinson.joanie@yahoo.com");
	    }

}
