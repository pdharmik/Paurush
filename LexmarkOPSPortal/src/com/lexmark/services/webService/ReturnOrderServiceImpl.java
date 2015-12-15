/**
 * 
 */
package com.lexmark.services.webService;

import java.net.URL;
import java.util.Date;
import java.util.Map;

import javax.xml.rpc.holders.StringHolder;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestData;
import com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestWSInput;
import com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestWSInput2;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSLocator;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS_PortType;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelAccount;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact;
import com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData;
import com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder;
import com.lexmark.contract.source.ReturnServiceRequestContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.framework.exception.LGSServiceException;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.services.api.ReturnOrderService;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.StringUtil;

/**
 * @author gsarkar
 *
 */
public class ReturnOrderServiceImpl  implements ReturnOrderService{
	
	private static final Logger LOGGER = LogManager.getLogger(ReturnOrderServiceImpl.class);
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging

	private String address;
	private String senderId;
	private String senderName;
	private String receiverId;
	private String userName;
	private String password;
	
	private static final String synchOrAsynch = "asynch";
	private static  final String debug="$null";
	private static  final String sourceSystem = "Web";
	
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
	
	/**.
	 * Invokes the Web-Method web service to create return order request.
	 * @param contract ReturnServiceRequestContract
	 * @param accountDetails Map<String, String>
	 * @throws Exception Exception
	 * @author Sagar Sarkar
	 */
	public CreateServiceRequestResult createChangeManagementServiceRequest(
			ReturnServiceRequestContract contract,
			Map<String, String> accountDetails,boolean isVendorFlag) throws LGSServiceException,Exception {
		
		if(contract == null || contract.getServiceRequest() == null){
			throw new Exception("Exception:ReturnServiceRequestContract null or invalid.");
		}
		CreateServiceRequestResult result = new CreateServiceRequestResult();
		
		
		ServiceRequestWS locator = new ServiceRequestWSLocator();		
		ServiceRequestWS_PortType port = locator.getServiceRequest_serviceRequestWS_Port(new URL(getAddress()));	
		org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());
		
		ConsumablesServiceRequestWSInput2 consumablesServiceRequestWSInput2 = new ConsumablesServiceRequestWSInput2();
		
		WebServiceDocumentMetaData documentMetaData = new WebServiceDocumentMetaData();
		documentMetaData.setSenderId(getSenderId());
		documentMetaData.setSenderName(getSenderName());
		documentMetaData.setReceiverId(getReceiverId());
		consumablesServiceRequestWSInput2.setDocumentMetaData(documentMetaData);
		ConsumablesServiceRequestData consumablesServiceRequestData = new ConsumablesServiceRequestData();
		ServiceRequest serviceRequest = contract.getServiceRequest();		
		consumablesServiceRequestData.setServiceRequestType(serviceRequest.getServiceRequestType().getValue());
		consumablesServiceRequestData.setServiceRequestSource(sourceSystem);
		consumablesServiceRequestData.setServiceRequestStatus("Open");
		consumablesServiceRequestData.setRequestedService(serviceRequest.getArea().getValue());
		consumablesServiceRequestData.setRequestedServiceAction(serviceRequest.getSubArea().getValue());
		consumablesServiceRequestData.setServiceRequestDate(DateUtil.convertDateToGMTString(new Date()));
		consumablesServiceRequestData.setServiceRequestDescription(serviceRequest.getAddtnlDescription());
		
		SiebelAccount accountInformation = new SiebelAccount();
		if(accountDetails !=null && accountDetails.get("accountId")!=null){
			LOGGER.debug("Account id sending is done which is "+accountDetails.get("accountId"));
			accountInformation.setAccountId(accountDetails.get("accountId"));
		}
		accountInformation.setMDMId(contract.getMdmId());
		accountInformation.setMDMLevel(contract.getMdmLevel());
		consumablesServiceRequestData.setAccountInformation(accountInformation);
		
		if(serviceRequest.getRequestor()!=null){
			//Requester3
			SiebelContact requester = new SiebelContact();
			requester.setContactId(serviceRequest.getRequestor().getContactId());
			requester.setFirstName(serviceRequest.getRequestor().getFirstName());
			requester.setLastName(serviceRequest.getRequestor().getLastName());
			requester.setWorkPhone(serviceRequest.getRequestor().getWorkPhone());
			requester.setEmailAddress(serviceRequest.getRequestor().getEmailAddress());
			
			if(!isVendorFlag)
			{
				requester.setContactType("Customer");
			}else{
				requester.setContactType("Vendor");
			}
			consumablesServiceRequestData.setRequester(requester);
		}if(serviceRequest.getPrimaryContact()!=null){
			//PrimaryContact3
			SiebelContact primaryContact = new SiebelContact();
			primaryContact.setContactId(serviceRequest.getPrimaryContact().getContactId());
			primaryContact.setFirstName(serviceRequest.getPrimaryContact().getFirstName());
			primaryContact.setLastName(serviceRequest.getPrimaryContact().getLastName());
			primaryContact.setWorkPhone(serviceRequest.getPrimaryContact().getWorkPhone());
			primaryContact.setEmailAddress(serviceRequest.getPrimaryContact().getEmailAddress());
			consumablesServiceRequestData.setPrimaryContact(primaryContact);
		}
		if(serviceRequest.getSecondaryContact()!=null){
			SiebelContact secondaryContact = new SiebelContact();
			//SecondaryContact3
			secondaryContact.setContactId(serviceRequest.getSecondaryContact().getContactId());
			secondaryContact.setFirstName(serviceRequest.getSecondaryContact().getFirstName());
			secondaryContact.setLastName(serviceRequest.getSecondaryContact().getLastName());
			secondaryContact.setWorkPhone(serviceRequest.getSecondaryContact().getWorkPhone());
			secondaryContact.setEmailAddress(serviceRequest.getSecondaryContact().getEmailAddress());
			LOGGER.debug("ReturnOrderServiceImpl.secondaryContact:"+secondaryContact.getFirstName());
			consumablesServiceRequestData.setSecondaryContact(secondaryContact);
		}
		if(LOGGER.isDebugEnabled())
		{
		LOGGER.debug("contract.getReturnAddress().getAddressId()---->"+contract.getReturnAddress().getAddressId());
		LOGGER.debug("contract.getReturnAddress().getAddressName()---->"+contract.getReturnAddress().getAddressName());
		LOGGER.debug("contract.getReturnAddress().getAddressLine1()---->"+contract.getReturnAddress().getAddressLine1());
		LOGGER.debug("contract.getReturnAddress().getAddressLine2()--->"+contract.getReturnAddress().getAddressLine2());
		LOGGER.debug("contract.getReturnAddress().getAddressLine3()--->"+contract.getReturnAddress().getAddressLine3());
		LOGGER.debug("contract.getReturnAddress().getCountry()--->"+contract.getReturnAddress().getCountry());
		LOGGER.debug("contract.getReturnAddress().getState()--->"+contract.getReturnAddress().getState());
		LOGGER.debug("contract.getReturnAddress().getPostalCode()--->"+contract.getReturnAddress().getPostalCode());
		}
		
		//Changes for CI BRD 13-10-08 --STARTS
		//ShipToAddress
		SiebelAddress shipToAddress = new SiebelAddress();
		if (!(contract.getReturnAddress().getAddressId().equalsIgnoreCase("-1"))) {
			LOGGER.debug("This is not a new addres hence we are not sending any address id");
			shipToAddress.setAddressId(contract.getReturnAddress().getAddressId());
		}
		
		shipToAddress.setAddressName(contract.getReturnAddress().getAddressName());
		shipToAddress.setAddressLine1(contract.getReturnAddress().getAddressLine1());
		shipToAddress.setAddressLine2(contract.getReturnAddress().getAddressLine2());
		//shipToAddress.setOfficeNumber(contract.getReturnAddress().getOfficeNumber());
		shipToAddress.setHouseNumber(contract.getReturnAddress().getOfficeNumber());
		shipToAddress.setCity(contract.getReturnAddress().getCity());
		shipToAddress.setStateCode(contract.getReturnAddress().getStateCode());
		shipToAddress.setProvince(contract.getReturnAddress().getProvince());
		shipToAddress.setDistrict(contract.getReturnAddress().getDistrict());
		shipToAddress.setCounty(contract.getReturnAddress().getCounty());
		shipToAddress.setRegion(contract.getReturnAddress().getRegion());
		shipToAddress.setCountry(contract.getReturnAddress().getCountry());
		shipToAddress.setCountryCode(contract.getReturnAddress().getCountryISOCode());
		shipToAddress.setPostalCode(contract.getReturnAddress().getPostalCode());
		shipToAddress.setStateFullName(contract.getReturnAddress().getStateFullName());
		shipToAddress.setLatitude(contract.getReturnAddress().getLatitude());
		shipToAddress.setLongitude(contract.getReturnAddress().getLongitude());
		
		//Changes for CI BRD 13-10-08 --ENDS
		shipToAddress.setPhysicalLocation1(contract.getReturnAddress().getPhysicalLocation1());
		shipToAddress.setPhysicalLocation2(contract.getReturnAddress().getPhysicalLocation2());
		shipToAddress.setPhysicalLocation3(contract.getReturnAddress().getPhysicalLocation3());
		
		
		/* Added for MPS2.1, send address cleansing flag */
		shipToAddress.setAddressMessage(contract.getReturnAddress().getErrorMsgForCleansing());//Address Cleansing Flag
		if(contract.getReturnAddress().getIsAddressCleansed()){
			shipToAddress.setAddressCleansedFlag("Y");
		}else{
			shipToAddress.setAddressCleansedFlag("N");
		}
		/* Added for MPS2.1 end */
		consumablesServiceRequestData.setShipToAddress(shipToAddress);
		consumablesServiceRequestData.setCustomerReferenceNumber(contract.getServiceRequest().getCustomerReferenceId());
		consumablesServiceRequestData.setCostCenter(contract.getServiceRequest().getCostCenter());
		consumablesServiceRequestData.setAttachmentNotes(contract.getServiceRequest().getNotes());
		
		//Added for source by MPS offshore
		if(isVendorFlag)
		{
			consumablesServiceRequestData.setVendorId(contract.getVendorID());
		}
		//End
		
		consumablesServiceRequestWSInput2.setConsumablesServiceRequestData(consumablesServiceRequestData);
		
		ConsumablesServiceRequestWSInput consumablesServiceRequestWSInput = new ConsumablesServiceRequestWSInput();
		consumablesServiceRequestWSInput.setConsumablesServiceRequestWSInput(consumablesServiceRequestWSInput2);
		StringHolder srNumber = new StringHolder();
		StringHolder srRowId = new StringHolder();
		ServiceRequestDetailsOutputHolder serviceRequestDetailsOutPutHolder = new ServiceRequestDetailsOutputHolder();
		LOGGER.debug("Started Return Supplies WS ----->"+System.currentTimeMillis());
		try {
			long timeBeforeCall = System.currentTimeMillis();
			port.createConsumablesServiceRequest(debug, consumablesServiceRequestWSInput, synchOrAsynch, serviceRequestDetailsOutPutHolder, srNumber, srRowId);
			long timeAfterCall=System.currentTimeMillis();
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.RETURNORDER_MSG_CREATECONSUMABLESSERVICEREQUEST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,contract);
		} catch (Exception e) {
			throw new LGSServiceException("WM_1001", e);
		}
			
		
		if(!StringUtil.isStringEmpty(srNumber.value) && !StringUtil.isStringEmpty(srRowId.value)){
			result.setServiceRequestNumber(srNumber.value);
			result.setServiceRequestRowId(srRowId.value);
		} 
		else {
				throw new LGSBusinessException("Error creating service request - SR Number not received");
			}
		
		return result;
	}

}
