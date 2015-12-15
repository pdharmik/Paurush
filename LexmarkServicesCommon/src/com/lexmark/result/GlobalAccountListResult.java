package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.GlobalAccount;

public class GlobalAccountListResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7145972902548471085L;
	private List<GlobalAccount>  globalAccountList = new ArrayList<GlobalAccount>();

	public List<GlobalAccount> getGlobalAccountList() {
		return globalAccountList;
	}

	public void setGlobalAccountList(List<GlobalAccount> globalAccountList) {
		this.globalAccountList = globalAccountList;
	}
	
}
