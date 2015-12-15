package com.lexmark.service.impl.real.domain;

import java.util.Date;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

public class PartnerDetailOrderLineBase extends BaseEntity {
	
	private Date partOrderedDate;
	private int quantity;
	private String partNumber;
	private String partName;
	private String lineStatus;
	private String serialNumber;
	private Date shipDate;
	private String carrier;
	private String trackingNumber;
	private String shipToAddressId;
	private String shipToAddressLine1;
	private String shipToAddressLine2;
	private String shipToAddressLine3;
	private String shipToCity;
	private String shipToProvince;
	private String shipToPostalCode;
	private String shipToCountry;
	private String shipToState;
	private String shipToCounty;
	private String shipToDistrict;
	private String shipToOfficeNumber;
	private String customerAddressId;
	private String customerAddressLine1;
	private String customerAddressLine2;
	private String customerAddressLine3;
	private String customerAddressCity;
	private String customerAddressProvince;
	private String customerAddressPostalCode;
	private String customerAddressCountry;
	private String customerAddressState;
	private String customerAddressCounty;
	private String customerAddressDistrict;
	private String customerAddressOfficeNumber;
	private String returnAddressId;
	private String returnAddressName;
	private String returnAddressLine1;
	private String returnAddressLine2;
	private String returnAddressLine3;
	private String returnAddressCity;
	private String returnAddressProvince;
	private String returnAddressPostalCode;
	private String returnAddressCountry;
	private String returnAddressState;
	private String lineType;
	private Date partReceivedDate;
	private String modelNumber;
	private String shippingMethod;
	private String county;
	private String district;
	private String officeNumber;
	private String returnCarrierCode;
	private String returnTrackingNumber;
	private String partStatus;
	
	public String getPartStatus() {
		return partStatus;
	}

	public void setPartStatus(String partStatus) {
		this.partStatus = partStatus;
	}

	public String getReturnCarrierCode() {
		return returnCarrierCode;
	}

	public void setReturnCarrierCode(String returnCarrierCode) {
		this.returnCarrierCode = returnCarrierCode;
	}

	public String getReturnTrackingNumber() {
		return returnTrackingNumber;
	}

	public void setReturnTrackingNumber(String returnTrackingNumber) {
		this.returnTrackingNumber = returnTrackingNumber;
	}

	private ArrayList<PartnerActivityShipmentDataDo> shipData;

	public ArrayList<PartnerActivityShipmentDataDo> getShipData() {
		return shipData;
	}

	public void setShipData(ArrayList<PartnerActivityShipmentDataDo> shipData) {
		this.shipData = shipData;
	}
		public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public Date getPartOrderedDate() {
		return partOrderedDate;
	}
	public void setPartOrderedDate(Date partOrderedDate) {
		this.partOrderedDate = partOrderedDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getLineStatus() {
		return lineStatus;
	}
	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Date getShipDate() {
		return shipDate;
	}
	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public String getShipToAddressId() {
		return shipToAddressId;
	}
	public void setShipToAddressId(String shipToAddressId) {
		this.shipToAddressId = shipToAddressId;
	}
	public String getShipToAddressLine1() {
		return shipToAddressLine1;
	}
	public void setShipToAddressLine1(String shipToAddressLine1) {
		this.shipToAddressLine1 = shipToAddressLine1;
	}
	public String getShipToAddressLine2() {
		return shipToAddressLine2;
	}
	public void setShipToAddressLine2(String shipToAddressLine2) {
		this.shipToAddressLine2 = shipToAddressLine2;
	}
	public String getShipToAddressLine3() {
		return shipToAddressLine3;
	}
	public void setShipToAddressLine3(String shipToAddressLine3) {
		this.shipToAddressLine3 = shipToAddressLine3;
	}
	public String getShipToCity() {
		return shipToCity;
	}
	public void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}
	public String getShipToProvince() {
		return shipToProvince;
	}
	public void setShipToProvince(String shipToProvince) {
		this.shipToProvince = shipToProvince;
	}
	public String getShipToPostalCode() {
		return shipToPostalCode;
	}
	public void setShipToPostalCode(String shipToPostalCode) {
		this.shipToPostalCode = shipToPostalCode;
	}
	public String getShipToCountry() {
		return shipToCountry;
	}
	public void setShipToCountry(String shipToCountry) {
		this.shipToCountry = shipToCountry;
	}
	public String getShipToState() {
		return shipToState;
	}
	public void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}
	public String getCustomerAddressId() {
		return customerAddressId;
	}
	public void setCustomerAddressId(String customerAddressId) {
		this.customerAddressId = customerAddressId;
	}
	public String getCustomerAddressLine1() {
		return customerAddressLine1;
	}
	public void setCustomerAddressLine1(String customerAddressLine1) {
		this.customerAddressLine1 = customerAddressLine1;
	}
	public String getCustomerAddressLine2() {
		return customerAddressLine2;
	}
	public void setCustomerAddressLine2(String customerAddressLine2) {
		this.customerAddressLine2 = customerAddressLine2;
	}
	public String getCustomerAddressLine3() {
		return customerAddressLine3;
	}
	public void setCustomerAddressLine3(String customerAddressLine3) {
		this.customerAddressLine3 = customerAddressLine3;
	}
	public String getCustomerAddressCity() {
		return customerAddressCity;
	}
	public void setCustomerAddressCity(String customerAddressCity) {
		this.customerAddressCity = customerAddressCity;
	}
	public String getCustomerAddressProvince() {
		return customerAddressProvince;
	}
	public void setCustomerAddressProvince(String customerAddressProvince) {
		this.customerAddressProvince = customerAddressProvince;
	}
	public String getCustomerAddressPostalCode() {
		return customerAddressPostalCode;
	}
	public void setCustomerAddressPostalCode(String customerAddressPostalCode) {
		this.customerAddressPostalCode = customerAddressPostalCode;
	}
	public String getCustomerAddressCountry() {
		return customerAddressCountry;
	}
	public void setCustomerAddressCountry(String customerAddressCountry) {
		this.customerAddressCountry = customerAddressCountry;
	}
	public String getCustomerAddressState() {
		return customerAddressState;
	}
	public void setCustomerAddressState(String customerAddressState) {
		this.customerAddressState = customerAddressState;
	}
	public String getReturnAddressId() {
		return returnAddressId;
	}
	public void setReturnAddressId(String returnAddressId) {
		this.returnAddressId = returnAddressId;
	}
	public String getReturnAddressName() {
		return returnAddressName;
	}
	public void setReturnAddressName(String returnAddressName) {
		this.returnAddressName = returnAddressName;
	}
	public String getReturnAddressLine1() {
		return returnAddressLine1;
	}
	public void setReturnAddressLine1(String returnAddressLine1) {
		this.returnAddressLine1 = returnAddressLine1;
	}
	public String getReturnAddressLine2() {
		return returnAddressLine2;
	}
	public void setReturnAddressLine2(String returnAddressLine2) {
		this.returnAddressLine2 = returnAddressLine2;
	}
	public String getReturnAddressLine3() {
		return returnAddressLine3;
	}
	public void setReturnAddressLine3(String returnAddressLine3) {
		this.returnAddressLine3 = returnAddressLine3;
	}
	public String getReturnAddressCity() {
		return returnAddressCity;
	}
	public void setReturnAddressCity(String returnAddressCity) {
		this.returnAddressCity = returnAddressCity;
	}
	public String getReturnAddressProvince() {
		return returnAddressProvince;
	}
	public void setReturnAddressProvince(String returnAddressProvince) {
		this.returnAddressProvince = returnAddressProvince;
	}
	public String getReturnAddressPostalCode() {
		return returnAddressPostalCode;
	}
	public void setReturnAddressPostalCode(String returnAddressPostalCode) {
		this.returnAddressPostalCode = returnAddressPostalCode;
	}
	public String getReturnAddressCountry() {
		return returnAddressCountry;
	}
	public void setReturnAddressCountry(String returnAddressCountry) {
		this.returnAddressCountry = returnAddressCountry;
	}
	public String getReturnAddressState() {
		return returnAddressState;
	}
	public void setReturnAddressState(String returnAddressState) {
		this.returnAddressState = returnAddressState;
	}
	public void setLineType(String lineType) {
		this.lineType = lineType;
	}
	public String getLineType() {
		return lineType;
	}
	public void setPartReceivedDate(Date partReceivedDate) {
		this.partReceivedDate = partReceivedDate;
	}
	public Date getPartReceivedDate() {
		return partReceivedDate;
	}
	public String getShippingMethod() {
		return shippingMethod;
	}
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}

	public String getShipToCounty() {
		return shipToCounty;
	}

	public void setShipToCounty(String shipToCounty) {
		this.shipToCounty = shipToCounty;
	}

	public String getShipToDistrict() {
		return shipToDistrict;
	}

	public void setShipToDistrict(String shipToDistrict) {
		this.shipToDistrict = shipToDistrict;
	}

	public String getCustomerAddressCounty() {
		return customerAddressCounty;
	}

	public void setCustomerAddressCounty(String customerAddressCounty) {
		this.customerAddressCounty = customerAddressCounty;
	}

	public String getCustomerAddressDistrict() {
		return customerAddressDistrict;
	}

	public void setCustomerAddressDistrict(String customerAddressDistrict) {
		this.customerAddressDistrict = customerAddressDistrict;
	}

	public String getShipToOfficeNumber() {
		return shipToOfficeNumber;
	}

	public void setShipToOfficeNumber(String shipToOfficeNumber) {
		this.shipToOfficeNumber = shipToOfficeNumber;
	}

	public String getCustomerAddressOfficeNumber() {
		return customerAddressOfficeNumber;
	}

	public void setCustomerAddressOfficeNumber(String customerAddressOfficeNumber) {
		this.customerAddressOfficeNumber = customerAddressOfficeNumber;
	}

}
