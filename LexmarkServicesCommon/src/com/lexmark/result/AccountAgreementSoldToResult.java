package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.PunchoutAccount;

public class AccountAgreementSoldToResult implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<PunchoutAccount> mpsB2bList;
	
	public AccountAgreementSoldToResult(List<PunchoutAccount> mpsB2bList) {
	    this.mpsB2bList = mpsB2bList;
    }

	public List<PunchoutAccount> getMpsB2bList() {
		return mpsB2bList;
	}

	public void setMpsB2bList(List<PunchoutAccount> mpsB2bList) {
		this.mpsB2bList = mpsB2bList;
	}

}
