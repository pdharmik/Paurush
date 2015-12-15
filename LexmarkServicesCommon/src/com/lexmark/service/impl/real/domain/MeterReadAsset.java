package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

/**
 * mapping file: "do-contractedmeterreadassetdo-mapping.xml"
 */
public class MeterReadAsset extends MeterReadBase implements Serializable {
	private static final long serialVersionUID = 1926122927790833725L;
	
	private String parentChain;
	private String ownerAccountId;
	
	
	public String getOwnerAccountId() {
		return ownerAccountId;
	}
	public void setOwnerAccountId(String ownerAccountId) {
		this.ownerAccountId = ownerAccountId;
	}
	public String getParentChain() {
		return parentChain;
	}
	public void setParentChain(String parentChain) {
		this.parentChain = parentChain;
	}
}
