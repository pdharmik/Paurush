package com.lexmark.result;

import java.io.Serializable;

public class WarrantyClaimCreateResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4003137511866142251L;
	
	private String requestId;
	
	private String requestNumber;

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}	
}
