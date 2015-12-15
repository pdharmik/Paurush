package com.lexmark.domain;

import java.io.Serializable;
/**
 * This Class written under BRD 14-07-04
 */
public class PartnerTypes implements Serializable {
	private static final long serialVersionUID = 7085922728860557942L;
	
	private int partnerTypeId;

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPartnerTypeId() {
		return partnerTypeId;
	}

	public void setPartnerTypeId(int partnerTypeId) {
		this.partnerTypeId = partnerTypeId;
	}
}
