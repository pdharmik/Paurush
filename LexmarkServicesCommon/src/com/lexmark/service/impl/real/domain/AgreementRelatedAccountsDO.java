package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * @author i.prikhodko
 * 
 * do-mapping: "do-agreementrelatedaccountsdo-mapping.xml"
 */

public class AgreementRelatedAccountsDO extends AccountBasedDo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String accountId;
	private String accountName;
	private String accountCountry;
	private String level;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountCountry() {
		return accountCountry;
	}
	public void setAccountCountry(String accountCountry) {
		this.accountCountry = accountCountry;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

}
