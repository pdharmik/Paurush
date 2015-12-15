package com.lexmark.contract;

import com.lexmark.contract.api.SearchContractBase;

public class RequestContract extends SearchContractBase {

	private static final long serialVersionUID = -6192922506341054593L;

	private String serviceRequestNumber;
    private String visibilityRole;
    private boolean madcServiceRequestFlag;
    private boolean isCreateChildSR;
    
	public String getServiceRequestNumber() {
		return serviceRequestNumber;
	}

	public void setServiceRequestNumber(String serviceRequestNumber) {
		this.serviceRequestNumber = serviceRequestNumber;
	}

	public void setVisibilityRole(String visibilityRole) {
		this.visibilityRole = visibilityRole;
	}

	public String getVisibilityRole() {
		return visibilityRole;
	}

	public boolean isMadcServiceRequestFlag() {
		return madcServiceRequestFlag;
	}

	public void setMadcServiceRequestFlag(boolean madcServiceRequestFlag) {
		this.madcServiceRequestFlag = madcServiceRequestFlag;
	}

	public boolean isCreateChildSR() {
		return isCreateChildSR;
	}

	public void setCreateChildSR(boolean isCreateChildSR) {
		this.isCreateChildSR = isCreateChildSR;
	}
	
}
