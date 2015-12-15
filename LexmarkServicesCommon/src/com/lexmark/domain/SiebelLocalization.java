package com.lexmark.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SiebelLocalization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2473011184938960941L;
	private Integer siebelLocalizationId;
	private String optionType;
	private String siebelValue;
	private Integer statusOrder;
	private Boolean showEntitlementFlag;
	private String partnerType;
	private String partnerDirectValue;
	private String partnerIndirectValue;
	private List<SiebelLocalizationLocale> localeList = new ArrayList<SiebelLocalizationLocale>(0);
	public Integer getSiebelLocalizationId() {
		return siebelLocalizationId;
	}
	public void setSiebelLocalizationId(Integer siebelLocalizationId) {
		this.siebelLocalizationId = siebelLocalizationId;
	}
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	public String getSiebelValue() {
		return siebelValue;
	}
	public void setSiebelValue(String siebelValue) {
		this.siebelValue = siebelValue;
	}
	public void setStatusOrder(Integer statusOrder) {
		this.statusOrder = statusOrder;
	}
	public Integer getStatusOrder() {
		return statusOrder;
	}
	public Boolean getShowEntitlementFlag() {
		return showEntitlementFlag;
	}
	public void setShowEntitlementFlag(Boolean showEntitlementFlag) {
		this.showEntitlementFlag = showEntitlementFlag;
	}
	public String getPartnerType() {
		return partnerType;
	}
	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}
	public List<SiebelLocalizationLocale> getLocaleList() {
		return localeList;
	}
	public void setLocaleList(List<SiebelLocalizationLocale> localeList) {
		this.localeList = localeList;
	}
	
	/**
	 * @param partnerDirectValue the partnerDirectValue to set
	 */
	public void setPartnerDirectValue(String partnerDirectValue) {
		this.partnerDirectValue = partnerDirectValue;
	}
	/**
	 * @return the partnerDirectValue
	 */
	public String getPartnerDirectValue() {
		return partnerDirectValue;
	}

	/**
	 * @param partnerIndirectValue the partnerIndirectValue to set
	 */
	public void setPartnerIndirectValue(String partnerIndirectValue) {
		this.partnerIndirectValue = partnerIndirectValue;
	}
	/**
	 * @return the partnerIndirectValue
	 */
	public String getPartnerIndirectValue() {
		return partnerIndirectValue;
	}

	public enum SiebelLocalizationOptionEnum {
		SERVICE_STATUS("Service Status", "SERVICE_STATUS"),
		ENTITLEMENT_SERVICE_DETAILS("Entitlement Service Details", "ENTITLEMENT_SERVICE_DETAILS"),
		SERVICE_ACTIVITY_STATUS_DESCRIPTION("Service Activity Status Description", "SERVICE_ACTIVITY_STATUS_DESCRIPTION"),
		PARTNER_SERVICE_REQUEST_TYPE("Partner Service Request Type","SR_REQUEST_TYPE"),
		PARTNER_SERVICE_REQUEST_STATUS("Partner Service Request Status","EVENT_STATUS"),
		PARTNER_SERVICE_REQUEST_SUBSTATUS("Partner Service Request Substatus","FS_ACTIVITY_CLASS"),
		PARTNER_SERVICE_ORDER_STATUS("Partner Service Order Status","SERVICE_ORDER_STATUS"),
		PARTNER_PROBLEM_CODE("Partner Problem Code","ACTUAL_FAIL_CD"),
		PARTNER_RESOLUTION_CODE("Partner Resolution Code","LXK_RESOLUTION_CODE"),
		PARTNER_ADDITIONAL_PAYMENT_TYPE("Partner Additional Payment Type","EXP_ITEM_TYPE"),
		PARTNER_CARRIER("Partner Carrier","FS_CARRIER"),
		PARTNER_DEBRIEF_ERROR_CODE_1("Partner Debrief Error Code 1","LXK_SERVICE_ERR_CODE_1"),
		PARTNER_DEBRIEF_ERROR_CODE_2("Partner Debrief Error Code 2","LXK_SERVICE_ERR_CODE_2"),
		PARTNER_DEVICE_CONDITION("Partner Device Condition","LXK_DEVICE_CONDITION"),
		PARTNER_PART_STATUS("Partner Part Status","LXK_PART_DISPOSITION"),
		PARTNER_TRAVEL_UNIT_OF_MEASURE("Partner Travel Unit of Measure","DISTANCE_UNIT"),
		PARTNER_PAYMENT_STATUS("Partner Payment Status", "PAYMENT_STATUS"),
		// Added RMA LINE TEM STATUS
		PARTNER_ORDER_RMA_LINE_ITEM_STATUS("Partner Order RMA Line Item Status","LXK_C_FS_ORDER_LINE_STATUS"),
		PARTNER_ORDER_LINE_ITEM_STATUS("Partner Order Line Item Status","LXK_C_FS_ORDER_LINE_STATUS"),
		PARTNER_DEBRIEF_PART_SOURCE("Partner Debrief Part Source","LXK_UP_SOURCE"),
		CM_AREA_NAME("CM Area Name","CM_AREA_NAME"),
		CM_SUB_AREA_NAME("CM Sub Area Name","CM_SUB_AREA_NAME"),
		SUBAREA_CHANGE_CONTRACT_DATA("Change Contract Data","SUBAREA_CHANGE_CONTRACT_DATA"),
		SUBAREA_UPDATE_REQUEST("Update Request","SUBAREA_UPDATE_REQUEST"),
		SUBAREA_CANCEL_REQUEST("Cancel Request","SUBAREA_CANCEL_REQUEST"),
		SUBAREA_RETURNS("Returns","SUBAREA_RETURNS"),
		SUBAREA_BILLING("Billing","SUBAREA_BILLING"),
		SUBAREA_INQUIRY("Inquiry","SUBAREA_INQUIRY"),
		SUBAREA_INITIATE_PO("Initiate PO","SUBAREA_INITIATE_PO"),
		SUBAREA_REPORTING("Reporting","SUBAREA_REPORTING"),
		SUBAREA_TRAINING("Training","SUBAREA_TRAINING"),
		REQUEST_TYPE("Request Type","SR_TYPE"),
		REQUEST_AREA("Request Area","SR_AREA"),
		REQUEST_SUBAREA("Request Sub Area","SR_SUBAREA"),
		REQUEST_STATUS("Request Status","SR_STATUS"),
		PAGE_COUNT_DATA("Order Page Count","PAGE_COUNT_DATA"),
		OM_RETURNS_SUBAREA("OM Returns Sub Area","OM_RETURNS_SUBAREA"),
		PAYMENT_TYPE_PAY_NOW("Pay Now","PAYMENT_TYPE_PAY_NOW"),
		PAYMENT_TYPE_PAY_LATER("Pay Later","PAYMENT_TYPE_PAY_LATER"),
		PAYMENT_TYPE_UBB("Lease","PAYMENT_TYPE_UBB"),
		PAYMENT_TYPE("Payment Type","PAYMENT_TYPE"),
		CONSUMABLE_PAYMENT_TYPE ("Consumable Payment Type","CONSUMABLE_PAYMENT_TYPE"),
		HARDWARE_PAYMENT_TYPE ("Hardware Payment Type","HARDWARE_PAYMENT_TYPE"),
		// Added For Printer Error Code for CI 13.10.17 by Pranjit
		PRINTER_ERROR("Printer Error","PRINTER_ERROR"),
		//Added for Asset Acceptance
		CUSTOMER_ACCEPTANCE("Customer Acceptance","CUSTOMER_ACCEPTANCE"),
		PAGE_COUNTS_FOR_ASSET("Page Counts","PAGE_COUNTS_FOR_ASSET"),
		CONTACT_TYPE_FOR_ASSET("Contact Type","CONTACT_TYPE_FOR_ASSET"),
		ACTIVITY_SUB_STATUS("Activity Sub Status","ACTIVITY_SUB_STATUS"),
		PARTNER_REQUEST_LIST("Partner Request List","SR_REQUEST_LIST"),
		CUSTOMER_INVOICE_STATUS("Customer Invoice Status","CUSTOMER_INVOICE_STATUS"),
		UPLOAD_HISTORY_TYPE("Upload History Type","UPLOAD_HISTORY_TYPE"),//Added for type of Upload History.
		FAX_CONNECTED_TYPE("Fax Connected","FAX_CONNECTED"),//Added for Hardware debrief Fax connected or not.
		PAGE_COUNTS_STATUS("Page Counts Status","PAGE_COUNTS_STATUS"),//Added for CI7 BRD-14-06-04
		CALL_BACK_FCC("Call Back FCC Code","CALL_BACK_FCC_CODE"), //added for july release BRD #14-07-01
		SUBAREA_UPLOAD("Upload","SUBAREA_UPLOAD"),//added for june release
		ALLOW_CHILD_SR("Allow Child SR","ALLOW_CHILD_SR"),//Added for Partner Portal Request TAb Allow Child SR July Release 2014 
		CATEGORY_PARTNER_TYPE_DOCUMENT_LIB("Partner Type Document Library","PARTNER_TYPE_DOCUMENT_LIBRARY"),
		PAGE_COUNTS_MONO("Page Counts Mono","PAGE_COUNTS_MONO"),
		//Added for CR#12022
		SPECIAL_USAGE("Special Usage","SPECIAL_USAGE"),
		// Added for LBS 
		//LBS_DEVICE_MODEL("Device Model","DEVICE_MODEL"),
		//LBS_DEVICE_MODEL_TYPE("Device Model Type","DEVICE_MODEL_TYPE"),
		//LBS_PRODUCT("PRODUCT","PRODUCT"),
		LBS_PRODUCT_TYPE("Product Type","PRODUCT_TYPE"),
		LBS_PRODUCT_SERIES("Product Series","PRODUCT_SERIES"),
		//LBS_PRODUCT_BRAND("Product Brand","PRODUCT_BRAND"),
		LBS_PRODUCT_OWNERSHIP("Product Ownership","PRODUCT_OWNERSHIP"),
		LBS_METER_READ("Meter Read","METER_READ"),
		//LBS_PRODUCT_COST_CENTER("Product Cost Center","PRODUCT_COST_CENTER"),
		//LBS_PRODUCT_DEPARTMENT("Product Department","PRODUCT_DEPARTMENT"),
		//LBS_ASSET_TAG("Asset Tag","PRODUCT_ASSET_TAG"),
		
		LBS_SR_TYPE("SRType","LBS_SR_TYPE"),
		//LBS_SR_STATUS("SRStatus","SR_STATUS"),
		//LBS_SR_AREA("SRArea","SR_AREA"),
		LBS_SUB_AREA("Sub Area","LBS_SUB_AREA"),
		LBS_ASSET_LIFECYCLE("LBS ASSET LIFECYCLE","LBS_ASSET_LIFECYCLE"),
		LBS_DEVICE_PHASE("LBS DEVICE PHASE","LBS_DEVICE_PHASE"),
		LBS_HARDWARE_STATUS("LBS HARDWARE STATUS","LBS_HARDWARE_STATUS"),
		// LBS Changes Ends
		
		//OPS Portal Fleet Management Request Filter Changes
		
		OPS_SR_STATUS("OPS SR STATUS","OPS_SR_STATUS"),
		OPS_SR_SUBSTATUS("OPS SR SUBSTATUS","OPS_SR_SUBSTATUS"),
		OPS_SR_SOURCE("OPS SR SOURCE","OPS_SR_SOURCE"),
		// added for 15.4 *
		PARTNER_PROBLEM_CODE_1("Partner Debrief Problem Code Code 1","PARTNER_PROBLEM_CODE_1"),
		PARTNER_PROBLEM_CODE_2("Partner Debrief Problem Code Code 2","PARTNER_PROBLEM_CODE_2"),
		PARTNER_PROBLEM_CODE_3("Partner Debrief Problem Code Code 3","PARTNER_PROBLEM_CODE_3"),
		PARTNER_DEBRIEF_RESOLUTION_CODE("Partner Debrief Resolution Code","PARTNER_DEBRIEF_RESOLUTION_CODE"),
		PARTNER_DEBRIEF_PART_STATUS("Partner Debrief Part Status","PARTNER_DEBRIEF_PART_STATUS"),
		PARTNER_DEBRIEF_PART_STATUS_DETAIL("Partner Debrief Part Status Detail","PARTNER_DEBRIEF_PART_STATUS_DETAIL"),
		PARTNER_CLAIM_TYPE("Partner Claim Type","PARTNER_CLAIM_TYPE");
		
		
		private String displayName;
		private String value;
		SiebelLocalizationOptionEnum(String displayName, String value) {
			this.displayName = displayName;
			this.value = value;
		}
		public String getDisplayName() {
			return displayName;
		}
		public String getValue() {
			return value;
		}
		
	}
}
