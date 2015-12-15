package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import com.amind.common.domain.BaseEntity;
/**
 * The mapping file: do-supplyaccountcontactdetail-mapping.xml
 * 
**/

public class SupplyAccountContactDetailDo extends BaseEntity implements Serializable {
	
	private String serialNumber;
    private String lastName;
    private String workPhone;
    private String homePhone;
    private String emailAddress;
    private String serviceRequestNumber;
    
    public String getServiceRequestNumber() {
		return serviceRequestNumber;
	}
    public void setServiceRequestNumber(String serviceRequestNumber) {
		this.serviceRequestNumber = serviceRequestNumber;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
}
