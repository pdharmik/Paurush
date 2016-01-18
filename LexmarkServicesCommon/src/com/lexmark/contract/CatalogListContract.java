package com.lexmark.contract;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

import com.lexmark.contract.api.MdmSearchContractBase;
import com.lexmark.service.api.CrmSessionHandle;




public class CatalogListContract extends MdmSearchContractBase implements Serializable {
	

	private static final long serialVersionUID = -3710698966678741897L;

	
	private CrmSessionHandle sessionHandle;
	
	//Basic Required Parameters
	private String mdmID;
	private String mdmLevel;
	private String contactID;
	private String country;
	private Locale locale;
	private String soldToNumber;
	private String paymentType;
	private String contractNumber;
	//Part details


	// for list of product type, product model, part type
	private String productType;
	private String partType;
	private String productModel;
	private String partNumber;
	// for list of product type, product model, part type
	private String accountId;
	private String agreementId;
	private String contactId;
	private boolean portalFlag;
	private boolean catalogFlag;
	private boolean hardwareFlag;
	
	private boolean hardwareSuppliesFlag;
	private boolean hardwareAccessoriesFlag;
	private String printerMaterialNum;
	
	private Date effectiveDate;
	private String bundleId;//added for b2b this will get the options & warranties on the basis of which bundle

	public boolean isHardwareSuppliesFlag() {
		return hardwareSuppliesFlag;
	}
	public void setHardwareSuppliesFlag(boolean hardwareSuppliesFlag) {
		this.hardwareSuppliesFlag = hardwareSuppliesFlag;
	}
	public boolean isHardwareAccessoriesFlag() {
		return hardwareAccessoriesFlag;
	}
	public void setHardwareAccessoriesFlag(boolean hardwareAccessoriesFlag) {
		this.hardwareAccessoriesFlag = hardwareAccessoriesFlag;
	}
	/**
	 * Session handle used by service maintain siebel session
	 * @param sessionHandle handle object
	 */
	public void setSessionHandle(CrmSessionHandle sessionHandle) {
		this.sessionHandle = sessionHandle;
	}
	/**
	 * Session handle used by service maintain siebel session
	 * @return sessionHandle
	 */
	public CrmSessionHandle getSessionHandle() {
		return sessionHandle;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
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
	public String getContactID() {
		return contactID;
	}
	public void setContactID(String contactID) {
		this.contactID = contactID;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}



	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;

	}
	public String getAccountId() {
		return accountId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getContactId() {
		return contactId;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public boolean isPortalFlag() {
		return portalFlag;
	}

	public void setPortalFlag(boolean portalFlag) {
		this.portalFlag = portalFlag;
	}
	public String getSoldToNumber() {
		return soldToNumber;
	}
	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public boolean isCatalogFlag() {
		return catalogFlag;
	}
	public void setCatalogFlag(boolean catalogFlag) {
		this.catalogFlag = catalogFlag;
	}
	public boolean isHardwareFlag() {
		return hardwareFlag;
	}
	public void setHardwareFlag(boolean hardwareFlag) {
		this.hardwareFlag = hardwareFlag;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}
	public String getBundleId() {
		return bundleId;
	}
	
	public String getPrinterMaterialNum() {
		 	return printerMaterialNum;
		 	}
		 	public void setPrinterMaterialNum(String printerMaterialNum) {
		 		this.printerMaterialNum = printerMaterialNum;
		 }
}
