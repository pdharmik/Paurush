package com.lexmark.form.source;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lexmark.domain.Order;
import com.lexmark.form.BaseForm;

public class OrderDetailForm extends BaseForm{

	private static final long serialVersionUID = -2540522215207793737L;
	private Order orderDetail ;
	private String orderNumber;
	private String activityNoteListXML;
	private String pendingShipmentPartListXML;
	private String processedPartListXML;
	private String pendingOrderPartListXML;
	private String emailNotificationListXML;
	private String contactId;
	private float timezoneOffset;
	private String fromPage;
	private String serviceProviderOrderRefNo;
	private String quantityList;
	private String lineIdList;
	private String partNumberList;
	private String shipQuantityList;
	private String carrierList;
	private String trackingList;
	private String actualShippedDateList;
	private String backOrderedQtyList;
	private String actualBackOrderedQtyList;
	private String ETAList;
	private String serialNumberList;
	private String statusList;
	private String deliveryDateList;
	private String processedPartList;
	private String processedTrackingList;
	private String processedLineIdList;
	
	public  String getSerialNumberList() {
		return serialNumberList;
	}
	public void setSerialNumberList(String serialNumberList) {
		this.serialNumberList = serialNumberList;
	}
	
	public  String getQuantityList() {
		return quantityList;
	}
	public void setQuantityList(String quantityList) {
		this.quantityList = quantityList;
	}
	
	public  String getPartNumberList() {
		return partNumberList;
	}
	public void setPartNumberList(String partNumberList) {
		this.partNumberList = partNumberList;
	}
	
	public  String getShipQuantityList() {
		return shipQuantityList;
	}
	public void setShipQuantityList(String shipQuantityList) {
		this.shipQuantityList = shipQuantityList;
	}
	
	public  String getCarrierList() {
		return carrierList;
	}
	public void setCarrierList(String carrierList) {
		this.carrierList = carrierList;
	}
	
	public  String getTrackingList() {
		return trackingList;
	}
	public void setTrackingList(String trackingList) {
		this.trackingList = trackingList;
	}
	
	public  String getActualShippedDateList() {
		return actualShippedDateList;
	}
	public void setActualShippedDateList(String actualShippedDateList) {
		this.actualShippedDateList = actualShippedDateList;
	}
	
	public  String getBackOrderedQtyList() {
		return backOrderedQtyList;
	}
	public void setBackOrderedQtyList(String backOrderedQtyList) {
		this.backOrderedQtyList = backOrderedQtyList;
	}
	
	public  String getETAList() {
		return ETAList;
	}
	public void setETAList(String ETAList) {
		this.ETAList = ETAList;
	}
	
	
	public Order getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(Order orderDetail) {
		this.orderDetail = orderDetail;
	}
	public String getActivityNoteListXML() {
		return activityNoteListXML;
	}
	public void setActivityNoteListXML(String activityNoteListXML) {
		this.activityNoteListXML = activityNoteListXML;
	}
	public String getPendingShipmentPartListXML() {
		return pendingShipmentPartListXML;
	}
	public void setPendingShipmentPartListXML(
			String pendingShipmentPartListXML) {
		this.pendingShipmentPartListXML = pendingShipmentPartListXML;
	}
	public String getProcessedPartListXML() {
		return processedPartListXML;
	}
	public void setProcessedPartListXML(String processedPartListXML) {
		this.processedPartListXML = processedPartListXML;
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
	public String getPendingOrderPartListXML() {
		return pendingOrderPartListXML;
	}
	public void setPendingOrderPartListXML(String pendingOrderPartListXML) {
		this.pendingOrderPartListXML = pendingOrderPartListXML;
	}
	public String getEmailNotificationListXML() {
		return emailNotificationListXML;
	}
	public void setEmailNotificationListXML(String emailNotificationListXML) {
		this.emailNotificationListXML = emailNotificationListXML;
	}
	public String getStatusList() {
		return statusList;
	}
	public void setStatusList(String statusList) {
		this.statusList = statusList;
	}
	public String getDeliveryDateList() {
		return deliveryDateList;
	}
	public void setDeliveryDateList(String deliveryDateList) {
		this.deliveryDateList = deliveryDateList;
	}
	public String getProcessedPartList() {
		return processedPartList;
	}
	public void setProcessedPartList(String processedPartList) {
		this.processedPartList = processedPartList;
	}
	public String getProcessedTrackingList() {
		return processedTrackingList;
	}
	public void setProcessedTrackingList(String processedTrackingList) {
		this.processedTrackingList = processedTrackingList;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getLineIdList() {
		return lineIdList;
	}
	public void setLineIdList(String lineIdList) {
		this.lineIdList = lineIdList;
	}
	public String getProcessedLineIdList() {
		return processedLineIdList;
	}
	public void setProcessedLineIdList(String processedLineIdList) {
		this.processedLineIdList = processedLineIdList;
	}
	public String getServiceProviderOrderRefNo() {
		return serviceProviderOrderRefNo;
	}
	public void setServiceProviderOrderRefNo(String serviceProviderOrderRefNo) {
		this.serviceProviderOrderRefNo = serviceProviderOrderRefNo;
	}
	public String getActualBackOrderedQtyList() {
		return actualBackOrderedQtyList;
	}
	public void setActualBackOrderedQtyList(String actualBackOrderedQtyList) {
		this.actualBackOrderedQtyList = actualBackOrderedQtyList;
	}
}
