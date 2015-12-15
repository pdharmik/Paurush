package com.lexmark.contract;

import java.io.Serializable;

public class ContactInformationContract implements Serializable{
	private static final long serialVersionUID = -8586630865436344456L;
	
	private String mdmId;
	private String mdmLevel;
	private String roleName;
	
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleName() {
		return roleName;
	}
	
}
