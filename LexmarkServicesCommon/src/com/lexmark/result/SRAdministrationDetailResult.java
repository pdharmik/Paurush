package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.SiebelLocalization;

public class SRAdministrationDetailResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2206249374904292027L;
	private SiebelLocalization siebelLocalization;
	public SiebelLocalization getSiebelLocalization() {
		return siebelLocalization;
	}
	public void setSiebelLocalization(SiebelLocalization siebelLocalization) {
		this.siebelLocalization = siebelLocalization;
	}
}
