package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.SiebelLocalization;

public class SRAdministrationSaveContract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7059863534071543926L;
	private SiebelLocalization siebelLocalization;
	public SiebelLocalization getSiebelLocalization() {
		return siebelLocalization;
	}
	public void setSiebelLocalization(SiebelLocalization siebelLocalization) {
		this.siebelLocalization = siebelLocalization;
	}
}
