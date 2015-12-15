package com.lexmark.result;

import java.io.Serializable;

public class ProcessCustomerContactResult implements Serializable {

	private static final long serialVersionUID = 8527391893747412244L;
	private String siebelContactId;
	
	public void setSiebelContactId(String siebelContactId) {
		this.siebelContactId = siebelContactId;
	}
	public String getSiebelContactId() {
		return siebelContactId;
	}
	

}
