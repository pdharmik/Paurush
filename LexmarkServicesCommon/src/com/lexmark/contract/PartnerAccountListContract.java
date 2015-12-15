package com.lexmark.contract;

import java.io.Serializable;

public class PartnerAccountListContract implements Serializable {
	
	private static final long serialVersionUID = -4849777356652327432L;
	private String mdmId;
	private String mdmLevel;
	private boolean massUploadFlag;
	private boolean massUploadInstallDebriefFlag;
	
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public boolean isMassUploadFlag() {
		return massUploadFlag;
	}
	public void setMassUploadFlag(boolean massUploadFlag) {
		this.massUploadFlag = massUploadFlag;
	}
	public boolean isMassUploadInstallDebriefFlag() {
		return massUploadInstallDebriefFlag;
	}
	public void setMassUploadInstallDebriefFlag(boolean massUploadInstallDebriefFlag) {
		this.massUploadInstallDebriefFlag = massUploadInstallDebriefFlag;
	}
	
	
}
