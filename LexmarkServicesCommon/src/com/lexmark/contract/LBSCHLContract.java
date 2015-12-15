package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.SearchContractBase;
import com.lexmark.service.api.CrmSessionHandle;

public class LBSCHLContract extends SearchContractBase implements Serializable {
	
	private static final long serialVersionUID = -8909858949224665519L;
	private CrmSessionHandle sessionHandle;
	private String childID;
	
	public CrmSessionHandle getSessionHandle() {
		return sessionHandle;
	}
	public void setSessionHandle(CrmSessionHandle sessionHandle) {
		this.sessionHandle = sessionHandle;
	}
	public String getChildID() {
		return childID;
	}
	public void setChildID(String childID) {
		this.childID = childID;
	}
}
