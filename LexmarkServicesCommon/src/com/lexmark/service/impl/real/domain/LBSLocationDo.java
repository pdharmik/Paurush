package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.lexmark.service.impl.real.domain.AccountBasedDo;

/**
 * @author David Tsamalashvili
 * mapping file: "do-lbslocationdo-mapping.xml"
 */
public class LBSLocationDo extends AccountBasedDo implements Serializable {
	private static final long serialVersionUID = 6483553507743496750L;

	private String site;
	private String siteId;
	private String building;
	private String buildingId;
	private String floor;
	private String floorId;
	private String zone;
	private String zoneId;
	private String country;
	private String state;
	private String city;
	private String locationName;
	private String locationId;
	private String province;
	private String county;
	private String district;
	private String buildingType;
	private String floorLevelOfDetails;
	
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getFloorId() {
		return floorId;
	}
	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
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
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
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
	public String getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}
	public String getFloorLevelOfDetails() {
		return floorLevelOfDetails;
	}
	public void setFloorLevelOfDetails(String floorLevelOfDetails) {
		this.floorLevelOfDetails = floorLevelOfDetails;
	}

}
