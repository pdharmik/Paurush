package com.lexmark.service.impl.real.domain;

/**
 * The mapping file: do-accountbyvendorid-mapping.xml
 * 
 * @see com.lexmark.service.impl.real.domain.AccountDo
 */
public class AccountByVendorIdDo extends AccountDo {

    private static final long serialVersionUID = 1L;

    private String vendorAccountId;
    private Boolean partnerFlag;
    private Boolean primaryAccount;

    private String agreementName;
    
    private String mdmLevel;
//    private String name;    // defined in NamedEntity
    private String country;
    private boolean addressFlag;
    private boolean consumableFlag;
    
    public String getVendorAccountId() {
        return vendorAccountId;
    }

    public void setVendorAccountId(String vendorId) {
        this.vendorAccountId = vendorId;
    }

    public Boolean getPartnerFlag() {
        return partnerFlag;
    }

    public Boolean isPartnerFlag() {
        return partnerFlag;
    }

    public void setPartnerFlag(Boolean partnerFlag) {
        this.partnerFlag = partnerFlag;
    }

    public void setPrimaryAccount(Boolean primaryAccount) {
        this.primaryAccount = primaryAccount;
    }

    public Boolean getPrimaryAccount() {
        return primaryAccount;
    }

    public String getAgreementName() {
        return agreementName;
    }

    public void setAgreementName(String agreementName) {
        this.agreementName = agreementName;
    }

    public String getMdmLevel() {
        return mdmLevel;
    }

    public void setMdmLevel(String mdmLevel) {
        this.mdmLevel = mdmLevel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

	public boolean isAddressFlag() {
		return addressFlag;
	}

	public void setAddressFlag(boolean addressFlag) {
		this.addressFlag = addressFlag;
	}

	public boolean isConsumableFlag() {
		return consumableFlag;
	}

	public void setConsumableFlag(boolean consumableFlag) {
		this.consumableFlag = consumableFlag;
	}
}