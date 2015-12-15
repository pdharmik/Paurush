package com.lexmark.contract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.ReportSchedule;
import com.lexmark.domain.ReportScheduleParameter;

public class ScheduleReportContract implements Serializable{
    
    private static final long serialVersionUID = 1L;
	private ReportSchedule	reportSchedule;
	private List<ReportScheduleParameter> parameterValues = new ArrayList<ReportScheduleParameter>();
	
	
	public ReportSchedule getReportSchedule() {
		return reportSchedule;
	}
	public void setReportSchedule(ReportSchedule reportSchedule) {
		this.reportSchedule = reportSchedule;
	}
	public List<ReportScheduleParameter> getParameterValues() {
		return parameterValues;
	}
	public void setParameterValues(List<ReportScheduleParameter> parameterValues) {
		this.parameterValues = parameterValues;
	}
	
	//start-Shriya
	private String cntry;
	private String lang;
	private String CHL;
	private String MDMId_LDAP;
	
	public String getMDMId_LDAP() {
		return MDMId_LDAP;
	}
	public void setMDMId_LDAP(String mDMId_LDAP) {
		MDMId_LDAP = mDMId_LDAP;
	}
	public String getCntry() {
		return cntry;
	}
	public void setCntry(String cntry) {
		this.cntry = cntry;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getCHL() {
		return CHL;
	}
	public void setCHL(String cHL) {
		CHL = cHL;
	}
	//end -Shriya

}
