package com.lexmark.service.impl.real.domain;


import java.io.Serializable;
import java.util.ArrayList;

public class MeterReadBase extends AssetBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3335918331930141941L;
	private String modelNumber;
	private String physicalLocation1;
	private String physicalLocation2;
	private String physicalLocation3;
	private String meterType;
	private String contactId;
	private String contactFirstName;
	private String contactLastName;
	private String contactEmail;
	private String contactPhone;
	private ArrayList<AssetMeasurementCharacteristicsBase> measurementCharacteristics;

	public String getContactFirstName() {
		return contactFirstName;
	}
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}
	public String getContactLastName() {
		return contactLastName;
	}
	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getPhysicalLocation1() {
		return physicalLocation1;
	}
	public void setPhysicalLocation1(String physicalLocation1) {
		this.physicalLocation1 = physicalLocation1;
	}
	public String getPhysicalLocation2() {
		return physicalLocation2;
	}
	public void setPhysicalLocation2(String physicalLocation2) {
		this.physicalLocation2 = physicalLocation2;
	}
	public String getPhysicalLocation3() {
		return physicalLocation3;
	}
	public void setPhysicalLocation3(String physicalLocation3) {
		this.physicalLocation3 = physicalLocation3;
	}
	public String getMeterType() {
		return meterType;
	}
	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public ArrayList<AssetMeasurementCharacteristicsBase> getMeasurementCharacteristics() {
		return measurementCharacteristics;
	}
	public void setMeasurementCharacteristics(
			ArrayList<AssetMeasurementCharacteristicsBase> measurementCharacteristics) {
		this.measurementCharacteristics = measurementCharacteristics;
	}
	
	
}
