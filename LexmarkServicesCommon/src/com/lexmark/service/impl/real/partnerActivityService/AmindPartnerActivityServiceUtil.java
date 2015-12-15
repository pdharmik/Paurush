package com.lexmark.service.impl.real.partnerActivityService;

import static com.lexmark.service.impl.real.AmindSiebelCrmService.logger;
import static com.lexmark.service.impl.real.util.AmindPartnerDataManagerUtil.setPartnerFlags;
import static com.lexmark.service.impl.real.util.AmindPartnerDataManagerUtil.setPartnerTypes;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.lexmark.contract.ActivityListContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ActivityNote;
import com.lexmark.domain.AdditionalPaymentRequest;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.Debrief;
import com.lexmark.domain.DownloadClaim;
import com.lexmark.domain.DownloadClaimPart;
import com.lexmark.domain.DownloadRequest;
import com.lexmark.domain.DownloadRequestPart;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.OfflineModeAttachment;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.PartnerActivityCustomerProfile;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.domain.TechnicianInstruction;
import com.lexmark.result.ActivityListResult;
import com.lexmark.service.impl.real.domain.AccountFlag;
import com.lexmark.service.impl.real.domain.AccountType;
import com.lexmark.service.impl.real.domain.OfflineModeRequestAttachmentDO;
import com.lexmark.service.impl.real.domain.PartnerActivityAssetReadingDo;
import com.lexmark.service.impl.real.domain.PartnerActivityBase;
import com.lexmark.service.impl.real.domain.PartnerActivityContactInfoDo;
import com.lexmark.service.impl.real.domain.PartnerActivityCustomerProfileDo;
import com.lexmark.service.impl.real.domain.PartnerActivityDetailDo;
import com.lexmark.service.impl.real.domain.PartnerActivityDetailFlagDo;
import com.lexmark.service.impl.real.domain.PartnerActivityDetailInstalledAssetDo;
import com.lexmark.service.impl.real.domain.PartnerActivityDetailInstructionDo;
import com.lexmark.service.impl.real.domain.PartnerActivityDetailPartDo;
import com.lexmark.service.impl.real.domain.PartnerActivityDetailPartOptionsDo;
import com.lexmark.service.impl.real.domain.PartnerActivityDetailRepairedDeviceDo;
import com.lexmark.service.impl.real.domain.PartnerActivityDo;
import com.lexmark.service.impl.real.domain.PartnerActivityGridDo;
import com.lexmark.service.impl.real.domain.PartnerActivityHistory;
import com.lexmark.service.impl.real.domain.PartnerActivityHistoryParts;
import com.lexmark.service.impl.real.domain.PartnerActivityListMassUploadDebriefDo;
import com.lexmark.service.impl.real.domain.PartnerActivityListPartDo;
import com.lexmark.service.impl.real.domain.PartnerActivityListRecommPartDo;
import com.lexmark.service.impl.real.domain.PartnerActivityNoteInstructionDo;
import com.lexmark.service.impl.real.domain.PartnerActivityShipmentDataDo;
import com.lexmark.service.impl.real.domain.PartnerClaimDetailDo;
import com.lexmark.service.impl.real.domain.PartnerClaimDetailFlagDo;
import com.lexmark.service.impl.real.domain.PartnerClaimDetailPartDo;
import com.lexmark.service.impl.real.domain.PartnerDetailAttachmentBase;
import com.lexmark.service.impl.real.domain.PartnerDetailExpenseBase;
import com.lexmark.service.impl.real.domain.PartnerDetailNoteBase;
import com.lexmark.service.impl.real.domain.PartnerDetailOrderLineBase;
import com.lexmark.service.impl.real.domain.PartnerDetailPartBase;
import com.lexmark.service.impl.real.domain.PartnerOfflineModeActivityDo;
import com.lexmark.service.impl.real.domain.PartnerOfflineModeActivityListPartDo;
import com.lexmark.service.impl.real.domain.ServiceRequestActivityAttachmentsDo;
import com.lexmark.service.impl.real.domain.ServiceRequestOfflineModeRequestAttachmentDO;
import com.lexmark.service.impl.real.domain.ServiceRequestPartnerOfflineModeActivityDo;
import com.lexmark.service.impl.real.domain.ServiceRequestPartnerOfflineModeActivityListPartDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;

public class AmindPartnerActivityServiceUtil {
	

	
	public static List<Activity> convertActivityDOListToActivityList(List<PartnerActivityBase> activityList, ActivityListContract contract ) {
		
		List<Activity> resultActivityList = new ArrayList<Activity>();
		boolean massUpload=  false;
		long timeDifferance = (long) 6.48e+7;
		
		if(contract!=null)
		{
			massUpload = contract.isMassUploadRequest();
		}
		for (PartnerActivityBase partnerActivity : activityList) {

			Activity portalActivity = new Activity();
			if (partnerActivity instanceof PartnerActivityDo) {

				PartnerActivityDo activityDo = (PartnerActivityDo) partnerActivity;

				portalActivity.setActivityId(activityDo.getActivityId());
				portalActivity.setServiceAddress(populateServiceAddress(activityDo));
				portalActivity.setExpectedStartInterventionDate(activityDo.getCustomerRequestedResponseDateWithOutMarker());
				portalActivity.setCustomerRequestedResponseDate(activityDo.getCustomerRequestedResponseDate());
				if (LangUtil.isEmpty(activityDo.getAssetId())) {
					portalActivity.setResponseMetric(activityDo
							.getMadcResponseMatric());
				} else {
					portalActivity.setResponseMetric(activityDo
							.getResponseMatric());
				}
				portalActivity.setTechnician(populateTechnician(activityDo));
				portalActivity.setCustomerAccount(populateCustomerAccount(activityDo));
				portalActivity.setPartnerAccount(populatePartnerAccount(activityDo , null, activityDo.getPartneraccountTypes()));
				portalActivity.setActivityDate(partnerActivity.getActivityDateWithOutMarker());
				portalActivity.setDebriefStatus(activityDo.getDebriefStatus());
				portalActivity.setCommittedDate(activityDo.getCommittedDate());
				portalActivity.setServiceProviderETA(activityDo.getServiceProviderETA());
				portalActivity.setExpectedCompletionDate(activityDo.getExpectedCompletionDate());	
				portalActivity.setAgencyContacteMail(activityDo.getAgencyContacteMail());
				portalActivity.setAgencyContactFirstName(activityDo.getAgencyContactFirstName());
				portalActivity.setAgencyContactLastName(activityDo.getAgencyContactLastName());
				portalActivity.setParentSRNum(activityDo.getParentSRNum());
				portalActivity.setDispatchDate(activityDo.getDispatchDate());
				portalActivity.setBuildingIdPredebrief(activityDo.getBuildingIdPredebrief());
				portalActivity.setFloorIdPredebrief(activityDo.getFloorIdPredebrief());
				portalActivity.setxCoordinatePreDebrief(activityDo.getxCoordinatePreDebrief());
				portalActivity.setyCoordinatePreDebrief(activityDo.getyCoordinatePreDebrief());
				portalActivity.setLatitudePreDebrief(activityDo.getLatitudePreDebrief());
				portalActivity.setLongitutdePreDebrief(activityDo.getLongitutdePreDebrief());
				portalActivity.setSiteIdPredDebrief(activityDo.getSiteIdPredDebrief());
				portalActivity.setSitePredDebrief(activityDo.getSitePredDebrief());
				portalActivity.setZoneIdPredDebrief(activityDo.getZoneIdPredDebrief());
				portalActivity.setZonePredDebrief(activityDo.getZonePredDebrief());
				if (!(contract.getCurrentTimeStamp() == null) && !(activityDo.getLastStatusUpdate() == null || activityDo.getLastStatusUpdate().isEmpty()) 
						&& LangUtil.isNotEmpty(partnerActivity.getServiceRequestType()) && !(partnerActivity.getServiceRequestType().contains("Claim") 
								|| partnerActivity.getServiceRequestType().contains("Consumables") || partnerActivity.getServiceRequestType().contains("Fleet"))) {
					Date portalDate = contract.getCurrentTimeStamp();
					Date oldDate = new Date(activityDo.getLastStatusUpdate());
					
					long timeStemp = portalDate.getTime() - oldDate.getTime();
					if ((activityDo.getDebriefStatus() != null && !activityDo.getDebriefStatus().isEmpty()) && (timeStemp <= timeDifferance) 
							&& LangUtil.isNotEmpty(partnerActivity.getServiceRequestType()) && !(partnerActivity.getServiceRequestType().contains("Claim") 
									|| partnerActivity.getServiceRequestType().contains("Consumables") || partnerActivity.getServiceRequestType().contains("Fleet"))) {
						portalActivity.setCreateChildSR(true);
					}
				}
				
			}
			else if(partnerActivity instanceof PartnerActivityGridDo){

				PartnerActivityGridDo activityDo = (PartnerActivityGridDo) partnerActivity;

				portalActivity.setActivityId(activityDo.getActivityId());
				portalActivity.setServiceAddress(populateServiceAddress(activityDo));
				portalActivity.setExpectedStartInterventionDate(activityDo.getCustomerRequestedResponseDateWithOutMarker());
				portalActivity.setCustomerRequestedResponseDate(activityDo.getCustomerRequestedResponseDate());
				if (LangUtil.isEmpty(activityDo.getAssetId())) {
					portalActivity.setResponseMetric(activityDo
							.getMadcResponseMatric());
				} else {
					portalActivity.setResponseMetric(activityDo
							.getResponseMatric());
				}
				
				
								
				if(LangUtil.isNotEmpty(activityDo.getParentSRId())){
					portalActivity.setIsChildSR(true);
				}else{
					portalActivity.setIsChildSR(false);
				}
				
				portalActivity.setTechnician(populateTechnician(activityDo));
				portalActivity.setCustomerAccount(populateCustomerAccount(activityDo));
				portalActivity.setPartnerAccount(populatePartnerAccount(activityDo , null, null));
				portalActivity.setActivityDate(partnerActivity.getActivityDateWithOutMarker());
				portalActivity.setDebriefStatus(activityDo.getDebriefStatus());
				portalActivity.setCommittedDate(activityDo.getCommittedDate());
				portalActivity.setExpectedCompletionDate(activityDo.getExpectedCompletionDate());	
				portalActivity.setAgencyContacteMail(activityDo.getAgencyContacteMail());
				portalActivity.setAgencyContactFirstName(activityDo.getAgencyContactFirstName());
				portalActivity.setAgencyContactLastName(activityDo.getAgencyContactLastName());
				portalActivity.setServiceProviderETA(activityDo.getServiceProviderETA());
				portalActivity.setParentSRNum(activityDo.getParentSRNum());
				portalActivity.setDispatchDate(activityDo.getDispatchDate());
				portalActivity.setBuildingIdPredebrief(activityDo.getBuildingIdPredebrief());
				portalActivity.setFloorIdPredebrief(activityDo.getFloorIdPredebrief());
				portalActivity.setxCoordinatePreDebrief(activityDo.getxCoordinatePreDebrief());
				portalActivity.setyCoordinatePreDebrief(activityDo.getyCoordinatePreDebrief());
				portalActivity.setLatitudePreDebrief(activityDo.getLatitudePreDebrief());
				portalActivity.setLongitutdePreDebrief(activityDo.getLongitutdePreDebrief());
				portalActivity.setSiteIdPredDebrief(activityDo.getSiteIdPredDebrief());
				portalActivity.setSitePredDebrief(activityDo.getSitePredDebrief());
				portalActivity.setZoneIdPredDebrief(activityDo.getZoneIdPredDebrief());
				portalActivity.setZonePredDebrief(activityDo.getZonePredDebrief());
				if (!(contract.getCurrentTimeStamp() == null) && !(activityDo.getLastStatusUpdate() == null || activityDo.getLastStatusUpdate().isEmpty()) && LangUtil.isNotEmpty(partnerActivity.getServiceRequestType()) && !(partnerActivity.getServiceRequestType().contains("Claim") || partnerActivity.getServiceRequestType().contains("Consumables") || partnerActivity.getServiceRequestType().contains("Fleet"))) {
					Date portalDate = contract.getCurrentTimeStamp();
					Date oldDate = new Date(activityDo.getLastStatusUpdate());
					
					long timeStemp = portalDate.getTime() - oldDate.getTime();
					if ((activityDo.getDebriefStatus() != null && !activityDo.getDebriefStatus().isEmpty()) && (timeStemp <= timeDifferance) && LangUtil.isNotEmpty(partnerActivity.getServiceRequestType()) && !(partnerActivity.getServiceRequestType().contains("Claim") || partnerActivity.getServiceRequestType().contains("Consumables") || partnerActivity.getServiceRequestType().contains("Fleet"))) {
						portalActivity.setCreateChildSR(true);
					}
				}
				
				//Added for CR 14243. David Tsamalashvili. - Need To Uncomment
				portalActivity.setRecommendedPartList(populateRecommendedPartListPartnerActivity(activityDo));
				
			}
				
			else if(partnerActivity instanceof PartnerOfflineModeActivityDo) {
				
				PartnerOfflineModeActivityDo activityDo = (PartnerOfflineModeActivityDo) partnerActivity;

				portalActivity.setActivityId(activityDo.getActivityId());
				portalActivity.setServiceAddress(populateServiceAddress(activityDo));
//				portalActivity.setCustomerRequestedResponseDate(activityDo.getCustomerRequestedResponseDateWithOutMarker());
				portalActivity.setCustomerRequestedResponseDate(activityDo.getCustomerRequestedResponseDate());
				portalActivity.setResponseMetric(activityDo.getResponseMatric());
				portalActivity.setTechnician(populateTechnician(activityDo));
				portalActivity.setCustomerAccount(populateCustomerAccount(activityDo));
				portalActivity.setPartnerAccount(populatePartnerAccount(activityDo , null, activityDo.getPartneraccountTypes()));
				portalActivity.setActivityDate(partnerActivity.getActivityDateWithOutMarker());
				portalActivity.setDebriefStatus(activityDo.getDebriefStatus());
				portalActivity.setRfvErrors(activityDo.getRfvErrors());
				
				portalActivity.setOfflineModeAttachment(populateOfflineModeAttachment(activityDo));
				
				//Added for CR 14243. David Tsamalashvili.
				portalActivity.setRecommendedPartList(populateRecommendedPartList(activityDo));
			}
			
			else if(partnerActivity instanceof ServiceRequestPartnerOfflineModeActivityDo) {
				
				ServiceRequestPartnerOfflineModeActivityDo activityDo = (ServiceRequestPartnerOfflineModeActivityDo) partnerActivity;

				portalActivity.setActivityId(activityDo.getActivityId());
				portalActivity.setServiceAddress(populateServiceAddress(activityDo));
//				portalActivity.setCustomerRequestedResponseDate(activityDo.getCustomerRequestedResponseDateWithOutMarker());
				portalActivity.setCustomerRequestedResponseDate(activityDo.getCustomerRequestedResponseDate());
				portalActivity.setResponseMetric(activityDo.getResponseMatric());
				portalActivity.setTechnician(populateTechnician(activityDo));
				portalActivity.setCustomerAccount(populateCustomerAccount(activityDo));
				portalActivity.setPartnerAccount(populatePartnerAccount(activityDo , null, activityDo.getPartneraccountTypes()));
				portalActivity.setActivityDate(partnerActivity.getActivityDateWithOutMarker());
				portalActivity.setDebriefStatus(activityDo.getDebriefStatus());
				portalActivity.setRfvErrors(activityDo.getRfvErrors());
				
				portalActivity.setOfflineModeAttachment(populateOfflineModeAttachmentSR(activityDo));
				
			}
			
//			else if(partnerActivity instanceof PartnerOfflineModeActivityDo) {
//				
//				PartnerOfflineModeActivityDo activityDo = (PartnerOfflineModeActivityDo) partnerActivity;
//
//				portalActivity.setActivityId(activityDo.getActivityId());
//				portalActivity.setServiceAddress(populateServiceAddress(activityDo));
////				portalActivity.setCustomerRequestedResponseDate(activityDo.getCustomerRequestedResponseDateWithOutMarker());
//				portalActivity.setCustomerRequestedResponseDate(activityDo.getCustomerRequestedResponseDate());
//				portalActivity.setResponseMetric(activityDo.getResponseMatric());
//				portalActivity.setTechnician(populateTechnician(activityDo));
//				portalActivity.setCustomerAccount(populateCustomerAccount(activityDo));
//				portalActivity.setPartnerAccount(populatePartnerAccount(activityDo , null, activityDo.getPartneraccountTypes()));
//				portalActivity.setActivityDate(partnerActivity.getActivityDateWithOutMarker());
//				portalActivity.setDebriefStatus(activityDo.getDebriefStatus());
//				portalActivity.setRfvErrors(activityDo.getRfvErrors());
//				
//				portalActivity.setOfflineModeAttachment(populateOfflineModeAttachment(activityDo));
//				
//			}
			
			if (partnerActivity instanceof PartnerActivityHistory) {

				PartnerActivityHistory activityHistoryDo = (PartnerActivityHistory) partnerActivity;
				portalActivity.setActivityId(activityHistoryDo.getActivityId());
				portalActivity.setActualFailureCode(populateActualFailureCode(partnerActivity));
				portalActivity.setResolutionCode(populateResolutionCode(partnerActivity) );
				portalActivity.setActivityDate(partnerActivity.getActivityDate());
					if (activityHistoryDo.getParts() != null && activityHistoryDo.getParts().size() > 0) {
						portalActivity.setOrderPartList(populateServiceHistoryPartList(partnerActivity));
				}
			}
			
			portalActivity.setServiceRequest(populateServiceRequest(partnerActivity,massUpload));
			portalActivity.setActivityStatus(populateActivityStatus(partnerActivity));
			portalActivity.setActivitySubStatus(populateActivitySubStatus(partnerActivity));

			portalActivity.setActivityType(populateActivityType(partnerActivity));
			portalActivity.setServiceProviderReferenceNumber(partnerActivity.getServiceProviderReferenceNumber());
			portalActivity.setDebrief(populateDebrief(partnerActivity,false,null));
			resultActivityList.add(portalActivity);
		}

		return resultActivityList;
	}	
	
	//FIXME waiting for siebel.
	private static List<PartLineItem> populateRecommendedPartList(PartnerOfflineModeActivityDo activityDo) {
		List<PartLineItem> convertedParts = new ArrayList<PartLineItem>();
		boolean activityDoChecked = false;
		activityDoChecked = LangUtil.isNotEmpty(activityDo.getPartsList());
		boolean materialTypePrinter = false;
			if(activityDoChecked){
				for (PartnerOfflineModeActivityListPartDo parts : activityDo.getPartsList()) {
					PartLineItem recomandedItems = new PartLineItem();			
					
					if(parts.getMaterialLineType().equalsIgnoreCase("Printers")){
						materialTypePrinter = true;
					}
						boolean qntStringCheck = LangUtil.isNotEmpty(parts.getRecommendedQuantity());
						int qnt = 0;
							if(qntStringCheck){
								qnt = Integer.parseInt(parts.getRecommendedQuantity());
							}
						
						recomandedItems.setPartNumber(parts.getPartNumber());
						recomandedItems.setProductDescription(parts.getProductDescription());
						recomandedItems.setRecommendedQuantity(qnt);
						recomandedItems.setMachineTypeModel(parts.getMachineTypeModel());
						recomandedItems.setMaterialLineType(parts.getMaterialLineType());					
						recomandedItems.setTypePrinter(materialTypePrinter);
					convertedParts.add(recomandedItems);
				}
			}
		return convertedParts;
	}
	
	
	//FIXME waiting for siebel.
	
	private static List<PartLineItem> populateRecommendedPartListPartnerActivity(PartnerActivityGridDo activityDo) {
		List<PartLineItem> convertedParts = new ArrayList<PartLineItem>();
		boolean activityDoChecked = false;
		activityDoChecked = LangUtil.isNotEmpty(activityDo.getPartsList());
		boolean materialTypePrinter = false;	
		if(activityDoChecked){
				for (PartnerActivityListRecommPartDo parts : activityDo.getPartsList()) {
					PartLineItem recomandedItems = new PartLineItem();	
					
					if(parts.getMaterialLineType().equalsIgnoreCase("Printers")){
						materialTypePrinter = true;
						
					}
						boolean qntStringCheck = LangUtil.isNotEmpty(parts.getRecommendedQuantity());
						int qnt = 0;
							if(qntStringCheck){
								qnt = Integer.parseInt(parts.getRecommendedQuantity());
							}
						
						recomandedItems.setPartNumber(parts.getPartNumber());
						recomandedItems.setProductDescription(parts.getProductDescription());
						recomandedItems.setRecommendedQuantity(qnt);
						recomandedItems.setMachineTypeModel(parts.getMachineTypeModel());
						recomandedItems.setMaterialLineType(parts.getMaterialLineType());	
						recomandedItems.setTypePrinter(materialTypePrinter);
						convertedParts.add(recomandedItems);
					
					
				}
			}
		return convertedParts;
	}

	private static ListOfValues populateActivityType(PartnerActivityBase activityBase) {
		ListOfValues activityType = new ListOfValues();
		activityType.setValue(activityBase.getActivityType());
		return activityType;
	}
	
	private static ListOfValues populateActivityStatus(PartnerActivityBase activityBase) {
		ListOfValues activityStatus = new ListOfValues();
		activityStatus.setValue(activityBase.getActivityStatus());
		return activityStatus;
	}
	
	private static ListOfValues populateActivitySubStatus(PartnerActivityBase activityBase) {
		ListOfValues activitySubStatus = new ListOfValues();
		if(!StringUtils.isEmpty(activityBase.getActivitySubStatus())) 
		{
			activitySubStatus.setValue(activityBase.getActivitySubStatus());
		}
		else{
			activitySubStatus.setValue("");
		
		}
		return activitySubStatus;
	}
	
	private static ListOfValues populateActualFailureCode(PartnerActivityBase activityBase) {
		ListOfValues actualFailCode = new ListOfValues();
		if(!StringUtils.isEmpty(activityBase.getActualFailureCode()))
		{
			actualFailCode.setValue(activityBase.getActualFailureCode());
		} else {
			actualFailCode.setValue(activityBase.getOverrideActualFailureCode());
		}
		
		return actualFailCode;
	}
	
	private static ListOfValues populateResolutionCode(PartnerActivityBase activityBase) {
		ListOfValues resolutionCode = new ListOfValues();
		resolutionCode.setValue(activityBase.getResolutionCode());
		return resolutionCode;
	}

	private static Account populateCustomerAccount (PartnerActivityBase activityBase) {
		Account customerAccount = new Account();
		customerAccount.setAccountId(activityBase.getCustomerAccountId());
		if(StringUtils.isEmpty(activityBase.getCustomerAccountName())) {
			customerAccount.setAccountName(activityBase.getOverridecustomerAccountName());
		} else {
			customerAccount.setAccountName(activityBase.getCustomerAccountName());
		}
		
		return customerAccount;
	}
	private static AccountContact populateTechnician (PartnerActivityBase activityBase) {
		AccountContact technician = new AccountContact();
		/* Technician */
		if (activityBase.getTechnicianContactId() != null) {
			technician.setContactId(activityBase.getTechnicianContactId());
			technician.setContactId(activityBase.getTechnicianContactId());
			technician.setFirstName(activityBase.getTechnicianContactFirstName());
			technician.setLastName(activityBase.getTechnicianContactLastName());
			technician.setTechnicianName(activityBase.getTechnicianName());
		}
		return technician;
	}
	private static List<PartLineItem> populateServiceHistoryPartList (PartnerActivityBase activityBase) {
		List<PartLineItem> orderParts = new ArrayList<PartLineItem>();
		if(activityBase instanceof PartnerActivityHistory) {
			PartnerActivityHistory  activityHistoryDo = (PartnerActivityHistory) activityBase;
			for(PartnerActivityHistoryParts activityPart : activityHistoryDo.getParts()) {
				PartLineItem portalOrderPart = new PartLineItem();
				portalOrderPart.setQuantity(activityPart.getQuantity());
				portalOrderPart.setPartName(activityPart.getPartName());
				portalOrderPart.setPartNumber(activityPart.getPartNumber());
				ListOfValues partDesposition = new ListOfValues();
				partDesposition.setValue(activityPart.getPartDisposition());
				portalOrderPart.setPartDisposition(partDesposition);
				ListOfValues actualFailureCode1 = new ListOfValues();
				actualFailureCode1.setValue(activityPart.getErrorCode1());
				portalOrderPart.setErrorCode1(actualFailureCode1);

				ListOfValues actualFailureCode2 = new ListOfValues();
				actualFailureCode2.setValue(activityPart.getErrorCode2());
				portalOrderPart.setErrorCode2(actualFailureCode2);

				orderParts.add(portalOrderPart);
			}
		}

		return orderParts;
	}
	
	private static ServiceRequest populateServiceRequest (PartnerActivityBase activityBase, boolean massUpload) {
		ServiceRequest serviceRequest = new ServiceRequest();
		Asset asset = new Asset();
		boolean isActivityListCall = false;
		
		
		asset.setAssetId(activityBase.getAssetId());
		serviceRequest.setId(activityBase.getServiceRequestId());
		serviceRequest.setServiceRequestNumber(activityBase.getServiceRequestNumber());

		ListOfValues serviceRequestTypeLOV = new ListOfValues();
		serviceRequestTypeLOV.setType(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_TYPE.getValue());
		if(activityBase.getServiceRequestType() != null && activityBase.getServiceRequestType().contains("Claim"))  {
			serviceRequestTypeLOV.setValue("Claim Request");
		} else if (activityBase.getServiceRequestType() !=null && activityBase.getServiceRequestType().contains("Fleet")) {
			serviceRequestTypeLOV.setValue("Change Request");
		} else {
			serviceRequestTypeLOV.setValue("Service Request");
		}
		
		serviceRequest.setServiceRequestType(serviceRequestTypeLOV);
		serviceRequest.setServiceRequestDate(activityBase.getServiceRequestDate());
		serviceRequest.setProblemDescription(activityBase.getProblemDescription());
		serviceRequest.setServiceRequestStatus(activityBase.getServiceRequestStatus());
		serviceRequest.setPrimaryContact(populatePrimaryContact(activityBase));
		
		if(activityBase instanceof PartnerActivityDo) {
			PartnerActivityDo activityDo = (PartnerActivityDo)activityBase;
			
			if(!StringUtils.isEmpty(activityDo.getAssetSerialNumber())) {
				asset.setSerialNumber(activityDo.getAssetSerialNumber().toUpperCase());
			} else {
				asset.setSerialNumber(activityDo.getAssetSerialNumberOverride().toUpperCase());
			}
			
			serviceRequest.setAttachments(populateServiceRequestAttachments(activityBase.getServiceRequestAttachmentss()));
			serviceRequest.setServiceRequestDate(activityBase.getActivityDateWithOutMarker());
			serviceRequest.setRequestType(activityDo.getServiceRequestType());
			asset.setPartList(populatePartList(activityDo, massUpload));
			asset.setActivityNumber(activityDo.getAssetActivityNumber());
			asset.setInstallDate(activityDo.getAssetInstallDate());
			serviceRequest.setStatusDetail(activityDo.getStatusDetail());
			serviceRequest.setProjectName(activityDo.getProjectName()); 
			serviceRequest.setActualStartDate(activityDo.getProjectStartDate()); 
			asset.setStoreFrontName(activityDo.getStoreFrontName());
			asset.setCustomerReportingName(activityDo.getCustomerReportingName());
			asset.setAssetField1(activityDo.getAssetField1());
			asset.setAssetField2(activityDo.getAssetField2());
			asset.setAssetField3(activityDo.getAssetField3());
			
			//Critical Path CR
			asset.setDeinstSerialNumber(activityDo.getDeinstSerialNumber());
			asset.setDeinstPartNumber(activityDo.getDeinstPartNumber());
			asset.setDeinstModel(activityDo.getDeinstModel());
			asset.setDeinstBrand(activityDo.getDeinstBrand());
			asset.setDeinstIpAddress(activityDo.getDeinstIpAddress());
			asset.setDeinstHostName(activityDo.getDeinstHostName());
			asset.setDeinstRemovalDate(activityDo.getDeinstRemovalDate());
			asset.setDeinstComments(activityDo.getDeinstComments());
			asset.setDeinstAssetTag(activityDo.getDeinstAssetTag());
			asset.setDeviceCondition(activityDo.getDeinstDeviceCondition());
			
			if (LangUtil.isEmpty(activityDo.getAssetId())) {
				asset.setProductLine(activityBase.getMadcProductLine());
				asset.setProductTLI(activityBase.getMadcProductTLI());
				asset.setMachineTypeModel(activityBase.getMadcMachineTypeModel());
				asset.setModelNumber(activityBase.getMadcMachineTypeModel());
				asset.setProductModelNumber(activityBase.getMadcProductLine());
			} else {
				asset.setProductLine(activityBase.getAssetProductLine());
				asset.setProductTLI(activityBase.getAssetProductTLI());
				asset.setMachineTypeModel(activityDo.getAssetMachineTypeModel());
				asset.setProductModelNumber(activityBase.getAssetProductLine());
				if (!StringUtils.isEmpty(activityBase.getAssetMachineTypeModel())) {
					asset.setModelNumber(activityBase.getAssetMachineTypeModel());
				} else {
					asset.setModelNumber(activityBase.getMachineTypeModelOverride());
				}
			}
			asset.setDeviceName(activityBase.getMadcProductLine());
			if(massUpload){
			serviceRequest.setMassUploadContact(populateMassUploadContact(activityBase));
			serviceRequest.setMassUploadserviceProviderReferenceNum(activityBase.getMassUploadServiceProviderReferenceNum());
			asset.setPageCounts(populateMassUploadPageCount(activityDo));
			asset.setNetworkConnectedFlag(activityBase.getMassUploadNetworkConnected());    // boolean value
			asset.setIpAddress(activityBase.getMassUploadIPAddress());
			asset.setGateway(activityBase.getMassUploadIPGateway());
			asset.setSubnet(activityBase.getMassUploadIPSubmask());
			asset.setIpV6(activityBase.getMassUploadIPv6());
			asset.setMacAddress(activityBase.getMassUploadAssetMacAddress());
			asset.setPrinterWorkingCondition(activityBase.getMassUploadAssetDeviceCondition());    //asset device condition
			asset.setFaxConnected(activityBase.getMassUploadFaxConnected());
			asset.setFaxPortNumber(activityBase.getMassUploadFaxPortNumber());
			asset.setHostName(activityBase.getMassUploadHostName());
			asset.setComputerName(activityBase.getMassUploadComputerName());
			asset.setPortNumber(activityBase.getMassUploadPortNumber());
			asset.setWiringClosestNetworkPoint(activityBase.getMassUploadWiringClosetNetwkPt());
			asset.setAssetCostCenter(activityBase.getMassUploadAssetCostCenter());
			asset.setDeviceTag(activityBase.getMassUploadCustomerDeviceTag());
			asset.setDepartmentId(activityBase.getMassUploadDeptId());
			asset.setDepartment(activityBase.getMassUploadDeptName());   //mass upload department name
			asset.setFirmware(activityBase.getMassUploadFirmware());
			asset.setNetworkTopology(activityBase.getMassUploadNetworkTopology());
			asset.setOperatingSystem(activityBase.getMassUploadOS());
			asset.setOperatingSystemVersion(activityBase.getMassUploadOSVersion());
			asset.setTopBill(activityBase.getMassUploadTopBill());
			asset.setSpecialUsage(activityBase.getMassUploadSpecialUsage());
			asset.setDescription(activityBase.getMassUploadAssetDesc());
			asset.setAccount(populateMassUploadAccount(activityBase));
			asset.setMassUploadInstallAddress(populateMassUploadGenericAddress(activityBase));
			massUpload= false;
			}
			isActivityListCall = true;
		} 
		else if(activityBase instanceof PartnerActivityGridDo){
			PartnerActivityGridDo activityDo = (PartnerActivityGridDo)activityBase;
			
			if(!StringUtils.isEmpty(activityDo.getAssetSerialNumber())) {
				asset.setSerialNumber(activityDo.getAssetSerialNumber().toUpperCase());
			} else {
				asset.setSerialNumber(activityDo.getAssetSerialNumberOverride().toUpperCase());
			}
			
			serviceRequest.setServiceRequestDate(activityBase.getActivityDateWithOutMarker());
			serviceRequest.setRequestType(activityDo.getServiceRequestType());
			asset.setPartList(populatePartList(activityDo, massUpload));
			asset.setActivityNumber(activityDo.getAssetActivityNumber());
			asset.setInstallDate(activityDo.getAssetInstallDate());
			serviceRequest.setStatusDetail(activityDo.getStatusDetail());
			serviceRequest.setProjectName(activityDo.getProjectName()); 
			serviceRequest.setActualStartDate(activityDo.getProjectStartDate()); 
			asset.setStoreFrontName(activityDo.getStoreFrontName());
			asset.setCustomerReportingName(activityDo.getCustomerReportingName());
			asset.setAssetField1(activityDo.getAssetField1());
			asset.setAssetField2(activityDo.getAssetField2());
			asset.setAssetField3(activityDo.getAssetField3());
			if (LangUtil.isEmpty(activityDo.getAssetId())) {
				asset.setProductLine(activityBase.getMadcProductLine());
				asset.setProductTLI(activityBase.getMadcProductTLI());
				asset.setMachineTypeModel(activityBase.getMadcMachineTypeModel());
				asset.setModelNumber(activityBase.getMadcMachineTypeModel());
				asset.setProductModelNumber(activityBase.getMadcProductLine());
			} else {
				asset.setProductLine(activityBase.getAssetProductLine());
				asset.setProductTLI(activityBase.getAssetProductTLI());
				asset.setMachineTypeModel(activityDo.getAssetMachineTypeModel());
				asset.setProductModelNumber(activityBase.getAssetProductLine());
				if (!StringUtils.isEmpty(activityBase.getAssetMachineTypeModel())) {
					asset.setModelNumber(activityBase.getAssetMachineTypeModel());
				} else {
					asset.setModelNumber(activityBase.getMachineTypeModelOverride());
				}
			}
			asset.setDeviceName(activityBase.getMadcProductLine());
			if(massUpload){
			serviceRequest.setMassUploadContact(populateMassUploadContact(activityBase));
			serviceRequest.setMassUploadserviceProviderReferenceNum(activityBase.getMassUploadServiceProviderReferenceNum());
			asset.setPageCounts(populateMassUploadPageCount(activityDo));
			asset.setNetworkConnectedFlag(activityBase.getMassUploadNetworkConnected());    // boolean value
			asset.setIpAddress(activityBase.getMassUploadIPAddress());
			asset.setGateway(activityBase.getMassUploadIPGateway());
			asset.setSubnet(activityBase.getMassUploadIPSubmask());
			asset.setIpV6(activityBase.getMassUploadIPv6());
			asset.setMacAddress(activityBase.getMassUploadAssetMacAddress());
			asset.setPrinterWorkingCondition(activityBase.getMassUploadAssetDeviceCondition());    //asset device condition
			asset.setFaxConnected(activityBase.getMassUploadFaxConnected());
			asset.setFaxPortNumber(activityBase.getMassUploadFaxPortNumber());
			asset.setHostName(activityBase.getMassUploadHostName());
			asset.setComputerName(activityBase.getMassUploadComputerName());
			asset.setPortNumber(activityBase.getMassUploadPortNumber());
			asset.setWiringClosestNetworkPoint(activityBase.getMassUploadWiringClosetNetwkPt());
			asset.setAssetCostCenter(activityBase.getMassUploadAssetCostCenter());
			asset.setDeviceTag(activityBase.getMassUploadCustomerDeviceTag());
			asset.setDepartmentId(activityBase.getMassUploadDeptId());
			asset.setDepartment(activityBase.getMassUploadDeptName());   //mass upload department name
			asset.setFirmware(activityBase.getMassUploadFirmware());
			asset.setNetworkTopology(activityBase.getMassUploadNetworkTopology());
			asset.setOperatingSystem(activityBase.getMassUploadOS());
			asset.setOperatingSystemVersion(activityBase.getMassUploadOSVersion());
			asset.setTopBill(activityBase.getMassUploadTopBill());
			asset.setSpecialUsage(activityBase.getMassUploadSpecialUsage());
			asset.setDescription(activityBase.getMassUploadAssetDesc());
			asset.setAccount(populateMassUploadAccount(activityBase));
			asset.setMassUploadInstallAddress(populateMassUploadGenericAddress(activityBase));
			massUpload= false;
			}
			
			//Critical Path CR
			asset.setDeinstSerialNumber(activityBase.getDeinstSerialNumber());
			asset.setDeinstPartNumber(activityBase.getDeinstPartNumber());
			asset.setDeinstModel(activityBase.getDeinstModel());
			asset.setDeinstBrand(activityBase.getDeinstBrand());
			asset.setDeinstIpAddress(activityBase.getDeinstIpAddress());
			asset.setDeinstHostName(activityBase.getDeinstHostName());
			asset.setDeinstRemovalDate(activityBase.getDeinstRemovalDate());
			asset.setDeinstComments(activityBase.getDeinstComments());
			asset.setDeinstAssetTag(activityBase.getDeinstAssetTag());
			asset.setDeviceCondition(activityBase.getDeviceCondition());
			
			isActivityListCall = true;
		
		}
		else if(activityBase instanceof PartnerOfflineModeActivityDo) {
			PartnerOfflineModeActivityDo activityDo = (PartnerOfflineModeActivityDo)activityBase;
			
			if(!StringUtils.isEmpty(activityDo.getAssetSerialNumber())) {
				asset.setSerialNumber(activityDo.getAssetSerialNumber());
			} else {
				asset.setSerialNumber(activityDo.getAssetSerialNumberOverride());
			}
			
			if(!StringUtils.isEmpty(activityBase.getAssetMachineTypeModel()))
			{
				asset.setModelNumber(activityBase.getAssetMachineTypeModel());
			} else {
				asset.setModelNumber(activityBase.getMachineTypeModelOverride());
			}

			serviceRequest.setRequestType(activityDo.getServiceRequestType());
			serviceRequest.setStatusType(populateStatusType(activityDo));
			asset.setPartList(populatePartList(activityDo,false));
			asset.setMachineTypeModel(activityDo.getAssetMachineTypeModel());
			asset.setActivityNumber(activityDo.getAssetActivityNumber());
			asset.setInstallDate(activityDo.getAssetInstallDate());
			serviceRequest.setStatusDetail(activityDo.getStatusDetail());
			serviceRequest.setProjectName(activityDo.getProjectName());
			serviceRequest.setActualStartDate(activityDo.getProjectStartDate());
			asset.setStoreFrontName(activityDo.getStoreFrontName());
			asset.setCustomerReportingName(activityDo.getCustomerReportingName());
			asset.setProductModelNumber(activityDo.getProductModelNumber());
			asset.setDeviceName(activityDo.getMadcProductLine());
			isActivityListCall = false;
		} 
		else if(activityBase instanceof ServiceRequestPartnerOfflineModeActivityDo) {
			ServiceRequestPartnerOfflineModeActivityDo activityDo = (ServiceRequestPartnerOfflineModeActivityDo)activityBase;
			
			if(!StringUtils.isEmpty(activityDo.getAssetSerialNumber())) {
				asset.setSerialNumber(activityDo.getAssetSerialNumber());
			} else {
				asset.setSerialNumber(activityDo.getAssetSerialNumberOverride());
			}
			
			if(!StringUtils.isEmpty(activityBase.getAssetMachineTypeModel()))
			{
				asset.setModelNumber(activityBase.getAssetMachineTypeModel());
			} else {
				asset.setModelNumber(activityBase.getMachineTypeModelOverride());
			}

			serviceRequest.setRequestType(activityDo.getServiceRequestType());
			serviceRequest.setStatusType(populateSRStatusType(activityDo));
			asset.setPartList(populatePartList(activityDo,false));
			asset.setMachineTypeModel(activityDo.getAssetMachineTypeModel());
			asset.setActivityNumber(activityDo.getAssetActivityNumber());
			asset.setInstallDate(activityDo.getAssetInstallDate());
			serviceRequest.setStatusDetail(activityDo.getStatusDetail());
			serviceRequest.setProjectName(activityDo.getProjectName());
			serviceRequest.setActualStartDate(activityDo.getProjectStartDate());
			asset.setStoreFrontName(activityDo.getStoreFrontName());
			asset.setCustomerReportingName(activityDo.getCustomerReportingName());
			asset.setProductModelNumber(activityDo.getProductModelNumber());
			asset.setDeviceName(activityDo.getMadcProductLine());
			isActivityListCall = false;
		} 
		if(activityBase instanceof PartnerActivityHistory) {
			PartnerActivityHistory activityHistoryDo = (PartnerActivityHistory) activityBase;
			serviceRequest.setServiceRequestDate(activityHistoryDo.getServiceRequestDateWithOutMarker());
			serviceRequest.setServiceRequestor(activityHistoryDo.getServiceRequestor());
			
			asset.setProblemDescription(activityHistoryDo.getProblemDescription());
			asset.setSerialNumber(activityHistoryDo.getAssetSerialNumber());
			asset.setModelNumber(activityBase.getAssetMachineTypeModel());
			asset.setCustomerReportingName(activityHistoryDo.getCustomerReportingName());
			isActivityListCall = false;
			
		}else if(activityBase instanceof PartnerActivityDetailDo) {
			PartnerActivityDetailDo activityDetail = (PartnerActivityDetailDo) activityBase;
			serviceRequest.setReferenceNumber(activityDetail.getServiceProviderReferenceNumber());
			serviceRequest.setCustomerReferenceNumber(activityDetail.getCustomerReferenceNumber());
			serviceRequest.setSecondaryContact(populateSecondaryContact(activityDetail));
			//serviceRequest.setPrimarySuppliesContact(populatePrimarySuppliesContact(activityBase));
			serviceRequest.setSecondarySuppliesContact(populateSecondarySuppliesContact(activityBase));
			serviceRequest.setProjectPhase(activityDetail.getProjectPhase());
			serviceRequest.setProjectName(activityDetail.getProjectName());
			
			serviceRequest.setServiceRequestDate(activityDetail.getActivityDate());
			serviceRequest.setResolveWithin(activityDetail.getExpectedStartDate());
			serviceRequest.setContactInfoForDevice(populateContact(activityDetail.getContacts()));
			if(!StringUtils.isEmpty(activityDetail.getRepairedSerialNumber()) &&
					!StringUtils.isEmpty(activityDetail.getInstalledAssetSerialNumber())) {
				if(matchInstallAssetAndRepairedAsset(activityDetail)) {
					populateInstallAssetActivityDetails(activityDetail,asset);
				} else {
					populateRepairedAsset(activityDetail, asset);
				}
			} else if (!StringUtils.isEmpty(activityDetail.getRepairedSerialNumber()) && 
					StringUtils.isEmpty(activityDetail.getInstalledAssetSerialNumber()))
			{
				populateRepairedAsset(activityDetail, asset);
			} else {
				populateInstallAssetActivityDetails(activityDetail,asset);
			}
			asset.setModelNumber(activityDetail.getAssetMachineTypeModel());
			asset.setMachineTypeModel(activityDetail.getAssetMachineTypeModel());
			asset.setProductModelNumber(activityDetail.getProductModelNumber());
			asset.setNewAddress(populateDetailsNewAddress(activityDetail));
			asset.setRepairedAssetModelNumber(activityDetail.getRepairedAssetModelNumber());
			asset.setDepartment(activityDetail.getAssetDepartment());
			asset.setNetworkConnectedFlag(activityDetail.getAssetNetworkConnectedFlag());
			asset.setIpAddress(activityDetail.getAssetIPAddress());
			asset.setDeviceTag(activityDetail.getRepairedAssetDeviceTag());
			asset.setDescription(activityDetail.getAssetDescription());
			asset.setSubnet(activityDetail.getAssetSubnetMask());
			asset.setMacAddress(activityDetail.getAssetMac());
			asset.setAssetCostCenter(activityDetail.getAssetCostCenter());
			asset.setTopBill(activityDetail.getAssetTopBill());
			asset.setIpV6(activityDetail.getIpV6());
			asset.setPortNumber(activityDetail.getPortNumber());
			asset.setHostName(activityDetail.getAssetHostName());
			asset.setDevTypeModelNumber(activityDetail.getAssetDevTypeModelNumber());
			asset.setWiringClosestNetworkPoint(activityDetail.getAssetWiringClosestNetworkPoint());
			asset.setFaxConnectedValue(activityDetail.getAssetFaxConnected());
			asset.setFaxPortNumber(activityDetail.getAssetFaxPortNumber());
			asset.setInstallDate(activityDetail.getAssetInstallDate());
			asset.setOperatingSystem(activityDetail.getAssetOperatingSystem());
			asset.setSpecialUsage(activityDetail.getAssetSpecialUsage());
			asset.setOperatingSystemVersion(activityDetail.getAssetOperatingSystemVersion());
			asset.setAssetLifeCycle(activityDetail.getAssetLifeCycle());
			asset.setFirmware(activityDetail.getAssetFirmware());
			asset.setNetworkTopology(activityDetail.getAssetNetworkTopology());
			asset.setAssetHierarchyLevel(activityDetail.getAssetHierarchyLevel());
			asset.setInstallAddress(populateDetailsInstallAddress(activityDetail));
			asset.setPageCounts(populatePageCounts(activityDetail));
			asset.setDeviceName(activityDetail.getAssetDeviceName());
			asset.setProductImageURL(activityDetail.getAssetProductImageURL());
			asset.setCustomerAccount(populateAssetCustomerAccount(activityDetail));
			asset.setPickupAddress(populatePickupAddress(activityDetail));
			asset.setGateway(activityDetail.getAssetIPGateway());
			asset.setMoveToAddressGrouped(activityDetail.getMoveToAddressGrouped());
			
			asset.setAssetField1(activityDetail.getAssetField1());
			asset.setAssetField2(activityDetail.getAssetField2());
			asset.setAssetField3(activityDetail.getAssetField3());
			asset.setPrinterWorkingCondition(activityDetail.getDeviceCondition());
			asset.setDeviceName(activityDetail.getMadcProductLine());
		
			if(!StringUtils.isEmpty(activityDetail.getAssetSerialNumber())) {
				asset.setSerialNumber(activityDetail.getAssetSerialNumber());
			} else {
				asset.setSerialNumber(activityDetail.getAssetSerialNumberOverride());
			}
			
			asset.setMoveFromAddress(populateMoveFromAddress(activityDetail));
			isActivityListCall = false;
			
		} else if (activityBase instanceof PartnerClaimDetailDo) {
			PartnerClaimDetailDo claimDetail = (PartnerClaimDetailDo) activityBase;
			serviceRequest.setServiceRequestStatus(claimDetail.getActivityStatus());
			
			asset.setInstallAddress(populateInstallAddress(claimDetail));
			
			if(!StringUtils.isEmpty(claimDetail.getAssetSerialNumber())) {
				asset.setSerialNumber(claimDetail.getAssetSerialNumber());
			} else {
				asset.setSerialNumber(claimDetail.getAssetSerialNumberOverride());
			}
			
			if(!StringUtils.isEmpty(activityBase.getAssetMachineTypeModel()))
			{
				asset.setModelNumber(activityBase.getAssetMachineTypeModel());
			} else {
				asset.setModelNumber(activityBase.getMachineTypeModelOverride());
			}
			isActivityListCall = false;
		}
		 
		if(!isActivityListCall)
		{
			if (LangUtil.isEmpty(activityBase.getAssetId())) {
				asset.setProductLine(activityBase.getMadcProductLine());
				asset.setProductTLI(activityBase.getMadcProductTLI());
				
			} else {
				asset.setProductLine(activityBase.getAssetProductLine());
				asset.setProductTLI(activityBase.getAssetProductTLI());
				
			}
		//asset.setProductLine(activityBase.getAssetProductLine());
		//asset.setProductTLI(activityBase.getAssetProductTLI());
		}
		serviceRequest.setAsset(asset);
		return serviceRequest;
	}
	
	private static List<Attachment> populateServiceRequestAttachments(ArrayList<ServiceRequestActivityAttachmentsDo> serviceRequestAttachments) {
		List<Attachment> convertedSRAttachments = new ArrayList<Attachment>();
		boolean attachCheck = LangUtil.isNotEmpty(serviceRequestAttachments);
		
			if(attachCheck){
					for(ServiceRequestActivityAttachmentsDo item : serviceRequestAttachments){
						Attachment attachmentItems = new Attachment();						
						String visibilityType = item.getVisibility();
						
						if(visibilityType.equalsIgnoreCase("Partner") || visibilityType.equalsIgnoreCase("Both")){
							if(!StringUtils.isEmpty(item.getAttachmentName())){
								boolean sizeCheck = LangUtil.isNotEmpty(item.getActivityFileSize());
								
								if(LangUtil.isNotEmpty(item.getAttachmentName())){
									attachmentItems.setAttachmentName(item.getAttachmentName());
								}else{
									String siebelFileName = item.getActivityFileName();
									String portalFileName = siebelFileName.substring(siebelFileName.indexOf("@")+1);
									String fileName = portalFileName+"."+item.getActivityFileExt();
									attachmentItems.setAttachmentName(fileName);
								}
								attachmentItems.setActivityId(item.getActivityId());
								attachmentItems.setActualFileName(item.getActivityFileName() + "." + item.getActivityFileExt());
								attachmentItems.setType(item.getType());
								attachmentItems.setFileSourcePath(item.getActivityFileSrcPath());	
								attachmentItems.setId(item.getAttachmentId());
								
								if(sizeCheck){
									attachmentItems.setSize(Integer.parseInt(item.getActivityFileSize().replace(",", "")));
								}
							
							convertedSRAttachments.add(attachmentItems);
							}
						}
					}
			}
		
		return convertedSRAttachments;
	}

	private static GenericAddress populateMoveFromAddress(
			PartnerActivityDetailDo activityDo) {
		
		GenericAddress moveFromAddress = new GenericAddress();
		moveFromAddress.setAddressId(activityDo.getMoveFromMadcAddressID());
		moveFromAddress.setAddressLine1(activityDo.getMoveFromMadcAddressLine1());
		moveFromAddress.setAddressLine2(activityDo.getMoveFromMadcAddressLine2());
		moveFromAddress.setCity(activityDo.getMoveFromMadcCity());
		moveFromAddress.setCountry(activityDo.getMoveFromMadcCountry());
		moveFromAddress.setCountryISOCode(activityDo.getMoveFromMadcISOCountryCode());
		moveFromAddress.setCounty(activityDo.getMoveFromMadcCounty());
		moveFromAddress.setDistrict(activityDo.getMoveFromMadcDistrict());
		moveFromAddress.setState(activityDo.getMoveFromMadcState());
		moveFromAddress.setStateFullName(activityDo.getMoveFromMadcStateFullName());
		moveFromAddress.setOfficeNumber(activityDo.getMoveFromMadcHouseNum());
		moveFromAddress.setErrorMsgForCleansing(activityDo.getMoveFromMadcFirstLogicErrorMessage());
		moveFromAddress.setLatitude(activityDo.getMoveFromMadcLatitude());
		moveFromAddress.setLongitude(activityDo.getMoveFromMadcLongitude());
		moveFromAddress.setRegion(activityDo.getMoveFromMadcRegion());
		moveFromAddress.setPostalCode(activityDo.getMoveFromMadcPostalCode());
		moveFromAddress.setProvince(activityDo.getMoveFromMadcProvince());
		moveFromAddress.setPhysicalLocation1(activityDo.getMoveFromMadcBuilding());
		moveFromAddress.setPhysicalLocation2(activityDo.getMoveFromMadcFloor());
		moveFromAddress.setPhysicalLocation3(activityDo.getMoveFromMadcOffice());
		
		return moveFromAddress;
	}

	public static GenericAddress populateMassUploadGenericAddress(PartnerActivityBase activityBase)
	{
		
		GenericAddress primaryAddress = new GenericAddress();
		primaryAddress.setAddressLine1(activityBase.getMassUploadInstalledAddressLine1());
		primaryAddress.setAddressLine2(activityBase.getMassUploadInstalledAddressLine2());
		primaryAddress.setAddressName(activityBase.getMassUploadInstalledAssetAddressName());
		primaryAddress.setMassUploaderCountry(activityBase.getMassUploadUploaderCountry());		// new
		primaryAddress.setCity(activityBase.getMassUploadInstalledCity());
		primaryAddress.setCountry(activityBase.getMassUploadInstalledCountry());
		primaryAddress.setPostalCode(activityBase.getMassUploadInstalledPostalCode());
		primaryAddress.setProvince(activityBase.getMassUploadInstalledProvince());
		primaryAddress.setState(activityBase.getMassUploadInstalledState());
		primaryAddress.setCounty(activityBase.getMassUploadInstalledCounty());
		primaryAddress.setDistrict(activityBase.getMassUploadInstalledAddressDistrict());
		primaryAddress.setOfficeNumber(activityBase.getMassUploadInstalledAddressOfficeNum());
		primaryAddress.setPhysicalLocation1(activityBase.getMassUploadInstalledAssetPhyLoc1());
		primaryAddress.setPhysicalLocation2(activityBase.getMassUploadInstalledAssetPhyLoc2());
		primaryAddress.setPhysicalLocation3(activityBase.getMassUploadInstalledAssetPhyLoc3());
		return primaryAddress;
		
	}

	public static Account populateMassUploadAccount(PartnerActivityBase activityBase)
	{
		
		Account partnerAccount = new Account();
		partnerAccount.setAccountName(activityBase.getMassUploadAccountName());
		
		return partnerAccount;
		
		
	}

	
	private static ListOfValues populateStatusType(PartnerOfflineModeActivityDo activityDo) {
		ListOfValues lov = new ListOfValues();
		lov.setValue(activityDo.getServiceRequestWebStatus());
		return lov;
	}
	
	private static ListOfValues populateSRStatusType(ServiceRequestPartnerOfflineModeActivityDo activityDo) {
		ListOfValues lov = new ListOfValues();
		lov.setValue(activityDo.getServiceRequestWebStatus());
		return lov;
	}

	private static List<AccountContact> populateContact(List<PartnerActivityContactInfoDo> contacts)
	{
		List <AccountContact> accountContactList = new ArrayList<AccountContact>();
		for(PartnerActivityContactInfoDo contact: LangUtil.notNull(contacts) )
			{
				AccountContact primaryContact = new AccountContact();
				primaryContact.setFirstName(contact.getFirstName());
				primaryContact.setLastName(contact.getLastName());
				primaryContact.setEmailAddress(contact.getEmailAddress());
				primaryContact.setWorkPhone(contact.getWorkPhone());
				primaryContact.setDeviceContactType(contact.getJobRole());
						GenericAddress address = new GenericAddress();
						address.setAddressLine1(contact.getPrimaryPersonalAddress());
						address.setAddressLine2(contact.getPrimaryPersonalAddress2());
						address.setCity(contact.getPrimaryPersonalCity());
						address.setCountry(contact.getPrimaryPersonalCountry());
						address.setCounty(contact.getPrimaryPersonalCounty());
						address.setPostalCode(contact.getPrimaryPersonalPostalCode());
						address.setProvince(contact.getPrimaryPersonalProvince());
						address.setState(contact.getPrimaryPersonalState());
						address.setAddressId(contact.getAddressId());
						address.setPhysicalLocation1(contact.getPhysicalLocation1());
						address.setPhysicalLocation2(contact.getPhysicalLocation2());
						address.setPhysicalLocation3(contact.getPhysicalLocation3());
				primaryContact.setAddress(address);
			accountContactList.add(primaryContact);	
		}
		
	return accountContactList;
	}

	private static GenericAddress populateServiceAddress (PartnerActivityBase activityBase) {
		GenericAddress serviceAddress = new GenericAddress();
		serviceAddress.setAddressId(activityBase.getServiceAddressId());
		serviceAddress.setAddressName(activityBase.getServiceAddressName());
		serviceAddress.setAddressLine1(activityBase.getServiceAddressLine1());
		serviceAddress.setAddressLine2(activityBase.getServiceAddressLine2());
		serviceAddress.setAddressLine3(activityBase.getServiceAddressLine3());
		serviceAddress.setCity(activityBase.getServiceCity());
		serviceAddress.setState(activityBase.getServiceState());
		serviceAddress.setCountry(activityBase.getServiceCountry());
		serviceAddress.setPostalCode(activityBase.getServicePostalCode());
		serviceAddress.setProvince(activityBase.getServiceProvince());
		serviceAddress.setCounty(activityBase.getCounty());
		serviceAddress.setDistrict(activityBase.getDistrict());
		serviceAddress.setOfficeNumber(activityBase.getOfficeNumber());
		serviceAddress.setStoreFrontName(activityBase.getStoreFrontName());
		return serviceAddress;
	}
	
	private static Account populatePartnerAccount (PartnerActivityBase activityBase, List<AccountFlag> flagList , List<? extends AccountType> partneraccountTypes) {
		Account partnerAccount = new Account();
	
		if(activityBase instanceof PartnerActivityDetailDo || activityBase instanceof PartnerClaimDetailDo || activityBase instanceof PartnerActivityDo || activityBase instanceof PartnerActivityGridDo) {
			partnerAccount.setAccountId(activityBase.getMdmLevel5AccountId());
		} else {
			
			partnerAccount.setAccountId(activityBase.getPartnerAccountId());
		}
		partnerAccount.setAccountName(activityBase.getPartnerAccountName());
		partnerAccount.setOrganizationID(activityBase.getOrganizationID());
		partnerAccount.setDefaultCurrency(activityBase.getCurrency());
		/*logic for max 5 parts*/
		if(StringUtils.isEmpty(activityBase.getPartQuantityOrderLimit())) {
			partnerAccount.setPartQuantityOrderLimit("5");
		} else {
			partnerAccount.setPartQuantityOrderLimit(activityBase.getPartQuantityOrderLimit());
		}
		
		if(flagList != null) {
			partnerAccount = setPartnerFlags(flagList, partnerAccount);
		}

		GenericAddress primaryAddress = new GenericAddress();
		primaryAddress.setAddressId(activityBase.getPrimaryAddressId());
		primaryAddress.setAddressLine1(activityBase.getPrimaryAddressLine1());
		primaryAddress.setAddressLine2(activityBase.getPrimaryAddressLine2());
		primaryAddress.setAddressLine3(activityBase.getPrimaryAddressLine3());
		primaryAddress.setAddressName(activityBase.getPrimaryAddressName());
		primaryAddress.setCity(activityBase.getPrimaryAddressCity());
		primaryAddress.setCountry(activityBase.getPrimaryAddressCountry());
		primaryAddress.setPostalCode(activityBase.getPrimaryAddressPostalCode());
		primaryAddress.setProvince(activityBase.getPrimaryAddressProvince());
		primaryAddress.setState(activityBase.getPrimaryAddressState());
		primaryAddress.setCounty(activityBase.getCounty());
		primaryAddress.setDistrict(activityBase.getDistrict());
		primaryAddress.setOfficeNumber(activityBase.getOfficeNumber());
		partnerAccount.setAddress(primaryAddress);

	
		if (partneraccountTypes != null) {
			partnerAccount = setPartnerTypes(partneraccountTypes, partnerAccount);
		}
		return partnerAccount;
	}
	
	public static Activity convertActivityDetailDoToActivity (PartnerActivityBase activityBase, boolean debriefFlag, String pageName,  boolean offlineActivityDetailsCall) throws ParseException {
		logger.debug("----------------- [IN] convertActivityDetailDoToActivity --------------");
		List<PartnerDetailNoteBase> noteList = new ArrayList<PartnerDetailNoteBase>();
		List<PartnerActivityNoteInstructionDo> debriefNoteist = new ArrayList<PartnerActivityNoteInstructionDo>();
		List<PartnerDetailExpenseBase> expenseList = new ArrayList<PartnerDetailExpenseBase>();
		List<PartnerDetailOrderLineBase> orderLineList = new ArrayList<PartnerDetailOrderLineBase>();
		List<PartnerDetailPartBase> partsList = new ArrayList<PartnerDetailPartBase>();		
		List<PartnerDetailPartBase> debriefPartsList = new ArrayList<PartnerDetailPartBase>();		
		List<PartLineItem> partsListToItem = new ArrayList<PartLineItem>();
		List<PartLineItem> debriefPartsListToItem = new ArrayList<PartLineItem>();
		List<AccountFlag> flagList = new ArrayList<AccountFlag>();
		List<PartnerDetailAttachmentBase> attachmentList = new ArrayList<PartnerDetailAttachmentBase>();//Attachment
		List<PartLineItem> recommendedPartList = new ArrayList<PartLineItem>();
		List<PartLineItem> additionalPartList = new ArrayList<PartLineItem>();
		Activity portalActivity = new Activity();
		boolean activityDetailFlag = false;
		List<Attachment> attachmentLists = new ArrayList<Attachment>();
		Account customerAccount = populateCustomerAccount(activityBase);

		portalActivity.setActivityId(activityBase.getActivityId());
		portalActivity.setReviewComments(activityBase.getReviewComments());
		portalActivity.setActualFailureCode(populateActualFailureCode(activityBase));
		portalActivity.setActivityStatus(populateActivityStatus(activityBase));
		
		portalActivity.setResponseMetric(activityBase.getResponseMatric());
		portalActivity.setCustomerRequestedResponseDate(activityBase.getCustomerRequestedResponseDate());
		portalActivity.setAddressStatus(activityBase.getAddressStatus());
		portalActivity.setEmailAddress(activityBase.getServiceProviderEmailAddress());
		portalActivity.setActivitySubStatus(populateActivitySubStatus(activityBase));
		
		if (activityBase instanceof PartnerActivityDetailDo) {
			
			PartnerActivityDetailDo activityDetail = (PartnerActivityDetailDo) activityBase;
			activityDetailFlag = true;
			portalActivity.setServiceProviderAttemptNumber(activityDetail.getServiceProviderAttemptNumber());
			portalActivity.setResolutionMetric(activityDetail.getResolutionMetric());
			portalActivity.setResolutionDate(activityDetail.getResolutionDate());
			
			//Added for CR 13218 - David Tsamalashvili
			portalActivity.setRequestorEmail(activityDetail.getRequestorEmail());
			portalActivity.setRequestorFirstName(activityDetail.getRequestorFirstName());
			portalActivity.setRequestorLastName(activityDetail.getRequestorLastName());
			portalActivity.setRequestorWorkPhone(activityDetail.getRequestorWorkPhone());
			
			ListOfValues resolutionCodeLov = new ListOfValues();
			resolutionCodeLov.setValue(activityDetail.getDebriefResolutionCode());
			portalActivity.setResolutionCode(resolutionCodeLov);

			portalActivity.setServiceActivityWithin30Days(activityDetail.getServiceActivityWithin30Days());
			portalActivity.setServiceSummary(activityDetail.getServiceSummary());
			portalActivity.setAccountSpecialHandling(activityDetail.getAccountSpecialHandling());
			portalActivity.setAssetWarningMessage(activityDetail.getAssetWarningMessage());
			portalActivity.setEstimatedArrivalTime(activityDetail.getEstimatedArrivalTime());
			portalActivity.setServicerComments(activityDetail.getComments());
			portalActivity.setServiceProviderReferenceNumber(activityDetail.getServiceProviderReferenceNumber());
			
			portalActivity.setServiceProviderStatus(activityDetail.getActivityStatus());
			portalActivity.setStatusUpdateDate(activityDetail.getStatusUpdateDate());
			
			ListOfValues activityType = new ListOfValues();
			activityType.setValue(activityDetail.getActivityType());
			portalActivity.setActivityType(activityType);
		
			customerAccount.setAddress(populateServiceAddress(activityBase));
			
			portalActivity.setServiceInstructionList(populateInstructionList(activityDetail));
			populateActivityDetailList(activityDetail, pageName, noteList, debriefNoteist, 
					expenseList, orderLineList, partsList, debriefPartsList, 
					flagList, attachmentList, offlineActivityDetailsCall);
			
			populateRecommendedAndPendingList(partsList,activityDetailFlag, recommendedPartList, additionalPartList);

			partsListToItem = populatePartsList(partsList);
			debriefPartsListToItem = populateDebrifPartsList(debriefPartsList);

			portalActivity.setPartsList(partsListToItem);
			portalActivity.setDebriefPartsList(debriefPartsListToItem);

			portalActivity.setActivityServiceInstructions(activityDetail.getServiceInstruction());
		
			attachmentLists = populateAttachmentList(attachmentList,activityBase.getServiceRequestAttachmentss());
			portalActivity.setAttachmentList(attachmentLists);
			
			portalActivity.setServiceAddress(populateDetailsServiceAddress(activityDetail));
			portalActivity.setCreatedDate(activityDetail.getCreatedDate());
			portalActivity.setDispatchDate(activityDetail.getDispatchDate());
			portalActivity.setExpectedCompletionDate(activityDetail.getExpectedCompletionDate());
			portalActivity.setServiceProviderETA(activityDetail.getServiceProviderETA());
			if(LangUtil.isNotEmpty(activityDetail.getReadings()))
			{
				for(PartnerActivityAssetReadingDo readingDo: activityDetail.getReadings())
				{
					portalActivity.setCountType(readingDo.getCountType());
					portalActivity.setPageCount(readingDo.getPageCount());
				}
			}
			
			portalActivity.setDebriefStatus(activityDetail.getDebriefStatus());
			portalActivity.setRfvErrors(activityDetail.getRfvErrors());
			//String cp;
			List<PartnerActivityCustomerProfile> temp = new ArrayList<PartnerActivityCustomerProfile>();
//
			if (LangUtil.isNotEmpty(activityDetail.getCustomerProfile())) {
				for (PartnerActivityCustomerProfileDo customerProfile : activityDetail
						.getCustomerProfile()) {
					PartnerActivityCustomerProfile cp = new PartnerActivityCustomerProfile();
					cp.setFieldName(customerProfile.getFieldName());
					cp.setCoordinatesXPreDebriefRFV(customerProfile.getCoordinatesXPreDebriefRFV());
					cp.setCoordinatesYPreDebriefRFV(customerProfile.getCoordinatesYPreDebriefRFV());
				//	cp = customerProfile.getFieldName();
					temp.add(cp);
				}
				
			}
			portalActivity.setCpFields(temp);
				
		} else {
			
			PartnerClaimDetailDo claimDetail = (PartnerClaimDetailDo) activityBase;
			activityDetailFlag = false;
			if(claimDetail.getPageCountAll()!=null && claimDetail.getPageCountAll().size()>0){
                portalActivity.setPageCountAll(claimDetail.getPageCountAll().get(0).getPageCountAll());
			}
			
			portalActivity.setServiceProviderReferenceNumber(claimDetail.getServiceProviderReferenceNumber());
			portalActivity.setNewCustomerAddressCombined(claimDetail.getNewCustomerAddressCombined());
			portalActivity.setCustomerRequestedDate(LangUtil.convertStringToGMTDate(claimDetail.getCustomerRequestedDate()));
			populateCliamDetailList(claimDetail,noteList, debriefNoteist, 
					expenseList, orderLineList, partsList, debriefPartsList, 
					flagList, attachmentList);
			
			populateRecommendedAndPendingList(partsList,activityDetailFlag, recommendedPartList, additionalPartList);

			attachmentLists = populateAttachmentList(attachmentList, activityBase.getServiceRequestAttachmentss());
			portalActivity.setAttachmentList(attachmentLists);
			
		}

		portalActivity.setServiceRequest(populateServiceRequest(activityBase, false ));
		portalActivity.setPartnerAccount(populatePartnerAccount(activityBase, flagList, null));
		portalActivity.setTechnician(populateTechnician(activityBase));
		portalActivity.setCustomerAccount(customerAccount);

		if (activityDetailFlag) {
			portalActivity.setRecommendedPartList(recommendedPartList);
			if (LangUtil.isNotEmpty(additionalPartList)) {
				portalActivity.setAdditionalPartList(additionalPartList);
			}
		}
		else {
			portalActivity.setPendingPartList(recommendedPartList);
		}
		
		if("debrief".equalsIgnoreCase(pageName) && LangUtil.isNotEmpty(debriefNoteist))
		{
		portalActivity.setActivityNoteList(populateActivityNoteInstructionList(debriefNoteist));
		}
		else
		{
			portalActivity.setActivityNoteList(populateActivityNoteList(noteList));	
		}

		portalActivity.setOrderPartList(populateOrderPartsList(orderLineList, activityBase.getSrType()));
		
		portalActivity.setReturnPartList(populateReturnPartsList(orderLineList));
		
		portalActivity.setAdditionalPaymentRequestList(populateExpenseList(expenseList));
		
		portalActivity.setDebrief(populateDebrief(activityBase, debriefFlag, partsList));
		logger.debug("----------------- [OUT] convertActivityDetailDoToActivity and portalActivity object is created --------------");
		return portalActivity;
	}

	private static void populateCliamDetailList (PartnerClaimDetailDo claimDetail, List<PartnerDetailNoteBase> noteList, 
			List<PartnerActivityNoteInstructionDo> debriefNoteist, List<PartnerDetailExpenseBase> expenseList, 
			List<PartnerDetailOrderLineBase> orderLineList, List<PartnerDetailPartBase> partsList, 
			List<PartnerDetailPartBase> debriefPartsList, List<AccountFlag> flagList, 
			List<PartnerDetailAttachmentBase> attachmentList) {
		
		if(claimDetail.getNotes() != null && claimDetail.getNotes().size() > 0) {
			for(PartnerDetailNoteBase note : claimDetail.getNotes()) {
				noteList.add(note);
			}
		}
		
		if(claimDetail.getExpenses() != null && claimDetail.getExpenses().size() > 0 ) {
			for(PartnerDetailExpenseBase expense : claimDetail.getExpenses()) {
				expenseList.add(expense);
			}
		}

		if(claimDetail.getParts() != null && claimDetail.getParts().size() > 0 ) {
			for(PartnerDetailPartBase part :  claimDetail.getParts()) {
			partsList.add(part);
			debriefPartsList.add(part);
			}
		}
		
		if(claimDetail.getOrderLines() != null && claimDetail.getOrderLines().size() > 0 ) {
			for(PartnerDetailOrderLineBase orderLine : claimDetail.getOrderLines()) {
				orderLineList.add(orderLine);
			}
		}
		
		if(claimDetail.getFlags() != null && claimDetail.getFlags().size() > 0) {
			for(PartnerClaimDetailFlagDo flag : claimDetail.getFlags()) {
				flagList.add(flag);
			}
		}
		//Attachment
		if(claimDetail.getAttachments()!= null && claimDetail.getAttachments().size()>0) {
			for(PartnerDetailAttachmentBase attachment: claimDetail.getAttachments()){
				attachment.setType("Claim Update");
				attachmentList.add(attachment);
			}
		}
		
		if(claimDetail.getServiceRequestAttachments()!= null && claimDetail.getServiceRequestAttachments().size()>0) {
			for(PartnerDetailAttachmentBase serviceRequestAttachment: claimDetail.getServiceRequestAttachments())
			{
				serviceRequestAttachment.setType("Claim Create");
				attachmentList.add(serviceRequestAttachment);
			}
		}
	}
	private static void populateActivityDetailList (PartnerActivityDetailDo activityDetail, String pageName, 
			List<PartnerDetailNoteBase> noteList, List<PartnerActivityNoteInstructionDo> debriefNoteist, 
			List<PartnerDetailExpenseBase> expenseList, List<PartnerDetailOrderLineBase> orderLineList, 
			List<PartnerDetailPartBase> partsList, List<PartnerDetailPartBase> debriefPartsList, 
			List<AccountFlag> flagList, List<PartnerDetailAttachmentBase> attachmentList, boolean offlineActivityDetailsCall) {
		
		if(activityDetail.getNotes() != null && activityDetail.getNotes().size() > 0) {
			for(PartnerDetailNoteBase note : activityDetail.getNotes()) {
				noteList.add(note);
			}
		}
		
		if(LangUtil.isNotEmpty(activityDetail.getNoteDetails()) && "debrief".equalsIgnoreCase(pageName))
		{
			for(PartnerActivityNoteInstructionDo noteDebrief : activityDetail.getNoteDetails()) {
				debriefNoteist.add(noteDebrief);
			}
		}
			
		if(activityDetail.getExpenses() != null && activityDetail.getExpenses().size() > 0) {
			for(PartnerDetailExpenseBase expense : activityDetail.getExpenses()) {
				expenseList.add(expense);
			}
		}
		
		if(activityDetail.getOrderLines() != null && activityDetail.getOrderLines().size() > 0) {
			for(PartnerDetailOrderLineBase orderLine : activityDetail.getOrderLines()) {
				orderLineList.add(orderLine);
			}
		}
		
		if(activityDetail.getFlags() != null && activityDetail.getFlags().size() > 0) {
			for(PartnerActivityDetailFlagDo flag : activityDetail.getFlags()) {
				flagList.add(flag);
			}
		}
		
		if(activityDetail.getActivityType().startsWith("Option")){
			if(activityDetail.getParts() != null && activityDetail.getParts().size() > 0) {
				for(PartnerActivityDetailPartDo part : activityDetail.getParts()) {
					debriefPartsList.add(part);
					partsList.add(part);
				}
			}
			if(activityDetail.getPartsOptions() != null && activityDetail.getPartsOptions().size() > 0) {
				for(PartnerActivityDetailPartOptionsDo part : activityDetail.getPartsOptions()) {
					if("Feature/Option".equalsIgnoreCase(part.getRecommendedType())){
						debriefPartsList.add(part);
						partsList.add(part);
					}
				}
			}
			
		}else if(!activityDetail.getActivityType().startsWith("Option")){
			if(activityDetail.getParts() != null && activityDetail.getParts().size() > 0) {
				for(PartnerActivityDetailPartDo part : activityDetail.getParts()) {
					debriefPartsList.add(part);
					partsList.add(part);
				}
			}
		}		
	
		if (activityDetail.getAttachments() != null && activityDetail.getAttachments().size() > 0) {
			for (PartnerDetailAttachmentBase attachment : activityDetail.getAttachments()) {
				if (offlineActivityDetailsCall
						|| (("Debrief".equalsIgnoreCase(pageName) && ("Partner"
								.equalsIgnoreCase(attachment.getVisibility()) || "Both"
								.equalsIgnoreCase(attachment.getVisibility()))))) {
					attachmentList.add(attachment);
				} else if (offlineActivityDetailsCall || (!"Debrief".equalsIgnoreCase(pageName)
						|| LangUtil.isBlank(pageName))) {
					attachmentList.add(attachment);
				}
			}
		}
	}
	
	private static AccountContact populateMassUploadContact (PartnerActivityBase activityBase) {
		AccountContact massUploadContact = new AccountContact();
		massUploadContact.setLastName(activityBase.getMassUploadContactLastName());
		massUploadContact.setFirstName(activityBase.getMassUploadContactFirstName());
		massUploadContact.setWorkPhone(activityBase.getMassUploadContactWorkPhoneNum());
		massUploadContact.setHomePhone(activityBase.getMassUploadContactHomePhone());
		massUploadContact.setEmailAddress(activityBase.getMassUploadContactEmailAddress());
		massUploadContact.setContactType(activityBase.getMassUploadContactJobRole());    //job role
		massUploadContact.setCountry(activityBase.getMassUploadUploaderCountry());
		return massUploadContact;
	}
	
	private static AccountContact populatePrimaryContact (PartnerActivityBase activityBase) {
		AccountContact primaryContact = new AccountContact();
		primaryContact.setContactId(activityBase.getPrimaryContactId());
		primaryContact.setFirstName(activityBase.getPrimaryContactFirstName());
		primaryContact.setLastName(activityBase.getPrimaryContactLastName());
		primaryContact.setWorkPhone(activityBase.getPrimaryContactWorkPhone());
		primaryContact.setEmailAddress(activityBase.getPrimaryContactEmailAddress());
		primaryContact.setAlternatePhone(activityBase.getPrimaryContactAlternateWorkPhone());
		
		if(activityBase instanceof PartnerActivityDetailDo) {
			GenericAddress primaryAddress = new GenericAddress();
			primaryAddress.setAddressId(activityBase.getPrimaryAddressId());
			primaryAddress.setAddressLine1(activityBase.getPrimaryAddressLine1());
			primaryAddress.setAddressLine2(activityBase.getPrimaryAddressLine2());
			primaryAddress.setAddressLine3(activityBase.getPrimaryAddressLine3());
			primaryAddress.setAddressName(activityBase.getPrimaryAddressName());
			primaryAddress.setCity(activityBase.getPrimaryAddressCity());
			primaryAddress.setCountry(activityBase.getPrimaryAddressCountry());
			primaryAddress.setPostalCode(activityBase.getPrimaryAddressPostalCode());
			primaryAddress.setProvince(activityBase.getPrimaryAddressProvince());
			primaryAddress.setState(activityBase.getPrimaryAddressState());
//			primaryAddress.setCounty(activityBase.getCounty());
//			primaryAddress.setDistrict(activityBase.getDistrict());
//			primaryAddress.setOfficeNumber(activityBase.getOfficeNumber());
			primaryContact.setAddress(primaryAddress);
		}
		
		
		return primaryContact;
	}
	private static void populateRecommendedAndPendingList(List<PartnerDetailPartBase> partsList, boolean activityDetailFlag, List<PartLineItem> recommendedPartList, List<PartLineItem> additionalPartList ) {
	
		Map<String,PartnerDetailPartBase> recommentedMap = new HashMap<String,PartnerDetailPartBase>();
	
		for(PartnerDetailPartBase part : partsList) {
			if(StringUtils.isEmpty(part.getOrderLineItemId())) {
				if(recommentedMap != null &&recommentedMap.containsKey(part.getProductId())){
					PartnerDetailPartBase recommentedClaimPart = recommentedMap.get(part.getProductId());
					if(recommentedClaimPart != null){
						recommentedClaimPart.setRecommendedQuantity(recommentedClaimPart.getRecommendedQuantity() + part.getQuantity());
					}
				} else {
					recommentedMap.put(part.getProductId(), part);
				}
			}

		}
		
		if(recommentedMap != null && !recommentedMap.isEmpty()) {
			Iterator<PartnerDetailPartBase> partIterator = recommentedMap.values().iterator();
			while(partIterator.hasNext()) {
				PartLineItem recommendedPart = new PartLineItem();
				PartnerDetailPartBase partBase = (PartnerDetailPartBase)partIterator.next();
				recommendedPart.setPartId(partBase.getProductId());
				recommendedPart.setUsedQuantity(partBase.getUsedQuantity());
				recommendedPart.setNotUsedQuantity(partBase.getNotUsedQuantity());
				recommendedPart.setDoaQuantity(partBase.getDoaQuantity());
				recommendedPart.setPartLineItemId(partBase.getId());
				recommendedPart.setPartNumber(partBase.getPartNumber());
				recommendedPart.setPartName(partBase.getPartName());
				recommendedPart.setDescription(partBase.getProductDescription());
				recommendedPart.setRecommendedQuantity(partBase.getRecommendedQuantity());
				recommendedPart.setOrderQuantity(String.valueOf(partBase.getQuantity()));
				recommendedPart.setReturnRequiredFlag(partBase.isReturnRequiredFlag());
				recommendedPart.setTrackingNumber(partBase.getTrackingNumber());
				ListOfValues lineStatus = new ListOfValues();
				lineStatus.setValue(partBase.getLineStatus());
				recommendedPart.setLineStatus(lineStatus);
				recommendedPart.setExpedite(partBase.getShippingMethod());
				if(!activityDetailFlag) {
					recommendedPart.setRequestedDate(partBase.getPartOrderedDate());
				}
				if("Associated".equalsIgnoreCase(partBase.getRelationType()) && activityDetailFlag )
				{
					additionalPartList.add(recommendedPart);
				}
				recommendedPartList.add(recommendedPart);
				
			}
		}
	
		//return recommendedPartList;

	}
	private static List<TechnicianInstruction> populateInstructionList (PartnerActivityDetailDo activityDetail) {
		List<TechnicianInstruction> serviceInstructionList = new ArrayList<TechnicianInstruction>();
		if(activityDetail.getInstructions() != null && activityDetail.getInstructions().size() > 0) {
			for(PartnerActivityDetailInstructionDo instruction : activityDetail.getInstructions()) {
					TechnicianInstruction technicianInstruction = new TechnicianInstruction();
					technicianInstruction.setInstructionId(instruction.getId());
					technicianInstruction.setInstructionDate(instruction.getInstructionDate());
					technicianInstruction.setInstructionType(instruction.getInstructionType());
					technicianInstruction.setActualInstruction(instruction.getActualInstruction());
					serviceInstructionList.add(technicianInstruction);
				}
			}
		return serviceInstructionList;
	}
	private static AccountContact populateSecondaryContact (PartnerActivityDetailDo activityDetail) {
		AccountContact secondaryContact = new AccountContact();
		secondaryContact.setContactId(activityDetail.getSecondaryContactId());
		secondaryContact.setFirstName(activityDetail.getSecondaryContactFirstName());
		secondaryContact.setLastName(activityDetail.getSecondaryContactLastName());
		secondaryContact.setWorkPhone(activityDetail.getSecondaryContactWorkPhone());
		secondaryContact.setEmailAddress(activityDetail.getSecondaryContactEmailAddress());
		secondaryContact.setAlternatePhone(activityDetail.getSecondaryContactAlternateWorkPhone());
		GenericAddress secondaryAddress = new GenericAddress();
		secondaryAddress.setAddressId(activityDetail.getSecondaryAddressId());
		secondaryAddress.setAddressLine1(activityDetail.getSecondaryAddressLine1());
		secondaryAddress.setAddressLine2(activityDetail.getSecondaryAddressLine2());
		secondaryAddress.setAddressLine3(activityDetail.getSecondaryAddressLine3());
		secondaryAddress.setAddressName(activityDetail.getSecondaryAddressName());
		secondaryAddress.setCity(activityDetail.getSecondaryAddressCity());
		secondaryAddress.setCountry(activityDetail.getSecondaryAddressCountry());
		secondaryAddress.setPostalCode(activityDetail.getSecondaryAddressPostalCode());
		secondaryAddress.setProvince(activityDetail.getSecondaryAddressProvince());
		secondaryAddress.setState(activityDetail.getSecondaryAddressState());
//		primaryAddress.setCounty(activityDetail.getCounty());
//		primaryAddress.setDistrict(activityDetail.getDistrict());
//		primaryAddress.setOfficeNumber(activityDetail.getOfficeNumber());
		secondaryContact.setAddress(secondaryAddress);
		
		return secondaryContact;
	}
	private static GenericAddress populateInstallAddress(PartnerClaimDetailDo claimDetail ) {
		GenericAddress installAddress = new GenericAddress();
		installAddress.setAddressId(claimDetail.getInstallAddressId());
		installAddress.setAddressLine1(claimDetail.getInstallAddressLine1());
		installAddress.setAddressLine2(claimDetail.getInstallAddressLine2());
		installAddress.setAddressLine3(claimDetail.getInstallAddressLine3());
		installAddress.setCity(claimDetail.getInstallCity());
		installAddress.setCountry(claimDetail.getInstallCountry());
		installAddress.setPostalCode(claimDetail.getInstallPostalCode());
		installAddress.setProvince(claimDetail.getInstallProvince());
		installAddress.setState(claimDetail.getInstallState());
		installAddress.setCounty(claimDetail.getCounty());
		installAddress.setDistrict(claimDetail.getDistrict());
		installAddress.setOfficeNumber(claimDetail.getOfficeNumber());
		return installAddress;
	}
	private static List<PartLineItem> populateReturnPartsList (List<PartnerDetailOrderLineBase> orderLineList) {
		List<PartLineItem> returnPartList = new ArrayList<PartLineItem>();
		for(PartnerDetailOrderLineBase orderLine : orderLineList) {
			if ("RMA".equalsIgnoreCase(orderLine.getLineType())) {
				
				/*returnPartList*/
				PartLineItem returnPart = new PartLineItem();
				returnPart.setPartLineItemId(orderLine.getId());
				returnPart.setQuantity(orderLine.getQuantity());
				returnPart.setPartName(orderLine.getPartName());
				returnPart.setPartNumber(orderLine.getPartNumber());
				returnPart.setExpedite(orderLine.getShippingMethod());
				ListOfValues returnPartLineStatus = new ListOfValues();
				returnPartLineStatus.setValue(orderLine.getLineStatus());
				returnPart.setLineStatus(returnPartLineStatus);
				returnPart.setLineRMAStatus(returnPartLineStatus);
				
				returnPart.setSerialNumber(orderLine.getSerialNumber());
				
				returnPart.setPartReceivedDate(orderLine.getPartReceivedDate());   //added for BRD 140706	
				returnPart.setTrackingNumber(orderLine.getReturnTrackingNumber());
				
				//added for Defect 14235
				for (PartnerDetailOrderLineBase orderShipLine : orderLineList) {
					if("Ship".equalsIgnoreCase(orderShipLine.getLineType())){
						if((orderShipLine.getPartName().equalsIgnoreCase(returnPart.getPartName()) && LangUtil.isNotEmpty(orderShipLine.getPartName())) &&(orderShipLine.getPartNumber().equalsIgnoreCase(returnPart.getPartNumber()) && LangUtil.isNotEmpty(orderShipLine.getPartNumber())) ){
							if(returnPart.getCarrier() == null){
								ListOfValues carrierr = new ListOfValues();
								carrierr.setValue(orderShipLine.getReturnCarrierCode());
								returnPart.setCarrier(carrierr);
							}
								if(LangUtil.isEmpty(returnPart.getReturnTrackingNumber()) || returnPart.getReturnTrackingNumber() == null){
									returnPart.setReturnTrackingNumber(orderShipLine.getReturnTrackingNumber());
								}
							if(LangUtil.isEmpty(returnPart.getTrackingNumber()) || returnPart.getTrackingNumber() == null){
								returnPart.setTrackingNumber(orderShipLine.getReturnTrackingNumber());
							}	
						}
					}
				}
								
				GenericAddress returnShipToAddress = new GenericAddress();
				returnShipToAddress.setAddressId(orderLine.getReturnAddressId());
				returnShipToAddress.setAddressLine1(orderLine.getReturnAddressLine1());
				returnShipToAddress.setAddressLine2(orderLine.getReturnAddressLine2());
				returnShipToAddress.setAddressLine3(orderLine.getReturnAddressLine3());
				returnShipToAddress.setAddressName(orderLine.getReturnAddressName());
				returnShipToAddress.setCity(orderLine.getReturnAddressCity());
				returnShipToAddress.setState(orderLine.getReturnAddressState());
				returnShipToAddress.setCountry(orderLine.getReturnAddressCountry());
				returnShipToAddress.setPostalCode(orderLine.getReturnAddressPostalCode());
				returnShipToAddress.setProvince(orderLine.getReturnAddressProvince());
				returnShipToAddress.setPostalCode(orderLine.getReturnAddressPostalCode());
				returnShipToAddress.setCounty(orderLine.getCounty());
				returnShipToAddress.setDistrict(orderLine.getDistrict());
				returnShipToAddress.setOfficeNumber(orderLine.getOfficeNumber());
				returnPart.setShipToAdress(returnShipToAddress);
				returnPartList.add(returnPart);
				
			}
	
		}
		return returnPartList;
	}

	private static List<PartLineItem> populateOrderPartsList (List<PartnerDetailOrderLineBase> orderLineList, String serviceRequestType) {
		List<PartLineItem> orderPartList = new ArrayList<PartLineItem>();
		for(PartnerDetailOrderLineBase orderLine : orderLineList) {
			PartLineItem orderPart = new PartLineItem();
			ListOfValues orderPartCarrier = new ListOfValues();
			
			if(serviceRequestType!= null && (serviceRequestType.toUpperCase()).contains("MPS")) {
			   
			       StringBuilder tempShipmentCarrierBuilder = new StringBuilder();
			       StringBuilder tempShipmentTrackingNumberBuilder = new StringBuilder();
			       StringBuilder tempShipmentTrackingStatusBuilder = new StringBuilder();
			       StringBuilder tempShipmentDateBuilder = new StringBuilder();
			    
				for (PartnerActivityShipmentDataDo shipmentDo : LangUtil
						.notNull(orderLine.getShipData())) {

					if (shipmentDo.getShipmentCarrier() != null) {
						if (tempShipmentCarrierBuilder.length() > 0) {
							tempShipmentCarrierBuilder.append(",");
						}
						tempShipmentCarrierBuilder.append(shipmentDo
								.getShipmentCarrier());
					}
					if (shipmentDo.getShipmentTrackingNumber() != null) {
						if (tempShipmentTrackingNumberBuilder.length() > 0) {
							tempShipmentTrackingNumberBuilder.append(",");
						}
						tempShipmentTrackingNumberBuilder.append(shipmentDo
								.getShipmentTrackingNumber());
					}
					if (shipmentDo.getShipmentTrackingStatus() != null) {
						if (tempShipmentTrackingStatusBuilder.length() > 0) {
							tempShipmentTrackingStatusBuilder.append(",");
						}
						tempShipmentTrackingStatusBuilder.append(shipmentDo
								.getShipmentTrackingStatus());
					}
					if (shipmentDo.getShipmentDate() != null) {
						if (tempShipmentDateBuilder.length() > 0) {
							tempShipmentDateBuilder.append(",");
						}
						tempShipmentDateBuilder.append(shipmentDo
								.getShipmentDate());
					}
				}
				orderPartCarrier.setValue(tempShipmentCarrierBuilder.toString());
				orderPart.setCarrier(orderPartCarrier);
				orderPart.setTrackingNumber(tempShipmentTrackingNumberBuilder.toString());
				//orderPart.setShipDate(LangUtil.convertStringToGMTDate(tempShipmentDateBuilder.toString()));
				orderPart.setShipDate(tempShipmentDateBuilder.toString());
				
			}
			else{
				//ListOfValues orderPartCarrier = new ListOfValues();
				orderPartCarrier.setValue(orderLine.getCarrier());
				orderPart.setCarrier(orderPartCarrier);
				orderPart.setTrackingNumber(orderLine.getTrackingNumber());
				
				ListOfValues partStatus = new ListOfValues();				//	added for part status for BRD 14 07 06. 
				partStatus.setValue(orderLine.getPartStatus());
				orderPart.setPartStatus(partStatus);
				orderPart.setReturnTrackingNumber(orderLine.getReturnTrackingNumber());
				orderPart.setShipDate(orderLine.getShipDate());
			}
			if("Ship".equalsIgnoreCase(orderLine.getLineType())) {
				orderPart.setPartLineItemId(orderLine.getId());
				orderPart.setPartOrderedDate(orderLine.getPartOrderedDate());
				orderPart.setQuantity(orderLine.getQuantity());
				orderPart.setPartNumber(orderLine.getPartNumber());
				orderPart.setPartName(orderLine.getPartName());
				orderPart.setExpedite(orderLine.getShippingMethod());
				ListOfValues orderPartLineStatus = new ListOfValues();
				orderPartLineStatus.setValue(orderLine.getLineStatus());
				orderPart.setLineStatus(orderPartLineStatus);
				
				orderPart.setSerialNumber(orderLine.getSerialNumber());
				//orderPart.setShipDate(orderLine.getShipDate());
				orderPart.setModelNumber(orderLine.getModelNumber());
//				ListOfValues orderPartCarrier = new ListOfValues();
//				orderPartCarrier.setValue(orderLine.getCarrier());
//				orderPart.setCarrier(orderPartCarrier);
//				orderPart.setTrackingNumber(orderLine.getTrackingNumber());
				
				GenericAddress shipToAddress = new GenericAddress();
				if(orderLine.getCustomerAddressId() != null && !orderLine.getCustomerAddressId().isEmpty()) {
					shipToAddress.setAddressId(orderLine.getCustomerAddressId());
					shipToAddress.setAddressLine1(orderLine.getCustomerAddressLine1());
					shipToAddress.setAddressLine2(orderLine.getCustomerAddressLine2());
					shipToAddress.setAddressLine3(orderLine.getCustomerAddressLine3());
					shipToAddress.setAddressName(null);
					shipToAddress.setCity(orderLine.getCustomerAddressCity());
					shipToAddress.setState(orderLine.getCustomerAddressState());
					shipToAddress.setCountry(orderLine.getCustomerAddressCountry());
					shipToAddress.setPostalCode(orderLine.getCustomerAddressPostalCode());
					shipToAddress.setProvince(orderLine.getCustomerAddressProvince());
					shipToAddress.setPostalCode(orderLine.getCustomerAddressPostalCode());
					shipToAddress.setCounty(orderLine.getCustomerAddressCounty());
					shipToAddress.setDistrict(orderLine.getCustomerAddressDistrict());
					shipToAddress.setOfficeNumber(orderLine.getCustomerAddressOfficeNumber());
					orderPart.setShipToAdress(shipToAddress);
				} else {
					shipToAddress.setAddressId(orderLine.getShipToAddressId());
					shipToAddress.setAddressLine1(orderLine.getShipToAddressLine1());
					shipToAddress.setAddressLine2(orderLine.getShipToAddressLine2());
					shipToAddress.setAddressLine3(orderLine.getShipToAddressLine3());
					shipToAddress.setAddressName(null);
					shipToAddress.setCity(orderLine.getShipToCity());
					shipToAddress.setState(orderLine.getShipToState());
					shipToAddress.setCountry(orderLine.getShipToCountry());
					shipToAddress.setPostalCode(orderLine.getShipToPostalCode());
					shipToAddress.setProvince(orderLine.getShipToProvince());
					shipToAddress.setPostalCode(orderLine.getShipToPostalCode());
					shipToAddress.setCounty(orderLine.getShipToCounty());
					shipToAddress.setDistrict(orderLine.getShipToDistrict());
					shipToAddress.setOfficeNumber(orderLine.getShipToOfficeNumber());
					orderPart.setShipToAdress(shipToAddress);
					
				}

				
			}
			orderPartList.add(orderPart);
		}
		return orderPartList;
	}

	private static List<AdditionalPaymentRequest> populateExpenseList (List<PartnerDetailExpenseBase> expenseList) throws ParseException {
		List<AdditionalPaymentRequest> additionalPaymentList = new ArrayList<AdditionalPaymentRequest>();
		for(PartnerDetailExpenseBase expense : expenseList) {
			AdditionalPaymentRequest additionalPayment = new AdditionalPaymentRequest();
			additionalPayment.setDescription(expense.getDescription());
			additionalPayment.setPaymentCurrency(expense.getPaymentCurrency());
			additionalPayment.setPaymentRequestId(expense.getId());
			
			ListOfValues additionalPaymentType = new ListOfValues();
			additionalPaymentType.setValue(expense.getPaymentType());
			additionalPayment.setPaymentType(additionalPaymentType);
			additionalPayment.setQuantity(expense.getQuantity());
			
			/*String to Double conversion for Amount and UnitPrice*/
			NumberFormat format = NumberFormat.getInstance(Locale.US);
		    Number totalAmount = format.parse(expense.getTotalAmount());
		    Number unitPrice =format.parse(expense.getUnitPrice());
		    
			additionalPayment.setTotalAmount(totalAmount.doubleValue());
			additionalPayment.setUnitPrice(unitPrice.doubleValue());
			additionalPaymentList.add(additionalPayment);
		}
		return additionalPaymentList;
	}
	private static List<ActivityNote> populateActivityNoteInstructionList (List<PartnerActivityNoteInstructionDo> noteInstructionList) {
 		List<ActivityNote> activityNoteList = new ArrayList<ActivityNote> ();
		
 		for(PartnerActivityNoteInstructionDo note : LangUtil.notNull(noteInstructionList)) {
					if(note.getInstructionType().equalsIgnoreCase("Service Technician")){
					ActivityNote activityNote = new ActivityNote();
					activityNote.setNoteId(note.getId());
					activityNote.setNoteDate(note.getCreated());
					activityNote.setNoteDetails(note.getInstruction());
		
					AccountContact noteAuthor = new AccountContact();
					noteAuthor.setContactId(note.getAuthorId());
					noteAuthor.setFirstName(note.getAuthorFirstName());
					noteAuthor.setLastName(note.getAuthorLastName());
					activityNote.setNoteAuthor(noteAuthor);
					activityNoteList.add(activityNote);
					
					}
			}
		 return activityNoteList;
		
	}
	private static List<ActivityNote> populateActivityNoteList (List<PartnerDetailNoteBase> noteList) {
 		List<ActivityNote> activityNoteList = new ArrayList<ActivityNote> ();
 		for(PartnerDetailNoteBase note : noteList) {
			ActivityNote activityNote = new ActivityNote();
			activityNote.setNoteId(note.getId());
			activityNote.setNoteDate(note.getNoteDate());
			activityNote.setNoteDetails(note.getDetails());
			
			AccountContact noteAuthor = new AccountContact();
			noteAuthor.setContactId(note.getContactId());
			noteAuthor.setFirstName(note.getAuthorFirstName());
			noteAuthor.setLastName(note.getAuthorLastName());
			activityNote.setNoteAuthor(noteAuthor);
			activityNoteList.add(activityNote);
		}
		return activityNoteList;
		
	}
	private static Debrief populateDebrief (PartnerActivityBase activityBase, boolean debriefFlag , List<PartnerDetailPartBase> partsList ) {
		Debrief debrief = new Debrief();
		debrief.setRepairDescription(activityBase.getDebriefRepairDescription());
		
		if(activityBase instanceof PartnerActivityHistory) {
			PartnerActivityHistory activityHistoryDo = (PartnerActivityHistory) activityBase;
			debrief.setServiceEndDate(activityHistoryDo.getStartDate());
			debrief.setServiceEndDate(activityHistoryDo.getEndDate());
			debrief.setRepairDescription(activityHistoryDo.getRepairDescription());
		}
		else if(activityBase instanceof PartnerActivityDetailDo) {
			PartnerActivityDetailDo activityDetail = (PartnerActivityDetailDo) activityBase;
			
			debrief.setTravelDistance(activityDetail.getDebriefTravelDistance());
		
			ListOfValues travelUnitOfMeasureLOV = new ListOfValues();
			travelUnitOfMeasureLOV.setType(SiebelLocalizationOptionEnum.PARTNER_TRAVEL_UNIT_OF_MEASURE.getValue());
			travelUnitOfMeasureLOV.setValue(activityDetail.getDebriefTravelUnitOfMeasure());
			debrief.setTravelUnitOfMeasure(travelUnitOfMeasureLOV);
			
			debrief.setTravelDuration(activityDetail.getDebriefTravelDuration());
			debrief.setSupplyManufacturer(activityDetail.getDebriefSupplyManufacturer());
			debrief.setProblemDescription(activityDetail.getDebriefProblemDescription());
			
			if(activityDetail.getDebriefGenuineLexmarkSuppliesUsedFlag() != null && activityDetail.getDebriefGenuineLexmarkSuppliesUsedFlag().equals("Yes")) {
				debrief.setGenuineLexmarkSuppliesUsedFlag(false);
			} else {
 				debrief.setGenuineLexmarkSuppliesUsedFlag(true);
			}
						
			ListOfValues debriefFailureCode = new ListOfValues();
			debriefFailureCode.setValue(activityDetail.getDebriefActualFailureCode());
			debrief.setActualFailureCode(debriefFailureCode);
	
			if(!StringUtils.isEmpty(activityDetail.getRepairedSerialNumber()) &&
					!StringUtils.isEmpty(activityDetail.getInstalledAssetSerialNumber())) {
				if(!matchInstallAssetAndRepairedAsset(activityDetail)) {
					debrief.setInstalledAsset(populateInstallAsset(activityDetail));
				}
			}
			
			debrief.setDebriefActionStatus(activityDetail.getDebriefActionStatus());
		}

		debrief.setTravelDistance(activityBase.getDebriefTravelDistance());
		
		ListOfValues travelUnitOfMeasureLOV = new ListOfValues();
		travelUnitOfMeasureLOV.setType(SiebelLocalizationOptionEnum.PARTNER_TRAVEL_UNIT_OF_MEASURE.getValue());
		travelUnitOfMeasureLOV.setValue(activityBase.getDebriefTravelUnitOfMeasure());
		debrief.setTravelUnitOfMeasure(travelUnitOfMeasureLOV);
		
		debrief.setTravelDuration(activityBase.getDebriefTravelDuration());
		
		ListOfValues debriefFailureCode = new ListOfValues();
		debriefFailureCode.setValue(activityBase.getDebriefActualFailureCode());
		debrief.setActualFailureCode(debriefFailureCode);

		ListOfValues deviceConditionLOV = new ListOfValues();
		deviceConditionLOV.setType(SiebelLocalizationOptionEnum.PARTNER_DEVICE_CONDITION.getValue());
		deviceConditionLOV.setValue(activityBase.getDebriefDeviceCondition());
		debrief.setDeviceCondition(deviceConditionLOV);
		debrief.setExpectedStartDate(activityBase.getDebriefExpectedStartDate());
		debrief.setServiceStartDate(activityBase.getDebriefServiceStartDate());
		debrief.setServiceEndDate(activityBase.getDebriefServiceEndDate());
		debrief.setDebriefStatus(activityBase.getDebriefStatus());
		debrief.setServiceRequestedDate(activityBase.getServiceRequestDate());
		ListOfValues debriefResolutionCode = new ListOfValues();
		debriefResolutionCode.setValue(activityBase.getDebriefResolutionCode());
		debrief.setResolutionCode(debriefResolutionCode);
		
		if(debriefFlag) {
			debrief.setPartDebriefList(populatePartDebriefList(partsList));
		}
		return debrief;
	}
	
	private static Asset populateInstallAsset(PartnerActivityDetailDo activityDetail) {
		Asset installedAsset = new Asset();
		installedAsset.setDeviceTag(activityDetail.getInstalledAssetDeviceTag());
		installedAsset.setNetworkConnectedFlag(activityDetail.getAssetNetworkConnectedFlag());
		installedAsset.setIpAddress(activityDetail.getAssetIPAddress());
		installedAsset.setMacAddress(activityDetail.getAssetMACAddress());
		installedAsset.setModelNumber(activityDetail.getInstalledAssetModelNumber());
		installedAsset.setSerialNumber(activityDetail.getInstalledAssetSerialNumber());
		installedAsset.setAssetId(activityDetail.getInstalledAssetId());
		if(activityDetail.getInstalledDevices() != null && activityDetail.getInstalledDevices().size() > 0) {
			for(PartnerActivityDetailInstalledAssetDo installAssetDetail : activityDetail.getInstalledDevices()) {
				if(installAssetDetail.isPrimaryFlag()) {
					installedAsset.setNewPageCount(installAssetDetail.getPageCount());
				}
			}
			
		}
		
		return installedAsset;

	}
	
	
	private static void populateInstallAssetActivityDetails(PartnerActivityDetailDo activityDetail, Asset installedAsset) {
		installedAsset.setDeviceTag(activityDetail.getInstalledAssetDeviceTag());
		installedAsset.setNetworkConnectedFlag(activityDetail.getAssetNetworkConnectedFlag());
		installedAsset.setIpAddress(activityDetail.getAssetIPAddress());
		installedAsset.setMacAddress(activityDetail.getAssetMACAddress());
		installedAsset.setModelNumber(activityDetail.getInstalledAssetModelNumber());
		installedAsset.setSerialNumber(activityDetail.getInstalledAssetSerialNumber());
		installedAsset.setAssetId(activityDetail.getInstalledAssetId());
		if(activityDetail.getInstalledDevices() != null && activityDetail.getInstalledDevices().size() > 0) {
			for(PartnerActivityDetailInstalledAssetDo installAssetDetail : activityDetail.getInstalledDevices()) {
				if(installAssetDetail.isPrimaryFlag()) {
					installedAsset.setNewPageCount(installAssetDetail.getPageCount());
				}
			}
			
		}

	}
	
	private static void populateRepairedAsset(PartnerActivityDetailDo activityDetail, Asset repairedAsset) {
		repairedAsset.setSerialNumber(activityDetail.getRepairedSerialNumber());
		repairedAsset.setDeviceTag(activityDetail.getRepairedAssetDeviceTag());
		repairedAsset.setModelNumber(activityDetail.getRepairedAssetModelNumber());
		repairedAsset.setAssetId(activityDetail.getRepairedAssetId());
		
		if(activityDetail.getRepairedDevices() != null && activityDetail.getRepairedDevices().size() > 0) {
			for(PartnerActivityDetailRepairedDeviceDo repairAssetDetail : activityDetail.getRepairedDevices()) {
				if(repairAssetDetail.isPrimaryFlag()) {
					repairedAsset.setNewPageCount(repairAssetDetail.getPageCount());
				}
			}
			
		}
	}
	
	private static List<PartLineItem> populatePartDebriefList (List<PartnerDetailPartBase> partsList) {
		List<PartLineItem> partDebriefList = new ArrayList<PartLineItem>();
		for(PartnerDetailPartBase part : partsList) {
				PartLineItem debriefPart = new PartLineItem();
				debriefPart.setPartId(part.getProductId());
				debriefPart.setPartLineItemId(part.getId());
				debriefPart.setQuantity(part.getQuantity());
				debriefPart.setPartName(part.getPartName());
				debriefPart.setPartNumber(part.getPartNumber());
				
				ListOfValues partDisposition = new ListOfValues();
				partDisposition.setValue(part.getLineStatus());
				debriefPart.setPartDisposition(partDisposition);
				
				ListOfValues debriefPartLineStatus = new ListOfValues();
				debriefPartLineStatus.setValue(part.getLineStatus());
				debriefPart.setLineStatus(debriefPartLineStatus);
				
				ListOfValues errorCode1Lov = new ListOfValues();
				errorCode1Lov.setValue(part.getErrorCode1());
				debriefPart.setErrorCode1(errorCode1Lov);
		
				ListOfValues errorCode2Lov = new ListOfValues();
				errorCode2Lov.setValue(part.getErrorCode2());
				debriefPart.setErrorCode2(errorCode2Lov);
	
				debriefPart.setReturnRequiredFlag(part.isReturnRequiredFlag());
				debriefPart.setTrackingNumber(part.getTrackingNumber());
				ListOfValues carrier = new ListOfValues();
				carrier.setValue(part.getCarrier());
				debriefPart.setCarrier(carrier);
				ListOfValues lineStatus = new ListOfValues();
				lineStatus.setValue(part.getLineStatus());						
				debriefPart.setLineStatus(lineStatus);
				debriefPart.setPartOrderedDate(part.getPartOrderedDate());
				
				ListOfValues partSource = new ListOfValues();
				partSource.setValue(part.getSource());
				debriefPart.setPartSource(partSource);
				
				partDebriefList.add(debriefPart);
		}
		return partDebriefList;
		
	}
	
	public static List<DownloadClaim> convertActivityBaseListToDownloadClaimList (List<PartnerClaimDetailDo> activityList) {
		List<DownloadClaim> downloadClaimList = new ArrayList<DownloadClaim>();
		for(PartnerClaimDetailDo activityBase : activityList){
			
			DownloadClaim downloadClaim = new DownloadClaim();

			downloadClaim.setSrNum(activityBase.getServiceRequestNumber());
			downloadClaim.setAssetProduct(activityBase.getAssetProductTLI());
			downloadClaim.setPartnerName(activityBase.getPartnerAccountName());
			downloadClaim.setPartnerOrg(activityBase.getPartnerOrganization());
			downloadClaim.setPartnerRefNumber(activityBase.getPartnerRefNumber());
			downloadClaim.setPartnerSite(activityBase.getPartnerSite());
			
			downloadClaim.setActivityId(activityBase.getActivityId());
			downloadClaim.setAssetMTM(activityBase.getAssetMachineTypeModel());
			if(!StringUtils.isEmpty(activityBase.getAssetSerialNumber())) {
				downloadClaim.setAstSerialNumber(activityBase.getAssetSerialNumber());
			} else {
				downloadClaim.setAstSerialNumber(activityBase.getAssetSerialNumberOverride());
			}

			if(StringUtils.isEmpty(activityBase.getCustomerAccountName())) {
				downloadClaim.setNewCustomerAccount(activityBase.getOverridecustomerAccountName());
			} else {
				downloadClaim.setNewCustomerAccount(activityBase.getCustomerAccountName());
			}

			downloadClaim.setNewCustomerAddress(activityBase.getNewCustomerAddressCombined());
			downloadClaim.setNewTechFirstName(activityBase.getNewTechFirstName());
			downloadClaim.setNewTechLastName(activityBase.getNewTechLastName());
			downloadClaim.setPartnerAddress1(activityBase.getPrimaryAddressLine1());
			downloadClaim.setPartnerAddress2(activityBase.getPrimaryAddressLine2());
			downloadClaim.setPartnerAddress3(activityBase.getPrimaryAddressLine3());
			downloadClaim.setPartnerCity(activityBase.getPrimaryAddressCity());
			downloadClaim.setPartnerCountry(activityBase.getPrimaryAddressCountry());
			downloadClaim.setPartnerPostal(activityBase.getPrimaryAddressPostalCode());
			downloadClaim.setPartnerProvince(activityBase.getPrimaryAddressProvince());
			downloadClaim.setPartnerState(activityBase.getPrimaryAddressState());

			downloadClaim.setPrContactEmail(activityBase.getPrimaryContactEmailAddress());
			downloadClaim.setPrContactFN(activityBase.getPrimaryContactFirstName());
			downloadClaim.setPrContactLN(activityBase.getPrimaryContactLastName());
			downloadClaim.setPrContactWorkPhone(activityBase.getPrimaryContactWorkPhone());
			downloadClaim.setPrinterCondition(activityBase.getDebriefDeviceCondition());
			downloadClaim.setProblemCode(activityBase.getOverrideActualFailureCode());
			downloadClaim.setProblemDetails(activityBase.getProblemDescription());
	
			downloadClaim.setRepairDesc(activityBase.getRepairDesc());
			downloadClaim.setResolutionCode(activityBase.getDebriefResolutionCode());
			downloadClaim.setReviewComments(activityBase.getReviewComments());
			downloadClaim.setServiceAddress1(activityBase.getInstallAddressLine1());
			downloadClaim.setServiceAddress2(activityBase.getInstallAddressLine2());
			downloadClaim.setServiceAddress3(activityBase.getInstallAddressLine3());
			downloadClaim.setServiceCity(activityBase.getInstallCity());
			downloadClaim.setServiceCountry(activityBase.getInstallCountry());
			downloadClaim.setServicePostal(activityBase.getInstallPostalCode());
			downloadClaim.setServiceProvince(activityBase.getInstallProvince());
			downloadClaim.setServiceState(activityBase.getInstallState());
			downloadClaim.setSrId(activityBase.getServiceRequestId());
			downloadClaim.setSrvcEndDate(activityBase.getDebriefServiceEndDate());
			downloadClaim.setSrvcStartDate(activityBase.getDebriefServiceStartDate());
			downloadClaim.setTechLoginName(activityBase.getTechLoginName());
			downloadClaim.setDebriefStatus(activityBase.getDebriefActionStatus());

			if(StringUtils.isEmpty(activityBase.getActivitySubStatus())) 
			{
				downloadClaim.setActivitySubStatus("Pending SP Acknowledgement");
			} else 
			{
				downloadClaim.setActivitySubStatus(activityBase.getActivitySubStatus());
			}
			

			List<DownloadClaimPart> downloadClaimPartsList = new ArrayList<DownloadClaimPart>();
			if(activityBase.getParts() != null) {
				for(PartnerClaimDetailPartDo claimDetailpart : activityBase.getParts()) {
					DownloadClaimPart downloadClaimPart = new DownloadClaimPart();
					downloadClaimPart.setRecPartName(claimDetailpart.getPartName());
					downloadClaimPart.setRecPartsErrCd1(claimDetailpart.getErrorCode1());
					downloadClaimPart.setRecPartsErrCd2(claimDetailpart.getErrorCode2());
					downloadClaimPart.setRecPartsQty(Integer.toString(claimDetailpart.getQuantity()));
					downloadClaimPart.setRecPartsRetCarrier(claimDetailpart.getCarrier());
					downloadClaimPart.setRecPartsRetTrackNum(claimDetailpart.getTrackingNumber());
					downloadClaimPart.setRecPartsStatus(claimDetailpart.getLineStatus());
					downloadClaimPartsList.add(downloadClaimPart);	
				}
			}


			downloadClaim.setDownloadClaimPart(downloadClaimPartsList);
			downloadClaimList.add(downloadClaim);
		}
		return downloadClaimList;
	}
	
	public static List<DownloadRequest> convertActivityBaseListToDownloadRequestList (List<PartnerActivityDetailDo> activityList) throws ParseException {
		List<DownloadRequest> downloadRequestList = new ArrayList<DownloadRequest>();
		for(PartnerActivityDetailDo activityBase : activityList) {
			DownloadRequest downloadRequest = new DownloadRequest();
			
			downloadRequest.setSrNum(activityBase.getServiceRequestNumber());
			downloadRequest.setActionNarrative(activityBase.getActionNarrative());
			downloadRequest.setActionSubStatus(activityBase.getActivitySubStatus());
			downloadRequest.setActivityId(activityBase.getActivityId());
			downloadRequest.setActualEnd(activityBase.getDebriefServiceEndDate());
			downloadRequest.setActualFailureCode(activityBase.getActualFailureCode());
			downloadRequest.setActualStart(activityBase.getDebriefServiceStartDate());
			downloadRequest.setAddlServiceReq(activityBase.getAddlServiceReq());
			downloadRequest.setComments(activityBase.getComments());
			downloadRequest.setCustomerRequestResponse(activityBase.getCustomerRequestedResponseDate());
			downloadRequest.setDeInstalledAssetTag(activityBase.getRepairedAssetDeviceTag());
		
			if(activityBase.getRepairedDevices() != null && activityBase.getRepairedDevices().size() > 0) {
				for(PartnerActivityDetailRepairedDeviceDo repairAssetDetail : activityBase.getRepairedDevices()) {
					if(repairAssetDetail.isPrimaryFlag()) {
						downloadRequest.setDeInstalledPageCount(repairAssetDetail.getPageCount());
					}
				}
				
			}

			downloadRequest.setEstTimeArrival(activityBase.getEstimatedArrivalTime());
			downloadRequest.setInstalledAssetTag(activityBase.getAssetDeviceTag());
			downloadRequest.setInstalledDeviceCondition(activityBase.getDebriefDeviceCondition());
			downloadRequest.setInstalledIPAddress(activityBase.getAssetIPAddress());
			downloadRequest.setInstalledMacAddress(activityBase.getInstalledAssetMACAddress());
			
			if(activityBase.getAssetNetworkConnectedFlag() != null && activityBase.getAssetNetworkConnectedFlag()) {
				downloadRequest.setNetworkConnected("Y");
			} else  {
				downloadRequest.setNetworkConnected("N");
			}
			
			downloadRequest.setNonLexmarkSuppliesUsed(activityBase.getDebriefGenuineLexmarkSuppliesUsedFlag());
			if(activityBase.getInstalledDevices() != null && activityBase.getInstalledDevices().size() > 0) {
				for(PartnerActivityDetailInstalledAssetDo installAssetDetail : activityBase.getInstalledDevices()) {
					if(installAssetDetail.isPrimaryFlag()) {
						downloadRequest.setInstalledPageCount(installAssetDetail.getPageCount());
					}
				}
				
			}
		
			List<DownloadRequestPart> downloadPartsList = new ArrayList<DownloadRequestPart>();
			if(activityBase.getParts() != null){
				for(PartnerActivityDetailPartDo partnerPart : activityBase.getParts()) {
					if(!partnerPart.isOrderFlag()) {
						DownloadRequestPart downloadPart = new DownloadRequestPart();
						downloadPart.setRecPartsDisp(partnerPart.getLineStatus());
						downloadPart.setRecPartsErrCd1(partnerPart.getErrorCode1());
						downloadPart.setRecPartsErrCd2(partnerPart.getErrorCode2());
						downloadPart.setRecPartsName(partnerPart.getPartNumber());
						downloadPart.setRecPartsQty(Integer.toString(partnerPart.getQuantity()));
						downloadPart.setRecPartsRetCarrier(partnerPart.getCarrier());
						downloadPart.setRecPartsRetTrackNum(partnerPart.getTrackingNumber());
						downloadPartsList.add(downloadPart);
					}
				}
			}
			
			downloadRequest.setDownloadRequestPart(downloadPartsList);
			downloadRequest.setResolutionCode(activityBase.getDebriefResolutionCode());
			downloadRequest.setsPReferenceNum(activityBase.getSPReferenceNum());
			downloadRequest.setStatusAsOf(activityBase.getStatusAsOf());
			downloadRequest.setSupplyManufacturer(activityBase.getDebriefSupplyManufacturer());
			downloadRequest.setTechnician(activityBase.getTechnician());
			downloadRequest.setTravelDistance(activityBase.getDebriefTravelDistance());
			downloadRequest.setTravelDistanceUM(activityBase.getDebriefTravelUnitOfMeasure());
			downloadRequest.setTravelDurationMin(activityBase.getDebriefTravelDuration());
			downloadRequest.setDebriefStatus(activityBase.getDebriefActionStatus());
			
			if(StringUtils.isEmpty(activityBase.getActivitySubStatus())) 
			{
				downloadRequest.setActivitySubStatus("Pending SP Acknowledgement");
			} else 
			{
				downloadRequest.setActivitySubStatus(activityBase.getActivitySubStatus());
			}
			
			downloadRequestList.add(downloadRequest);
		}
		return downloadRequestList;
	}
	
	private static boolean matchInstallAssetAndRepairedAsset(PartnerActivityDetailDo activityDetail) {
		boolean matchFlag = false;
		if(activityDetail.getRepairedSerialNumber().equalsIgnoreCase(activityDetail.getInstalledAssetSerialNumber())) {
				matchFlag = true;
			}
		return matchFlag;
	}

	private static List<Attachment> populateAttachmentList(List<PartnerDetailAttachmentBase> attachmentList, ArrayList<ServiceRequestActivityAttachmentsDo> serviceRequestAttachments) {
		List<Attachment> attachList = new ArrayList<Attachment>();
		List<Attachment> serviceRequestAttachList = new ArrayList<Attachment>();
		
			for(PartnerDetailAttachmentBase attachment : attachmentList) {
			if(!StringUtils.isEmpty(attachment.getAttachmentName())){
				Attachment claimAttachment = new Attachment();
				if(LangUtil.isNotEmpty(attachment.getDisplayFileName())){
					claimAttachment.setAttachmentName(attachment.getDisplayFileName());
				}else{
					String siebelFileName = attachment.getAttachmentName();
					String portalFileName = siebelFileName.substring(siebelFileName.indexOf("@")+1);
					String fileName = portalFileName+"."+attachment.getActivityFileExt();
					claimAttachment.setAttachmentName(fileName);
				}
				claimAttachment.setActivityId(attachment.getActivityId());
				claimAttachment.setActualFileName(attachment.getAttachmentName() + "." +  attachment.getActivityFileExt());
				claimAttachment.setIdentifier(attachment.getIdentifier());
				claimAttachment.setType(attachment.getType());
				claimAttachment.setSize(Integer.parseInt(attachment.getFileSize().replace(",", "")));
				claimAttachment.setFileSourcePath(attachment.getFileSourcePath());
				
				attachList.add(claimAttachment);
			}
		}
			serviceRequestAttachList = populateServiceRequestAttachments(serviceRequestAttachments);
			
			if(LangUtil.isNotEmpty(serviceRequestAttachList)){
				for (Attachment srAttachment : serviceRequestAttachList) {
					attachList.add(srAttachment);
				}
			}

		return attachList;

}
	
	private static List<PartLineItem> populatePartsList(List<PartnerDetailPartBase> partList){
		List<PartLineItem> partLineItemList = new ArrayList<PartLineItem>();
		
		for (PartnerDetailPartBase partnerDetailPartBase : partList) {
			PartLineItem parts = new PartLineItem();
			if(!partnerDetailPartBase.getPartName().isEmpty() && !partnerDetailPartBase.getId().isEmpty()){
				parts.setPartNumber(partnerDetailPartBase.getPartNumber());
				parts.setPartName(partnerDetailPartBase.getPartName());
				parts.setQuantity(partnerDetailPartBase.getQuantity());
				parts.setProductId(partnerDetailPartBase.getProductId());				
				parts.setLineStatus(populatePartLineStatus(partnerDetailPartBase.getLineStatus()));
				parts.setErrorCode1(populatePartErrorCode1(partnerDetailPartBase.getErrorCode1()));
				parts.setErrorCode2(populatePartErrorCode2(partnerDetailPartBase.getErrorCode2()));
				parts.setTrackingNumber(partnerDetailPartBase.getTrackingNumber());
				parts.setPartOrderedDate(partnerDetailPartBase.getPartOrderedDate());
				parts.setCarrier(populatePartCarrier(partnerDetailPartBase.getCarrier()));
				parts.setRecommendedType(partnerDetailPartBase.getRecommendedType());
				parts.setOrderLineStatus(partnerDetailPartBase.getOrderLineStatus());
				parts.setSource(partnerDetailPartBase.getSource());
				parts.setShippingMethod(partnerDetailPartBase.getShippingMethod());
				parts.setProductDescription(partnerDetailPartBase.getProductDescription());
				parts.setMachineTypeModel(partnerDetailPartBase.getMachineTypeModel());
				parts.setRelationType(partnerDetailPartBase.getRelationType());
				parts.setUsedQuantity(partnerDetailPartBase.getUsedQuantity());
				parts.setNotUsedQuantity(partnerDetailPartBase.getNotUsedQuantity());
				parts.setDoaQuantity(partnerDetailPartBase.getDoaQuantity());
				partLineItemList.add(parts);
			}
			
		}
		return partLineItemList;
		
	}
	
	private static List<PartLineItem> populateDebrifPartsList(List<PartnerDetailPartBase> debrifPartList){
		List<PartLineItem> partLineItemList = new ArrayList<PartLineItem>();
		
		for (PartnerDetailPartBase partnerDetailPartBase : debrifPartList) {
			PartLineItem parts = new PartLineItem();
			if(!partnerDetailPartBase.getPartName().isEmpty() && !partnerDetailPartBase.getId().isEmpty()){
				parts.setPartNumber(partnerDetailPartBase.getPartNumber());
				parts.setPartName(partnerDetailPartBase.getPartName());
				parts.setQuantity(partnerDetailPartBase.getQuantity());
				parts.setProductId(partnerDetailPartBase.getProductId());				
				parts.setLineStatus(populatePartLineStatus(partnerDetailPartBase.getLineStatus()));
				parts.setErrorCode1(populatePartErrorCode1(partnerDetailPartBase.getErrorCode1()));
				parts.setErrorCode2(populatePartErrorCode2(partnerDetailPartBase.getErrorCode2()));
				parts.setTrackingNumber(partnerDetailPartBase.getTrackingNumber());
				parts.setPartOrderedDate(partnerDetailPartBase.getPartOrderedDate());
				parts.setCarrier(populatePartCarrier(partnerDetailPartBase.getCarrier()));
				parts.setRecommendedType(partnerDetailPartBase.getRecommendedType());
				parts.setOrderLineStatus(partnerDetailPartBase.getOrderLineStatus());
				parts.setSource(partnerDetailPartBase.getSource());
				parts.setShippingMethod(partnerDetailPartBase.getShippingMethod());
				parts.setProductDescription(partnerDetailPartBase.getProductDescription());
				parts.setMachineTypeModel(partnerDetailPartBase.getMachineTypeModel());
				parts.setRelationType(partnerDetailPartBase.getRelationType());
				parts.setUsedQuantity(partnerDetailPartBase.getUsedQuantity());
				parts.setNotUsedQuantity(partnerDetailPartBase.getNotUsedQuantity());
				parts.setDoaQuantity(partnerDetailPartBase.getDoaQuantity());
				partLineItemList.add(parts);
			}
			
		}
		return partLineItemList;
		
	}
	
	private static ListOfValues populatePartLineStatus(String lineStatusStr) {
		ListOfValues lineStatus = new ListOfValues();
		lineStatus.setValue(lineStatusStr);
		return lineStatus;
	}
	private static ListOfValues populatePartErrorCode1(String errorCode1) {
		ListOfValues error1 = new ListOfValues();
		error1.setValue(errorCode1);
		return error1;
	}
	private static ListOfValues populatePartErrorCode2(String errorCode2) {
		ListOfValues error2 = new ListOfValues();
		error2.setValue(errorCode2);
		return error2;
	}
	private static ListOfValues populatePartCarrier(String carrier) {
		ListOfValues carrie = new ListOfValues();
		carrie.setValue(carrier);
		return carrie;
	}
	private static ListOfValues populatePartStatus(String status) {
		ListOfValues statuss = new ListOfValues();
		statuss.setValue(status);
		return statuss;
	}
	
	private static List<PageCounts> populateMassUploadPageCount(PartnerActivityBase activityDo) {
		
		List<PageCounts> pageCounts = new ArrayList<PageCounts>();
		
		if(activityDo instanceof PartnerActivityDo) {
			
			PartnerActivityDo partnerActivityDo = (PartnerActivityDo)activityDo;
			
			List<PartnerActivityListMassUploadDebriefDo> pgDOs = partnerActivityDo.getMassUploadDebriefList(); 
			
			if(LangUtil.isEmpty(pgDOs)) {
				
				return pageCounts;
			}
			
			for(PartnerActivityListMassUploadDebriefDo pgCount : pgDOs)
			{
				PageCounts pg = new PageCounts();
				pg.setType(pgCount.getPageCountType());
				pg.setCount(pgCount.getPageCount());
				pageCounts.add(pg);
			}
			
		}
		return pageCounts;
	}
			
			
	
	private static List<Part> populatePartList(PartnerActivityBase activityDo, boolean massUpload) {
		
		List<Part> parts = new ArrayList<Part>();
		
		if(activityDo instanceof PartnerActivityDetailDo) {
			
			PartnerActivityDetailDo activityDetailDo = (PartnerActivityDetailDo)activityDo;
			
			List<PartnerActivityDetailPartDo> partDOs = AmindServiceUtil.removeNulls(activityDetailDo.getParts()); 
			
			if(LangUtil.isEmpty(partDOs)) {
				return parts;
			}
			
			for (PartnerActivityDetailPartDo partDo : partDOs) {
				if(!"ASSOCIATED".equalsIgnoreCase(partDo.getRelationType()))
				{
					Part part = new Part();
					part.setPartNumber(partDo.getPartNumber());
					part.setDescription(partDo.getProductDescription());
					part.setOrderQuantity(String.valueOf(partDo.getQuantity()));
					part.setModel(partDo.getMachineTypeModel());
					part.setRelationType(partDo.getRelationType());
					part.setUsedQuantity(partDo.getUsedQuantity());
					part.setNotUsedQuantity(partDo.getNotUsedQuantity());
					part.setDoaQuantity(partDo.getDoaQuantity());
					parts.add(part);
				}
			}
		}
		else if(activityDo instanceof PartnerActivityDo){
			
			PartnerActivityDo partnerActivityDo = (PartnerActivityDo)activityDo;
			
			List<PartnerActivityListPartDo> partDOs = AmindServiceUtil.removeNulls(partnerActivityDo.getPartsList()); 
			
			if(LangUtil.isEmpty(partDOs)) {
				return parts;
			}
			
			for (PartnerActivityListPartDo partDo : partDOs) {
				Part part = new Part();
				part.setPartNumber(partDo.getPartNumber());
				part.setDescription(partDo.getProductDescription());
				part.setOrderQuantity(partDo.getRecommendedQuantity());
				part.setModel(partDo.getMachineTypeModel());
				if(massUpload) {
					part.setMassuploadRecommendedType(partDo.getMassuploadRecommendedType());
					part.setMassuploadSequenceNumber(partDo.getMassuploadSequenceNumber());
					part.setUsedQuantity(partDo.getMassuploadUsedQuantity());
					part.setNotUsedQuantity(partDo.getMassuploadUnusedQuantity());
					part.setDoaQuantity(partDo.getMassuploadDOAQuantity());
				}
				parts.add(part);
			}
		}
		
		else if(activityDo instanceof PartnerOfflineModeActivityDo){
			
			PartnerOfflineModeActivityDo partnerActivityOfflineModeDo = (PartnerOfflineModeActivityDo)activityDo;
			
			List<PartnerOfflineModeActivityListPartDo> partDOs = AmindServiceUtil.removeNulls(partnerActivityOfflineModeDo.getPartsList()); 
			
			if(LangUtil.isEmpty(partDOs)) {
				return parts;
			}
			
			for (PartnerOfflineModeActivityListPartDo partDo : partDOs) {
				Part part = new Part();
				part.setPartNumber(partDo.getPartNumber());
				part.setDescription(partDo.getProductDescription());
				part.setOrderQuantity(partDo.getRecommendedQuantity());
				part.setModel(partDo.getMachineTypeModel());
				
				parts.add(part);
			}
		}
		
		else if(activityDo instanceof ServiceRequestPartnerOfflineModeActivityDo){
			
			ServiceRequestPartnerOfflineModeActivityDo serviceRequestPartnerActivityOfflineModeDo = (ServiceRequestPartnerOfflineModeActivityDo)activityDo;
			
			List<ServiceRequestPartnerOfflineModeActivityListPartDo> partDOs = AmindServiceUtil.removeNulls(serviceRequestPartnerActivityOfflineModeDo.getPartsList()); 
			
			if(LangUtil.isEmpty(partDOs)) {
				return parts;
			}
			
			for (ServiceRequestPartnerOfflineModeActivityListPartDo partDo : partDOs) {
				Part part = new Part();
				part.setPartNumber(partDo.getPartNumber());
				part.setDescription(partDo.getProductDescription());
				part.setOrderQuantity(partDo.getRecommendedQuantity());
				part.setModel(partDo.getMachineTypeModel());
				
				parts.add(part);
			}
		}
		else if(activityDo instanceof PartnerActivityGridDo){
			
			PartnerActivityGridDo serviceRequestPartnerActivityGridDo = (PartnerActivityGridDo)activityDo;
					
					List<PartnerActivityListRecommPartDo> partDOs = AmindServiceUtil.removeNulls(serviceRequestPartnerActivityGridDo.getPartsList()); 
					
					if(LangUtil.isEmpty(partDOs)) {
						return parts;
					}
					
					for (PartnerActivityListRecommPartDo partDo : partDOs) {
						Part part = new Part();
						part.setPartNumber(partDo.getPartNumber());
						part.setDescription(partDo.getProductDescription());
						part.setOrderQuantity(partDo.getRecommendedQuantity());
						part.setModel(partDo.getMachineTypeModel());
						
						parts.add(part);
					}
				}

		return parts;
	}
	
	private static GenericAddress populateDetailsServiceAddress(PartnerActivityDetailDo activityDetail) {
		GenericAddress serviceAddress = new GenericAddress();
		serviceAddress.setAddressName(activityDetail.getServiceAddressName());
		
		return serviceAddress;
	}
	
	private static GenericAddress populateDetailsNewAddress(PartnerActivityDetailDo activityDetail) {
		GenericAddress newAddress = new GenericAddress();
		newAddress.setAddressName(activityDetail.getHardwareDebriefAddressName());
		
		return newAddress;
	}
	

	private static GenericAddress populateDetailsInstallAddress(PartnerActivityDetailDo activityDetail) {
		GenericAddress installAddress = new GenericAddress();
		installAddress.setAddressLine1(activityDetail.getAssetInstalledAddressLine1());
		installAddress.setAddressLine2(activityDetail.getAssetInstalledAddressLine2());
		installAddress.setAddressLine3(activityDetail.getAssetInstalledAddressLine3());
		installAddress.setAddressName(activityDetail.getAssetInstalledAddressName());
		installAddress.setCity(activityDetail.getAssetInstalledCity());
		installAddress.setCountry(activityDetail.getAssetInstalledCountry());
		installAddress.setProvince(activityDetail.getAssetInstalledProvince());
		installAddress.setCounty(activityDetail.getAssetInstalledCounty());
		installAddress.setState(activityDetail.getAssetInstalledState());
		installAddress.setPostalCode(activityDetail.getAssetInstalledZipcode());
		installAddress.setStoreFrontName(activityDetail.getStoreFrontName());
			if (LangUtil.isEmpty(activityDetail.getAssetId())) {
				installAddress.setPhysicalLocation1(LangUtil.isEmpty(activityDetail.getAssetInstalledAddressBuilding())? activityDetail.getPartnerPortalPhysicaLocation1():activityDetail.getAssetInstalledAddressBuilding());
				installAddress.setPhysicalLocation2(LangUtil.isEmpty(activityDetail.getAssetInstalledAddressFloor())? activityDetail.getPartnerPortalPhysicaLocation2():activityDetail.getAssetInstalledAddressFloor());
				installAddress.setPhysicalLocation3(LangUtil.isEmpty(activityDetail.getAssetInstalledAddressOffice())? activityDetail.getPartnerPortalPhysicaLocation3():activityDetail.getAssetInstalledAddressOffice());
			}
			else {
				installAddress.setPhysicalLocation1(activityDetail.getAssetInstalledAddressBuilding());
				installAddress.setPhysicalLocation2(activityDetail.getAssetInstalledAddressFloor());
				installAddress.setPhysicalLocation3(activityDetail.getAssetInstalledAddressOffice());
			}
		installAddress.setLbsAddressFlag(activityDetail.getLbsAddressFlag());
		installAddress.setZoneName(activityDetail.getZoneName());
		installAddress.setCampusName(activityDetail.getCampusName());
		installAddress.setAddressId(activityDetail.getAddressId());
		installAddress.setCoordinatesXPreDebriefRFV(activityDetail.getCoordinatesXPreDebriefRFV());
		installAddress.setCoordinatesYPreDebriefRFV(activityDetail.getCoordinatesYPreDebriefRFV());
		return installAddress;
	}
	
	private static List<PageCounts> populatePageCounts(PartnerActivityBase activityBase) {
		List<PageCounts> pageCountsList = new ArrayList<PageCounts>();
		
		if (activityBase instanceof PartnerActivityDetailDo) {
			PartnerActivityDetailDo activityDetail = (PartnerActivityDetailDo) activityBase;
			for (PartnerActivityAssetReadingDo readingDo : LangUtil.notNull(activityDetail.getReadings())) {
				if ("DeInstalled".equalsIgnoreCase(readingDo.getType())) {
					if (LangUtil.isNotEmpty(readingDo.getCountType())) {
						PageCounts pageCounts = new PageCounts();
						pageCounts.setDeinstalledCount(readingDo.getPageCount());
						pageCounts.setDeinstalledType(readingDo.getCountType());
						pageCountsList.add(pageCounts);
					}
				} else {
					if (LangUtil.isNotEmpty(readingDo.getCountType())) {
						PageCounts pageCounts = new PageCounts();
						pageCounts.setCount(readingDo.getPageCount());
						pageCounts.setType(readingDo.getCountType());
						pageCountsList.add(pageCounts);
					}
				}
			}
		}

		return pageCountsList;
	}
	
	private static Account populateAssetCustomerAccount(PartnerActivityDetailDo activityDetail) {
		Account account = new Account();
		GenericAddress address = new GenericAddress();
		address.setAddressId(activityDetail.getServiceAddressId());
		address.setAddressName(activityDetail.getServiceAddressName());
		address.setAddressLine1(activityDetail.getServiceAddressLine1());
		address.setAddressLine2(activityDetail.getServiceAddressLine2());
		address.setAddressLine3(activityDetail.getServiceAddressLine3());
		address.setCity(activityDetail.getServiceCity());
		address.setState(activityDetail.getServiceState());
		address.setCountry(activityDetail.getServiceCountry());
		address.setPostalCode(activityDetail.getServicePostalCode());
		address.setProvince(activityDetail.getServiceProvince());
		address.setCounty(activityDetail.getCounty());
		address.setDistrict(activityDetail.getDistrict());
		address.setOfficeNumber(activityDetail.getOfficeNumber());
		address.setStoreFrontName(activityDetail.getStoreFrontName());
		
		account.setAddress(address);
		return account;
	}
	
	private static AccountContact populateSecondarySuppliesContact(PartnerActivityBase activityBase) {
		AccountContact secondarySuppliesContact = new AccountContact();
		secondarySuppliesContact.setFirstName(activityBase.getSecondarySuppliesContactFirstName());
		secondarySuppliesContact.setLastName(activityBase.getSecondarySuppliesContactLastName());
		
		return secondarySuppliesContact;
	}
	
	private static GenericAddress populatePickupAddress(PartnerActivityDetailDo activityDetail) {
		GenericAddress pickupAddress = new GenericAddress();
		pickupAddress.setAddressName(activityDetail.getSRAddressName());
		pickupAddress.setAddressLine1(activityDetail.getServiceAddressLine1()); 
		pickupAddress.setAddressLine2(activityDetail.getServiceAddressLine2()); 
		pickupAddress.setAddressLine3(activityDetail.getServiceAddressLine3()); 
		pickupAddress.setCity(activityDetail.getServiceCity()); 
		pickupAddress.setState(activityDetail.getServiceState()); 
		pickupAddress.setCountry(activityDetail.getServiceCountry()); 
		pickupAddress.setPostalCode(activityDetail.getServicePostalCode()); 
		pickupAddress.setProvince(activityDetail.getServiceProvince()); 
		pickupAddress.setCounty(activityDetail.getServiceCounty());
		pickupAddress.setDistrict(activityDetail.getServiceDistrict());
		pickupAddress.setCountryISOCode(activityDetail.getServiceCountryCode());
		pickupAddress.setOfficeNumber(activityDetail.getServiceHouseNo());
		pickupAddress.setRegion(activityDetail.getServiceRegion());
		pickupAddress.setStateFullName(activityDetail.getServiceStateFullName());
		pickupAddress.setLatitude(activityDetail.getServiceLatitude());
		pickupAddress.setLongitude(activityDetail.getServiceLongitude());
		pickupAddress.setPhysicalLocation1(activityDetail.getPartnerPortalPhysicaLocation1());
		pickupAddress.setPhysicalLocation2(activityDetail.getPartnerPortalPhysicaLocation2());
		pickupAddress.setPhysicalLocation3(activityDetail.getPartnerPortalPhysicaLocation3());
		return pickupAddress;
	}
	
	private static OfflineModeAttachment populateOfflineModeAttachment(PartnerOfflineModeActivityDo partnerActivityDO) {
		
		OfflineModeAttachment attachment = new OfflineModeAttachment();
		
		PartnerOfflineModeActivityDo activityDo = (PartnerOfflineModeActivityDo) partnerActivityDO;
		List<OfflineModeRequestAttachmentDO> attachmentDOsList = activityDo.getOfflineModeAttachments();
		
		if(LangUtil.isEmpty(attachmentDOsList)) {
			attachment.setComments("");
			return attachment;
		}

		OfflineModeRequestAttachmentDO attachmentDO = attachmentDOsList.get(0);
//		for (OfflineModeRequestAttachmentDO offlineModeRequestAttachmentDOFromList : attachmentDOsList) {
//			//TODO: Need to make sure what to do when "created" is null
//			if(attachmentDO == offlineModeRequestAttachmentDOFromList 
//					|| attachmentDO.getCreated() == null || offlineModeRequestAttachmentDOFromList.getCreated() == null) {
//				continue;
//			}
//			Date currentDate = LangUtil.convertStringToGMTDate(attachmentDO.getCreated());
//			Date newDate = LangUtil.convertStringToGMTDate(offlineModeRequestAttachmentDOFromList.getCreated());
//			if(newDate.compareTo(currentDate) > 0) {
//				attachmentDO = offlineModeRequestAttachmentDOFromList;
//			}
//		}
		
		attachment.setCreated(attachmentDO.getCreated());
		attachment.setAttachmentId(attachmentDO.getAttachmentId());
		attachment.setAutoUploadFlag(attachmentDO.getAutoUploadFlag());
		attachment.setFileDate(attachmentDO.getFileDate());
		attachment.setFileExtension(attachmentDO.getFileExtension());
		attachment.setFileName(attachmentDO.getFileName());
		attachment.setFileSize(attachmentDO.getFileSize());
		attachment.setFileSourcePath(attachmentDO.getFileSourcePath());
		attachment.setFileSourceType(attachmentDO.getFileSourcePath());
		attachment.setStatus(attachmentDO.getStatus());
		attachment.setVisibility(attachmentDO.getVisibility());
		attachment.setComments(attachmentDO.getComments());
		
		return attachment;
	}



private static OfflineModeAttachment populateOfflineModeAttachmentSR(ServiceRequestPartnerOfflineModeActivityDo partnerActivityDO) {
		
		OfflineModeAttachment attachment = new OfflineModeAttachment();
		
		ServiceRequestPartnerOfflineModeActivityDo activityDo = (ServiceRequestPartnerOfflineModeActivityDo) partnerActivityDO;
		List<ServiceRequestOfflineModeRequestAttachmentDO> attachmentDOsList = activityDo.getOfflineModeAttachments();
		
		if(LangUtil.isEmpty(attachmentDOsList)) {
			attachment.setComments("");
			return attachment;
		}
	
		ServiceRequestOfflineModeRequestAttachmentDO attachmentDO = attachmentDOsList.get(0);
		attachment.setCreated(attachmentDO.getCreated());
		attachment.setAttachmentId(attachmentDO.getAttachmentId());
		attachment.setAutoUploadFlag(attachmentDO.getAutoUploadFlag());
		attachment.setFileDate(attachmentDO.getFileDate());
		attachment.setFileExtension(attachmentDO.getFileExtension());
		attachment.setFileName(attachmentDO.getFileName());
		attachment.setFileSize(attachmentDO.getFileSize());
		attachment.setFileSourcePath(attachmentDO.getFileSourcePath());
		attachment.setFileSourceType(attachmentDO.getFileSourcePath());
		attachment.setStatus(attachmentDO.getStatus());
		attachment.setVisibility(attachmentDO.getVisibility());
		attachment.setComments(attachmentDO.getComments());
		
		return attachment;
	}

	public static List<Activity> filterCreateChildSRActivityList(ActivityListResult result, String childSRFilter) {
		List<Activity> filteredActivity = new ArrayList<Activity>();
			for (Activity filterChildSRActivities : result.getActivityList()) {
				String currentChildSR = toString(filterChildSRActivities.isCreateChildSR());
					if(LangUtil.equalIgnoreCase(currentChildSR, childSRFilter)){
						filteredActivity.add(filterChildSRActivities);
					}
			}
		return filteredActivity;
	}
	
	public static String toString(boolean value) {
	    return value ? "true" : "false";
	}

}


	
