package com.lexmark.contract.source;

import java.io.Serializable;
import java.util.List;

import com.lexmark.contract.api.SearchContractBase;
import com.lexmark.contract.api.SecureContract;

public class OrderDetailContract extends SearchContractBase implements Serializable {
	private static final long serialVersionUID = -8298447966185294873L;
	private String serviceRequestId;

	/**
	 * The debriefFlag is set based on user actions performed on the screen and
	 * will need to be set to true only when loading the debrief (Close Out)
	 * screen, this will tell aMind to populate the partDebriefList. If the
	 * Detail screen is being loaded you will set the flag to false.
	 **/
	private boolean debriefFlag;

	private String activityId;
	
	private List<String> vendorAccountIds; 
	private String orderNumber;
	private String requestNumber;

	public String getServiceRequestId() {
		return serviceRequestId;
	}

	public void setServiceRequestId(String serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}

	public boolean isDebriefFlag() {
		return debriefFlag;
	}

	public void setDebriefFlag(boolean debriefFlag) {
		this.debriefFlag = debriefFlag;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

    public List<String> getVendorAccountIds() {
        return vendorAccountIds;
    }

    public void setVendorAccountIds(List<String> vendorAccountIds) {
        this.vendorAccountIds = vendorAccountIds;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }
}
