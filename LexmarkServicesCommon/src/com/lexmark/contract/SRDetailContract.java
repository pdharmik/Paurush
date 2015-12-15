package com.lexmark.contract;

import java.io.Serializable;

public class SRDetailContract implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6595846793209135662L;
	private String serviceReqNo;

	public String getServiceReqNo() {
		return serviceReqNo;
	}

	public void setServiceReqNo(String serviceReqNo) {
		this.serviceReqNo = serviceReqNo;
	}

}
