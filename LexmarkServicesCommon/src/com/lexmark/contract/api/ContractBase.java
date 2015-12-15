package com.lexmark.contract.api;

import java.io.Serializable;

import com.lexmark.service.api.CrmSessionHandle;

public abstract class ContractBase implements Serializable {

	private static final long serialVersionUID = 9063143173265931852L;
	private CrmSessionHandle sessionHandle;

	/**
	 * Session handle used by service maintain siebel session
	 * @param sessionHandle handle object
	 */
	public void setSessionHandle(CrmSessionHandle sessionHandle) {
		this.sessionHandle = sessionHandle;
	}
	/**
	 * Session handle used by service maintain siebel session
	 * @return sessionHandle
	 */
	public CrmSessionHandle getSessionHandle() {
		return sessionHandle;
	}
}
