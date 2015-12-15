package com.lexmark.service.impl.real.domain;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-partneractivitydetailrepaireddevice-mapping.xml"
 */
public class PartnerActivityDetailRepairedDeviceDo extends BaseEntity {
	
	private static final long serialVersionUID = 1957467156169017737L;
	
	private String pageCount;
	private boolean primaryFlag;
	private String pageCountDate;
	private String pageCountType;
	
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

	public String getPageCountDate() {
		return pageCountDate;
	}

	public void setPageCountDate(String pageCountDate) {
		this.pageCountDate = pageCountDate;
	}

	public String getPageCountType() {
		return pageCountType;
	}

	public void setPageCountType(String pageCountType) {
		this.pageCountType = pageCountType;
	}
}
