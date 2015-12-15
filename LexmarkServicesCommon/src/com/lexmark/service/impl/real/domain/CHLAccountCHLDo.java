package com.lexmark.service.impl.real.domain;

/**
 * mapping file: "do-chlaccountchldo.xml"
 */
public class CHLAccountCHLDo extends AccountBasedDo {

	private static final long serialVersionUID = -5476577155452748374L;
	
	private String parentChain;
	private String location;
	private String parentAccountId;
	
	
	public String getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(String parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getParentChain() {
		return parentChain;
	}

	public void setParentChain(String parentChain) {
		this.parentChain = parentChain;
	}
}
