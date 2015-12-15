package com.lexmark.service.impl.real.domain;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-dunsnumberdo-mapping.xml"
 */
public class DunsNumberDo extends BaseEntity {
	private String legalEntity;
	private String transFlag;
	private String globalDuns;
	private String domesticDuns;
	
	public void setLegalEntity(String legalEntity) {
		this.legalEntity = legalEntity;
	}
	public String getLegalEntity() {
		return legalEntity;
	}
	public void setTransFlag(String transFlag) {
		this.transFlag = transFlag;
	}
	public String getTransFlag() {
		return transFlag;
	}
	public void setGlobalDuns(String globalDuns) {
		this.globalDuns = globalDuns;
	}
	public String getGlobalDuns() {
		return globalDuns;
	}
	public void setDomesticDuns(String domesticDuns) {
		this.domesticDuns = domesticDuns;
	}
	public String getDomesticDuns() {
		return domesticDuns;
	}
}
