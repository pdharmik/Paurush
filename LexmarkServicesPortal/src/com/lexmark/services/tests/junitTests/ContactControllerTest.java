/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ContactControllerTest.java
 * Package     		: com.lexmark.services.tests.junitTests
 * Creation Date 	: 16th July 2013
 *
 * Modification History:
 *
 * --------------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * --------------------------------------------------------------------------
 * wipro			16th July 2013 		     1.0             Initial Version
 
 *
 */

package com.lexmark.services.tests.junitTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockPortletRequest;
import org.springframework.mock.web.portlet.MockPortletResponse;
import org.springframework.mock.web.portlet.MockPortletSession;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.ContactListResult;
import com.lexmark.services.form.validator.CommonValidator;
import com.lexmark.services.portlet.common.ContactController;

/**.
 * This Class is designed to test the Contact details methods of ContactController.java
 * @author wipro
 * 
 */

public class ContactControllerTest {
	
	private ContactController controller;
	private MockResourceRequest resourceRequest = new MockResourceRequest();
	private MockResourceResponse resourceResponse = new MockResourceResponse();
	private Model model;
	private MockRenderRequest renderRequest = new MockRenderRequest();
	private MockRenderResponse renderResponse = new MockRenderResponse();
	private MockPortletRequest portletRequest = new MockPortletRequest();
	private MockPortletResponse portletResponse = new MockPortletResponse();
	private MockPortletSession mockSession;
	ServicesUser serviceUser;
	
	/**
	 * @throws Exception 
	 */
	@Before
	public void setUp() throws Exception {
		/**
		 * Autowiring and resource binding
		 **/
			controller = new ContactController();
			ReflectionTestUtils.setField(controller, "commonValidator",new CommonValidator());
			
		/**
		 * Autowiring and resource binding end
		 **/

			model = createMock(Model.class);
			
			mockSession=new MockPortletSession();
			
	}
	
	/**
	 * @param mockModelClass 
	 * @return Model 
	 */
	private Model createMock(Class<Model> mockModelClass) {

		return new Model() {

			@Override
			public Model mergeAttributes(Map<String, ?> arg0) {
				return null;
			}

			@Override
			public boolean containsAttribute(String arg0) {
				return false;
			}

			@Override
			public Map<String, Object> asMap() {
				return null;
			}

			@Override
			public Model addAttribute(String arg0, Object arg1) {
				return null;
			}

			@Override
			public Model addAttribute(Object arg0) {
				return null;
			}

			@Override
			public Model addAllAttributes(Map<String, ?> arg0) {
				return null;
			}

			@Override
			public Model addAllAttributes(Collection<?> arg0) {
				return null;
			}
		};
	
}

	/**
	 * Destroying objects
	 **/
	 
	/**
	 * @throws Exception 
	 */
	@After
	public void tearDown() throws Exception {
		model = null;
		renderRequest = null;
		resourceRequest = null;
	}
	
	/**
	 * Destroying objects end
	 **/
	
	
	/**
	 * Test Cases Start
	 **/
	 
	/**
	 * 
	 */
	@Test
	public void retrieveContactListTest(){
		String favFlag = "fav";
		ServicesUser servicesUser=new ServicesUser();
		servicesUser.setEmailAddress("xxx@abx.com");
		servicesUser.setUserNumber("123userNumber");
		servicesUser.setMdmId("1234");
		servicesUser.setMdmLevel("L1");
		 mockSession.setAttribute(LexmarkConstants.SERVICES_USER, servicesUser,
				 mockSession.APPLICATION_SCOPE);
		 ContactListResult result =new ContactListResult();

		 result.setTotalCount(789);
	
		 resourceRequest.setSession(mockSession);
		 resourceRequest.setParameter("isPopUp"," true");
		 resourceRequest.setParameter("isPopUp"," false");
		 
		 
		 List<String> reqTypeValues = new ArrayList<String>();
			reqTypeValues.add("Consumables Management");
			reqTypeValues.add("Fleet Management");
			reqTypeValues.add( "BreakFix");
			resourceRequest.setParameter("reqTypeValues", "others");
			resourceRequest.setParameter("requestedFrom", "ALL_REQUESTS");
			
			Map<String, Object>  filterCriteria = new HashMap<String, Object>();
			filterCriteria.put("requestType", reqTypeValues);
			resourceRequest.setAttribute("filterCriteria", filterCriteria);
		controller.retrieveContactList(resourceRequest, resourceResponse, model, favFlag);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void setContactFavouriteFlagTest() throws Exception{
		String contactId = "12345";
		String favoriteContactId = "98765";
		Boolean flagStatus = true;
		controller.setContactFavouriteFlag(contactId, favoriteContactId, flagStatus, resourceRequest, resourceResponse);
	}
	
	/**
	 * 
	 */
	@Test
	public void showContactListPopUpTest(){
		
		String contactListPopUp = controller.showContactListPopUp(renderRequest, renderResponse, model);
		assertEquals("Ok", "common/selectContact", contactListPopUp);
	}
	
	/**
	 * 
	 */
	@Test
	public void getContactInfoTest(){
		
		/**
		 * Account Contact Information
		 **/
		AccountContact accountContact = new AccountContact();
		accountContact.setContactId("123456");
		accountContact.setFirstName("First Name");
		accountContact.setLastName("Last Name");
		accountContact.setWorkPhone("123456789");
		accountContact.setAlternatePhone("987654321");
		accountContact.setEmailAddress("test@test.com");
		/**
		 * Account Contact Information end
		 **/
		portletRequest.setAttribute("accountContact", accountContact);
		
		controller.getContactInfo(portletRequest, portletResponse);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void contactPopupReviewTest() throws Exception{
		
		Map<String,String[]> contactPopupValuesMap = new HashMap<String, String[]>();
		String firstName[] = {"fname1","fname2","fname3"};
		String lastName[] = {"lname1","lname2","lname3"};
		String workPhone[] = {"123456789","987654321","456987123"};
		String alternatePhone[] = {"987654321","123456789","654321789"};
		String emailId[] = {"abc@gmail.com","test@gmail.com","xyz@gmail.com"};
		contactPopupValuesMap.put("firstName", firstName);
		contactPopupValuesMap.put("lastName", lastName);
		contactPopupValuesMap.put("workPhone", workPhone);
		contactPopupValuesMap.put("alternatePhone", alternatePhone);
		contactPopupValuesMap.put("emailId", emailId);
		resourceRequest.setParameters(contactPopupValuesMap);		 
		controller.contactPopupReview(model, resourceRequest, resourceResponse);
		
	}
	
	/**
	 * Test Cases End
	 **/
	 
}
