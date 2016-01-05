package com.lexmark.services.webService;

//Added for MPS 2.1 for Debugging purpose
import static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_INPUT;
import static com.lexmark.services.util.ChangeMgmtConstant.ADDRESSCLEANSEFIELDS_OUTPUT;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.xml.rpc.holders.StringHolder;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.SiebelShared.createServiceRequest.client.AssetInformation2;
import com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestData;
import com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestWSInput;
import com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestWSInput2;
import com.lexmark.SiebelShared.createServiceRequest.client.CreditCardInformation;
import com.lexmark.SiebelShared.createServiceRequest.client.MaterialDetails;
import com.lexmark.SiebelShared.createServiceRequest.client.OrderInformation;
import com.lexmark.SiebelShared.createServiceRequest.client.PageCountData;
import com.lexmark.SiebelShared.createServiceRequest.client.PaymentDetails;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSLocator;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWS_PortType;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelAccount;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact;
import com.lexmark.SiebelShared.createServiceRequest.client.SiebelPaymentDetails;
import com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData;
import com.lexmark.SiebelShared.createServiceRequest.client.holders.ServiceRequestDetailsOutputHolder;
import com.lexmark.contract.CreateConsumableServiceRequestContract;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.Part;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.services.api.CreateConsumableRequest;
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
public class CreateConsumableRequestImpl implements CreateConsumableRequest {
	private static final Logger LOGGER = LogManager.getLogger(CreateConsumableRequestImpl.class);
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging
	private static final LEXLogger LEXLOGGER = LEXLogger.getLEXLogger(CreateConsumableRequestImpl.class);
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

	public CreateServiceRequestResult createConsumableServiceRequest(
			CreateConsumableServiceRequestContract contract,
			Map<String, String> accountDetails, boolean isVendorFlag)
			throws Exception {

		CreateServiceRequestResult result = new CreateServiceRequestResult();
		LOGGER.debug("Beginning Service call for CreateService Request for Consumable Order");
		printValueToBeSentToWM(contract);
		Date dateNow = new Date();
		SimpleDateFormat dateformatMMDDYYYY = new SimpleDateFormat("MMddyyyy");

		String srStatus = ChangeMgmtConstant.OM_SR_STATUS_OPEN;// Added for MPS
																// 2.1 changes

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
		
		//LBS
		String sourceSystem=null;
		if(contract.getFleetManagementFlag()!=null && contract.getFleetManagementFlag().equalsIgnoreCase("true")){
		LOGGER.debug("Setting Map-Web");
			sourceSystem = "Map-LBS Internal";
		}
		else{
			LOGGER.debug("Setting Web");
		sourceSystem = "LBS Internal";
		}
		
		ConsumablesServiceRequestWSInput2 consumablesServiceRequestWSInput2 = new ConsumablesServiceRequestWSInput2();
		// Setting up DocumentMetaData
		WebServiceDocumentMetaData documentMetaData = new WebServiceDocumentMetaData();
		documentMetaData.setSenderId(getSenderId());
		documentMetaData.setSenderName(getSenderName());
		documentMetaData.setReceiverId(getReceiverId());

		LOGGER.debug("getSenderId:: " + getSenderId() + " getReceiverId:: "
				+ getReceiverId());
		consumablesServiceRequestWSInput2.setDocumentMetaData(documentMetaData);
		// End Setting up DocumentMetaData

		// Setting up ConsumablesServiceRequestData
		ConsumablesServiceRequestData consumablesServiceRequestData = new ConsumablesServiceRequestData();
		consumablesServiceRequestData.setServiceRequestType(contract
				.getSrType());
		consumablesServiceRequestData.setServiceRequestSource(sourceSystem);
		if (contract.getSRStatus() != null) {
			LOGGER.debug("service Request status is " + contract.getSRStatus());
			consumablesServiceRequestData.setServiceRequestStatus(contract
					.getSRStatus());
			if (contract.getSRStatus().equalsIgnoreCase(
					ChangeMgmtConstant.OM_SR_STATUS_DRAFT)) {
				srStatus = ChangeMgmtConstant.OM_SR_STATUS_DRAFT;
			}

		}
		consumablesServiceRequestData.setRequestedService(contract.getSrArea());
		consumablesServiceRequestData.setRequestedServiceAction(contract
				.getSrSubArea());
		consumablesServiceRequestData.setServiceRequestDate(DateUtil
				.convertDateToGMTString(new Date()));// Non nillable
		consumablesServiceRequestData.setServiceRequestDescription(contract
				.getServiceRequest().getAddtnlDescription());// Non nillable
		consumablesServiceRequestData.setBillingModel(contract
				.getBillingModel());
		if (accountDetails.get("contractNumber") != null) {
			LOGGER.debug("contractNumber is " + accountDetails.get("contractNumber"));
			consumablesServiceRequestData
					.setRelatedContractNumber(accountDetails
							.get("contractNumber"));
		}
		if (contract.getContractNumber() != null) {
			LOGGER.debug("contractNumber from contract is " + contract.getContractNumber());
			consumablesServiceRequestData.setRelatedContractNumber(contract.getContractNumber());
		}
		if (accountDetails.get("agreementId") != null) {
			LOGGER.debug("agreementId is " + accountDetails.get("agreementId"));
			consumablesServiceRequestData
					.setRelatedAgreementNumber(accountDetails
							.get("agreementId"));
		}

		SiebelAccount accountInformation = new SiebelAccount();
		
		if (accountDetails != null) {
			LOGGER.debug("Account id :::: " + accountDetails.get("accountId")+ "Account Name :::: " + accountDetails.get("accountName") + "Account Organization :::: " + accountDetails.get("accountOrganization")	+ "Agreement Id :::: " + accountDetails.get("agreementId") + "Agreement Name :::: "	+ accountDetails.get("agreementName"));
			accountInformation.setAccountId(accountDetails.get("accountId"));
		}
		consumablesServiceRequestData.setAccountInformation(accountInformation);
		

		if (contract.getSoldToNumber() != null
				&& !contract.getSoldToNumber().equals("")) {
			accountInformation.setSoldToNumber(contract.getSoldToNumber());
		}
		if (contract.getBillToNumber() != null
				&& !contract.getBillToNumber().equals("")) {
			accountInformation.setBillToNumber(contract.getBillToNumber());
		}

		// Added for source by MPS offshore
		if (isVendorFlag) {
			consumablesServiceRequestData.setVendorId(contract.getVendorName());
			if (accountDetails != null) {
				accountInformation
						.setAccountId(accountDetails.get("accountId"));
			}
			consumablesServiceRequestData
					.setAccountInformation(accountInformation);
		}
		// End

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
			consumablesServiceRequestData.setRequester(requester);
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
			consumablesServiceRequestData.setPrimaryContact(primaryContact);
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
			consumablesServiceRequestData.setSecondaryContact(secondaryContact);
		}
		// Changes for JAN release
		// ShipToAddress
		SiebelAddress shipToAddress = new SiebelAddress();
		if (!(contract.getShipToAddress().getAddressId().equalsIgnoreCase("-1"))) {
			LOGGER
					.debug("This is not a new addres hence we are not sending any address id");
			shipToAddress.setAddressId(contract.getShipToAddress()
					.getAddressId());
		}

		// Changes made for MPS 2.1 Address cleansing
		shipToAddress.setAddressName(contract.getShipToAddress()
				.getStoreFrontName() != null ? contract.getShipToAddress()
				.getStoreFrontName().trim() : "");// Added by sankha for
													// LEX:AIR00071646
		shipToAddress.setAddressLine1(contract.getShipToAddress()
				.getAddressLine1() != null ? contract.getShipToAddress()
				.getAddressLine1().trim() : "");// Added by sankha for
												// LEX:AIR00071646
		shipToAddress.setAddressLine2(contract.getShipToAddress()
				.getAddressLine2() != null ? contract.getShipToAddress()
				.getAddressLine2().trim() : "");// Added by sankha for
												// LEX:AIR00071646
		shipToAddress
				.setCity(contract.getShipToAddress().getCity() != null ? contract
						.getShipToAddress().getCity().trim()
						: "");// Added by sankha for LEX:AIR00071646
		shipToAddress.setCountry(contract.getShipToAddress().getCountry());
		shipToAddress
				.setPostalCode(contract.getShipToAddress().getPostalCode() != null ? contract
						.getShipToAddress().getPostalCode().trim()
						: "");// Added by sankha for LEX:AIR00071646
		shipToAddress.setPhysicalLocation1(contract.getShipToAddress()
				.getPhysicalLocation1() != null ? contract.getShipToAddress()
				.getPhysicalLocation1().trim() : "");// Added by sankha for
														// LEX:AIR00071646
		shipToAddress.setPhysicalLocation2(contract.getShipToAddress()
				.getPhysicalLocation2() != null ? contract.getShipToAddress()
				.getPhysicalLocation2().trim() : "");// Added by sankha for
														// LEX:AIR00071646
		shipToAddress.setPhysicalLocation3(contract.getShipToAddress()
				.getPhysicalLocation3() != null ? contract.getShipToAddress()
				.getPhysicalLocation3().trim() : "");// Added by sankha for
														// LEX:AIR00071646
		// Added for cleansed fields
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
				.getErrorMsgForCleansing());// Address Cleansing Flag
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
		LOGGER.debug("address cleansing flag:: "
				+ contract.getShipToAddress().getIsAddressCleansed());
		// Ends

		consumablesServiceRequestData.setShipToAddress(shipToAddress);

		// Changes for JAN release Ends

		AssetInformation2 assetInformation = new AssetInformation2();
		assetInformation.setAssetId(contract.getAssetId());
		assetInformation.setProductNumber(contract.getPrinterProductNumber());
		assetInformation.setSerialNumber(contract.getAssetSerialNumber());// To
																			// be
																			// sent
																			// only
																			// for
																			// asset
																			// based

		// PageCountData
		if (contract.getPageCountList() != null) {
			int  setArrayCount=0;
			for(int i=0;i<contract.getPageCountList().size();i++) {
				
				if((contract.getPageCountList().get(i).getDate() !=null && !"".equals(contract.getPageCountList().get(i).getDate().trim()))&& (contract.getPageCountList().get(i).getCount()!=null && !"".equals(contract.getPageCountList().get(i).getCount().trim()))){
					setArrayCount++;
				}
			}
			int  count=0; 
			PageCountData[] pageCountDataArray = new PageCountData[setArrayCount];
			for (int i = 0; i < contract.getPageCountList().size(); i++) {
				PageCountData pageCountData = new PageCountData();
				if((contract.getPageCountList().get(i).getDate() !=null && !"".equals(contract.getPageCountList().get(i).getDate().trim()))&& (contract.getPageCountList().get(i).getCount()!=null && !"".equals(contract.getPageCountList().get(i).getCount().trim()))){
				
				pageCountData.setPageCount(contract.getPageCountList().get(i).getCount());
				pageCountData.setPageCountType(contract.getPageCountList().get(i).getName());
				pageCountData.setReadingDateTime(contract.getPageCountList().get(i).getDate());
				pageCountDataArray[count] = pageCountData;
				count++;
			}
			}
			assetInformation.setPageCountData(pageCountDataArray);

		}
		if ("assetbased".equalsIgnoreCase(contract.getConsumableOrderType())) {
			consumablesServiceRequestData.setAssetInformation(assetInformation);
		}

		// MaterialDetails
		if ("assetbased".equalsIgnoreCase(contract.getConsumableOrderType())) {
			// Changes for JAN release
			if (contract.getInstallationOnlyFlag() != null
					&& contract.getInstallationOnlyFlag().equalsIgnoreCase("N")) {
				// If user doesnot choose installation only flag
				int materialArraySize = contract.getAssetPartList().size();

				MaterialDetails[] materialDetailsArray = new MaterialDetails[materialArraySize];
				for (int i = 0; i < contract.getAssetPartList().size(); i++) {// DESCRIPTION
																				// FIELD
																				// IS
																				// NOT
																				// THERE
					MaterialDetails materialDetails = new MaterialDetails();
					Part part = contract.getAssetPartList().get(i);

					materialDetails.setMaterialType(part.getPartType());// part
																		// type
					materialDetails.setQuantity(part.getOrderQuantity());
					materialDetails.setCatalogId(part.getCatalogId());
					
					if (part.getProviderOrderNumber() != null
							&& !part.getProviderOrderNumber().equals("")) {
						LOGGER.debug("provider order number for this material "+part.getProviderOrderNumber());
						materialDetails.setServiceProviderReferenceNumber(part.getProviderOrderNumber());
					}

					LOGGER.debug("SupplyId 1 --->"
							+ contract.getAssetPartList().get(i).getSupplyId());
					materialDetails.setMaterialId(contract.getAssetPartList()
							.get(i).getSupplyId());// comment out after wsdl
													// change
					materialDetails.setMaterialNumber(contract
							.getAssetPartList().get(i).getPartNumber());// part
																		// number

					LOGGER.debug("ProductId 1 --->" + part.getProductId());
					materialDetails.setProductId(part.getProductId());// comment
																		// out
																		// after
																		// wsdl
																		// change

					// MPS 2.1 changes new fields
					materialDetails.setUnitPrice(part.getUnitPrice());
					materialDetails.setTaxAmount(part.getTaxAmount());
					materialDetails.setTotalLinePrice(part.getTotalPrice());
					materialDetails.setCurrencyCode(part.getCurrency());

					materialDetailsArray[i] = materialDetails;
				}
				consumablesServiceRequestData
						.setMaterialDetails(materialDetailsArray);
			} else {
				// Need to send {Parts detail information textarea values)
				consumablesServiceRequestData.setInstallationOnlyFlag(contract
						.getInstallationOnlyFlag());
				consumablesServiceRequestData
						.setServiceRequestDescription(consumablesServiceRequestData
								.getServiceRequestDescription()
								+ " "
								+ contract.getPartsToBeInstalledDescription());
			}
			// End of Jan release changes
		} else {
			int materialArraySize = contract.getCatalogPartList().size();
			LOGGER.debug("materialArraySize --->" + materialArraySize);
			MaterialDetails[] materialDetailsArray = new MaterialDetails[materialArraySize];
			for (int i = 0; i < contract.getCatalogPartList().size(); i++) {// DESCRIPTION
																			// FIELD
																			// IS
																			// NOT
																			// THERE
				MaterialDetails materialDetails = new MaterialDetails();
				OrderPart orderPart = contract.getCatalogPartList().get(i);

				materialDetails.setMaterialType(orderPart.getPartType());// part
																			// type
				materialDetails.setQuantity(orderPart.getOrderQuantity());
				materialDetails.setCatalogId(orderPart.getCatalogId());
				materialDetails.setMaterialId(orderPart.getSupplyId());
				LOGGER.debug("SupplyId 2 --->" + orderPart.getSupplyId());
				materialDetails.setMaterialNumber(orderPart.getPartNumber());// part
																				// number
				materialDetails.setProductId(orderPart.getProductId());
				LOGGER.debug("ProductId 2 --->" + orderPart.getProductId());

				// MPS 2.1 changes new fields
				materialDetails.setUnitPrice(orderPart.getUnitPrice());
				materialDetails.setTaxAmount(orderPart.getTax());// Tax Value
				materialDetails.setTotalLinePrice(orderPart.getTotal());
				materialDetails.setCurrencyCode(orderPart.getCurrency());
				if (orderPart.getProviderOrderNumber() != null
						&& !orderPart.getProviderOrderNumber().equals("")) {
					LOGGER.debug("provider order number for this material "+orderPart.getProviderOrderNumber());
					materialDetails.setServiceProviderReferenceNumber(orderPart.getProviderOrderNumber());
				}
				if (orderPart.getContractLineItemId() != null
						&& !orderPart.getContractLineItemId().equals("")) {
					materialDetails.setRelatedContractLineNumber(orderPart
							.getContractLineItemId());
					materialDetails.setHardwareConfigurationID(orderPart
							.getContractLineItemId());
				}

				materialDetailsArray[i] = materialDetails;
			}
			consumablesServiceRequestData
					.setMaterialDetails(materialDetailsArray);
		}
		// MaterialDetails done

		consumablesServiceRequestData.setCustomerReferenceNumber(contract
				.getServiceRequest().getCustomerReferenceId());
		consumablesServiceRequestData.setCostCenter(contract
				.getServiceRequest().getCostCenter());
		consumablesServiceRequestData.setServiceRequestComments(contract
				.getServiceRequest().getAddtnlDescription());
		consumablesServiceRequestData.setAttachmentNotes(contract
				.getAttachmentNotes());

		// OrderInformation
		OrderInformation orderInformation = new OrderInformation();
		orderInformation.setExpediteOrderFlag(contract.getExpediteOrder()
				.toString());
		orderInformation.setRequestedDeliveryDate(contract
				.getRequestedDeliveryDate());
		orderInformation.setSpecialHandlingInstructions(contract
				.getSpecialInstruction());
		consumablesServiceRequestData.setOrderInformation(orderInformation);
		if(srStatus.equalsIgnoreCase(ChangeMgmtConstant.OM_SR_STATUS_DRAFT)){
			PaymentDetails paymentInformation = new PaymentDetails();
			paymentInformation.setPurchaseOrderNumber(contract.getPoNumber());
			if (contract.getPaymentMethod() != null) {
				paymentInformation.setPaymentMethod(contract.getPaymentMethod());
			}
			SiebelPaymentDetails siebelPaymentDetails = new SiebelPaymentDetails();
			siebelPaymentDetails.setPaymentDetails(paymentInformation);
			consumablesServiceRequestData.setPaymentInformation(siebelPaymentDetails);
		}
		// PaymentInformation
		if (srStatus.equalsIgnoreCase(ChangeMgmtConstant.OM_SR_STATUS_OPEN)) {// MPS
																				// 2.1
																				// Changes
			LOGGER.debug("Entering Payment Informations SR status is --->" + srStatus);
			PaymentDetails paymentInformation = new PaymentDetails();
			paymentInformation.setPurchaseOrderNumber(contract.getPoNumber());
			if (contract.getPaymentMethod() != null) {
				paymentInformation.setPaymentMethod(contract.getPaymentMethod());
			}
			// MPS 2.1 changes new fields added
			LOGGER.debug("Bill To address--->" + contract.getBillToAddress());
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
						.getErrorMsgForCleansing());// Flag for cleansing to be
													// passed to WM
				
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
			// Ends

			if (contract.getCreditCardToken() != null
					&& contract.getCreditCardToken() != "") {
				CreditCardInformation creditCardInfo = new CreditCardInformation();
				creditCardInfo.setCreditCardAuthorizationNumber(contract
						.getCreditCardToken());
				creditCardInfo
						.setCreditCardToken(contract.getCreditCardToken());
				creditCardInfo.setCreditCardExpirationDate(contract
						.getCreditCardExpirationDate());
				creditCardInfo.setCreditCardNumber(contract
						.getCreditCardEncryptedNo());
				creditCardInfo.setCreditCardType(contract.getCreditCardType());
				creditCardInfo.setCreditCardHolderName(contract
						.getCardHolderName());
				creditCardInfo.setCreditCardTransactionId(contract
						.getTransactionId());
				paymentInformation.setCreditCardInformation(creditCardInfo);
			}
			SiebelPaymentDetails siebelPaymentDetails = new SiebelPaymentDetails();
			siebelPaymentDetails.setPaymentDetails(paymentInformation);

			consumablesServiceRequestData
					.setPaymentInformation(siebelPaymentDetails);
		}

		if (contract.getRelatedServiceRequestedNumber() != null
				&& !contract.getRelatedServiceRequestedNumber().isEmpty()) {
			LOGGER.debug("Updating SR NUMBER : " + contract.getRelatedServiceRequestedNumber());
			consumablesServiceRequestData.setServiceRequestNumber(contract
					.getRelatedServiceRequestedNumber());
		}
		consumablesServiceRequestWSInput2
				.setConsumablesServiceRequestData(consumablesServiceRequestData);
		// End Setting up ConsumablesServiceRequestData
		ConsumablesServiceRequestWSInput consumablesServiceRequestWSInput = new ConsumablesServiceRequestWSInput();
		consumablesServiceRequestWSInput
				.setConsumablesServiceRequestWSInput(consumablesServiceRequestWSInput2);
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
			port.updateConsumablesServiceRequest(
					consumablesServiceRequestWSInput, debug);
			long timeAfterCall=System.currentTimeMillis();
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.CREATECONSUMABLES_MSG_UPDATECONSUMABLESSERVICEREQUEST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,contract);
			//LOGGER.debug("** MPS PERFORMANCE TESTING UPDATE CONSUMABLE SR ==>: " + (System.currentTimeMillis() - timeBeforeCall) / 1000.0 + " SR NUMBER IS " + contract.getRelatedServiceRequestedNumber());
			LOGGER.debug(" SR NUMBER IS " + contract.getRelatedServiceRequestedNumber());
			srNumber.value = contract.getRelatedServiceRequestedNumber();
			srRowId.value = contract.getServiceRequest().getId();

		} else {
			long timeBeforeCall = System.currentTimeMillis();
			port.createConsumablesServiceRequest(debug,
					consumablesServiceRequestWSInput, synchOrAsynch,
					serviceRequestDetailsOutPutHolder, srNumber, srRowId);
			long timeAfterCall=System.currentTimeMillis();
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.CREATECONSUMABLES_MSG_CREATECONSUMABLESSERVICEREQUEST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,contract);
			//LEXLOGGER.logTime("** MPS PERFORMANCE TESTING CREATE CONSUMABLE SR ==>: " + (System.currentTimeMillis() - timeBeforeCall) / 1000.0 + " SR NUMBER IS " + srNumber.value);
			LEXLOGGER.logTime(" SR NUMBER IS " + srNumber.value);
		}

		LOGGER.debug("SR Number CM Req is " + srNumber.value);
		LOGGER.debug("SR Row ID CM Req is " + srRowId.value);

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

	private void printValueToBeSentToWM(CreateConsumableServiceRequestContract contract) {
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Printing the contract we arew sending to WM");
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
			LOGGER.debug("contract.getServiceRequest().getRequestor().getContactId() "
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
			LOGGER.debug("contract.getServiceRequest().getPrimaryContact().getContactId() "
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
			LOGGER.debug("contract.getServiceRequest().getSecondaryContact().getContactId() "
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
			if ("assetbased".equalsIgnoreCase(contract.getConsumableOrderType())) {
				if (contract.getPageCountList() != null) {
					for (int i = 0; i < contract.getPageCountList().size(); i++) {
						LOGGER.debug("contract.getPageCountList().get(i).getCount() "
										+ contract.getPageCountList().get(i)
												.getCount()
										+ " contract.getPageCountList().get(i).getSiebelName() "
										+ contract.getPageCountList().get(i)
												.getSiebelName()
										+ " contract.getPageCountList().get(i).getDate() "
										+ contract.getPageCountList().get(i)
												.getDate());
					}
				}

				LOGGER.debug("");
				// MaterialDetails
				for (int i = 0; i < contract.getAssetPartList().size(); i++) {// DESCRIPTION
																				// FIELD
																				// IS
																				// NOT
																				// THERE
					
					LOGGER.debug("contract.getAssetPartList().get(i).getPartNumber() "
									+ contract.getAssetPartList().get(i)
											.getPartNumber()
									+ " contract.getAssetPartList().get(i).getPartType() "
									+ contract.getAssetPartList().get(i)
											.getPartType()
									+ " contract.getAssetPartList().get(i).getOrderQuantity() "
									+ contract.getAssetPartList().get(i)
											.getOrderQuantity()
									+ " contract.getAssetPartList().get(i).getCatalogId() "
									+ contract.getAssetPartList().get(i)
											.getCatalogId()
									+ " contract.getAssetPartList().get(i).getSupplyId() "
									+ contract.getAssetPartList().get(i)
											.getSupplyId()
									+ " contract.getAssetPartList().get(i).getProductId() "
									+ contract.getAssetPartList().get(i)
											.getProductId()
									+
									" contract.getAssetPartList().get(i).getUnitPrice "
									+ contract.getAssetPartList().get(i)
											.getUnitPrice()
									+ " contract.getAssetPartList().get(i).getTaxAmount "
									+ contract.getAssetPartList().get(i)
											.getTaxAmount()
									+ " contract.getAssetPartList().get(i).getTotalPrice "
									+ contract.getAssetPartList().get(i)
											.getTotalPrice()
									+ " contract.getAssetPartList().get(i).getCurrency "
									+ contract.getAssetPartList().get(i)
											.getCurrency());
				}
			} else {
				LOGGER.debug("Catalog order part list check");
				for (int i = 0; i < contract.getCatalogPartList().size(); i++) {// DESCRIPTION
																				// FIELD
																				// IS
																				// NOT
																				// THERE

					LOGGER
							.debug("contract.getCatalogPartList().get(i).getPartNumber() "
									+ contract.getCatalogPartList().get(i)
											.getPartNumber()
									+ " contract.getCatalogPartList().get(i).getPartType() "
									+ contract.getCatalogPartList().get(i)
											.getPartType()
									+ " contract.getCatalogPartList().get(i).getOrderQuantity() "
									+ contract.getCatalogPartList().get(i)
											.getOrderQuantity()
									+ " contract.getCatalogPartList().get(i).getCatalogId() "
									+ contract.getCatalogPartList().get(i)
											.getCatalogId()
									+ " contract.getAssetPartList().get(i).getSupplyId() "
									+ contract.getCatalogPartList().get(i)
											.getSupplyId()
									+ " contract.getAssetPartList().get(i).getProductId() "
									+ contract.getCatalogPartList().get(i)
											.getProductId()
									+
									" contract.getAssetPartList().get(i).getUnitPrice "
									+ contract.getCatalogPartList().get(i)
											.getUnitPrice()
									+ " contract.getAssetPartList().get(i).getTaxAmount "
									+ contract.getCatalogPartList().get(i).getTax()
									+ " contract.getAssetPartList().get(i).getTotalPrice "
									+ contract.getCatalogPartList().get(i)
											.getTotal()
									+ " contract.getAssetPartList().get(i).getCurrency "
									+ contract.getCatalogPartList().get(i)
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
			LOGGER.debug("contract.getExpediteOrder().toString() "
					+ contract.getExpediteOrder().toString()
					+ " contract.getRequestedDeliveryDate() "
					+ contract.getRequestedDeliveryDate()
					+ " contract.getSpecialInstruction() "
					+ contract.getSpecialInstruction());
			LOGGER.debug("Payment Information");
			LOGGER.debug("contract.getPoNumber() " + contract.getPoNumber());
			LOGGER.debug("contract.getCreditCardToken() "
					+ contract.getCreditCardToken());
		}
		
	}

}
