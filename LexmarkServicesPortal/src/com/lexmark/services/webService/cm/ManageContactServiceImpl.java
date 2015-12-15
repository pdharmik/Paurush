/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ManageContactServiceImpl.java
 * Package     		: com.lexmark.services.webService.cm
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro		2013 		2.1             Initial Version
 * 
 */

package com.lexmark.services.webService.cm;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.xml.rpc.holders.StringHolder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestData;
import com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestWSInput;
import com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestWSInput2;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSLocator;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS_PortType;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelAccount;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact;
import com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData;
import com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder;
import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.contract.ProcessCustomerContactContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.result.ProcessCustomerContactResult;
import com.lexmark.services.api.cm.ManageContactService;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.webServices.processCustomerContactCM.CustomerContact;
import com.lexmark.webServices.processCustomerContactCM.CustomerContact2;
import com.lexmark.webServices.processCustomerContactCM.Data;
import com.lexmark.webServices.processCustomerContactCM.DocumentMetaData;
import com.lexmark.webServices.processCustomerContactCM.IntegrationFrequency;
import com.lexmark.webServices.processCustomerContactCM.ObjectChangeActionType;
import com.lexmark.webServices.processCustomerContactCM.PhysicalAddress;
import com.lexmark.webServices.processCustomerContactCM.ProcessCustomerContactWS;
import com.lexmark.webServices.processCustomerContactCM.ProcessCustomerContactWSLocator;
import com.lexmark.webServices.processCustomerContactCM.ProcessCustomerContactWS_PortType;
import com.lexmark.webServices.processCustomerContactCM.PublishContacts;
import com.lexmark.webServices.processCustomerContactCM.PublishContactsResponse;



/**
 * This impl class is used for webService call for contact which implements manageContactService interface class
 * @author wipro
 *
 */
public class ManageContactServiceImpl implements ManageContactService{
	
	/**. Instance variable of wrapper logger class **/
	private static final LEXLogger LOGGER = LEXLogger.getLEXLogger(ManageContactServiceImpl.class);
	private static Logger logger = LogManager.getLogger(ManageContactServiceImpl.class);
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging

	private String address;
	private String senderId;
	private String senderName;
	private String receiverId;
	private String userName;
	private String password;
	//Added for MPS 2
	private String customerName;
	private String addUpdateContactURL;
	private String contactType;
	private String businessProcess;
	private String integrationFrequency;
	private String objectChangeActionTypeNew;
	private String objectChangeActionTypeChange;
	private String synchOrAsynch;
	private String sourceSystem;
	private String debug;


	

	private static final String CREATECMREQSERV = "createCMRequestService";
	//Changes for MPS 2
	private static final String ADDANDUPDATECONTACTSERVICE = "addAndUpdateContactService";
	//Ends
	/**
	 * 
	 * @return 
	 */
	public String getAddress() {
		
		return address;
	}
	/**
	 * 
	 * @param address 
	 */
	public void setAddress(String address) {
		
		this.address = address;
	}
	/**
	 * 
	 * @return 
	 */
	public String getSenderId() {
		
		return senderId;
	}
	/**
	 * 
	 * @param senderId 
	 */
	public void setSenderId(String senderId) {
		
		this.senderId = senderId;
	}
	/**
	 * 
	 * @return 
	 */
	public String getSenderName() {
		
		return senderName;
	}
	/**
	 * 
	 * @param senderName  
	 */
	public void setSenderName(String senderName) {
		
		this.senderName = senderName;
	}
	/**
	 * 
	 * @return 
	 */
	public String getReceiverId() {
		
		return receiverId;
	}
	/**
	 * 
	 * @param receiverId  
	 */
	public void setReceiverId(String receiverId) {
		
		this.receiverId = receiverId;
	}
	
	/**
	 * This is the Constructor method of this class
	 */
	public ManageContactServiceImpl() {

	}
	
	/**
	 * constructor method of this class
	 * @param address   
	 */
	public ManageContactServiceImpl(String address) {
		this.address = address;
	}
	
	/**
	 * 
	 * @return 
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * 
	 * @param userName  
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 
	 * @return 
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 
	 * @param password  
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	//ADded for MPS 2
	/**
	 * 
	 * @return 
	 */
	public String getContactType() {
		return contactType;
	}
	
	/**
	 * 
	 * @param contactType 
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	
	/**
	 * 
	 * @return 
	 */
	public String getDebug() {
		return debug;
	}
	
	/**
	 * 
	 * @param debug 
	 */
	public void setDebug(String debug) {
		this.debug = debug;
	}
	
	/**
	 * 
	 * @param customerName  
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 
	 * @return 
	 */
	public String getCustomerName() {
		return customerName;
	}

	
	/**
	 * 
	 * @param businessProcess  
	 */
	public void setBusinessProcess(String businessProcess) {
		this.businessProcess = businessProcess;
	}
	/**
	 * 
	 * @return 
	 */
	public String getBusinessProcess() {
		return businessProcess;
	}
	/**
	 * 
	 * @param integrationFrequency  
	 */
	public void setIntegrationFrequency(String integrationFrequency) {
		this.integrationFrequency = integrationFrequency;
	}
	/**
	 * 
	 * @return 
	 */
	public String getIntegrationFrequency() {
		return integrationFrequency;
	}
	/**
	 * 
	 * @param objectChangeActionTypeNew  
	 */
	public void setObjectChangeActionTypeNew(String objectChangeActionTypeNew) {
		this.objectChangeActionTypeNew = objectChangeActionTypeNew;
	}
	/**
	 * 
	 * @return 
	 */
	public String getObjectChangeActionTypeNew() {
		return objectChangeActionTypeNew;
	}
	/**
	 * 
	 * @param objectChangeActionTypeChange  
	 */
	public void setObjectChangeActionTypeChange(
			String objectChangeActionTypeChange) {
		this.objectChangeActionTypeChange = objectChangeActionTypeChange;
	}
	/**
	 * 
	 * @return 
	 */
	public String getObjectChangeActionTypeChange() {
		return objectChangeActionTypeChange;
	}
	/**
	 * 
	 * @param synchOrAsynch  
	 */
	public void setSynchOrAsynch(String synchOrAsynch) {
		this.synchOrAsynch = synchOrAsynch;
	}
	/**
	 * 
	 * @return 
	 */
	public String getSynchOrAsynch() {
		return synchOrAsynch;
	}
	/**
	 * 
	 * @return 
	 */
	public String getSourceSystem() {
		return sourceSystem;
	}
	/**
	 * 
	 * @param sourceSystem  
	 */
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}
	/**
	 * 
	 * @param addressAddUpdateContactURL  
	 */
	public void setAddUpdateContactURL(String addressAddUpdateContactURL) {
		this.addUpdateContactURL = addressAddUpdateContactURL;
	}
	/**
	 * 
	 * @return 
	 */
	public String getAddUpdateContactURL() {
		return addUpdateContactURL;
	}
	//Ends MPS 2
	
	/**
	 * This method is used for service call for manageContact section in ChangeManagement
	 */
	public CreateServiceRequestResult createCMRequestService(
			CreateServiceRequestContract serviceReqContract,Map<String,String> accountDetails)
			throws LGSRuntimeException, LGSBusinessException {
		
		CreateServiceRequestResult result = null;
		LOGGER.enter(this.getClass().getSimpleName(), CREATECMREQSERV);
		try{
			ServiceRequestWS wsLocator = new ServiceRequestWSLocator();		 
			ServiceRequestWS_PortType port = wsLocator.getServiceRequest_serviceRequestWS_Port(new URL(getAddress()));		
			org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
			stub.setUsername(getUserName());
			stub.setPassword(getPassword());
			
			final String synchOrAsynch = "asynch";
			final String debug="$null";
			ServiceRequest srData=null;
			String serviceRequestNumber=null;
			final String sourceSystem = "Web";

			WebServiceDocumentMetaData documentMetaData = new WebServiceDocumentMetaData();
			documentMetaData.setSenderId(getSenderId());
			
			documentMetaData.setSenderName(getSenderName());			
			documentMetaData.setReceiverId(getReceiverId());			
			if(serviceReqContract.getServiceRequest()!=null){				
				srData = serviceReqContract.getServiceRequest();
			}

			SiebelAccount accInformation=new SiebelAccount();
			if (accountDetails.get(ChangeMgmtConstant.ACCOUNTID)==null){
				accInformation.setAccountId("");
				accInformation.setAccountName("");
			}else{
				LOGGER.debug("Account id :::: " + accountDetails.get("accountId")+ "Account Name :::: " + accountDetails.get("accountName") + "Account Organization :::: " + accountDetails.get("accountOrganization")	+ "Agreement Id :::: " + accountDetails.get("agreementId") + "Agreement Name :::: "	+ accountDetails.get("agreementName"));
				accInformation.setAccountId(accountDetails.get(ChangeMgmtConstant.ACCOUNTID));
				accInformation.setAccountName(accountDetails.get(ChangeMgmtConstant.ACCOUNTNAME));
				accInformation.setAccountCountry(accountDetails.get(ChangeMgmtConstant.COUNTRY));
			}
			
			ChangeManagementServiceRequestData cmServiceReqData= new ChangeManagementServiceRequestData();
			cmServiceReqData.setAccountInformation(accInformation);
			// changes for CR 13558, July release
			if(serviceReqContract.getPrevSrNo() != null){
				cmServiceReqData.setRelatedServiceRequestNumber(serviceReqContract.getPrevSrNo());	
			}else{
				cmServiceReqData.setRelatedServiceRequestNumber("");
			}
			
			cmServiceReqData.setServiceRequestType("Fleet Management");
			cmServiceReqData.setRequestedService(serviceReqContract.getServiceRequest().getArea().getValue());
			cmServiceReqData.setRequestedServiceAction(serviceReqContract.getServiceRequest().getSubArea().getValue());
			cmServiceReqData.setServiceRequestDate(DateUtil.convertDateToGMTString(srData.getRequestedEffectiveDate()));
			cmServiceReqData.setServiceRequestDescription(srData.getAddtnlDescription());
			cmServiceReqData.setCustomerReferenceNumber(srData.getCustomerReferenceId());
			cmServiceReqData.setCostCenter(srData.getCostCenter());
			cmServiceReqData.setServiceRequestSource(sourceSystem);
			//notes attachment start
			cmServiceReqData.setAttachmentNotes(srData.getNotes());
			//notes attachment end
			LOGGER.debug("Area is " + serviceReqContract.getServiceRequest().getArea().getValue() + "Sub area is " + serviceReqContract.getServiceRequest().getSubArea().getValue());
			
			SiebelContact requestor=null;
			
			if(srData.getRequestor()!=null)
			{
				requestor= new SiebelContact();
				requestor.setContactId(srData.getRequestor().getContactId());
				requestor.setEmailAddress(srData.getRequestor().getEmailAddress());
				requestor.setFirstName(srData.getRequestor().getFirstName());
				requestor.setLastName(srData.getRequestor().getLastName());
				requestor.setWorkPhone(srData.getRequestor().getWorkPhone());
				LOGGER.debug("user type is "+serviceReqContract.getUserType());
				if("Partner".equalsIgnoreCase(serviceReqContract.getUserType())){
					requestor.setContactType("Vendor");
				}else{
					if(serviceReqContract.getUserType().equalsIgnoreCase("employee"))
					{
						requestor.setContactType("Lexmark");
					}else{
						requestor.setContactType(serviceReqContract.getUserType());
					}
				}				
				cmServiceReqData.setRequester(requestor);
			}
					
			LOGGER.info("Setting info of the requestor Contact ID: " + srData.getPrimaryContact().getContactId() + " First Name :" + srData.getPrimaryContact().getFirstName() + " Lst Name: " + srData.getPrimaryContact().getLastName() + " Email ID: " + srData.getPrimaryContact().getEmailAddress() + " Ph no is"+srData.getPrimaryContact().getWorkPhone() + " Setting primary contact ");
			
			SiebelContact primaryContact=null;			
			if(srData.getPrimaryContact()!=null)
			{
				primaryContact=new SiebelContact();
				primaryContact.setContactId(srData.getPrimaryContact().getContactId());
				primaryContact.setEmailAddress(srData.getPrimaryContact().getEmailAddress());
				primaryContact.setFirstName(srData.getPrimaryContact().getFirstName());
				primaryContact.setLastName(srData.getPrimaryContact().getLastName());
				primaryContact.setWorkPhone(srData.getPrimaryContact().getWorkPhone());
				primaryContact.setAlternatePhone(srData.getPrimaryContact().getAlternatePhone());				
				cmServiceReqData.setPrimaryContact(primaryContact);				
			}
			
			
			SiebelContact secContact=null;			
			if(srData.getSecondaryContact()!=null)
			{
				secContact=new SiebelContact();
				secContact.setContactId(srData.getSecondaryContact().getContactId());
				secContact.setEmailAddress(srData.getSecondaryContact().getEmailAddress());
				secContact.setFirstName(srData.getSecondaryContact().getFirstName());
				secContact.setLastName(srData.getSecondaryContact().getLastName());
				secContact.setWorkPhone(srData.getSecondaryContact().getWorkPhone());
				secContact.setAlternatePhone(srData.getSecondaryContact().getAlternatePhone());				
				cmServiceReqData.setSecondaryContact(secContact);				
			}			
			// Setting the previous Service Request No for the Update call
			LOGGER.debug("The previous service request no is -->" +serviceReqContract.getPrevSrNo());
			if(serviceReqContract.getPrevSrNo()!=null){
				 serviceRequestNumber= serviceReqContract.getPrevSrNo(); 
				 cmServiceReqData.setServiceRequestNumber(serviceRequestNumber);
			 }
			 
			
			ChangeManagementServiceRequestWSInput2 cmServiceReqWSInputTwo=new ChangeManagementServiceRequestWSInput2();
			cmServiceReqWSInputTwo.setChangeManagementServiceRequestData(cmServiceReqData);
			cmServiceReqWSInputTwo.setDocumentMetaData(documentMetaData);
			
			ChangeManagementServiceRequestWSInput cmServiceReqWSInput=new ChangeManagementServiceRequestWSInput();
			cmServiceReqWSInput.setChangeManagementServiceRequestWSInput(cmServiceReqWSInputTwo);
			
			StringHolder srNumber = new StringHolder();
			StringHolder srRowId = new StringHolder();
			ServiceRequestDetailsOutputHolder serviceRequestDetailsOutPutHolder = new ServiceRequestDetailsOutputHolder(); 
			
			ObjectDebugUtil.printMultiObjectContent(cmServiceReqWSInput,logger);
			long timeBeforeCall = System.currentTimeMillis();
			port.createChangeManagementServiceRequest(debug, cmServiceReqWSInput, synchOrAsynch, 
					serviceRequestDetailsOutPutHolder, srRowId, srNumber); 
			long timeAfterCall=System.currentTimeMillis();
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MANAGECONTACT_MSG_CREATECHANGEMANAGEMENTSERVICEREQUEST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,serviceReqContract);
			//LOGGER.logTime("** MPS PERFORMANCE TESTING CONTACT SERVICE CALL ==>: " + (System.currentTimeMillis() - timeBeforeCall) / 1000.0);
			
			result = new CreateServiceRequestResult();			
			LOGGER.debug("SR Number CM Req is "+ srNumber.value+ " SR Row ID CM Req is " + srRowId.value);					
			if(!StringUtil.isStringEmpty(srNumber.value) && !StringUtil.isStringEmpty(srRowId.value)){
				result.setServiceRequestNumber(srNumber.value);
				result.setServiceRequestRowId(srRowId.value);
			} 
			else {
					throw new LGSBusinessException("Error creating service request - SR Number not received");
				}
			
			}
			catch (Exception ex) {
				throw new LGSRuntimeException("Error while saving data");
			}
			LOGGER.exit(this.getClass().getSimpleName(),CREATECMREQSERV);
			return result;	
	}
	
	/**
	 * @param throwable  
	 */
	private void logStackTrace(Throwable throwable)
	{
		StringWriter writer = new StringWriter();
		throwable.printStackTrace(new PrintWriter(writer));
		
	}
	//	Added for MPS 2
	/**
	 * This method is used to add and update contact service in manage contact section
	 */
	public ProcessCustomerContactResult addAndUpdateContactService(ProcessCustomerContactContract contract) 
			throws LGSRuntimeException,
			LGSBusinessException {
		LOGGER.enter(this.getClass().getSimpleName(), ADDANDUPDATECONTACTSERVICE);
		
		AccountContact accountContact= contract.getCustomerContact();
		try{

			
			ProcessCustomerContactWS locator = new ProcessCustomerContactWSLocator();
			ProcessCustomerContactWS_PortType port = locator
					.getLXKCustomerContact_webServices_provider_processCustomerContactWS_Port(new URL(
							getAddUpdateContactURL()));

			org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
			stub.setUsername(getUserName());
			stub.setPassword(getPassword());
			
			// define the Document Meta Data
			ObjectChangeActionType objectChangeActionType=null;
			
			LOGGER.debug("update contact flag="+accountContact.getUpdateContactFlag()+"new contact flag="+
			accountContact.getNewContactFlag()+"object action type is "+getObjectChangeActionTypeChange());
			if(accountContact!=null 
					&& accountContact.getUpdateContactFlag()==true
					&&  accountContact.getNewContactFlag()==false){
				
				objectChangeActionType= ObjectChangeActionType.fromValue(getObjectChangeActionTypeChange());
			}
			else if(accountContact!=null 
					&& accountContact.getUpdateContactFlag()==false
					&&  accountContact.getNewContactFlag()==true){
				objectChangeActionType= ObjectChangeActionType.fromValue(getObjectChangeActionTypeNew());
			}
			
			IntegrationFrequency integrationFrequency = IntegrationFrequency
					.fromValue(getIntegrationFrequency());
			
			DocumentMetaData documentMetaData = new DocumentMetaData();
			documentMetaData.setSourceSystem(getSourceSystem());
			documentMetaData.setBusinessProcess(getBusinessProcess());
			LOGGER.debug("objectChangeActionType--------------------------------------->>" + objectChangeActionType);
			documentMetaData.setObjectChangeActionType(objectChangeActionType);
			documentMetaData.setIntegrationCreationDateTime(getCurrentDateTime());
			documentMetaData.setIntegrationFrequency(integrationFrequency);		
					
			
			PhysicalAddress physicalAddress=null;
			if(accountContact.getAddress()!=null){
				physicalAddress = new PhysicalAddress();
				physicalAddress.setAddressName(accountContact.getAddress().getAddressName());
				physicalAddress.setAddressLine1(accountContact.getAddress().getAddressLine1());
				physicalAddress.setAddressLine2(accountContact.getAddress().getAddressLine2());
				physicalAddress.setOfficeNumber(accountContact.getAddress().getOfficeNumber());
				physicalAddress.setCity(accountContact.getAddress().getCity());
				physicalAddress.setStateCode(accountContact.getAddress().getStateCode());
				physicalAddress.setProvince(accountContact.getAddress().getProvince());
				physicalAddress.setDistrict(accountContact.getAddress().getDistrict());
				physicalAddress.setCounty(accountContact.getAddress().getCounty());
				
				physicalAddress.setRegion(accountContact.getAddress().getRegion());
				physicalAddress.setCountry(accountContact.getAddress().getCountry());
				physicalAddress.setCountryCode(accountContact.getAddress().getCountryISOCode());
				physicalAddress.setPostalCode(accountContact.getAddress().getPostalCode());
				physicalAddress.setStateFullName(accountContact.getAddress().getStateFullName());
				physicalAddress.setLatitude(accountContact.getAddress().getLatitude());
				physicalAddress.setLongitude(accountContact.getAddress().getLongitude());
				physicalAddress.setAddressCleansedFlag(accountContact.getAddress().getIsAddressCleansed()==true?"Y":"N");
				
				
				if (!accountContact.getAddress().getIsAddressCleansed()) {
					if(accountContact.getAddress().getState() !=null && !"".equals(accountContact.getAddress().getState().trim())){
					physicalAddress.setRegion(accountContact.getAddress().getState());
					}else{
						physicalAddress.setRegion("");
					}
				} 
				physicalAddress.setPhysicalLocation1(accountContact.getAddress().getPhysicalLocation1());
				physicalAddress.setPhysicalLocation2(accountContact.getAddress().getPhysicalLocation2());
				physicalAddress.setPhysicalLocation3(accountContact.getAddress().getPhysicalLocation3());
					
			}
			Data customerContact = new Data();
			customerContact.setContactId(contract.getUserContactId());
			customerContact.setCustomerName(getCustomerName());
			customerContact.setCustomerMDMId(contract.getMdmId());
			customerContact.setMDMCustomerLevel(contract.getMdmLevel());
			customerContact.setContactType(getContactType());
			customerContact.setFirstName(accountContact.getFirstName());
			customerContact.setLastName(accountContact.getLastName());
			customerContact.setEmailAddress(accountContact.getEmailAddress());
			customerContact.setWorkPhoneNumber(accountContact.getWorkPhone());
			customerContact.setAlternatePhoneNumber(accountContact.getAlternatePhone());
			customerContact.setContactReferenceId(accountContact.getContactId());
			customerContact.setPhysicalAddress(physicalAddress==null?null:physicalAddress);
					
			
			// Merge the Document meta data and employee data
			CustomerContact2 customerContact2 = new CustomerContact2(documentMetaData,customerContact);
			
			CustomerContact contact=new CustomerContact(customerContact2);
			
			PublishContacts publishContacts = new PublishContacts();
			publishContacts.setDebug(getDebug());
			publishContacts.setContact(contact);
			publishContacts.setSynchOrAsynch(getSynchOrAsynch());		
					
					
			
			PublishContactsResponse response = port.processCustomerContact(publishContacts);
				
			ProcessCustomerContactResult result = new ProcessCustomerContactResult();
			result.setSiebelContactId(response.getContactReferenceId());
			
			return result;
			
		}catch (Exception exception){
			
			
			throw new LGSBusinessException();
		}
			
	}
	
	/**
	 * This method is to get current date/time
	 * @return  
	 */
	private Calendar getCurrentDateTime() {
		Calendar cal = Calendar.getInstance();
		try {
			final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
			DateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			String currentDate = sdf.format(cal.getTime());
			Date date;
			date = (Date) sdf.parse(currentDate);
			cal.setTime(date);
		} catch (Exception e) {
			
			throw new RuntimeException(e.getMessage());
		}
		return cal;
	}
	//Ends MPS 2

	
}
