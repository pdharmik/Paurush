package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * 
 * @author vshynkarenko
 * mapping-file: "do-supplyrequestdetailpagecount-mapping.xml"
 */
public class SupplyRequestDetailPageCountDo extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	private String count;
	private String date;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
