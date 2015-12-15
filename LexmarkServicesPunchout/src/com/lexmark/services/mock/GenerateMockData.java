package com.lexmark.services.mock;

import com.lexmark.domain.Asset;
import com.lexmark.result.RequestListResult;
import com.lexmark.result.RequestResult;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.Bundle;
import com.lexmark.domain.EntitlementServiceDetail;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.domain.ServiceRequestOrderLineItem;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenerateMockData {

	/**
	 * @return List 
	 */
	public List<Part> generatePartList() {
		Part parts = new Part();
		List<Part> partList = new ArrayList<Part>();
		for (int i = 1; i <= 3; i++) {

			parts.setPartNumber("3553144");
			parts.setDescription("Description Goes Here");
			parts.setOrderQuantity("1");
			partList.add(parts);
		}
		return partList;

	}

	/**
	 * @return List 
	 */
	public List<OrderPart> generateWarrantiesList() {
		
		List<OrderPart> partList = new ArrayList<OrderPart>();
		for (int i = 1; i <= 6; i++) {
			OrderPart part = new OrderPart();
			part.setPartNumber("40G0802");
			part.setPrice(new BigDecimal(200));
			part.setDescription("550-Sheet Tray");

			partList.add(part);
		}
		return partList;

	}

	/**
	 * @return List 
	 */
	public List<Bundle> generateBundle() {

		List<Bundle> bundleList = new ArrayList<Bundle>();
		for (int i = 1; i <= 3; i++) {
			Bundle bundle = new Bundle();
			bundle.setBundleId("bundleId" + i);
			bundle.setBundleName("Mono Laser Printer");
			bundle.setPrice(new BigDecimal(300));
			bundle.setDescription("The Lexmark MS410dn provides more enterprise-worthy speed, reliability and convenience. Enhance productivity and save time with 2-sided printing and networking, too.");
			bundle.setPartList(generatePartList());

			bundleList.add(bundle);

		}
		return bundleList;

	}

	/**
	 * @return List 
	 */
	public List<OrderPart> generateSuppliesProductList() {

		//List<Bundle> bundleList = new ArrayList<Bundle>();
        
		List<OrderPart> parts = new ArrayList<OrderPart>();
		for(int i=0;i<=2;i++){
			OrderPart part= new OrderPart();
			part.setPartNumber("123456");
			part.setModel("Hi FI model");
			part.setDescription("Best In The world");
			parts.add(part);
		}
		/*Bundle bundle = new Bundle();

		bundle.setBundleName("Mono Laser Printer");
		bundle.setBundleId("MS410DN");
		bundle.setPrice(new BigDecimal(300));
		bundle.setDescription("The Lexmark MS410dn provides more enterprise-worthy speed, reliability and convenience. Enhance productivity and save time with 2-sided printing and networking, too.");*/
		
		return parts;

	}

	/**
	 * @return List 
	 */
	public List<ListOfValues> generatePrintersList() {

		
		List<ListOfValues> lovs=new ArrayList<ListOfValues>();
		for (int i = 1; i <= 5; i++) {
			ListOfValues lov = new ListOfValues();
			lov.setName("productType");
			lov.setValue("Laser");
			lovs.add(lov);
		}
		return lovs;

	}

	/**
	 * @return RequestListResult 
	 */
	public RequestListResult generateRequestListResult() {
		RequestListResult reqListResult = new RequestListResult();
		List<ServiceRequest> generateHistoryGridList = generateHistoryGridList();
		reqListResult.setRequestList(generateHistoryGridList);
		reqListResult.setTotalCount(5);
		return reqListResult;
	}

	/**
	 * @return List 
	 */
	public List<ServiceRequest> generateHistoryGridList() {
		List<ServiceRequest> generateHistoryGridList = new ArrayList<ServiceRequest>();
		for (int i = 0; i <= 4; i++) {
			ServiceRequest serviceRequest = new ServiceRequest();
			serviceRequest.setServiceRequestNumber("123456789");
			String dateString = "2014/03/09";
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
			Date convertedDate = null;
			try {
				convertedDate = dateFormat.parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
		
				
			}
			serviceRequest.setServiceRequestDate(convertedDate);
			serviceRequest.setRequestType("Hardware");
			
			ListOfValues LOV = new ListOfValues();
			serviceRequest.setArea(LOV);
			serviceRequest.setServiceRequestStatus("Completed");
			serviceRequest.setAccountCountry("account Country");
			serviceRequest.setAccountId("accountId");
			serviceRequest.setAccountName("accountName");
			List<ServiceRequestActivity> webUpdateActivity = new ArrayList<ServiceRequestActivity>();
			serviceRequest.setActivitywebUpdateActivities(webUpdateActivity);
			serviceRequest.setActualEndDate(convertedDate);
			serviceRequest.setActualStartDate(convertedDate);
			serviceRequest.setAddtnlDescription("addtnlDescription");
			Asset asset = new Asset();
			serviceRequest.setAsset(asset);
			serviceRequest.setAssetShipped(asset);
			List<Attachment> attachments = new ArrayList<Attachment>();
			serviceRequest.setAttachments(attachments);
			serviceRequest.setBillingModel("billingModel");
			List<Part> cancelledParts = new ArrayList<Part>();
			serviceRequest.setCancelledParts(cancelledParts);
			serviceRequest.setComments("comments");
			List<AccountContact> contactInfoForDevice = new ArrayList<AccountContact>();
			serviceRequest.setContactInfoForDevice(contactInfoForDevice);
			serviceRequest.setContractType("contractType");
			serviceRequest.setCostCenter("costCenter");
			serviceRequest.setCoveredService("coveredService");
			serviceRequest.setCreditCardToken("creditCardToken");
			serviceRequest.setCreditCardType("creditCardType");
			serviceRequest.setCreditNumberFlag(true);
			serviceRequest.setCurrency("currency");
			serviceRequest.setCustomerReferenceId("customerReferenceId");
			serviceRequest
					.setCustomerReferenceNumber("customerReferenceNumber");
			serviceRequest.setCustomerRequestedResponseDate(convertedDate);
			serviceRequest.setCustRefNumber("custRefNumber");
			serviceRequest
					.setDefaultSpecialInstructions("defaultSpecialInstructions");

			serviceRequest.setEmailActivities(webUpdateActivity);
			serviceRequest.setEta("eta");
			serviceRequest.setExpediteOrder(true);
			serviceRequest
					.setHelpdeskReferenceNumber("helpdeskReferenceNumber");
			serviceRequest.setId("id");
			GenericAddress installAddress = new GenericAddress();
			serviceRequest.setInstallAddress(installAddress);
			BigDecimal itemSubTotalBeforeTax = new BigDecimal(10);
			serviceRequest.setItemSubTotalBeforeTax(itemSubTotalBeforeTax);
			AccountContact accountContact = new AccountContact();
			serviceRequest.setMassUploadContact(accountContact);
			serviceRequest
					.setMassUploadDebriefStatus("massUploadDebriefStatus");
			serviceRequest.setMassUploadserviceProviderReferenceNum(10);
			serviceRequest
					.setMassUploadTechnicianName("massUploadTechnicianName");
			serviceRequest.setNotes("notes");
			serviceRequest
					.setOptionExchangeOtherDescription("optionExchangeOtherDescription");
			serviceRequest.setOrderSource("orderSource");
			serviceRequest.setOtherRequestedService("otherRequestedService");
			List<PageCounts> pageCountList = new ArrayList<PageCounts>();
			serviceRequest.setPageCounts(pageCountList);
			serviceRequest.setParts(cancelledParts);
			List<ServiceRequestOrderLineItem> pendingShipments = new ArrayList<ServiceRequestOrderLineItem>();
			serviceRequest.setPendingShipments(pendingShipments);
			serviceRequest.setPoNumber("poNumber");
			serviceRequest.setPoNumberFlag(true);
			serviceRequest.setPrimaryContact(accountContact);
			serviceRequest.setPrimarySuppliesContact(accountContact);
			serviceRequest.setProblemDescription("problemDescription");
			serviceRequest.setProjectName("projectName");
			serviceRequest.setProjectPhase("projectPhase");
			serviceRequest.setReferenceNumber("referenceNumber");
			serviceRequest.setRequestedEffectiveDate(convertedDate);
			serviceRequest.setRequestor(accountContact);
			serviceRequest.setResolutionCode("resolutionCode");
			serviceRequest.setResolveWithin(convertedDate);
			serviceRequest.setRespondWithin(dateString);
			serviceRequest.setResponseMetric("responseMetric");
			serviceRequest.setReturnOrderLines(pendingShipments);
			serviceRequest.setSecondaryContact(accountContact);
			serviceRequest.setSecondarySuppliesContact(accountContact);
			List<EntitlementServiceDetail> selectedServiceDetails = new ArrayList<EntitlementServiceDetail>();
			serviceRequest.setSelectedServiceDetails(selectedServiceDetails);
			serviceRequest.setServiceActivityStatus("serviceActivityStatus");
			serviceRequest.setServiceAddress(installAddress);
			serviceRequest.setServiceRequestDate(convertedDate);
			serviceRequest.setServiceRequestEndDate(convertedDate);
			serviceRequest.setServiceRequestETA(dateString);
			serviceRequest.setServiceRequestor("serviceRequestor");
			serviceRequest.setServiceRequestSLA("serviceRequestSLA");
			serviceRequest.setServiceRequestStatus("serviceRequestStatus");
			serviceRequest.setServiceRequestStatusDate(convertedDate);
			ListOfValues serviceRequestType = new ListOfValues();
			serviceRequest.setServiceRequestType(serviceRequestType);
			serviceRequest.setServicewebUpdateActivities(webUpdateActivity);
			serviceRequest.setShipmentOrderLines(pendingShipments);
			serviceRequest.setSoldToNumber("soldToNumber");
			serviceRequest.setSpecialInstructions("specialInstructions");
			serviceRequest.setStatusDetail("statusDetail");
			serviceRequest.setStatusType(serviceRequestType);
			serviceRequest.setSubArea(serviceRequestType);
			serviceRequest.setTax(itemSubTotalBeforeTax);
			serviceRequest.setTechnicianContact(accountContact);
			serviceRequest.setTotalAmount(itemSubTotalBeforeTax);
			serviceRequest.setTravelDistance("travelDistance");
			serviceRequest
					.setTravelDistanceUnitOfMeasure("travelDistanceUnitOfMeasure");
			serviceRequest.setTravelDuration("travelDuration");
			serviceRequest.setWebOrderNumber("webOrderNumber");

			generateHistoryGridList.add(serviceRequest);
			
		}
		return generateHistoryGridList;
	}

	/**
	 * @return List 
	 */
	public List<ListOfValues> generateAreaList() {
		List<ListOfValues> areaList = new ArrayList<ListOfValues>();
		for (int i = 0; i <= 5; i++) {
			ListOfValues LOV = new ListOfValues();
			LOV.setValue("BAU");
			LOV.setId("123");
			LOV.setLanguage("English");
			LOV.setLanguageName("Eng");
			LOV.setName("abc");
			LOV.setType("BAU");
			areaList.add(LOV);
		}
		
		return areaList;
	}

	/**
	 * @return RequestResult 
	 */
	public RequestResult generateRequestDetails() {
		RequestResult requestResult = new RequestResult();
		ServiceRequest serviceRequest = new ServiceRequest();

		serviceRequest.setServiceRequestNumber("123456789");
		String dateString = "2014/03/09";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
		Date convertedDate = null;
		try {
			convertedDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			}
		serviceRequest.setServiceRequestDate(convertedDate);
		serviceRequest.setRequestType("Hardware");
		ListOfValues LOV = new ListOfValues();
		LOV.setValue("BAU");
		serviceRequest.setArea(LOV);
		serviceRequest.setServiceRequestStatus("Completed");
		serviceRequest.setAccountCountry("account Country");
		serviceRequest.setAccountId("accountId");
		serviceRequest.setAccountName("accountName");
		List<ServiceRequestActivity> webUpdateActivity = new ArrayList<ServiceRequestActivity>();
		serviceRequest.setActivitywebUpdateActivities(webUpdateActivity);
		serviceRequest.setActualEndDate(convertedDate);
		serviceRequest.setActualStartDate(convertedDate);
		serviceRequest.setAddtnlDescription("addtnlDescription");
		Asset asset = new Asset();
		asset.setActivityNumber("26543");
		asset.setSerialNumber("12364585");
		asset.setProductTLI("JDS436");
		asset.setInstallDate(convertedDate);
		asset.setIpAddress("10.148.65.67");
		asset.setHostName("host Name");
		asset.setAssetTag("WT363");
		GenericAddress installAddress = new GenericAddress();
		installAddress.setStoreFrontName("ATY AJK");
		installAddress.setAddressLine1("740 W New Circle Road");
		installAddress.setCity("Lexington");
		installAddress.setPostalCode("40554");
		asset.setInstallAddress(installAddress);

		serviceRequest.setAsset(asset);
		serviceRequest.setAssetShipped(asset);
		List<Attachment> attachments = new ArrayList<Attachment>();
		serviceRequest.setAttachments(attachments);
		serviceRequest.setBillingModel("billingModel");
		List<Part> cancelledParts = new ArrayList<Part>();
		serviceRequest.setCancelledParts(cancelledParts);
		serviceRequest.setComments("comments");
		List<AccountContact> contactInfoForDevice = new ArrayList<AccountContact>();
		serviceRequest.setContactInfoForDevice(contactInfoForDevice);
		serviceRequest.setContractType("contractType");
		serviceRequest.setCostCenter("costCenter");
		serviceRequest.setCoveredService("coveredService");
		serviceRequest.setCreditCardToken("creditCardToken");
		serviceRequest.setCreditCardType("creditCardType");
		serviceRequest.setCreditNumberFlag(true);
		serviceRequest.setCurrency("currency");
		serviceRequest.setCustomerReferenceId("customerReferenceId");
		serviceRequest.setCustomerReferenceNumber("customerReferenceNumber");
		serviceRequest.setCustomerRequestedResponseDate(convertedDate);
		serviceRequest.setCustRefNumber("custRefNumber");
		serviceRequest
				.setDefaultSpecialInstructions("defaultSpecialInstructions");

		serviceRequest.setEmailActivities(webUpdateActivity);
		serviceRequest.setEta("eta");
		serviceRequest.setExpediteOrder(true);
		serviceRequest.setHelpdeskReferenceNumber("helpdeskReferenceNumber");
		serviceRequest.setId("id");

		serviceRequest.setInstallAddress(installAddress);
		BigDecimal itemSubTotalBeforeTax = new BigDecimal(10);
		serviceRequest.setItemSubTotalBeforeTax(itemSubTotalBeforeTax);
		AccountContact accountContact = new AccountContact();
		accountContact.setFirstName("Test FName");
		accountContact.setLastName("Test LName");
		accountContact.setWorkPhone("8954664558");
		accountContact.setEmailAddress("test@gmail.com");

		serviceRequest.setMassUploadContact(accountContact);
		serviceRequest.setMassUploadDebriefStatus("massUploadDebriefStatus");
		serviceRequest.setMassUploadserviceProviderReferenceNum(10);
		serviceRequest.setMassUploadTechnicianName("massUploadTechnicianName");
		serviceRequest.setNotes("notes");
		serviceRequest
				.setOptionExchangeOtherDescription("optionExchangeOtherDescription");
		serviceRequest.setOrderSource("orderSource");
		serviceRequest.setOtherRequestedService("otherRequestedService");
		List<PageCounts> pageCountList = new ArrayList<PageCounts>();
		serviceRequest.setPageCounts(pageCountList);
		serviceRequest.setParts(cancelledParts);
		List<ServiceRequestOrderLineItem> pendingShipments = new ArrayList<ServiceRequestOrderLineItem>();
		serviceRequest.setPendingShipments(pendingShipments);
		serviceRequest.setPoNumber("poNumber");
		serviceRequest.setPoNumberFlag(true);
		serviceRequest.setPrimaryContact(accountContact);
		serviceRequest.setPrimarySuppliesContact(accountContact);
		serviceRequest.setProblemDescription("problemDescription");
		serviceRequest.setProjectName("projectName");
		serviceRequest.setProjectPhase("projectPhase");
		serviceRequest.setReferenceNumber("referenceNumber");
		serviceRequest.setRequestedEffectiveDate(convertedDate);
		serviceRequest.setRequestor(accountContact);
		serviceRequest.setResolutionCode("resolutionCode");
		serviceRequest.setResolveWithin(convertedDate);
		serviceRequest.setRespondWithin(dateString);
		serviceRequest.setResponseMetric("responseMetric");
		serviceRequest.setReturnOrderLines(pendingShipments);
		serviceRequest.setSecondaryContact(accountContact);
		serviceRequest.setSecondarySuppliesContact(accountContact);
		List<EntitlementServiceDetail> selectedServiceDetails = new ArrayList<EntitlementServiceDetail>();
		serviceRequest.setSelectedServiceDetails(selectedServiceDetails);
		serviceRequest.setServiceActivityStatus("serviceActivityStatus");
		serviceRequest.setServiceAddress(installAddress);
		serviceRequest.setServiceRequestDate(convertedDate);
		serviceRequest.setServiceRequestEndDate(convertedDate);
		serviceRequest.setServiceRequestETA(dateString);
		serviceRequest.setServiceRequestor("serviceRequestor");
		serviceRequest.setServiceRequestSLA("serviceRequestSLA");
		serviceRequest.setServiceRequestStatus("serviceRequestStatus");
		serviceRequest.setServiceRequestStatusDate(convertedDate);
		ListOfValues serviceRequestType = new ListOfValues();
		serviceRequest.setServiceRequestType(serviceRequestType);
		serviceRequest.setServicewebUpdateActivities(webUpdateActivity);
		serviceRequest.setShipmentOrderLines(pendingShipments);
		serviceRequest.setSoldToNumber("soldToNumber");
		serviceRequest.setSpecialInstructions("specialInstructions");
		serviceRequest.setStatusDetail("statusDetail");
		serviceRequest.setStatusType(serviceRequestType);
		serviceRequest.setSubArea(serviceRequestType);
		serviceRequest.setTax(itemSubTotalBeforeTax);
		serviceRequest.setTechnicianContact(accountContact);
		serviceRequest.setTotalAmount(itemSubTotalBeforeTax);
		serviceRequest.setTravelDistance("travelDistance");
		serviceRequest
				.setTravelDistanceUnitOfMeasure("travelDistanceUnitOfMeasure");
		serviceRequest.setTravelDuration("travelDuration");
		serviceRequest.setWebOrderNumber("webOrderNumber");
		requestResult.setServiceRequest(serviceRequest);
		return requestResult;
	}
}
