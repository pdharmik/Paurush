package com.lexmark.service.impl.real.domain;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-partneractivitydetailinstalleddevice-mapping.xml"
 */
public class PartnerActivityDetailInstalledAssetDo extends BaseEntity {
	
	private String pageCount;
	private boolean primaryFlag;
	
	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public void setPrimaryFlag(boolean primaryFlag) {
		this.primaryFlag = primaryFlag;
	}

	public boolean isPrimaryFlag() {
		return primaryFlag;
	}
}
