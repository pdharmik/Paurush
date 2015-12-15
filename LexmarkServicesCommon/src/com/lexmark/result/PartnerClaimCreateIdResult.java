package com.lexmark.result;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.lexmark.domain.Account;
import com.lexmark.domain.Payment;
import com.lexmark.result.api.AccountSecuredResult;

public class PartnerClaimCreateIdResult implements Serializable {

	private static final long serialVersionUID = -3912937171951073998L;
	
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
