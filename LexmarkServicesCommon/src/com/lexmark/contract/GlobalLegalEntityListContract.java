package com.lexmark.contract;

import com.lexmark.contract.api.CachingContract;
import com.lexmark.service.api.CrmSessionHandle;

public class GlobalLegalEntityListContract extends CachingContract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5929330910623381606L;
	private CrmSessionHandle sessionHandle;

	@Override
	public String getCacheKey() {
		return "";
	}

	public CrmSessionHandle getSessionHandle() {
		return sessionHandle;
	}

	public void setSessionHandle(CrmSessionHandle sessionHandle) {
		this.sessionHandle = sessionHandle;
	}

}
