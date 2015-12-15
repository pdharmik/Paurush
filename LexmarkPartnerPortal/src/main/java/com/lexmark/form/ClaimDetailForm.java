package com.lexmark.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lexmark.domain.Activity;
import com.lexmark.domain.AdditionalPaymentRequest;
import com.lexmark.domain.Attachment;

public class ClaimDetailForm extends BaseForm{

	private static final long serialVersionUID = -2540522215207793737L;
	private Activity claimDetail ;
	private Map<String, String> paymentTypes;
	private String activityNoteListXML;
	private String additionalPaymentRequestListXML;
	private String pendingPartListXML;
	private String orderPartListXML;
	private String returnPartListXML;
	private String technicianFullName;
	private String contactId;
	private float timezoneOffset;
	private String fromPage;
	private List<AdditionalPaymentRequest> newAdditionalPaymentRequestList = new ArrayList<AdditionalPaymentRequest>();
	private CommonsMultipartFile fileData;		// for CI5 attachment feature
	private int fileCount;//Attachment
	private String totalFileSize;//Attachment
	private String attachmentFileXML;		
	private List<Attachment> attachmentList;
	
	 private String pageCountAll;
	
	// CI6 Attachment Error Message
		
		private String errorMessage;
		
		//added
		private String formattedServiceStartDate;
		private String formattedServiceEndDate;
		private String formattedServiceRequestedDate;
		//ends here
		
		public String getFormattedServiceStartDate() {
			return formattedServiceStartDate;
		}
		public void setFormattedServiceStartDate(String formattedServiceStartDate) {
			this.formattedServiceStartDate = formattedServiceStartDate;
		}
		public String getFormattedServiceEndDate() {
			return formattedServiceEndDate;
		}
		public void setFormattedServiceEndDate(String formattedServiceEndDate) {
			this.formattedServiceEndDate = formattedServiceEndDate;
		}
		public String getFormattedServiceRequestedDate() {
			return formattedServiceRequestedDate;
		}
		public void setFormattedServiceRequestedDate(
				String formattedServiceRequestedDate) {
			this.formattedServiceRequestedDate = formattedServiceRequestedDate;
		}
		public String getErrorMessage() {
			return errorMessage;
		}
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
	public String getPageCountAll() {
		return pageCountAll;
	}
	public void setPageCountAll(String pageCountAll) {
		this.pageCountAll = pageCountAll;
	}
	public Activity getClaimDetail() {
		return claimDetail;
	}
	public void setClaimDetail(Activity claimDetail) {
		this.claimDetail = claimDetail;
	}
	public String getActivityNoteListXML() {
		return activityNoteListXML;
	}
	public void setActivityNoteListXML(String activityNoteListXML) {
		this.activityNoteListXML = activityNoteListXML;
	}
	public String getAdditionalPaymentRequestListXML() {
		return additionalPaymentRequestListXML;
	}
	public void setAdditionalPaymentRequestListXML(
			String additionalPaymentRequestListXML) {
		this.additionalPaymentRequestListXML = additionalPaymentRequestListXML;
	}
	public String getPendingPartListXML() {
		return pendingPartListXML;
	}
	public void setPendingPartListXML(String pendingPartListXML) {
		this.pendingPartListXML = pendingPartListXML;
	}
	public String getOrderPartListXML() {
		return orderPartListXML;
	}
	public void setOrderPartListXML(String orderPartListXML) {
		this.orderPartListXML = orderPartListXML;
	}
	public String getReturnPartListXML() {
		return returnPartListXML;
	}
	public void setReturnPartListXML(String returnPartListXML) {
		this.returnPartListXML = returnPartListXML;
	}
	public Map<String, String> getPaymentTypes() {
		return paymentTypes;
	}
	public void setPaymentTypes(Map<String, String> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
	public String getTechnicianFullName() {
		return technicianFullName;
	}
	public void setTechnicianFullName(String technicianFullName) {
		this.technicianFullName = technicianFullName;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getFromPage() {
		return fromPage;
	}
	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}
	public void setTimezoneOffset(float timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}
	public float getTimezoneOffset() {
		return timezoneOffset;
	}
	public List<AdditionalPaymentRequest> getNewAdditionalPaymentRequestList() {
		return newAdditionalPaymentRequestList;
	}
	public void setNewAdditionalPaymentRequestList(
			List<AdditionalPaymentRequest> newAdditionalPaymentRequestList) {
		this.newAdditionalPaymentRequestList = newAdditionalPaymentRequestList;
	}
// for attachment feature CI5

	public void setAttachmentFileXML(String attachmentFileXML) {
		this.attachmentFileXML = attachmentFileXML;
	}
	public String getAttachmentFileXML() {
		return attachmentFileXML;
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	public int getFileCount() {
		return fileCount;
	}
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	public String getTotalFileSize() {
		return totalFileSize;
	}
	public void setTotalFileSize(String totalFileSize) {
		this.totalFileSize = totalFileSize;
	}
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	
//	end of addition
}
