/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: CatalogControllerTest.java
 * Package     		: com.lexmark.services.tests.junitTests
 * Creation Date 	: 16th July 2013
 *
 * Modification History:
 *
 * --------------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * --------------------------------------------------------------------------
 * Sudipta			16th July 2013 		     1.0             Initial Version
 
 *
 */

package com.lexmark.junitTests;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.fileupload.FileItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.mock.web.portlet.MockActionResponse;
import org.springframework.mock.web.portlet.MockPortletSession;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.support.SessionStatus;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.RequestListContract;
import com.lexmark.contract.UserGridSettingContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServicesUser;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.form.FileUploadForm;
import com.lexmark.form.RequestListForm;
import com.lexmark.portlet.GridSettingController;
import com.lexmark.portlet.MassUploadController;
import com.lexmark.portlet.common.AccountSelectionController;
import com.lexmark.result.RequestResult;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.impl.mock.GlobalServiceImpl;
import com.lexmark.service.impl.mock.ServiceRequestLocaleImpl;
import com.lexmark.service.impl.mock.UserGridSettingServiceImpl;
import com.lexmark.service.impl.real.AmindAttachmentService;
import com.lexmark.service.impl.real.AmindPartnerOrderService;
import com.lexmark.form.RequestListForm;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ControllerUtil;

import com.lexmark.webservice.impl.real.MassUploadServiceImpl;

/**
 * . This Class is designed to test the Asset Catalog details methods of
 * OrderSuppliesCatalogController.java
 * 
 * @author Sudipta
 * 
 */

public class MassUploadDetailsTest {
	private static final Object ContractFactory = null;
	private MassUploadController target;
	private Model mockModel;
	private BindingResult BindingResult;
	private MockRenderRequest renderRequest;
	private MockRenderResponse renderResponse;
	private MockActionRequest actionrequest;
	private MockActionResponse actionresponse;
	private MockPortletSession mockSession;
	private Map<String, Object> ldapUserData;
	private Map<String, String> accDetails;
	private ModelMap modelmap;
	private ServicesUser servicesUser;
	private SessionStatus mockSessionStatus;
	private RequestListForm requestform = new RequestListForm();
	private FileUploadForm fileUploadForm = new FileUploadForm();
	private MockResourceRequest resourceReq;
	private MockResourceResponse resourceRes;
	private HashMap<String, String> webServiceCallValues = new HashMap<String, String>();
	private ListOfValues lov = new ListOfValues();
	private RequestResult reqResult = new RequestResult();
	private Map<String, String> userAccessMapForSr = new HashedMap();
	private List<String> USERROLES = new ArrayList<String>();
	private RequestListContract downLoadContract;
	private Map<String, Object> filterCriteria = new HashMap<String, Object>();
	List<Attachment> attachment;
	FileItem fileItem;

	@Before
	public void setUp() throws Exception {
		/**
		 * Autowiring and resource binding
		 **/
		target = new MassUploadController();
		GridSettingController gridController = new GridSettingController();

		ReflectionTestUtils.setField(gridController, "userGridSettingService",
				new UserGridSettingServiceImpl());

		ReflectionTestUtils.setField(target, "partnerOrderService",
				new AmindPartnerOrderService());
		ReflectionTestUtils.setField(target, "globalService",
				new GlobalServiceImpl());
		ReflectionTestUtils.setField(target, "gridSettingController",
				gridController);
		ReflectionTestUtils.setField(target, "serviceRequestLocaleService",
				new ServiceRequestLocaleImpl());
		ReflectionTestUtils.setField(target, "attachmentService",
				new AmindAttachmentService());
		ReflectionTestUtils.setField(target, "massUploadService",
				new MassUploadServiceImpl());
		ReflectionTestUtils.setField(target, "accountSelectionController",
				new AccountSelectionController());
		ReflectionTestUtils.setField(target, "webServiceCallValues",
				new HashMap<String, String>());

		/**
		 * Autowiring and resource binding end
		 **/

		renderRequest = new MockRenderRequest();
		renderResponse = new MockRenderResponse();
		mockSessionStatus = new SessionStatus() {

			@Override
			public boolean isComplete() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setComplete() {
				// TODO Auto-generated method stub

			}

		};
		mockModel = createMock(Model.class);
		BindingResult = createMockBindingResult(BindingResult.class);
		renderRequest = new MockRenderRequest();
		renderResponse = new MockRenderResponse();
		actionrequest = new MockActionRequest();
		actionresponse = new MockActionResponse();
		mockSession = new MockPortletSession();
		resourceReq = new MockResourceRequest();
		resourceRes = new MockResourceResponse();
		renderRequest.addLocale(Locale.US);
		resourceReq.addLocale(Locale.US);
		ldapUserData = new HashedMap();
		/**
		 * ldap Details Information
		 **/
		ldapUserData.put(LexmarkConstants.MDMID, "xxx Mdmid");
		ldapUserData.put(LexmarkConstants.MDMLEVEL, "xxx MdmLevel");
		ldapUserData.put(LexmarkConstants.CONTACTID, "xxx ContactId");
		ldapUserData.put(LexmarkConstants.USERNUMBER, "xxx User Name");
		ldapUserData.put(LexmarkConstants.FIRSTNAME, "xxx First Name");
		ldapUserData.put(LexmarkConstants.LASTNAME, "xxx Last Name");
		ldapUserData.put(LexmarkConstants.WORKPHONE, "0123456789");
		ldapUserData.put(LexmarkConstants.LANGUAGE, "English");
		ldapUserData.put(LexmarkConstants.COUNTRY, "india");
		ldapUserData.put(LexmarkConstants.EMAIL, "abc@xyx.com");
		ldapUserData.put(LexmarkConstants.USERSEGMENT, "xxx Usersegment");
		ldapUserData.put(LexmarkConstants.SHORTID, "xxx sort id");
		ldapUserData.put(LexmarkConstants.COMPANYNAME, "xxx company name");
		/**
		 * ldap Details Information End
		 **/
		mockSession.setAttribute(LexmarkConstants.LDAP_USER_DATA, ldapUserData,
				mockSession.APPLICATION_SCOPE);
		servicesUser = new ServicesUser();
		servicesUser.setEmailAddress("xxx@abx.com");
		servicesUser.setUserNumber("123userNumber");
		accDetails = new HashedMap();
		/**
		 * Account Details Information
		 **/
		servicesUser.setMdmId("1234");
		servicesUser.setMdmLevel("L1");
		accDetails.put("accountId", "1-28FWPH");
		accDetails.put("accountName", "AT&T Corporation");
		accDetails.put("accountOrganization", "Wipro");
		accDetails.put("agreementId", "Aggreement12345");
		accDetails.put("agreementName", "agreementName122121");
		accDetails.put("country", "India");
		/**
		 * Account Details Information End
		 **/
		ServiceRequest serviceRequest = new ServiceRequest();
		AccountContact accountContact = new AccountContact();
		GenericAddress genericAddress = new GenericAddress();
		/**
		 * Generic Address and other page related details
		 **/
		genericAddress.setAddressId("addressid");
		genericAddress.setAddressLine1("line1");
		genericAddress.setAddressLine2("line2");
		genericAddress.setAddressLine3("line3");
		genericAddress.setAddressLine4("line4");
		genericAddress.setAddressName("addressname");
		genericAddress.setCity("city");
		genericAddress.setCountry("country");
		genericAddress.setNewAddressFlag("addressflag");
		genericAddress.setPhysicalLocation1("building");
		genericAddress.setPhysicalLocation2("floor");
		genericAddress.setPhysicalLocation3("office");
		genericAddress.setPostalCode("123456");
		genericAddress.setProvince("province");
		genericAddress.setStoreFrontName("storeFrontName");
		genericAddress.setUserFavorite(true);
		accountContact.setAddress(genericAddress);
		accountContact.setAlternatePhone("123124234234");
		accountContact.setContactId("122131");
		accountContact.setCountry("country");
		accountContact.setDepartment("department");
		accountContact.setEmailAddress("sudipta@gmail.com");
		accountContact.setFirstName("firstName");
		accountContact.setLastName("lastName");
		accountContact.setManageContactFlag(true);
		accountContact.setMiddleName("middleName");
		accountContact.setNewContactFlag(true);
		accountContact.setShortId("sa123");
		accountContact.setWorkPhone("23423423423");
		accountContact.setUpdateContactFlag(true);
		accountContact.setUserFavorite(true);

		serviceRequest.setRequestor(accountContact);
		serviceRequest.setPrimaryContact(accountContact);
		serviceRequest.setSecondaryContact(accountContact);
		lov.setId("123");
		lov.setLanguage("english");
		lov.setLanguageName("en");
		lov.setName("sudipta");
		lov.setType("add asset data");
		lov.setValue("customer acceptance");
		serviceRequest.setArea(lov);
		serviceRequest.setSubArea(lov);
		reqResult.setServiceRequest(serviceRequest);
		requestform
				.setServiceRequestStatusMap((Map<String, String>) serviceRequest);
		requestform
				.setServiceRequestStatusDetailMap((Map<String, String>) serviceRequest);
		userAccessMapForSr.put("partner", "partner");
		userAccessMapForSr.put("Customer", "Customer");
		USERROLES.add("partner");
		USERROLES.add("Customer");
		attachment = new ArrayList<Attachment>();

		/**
		 * Generic Address and other page related details end
		 **/

		/**
		 * Binding attributes with session and requests
		 **/

		mockSession.setAttribute("accountCurrentDetails", accDetails,
				mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute(LexmarkConstants.SERVICES_USER, servicesUser,
				mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute("userAccessMapForSr", userAccessMapForSr,
				mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute("USERROLES", USERROLES,
				mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute("fileUploadForm", fileUploadForm,
				mockSession.APPLICATION_SCOPE);

		downLoadContract = new RequestListContract();
		downLoadContract.setMdmId("121312312");
		downLoadContract.setMdmLevel("siebel");
		downLoadContract.setAssetFavoriteFlag(false);
		downLoadContract.setFilterCriteria(filterCriteria);
		mockSession.setAttribute("downLoadContract", downLoadContract);

		actionrequest.setSession(mockSession);
		renderRequest.setSession(mockSession);
		resourceReq.setSession(mockSession);

		/**
		 * Binding attributes with session and requests end
		 **/
	}

	private BindingResult createMockBindingResult(Class<BindingResult> class1) {
		return new BindingResult() {

			@Override
			public void addAllErrors(Errors arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public List<ObjectError> getAllErrors() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getErrorCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public FieldError getFieldError() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public FieldError getFieldError(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getFieldErrorCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getFieldErrorCount(String arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public List<FieldError> getFieldErrors() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<FieldError> getFieldErrors(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Class getFieldType(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object getFieldValue(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ObjectError getGlobalError() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getGlobalErrorCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public List<ObjectError> getGlobalErrors() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getNestedPath() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getObjectName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean hasErrors() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean hasFieldErrors() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean hasFieldErrors(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean hasGlobalErrors() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void popNestedPath() throws IllegalStateException {
				// TODO Auto-generated method stub

			}

			@Override
			public void pushNestedPath(String arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void reject(String arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void reject(String arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void reject(String arg0, Object[] arg1, String arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void rejectValue(String arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void rejectValue(String arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void rejectValue(String arg0, String arg1, Object[] arg2,
					String arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setNestedPath(String arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void addError(ObjectError arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public PropertyEditor findEditor(String arg0, Class arg1) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Map<String, Object> getModel() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public PropertyEditorRegistry getPropertyEditorRegistry() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object getRawFieldValue(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String[] getSuppressedFields() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object getTarget() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void recordSuppressedField(String arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public String[] resolveMessageCodes(String arg0, String arg1) {
				// TODO Auto-generated method stub
				return null;
			}

		};
	}

	private Model createMock(Class<Model> mockModelClass) {

		return new Model() {

			@Override
			public Model addAllAttributes(Collection<?> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Model addAllAttributes(Map<String, ?> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Model addAttribute(Object arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Model addAttribute(String arg0, Object arg1) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Map<String, Object> asMap() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean containsAttribute(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Model mergeAttributes(Map<String, ?> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

		};
	}

	/**
	 * Destroying objects
	 **/
	@After
	public void tearDown() throws Exception {
		mockModel = null;
		mockSession.clearAttributes();
		actionrequest = null;
		renderRequest = null;
		resourceReq = null;
	}

	/**
	 * Destroying objects end
	 **/

	/**
	 * Test Cases Start
	 **/
	@Test
	public void testshowMassUpload() {
		Map<String, String> optionType = new HashMap<String, String>();
		UserGridSettingResult result = new UserGridSettingResult();
		result.setColsFilter("gcsgcd");
		result.setColsHidden("hide");
		result.setColsOrder("centre");
		result.setColsSorting("descending");
		result.setColsWidth("10");
		result.setGridId("12");
		result.setUserNumber("445644");

		result.setUserNumber("12345");
		optionType.put("gridIDOrderRequestList", "Service Order");
		// optionType.put("ServiceOrderHW", "Service Order Hardware");
		optionType.put("gridIDOrderRequestList", "result");
		mockSession.setAttribute("gridIDOrderRequestList", optionType);
		renderRequest.setSession(mockSession);

		// renderRequest.setParameter("serviceRequestNumber", "123456789");
		String output = target.showMassUpload(renderRequest, mockModel);
		assertEquals("OK", "massUpload/massUploadDefaultView", output);
	}

	@Test
	public void testgetGridSetting() {
		resourceReq.setSession(mockSession);
		String gridId = null;
		target.getGridSetting(resourceReq, resourceRes,  gridId);
		
	}

	@Test
	public void testdownloadTemplate() {
		resourceReq.setSession(mockSession);
		String gridId = null;
		target.downloadTemplate(resourceReq, resourceRes, gridId);
		
	}

	@Test
	public void testgetOrderRequestList() throws LGSCheckedException,
			LGSRuntimeException {
		resourceReq.setSession(mockSession);
		resourceReq.setParameter("status", "Done");
		resourceReq.setParameter("beginDate", "12/07/2013");
		resourceReq.setParameter("endDate", "15/07/2013");
		Object vendorAccountId = "12354";
		if (vendorAccountId != null) {
			resourceReq.setParameter("vendorAccountId", "12354");
		}
		boolean isMassUpload = false;
		if (isMassUpload = true) {
			resourceReq.setParameter("isMassUpload", "ddfdxd");
		}
		resourceReq.setSession(mockSession);

		//target.getOrderRequestList(resourceReq, resourceRes, mockModel, null);
		
	}

	@Test
	public void testsubmitRequest() {
		resourceReq.setSession(mockSession);
		target.submitRequest(resourceReq, resourceRes, resourceReq.getParameter("type"));
		
	}
}
/**
 * Test Cases End
 **/
