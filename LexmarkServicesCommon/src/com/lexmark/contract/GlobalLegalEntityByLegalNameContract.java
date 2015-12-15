package com.lexmark.contract;

import java.io.Serializable;

public class GlobalLegalEntityByLegalNameContract implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private String leagalName;

	public String getLeagalName() {
		return leagalName;
	}

	public void setLeagalName(String leagalName) {
		this.leagalName = leagalName;
	}
	
}
