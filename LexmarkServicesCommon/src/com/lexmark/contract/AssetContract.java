package com.lexmark.contract;

import java.io.Serializable;
import java.util.Date;

import com.lexmark.contract.api.ContractBase;

/**
 * Contract used to retrieve device (or printer) by asset id, service session id.
 * @author Roger.Lin
 *
 */
public class AssetContract extends ContractBase implements Serializable {
	private static final long serialVersionUID = -8230285470836338775L;
	private String assetId; 
	private String contactId;
	private String serialNumber;
	private String mdmId;
	private String mdmLevel;
	private String pageName;
	private Date effectiveDate;
 	
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	private Date currentDate;
	
	
	
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
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
    public Date getCurrentDate() {
        return currentDate;
    }
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}
