package com.lexmark.service.impl.real.domain;

/**
 * The mapping file: do-ordersuppliesassetdo-mapping.xml
 * 
 * @see com.lexmark.service.impl.real.domain.OrderSuppliesAssetFavoriteDo
 * 
 * @author vpetruchok
 * @version 1.0, 2012-03-16
 */
@SuppressWarnings("serial")
public class OrderSuppliesAssetDo extends AssetBase {
    
    public static final String IO = "LXK MPS SW Consumable Asset List";
    public static final String IC = "LXK MPS SW Consumable Asset List";
    public static final String BO = "LXK MPS SW Consumable Asset List";
    public static final String BC = "LXK MPS SW Consumable Asset List";
    public static final String SIEBEL_MDM_LEVEL_FILTER_FIELD = "LXK SW MDM Account Level";
    

    private String ownerAccountId;
    private String agreementType;
    private String agreementId;

    private String contactFirstName;
    private String contactLastName;
    private String contactEmailAddress;
    private String contactWorkPhone;
    private String mdmLevel;
    private Boolean consumableAssetFlag;    
    private String parentChain;
    private String consumableType;
    
    
    // private String contactId;

    public String getOwnerAccountId() {
        return ownerAccountId;
    }

    public void setOwnerAccountId(String ownerAccountId) {
        this.ownerAccountId = ownerAccountId;
    }

    public String getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }
    

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }
    

    public String getContactLastName() {
        return contactLastName;
    }
    

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }
    

    public String getContactEmailAddress() {
        return contactEmailAddress;
    }
    

    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }
    

    public String getContactWorkPhone() {
        return contactWorkPhone;
    }
    

    public void setContactWorkPhone(String contactWorkPhone) {
        this.contactWorkPhone = contactWorkPhone;
    }

    public String getMdmLevel() {
        return mdmLevel;
    }

    public void setMdmLevel(String mdmLevel) {
        this.mdmLevel = mdmLevel;
    }
    

    public Boolean isConsumableAssetFlag() {
        return consumableAssetFlag;
    }
    

    public void setConsumableAssetFlag(Boolean consumableAssetFlag) {
        this.consumableAssetFlag = consumableAssetFlag;
    }

    public String getParentChain() {
        return parentChain;
    }

    public void setParentChain(String parentChain) {
        this.parentChain = parentChain;
    }

	public void setConsumableType(String consumableType) {
		this.consumableType = consumableType;
	}

	public String getConsumableType() {
		return consumableType;
	}
}
