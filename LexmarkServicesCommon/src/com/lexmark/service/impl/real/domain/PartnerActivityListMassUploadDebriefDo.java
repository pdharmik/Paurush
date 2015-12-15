package com.lexmark.service.impl.real.domain;

import com.amind.common.domain.BaseEntity;

/**
 * @author s.sasidharan
 *
 * do-mapping file: "do-PartnerActivityListMassUploadDebrief-mapping.xml" 
 */

public class PartnerActivityListMassUploadDebriefDo extends BaseEntity{
private String pageCountType;
private String pageCount;


public String getPageCountType() {
	return pageCountType;
}
public void setPageCountType(String pageCountType) {
	this.pageCountType = pageCountType;
}
public String getPageCount() {
	return pageCount;
}
public void setPageCount(String pageCount) {
	this.pageCount = pageCount;
}

	
}
