package com.lexmark.services.webService;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.xml.rpc.holders.StringHolder;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.SiebelShared.createServiceRequest.client.CreditCardInformation;
import com.lexmark.SiebelShared.createServiceRequest.client.OrderInformation2;
import com.lexmark.SiebelShared.createServiceRequest.client.PaymentDetails;
import com.lexmark.SiebelShared.createServiceRequest.client.ProductDetails;
import com.lexmark.SiebelShared.createServiceRequest.client.SellableItemServiceRequestWSInput;
import com.lexmark.SiebelShared.createServiceRequest.client.SellableItemsServiceRequestData;
import com.lexmark.SiebelShared.createServiceRequest.client.SellableItemsServiceRequestWSInput;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSLocator;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS_PortType;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelAccount;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelPaymentDetails;
import com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData;
import com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder;
import com.lexmark.contract.CreateHardwareRequestContract;
import com.lexmark.domain.BundlePart;
import com.lexmark.domain.OrderPart;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.services.api.CreateHardwareRequest;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.StringUtil;

/**
 * @author wipro
 * @version 2.1
 * 
 */
public class CreateHardwareRequestImpl implements CreateHardwareRequest {
	private static final Logger LOGGER = LogManager.getLogger(CreateHardwareRequestImpl.class);
	private static final LEXLogger LEXLOGGER = LEXLogger.getLEXLogger(CreateHardwareRequestImpl.class);
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

	public CreateServiceRequestResult createHardwareRequest(
			CreateHardwareRequestContract contract,
			Map<String, String> accountDetails) throws Exception {
		CreateServiceRequestResult result = new CreateServiceRequestResult();
		LOGGER.debug("Beginning Service call for CreateService Request for Consumable Order");
		printValueToBeSentToWM(contract);
		Date dateNow = new Date();
		SimpleDateFormat dateformatMMDDYYYY = new SimpleDateFormat("MMddyyyy");

		String srStatus = ChangeMgmtConstant.OM_SR_STATUS_OPEN;

		StringBuilder nowMMDDYYYY = new StringBuilder(dateformatMMDDYYYY
				.format(dateNow));
		LOGGER.info("DEBUG: Today in MMDDYYYY: '" + nowMMDDYYYY + "'");

		ServiceRequestWS locator = new ServiceRequestWSLocator();
		ServiceRequestWS_PortType port = locator
				.getServiceRequest_serviceRequestWS_Port(new URL(getAddress()));
		org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());

		final String synchOrAsynch = "asynch";
		final String debug = "$null";
		final String sourceSystem = "Web";
		SellableItemsServiceRequestWSInput sellableServiceRequestWSInput = new SellableItemsServiceRequestWSInput();
		WebServiceDocumentMetaData documentMetaData = new WebServiceDocumentMetaData();
		documentMetaData.setSenderId(getSenderId());
		documentMetaData.setSenderName(getSenderName());
		documentMetaData.setReceiverId(getReceiverId());

		LOGGER.debug("getSenderId:: " + getSenderId() + " getReceiverId:: " + getReceiverId());
		sellableServiceRequestWSInput.setDocumentMetaData(documentMetaData);

		// Setting up ConsumablesServiceRequestData
		SellableItemsServiceRequestData sellableServiceRequestData = new SellableItemsServiceRequestData();
		sellableServiceRequestData.setServiceRequestType(contract.getSrType());
		sellableServiceRequestData.setServiceRequestSource(sourceSystem);
		if (contract.getSRStatus() != null) {
			LOGGER.debug("service Request status is " + contract.getSRStatus());
			sellableServiceRequestData.setServiceRequestStatus(contract
					.getSRStatus());
			if (contract.getSRStatus().equalsIgnoreCase(
					ChangeMgmtConstant.OM_SR_STATUS_DRAFT)) {
				srStatus = ChangeMgmtConstant.OM_SR_STATUS_DRAFT;
			}

		}
		if(contract.getServiceRequest().isProject()== true){
			sellableServiceRequestData.setRequestedService("HW Order");
			LOGGER.debug("Hardware  Area coming as " + "HW Order");
			sellableServiceRequestData.setRequestedServiceAction("Project Based");
			LOGGER.debug("Hardware Sub Area coming as " + "Project Based");
			sellableServiceRequestData.setProjectName(contract.getServiceRequest().getProjectName());
			sellableServiceRequestData.setProjectPhase(contract.getServiceRequest().getProjectPhase());
			sellableServiceRequestData.setCoveredService("Shipment Only");
		}else{
			sellableServiceRequestData.setRequestedService(contract.getSrArea());
			LOGGER.debug("Hardware Sub Area coming as " + contract.getSrSubArea());
			sellableServiceRequestData.setRequestedServiceAction(contract
					.getSrSubArea());
			if(contract.getSrArea().equalsIgnoreCase("HW Order")){
				sellableServiceRequestData.setCoveredService("Shipment Only");
			}
		}
		
		LOGGER.debug("After setting the Sub Area for SR is " + sellableServiceRequestData.getRequestedServiceAction());
		sellableServiceRequestData.setServiceRequestDate(DateUtil
				.convertDateToGMTString(new Date()));// Non nillable
		if (accountDetails.get("contractNumber") != null) {
			LOGGER.debug("contractNumber is " + accountDetails.get("contractNumber"));
			sellableServiceRequestData.setRelatedContractNumber(accountDetails
					.get("contractNumber"));
		}
		if (accountDetails.get("agreementId") != null) {
			LOGGER.debug("agreementId is " + accountDetails.get("agreementId"));
			sellableServiceRequestData.setRelatedAgreementNumber(accountDetails
					.get("agreementId"));
		}
		if (contract.getInstallationOnlyFlag() != null
				&& contract.getInstallationOnlyFlag() != "") {
			sellableServiceRequestData.setInstallationOnlyFlag(contract
					.getInstallationOnlyFlag());
		}
		LOGGER.debug("before Additional Desc");
		sellableServiceRequestData.setServiceRequestDescription(contract
				.getServiceRequest().getAddtnlDescription());// Non nillable
		LOGGER.debug("after Additional Desc");

		SiebelAccount accountInformation = new SiebelAccount();
		LOGGER.debug("Hardware based order so need to send account id");
		accountInformation.setAccountId(accountDetails.get("accountId"));// Non
																			// nillable
		LOGGER.debug("Account id sending is done which is " + accountDetails.get("accountId"));
		accountInformation.setSoldToNumber(contract.getSoldToNumber());
		if (contract.getBillToNumber() != null && !contract.getBillToNumber().equals("")) {
			accountInformation.setBillToNumber(contract.getBillToNumber());
		}
		accountInformation.setSoldToNumber(contract.getSoldToNumber());
		sellableServiceRequestData.setAccountInformation(accountInformation);

		if (contract.getServiceRequest().getRequestor() != null) {
			SiebelContact requester = new SiebelContact();
			requester.setContactId(contract.getServiceRequest().getRequestor()
					.getContactId());
			requester.setFirstName(contract.getServiceRequest().getRequestor()
					.getFirstName());
			requester.setLastName(contract.getServiceRequest().getRequestor()
					.getLastName());
			requester.setWorkPhone(contract.getServiceRequest().getRequestor()
					.getWorkPhone());
			requester.setEmailAddress(contract.getServiceRequest()
					.getRequestor().getEmailAddress());
			requester.setAlternatePhone(contract.getServiceRequest()
					.getRequestor().getAlternatePhone());
			requester.setContactType(contract.getUserType());
			LOGGER.debug("RequesterType ----->" + requester.getContactType());
			sellableServiceRequestData.setRequester(requester);
		}

		if (contract.getServiceRequest().getPrimaryContact() != null) {
			SiebelContact primaryContact = new SiebelContact();
			primaryContact.setContactId(contract.getServiceRequest()
					.getPrimaryContact().getContactId());
			primaryContact.setFirstName(contract.getServiceRequest()
					.getPrimaryContact().getFirstName());
			primaryContact.setLastName(contract.getServiceRequest()
					.getPrimaryContact().getLastName());
			primaryContact.setWorkPhone(contract.getServiceRequest()
					.getPrimaryContact().getWorkPhone());
			primaryContact.setEmailAddress(contract.getServiceRequest()
					.getPrimaryContact().getEmailAddress());
			primaryContact.setAlternatePhone(contract.getServiceRequest()
					.getPrimaryContact().getAlternatePhone());
			sellableServiceRequestData.setPrimaryContact(primaryContact);
		}

		if (contract.getServiceRequest().getSecondaryContact() != null) {
			SiebelContact secondaryContact = new SiebelContact();
			secondaryContact.setContactId(contract.getServiceRequest()
					.getSecondaryContact().getContactId());
			secondaryContact.setFirstName(contract.getServiceRequest()
					.getSecondaryContact().getFirstName());
			secondaryContact.setLastName(contract.getServiceRequest()
					.getSecondaryContact().getLastName());
			secondaryContact.setWorkPhone(contract.getServiceRequest()
					.getSecondaryContact().getWorkPhone());
			secondaryContact.setEmailAddress(contract.getServiceRequest()
					.getSecondaryContact().getEmailAddress());
			secondaryContact.setAlternatePhone(contract.getServiceRequest()
					.getSecondaryContact().getAlternatePhone());
			sellableServiceRequestData.setSecondaryContact(secondaryContact);
		}

		// Ship To Address
		SiebelAddress shipToAddress = new SiebelAddress();
		if (!(contract.getShipToAddress().getAddressId().equalsIgnoreCase("-1"))) {
			LOGGER.debug("This is not a new addres hence we are not sending any address id");
			shipToAddress.setAddressId(contract.getShipToAddress()
					.getAddressId());
		}

		shipToAddress.setAddressName(contract.getShipToAddress()
				.getStoreFrontName() != null ? contract.getShipToAddress()
				.getStoreFrontName().trim() : "");
		shipToAddress.setAddressLine1(contract.getShipToAddress()
				.getAddressLine1() != null ? contract.getShipToAddress()
				.getAddressLine1().trim() : "");
		shipToAddress.setAddressLine2(contract.getShipToAddress()
				.getAddressLine2() != null ? contract.getShipToAddress()
				.getAddressLine2().trim() : "");
		shipToAddress
				.setCity(contract.getShipToAddress().getCity() != null ? contract
						.getShipToAddress().getCity().trim()
						: "");
		shipToAddress.setCountry(contract.getShipToAddress().getCountry());
		shipToAddress
				.setPostalCode(contract.getShipToAddress().getPostalCode() != null ? contract
						.getShipToAddress().getPostalCode().trim()
						: "");
		shipToAddress.setPhysicalLocation1(contract.getShipToAddress()
				.getPhysicalLocation1() != null ? contract.getShipToAddress()
				.getPhysicalLocation1().trim() : "");
		shipToAddress.setPhysicalLocation2(contract.getShipToAddress()
				.getPhysicalLocation2() != null ? contract.getShipToAddress()
				.getPhysicalLocation2().trim() : "");
		shipToAddress.setPhysicalLocation3(contract.getShipToAddress()
				.getPhysicalLocation3() != null ? contract.getShipToAddress()
				.getPhysicalLocation3().trim() : "");
		shipToAddress.setHouseNumber(contract.getShipToAddress()
				.getOfficeNumber());
		shipToAddress.setStateCode(contract.getShipToAddress().getStateCode());
		shipToAddress.setStateFullName(contract.getShipToAddress()
				.getStateFullName());
		shipToAddress.setCounty(contract.getShipToAddress().getCounty());
		shipToAddress.setProvince(contract.getShipToAddress().getProvince());
		shipToAddress.setDistrict(contract.getShipToAddress().getDistrict());
		shipToAddress.setLatitude(contract.getShipToAddress().getLatitude());
		shipToAddress.setLongitude(contract.getShipToAddress().getLongitude());
		shipToAddress.setRegion(contract.getShipToAddress().getRegion());
		shipToAddress.setAddressMessage(contract.getShipToAddress()
				.getErrorMsgForCleansing());
		
		if (contract.getShipToAddress().getIsAddressCleansed()) {
			shipToAddress.setAddressCleansedFlag("Y");
		} else {
			shipToAddress.setAddressCleansedFlag("N");
			if(contract.getShipToAddress().getState() !=null && !"".equals(contract.getShipToAddress().getState().trim())){
			shipToAddress.setRegion(contract.getShipToAddress().getState());
			}else{
				shipToAddress.setRegion("");
			}
		}
		
		
		sellableServiceRequestData.setShipToAddress(shipToAddress);

		/*
		 * Setting up Material Array Size based on total number of products in
		 * cart
		 */
		int materialArraySize = 0;
		if (contract.getBundleList() != null
				&& contract.getBundleList().size() > 0) {
			materialArraySize = materialArraySize
					+ contract.getBundleList().size();
			for (int i = 0; i < contract.getBundleList().size(); i++) {
				OrderPart orderPart = contract.getBundleList().get(i);
				if (orderPart.getBundlePartList() != null
						&& orderPart.getBundlePartList().size() > 0) {
					materialArraySize = materialArraySize
							+ orderPart.getBundlePartList().size();
				}
			}
		}

		if (contract.getAccessoriesList() != null
				&& contract.getAccessoriesList().size() > 0) {
			materialArraySize = materialArraySize
					+ contract.getAccessoriesList().size();
		}
		/* End Setting Size */

		LOGGER.debug("materialArraySize --->" + materialArraySize);
		ProductDetails[] materialDetailsArray = new ProductDetails[materialArraySize];
		int materialArrIndex = 0;
		// Adding Material for Bundle
		if (contract.getBundleList() != null
				&& contract.getBundleList().size() > 0) {
			for (int i = 0; i < contract.getBundleList().size(); i++) {
				ProductDetails materialDetails = new ProductDetails();
				OrderPart orderPart = contract.getBundleList().get(i);
				materialDetails.setQuantity(orderPart.getOrderQuantity());
				materialDetails.setCatalogId(orderPart.getCatalogId());
				materialDetails.setProductId(orderPart.getSupplyId());
				materialDetails.setUnitPrice(orderPart.getUnitPrice());
				materialDetails.setTaxAmount(orderPart.getTax());
				materialDetails.setTotalLinePrice(orderPart.getTotal());
				materialDetails.setCurrencyCode(orderPart.getCurrency());
				materialDetails.setRelatedContractLineNumber(orderPart
						.getContractLineItemId());
				materialDetails.setHardwareConfigurationID(orderPart
						.getContractLineItemId());
				
				if (orderPart.getBundlePartList() != null
						&& orderPart.getBundlePartList().size() > 0) {
					for (int j = 0; j < orderPart.getBundlePartList().size(); j++) {
						
						int partQty = 0;
						ProductDetails bundlePartDetails = new ProductDetails();
						BundlePart bundlePart = orderPart.getBundlePartList()
								.get(j);
						if (bundlePart.getLineId() != null) {
							bundlePartDetails
									.setRelatedContractLineNumber(bundlePart
											.getLineId());
						}
						
						if (bundlePart.getParentLineId() != null) {
							bundlePartDetails
									.setRelatedContractParentLineNumber(bundlePart
											.getParentLineId());
						}
						if (bundlePart.getQty() != null
								&& !bundlePart.getQty().equals("")) {
							partQty = Integer.parseInt(bundlePart.getQty())
									* Integer.parseInt(orderPart
											.getOrderQuantity());
						}
						bundlePartDetails
								.setQuantity(Integer.toString(partQty));
						bundlePartDetails.setCatalogId(bundlePart
								.getCatalogId());
						bundlePartDetails
								.setProductId(bundlePart.getSupplyId());
						bundlePartDetails.setProductNumber(bundlePart
								.getPartNumber());
						bundlePartDetails.setPlacementId(contract.getPlacementId());
						materialDetailsArray[materialArrIndex] = bundlePartDetails;
						materialArrIndex++;
					}
				}
				materialDetailsArray[materialArrIndex] = materialDetails;
				materialArrIndex++;
			}

		}
		if (contract.getAccessoriesList() != null
				&& contract.getAccessoriesList().size() > 0) {
			for (int i = 0; i < contract.getAccessoriesList().size(); i++) {
				ProductDetails materialDetails = new ProductDetails();
				OrderPart orderPart = contract.getAccessoriesList().get(i);

				materialDetails.setProductType(orderPart.getPartType());
				materialDetails.setQuantity(orderPart.getOrderQuantity());
				materialDetails.setCatalogId(orderPart.getCatalogId());
				materialDetails.setProductId(orderPart.getSupplyId());
				materialDetails.setProductNumber(orderPart.getPartNumber());
				materialDetails.setHardwareConfigurationID(orderPart
						.getContractLineItemId());
				materialDetails.setRelatedContractLineNumber(orderPart
						.getContractLineItemId());
				materialDetails.setUnitPrice(orderPart.getUnitPrice());
				materialDetails.setTaxAmount(orderPart.getTax());
				materialDetails.setTotalLinePrice(orderPart.getTotal());
				materialDetails.setCurrencyCode(orderPart.getCurrency());

				materialDetailsArray[materialArrIndex] = materialDetails;
				materialArrIndex++;
			}
		}

		sellableServiceRequestData.setProductDetails(materialDetailsArray);
		// ProductDetails done
		
		sellableServiceRequestData.setCustomerReferenceNumber(contract
				.getServiceRequest().getCustomerReferenceId());
		sellableServiceRequestData.setCostCenter(contract.getServiceRequest()
				.getCostCenter());
		sellableServiceRequestData.setServiceRequestComments(contract
				.getServiceRequest().getAddtnlDescription());
		sellableServiceRequestData.setAttachmentNotes(contract
				.getAttachmentNotes());

		// OrderInformation2
		OrderInformation2 orderInformation = new OrderInformation2();
		orderInformation.setSpecialHandlingInstructions(contract
				.getSpecialInstruction());
		sellableServiceRequestData.setOrderInformation(orderInformation);

		// PaymentInformation
		sellableServiceRequestData.setBillingModel(contract.getPaymentType());

		if (contract.getSRStatus().equalsIgnoreCase(
				ChangeMgmtConstant.OM_SR_STATUS_DRAFT)) {
			LOGGER.debug("Entering Payment Informations SR status is --->"
					+ srStatus);
			PaymentDetails paymentInformation = new PaymentDetails();
			
			if (contract.getPoNumber() != null) {
				paymentInformation.setPurchaseOrderNumber(contract
						.getPoNumber());
			}
			if (contract.getBillToAddress() != null) {
				SiebelAddress billToAddress = new SiebelAddress();
				billToAddress.setAddressId(contract.getBillToAddress()
						.getAddressId());
				billToAddress.setAddressName(contract.getBillToAddress()
						.getStoreFrontName());// set all 16 fields for bill to
												// address
				billToAddress.setAddressLine1(contract.getBillToAddress()
						.getAddressLine1());
				billToAddress.setAddressLine2(contract.getBillToAddress()
						.getAddressLine2());
				billToAddress.setCity(contract.getBillToAddress().getCity());
				billToAddress.setCountry(contract.getBillToAddress()
						.getCountry());
				billToAddress.setPostalCode(contract.getBillToAddress()
						.getPostalCode());
				// Added for cleansed fields MPS 2.1
				billToAddress.setHouseNumber(contract.getBillToAddress()
						.getOfficeNumber());
				billToAddress.setStateCode(contract.getBillToAddress()
						.getStateCode());
				billToAddress.setStateFullName(contract.getBillToAddress()
						.getStateFullName());
				billToAddress
						.setCounty(contract.getBillToAddress().getCounty());
				billToAddress.setProvince(contract.getBillToAddress()
						.getProvince());
				billToAddress.setDistrict(contract.getBillToAddress()
						.getDistrict());
				billToAddress.setLatitude(contract.getBillToAddress()
						.getLatitude());
				billToAddress.setLongitude(contract.getBillToAddress()
						.getLongitude());
				billToAddress
						.setRegion(contract.getBillToAddress().getRegion());
				billToAddress.setAddressMessage(contract.getBillToAddress()
						.getErrorMsgForCleansing());
				if (contract.getBillToAddress().getIsAddressCleansed()) {
					billToAddress.setAddressCleansedFlag("Y");
				} else {
					billToAddress.setAddressCleansedFlag("N");
					if(contract.getBillToAddress().getState() !=null && !"".equals(contract.getBillToAddress().getState().trim())){
					billToAddress.setRegion(contract.getBillToAddress().getState());
					}else{
						billToAddress.setRegion("");
					}
				}
				
				paymentInformation.setBillToAddress(billToAddress);
			}

			if (contract.getPoNumber() != null
					|| contract.getBillToAddress() != null) {
				SiebelPaymentDetails siebelPaymentDetails = new SiebelPaymentDetails();
				siebelPaymentDetails.setPaymentDetails(paymentInformation);
				sellableServiceRequestData
						.setPaymentInformation(siebelPaymentDetails);
			}
		}

		if (srStatus.equalsIgnoreCase(ChangeMgmtConstant.OM_SR_STATUS_OPEN)) {

			PaymentDetails paymentInformation = new PaymentDetails();
			paymentInformation.setPurchaseOrderNumber(contract.getPoNumber());
			if (contract.getPaymentMethod() != null) {
				paymentInformation.setPaymentMethod(contract.getPaymentMethod());
			}
			if (contract.getCreditCardToken() != null) {
				if (contract.getBillToAddress() != null) {
					SiebelAddress billToAddress = new SiebelAddress();
					billToAddress.setAddressId(contract.getBillToAddress()
							.getAddressId());
					billToAddress.setAddressName(contract.getBillToAddress()
							.getStoreFrontName());// set all 16 fields for bill
													// to address
					billToAddress.setAddressLine1(contract.getBillToAddress()
							.getAddressLine1());
					billToAddress.setAddressLine2(contract.getBillToAddress()
							.getAddressLine2());
					billToAddress
							.setCity(contract.getBillToAddress().getCity());
					billToAddress.setCountry(contract.getBillToAddress()
							.getCountry());
					billToAddress.setPostalCode(contract.getBillToAddress()
							.getPostalCode());
					// Added for cleansed fields MPS 2.1
					billToAddress.setHouseNumber(contract.getBillToAddress()
							.getOfficeNumber());
					billToAddress.setStateCode(contract.getBillToAddress()
							.getStateCode());
					billToAddress.setStateFullName(contract.getBillToAddress()
							.getStateFullName());
					billToAddress.setCounty(contract.getBillToAddress()
							.getCounty());
					billToAddress.setProvince(contract.getBillToAddress()
							.getProvince());
					billToAddress.setDistrict(contract.getBillToAddress()
							.getDistrict());
					billToAddress.setLatitude(contract.getBillToAddress()
							.getLatitude());
					billToAddress.setLongitude(contract.getBillToAddress()
							.getLongitude());
					billToAddress.setRegion(contract.getBillToAddress()
							.getRegion());
					billToAddress.setAddressMessage(contract.getBillToAddress()
							.getErrorMsgForCleansing());// Flag for cleansing to
														// be passed to WM
					
					if (contract.getBillToAddress().getIsAddressCleansed()) {
						billToAddress.setAddressCleansedFlag("Y");
					} else {
						billToAddress.setAddressCleansedFlag("N");
						if(contract.getBillToAddress().getState() !=null && !"".equals(contract.getBillToAddress().getState().trim())){
						billToAddress.setRegion(contract.getBillToAddress().getState());
						}else{
							billToAddress.setRegion("");
						}
					}
					paymentInformation.setBillToAddress(billToAddress);
				}
				// Populating Credit card details
				if (contract.getCreditCardToken() != null
						&& contract.getCreditCardToken() != "") {
					CreditCardInformation creditCardInfo = new CreditCardInformation();
					creditCardInfo.setCreditCardAuthorizationNumber(contract
							.getCreditCardToken());
					creditCardInfo.setCreditCardToken(contract
							.getCreditCardToken());
					creditCardInfo.setCreditCardExpirationDate(contract
							.getCreditCardExpirationDate());
					creditCardInfo.setCreditCardNumber(contract
							.getCreditCardEncryptedNo());
					creditCardInfo.setCreditCardType(contract
							.getCreditCardType());
					creditCardInfo.setCreditCardHolderName(contract
							.getCardHolderName());
					creditCardInfo.setCreditCardTransactionId(contract
							.getTransactionId());
					paymentInformation.setCreditCardInformation(creditCardInfo);
				}

			} else {
				paymentInformation.setPurchaseOrderNumber(contract
						.getPoNumber());
			}

			SiebelPaymentDetails siebelPaymentDetails = new SiebelPaymentDetails();
			siebelPaymentDetails.setPaymentDetails(paymentInformation);

			sellableServiceRequestData
					.setPaymentInformation(siebelPaymentDetails);
		}

		if (contract.getRelatedServiceRequestedNumber() != null
				&& !contract.getRelatedServiceRequestedNumber().isEmpty()) {
			LOGGER.debug("Updating SR NUMBER : " + contract.getRelatedServiceRequestedNumber());
			sellableServiceRequestData.setServiceRequestNumber(contract
					.getRelatedServiceRequestedNumber());
		}
		sellableServiceRequestWSInput
				.setSellableItemsServiceRequestData(sellableServiceRequestData);
		// End Setting up sellableServiceRequestData
		SellableItemServiceRequestWSInput sellableItemServiceRequestWSInput = new SellableItemServiceRequestWSInput();
		sellableItemServiceRequestWSInput
				.setSellableItemsServiceRequestWSInput(sellableServiceRequestWSInput);
		StringHolder srNumber = new StringHolder();
		StringHolder srRowId = new StringHolder();
		ServiceRequestDetailsOutputHolder serviceRequestDetailsOutPutHolder = new ServiceRequestDetailsOutputHolder();
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("@@@@@@@@@@@@@@ webmethods address is " + getAddress());
			LOGGER.debug("@@@@@@@@@@@@@@ RelatedServiceRequestNumber  [" + contract.getRelatedServiceRequestedNumber() + "]");
			LOGGER.debug("@@@@@@@@@@@@@@ RelatedServiceRequestRowID  [" + contract.getServiceRequest().getId() + "]");
		}
		
		if (contract.getRelatedServiceRequestedNumber() != null
				&& !contract.getRelatedServiceRequestedNumber().isEmpty()) {
			long timeBeforeCall = System.currentTimeMillis();
			port.updateSellableItemsServiceRequest(
					sellableItemServiceRequestWSInput, debug);
			long timeAfterCall=System.currentTimeMillis();
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.CREATEHARDWARE_MSG_UPDATESELLABLEITEMSSERVICEREQUEST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,contract);
			//LOGGER.debug("** MPS PERFORMANCE TESTING UPDATE HARDWARE SR ==>: " + (System.currentTimeMillis() - timeBeforeCall) / 1000.0 + " SR NUMBER IS " + contract.getRelatedServiceRequestedNumber());
			LOGGER.debug(" SR NUMBER IS " + contract.getRelatedServiceRequestedNumber());
			srNumber.value = contract.getRelatedServiceRequestedNumber();
			srRowId.value = contract.getServiceRequest().getId();

		} else {
			long timeBeforeCall = System.currentTimeMillis();
			port.createSellableItemsServiceRequest(
					sellableItemServiceRequestWSInput, synchOrAsynch, debug,
					serviceRequestDetailsOutPutHolder, srNumber, srRowId);
			long timeAfterCall=System.currentTimeMillis();
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.CREATEHARDWARE_MSG_CREATESELLABLEITEMSSERVICEREQUEST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,contract);
			//LEXLOGGER.logTime("** MPS PERFORMANCE TESTING CREATE CONSUMABLE SR ==>: " + (System.currentTimeMillis() - timeBeforeCall) / 1000.0 + " SR NUMBER IS " + srNumber.value);
			LEXLOGGER.logTime(" SR NUMBER IS " + srNumber.value);
		}
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("SR Number CM Req is " + srNumber.value);
			LOGGER.debug("SR Row ID CM Req is " + srRowId.value);
		}

		if (!StringUtil.isStringEmpty(srNumber.value)
				&& !StringUtil.isStringEmpty(srRowId.value)) {
			result.setServiceRequestNumber(srNumber.value);
			result.setServiceRequestRowId(srRowId.value);
		} else {
			throw new LGSBusinessException(
					"Error creating service request - SR Number not received");
		}

		return result;
	}

	private void printValueToBeSentToWM(CreateHardwareRequestContract contract) {
		LOGGER.debug("Printing the contract we arew sending to WM");
		if(LOGGER.isDebugEnabled()){
		LOGGER.debug("Document meta data getSenderId() " + getSenderId()
				+ " getSenderName() " + getSenderName() + " getReceiverId() "
				+ getReceiverId());
		LOGGER.debug("Other one contract.getSrType() " + contract.getSrType()
				+ " contract.getSrArea() " + contract.getSrArea()
				+ " contract.getSrSubArea() " + contract.getSrSubArea()
				+ " contract.getServiceRequest().getAddtnlDescription()"
				+ contract.getServiceRequest().getAddtnlDescription());
		LOGGER.debug("Account details");
		if (contract.getContactId() != null) {
			LOGGER.debug("Contact id is " + contract.getContactId());
		}
		LOGGER.debug("accountId ");
		LOGGER.debug("accountName ");
		LOGGER.debug("contract.getSRStatus() " + contract.getSRStatus());// Should
																			// never
																			// be
																			// null
		LOGGER.debug("contract.getMdmId() " + contract.getMdmId()
				+ " contract.getMdmLevel() " + contract.getMdmLevel());
		LOGGER.debug("Requester");
		LOGGER
				.debug("contract.getServiceRequest().getRequestor().getContactId() "
						+ contract.getServiceRequest().getRequestor()
								.getContactId()
						+ " contract.getServiceRequest().getRequestor().getFirstName() "
						+ contract.getServiceRequest().getRequestor()
								.getFirstName()
						+ " contract.getServiceRequest().getRequestor().getLastName() "
						+ contract.getServiceRequest().getRequestor()
								.getLastName()
						+ " contract.getServiceRequest().getRequestor().getWorkPhone() "
						+ contract.getServiceRequest().getRequestor()
								.getWorkPhone()
						+ " contract.getServiceRequest().getRequestor().getEmailAddress() "
						+ contract.getServiceRequest().getRequestor()
								.getEmailAddress()
						+ " contract.getServiceRequest().getRequestor().getAlternatePhone() "
						+ contract.getServiceRequest().getRequestor()
								.getAlternatePhone()
						+ " contract.getUserType() " + contract.getUserType());
		LOGGER.debug("Primary Contact");
		LOGGER
				.debug("contract.getServiceRequest().getPrimaryContact().getContactId() "
						+ contract.getServiceRequest().getPrimaryContact()
								.getContactId()
						+ " contract.getServiceRequest().getPrimaryContact().getFirstName() "
						+ contract.getServiceRequest().getPrimaryContact()
								.getFirstName()
						+ " contract.getServiceRequest().getPrimaryContact().getLastName() "
						+ contract.getServiceRequest().getPrimaryContact()
								.getLastName()
						+ " contract.getServiceRequest().getPrimaryContact().getWorkPhone() "
						+ contract.getServiceRequest().getPrimaryContact()
								.getWorkPhone()
						+ " contract.getServiceRequest().getPrimaryContact().getAlternatePhone() "
						+ contract.getServiceRequest().getPrimaryContact()
								.getAlternatePhone()
						+ " contract.getServiceRequest().getPrimaryContact().getEmailAddress() "
						+ contract.getServiceRequest().getPrimaryContact()
								.getEmailAddress());
		LOGGER.debug("Secondary Contact");
		LOGGER
				.debug("contract.getServiceRequest().getSecondaryContact().getContactId() "
						+ contract.getServiceRequest().getSecondaryContact()
								.getContactId()
						+ " contract.getServiceRequest().getSecondaryContact().getFirstName() "
						+ contract.getServiceRequest().getSecondaryContact()
								.getFirstName()
						+ " contract.getServiceRequest().getSecondaryContact().getLastName() "
						+ contract.getServiceRequest().getSecondaryContact()
								.getLastName()
						+ " contract.getServiceRequest().getSecondaryContact().getWorkPhone() "
						+ contract.getServiceRequest().getSecondaryContact()
								.getWorkPhone()
						+ " contract.getServiceRequest().getSecondaryContact().getAlternatePhone() "
						+ contract.getServiceRequest().getSecondaryContact()
								.getAlternatePhone()
						+ " contract.getServiceRequest().getSecondaryContact().getEmailAddress() "
						+ contract.getServiceRequest().getSecondaryContact()
								.getEmailAddress());
		LOGGER.debug("Ship to Address");
		LOGGER.debug("contract.getShipToAddress().getAddressId() "
				+ contract.getShipToAddress().getAddressId());
		LOGGER.debug("contract.getShipToAddress().getAddressName() "
				+ contract.getShipToAddress().getAddressName()
				+ " contract.getShipToAddress().getStoreFrontName() "
				+ contract.getShipToAddress().getStoreFrontName()
				+ " contract.getShipToAddress().getAddressLine1() "
				+ contract.getShipToAddress().getAddressLine1()
				+ " contract.getShipToAddress().getAddressLine2() "
				+ contract.getShipToAddress().getAddressLine2()
				+ " contract.getShipToAddress().getAddressLine3() "
				+ contract.getShipToAddress().getAddressLine3()
				+ " contract.getShipToAddress().getAddressLine4() "
				+ contract.getShipToAddress().getAddressLine4()
				+ " contract.getShipToAddress().getCity() "
				+ contract.getShipToAddress().getCity()
				+ " contract.getShipToAddress().getState() "
				+ contract.getShipToAddress().getState()
				+ " contract.getShipToAddress().getProvince() "
				+ contract.getShipToAddress().getProvince()
				+ " contract.getShipToAddress().getCountry() "
				+ contract.getShipToAddress().getCountry()
				+ " contract.getShipToAddress().getPostalCode() "
				+ contract.getShipToAddress().getPostalCode()
				+ " contract.getShipToAddress().getPhysicalLocation1() "
				+ contract.getShipToAddress().getPhysicalLocation1()
				+ " contract.getShipToAddress().getPhysicalLocation2() "
				+ contract.getShipToAddress().getPhysicalLocation2()
				+ " contract.getShipToAddress().getPhysicalLocation3() "
				+ contract.getShipToAddress().getPhysicalLocation3()
				+ " contract.getShipToAddress().getCountryISOCode() "
				+ contract.getShipToAddress().getCountryISOCode()
				+ " contract.getShipToAddress().getCounty() "
				+ contract.getShipToAddress().getCounty()
				+ " contract.getShipToAddress().getDistrict() "
				+ contract.getShipToAddress().getDistrict()
				+ " contract.getShipToAddress().getLatitude()"
				+ contract.getShipToAddress().getLatitude()
				+ " contract.getShipToAddress().getLongitude() "
				+ contract.getShipToAddress().getLongitude()
				+ " contract.getShipToAddress().getOfficeNumber() "
				+ contract.getShipToAddress().getOfficeNumber()
				+ " contract.getShipToAddress().getStateFullName() "
				+ contract.getShipToAddress().getStateFullName()
				+ " contract.getShipToAddress().getRegion() "
				+ contract.getShipToAddress().getRegion()
				+ " contract.getShipToAddress().getStateCode() "
				+ contract.getShipToAddress().getStateCode()
				+ " contract.getShipToAddress().getIsAddressCleansed() "
				+ contract.getShipToAddress().getIsAddressCleansed() +

				" ");

		LOGGER.debug("Bill to Address");
		if (contract.getBillToAddress() != null) {
			LOGGER.debug("contract.getBillToAddress().getAddressId() "
					+ contract.getBillToAddress().getAddressId());
			LOGGER.debug("contract.getBillToAddress().getAddressName() "
					+ contract.getBillToAddress().getAddressName()
					+ " contract.getBillToAddress().getStoreFrontName() "
					+ contract.getBillToAddress().getStoreFrontName()
					+ " contract.getBillToAddress().getAddressLine1() "
					+ contract.getBillToAddress().getAddressLine1()
					+ " contract.getBillToAddress().getAddressLine2() "
					+ contract.getBillToAddress().getAddressLine2()
					+ " contract.getBillToAddress().getAddressLine3() "
					+ contract.getBillToAddress().getAddressLine3()
					+ " contract.getBillToAddress().getAddressLine4() "
					+ contract.getBillToAddress().getAddressLine4()
					+ " contract.getBillToAddress().getCity() "
					+ contract.getBillToAddress().getCity()
					+ " contract.getBillToAddress().getState() "
					+ contract.getBillToAddress().getState()
					+ " contract.getBillToAddress().getProvince() "
					+ contract.getBillToAddress().getProvince()
					+ " contract.getBillToAddress().getCountry() "
					+ contract.getBillToAddress().getCountry()
					+ " contract.getBillToAddress().getPostalCode() "
					+ contract.getBillToAddress().getPostalCode()
					+ " contract.getBillToAddress().getPhysicalLocation1() "
					+ contract.getBillToAddress().getPhysicalLocation1()
					+ " contract.getBillToAddress().getPhysicalLocation2() "
					+ contract.getBillToAddress().getPhysicalLocation2()
					+ " contract.getBillToAddress().getPhysicalLocation3() "
					+ contract.getBillToAddress().getPhysicalLocation3()
					+ " contract.getBillToAddress().getCountryISOCode() "
					+ contract.getBillToAddress().getCountryISOCode()
					+ " contract.getBillToAddress().getCounty() "
					+ contract.getBillToAddress().getCounty()
					+ " contract.getBillToAddress().getDistrict() "
					+ contract.getBillToAddress().getDistrict()
					+ " contract.getBillToAddress().getLatitude()"
					+ contract.getBillToAddress().getLatitude()
					+ " contract.getBillToAddress().getLongitude() "
					+ contract.getBillToAddress().getLongitude()
					+ " contract.getBillToAddress().getOfficeNumber() "
					+ contract.getBillToAddress().getOfficeNumber()
					+ " contract.getBillToAddress().getStateFullName() "
					+ contract.getBillToAddress().getStateFullName()
					+ " contract.getBillToAddress().getRegion() "
					+ contract.getBillToAddress().getRegion()
					+ " contract.getBillToAddress().getStateCode() "
					+ contract.getBillToAddress().getStateCode()
					+ " contract.getBillToAddress().getIsAddressCleansed() "
					+ contract.getBillToAddress().getIsAddressCleansed() +

					" ");
		}
		LOGGER.debug("Product details");
		LOGGER.debug("contract.getPrinterProductNumber() "
				+ contract.getPrinterProductNumber()
				+ " contract.getAssetSerialNumber() "
				+ contract.getAssetSerialNumber());
		LOGGER.debug("PageCountData");

		LOGGER.debug("Catalog order part list check");
		if (contract.getBundleList() != null
				&& contract.getBundleList().size() > 0) {
			for (int i = 0; i < contract.getBundleList().size(); i++) {// DESCRIPTION
																		// FIELD
																		// IS
																		// NOT
																		// THERE

				LOGGER
						.debug(" contract.getBundleList().get(i).getOrderQuantity() "
								+ contract.getBundleList().get(i)
										.getOrderQuantity()
								+ " contract.getBundleList().get(i).getCatalogId() "
								+ contract.getBundleList().get(i)
										.getCatalogId()
								+ " contract.getBundleList().get(i).getUnitPrice "
								+ contract.getBundleList().get(i)
										.getUnitPrice()
								+ " contract.getBundleList().get(i).getTaxAmount "
								+ contract.getBundleList().get(i).getTax()
								+ " contract.getBundleList().get(i).getTotalPrice "
								+ contract.getBundleList().get(i).getTotal()
								+ " contract.getBundleList().get(i).getCurrency "
								+ contract.getBundleList().get(i).getCurrency());
			}
		}

		if (contract.getAccessoriesList() != null
				&& contract.getAccessoriesList().size() > 0) {
			for (int i = 0; i < contract.getAccessoriesList().size(); i++) {// DESCRIPTION
																			// FIELD
																			// IS
																			// NOT
																			// THERE

				LOGGER
						.debug("contract.getAccessoriesList().get(i).getPartNumber() "
								+ contract.getAccessoriesList().get(i)
										.getPartNumber()
								+ " contract.getAccessoriesList().get(i).getPartType() "
								+ contract.getAccessoriesList().get(i)
										.getPartType()
								+ " contract.getAccessoriesList().get(i).getOrderQuantity() "
								+ contract.getAccessoriesList().get(i)
										.getOrderQuantity()
								+ " contract.getAccessoriesList().get(i).getCatalogId() "
								+ contract.getAccessoriesList().get(i)
										.getCatalogId()
								+ " contract.getAccessoriesList().get(i).getUnitPrice "
								+ contract.getAccessoriesList().get(i)
										.getUnitPrice()
								+ " contract.getAccessoriesList().get(i).getTaxAmount "
								+ contract.getAccessoriesList().get(i).getTax()
								+ " contract.getAccessoriesList().get(i).getTotalPrice "
								+ contract.getAccessoriesList().get(i)
										.getTotal()
								+ " contract.getAccessoriesList().get(i).getCurrency "
								+ contract.getAccessoriesList().get(i)
										.getCurrency());
			}
		}
		LOGGER.debug("Service request");
		LOGGER
				.debug("contract.getServiceRequest().getCustomerReferenceNumber() "
						+ contract.getServiceRequest()
								.getCustomerReferenceNumber()
						+ " contract.getServiceRequest().getCostCenter() "
						+ contract.getServiceRequest().getCostCenter()
						+ " consumablesServiceRequestData.setServiceRequestComments "
						+ contract.getServiceRequest().getAddtnlDescription()
						+ " contract.getAttachmentNotes() "
						+ contract.getAttachmentNotes());
		LOGGER.debug("Order Information");
		LOGGER.debug(" contract.getSpecialInstruction() "
				+ contract.getSpecialInstruction());
		LOGGER.debug("Payment Information");
		LOGGER.debug("contract.getPoNumber() " + contract.getPoNumber());
		LOGGER.debug("contract.getCreditCardToken() "
				+ contract.getCreditCardToken());
		}
	}

}
