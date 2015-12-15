package com.lexmark.interceptor;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockPortletSession;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;

import com.lexmark.util.PortalSessionUtil;

public class UserInfoInterceptorTest {

	MockRenderRequest request = new MockRenderRequest();
	MockRenderResponse response = new MockRenderResponse();
	MockPortletSession session = new MockPortletSession();
	MyUserInfoInterceptor  info;
	class MyUserInfoInterceptor extends UserInfoInterceptor {
		public void test(PortletRequest request, PortletResponse response, Object handler) throws Exception {
			super.preHandle(request, response, handler);
		}
		
		protected String getUserEmail(PortletRequest portletRequest) throws Exception {
			return this.tempEmail;
		}
		private String tempEmail;
		public void setTempEmail(String email) {
			this.tempEmail = email;
		}
	}

	@Before
	public void setup() {
		info = new MyUserInfoInterceptor();
		info.setUserService(new com.lexmark.service.impl.mock.UserServiceImpl());
		info.setGlobalService(new com.lexmark.service.impl.mock.GlobalServiceImpl());

		request = new MockRenderRequest();
		response = new MockRenderResponse();
		session = new MockPortletSession();
		request.setSession(session);
	}
	@After
	public void cleanup() {
		request = null;
		response = null;
		session = null;
		request = null;
	}
	
	@Test
	public void testPartner() throws Exception {
		info.setTempEmail("bruno@7cogs.com");
		info.test(request, response, null);
		
		Assert.assertTrue(PortalSessionUtil.getCreateClaimFlag(session));
		Assert.assertTrue(PortalSessionUtil.getDirectPartnerFlag(session));
		Assert.assertTrue(PortalSessionUtil.getIndirectPartnerFlag(session));
		Assert.assertTrue(PortalSessionUtil.getlogisticsPartnerFlag(session));
		Assert.assertFalse(PortalSessionUtil.getLexmarkEmployeeFlag(session));
		
	}
	
	@Test
	public void testTechnician() throws Exception {
		info.setTempEmail("technician@7cogs.com");
		info.test(request, response, null);

		Assert.assertTrue(PortalSessionUtil.getLexmarkFSEFlag(session));
		Assert.assertTrue(PortalSessionUtil.getLexmarkEmployeeFlag(session));
	}
	
	@Test
	public void testEmployee() throws Exception {
		info.setTempEmail("employee@7cogs.com");
		info.test(request, response, null);

		Assert.assertFalse(PortalSessionUtil.getLexmarkFSEFlag(session));
		Assert.assertTrue(PortalSessionUtil.getLexmarkEmployeeFlag(session));
	}
	

}
