package com.lexmark.webservice.impl.mock;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.ClaimDebriefSubmitContract;
import com.lexmark.contract.ClaimUpdateContract;
import com.lexmark.contract.WarrantyClaimCreateContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ActivityNote;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.enums.RequestStatusEnum;
import com.lexmark.enums.RequestSubStatusEnum;
import com.lexmark.result.ClaimDebriefSubmitResult;
import com.lexmark.result.ClaimUpdateResult;
import com.lexmark.result.WarrantyClaimCreateResult;
import com.lexmark.service.impl.mock.PartnerDomainMockDataGenerator;
import com.lexmark.webservice.api.ClaimService;

public class ClaimServiceImpl implements ClaimService {
	private static Logger logger = LogManager.getLogger(ClaimServiceImpl.class);
	
	private String address;
	private String senderId; 
	private String senderName;
	private String receiverId;
	private String claimSource;
	private String synchOrAsynch;
	
	
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

	public String getClaimSource() {
		return claimSource;
	}

	public void setClaimSource(String claimSource) {
		this.claimSource = claimSource;
	}

	public String getSynchOrAsynch() {
		return synchOrAsynch;
	}

	public void setSynchOrAsynch(String synchOrAsynch) {
		this.synchOrAsynch = synchOrAsynch;
	}

	@Override
	public WarrantyClaimCreateResult createWarrantyClaim(
			WarrantyClaimCreateContract contract) throws Exception {
		logger.debug("************ CREATE WARRANTY CLAIM - MOCK ************");
		logger.debug("address: " + address);
		logger.debug("senderId: " + senderId);
		logger.debug("senderName: " + senderName);
		logger.debug("receiverId: " + receiverId);
		logger.debug("claimSource: " + claimSource);
		logger.debug("synchOrAsynch: " + synchOrAsynch);
		WarrantyClaimCreateResult result = new WarrantyClaimCreateResult();
		Activity activity = contract.getActivity();
		activity.setActivityId("123785");
		if(activity.getServiceRequest().getAsset().getAssetId() == null){
			activity.getServiceRequest().getAsset().setAssetId("3453445");
		}
		
		ListOfValues activityStatusLOV = new ListOfValues();
		activityStatusLOV.setValue(RequestStatusEnum.OPEN.getValue());
		activityStatusLOV.setName(RequestStatusEnum.OPEN.getValue());
		
		ListOfValues activitySubStatusLOV = new ListOfValues();
		activitySubStatusLOV.setValue(RequestSubStatusEnum.TECH_ON_SITE.getValue());
		activitySubStatusLOV.setName(RequestSubStatusEnum.TECH_ON_SITE.getValue());
		
		activity.setActivityStatus(activityStatusLOV);
		activity.setActivitySubStatus(activitySubStatusLOV);
		
		PartnerDomainMockDataGenerator.getActivities().add(activity);
		result.setRequestId("123785");
		return result;
	}

	@Override
	public ClaimUpdateResult updateWarrantyClaim(ClaimUpdateContract contract)
			throws Exception {
		logger.debug("************ UPDATE WARRANTY CLAIM - REAL ************");
		logger.debug("address: " + address);
		logger.debug("senderId: " + senderId);
		logger.debug("senderName: " + senderName);
		logger.debug("receiverId: " + receiverId);
		logger.debug("claimSource: " + claimSource);
		logger.debug("synchOrAsynch: " + synchOrAsynch);

		ClaimUpdateResult result = new ClaimUpdateResult();
		result.setSuccess(true);
		return result;
	}

	@Override
	public ClaimDebriefSubmitResult submitClaimDebrief(
			ClaimDebriefSubmitContract contract) throws Exception {
		logger.debug("************ SUBMIT CLAIM DEBRIEF - MOCK ************");
		logger.debug("address: " + address);
		logger.debug("senderId: " + senderId);
		logger.debug("senderName: " + senderName);
		logger.debug("receiverId: " + receiverId);
		logger.debug("claimSource: " + claimSource);
		logger.debug("synchOrAsynch: " + synchOrAsynch);
		
		Activity debriefActivity = contract.getActivity();
		String activityId = debriefActivity.getActivityId();
		ClaimDebriefSubmitResult claimDebriefSubmitResult = new ClaimDebriefSubmitResult();
		List<Activity> activityList = PartnerDomainMockDataGenerator.getActivities();
		ListOfValues statusCompleted = new ListOfValues();
		statusCompleted.setType(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue());
		statusCompleted.setValue(RequestStatusEnum.COMPLETED.getValue());
		for (Activity activity : activityList) {
			if (activityId.equals(activity.getActivityId())) {
				activity.setActualFailureCode(debriefActivity.getDebrief().getActualFailureCode());
				activity.setActivityStatus(statusCompleted);
				activity.getServiceRequest().setServiceRequestStatus(RequestStatusEnum.COMPLETED.getValue());
				activity.setServiceProviderReferenceNumber(debriefActivity.getServiceProviderReferenceNumber());
				activity.setReviewComments(debriefActivity.getReviewComments());
				
				
				AccountContact accountContact = debriefActivity.getTechnician();
				if(accountContact == null || accountContact.getContactId() == ""){
					activity.setTechnician(accountContact);
				}else{
					for(AccountContact contact : PartnerDomainMockDataGenerator.getAccountContactList()){
						if (accountContact.getContactId().equals(contact.getContactId())) {
							activity.setTechnician(contact);
							break;
						}
					}
				}

				activity.setDebrief(debriefActivity.getDebrief());
				activity.getServiceRequest().setProblemDescription(debriefActivity.getServiceRequest().getProblemDescription());
				activity.setAdditionalPaymentRequestList(debriefActivity.getAdditionalPaymentRequestList());
				activity.setActivityNoteList(debriefActivity.getActivityNoteList());
				
				for (ActivityNote activityNote : debriefActivity.getActivityNoteList()) {
					for(AccountContact contact : PartnerDomainMockDataGenerator.getAccountContactList()){
						if (activityNote.getNoteAuthor().getContactId().equals(contact.getContactId())) {
							activityNote.setNoteAuthor(contact);
							break;
						}
					}
				}
					
				activity.setShipToAddress(debriefActivity.getShipToAddress());
				break;
			}
		}
		claimDebriefSubmitResult.setSuccess(true);
		String mockFalseActivityId = PartnerDomainMockDataGenerator.getActivity(0).getActivityId();
		if(mockFalseActivityId.equals(activityId)){
			claimDebriefSubmitResult.setSuccess(false);
		}
		return claimDebriefSubmitResult;
	}
	


}
