package com.lexmark.contract.uploadHistory;


import java.io.Serializable;
import java.util.Map.Entry;

import com.lexmark.contract.api.SearchContractBase;

public class UploadHistoryListContract extends SearchContractBase implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String mdmId;
	private String mdmLevel;

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

	
}
