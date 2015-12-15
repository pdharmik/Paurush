package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;

/**
 * The mapping file: do-accountserviceagreement-mapping.xml
 * 
 * @author vpetruchok
 * @version 1.0, 2013-01-17
 */
public class AccountServiceAgreementDo extends AccountBasedDo {

    private static final long serialVersionUID = 1L;

    private String assetEntitlementFlag;
    private String catalogEntitlementFlag;
    private String expediteOrderCount;
    private String catalogExpediteOrderCount;
    private String agreementStatus;
	private ArrayList<AccountEntitlementDo> accountEntitlements;
    // private ArrayList<AccountServiceAgreementCatalogDo> catalogs;
    // private ArrayList<AccountServiceAgreementEntitlementDo> entitlements;

    public ArrayList<AccountEntitlementDo> getAccountEntitlements() {
		return accountEntitlements;
	}

	public void setAccountEntitlements(
			ArrayList<AccountEntitlementDo> accountEntitlements) {
		this.accountEntitlements = accountEntitlements;
	}

	public String getAssetEntitlementFlag() {
        return assetEntitlementFlag;
    }

    public void setAssetEntitlementFlag(String assetEntitlementFlag) {
        this.assetEntitlementFlag = assetEntitlementFlag;
    }

    public String getCatalogEntitlementFlag() {
        return catalogEntitlementFlag;
    }

    public void setCatalogEntitlementFlag(String catalogEntitlementFlag) {
        this.catalogEntitlementFlag = catalogEntitlementFlag;
    }

    public String getExpediteOrderCount() {
        return expediteOrderCount;
    }

    public void setExpediteOrderCount(String expediteOrderCount) {
        this.expediteOrderCount = expediteOrderCount;
    }
    

    public String getCatalogExpediteOrderCount() {
        return catalogExpediteOrderCount;
    }
    

    public void setCatalogExpediteOrderCount(String catalogExpediteOrderCount) {
        this.catalogExpediteOrderCount = catalogExpediteOrderCount;
    }

    public String getAgreementStatus() {
        return agreementStatus;
    }

    public void setAgreementStatus(String agreementStatus) {
        this.agreementStatus = agreementStatus;
    }
    
}
