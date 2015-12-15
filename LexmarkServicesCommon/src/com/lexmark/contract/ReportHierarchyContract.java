package com.lexmark.contract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.lexmark.contract.api.ContractBase;

public class ReportHierarchyContract extends ContractBase implements Serializable {
	private static final long serialVersionUID = 1675990037743413623L;
	
	private String mdmID;
	private String mdmLevel;
	/* Added for (BRD 14-02-14) */
	private String userMdmId;	
	private String userMdmLevel;
	/* end (BRD 14-02-14)*/
	private List<String> userRoleNames;
	private Locale locale;
	private List<String>  viewTypes = new ArrayList<String>();
	
	
	public String getMdmID() {
		return mdmID;
	}
	public void setMdmID(String mdmID) {
		this.mdmID = mdmID;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public List<String> getUserRoleNames() {
		return userRoleNames;
	}
	public void setUserRoleNames(List<String> userRoleNames) {
		this.userRoleNames = userRoleNames;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public List<String> getViewTypes() {
		return viewTypes;
	}
	public void setViewTypes(List<String> viewTypes) {
		this.viewTypes = viewTypes;
	}
	/* Added for (BRD 14-02-14) */
	public String getUserMdmId() {
		return userMdmId;
	}
	public void setUserMdmId(String userMdmId) {
		this.userMdmId = userMdmId;
	}
	public String getUserMdmLevel() {
		return userMdmLevel;
	}
	public void setUserMdmLevel(String userMdmLevel) {
		this.userMdmLevel = userMdmLevel;
	}
	/* End (BRD 14-02-14)*/
	
}
