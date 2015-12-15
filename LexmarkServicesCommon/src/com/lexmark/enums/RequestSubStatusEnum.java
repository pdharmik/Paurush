package com.lexmark.enums;

/**
 * Enum for Request Substatus
 */
public enum RequestSubStatusEnum {
	
	ACCEPTED("Accepted"),
	ACTION_INCOMPLETE("Action Incomplete"),
	ACTIVITY_ESCALATED("Activity Escalated"),
	ADDITIONAL_PARTS_APPROVED("Additional Parts Approved"),
	ADDRESS_PROVIDED("Address Provided"),
	AGENT_CANCELLED_CALL_BACK("Agent Cancelled Call Back"),
	AGENT_SCHEDULED_CALLBACK("Agent Scheduled Callback"),
	APPT_SCHEDULED("Appt Scheduled"),
	ASSET_OUT_OF_WARRANTY("Asset Out of Warranty"),
	ASSET_UNAVAILABLE("Asset Unavailable"),
	BACKORDER_PART("Backorder Part"),
	BACKORDER_PRINTER("Backorder Printer"),
	CALLED_SITE_CUST_NOT_AVAILABLE("Called Site-Cust Not Available"),
	CANCELLATION_ACKNOWLEDGED("Cancellation Acknowledged"),
	CANCELLED_WITH_PARTS("Cancelled With Parts"),
	CANCELLED_WITHOUT_PARTS("Cancelled Without Parts"),
	CARRY_BACK_TO_SP_WORKSHOP("Carry back to SP Workshop"),
	CLAIM_ACCEPTED("Claim Accepted"),
	CLAIM_DENIED("Claim Denied"),
	CONTACTED_CUSTOMER("Contacted Customer"),
	COULD_NOT_REACH_CUST("Could Not Reach Cust"),
	CUSTOMER_CANCELLED("Customer Cancelled"),
	CUSTOMER_DAMAGE("Customer Damage"),
	CUSTOMER_REQUESTED_APPT("Customer Requested Appt"),
	DEAD_ON_ARRIVAL("Dead on Arrival"),
	DEFECTIVE_PART("Defective Part"),
	DEFERRED_APPT("Deferred Appt"),
	DELAYED_DUE_TO_SHIPMENT("Delayed Due to Shipment"),
	DELAYED_DUE_TO_WEATHER("Delayed Due to Weather"),
	ETA_PROVIDED("ETA Provided"),
	FORCE_MAJEURE("Force Majeure"),
	ITEM_SHIPPED("Item Shipped"),
	LEAD_FAILED_QUALIFICATION("Lead Failed Qualification"),
	LEAD_QUALIFIED("Lead Qualified"),
	LOANER_MACHINE_INSTALLED("Loaner Machine Installed"),
	LOS_BY_CUSTOMER("Lost by Customer"),
	MAX_OF_PARTS_EXCEEDED("Max # of Parts Exceeded"),
	NEED_ADDITIONAL_INFO("Need Additional Info"),
	NEED_TO_VERIFY_CUSTOMER_INFO("Need to Verify Customer Info"),
	NEED_TO_VERIFY_PRODUCT("Need to Verify Product"),
	NEED_TO_VERIFY_SERIAL("Need to Verify Serial #"),
	NOT_ACCEPTED("Not Accepted"),
	OBSOLETE("Obsolete"),
	PARTS_UNAVAILABLE("Parts Unavailable"),
	PENDING_ASR("Pending ASR"),
	PENDING_CUSTOMER_CALLBACK("Pending Customer Callback"),
	PENDING_PART_RECOMMENDATION("Pending Part Recommendation"),
	PENDING_PROOF_OF_PURCHASE("Pending Proof of Purchase"),
	PENDING_SP_ACKNOWLEDGEMENT("Pending SP Acknowledgement"),
	READY_FOR_QC("Ready for QC"),
	RECEIVED_AT_VENDOR("Received at Vendor"),
	REFUSED_BY_CUSTOMER("Refused by Customer"),
	REQUEST_FOR_SUPPLY("Request for Supply"),
	SP_PARTS_REQUESTED("SP Parts Requested"),
	SCHEDULED_CALL_BACK_ABANDONED("Scheduled call back abandoned"),
	SCHEDULED_CALLBACK_CANCELLED("Scheduled callback cancelled"),
	SCRAPPED("Scrapped"),
	SEND_TECH_WITHOUT_PARTS("Send Tech without Parts"),
	SENT("Sent"),
	SERVICE_ACTION_COMPLETE("Service Action Complete"),
	SURVEY_1ST_ATTEMPT_NOT_SUCCESS("Survey-1st Attempt Not Success"),
	SURVEY_1ST_ATTEMPT_SUCCESS("Survey-1st Attempt Success"),
	SURVEY_2ND_ATTEMPT_NOT_SUCCESS("Survey-2nd Attempt Not Success"),
	SURVEY_2ND_ATTEMPT_SUCCESS("Survey-2nd Attempt Success"),
	SURVEY_READY("Survey-Ready"),
	TECH_UNAVAILABLE("Tech Unavailable"),
	TECH_EN_ROUTE("Tech en route"),
	TECH_ON_SITE("Tech on site"),
	TELEPHONE_FIX("Telephone Fix"),
	UPDATED_SP("Updated SP"),
	WAITING_FOR_ADDRESS("Waiting for Address"),
	WAITING_FOR_PARTS("Waiting for Parts"),
	WRONG_PARTS_RECOMMENDED("Wrong Parts Recommended"),
	CLAIM_SUBMITTED("Claim Submitted"),
	CLAIM_CANCELLED("Claim Cancelled"),
	PENDING_LEXMARK_REVIEW("Pending Lexmark Review"),
	PROCESSING_REQUEST("Processing Request");

	private String value;
	
	RequestSubStatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
