package com.lexmark.service.impl.real.domain;

import com.amind.common.domain.BaseEntity;



public class PartnerClaimCreateIdDo extends BaseEntity {

private static final long serialVersionUID = -996344515775151964L;
	
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
