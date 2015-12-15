package com.lexmark.util;

import static org.easymock.EasyMock.anyLong;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockPortletRequest;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.Account;
import com.lexmark.exception.LoginException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;

public class LexmarkUserUtilTest extends EasyMockSupport {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = LoginException.class)
	public void testGetUserEmailAddressNullUserInfo() throws Exception {
		LexmarkUserUtil.getUserEmailAddress(new MockPortletRequest());
	}

	@Test(expected = Exception.class)
	public void testGetUserEmailAddressEmptyEmail() throws Exception {
		MockPortletRequest request = new MockPortletRequest();
		Map<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("liferay.user.id", "888");
		request.setAttribute(PortletRequest.USER_INFO, userInfo);
		LexmarkUserUtil.getUserEmailAddress(request);
	}

	@Test
	public void testGetUserEmailAddressSuccess() throws Exception {
		UserLocalService userLocalService = createMock(UserLocalService.class);
		User user = createMock(User.class);
		expect(user.getEmailAddress()).andReturn("peter@lexmark.com");
		expect(userLocalService.getUserById(anyLong())).andReturn(user).once();
		UserLocalServiceUtil userLocalServiceUtil = new UserLocalServiceUtil();
		userLocalServiceUtil.setService(userLocalService);
		replayAll();
		MockPortletRequest request = new MockPortletRequest();
		Map<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("liferay.user.id", "888");
		request.setAttribute(PortletRequest.USER_INFO, userInfo);
		assertEquals("peter@lexmark.com", LexmarkUserUtil.getUserEmailAddress(request));

		verifyAll();
	}

	@Test(expected = LoginException.class)
	public void testGetUserIdNullUserInfo() throws Exception {
		LexmarkUserUtil.getUserId(new MockPortletRequest());
	}

	@Test
	public void testGetUserIdSuccess() throws Exception {
		MockPortletRequest request = new MockPortletRequest();
		Map<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("liferay.user.id", "888");
		request.setAttribute(PortletRequest.USER_INFO, userInfo);
		assertEquals("888", LexmarkUserUtil.getUserId(request));
	}

	@Test
	public void testGetUserRoleNameList() throws Exception {
		MockPortletRequest request = new MockPortletRequest();
		PortletSession session = request.getPortletSession();
		ArrayList<String> roleList = new ArrayList<String>();
		session.setAttribute(LexmarkConstants.USERROLES_PHASE2, roleList, PortletSession.APPLICATION_SCOPE);
		assertTrue(LexmarkUserUtil.getUserRoleNameList(request) == roleList);

	}

	@Test
	public void testIsPublishingTrue() {
		MockPortletRequest request = new MockPortletRequest();
		PortletSession session = request.getPortletSession();
		session.setAttribute(LexmarkConstants.USERROLES_PHASE2, Arrays.asList(LexmarkConstants.ROLE_PUBLISHING,
				LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT), PortletSession.APPLICATION_SCOPE);
		assertTrue(LexmarkUserUtil.isPublishing(request));
	}

	@Test
	public void testIsPublishingFalse() {
		MockPortletRequest request = new MockPortletRequest();
		PortletSession session = request.getPortletSession();
		session.setAttribute(LexmarkConstants.USERROLES_PHASE2, Arrays.asList(
				LexmarkConstants.ROLE_ACCOUNT_ADMINISTRATOR, LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT),
				PortletSession.APPLICATION_SCOPE);
		assertFalse(LexmarkUserUtil.isPublishing(request));
	}

	@Test
	public void testIsTechnicianTrue() {
		MockPortletRequest request = new MockPortletRequest();
		PortletSession session = request.getPortletSession();
		session.setAttribute(LexmarkConstants.USERROLES_PHASE2, Arrays.asList(LexmarkConstants.ROLE_SERVICE_TECHNICIAN,
				LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT), PortletSession.APPLICATION_SCOPE);
		assertTrue(LexmarkUserUtil.isTechnician(request));
	}

	@Test
	public void testIsTechnicianFalse() {
		MockPortletRequest request = new MockPortletRequest();
		PortletSession session = request.getPortletSession();
		session.setAttribute(LexmarkConstants.USERROLES_PHASE2, Arrays.asList(LexmarkConstants.ROLE_SERVICE_SUPPORT,
				LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT), PortletSession.APPLICATION_SCOPE);
		assertFalse(LexmarkUserUtil.isTechnician(request));
	}

	@Test
	public void testSetUserFlagsInSession() {
		MockPortletRequest request = new MockPortletRequest();
		PortletSession session = request.getPortletSession();
		ArrayList<Account> accountList = new ArrayList<Account>();
		Account account1 = new Account();
		account1.setCreateClaimFlag(true);
		account1.setIndirectPartnerFlag(true);
		account1.setLogisticsPartnerFlag(true);
		accountList.add(account1);
		Account account2 = new Account();
		account2.setDirectPartnerFlag(true);
		account2.setAllowAdditionalPaymentRequestFlag(true);
		account2.setUploadClaimFlag(true);
		account2.setUploadRequestFlag(true);
		accountList.add(account2);

		LexmarkUserUtil.setUserFlagsInSession(session, accountList);
		assertEquals(true, session.getAttribute("flag.directPartnerFlag", PortletSession.APPLICATION_SCOPE));
		assertEquals(true, session.getAttribute("flag.indirectPartnerFlag", PortletSession.APPLICATION_SCOPE));
		assertEquals(true, session.getAttribute("flag.logiticsPartnerFlag", PortletSession.APPLICATION_SCOPE));
		assertEquals(true, session.getAttribute("flag.createClaimFlag", PortletSession.APPLICATION_SCOPE));
		assertEquals(true, session.getAttribute("flag.uploadClaimFlag", PortletSession.APPLICATION_SCOPE));
		assertEquals(true, session.getAttribute("flag.uploadRequestFlag", PortletSession.APPLICATION_SCOPE));
	}

}
