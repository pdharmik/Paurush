package com.lexmark.services.tests;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockPortletRequest;
import org.springframework.mock.web.portlet.MockPortletSession;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.services.intercepter.UserInfoInterceptor;

public class UserInfoInterceptorTest {
	private UserInfoInterceptor target;
	private PortletRequest request;
	private PortletSession session;
	/**
	 * 
	 */
	@Before 
	public void setup() {
		target = new UserInfoInterceptor();
		
		session = new MockPortletSession();
		request = new MockPortletRequest() {
			public PortletSession getSession() {
				return session;
			}
		};
		
		target.setUserService(new com.lexmark.service.impl.mock.UserServiceImpl());
	}
	
	/**
	 * @throws Exception 
	 */
	@Test 
	@Ignore 
	public void testPreHandlePortletRequestPortletResponseObject()throws Exception{
		//target.preHandle(request, null, null);
		Assert.assertNotNull(session.getAttribute(LexmarkConstants.SERVICES_USER, session.APPLICATION_SCOPE ));
	}

}
