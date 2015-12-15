package com.lexmark.service.impl.real.domain;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-partnertechnician-mapping.xml"
 */
// TODO does not implement serializable
public class PartnerTechnicianDo extends BaseEntity {

	private String firstName;
	private String lastName;
	private String employeeOrgId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmployeeOrgId() {
		return employeeOrgId;
	}

	public void setEmployeeOrgId(String employeeOrgId) {
		this.employeeOrgId = employeeOrgId;
	}

}
