package com.lexmark.contract;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import com.lexmark.contract.api.SearchContractBase;
import com.lexmark.service.api.CrmSessionHandle;

public class AddressListContract extends SearchContractBase implements Serializable {
	private static final long serialVersionUID = -8909858949224665519L;
	private CrmSessionHandle sessionHandle;
	private String mdmId;
	private String mdmLevel;
	private boolean favoriteFlag;
	private Locale locale;
	private String contactId;
	private List<String> soldToNumbers;
	private boolean cleansedAddress;
	private boolean lbsFlag;
	private String country;
	private String state;
	private String city;
	private String locationId;
	private String locationType;
	private String lbsAddressId;
	private boolean firstLoccationCall;
	private String province;
	private String county;
	private String district;
	
	public boolean isCleansedAddress() {
		return cleansedAddress;
	}

	public void setCleansedAddress(boolean cleansedAddress) {
		this.cleansedAddress = cleansedAddress;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public boolean isFavoriteFlag() {
		return favoriteFlag;
	}

	public void setFavoriteFlag(boolean favoriteFlag) {
		this.favoriteFlag = favoriteFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public List<String> getSoldToNumbers() {
		return soldToNumbers;
	}

	public void setSoldToNumbers(List<String> soldToNumbers) {
		this.soldToNumbers = soldToNumbers;
	}

	public CrmSessionHandle getSessionHandle() {
		return sessionHandle;
	}

	public void setSessionHandle(CrmSessionHandle sessionHandle) {
		this.sessionHandle = sessionHandle;
	}

	public boolean isLbsFlag() {
		return lbsFlag;
	}

	public void setLbsFlag(boolean lbsFlag) {
		this.lbsFlag = lbsFlag;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getLbsAddressId() {
		return lbsAddressId;
	}

	public void setLbsAddressId(String lbsAddressId) {
		this.lbsAddressId = lbsAddressId;
	}

	public boolean isFirstLoccationCall() {
		return firstLoccationCall;
	}

	public void setFirstLoccationCall(boolean firstLoccationCall) {
		this.firstLoccationCall = firstLoccationCall;
	}
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
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

	
}
