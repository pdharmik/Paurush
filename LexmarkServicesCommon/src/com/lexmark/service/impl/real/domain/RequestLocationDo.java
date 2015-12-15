package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import com.amind.common.domain.BaseEntity;

/**
 * 
 * @author vshynkarenko
 *         mapping-file: "do-requestlocation-mapping.xml"
 * 
 */
public class RequestLocationDo extends AccountBasedDo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String addressName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String state;
    private String province;
    private String postalCode;
    private String country;

    private String chlNodeId;
    private String chlNodeName;

    private String vendorMdmLevel1AccountId;
    private String vendorMdmLevel2AccountId;
    private String vendorMdmLevel3AccountId;
    private String vendorMdmLevel4AccountId;
    private String vendorMdmLevel5AccountId;
    private String vendorMdmLevel;
	private String agreementMdmLevel1AccountId;
	private String agreementMdmLevel2AccountId;
	private String agreementMdmLevel3AccountId;
	private String agreementMdmLevel4AccountId;
	private String agreementMdmLevel5AccountId;
	private String county;
	private String district;
	private String officeNumber;
    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
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

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getChlNodeId() {
        return chlNodeId;
    }

    public void setChlNodeId(String chlNodeId) {
        this.chlNodeId = chlNodeId;
    }

    public String getChlNodeName() {
        return chlNodeName;
    }

    public void setChlNodeName(String chlNodeName) {
        this.chlNodeName = chlNodeName;
    }

    public String getVendorMdmLevel1AccountId() {
        return vendorMdmLevel1AccountId;
    }

    public void setVendorMdmLevel1AccountId(String vendorMdmLevel1AccountId) {
        this.vendorMdmLevel1AccountId = vendorMdmLevel1AccountId;
    }

    public String getVendorMdmLevel2AccountId() {
        return vendorMdmLevel2AccountId;
    }

    public void setVendorMdmLevel2AccountId(String vendorMdmLevel2AccountId) {
        this.vendorMdmLevel2AccountId = vendorMdmLevel2AccountId;
    }

    public String getVendorMdmLevel3AccountId() {
        return vendorMdmLevel3AccountId;
    }

    public void setVendorMdmLevel3AccountId(String vendorMdmLevel3AccountId) {
        this.vendorMdmLevel3AccountId = vendorMdmLevel3AccountId;
    }

    public String getVendorMdmLevel4AccountId() {
        return vendorMdmLevel4AccountId;
    }

    public void setVendorMdmLevel4AccountId(String vendorMdmLevel4AccountId) {
        this.vendorMdmLevel4AccountId = vendorMdmLevel4AccountId;
    }

    public String getVendorMdmLevel5AccountId() {
        return vendorMdmLevel5AccountId;
    }

    public void setVendorMdmLevel5AccountId(String vendorMdmLevel5AccountId) {
        this.vendorMdmLevel5AccountId = vendorMdmLevel5AccountId;
    }

    public String getVendorMdmLevel() {
        return vendorMdmLevel;
    }

    public void setVendorMdmLevel(String vendorMdmLevel) {
        this.vendorMdmLevel = vendorMdmLevel;
    }

	public String getAgreementMdmLevel1AccountId() {
		return agreementMdmLevel1AccountId;
	}

	public void setAgreementMdmLevel1AccountId(String agreementMdmLevel1AccountId) {
		this.agreementMdmLevel1AccountId = agreementMdmLevel1AccountId;
	}

	public String getAgreementMdmLevel2AccountId() {
		return agreementMdmLevel2AccountId;
	}

	public void setAgreementMdmLevel2AccountId(String agreementMdmLevel2AccountId) {
		this.agreementMdmLevel2AccountId = agreementMdmLevel2AccountId;
	}

	public String getAgreementMdmLevel3AccountId() {
		return agreementMdmLevel3AccountId;
	}

	public void setAgreementMdmLevel3AccountId(String agreementMdmLevel3AccountId) {
		this.agreementMdmLevel3AccountId = agreementMdmLevel3AccountId;
	}

	public String getAgreementMdmLevel4AccountId() {
		return agreementMdmLevel4AccountId;
	}

	public void setAgreementMdmLevel4AccountId(String agreementMdmLevel4AccountId) {
		this.agreementMdmLevel4AccountId = agreementMdmLevel4AccountId;
	}

	public String getAgreementMdmLevel5AccountId() {
		return agreementMdmLevel5AccountId;
	}

	public void setAgreementMdmLevel5AccountId(String agreementMdmLevel5AccountId) {
		this.agreementMdmLevel5AccountId = agreementMdmLevel5AccountId;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	
    
}
