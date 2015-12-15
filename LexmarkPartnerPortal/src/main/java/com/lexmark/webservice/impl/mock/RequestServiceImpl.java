package com.lexmark.webservice.impl.mock;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.ActivityDebriefSubmitContract;
import com.lexmark.contract.ChildSRContract;
import com.lexmark.contract.RequestUpdateContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ActivityNote;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.enums.RequestStatusEnum;
import com.lexmark.result.ActivityDebriefSubmitResult;
import com.lexmark.result.CreateChildSRResult;
import com.lexmark.result.RequestUpdateResult;
import com.lexmark.service.impl.mock.PartnerDomainMockDataGenerator;
import com.lexmark.webservice.api.RequestService;

public class RequestServiceImpl implements RequestService {
	private static Logger logger = LogManager.getLogger(RequestServiceImpl.class);
	
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
	public RequestUpdateResult updateRequest(RequestUpdateContract contract)
			throws Exception {
		logger.debug("************ UPDATE REQUEST - MOCK ************");
		logger.debug("address: " + address);
		logger.debug("senderId: " + senderId);
		logger.debug("senderName: " + senderName);
		logger.debug("receiverId: " + receiverId);
		logger.debug("claimSource: " + claimSource);
		logger.debug("synchOrAsynch: " + synchOrAsynch);
		RequestUpdateResult result = new RequestUpdateResult();
		for(ActivityNote note :contract.getActivity().getActivityNoteList()){
			if(note.getNoteId()=="" || note.getNoteId()==null){
				note.setNoteId(String.valueOf(new Date().getTime()));
			}
		}
		result.setSuccess(true);
		List<Activity> activityList = PartnerDomainMockDataGenerator.getActivities();
		for (Activity activity : activityList) {
			if (contract.getActivity().getActivityId().equals(activity.getServiceRequest().getId())) {
				activity = contract.getActivity();
				result.setSuccess(true);
				return result;
			}
		}
		
		return result;
	}

	@Override
	public ActivityDebriefSubmitResult submitActivityDebrief(
			ActivityDebriefSubmitContract contract) throws Exception {
		logger.debug("************ SUBMIT ACTIVITY DEBRIEF - MOCK ************");
		logger.debug("address: " + address);
		logger.debug("senderId: " + senderId);
		logger.debug("senderName: " + senderName);
		logger.debug("receiverId: " + receiverId);
		logger.debug("claimSource: " + claimSource);
		logger.debug("synchOrAsynch: " + synchOrAsynch);
		Activity debriefActivity = contract.getActivity();
		String activityId = debriefActivity.getActivityId();
		ActivityDebriefSubmitResult activityDebriefSubmitResult = new ActivityDebriefSubmitResult();
		List<Activity> activityList = PartnerDomainMockDataGenerator.getActivities();
		ListOfValues statusCompleted = new ListOfValues();
		statusCompleted.setType(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue());
		statusCompleted.setValue(RequestStatusEnum.COMPLETED.getValue());
		for (Activity activity : activityList) {
			if (activityId.equals(activity.getActivityId())) {
				activity.setActualFailureCode(debriefActivity.getDebrief().getActualFailureCode());
				activity.setActivityStatus(statusCompleted);
				activity.getServiceRequest().setServiceRequestStatus("Closed");
				activity.setRepairCompleteFlag(debriefActivity.isRepairCompleteFlag());
			
				AccountContact accountContact = debriefActivity.getTechnician();
				if(accountContact.getContactId() == ""){
					activity.setTechnician(accountContact);
				}else{
					for(AccountContact contact : PartnerDomainMockDataGenerator.getAccountContactList()){
						if (accountContact.getContactId().equals(contact.getContactId())) {
							activity.setTechnician(contact);
							break;
						}
					}
				}
				
				activity.setShipToAddress(debriefActivity.getShipToAddress());
				
				activity.setDebrief(debriefActivity.getDebrief());
				activity.getServiceRequest().setProblemDescription(debriefActivity.getServiceRequest().getProblemDescription());
				activity.setAdditionalPaymentRequestList(debriefActivity.getAdditionalPaymentRequestList());
				activity.setActivityNoteList(debriefActivity.getActivityNoteList());
				
				activity.getServiceRequest().getAsset().setIpAddress(debriefActivity.getServiceRequest().getAsset().getIpAddress());
				activity.getServiceRequest().getAsset().setMacAddress(debriefActivity.getServiceRequest().getAsset().getMacAddress());
				activity.getServiceRequest().getAsset().setNewPageCount(debriefActivity.getServiceRequest().getAsset().getNewPageCount());
				activity.getServiceRequest().getAsset().setAssetTag(debriefActivity.getServiceRequest().getAsset().getAssetTag());
				activity.getServiceRequest().getAsset().setNetworkConnectedFlag(debriefActivity.getServiceRequest().getAsset().getNetworkConnectedFlag());
			
				for (ActivityNote activityNote : debriefActivity.getActivityNoteList()) {
					for(AccountContact contact : PartnerDomainMockDataGenerator.getAccountContactList()){
						if (activityNote.getNoteAuthor().getContactId().equals(contact.getContactId())) {
							activityNote.setNoteAuthor(contact);
							break;
						}
					}
				}
			}
		}
		
		activityDebriefSubmitResult.setSuccess(true);
		return activityDebriefSubmitResult;
	}
	
	public CreateChildSRResult createChildSR(ChildSRContract contract)
			throws Exception {
		boolean status = true;
		CreateChildSRResult createChildSRResult= new CreateChildSRResult();
		createChildSRResult.setSuccess(status);
		return createChildSRResult;
	}


}
