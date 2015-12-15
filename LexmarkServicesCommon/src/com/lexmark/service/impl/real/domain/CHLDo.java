package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-chldo-mapping.xml"
 */
public class CHLDo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 3047182647918477012L;

	private String parentChain;
	private String topLevelAccountId;
	private String parentAccountId;
	private boolean hasChild;
	private boolean activeFlag;
	
	public boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(String parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public String getTopLevelAccountId() {
		return topLevelAccountId;
	}

	public void setTopLevelAccountId(String topLevelAccountId) {
		this.topLevelAccountId = topLevelAccountId;
	}

	public String getParentChain() {
		return parentChain;
	}

	public void setParentChain(String parentChain) {
		this.parentChain = parentChain;
	}
}
