package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.ObieeImpersonate;

public class ObieeImpersonateContract implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3078740107687107425L;
	private ObieeImpersonate obieeImpersonate;

	public ObieeImpersonate getObieeImpersonate() {
		return obieeImpersonate;
	}

	public void setObieeImpersonate(ObieeImpersonate obieeImpersonate) {
		this.obieeImpersonate = obieeImpersonate;
	}
	
	
}
