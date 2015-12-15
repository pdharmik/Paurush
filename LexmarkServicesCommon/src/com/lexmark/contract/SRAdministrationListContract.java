package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.CachingContract;

public class SRAdministrationListContract  extends CachingContract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7059863534071543926L;

	@Override
	public String getCacheKey() {
		return "";
	}
	
}
