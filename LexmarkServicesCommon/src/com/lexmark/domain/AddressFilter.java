package com.lexmark.domain;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class AddressFilter implements Serializable{
	
	private static final long serialVersionUID = -1583028903290232332L;
	
	//Address Fields Annotations
		@SerializedName("aId")
		private String installedAddressId;
		
		@SerializedName("cty")
		private String city;
		
		@SerializedName("ctry")
		private String country;
		
		@SerializedName("state")
		private String state;
		
		@SerializedName("prvnc")
		private String province;
		
		@SerializedName("poCde")
		private String zipCode;
		
		@SerializedName("sNm")
		private String site;
		
		@SerializedName("sIT")
		private String siteText;
		
		
		@SerializedName("bNm")
		private String building;
		
		@SerializedName("bIT")
		private String buildingText;
		
		@SerializedName("fNm")
		private String floor;
		
		@SerializedName("fIT")
		private String floorText;
		
		@SerializedName("aLine1")
		private String addressLine1;
		
		@SerializedName("aLine2")
		private String addressLine2;
		
		@SerializedName("zn")
		private String zone;
		
		@SerializedName("zIT")
		private String zoneText;
		
		@SerializedName("bTyp")
		private String buildingType;
		
		public String getSiteText() {
			return siteText;
		}

		public void setSiteText(String siteText) {
			this.siteText = siteText;
		}

		public String getBuildingText() {
			return buildingText;
		}

		public void setBuildingText(String buildingText) {
			this.buildingText = buildingText;
		}

		public String getFloorText() {
			return floorText;
		}

		public void setFloorText(String floorText) {
			this.floorText = floorText;
		}

		public String getZoneText() {
			return zoneText;
		}

		public void setZoneText(String zoneText) {
			this.zoneText = zoneText;
		}

		public String getInstalledAddressId() {
			return installedAddressId;
		}

		public void setInstalledAddressId(String installedAddressId) {
			this.installedAddressId = installedAddressId;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
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

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getZipCode() {
			return zipCode;
		}

		public void setZipcode(String zipCode) {
			this.zipCode = zipCode;
		}

		public String getSite() {
			return site;
		}

		public void setSite(String site) {
			this.site = site;
		}

		public String getBuilding() {
			return building;
		}

		public void setBuilding(String building) {
			this.building = building;
		}

		public String getFloor() {
			return floor;
		}

		public void setFloor(String floor) {
			this.floor = floor;
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

		public String getZone() {
			return zone;
		}

		public void setZone(String zone) {
			this.zone = zone;
		}

		public String getBuildingType() {
			return buildingType;
		}

		public void setBuildingType(String buildingType) {
			this.buildingType = buildingType;
		}

		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}

		

		
}
