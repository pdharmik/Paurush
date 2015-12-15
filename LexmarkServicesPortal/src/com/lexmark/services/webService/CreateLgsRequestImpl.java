/**
 * 
 */
package com.lexmark.services.webService;

import java.net.URL;
import java.util.Map;

import javax.xml.rpc.holders.StringHolder;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

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
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.services.api.CreateLgsRequest;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.util.DateUtil;

/**. This Class implements all the service methods of CreateLgsRequest to invoke
 * the webservice for creating service requests through webmethod.
 * 
 * @author Sagar Sarkar
 *
 */
public class CreateLgsRequestImpl implements CreateLgsRequest {
	
	private static final Logger LOGGER = LogManager.getLogger(CreateLgsRequestImpl.class);
	
	
	private String address;	
	private String senderId; 	
	private String senderName;	
	private String receiverId;
	private String userName;
	private String password;
	
	private String originalPartnerId = ""; 
	private String originalPartnerName = "";
	private String receiverName = "";
	private String synchOrAsynch = "asynch";
	//private String returnDetail_x003F_ = "no";
	private String sourceSystem = "Web";

	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the senderId
	 */
	public String getSenderId() {
		return senderId;
	}

	/**
	 * @param senderId the senderId to set
	 */
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	/**
	 * @return the senderName
	 */
	public String getSenderName() {
		return senderName;
	}

	/**
	 * @param senderName the senderName to set
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * @return the receiverId
	 */
	public String getReceiverId() {
		return receiverId;
	}

	/**
	 * @param receiverId the receiverId to set
	 */
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	
	/**
	 * @return String 
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return String 
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/* (non-Javadoc)
	 * @see com.lexmark.services.api.CreateLgsRequest#createChangeManagementServiceRequest(com.lexmark.contract.CreateServiceRequestContract)
	 */
	/**
	 * @param contract  
	 * @param accountDetails  
	 * @return CreateServiceRequestResult  
	 * @throws Exception 
	 */
	public CreateServiceRequestResult createChangeManagementServiceRequest(
			CreateServiceRequestContract contract,Map<String,String> accountDetails) throws Exception {
		LOGGER.debug("CreateLgsRequestImpl.createChangeManagementServiceRequest.Enter");
		
		ServiceRequestWS wsLocator = new ServiceRequestWSLocator();		 
		ServiceRequestWS_PortType port = wsLocator.getServiceRequest_serviceRequestWS_Port(new URL(getAddress()));		
		org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());
		
		WebServiceDocumentMetaData metaData = new WebServiceDocumentMetaData(); 
		metaData.setOriginalPartnerId(originalPartnerId);
		metaData.setOriginalPartnerName(originalPartnerName);
		metaData.setReceiverId(getReceiverId());
		metaData.setReceiverName(receiverName);
		metaData.setSenderId(getSenderId());
		metaData.setSenderName(getSenderName());
		
		ChangeManagementServiceRequestData  serviceRequestData = null;
				
		if(contract.getServiceRequest()!=null){			
		serviceRequestData = new 	ChangeManagementServiceRequestData();
				
		serviceRequestData.setServiceRequestNumber(contract.getServiceRequest().getServiceRequestNumber());
		serviceRequestData.setServiceRequestType(contract.getServiceRequest().getServiceRequestType().getValue());
		LOGGER.debug(" Calling WM -- ServiceRequestType--->"+contract.getServiceRequest().getServiceRequestType().getValue());
		
		serviceRequestData.setServiceRequestSource(sourceSystem);		
		
		serviceRequestData.setRequestedService(contract.getServiceRequest().getArea().getValue());
		LOGGER.debug(" Calling WM -- RequestedService--->"+contract.getServiceRequest().getArea().getValue());			
		serviceRequestData.setRequestedServiceAction(contract.getServiceRequest().getSubArea().getValue());
		LOGGER.debug(" Calling  WM -- RequestedServiceAction ----->"+contract.getServiceRequest().getSubArea().getValue());
		LOGGER.debug("CreateLgsRequestImpl.createChangeManagementServiceRequest.ServiceRequestDate-->"+DateUtil.convertDateToGMTString(contract.getServiceRequest().getRequestedEffectiveDate()));
		serviceRequestData.setServiceRequestDate(DateUtil.convertDateToGMTString(contract.getServiceRequest().getRequestedEffectiveDate()));
				
		//LOGGER.debug("CreateLgsRequestImpl.createChangeManagementServiceRequest.Setting requestor....");

		SiebelContact requester = null;
		if(contract.getServiceRequest().getRequestor() != null){
			//requester =factory.createRequester2();
			requester = new SiebelContact();
			requester.setContactId(contract.getServiceRequest().getRequestor().getContactId());			
			requester.setFirstName(contract.getServiceRequest().getRequestor().getFirstName());
			requester.setLastName(contract.getServiceRequest().getRequestor().getLastName());
			requester.setEmailAddress(contract.getServiceRequest().getRequestor().getEmailAddress());
			requester.setWorkPhone(contract.getServiceRequest().getRequestor().getWorkPhone());
			//requester.setRequesterType(contract.getUserType());
			/*******Added For Partner and Employee, SR Creation was failing *******/
			if("Partner".equalsIgnoreCase(contract.getUserType())){
				requester.setContactType(ChangeMgmtConstant.VENDORREQTYPE);
			}
			else if(contract.getUserType().equalsIgnoreCase("employee")){
				LOGGER.debug("User type is employee");
				requester.setContactType(ChangeMgmtConstant.LEXMARKREQTYPE);
			}else{
					requester.setContactType(contract.getUserType());
				}
			
			LOGGER.debug("CreateLgsRequestImpl.createChangeManagementServiceRequest.requester.ContactId-->"+contract.getServiceRequest().getRequestor().getContactId());
			LOGGER.debug("CreateLgsRequestImpl.createChangeManagementServiceRequest.requester.UserType-->"+requester.getContactType());
			
		}		
		serviceRequestData.setRequester(requester);
		
		//LOGGER.debug("CreateLgsRequestImpl.createChangeManagementServiceRequest.Setting PrimaryContact...."+contract.getServiceRequest().getPrimaryContact());
		SiebelContact primaryContact = null;
		if(contract.getServiceRequest().getPrimaryContact() != null){
			primaryContact = new SiebelContact();
		
			primaryContact.setContactId(contract.getServiceRequest().getPrimaryContact().getContactId());
			primaryContact.setFirstName(contract.getServiceRequest().getPrimaryContact().getFirstName());
			primaryContact.setLastName(contract.getServiceRequest().getPrimaryContact().getLastName());			
			primaryContact.setWorkPhone(contract.getServiceRequest().getPrimaryContact().getWorkPhone());
			primaryContact.setEmailAddress(contract.getServiceRequest().getPrimaryContact().getEmailAddress());
			primaryContact.setAlternatePhone(contract.getServiceRequest().getPrimaryContact().getAlternatePhone());
			LOGGER.debug("CreateLgsRequestImpl.createChangeManagementServiceRequest.primaryContact.ContactId-->"+contract.getServiceRequest().getPrimaryContact().getContactId());
			LOGGER.debug("CreateLgsRequestImpl.createChangeManagementServiceRequest.primaryContact.FirstName-->"+contract.getServiceRequest().getPrimaryContact().getFirstName());
			LOGGER.debug("CreateLgsRequestImpl.createChangeManagementServiceRequest.primaryContact.LastName-->"+contract.getServiceRequest().getPrimaryContact().getLastName()
					+ "Alternate ph " + contract.getServiceRequest().getPrimaryContact().getAlternatePhone());
		}
		serviceRequestData.setPrimaryContact(primaryContact);
		

		LOGGER.debug("CreateLgsRequestImpl.createChangeManagementServiceRequest.Setting SecondaryContact...."+contract.getServiceRequest().getSecondaryContact());
		SiebelContact secondaryContact = null;
		if(contract.getServiceRequest().getSecondaryContact() != null){
			secondaryContact = new SiebelContact();		
			secondaryContact.setContactId(contract.getServiceRequest().getSecondaryContact().getContactId());
			secondaryContact.setFirstName(contract.getServiceRequest().getSecondaryContact().getFirstName());
			secondaryContact.setLastName(contract.getServiceRequest().getSecondaryContact().getLastName());		
			secondaryContact.setWorkPhone(contract.getServiceRequest().getSecondaryContact().getWorkPhone());
			secondaryContact.setEmailAddress(contract.getServiceRequest().getSecondaryContact().getEmailAddress());
			secondaryContact.setAlternatePhone(contract.getServiceRequest().getSecondaryContact().getAlternatePhone());
			LOGGER.debug("CreateLgsRequestImpl.createChangeManagementServiceRequest.secondaryContact.ContactId-->"+contract.getServiceRequest().getSecondaryContact().getContactId());
			LOGGER.debug("CreateLgsRequestImpl.createChangeManagementServiceRequest.secondaryContact.FirstName-->"+contract.getServiceRequest().getSecondaryContact().getFirstName());
			LOGGER.debug("CreateLgsRequestImpl.createChangeManagementServiceRequest.secondaryContact.LastName-->"+contract.getServiceRequest().getSecondaryContact().getLastName());
				
		}
		
		serviceRequestData.setSecondaryContact(secondaryContact);
		
		serviceRequestData.setServiceRequestDescription(contract.getServiceRequest().getAddtnlDescription());
		serviceRequestData.setCustomerReferenceNumber(contract.getServiceRequest().getCustomerReferenceId());
		serviceRequestData.setCostCenter(contract.getServiceRequest().getCostCenter());
		LOGGER.debug("Related SR Number ---->"+contract.getServiceRequest().getServiceRequestNumber());
		serviceRequestData.setRelatedServiceRequestNumber(contract.getServiceRequest().getServiceRequestNumber());
		serviceRequestData.setAttachmentNotes(contract.getServiceRequest().getNotes());		
		   
		}

	//	LOGGER.debug("Account id ::::   "+accountDetails.get("accountId"));
	//	LOGGER.debug("accountName ::::   "+accountDetails.get("accountName"));
	//	LOGGER.debug("accountOrganization ::::   "+accountDetails.get("accountOrganization"));
		//LOGGER.debug("agreementId ::::   "+accountDetails.get("agreementId"));
		
		SiebelAccount accountInformation=new SiebelAccount();
		//accInformation.setAccountId("");
		//accInformation.setAccountId("1-3S-540");
		if (accountDetails.get("accountId")==null){
			accountInformation.setAccountId("1-28FWPH");// this is the account id for la@lexmark.com in Siebel QA
			accountInformation.setAccountName("AT&T Corporation");
		}else{
			LOGGER.debug("inside else to get account info");
			accountInformation.setAccountId(accountDetails.get("accountId"));
			accountInformation.setAccountName(accountDetails.get("accountName"));
			accountInformation.setAccountCountry(accountDetails.get("country"));
		}
		serviceRequestData.setAccountInformation(accountInformation)   ;
		
		ChangeManagementServiceRequestWSInput2 changeManagementServiceRequestWSInput2 = new ChangeManagementServiceRequestWSInput2();
		changeManagementServiceRequestWSInput2.setDocumentMetaData(metaData);
		changeManagementServiceRequestWSInput2.setChangeManagementServiceRequestData(serviceRequestData);
		
		ChangeManagementServiceRequestWSInput changeManagementServiceRequestWSInput = new ChangeManagementServiceRequestWSInput();
		changeManagementServiceRequestWSInput.setChangeManagementServiceRequestWSInput(changeManagementServiceRequestWSInput2);
		
		
		StringHolder srNumber = new StringHolder();
		StringHolder srRowId = new StringHolder();
		ServiceRequestDetailsOutputHolder serviceRequestDetailsOutPutHolder = new ServiceRequestDetailsOutputHolder(); 
		LOGGER.debug("Printing WS input -------------------------------------------------------------------------");
		ObjectDebugUtil.printMultiObjectContent(changeManagementServiceRequestWSInput,LOGGER);
		LOGGER.debug("----------------------------------------- End -------------------------------------------------------");
		LOGGER.debug("Calling WM endpoint URL -->"+getAddress());
		LOGGER.debug(" New Webservice Call started ----->"+System.currentTimeMillis());
		port.createChangeManagementServiceRequest(null, changeManagementServiceRequestWSInput, synchOrAsynch, serviceRequestDetailsOutPutHolder, srRowId, srNumber);
		
		LOGGER.debug("Webservice Call ended ----->"+System.currentTimeMillis());
		CreateServiceRequestResult result = new CreateServiceRequestResult ();
		
		if (srNumber != null && srNumber.value != null ){
			   	LOGGER.debug("srNumber recieved : "+srNumber.value); 
			   	result.setServiceRequestNumber(srNumber.value);
		   }else{
			   throw new Exception("Error : Create Change Management Request failed ! [SR Number not received]");
		 }

		 if(srRowId != null && srRowId.value != null ) {
			 LOGGER.debug("SRRowId recieved : "+srRowId.value); 
			 result.setServiceRequestRowId(srRowId.value);
		   } else {
			   LOGGER.warn("Warning : Create Change Management Request failed ! [SR Row ID not received]");
		   }
		 LOGGER.debug("CreateLgsRequestImpl.createChangeManagementServiceRequest.Exit");
		 
		 
		 return result;
		 
	}
	

}
