package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * The mapping file: do-accountpayable-mapping.xml
 * 
 * 
 * @author vpetruchok
 * @version 1.0, 2012-09-13
 */
public class AccountPayableDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String BO = "LXK Company Codes";
    public static final String BC = "LXK Company Codes";

    private String mdmLevel1AccountId;
    private String mdmLevel2AccountId;
    private String mdmLevel3AccountId;
    private String mdmLevel4AccountId;
    private String mdmLevel5AccountId;
    private String mdmLevel;
    
    private String vendorClass;
    private String vendorAccountType;

    private String vendorId; 
    private String vendorName; 
    private String companyCodeDescription; 
    private String companyCode;
    private String payeeName;

    // Payable Address:   
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String province;
    private String country;
    private String postalCode;

    public String getMdmLevel1AccountId() {
        return mdmLevel1AccountId;
    }

    public void setMdmLevel1AccountId(String mdmLevel1AccountId) {
        this.mdmLevel1AccountId = mdmLevel1AccountId;
    }

    public String getMdmLevel2AccountId() {
        return mdmLevel2AccountId;
    }

    public void setMdmLevel2AccountId(String mdmLevel2AccountId) {
        this.mdmLevel2AccountId = mdmLevel2AccountId;
    }

    public String getMdmLevel3AccountId() {
        return mdmLevel3AccountId;
    }

    public void setMdmLevel3AccountId(String mdmLevel3AccountId) {
        this.mdmLevel3AccountId = mdmLevel3AccountId;
    }

    public String getMdmLevel4AccountId() {
        return mdmLevel4AccountId;
    }

    public void setMdmLevel4AccountId(String mdmLevel4AccountId) {
        this.mdmLevel4AccountId = mdmLevel4AccountId;
    }

    public String getMdmLevel5AccountId() {
        return mdmLevel5AccountId;
    }

    public void setMdmLevel5AccountId(String mdmLevel5AccountId) {
        this.mdmLevel5AccountId = mdmLevel5AccountId;
    }

    public String getMdmLevel() {
        return mdmLevel;
    }

    public void setMdmLevel(String mdmLevel) {
        this.mdmLevel = mdmLevel;
    }

    public String getVendorClass() {
        return vendorClass;
    }

    public void setVendorClass(String vendorClass) {
        this.vendorClass = vendorClass;
    }

    public String getVendorAccountType() {
        return vendorAccountType;
    }

    public void setVendorAccountType(String vendorAccountType) {
        this.vendorAccountType = vendorAccountType;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getCompanyCodeDescription() {
        return companyCodeDescription;
    }

    public void setCompanyCodeDescription(String companyCodeDescription) {
        this.companyCodeDescription = companyCodeDescription;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
