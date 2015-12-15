package com.lexmark.domain;

import java.util.List;

public class ReportDefinition extends DocumentDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4649871691090089740L;
	private String reportType;
	private String reportSourceId;
	private boolean isSendMDMParameter;
	private boolean isSchedulable;
	private String description;
	private String viewType;   // ALL, DFM,  CSS
	private List<ReportParameters> parameterList;
	private String displayMdmId;
	private List<ReportCustomers> reportCustomersList;		// added by nelson for CI5
	
	
	public String getDisplayMdmId() {
		return displayMdmId;
	}

	public void setDisplayMdmId(String displayMdmId) {
		this.displayMdmId = displayMdmId;
	}

	
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getReportSourceId() {
		return reportSourceId;
	}
	public void setReportSourceId(String reportSourceId) {
		this.reportSourceId = reportSourceId;
	}
	public boolean getIsSchedulable() {
		return isSchedulable;
	}
	public void setIsSchedulable(boolean isSchedulable) {
		this.isSchedulable = isSchedulable;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public void setIsSendMDMParameter(boolean isSendMDMParameter) {
		this.isSendMDMParameter = isSendMDMParameter;
	}
	public boolean getIsSendMDMParameter() {
		return isSendMDMParameter;
	}

	public void setParameterList(List<ReportParameters> parameterList) {
		this.parameterList = parameterList;
	}
	public List<ReportParameters> getParameterList() {
		return parameterList;
	}
//	added by nelson for CI5
	public List<ReportCustomers> getReportCustomersList() {
		return reportCustomersList;
	}
	
	public void setReportCustomersList(List<ReportCustomers> reportCustomersList) {
		this.reportCustomersList = reportCustomersList;
	}
//	end of addition for CI5 by nelson


	public enum ReportType {
		BUSINESS_OBJECTS("BO", "Business Objects"),
		MANUAL_UPLOAD("MU", "Manual Upload"),
		ORACLE_ANALYTICS("OA", "Oracle Analytics"),
		SECURED_DOCUMENT("SD", "Secured Document");
		
		private String value;
		private String displayName;
		ReportType(String value, String displayName) {
			this.value = value;
			this.displayName = displayName;
		}
		public String getValue() {
			return value;
		}
		
		public String getDisplayName() {
			return displayName;
		}
	}
}
