package com.lexmark.domain;

import com.lexmark.util.StringUtil;
import java.util.HashSet;
/* Added by sankha (BRD 14-02-14) */
public class CompanyTreeBean {

    private boolean childPresent = false;
    private String levelName = null;
    private String company_global_ult_no=null;
    private String company_global_ult_name= null;
    private String company_domestic_ult_no= null;
    private String company_domestic_ult_name = null;
    private String company_legal_mdm_id = null;
    private String company_legal_mdm_name = null;

    private String userCompanyCheck="";
    private String expandOrCollapsed ="closed";

    private HashSet globalId = new HashSet();
    private HashSet domesticId = new HashSet();

    private String expandGlobal="closed";
    private String expandDomestic="closed";

    public String getExpandDomestic() {
        return expandDomestic;
    }

    public void setExpandDomestic(String expandDomestic) {
        this.expandDomestic = expandDomestic;
    }

    public String getExpandGlobal() {
        return expandGlobal;
    }

    public void setExpandGlobal(String expandGlobal) {
        this.expandGlobal = expandGlobal;
    }

    

    public String getExpandOrCollapsed() {
        return expandOrCollapsed;
    }

    public void setExpandOrCollapsed(String expandOrCollapsed) {
        this.expandOrCollapsed = expandOrCollapsed;
    }

    public String getUserCompanyCheck() {
        return userCompanyCheck;
    }

    public void setUserCompanyCheck(String userCompanyCheck) {
        this.userCompanyCheck = userCompanyCheck;
    }

    



    public boolean isChildPresent() {
        return childPresent;
    }

    public void setChildPresent(boolean childPresent) {
        this.childPresent = childPresent;
    }

    public String getLevelName() {
        return StringUtil.isNull(levelName);
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
    
   
     public void setlevelName(String levelName) {
        this.levelName= levelName;
    }
    public String getCompany_domestic_ult_name() {
        return StringUtil.isNull(company_domestic_ult_name);
    }

    public void setCompany_domestic_ult_name(String company_domestic_ult_name) {
        this.company_domestic_ult_name = company_domestic_ult_name;
    }

    public String getCompany_domestic_ult_no() {
        return StringUtil.isNull(company_domestic_ult_no);
    }

    public void setCompany_domestic_ult_no(String company_domestic_ult_no) {
        this.company_domestic_ult_no = company_domestic_ult_no;

       if(null!=company_domestic_ult_no){
        if(!"".equals(this.company_domestic_ult_no)){
            this.childPresent=true;
        }
       }
    }

    public String getCompany_global_ult_name() {
        return StringUtil.isNull(company_global_ult_name);
    }

    public void setCompany_global_ult_name(String company_global_ult_name) {
        this.company_global_ult_name = company_global_ult_name;
    }

    public String getCompany_global_ult_no() {
        return StringUtil.isNull(company_global_ult_no);
    }

    public void setCompany_global_ult_no(String company_global_ult_no) {
        this.company_global_ult_no = company_global_ult_no;
    }

    public String getCompany_legal_mdm_id() {
        return StringUtil.isNull(company_legal_mdm_id);
    }

    public void setCompany_legal_mdm_id(String company_legal_mdm_id) {
        this.company_legal_mdm_id = company_legal_mdm_id;
    }

    public String getCompany_legal_mdm_name() {
        return StringUtil.isNull(company_legal_mdm_name);
    }

    public void setCompany_legal_mdm_name(String company_legal_mdm_name) {
        this.company_legal_mdm_name = company_legal_mdm_name;
    }


/* End */
}
