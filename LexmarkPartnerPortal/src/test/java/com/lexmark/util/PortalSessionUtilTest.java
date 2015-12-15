package com.lexmark.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletSession;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockPortletRequest;
import org.springframework.mock.web.portlet.MockPortletSession;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.ServicesUser;
import com.lexmark.form.BaseForm;
import com.lexmark.result.LdapUserDataResult;


public class PortalSessionUtilTest {
	private MockPortletRequest request;
	private MockPortletSession session;
	private final String servicesUserMdmId = "mdmId_1";
	private final String servicesChlNodeId = "chlNodeId_1";
	private final String servicesEmailAddress = "Andy@lexmark.com";
	
	private final String ldapMdmId = "mdmId_2";
	private final String ldapContactId = "contactId_2";
	private final String ldapFirstname = "Andy";
	private final String ldapLastName = "Lau";
	private final String ldapWorkPhone = "15967127458";
	
	private final String empty = "";
	
	@Before
	public void setUp() throws Exception {
		request = new MockPortletRequest();
		session = new MockPortletSession();
	}
	/**
	 * test CrmSessionHandle
	 */
	@Ignore
	public  void testGetSiebelCrmSessionHandle(){
		PortalSessionUtil.getSiebelCrmSessionHandle(request);
	}
	/**
	 * test getMdmIdOrDunsBasedOnLevel start.
	 */
	@Test
	public void testGetMdmIdOrDunsBasedOnLevelLdapNull(){
		generateServicesUser(session, empty, empty, empty, empty);
		String mdmId = PortalSessionUtil.getMdmIdOrDunsBasedOnLevel(session);
		assertNull(mdmId);
	}
	@Test
	public void testGetMdmIdOrDunsBasedOnLevelServicesUserNull(){
		generateLdapData(session,ldapMdmId);
		generateServicesUser(session, empty, empty, empty, empty);
		String mdmId = PortalSessionUtil.getMdmIdOrDunsBasedOnLevel(session);
		assertTrue(ldapMdmId.equals(mdmId));
	}
	@Test
	public void testGetMdmIdOrDunsBasedOnLevel(){
		generateLdapData(session,ldapMdmId);
		generateServicesUser(session, servicesUserMdmId, LexmarkConstants.MDM_LEVEL_GLOBAL, empty, empty);
		String mdmId = PortalSessionUtil.getMdmIdOrDunsBasedOnLevel(session);
		assertTrue(servicesUserMdmId.equals(mdmId));
	}
	@Test
	public void testGetMdmIdOrDunsBasedOnLevelMdmLevelEmpty(){
		generateLdapData(session,ldapMdmId);
		generateServicesUser(session, servicesUserMdmId, empty, empty, empty);
		String mdmId = PortalSessionUtil.getMdmIdOrDunsBasedOnLevel(session);
		assertTrue(ldapMdmId.equals(mdmId));
	}
	/**
	 * test getMdmIdOrDunsBasedOnLevel end.
	 */
	
	/**
	 * test getMdmId start.
	 */
	@Test
	public void testGetMdmId(){
		generateServicesUser(session, servicesUserMdmId, empty, empty, empty);
		String mdmId = PortalSessionUtil.getMdmId(session);
		assertTrue(servicesUserMdmId.equals(mdmId));
	}
	@Test
	public void testGetMdmIdServicesUserNull(){
		generateLdapData(session,ldapMdmId);
		generateServicesUser(session, empty, empty, empty, empty);
		String mdmId = PortalSessionUtil.getMdmId(session);
		assertTrue(ldapMdmId.equals(mdmId));
	}
	@Test
	public void testGetMdmIdServicesUserNullLdapNull(){
		generateServicesUser(session, empty, empty, empty, empty);
		String mdmId = PortalSessionUtil.getMdmId(session);
		assertNull(mdmId);
	}
	/**
	 * test getMdmId end.
	 */
	
	/**
	 * test getMdmLevel start.
	 */
	@Test
	public void testGetMdmLevel(){
		generateServicesUser(session, servicesUserMdmId, LexmarkConstants.MDM_LEVEL_GLOBAL, empty, empty);
		String mdmLevel = PortalSessionUtil.getMdmLevel(session);
		assertTrue(LexmarkConstants.MDM_LEVEL_GLOBAL.equals(mdmLevel));
	}
	@Test
	public void testGetMdmLevelServicesUserLevelEmptyLdapNull(){
		generateServicesUser(session, servicesUserMdmId, empty, empty, empty);
		String mdmLevel = PortalSessionUtil.getMdmLevel(session);
		assertNull(mdmLevel);
	}
	@Test
	public void testGetMdmLevelServicesUserLevelEmpty(){
		generateServicesUser(session, servicesUserMdmId, empty, empty, empty);
		generateLdapData(session, ldapMdmId);
		String mdmLevel = PortalSessionUtil.getMdmLevel(session);
		assertTrue(LexmarkConstants.MDM_LEVEL_GLOBAL.equals(mdmLevel));
	}
	/**
	 * test getMdmLevel end.
	 */
	
	/**
	 * test getContactId start.
	 */
	@Test
	public void testGetContactIdLadapNull(){
		String contactId = PortalSessionUtil.getContactId(session);
		assertNull(contactId);
	}
	@Test
	public void testGetContactId(){
		generateLdapData(session, ldapMdmId);
		String contactId = PortalSessionUtil.getContactId(session);
		assertTrue(ldapContactId.equals(contactId));
	}
	/**
	 * test getContactId end.
	 */
	
	/**
	 * test getChlNodeId start.
	 */
	@Test
	public void testGetChlNodeId(){
		generateServicesUser(session, empty, empty, servicesChlNodeId, empty);
		String nodeId = PortalSessionUtil.getChlNodeId(session);
		assertTrue(servicesChlNodeId.equals(nodeId));
	}
	@Test
	public void testGetChlNodeIdChlNodeIdEmpty(){
		generateServicesUser(session, empty, empty, empty, empty);
		String nodeId = PortalSessionUtil.getChlNodeId(session);
		assertNull(nodeId);
	}
	/**
	 * test getChlNodeId end.
	 */
	
	/**
	 * test getFirstName start.
	 */
	@Test
	public void testGetFirstName(){
		generateLdapData(session, empty);
		String firstName = PortalSessionUtil.getFirstName(session);
		assertTrue(ldapFirstname.equals(firstName));
	}
	@Test
	public void testGetFirstNameLdapUserDataNull(){
		String firstName = PortalSessionUtil.getFirstName(session);
		assertNull(firstName);
	}
	/**
	 * test getFirstName end.
	 */
	
	/**
	 * test getLastName start.
	 */
	@Test
	public void testGetLastName(){
		generateLdapData(session, empty);
		String lastName = PortalSessionUtil.getLastName(session);
		assertTrue(ldapLastName.equals(lastName));
	}
	@Test
	public void testGetLastNameLdapUserDataNull(){
		String lastName = PortalSessionUtil.getLastName(session);
		assertNull(lastName);
	}
	/**
	 * test getLastName end.
	 */
	
	/**
	 * test getWorkPhone start.
	 */
	@Test
	public void testGetWorkPhone(){
		generateLdapData(session, empty);
		String workPhone = PortalSessionUtil.getWorkPhone(session);
		assertTrue(ldapWorkPhone.equals(workPhone));
	}
	@Test
	public void testGetWorkPhoneLdapUserDataNull(){
		String workPhone = PortalSessionUtil.getWorkPhone(session);
		assertNull(workPhone);
	}
	/**
	 * test getWorkPhone end.
	 */
	
	/**
	 * test getEmailAddress start.
	 */
	@Test
	public void testGetEmailAddress(){
		generateServicesUser(session, empty,empty,empty,servicesEmailAddress);
		String email = PortalSessionUtil.getEmailAddress(session);
		assertTrue(servicesEmailAddress.equals(email));
	}
	@Test
	public void testGetEmailAddressLdapEmailEmpty(){
		generateServicesUser(session, empty,empty,empty,empty);
		String email = PortalSessionUtil.getEmailAddress(session);
		assertNull(email);
	}
	/**
	 * test getEmailAddress end.
	 */
	
	/**
	 * test getUserRoles start.
	 */
	@Test
	public void testGetUserRoles(){
		List<String> Roles = new ArrayList<String>();
		Roles.add(LexmarkConstants.ROLE_ACCOUNT_ADMINISTRATOR);
		Roles.add(LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT);
		session.setAttribute(LexmarkConstants.USERROLES_PHASE2, Roles, session.APPLICATION_SCOPE);
		List<String> ldapUserRoles = PortalSessionUtil.getUserRoles(session);
		assertTrue(ldapUserRoles.size() == 2);
		assertTrue(LexmarkConstants.ROLE_ACCOUNT_ADMINISTRATOR.equals(ldapUserRoles.get(0)));
		assertTrue(LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT.equals(ldapUserRoles.get(1)));
		//assertTrue(ldapWorkPhone.equals(workPhone));
	}
	@Test
	public void testGetUserRolesLdapNull(){
		List<String> ldapUserRoles = PortalSessionUtil.getUserRoles(session);
		assertNull(ldapUserRoles);
	}
	/**
	 * test getUserRoles end.
	 */
	
	/**
	 * test getUserSegment start.
	 */
	@Test
	public void testGetUserSegment(){
		generateLdapData(session, empty);
		String segment = PortalSessionUtil.getUserSegment(session);
		assertTrue(LexmarkConstants.USER_SEGMENT_EMPLOYEE.equals(segment));
	}
	@Test
	public void testGetUserSegmentLdapNull(){
		String segment = PortalSessionUtil.getUserSegment(session);
		assertNull(segment);
	}
	/**
	 * test getUserSegment end.
	 */
	
	/**
	 * test createSubmitToken start.
	 */
	@Test
	public void testCreateSubmitToken(){
		generateLdapData(session, empty);
		Long token = PortalSessionUtil.createSubmitToken(request);
		assertNotNull(token);
		assertNotNull(request.getAttribute("UNIQUETOKENFORPAGEREFRESH"));
	}
	/**
	 * test createSubmitToken end.
	 */
	
	/**
	 * test createSubmitToken start.
	 */
	@Ignore
	public void testSetSiebelCrmSessionHandle(){
		
		request.setSession(session);
		PortalSessionUtil.setSiebelCrmSessionHandle(request, null);
	}
	/**
	 * test createSubmitToken end.
	 */
	
	/**
	 * test setServicesUser start.
	 */
	@Test
	public void testSetServicesUser(){
		ServicesUser servicesUser = new ServicesUser();
		servicesUser.setMdmId(servicesUserMdmId);
		PortalSessionUtil.setServicesUser(session,servicesUser);
		ServicesUser user = (ServicesUser)session.getAttribute(LexmarkConstants.SERVICES_USER_PHASE2,session.APPLICATION_SCOPE);
		assertTrue(servicesUserMdmId.equals(user.getMdmId()));
	}
	/**
	 * test setServicesUser end.
	 */
	
	/**
	 * test setLdapUserData start.
	 */
	@Test
	public void testSetLdapUserData(){
		LdapUserDataResult ldapUserDataResult = generateLdapUserDataResult("employee", "shortId");
		PortalSessionUtil.setLdapUserData(session,ldapUserDataResult);
		Map ldapUserData = (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2,session.APPLICATION_SCOPE);
		assertTrue("mdmid_3".equals(ldapUserData.get(LexmarkConstants.MDMID)));
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetLdapUserDataException(){
		LdapUserDataResult ldapUserDataResult = generateLdapUserDataResult("manager", "");
		PortalSessionUtil.setLdapUserData(session,ldapUserDataResult);
	}
	/**
	 * test setLdapUserData end.
	 */
	
	/**
	 * test isAccountAssociated start.
	 */
	@Test
	public void testIsAccountAssociatedSessionNull(){
		session = null;
		Boolean isAssociated = PortalSessionUtil.isAccountAssociated(session);
		assertFalse(isAssociated);
	}
	@Test
	public void testIsAccountAssociatedLexmarkFSEFlagFalseMdmIdEmpty(){
		session.setAttribute("flag.lexmarkFSEFlag", false, session.APPLICATION_SCOPE);
		session.setAttribute("employeeMdmId", empty, session.APPLICATION_SCOPE);
		session.setAttribute("employeeMdmLevel", "mdmLevel_4", session.APPLICATION_SCOPE);
		session.setAttribute("employeeAccountName", "accountName_4", session.APPLICATION_SCOPE);
		Boolean isAssociated = PortalSessionUtil.isAccountAssociated(session);
		assertFalse(isAssociated);
	}
	@Test
	public void testIsAccountAssociatedLexmarkFSEFlagFalse(){
		session.setAttribute("flag.lexmarkFSEFlag", false, session.APPLICATION_SCOPE);
		session.setAttribute("employeeMdmId", "mdmId_4", session.APPLICATION_SCOPE);
		session.setAttribute("employeeMdmLevel", "mdmLevel_4", session.APPLICATION_SCOPE);
		session.setAttribute("employeeAccountName", "accountName_4", session.APPLICATION_SCOPE);
		Boolean isAssociated = PortalSessionUtil.isAccountAssociated(session);
		ServicesUser servicesUser = (ServicesUser)session.getAttribute(LexmarkConstants.SERVICES_USER_PHASE2, session.APPLICATION_SCOPE);
		assertTrue(isAssociated);
		assertTrue("mdmId_4".equals(servicesUser.getMdmId()));
	}
	@Test
	public void testIsAccountAssociatedMdmIdNull(){
		session.setAttribute("flag.lexmarkFSEFlag", false, session.APPLICATION_SCOPE);
		session.setAttribute("employeeMdmId", empty, session.APPLICATION_SCOPE);
		session.setAttribute("employeeMdmLevel", "mdmLevel_4", session.APPLICATION_SCOPE);
		session.setAttribute("employeeAccountName", "accountName_4", session.APPLICATION_SCOPE);
		generateServicesUser(session, empty, empty, empty, empty);
		Boolean isAssociated = PortalSessionUtil.isAccountAssociated(session);
		assertFalse(isAssociated);
	}
	@Test
	public void testIsAccountAssociated(){
		session.setAttribute("flag.lexmarkFSEFlag", false, session.APPLICATION_SCOPE);
		session.setAttribute("employeeMdmId", "mdmId_4", session.APPLICATION_SCOPE);
		session.setAttribute("employeeMdmLevel", "mdmLevel_4", session.APPLICATION_SCOPE);
		session.setAttribute("employeeAccountName", "accountName_4", session.APPLICATION_SCOPE);
		generateServicesUser(session, empty, empty, empty, empty);
		Boolean isAssociated = PortalSessionUtil.isAccountAssociated(session);
		ServicesUser servicesUser = (ServicesUser)session.getAttribute(LexmarkConstants.SERVICES_USER_PHASE2, session.APPLICATION_SCOPE);
		assertTrue(isAssociated);
		assertTrue("mdmId_4".equals(servicesUser.getMdmId()));
	}
	@Test
	public void testIsAccountAssociatedEmployeeMdmIdNull(){
		session.setAttribute("flag.lexmarkFSEFlag", false, session.APPLICATION_SCOPE);
		session.setAttribute("employeeMdmId", empty, session.APPLICATION_SCOPE);
		session.setAttribute("employeeMdmLevel", "mdmLevel_4", session.APPLICATION_SCOPE);
		session.setAttribute("employeeAccountName", "accountName_4", session.APPLICATION_SCOPE);
		generateServicesUser(session, servicesUserMdmId, LexmarkConstants.MDM_LEVEL_GLOBAL, empty, empty);
		Boolean isAssociated = PortalSessionUtil.isAccountAssociated(session);
		assertTrue(isAssociated);
	}
	@Test
	public void testIsAccountAssociatedEmployeeMdmIdEquals(){
		session.setAttribute("flag.lexmarkFSEFlag", false, session.APPLICATION_SCOPE);
		session.setAttribute("employeeMdmId", servicesUserMdmId, session.APPLICATION_SCOPE);
		session.setAttribute("employeeMdmLevel", LexmarkConstants.MDM_LEVEL_GLOBAL, session.APPLICATION_SCOPE);
		session.setAttribute("employeeAccountName", "accountName_4", session.APPLICATION_SCOPE);
		generateServicesUser(session, servicesUserMdmId, LexmarkConstants.MDM_LEVEL_GLOBAL, empty, empty);
		Boolean isAssociated = PortalSessionUtil.isAccountAssociated(session);
		assertTrue(isAssociated);
	}
	@Test
	public void testIsAccountAssociatedEmployeeMdmIdNotEquals(){
		session.setAttribute("flag.lexmarkFSEFlag", false, session.APPLICATION_SCOPE);
		session.setAttribute("employeeMdmId", "mdmId_4", session.APPLICATION_SCOPE);
		session.setAttribute("employeeMdmLevel", "mdmLevel_4", session.APPLICATION_SCOPE);
		session.setAttribute("employeeAccountName", "accountName_4", session.APPLICATION_SCOPE);
		generateServicesUser(session, servicesUserMdmId, LexmarkConstants.MDM_LEVEL_GLOBAL, empty, empty);
		Boolean isAssociated = PortalSessionUtil.isAccountAssociated(session);
		ServicesUser servicesUser = (ServicesUser)session.getAttribute(LexmarkConstants.SERVICES_USER_PHASE2, session.APPLICATION_SCOPE);
		assertTrue(isAssociated);
		assertTrue("mdmId_4".equals(servicesUser.getMdmId()));
	}
	/**
	 * test isAccountAssociated end.
	 */
	
	/**
	 * test getDirectPartnerFlag start.
	 */
	@Test
	public void testGetDirectPartnerFlagSessionNull(){
		session = null;
		Boolean flag = PortalSessionUtil.getDirectPartnerFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetDirectPartnerFlagNull(){
		Boolean flag = PortalSessionUtil.getDirectPartnerFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetDirectPartnerFlagTrue(){
		session.setAttribute("flag.directPartnerFlag", true, session.APPLICATION_SCOPE);
		Boolean flag = PortalSessionUtil.getDirectPartnerFlag(session);
		assertTrue(flag);
	}
	/**
	 * test getDirectPartnerFlag end.
	 */
	
	/**
	 * test setDirectPartnerFlag start.
	 */
	@Test
	public void testSetDirectPartnerFlagSessionNull(){
		session = null;
		PortalSessionUtil.setDirectPartnerFlag(session,null);
	}
	@Test
	public void testSetDirectPartnerFlag(){
		PortalSessionUtil.setDirectPartnerFlag(session,true);
		Boolean flag = (Boolean)session.getAttribute("flag.directPartnerFlag", session.APPLICATION_SCOPE);
		assertTrue(flag);
	}
	/**
	 * test setDirectPartnerFlag end.
	 */
	
	/**
	 * test getIndirectPartnerFlag start.
	 */
	@Test
	public void testGetIndirectPartnerFlagSessionNull(){
		session = null;
		Boolean flag = PortalSessionUtil.getIndirectPartnerFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetIndirectPartnerFlagNull(){
		Boolean flag = PortalSessionUtil.getIndirectPartnerFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetIndirectPartnerFlagTrue(){
		session.setAttribute("flag.indirectPartnerFlag", true, session.APPLICATION_SCOPE);
		Boolean flag = PortalSessionUtil.getIndirectPartnerFlag(session);
		assertTrue(flag);
	}
	/**
	 * test getIndirectPartnerFlag end.
	 */
	
	/**
	 * test setIndirectPartnerFlag start.
	 */
	@Test
	public void testSetIndirectPartnerFlagSessionNull(){
		session = null;
		PortalSessionUtil.setIndirectPartnerFlag(session,null);
	}
	@Test
	public void testSetIndirectPartnerFlag(){
		PortalSessionUtil.setIndirectPartnerFlag(session,true);
		Boolean flag = (Boolean)session.getAttribute("flag.indirectPartnerFlag", session.APPLICATION_SCOPE);
		assertTrue(flag);
	}
	/**
	 * test setIndirectPartnerFlag end.
	 */
	
	/**
	 * test getlogisticsPartnerFlag start.
	 */
	@Test
	public void testGetlogisticsPartnerFlagSessionNull(){
		session = null;
		Boolean flag = PortalSessionUtil.getlogisticsPartnerFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetlogisticsPartnerFlagNull(){
		Boolean flag = PortalSessionUtil.getlogisticsPartnerFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetlogisticsPartnerFlagTrue(){
		session.setAttribute("flag.logiticsPartnerFlag", true, session.APPLICATION_SCOPE);
		Boolean flag = PortalSessionUtil.getlogisticsPartnerFlag(session);
		assertTrue(flag);
	}
	/**
	 * test getlogisticsPartnerFlag end.
	 */
	
	/**
	 * test setlogisticsPartnerFlag start.
	 */
	@Test
	public void testSetlogisticsPartnerFlagSessionNull(){
		session = null;
		PortalSessionUtil.setlogisticsPartnerFlag(session,null);
	}
	@Test
	public void testSetlogisticsPartnerFlag(){
		PortalSessionUtil.setlogisticsPartnerFlag(session,true);
		Boolean flag = (Boolean)session.getAttribute("flag.logiticsPartnerFlag", session.APPLICATION_SCOPE);
		assertTrue(flag);
	}
	/**
	 * test setlogisticsPartnerFlag end.
	 */
	
	/**
	 * test getLexmarkEmployeeFlag start.
	 */
	@Test
	public void testGetLexmarkEmployeeFlagSessionNull(){
		session = null;
		Boolean flag = PortalSessionUtil.getLexmarkEmployeeFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetLexmarkEmployeeFlagNull(){
		Boolean flag = PortalSessionUtil.getLexmarkEmployeeFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetLexmarkEmployeeFlagTrue(){
		session.setAttribute("flag.lexmarkEmployeeFlag", true, session.APPLICATION_SCOPE);
		Boolean flag = PortalSessionUtil.getLexmarkEmployeeFlag(session);
		assertTrue(flag);
	}
	/**
	 * test getLexmarkEmployeeFlag end.
	 */
	
	/**
	 * test setLexmarkEmployeeFlag start.
	 */
	@Test
	public void testSetLexmarkEmployeeFlagSessionNull(){
		session = null;
		PortalSessionUtil.setLexmarkEmployeeFlag(session,null);
	}
	@Test
	public void testSetLexmarkEmployeeFlag(){
		PortalSessionUtil.setLexmarkEmployeeFlag(session,true);
		Boolean flag = (Boolean)session.getAttribute("flag.lexmarkEmployeeFlag", session.APPLICATION_SCOPE);
		assertTrue(flag);
	}
	/**
	 * test setLexmarkEmployeeFlag end.
	 */
	
	/**
	 * test getLexmarkFSEFlag start.
	 */
	@Test
	public void testGetLexmarkFSEFlagSessionNull(){
		session = null;
		Boolean flag = PortalSessionUtil.getLexmarkFSEFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetLexmarkFSEFlagNull(){
		Boolean flag = PortalSessionUtil.getLexmarkFSEFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetLexmarkFSEFlagTrue(){
		session.setAttribute("flag.lexmarkFSEFlag", true, session.APPLICATION_SCOPE);
		Boolean flag = PortalSessionUtil.getLexmarkFSEFlag(session);
		assertTrue(flag);
	}
	/**
	 * test getLexmarkFSEFlag end.
	 */
	
	/**
	 * test setLexmarkFSEFlag start.
	 */
	@Test
	public void testSetLexmarkFSEFlagSessionNull(){
		session = null;
		PortalSessionUtil.setLexmarkFSEFlag(session,null);
	}
	@Test
	public void testSetLexmarkFSEFlag(){
		PortalSessionUtil.setLexmarkFSEFlag(session,true);
		Boolean flag = (Boolean)session.getAttribute("flag.lexmarkFSEFlag", session.APPLICATION_SCOPE);
		assertTrue(flag);
	}
	/**
	 * test setLexmarkFSEFlag end.
	 */
	
	/**
	 * test getCreateClaimFlag start.
	 */
	@Test
	public void testGetCreateClaimFlagSessionNull(){
		session = null;
		Boolean flag = PortalSessionUtil.getCreateClaimFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetCreateClaimFlagNull(){
		Boolean flag = PortalSessionUtil.getCreateClaimFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetCreateClaimFlagTrue(){
		session.setAttribute("flag.createClaimFlag", true, session.APPLICATION_SCOPE);
		Boolean flag = PortalSessionUtil.getCreateClaimFlag(session);
		assertTrue(flag);
	}
	/**
	 * test getCreateClaimFlag end.
	 */
	
	/**
	 * test setCreateClaimFlag start.
	 */
	@Test
	public void testSetCreateClaimFlagSessionNull(){
		session = null;
		PortalSessionUtil.setCreateClaimFlag(session,null);
	}
	@Test
	public void testSetCreateClaimFlag(){
		PortalSessionUtil.setCreateClaimFlag(session,true);
		Boolean flag = (Boolean)session.getAttribute("flag.createClaimFlag", session.APPLICATION_SCOPE);
		assertTrue(flag);
	}
	/**
	 * test setCreateClaimFlag end.
	 */
	
	/**
	 * test getAllowAdditionalPaymentRequestFlag start.
	 */
	@Test
	public void testGetAllowAdditionalPaymentRequestFlagSessionNull(){
		session = null;
		Boolean flag = PortalSessionUtil.getAllowAdditionalPaymentRequestFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetAllowAdditionalPaymentRequestFlagNull(){
		Boolean flag = PortalSessionUtil.getAllowAdditionalPaymentRequestFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetAllowAdditionalPaymentRequestFlagTrue(){
		session.setAttribute("flag.allowAdditionalPaymentRequestFlag", true, session.APPLICATION_SCOPE);
		Boolean flag = PortalSessionUtil.getAllowAdditionalPaymentRequestFlag(session);
		assertTrue(flag);
	}
	/**
	 * test getAllowAdditionalPaymentRequestFlag end.
	 */
	
	/**
	 * test setAllowAdditionalPaymentRequestFlag start.
	 */
	@Test
	public void testSetAllowAdditionalPaymentRequestFlagSessionNull(){
		session = null;
		PortalSessionUtil.setAllowAdditionalPaymentRequestFlag(session,null);
	}
	@Test
	public void testSetAllowAdditionalPaymentRequestFlag(){
		PortalSessionUtil.setAllowAdditionalPaymentRequestFlag(session,true);
		Boolean flag = (Boolean)session.getAttribute("flag.allowAdditionalPaymentRequestFlag", session.APPLICATION_SCOPE);
		assertTrue(flag);
	}
	/**
	 * test setAllowAdditionalPaymentRequestFlag end.
	 */
	
	/**
	 * test isDuplicatedSubmit start.
	 */
	@Test
	public void testIsDuplicatedSubmitTokenNull(){
		request.setSession(session);
		Boolean isDuplicate = PortalSessionUtil.isDuplicatedSubmit(request,null);
		assertTrue(isDuplicate);
	}
	@Test
	public void testIsDuplicatedSubmitEquals(){
		BaseForm form = new BaseForm();
		form.setSubmitToken(123l);
		session.setAttribute(LexmarkConstants.SUBMIT_TOKEN, 123l, session.PORTLET_SCOPE);
		request.setSession(session);
		Boolean isDuplicate = PortalSessionUtil.isDuplicatedSubmit(request,form);
		assertFalse(isDuplicate);
	}
	@Test
	public void testIsDuplicatedSubmitNotEquals(){
		BaseForm form = new BaseForm();
		form.setSubmitToken(1234l);
		session.setAttribute(LexmarkConstants.SUBMIT_TOKEN, 123l, session.PORTLET_SCOPE);
		request.setSession(session);
		Boolean isDuplicate = PortalSessionUtil.isDuplicatedSubmit(request,form);
		assertTrue(isDuplicate);
	}
	/**
	 * test isDuplicatedSubmit end.
	 */
	
	/**
	 * test retrieveLoginAccountContact start.
	 */
	@Test
	public void testRetrieveLoginAccountContact(){
		generateLdapData(session, ldapMdmId);
		generateServicesUser(session, empty, empty, empty, servicesEmailAddress);
		request.setSession(session);
		AccountContact contact = PortalSessionUtil.retrieveLoginAccountContact(request);
		assertTrue(ldapContactId.equals(contact.getContactId()));
		assertTrue(ldapFirstname.equals(contact.getFirstName()));
		assertTrue(ldapLastName.equals(contact.getLastName()));
		assertTrue(ldapWorkPhone.equals(contact.getWorkPhone()));
		assertTrue(servicesEmailAddress.equals(contact.getEmailAddress()));
	}
	
	/**
	 * test retrieveLoginAccountContact end.
	 */
	
	/**
	 * test setCreateClaimStartTime start.
	 */
	@Test
	public void testSetCreateClaimStartTimeSessionNull(){
		session = null;
		PortalSessionUtil.setCreateClaimStartTime(session,null);
	}
	@Test
	public void testSetCreateClaimStartTime(){
		Calendar time = Calendar.getInstance();
		PortalSessionUtil.setCreateClaimStartTime(session,time);
		Calendar sessionTime = (Calendar)session.getAttribute("omniture.createClaimStart", session.APPLICATION_SCOPE);
		assertTrue(time.getTime().toString().equals(sessionTime.getTime().toString()));
	}
	/**
	 * test setCreateClaimStartTime end.
	 */
	
	/**
	 * test getCreateClaimStartTime start.
	 */
	@Test
	public void testGetCreateClaimStartTimeSessionNull(){
		session = null;
		Calendar sessionTime = PortalSessionUtil.getCreateClaimStartTime(session);
		assertNull(sessionTime);
	}
	@Test
	public void testGetCreateClaimStartTime(){
		Calendar time = Calendar.getInstance();
		session.setAttribute("omniture.createClaimStart", time, session.APPLICATION_SCOPE);
		Calendar sessionTime = PortalSessionUtil.getCreateClaimStartTime(session);
		assertTrue(time.getTime().toString().equals(sessionTime.getTime().toString()));
	}
	/**
	 * test getCreateClaimStartTime end.
	 */
	
	/**
	 * test getUploadClaimFlag start.
	 */
	@Test
	public void testGetUploadClaimFlagSessionNull(){
		session = null;
		Boolean flag = PortalSessionUtil.getUploadClaimFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetUploadClaimFlagNull(){
		Boolean flag = PortalSessionUtil.getUploadClaimFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetUploadClaimFlagTrue(){
		session.setAttribute("flag.uploadClaimFlag", true, session.APPLICATION_SCOPE);
		Boolean flag = PortalSessionUtil.getUploadClaimFlag(session);
		assertTrue(flag);
	}
	/**
	 * test getUploadClaimFlag end.
	 */
	
	/**
	 * test setUploadClaimFlag start.
	 */
	@Test
	public void testSetUploadClaimFlagSessionNull(){
		session = null;
		PortalSessionUtil.setUploadClaimFlag(session,null);
	}
	@Test
	public void testSetUploadClaimFlag(){
		PortalSessionUtil.setUploadClaimFlag(session,true);
		Boolean flag = (Boolean)session.getAttribute("flag.uploadClaimFlag", session.APPLICATION_SCOPE);
		assertTrue(flag);
	}
	/**
	 * test setUploadClaimFlag end.
	 */
	
	/**
	 * test getUploadRequestFlag start.
	 */
	@Test
	public void testGetUploadRequestFlagSessionNull(){
		session = null;
		Boolean flag = PortalSessionUtil.getUploadRequestFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetUploadRequestFlagNull(){
		Boolean flag = PortalSessionUtil.getUploadRequestFlag(session);
		assertFalse(flag);
	}
	@Test
	public void testGetUploadRequestFlagTrue(){
		session.setAttribute("flag.uploadRequestFlag", true, session.APPLICATION_SCOPE);
		Boolean flag = PortalSessionUtil.getUploadRequestFlag(session);
		assertTrue(flag);
	}
	/**
	 * test getUploadRequestFlag end.
	 */
	
	/**
	 * test setUploadRequestFlag start.
	 */
	@Test
	public void testSetUploadRequestFlagSessionNull(){
		session = null;
		PortalSessionUtil.setUploadRequestFlag(session,null);
	}
	@Test
	public void testSetUploadRequestFlag(){
		PortalSessionUtil.setUploadRequestFlag(session,true);
		Boolean flag = (Boolean)session.getAttribute("flag.uploadRequestFlag", session.APPLICATION_SCOPE);
		assertTrue(flag);
	}
	/**
	 * test setUploadRequestFlag end.
	 */
	private LdapUserDataResult generateLdapUserDataResult(String userSegment, String shortId){
		LdapUserDataResult ldapUserDataResult = new LdapUserDataResult();
		ldapUserDataResult.setMdmId("mdmid_3");
		ldapUserDataResult.setMdmLevel("mdmlevel_3");
		ldapUserDataResult.setContactId("contactid_3");
		ldapUserDataResult.setUserNumber("userNumber_3");
		ldapUserDataResult.setFirstName("andy");
		ldapUserDataResult.setLastName("Lau");
		ldapUserDataResult.setWorkPhone("15967127895");
		ldapUserDataResult.setLanguage("English");
		ldapUserDataResult.setCountry("USA");
		ldapUserDataResult.setEmailAddress("andy@lexmark.com");
		ldapUserDataResult.setUserSegment(userSegment);
		ldapUserDataResult.setShortId(shortId);
		
		return ldapUserDataResult;
	}
	private void generateServicesUser(PortletSession session, String mdmId, String mdmLevel, String chlNodeId, String email){
		ServicesUser servicesUser = new ServicesUser();
		servicesUser.setMdmId(mdmId);
		servicesUser.setMdmLevel(mdmLevel);
		servicesUser.setChlNodeId(chlNodeId);
		servicesUser.setEmailAddress(email);
		session.setAttribute(LexmarkConstants.SERVICES_USER_PHASE2, servicesUser, session.APPLICATION_SCOPE);
	}
	private void generateLdapData(PortletSession session, String mdmId){
		Map<String, String> ldapUserData = new HashMap<String, String>();
		ldapUserData.put(LexmarkConstants.MDMID, mdmId);
		ldapUserData.put(LexmarkConstants.MDMLEVEL, LexmarkConstants.MDM_LEVEL_GLOBAL);
		ldapUserData.put(LexmarkConstants.CONTACTID, ldapContactId);
		ldapUserData.put(LexmarkConstants.FIRSTNAME, ldapFirstname);
		ldapUserData.put(LexmarkConstants.LASTNAME, ldapLastName);
		ldapUserData.put(LexmarkConstants.WORKPHONE, ldapWorkPhone);
		ldapUserData.put(LexmarkConstants.USERSEGMENT, LexmarkConstants.USER_SEGMENT_EMPLOYEE);
		session.setAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, ldapUserData, session.APPLICATION_SCOPE);
	}
}
