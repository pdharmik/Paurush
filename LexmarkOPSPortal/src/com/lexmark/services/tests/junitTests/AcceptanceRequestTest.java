package com.lexmark.services.tests.junitTests;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


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
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.FileObject;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServicesUser;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.RequestResult;
import com.lexmark.service.impl.mock.ContactServiceImpl;
import com.lexmark.service.impl.mock.GlobalServiceImpl;
import com.lexmark.service.impl.mock.ServiceRequestLocaleImpl;
import com.lexmark.service.impl.mock.ServiceRequestServiceImpl;
import com.lexmark.service.impl.real.AmindAttachmentService;
import com.lexmark.service.impl.real.jdbc.GeographyServiceImpl;
import com.lexmark.services.form.AssetAcceptanceForm;
import com.lexmark.services.form.BaseForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.validator.CommonValidator;
import com.lexmark.services.form.validator.FileUploadFormValidator;
import com.lexmark.services.portlet.SharedPortletController;
import com.lexmark.services.portlet.cm.AssetAcceptanceController;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.portlet.common.ContactController;
import com.lexmark.services.webService.cm.AssetAcceptanceServiceImpl;

public class AcceptanceRequestTest {
	private AssetAcceptanceController target;
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
	private BaseForm globalsubmittoken;
	private SessionStatus mockSessionStatus;
	private HashMap<String, String> serviceRequestsTypeMap=new HashMap<String,String>();
	
	private LocalizedSiebelLOVListResult lovRes= new LocalizedSiebelLOVListResult();
	private List<ListOfValues> lovList = new ArrayList<ListOfValues>();
	private ListOfValues lov=new ListOfValues();
	private Map<String, String> userAccessMapForSr= new HashMap<String, String>();
	private AssetAcceptanceForm assetAcceptform= new AssetAcceptanceForm();
	private FileUploadForm fileUploadForm = new FileUploadForm();
	private List<String> USERROLES = new ArrayList<String>();
	private RequestResult reqResult = new RequestResult();
	private RequestListContract downLoadContract;
	private String fileName;
	private MockResourceRequest resourceReq;
	private MockResourceResponse resourceRes;
	
	
	@Before
	public void setUp() throws Exception {
		target = new AssetAcceptanceController();
		ContactController commonContact = new ContactController();
		 CommonController commonController =new CommonController();
		ReflectionTestUtils.setField(commonContact, "globalService",new GlobalServiceImpl());
		ReflectionTestUtils.setField(commonContact, "commonValidator",new CommonValidator());
		ReflectionTestUtils.setField(commonContact, "contactService",new ContactServiceImpl());
		
		
		ReflectionTestUtils.setField(commonController, "globalService",new GlobalServiceImpl());
		ReflectionTestUtils.setField(commonController, "sharedPortletController",new SharedPortletController());
		ReflectionTestUtils.setField(commonController, "serviceRequestService",new ServiceRequestServiceImpl());
		ReflectionTestUtils.setField(commonController, "geographyService",new GeographyServiceImpl());
		ReflectionTestUtils.setField(commonController, "serviceRequestsTypeMap",new HashMap<String,String>());
		ReflectionTestUtils.setField(commonController, "serviceRequestLocaleService",new ServiceRequestLocaleImpl());
		
		ReflectionTestUtils.setField(commonController, "contactController",commonContact);
		
		ReflectionTestUtils.setField(target, "commonController",commonController);
		ReflectionTestUtils.setField(target, "globalService",new GlobalServiceImpl());
		ReflectionTestUtils.setField(target, "assetAcceptanceService",new AssetAcceptanceServiceImpl());
		ReflectionTestUtils.setField(target, "fileUploadFormValidator",new FileUploadFormValidator());
		ReflectionTestUtils.setField(target, "attachmentService",new AmindAttachmentService());
		
		
		

		/*ReflectionTestUtils.setField(target, "requestTypeService",new AmindRequestTypeService());
		ReflectionTestUtils.setField(target, "serviceRequestLocaleService",new ServiceRequestLocaleImpl());
		ReflectionTestUtils.setField(target, "sharedPortletController",new SharedPortletController());
		ReflectionTestUtils.setField(target, "serviceRequestService",new ServiceRequestServiceImpl());*/
		
		renderRequest = new MockRenderRequest();
		renderResponse = new MockRenderResponse();
		mockSessionStatus= new SessionStatus() {

			public boolean isComplete() {
				// TODO Auto-generated method stub
				return false;
			}

			public void setComplete() {
				// TODO Auto-generated method stub
				
			}
			
		};
		mockModel = createMock(Model.class);
		BindingResult=createMockBindingResult(BindingResult.class);
		actionrequest = new MockActionRequest();
		actionresponse = new MockActionResponse();
		mockSession=new MockPortletSession();
		resourceReq = new MockResourceRequest();
		resourceRes = new MockResourceResponse();
		renderRequest.addLocale(Locale.US);
		resourceReq.addLocale(Locale.US);
		ldapUserData=new HashMap<String, Object>();
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
		mockSession.setAttribute(LexmarkConstants.LDAP_USER_DATA, ldapUserData, mockSession.APPLICATION_SCOPE);
		servicesUser=new ServicesUser();
		servicesUser.setEmailAddress("xxx@abx.com");
		servicesUser.setUserNumber("123userNumber");
		accDetails = new HashMap<String, String>();
		
		servicesUser.setMdmId("1234");
		servicesUser.setMdmLevel("L1");
		accDetails.put("accountId", "1-28FWPH");
		accDetails.put("accountName", "AT&T Corporation");
		accDetails.put("accountOrganization", "Wipro");
		accDetails.put("agreementId", "Aggreement12345");
		accDetails.put("agreementName", "agreementName122121");
		accDetails.put("country", "India");
		
		ServiceRequest serviceRequest = new ServiceRequest();
		AccountContact accountContact = new AccountContact();
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
		assetAcceptform.setServiceRequest(serviceRequest);
		userAccessMapForSr.put("partner", "partner");
		userAccessMapForSr.put("Customer", "Customer");
		USERROLES.add("partner"); 
		USERROLES.add("Customer");
		
		mockSession.setAttribute("accountCurrentDetails", accDetails, mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute(LexmarkConstants.SERVICES_USER,servicesUser,mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute(LexmarkConstants.SUBMIT_TOKEN, globalsubmittoken, mockSession.PORTLET_SCOPE);
		mockSession.setAttribute("assetAcceptform", assetAcceptform, mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute("userAccessMapForSr", userAccessMapForSr, mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute("USERROLES", USERROLES, mockSession.APPLICATION_SCOPE);
		
		downLoadContract = new RequestListContract();
		downLoadContract.setMdmId("121312312");
		downLoadContract.setMdmLevel("siebel");
		//downLoadContract.setFilterCriteria(filterCriteria);
		mockSession.setAttribute("downLoadContract", downLoadContract);
		
		actionrequest.setSession(mockSession);
		renderRequest.setSession(mockSession);
		resourceReq.setSession(mockSession);
		}
	private BindingResult createMockBindingResult(Class<BindingResult> class1) {
		return new BindingResult() {

			public void addAllErrors(Errors arg0) {
				// TODO Auto-generated method stub
				
			}

			public List<ObjectError> getAllErrors() {
				// TODO Auto-generated method stub
				return null;
			}

			public int getErrorCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			public FieldError getFieldError() {
				// TODO Auto-generated method stub
				return null;
			}

			public FieldError getFieldError(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public int getFieldErrorCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			public int getFieldErrorCount(String arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			public List<FieldError> getFieldErrors() {
				// TODO Auto-generated method stub
				return null;
			}

			public List<FieldError> getFieldErrors(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public Class getFieldType(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public Object getFieldValue(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public ObjectError getGlobalError() {
				// TODO Auto-generated method stub
				return null;
			}

			public int getGlobalErrorCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			public List<ObjectError> getGlobalErrors() {
				// TODO Auto-generated method stub
				return null;
			}

			public String getNestedPath() {
				// TODO Auto-generated method stub
				return null;
			}

			public String getObjectName() {
				// TODO Auto-generated method stub
				return null;
			}

			public boolean hasErrors() {
				// TODO Auto-generated method stub
				return false;
			}

			public boolean hasFieldErrors() {
				// TODO Auto-generated method stub
				return false;
			}

			public boolean hasFieldErrors(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			public boolean hasGlobalErrors() {
				// TODO Auto-generated method stub
				return false;
			}

			public void popNestedPath() throws IllegalStateException {
				// TODO Auto-generated method stub
				
			}

			public void pushNestedPath(String arg0) {
				// TODO Auto-generated method stub
				
			}

			public void reject(String arg0) {
				// TODO Auto-generated method stub
				
			}

			public void reject(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			public void reject(String arg0, Object[] arg1, String arg2) {
				// TODO Auto-generated method stub
				
			}

			public void rejectValue(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			public void rejectValue(String arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				
			}

			public void rejectValue(String arg0, String arg1, Object[] arg2,
					String arg3) {
				// TODO Auto-generated method stub
				
			}

			public void setNestedPath(String arg0) {
				// TODO Auto-generated method stub
				
			}

			public void addError(ObjectError arg0) {
				// TODO Auto-generated method stub
				
			}

			public PropertyEditor findEditor(String arg0, Class arg1) {
				// TODO Auto-generated method stub
				return null;
			}

			public Map<String, Object> getModel() {
				// TODO Auto-generated method stub
				return null;
			}

			public PropertyEditorRegistry getPropertyEditorRegistry() {
				// TODO Auto-generated method stub
				return null;
			}

			public Object getRawFieldValue(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public String[] getSuppressedFields() {
				// TODO Auto-generated method stub
				return null;
			}

			public Object getTarget() {
				// TODO Auto-generated method stub
				return null;
			}

			public void recordSuppressedField(String arg0) {
				// TODO Auto-generated method stub
				
			}

			public String[] resolveMessageCodes(String arg0, String arg1) {
				// TODO Auto-generated method stub
				return null;
			}
	};
	
}
	private Model createMock(Class<Model> mockModelClass) {

		return new Model() {

			public Model addAllAttributes(Collection<?> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public Model addAllAttributes(Map<String, ?> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public Model addAttribute(Object arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public Model addAttribute(String arg0, Object arg1) {
				// TODO Auto-generated method stub
				return null;
			}

			public Map<String, Object> asMap() {
				// TODO Auto-generated method stub
				return null;
			}

			public boolean containsAttribute(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			public Model mergeAttributes(Map<String, ?> arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		
	};
}
	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testviewAssetAcceptance()throws LGSCheckedException,
	LGSRuntimeException, Exception {
		renderRequest.setSession(mockSession);
		
		renderRequest.setParameter("serviceRequestNumber", "123456789");
		String output = target.viewAssetAcceptance(mockModel, renderRequest, renderResponse);
		assertEquals("OK", "changemanagement/assetAcceptance/assetAcceptanceDetails", output);
	}
	
	@Test
	public void testshowAssetAcceptanceReview()throws LGSCheckedException, LGSRuntimeException, Exception{
		
		
		Map<String, FileObject> fileMap = new HashMap<String, FileObject>();
		FileObject fo= new FileObject();
		fo.setDisplayFileName("sudipta");
		fo.setFileName("lxk-sudipta");
		fo.setFileSize("10");
		fo.setFileSizeInBytes("122");
		fo.setUploadDate("12/12/2013");
		fileMap.put("filemane",fo);
		mockSession.setAttribute("fileMapInSession", fileMap);
		actionrequest.setSession(mockSession);
		
		target.showAssetAcceptanceReview(mockModel, actionresponse, actionrequest, assetAcceptform);
		
		
		
	}
	@Test
	public void testbackToEdit()throws LGSCheckedException, LGSRuntimeException,Exception{
		Map<String, FileObject> fileMap = new HashMap<String, FileObject>();
		FileObject fo= new FileObject();
		fo.setDisplayFileName("sudipta");
		fo.setFileName("lxk-sudipta");
		fo.setFileSize("10");
		fo.setFileSizeInBytes("122");
		fo.setUploadDate("12/12/2013");
		fileMap.put("filemane",fo);
		mockSession.setAttribute("fileMapInSession", fileMap);
		actionrequest.setSession(mockSession);
		target.backToEdit(mockModel, actionrequest, actionresponse, assetAcceptform);
		
	}
	@Test
	public void testattachDocument()throws LGSCheckedException,
	LGSRuntimeException{
		Map<String, FileObject> fileMap = new HashMap<String, FileObject>();
		FileObject fo= new FileObject();
		fo.setDisplayFileName("sudipta");
		fo.setFileName("lxk-sudipta");
		fo.setFileSize("10");
		fo.setFileSizeInBytes("122");
		fo.setUploadDate("12/12/2013");
		fileMap.put("filemane",fo);
		mockSession.setAttribute("fileMapInSession", fileMap);
		actionrequest.setSession(mockSession);
		target.attachDocument(mockModel, actionrequest, actionresponse, fileUploadForm, BindingResult);
		
	}
	@Test
	public void testremoveDocument()throws LGSCheckedException, LGSRuntimeException{
		Map<String, FileObject> fileMap = new HashMap<String, FileObject>();
		FileObject fo= new FileObject();
		fo.setDisplayFileName("sudipta");
		fo.setFileName("lxk-sudipta");
		fo.setFileSize("10");
		fo.setFileSizeInBytes("122");
		fo.setUploadDate("12/12/2013");
		fileMap.put("filemane",fo);
		mockSession.setAttribute("fileMapInSession", fileMap);
		actionrequest.setSession(mockSession);
		target.removeDocument(mockModel, actionrequest, actionresponse, fileUploadForm, fileName);
		
	}
	@Test
	public void testdisplayAttachment()throws LGSRuntimeException{
		resourceReq.setParameter("fileType", "xls");
		resourceReq.setParameter("fileName", "chlTemplate");
		target.displayAttachment(resourceReq, resourceRes);
		
		resourceReq.setParameter("fileType", "pdf");
		resourceReq.setParameter("fileName", "Template");
		target.displayAttachment(resourceReq, resourceRes);
		
	}
	@Test
	public void testshowPrintAssetAcceptance()throws Exception{
		String output = target.showPrintAssetAcceptance();
		assertEquals("OK", "changemanagement/assetAcceptance/printAssetAcceptance", output);
	}
	@Test
	public void testshowEmailAssetAcceptance()throws Exception{
		String output = target.showEmailAssetAcceptance();
		assertEquals("OK", "changemanagement/assetAcceptance/emailAssetAcceptance", output);
	}
	@Test
	public void testcreateRequestAction()throws Exception{
		Map<String, FileObject> fileMap = new HashMap<String, FileObject>();
		FileObject fo= new FileObject();
		fo.setDisplayFileName("sudipta");
		fo.setFileName("lxk-sudipta");
		fo.setFileSize("10");
		fo.setFileSizeInBytes("122");
		fo.setUploadDate("12/12/2013");
		fileMap.put("filemane",fo);
		mockSession.setAttribute("fileMapInSession", fileMap);
		actionrequest.setSession(mockSession);
		target.createRequestAction(mockModel, actionrequest, actionresponse, assetAcceptform);
		
		
	}
	@Test
	public void testcreateRequestActionSuccess(){
		renderRequest.setParameter("timezoneOffset", "5.5");
		String output = target.createRequestActionSuccess(mockModel, renderRequest, renderResponse);
		assertEquals("OK", "changemanagement/assetAcceptance/assetAcceptanceConfirm", output);
		
	}
	@Test
	public void testcreateRequestActionError(){
		String output = target.createRequestActionError(mockModel, renderResponse);
		assertEquals("OK","changemanagement/assetAcceptance/assetAcceptanceReview", output);
	}
		}
