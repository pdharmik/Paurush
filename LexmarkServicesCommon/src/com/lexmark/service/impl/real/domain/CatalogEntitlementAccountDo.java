package com.lexmark.service.impl.real.domain;


/** 
 * @author Vano 
 * 
 * mapping file: "do-catalogentitlement-mapping.xml"
 */

import java.util.ArrayList;

public class CatalogEntitlementAccountDo extends AccountBasedDo {

	private static final long serialVersionUID = 1387359540417781599L;
	
	private String level;
	private String agreementStatus;
	private boolean catalogEntitlementFlag;
	private String agreementId;
	private String catalogExpediteFlag;
	private String accountId;
	private String accountName;
	private String accountCountry;
	private String agreementName;
	private boolean accountSplitterFlag;
	private String showPrice;
    private ArrayList<SuppliesSAPCatalogDo> suppliesCatalogDo;
    private String entitlementType;
    private String paymentMethod;
	private String organizationName;
	private ArrayList<AgreementRelatedAccountsDO> agreementRelatedAccounts;
	private String mpsQuantity;
	
	public String getMpsQuantity() {
		return mpsQuantity;
	}
	public void setMpsQuantity(String mpsQuantity) {
		this.mpsQuantity = mpsQuantity;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getAgreementStatus() {
		return agreementStatus;
	}
	public void setAgreementStatus(String agreementStatus) {
		this.agreementStatus = agreementStatus;
	}
	public boolean getCatalogEntitlementFlag() {
		return catalogEntitlementFlag;
	}
	public void setCatalogEntitlementFlag(boolean catalogEntitlementFlag) {
		this.catalogEntitlementFlag = catalogEntitlementFlag;
	}
	public String getAgreementId() {
		return agreementId;
	}
	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}
	public String getCatalogExpediteFlag() {
		return catalogExpediteFlag;
	}
	public void setCatalogExpediteFlag(String catalogExpediteFlag) {
		this.catalogExpediteFlag = catalogExpediteFlag;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountCountry() {
		return accountCountry;
	}
	public void setAccountCountry(String accountCountry) {
		this.accountCountry = accountCountry;
	}
	public String getAgreementName() {
		return agreementName;
	}
	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}
	public ArrayList<SuppliesSAPCatalogDo> getSuppliesCatalogDo() {
		return suppliesCatalogDo;
	}
	public void setSuppliesCatalogDo(
			ArrayList<SuppliesSAPCatalogDo> suppliesCatalogDo) {
		this.suppliesCatalogDo = suppliesCatalogDo;
	}
	public boolean isAccountSplitterFlag() {
		return accountSplitterFlag;
	}
	public void setAccountSplitterFlag(boolean accountSplitterFlag) {
		this.accountSplitterFlag = accountSplitterFlag;
	}
	public String getEntitlementType() {
		return entitlementType;
	}
	public void setEntitlementType(String entitlementType) {
		this.entitlementType = entitlementType;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getShowPrice() {
		return showPrice;
	}
	public void setShowPrice(String showPrice) {
		this.showPrice = showPrice;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public ArrayList<AgreementRelatedAccountsDO> getAgreementRelatedAccounts() {
		return agreementRelatedAccounts;
	}
	public void setAgreementRelatedAccounts(
			ArrayList<AgreementRelatedAccountsDO> agreementRelatedAccounts) {
		this.agreementRelatedAccounts = agreementRelatedAccounts;
	}
}
