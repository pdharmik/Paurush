package com.lexmark.domain;

import com.lexmark.util.StringUtil;
import java.util.HashSet;
/* Added for (BRD 14-02-14) */
public class CompanyMdmAssociation {

    private String companyDAId=null;
    private String companyLegalName=null;
    private String companyType=null;
    private String companyRole=null;
    private String companyLegalNo=null;
    private String companyGlobalNo=null;
    
    public String getCompanyGlobalNo() {
		return companyGlobalNo;
	}
	public void setCompanyGlobalNo(String companyGlobalNo) {
		this.companyGlobalNo = companyGlobalNo;
	}
	public String getCompanyDomesticNo() {
		return companyDomesticNo;
	}
	public void setCompanyDomesticNo(String companyDomesticNo) {
		this.companyDomesticNo = companyDomesticNo;
	}
	private String companyDomesticNo=null;
    
	public String getCompanyDAId() {
		return companyDAId;
	}
	public void setCompanyDAId(String companyDAId) {
		this.companyDAId = companyDAId;
	}
	public String getCompanyLegalName() {
		return companyLegalName;
	}
	public void setCompanyLegalName(String companyLegalName) {
		this.companyLegalName = companyLegalName;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getCompanyRole() {
		return companyRole;
	}
	public void setCompanyRole(String companyRole) {
		this.companyRole = companyRole;
	}
	public String getCompanyLegalNo() {
		return companyLegalNo;
	}
	public void setCompanyLegalNo(String companyLegalNo) {
		this.companyLegalNo = companyLegalNo;
	}
    
    
/* End */
}
