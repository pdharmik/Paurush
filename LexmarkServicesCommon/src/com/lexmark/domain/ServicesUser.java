package com.lexmark.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

public class ServicesUser implements Serializable {
	private static final long serialVersionUID = -2760762011654901519L;
	private String emailAddress;
	private String chlNodeId;
	private String userNumber;
	private String mdmId;
	private String mdmLevel;
	private String accountName;
	private Integer servicesUserId;
	private String language;
	private String country;
    private HashSet<String> accountids = new HashSet<String>();
    private boolean allAccounts=false;
    private String companyName;

    public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getChlNodeId() {
		return chlNodeId;
	}
	public void setChlNodeId(String chlNodeId) {
		this.chlNodeId = chlNodeId;
	}
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setServicesUserId(Integer servicesUserId) {
		this.servicesUserId = servicesUserId;
	}
	public Integer getServicesUserId() {
		return servicesUserId;
	}

    public boolean hasAccount(Account account) {
        return allAccounts || accountids.contains(account.getAccountId());
    }

    public HashSet<String> getAccountids() {
        return accountids;
    }

    public void setAccountids(HashSet<String> accountids) {
        this.accountids = accountids;
    }

    public void setAccounts(List<Account> accountList) {
        for(Account account: accountList) {
            accountids.add(account.getAccountId());
        }
    }

    public void setAllAccount(boolean b) {
        this.allAccounts = b;
    }
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
}
