package com.lexmark.service.impl.mock;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Vector;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.amind.catalog.service.CatalogSearchManager;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.AccountPayable;
import com.lexmark.domain.AccountsPayable;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ActivityNote;
import com.lexmark.domain.AdditionalPaymentRequest;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.BulkUploadStatus;
import com.lexmark.domain.ContactInformation;
import com.lexmark.domain.Debrief;
import com.lexmark.domain.Document;
import com.lexmark.domain.DocumentDefinition;
import com.lexmark.domain.DownloadClaimPart;
import com.lexmark.domain.DownloadRequestPart;
import com.lexmark.domain.EmailNotification;
import com.lexmark.domain.EmailNotificationLocale;
import com.lexmark.domain.Entitlement;
import com.lexmark.domain.EntitlementServiceDetail;
import com.lexmark.domain.EntitlementServiceDetails;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.MeterReadStatus;
import com.lexmark.domain.Notification;
import com.lexmark.domain.NotificationDetail;
import com.lexmark.domain.NotificationLocale;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.PartnerAgreement;
import com.lexmark.domain.Payment;
import com.lexmark.domain.Role;
import com.lexmark.domain.RoleCategory;
import com.lexmark.domain.RoleCategoryLocale;
import com.lexmark.domain.SRDetail;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.domain.SupportedLocale;
import com.lexmark.domain.TechnicianInstruction;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.enums.ActualFailureCodeEnum;
import com.lexmark.enums.AdditionalPaymentTypeEnum;
import com.lexmark.enums.CarrierEnum;
import com.lexmark.enums.DeviceConditionEnum;
import com.lexmark.enums.ErrorCodeOneEnum;
import com.lexmark.enums.ErrorCodeTwoEnum;
import com.lexmark.enums.LineStatusEnum;
import com.lexmark.enums.PartStatusEnum;
import com.lexmark.enums.PaymentStatusEnum;
import com.lexmark.enums.RequestStatusEnum;
import com.lexmark.enums.RequestSubStatusEnum;
import com.lexmark.enums.ResolutionCodeEnum;
import com.lexmark.enums.ServiceRequestTypeEnum;
import com.lexmark.enums.ServiceStatusEnum;
import com.lexmark.enums.ServiceTypeEnum;
import com.lexmark.result.LdapUserDataResult;
import com.lexmark.domain.DownloadClaim;
import com.lexmark.domain.DownloadRequest;

public class PartnerDomainMockDataGenerator {
	private static Logger logger = LogManager.getLogger(PartnerDomainMockDataGenerator.class);

	private static final List<String> NAME_LIBARY = Arrays.asList(
			"Bryant Kobe", "Fernando Torres", "Cristiano Ronaldo",
			"Michelle Richard");
	private static final List<String> PARTNER_FIRST_NAME_LIST = Arrays.asList(
			"Shia", "Megan", "Rachael", "Anthony", "Jon");
	private static final List<String> PARTNER_LAST_NAME_LIST = Arrays.asList(
			"LaBeouf", "Fox", "Taylor", "Anderson", "Voight");
	private static final List<String> CUSTOMER_FIRST_NAME_LIST = Arrays.asList(
			"Sam", "Mikaela", "Maggie", "Glen", "John");
	private static final List<String> CUSTOMER_LAST_NAME_LIST = Arrays.asList(
			"Witwicky", "Banes", "Madsen", "Whitmann", "Keller");
	private static final List<String> ADDRESS_STATUS_LIST = Arrays.asList(
			LexmarkConstants.ADDRESS_STATUS_DO_NOT_SHIP_PARTS,
			LexmarkConstants.ADDRESS_STATUS_SHIP_TO_CUSTOMER,
			LexmarkConstants.ADDRESS_STATUS_SHIP_TO_SERVICE_PARTNER,
			LexmarkConstants.ADDRESS_STATUS_PARTNER_TO_PROVIDE);
	private static String[] PARTNER_AGREEMENT_NAMES = { "Tolt Walmart",
			"Tolt Best Buy", "Walgreens", "Agreement 4" };

	private static final List<AccountContact> accountContacts = new Vector<AccountContact>();
	private static List<Asset> devices = new Vector<Asset>();
	private static List<ServiceRequest> serviceRequests = new Vector<ServiceRequest>();
	private static List<Activity> activities = new Vector<Activity>();

	// DownloadClaim and DownloadRequest
	private static List<DownloadClaim> downloadClaim = new Vector<DownloadClaim>();
	private static List<DownloadRequest> downloadRequest = new Vector<DownloadRequest>();
	//
	private static List<ListOfValues> actualFailureCodes = new Vector<ListOfValues>();
	private static List<Account> customerAccountList = new ArrayList<Account>();
	private static List<Account> partnerAccountList = new ArrayList<Account>();

	private static List<PartnerAgreement> partnerAgreements = new Vector<PartnerAgreement>();

	private static List<SupportedLocale> supportedLocales;
	private static ListOfValues activityStatusOpen = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS
					.getValue(), RequestStatusEnum.OPEN.getValue());
	private static ListOfValues activityStatusCompleted = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS
					.getValue(), RequestStatusEnum.COMPLETED.getValue());
	private static ListOfValues activityStatusClaimSubmitted = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS
					.getValue(), RequestStatusEnum.CLAIM_SUBMITTED.getValue());
	//Added by MPS Offshore team for New Claim Status creation for accept and update Service Orders
	private static ListOfValues activityStatusAccepted = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS
					.getValue(), RequestStatusEnum.ACCEPTED.getValue());
	private static ListOfValues activityStatusInProgress = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS
					.getValue(), RequestStatusEnum.IN_PROGRESS.getValue());
	private static ListOfValues activityStatusDispatchedToSP = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS
					.getValue(), RequestStatusEnum.DISPATCHED_TO_SP.getValue());

	private static ListOfValues partLineItemPartDispositionUsed = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_PART_STATUS.getValue(),
			PartStatusEnum.USED.getValue());
	private static ListOfValues partLineItemPartDispositionDOA = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_PART_STATUS.getValue(),
			PartStatusEnum.DOA.getValue());
	private static ListOfValues partLineItemPartDispositionNotUsed = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_PART_STATUS.getValue(),
			PartStatusEnum.NOT_USED.getValue());

	private static ListOfValues partLineItemLineStatusInProcess = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_ORDER_LINE_ITEM_STATUS
					.getValue(), LineStatusEnum.IN_PROCESS.getValue());
	private static ListOfValues partLineItemLineStatusReturned = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_ORDER_LINE_ITEM_STATUS
					.getValue(), LineStatusEnum.RETURNED.getValue());
	private static ListOfValues partLineItemLineStatusShipped = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_ORDER_LINE_ITEM_STATUS
					.getValue(), LineStatusEnum.SHIPPED.getValue());

	private static ListOfValues additionalPaymentTypeOne = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_ADDITIONAL_PAYMENT_TYPE
					.getValue(),
			AdditionalPaymentTypeEnum.ADDITIONAL_PAYMENT_TYPE_ONE.getValue());
	private static ListOfValues additionalPaymentTypeTwo = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_ADDITIONAL_PAYMENT_TYPE
					.getValue(),
			AdditionalPaymentTypeEnum.ADDITIONAL_PAYMENT_TYPE_TWO.getValue());

	private static ListOfValues partLineItemPartCarrierFEDEX = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue(),
			CarrierEnum.FEDEX.getValue());
	private static ListOfValues partLineItemPartCarrierUPS = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue(),
			CarrierEnum.UPS.getValue());
	private static ListOfValues partLineItemPartCarrierDHL = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue(),
			CarrierEnum.DHL.getValue());
	private static ListOfValues partLineItemPartCarrierYELLOW_FREIGHT = createListOfValues(
			SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue(),
			CarrierEnum.YELLOW_FREIGHT.getValue());

	private static int INDEX_LOCALE_US = 0;
	private static int INDEX_LOCALE_ITALY = 1;
	private static int INDEX_LOCALE_FRANCE = 2;
	private static int INDEX_LOCALE_GERMAN = 3;
	private static int INDEX_LOCALE_CHINESE = 4;
	private static final String USER_SEGMENT_EMPLOYEE = "employee";
	private static final String USER_SEGMENT_PARTNER = "partner";
	private static final String CONTACT_LANGUAGE = "en";
	private static final String CONTACT_COUNTRY = "America";

	public static final String PARTNER_ONE_CONTACT_ID = "1-687WSZZ";
	private static final String PARTNER_ONE_MDM_ID = "LE6789";
	private static final String PARTNER_ONE_USER_NUMBER = "1-QZ27F0";
	private static final String PARTNER_ONE_WORK_PHONE = "13800000001";
	public static final String PARTNER_ONE_EMAIL_ADDRESS = "partner1@lexmark.com";

	public static final String PARTNER_TWO_CONTACT_ID = "1002";
	private static final String PARTNER_TWO_MDM_ID = "L1-L4BTZC";
	private static final String PARTNER_TWO_USER_NUMBER = "BP12346";
	private static final String PARTNER_TWO_WORK_PHONE = "13800000002";
	public static final String PARTNER_TWO_EMAIL_ADDRESS = "partner2@lexmark.com";

	public static final String PARTNER_THREE_CONTACT_ID = "1003";
	private static final String PARTNER_THREE_MDM_ID = "L1-1DTJNV";
	private static final String PARTNER_THREE_USER_NUMBER = "BP12347";
	private static final String PARTNER_THREE_WORK_PHONE = "13800000003";
	public static final String PARTNER_THREE_EMAIL_ADDRESS = "partner3@lexmark.com";

	public static final String PARTNER_FOUR_CONTACT_ID = "1004";
	private static final String PARTNER_FOUR_MDM_ID = "L2-QTJJWV";
	private static final String PARTNER_FOUR_USER_NUMBER = "BP23456";
	private static final String PARTNER_FOUR_WORK_PHONE = "13800000004";
	public static final String PARTNER_FOUR_EMAIL_ADDRESS = "partner4@lexmark.com";

	private static final String PARTNER_ACCOUNT_ONE_ACCOUNT_ID = "3001";
	private static final String PARTNER_ACCOUNT_TWO_ACCOUNT_ID = "3002";
	private static final String PARTNER_ACCOUNT_THREE_ACCOUNT_ID = "3003";
	private static final String PARTNER_ACCOUNT_FOUR_ACCOUNT_ID = "3004";
	private static final String PARTNER_ACCOUNT_FIVE_ACCOUNT_ID = "3005";

	static {
		populateLOVs();

		accountContacts.add(createAccountContactOne());
		accountContacts.add(createAccountContactTwo());
		accountContacts.add(createAccountContactThree());

		for (int i = 0; i < 5; i++) {
			customerAccountList.add(getCustomerAccount(i));
		}

		partnerAccountList.add(createPartnerAccountOne());
		partnerAccountList.add(createPartnerAccountTwo());
		partnerAccountList.add(createPartnerAccountThree());
		partnerAccountList.add(createPartnerAccountFour());
		partnerAccountList.add(createPartnerAccountFive());

		for (ActualFailureCodeEnum currentEnum : ActualFailureCodeEnum.values()) {
			ListOfValues lov = new ListOfValues();
			lov.setType(SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE
					.getValue());
			lov.setValue(currentEnum.getValue());
			actualFailureCodes.add(lov);
		}

		// populate assets that have associated service request
		for (int i = 0; i < 300; i++) {
			devices.add((Asset) getDevice(i));
		}

		// populate assets that have no associated service request
		for (int i = 300; i < 310; i++) {
			devices.add((Asset) getDevice(i));
		}

		// change the serial number to "1010004", so that there're two assets
		// with same serial number.
		devices.get(299).setSerialNumber("1010004");

		// populate service requests
		for (int i = 0; i < 600; i++) {
			serviceRequests.add((ServiceRequest) getServiceRequest(i));
		}

		// populate activities
		for (int i = 0; i < 600; i++) {
			activities.add((Activity) getActivity(i));
		}

		// populate downloadClaim and downloadRequest
		for (int i = 0; i < 10; i++) {
			downloadClaim.add((DownloadClaim) getDownloadClaims(i));
		}

		for (int i = 0; i < 10; i++) {
			downloadRequest.add((DownloadRequest) getDownloadRequests(i));
		}
		// populate downloadClaim and downloadRequest

		for (int i = 0; i < PARTNER_AGREEMENT_NAMES.length; i++) {
			PartnerAgreement partnerAgreement = new PartnerAgreement();
			partnerAgreement.setPartnerAgreementId("id" + String.valueOf(i));
			partnerAgreement
					.setPartnerAgreementName(PARTNER_AGREEMENT_NAMES[i]);
			partnerAgreements.add(partnerAgreement);
		}
	}

	public static List<Account> getCustomerAccountList() {
		return customerAccountList;
	}

	public static List<Account> getPartnerAccountList() {
		return partnerAccountList;
	}

	public static List<AccountContact> getAccountContactList() {
		return accountContacts;
	}

	public static List<Asset> getDevices() {
		return devices;
	}

	public static List<ServiceRequest> getServiceRequests() {
		return serviceRequests;
	}

	public static List<Activity> getActivities() {
		return activities;
	}

	public static List<DownloadClaim> getDownloadClaims() {
		return downloadClaim;
	}

	public static List<DownloadRequest> getDownloadRequests() {
		return downloadRequest;
	}

	public static List<PartnerAgreement> getPartnerAgreements() {
		return partnerAgreements;
	}

	private static void populateLOVs() {
		activityStatusOpen
				.setType(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS
						.getValue());
		activityStatusOpen.setValue(RequestStatusEnum.OPEN.getValue());
		activityStatusCompleted
				.setType(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS
						.getValue());
		activityStatusCompleted
				.setValue(RequestStatusEnum.COMPLETED.getValue());
		activityStatusClaimSubmitted
				.setType(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS
						.getValue());
		activityStatusClaimSubmitted.setValue(RequestStatusEnum.CLAIM_SUBMITTED
				.getValue());
		
		activityStatusAccepted
		.setType(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS
				.getValue());
		activityStatusAccepted.setValue(RequestStatusEnum.ACCEPTED
		.getValue());
		
		activityStatusInProgress
		.setType(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS
				.getValue());
		activityStatusInProgress.setValue(RequestStatusEnum.IN_PROGRESS
		.getValue());
		
		activityStatusDispatchedToSP
		.setType(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS
				.getValue());
		activityStatusDispatchedToSP.setValue(RequestStatusEnum.DISPATCHED_TO_SP
		.getValue());
	}

	private static ListOfValues getPaymentStatus(int i) {
		PaymentStatusEnum paymentStatusnum = PaymentStatusEnum.values()[i % 6];
		ListOfValues paymentStatus = new ListOfValues();
		paymentStatus
				.setType(SiebelLocalizationOptionEnum.PARTNER_PAYMENT_STATUS
						.getValue());
		paymentStatus.setValue(paymentStatusnum.getValue());
		return paymentStatus;
	}

	public static List<Account> getAccountList() {
		List<Account> accounts = new ArrayList<Account>();
		for (int i = 0; i < 5; i++) {
			accounts.add(getAccount(i));
		}
		return accounts;
	}

	public static List<Account> getPartnerIndirectAccountList() {
		List<Account> accounts = new ArrayList<Account>();
		for (int i = 0; i < 5; i++) {
			accounts.add(getPartnerAccount(i));
		}

		return accounts;
	}

	public static DownloadClaim getDownloadClaims(int i) {
		logger.info("### In Mock getDownloadClaims #####");

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				- i % 40);

		final DownloadClaim downloadClaim = new DownloadClaim();
		downloadClaim.setSrNum("srId " + i);
		downloadClaim.setSrId("srId " + i);
		downloadClaim.setActivityId("activityId " + i);
		downloadClaim.setAddlPaymentDesc1("addlPaymentDesc1 " + i);
		downloadClaim.setAddlPaymentDesc2("addlPaymentDesc2 " + i);
		downloadClaim.setAddlPaymentQty1("addlPaymentQty1 " + i);
		downloadClaim.setAddlPaymentQty2("addlPaymentQty2 " + i);
		downloadClaim.setAddlPaymentType1("addlPaymentType1 " + i);
		downloadClaim.setAddlPaymentType2("addlPaymentType2 " + i);
		downloadClaim.setAssetMTM("asdownloadClaim.setMTM " + i);
		downloadClaim.setAssetProduct("asdownloadClaim.setProduct " + i);
		downloadClaim.setAstSerialNumber("astSerialNumber " + i);
		downloadClaim.setNewCustomerAccount("newCustomerAccount " + i);
		downloadClaim.setNewCustomerAddress("newCustomerAddress " + i);
		downloadClaim.setNewTechFirstName("newTechFirstName " + i);
		downloadClaim.setNewTechLastName("newTechLastName " + i);
		downloadClaim.setPartnerAddress1("partnerAddress1 " + i);
		downloadClaim.setPartnerAddress2("partnerAddress2 " + i);
		downloadClaim.setPartnerAddress3("partnerAddress3 " + i);
		downloadClaim.setPartnerCity("partnerCity " + i);
		downloadClaim.setPartnerCountry("partnerCountry " + i);
		downloadClaim.setPartnerName("partnerName " + i);
		downloadClaim.setPartnerPostal("partnerPostal " + i);
		downloadClaim.setPartnerProvince("partnerProvince " + i);
		downloadClaim.setPartnerRefNumber("partnerRefNumber " + i);
		downloadClaim.setPartnerSite("partnerSite " + i);
		downloadClaim.setPrContactEmail("prContactEmail " + i);
		downloadClaim.setPrContactFN("prContactFN " + i);
		downloadClaim.setPrContactLN("prContactLN " + i);
		downloadClaim.setPrContactWorkPhone("prContactWorkPhone " + i);
		downloadClaim.setPrinterCondition("printerCondition " + i);
		downloadClaim.setProblemCode("problemCode " + i);
		downloadClaim.setProblemDetails("problemDetails " + i);
		downloadClaim.setRepairCompleteFlag("repairCompleteFlag " + i);
		downloadClaim.setRepairDesc("repairDesc " + i);
		downloadClaim.setReqContactEmail("reqContactEmail " + i);
		downloadClaim.setReqContactFN("reqContactFN " + i);
		downloadClaim.setReqContactLN("reqContactLN " + i);
		downloadClaim.setReqContactWorkPhone("reqContactWorkPhone " + i);
		downloadClaim.setRequestReviewFlag("requestReviewFlag " + i);
		downloadClaim.setResolutionCode("resolutionCode " + i);
		downloadClaim.setReviewComments("reviewComments " + i);
		downloadClaim.setServiceAddress1("serviceAddress1 " + i);
		downloadClaim.setServiceAddress2("serviceAddress2 " + i);
		downloadClaim.setServiceAddress3("serviceAddress3 " + i);
		downloadClaim.setServiceCity("serviceCity " + i);
		downloadClaim.setServiceCountry("serviceCountry " + i);
		downloadClaim.setServicePostal("servicePostal " + i);
		downloadClaim.setServiceProvince("serviceProvince " + i);
		downloadClaim.setServiceState("serviceState " + i);
		downloadClaim.setSrvcEndDate(calendar.getTime());
		downloadClaim.setSrvcStartDate(calendar.getTime());
		downloadClaim.setTechLoginName("techLoginName " + i);

		downloadClaim.setAddlPaymentUnitPrice1("addlPaymentUnitPrice1 " + i);
		downloadClaim.setAddlPaymentUnitPrice2("addlPaymentUnitPrice2 " + i);
		downloadClaim.setPartnerOrg("partnerOrg " + i);
		downloadClaim.setPartnerState("partnerState" + i);
		List<DownloadClaimPart> downloadClaimPartList = new ArrayList<DownloadClaimPart>();
		for (int j = 0; j < 5; j++) {
			final DownloadClaimPart downloadClaimPart = new DownloadClaimPart();
			downloadClaimPart.setRecPartFlag("recPartFlag " + j);
			downloadClaimPart.setRecPartName("recPartName " + j);
			downloadClaimPart.setRecPartsErrCd1("recPartsErrCd1Fo " + j);
			downloadClaimPart.setRecPartsErrCd2("recPartsErrCd2Fo " + j);
			downloadClaimPart.setRecPartsQty("recPartsQty " + j);
			downloadClaimPart.setRecPartsRetCarrier("recPartsRetCarrier " + j);
			downloadClaimPart
					.setRecPartsRetTrackNum("recPartsRetTrackNum " + j);
			downloadClaimPart.setRecPartsStatus("recPartsStatus " + j);

			downloadClaimPartList.add(downloadClaimPart);
		}
		downloadClaim.setDownloadClaimPart(downloadClaimPartList);
		return downloadClaim;
	}

	public static DownloadRequest getDownloadRequests(int i) {
		logger.info("### In Mock getDownloadRequests #####");

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				- i % 40);

		final DownloadRequest downloadRequest = new DownloadRequest();
		downloadRequest.setSrNum("srNUM " + i);
		downloadRequest.setActionNarrative(" actionNarrative " + i);
		downloadRequest.setActionSubStatus(" actionSubStatus " + i);
		downloadRequest.setActivityId(" activityId " + i);
		downloadRequest.setActualFailureCode(" actualFailureCode " + i);
		downloadRequest.setActualEnd(calendar.getTime());
		downloadRequest.setActualStart(calendar.getTime());
		downloadRequest.setAddlPaymentDesc1(" addlPaymentDesc1 " + i);
		downloadRequest.setAddlPaymentDesc2(" addlPaymentDesc2 " + i);
		downloadRequest.setAddlPaymentQty1(" addlPaymentQty1 " + i);
		downloadRequest.setAddlPaymentQty2(" addlPaymentQty2 " + i);
		downloadRequest.setAddlPaymentType1(" addlPaymentType1 " + i);
		downloadRequest.setAddlPaymentType2(" addlPaymentType2 " + i);
		downloadRequest.setAddlPaymentUnitPrice1(" addlPaymentUnitPrice1 " + i);
		downloadRequest.setAddlPaymentUnitPrice2(" addlPaymentUnitPrice2 " + i);
		downloadRequest.setAddlServiceReq(" addlServiceReq " + i);
		downloadRequest.setComments(" comments " + i);
		downloadRequest.setCustomerRequestResponse(calendar.getTime());
		downloadRequest.setEstTimeArrival(calendar.getTime());
		downloadRequest
				.setInstalledDeviceCondition(" installedDeviceCondition " + i);
		downloadRequest.setInstalledIPAddress(" installedIPAddress " + i);
		downloadRequest.setInstalledMacAddress(" installedMacAddress " + i);
		downloadRequest.setNetworkConnected(" networkConnected " + i);
		downloadRequest.setNewTechFirstName(" newTechFirstName " + i);
		downloadRequest.setNewTechLastName(" newTechLastName " + i);
		downloadRequest.setNonLexmarkSuppliesUsed(" nonLexmarkSuppliesUsed "
				+ i);

		downloadRequest.setRepairCompleteFlag(" repairCompleteFlag " + i);
		downloadRequest.setResolutionCode(" resolutionCode " + i);
		downloadRequest.setsPReferenceNum(" sPReferenceNum " + i);
		downloadRequest.setStatusAsOf(calendar.getTime());
		downloadRequest.setSupplyManufacturer(" supplyManufacturer " + i);
		downloadRequest.setTechnician(" technician " + i);
		downloadRequest.setTravelDistance(" travelDistance " + i);
		downloadRequest.setTravelDistanceUM(" travelDistanceUM " + i);
		downloadRequest.setTravelDurationMin(" travelDurationMin " + i);

		downloadRequest.setDeInstalledAssetTag(" deInstalledAssetTag " + i);
		downloadRequest.setDeInstalledPageCount(" deInstalledPageCount " + i);
		downloadRequest.setInstalledAssetTag(" installedAssetTag " + i);
		downloadRequest.setInstalledPageCount(" installedPageCount " + i);

		List<DownloadRequestPart> downloadRequestPartList = new ArrayList<DownloadRequestPart>();
		for (int j = 0; j < 5; j++) {
			final DownloadRequestPart downloadRequestPart = new DownloadRequestPart();
			downloadRequestPart.setRecPartsDisp(" recPartsDisp " + j);
			downloadRequestPart.setRecPartsErrCd1(" recPartsErrCd1 " + j);
			downloadRequestPart.setRecPartsErrCd2(" recPartsErrCd2 " + j);
			downloadRequestPart.setRecPartsName(" recPartsName " + j);
			downloadRequestPart.setRecPartsQty(" recPartsQty " + j);
			downloadRequestPart.setRecPartsRetCarrier(" recPartsRetCarrier "
					+ j);
			downloadRequestPart.setRecPartsRetTrackNum(" recPartsRetTrackNum "
					+ j);

			downloadRequestPartList.add(downloadRequestPart);
		}

		downloadRequest.setDownloadRequestPart(downloadRequestPartList);

		return downloadRequest;
	}

	//Changed By MPS Offshore Team for updating new statuses for Service Order Types
	public static Activity getActivity(int i) {
		final Activity activity = new Activity();
		//Commented for MPS 2.1 wave 4 initial development by portal
		/*activity.setActivityId(String.valueOf(10000 + i));
		if (i < 5) {
			activity.setActivityStatus(activityStatusOpen);
		} else {
			if (Math.random() < 0.3) {
				activity.setActivityStatus(activityStatusOpen);
			} else if (Math.random() < 0.6) {
				activity.setActivityStatus(activityStatusCompleted);
			} else if (Math.random() < 0.7) {
				activity.setActivityStatus(activityStatusAccepted);
			} else if (Math.random() < 0.8) {
					activity.setActivityStatus(activityStatusInProgress);
			} else if (Math.random() < 0.9) {
					activity.setActivityStatus(activityStatusDispatchedToSP);
			} else {
				activity.setActivityStatus(activityStatusClaimSubmitted);
			}
		}

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				- i % 40);
		activity.setActivityDate(calendar.getTime());

		ListOfValues activityTypeLOV = new ListOfValues();
		if (Math.random() < 0.5) {
			activityTypeLOV.setValue(ServiceTypeEnum.INSTALLATION.getValue());
		} else {
			// activityTypeLOV.setValue(ServiceTypeEnum.LABOR_ONLY.getValue());
		}
		activity.setActivityType(activityTypeLOV);

		activity.setResponseMetric("responseMetric");
		ListOfValues activitySubStatusLOV = new ListOfValues();
		if (i % 5 == 0) {
			activitySubStatusLOV.setValue(RequestSubStatusEnum.CLAIM_ACCEPTED
					.getValue());
		} else if (i % 5 == 1) {
			activitySubStatusLOV.setValue(RequestSubStatusEnum.CLAIM_SUBMITTED
					.getValue());
		} else if (i % 5 == 2) {
			activitySubStatusLOV.setValue(RequestSubStatusEnum.NOT_ACCEPTED
					.getValue());
		} else if (i % 5 == 3) {
			activitySubStatusLOV
					.setValue(RequestSubStatusEnum.WRONG_PARTS_RECOMMENDED
							.getValue());
		} else if (i % 5 == 4) {
			activitySubStatusLOV.setValue(RequestSubStatusEnum.CLAIM_DENIED
					.getValue());
		}
		activity.setActivitySubStatus(activitySubStatusLOV);
		activity.setServiceProviderReferenceNumber("Provider Reference Number");
		activity.setCustomerRequestedResponseDate(new Date());
		activity.setServiceSummary("This is a service summary");
		activity.setAccountSpecialHandling("This is account special handling");
		activity.setAssetWarningMessage("This is asset warning message");
		activity.setServiceProviderAttemptNumber("This is service provider attempt number");
		
		activity.setServiceActivityWithin30Days("activity within 30 days");

		calendar.set(Calendar.DAY_OF_YEAR,
				calendar.get(Calendar.DAY_OF_YEAR) + 1);
		activity.setCustomerRequestedResponseDate(calendar.getTime());
		calendar.set(Calendar.DAY_OF_YEAR,
				calendar.get(Calendar.DAY_OF_YEAR) + 10);
		activity.setEstimatedArrivalTime(calendar.getTime());
		if (i % 4 == 0) {
			activity
					.setServiceProviderStatus(RequestStatusEnum.OPEN.getValue());
		} else if (i % 4 == 1) {
			activity.setServiceProviderStatus(RequestStatusEnum.CLAIM_SUBMITTED
					.getValue());
		} else if (i % 4 == 2) {
			activity.setServiceProviderStatus(RequestStatusEnum.COMPLETED
					.getValue());
		}
		if (i % 4 == 0) {
			activity.setAddressStatus(ADDRESS_STATUS_LIST.get(0));
		} else if (i % 4 == 1) {
			activity.setAddressStatus(ADDRESS_STATUS_LIST.get(1));
		} else if (i % 4 == 2) {
			activity.setAddressStatus(ADDRESS_STATUS_LIST.get(2));
		} else {
			activity.setAddressStatus(ADDRESS_STATUS_LIST.get(3));
		}
		if (i % 3 == 0) {
			activity.setStatusUpdateDate(calendar.getTime());
		}
		activity.setServicerComments("this is a service comments...");

		activity.setActualFailureCode(actualFailureCodes.get(i % 3));
		if (i % 3 == 0) {
			activity.setReviewComments("This is review comments " + i);
			activity.setRequestLexmarkReviewFlag(true);
		} else {
			activity.setRequestLexmarkReviewFlag(false);
		}
		activity.setPartnerAccount(partnerAccountList.get(i % 5));
		activity.setCustomerAccount(customerAccountList.get(i % 5));
		EntitlementServiceDetails serviceDetails = new EntitlementServiceDetails();
		serviceDetails.setServiceDetailsDescription("service type");
		activity.setSelectedServiceDetails(serviceDetails);
		activity.setServiceRequest(serviceRequests.get(i));
		activity.getServiceRequest().setPrimaryContact(
				accountContacts.get(i % 3));
		double technicianRandom;
		technicianRandom = Math.random();
		if (technicianRandom < 0.3) {
			activity.setTechnician(accountContacts.get(i % 3));
		} else if (technicianRandom < 0.4) {
			AccountContact technician = new AccountContact();
			technician.setNewContactFlag(true);
			technician.setFirstName("Other:firstName");
			technician.setLastName("Other:lastName");
			activity.setTechnician(technician);
		}
		activity.setServiceAddress(getGenericAddress(i));
		if (!activity.getServiceRequest().getServiceRequestNumber().endsWith(
				"3")) {
			activity.setDebrief(getDebrief(i));
		}
		if (Math.random() < 0.3) {
			activity
					.setNewCustomerAddressCombined("13th Street Department D 1st Floor Austin,ZJ 73301 USA");
		}
		activity.setRecommendedPartList(getRecommendedPartLineItemList(i));
		activity.setPendingPartList(getPartLineItemList());
		activity.setOrderPartList(getPartLineItemList());
		activity.setReturnPartList(getPartLineItemList());
		activity.setPendingShipmentPartList(getPartLineItemList());
		activity.setProcessedPartList(getPartLineItemList());
		activity.setAdditionalPaymentRequestList(getAddtionalPaymentRequestList());
		activity.setActivityNoteList(getActivityNoteList());
		
		if (i % 3 == 0) {
			activity.setServiceInstructionList(getServiceInstructionList());
		}

		activity.setEligibleToPay("eligibleToPay");
		activity.setPayEligiblityOverride("payEligiblityOverride");
		if (i % 4 == 0) {
			activity.setPartnerAgreementName(PARTNER_AGREEMENT_NAMES[0]);
		} else if (i % 4 == 1) {
			activity.setPartnerAgreementName(PARTNER_AGREEMENT_NAMES[1]);
		} else if (i % 4 == 2) {
			activity.setPartnerAgreementName(PARTNER_AGREEMENT_NAMES[2]);
		} else if (i % 4 == 3) {
			activity.setPartnerAgreementName(PARTNER_AGREEMENT_NAMES[3]);
		}
		activity.setLaborPayment(i);
		activity.setPartsPayment(i + 1);
		activity.setAdditionalPayments(i + 2);
		activity.setPartnerFee(i + 3);
		activity.setSuppliesFulfillmentFee(i + 5);	// added by nelson for MPS Source
		activity.setTotalPayment(4 * i + 6);
		if (i % 2 == 0) {
			activity.setPaymentServiceType("Onsite Repair");
			activity.setPayment(getPaymentsDetail(100));
		} else {
			activity.setPaymentServiceType("Complete");
			activity.setPayment(getPaymentsDetail(i));
		}

		if (activity.getActivityStatus().getValue() == RequestStatusEnum.COMPLETED
				.getValue()) {
			ServiceRequest serviceRequest = activity.getServiceRequest();
			Date now = new Date(serviceRequest.getServiceRequestDate()
					.getTime()
					+ 24 * 60 * 60 * 1000l);
			serviceRequest.setServiceRequestEndDate(now);
		}
		activity.setShipToAddress(getGenericAddress(i % 5));*/
		
		//Added for MPS 2.1 Wave 4 implementation
		
		ServiceRequest serviceRequest = getServiceRequest(i);
		//serviceRequest.setServiceRequestNumber("1234");
		
		
		
		
		if(i==1){
			serviceRequest.setRequestType("INSTALL")	;
		}else if(i==2){
			serviceRequest.setRequestType("DEINSTALL");
		}else{
			serviceRequest.setRequestType("HWMOVE");
		}
			
		
				
		Asset asset = getDevice(i);
		List<Part> partList = new ArrayList<Part>();
		Part part = null;
		for(int j=0; j<3; j++){
			part = new Part();
			part.setPartNumber("41G0100");
			part.setDescription("Lexmark C789dn");
			part.setOrderQuantity("2");
			partList.add(part);
		}
		
		asset.setDescription("Lexmark C746dtn color laser printer delivers high-quality color printing and reliable performance. Features like the 550-sheet tray increase efficiency. Energy-saving features make responsible printing easy");
		asset.setPartList(partList);
		
		asset.setDeviceName("Lexmark C789dn111");
		asset.setPartList(partList);
		asset.setPageCounts(getPageCounts());
		serviceRequest.setAsset(asset);
		
		activity.setServiceRequest(serviceRequest);
		activity.setActivityNoteList(getActivityNoteList());
		return activity;
	}
	
	public static Account getAccount(int i) {
		Account account = new Account();
		account.setAccountName(NAME_LIBARY.get(i % NAME_LIBARY.size()));
		account.setAccountId(String.valueOf(i));
		account.setAddress(getGenericAddress(i));
		if (i % 2 == 0)
			account.setCreateServiceRequest("update");
		else {
			account.setCreateServiceRequest("update");
		}
		account.setOrganizationID(String.valueOf((1000 + i)));
		account.setAddressStatus(ADDRESS_STATUS_LIST.get((i % 4)));
		return account;
	}

	public static Account getCustomerAccount(int i) {
		Account account = new Account();
		account.setAccountId("100" + i);
		account.setAccountName(CUSTOMER_FIRST_NAME_LIST.get(i % 5) + " "
				+ CUSTOMER_LAST_NAME_LIST.get(i % 5));
		account.setAddress(getGenericAddress(i));
		return account;
	}

	public static Account getPartnerAccount(int i) {
		Account account = getAccount(i);
		account.setAccountId(String.valueOf(i));
		account.setManualMeterRead("update");
		account.setUserConsumables("update");
		if (i % 2 == 0) {
			account.setIndirectPartnerFlag(false);
			account.setDirectPartnerFlag(true);
		} else {
			account.setIndirectPartnerFlag(true);
			account.setDirectPartnerFlag(false);
		}
		account.setAccountType(LexmarkConstants.VIEWTYPE_MANAGED_DEVICES);
		return account;
	}

	public static AccountContact getAccountContact(int i) {
		AccountContact ac = new AccountContact();
		if (i == 1)
			ac.setContactId("1-P3YKE" + i);
		else
			ac.setContactId("1-P0091" + i);
		ac.setFirstName("Alex" + Integer.toString(i));
		ac.setLastName("zhou");
		ac.setEmailAddress("takethat" + Integer.toString(i) + "@gmail.com");
		ac.setWorkPhone("135888888" + Integer.toString(i));
		if (i % 2 == 0)
			ac.setUserFavorite(true);
		else {
			ac.setUserFavorite(false);
		}
		return ac;
	}

	private static List<ServiceRequestActivity> getWebUpdateActivities(int i) {
		List<ServiceRequestActivity> webUpdateActivities = new ArrayList<ServiceRequestActivity>();
		Calendar calendar = Calendar.getInstance();
		for (int j = 0; j < 8; j++) {
			ServiceRequestActivity serviceRequestActivity = new ServiceRequestActivity();
			serviceRequestActivity.setActivityId(String.valueOf(83800 + j));
			if (j % 8 == 1) {
				serviceRequestActivity.setActivityStatus("complete");
				serviceRequestActivity
						.setActivityDescription("Your Service Request has been completed.");
			} else if (j % 8 == 2) {
				serviceRequestActivity.setActivityStatus("Delivered");
				serviceRequestActivity
						.setActivityDescription("Your item has been delivered.");
			} else if (j % 8 == 3) {
				serviceRequestActivity.setActivityStatus("In Transit");
				serviceRequestActivity
						.setActivityDescription("Your item is in transit for delivery.");
			} else if (j % 8 == 4) {
				serviceRequestActivity.setActivityStatus("test");
				serviceRequestActivity
						.setActivityDescription("Your item has been shipped.");
			} else if (j % 8 == 5) {
				serviceRequestActivity.setActivityStatus("Processed");
				serviceRequestActivity
						.setActivityDescription("Your request for an exchange has been processed.");
			} else if (j % 8 == 6) {
				serviceRequestActivity.setActivityStatus("scheduled");
				serviceRequestActivity
						.setActivityDescription("Your request for a technician has been scheduled.");
			} else if (j % 8 == 7) {
				serviceRequestActivity.setActivityStatus("In Process");
				serviceRequestActivity
						.setActivityDescription("Your request for serivice is in process.");
			} else {
				serviceRequestActivity.setActivityStatus("Submitted");
				serviceRequestActivity
						.setActivityDescription("Your request for service has been submitted.");
			}
			serviceRequestActivity.setActivityDate(calendar.getTime());
			serviceRequestActivity.setRecipientEmail("kemololler@lexmark.com");
			webUpdateActivities.add(serviceRequestActivity);
		}
		return webUpdateActivities;
	}

	private static List<ServiceRequestActivity> getEmailActivities(int i) {
		// TODO Auto-generated method stub
		return getWebUpdateActivities(1);
	}

	public static ServiceRequest getServiceRequest(int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2000 + i % 11);
		ServiceRequest sr = new ServiceRequest();
		sr.setId(String.valueOf(10000 + i));
		sr.setAsset(devices.get(i / 2));
		if (i % 4 == 0) {
			sr.setServiceRequestStatus(ServiceStatusEnum.IN_PROCESS.getValue());
			
		}
		if (i % 4 == 1) {
			sr.setServiceRequestStatus(ServiceStatusEnum.SUBMITTED.getValue());
		}
		if (i % 4 == 2) {
			sr.setServiceRequestStatus(ServiceStatusEnum.COMPLETED.getValue());
		}
		if (i % 4 == 3) {
			sr.setServiceRequestStatus(ServiceStatusEnum.SHIPPED.getValue());
		}
		sr.setStatusDetail("Tech On Site");
		sr.setServiceRequestDate(calendar.getTime());
	
		sr.setOptionExchangeOtherDescription("Exchange this printer for");
		sr.setProblemDescription("My printer is jammed");
		sr.setReferenceNumber("216" + Integer.toString(i));
		sr.setHelpdeskReferenceNumber("420" + Integer.toString(i));
		
		sr.setRespondWithin("1 day");
		//sr.setResolveWithin("2 days");
		sr.setServiceRequestEndDate(calendar.getTime());
		sr.setSpecialInstructions("Lorem ipsum dolor sit amet lorem ipsum dolor sit amet orem ipsum dolor sit amet.");
		sr.setActualStartDate(calendar.getTime());
		sr.setActualEndDate(calendar.getTime());
		sr.setTechnicianContact(getAccountContact(i));
		sr.setComments("Need to demolish  this device");
		
		sr.setOtherRequestedService("exchange");
		sr.setServiceRequestNumber("123" + Integer.toString(i));
		sr.setRequestor(getAccountContactList().get(0));
		sr.setPrimaryContact(getAccountContactList().get(1));
		sr.setSecondaryContact(getAccountContactList().get(2));
		sr.setServiceAddress(getGenericAddress(1));
		sr.setActivitywebUpdateActivities(getWebUpdateActivities(1));
		sr.setServicewebUpdateActivities(getWebUpdateActivities(1));
		sr.setSelectedServiceDetails(getEntitlementServiceDetails());
		sr.setEmailActivities(getEmailActivities(1));
		sr.setReturnOrderLines(getServiceRequestOrderLines("return"));
		sr.setShipmentOrderLines(getServiceRequestOrderLines("ship"));
		sr.setCustomerReferenceNumber("This is customer reference number");
		String serviceRequestType = "";
		if (i < 5) {
			serviceRequestType = ServiceRequestTypeEnum.SERVICE_REQUEST
					.getValue();
		} else {
			serviceRequestType = i % 2 == 0 ? ServiceRequestTypeEnum.ORDER_REQUEST
					.getValue()
					: ServiceRequestTypeEnum.CLAIM_REQUEST.getValue();
		}
		ListOfValues serviceRequestTypeLOV = new ListOfValues();
		serviceRequestTypeLOV.setValue(serviceRequestType);
		serviceRequestTypeLOV
				.setType(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_TYPE
						.getValue());
		sr.setServiceRequestType(serviceRequestTypeLOV);
		sr.setContactInfoForDevice(getAccountContactList());
		sr.setAttachments(getAttachments());
		return sr;
	}

	public static List<ServiceRequestOrderLineItem> getServiceRequestOrderLines(
			String derection) {
		List<ServiceRequestOrderLineItem> returnSROrderList = new ArrayList<ServiceRequestOrderLineItem>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < 36; i++) {
			calendar.set(Calendar.YEAR, 2000 + i % 11);
			ServiceRequestOrderLineItem orderLine = new ServiceRequestOrderLineItem();
			orderLine.setStatus("Shipped");
			orderLine.setProductTLI("part number " + i);
			orderLine.setSerialNumber("serialNumber" + i);
			orderLine.setProductDescription("productDescriptions" + i);
			orderLine.setStatusDate(calendar.getTime());
			if (i % 3 == 0) {
				orderLine.setCarrier(CarrierEnum.UPS.getValue());
			} else if (i % 3 == 1) {
				orderLine.setCarrier(CarrierEnum.FEDEX.getValue());
			} else {
				orderLine.setCarrier(CarrierEnum.YELLOW_FREIGHT.getValue());
			}
			if ("return".equals(derection)) {
				if (i % 3 == 0) {
					orderLine.setTrackingNumber(null);
					orderLine.setStatus("In Process");
				} else if (i % 3 == 1) {
					orderLine.setStatus("Delivered");
					if (i % 2 == 0) {
						orderLine.setTrackingNumber("1Z78E26E0110179717");
					} else {
						orderLine.setTrackingNumber("468086197421");
					}
				} else {
					orderLine.setTrackingNumber(null);
					orderLine.setStatus("Delivered");
				}
				orderLine.setSerialNumber(derection + " serialNumber" + i);
			} else {
				if (i % 7 == 1) {
					orderLine.setTrackingNumber("1Z78E26E0110179717");
				} else if (i % 7 == 2) {
					orderLine.setTrackingNumber("468086197421");
				} else if (i % 7 == 3) {
					orderLine.setTrackingNumber("468086197020");
				} else if (i % 7 == 4) {
					orderLine.setTrackingNumber("468086197465");
				} else if (i % 7 == 5) {
					orderLine.setTrackingNumber("1Z78E26E0110185326");
				} else if (i % 7 == 6) {
					orderLine.setTrackingNumber("");
				} else {
					orderLine.setTrackingNumber("1Z78E26E0110179717");
				}
			}
			returnSROrderList.add(orderLine);
		}
		return returnSROrderList;
	}

	private static Asset getDevice(int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2000 + i % 11);
		Asset aa = new Asset();
		String str = Integer.toString(i);
		aa.setAssetTag("23431325135" + str);
		aa.setMachineTypeModel("Lexmark C746dtn");
		//Commented because physicallocation1, 2 and 3 have been removed from Asset bean
		//aa.setPhysicalLocation2("Bentonville Sales Office");
		//aa.setPhysicalLocation3("Lexington.KY3");
		aa.setAssetId(Integer.toString(10000 + i));
		// set different serial number.
		if (i % 4 == 0) {
			aa.setSerialNumber("10" + Integer.toString(10000 + i));
			aa.setModelNumber("7465-985");
		} else if (i % 4 == 1) {
			aa.setSerialNumber("100000" + Integer.toString(10000 + i));
			aa.setModelNumber("4040-011");
		} else if (i % 4 == 2) {
			aa.setSerialNumber("10000000" + Integer.toString(10000 + i));
			aa.setModelNumber("4340-011");
		} else {
			aa.setSerialNumber("10000000000000" + Integer.toString(10000 + i));
			aa.setModelNumber("4040-016");
		}
		aa.setDeviceName("deviceName" + str);
		aa.setDeviceTag("deviceTag" + str);
		if (i % 2 == 0) {
			aa.setHostName("lexmark");
		} else {
			aa.setHostName("lexmark.dev");
		}
		aa.setIpAddress("192.168.1." + str);
		aa.setMeterReadDate(calendar.getTime());
		aa.setLastPageCount("500" + str);
		aa.setLastPageReadDate(calendar.getTime());
		aa.setNotMyPrinter(false);
		aa.setProductTLI(Integer.toString(10000 + i));
		//Commented because physicallocation1, 2 and 3 have been removed from Asset bean
		//aa.setPhysicalLocation1("3rd floor, east building");
		aa.setProductLine("Lexmark xd464dfe");
		Entitlement e = new Entitlement();
		e.setServiceDetails(getEntitlementServiceDetails());
		aa.setEntitlement(e);
		if (i % 2 == 1) {
			aa.setColorCapableFlag(true);
			aa.setLastColorPageCount("600" + str);
			aa.setLastColorPageReadDate(calendar.getTime());
		} else {
			aa.setColorCapableFlag(false);
			aa.setLastColorPageCount("0");
		}
		// if(i % 3 == 2)
		// aa.setProductImageURL("http://www.downloaddelivery.com/webcontent/images/product/75x75/MULTIFUNCTION_LASER.gif");
		aa.setInstallAddress(getGenericAddress(i));
		aa.setAssetContact(getAccountContact(i));
		if (i % 4 == 0) {
			aa.setUserFavoriteFlag(false);

		} else if (i % 4 == 1) {
			aa.setAssetType("DFM");
		} else if (i % 4 == 2) {
			aa.setAssetType("CSS");
		} else if (i % 4 == 3) {
			aa.setUserFavoriteFlag(true);
		}
		if (i < 305) {
			aa.setAccount(customerAccountList.get(i % 5));
			aa.setPartnerAccount(partnerAccountList.get(i % 5));
		} else if (i == 305) {
			aa.setAccount(customerAccountList.get(i % 5));
		} else if (i == 306) {
			aa.setPartnerAccount(partnerAccountList.get(i % 5));
		}
		aa.setCustomerAccount(getCustomerAccount(i));
		aa.setNewAddress(getGenericAddress(i));
		aa.setMoveToAddress(getGenericAddress(i+1));
		aa.setInstallDate(calendar.getTime());
		aa.setNetworkConnectedFlag(i%2==0?true:false);
		aa.setMacAddress("90-fb-a6-ec-d2-6f");
		aa.setSubnet("255.255.255.0");
		
		aa.setPortNumber("8080");
		aa.setFaxConnected(i%2==0?true:false);
		aa.setFaxPortNumber("8081");
		aa.setPrinterWorkingCondition("Not Working");
		aa.setWiringClosestNetworkPoint("STAR");
		aa.setDescription("Target - CTNDT100-B1");
		aa.setChlNodeValue("Solution Center");
		aa.setAssetCostCenter("CC-242333");
		
		aa.setOperatingSystem("Linux");
		aa.setOperatingSystemVersion("10.420");
		aa.setFirmware("15.02.3");
		aa.setNetworkTopology("STAR");
		aa.setTopBill("Top Bill");
		aa.setSpecialUsage("Special usage");
		aa.setAssetLifeCycle("End of Life");
		
		return aa;
	}

	private static Object fillNullFieldValues(Object object) {
		try {
			for (Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (field.getType().getSimpleName().equals("String")
						&& field.get(object) == null) {
					final String fieldName = field.getName();
					field.set(object, "This is " + fieldName);
				}
			}
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		return object;
	}

	public static List<EntitlementServiceDetail> getEntitlementServiceDetails() {
		EntitlementServiceDetail esd = new EntitlementServiceDetail();
		esd.setServiceDetailId("1");
		esd.setServiceDetailDescription("Onsite Repair");
		EntitlementServiceDetail esd2 = new EntitlementServiceDetail();
		esd2.setServiceDetailId("2");
		esd2.setServiceDetailDescription("Onsite Exchange of Option");
		EntitlementServiceDetail esd3 = new EntitlementServiceDetail();
		esd3.setServiceDetailId("3");
		esd3.setServiceDetailDescription("Exchange of Device");
		EntitlementServiceDetail esd4 = new EntitlementServiceDetail();
		esd4.setServiceDetailId("4");
		esd4.setServiceDetailDescription("Exchange of Option");
		ArrayList<EntitlementServiceDetail> esds = new ArrayList<EntitlementServiceDetail>();
		esds.add(esd);
		esds.add(esd2);
		esds.add(esd3);
		// esds.add(esd4);
		return esds;
	}

	public static List<GenericAddress> getGenericAddressList() {
		List<GenericAddress> genericAddresses = new ArrayList<GenericAddress>();
		int totalCount = 0;
		for (int i = 0; i < 18; i++) {
			genericAddresses.add(getGenericAddress(i));
			totalCount++;
		}
		return genericAddresses;
	}

	public static GenericAddress getGenericAddress(int i) {
		GenericAddress ga = new GenericAddress();
		switch (i % 9) {
		case 0:
			ga = populateGenericAddress(String.valueOf(i), "16th Street",
					"Department A", "1st Floor", "--", "Axis Cart Rent",
					"80202", "USA", null, "Colorado", "Denver");
			break;
		case 1:
			ga = populateGenericAddress(String.valueOf(i), "15th Street",
					"Department B", "1st Floor", "--", "Company B", "80903",
					"USA", null, "Colorado", "Colorado Springs");
			ga.setUserFavorite(true);
			break;
		case 2:
			ga = populateGenericAddress(String.valueOf(i), "14th Street",
					"Department C", "1st Floor", "--", "Company C", "81501",
					"USA", null, "Colorado", "Grand Junction");
			break;
		case 3:
			ga = populateGenericAddress(String.valueOf(i), "13th Street",
					"Department D", "1st Floor", "--", "Company D", "73301",
					"USA", null, "Texas", "Austin");
			break;
		case 4:
			ga = populateGenericAddress(String.valueOf(i), "12th Street",
					"Department E", "1st Floor", "--", "Company E", "78201",
					"USA", null, "Texas", "San Antonio");
			ga.setUserFavorite(true);
			break;
		case 5:
			ga = populateGenericAddress(String.valueOf(i), "11th Street",
					"Department E", "1st Floor", "--", "Company E", "77001",
					"USA", null, "Texas", "Houston");
			ga.setUserFavorite(true);
			break;
		case 6:
			ga = populateGenericAddress(String.valueOf(i), "10th Street",
					"Department F", "1st Floor", "--", "Company F", "75201",
					"USA", null, "Texas", "Dallas");
			break;
		case 7:
			ga = populateGenericAddress(String.valueOf(i), "9th Street",
					"Department G", "1st Floor", "--", "Company G", "M4B 1T4",
					"Canada", "Ontario", null, "Toronto");
			ga.setUserFavorite(true);
			break;
		case 8:
			ga = populateGenericAddress(String.valueOf(i), "8th Street",
					"Department H", "1st Floor", "--", "Company H", "K1A 0C1",
					"Canada", "Ontario", null, "Ottawa");
			break;
		}

		return ga;
	}

	private static GenericAddress populateGenericAddress(String addressId,
			String addressLine1, String addressLine2, String addressLine3,
			String addressLine4, String addressName, String postalCode,
			String country, String province, String state, String city) {
		GenericAddress address = new GenericAddress();
		address.setAddressId(addressId);
		address.setAddressLine1(addressLine1);
		address.setAddressLine2(addressLine2);
		address.setAddressLine3(addressLine3);
		address.setAddressLine4(addressLine4);
		address.setAddressName(addressName);
		address.setPostalCode(postalCode);
		address.setCountry(country);
		address.setProvince(province);
		address.setState(state);
		address.setCity(city);
		return address;
	}

	private static Notification getNotification(int index,
			Integer notificationId) {
		Notification notification = new Notification();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

		calendar.set(Calendar.DAY_OF_YEAR, dayOfYear - 3 + index);

		Date displayDate = calendar.getTime();
		calendar.set(Calendar.DAY_OF_YEAR, dayOfYear - 3 + index + 30 + index
				% 2);
		Date removeDate = calendar.getTime();

		notification.setDisplayDate(displayDate);
		notification.setRemoveDate(removeDate);
		notification.setDisplayOrder(index + 1);
		notification.setAdminName("FirmWare update " + Integer.toString(index));
		notification.setNotificationId(notificationId);
		if ((notificationId - 100000) % 3 == 0) {
			notification.setDisplayURL("http://www.google.com/");
		} else if ((notificationId - 100000) % 3 == 1) {
			notification.setDisplayURL("http://www.lexmark.com/");
		} else {
		}
		return notification;
	}

	private static NotificationLocale generateNotificationLocale(
			Integer notificationLocaleId, Integer notifictionId,
			int localeIndex, String displayDescription) {
		NotificationLocale notificationLocale = new NotificationLocale();
		notificationLocale.setDisplayDescription(displayDescription);
		notificationLocale.setNotificationLocaleId(notificationLocaleId);
		notificationLocale.setNotificationId(notifictionId);
		notificationLocale.setSupportedLocale(getSupportedLocales().get(
				localeIndex));
		return notificationLocale;
	}

	public static List<NotificationLocale> getNotificationLocaleList(
			Integer notificationId, Locale locale) {
		List<NotificationLocale> notificationLocaleList = new ArrayList<NotificationLocale>();
		// TODO: add more detailed precondition to get specific
		// NotificationLocale based on locale
		if (locale != null) {
			int localeIndex = 0;
			for (SupportedLocale supportedLocale : getSupportedLocales()) {
				if (supportedLocale.getSupportedLocaleCode().equals(
						locale.getLanguage())) {
					break;
				}
				localeIndex++;
			}
			if ((notificationId - 100000) % 3 == 0) {
				notificationLocaleList.add(generateNotificationLocale(
						notificationId * 10 + 1, notificationId, localeIndex,
						"This is the Notification call back" + notificationId
								+ " in " + locale.getDisplayLanguage()));
			} else if ((notificationId - 100000) % 3 == 1) {
				notificationLocaleList.add(generateNotificationLocale(
						notificationId * 10 + 1, notificationId, localeIndex,
						"This is the Notification from lexmark"
								+ notificationId + " in "
								+ locale.getDisplayLanguage()));
			} else {
				notificationLocaleList.add(generateNotificationLocale(
						notificationId * 10 + 1, notificationId, localeIndex,
						"This is the Notification for note" + notificationId
								+ " in " + locale.getDisplayLanguage()));
			}
		} else {
			notificationLocaleList
					.add(generateNotificationLocale(notificationId * 10 + 1,
							notificationId, INDEX_LOCALE_US,
							"Notification Description" + notificationId
									+ " in English"));
			notificationLocaleList.add(generateNotificationLocale(
					notificationId * 10 + 2, notificationId,
					INDEX_LOCALE_ITALY, "Notification Description"
							+ notificationId + " in Italy"));
			notificationLocaleList.add(generateNotificationLocale(
					notificationId * 10 + 3, notificationId,
					INDEX_LOCALE_FRANCE, "Notification Description"
							+ notificationId + " in French"));
			// notificationLocaleList.add(generateNotificationLocale(notificationId
			// * 10 + 4, notificationId, INDEX_LOCALE_GERMAN,
			// "Notification Description" + notificationId + " in Germany"));
			notificationLocaleList.add(generateNotificationLocale(
					notificationId * 10 + 5, notificationId,
					INDEX_LOCALE_CHINESE, "Notification Description"
							+ notificationId + " in Chinese"));
		}
		return notificationLocaleList;
	}

	private static List<NotificationLocale> getNotificationLocaleListEmptyDescription() {
		List<NotificationLocale> notificationLocaleList = new ArrayList<NotificationLocale>();
		notificationLocaleList.add(generateNotificationLocale(null, null,
				INDEX_LOCALE_US, null));
		notificationLocaleList.add(generateNotificationLocale(null, null,
				INDEX_LOCALE_ITALY, null));
		notificationLocaleList.add(generateNotificationLocale(null, null,
				INDEX_LOCALE_FRANCE, null));
		notificationLocaleList.add(generateNotificationLocale(null, null,
				INDEX_LOCALE_GERMAN, null));
		notificationLocaleList.add(generateNotificationLocale(null, null,
				INDEX_LOCALE_CHINESE, null));
		return notificationLocaleList;
	}

	public static List<NotificationDetail> getNotificationDetailList(
			Locale locale) {
		// TODO: add mock data here. notificationLocaleList contains only one
		// NotificationLocale object which is based on locale.
		List<NotificationDetail> notificationDetailList = new ArrayList<NotificationDetail>();
		for (int i = 0; i < 15; i++) {
			notificationDetailList.add(getNotificationDetail(i, 100000 + i,
					locale));
		}
		return notificationDetailList;
	}

	private static NotificationDetail getNotificationDetail(int index,
			Integer notificationId, Locale locale) {
		NotificationDetail notificationDetail = new NotificationDetail();
		notificationDetail.setNotification(getNotification(index,
				notificationId));
		notificationDetail.setNotificationLocaleList(getNotificationLocaleList(
				notificationId, locale));
		return notificationDetail;
	}

	public static NotificationDetail getEmptyNotificationDetail() {
		NotificationDetail detail = new NotificationDetail();
		detail
				.setNotificationLocaleList(getNotificationLocaleListEmptyDescription());
		Notification emptyNotification = new Notification();
		Calendar calendar = Calendar.getInstance();
		emptyNotification.setDisplayDate(calendar.getTime());
		emptyNotification.setRemoveDate(calendar.getTime());
		detail.setNotification(emptyNotification);
		return detail;
	}

	public static List<SupportedLocale> getSupportedLocales() {
		if (supportedLocales == null) {
			supportedLocales = new ArrayList<SupportedLocale>();
			final Locale[] locales = { Locale.US, Locale.FRANCE, Locale.ITALY,
					Locale.GERMAN, new Locale("es"), new Locale("pt"),
					Locale.TRADITIONAL_CHINESE, Locale.SIMPLIFIED_CHINESE };
			for (int i = 0; i < locales.length; i++) {
				supportedLocales.add(generateSupportedLocale(i, locales[i]));
			}
		}
		return supportedLocales;
	}

	private static SupportedLocale generateSupportedLocale(Integer id,
			Locale locale) {
		SupportedLocale supportedLocale = new SupportedLocale();
		supportedLocale.setSupportedLocaleCode(locale.getLanguage());
		supportedLocale.setSupportedLocaleId(id);
		supportedLocale.setSupportedLocaleName(locale.getDisplayName());
		return supportedLocale;
	}

	public static List<ContactInformation> getContactInformationList() {
		List<ContactInformation> contactInfoList = new ArrayList<ContactInformation>();

		ContactInformation contactInformation2 = new ContactInformation();
		contactInformation2.setRoleName("standardaccess");
		contactInformation2
				.setContactData("Micheal Jordan<br>(231)-331-1006<br>micheal@lexmark.com");
		contactInfoList.add(contactInformation2);

		ContactInformation contactInformation = new ContactInformation();
		contactInformation.setRoleName("accountmanagement");
		contactInformation
				.setContactData("Jay Michaels<br>(231)-331-1004<br>jay@lexmark.com");
		contactInfoList.add(contactInformation);

		ContactInformation contactInformation1 = new ContactInformation();
		contactInformation1.setRoleName("billing");
		contactInformation1
				.setContactData("Adam Moore<br>(231)-331-1005<br>adam@lexmark.com");
		contactInfoList.add(contactInformation1);

		return contactInfoList;
	}

	public static List<Document> generateDocumentList() {
		List<Document> documents = new ArrayList<Document>();

		DocumentDefinition[] definitions = generateDocumentDefinition();

		String[] exts = { ".pdf", ".doc", ".pdf", ".xls", ".txt", ".docx",
				".docx", ".aaa", ".xls", ".pdf", ".xlsx" };
		Calendar cal = Calendar.getInstance();
		final int NUM_FISRT_LEVEL = 7;
		int idxDoc = 0;
		for (int i = 0; i < NUM_FISRT_LEVEL; i++) {
			String level1Name = "/Level 1 Node " + i + "/";
			final int NUM_SECOND_LEVEL = 4;
			for (int j = 0; j < NUM_SECOND_LEVEL; j++) {
				String level2Name = level1Name + "Level 2 Node " + i + "." + j
						+ "/";
				final int NUM_THIRD_LEVEL = 5;
				for (int k = 0; k < NUM_THIRD_LEVEL; k++) {
					String level3Name = level2Name + "Level 3 Node " + i + "."
							+ j + "." + k + "/";
					final int NUM_FILES = 35;
					for (int l = 0; l < NUM_FILES; l++) {
						String ext = exts[idxDoc % exts.length];
						String fileName = "File " + i + "." + j + "." + k + "."
								+ l + ext;
						String path = level3Name + fileName;
						logger.info(path);
						Document document = new Document();
						document.setFileName(fileName);
						document.setFiledataLink(0L);
						document.setFilePath(path);
						document.setFileSize(3000 + idxDoc);
						document.setFileType(ext.substring(1));
						document.setDefinition(definitions[idxDoc
								% definitions.length]);
						cal.set(Calendar.DAY_OF_MONTH, idxDoc % 29 + 1);
						document.setLastUpdateTime(cal.getTime());
						documents.add(document);
						document.setFileObjectId(idxDoc + 1 + "");
						idxDoc++;
					}
				}
			}
		}
		return documents;
	}

	public static List<EmailNotification> getEmailNotificationList() {
		List<EmailNotification> list = new ArrayList<EmailNotification>();
		for (int i = 0; i < 10; i++) {
			EmailNotification emailNotification = new EmailNotification();
			emailNotification.setEmailNotificationId(new Integer("123456789"
					+ i));
			emailNotification.setEmailName("Report Share" + i);
			emailNotification
					.setEmailDescription("Report Share Email Description" + i);
			List<EmailNotificationLocale> emailNotificationLocaleList = new ArrayList<EmailNotificationLocale>();
			for (int num = 0; num < 5; num++) {
				emailNotificationLocaleList.add(getEmailNotificationLocale(num,
						i));
			}
			emailNotification
					.setEmailNotificationLocaleList(emailNotificationLocaleList);

			list.add(emailNotification);
		}
		return list;
	}

	public static EmailNotificationLocale getEmailNotificationLocale(int i,
			int idAtrr) {
		String country[] = { "English", "Spanish", "French", "German",
				"Japanse" };
		EmailNotificationLocale emailNotificationLocale = new EmailNotificationLocale();
		if (new Random().nextInt(3) == 0) {
			// emailNotificationLocale.setEmailNotificationId("123456789"+idAtrr);
			emailNotificationLocale.setEmailNotificationLocaleId(new Integer(
					"987654321" + idAtrr + i));
			// emailNotificationLocale.setLocaleId(new Integer("1000"+i));
			// emailNotificationLocale.setLocaleName(country[i]);
			emailNotificationLocale
					.setEmailSubject("emailSubject" + idAtrr + i);
			emailNotificationLocale.setEmailHeader("emailHeader" + idAtrr + i);
			emailNotificationLocale.setEmailBody("emailBody" + idAtrr + i);
			emailNotificationLocale.setEmailFooter("emailFooter" + idAtrr + i);
		} else {
			// emailNotificationLocale.setEmailNotificationId("123456789"+idAtrr);
			emailNotificationLocale.setEmailNotificationLocaleId(new Integer(
					"987654321" + idAtrr + i));
			// emailNotificationLocale.setLocaleId(new Integer("1000"+i));
			// emailNotificationLocale.setLocaleName(country[i]);
		}
		return emailNotificationLocale;
	}

	public static EmailNotificationLocale getEmailNotificationLocaleFields(int i) {
		String country[] = { "English", "Spanish", "French", "German",
				"Japanse" };
		EmailNotificationLocale emailNotificationLocale = new EmailNotificationLocale();
		// emailNotificationLocale.setLocaleId(new Integer("1000"+i));
		// emailNotificationLocale.setLocaleName(country[i]);

		return emailNotificationLocale;
	}

	private static DocumentDefinition[] generateDocumentDefinition() {
		Role[] roles = generateRoles();
		SupportedLocale[] locales = generateSupportedLocales();
		RoleCategory[] categories = generateRoleCategories(roles, locales);
		String[] mdmids = { "Global", "Domestic", "Legal", "Account", "Siebel" };
		DocumentDefinition[] definitions = new DocumentDefinition[100];
		for (int i = 0; i < definitions.length; i++) {
			DocumentDefinition definition = definitions[i] = new DocumentDefinition();
			definition.setId(i + 1);
			definition.setMdmId(mdmids[i % mdmids.length].toUpperCase());
			definition.setMdmLevel(mdmids[i % mdmids.length]);
			definition.setName("Document Name " + i);
			definition.setRoleCategory(categories[i % categories.length]);
		}
		return definitions;
	}

	private static SupportedLocale[] generateSupportedLocales() {
		String[][] localesStrs = { { "English", "en" }, { "French", "fr" },
				{ "Italian", "it" }, { "German", "de" }, { "Spanish", "es" },
				{ "Portuguese", "pt" }, { "Traditional Chinese", "zh_TW" },
				{ "Simplified Chinese", "zh_CN" }, };
		SupportedLocale[] locales = new SupportedLocale[localesStrs.length];
		for (int i = 0; i < locales.length; i++) {
			SupportedLocale locale = locales[i] = new SupportedLocale();
			locale.setSupportedLocaleCode(localesStrs[i][1]);
			locale.setSupportedLocaleName(localesStrs[i][0]);
		}
		return locales;
	}

	private static RoleCategory[] generateRoleCategories(Role[] roles,
			SupportedLocale[] supportedLocales) {
		int[][] maps = { { 5, 0, }, { 8, 2, 7, 3, 6, }, { 3, 8, },
				{ 2, 5, 7, 4, }, { 0, 5, }, { 3, 0, 2, }, { 4, 2, }, { 1, },
				{ 5, 7, 0, }, { 0, 2, 3, }, };
		RoleCategory[] categories = new RoleCategory[maps.length];
		for (int i = 0; i < categories.length; i++) {
			RoleCategory roleCategory = categories[i] = new RoleCategory();
			roleCategory.setName("Category " + (i + 1));
			roleCategory.setOrderNumber(i);

			List<RoleCategoryLocale> localeList = new ArrayList<RoleCategoryLocale>();
			for (SupportedLocale supportedLocale : supportedLocales) {
				RoleCategoryLocale categoryLocale = new RoleCategoryLocale();
				categoryLocale.setName(roleCategory.getName() + " - "
						+ supportedLocale.getSupportedLocaleCode());
				categoryLocale.setSupportedLocale(supportedLocale);
			}
			roleCategory.setLocaleList(localeList);
			roleCategory.setType("D");
			List<Role> listroles = new ArrayList<Role>();
			for (int roleId : maps[i]) {
				listroles.add(roles[roleId % roles.length]);
			}
			roleCategory.setRoles(listroles);

		}
		return categories;
	}

	private static Role[] generateRoles() {
		String[] roleNames = { "Standard Access", "Service and Support",
				"Account Management", "Billing", "Project Management",
				"Analyst", "Account Administrator",
				"Services Portal Administrator", "Publishing" };
		Role[] roles = new Role[roleNames.length];
		for (int i = 0; i < roleNames.length; i++) {
			roles[i] = new Role();
			roles[i].setName(roleNames[i]);
		}
		return roles;
	}

	public static List<MeterReadStatus> getMeterReadStatusList() {
		List<MeterReadStatus> list = new ArrayList<MeterReadStatus>();
		for (int i = 1; i < 500; i++) {
			MeterReadStatus meterReadStatus = new MeterReadStatus();
			meterReadStatus.setAttachmentName("123456~filename" + i + "~"
					+ formatDateString());
			meterReadStatus.setSize(5500);
			meterReadStatus.setSubmittedOn(formatDate());
			meterReadStatus.setCompletedOn(formatDate());
			if (i % 9 == 0) {
				meterReadStatus.setStatus("Error");
				meterReadStatus.setComment("Error on import:20 rows failed");
			} else {
				meterReadStatus.setStatus("Successful");
				meterReadStatus.setComment("File imported successfully");
			}

			list.add(meterReadStatus);
		}
		return list;
	}

	private static String formatDate() {
		Calendar currentDate = GregorianCalendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(currentDate.getTime());
	}

	private static String formatDateString() {
		Calendar currentDate = GregorianCalendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssmmm");
		return dateFormat.format(currentDate.getTime());
	}

	public static List<Part> getPartList() {
		List<Part> list = new ArrayList<Part>();
		for (int i = 0; i <= 200; i++) {
			list.add(getPart(i));
		}
		return list;
	}

	public static Part getPart(int index) {
		Part part = new Part();
		Random random = new Random();
		if (index < 100 && index > 1) {
			int ind = 1000;
			part.setPartId("id_" + String.valueOf((ind + index)));
			part.setPartNumber(String.valueOf((ind + index)));
			part.setPartName("Part Name" + index);
			part.setReturnRequiredFlag(random.nextBoolean());
			part.setReplacementPartNumber(random.nextInt(10) == 0 ? String
					.valueOf((index + 2000)) : "");
		} else if (index == 0) {
			part.setPartId("id_1000");
			part.setPartNumber("1000");
			part.setPartName("Part Name0");
			part.setReturnRequiredFlag(true);
			part.setReplacementPartNumber("2000");

		} else if (index == 1) {
			part.setPartId("id_1001");
			part.setPartNumber("1001");
			part.setPartName("Part Name1");
			part.setReturnRequiredFlag(false);
			part.setReplacementPartNumber("2001");
		} else if (index == 100) {
			part.setPartId("id_2000");
			part.setPartNumber("2000");
			part.setPartName("Part Name100");
			part.setReturnRequiredFlag(true);
		} else if (index == 101) {
			part.setPartId("id_2001");
			part.setPartNumber("2001");
			part.setPartName("Part Name101");
			part.setReturnRequiredFlag(false);
			part.setReplacementPartNumber("3000");

		} else if (index == 200) {
			part.setPartId("id_3000");
			part.setPartNumber("3000");
			part.setPartName("Part Name200");
			part.setReturnRequiredFlag(false);
		} else {
			int ind = 2000;
			part.setPartId("id_" + String.valueOf((ind + index - 100)));
			part.setPartNumber(String.valueOf((ind + index - 100)));
			part.setPartName("Part Name" + index);
			part.setReturnRequiredFlag(random.nextBoolean());
		}
		return part;
	}
//CatalogOrder Mock Object
	public static List<OrderPart> getCatalogPartList() {
		List<OrderPart> catalogPartList = new ArrayList<OrderPart>();
		for (int i = 0; i <= 20; i++) {
			catalogPartList.add(getCatalogPart(i));
		}
		return catalogPartList;
	}

	public static OrderPart getCatalogPart(int index) {
		OrderPart part = new OrderPart();
		Random random = new Random();
		if (index < 100 && index > 1) {
			int ind = 1000;
			//part.setPartId("id_" + String.valueOf(("ind"+ index)));
			part.setPartNumber(String.valueOf((ind + index)));
			part.setPartType("PartType" + index);
			part.setDescription("MockDescriptionofthePartName" + index);
			part.setYield("color" + index);
			part.setModel("model"+index);
			part.setCatalogId("catalogId"+index);
			// part.setReplacementPartNumber(random.nextInt(10) == 0?
			// String.valueOf((index + 2000)) : "");
		} else if (index == 0) {
			part.setPartNumber("1000");
			part.setPartType("PartType0");
			part.setDescription("MockDescriptionofthePartName0");
			part.setYield("Mono"+index);
			part.setModel("model"+index);
			part.setCatalogId("catalogId"+index);
		} else if (index == 1) {
			part.setPartNumber("1001");
			part.setPartType("PartType0");
			part.setDescription("MockDescriptionofthePartName0");
			part.setYield("color"+index);
			part.setModel("model"+index);
			part.setCatalogId("catalogId"+index);
		} else if (index == 100) {
			
			part.setPartNumber("2000");
			
			part.setPartType("PartType100");
			part.setDescription("MockDescriptionofthePartName100");
			part.setYield("Mono100"+index);
			part.setModel("model"+index);
			part.setCatalogId("catalogId"+index);
		} else if (index == 101) {
			
			part.setPartNumber("2001");
			
			part.setPartType("PartType101");
			part.setDescription("MockDescriptionofthePartName101");
			part.setYield("color101"+index);
			part.setModel("model"+index);
			part.setCatalogId("catalogId"+index);
		} else if (index == 200) {

			part.setPartNumber("3000");

			part.setPartType("PartType200");
			part.setDescription("MockDescriptionofthePartName200");
			part.setYield("color200"+index);
			part.setModel("model"+index);
			part.setCatalogId("catalogId"+index);
		} else {
			int ind = 2000;
			part.setPartNumber(String.valueOf((ind + index - 100)));
			part.setPartType("PartType2000");
			part.setDescription("MockDescriptionofthePartName2000");
			part.setYield("color2000"+index);
			part.setModel("model"+index);
			part.setCatalogId("catalogId"+index);
		}
		return part;
	}

		
	
	public static List<OrderPart> getCatalogSelectedPartList() {
		List<OrderPart> selectedPartList = new ArrayList<OrderPart>();
		for (int i = 0; i <= 5; i++) {
			selectedPartList.add(getCatalogSelectedPart(i));
		}
		return selectedPartList;
	}

	public static OrderPart getCatalogSelectedPart(int index) {
		OrderPart part = new OrderPart();
		Random random = new Random();
		if (index < 100 && index > 1) {
			int ind = 1000;
			//part.setPartId("id_" + String.valueOf(("ind"+ index)));
			part.setPartNumber(String.valueOf((ind + index)));
			part.setPartType("PartType" + index);
			part.setDescription("MockDescriptionofthePartName" + index);
			part.setYield("color" + index);
			part.setOrderQuantity(String.valueOf(index));
			// part.setReplacementPartNumber(random.nextInt(10) == 0?
			// String.valueOf((index + 2000)) : "");
		} else if (index == 0) {
		
			part.setPartNumber("2000");
			
			part.setPartType("PartType0");
			part.setDescription("MockDescriptionofthePartName0");
			part.setYield("Mono"+index);
			part.setOrderQuantity(String.valueOf(index));

		} else if (index == 1) {
			
			part.setPartNumber("1001");
			
			part.setPartType("PartType0");
			part.setDescription("MockDescriptionofthePartName0");
			part.setYield("color"+index);
			part.setOrderQuantity(String.valueOf(index));
		} else if (index == 100) {
			
			part.setPartNumber("2000");
			
			part.setPartType("PartType100");
			part.setDescription("MockDescriptionofthePartName100");
			part.setYield("Mono100"+index);
			part.setOrderQuantity(String.valueOf(index));
		} else if (index == 101) {
			
			part.setPartNumber("2001");
			
			part.setPartType("PartType101");
			part.setDescription("MockDescriptionofthePartName101");
			part.setYield("color101"+index);
			part.setOrderQuantity(String.valueOf(index));

		} else if (index == 200) {

			part.setPartNumber("3000");
	
			part.setPartType("PartType200");
			part.setDescription("MockDescriptionofthePartName200");
			part.setYield("color200"+index);
			part.setOrderQuantity(String.valueOf(index));
		} else {
			int ind = 2000;
	
			part.setPartNumber(String.valueOf((ind + index - 100)));
		
			part.setPartType("PartType2000");
			part.setDescription("MockDescriptionofthePartName2000");
			part.setYield("color2000"+index);
			part.setOrderQuantity(String.valueOf(index));
		}
		return part;
	}

	
	
	private static Debrief getDebrief(int i) {
		Debrief debrief = new Debrief();

		if (i % 2 == 0) {
			debrief.setRepairDescription("Lorem ispsum dolor sit amet");
			debrief.setPartDebriefList(getPartDebriefList());
		} else {
			StringBuilder repairDescription = new StringBuilder(
					"repair description");
			if (i > 400) {
				for (int k = 0; k < 100; k++)
					repairDescription.append(" repair description");
			}
			debrief.setRepairDescription(repairDescription.toString());
		}
		StringBuilder problemDescription = new StringBuilder(
				"problem description");
		if (i > 350) {
			for (int k = 0; k < 100; k++)
				problemDescription.append(" problem description");
		}
		debrief.setProblemDescription(problemDescription.toString());

		ListOfValues resolutionCode = new ListOfValues();
		resolutionCode.setValue(ResolutionCodeEnum.values()[i % 2].getValue());
		debrief.setResolutionCode(resolutionCode);
		ListOfValues deviceConditionLOV = new ListOfValues();
		deviceConditionLOV.setValue(DeviceConditionEnum.values()[(i + 1) % 2]
				.getValue());
		deviceConditionLOV
				.setType(SiebelLocalizationOptionEnum.PARTNER_DEVICE_CONDITION
						.getValue());
		debrief.setDeviceCondition(deviceConditionLOV);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2000 + i % 11);
		debrief.setServiceStartDate(calendar.getTime());
		calendar.set(Calendar.YEAR, 2000 + i % 11 + 1);
		debrief.setServiceEndDate(calendar.getTime());
		debrief.setInstalledAsset(devices.get(i % devices.size()));

		if (i % 2 == 0) {
			debrief.setDebriefActionStatus("working");
		} else {
			debrief.setDebriefActionStatus("Condition " + i);
		}

		return (Debrief) fillNullFieldValues(debrief);
	}

	private static List<PartLineItem> getPartDebriefList() {
		List<PartLineItem> partLineItemList = new ArrayList<PartLineItem>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 1; i < 5; i++) {
			calendar.set(Calendar.DAY_OF_MONTH, i % 28);
			PartLineItem partLineItem = new PartLineItem();
			partLineItem.setPartName("Fusion Coil 110V " + i);
			partLineItem.setPartNumber("100" + i);
			partLineItem.setPartId("1000" + i);
			partLineItem.setPartLineItemId("10000" + i);
			partLineItem.setRequestedDate(calendar.getTime());
			partLineItem.setQuantity(i % 3);
			partLineItem.setReturnRequiredFlag(true);
			partLineItem.setPartOrderedDate(calendar.getTime());
			partLineItem.setPartReceivedDate(calendar.getTime());
			if (i % 3 == 0) {
				partLineItem.setPartDisposition(partLineItemPartDispositionDOA);
			} else {
				partLineItem
						.setPartDisposition(partLineItemPartDispositionUsed);

				ListOfValues errorCodeOne = new ListOfValues();
				errorCodeOne
						.setType(SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_ERROR_CODE_1
								.getValue());
				errorCodeOne.setValue(ErrorCodeOneEnum.PAPER_FEED_TYPE_TWO
						.getValue());
				partLineItem.setErrorCode1(errorCodeOne);

				ListOfValues errorCodeTwo = new ListOfValues();
				errorCodeTwo
						.setType(SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_ERROR_CODE_2
								.getValue());
				errorCodeTwo.setValue(ErrorCodeTwoEnum.CONTINUOUS_FEED_FIVE
						.getValue());
				partLineItem.setErrorCode2(errorCodeTwo);

			}
			if (Math.random() > 0.5) {
				partLineItem.setReturnRequiredFlag(true);
				if (i == 1) {
					partLineItem.setCarrier(partLineItemPartCarrierDHL);
				} else {
					partLineItem.setCarrier(partLineItemPartCarrierUPS);
				}
				if (partLineItem.getCarrier() != null) {
					partLineItem.setTrackingNumber("trackingNumber_" + i);
				}
			} else {
				partLineItem.setReturnRequiredFlag(false);
			}
			partLineItem.setSerialNumber("SerialNumber " + i);
			partLineItem.setShipDate(calendar.getTime());

			partLineItem.setShipToAdress(getGenericAddress(i));
			partLineItemList.add(partLineItem);
		}

		return partLineItemList;
	}

	private static List<PartLineItem> getPartLineItemList() {
		if (Math.random() < 0.3)
			return null;

		List<PartLineItem> partLineItemList = new ArrayList<PartLineItem>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 1; i < 18; i++) {
			calendar.set(Calendar.YEAR, 2000 + i % 11);
			PartLineItem partLineItem = new PartLineItem();
			partLineItem.setPartLineItemId("id_" + i);
			partLineItem.setPartName("partName " + i);
			partLineItem.setPartNumber("partNumber " + i);
			partLineItem.setRequestedDate(calendar.getTime());
			partLineItem.setQuantity(1);
			partLineItem.setReturnRequiredFlag(true);
			partLineItem.setPartOrderedDate(calendar.getTime());
			partLineItem.setPartReceivedDate(calendar.getTime());
			if (i % 3 == 0) {
				partLineItem.setLineStatus(partLineItemLineStatusShipped);
			} else if (i % 3 == 1) {
				partLineItem.setLineStatus(partLineItemLineStatusReturned);
			} else {
				partLineItem.setLineStatus(partLineItemLineStatusInProcess);
			}
			partLineItem.setSerialNumber("SerialNumber " + i);
			partLineItem.setShipDate(calendar.getTime());

			if (i % 4 == 0) {
				partLineItem.setCarrier(partLineItemPartCarrierDHL);
			} else if (i % 4 == 1) {
				partLineItem.setCarrier(partLineItemPartCarrierFEDEX);
			} else if (i % 4 == 2) {
				partLineItem.setCarrier(partLineItemPartCarrierUPS);
			} else {
				partLineItem.setCarrier(partLineItemPartCarrierYELLOW_FREIGHT);
			}

			partLineItem.setTrackingNumber("trackingNumber " + i);
			partLineItem.setShipToAdress(getGenericAddress(i));
			partLineItemList.add(partLineItem);
		}

		return partLineItemList;
	}

	private static List<AdditionalPaymentRequest> getAddtionalPaymentRequestList() {
		if (Math.random() < 0.3)
			return null;

		List<AdditionalPaymentRequest> addtionalPaymentRequestList = new ArrayList<AdditionalPaymentRequest>();
		for (int i = 1; i < 10; i++) {
			AdditionalPaymentRequest addtionalPaymentRequest = new AdditionalPaymentRequest();
			addtionalPaymentRequest
					.setPaymentRequestId("paymentRequestId " + i);
			addtionalPaymentRequest.setDescription("description " + i);
			if (i % 2 == 0) {
				addtionalPaymentRequest
						.setPaymentType(additionalPaymentTypeOne);
			} else {
				addtionalPaymentRequest
						.setPaymentType(additionalPaymentTypeTwo);
			}
			addtionalPaymentRequest.setQuantity(i);
			addtionalPaymentRequest.setUnitPrice(100);
			addtionalPaymentRequest.setPaymentCurrency("USD");
			addtionalPaymentRequest.setTotalAmount(i * 100);
			addtionalPaymentRequestList.add(addtionalPaymentRequest);
		}
		return addtionalPaymentRequestList;
	}

	private static List<ActivityNote> getActivityNoteList() {
		List<ActivityNote> activityNoteList = new ArrayList<ActivityNote>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 1; i < 18; i++) {
			calendar.set(Calendar.YEAR, 2000 + i % 11);
			ActivityNote activityNote = new ActivityNote();
			activityNote.setNoteAuthor(accountContacts.get(i % 3));
			activityNote.setNoteDate(calendar.getTime());
			if (i % 2 == 0) {
				activityNote.setRepairCompleteFlag(true);
				activityNote.setNoteDetails("noteDetails");
			} else {
				activityNote.setRepairCompleteFlag(false);
				activityNote
						.setNoteDetails("This is noteDetails This is noteDetails  This is noteDetails This is noteDetails This is noteDetails This is noteDetails This is noteDetails This is noteDetails");
			}
			activityNote.setNoteId("NoteId " + i);
			activityNoteList.add(activityNote);
		}
		return activityNoteList;
	}

	private static List<TechnicianInstruction> getServiceInstructionList() {
		List<TechnicianInstruction> technicianInstructionList = new ArrayList<TechnicianInstruction>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 1; i < 5; i++) {
			calendar.set(Calendar.YEAR, 2000 + i % 11);
			TechnicianInstruction TechnicianInstruction = new TechnicianInstruction();
			TechnicianInstruction.setInstructionDate(calendar.getTime());
			String instructionType = "Type";
			TechnicianInstruction.setInstructionType(instructionType + i);
			String actualInstruction = "this is a Technician Instruction introduce";
			TechnicianInstruction.setActualInstruction(actualInstruction + i);
			technicianInstructionList.add(TechnicianInstruction);
		}
		return technicianInstructionList;
	}

	private static List<PartLineItem> getRecommendedPartLineItemList(int index) {
		if (Math.random() < 0.1)
			return null;

		int sum = 3 + (index % 3);
		Random random = new Random();
		List<PartLineItem> partLineItemList = new ArrayList<PartLineItem>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 1; i <= sum; i++) {
			calendar.set(Calendar.YEAR, 2000 + i % 11);
			PartLineItem partLineItem = new PartLineItem();
			partLineItem.setPartLineItemId("id_" + String.valueOf((880 + i)));
			partLineItem.setPartName("partName " + i);
			partLineItem.setPartNumber(String.valueOf((1200 + i)));
			partLineItem.setReturnRequiredFlag(new Random().nextBoolean());
			partLineItem.setRecommendedQuantity((random.nextInt(5)));
			partLineItem.setPartId("partId_" + String.valueOf((880 + i)));
			partLineItemList.add(partLineItem);
		}

		return partLineItemList;
	}

	public static List<LdapUserDataResult> getLdapUserDataResultList() {
		List<LdapUserDataResult> listResult = new ArrayList<LdapUserDataResult>();
		listResult.add(crateUserPartnerOne());
		listResult.add(crateUserPartnerTwo());
		listResult.add(crateUserPartnerThree());
		listResult.add(crateUserPartnerFour());
		return listResult;
	}

	private static LdapUserDataResult crateUserPartnerOne() {
		LdapUserDataResult result = new LdapUserDataResult();
		result.setContactId(PARTNER_ONE_CONTACT_ID);
		result.setMdmId(PARTNER_ONE_MDM_ID);
		result.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
		result.setEmailAddress(PARTNER_ONE_EMAIL_ADDRESS);
		result.setFirstName(PARTNER_FIRST_NAME_LIST.get(0));
		result.setLastName(PARTNER_LAST_NAME_LIST.get(0));
		result.setUserNumber(PARTNER_ONE_USER_NUMBER);
		result.setLanguage(CONTACT_LANGUAGE);
		result.setCountry(CONTACT_COUNTRY);
		result.setWorkPhone(PARTNER_ONE_WORK_PHONE);
		List<String> userRoles = new ArrayList<String>(0);
		userRoles.add(LexmarkConstants.ROLE_SERVICE_TECHNICIAN);
		userRoles.add(LexmarkConstants.ROLE_SERVICE_MANAGER);
		userRoles.add(LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR);
		addPhaseOneRoles(userRoles);
		result.setUserRoles(userRoles);

		result.setUserSegment(USER_SEGMENT_EMPLOYEE);
		return result;
	}

	private static LdapUserDataResult crateUserPartnerTwo() {
		LdapUserDataResult result = new LdapUserDataResult();
		result.setContactId(PARTNER_TWO_CONTACT_ID);
		result.setMdmId(PARTNER_TWO_MDM_ID);
		result.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
		result.setEmailAddress(PARTNER_TWO_EMAIL_ADDRESS);
		result.setFirstName(PARTNER_FIRST_NAME_LIST.get(1));
		result.setLastName(PARTNER_LAST_NAME_LIST.get(1));
		result.setUserNumber(PARTNER_TWO_USER_NUMBER);
		result.setLanguage(CONTACT_LANGUAGE);
		result.setCountry(CONTACT_COUNTRY);
		result.setWorkPhone(PARTNER_TWO_WORK_PHONE);
		List<String> userRoles = new ArrayList<String>(0);
		userRoles.add(LexmarkConstants.ROLE_SERVICE_TECHNICIAN);
		userRoles.add(LexmarkConstants.ROLE_SERVICE_MANAGER);
		userRoles.add(LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR);
		addPhaseOneRoles(userRoles);
		result.setUserRoles(userRoles);

		result.setUserSegment(USER_SEGMENT_PARTNER);
		return result;
	}

	private static LdapUserDataResult crateUserPartnerThree() {
		LdapUserDataResult result = new LdapUserDataResult();
		result.setContactId(PARTNER_THREE_CONTACT_ID);
		result.setMdmId(PARTNER_THREE_MDM_ID);
		result.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
		result.setEmailAddress(PARTNER_THREE_EMAIL_ADDRESS);
		result.setFirstName(PARTNER_FIRST_NAME_LIST.get(2));
		result.setLastName(PARTNER_LAST_NAME_LIST.get(2));
		result.setUserNumber(PARTNER_THREE_USER_NUMBER);
		result.setLanguage(CONTACT_LANGUAGE);
		result.setCountry(CONTACT_COUNTRY);
		result.setWorkPhone(PARTNER_THREE_WORK_PHONE);
		List<String> userRoles = new ArrayList<String>(0);
		userRoles.add(LexmarkConstants.ROLE_SERVICE_TECHNICIAN);
		userRoles.add(LexmarkConstants.ROLE_SERVICE_MANAGER);
		userRoles.add(LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR);
		addPhaseOneRoles(userRoles);
		result.setUserRoles(userRoles);

		result.setUserSegment(USER_SEGMENT_EMPLOYEE);
		return result;
	}

	private static LdapUserDataResult crateUserPartnerFour() {
		LdapUserDataResult result = new LdapUserDataResult();
		result.setContactId(PARTNER_FOUR_CONTACT_ID);
		result.setMdmId(PARTNER_FOUR_MDM_ID);
		result.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
		result.setEmailAddress(PARTNER_FOUR_EMAIL_ADDRESS);
		result.setFirstName(PARTNER_FIRST_NAME_LIST.get(3));
		result.setLastName(PARTNER_LAST_NAME_LIST.get(3));
		result.setUserNumber(PARTNER_FOUR_USER_NUMBER);
		result.setLanguage(CONTACT_LANGUAGE);
		result.setCountry(CONTACT_COUNTRY);
		result.setWorkPhone(PARTNER_FOUR_WORK_PHONE);
		List<String> userRoles = new ArrayList<String>(0);
		userRoles.add(LexmarkConstants.ROLE_SERVICE_TECHNICIAN);
		addPhaseOneRoles(userRoles);
		result.setUserRoles(userRoles);

		result.setUserSegment(USER_SEGMENT_EMPLOYEE);
		return result;
	}

	private static void addPhaseOneRoles(List<String> userRoles) {
		userRoles.add(LexmarkConstants.ROLE_STANDARD_ACCESS);
		userRoles.add(LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT);
		userRoles.add(LexmarkConstants.ROLE_BILLING);
		userRoles.add(LexmarkConstants.ROLE_PUBLISHING);
	}

	private static AccountContact createAccountContactOne() {
		AccountContact contact = new AccountContact();
		contact.setContactId(PARTNER_ONE_CONTACT_ID);
		contact.setFirstName(PARTNER_FIRST_NAME_LIST.get(0));
		contact.setLastName(PARTNER_LAST_NAME_LIST.get(0));
		contact.setCountry(CONTACT_COUNTRY);
		contact.setEmailAddress(PARTNER_ONE_EMAIL_ADDRESS);
		contact.setWorkPhone(PARTNER_ONE_WORK_PHONE);
		contact.setUserFavorite(true);
		contact.setAddress(getGenericAddress(1));
		contact.setContactType("Service Contact");
		return contact;
	}

	private static AccountContact createAccountContactTwo() {
		AccountContact contact = new AccountContact();
		contact.setContactId(PARTNER_TWO_CONTACT_ID);
		contact.setFirstName(PARTNER_FIRST_NAME_LIST.get(1));
		contact.setLastName(PARTNER_LAST_NAME_LIST.get(1));		
		contact.setCountry(CONTACT_COUNTRY);
		contact.setEmailAddress(PARTNER_TWO_EMAIL_ADDRESS);
		contact.setWorkPhone(PARTNER_TWO_WORK_PHONE);
		contact.setUserFavorite(false);
		contact.setAddress(getGenericAddress(2));
		contact.setContactType("Consumable Contact");
		return contact;
	}

	private static AccountContact createAccountContactThree() {
		AccountContact contact = new AccountContact();
		contact.setContactId(PARTNER_THREE_CONTACT_ID);
		contact.setFirstName(PARTNER_FIRST_NAME_LIST.get(2));
		contact.setLastName(PARTNER_LAST_NAME_LIST.get(2));
		contact.setCountry(CONTACT_COUNTRY);
		contact.setEmailAddress(PARTNER_THREE_EMAIL_ADDRESS);
		contact.setWorkPhone(PARTNER_THREE_WORK_PHONE);
		contact.setUserFavorite(true);
		contact.setAddress(getGenericAddress(3));
		contact.setContactType("Service Contact");
		return contact;
	}

	public static Account createPartnerAccountOne() {
		Account account = new Account();
		account.setAccountId(PARTNER_ACCOUNT_ONE_ACCOUNT_ID);
		account.setAccountName(PARTNER_FIRST_NAME_LIST.get(0) + " "
				+ PARTNER_LAST_NAME_LIST.get(0));
		account.setDirectPartnerFlag(true);
		account.setIndirectPartnerFlag(true);
		account.setLogisticsPartnerFlag(true);
		account.setCreateServiceRequestFlag(true);
		account.setCreateShipToAddressFlag("true");
		account.setCreateClaimFlag(true);
		account.setAllowAdditionalPaymentRequestFlag(true);
		account.setOrderPartsFlag(true);
		account.setDefaultCurrency("RMB");

		account.setAddress(getGenericAddress(1));
		account.setAddressStatus(ADDRESS_STATUS_LIST.get(0));
		return account;
	}

	public static Account createPartnerAccountTwo() {
		Account account = new Account();
		account.setAccountId(PARTNER_ACCOUNT_TWO_ACCOUNT_ID);
		account.setAccountName(PARTNER_FIRST_NAME_LIST.get(1) + " "
				+ PARTNER_LAST_NAME_LIST.get(1));
		account.setDirectPartnerFlag(true);
		account.setIndirectPartnerFlag(false);
		account.setLogisticsPartnerFlag(false);
		account.setCreateServiceRequestFlag(false);
		account.setCreateShipToAddressFlag("false");
		account.setCreateClaimFlag(false);
		account.setAllowAdditionalPaymentRequestFlag(false);
		account.setDefaultCurrency("USD");

		account.setAddress(getGenericAddress(2));
		account.setAddressStatus(ADDRESS_STATUS_LIST.get(1));
		return account;
	}

	public static Account createPartnerAccountThree() {
		Account account = new Account();
		account.setAccountId(PARTNER_ACCOUNT_THREE_ACCOUNT_ID);
		account.setAccountName(PARTNER_FIRST_NAME_LIST.get(2) + " "
				+ PARTNER_LAST_NAME_LIST.get(2));
		account.setDirectPartnerFlag(false);
		account.setIndirectPartnerFlag(true);
		account.setLogisticsPartnerFlag(true);
		account.setCreateServiceRequestFlag(true);
		account.setCreateShipToAddressFlag("true");
		account.setCreateClaimFlag(true);
		account.setAllowAdditionalPaymentRequestFlag(true);
		account.setOrderPartsFlag(true);
		account.setDefaultCurrency("EUR");

		account.setAddress(getGenericAddress(3));
		account.setAddressStatus(ADDRESS_STATUS_LIST.get(2));
		return account;
	}

	public static Account createPartnerAccountFour() {
		Account account = new Account();
		account.setAccountId(PARTNER_ACCOUNT_FOUR_ACCOUNT_ID);
		account.setAccountName(PARTNER_FIRST_NAME_LIST.get(3) + " "
				+ PARTNER_LAST_NAME_LIST.get(3));
		account.setDirectPartnerFlag(false);
		account.setIndirectPartnerFlag(false);
		account.setLogisticsPartnerFlag(true);
		account.setCreateServiceRequestFlag(false);
		account.setCreateShipToAddressFlag("false");
		account.setCreateClaimFlag(false);
		account.setAllowAdditionalPaymentRequestFlag(false);
		account.setDefaultCurrency("USD");

		account.setAddress(getGenericAddress(4));
		account.setAddressStatus(ADDRESS_STATUS_LIST.get(3));
		return account;
	}

	public static Account createPartnerAccountFive() {
		Account account = new Account();
		account.setAccountId(PARTNER_ACCOUNT_FIVE_ACCOUNT_ID);
		account.setAccountName(PARTNER_FIRST_NAME_LIST.get(4) + " "
				+ PARTNER_LAST_NAME_LIST.get(4));
		account.setDirectPartnerFlag(true);
		account.setIndirectPartnerFlag(false);
		account.setLogisticsPartnerFlag(true);
		account.setCreateServiceRequestFlag(false);
		account.setCreateShipToAddressFlag("true");
		account.setCreateClaimFlag(false);
		account.setAllowAdditionalPaymentRequestFlag(false);
		account.setDefaultCurrency("RMB");

		account.setAddress(getGenericAddress(5));
		account.setAddressStatus(ADDRESS_STATUS_LIST.get(3));
		return account;
	}

	public static Payment getPaymentsDetail(int id) {
		Payment payment = new Payment();
		payment.setCheckNumber("110" + id % 10 + "03" + id % 10);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				- id % 40);
		payment.setDateCreated(calendar.getTime());

		payment.setPartnerAccount(getAccount(id));
		payment.setPartnerAgreement("Tolt Walmart " + id);
		payment.setPayableToName("Mark C " + id);
		payment.setPaymentId(String.valueOf(id));
		payment.setPaymentNumber("003" + id % 10 + "09" + id % 10);
		payment.setPaymentStatus(getPaymentStatus(id));
		payment.setPaymentTotal(5000 - (id % 10) * 100);
		payment.setProviderReferenceNumber("323" + id % 10 + "09" + id % 10);

		return payment;
	}

	public static List<Activity> getPaymentLineItemList(int paymentId) {
		List<Activity> activitiesByPayment = new ArrayList<Activity>();
		for (Activity activity : activities) {
			if (String.valueOf(paymentId).equals(
					activity.getPayment().getPaymentId())) {
				activitiesByPayment.add(activity);
			}
		}
		return activitiesByPayment;
	}

	private static ListOfValues createListOfValues(String type, String value) {
		ListOfValues listOfValues = new ListOfValues();
		listOfValues.setType(type);
		listOfValues.setValue(value);
		return listOfValues;
	}

	public static List<PartLineItem> getPartLineItemListWithoutRandom() {
		List<PartLineItem> partLineItemList = new ArrayList<PartLineItem>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 1; i < 18; i++) {
			calendar.set(Calendar.YEAR, 2000 + i % 11);
			PartLineItem partLineItem = new PartLineItem();
			partLineItem.setPartLineItemId("id_" + i);
			partLineItem.setPartName("partName " + i);
			partLineItem.setPartNumber("partNumber " + i);
			partLineItem.setRequestedDate(calendar.getTime());
			partLineItem.setQuantity(1);
			partLineItem.setReturnRequiredFlag(true);
			partLineItem.setPartOrderedDate(calendar.getTime());
			partLineItem.setPartReceivedDate(calendar.getTime());
			if (i % 3 == 0) {
				partLineItem.setLineStatus(partLineItemLineStatusShipped);
			} else if (i % 3 == 1) {
				partLineItem.setLineStatus(partLineItemLineStatusReturned);
			} else {
				partLineItem.setLineStatus(partLineItemLineStatusInProcess);
			}
			partLineItem.setSerialNumber("SerialNumber " + i);
			partLineItem.setShipDate(calendar.getTime());

			if (i % 4 == 0) {
				partLineItem.setCarrier(partLineItemPartCarrierDHL);
			} else if (i % 4 == 1) {
				partLineItem.setCarrier(partLineItemPartCarrierFEDEX);
			} else if (i % 4 == 2) {
				partLineItem.setCarrier(partLineItemPartCarrierUPS);
			} else {
				partLineItem.setCarrier(partLineItemPartCarrierYELLOW_FREIGHT);
			}

			partLineItem.setTrackingNumber("trackingNumber " + i);
			partLineItem.setShipToAdress(getGenericAddress(i));
			partLineItemList.add(partLineItem);
		}

		return partLineItemList;
	}

	public static List<TechnicianInstruction> getServiceInstructionListForTesting() {
		return getServiceInstructionList();
	}

	public static List<PartLineItem> getPartDebriefListForTesting() {
		return getPartDebriefList();
	}

	public static List<AdditionalPaymentRequest> getAddtionalPaymentRequestListForTesting() {
		List<AdditionalPaymentRequest> addtionalPaymentRequestList = new ArrayList<AdditionalPaymentRequest>();
		for (int i = 1; i < 10; i++) {
			AdditionalPaymentRequest addtionalPaymentRequest = new AdditionalPaymentRequest();
			addtionalPaymentRequest
					.setPaymentRequestId("paymentRequestId " + i);
			addtionalPaymentRequest.setDescription("description " + i);
			if (i % 2 == 0) {
				addtionalPaymentRequest
						.setPaymentType(additionalPaymentTypeOne);
			} else {
				addtionalPaymentRequest
						.setPaymentType(additionalPaymentTypeTwo);
			}
			addtionalPaymentRequest.setQuantity(i);
			addtionalPaymentRequest.setUnitPrice(100);
			addtionalPaymentRequest.setPaymentCurrency("USD");
			addtionalPaymentRequest.setTotalAmount(i * 100);
			addtionalPaymentRequestList.add(addtionalPaymentRequest);
		}
		return addtionalPaymentRequestList;
	}

	public static List<ActivityNote> getActivityNoteListForTesting() {
		return getActivityNoteList();
	}

	public static List<PartLineItem> getRecommendedPartLineItemListForTesting() {
		int sum = 10;
		Random random = new Random();
		List<PartLineItem> partLineItemList = new ArrayList<PartLineItem>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 1; i <= sum; i++) {
			calendar.set(Calendar.YEAR, 2000 + i % 11);
			PartLineItem partLineItem = new PartLineItem();
			partLineItem.setPartLineItemId("id_" + String.valueOf((880 + i)));
			partLineItem.setPartName("partName " + i);
			partLineItem.setPartNumber(String.valueOf((1200 + i)));
			partLineItem.setReturnRequiredFlag(sum % 2 == 0);
			partLineItem.setRecommendedQuantity((random.nextInt(5)));
			partLineItem.setPartId("partId_" + String.valueOf((880 + i)));
			partLineItemList.add(partLineItem);
		}

		return partLineItemList;
	}

	public static List<ServiceRequestActivity> getServiceRequestActivityListForTesting() {
		return getWebUpdateActivities(1);
	}

	public static List<BulkUploadStatus> getBulkUploadStatusListForTesting() {
		List<BulkUploadStatus> list = new ArrayList<BulkUploadStatus>();
		BulkUploadStatus bulkUploadStatus = null;
		for (int i = 0; i < 10; i++) {
			bulkUploadStatus = new BulkUploadStatus();
			bulkUploadStatus.setAttachmentName("attachmentName");
			bulkUploadStatus.setComment("comment");
			bulkUploadStatus.setCompletedOn("completedOn");
			bulkUploadStatus.setSize(5);
			bulkUploadStatus.setStatus("status");
			bulkUploadStatus.setSubmittedOn("submittedOn");
			bulkUploadStatus.setUploadFileType("uploadFileType");
			list.add(bulkUploadStatus);
		}
		return list;
	}

	public static Debrief getDebriefForTesting() {
		return getDebrief(0);
	}
	
	/*Added by MPS Offshore team for AR CR implementation*/
	
	public static List<SRDetail> getSRDetailsList() {
		List<SRDetail> srDetail = new ArrayList<SRDetail>();
		for (int i = 0; i < 5; i++) {
			srDetail.add(getSRDetails(i));
		}
		return srDetail;
	}
	
	
	private static SRDetail getSRDetails(int i) {
		
		SRDetail srDetail = new SRDetail();
		srDetail.setAmount(100 + Math.round(Math.random()) + i);
		srDetail.setFulfillmentFee(200 + Math.round(Math.random()) + i);
		srDetail.setPartFee(300 + Math.round(Math.random()) + i);
		
		srDetail.setPartNo("ABCD - " + String.valueOf(i));
		srDetail.setQty(i);
		srDetail.setSuppliesDesc("Supplies Desc -" + String.valueOf(i));
		
		return srDetail;		
	}

	/*Added by MPS Offshore team for AP CR implementation*/
	
	public static List<AccountPayable> getAccountsPayableList() {
		List<AccountPayable> accounts = new ArrayList<AccountPayable>();
		for (int i = 0; i < 5; i++) {
			accounts.add(getAccountsPayable(i));
		}
		return accounts;
	}
	
	/*Added by MPS Offshore team for AR CR implementation*/	
	public static List<AccountPayable> getAccountsRecivblList() {
		List<AccountPayable> accRecvbl = new ArrayList<AccountPayable>();
		for (int i = 0; i < 5; i++) {
			accRecvbl.add(getAccountsRecivable(i));
		}
		return accRecvbl;
	}
	
	/**
	 * This method is used for accounts recievable mock data
	 * @param i
	 * @return
	 */
	private static AccountPayable getAccountsRecivable(int i)
	{
		AccountPayable accountsRecvbl = new AccountPayable();
// 2012-05-10 vpetruchok: 	AccountPayable fields have been changed accordingly to the "Integrations_PartnerPayments_ARAP" document 	
		accountsRecvbl.setAccountName(getVendorNames(i));
		accountsRecvbl.setBillTo(String.valueOf(i)+ "-ABCDEXYZ");
		accountsRecvbl.setSoldTo("ZEWGHE-" + String.valueOf(i));
		return accountsRecvbl;
	}
	
	public static AccountPayable getAccountsPayable(int i){		
		AccountPayable accountsPayble = new AccountPayable();
		accountsPayble.setVendorId(String.valueOf(i)+"-ABCDEXYZ");
		accountsPayble.setVendorName(getVendorNames(i));
		accountsPayble.setCountry(getCountry(i));		
		if (i%2 == 0)
			accountsPayble.setCompanyCode("COMPANY123");
		else{
			accountsPayble.setCompanyCode("COMPANY456");
		}
		accountsPayble.setPayeeName(getPayeeName(i));
		accountsPayble.setPayeeAddress(getGenericAddress(i));
		return accountsPayble;
	}
	
	public static String getVendorNames(int i) {
		String vendorName;
		String value[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		int j=0;
		vendorName = value[i]+value[j];
		return vendorName;
	}
	
	public static String getPayeeName(int i) {
		String payeeName;
		String value[]={"Micheal Jordan","Jay Michaels","Adam Moore","Dammy Michaels","Sam Terrell"};
		payeeName = value[i];
		return payeeName;
	}
	
	public static String getCountry(int i) {
		String country = null;
		switch (i % 9) {
		case 0:
			country = "USA";
			break;
		case 1:
			country = "Canada";
			break;
		case 2:
			country = "Brasil";
			break;
		case 3:
			country = "France";
			break;
		case 4:
			country = "Canada";
			break;
		case 5:
			country = "USA";
			break;
		case 6:
			country = "Spain";
			break;
		case 7:
			country = "Mexico";
			break;
		case 8:
			country = "Germany";
			break;
		}
		return country;
	}
	
	public static List<PageCounts> getPageCounts(){
		List<PageCounts> pagecounts=new ArrayList<PageCounts>();
		String[] type={"MONO","COLOR","BLACK","CYAN","MONO"};
		String[] count={"12","42","15","19","20"};
		for(int i=0;i<5;i++){
			PageCounts pc=new PageCounts();
			pc.setType(type[i]);
			pc.setCount(count[i]);
			pagecounts.add(pc);
		}
		return pagecounts;
	}
	
	public static List<Attachment> getAttachments(){
		List<Attachment> attachments=new ArrayList<Attachment>();
		String[] name={"Joyeeta.doc","Suman.doc","Subarata.docx","arup.html","tanmoy.dat"};
		int[] size={12,42,15,19,20};
		for(int i=0;i<5;i++){
			Attachment ac=new Attachment();
			ac.setAttachmentName(name[i]);
			ac.setSize(size[i]);
			attachments.add(ac);
			
		}
		return attachments;
	}
	
	/*End*/
}
