package com.lexmark.domain;

import java.io.Serializable;

public class CreateClaimId implements Serializable {

	private static final long serialVersionUID = 464791871449758036L;
	
	private String claimNumber;
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public String getClaimId() {
		return claimId;
	}
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	private String claimId;	
}
