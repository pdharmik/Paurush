package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;

/**
 * The mapping file:  do-partnerclaimdetail-mapping.xml
 *
 */
public class PartnerClaimDetailDo extends PartnerActivityDo  {
	private static final long serialVersionUID = 1607447197329948071L;
	
	private String installAddressId;
	private String newCustomerAddressCombined;
	private String installAddressLine1;
	private String installAddressLine2;
	private String installAddressLine3;
	private String installCity;
	private String installState;
	private String installProvince;
	private String installPostalCode;
	private String installCountry;
	private String newTechFirstName;
	private String newTechLastName;
	private String repairDesc;
	private String techLoginName;
	private String partnerOrganization;
	private String partnerSite;
	private String partnerRefNumber;
	private ArrayList<PartnerClaimDetailExpenseDo> expenses;
	private ArrayList<PartnerClaimDetailServiceRequestAttachmentDo> serviceRequestAttachments;
	private ArrayList<PartnerClaimDetailAttachmentDo> attachments;//Attachment
	
	private ArrayList<PartnerClaimDetailPageCountDo> pageCountAll;
	
	private String customerRequestedDate;
	
	public String getNewTechFirstName() {
		return newTechFirstName;
	}
	public void setNewTechFirstName(String newTechFirstName) {
		this.newTechFirstName = newTechFirstName;
	}
	public String getNewTechLastName() {
		return newTechLastName;
	}
	public void setNewTechLastName(String newTechLastName) {
		this.newTechLastName = newTechLastName;
	}
	public String getRepairDesc() {
		return repairDesc;
	}
	public void setRepairDesc(String repairDesc) {
		this.repairDesc = repairDesc;
	}
	public String getTechLoginName() {
		return techLoginName;
	}
	public void setTechLoginName(String techLoginName) {
		this.techLoginName = techLoginName;
	}

	private ArrayList<PartnerClaimDetailNoteDo> notes;
	private ArrayList<PartnerClaimDetailOrderLineDo> orderLines;
	private ArrayList<PartnerClaimDetailPartDo> parts;
	private ArrayList<PartnerClaimDetailFlagDo> flags;
	private String displayWarning;
	
	public String getDisplayWarning() {
		return displayWarning;
	}
	public void setDisplayWarning(String displayWarning) {
		this.displayWarning = displayWarning;
	}
		public ArrayList<PartnerClaimDetailExpenseDo> getExpenses() {
		return expenses;
	}
	public void setExpenses(ArrayList<PartnerClaimDetailExpenseDo> expenses) {
		this.expenses = expenses;
	}
	public ArrayList<PartnerClaimDetailNoteDo> getNotes() {
		return notes;
	}
	public void setNotes(ArrayList<PartnerClaimDetailNoteDo> notes) {
		this.notes = notes;
	}
	public ArrayList<PartnerClaimDetailOrderLineDo> getOrderLines() {
		return orderLines;
	}
	public void setOrderLines(ArrayList<PartnerClaimDetailOrderLineDo> orderLines) {
		this.orderLines = orderLines;
	}
	public ArrayList<PartnerClaimDetailPartDo> getParts() {
		return parts;
	}
	public void setParts(ArrayList<PartnerClaimDetailPartDo> parts) {
		this.parts = parts;
	}

	public void setFlags(ArrayList<PartnerClaimDetailFlagDo> flags) {
		this.flags = flags;
	}

	public ArrayList<PartnerClaimDetailFlagDo> getFlags() {
		return flags;
	}
	public String getInstallAddressId() {
		return installAddressId;
	}
	public void setInstallAddressId(String installAddressId) {
		this.installAddressId = installAddressId;
	}
	public String getNewCustomerAddressCombined() {
		return newCustomerAddressCombined;
	}
	public void setNewCustomerAddressCombined(String newCustomerAddressCombined) {
		this.newCustomerAddressCombined = newCustomerAddressCombined;
	}
	public String getInstallAddressLine1() {
		return installAddressLine1;
	}
	public void setInstallAddressLine1(String installAddressLine1) {
		this.installAddressLine1 = installAddressLine1;
	}
	public String getInstallAddressLine2() {
		return installAddressLine2;
	}
	public void setInstallAddressLine2(String installAddressLine2) {
		this.installAddressLine2 = installAddressLine2;
	}
	public String getInstallAddressLine3() {
		return installAddressLine3;
	}
	public void setInstallAddressLine3(String installAddressLine3) {
		this.installAddressLine3 = installAddressLine3;
	}
	public String getInstallCity() {
		return installCity;
	}
	public void setInstallCity(String installCity) {
		this.installCity = installCity;
	}
	public String getInstallState() {
		return installState;
	}
	public void setInstallState(String installState) {
		this.installState = installState;
	}
	public String getInstallProvince() {
		return installProvince;
	}
	public void setInstallProvince(String installProvince) {
		this.installProvince = installProvince;
	}
	public String getInstallPostalCode() {
		return installPostalCode;
	}
	public void setInstallPostalCode(String installPostalCode) {
		this.installPostalCode = installPostalCode;
	}
	public String getInstallCountry() {
		return installCountry;
	}
	public void setInstallCountry(String installCountry) {
		this.installCountry = installCountry;
	}
	public void setPartnerOrganization(String partnerOrganization) {
		this.partnerOrganization = partnerOrganization;
	}
	public String getPartnerOrganization() {
		return partnerOrganization;
	}
	public String getPartnerSite() {
		return partnerSite;
	}
	public void setPartnerSite(String partnerSite) {
		this.partnerSite = partnerSite;
	}
	public String getPartnerRefNumber() {
		return partnerRefNumber;
	}
	public void setPartnerRefNumber(String partnerRefNumber) {
		this.partnerRefNumber = partnerRefNumber;
	}
	
	public void setAttachments(ArrayList<PartnerClaimDetailAttachmentDo> attachments) {
		this.attachments = attachments;
	}
	public ArrayList<PartnerClaimDetailAttachmentDo> getAttachments() {
		return attachments;
	}
	public ArrayList<PartnerClaimDetailPageCountDo> getPageCountAll() {
		return pageCountAll;
	}
	public void setPageCountAll(
			ArrayList<PartnerClaimDetailPageCountDo> pageCountAll) {
		this.pageCountAll = pageCountAll;
	}
	
	public String getCustomerRequestedDate() {
		return customerRequestedDate;
	}
	public void setCustomerRequestedDate(String customerRequestedDate) {
		this.customerRequestedDate = customerRequestedDate;
	}
	public ArrayList<PartnerClaimDetailServiceRequestAttachmentDo> getServiceRequestAttachments() {
		return serviceRequestAttachments;
	}
	public void setServiceRequestAttachments(
			ArrayList<PartnerClaimDetailServiceRequestAttachmentDo> serviceRequestAttachments) {
		this.serviceRequestAttachments = serviceRequestAttachments;
	}	

}
