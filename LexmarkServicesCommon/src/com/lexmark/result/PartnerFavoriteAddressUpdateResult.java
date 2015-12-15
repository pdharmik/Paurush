package com.lexmark.result;

import java.io.Serializable;

public class PartnerFavoriteAddressUpdateResult implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8310267723630313650L;
	private boolean result;	
	
	public boolean isResult() {
		return result;
	}
	
	public void setResult(boolean result) {
		this.result = result;
	}


}
