package com.lexmark.service.impl.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.b.ac;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.AssetIdentifier;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.Entitlement;
import com.lexmark.domain.EntitlementServiceDetail;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.domain.ServiceRequestOrderLineItem;

public class MockDataGeneratorForAllRequestHistory {
	
	public static List<ServiceRequest> settingValues(){
		List<ServiceRequest> servList = new ArrayList<ServiceRequest>();
		
		Boolean b = true;
		
		
		
		
		ServiceRequest sr = new ServiceRequest();
		Date dt = new Date();
		
		
		
		
		
		
		Asset asset = new Asset();
		asset.setAssetId("assetId");
		asset.setSerialNumber("assetSerialNo");
		asset.setAssetTag("assetTag");
		asset.setModelNumber("modelNumber");
		asset.setIpAddress("ipAddress");
		
		GenericAddress installAddress = new GenericAddress();
		installAddress.setAddressId("addressId");
		installAddress.setAddressName("addressName");
		installAddress.setAddressLine1("addressLine1");
		installAddress.setAddressLine2("addressLine2");
		installAddress.setAddressLine3("addressLine3");
		installAddress.setAddressLine4("addressLine4");
		installAddress.setCity("city");
		installAddress.setState("state");
		installAddress.setProvince("province");
		installAddress.setPostalCode("postalCode");
		installAddress.setCountry("country");
		installAddress.setStoreFrontName("storeFrontName");
		installAddress.setNewAddressFlag("newAddressFlag");
		installAddress.setUserFavorite(b);
		installAddress.setStateProvince("stateProvince");
		installAddress.setPhysicalLocation1("physicalLocation1");
		installAddress.setPhysicalLocation2("physicalLocation2");
		installAddress.setPhysicalLocation3("physicalLocation3");
		installAddress.setErrorMsgForCleansing("errorMsgForCleansing");
		
		asset.setInstallAddress(installAddress);
		asset.setConsumableAddress(installAddress);
		asset.setPickupAddress(installAddress);
		asset.setUserFavoriteFlag(b);
		
		Entitlement en = new Entitlement();
		en.setEntitlementId("entitlementId");
		en.setEntitlementName("entitlementName");
		List<EntitlementServiceDetail> ensrlist = new ArrayList<EntitlementServiceDetail>();
		EntitlementServiceDetail ensr = new EntitlementServiceDetail();
		ensr.setServiceDetailId("serviceDetailId");
		ensr.setServiceDetailDescription("serviceDetailDescription");
		ensr.setSiebelValue("siebelValue");
		ensrlist.add(0, ensr);
		en.setServiceDetails(ensrlist);
		asset.setEntitlement(en);
		
		asset.setAssetType("assetType");
		asset.setSupportUrl("supportUrl");
		asset.setControlPanelURL("controlPanelURL");
		asset.setDownloadsUrl("downloadsUrl");
		asset.setHostName("hostName");
		asset.setDeviceTag("deviceTag");
		
		AccountContact assetContact = new AccountContact();
		assetContact.setContactId("contactId");
		assetContact.setDepartment("department");
		assetContact.setWorkPhone("workPhone");
		assetContact.setEmailAddress("emailAddress");
		assetContact.setUserFavorite(b);
		assetContact.setFirstName("firstName");
		assetContact.setLastName("lastName");
		assetContact.setUpdateContactFlag(b);
		assetContact.setNewContactFlag(b);
		assetContact.setShortId("shortId");
		assetContact.setAlternatePhone("alternatePhone");
		assetContact.setMiddleName("middleName");
		assetContact.setAddress(installAddress);
		asset.setAssetContact(assetContact);
		asset.setConsumableContact(assetContact);
		asset.setNotMyPrinter(b);
		asset.setProductTLI("productTLI");
		asset.setProductImageURL("productImageURL");
		asset.setDeviceName("deviceName");
		asset.setPhysicalLocationAddress(installAddress);
		asset.setProductLine("productLine");
		asset.setMeterReadDate(dt);
		asset.setLastPageCount("lastPageCount");
		asset.setNewPageCount("newPageCount");
		asset.setLastColorPageCount("lastColorPageCount");
		asset.setNewColorPageCount("newColorPageCount");
		asset.setColorCapableFlag(b);
		
		Account account = new Account();
		account.setAccountId("accountId");
		account.setAccountName("accountName");
		account.setAddress(installAddress);
		account.setManualMeterRead("manualMeterRead");
		account.setCreateServiceRequest("createServiceRequest");
		account.setUserConsumables("userConsumables");
		
		AssetIdentifier primaryAssetIdentifier = new AssetIdentifier();
		primaryAssetIdentifier.setAssetIdentifierId("assetIdentifierId");
		primaryAssetIdentifier.setAssetIdentifierName("assetIdentifierName");
		account.setPrimaryAssetIdentifier(primaryAssetIdentifier);
		
		account.setAccountType("accountType");
		account.setOrderPartsFlag(b);
		account.setCreateShipToAddressFlag("createShipToAddressFlag");
		account.setCreateClaimFlag(b);
		account.setAllowAdditionalPaymentRequestFlag(b);
		account.setPartQuantityOrderLimit("partQuantityOrderLimit");
		account.setManualMeterReadFlag(b);
		account.setCreateServiceRequestFlag(b);
		account.setUsesConsumablesFlag(b);
		account.setActivityUploadFlag(b);
		account.setInvoiceUploadFlag(b);
		account.setDebriefUploadFlag(b);
		account.setViewInvoiceFlag(b);
		account.setOrganizationID("organizationID");
		account.setAddressStatus("addressStatus");
		account.setIndirectPartnerFlag(b);
		account.setDirectPartnerFlag(b);
		account.setLogisticsPartnerFlag(b);
		account.setDefaultCurrency("defaultCurrency");
		account.setUploadClaimFlag(b);
		account.setUploadRequestFlag(b);
		account.setUploadClaim("uploadClaim");
		account.setUploadRequest("uploadRequest");
		account.setOrderPartsDebriefFlag(b);
		account.setAccountOrganization("accountOrganization");
		account.setAccountSite("accountSite");
		account.setCountryCode("countryCode");
		account.setDisplayName("displayName");
		
		asset.setAccount(account);
		asset.setLastPageReadDate(dt);
		asset.setLastColorPageReadDate(dt);
		asset.setColorRequiredFlag(b);
		asset.setMacAddress("macAddress");
		asset.setMachineTypeModel("machineTypeModel");
		asset.setProblemDescription("problemDescription");
		asset.setPartnerAccount(account);
		asset.setNetworkConnectedFlag(b);
		asset.setInstallDate(dt);
		
		List<Part> parts = new ArrayList<Part>();
		Part pr = new Part();
		pr.setPartId("partId");
		pr.setPartNumber("partNumber");
		pr.setPartName("partName");
		pr.setReturnRequiredFlag(b);
		pr.setReplacementPartNumber("replacementPartNumber");
		pr.setCategory("category");
		pr.setDescription("description");
		pr.setOrderQuantity("orderQuantity");
		pr.setPartType("partType");
		pr.setSupplyType("supplyType");
		pr.setImplicitFlag(b);
		pr.setCatalogId("catalogId");
		pr.setPrinterPartNumber("printerPartNumber");
		pr.setAssetUsageType("assetUsageType");
		pr.setAssetSerialNumber("assetSerialNumber");
		pr.setConsumableType("consumableType");
		pr.setYield("yield");
		pr.setProductId("productId");
		pr.setSupplyId("supplyId");
		pr.setAgreementId("agreementId");
		pr.setModel("model");
		pr.setStatus("status");
		pr.setCancelledQuantity("cancelledQuantity");
		pr.setShippedDate(dt);
		pr.setLastOrderDate(dt);
		pr.setOrderNumber("orderNumber");
		parts.add(0, pr);
		
		asset.setPartList(parts);
		//asset.setServiceRequest(null);
		asset.setProductNo("productNo");
		asset.setChlNodeId("chlNodeId");
		asset.setChlNodeValue("chlNodeValue");
		asset.setAssetCostCenter("assetCostCenter");
		asset.setDefaultSpecialInstruction("defaultSpecialInstruction");
		asset.setPoNumber("poNumber");
		asset.setDescription("description");
		asset.setNotes("notes");
		asset.setAddressFlag(b);
		
		List<PageCounts> pglist = new ArrayList<PageCounts>();
		PageCounts pg = new PageCounts();
		pg.setName("name");
		pg.setCount("count");
		pg.setDate("dt");
		pg.setSiebelName("siebelName");
		pg.setBothValueBlank(b);
		pglist.add(0, pg);
		asset.setPageCounts(pglist);
		
		asset.setConsumableAssetFlag(b);
		asset.setInstallationOnlyFlag(b);
		asset.setLastOrderPartList(parts);
		
		
		
		AccountContact primaryContact = new AccountContact();
		primaryContact.setFirstName("accFN");
		primaryContact.setLastName("accLN");
		primaryContact.setEmailAddress("accEMAIL");
		primaryContact.setWorkPhone("accPHONE");
		
		GenericAddress address = new GenericAddress();
		address.setPhysicalLocation1("physicalLocation1");
		address.setPhysicalLocation2("physicalLocation2");
		address.setPhysicalLocation3("physicalLocation3");
		address.setAddressName("addressName");
		address.setStoreFrontName("storeFrontName");
		address.setAddressLine1("addressLine1");
		address.setCity("city");
		address.setState("state");
		address.setProvince("province");
		address.setCountry("country");
		address.setPostalCode("postalCode");
		address.setOfficeNumber("officeNumber");
		address.setCounty("county");
		address.setDistrict("district");
		GenericAddress address2 = new GenericAddress();
		address2.setPhysicalLocation1("physicalLocation1");
		address2.setPhysicalLocation2("physicalLocation2");
		address2.setPhysicalLocation3("physicalLocation3");
		address2.setAddressName("addressName");
		address2.setStoreFrontName("storeFrontName");
		address2.setAddressLine1("addressLine1");
		address2.setCity("city");
		address2.setState("state");
		address2.setProvince("province");
		address2.setCountry("country");
		address2.setPostalCode("postalCode");
		address2.setOfficeNumber("officeNumber");
		address2.setCounty("county");
		address2.setDistrict("district");
		
		
		
		
		
		
		sr.setAsset(asset);
		sr.setPrimaryContact(primaryContact);
	
		
		
		
		
		sr.setCustomerReferenceId("customerReferenceId");
		sr.setOrderSource("orderSource");
		sr.setRequestedEffectiveDate(null);
		sr.setCostCenter("costCenter");
		sr.setExpediteOrder(b);
		
		ListOfValues area = new ListOfValues();
		area.setId("areaId");
		area.setLanguage("areaLNG");
		area.setLanguageName("areaLNGNAME");
		area.setName("areaName");
		area.setType("areaType");
		area.setValue("areaValue");
		sr.setArea(area);
		
		ListOfValues subArea = new ListOfValues();
		subArea.setId("subaID");
		subArea.setLanguage("subaLNG");
		subArea.setLanguageName("subaLNGNAME");
		subArea.setName("subaNAME");
		subArea.setType("subaTYPE");
		subArea.setValue("subaVALUE");
		sr.setSubArea(subArea);
		
		sr.setNotes("notes");
		sr.setPoNumber("poNumber");
		sr.setRequestType("Change Request");
		sr.setServiceRequestNumber("1-11258746381");
		sr.setServiceRequestDate(dt);
		sr.setServiceRequestEndDate(dt);
		sr.setAsset(asset);
		sr.setServiceRequestStatus("Submitted");
		sr.setServiceRequestStatusDate(dt);
		sr.setRequestor(assetContact);
		sr.setOtherRequestedService("otherRequestedService");
		sr.setSelectedServiceDetails(ensrlist);
		sr.setPrimaryContact(assetContact);
		sr.setSecondaryContact(assetContact);
		sr.setServiceAddress(address2);
		sr.setProblemDescription("problemDescription");
		sr.setReferenceNumber("referenceNumber");
		sr.setOptionExchangeOtherDescription("optionExchangeOtherDescription");
		sr.setAssetShipped(asset);
		sr.setId("id");
		
		
		
		



List<ServiceRequestActivity> activitywebUpdateActivities = new ArrayList<ServiceRequestActivity>();

	ServiceRequestActivity sra = new ServiceRequestActivity();


		 sra.setActivityId("activityId");
		 sra.setActivityDate(dt);
		 sra.setActivityStatus("activityStatus");
		 sra.setStatusOrder(10);
		 sra.setActivityDescription("activityDescription");
		 sra.setRecipientEmail("recipientEmail");
		 sra.setMessage("message");
		 sra.setServiceRequestETA("serviceRequestETA");
		 sra.setServiceRequestSLA("serviceRequestSLA");
		 sra.setComment("comment");
		 activitywebUpdateActivities.add(0,sra);

		 sr.setActivitywebUpdateActivities(activitywebUpdateActivities);
		 sr.setEmailActivities(activitywebUpdateActivities);
		 sr.setServicewebUpdateActivities(activitywebUpdateActivities);
		 
		 List<ServiceRequestOrderLineItem> shipmentOrderLines = new ArrayList<ServiceRequestOrderLineItem>();
			List<String> serialNumbers = new ArrayList<String>();
				String s = "serialno";
				serialNumbers.add(0,s);
			ServiceRequestOrderLineItem srli = new ServiceRequestOrderLineItem();		

			srli.setCarrier("carrier");
			srli.setTrackingNumber("trackingNumber");
			srli.setServiceRequestId("serviceRequestId");
			srli.setAssetshippedModelNumber("assetshipedModelNumber");
			srli.setOrderLineType("orderLineType");
			srli.setProductTLI("productTLI");
			srli.setProductDescription("productDescription");
			srli.setSerialNumber("serialNumber");
			srli.setStatus("status");
			srli.setStatusDate(dt);
			srli.setQuantity("quantity");
			srli.setEta("eta");
			srli.setActualDeliveryDate(dt);
			srli.setVendorProduct("vendorProduct");
			srli.setContactMethod("contactMethod");
			srli.setPartnumber("partnumber");
			srli.setBackOrderQuantity(10);
			srli.setShippedQuantity(10);
			srli.setPendingQuantity(10);
			srli.setOrderedDate(dt);
			srli.setPartName("partName");
			srli.setActualShipDate(dt);
			srli.setSerialNumbers(serialNumbers);
			
			srli.setVendorId("vendorId");
			srli.setPartType("partType");
			srli.setOrderReferenceNumber("orderReferenceNumber");
			srli.setOrderFultillmentStatus("orderFultillmentStatus");
			srli.setPortalFulfillmentStatus("portalFulfillmentStatus");
			srli.setFulfillmentType("fulfillmentType");
			shipmentOrderLines .add(0,srli);

			 sr.setShipmentOrderLines(shipmentOrderLines);
			 sr.setReturnOrderLines(shipmentOrderLines);
			 sr.setPendingShipments(shipmentOrderLines);
		//1 activitywebUpdateActivities
		//2 emailActivities
		//3 servicewebUpdateActivities
		//4 shipmentOrderLines
		//5 returnOrderLines
		//6 pendingShipments
		
		sr.setServiceActivityStatus("serviceActivityStatus");
		
		ListOfValues serviceRequestType = new ListOfValues();
		serviceRequestType.setId("srtID");
		serviceRequestType.setLanguage("srtLNG");
		serviceRequestType.setLanguageName("srtLNGNAME");
		serviceRequestType.setName("srtNAME");
		serviceRequestType.setType("srtTYPE");
		serviceRequestType.setValue("srtVALUE");
		sr.setServiceRequestType(serviceRequestType);
		
		
		ListOfValues statusType = new ListOfValues();
		statusType.setId("sttID");
		statusType.setLanguage("sttLNG");
		statusType.setLanguageName("sttLNGNAME");
		statusType.setName("sttNAME");
		statusType.setType("sttTYPE");
		statusType.setValue("sttVALUE");
		sr.setStatusType(statusType);
		
		sr.setServiceRequestor("serviceRequestor");
		sr.setCustomerReferenceNumber("customerReferenceNumber");
		sr.setContractType("contractType");
		sr.setParts(parts);
		sr.setCancelledParts(parts);
		sr.setAddtnlDescription("addtnlDescription");
		sr.setEta("eta");
		sr.setHelpdeskReferenceNumber("helpdeskReferenceNumber");
		sr.setResolutionCode("resolutionCode");
		sr.setAccountName("accountName");
		
		List<Attachment> attachmentlist = new ArrayList<Attachment>();
			Attachment attachment = new Attachment();
			attachment.setAttachmentName("attachmentName");
			//attachment.setSubmittedOn("submittedOn");
			//hard coded for mock
			attachment.setSize(10);
			attachment.setSizeForDisplay("sizeForDisplay");
			//attachment.setCompletedOn("completedOn");
			attachment.setStatus("status");
			attachment.setDescription("description");
			attachment.setActivityId("activityId");
			attachment.setExtension("extension");
			attachment.setVisibility("visibility");
			attachment.setDisplayAttachmentName("displayAttachmentName");
			attachment.setId("id");
			attachment.setActualFileName("actualFileName");
			attachment.setIdentifier("identifier");
			attachment.setType("type");
		attachmentlist.add(0, attachment);	
		sr.setAttachments(attachmentlist);	
		
		sr.setPageCounts(pglist);
		sr.setCustRefNumber("custRefNumber");
		sr.setServiceRequestETA("serviceRequestETA");
		sr.setServiceRequestSLA("serviceRequestSLA");
		sr.setDefaultSpecialInstructions("defaultSpecialInstructions");
		sr.setSpecialInstructions("specialInstructions");
		sr.setAccountId("accountId");
		sr.setAccountCountry("accountCountry");
		//sr.setSpecialUsage("specialUsage");
		//sr.setAccountSite("accountSite");
		//sr.setAreaClassification("areaClassification");
		//sr.setSiteClassification("siteClassification");
		//sr.setAssetDivision("assetDivision");
		//sr.setDepartmentName("departmentName");
		//sr.setDepartments("departments");
		
		for(int i=0; i<=20; i++){
			servList.add(i, sr);
		}
	return servList;
	
	}
}
