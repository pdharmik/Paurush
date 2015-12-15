/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ManageAssetFormValidator
 * Package     		: com.lexmark.services.form.validator
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro						 		1.0             Initial Version
 *
 */
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
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.services.api.cm.ManageCHLOthersService;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.util.DateUtil;


public class ManageCHLOthersServiceImpl implements ManageCHLOthersService{
	private static final LEXLogger LOGGER = LEXLogger.getLEXLogger(ManageCHLOthersServiceImpl.class);
	private static Logger logger = LogManager.getLogger(ManageCHLOthersServiceImpl.class); 
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging
	
	private static String CLASS_NAME = "ManageCHLOthersServiceImpl" ;
	
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
	
		
	/* (non-Javadoc)
	 * @see com.lexmark.services.api.ManageCHLOthersService#createCMRequest(com.lexmark.contract.CreateServiceRequestContract)
	 */
	public CreateServiceRequestResult createCMRequest(CreateServiceRequestContract contract,Map<String,String> accountDetails) throws LGSBusinessException, LGSRuntimeException {
		String METHOD_NAME="createCMRequest";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		CreateServiceRequestResult result = new CreateServiceRequestResult();
		try{
			LOGGER.debug("Initialising static values");
			String synchOrAsynch = "asynch";
			String debug="$null";
			String sourceSystem = "Web";
			String serviceRequestNumber =null;
			
			ServiceRequestWS wsLocator = new ServiceRequestWSLocator();		 
			ServiceRequestWS_PortType port = wsLocator.getServiceRequest_serviceRequestWS_Port(new URL(getAddress()));		
			
			org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
			stub.setUsername(getUserName());
			stub.setPassword(getPassword());
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("User Name "+getUserName());
				LOGGER.debug("Password "+getPassword());
				
				LOGGER.debug("Initialising Metadata object");
			}
			WebServiceDocumentMetaData metaData =  new WebServiceDocumentMetaData(); 
			metaData.setReceiverId(getReceiverId());
			metaData.setSenderId(getSenderId());
			metaData.setSenderName(getSenderName());
			
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
			
			ServiceRequest serviceReq = contract.getServiceRequest();
			
			ChangeManagementServiceRequestData  serviceRequestData = new ChangeManagementServiceRequestData();//factory.createChangeManagementServiceRequestData();
			serviceRequestData.setAccountInformation(accInformation);
					
			//Changes for June 14.6 release 
			LOGGER.info("contract.getRequestedFrom() :: "+ contract.getRequestedFrom());
			if(contract.getRequestedFrom()!=null && contract.getRequestedFrom()!="" && (contract.getRequestedFrom().equalsIgnoreCase(ChangeMgmtConstant.SUPPLIES_REQ) || ("Upload".equalsIgnoreCase(serviceReq.getArea().getValue()) && ((ChangeMgmtConstant.UPLOADSUPPLIESREQUESTS.equalsIgnoreCase(serviceReq.getSubArea().getValue())))))){
				serviceRequestData.setServiceRequestType(ChangeMgmtConstant.OM_SR_TYPE);//Type to be decided yet for cancel request
			}
			else if("Consumables SR".equalsIgnoreCase(serviceReq.getSubArea().getValue())){
				LOGGER.debug("in consumables sr sub area");
				serviceRequestData.setServiceRequestType(ChangeMgmtConstant.OM_SR_TYPE);
			}
			else{
				serviceRequestData.setServiceRequestType(ChangeMgmtConstant.SERVICEREQTYPE);
			}
			//Ends June release
			
			serviceRequestData.setServiceRequestSource(sourceSystem);
			//Changes for June 14.6 release
			if(serviceReq.getArea() != null){
			/*if("Inquiry".equalsIgnoreCase(serviceReq.getArea().getValue()) && ((ChangeMgmtConstant.UPLOADSUPPLIESREQUESTS.equalsIgnoreCase(serviceReq.getSubArea().getValue()))
		||(ChangeMgmtConstant.UPLOADCONSUMABLESRSREQUEST.equalsIgnoreCase(serviceReq.getSubArea().getValue()))
		||(ChangeMgmtConstant.UPLOADEDCONSUMABLEREQUEST.equalsIgnoreCase(serviceReq.getSubArea().getValue())))){
					serviceRequestData.setRequestedService(ChangeMgmtConstant.INQUERYAREA);
			}else{
					serviceRequestData.setRequestedService(serviceReq.getArea().getValue());	
				}*/
				serviceRequestData.setRequestedService(serviceReq.getArea().getValue());
			
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug(" Calling WM -- Area--->"+serviceReq.getArea().getValue());	
			}
			}
			if(serviceReq.getSubArea() != null){
			serviceRequestData.setRequestedServiceAction(serviceReq.getSubArea().getValue());
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug(" Calling  WM -- Sub ARea ----->"+serviceReq.getSubArea().getValue());
			}
			}
			serviceRequestData.setServiceRequestDate(DateUtil.convertDateToGMTString((serviceReq.getRequestedEffectiveDate())));
			if(LOGGER.isDebugEnabled()){		
				LOGGER.debug("Initialising Requestor data...." + serviceReq.getRequestor());
			}
			SiebelContact requester = null;
			if(serviceReq.getRequestor() != null){
				requester = new SiebelContact();
				requester.setContactId(serviceReq.getRequestor().getContactId());			
				requester.setFirstName(serviceReq.getRequestor().getFirstName());
				requester.setLastName(serviceReq.getRequestor().getLastName());
				requester.setEmailAddress(serviceReq.getRequestor().getEmailAddress());
				requester.setWorkPhone(serviceReq.getRequestor().getWorkPhone());
				LOGGER.debug("user type is "+contract.getUserType());
				if(LexmarkConstants.USER_SEGMENT_PARTNER.equalsIgnoreCase(contract.getUserType()))
					requester.setContactType(ChangeMgmtConstant.VENDORREQTYPE);
				else{
					if(contract.getUserType().equalsIgnoreCase(LexmarkConstants.USER_SEGMENT_EMPLOYEE))
					{
						LOGGER.debug("User type is employee");
						requester.setContactType(ChangeMgmtConstant.LEXMARKREQTYPE);
					}else{
						requester.setContactType(contract.getUserType());
					}
				}	
				
			}		
			serviceRequestData.setRequester(requester);
			
			LOGGER.debug("Initialising PrimaryContact data...." +serviceReq.getPrimaryContact());
			SiebelContact primaryContact = null;
			if(serviceReq.getPrimaryContact() != null){
				primaryContact = new SiebelContact();
				primaryContact.setContactId(serviceReq.getPrimaryContact().getContactId());
				primaryContact.setFirstName(serviceReq.getPrimaryContact().getFirstName());
				primaryContact.setLastName(serviceReq.getPrimaryContact().getLastName());			
				primaryContact.setWorkPhone(serviceReq.getPrimaryContact().getWorkPhone());
				primaryContact.setEmailAddress(serviceReq.getPrimaryContact().getEmailAddress());
				primaryContact.setAlternatePhone(serviceReq.getPrimaryContact().getAlternatePhone());
				LOGGER.info("Setting in cm service req data primary contact alternate phone"+serviceReq.getPrimaryContact().getAlternatePhone());
			}
			serviceRequestData.setPrimaryContact(primaryContact);
			LOGGER.debug("Initialising SecondaryContact...."+serviceReq.getSecondaryContact());
			SiebelContact secondaryContact = null;
			if(serviceReq.getSecondaryContact() != null){
				secondaryContact = new SiebelContact();
			
				secondaryContact.setContactId(serviceReq.getSecondaryContact().getContactId());
				secondaryContact.setFirstName(serviceReq.getSecondaryContact().getFirstName());
				secondaryContact.setLastName(serviceReq.getSecondaryContact().getLastName());		
				secondaryContact.setWorkPhone(serviceReq.getSecondaryContact().getWorkPhone());
				secondaryContact.setEmailAddress(serviceReq.getSecondaryContact().getEmailAddress());
				secondaryContact.setAlternatePhone(serviceReq.getSecondaryContact().getAlternatePhone());
				
				LOGGER.info("Setting in cm service req data secondary contact alternate Phone no is" +serviceReq.getSecondaryContact().getAlternatePhone());			
				
			}			
			serviceRequestData.setSecondaryContact(secondaryContact);
			
			LOGGER.debug("Initialising Other informations....");
			serviceRequestData.setServiceRequestDescription(serviceReq.getAddtnlDescription());
			serviceRequestData.setCustomerReferenceNumber(serviceReq.getCustomerReferenceId());
			serviceRequestData.setCostCenter(serviceReq.getCostCenter());
			// changes for CR 13558, July release
			if(serviceReq.getServiceRequestNumber() !=null){
				serviceRequestData.setRelatedServiceRequestNumber(serviceReq.getServiceRequestNumber());
			}else{
				serviceRequestData.setRelatedServiceRequestNumber("");
			}
			
			serviceRequestData.setAttachmentNotes(serviceReq.getNotes());
						
			// Setting the previous Service Request No for the Update call
			LOGGER.debug("The previous service request no is -->" +contract.getPrevSrNo());
			
			if(contract.getPrevSrNo()!=null){
				LOGGER.debug("Inside If block while previous service request no is not null-->"+contract.getPrevSrNo() );
			 serviceRequestNumber= contract.getPrevSrNo(); 
			 serviceRequestData.setRelatedServiceRequestNumber(serviceRequestNumber);
				LOGGER.debug("Previous service request number is "+serviceRequestData.getRelatedServiceRequestNumber());
			 }
			 
			
			LOGGER.debug("Initialising cmServiceReqWSInputTwo object");
			ChangeManagementServiceRequestWSInput2 changeManagementServiceRequestWSInput2 = new ChangeManagementServiceRequestWSInput2();
			changeManagementServiceRequestWSInput2.setDocumentMetaData(metaData);
			changeManagementServiceRequestWSInput2.setChangeManagementServiceRequestData(serviceRequestData);
			
			ChangeManagementServiceRequestWSInput changeManagementServiceRequestWSInput =new ChangeManagementServiceRequestWSInput();
			changeManagementServiceRequestWSInput.setChangeManagementServiceRequestWSInput(changeManagementServiceRequestWSInput2);
	
			// initialize the web service output
			LOGGER.debug("Initialising web service output");  
			
			StringHolder srNumber = new StringHolder();
			StringHolder srRowId = new StringHolder();
			ServiceRequestDetailsOutputHolder serviceRequestDetailsOutPutHolder = new ServiceRequestDetailsOutputHolder(); 
			
			ObjectDebugUtil.printMultiObjectContent(changeManagementServiceRequestWSInput,logger);
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Calling WM endpoint URL -->"+getAddress());
				LOGGER.debug("Starting Call for Service Request Creation");
			}
			long timeBeforeCall = System.currentTimeMillis();
			port.createChangeManagementServiceRequest(debug, changeManagementServiceRequestWSInput, synchOrAsynch, serviceRequestDetailsOutPutHolder, srRowId, srNumber);
			long timeAfterCall=System.currentTimeMillis();
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MANAGECHLOTHERS_MSG_CREATECHANGEMANAGEMENTSERVICEREQUEST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,contract);
			//LOGGER.debug("** MPS PERFORMANCE TESTING CREATE CHL / OTHERS SR ==>: " + (System.currentTimeMillis() - timeBeforeCall) / 1000.0);
			if(LOGGER.isDebugEnabled()){
			    LOGGER.debug("SRRowId::"+ srRowId.value);
			    LOGGER.debug("srNumber::"+ srNumber.value);
			}
			   
		   if(srNumber.value == null){
			   LOGGER.debug("Error creating service request - SR Number is null");
			   throw new LGSBusinessException("Error creating service request - SR Number is null");
		   }
		   else if(srNumber.value!= null && srNumber.value.trim().equals("")){
			   LOGGER.debug("Error creating service request - SR Number is blank");
			   throw new LGSBusinessException("Error creating service request - SR Number is blank");
		   }
		   
		   if(srRowId.value == null){
			   LOGGER.debug("Error creating service request - SR Row id is null");
			   throw new LGSBusinessException("Error creating service request - SR Row id is null");
		   }
		   else if(srRowId.value != null && srRowId.value.trim().equals("")){
			   LOGGER.debug("Error creating service request - SR Row Id not received");
			   throw new LGSBusinessException("Error creating service request - SR Row Id not received");
		   }
			   
		   result.setServiceRequestNumber(srNumber.value);
		   result.setServiceRequestRowId(srRowId.value);
			  
			
		}catch (LGSBusinessException e) {
			LOGGER.error("LGSBusinessException occured:: "+ e.getMessage());
			throw new LGSBusinessException(e.getMessage());
		}catch (Exception e) {
			LOGGER.error("Exception occured:: "+ e.getMessage());
			throw new LGSBusinessException("Could not create SR for the Change Management Request.");
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);  
		return result;
	}

	
	
}
