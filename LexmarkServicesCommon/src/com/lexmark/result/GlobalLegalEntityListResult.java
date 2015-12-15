package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.GlobalAccount;

public class GlobalLegalEntityListResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7145972902548471085L;
	private List<GlobalAccount>  legalEntityList = new ArrayList<GlobalAccount>();

	public List<GlobalAccount> getLegalEntityList() {
		return legalEntityList;
	}

	public void setLegalEntityList(List<GlobalAccount> legalEntityList) {
		this.legalEntityList = legalEntityList;
	}
	
}
