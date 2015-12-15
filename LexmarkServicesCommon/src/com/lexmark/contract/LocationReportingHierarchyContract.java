package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.ContractBase;
import com.lexmark.contract.api.SearchContractBase;

/**
 * Contract that is used to retrieve customer hierarchy, or location (Generic Address)
 * @author roger.lin
 *
 */
public class LocationReportingHierarchyContract extends SearchContractBase implements Serializable {

	private static final long serialVersionUID = -5393328525632601383L;
	private String mdmId;
	private String mdmLevel;
	private boolean vendorFlag;
	private String entitlementEndDate;
	private String pageName;
	
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
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
	private String chlNodeId;
	public String getChlNodeId() {
		return chlNodeId;
	}
	public void setChlNodeId(String chlNodeId) {
		this.chlNodeId = chlNodeId;
	}
	
	//NOTE:  This is used for caching because I could not extend with CachingContract since this class already extended ContractBase
	public String toString(){
		return this.getClass().getName() + mdmId + mdmLevel + chlNodeId;
	}
	public void setVendorFlag(boolean vendorFlag) {
		this.vendorFlag = vendorFlag;
	}
	public boolean isVendorFlag() {
		return vendorFlag;
	}
	public void setEntitlementEndDate(String entitlementEndDate) {
		this.entitlementEndDate = entitlementEndDate;
	}
	public String getEntitlementEndDate() {
		return entitlementEndDate;
	}

}
