package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Account;
import com.lexmark.service.impl.real.domain.PartnerAccountTypeDo;

public class PartnerAccountListResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -372664276708623288L;
	private List<Account> accountList = new ArrayList<Account>();
	private List<PartnerAccountTypeDo> partnerTypeList = new ArrayList<PartnerAccountTypeDo>();

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public List<PartnerAccountTypeDo> getPartnerTypeList() {
		return partnerTypeList;
	}

	public void setPartnerTypeList(List<PartnerAccountTypeDo> partnerTypeList) {
		this.partnerTypeList = partnerTypeList;
	}
	
}
