package com.lexmark.service.impl.real.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.lexmark.contract.GlobalAssetDetailContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Asset;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.Part;
import com.lexmark.domain.Payment;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.service.impl.real.domain.AddressDo;
import com.lexmark.service.impl.real.domain.FavoriteAddressDo;
import com.lexmark.service.impl.real.domain.PartnerAssetDo;
import com.lexmark.service.impl.real.domain.PartnerAssetFlagDo;
import com.lexmark.service.impl.real.domain.PartnerFruHardwarePartDetailDo;
import com.lexmark.service.impl.real.domain.PartnerFruPartBaseDo;
import com.lexmark.service.impl.real.domain.PartnerFruPartDetailDo;
import com.lexmark.service.impl.real.domain.PartnerFruPartDo;
import com.lexmark.service.impl.real.domain.PartnerNotificationDo;
import com.lexmark.service.impl.real.domain.PartnerOpenClaimDo;
import com.lexmark.service.impl.real.domain.PartnerPartBaseLocations;
import com.lexmark.service.impl.real.domain.PartnerPaymentDo;
import com.lexmark.service.impl.real.domain.PartnerPaymentRequestDO;
import com.lexmark.service.impl.real.domain.PartnerPaymentRequestExpenseDO;
import com.lexmark.service.impl.real.domain.PartnerTechnicianDo;

public class AmindPartnerDataCoversionUtil  {
	
	public static final String ORDER_PART = "Can Order Parts";
	public static final String CREATE_CLAIMS = "Can Create Claims";
	public static final String CREATE_ADDRESS = "Can Create Address";
	public static final String ALLOW_PAYMENT = "Allow Additional Payments";
	
	public static List<Activity> convertOpenClaimToPortalActivity (List<PartnerOpenClaimDo> openClaimList ) {
		List<Activity> portalActivityList = new ArrayList<Activity>();
		for(PartnerOpenClaimDo openClaim :  openClaimList) {
			
			Activity portalActivity = new Activity();
			portalActivity.setActivityId(openClaim.getActivityId());
			portalActivity.setServiceProviderReferenceNumber(openClaim.getServiceProviderReferenceNumber());
			portalActivity.setActivityDate(openClaim.getActivityDate());
			ServiceRequest serviceRequest = new ServiceRequest();
			serviceRequest.setId(openClaim.getServiceRequestId());
			serviceRequest.setServiceRequestNumber(openClaim.getServiceRequestNumber());
			serviceRequest.setServiceRequestDate(openClaim.getServiceRequestDate());
			portalActivity.setServiceRequest(serviceRequest);
			
			Account partnerAccount = new Account();
			partnerAccount.setAccountId(openClaim.getMdmLevel5AccountId());
			partnerAccount.setAccountName(openClaim.getAccountName());
			portalActivity.setPartnerAccount(partnerAccount);
			
			ListOfValues actualFailureCode = new ListOfValues();
			actualFailureCode.setValue(openClaim.getActualFailureCode());
			portalActivity.setActualFailureCode(actualFailureCode);
			
			portalActivityList.add(portalActivity);
		}
		return portalActivityList;
	}
	
	public static List<Asset> convertPartnerAssetDoToPartnerAssetList(List<PartnerAssetDo> partnerAssetList) {
		List<Asset> assetList = new ArrayList<Asset>();
		for(PartnerAssetDo partnerAsset : partnerAssetList) {
			Asset portalAsset = new Asset();
			portalAsset.setAssetId(partnerAsset.getAssetId());
			portalAsset.setSerialNumber(partnerAsset.getSerialNumber());
			portalAsset.setProductLine(partnerAsset.getProductLine());
			portalAsset.setProductTLI(partnerAsset.getProductTLI());
			portalAsset.setModelNumber(partnerAsset.getMachineTypeModel());
			portalAsset.setCustomerReportingName(partnerAsset.getCustomerAccountName());
			portalAsset.setMki(partnerAsset.getMki());
			portalAsset.setServiceProvider(partnerAsset.getServiceProvider());
			assetList.add(portalAsset);
			
		}
		return assetList;
	}
	
	public static Asset convertPartnerAssetToAsset(PartnerAssetDo assetDo,GlobalAssetDetailContract contract  ){
		Asset portalAsset = new Asset();
		boolean customerPartnerInfoFlag = false;
		if (AmindServiceUtil.SIEBEL_MDMLEVEL.equals(contract.getMdmLevel())) {
			if(assetDo.getMdmLevel5AccountId().equalsIgnoreCase(contract.getMdmId())) {
				customerPartnerInfoFlag = true;
			}
		}
		else if (AmindServiceUtil.ACCOUNT_MDMLEVEL.equals(contract.getMdmLevel())) {
			if(assetDo.getMdmLevel4AccountId().equalsIgnoreCase(contract.getMdmId())) {
				customerPartnerInfoFlag = true;
			}
		
		}
		else if (AmindServiceUtil.LEGAL_MDMLEVEL.equals(contract.getMdmLevel())) {
			if(assetDo.getMdmLevel3AccountId().equalsIgnoreCase(contract.getMdmId())) {
				customerPartnerInfoFlag = true;
			}
		}
		else if (AmindServiceUtil.DOMESTIC_MDMLEVEL.equals(contract.getMdmLevel())) {
			if(assetDo.getMdmLevel2AccountId().equalsIgnoreCase(contract.getMdmId())) {
				customerPartnerInfoFlag = true;
			}
		}
		else if (AmindServiceUtil.GLOBAL_MDMLEVEL.equals(contract.getMdmLevel())) {
			if(assetDo.getMdmLevel1AccountId().equalsIgnoreCase(contract.getMdmId())) {
				customerPartnerInfoFlag = true;
			}
		}
		
		portalAsset.setAssetId(assetDo.getAssetId());
		portalAsset.setSerialNumber(assetDo.getSerialNumber());
		portalAsset.setModelNumber(assetDo.getMachineTypeModel());
		portalAsset.setProductLine(assetDo.getProductLine());
		portalAsset.setProductTLI(assetDo.getProductTLI());
		portalAsset.setCustomerReportingName(assetDo.getCustomerReportingName());
		portalAsset.setDisplayWarning(assetDo.getDisplayWarning());

		/*
		GenericAddress installAddress = new GenericAddress();
		installAddress.setAddressId(assetDo.getAddressId());
		installAddress.setAddressName(assetDo.getAddressName());
		installAddress.setAddressLine1(assetDo.getAddress1());
		installAddress.setAddressLine2(assetDo.getAddress2());
		installAddress.setAddressLine3(assetDo.getAddress3());
		installAddress.setCity(assetDo.getInstallCity());
		installAddress.setState(assetDo.getInstallState());
		installAddress.setProvince(assetDo.getInstallProvince());
		installAddress.setCountry(assetDo.getInstallCountry());
		installAddress.setPostalCode(assetDo.getInstallPostalCode());
		portalAsset.setInstallAddress(installAddress);
		*/
		AssetConversionUtil.populateInstallAddress(portalAsset, assetDo);
		
		if(customerPartnerInfoFlag) {
			/*customerInfo*/
			com.lexmark.domain.Account customerAccount = new com.lexmark.domain.Account();
			customerAccount.setAccountId(assetDo.getCustomerAccountId());
			customerAccount.setAccountName(assetDo.getCustomerAccountName());
			portalAsset.setAccount(customerAccount);
			
			/*partnerInfo*/
			com.lexmark.domain.Account partnerAccount = new com.lexmark.domain.Account();
			partnerAccount.setAccountId(assetDo.getCustomerAccountId());   //changed as per Re: INC0129952
			partnerAccount.setAccountName(assetDo.getPartnerAccountName());
			partnerAccount.setOrganizationID(assetDo.getOrganizationId());
			partnerAccount.setDefaultCurrency(assetDo.getPartnerAccountCurrency());	
			if(StringUtils.isEmpty(assetDo.getPartQuantityOrderLimit()) ){
				partnerAccount.setPartQuantityOrderLimit("5");
			} else {
				partnerAccount.setPartQuantityOrderLimit(assetDo.getPartQuantityOrderLimit());
			}

			/*Translation for flags*/
			if(assetDo.getFlags() != null) {
				
				for(PartnerAssetFlagDo partnerAccountFlag : assetDo.getFlags()) {
					if(ORDER_PART.equalsIgnoreCase(partnerAccountFlag.getType())) {
						if(partnerAccountFlag.isValue()) {
							partnerAccount.setOrderPartsFlag(true);
						}
					}
					
					if(CREATE_CLAIMS.equalsIgnoreCase(partnerAccountFlag.getType())) {
						if(partnerAccountFlag.isValue()) {
							partnerAccount.setCreateClaimFlag(true);
						}
					}
					
					
					if(CREATE_ADDRESS.equalsIgnoreCase(partnerAccountFlag.getType())) {
						if(partnerAccountFlag.isValue()) {
							partnerAccount.setCreateShipToAddressFlag("true"); //changed for CI-5 2098
						}
					}
					
					
					if(ALLOW_PAYMENT.equalsIgnoreCase(partnerAccountFlag.getType())) {
						if(partnerAccountFlag.isValue()) {
							partnerAccount.setAllowAdditionalPaymentRequestFlag(true);
						}
					}
				}
			}
			GenericAddress shipToAddress = new GenericAddress();
			shipToAddress.setAddressId(assetDo.getShipAddressId());
			shipToAddress.setAddressName(assetDo.getShipAddressName());
			shipToAddress.setAddressLine1(assetDo.getShipAddress1());
			shipToAddress.setAddressLine2(assetDo.getShipAddress2());
			shipToAddress.setAddressLine3(assetDo.getShipAddress3());
			shipToAddress.setCity(assetDo.getShipCity());
			shipToAddress.setState(assetDo.getShipState());
			shipToAddress.setProvince(assetDo.getShipProvince());
			shipToAddress.setCountry(assetDo.getShipCountry());
			shipToAddress.setPostalCode(assetDo.getShipPostalCode());
			shipToAddress.setCounty(assetDo.getCounty());
			shipToAddress.setDistrict(assetDo.getDistrict());
			shipToAddress.setOfficeNumber(assetDo.getOfficeNumber());
			partnerAccount.setAddress(shipToAddress);
			portalAsset.setPartnerAccount(partnerAccount);
		}
		return portalAsset;
	}
	
	public static List<AccountContact> convertTechnicianListDoToAccountContact(List<PartnerTechnicianDo> partnerTechnicianDoList) {
		List<AccountContact> accountContactList = new ArrayList<AccountContact>();
		for(PartnerTechnicianDo technican : partnerTechnicianDoList) {
			AccountContact accountContact = new AccountContact();
			accountContact.setContactId(technican.getId());
			accountContact.setFirstName(technican.getFirstName());
			accountContact.setLastName(technican.getLastName());
			accountContactList.add(accountContact);
		}
		return accountContactList;
	}
	
	public static List<GenericAddress> convertAddressDoListToGenericAddress(List<AddressDo> addressDoList, Set<String> favoriteSet) {
		if (addressDoList == null) {
			return new ArrayList<GenericAddress>();
		}
		List<GenericAddress> addressList = new ArrayList<GenericAddress>(addressDoList.size());
		GenericAddress address = null;
		for (AddressDo addressDo: addressDoList) {
			address = new GenericAddress();
			
			if (addressDo instanceof  FavoriteAddressDo) {

				address.setUserFavorite(true);
				address.setAddressName(addressDo.getAddressName());
			} else {
				address.setAddressName(addressDo.getCustomAddressName());
			}

			address.setAddressId(addressDo.getAddressId());
			address.setAddressLine1(addressDo.getAddressLine1());
			address.setAddressLine2(addressDo.getAddressLine2());
			address.setAddressLine3(addressDo.getAddressLine3());
			address.setCity(addressDo.getCity());
			address.setState(addressDo.getState());
			address.setPostalCode(addressDo.getZip());
			address.setCountry(addressDo.getCountry());
			address.setProvince(addressDo.getProvince());
			
			address.setCounty(addressDo.getCounty());
			address.setDistrict(addressDo.getDistrict());
			address.setOfficeNumber(addressDo.getOfficeNumber());
			address.setCountryISOCode(addressDo.getCountyCode());
			address.setRegion(addressDo.getRegion());
			
			if (favoriteSet != null && favoriteSet.contains(addressDo.getId()))
			{
				address.setUserFavorite(true);
			}
			
			
			
			addressList.add(address);
		}
		return addressList;
	}
	
	public static List<ServiceRequestActivity> convertNotificationToServiceRequest (List<PartnerNotificationDo> notificationList, String emailAddress) {
		List<ServiceRequestActivity> SrList = new ArrayList<ServiceRequestActivity> ();
		for(PartnerNotificationDo parterNotification : notificationList) {
			if(parterNotification.getRecipientEmail() != null && emailAddress.contains(parterNotification.getRecipientEmail())) {
				ServiceRequestActivity srActivity = new ServiceRequestActivity();
				srActivity.setActivityDate(parterNotification.getActivityDate());
				srActivity.setActivityDescription(parterNotification.getActivityDescription());
				srActivity.setActivityId(parterNotification.getActivityId());
				srActivity.setRecipientEmail(parterNotification.getRecipientEmail());
				srActivity.setMessage(parterNotification.getMessage());
				SrList.add(srActivity);
			}

		}
		return SrList;
	}
	


	public static List<Part> convertFruPartDoToPortalFruPart(List<PartnerFruPartBaseDo> fruPartList, boolean locationFlag) {
		List<Part> partList = new ArrayList<Part>();
		boolean materialTypePrinter = false;
		
		for(PartnerFruPartBaseDo fruPartDo: fruPartList) {
				Part portalPart = new Part();
				portalPart.setPartName(fruPartDo.getPartName());
				portalPart.setPartNumber(fruPartDo.getPartNumber());
				portalPart.setPartId(fruPartDo.getId());
				
				if("Printers".equalsIgnoreCase(fruPartDo.getMaterialLine())){
					materialTypePrinter = true;
				}
				
				portalPart.setTypePrinter(materialTypePrinter);
				
				if(locationFlag) {
					PartnerPartBaseLocations location = null;
					if(fruPartDo instanceof PartnerFruPartDetailDo) {
						PartnerFruPartDetailDo fruPartDetailDo  = (PartnerFruPartDetailDo) fruPartDo;
						if(fruPartDetailDo.getPartLocations() != null ) {
							location = (PartnerPartBaseLocations) fruPartDetailDo.getPartLocations().get(0);
							portalPart.setReplacementPartNumber(location.getProdId());
						}
						
					} else if(fruPartDo instanceof PartnerFruHardwarePartDetailDo) { 
						PartnerFruHardwarePartDetailDo fruPartDetailDo  = (PartnerFruHardwarePartDetailDo) fruPartDo;
						if(fruPartDetailDo.getPartLocations() != null ) {
							location = (PartnerPartBaseLocations) fruPartDetailDo.getPartLocations().get(0);
							portalPart.setReplacementPartNumber(location.getProdId());
						}
					} else {
						PartnerFruPartDo fruPartDetailDo  = (PartnerFruPartDo) fruPartDo;
						if(fruPartDetailDo.getPartLocations() != null ) {
							location = (PartnerPartBaseLocations) fruPartDetailDo.getPartLocations().get(0);
							portalPart.setReplacementPartNumber(location.getProdId());
						}
						
					}
				}
				partList.add(portalPart);
		}
		return partList;
	}
	
	public static List<Payment> convertPaymentDOListToPaymentList( List<PartnerPaymentDo> partnerPaymentList ) throws ParseException {
		List<Payment> paymentList = new ArrayList<Payment>();
		for(PartnerPaymentDo partnerPayment : partnerPaymentList) {
			Payment payment = new Payment();
			payment.setPaymentId(partnerPayment.getId());
			payment.setDateCreated(partnerPayment.getDateCreated());
			payment.setPaymentNumber(partnerPayment.getPaymentNumber());
			payment.setPartnerAgreement(partnerPayment.getPartnerAgreement());
			payment.setCheckNumber(partnerPayment.getCheckNumber());
			
			NumberFormat format = NumberFormat.getInstance(Locale.US);
		    Number totalAmount = format.parse(partnerPayment.getPaymentTotal());
		    
			payment.setPaymentTotal(totalAmount.doubleValue());
			payment.setPayableToName(partnerPayment.getPayableToName());
			
			ListOfValues paymentStatus = new ListOfValues();
			paymentStatus.setValue(partnerPayment.getPaymentStatus());
			payment.setPaymentStatus(paymentStatus);
			
			Account partnerAccount = new Account();
			partnerAccount.setAccountId(partnerPayment.getPartnerAccountId());
			partnerAccount.setAccountName(partnerPayment.getPartnerAccountName());

			
			GenericAddress  partnerAddress = new GenericAddress ();
			partnerAddress.setAddressId(partnerPayment.getAddressId());
			partnerAddress.setAddressName(partnerPayment.getAddressName());
			partnerAddress.setAddressLine1(partnerPayment.getAddressLine1());
			partnerAddress.setAddressLine2(partnerPayment.getAddressLine2());
			partnerAddress.setAddressLine3(partnerPayment.getAddressLine3());
			partnerAddress.setCity(partnerPayment.getCity());
			partnerAddress.setCountry(partnerPayment.getCountry());
			partnerAddress.setPostalCode(partnerPayment.getPostalCode());
			partnerAddress.setProvince(partnerPayment.getProvince());
			partnerAddress.setState(partnerPayment.getState());
			partnerAccount.setAddress(partnerAddress);
			payment.setPartnerAccount(partnerAccount);
			
			paymentList.add(payment);
		}
		return paymentList;
	}
	
	public static List<Activity> convertPaymentLineItemDOListToActivityList( List<PartnerPaymentRequestDO> paymentLineItemList ) throws ParseException {
		List<Activity> activityList = new ArrayList<Activity>();
		for(PartnerPaymentRequestDO partnerPaymentLineItem : paymentLineItemList) {
			Activity activity = new Activity();
	
			activity.setActivityId(partnerPaymentLineItem.getActivityId());
			activity.setActivityDate(partnerPaymentLineItem.getActivityDate());
			activity.setServiceProviderReferenceNumber(partnerPaymentLineItem.getServiceProviderReferenceNumber());
			activity.setPaymentServiceType(partnerPaymentLineItem.getPaymentServiceType());
			
			/*String to Double conversion*/
			NumberFormat format = NumberFormat.getInstance(Locale.US);
		    Number labourAmount = format.parse(partnerPaymentLineItem.getLaborPayment());
		    Number parentFee = format.parse(partnerPaymentLineItem.getPartnerFee());
		    Number partsAmount = format.parse(partnerPaymentLineItem.getPartsPayment());
		    	
			Double laborPayment = labourAmount.doubleValue();
			Double partnerFee = parentFee.doubleValue();
			Double partsPayment = partsAmount.doubleValue();
			
			activity.setLaborPayment(laborPayment);
			activity.setPartnerFee(partnerFee);
			activity.setPartsPayment(partsPayment);
			
			Double totalPayment =  laborPayment + partnerFee + partsPayment;
			Double additionalpymnt = 0.00;//added by sankha for AIR64865
			/*Commented by sankha start for AIR64865
			if(partnerPaymentLineItem.getLineItems() != null) {
				for(PartnerPaymentRequestExpenseDO requestExpense : partnerPaymentLineItem.getLineItems()) {
					Double requestAdditionalPayment = Double.parseDouble(requestExpense.getAdditionalPayments());
					totalPayment = totalPayment + requestAdditionalPayment;
					
					activity.setAdditionalPayments(Double.parseDouble(requestExpense.getAdditionalPayments()));
				}
			}
			*/
			/*Added by sankha start for AIR64865*/
			if(partnerPaymentLineItem.getLineItems() != null) {
				for(PartnerPaymentRequestExpenseDO requestExpense : partnerPaymentLineItem.getLineItems()) {
					Double requestAdditionalPayment = Double.parseDouble(requestExpense.getAdditionalPayments());
					totalPayment = totalPayment + requestAdditionalPayment;
					additionalpymnt = additionalpymnt + requestAdditionalPayment;
				}
			}
			activity.setAdditionalPayments(additionalpymnt);
			activity.setTotalPayment(totalPayment);
			/*End for AIR64865*/ 
			
			ServiceRequest serviceRequest = new ServiceRequest();
			serviceRequest.setId(partnerPaymentLineItem.getServiceRequestId());
			serviceRequest.setServiceRequestNumber(partnerPaymentLineItem.getServiceRequestNumber());
					
			Asset asset = new Asset();
			asset.setAssetId(partnerPaymentLineItem.getAssetId());
			asset.setSerialNumber(partnerPaymentLineItem.getSerialNumber());
			asset.setProductTLI(partnerPaymentLineItem.getProductTLI());
			asset.setModelNumber(partnerPaymentLineItem.getModelNumber());
			asset.setProductLine(partnerPaymentLineItem.getProductLine());
			serviceRequest.setAsset(asset);

			Account customerAccount = new Account();
			customerAccount.setAccountId(partnerPaymentLineItem.getCustomerAccountId());
			customerAccount.setAccountName(partnerPaymentLineItem.getCustomerAccountName());
			activity.setCustomerAccount(customerAccount);
			
			if(partnerPaymentLineItem instanceof PartnerPaymentRequestDO) {
				PartnerPaymentRequestDO paymentRequest = (PartnerPaymentRequestDO) partnerPaymentLineItem;
				activity.setEligibleToPay(paymentRequest.getEligibleToPay());
				activity.setPayEligiblityOverride(paymentRequest.getPayEligiblityOverride());
				activity.setPartnerAgreementName(paymentRequest.getPartnerAgreementName());
				activity.setServiceProviderReferenceNumber(paymentRequest.getServiceProviderReferenceNumber());
				
				Account partnerAccount = new Account();
				partnerAccount.setAccountId(partnerPaymentLineItem.getPartnerAccountId());
				partnerAccount.setAccountName(partnerPaymentLineItem.getPartnerAccountName());
				activity.setPartnerAccount(partnerAccount);
				
				serviceRequest.setServiceRequestStatus(paymentRequest.getServiceRequestStatus());
				
				Payment payment = new Payment();
				payment.setPaymentId(paymentRequest.getPaymentId());
				payment.setPaymentNumber(paymentRequest.getPaymentNumber());
				
				ListOfValues paymentStatus = new ListOfValues();
				paymentStatus.setValue(paymentRequest.getPaymentStatus());
				payment.setPaymentStatus(paymentStatus);
				
				activity.setPayment(payment);
			}
			
			activity.setServiceRequest(serviceRequest);
			String supplyAgreementFee = partnerPaymentLineItem.getSupplyAgreementFee(); 
			if(StringUtils.isNotBlank(supplyAgreementFee)){
				activity.setSuppliesFulfillmentFee(Double.valueOf(supplyAgreementFee).doubleValue());
			}
		
			activityList.add(activity);
		}
		
		return activityList;
	}


	
}
