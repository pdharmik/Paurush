/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: CreateServiceRequestImpl
 * Package     		: com.lexmark.services.webService
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Wipro						 		1.0             Initial Version
 *
 */
package com.lexmark.services.webService;

import java.net.URL;
import java.util.Date;

import javax.xml.rpc.holders.StringHolder;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.SiebelShared.createServiceRequest.client.Account3;
import com.lexmark.SiebelShared.createServiceRequest.client.Entitlement5;
import com.lexmark.SiebelShared.createServiceRequest.client.EntitlementServiceDetails5;
import com.lexmark.SiebelShared.createServiceRequest.client.SelectedServiceDetails2;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestData3;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestInput;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSInput;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSLocator;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS_PortType;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelAssetInformation;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelMDMCustomerInformation;
import com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData;
import com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder;
import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.domain.EntitlementServiceDetail;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;

public class CreateServiceRequestImpl implements
		com.lexmark.services.api.CreateServiceRequest {

	private static final Logger LOGGER = LogManager
			.getLogger(CreateServiceRequestImpl.class);
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging

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

	public CreateServiceRequestImpl() {

	}

	public CreateServiceRequestImpl(String address) {
		this.address = address;

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

	public CreateServiceRequestResult createServiceRequest(
			CreateServiceRequestContract contract) throws Exception {

		ServiceRequestWS locator = new ServiceRequestWSLocator();
		LOGGER.debug("WSDL Endpoint Address is: " + address);
		ServiceRequestWS_PortType port = locator
				.getServiceRequest_serviceRequestWS_Port(new URL(getAddress()));

		org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());

		String originalPartnerId = "";
		String originalPartnerName = "";
		String receiverName = "";
		String synchOrAsynch = "asynch";
		String returnDetail_x003F_ = "no";
		
		
		String sourceSystem=null;
		if(contract.getFleetManagementFlag()!=null && contract.getFleetManagementFlag().equalsIgnoreCase("true")){
		LOGGER.debug("Setting Map-Web");
			sourceSystem = "Map-Internal Portal";
		}
		else{
			LOGGER.debug("Setting Web");
		sourceSystem = "Internal Portal";
		}

		WebServiceDocumentMetaData documentMetaData = new WebServiceDocumentMetaData(
				getSenderId(), getSenderName(), originalPartnerId,
				originalPartnerName, getReceiverId(), receiverName);

		com.lexmark.domain.ServiceRequest sr = contract.getServiceRequest();

		SiebelContact requester = new SiebelContact(sr.getRequestor()
				.getContactId(), sr.getRequestor().getFirstName(), sr
				.getRequestor().getLastName(), sr.getRequestor()
				.getDepartment(), sr.getRequestor().getWorkPhone(), sr
				.getRequestor().getAlternatePhone(), sr.getRequestor()
				.getEmailAddress(), null, null, null, null, booleanToString(sr
				.getRequestor().getUpdateContactFlag()), booleanToString(sr
				.getRequestor().getNewContactFlag()), booleanToString(sr
				.getRequestor().getUserFavorite()), null);

		SiebelContact primaryContact = new SiebelContact(sr.getPrimaryContact()
				.getContactId(), sr.getPrimaryContact().getFirstName(), sr
				.getPrimaryContact().getLastName(), sr.getPrimaryContact()
				.getDepartment(), sr.getPrimaryContact().getWorkPhone(), sr
				.getPrimaryContact().getAlternatePhone(), sr
				.getPrimaryContact().getEmailAddress(), null, null, null, null,
				booleanToString(sr.getPrimaryContact().getUpdateContactFlag()),
				booleanToString(sr.getPrimaryContact().getNewContactFlag()),
				booleanToString(sr.getPrimaryContact().getUserFavorite()), null);

		SiebelContact secondaryContact = new SiebelContact(sr
				.getSecondaryContact().getContactId(), sr.getSecondaryContact()
				.getFirstName(), sr.getSecondaryContact().getLastName(), sr
				.getSecondaryContact().getDepartment(), sr
				.getSecondaryContact().getWorkPhone(), sr.getSecondaryContact()
				.getAlternatePhone(), sr.getSecondaryContact()
				.getEmailAddress(), null, null, null, null, booleanToString(sr
				.getSecondaryContact().getUpdateContactFlag()),
				booleanToString(sr.getSecondaryContact().getNewContactFlag()),
				booleanToString(sr.getSecondaryContact().getUserFavorite()),
				null);

		SiebelContact assetContact = new SiebelContact(sr.getAsset()
				.getAssetContact().getContactId(), sr.getAsset()
				.getAssetContact().getFirstName(), sr.getAsset()
				.getAssetContact().getLastName(), sr.getAsset()
				.getAssetContact().getDepartment(), sr.getAsset()
				.getAssetContact().getWorkPhone(), sr.getAsset()
				.getAssetContact().getAlternatePhone(), sr.getAsset()
				.getAssetContact().getEmailAddress(), null, null, null, null,
				booleanToString(sr.getAsset().getAssetContact()
						.getUpdateContactFlag()), booleanToString(sr.getAsset()
						.getAssetContact().getNewContactFlag()),
				booleanToString(sr.getAsset().getAssetContact()
						.getUserFavorite()), null);

		Account3 account = new Account3();
		if (sr.getAsset().getAccount() == null
				|| sr.getAsset().isNotMyPrinter()) {
			account.setAccountId("");
			account.setAccountName("");
		} else {
			account.setAccountId(sr.getAsset().getAccount().getAccountId());
			account.setAccountName(sr.getAsset().getAccount().getAccountName());
		}

		SiebelAssetInformation assetInformation = new SiebelAssetInformation();

		assetInformation.setAssetId(sr.getAsset().getAssetId());
		assetInformation.setAssetTag(sr.getAsset().getAssetTag());
		assetInformation.setAssetType(sr.getAsset().getAssetType());
		assetInformation.setAssetContact(assetContact);
		assetInformation.setAccount(account);
		assetInformation.setColorCapableIndicator(booleanToString(sr.getAsset()
				.getColorCapableFlag()));
		assetInformation.setDeviceTag(sr.getAsset().getDeviceTag());
		assetInformation.setIPAddress(sr.getAsset().getIpAddress());
		assetInformation.setSerialNumber(sr.getAsset().getSerialNumber());
		assetInformation.setProductLine(sr.getAsset().getProductLine());
		assetInformation.setProductModel(sr.getAsset().getModelNumber()); // same
																			// as
																			// Machine
																			// Type/Model
																			// Type
		assetInformation.setDeviceTag(sr.getAsset().getDeviceTag());
		assetInformation.setProductNumber(sr.getAsset().getProductTLI());
		assetInformation.setNotMyPrinterFlag(booleanToString(sr.getAsset()
				.isNotMyPrinter()));
		SelectedServiceDetails2[] selectedServiceDetails = new SelectedServiceDetails2[sr
				.getSelectedServiceDetails().size()];

		int i = 0;
		for (EntitlementServiceDetail detail : sr.getSelectedServiceDetails()) {
			SelectedServiceDetails2 selectedServiceDetail = new SelectedServiceDetails2();

			EntitlementServiceDetails5 entitlementServiceDetails2 = new EntitlementServiceDetails5();
			entitlementServiceDetails2.setPrimaryFlag(booleanToString(detail
					.getPrimaryFlag()));
			entitlementServiceDetails2.setServiceDetailId(nullToString(detail
					.getServiceDetailId()));
			entitlementServiceDetails2
					.setServiceDetailsDescription(nullToString(detail
							.getSiebelValue()));
			Entitlement5 entitlement2 = new Entitlement5();

			if (detail == null || sr.getAsset().isNotMyPrinter()) {
				entitlement2.setEntitlementId("");
				entitlement2.setEntitlementName("");
			} else {
				entitlement2.setEntitlementId(nullToString(sr.getAsset()
						.getEntitlement().getEntitlementId()));
				entitlement2.setEntitlementName(nullToString(sr.getAsset()
						.getEntitlement().getEntitlementName()));
			}
			entitlement2
					.setEntitlementServiceDetails(entitlementServiceDetails2);
			selectedServiceDetail.setEntitlement(entitlement2);
			selectedServiceDetails[i] = selectedServiceDetail;
			i++;
		}

		//Address Cleansing fields added for CI BRD 13-10-08 STARTS
		SiebelAddress serviceAddress = new SiebelAddress();
		if (!("-1".equalsIgnoreCase(sr.getServiceAddress().getAddressId()))) {
			LOGGER.debug("This is not a new addres hence we are not sending any address id");
			serviceAddress.setAddressId(sr.getServiceAddress().getAddressId());
		}
		
		serviceAddress.setAddressName(sr.getServiceAddress().getAddressName());
		serviceAddress.setAddressLine1(sr.getServiceAddress().getAddressLine1());
		serviceAddress.setAddressLine2(sr.getServiceAddress().getAddressLine2());
		//serviceAddress.setOfficeNumber(sr.getServiceAddress().getOfficeNumber());
		serviceAddress.setHouseNumber(sr.getServiceAddress().getOfficeNumber());
		serviceAddress.setCity(sr.getServiceAddress().getCity());
		serviceAddress.setStateCode(sr.getServiceAddress().getStateCode());
		serviceAddress.setProvince(sr.getServiceAddress().getProvince());
		serviceAddress.setDistrict(sr.getServiceAddress().getDistrict());
		serviceAddress.setCounty(sr.getServiceAddress().getCounty());
		serviceAddress.setRegion(sr.getServiceAddress().getRegion());
		serviceAddress.setCountry(sr.getServiceAddress().getCountry());
		serviceAddress.setCountryCode(sr.getServiceAddress().getCountryISOCode());
		serviceAddress.setPostalCode(sr.getServiceAddress().getPostalCode());
		serviceAddress.setStateFullName(sr.getServiceAddress().getStateFullName());
		serviceAddress.setLatitude(sr.getServiceAddress().getLatitude());
		serviceAddress.setLongitude(sr.getServiceAddress().getLongitude());
		
		serviceAddress.setPhysicalLocation1(sr.getServiceAddress().getPhysicalLocation1());
		serviceAddress.setPhysicalLocation2(sr.getServiceAddress().getPhysicalLocation2());
		serviceAddress.setPhysicalLocation3(sr.getServiceAddress().getPhysicalLocation3());
		
		serviceAddress.setAddressMessage(sr.getServiceAddress().getErrorMsgForCleansing());
		//serviceAddress.setAddressCleansedFlag(String.valueOf(sr.getServiceAddress().getIsAddressCleansed()));
		serviceAddress.setAddressCleansedFlag("Y"); //defcet #9926
		

		//Address Cleansing fields added for CI BRD 13-10-08 ENDS
		String trackingNumber = "";
		String carrier = "";
		String shipDate = "";
		String referenceNumber = nullToString(sr.getReferenceNumber()); // same
																		// as
																		// error
																		// codes
		String returnTrackingNumber = "";

		ServiceRequestData3 requestData = new ServiceRequestData3(
				sr.getServiceRequestNumber(),
				dateToString(sr.getServiceRequestDate()),
				sr.getServiceRequestStatus(), sourceSystem, null, requester,
				new SiebelMDMCustomerInformation(contract.getMdmId(), contract
						.getMdmLevel()), primaryContact, secondaryContact,
				assetInformation, sr.getProblemDescription(), null,
				sr.getOtherRequestedService(), "",null,null,
				returnTrackingNumber, "",selectedServiceDetails, serviceAddress, trackingNumber,
				carrier, shipDate, referenceNumber, returnTrackingNumber,
				sr.getOptionExchangeOtherDescription(), null, null, null, null,
				null);

		ServiceRequestInput input = new ServiceRequestInput();
		ServiceRequestWSInput inputWS = new ServiceRequestWSInput(
				documentMetaData, requestData);
		input.setServiceRequestWSInput(inputWS);

		// initialize the web service output

		StringHolder srNumber = new StringHolder("");
		StringHolder activityId = new StringHolder("");
		StringHolder SRRowId = new StringHolder("");
		StringHolder SRNumHashValue = new StringHolder("");
		StringHolder serviceRegionId = new StringHolder("");
		StringHolder siebelStatusMessage = new StringHolder("");
		String debug = null;   // changed defect#9926 if the debug is empty webmethod will pick the details from pipeline
		ServiceRequestDetailsOutputHolder serviceRequestDetails = new ServiceRequestDetailsOutputHolder();
		LOGGER.debug("Webservice Call[createBreakfixServiceRequest] started time ----->"
				+ System.currentTimeMillis());
		long timeBeforeCall = System.currentTimeMillis();
		port.createServiceRequest(debug, input, synchOrAsynch,
				returnDetail_x003F_, srNumber, activityId, SRRowId,
				SRNumHashValue, serviceRegionId, siebelStatusMessage,
				serviceRequestDetails);
		long timeAfterCall=System.currentTimeMillis();
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.CREATESERVICEREQUEST_MSG_CREATESERVICEREQUEST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,contract);
		// port.createServiceRequest(input, synchOrAsynch, returnDetail_x003F_,
		// srNumber, activityId, SRRowId, SRNumHashValue, serviceRegionId,
		// siebelStatusMessage, serviceRequestDetails);
		/*LOGGER.debug("** MPS PERFORMANCE TESTING CREATE BREAKFIX SR ==>: "
				+ (System.currentTimeMillis() - timeBeforeCall) / 1000.0
				+ " SR NUMBER IS " + srNumber.value);*/
		LOGGER.debug(" SR NUMBER IS " + srNumber.value);
		CreateServiceRequestResult result = new CreateServiceRequestResult();
		if (srNumber != null && srNumber.value != null
				&& !srNumber.value.trim().equals("")) {
			result.setServiceRequestNumber(srNumber.value);
		} 
		// added by nelson for MPS breakfix
		if (SRRowId != null && SRRowId.value != null
				&& !SRRowId.value.trim().equals("")) {
			result.setServiceRequestRowId(SRRowId.value);
		} 
		// end of addition by nelson for MPS breakfix
		return result;
	}

	private String nullToString(String s) {
		if (s == null)
			{return "";}
		return s;
	}

	private String booleanToString(Boolean b) {
		if (b == null)
			{return "false";}
		return b ? "true" : "false";
	}

	private String dateToString(Date d) {
		if (d == null)
			{return "";}
		return d.toString();
	}
}
