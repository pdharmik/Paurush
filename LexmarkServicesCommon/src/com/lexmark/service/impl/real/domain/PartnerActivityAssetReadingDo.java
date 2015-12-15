package com.lexmark.service.impl.real.domain;

/**
 * The mapping file: "do-partneractivityassetreading-mapping.xml" 
 */
public class PartnerActivityAssetReadingDo extends PartnerDetailAttachmentBase{
	private static final long serialVersionUID = -8389602612941273410L;
	
	private String countType;
	private String pageCount;
	private String type;
	
	public String getCountType() {
		return countType;
	}
	public void setCountType(String countType) {
		this.countType = countType;
	}
	public String getPageCount() {
		return pageCount;
	}
	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
