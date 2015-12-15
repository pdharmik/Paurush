package com.lexmark.contract;

import java.io.Serializable;

public class SRLocalizationContract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7059863534071543926L;
	private Integer siebelLocalizationId;
	public Integer getSiebelLocalizationId() {
		return siebelLocalizationId;
	}
	public void setSiebelLocalizationId(Integer siebelLocalizationId) {
		this.siebelLocalizationId = siebelLocalizationId;
	}
}
