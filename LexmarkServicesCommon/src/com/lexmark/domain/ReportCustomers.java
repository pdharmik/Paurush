package com.lexmark.domain;

import java.io.Serializable;

/**
 * @author nelson
 * @category CI5
 * this class holds the customer details for a particular report
 */
public class ReportCustomers implements Serializable {
	
	private static final long serialVersionUID = 6925372726519158886L;
	
	private String mdmId;
	private String mdmLevel;
	private Integer reportDefinitionId;
	private Integer id;
	private String checkRestrictExclude;
	
	
	public String getCheckRestrictExclude() {
		return checkRestrictExclude;
	}
	public void setCheckRestrictExclude(String checkRestrictExclude) {
		this.checkRestrictExclude = checkRestrictExclude;
	}
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setReportDefinitionId(Integer reportDefinitionId) {
		this.reportDefinitionId = reportDefinitionId;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Integer getReportDefinitionId() {
		return reportDefinitionId;
	}
	
}
