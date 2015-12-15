package com.lexmark.services.webService.cm;

import java.net.URL;
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
import com.lexmark.domain.ServiceRequest;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.services.api.cm.ManageAddressService;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.StringUtil;
public class ManageAddressServiceImpl implements ManageAddressService {
	
	/**. Instance variable of wrapper logger class **/
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(ManageAddressServiceImpl.class);
	private static Logger logger = LogManager.getLogger(ManageAddressServiceImpl.class); //Added for performance logging
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging
	
	private static final String CREATECMREQSERV = "createCMRequestService";
	private String address;
	private String senderId;
	private String senderName;
	private String receiverId;
	private String userName;
	private String password;
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getSenderId() {
		return senderId;
	}
	
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	
	public String getSenderName() {
		return senderName;
	}
	
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	public String getReceiverId() {
		return receiverId;
	}
	
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	/*public ManageAssetCreateServiceImpl() {
	
	}
	
	public ManageAssetCreateServiceImpl(String address) {
	this.address = address;
	
	}*/
		

	public CreateServiceRequestResult createCMRequestService(
			CreateServiceRequestContract serviceReqContract, Map<String,String> accountDetails)
			throws LGSRuntimeException, LGSBusinessException {
		
		LOGGER.enter(this.getClass().getSimpleName(), CREATECMREQSERV);
		
		CreateServiceRequestResult result = null;
   		try{

		LOGGER.debug("Beginning Service call for CreateService Request for Manage Asset");
		
		//ServiceRequestWS locator = new ServiceRequestWS();
		//ServiceRequestWSPortType port = locator.getServiceRequestServiceRequestWSPort();
		
		ServiceRequestWS wsLocator = new ServiceRequestWSLocator();		 
		ServiceRequestWS_PortType port = wsLocator.getServiceRequest_serviceRequestWS_Port(new URL(getAddress()));		
		org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());
		final String synchOrAsynch = "asynch";
		final String debug="$null";
		ServiceRequest srData=null;
		String serviceRequestNumber =null;
		//final String sourceSystem = "SERVICES WEB PORTAL";
		final String sourceSystem = "Web";
		
		//ObjectFactory factory = new ObjectFactory();
		
		LOGGER.debug("Initialising Metadata object");		
		//DocumentMetaData3 documentMetaData = factory.createDocumentMetaData3();
		WebServiceDocumentMetaData documentMetaData = new WebServiceDocumentMetaData();
		
		documentMetaData.setSenderId(getSenderId());
		
		documentMetaData.setSenderName(getSenderName());
		
		documentMetaData.setReceiverId(getReceiverId());
		
		if(serviceReqContract.getServiceRequest()!=null){
			
		srData = serviceReqContract.getServiceRequest();
		}
		if (accountDetails!=null){
			LOGGER.debug("Account id ::::   "+accountDetails.get(ChangeMgmtConstant.ACCOUNTID));
			LOGGER.debug("Account Name ::::   "+accountDetails.get(ChangeMgmtConstant.ACCOUNTNAME));
			LOGGER.debug("Account Organization ::::   "+accountDetails.get(ChangeMgmtConstant.ACCOUNTORG));
			LOGGER.debug("Agreement Id ::::   "+accountDetails.get(ChangeMgmtConstant.AGREEMENTID));
			LOGGER.debug("Agreement Name ::::   "+accountDetails.get(ChangeMgmtConstant.AGREEMENTNAME));
			LOGGER.debug("Country ::::   "+accountDetails.get(ChangeMgmtConstant.COUNTRY));
		}
		
		SiebelAccount accInformation=new SiebelAccount();
		//accInformation.setAccountId("");
		//accInformation.setAccountId("1-3S-540");
		if (accountDetails.get(ChangeMgmtConstant.ACCOUNTID)==null){
			accInformation.setAccountId("1-28FWPH");// this is the account id for la@lexmark.com in Siebel QA
			accInformation.setAccountName("AT&T Corporation");
		}else{
			LOGGER.debug("inside else to get account info");
			accInformation.setAccountId(accountDetails.get(ChangeMgmtConstant.ACCOUNTID));
			accInformation.setAccountName(accountDetails.get(ChangeMgmtConstant.ACCOUNTNAME));
			accInformation.setAccountCountry(accountDetails.get(ChangeMgmtConstant.COUNTRY));
		}
		
		//accInformation.setMDMId("");
		//accInformation.setMDMLevel("");

		
		ChangeManagementServiceRequestData cmServiceReqData= new ChangeManagementServiceRequestData();//factory.createChangeManagementServiceRequestData();
		cmServiceReqData.setAccountInformation(accInformation);
		
		//cmServiceReqData.setServiceRequestNumber(serviceReqContract.getPrevSrNo());
		// changes for CR 13558, July release
		if(serviceReqContract.getPrevSrNo() !=null){
			cmServiceReqData.setRelatedServiceRequestNumber(serviceReqContract.getPrevSrNo());
		}else{
			cmServiceReqData.setRelatedServiceRequestNumber("");
		}
		
		LOGGER.debug("Previous service request number is "+cmServiceReqData.getRelatedServiceRequestNumber());
		cmServiceReqData.setServiceRequestType("Fleet Management");
		cmServiceReqData.setRequestedService(serviceReqContract.getServiceRequest().getArea().getValue());
		//cmServiceReqData.setRequestedService("Add Fleet data");
		
		LOGGER.debug("Area is " + serviceReqContract.getServiceRequest().getArea().getValue());
		LOGGER.debug("Sub area is " + serviceReqContract.getServiceRequest().getSubArea().getValue());
	
		cmServiceReqData.setRequestedServiceAction(serviceReqContract.getServiceRequest().getSubArea().getValue());
		//cmServiceReqData.setRequestedServiceAction("Address Creation");
		cmServiceReqData.setServiceRequestDate(DateUtil.convertDateToGMTString(srData.getRequestedEffectiveDate()));
		if (srData.getAddtnlDescription() == null){
			cmServiceReqData.setServiceRequestDescription("");
		}else{
			cmServiceReqData.setServiceRequestDescription(srData.getAddtnlDescription());
		}
		
		cmServiceReqData.setCustomerReferenceNumber(srData.getCustomerReferenceId());
		cmServiceReqData.setCostCenter(srData.getCostCenter());
		cmServiceReqData.setServiceRequestSource(sourceSystem);
		//notes attachment start
		cmServiceReqData.setAttachmentNotes(srData.getNotes());
		//notes attachment end
		//cmServiceReqData.setRelatedServiceRequestNumber(serviceReqContract.getPrevSrNo());
		//cmServiceReqData.setAttachmentNotes(srData.getNotes());
		
		SiebelContact requestor=null;
		
		if(srData.getRequestor()!=null)
		{
			requestor= new SiebelContact();//factory.createRequester2();
			requestor.setContactId(srData.getRequestor().getContactId());
			requestor.setEmailAddress(srData.getRequestor().getEmailAddress());
			requestor.setFirstName(srData.getRequestor().getFirstName());
			requestor.setLastName(srData.getRequestor().getLastName());
			requestor.setWorkPhone(srData.getRequestor().getWorkPhone());
			LOGGER.debug("user type is "+serviceReqContract.getUserType());
			if("Partner".equalsIgnoreCase(serviceReqContract.getUserType()))
				requestor.setContactType("Vendor");
			else{
				//requestor.setRequesterType(serviceReqContract.getUserType());
				if(serviceReqContract.getUserType().equalsIgnoreCase("employee"))
				{
					LOGGER.debug("User type is employee");
					requestor.setContactType("Lexmark");
				}else{
				requestor.setContactType(serviceReqContract.getUserType());
				}
			}

			LOGGER.info("Setting in cm service req data requestor info");
			cmServiceReqData.setRequester(requestor);
		}
				
		LOGGER.info("Setting info of the requestor Contact ID: " + srData.getPrimaryContact().getContactId()
				+ "First Name :" + srData.getPrimaryContact().getFirstName() + "Lst Name: "
				+ srData.getPrimaryContact().getLastName() + "Email ID: " +  srData.getPrimaryContact().getEmailAddress()+"Ph no is"+srData.getPrimaryContact().getWorkPhone());
		
		LOGGER.info("Setting primary contact ");
		
		SiebelContact primaryContact=null;
		
		if(srData.getPrimaryContact()!=null)
		{
			//primaryContact=factory.createPrimaryContact2();
			primaryContact = new SiebelContact();
			primaryContact.setContactId(srData.getPrimaryContact().getContactId());
			primaryContact.setEmailAddress(srData.getPrimaryContact().getEmailAddress());
			primaryContact.setFirstName(srData.getPrimaryContact().getFirstName());
			primaryContact.setLastName(srData.getPrimaryContact().getLastName());
			primaryContact.setWorkPhone(srData.getPrimaryContact().getWorkPhone());
			primaryContact.setAlternatePhone(srData.getPrimaryContact().getAlternatePhone());
			LOGGER.info("Setting in cm service req data primary contact alternate phone"+srData.getPrimaryContact().getAlternatePhone());
			
			cmServiceReqData.setPrimaryContact(primaryContact);
		}
		
		LOGGER.info("Setting secondary contact ");
		SiebelContact secContact=null;
		
		if(srData.getSecondaryContact()!=null)
		{
			//secContact=factory.createSecondaryContact2();
			secContact = new SiebelContact();
			secContact.setContactId(srData.getSecondaryContact().getContactId());
			secContact.setEmailAddress(srData.getSecondaryContact().getEmailAddress());
			secContact.setFirstName(srData.getSecondaryContact().getFirstName());
			secContact.setLastName(srData.getSecondaryContact().getLastName());
			secContact.setWorkPhone(srData.getSecondaryContact().getWorkPhone());
			secContact.setAlternatePhone(srData.getSecondaryContact().getAlternatePhone());
			
			LOGGER.info("Setting in cm service req data secondary alternate Phone-->"+srData.getSecondaryContact().getAlternatePhone());
			
			cmServiceReqData.setSecondaryContact(secContact);
		}
		
		// Setting the previous Service Request No for the Update call
		LOGGER.debug("The previous service request no is -->" +serviceReqContract.getPrevSrNo());
		
		if(serviceReqContract.getPrevSrNo()!=null){
			LOGGER.debug("Inside If block while previous service request no is not null-->"+serviceReqContract.getPrevSrNo() );
		 serviceRequestNumber= serviceReqContract.getPrevSrNo(); 
		 cmServiceReqData.setServiceRequestNumber(serviceRequestNumber);
		 }
		 
		 
		ChangeManagementServiceRequestWSInput2 cmServiceReqWSInputTwo=new ChangeManagementServiceRequestWSInput2();
		cmServiceReqWSInputTwo.setChangeManagementServiceRequestData(cmServiceReqData);
		cmServiceReqWSInputTwo.setDocumentMetaData(documentMetaData);
		
		
		ChangeManagementServiceRequestWSInput cmServiceReqWSInput=new ChangeManagementServiceRequestWSInput();
		cmServiceReqWSInput.setChangeManagementServiceRequestWSInput(cmServiceReqWSInputTwo);
		
		//Holder<String> srNumber = new Holder<String>("");		
		//Holder<String> srRowId = new Holder<String>("");		
		//Holder<ServiceRequestDetailsOutput> serviceRequestDetailsOutPutHolder = new Holder<ServiceRequestDetailsOutput>();
		
		StringHolder srNumber = new StringHolder();
		StringHolder srRowId = new StringHolder();
		ServiceRequestDetailsOutputHolder serviceRequestDetailsOutPutHolder = new ServiceRequestDetailsOutputHolder(); 
		
			
		LOGGER.info("Printing WS input ----------------------");	
		ObjectDebugUtil.printMultiObjectContent(cmServiceReqWSInput,logger);
		LOGGER.debug("Calling WM endpoint URL -->"+getAddress());
		LOGGER.debug(" New Webservice Call started ----->"+System.currentTimeMillis());	
		long timeBeforeCall = System.currentTimeMillis();
		//port.createChangeManagementServiceRequest(debug, cmServiceReqWSInput, synchOrAsynch, serviceRequestDetailsOutPutHolder, srRowId, srNumber); 
		try{
		port.createChangeManagementServiceRequest(debug, cmServiceReqWSInput, synchOrAsynch, serviceRequestDetailsOutPutHolder, srRowId, srNumber);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		long timeAfterCall=System.currentTimeMillis();
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MANAGEADDRESSES_MSG_CREATECMREQUESTSERVICE, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,serviceReqContract);
		//LOGGER.debug("After the ws call " + System.currentTimeMillis());
		
		result = new CreateServiceRequestResult();
		
		LOGGER.debug("SR Number CM Req is "+ srNumber.value);
		LOGGER.debug("SR Row ID CM Req is " + srRowId.value);
			
		/*result.setServiceRequestNumber(srNumber.value);
		
		result.setServiceRequestRowId(srRowId.value);*/
		
			
		if(!StringUtil.isStringEmpty(srNumber.value) && !StringUtil.isStringEmpty(srRowId.value)){
			result.setServiceRequestNumber(srNumber.value);
			result.setServiceRequestRowId(srRowId.value);
		} 
		else {
				throw new LGSBusinessException("Error creating service request - SR Number not received");
			}
		
		}//catch(ServiceException serviceEx)
		//{
		//	throw new LGSBusinessException("Error while getting ServiceRequestWS_PortType object");
		catch (Exception ex) {
			throw new LGSRuntimeException("Error while saving data");
		}
		LOGGER.exit(this.getClass().getSimpleName(), CREATECMREQSERV);
		return result;
		//return result;
	}
	
	
}

