package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

/**
 * @author David Tsamalashvili * 
 */
public class LBSCHLMapping implements Serializable {
	private static final long serialVersionUID = 6483553507743496750L;

	private String l5AccoountId;
	private String heirarchyParentChain;

	public String getHeirarchyParentChain() {
		return heirarchyParentChain;
	}

	public void setHeirarchyParentChain(String heirarchyParentChain) {
		this.heirarchyParentChain = heirarchyParentChain;
	}

	public String getL5AccoountId() {
		return l5AccoountId;
	}

	public void setL5AccoountId(String l5AccoountId) {
		this.l5AccoountId = l5AccoountId;
	}
	
}
