package com.lexmark.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.DeleteAttachmentContract;
import com.lexmark.contract.FRUPartDetailContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.contract.TechnicianListContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ActivityNote;
import com.lexmark.domain.AdditionalPaymentRequest;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.Pagination;
import com.lexmark.domain.Part;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.enums.PartnerTypeEnum;
import com.lexmark.result.ActivityListResult;
import com.lexmark.result.FRUPartDetailResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.LocalizedSiebelValueResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.result.TechnicianListResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;

public class ControllerUtil {
	private static final Logger logger = LogManager.getLogger(ControllerUtil.class);
	/**
	 * constructor
	 */
	private ControllerUtil() {}
	
	/**
	 * Retrieves the map of Siebel LOV value by given lov type and locale. Map'key is LOV value, Map'value is LOV local name.
	 * retrieves LOV list from Siebel, then localizes by JDBC call.
	 * @param lovType The type of Siebel Value, such as ACTUAL_FAIL_CD
	 * @param globalService
	 * @param serviceRequestLocaleService
	 * @param locale 
	 * @param errorCode1  
	 * @param globalService 
	 * @param serviceRequestLocaleService 
	 * @return the map of Siebel LOV values.
	 * @throws Exception 
	 */
	public static Map<String, String> retrieveLocalizedLOVMapForSelection(
														String lovType, 
														String errorCode1,
														GlobalService globalService,
														ServiceRequestLocale serviceRequestLocaleService,
														Locale locale) throws Exception{
		Map<String, String> valuePairs = new LinkedHashMap<String, String>();
		// retrieve LOV list from Siebel
		SiebelLOVListContract lovListcontract = ContractFactory.createSiebelLOVListContract(lovType, errorCode1);
		
		logger.debug("Berofe errorCode contract1");
		ObjectDebugUtil.printObjectContent(lovListcontract, logger);
		logger.debug("After errorCode contract1");
		SiebelLOVListResult listResult;
		if(lovType.trim().equalsIgnoreCase("ACTUAL_FAIL_CD")){
			 listResult = globalService.retrieveNonCacheableSiebelLOVList(lovListcontract);
		}
		else{
			listResult = globalService.retrieveSiebelLOVList(lovListcontract);
		}
		logger.debug("The lovListSize is-->>>"+listResult.getLovList().size());
		if (listResult == null || listResult.getLovList() == null || listResult.getLovList().size() == 0) {
			valuePairs.put("", "");
			return valuePairs;
		}
		
		// retrieve localized LOV list from database
		LocalizedSiebelLOVListContract localizedLOVListContract = ContractFactory.createLocalizedSiebelLOVListContract(lovType, null, locale);
		LocalizedSiebelLOVListResult localizedLOVListResult = serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(localizedLOVListContract);
		Map<String, String> dbLOVMap = new HashMap<String, String>();
		if (localizedLOVListResult != null && localizedLOVListResult.getLocalizedSiebelLOVList().size() > 0) {
			for (ListOfValues localizedLOV : localizedLOVListResult.getLocalizedSiebelLOVList()) {
				dbLOVMap.put(localizedLOV.getValue(), localizedLOV.getName());
			}
		}

		String tempName=null;
		for (ListOfValues lov : listResult.getLovList()) {
			tempName = dbLOVMap.get(lov.getValue());
			if (tempName != null) {
				lov.setName(tempName);
			} else {
				lov.setName(lov.getValue());
			}
		}

		List<ListOfValues> sortedLOVs = listResult.getLovList();
		Collections.sort(sortedLOVs, new LOVComparator(locale));
		
		for (ListOfValues lov : sortedLOVs) {
			if(lovType.trim().equalsIgnoreCase("ACTUAL_FAIL_CD")){
				if(null != lov.getDescription() && !lov.getDescription().trim().equals("")){
					valuePairs.put(lov.getDescription(), lov.getName());
				}
				else{
					valuePairs.put(lov.getValue(), lov.getName());
				}
			}
			else{
			valuePairs.put(lov.getValue(), lov.getName());
			}
		}
		
		return valuePairs;
	}	
	
	/**
	 * Get the localized Siebel LOV value by given LOV and locale.
	 * If localized Siebel LOV can not found, set name as value and return.
	 * @param lov 
	 * @param serviceRequestLocaleService 
	 * @param locale 
	 * @return string 
	 */
	public static ListOfValues getLocalizedSiebelValue(ListOfValues lov, 
													   ServiceRequestLocale serviceRequestLocaleService,
													   Locale locale) {
		LocalizedSiebelValueContract localizedContract = 
				ContractFactory.createLocalizedSiebelValueContract(lov, locale);
		try {
			LocalizedSiebelValueResult localizedResult =
				serviceRequestLocaleService.retrieveLocalizedSiebelValue(localizedContract);
			
			if (localizedResult != null && localizedResult.getLovValue() != null &&
					localizedResult.getLovValue().getName() != null ) {
				return localizedResult.getLovValue();
			} else {
				lov.setName(lov.getValue());
				return lov;
			}
		} catch (Exception ex) {
			logger.debug("Exception"+ex.getMessage()); 
			logger.error("Failed to retrieve localized Siebel Value for " + lov.getValue() + ", set name as value.");
			lov.setName(lov.getValue());
			return lov;
		}
	}
	
	/**
	 * Retrieves a map of localized LOV from database.
	 * It will be used in list page to fill selection filter, and to localize LOV string in grid.
	 * @param lovType  
	 * @param serviceRequestLocaleService  
	 * @param locale 
	 * @param partnerType    
	 * @return string  
	 */
	public static Map<String, String> retrieveLocalizedLOVMap(String lovType,
			String partnerType,
			ServiceRequestLocale serviceRequestLocaleService,
			Locale locale) {
		Map<String, String> lovMap = new LinkedHashMap<String, String>();
		List<ListOfValues> valuesList =
			retrieveLocalizedSiebelLOVList(lovType, partnerType, serviceRequestLocaleService, locale).getLocalizedSiebelLOVList();
		Collections.sort(valuesList, new LOVComparator(locale));
		
		for(ListOfValues lov : valuesList){
			lovMap.put(lov.getValue(), lov.getName());
		}
		return lovMap;		
	}
	
	/**
	 * Utility to get image URL from the productImageService
	 * @param productImageService 
	 * @param productTLI 
	 * @return   image URL 
	 */
	public static String retrieveProductImageUrl(ProductImageService productImageService, String productTLI){
		ProductImageContract productImageContract = new ProductImageContract();
		productImageContract.setPartNumber(productTLI);
		try {
			ProductImageResult productImageResult = productImageService
					.retrieveProductImageUrl(productImageContract,LexmarkConstants.PARTNER_PORTAL_CONTEXT_PATH);
			return productImageResult.getProductImageUrl();
		} catch (Exception e) {
			logger.error("trying to get the productImageResult productTLI by but failed with error: "
					+ e.getMessage());
			throw new RuntimeException("Can not get imageURL by part number: " + productTLI, e);
		}
	}
	
	/**
	 * @param requestService 
	 * @param contract 
	 * @return result 
	 * @throws Exception 
	 */
	public static FRUPartDetailResult retrieveFRUPartPRF(PartnerRequestsService requestService,FRUPartDetailContract contract) throws Exception{
		FRUPartDetailResult result = requestService.retrieveFRUPart(contract);
		Part part = result.getPart();
		logger.info("---REPLACEMENT_PART for part.getPartNumber----"+part.getPartNumber());
		FRUPartDetailResult reResult = new FRUPartDetailResult();
		if(null != part){
			String replaceMementPartNumber = result.getPart().getReplacementPartNumber();
			while((null!=replaceMementPartNumber && !"".equals(replaceMementPartNumber))){
				contract.setPartNumber(replaceMementPartNumber);
				contract.setReplacementFlag(true);
				reResult = requestService.retrieveFRUPart(contract);
				replaceMementPartNumber = reResult.getPart().getReplacementPartNumber();
			}
			if(reResult.getPart()!=null){
				reResult.getPart().setReplacementPartNumber(part.getPartNumber());
				return reResult;
			}else{
				return result;
			}
		}
		return result;
	}
	
	/**
	 * @param propertyAttribute 
	 * @param bundleName 
	 * @param locale 
	 * @return string 
	 */
	public static String localizeMessage(String propertyAttribute, String bundleName,Locale locale) {
		if (StringUtil.isStringEmpty(bundleName)) {
			return (PropertiesMessageUtil.getPropertyMessage(LexmarkPPConstants.MESSAGE_BUNDLE_NAME, propertyAttribute, locale));
		}
		return (PropertiesMessageUtil.getPropertyMessage(bundleName, propertyAttribute, locale));
	}
	
	/**
	 * localize attributes of the given activity. Such as activityStatus, actualFailureCode, etc.
	 * @param activity 
	 * @param serviceRequestLocaleService 
	 * @param locale 
	 */
	public static void localizeActivity(Activity activity, ServiceRequestLocale serviceRequestLocaleService, Locale locale) {
		// localize activity status
		ListOfValues activityStatusLOV = activity.getActivityStatus();
		if(activityStatusLOV != null){
			activityStatusLOV.setType(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue());
			activity.setActivityStatus(
					ControllerUtil.getLocalizedSiebelValue(activityStatusLOV, serviceRequestLocaleService, locale));
		}
		
		// localize subStatus
		ListOfValues activitySubStatusLOV = activity.getActivitySubStatus();
		if(activitySubStatusLOV != null){
			activitySubStatusLOV.setType(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_SUBSTATUS.getValue());
			activity.setActivitySubStatus(
					ControllerUtil.getLocalizedSiebelValue(activitySubStatusLOV, serviceRequestLocaleService, locale));
		}
		// localize Service Request Type
		ListOfValues serviceRequestTypeLOV = activity.getServiceRequest().getServiceRequestType();
		if(serviceRequestTypeLOV != null){
			serviceRequestTypeLOV.setType(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_TYPE.getValue());
			activity.getServiceRequest().setServiceRequestType(
					ControllerUtil.getLocalizedSiebelValue(serviceRequestTypeLOV, serviceRequestLocaleService, locale));
		}
		
		// localize Problem Code
		ListOfValues problemCodeLOV = activity.getActualFailureCode();
		if(problemCodeLOV != null){
			problemCodeLOV.setType(SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue());
			activity.setActualFailureCode(ControllerUtil.getLocalizedSiebelValue(problemCodeLOV, serviceRequestLocaleService,
					locale));
		}
		
		//localize additional payments
		if(activity.getAdditionalPaymentRequestList() != null){
			ControllerUtil.batchLocalizeAdditionPayments(activity.getAdditionalPaymentRequestList(), serviceRequestLocaleService, locale);
		}
		
		if(activity.getDebrief() != null){
			localizeDebriefInfoActivity(activity, serviceRequestLocaleService, locale);
		}
	}
	
	
	
	/**
	 * @param activity 
	 * @param serviceRequestLocaleService 
	 * @param locale 
	 */
	public static void localizeDebriefInfoActivity(Activity activity, ServiceRequestLocale serviceRequestLocaleService, Locale locale) {
		
		// localize Device Condition
		ListOfValues deviceConditionLOV = activity.getDebrief().getDeviceCondition();
		if(deviceConditionLOV != null){
			deviceConditionLOV.setType(SiebelLocalizationOptionEnum.PARTNER_DEVICE_CONDITION.getValue());
			ListOfValues deviceConditionLocalized = ControllerUtil.getLocalizedSiebelValue(deviceConditionLOV, serviceRequestLocaleService,
					locale);
			activity.getDebrief().setDeviceCondition(deviceConditionLocalized);
		}
		
		// localize Resolution Code
		ListOfValues resolutionCodeLOV = activity.getDebrief().getResolutionCode();
		if(resolutionCodeLOV != null){
			resolutionCodeLOV.setType(SiebelLocalizationOptionEnum.PARTNER_RESOLUTION_CODE.getValue());
			ListOfValues resolutionCodeLocalized = ControllerUtil.getLocalizedSiebelValue(resolutionCodeLOV, serviceRequestLocaleService,
					locale);
			activity.getDebrief().setResolutionCode(resolutionCodeLocalized);
		}
		
		// localize Debrief Problem Code
		ListOfValues problemCodeLOV = activity.getDebrief().getActualFailureCode();
		if(problemCodeLOV != null){
			problemCodeLOV.setType(SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue());
			ListOfValues problemCodeLocalized = ControllerUtil.getLocalizedSiebelValue(problemCodeLOV, serviceRequestLocaleService,
					locale);
			activity.getDebrief().setActualFailureCode(problemCodeLocalized);
		}
		
		ListOfValues travelUnitOfMeasureLOV = activity.getDebrief().getTravelUnitOfMeasure();
		if(travelUnitOfMeasureLOV != null && !"".equals(travelUnitOfMeasureLOV.getValue())){
			travelUnitOfMeasureLOV.setType(SiebelLocalizationOptionEnum.PARTNER_TRAVEL_UNIT_OF_MEASURE.getValue());
			ListOfValues travelUnitOfMeasureLocalized = ControllerUtil.getLocalizedSiebelValue(travelUnitOfMeasureLOV, serviceRequestLocaleService,
					locale);
			activity.getDebrief().setTravelUnitOfMeasure(travelUnitOfMeasureLocalized);
		}
	}
	
	
	

	/**
	 * localizes activityStatus of a list Activities.
	 * @param activityList 
	 * @param activityStatusMap 
	 */
	public static void batchLocalizeActivityStatus(List<Activity> activityList, Map<String, String> activityStatusMap) {
		for (Activity activity : activityList) {
			if (activity.getActivityStatus() != null && activity.getActivityStatus().getValue() != null) {
				String localizedActivityStatus=null;
				localizedActivityStatus = activityStatusMap.get(activity.getActivityStatus().getValue());
				if (localizedActivityStatus == null) {
					localizedActivityStatus = activity.getActivityStatus().getValue();
				}
				activity.getActivityStatus().setName(localizedActivityStatus);
			}
		}
	}

	/**
	 * localizes sub status of a list Activities.
	 * @param activityList 
	 * @param activitySubStatusMap 
	 */
	public static void batchLocalizeActivitySubStatus(
			List<Activity> activityList, Map<String, String> activitySubStatusMap) {
		for (Activity activity : activityList) {
			if (activity.getActivitySubStatus() != null && activity.getActivitySubStatus().getValue() != null) {
				String localizedActivitySubStatus=null;
				localizedActivitySubStatus = activitySubStatusMap.get(activity.getActivitySubStatus().getValue());
				if (localizedActivitySubStatus == null) {
					localizedActivitySubStatus = activity.getActivitySubStatus().getValue();
				}
				activity.getActivitySubStatus().setName(localizedActivitySubStatus);
			}
		}
		
	}
	
	/**
	 * localizes problem code of a list Activities.
	 * @param activityList 
	 * @param problemCodes 
	 */
	public static void batchLocalizeActivityProblemCode(List<Activity> activityList, Map<String, String> problemCodes) {
		for (Activity claim : activityList) {
			if (claim.getActualFailureCode() != null && claim.getActualFailureCode().getValue() != null) {
				String localizedProblemCode=null;
				localizedProblemCode = problemCodes.get(claim.getActualFailureCode().getValue());
				if (localizedProblemCode == null) {
					localizedProblemCode = claim.getActualFailureCode().getValue();
				}
				claim.getActualFailureCode().setName(localizedProblemCode);
			}
		}
	}

	/**
	 * localizes service type (activity type) of a list Activities.
	 * @param activityList 
	 * @param serviceTypeMap 
	 */
	public static void batchLocalizeServiceType(List<Activity> activityList,
			Map<String, String> serviceTypeMap) {
		for (Activity activity : activityList) {
			if (activity.getActivityType() != null && activity.getActivityType().getValue() != null) {
				String localizedServiceType=null;
				localizedServiceType = serviceTypeMap.get(activity.getActivityType().getValue());
				if (localizedServiceType == null) {
					localizedServiceType = activity.getActivityType().getValue();
				}
				activity.getActivityType().setName(localizedServiceType);
			}
		}
	}
	
	/**
	 * localizes serviceRequestType of a list Activities.
	 * @param activityList  
	 * @param serviceRequestTypeMap 
	 */
	public static void batchLocalizeServiceRequestType(List<Activity> activityList, Map<String, String> serviceRequestTypeMap) {
		for (Activity activity : activityList) {
			ServiceRequest serviceRequest = activity.getServiceRequest();
			if (serviceRequest != null &&
					serviceRequest.getServiceRequestType() != null &&
					serviceRequest.getServiceRequestType().getValue() != null) {
				String serviceRequestType=null;
				serviceRequestType = serviceRequestTypeMap.get(activity.getServiceRequest().getServiceRequestType().getValue());
				if (serviceRequestType == null) {
					serviceRequestType = activity.getServiceRequest().getServiceRequestType().getValue();
				}
				activity.getServiceRequest().getServiceRequestType().setName(serviceRequestType);
			}
		}
	}
	
	/**
	 * Retrieves localized localized Siebel LOV list from database.
	 * It will be used in list page to fill selection filter, and to localize LOV string in grid.
	 * @param lovType 
	 * @param partnerType only used to retrieve activity status or activity substatus
	 * @param locale   
	 * @param serviceRequestLocaleService  
	 * @return object  
	 */
	private static LocalizedSiebelLOVListResult retrieveLocalizedSiebelLOVList(
			String lovType, String partnerType, ServiceRequestLocale serviceRequestLocaleService, Locale locale) {
		LocalizedSiebelLOVListResult result=null;
		LocalizedSiebelLOVListContract contract = ContractFactory.createLocalizedSiebelLOVListContract(
				lovType, partnerType, locale);
		try {
			result = serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(contract);
		} catch (Exception ex) {
			logger.debug("Exception"+ex.getMessage()); 
			result = new LocalizedSiebelLOVListResult();
			result.setLocalizedSiebelLOVList(new ArrayList<ListOfValues>(0));
		}
		return result;
	}
	
	/**
	 * localizes carrier, partDisposition, and other LOV attributes of a list PartLineItem.
	 * @param partLineItemList 
	 * @param serviceRequestLocaleService 
	 * @param locale 
	 * @throws Exception  
	 */
	public static void batchLocalizePart(List<PartLineItem> partLineItemList, ServiceRequestLocale serviceRequestLocaleService, Locale locale) throws Exception{
		logger.debug("------------------- Calling batchLocalizePart ----------------------- ");
		Map<String, String> carrierMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue(), null, serviceRequestLocaleService, locale);
		Map<String, String> partDispositionMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_PART_STATUS.getValue(), null, serviceRequestLocaleService, locale);
		Map<String, String> lineStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_ORDER_LINE_ITEM_STATUS.getValue(), null, serviceRequestLocaleService, locale);
		logger.debug("------------------- lineStatusMap ----------------------- "+lineStatusMap.toString());
		// CI5 RMA
		
		Map<String, String> lineRMAStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_ORDER_RMA_LINE_ITEM_STATUS.getValue(), null, serviceRequestLocaleService, locale);
		logger.debug("------------------- lineRMAStatusMap ----------------------- "+lineRMAStatusMap.toString());
		
		Map<String, String> sourceMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_PART_SOURCE.getValue(), null, serviceRequestLocaleService, locale);
		Map<String, String> errorCodeOneMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_ERROR_CODE_1.getValue(), null, serviceRequestLocaleService, locale);
		Map<String, String> errorCodeTwoMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_ERROR_CODE_2.getValue(), null, serviceRequestLocaleService, locale);
		for (PartLineItem part : partLineItemList) {
			String localizedCarrier=null;
			String localizedPartDisposition=null;
			String localizedLineStatus=null;
			// RMAS
			String localizedRMALineStatus=null;
			String localizedPartSource=null;
			String localizedErrorCodeOne=null;
			String localizedErrorCodeTwo=null;
			if (part.getCarrier() != null && part.getCarrier().getValue() != null) {
				localizedCarrier = carrierMap.get(part.getCarrier().getValue());
				if (localizedCarrier == null) {
					localizedCarrier = part.getCarrier().getValue();
				}
				part.getCarrier().setName(localizedCarrier);
			}
			
			if (part.getPartDisposition() != null && part.getPartDisposition().getValue() != null) {
				localizedPartDisposition = partDispositionMap.get(part.getPartDisposition().getValue());
				if (localizedPartDisposition == null) {
					localizedPartDisposition = part.getPartDisposition().getValue();
				}
				part.getPartDisposition().setName(localizedPartDisposition);
			}
			if (part.getLineStatus() != null && part.getLineStatus().getValue() != null) {
				localizedLineStatus = lineStatusMap.get(part.getLineStatus().getValue());
				if (localizedLineStatus == null) {
					localizedLineStatus = part.getLineStatus().getValue();
				}
				part.getLineStatus().setName(localizedLineStatus);
			}
			// Added for RMA
			
			if (part.getLineRMAStatus() != null && part.getLineRMAStatus().getValue() != null) {
				logger.debug("------------------- Inside RAM  METHOD ----------------------- ");
				logger.debug("------------------- Inside RAM  METHOD part.getLineRMAStatus() ########### ----------------------- "+part.getLineRMAStatus());
				logger.debug("------------------- Inside RAM  METHOD part.getLineRMAStatus().getValue() 00000 ----------------------- "+part.getLineRMAStatus().getValue());
				localizedRMALineStatus = lineRMAStatusMap.get(part.getLineRMAStatus().getValue());
				logger.debug("------------------- Inside RMA  METHOD localizedRMALineStatus 11111 ----------------------- "+localizedRMALineStatus);

				if (localizedRMALineStatus == null) {
					localizedRMALineStatus = part.getLineRMAStatus().getValue();
					logger.debug("------------------- Inside RMA  METHOD localizedRMALineStatus 2222 ----------------------- "+localizedRMALineStatus);
				}
				logger.debug("------------------- Inside RMA  METHOD localizedRMALineStatus Final ----------------------- "+localizedRMALineStatus);
				part.getLineRMAStatus().setName(localizedRMALineStatus);
			}
			
			if (part.getPartSource() != null && part.getPartSource().getValue() != null) {
				localizedPartSource = sourceMap.get(part.getPartSource().getValue());
				if (localizedPartSource == null) {
					localizedPartSource = part.getPartSource().getValue();
				}
				part.getPartSource().setName(localizedPartSource);
			}
			if (part.getErrorCode1() != null && part.getErrorCode1().getValue() != null) {
				localizedErrorCodeOne = errorCodeOneMap.get(part.getErrorCode1().getValue());
				if (localizedErrorCodeOne == null) {
					localizedErrorCodeOne = part.getErrorCode1().getValue();
				}
				part.getErrorCode1().setName(localizedErrorCodeOne);
			}
			if (part.getErrorCode2() != null && part.getErrorCode2().getValue() != null) {
				localizedErrorCodeTwo = errorCodeTwoMap.get(part.getErrorCode2().getValue());
				if (localizedErrorCodeTwo == null) {
					localizedErrorCodeTwo = part.getErrorCode2().getValue();
				}
				part.getErrorCode2().setName(localizedErrorCodeTwo);
			}
		}
	}
	
	/**
	 * @param paymentRequestList 
	 * @param serviceRequestLocaleService 
	 * @param locale 
	 */
	public static void batchLocalizePaymentRequest(List<Activity> paymentRequestList,
			ServiceRequestLocale serviceRequestLocaleService,
			Locale locale) {
		Map<String, String> paymentStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_PAYMENT_STATUS.getValue(), null, serviceRequestLocaleService, locale);
		Map<String, String> serviceStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.SERVICE_STATUS.getValue(), null, serviceRequestLocaleService, locale);
		String tempPaymentStatusName=null;
		String tempServiceStatus=null;
		for (Activity activity : paymentRequestList) {
			if(activity.getPayment()!= null && activity.getPayment().getPaymentStatus()!= null){
				tempPaymentStatusName = paymentStatusMap.get(activity.getPayment().getPaymentStatus().getValue());
				if (tempPaymentStatusName == null) {
					tempPaymentStatusName = activity.getPayment().getPaymentStatus().getValue();
				}
				activity.getPayment().getPaymentStatus().setName(tempPaymentStatusName);
			}
			if(activity.getServiceRequest()!=null){
				tempServiceStatus = serviceStatusMap.get(activity.getServiceRequest().getServiceRequestStatus());
				if (tempServiceStatus != null) {
					activity.getServiceRequest().setServiceRequestStatus(tempServiceStatus);
				}
			}
		}
	}
	
	
	/**
	 * Sort the technician list.
	 * @param partnerAccountId 
	 * @param partnerRequestsService 
	 * @throws Exception  
	 * @return lsit  
	 */
	
	public static List<AccountContact> retrieveTechnicianListSorted(String partnerAccountId,PartnerRequestsService partnerRequestsService) throws Exception{
		List<AccountContact> sortResult = new ArrayList<AccountContact>();		
		TechnicianListContract contract = ContractFactory.createTechnicianListContract(partnerAccountId);
		TechnicianListResult technicianListResult = partnerRequestsService.retrieveTechnicianList(contract);
		if(technicianListResult != null && technicianListResult.getAccountContactList() != null){
			List<AccountContact> technicianList = technicianListResult.getAccountContactList();
			CollectionSorter sorter = new CollectionSorter();
			String sortCriteria = null;
			List<String> propertysList = new ArrayList<String>();
			sortCriteria = "lastName:" + CollectionSorter.SORT_ASCENDING;
			propertysList.add(sortCriteria);
			sortCriteria = "firstName:" + CollectionSorter.SORT_ASCENDING;
			propertysList.add(sortCriteria);
			sortResult = sorter.sort(technicianList, propertysList);
		}
		return sortResult;
	}

	/**
	 * localizes payment type of a list of AdditionalPaymentRequests.
	 * @param additionalPaymentRequestList 
	 * @param serviceRequestLocaleService 
	 * @param locale 
	 */
	public static void batchLocalizeAdditionPayments(
								List<AdditionalPaymentRequest> additionalPaymentRequestList,
								ServiceRequestLocale serviceRequestLocaleService, 
								Locale locale) {

		Map<String, String> paymentTypeMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_ADDITIONAL_PAYMENT_TYPE.getValue(), null, serviceRequestLocaleService, locale);	
		
		for (AdditionalPaymentRequest payment : additionalPaymentRequestList) {
			String localizedPaymentType=null;
			if (payment.getPaymentType() != null && payment.getPaymentType().getValue() != null) {
				localizedPaymentType = paymentTypeMap.get(payment.getPaymentType().getValue());
				if (localizedPaymentType == null) {
					localizedPaymentType = payment.getPaymentType().getValue();
				}
				payment.getPaymentType().setName(localizedPaymentType);
			}			
		}
	}
	
	/**
	 * @param activityNoteList 
	 */
	public static void formatHTMLTagForNotes(List<ActivityNote> activityNoteList){
		if(activityNoteList!=null){
			for(ActivityNote note: activityNoteList){
				String detail = note.getNoteDetails()==null?"":note.getNoteDetails();
				detail = detail.replaceAll("\\\\r", "\r");
				detail = detail.replaceAll("\\\\n", "\n");
				note.setNoteDetails(detail);
				if (note.getNoteId() != null && StringUtil.isStringEmpty(note.getNoteId())) {
					note.setNoteId(null);
				}
			}
		}
	}
	
	
	/**
	 * @param payments 
	 */
	public static void checkAdditionalPaymentsWithEmptyID(List<AdditionalPaymentRequest> payments) {
		if(payments != null){
			for(AdditionalPaymentRequest payment : payments){
				if("".equals(payment.getPaymentRequestId()) || "null".equalsIgnoreCase(payment.getPaymentRequestId())){
					payment.setPaymentRequestId(null);
				}
			}			
		}
	}
	
	/**
	 * @param activityList 
	 * @param serviceRequestLocaleService 
	 * @param locale 
	 * @throws Exception 
	 */
	public static void localizeActivityListForServiceHistory(List<Activity> activityList,ServiceRequestLocale serviceRequestLocaleService, Locale locale) throws Exception {
		// localize problem code
		Map<String, String> problemCodes = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue(), null, serviceRequestLocaleService, locale);
		ControllerUtil.batchLocalizeActivityProblemCode(activityList, problemCodes);

		// localize activity status
		Map<String, String> activityStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue(), null, serviceRequestLocaleService, locale);
		ControllerUtil.batchLocalizeActivityStatus(activityList, activityStatusMap);

		// localize request type
		Map<String, String> serviceRequestTypeMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_TYPE.getValue(), null, serviceRequestLocaleService, locale);
		ControllerUtil.batchLocalizeServiceRequestType(activityList, serviceRequestTypeMap);

		// localize service type
		Map<String, String> serviceTypeMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.ENTITLEMENT_SERVICE_DETAILS.getValue(), null, serviceRequestLocaleService, locale);
		ControllerUtil.batchLocalizeServiceType(activityList, serviceTypeMap);
	}
	
	
	//Added for Partner Portal Request TAb Allow Child SR July Release 2014
	/**
	 * @param activitylist 
	 * @param allowChildSRMap 
	 */
	public static void localizeAllowChildSR(List<Activity> activitylist,Map<String,String> allowChildSRMap){
		for(Activity activity:activitylist){
			activity.setLocalizedCreateChildSR(activity.isCreateChildSR()==true?allowChildSRMap.get("true"):"");			
		}
	}
	
	//Added for Partner Portal Request TAb Allow Child SR July Release 2014
	/**
	 * @param result 
	 * @param sortCriteria 
	 */
	public static void sortActivityListOnCHildSR(ActivityListResult result,Map<String,Object> sortCriteria){
		try{
		logger.debug("[ in filterActivityListOnCHildSR]");
		if(!sortCriteria.containsKey("Activity.createChildSR") || result.getTotalcount()==0
				|| result.getActivityList()==null){
			return;
		}
		List<Activity> activities=result.getActivityList();
		logger.debug(String.format("[ contains Activity.createChildSR %s]",sortCriteria.containsKey("Activity.createChildSR")));
		
		Object value=sortCriteria.get("Activity.createChildSR");
		if(value==null){
			return;
		}
		final String sortValue=(String)sortCriteria.get("Activity.createChildSR");
		
		logger.debug(String.format("[ value Activity.createChildSR %s]",sortValue));
		
		
		
		
		Collections.sort(activities, new Comparator<Activity>(){

			@Override
			public int compare(Activity act1, Activity act2) {
				
					if(sortValue.equalsIgnoreCase(Pagination.ORDER_ASC) && act1.isCreateChildSR()){
						return -1;
					}else{
					return 1;
				}
				
			}
			
		}) ;
		/*List<Activity> final=
		result.setActivityList();*/
		
		logger.debug("[ out filterActivityListOnCHildSR]");
		}catch(Exception e){
			logger.debug("Exception"+e.getMessage()); 
			logger.debug("Exception in FilterActivityListOnCHildSR");
		}
	}
	
	
//	added for CI5 attachment feature
	/**
	 * @param fileMap 
	 * @return string 
	 */
	public static String getAttachmentXML(Map<String, CommonsMultipartFile> fileMap) {
		logger.debug("#####getting xml data");
		int rowId = 0;
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows>\n");
		Set<String> fileNameSet = fileMap.keySet();
		Iterator<String> fileNameIt = fileNameSet.iterator();
		while(fileNameIt.hasNext()) {
			String fileName = fileNameIt.next();
			CommonsMultipartFile file = fileMap.get(fileName);
			xml.append(" <row id=\""+ rowId +"\">\n");
			xml.append("  <cell><![CDATA[<a href=\"###\" onClick=\"openFile('"+file.getOriginalFilename()+"')\">" + "</a>]]></cell>\n");
			xml.append("  <cell><![CDATA["+ "date" +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ file.getSize() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ "format" +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ "image" +"]]></cell>\n");
//			xml.append("  <cell><![CDATA[" + "<img onMouseOver=\"this.style.cursor='pointer'\" src=\"" + contextPath + IMAGE_PATH + WRENCH_IMAGE_FILE_NAME + "\" onClick=\"deleteFile(" + ",'" + fileName + "')\"/>]]></cell>\n");
			xml.append(" </row>\n");
			rowId++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
//	end for CI5 attachment feature
	
	/**
	 * @param isIndirectPartner 
	 * @param isDirectPartner 
	 * @return string 
	 */
	public static String getPartnerType(boolean isIndirectPartner, boolean isDirectPartner) {
		String partnerType = null;
		if (isDirectPartner && isIndirectPartner) {
			partnerType = PartnerTypeEnum.BOTH.getValue();
		} else if (isDirectPartner && !isIndirectPartner) {
			partnerType = PartnerTypeEnum.DIRECT.getValue();
		} else if (!isDirectPartner && isIndirectPartner) {
			partnerType = PartnerTypeEnum.INDIRECT.getValue();
		}
		return partnerType;
	}
	
	/**
	 * @param fileName 
	 * @return string 
	 */
	public static String getContentTypeAccordingToFile(String fileName){
		if(fileName.indexOf(".jpg") > 0) {
			return "image/jpg";
	    }else if(fileName.indexOf(".gif") > 0) {
	    	return "image/gif";
	    }else if(fileName.indexOf(".pdf") > 0) {
	    	return "application/pdf";
	    	
	    }else if(fileName.indexOf(".html") > 0) {
	    	return "text/html";
	    }else if(fileName.indexOf(".zip") > 0) {
	    	return "application/zip";
	    }else if(fileName.indexOf(".txt") > 0 ){
	    	return "text/plain";
	    }else if(fileName.indexOf(".xls")>0){
	    	return "application/vnd.ms-excel";
	    }else if(fileName.indexOf(".pdf")>0){
	    	return "application/pdf";
	    }else if(fileName.indexOf(".doc")>0){
	    	return "application/msword";
	    }else if(fileName.indexOf(".xlsx")>0){
	    	return "application/vnd.ms-excel";
	    }else if(fileName.indexOf(".docx")>0){
	    	return "application/msword";
	    }else if(fileName.indexOf(".csv")>0){
	    	return "application/vnd.ms-excel";
	    }else if(fileName.indexOf(".vsd")>0){
	    	return "application/x-visio";
	    }else{
	    	return "text/csv";
	    }
	}
	
	/**
	 * @param contract  
	 * @param attachmentService  
	 * This method is used to delete the temporary created files.
	 */
	public static void amindRemoveTemporaryAttachmentFile(DeleteAttachmentContract contract,AttachmentService attachmentService){
		attachmentService.deleteTempFileAfterDownload(contract);
		
	}
}
