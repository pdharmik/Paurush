package com.lexmark.domain;

import java.util.ArrayList;
import java.util.Date;

public class PartLineItem extends Part{
	private static final long serialVersionUID = -7833536575815136266L;
	private String partLineItemId;
	private Date partOrderedDate;
	private int quantity;
	private ListOfValues lineStatus;
	private Object shipDate;
	private ListOfValues carrier;
	private String serialNumber;
	private String trackingNumber;
	private Date partReceivedDate;
	private ListOfValues errorCode1;
	private ListOfValues errorCode2;
	private String returnAirbillNumber;
	private boolean partUsedFlag;
	private ListOfValues partDisposition; 
	private Date requestedDate;
	private GenericAddress shipToAdress;
	private int recommendedQuantity;
	private boolean partLineItemUpdateFlag;
	private String modelNumber;
	private ListOfValues partSource;
	private String expedite;
	// RMA
	private ListOfValues lineRMAStatus;
	
	//Addd By MPS Offshore Team
	private int backOrdered;
	private int shippedQuantity;
	private Date ETA;
	private Date DeliveredDate;
	private String shipmentCarrier;
	private String shipmentTrackingNumber;
	private String shipmentTrackingStatus;
	private String shipmentDate;
	private ListOfValues partStatus;
	private String returnTrackingNumber;
	
	
	//added for BRD 06, aMind Team
	private Boolean orderFlag;
	private String recommendedType;
	private String orderLineStatus;
	private String source;
	private String shippingMethod;
	private String productDescription;
	private String materialLineType;
	private boolean isTypePrinter;
	
	public String getReturnTrackingNumber() {
		return returnTrackingNumber;
	}
	public void setReturnTrackingNumber(String returnTrackingNumber) {
		this.returnTrackingNumber = returnTrackingNumber;
	}
	public ListOfValues getPartStatus() {
		return partStatus;
	}
	public void setPartStatus(ListOfValues partStatus) {
		this.partStatus = partStatus;
	}
	public String getShipmentCarrier() {
		return shipmentCarrier;
	}
	public void setShipmentCarrier(String shipmentCarrier) {
		this.shipmentCarrier = shipmentCarrier;
	}
	public String getShipmentTrackingNumber() {
		return shipmentTrackingNumber;
	}
	public void setShipmentTrackingNumber(String shipmentTrackingNumber) {
		this.shipmentTrackingNumber = shipmentTrackingNumber;
	}
	public String getShipmentTrackingStatus() {
		return shipmentTrackingStatus;
	}
	public void setShipmentTrackingStatus(String shipmentTrackingStatus) {
		this.shipmentTrackingStatus = shipmentTrackingStatus;
	}
	public String getShipmentDate() {
		return shipmentDate;
	}
	public void setShipmentDate(String shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
		
	private ArrayList<String> SerialNo;
	
	
	public ListOfValues getLineRMAStatus() {
		return lineRMAStatus;
	}
	public void setLineRMAStatus(ListOfValues lineRMAStatus) {
		this.lineRMAStatus = lineRMAStatus;
	}
	public ListOfValues getPartSource() {
		return partSource;
	}
	public void setPartSource(ListOfValues partSource) {
		this.partSource = partSource;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public boolean isPartLineItemUpdateFlag() {
		return partLineItemUpdateFlag;
	}
	public void setPartLineItemUpdateFlag(boolean partLineItemUpdateFlag) {
		this.partLineItemUpdateFlag = partLineItemUpdateFlag;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public ListOfValues getLineStatus() {
		return lineStatus;
	}
	public void setLineStatus(ListOfValues lineStatus) {
		this.lineStatus = lineStatus;
	}
	public Date getPartOrderedDate() {
		return partOrderedDate;
	}
	public void setPartOrderedDate(Date partOrderedDate) {
		this.partOrderedDate = partOrderedDate;
	}
	public Object getShipDate() {
		return shipDate;
	}
	public void setShipDate(Object shipDate) {
		this.shipDate = shipDate;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public Date getPartReceivedDate() {
		return partReceivedDate;
	}
	public void setPartReceivedDate(Date partReceivedDate) {
		this.partReceivedDate = partReceivedDate;
	}
	public ListOfValues getCarrier() {
		return carrier;
	}
	public void setCarrier(ListOfValues carrier) {
		this.carrier = carrier;
	}
	public ListOfValues getErrorCode1() {
		return errorCode1;
	}
	public void setErrorCode1(ListOfValues errorCode1) {
		this.errorCode1 = errorCode1;
	}
	public ListOfValues getErrorCode2() {
		return errorCode2;
	}
	public void setErrorCode2(ListOfValues errorCode2) {
		this.errorCode2 = errorCode2;
	}
	public String getReturnAirbillNumber() {
		return returnAirbillNumber;
	}
	public void setReturnAirbillNumber(String returnAirbillNumber) {
		this.returnAirbillNumber = returnAirbillNumber;
	}
	public boolean isPartUsedFlag() {
		return partUsedFlag;
	}
	public void setPartUsedFlag(boolean partUsedFlag) {
		this.partUsedFlag = partUsedFlag;
	}
	public ListOfValues getPartDisposition() {
		return partDisposition;
	}
	public void setPartDisposition(ListOfValues partDisposition) {
		this.partDisposition = partDisposition;
	}
	public Date getRequestedDate() {
		return requestedDate;
	}
	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}
	public GenericAddress getShipToAdress() {
		return shipToAdress;
	}
	public void setShipToAdress(GenericAddress shipToAdress) {
		this.shipToAdress = shipToAdress;
	}
	public String getPartLineItemId() {
		return partLineItemId;
	}
	public void setPartLineItemId(String partLineItemId) {
		this.partLineItemId = partLineItemId;
	}
	public int getRecommendedQuantity() {
		return recommendedQuantity;
	}
	public void setRecommendedQuantity(int recommendedQuantity) {
		this.recommendedQuantity = recommendedQuantity;
	}
	
	public Date getETA() {
		return ETA;
	}
	public void setETA(Date eTA) {
		ETA = eTA;
	}
	public int getBackOrdered() {
		return backOrdered;
	}
	public void setBackOrdered(int backOrdered) {
		this.backOrdered = backOrdered;
	}
	public int getShippedQuantity() {
		return shippedQuantity;
	}
	public void setShippedQuantity(int shippedQuantity) {
		this.shippedQuantity = shippedQuantity;
	}
	public Date getDeliveredDate() {
		return DeliveredDate;
	}
	public void setDeliveredDate(Date deliveredDate) {
		DeliveredDate = deliveredDate;
	}
	public ArrayList<String> getSerialNo() {
		return SerialNo;
	}
	public void setSerialNo(ArrayList<String> serialNo) {
		SerialNo = serialNo;
	}
	public void setExpedite(String expedite) {
		this.expedite = expedite;
	}
	public String getExpedite() {
		return expedite;
	}
	public Boolean getOrderFlag() {
		return orderFlag;
	}
	public void setOrderFlag(Boolean orderFlag) {
		this.orderFlag = orderFlag;
	}
	public String getRecommendedType() {
		return recommendedType;
	}
	public void setRecommendedType(String recommendedType) {
		this.recommendedType = recommendedType;
	}
	public String getOrderLineStatus() {
		return orderLineStatus;
	}
	public void setOrderLineStatus(String orderLineStatus) {
		this.orderLineStatus = orderLineStatus;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getShippingMethod() {
		return shippingMethod;
	}
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getMaterialLineType() {
		return materialLineType;
	}
	public void setMaterialLineType(String materialLineType) {
		this.materialLineType = materialLineType;
	}
	public boolean isTypePrinter() {
		return isTypePrinter;
	}
	public void setTypePrinter(boolean isTypePrinter) {
		this.isTypePrinter = isTypePrinter;
	}
}
