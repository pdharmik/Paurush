package com.lexmark.domain;

import java.io.Serializable;

public class GenericAddress implements Serializable {
    private static final long serialVersionUID = -8870347648805474590L;
    private String addressId;
    private String addressName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String city;
    private String state;
    private String province;
    private String postalCode;
    private String country;
    private String storeFrontName;
    private String newAddressFlag;//as part of the CI5 merge
    private boolean userFavorite;
    private String stateProvince;
    private String physicalLocation1;
    private String physicalLocation2;
    private String physicalLocation3;
    private String errorMsgForCleansing;
    
    //MPS phase 2.1 Address Cleansing changes for additional fields to be passed to Siebel
    private String county;
    private String officeNumber;
    private String stateCode;
    private String stateFullName;
    private String district;
    
    private String primaryAddressID;
    private String primaryAddressName;
	private String primaryAddressLine1;
	private String primaryAddressLine2;
	private String primaryAddressLine3;
	private String primaryCity;
	private String primaryProvince;
	private String primaryPostalCode;
	private String primaryCountry;
	private String primaryState;
	
	private String primaryCounty;
	private String primaryDistrict;
	private String primaryOfficeNumber;
	private String primaryRegion;

    
    private String region;    
    private String latitude;
    private String longitude;
    private String savedErrorMessage;
    private String countryISOCode;
    //private java.lang.String userFavoriteFlag;
	private String soldToNumber;
	private String massUploaderCountry;
	//Added for LBS Address Fields
	private String floorNumber;
	private String buildingId;
	private String buildingName;
	private String regionId;
	private String regionName;
	private String campusId;
	private String campusName;
	private String floorId;
	private String floorName;
	private String extAddressId;
	private String zoneName;
	private String zoneId;
	private Boolean lbsAddressFlag;
	private String LBSIdentifierFlag;
	private String lbsLongitude;
    private String lbsLatitude;
    private String coordinatesXPreDebriefRFV;
	private String coordinatesYPreDebriefRFV;
    //Ends LBS Address Fields
    
    public String getCoordinatesXPreDebriefRFV() {
		return coordinatesXPreDebriefRFV;
	}

	public void setCoordinatesXPreDebriefRFV(String coordinatesXPreDebriefRFV) {
		this.coordinatesXPreDebriefRFV = coordinatesXPreDebriefRFV;
	}

	public String getCoordinatesYPreDebriefRFV() {
		return coordinatesYPreDebriefRFV;
	}

	public void setCoordinatesYPreDebriefRFV(String coordinatesYPreDebriefRFV) {
		this.coordinatesYPreDebriefRFV = coordinatesYPreDebriefRFV;
	}

	public String getLbsLongitude() {
		return lbsLongitude;
	}

	public void setLbsLongitude(String lbsLongitude) {
		this.lbsLongitude = lbsLongitude;
	}

	public String getLbsLatitude() {
		return lbsLatitude;
	}

	public void setLbsLatitude(String lbsLatitude) {
		this.lbsLatitude = lbsLatitude;
	}

	public Boolean getLbsAddressFlag() {
		return lbsAddressFlag;
	}

	public void setLbsAddressFlag(Boolean lbsAddressFlag) {
		this.lbsAddressFlag = lbsAddressFlag;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getFloorNumber() {
		return floorNumber;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getCampusId() {
		return campusId;
	}

	public void setCampusId(String campusId) {
		this.campusId = campusId;
	}

	public String getCampusName() {
		return campusName;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getExtAddressId() {
		return extAddressId;
	}

	public void setExtAddressId(String extAddressId) {
		this.extAddressId = extAddressId;
	}

	public void setAddressCleansed(boolean isAddressCleansed) {
		this.isAddressCleansed = isAddressCleansed;
	}

	public String getPrimaryRegion() {
		return primaryRegion;
	}

	public void setPrimaryRegion(String primaryRegion) {
		this.primaryRegion = primaryRegion;
	}

	
	public String getPrimaryAddressID() {
		return primaryAddressID;
	}

	public void setPrimaryAddressID(String primaryAddressID) {
		this.primaryAddressID = primaryAddressID;
	}

	public String getPrimaryAddressName() {
		return primaryAddressName;
	}

	public void setPrimaryAddressName(String primaryAddressName) {
		this.primaryAddressName = primaryAddressName;
	}

	public String getPrimaryAddressLine1() {
		return primaryAddressLine1;
	}

	public void setPrimaryAddressLine1(String primaryAddressLine1) {
		this.primaryAddressLine1 = primaryAddressLine1;
	}

	public String getPrimaryAddressLine2() {
		return primaryAddressLine2;
	}

	public void setPrimaryAddressLine2(String primaryAddressLine2) {
		this.primaryAddressLine2 = primaryAddressLine2;
	}

	public String getPrimaryAddressLine3() {
		return primaryAddressLine3;
	}

	public void setPrimaryAddressLine3(String primaryAddressLine3) {
		this.primaryAddressLine3 = primaryAddressLine3;
	}

	public String getPrimaryCity() {
		return primaryCity;
	}

	public void setPrimaryCity(String primaryCity) {
		this.primaryCity = primaryCity;
	}

	public String getPrimaryProvince() {
		return primaryProvince;
	}

	public void setPrimaryProvince(String primaryProvince) {
		this.primaryProvince = primaryProvince;
	}

	public String getPrimaryPostalCode() {
		return primaryPostalCode;
	}

	public void setPrimaryPostalCode(String primaryPostalCode) {
		this.primaryPostalCode = primaryPostalCode;
	}

	public String getPrimaryCountry() {
		return primaryCountry;
	}

	public void setPrimaryCountry(String primaryCountry) {
		this.primaryCountry = primaryCountry;
	}

	public String getPrimaryState() {
		return primaryState;
	}

	public void setPrimaryState(String primaryState) {
		this.primaryState = primaryState;
	}

	public String getPrimaryCounty() {
		return primaryCounty;
	}

	public void setPrimaryCounty(String primaryCounty) {
		this.primaryCounty = primaryCounty;
	}

	public String getPrimaryDistrict() {
		return primaryDistrict;
	}

	public void setPrimaryDistrict(String primaryDistrict) {
		this.primaryDistrict = primaryDistrict;
	}

	public String getPrimaryOfficeNumber() {
		return primaryOfficeNumber;
	}

	public void setPrimaryOfficeNumber(String primaryOfficeNumber) {
		this.primaryOfficeNumber = primaryOfficeNumber;
	}

	
	public String getMassUploaderCountry() {
		return massUploaderCountry;
	}

	public void setMassUploaderCountry(String massUploaderCountry) {
		this.massUploaderCountry = massUploaderCountry;
	}

	/* Changes for MPS Phase 2
	 * Added on 6-6-2013 
	 * Added for Cleansing flag to be used during WEB Methods call*/
	private boolean isAddressCleansed;
	//Ends
	
	private String billToNumber;

    public String getPhysicalLocation1() {
        return physicalLocation1;
    }

    public void setPhysicalLocation1(String physicalLocation1) {
        this.physicalLocation1 = physicalLocation1;
    }

    public String getPhysicalLocation2() {
        return physicalLocation2;
    }

    public void setPhysicalLocation2(String physicalLocation2) {
        this.physicalLocation2 = physicalLocation2;
    }

    public String getPhysicalLocation3() {
        return physicalLocation3;
    }

    public void setPhysicalLocation3(String physicalLocation3) {
        this.physicalLocation3 = physicalLocation3;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

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

    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
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

    public String getNewAddressFlag() {
		return newAddressFlag;
	}

	public void setNewAddressFlag(String newAddressFlag) {
		this.newAddressFlag = newAddressFlag;
	}

	public boolean getUserFavorite() {
        return userFavorite;
    }

    public void setUserFavorite(boolean userFavorite) {
        this.userFavorite = userFavorite;
    }

    public String getStoreFrontName() {
        return storeFrontName;
    }

    public void setStoreFrontName(String storeFrontName) {
        this.storeFrontName = storeFrontName;
    }

	public String getErrorMsgForCleansing() {
		return errorMsgForCleansing;
	}

	public void setErrorMsgForCleansing(String errorMsgForCleansing) {
		this.errorMsgForCleansing = errorMsgForCleansing;
	}

	public java.lang.String getCounty() {
		return county;
	}

	public void setCounty(java.lang.String county) {
		this.county = county;
	}

	public java.lang.String getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(java.lang.String officeNumber) {
		this.officeNumber = officeNumber;
	}

	public java.lang.String getStateCode() {
		return stateCode;
	}

	public void setStateCode(java.lang.String stateCode) {
		this.stateCode = stateCode;
	}

	public java.lang.String getDistrict() {
		return district;
	}

	public void setDistrict(java.lang.String district) {
		this.district = district;
	}

	public java.lang.String getRegion() {
		return region;
	}

	public void setRegion(java.lang.String region) {
		this.region = region;
	}

	public java.lang.String getStateFullName() {
		return stateFullName;
	}

	public void setStateFullName(java.lang.String stateFullName) {
		this.stateFullName = stateFullName;
	}

	public java.lang.String getLatitude() {
		return latitude;
	}

	public void setLatitude(java.lang.String latitude) {
		this.latitude = latitude;
	}

	public java.lang.String getLongitude() {
		return longitude;
	}

	public void setLongitude(java.lang.String longitude) {
		this.longitude = longitude;
	}

	public String getSavedErrorMessage() {
		return savedErrorMessage;
	}

	public void setSavedErrorMessage(String savedErrorMessage) {
		this.savedErrorMessage = savedErrorMessage;
	}

	public String getCountryISOCode() {
		return countryISOCode;
	}

	public void setCountryISOCode(String countryISOCode) {
		this.countryISOCode = countryISOCode;
	}

	public String getSoldToNumber() {
		return soldToNumber;
	}

	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}

	public void setIsAddressCleansed(boolean isAddressCleansed) {
		this.isAddressCleansed = isAddressCleansed;
	}

	public boolean getIsAddressCleansed() {
		return isAddressCleansed;
	}

	public String getBillToNumber() {
		return billToNumber;
	}

	public void setBillToNumber(String billToNumber) {
		this.billToNumber = billToNumber;
	}

	public String getLBSIdentifierFlag() {
		return LBSIdentifierFlag;
	}

	public void setLBSIdentifierFlag(String lBSIdentifierFlag) {
		LBSIdentifierFlag = lBSIdentifierFlag;
	}


}
