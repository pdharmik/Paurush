package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;
import java.util.Date;


/**
 * mapping file: "do-partneractivitygrid-mapping.xml"
 */
public class PartnerActivityGridDo extends PartnerActivityBase {
	private static final long serialVersionUID = -7914371779063308195L;
	
	private Boolean employeeFlag;
	private String queryType;
	private String mdmLevel;
//	private ArrayList<PartnerActivityAccountTypeDo> partnerAccountTypes;
//	private ArrayList<PartnerActivityListPartDo> partsList;
//	private ArrayList<PartnerActivityListMassUploadDebriefDo> massUploadDebriefList;
	private String lastStatusUpdate;
	private ArrayList<PartnerActivityListRecommPartDo> partsList;
//	public ArrayList<PartnerActivityListMassUploadDebriefDo> getMassUploadDebriefList() {
//		return massUploadDebriefList;
//	}
//
//	public void setMassUploadDebriefList(
//			ArrayList<PartnerActivityListMassUploadDebriefDo> massUploadDebriefList) {
//		this.massUploadDebriefList = massUploadDebriefList;
//	}

	private String overrideOffering;
	private String claimStatusDate;
	private Date serviceProviderETA;
	private String parentSRId;
	
	public Boolean isEmployeeFlag() {
		if(employeeFlag == null) {
			employeeFlag = false;
		}
		return employeeFlag;
	}

	public void setEmployeeFlag(Boolean employeeFlag) {
		this.employeeFlag = employeeFlag;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

//	public void setPartneraccountTypes(ArrayList<PartnerActivityAccountTypeDo> partneraccountTypes) {
//		this.partnerAccountTypes = partneraccountTypes;
//	}
//
//	public ArrayList<PartnerActivityAccountTypeDo> getPartneraccountTypes() {
//		return partnerAccountTypes;
//	}

	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}

	public String getMdmLevel() {
		return mdmLevel;
	}
	public Boolean getEmployeeFlag() {
		return employeeFlag;
	}

    public String getOverrideOffering() {
        return overrideOffering;
    }

    public void setOverrideOffering(String overrideOffering) {
        this.overrideOffering = overrideOffering;
    }

//	public ArrayList<PartnerActivityListPartDo> getPartsList() {
//		return partsList;
//	}
//
//	public void setPartsList(ArrayList<PartnerActivityListPartDo> partsList) {
//		this.partsList = partsList;
//	}

	public String getClaimStatusDate() {
		return claimStatusDate;
	}

	public void setClaimStatusDate(String claimStatusDate) {
		this.claimStatusDate = claimStatusDate;
	}

	public String getLastStatusUpdate() {
		return lastStatusUpdate;
	}

	public void setLastStatusUpdate(String lastStatusUpdate) {
		this.lastStatusUpdate = lastStatusUpdate;
	}

	public Date getServiceProviderETA() {
		return serviceProviderETA;
	}

	public void setServiceProviderETA(Date serviceProviderETA) {
		this.serviceProviderETA = serviceProviderETA;
	}
	public ArrayList<PartnerActivityListRecommPartDo> getPartsList() {
		return partsList;
	}

	public void setPartsList(ArrayList<PartnerActivityListRecommPartDo> partsList) {
		this.partsList = partsList;
	}

	public String getParentSRId() {
		return parentSRId;
	}

	public void setParentSRId(String parentSRId) {
		this.parentSRId = parentSRId;
	}
	

}
