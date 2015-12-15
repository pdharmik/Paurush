package com.lexmark.domain;

import java.io.Serializable;
import java.util.List;

public class MassUploadTemplateOrderLineItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String partNumber;
	private String lineNumber;
	private String partDescription;
	private String orderNumber;
	private String serialNumber;
	private String orderStatus;
	private String partnerOrderReferenceNumber;
	private int quantitiyRequested;
	private int remainingQuantity;
	private int backOrderQuantity;
	private List<MassUploadTemplateShipment> shipments;
	

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getPartDescription() {
		return partDescription;
	}

	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPartnerOrderReferenceNumber() {
		return partnerOrderReferenceNumber;
	}

	public void setPartnerOrderReferenceNumber(String partnerOrderReferenceNumber) {
		this.partnerOrderReferenceNumber = partnerOrderReferenceNumber;
	}

	public int getQuantitiyRequested() {
		return quantitiyRequested;
	}

	public void setQuantitiyRequested(int quantitiyRequested) {
		this.quantitiyRequested = quantitiyRequested;
	}

	public int getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public int getBackOrderQuantity() {
		return backOrderQuantity;
	}

	public void setBackOrderQuantity(int backOrderQuantity) {
		this.backOrderQuantity = backOrderQuantity;
	}

	public List<MassUploadTemplateShipment> getShipments() {
		return shipments;
	}

	public void setShipments(List<MassUploadTemplateShipment> shipments) {
		this.shipments = shipments;
	}

}
