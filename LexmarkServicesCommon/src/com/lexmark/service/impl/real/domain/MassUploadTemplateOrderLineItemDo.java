package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * @author i.prikhodko
 *
 * do-mapping: do-massuploadtemplateorderlineitemdo-mapping.xml
 */

public class MassUploadTemplateOrderLineItemDo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String BO = "LXK C Order (EAI)";
	public static final String BC = "LXK MPS Order Line Item Import";

	private String partNumber;
	private String lineNumber;
	private String partDescription;
	private String quantitiyRequested;
	private String remainingQuantity;
	private String backOrderQuantity;
	private String orderNumber;
	private String serialNumber;
	private String orderStatus;
	private String partnerOrderReferenceNumber;
	private	ArrayList<MassUploadTemplateShipmentDo> shipments;
	
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

	public String getQuantitiyRequested() {
		return quantitiyRequested;
	}

	public void setQuantitiyRequested(String quantitiyRequested) {
		this.quantitiyRequested = quantitiyRequested;
	}

	public String getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(String remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public String getBackOrderQuantity() {
		return backOrderQuantity;
	}

	public void setBackOrderQuantity(String backOrderQuantity) {
		this.backOrderQuantity = backOrderQuantity;
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

	public ArrayList<MassUploadTemplateShipmentDo> getShipments() {
		return shipments;
	}

	public void setShipments(ArrayList<MassUploadTemplateShipmentDo> shipments) {
		this.shipments = shipments;
	}
	
}
