package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * mapping file:  do-contractedasset-mapping.xml
 */
public class AccountAsset extends AssetBase implements Serializable {
    private static final long serialVersionUID = 1417674785149968704L;

    private String assetNumber;
    private String contactFirstName;
    private String contactLastName;
    private String contactWorkPhone;
    private String contactEmailAddress;
    private String contactMiddleName;
    private String contactAlternatePhone;
    private String parentChain;
    private String ownerAccountId;
    private String agreementType;
    private Date createDate;
    private String assetType;
    private Boolean consumableAssetFlag;    
				

    public String getContactFirstName() {
        return contactFirstName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }

    public String getOwnerAccountId() {
        return ownerAccountId;
    }

    public void setOwnerAccountId(String ownerAccountId) {
        this.ownerAccountId = ownerAccountId;
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

    public String getContactWorkPhone() {
        return contactWorkPhone;
    }

    public void setContactWorkPhone(String contactWorkPhone) {
        this.contactWorkPhone = contactWorkPhone;
    }

    public String getContactEmailAddress() {
        return contactEmailAddress;
    }

    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }

    public void setParentChain(String parentChain) {
        this.parentChain = parentChain;
    }

    public String getParentChain() {
        return parentChain;
    }

    public String getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public void setContactMiddleName(String contactMiddleName) {
        this.contactMiddleName = contactMiddleName;
    }

    public String getContactMiddleName() {
        return contactMiddleName;
    }

    public void setContactAlternatePhone(String contactAlternatePhone) {
        this.contactAlternatePhone = contactAlternatePhone;
    }

    public String getContactAlternatePhone() {
        return contactAlternatePhone;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public Boolean isConsumableAssetFlag() {
        return consumableAssetFlag;
    }

    public void setConsumableAssetFlag(Boolean consumableAssetFlag) {
        this.consumableAssetFlag = consumableAssetFlag;
    }
}
