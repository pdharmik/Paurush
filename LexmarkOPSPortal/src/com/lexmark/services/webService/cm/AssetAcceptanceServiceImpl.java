/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: AssetAcceptanceServiceImpl.java
 * Package     		: com.lexmark.services.webService.cm
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro		2013 		1.0             Initial Version
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
import com.lexmark.services.api.cm.AssetAcceptanceService;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil; //Added for performance logging

/**
 * This Impl class implements the interface class AssetAcceptanceService for webService calls
 * @author wipro
 *
 */
public class AssetAcceptanceServiceImpl implements AssetAcceptanceService {
	
		/**. Instance variable of wrapper logger class **/
		private static final LEXLogger LOGGER = LEXLogger.getLEXLogger(AssetAcceptanceServiceImpl.class);
		private static Logger logger = LogManager.getLogger(AssetAcceptanceServiceImpl.class); 
		private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging
		private static String CLASS_NAME = "AssetAcceptanceServiceImpl" ;
		
		private String address;
		private String senderId; 	
		private String senderName;	
		private String receiverId;
		private String userName;
		private String password;
		
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
		
		/**
		 * This method is used for service call
		 */
		public CreateServiceRequestResult createCMRequest(CreateServiceRequestContract contract,
				Map<String,String> accountDetails) throws LGSBusinessException, LGSRuntimeException {
			String METHOD_NAME="createCMRequest";
			LOGGER.enter(CLASS_NAME, METHOD_NAME);
			CreateServiceRequestResult result = new CreateServiceRequestResult();
			try{
				String synchOrAsynch = "asynch";
				String debug="$null";
				String sourceSystem = "Web";
				String serviceRequestNumber =null;
				ServiceRequestWS wsLocator = new ServiceRequestWSLocator();		 
				ServiceRequestWS_PortType port = wsLocator.getServiceRequest_serviceRequestWS_Port(new URL(getAddress()));		
				org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
				stub.setUsername(getUserName());
				stub.setPassword(getPassword());
				LOGGER.debug("User Name:" +getUserName() + "Password:" +getPassword());
				WebServiceDocumentMetaData metaData =  new WebServiceDocumentMetaData(); 
				metaData.setReceiverId(getReceiverId());
				metaData.setSenderId(getSenderId());
				metaData.setSenderName(getSenderName());
				LOGGER.debug("Account Id:" +accountDetails.get(ChangeMgmtConstant.ACCOUNTID) + "Account Name:" +accountDetails.get(ChangeMgmtConstant.ACCOUNTNAME) + "Account Organization:" +accountDetails.get(ChangeMgmtConstant.ACCOUNTORG) + "Agreement Id:" +accountDetails.get(ChangeMgmtConstant.AGREEMENTID) + "Agreement Name:" +accountDetails.get(ChangeMgmtConstant.AGREEMENTNAME) + "Country:" +accountDetails.get(ChangeMgmtConstant.COUNTRY));
				SiebelAccount accInformation=new SiebelAccount();
				if (accountDetails.get(ChangeMgmtConstant.ACCOUNTID)==null){
					accInformation.setAccountId("");
					accInformation.setAccountName("");
				}else{
					accInformation.setAccountId(accountDetails.get(ChangeMgmtConstant.ACCOUNTID));
					accInformation.setAccountName(accountDetails.get(ChangeMgmtConstant.ACCOUNTNAME));
					accInformation.setAccountCountry(accountDetails.get(ChangeMgmtConstant.COUNTRY));
				}
				ServiceRequest serviceReq = contract.getServiceRequest();
				ChangeManagementServiceRequestData  serviceRequestData = new ChangeManagementServiceRequestData();
				serviceRequestData.setAccountInformation(accInformation);
				serviceRequestData.setServiceRequestType(ChangeMgmtConstant.SERVICEREQTYPE);
				serviceRequestData.setServiceRequestSource(sourceSystem);
				//serviceRequestData.setRequestedService(ChangeMgmtConstant.ADDASSETAREA);
				serviceRequestData.setRequestedService(ChangeMgmtConstant.DATAMANAGEMENTAREA);
				serviceRequestData.setRequestedServiceAction(serviceReq.getSubArea().getValue());
				LOGGER.debug("Calling WM---Area:" +ChangeMgmtConstant.DATAMANAGEMENTAREA + "Calling WM---SubArea:" +serviceReq.getSubArea().getValue());
				SiebelContact requester = null;
				if(serviceReq.getRequestor() != null){
					requester = new SiebelContact();
					requester.setContactId(serviceReq.getRequestor().getContactId());			
					requester.setFirstName(serviceReq.getRequestor().getFirstName());
					requester.setLastName(serviceReq.getRequestor().getLastName());
					requester.setEmailAddress(serviceReq.getRequestor().getEmailAddress());
					requester.setWorkPhone(serviceReq.getRequestor().getWorkPhone());
					if(LexmarkConstants.USER_SEGMENT_PARTNER.equalsIgnoreCase(contract.getUserType())){
						requester.setContactType(ChangeMgmtConstant.VENDORREQTYPE);
					}else{
						if(contract.getUserType().equalsIgnoreCase(LexmarkConstants.USER_SEGMENT_EMPLOYEE))
						{
							requester.setContactType(ChangeMgmtConstant.LEXMARKREQTYPE);
						}else{
							requester.setContactType(contract.getUserType());
						}
					}
				}
				serviceRequestData.setRequester(requester);
				SiebelContact primaryContact = null;
				if(serviceReq.getPrimaryContact() != null){
					primaryContact = new SiebelContact();
					primaryContact.setContactId(serviceReq.getPrimaryContact().getContactId());
					primaryContact.setFirstName(serviceReq.getPrimaryContact().getFirstName());
					primaryContact.setLastName(serviceReq.getPrimaryContact().getLastName());			
					primaryContact.setWorkPhone(serviceReq.getPrimaryContact().getWorkPhone());
					primaryContact.setEmailAddress(serviceReq.getPrimaryContact().getEmailAddress());
					primaryContact.setAlternatePhone(serviceReq.getPrimaryContact().getAlternatePhone());
				}
				serviceRequestData.setPrimaryContact(primaryContact);
				SiebelContact secondaryContact = null;
				if(serviceReq.getSecondaryContact() != null){
					secondaryContact = new SiebelContact();
					secondaryContact.setContactId(serviceReq.getSecondaryContact().getContactId());
					secondaryContact.setFirstName(serviceReq.getSecondaryContact().getFirstName());
					secondaryContact.setLastName(serviceReq.getSecondaryContact().getLastName());		
					secondaryContact.setWorkPhone(serviceReq.getSecondaryContact().getWorkPhone());
					secondaryContact.setEmailAddress(serviceReq.getSecondaryContact().getEmailAddress());
					secondaryContact.setAlternatePhone(serviceReq.getSecondaryContact().getAlternatePhone());
				}
				serviceRequestData.setSecondaryContact(secondaryContact);
				serviceRequestData.setServiceRequestDescription(serviceReq.getAddtnlDescription());
				serviceRequestData.setCustomerReferenceNumber(serviceReq.getCustomerReferenceId());
				serviceRequestData.setCostCenter(serviceReq.getCostCenter());
				// changes for CR 13558, July release
				if(serviceReq.getServiceRequestNumber() != null){
					serviceRequestData.setRelatedServiceRequestNumber(serviceReq.getServiceRequestNumber());
				}else{
					serviceRequestData.setRelatedServiceRequestNumber("");
				}
				
				serviceRequestData.setAttachmentNotes(serviceReq.getNotes());
				// Setting the previous Service Request No for the Update call
				LOGGER.debug("The previous service request no is -->" +contract.getPrevSrNo());
				if(contract.getPrevSrNo()!=null){
					serviceRequestNumber= contract.getPrevSrNo(); 
					serviceRequestData.setRelatedServiceRequestNumber(serviceRequestNumber);
				}
				ChangeManagementServiceRequestWSInput2 changeManagementServiceRequestWSInput2 = new ChangeManagementServiceRequestWSInput2();
				changeManagementServiceRequestWSInput2.setDocumentMetaData(metaData);
				changeManagementServiceRequestWSInput2.setChangeManagementServiceRequestData(serviceRequestData);
				ChangeManagementServiceRequestWSInput changeManagementServiceRequestWSInput =new ChangeManagementServiceRequestWSInput();
				changeManagementServiceRequestWSInput.setChangeManagementServiceRequestWSInput(changeManagementServiceRequestWSInput2);
				// initialize the web service output
				StringHolder srNumber = new StringHolder();
				StringHolder srRowId = new StringHolder();
				ServiceRequestDetailsOutputHolder serviceRequestDetailsOutPutHolder = new ServiceRequestDetailsOutputHolder(); 
				ObjectDebugUtil.printMultiObjectContent(changeManagementServiceRequestWSInput,logger);  
				if(LOGGER.isDebugEnabled()){
					LOGGER.debug("Starting Call for Service Request Creation");
				}
				long timeBeforeCall = System.currentTimeMillis();
				port.createChangeManagementServiceRequest(debug, changeManagementServiceRequestWSInput, synchOrAsynch, 
						serviceRequestDetailsOutPutHolder, srRowId, srNumber);
				long timeAfterCall=System.currentTimeMillis();
				
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ASSETACCEPTANCE_MSG_CREATECHANGEMANAGEMENTSERVICEREQUEST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,contract);
				//LOGGER.debug("** MPS PERFORMANCE TESTING CREATE ASSET ACCEPTANCE SR ==>: " + (System.currentTimeMillis() - timeBeforeCall) / 1000.0);
				if(LOGGER.isDebugEnabled()){
					LOGGER.debug("Debug:" +debug + "SR RowId:" +srRowId.value + "SR Number:" +srNumber.value);
				}
				if(srNumber.value == null){
				   throw new LGSBusinessException("Error creating service request - SR Number is null");
			   }
			   else if(srNumber.value!= null && srNumber.value.trim().equals("")){
				   throw new LGSBusinessException("Error creating service request - SR Number is blank");
			   }
			   if(srRowId.value == null){
				   throw new LGSBusinessException("Error creating service request - SR Row id is null");
			   }
			   else if(srRowId.value != null && srRowId.value.trim().equals("")){
				   throw new LGSBusinessException("Error creating service request - SR Row Id not received");
			   }
			   result.setServiceRequestNumber(srNumber.value);
			   result.setServiceRequestRowId(srRowId.value);
				
			}catch (LGSBusinessException e) {
				throw new LGSBusinessException(e.getMessage());
			}catch (Exception e) {
				throw new LGSBusinessException("Could not create SR for the Change Management Request.");
			}
			LOGGER.exit(CLASS_NAME, METHOD_NAME);  
			return result;
		}
}
