package com.lexmark.contract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.lexmark.contract.api.ContractBase;

public class ReportListContract extends ContractBase implements Serializable{
	
	private static final long serialVersionUID = -8887918776842094351L;
	
	private String reportDefinitionId;
	private String mdmId;
	private String mdmLevel;
	private Locale locale;
	private List<String> userRoleNames; 
	private String userNumber;
	private String accountNm;	// added by nelson for employee report
	private List<String>  viewTypes = new ArrayList<String>();
	private String employeeMdmId;
	
	public String getReportDefinitionId() {
		return reportDefinitionId;
	}
	public void setReportDefinitionId(String reportDefinitionId) {
		this.reportDefinitionId = reportDefinitionId;
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
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public List<String> getUserRoleNames() {
		return userRoleNames;
	}
	public void setUserRoleNames(List<String> userRoleNames) {
		this.userRoleNames = userRoleNames;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public List<String> getViewTypes() {
		return viewTypes;
	}
	public void setViewTypes(List<String> viewTypes) {
		this.viewTypes = viewTypes;
	}
	
//	added for employee report by nelson
	public void setAccountNm(String accountNm) {
		this.accountNm = accountNm;
	}
	
	public String getAccountNm() {
		return accountNm;
	}
	public String getEmployeeMdmId() {
		return employeeMdmId;
	}
	public void setEmployeeMdmId(String employeeMdmId) {
		this.employeeMdmId = employeeMdmId;
	}
	
//	end of addition by nelson for employee report
	
}
