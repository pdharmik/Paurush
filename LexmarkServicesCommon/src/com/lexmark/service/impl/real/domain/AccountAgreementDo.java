package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;

/**
 * @author pkozlov
 * 
 * mapping file: "do-accountagreement-mapping.xml"
 */
public class AccountAgreementDo extends AccountBasedDo {

	private static final long serialVersionUID = -3883964167871099655L;

	private String accountId;
	private String accountName;
	private String accountOrganization;
	private String location;
	private ArrayList<AgreementDo> agreements;

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

	public String getAccountOrganization() {
		return accountOrganization;
	}

	public void setAccountOrganization(String accountOrganization) {
		this.accountOrganization = accountOrganization;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ArrayList<AgreementDo> getAgreements() {
		return agreements;
	}

	public void setAgreements(ArrayList<AgreementDo> agreements) {
		this.agreements = agreements;
	}

}
