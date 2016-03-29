package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.CachingContract;

public class SRAdministrationListContract  extends CachingContract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7059863534071543926L;
	private boolean retrieveAll;//it True then retrieves all the values from the admin table, else the query will be condition based.
	@Override
	public String getCacheKey() {
		return "";
	}
	/**
	 * @return the retrieveAll
	 */
	public boolean isRetrieveAll() {
		return retrieveAll;
	}
	/**
	 * @param retrieveAll the retrieveAll to set
	 */
	public void setRetrieveAll(boolean retrieveAll) {
		this.retrieveAll = retrieveAll;
	}

		
}
