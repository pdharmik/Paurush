package com.lexmark.services.webService.impl;

import java.net.URL;

import javax.xml.rpc.holders.StringHolder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestData;
import com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestWSInput;
import com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestWSInput2;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSLocator;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS_PortType;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact;
import com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData;
import com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder;
import com.lexmark.contract.CreateServiceRequestB2bContract;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.services.domain.SessionInformation;
import com.lexmark.services.webService.api.CreateServiceRequestB2B;
import com.lexmark.util.StringUtil;

public class CreateServiceRequestB2BImpl implements CreateServiceRequestB2B{

		/**. Instance variable of wrapper logger class **/
		private static Logger LOGGER = LogManager.getLogger(CreateServiceRequestB2BImpl.class);

		
		private static final String CREATECMREQSERV = "createCMRequestService";
		private String address;
		private String senderId;
		private String senderName;
		private String receiverId;
		private String userName;
		private String password;
		
		/**
		 * @return string 
		 */
		public String getAddress() {
			return address;
		}
		
		/**
		 * @param address 
		 */
		public void setAddress(String address) {
			this.address = address;
		}
		
		/**
		 * @return senderId
		 */
		public String getSenderId() {
			return senderId;
		}
		
		/**
		 * @param senderId 
		 */
		public void setSenderId(String senderId) {
			this.senderId = senderId;
		}
		
		/**
		 * @return senderName 
		 */
		public String getSenderName() {
			return senderName;
		}
		
		/**
		 * @param senderName 
		 */
		public void setSenderName(String senderName) {
			this.senderName = senderName;
		}
		
		/**
		 * @return receiverId 
		 */
		public String getReceiverId() {
			return receiverId;
		}
		
		/**
		 * @param receiverId 
		 */
		public void setReceiverId(String receiverId) {
			this.receiverId = receiverId;
		}
		
		/**
		 * @return userName 
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
		 * @return password 
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

		
			

		/**
		 * @param information 
		 * @param contract 
		 * @return CreateServiceRequestResult 
		 * @throws Exception 
		 */
		public CreateServiceRequestResult createServiceRequestB2B(
				SessionInformation information,CreateServiceRequestB2bContract contract) throws Exception {
		
			
			LOGGER.debug("[ In createServiceRequestB2B]");
			
			CreateServiceRequestResult result = null;
	   		try{

			LOGGER.debug("Beginning Service call for CreateService Request for B2B");
			
			
			
			ServiceRequestWS wsLocator = new ServiceRequestWSLocator();		 
			ServiceRequestWS_PortType port = wsLocator.getServiceRequest_serviceRequestWS_Port(new URL(getAddress()));		
			org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
			stub.setUsername(getUserName());
			stub.setPassword(getPassword());
			final String synchOrAsynch = "asynch";
			final String debug="$null";
			
			//final String sourceSystem = "SERVICES WEB PORTAL";
			final String sourceSystem = "Web";
			
			//ObjectFactory factory = new ObjectFactory();
			
			LOGGER.debug("Initialising Metadata object");		
			//DocumentMetaData3 documentMetaData = factory.createDocumentMetaData3();
			WebServiceDocumentMetaData documentMetaData = new WebServiceDocumentMetaData();
			
			documentMetaData.setSenderId(getSenderId());
			
			documentMetaData.setSenderName(getSenderName());
			
			documentMetaData.setReceiverId(getReceiverId());
			
			SiebelContact requester=new SiebelContact();
			
			requester.setContactType(contract.getRequesterType());
			requester.setContactId(contract.getVendorId());
			
			SiebelContact primaryContact= new SiebelContact();
			primaryContact.setContactId(contract.getContactId());
			
			ChangeManagementServiceRequestData cmServiceReqData= new ChangeManagementServiceRequestData();
			cmServiceReqData.setRequestedService(contract.getArea());
			cmServiceReqData.setRequestedServiceAction(contract.getSubArea());
			cmServiceReqData.setRequester(requester);
			cmServiceReqData.setPrimaryContact(primaryContact);
			cmServiceReqData.setServiceRequestSource(sourceSystem);
			cmServiceReqData.setServiceRequestType(contract.getServiceRequestType());
			cmServiceReqData.setServiceRequestDescription("");
			
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
			
				
			
			LOGGER.debug("Calling WM endpoint URL -->"+getAddress());
			LOGGER.debug(" New Webservice Call started ----->"+System.currentTimeMillis());	
			 
			//port.createChangeManagementServiceRequest(debug, cmServiceReqWSInput, synchOrAsynch, serviceRequestDetailsOutPutHolder, srRowId, srNumber);
			LOGGER.debug("After the ws call " + System.currentTimeMillis());
			
			result = new CreateServiceRequestResult();
			
			//LOGGER.debug("SR Number CM Req is "+ srNumber.value);
			LOGGER.debug("SR Row ID CM Req is " + srRowId.value);
				
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
				throw new LGSRuntimeException("Error while saving data"+ex.getMessage());
			}
			
			
			LOGGER.debug("[Exit create service Request B2B]");
			return result;
			
		}

		

		
		
		
	}



