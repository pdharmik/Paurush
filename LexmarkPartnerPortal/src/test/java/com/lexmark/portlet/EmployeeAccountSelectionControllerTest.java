package com.lexmark.portlet;


import static org.easymock.EasyMock.anyLong;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;

import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.mock.web.portlet.MockActionResponse;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.FSEAccountListContract;
import com.lexmark.contract.PartnerAccountListContract;
import com.lexmark.contract.ProcessCustomerContactContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.domain.ServicesUser;
import com.lexmark.form.EmployeeAccountSelectionForm;
import com.lexmark.result.FSEAccountListResult;
import com.lexmark.result.PartnerAccountListResult;
import com.lexmark.result.ProcessCustomerContactResult;
import com.lexmark.service.api.GlobalLegalEntityService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.util.TestUtil;
import com.lexmark.webservice.api.ProcessCustomerContact;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalService;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;

public class EmployeeAccountSelectionControllerTest extends EasyMockSupport{
	private GlobalService globalService;
	private GlobalLegalEntityService globalLegalEntityService;
	private ProcessCustomerContact processCustomerContract;
	private Portal portal;
	private RoleLocalService service;
	private UserLocalService userLocalservice;
	private EmployeeAccountSelectionController controller;
	
	@Before
	public void setUp() throws Exception {
		globalService = createMock(GlobalService.class);
		globalLegalEntityService = createMock(GlobalLegalEntityService.class);
		processCustomerContract = createMock(ProcessCustomerContact.class);
		portal = createMock(Portal.class);
		service = createMock(RoleLocalService.class);
		userLocalservice = createMock(UserLocalService.class);
		controller = new EmployeeAccountSelectionController();
		TestUtil.setProperty(controller,"globalService",globalService);
		TestUtil.setProperty(controller,"globalLegalEntityService",globalLegalEntityService);
		TestUtil.setProperty(controller,"processCustomerContract",processCustomerContract);
		resetAll();
	}

	/**
	 * start showEmployeeAccountSelectionPage.
	 * @throws Exception 
	 */
	@Test
	public void testShowEmployeeAccountSelectionPage() throws Exception{
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		controller.showEmployeeAccountSelectionPage(model, request, response);
		assertNotNull(model.asMap().get("employeeProblem"));
		assertNotNull(model.asMap().get("showFseSelector"));
		assertNull(model.asMap().get("selectedAccount"));
	}
	@Test
	public void testShowEmployeeAccountSelectionPageSiebelIdAvailable() throws Exception{
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		Map<String, String> ldapUserData = new HashMap<String, String>();
		ldapUserData.put(LexmarkConstants.CONTACTID, "");
		request.getPortletSession().setAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, ldapUserData, request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("flag.lexmarkFSEFlag", true, request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute(LexmarkConstants.USERROLES_PHASE2, generateRoles(LexmarkConstants.ROLE_PUBLISHING), request.getPortletSession().APPLICATION_SCOPE);
		controller.showEmployeeAccountSelectionPage(model, request, response);
		assertNotNull(model.asMap().get("employeeProblem"));
		assertNotNull(model.asMap().get("showFseSelector"));
		assertNull(model.asMap().get("selectedAccount"));
	}
	
	@Test
	public void testShowEmployeeAccountSelectionPageShowFseSelector() throws Exception{
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		Map<String, String> ldapUserData = new HashMap<String, String>();
		ldapUserData.put(LexmarkConstants.CONTACTID, "");
		request.getPortletSession().setAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, ldapUserData, request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("flag.lexmarkFSEFlag", true, request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute(LexmarkConstants.USERROLES_PHASE2, generateRoles(LexmarkConstants.ROLE_ACCOUNT_ADMINISTRATOR), request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("siebelIdAvailable", "true", request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeMdmId", "accountId", request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeMdmLevel", "mdmLevel1", request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeAccountName", "accountName1", request.getPortletSession().APPLICATION_SCOPE);
		expect(globalService.retrieveFSEAccountList((FSEAccountListContract)anyObject())).andReturn(generateFSEAccountListResult("accountId"));
		replayAll();
		controller.showEmployeeAccountSelectionPage(model, request, response);
		verifyAll();
		assertNotNull(model.asMap().get("employeeProblem"));
		assertNotNull(model.asMap().get("showFseSelector"));
		assertNotNull(model.asMap().get("selectedAccount"));
		assertNotNull(model.asMap().get("fSEAccountListResult"));
		
	}
	
	@Test
	public void testShowEmployeeAccountSelectionPageShowFseSelectorFalse() throws Exception{
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		Map<String, String> ldapUserData = new HashMap<String, String>();
		ldapUserData.put(LexmarkConstants.CONTACTID, "");
		request.getPortletSession().setAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, ldapUserData, request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("flag.lexmarkFSEFlag", false, request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute(LexmarkConstants.USERROLES_PHASE2, generateRoles(LexmarkConstants.ROLE_ACCOUNT_ADMINISTRATOR), request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeMdmId", "accountId", request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeMdmLevel", "mdmLevel1", request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeAccountName", "accountName1", request.getPortletSession().APPLICATION_SCOPE);
		expect(globalService.retrievePartnerAccountList((PartnerAccountListContract)anyObject())).andReturn(generatePartnerAccountListResult("accountId"));
		replayAll();
		controller.showEmployeeAccountSelectionPage(model, request, response);
		verifyAll();
		assertNotNull(model.asMap().get("employeeProblem"));
		assertNotNull(model.asMap().get("showFseSelector"));
		assertNotNull(model.asMap().get("selectedAccount"));
		
	}
	
	@Test
	public void testShowEmployeeAccountSelectionPageServiceUser() throws Exception{
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		Map<String, String> ldapUserData = new HashMap<String, String>();
		ldapUserData.put(LexmarkConstants.CONTACTID, "");
		request.getPortletSession().setAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, ldapUserData, request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("flag.lexmarkFSEFlag", false, request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute(LexmarkConstants.USERROLES_PHASE2, generateRoles(LexmarkConstants.ROLE_ACCOUNT_ADMINISTRATOR), request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeMdmId", "accountId", request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeMdmLevel", "mdmLevel1", request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeAccountName", "accountName1", request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute(LexmarkConstants.SERVICES_USER_PHASE2, generateServiceUser(), request.getPortletSession().APPLICATION_SCOPE);
		expect(globalService.retrievePartnerAccountList((PartnerAccountListContract)anyObject())).andReturn(generatePartnerAccountListResult("accountId"));
		replayAll();
		controller.showEmployeeAccountSelectionPage(model, request, response);
		verifyAll();
		assertNotNull(model.asMap().get("employeeProblem"));
		assertNotNull(model.asMap().get("showFseSelector"));
		assertNotNull(model.asMap().get("selectedAccount"));
		
	}
	@Test
	public void testShowEmployeeAccountSelectionPageServiceUserTrue() throws Exception{
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		Map<String, String> ldapUserData = new HashMap<String, String>();
		ldapUserData.put(LexmarkConstants.CONTACTID, "");
		request.getPortletSession().setAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, ldapUserData, request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("flag.lexmarkFSEFlag", true, request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute(LexmarkConstants.USERROLES_PHASE2, generateRoles(LexmarkConstants.ROLE_ACCOUNT_ADMINISTRATOR), request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("siebelIdAvailable", "true", request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeMdmId", "accountId", request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeMdmLevel", "mdmLevel1", request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeAccountName", "accountName1", request.getPortletSession().APPLICATION_SCOPE);
		request.getPortletSession().setAttribute(LexmarkConstants.SERVICES_USER_PHASE2, generateServiceUser(), request.getPortletSession().APPLICATION_SCOPE);
		expect(globalService.retrieveFSEAccountList((FSEAccountListContract)anyObject())).andReturn(generateFSEAccountListResult("accountId"));
		replayAll();
		controller.showEmployeeAccountSelectionPage(model, request, response);
		verifyAll();
		assertNotNull(model.asMap().get("employeeProblem"));
		assertNotNull(model.asMap().get("showFseSelector"));
		assertNotNull(model.asMap().get("selectedAccount"));
		assertNotNull(model.asMap().get("fSEAccountListResult"));
		
	}
	/**
	 * end showEmployeeAccountSelectionPage.
	 */
	
	/**
	 * start retrieveGlobalLegalEntityList.
	 * @throws Exception 
	 */
	@Test
	public void testRetrieveGlobalLegalEntityListRequireServiceCallNull() throws Exception{
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		expect(globalLegalEntityService.getGlobalAccounts()).andReturn(generateGlobalAccount());
		replayAll();
		controller.retrieveGlobalLegalEntityList(request, response);
		verifyAll();
	}
	@Test
	public void testRetrieveGlobalLegalEntityListRequireServiceCallNullMask() throws Exception{
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		request.setParameter("mask", "mdmId");
		expect(globalLegalEntityService.getGlobalAccounts()).andReturn(generateGlobalAccount());
		replayAll();
		controller.retrieveGlobalLegalEntityList(request, response);
		verifyAll();
	}
	@Test
	public void testRetrieveGlobalLegalEntityList() throws Exception{
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		request.setParameter("requireLdapUpdate", "require");
		request.getPortletSession().setAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, generateUserData(), request.getPortletSession().APPLICATION_SCOPE);
		ProcessCustomerContactResult result = new ProcessCustomerContactResult();
		result.setSiebelContactId("sielbelId1");
		expect(processCustomerContract.processCustomerContact((ProcessCustomerContactContract)anyObject())).andReturn(result);
		replayAll();
		controller.retrieveGlobalLegalEntityList(request, response);
		verifyAll();
		Map<String, String> ldapUserData = (Map<String, String>)request.getPortletSession().getAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, request.getPortletSession().APPLICATION_SCOPE);
		assertTrue("sielbelId1".equals(ldapUserData.get(LexmarkConstants.CONTACTID)));
	}
	@Test
	public void testRetrieveGlobalLegalEntityListSoketException() throws Exception{
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		request.setParameter("requireLdapUpdate", "require");
		request.getPortletSession().setAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, generateUserData(), request.getPortletSession().APPLICATION_SCOPE);
		SocketTimeoutException exception = new SocketTimeoutException();
		expect(processCustomerContract.processCustomerContact((ProcessCustomerContactContract)anyObject())).andThrow(exception);
		replayAll();
		controller.retrieveGlobalLegalEntityList(request, response);
		verifyAll();
	}
	@Test
	public void testRetrieveGlobalLegalEntityListException() throws Exception{
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		request.setParameter("requireLdapUpdate", "require");
		request.getPortletSession().setAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, generateUserData(), request.getPortletSession().APPLICATION_SCOPE);
		Exception exception = new Exception();
		expect(processCustomerContract.processCustomerContact((ProcessCustomerContactContract)anyObject())).andThrow(exception);
		replayAll();
		controller.retrieveGlobalLegalEntityList(request, response);
		verifyAll();
	}
	/**
	 * end retrieveGlobalLegalEntityList.
	 */
	
	/**
	 * start submitEmployeeAccountSelection.
	 * @throws Exception 
	 */
	@Test
	public void testSubmitEmployeeAccountSelectionException() throws Exception{
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		EmployeeAccountSelectionForm employeeAccountSelectionForm = new EmployeeAccountSelectionForm();
		employeeAccountSelectionForm.setLegalName("legalName");
		BindingResult result = null;
		request.getPortletSession().setAttribute(LexmarkConstants.USERROLES_PHASE2, generateRoles(LexmarkConstants.ROLE_PUBLISHING), request.getPortletSession().APPLICATION_SCOPE);
		expect(globalLegalEntityService.getGlobalAccounts()).andReturn(generateGlobalAccount());
		expect(globalService.retrievePartnerAccountList((PartnerAccountListContract)anyObject())).andReturn(generatePartnerAccountListResult("accountId"));
		replayAll();
		controller.submitEmployeeAccountSelection(request, response, employeeAccountSelectionForm, result, model);
		verifyAll();
	}
	@Test
	public void testSubmitEmployeeAccountSelection() throws Exception{
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		EmployeeAccountSelectionForm employeeAccountSelectionForm = new EmployeeAccountSelectionForm();
		employeeAccountSelectionForm.setLegalName("legalName");
		BindingResult result = null;
		request.getPortletSession().setAttribute(LexmarkConstants.USERROLES_PHASE2, generateRoles(LexmarkConstants.ROLE_SERVICE_MANAGER), request.getPortletSession().APPLICATION_SCOPE);
		mockUserData();
		mockRoleData(LexmarkConstants.ROLE_UPLOAD_REQUEST);
		mockDeleteRoleData();
		expect(globalLegalEntityService.getGlobalAccounts()).andReturn(generateGlobalAccount());
		expect(globalService.retrievePartnerAccountList((PartnerAccountListContract)anyObject())).andReturn(generatePartnerAccountListResult("accountId"));
		replayAll();
		controller.submitEmployeeAccountSelection(request, response, employeeAccountSelectionForm, result, model);
		verifyAll();
	}
	
	@Test
	public void testSubmitEmployeeAccountSelectionProcessPartnerUpload() throws Exception{
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		EmployeeAccountSelectionForm employeeAccountSelectionForm = new EmployeeAccountSelectionForm();
		employeeAccountSelectionForm.setLegalName("legalName");
		BindingResult result = null;
		request.getPortletSession().setAttribute(LexmarkConstants.USERROLES_PHASE2, generateRoles(LexmarkConstants.ROLE_SERVICE_MANAGER), request.getPortletSession().APPLICATION_SCOPE);
		mockUserData();
		mockRoleData(LexmarkConstants.ROLE_UPLOAD_CLAIM);
		mockDeleteRoleData();
		expect(globalLegalEntityService.getGlobalAccounts()).andReturn(generateGlobalAccount());
		expect(globalService.retrievePartnerAccountList((PartnerAccountListContract)anyObject())).andReturn(generatePartnerAccountListResult("accountId"));
		replayAll();
		controller.submitEmployeeAccountSelection(request, response, employeeAccountSelectionForm, result, model);
		verifyAll();
	}
	@Test
	public void testSubmitEmployeeAccountSelectionShowFseSelector() throws Exception{
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		EmployeeAccountSelectionForm employeeAccountSelectionForm = new EmployeeAccountSelectionForm();
		employeeAccountSelectionForm.setLegalName("legalName");
		employeeAccountSelectionForm.setShowFseSelector(true);
		employeeAccountSelectionForm.setAccountNumber("accountId");
		BindingResult result = null;
		expect(globalService.retrieveFSEAccountList((FSEAccountListContract)anyObject())).andReturn(generateFSEAccountListResult("accountId"));
		request.getPortletSession().setAttribute(LexmarkConstants.USERROLES_PHASE2, generateRoles(LexmarkConstants.ROLE_SERVICE_MANAGER), request.getPortletSession().APPLICATION_SCOPE);
		mockUserData();
		mockRoleData(LexmarkConstants.ROLE_UPLOAD_CLAIM);
		mockDeleteRoleData();
		replayAll();
		controller.submitEmployeeAccountSelection(request, response, employeeAccountSelectionForm, result, model);
		verifyAll();
	}
	/**
	 * end submitEmployeeAccountSelection.
	 * @throws com.liferay.portal.kernel.exception.PortalException 
	 * @throws com.liferay.portal.kernel.exception.SystemException 
	 */
	private void mockUserData() throws com.liferay.portal.kernel.exception.PortalException, com.liferay.portal.kernel.exception.SystemException{
		new PortalUtil().setPortal(portal);
		User user = createMock(User.class);
		expect(user.getUserId()).andReturn(100L);
		expect(user.getCompanyId()).andReturn(200L);
		expect(portal.getUser((PortletRequest)anyObject())).andReturn(user);
	}
	private void mockRoleData(String roleName) throws com.liferay.portal.kernel.exception.SystemException{
		new RoleLocalServiceUtil().setService(service);
		List<Role> roleList = new ArrayList<Role>();
		Role role1 = createMock(Role.class);
		roleList.add(role1);
		expect(role1.getName()).andReturn(roleName);
		expect(role1.getPrimaryKey()).andReturn(300L);
		expect(service.getRoles(200L)).andReturn(roleList);
	}
	private void mockDeleteRoleData() throws com.liferay.portal.kernel.exception.PortalException, com.liferay.portal.kernel.exception.SystemException{
		new UserLocalServiceUtil().setService(userLocalservice);
		userLocalservice.deleteRoleUser(anyLong(), anyLong());
		expectLastCall().anyTimes();
		userLocalservice.addRoleUsers(anyLong(), (long[])anyObject());
		expectLastCall().anyTimes();
	}
	private Map<String, String> generateUserData(){
		Map<String, String> userData = new HashMap<String, String>();
		userData.put(LexmarkConstants.COUNTRY, "China");
		userData.put(LexmarkConstants.USERNUMBER, "number1");
		userData.put(LexmarkConstants.FIRSTNAME, "andy");
		userData.put(LexmarkConstants.LASTNAME, "zhao");
		userData.put(LexmarkConstants.EMAIL, "zhaoyang@gmail.com");
		userData.put(LexmarkConstants.WORKPHONE, "1234567896854");
		userData.put(LexmarkConstants.SHORTID, "zhao1659");
		return userData;
	}
	private List<GlobalAccount> generateGlobalAccount(){
		List<GlobalAccount> entityList = new ArrayList<GlobalAccount>();
		GlobalAccount account = new GlobalAccount();
		account.setDisplayMdmId("mdmId");
		account.setLegalName("legalName");
		entityList.add(account);
		return entityList;
	}
	private FSEAccountListResult generateFSEAccountListResult(String accountId){
		FSEAccountListResult retrieveFSEAccountList = new FSEAccountListResult();
		List<Account> accountList = new ArrayList<Account>();
		Account account = new Account();
		account.setAccountId(accountId);
		accountList.add(account);
		retrieveFSEAccountList.setAccountList(accountList);
		return retrieveFSEAccountList;
	}
	private PartnerAccountListResult generatePartnerAccountListResult(String accountId){
		PartnerAccountListResult partnerAccountListResult = new PartnerAccountListResult();
		List<Account> accountList = new ArrayList<Account>();
		Account account = new Account();
		account.setAccountId(accountId);
		account.setUploadClaimFlag(true);
		account.setUploadRequestFlag(true);
		accountList.add(account);
		partnerAccountListResult.setAccountList(accountList);
		return partnerAccountListResult;
	}
	private ServicesUser generateServiceUser(){
		ServicesUser serviceUser = new ServicesUser();
		serviceUser.setMdmId("mdmid");
		serviceUser.setMdmLevel("mdmlevel");
		return serviceUser;
	}
	private List<String> generateRoles(String role){
		List<String> ldapUserRoles = new ArrayList<String>();
		ldapUserRoles.add(role);
//		ldapUserRoles.add(LexmarkConstants.ROLE_STANDARD_ACCESS);
//		ldapUserRoles.add(LexmarkConstants.ROLE_SERVICE_SUPPORT);
//		ldapUserRoles.add(LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT);
//		ldapUserRoles.add(LexmarkConstants.ROLE_BILLING);
//		ldapUserRoles.add(LexmarkConstants.ROLE_PROJECT_MANAGEMENT);
//		ldapUserRoles.add(LexmarkConstants.ROLE_ANALYST);
//		ldapUserRoles.add(LexmarkConstants.ROLE_ACCOUNT_ADMINISTRATOR);
//		ldapUserRoles.add(LexmarkConstants.ROLE_SERVICES_PORTAL_ADMINISTRATOR);
//		ldapUserRoles.add(LexmarkConstants.ROLE_PUBLISHING);
//		ldapUserRoles.add(LexmarkConstants.ROLE_PROJECT_MANAGEMENT);
//		ldapUserRoles.add(LexmarkConstants.ROLE_SERVICE_TECHNICIAN);
//		ldapUserRoles.add(LexmarkConstants.ROLE_SERVICE_MANAGER);
//		ldapUserRoles.add(LexmarkConstants.ROLE_ACCOUNTS_RECEIVABLE);
//		ldapUserRoles.add(LexmarkConstants.ROLE_SERVICE_ADMINISTRATOR);
//		ldapUserRoles.add(LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR);
		return ldapUserRoles;
	}
//	private void generateLdapData(PortletSession session, String mdmId){
//		Map<String, String> ldapUserData = new HashMap<String, String>();
//		ldapUserData.put(LexmarkConstants.MDMID, mdmId);
//		ldapUserData.put(LexmarkConstants.MDMLEVEL, LexmarkConstants.MDM_LEVEL_GLOBAL);
//		ldapUserData.put(LexmarkConstants.CONTACTID, ldapContactId);
//		ldapUserData.put(LexmarkConstants.FIRSTNAME, ldapFirstname);
//		ldapUserData.put(LexmarkConstants.LASTNAME, ldapLastName);
//		ldapUserData.put(LexmarkConstants.WORKPHONE, ldapWorkPhone);
//		ldapUserData.put(LexmarkConstants.USERSEGMENT, LexmarkConstants.USER_SEGMENT_EMPLOYEE);
//		session.setAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, ldapUserData, session.APPLICATION_SCOPE);
//	}
}
