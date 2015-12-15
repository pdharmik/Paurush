package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.GenericAddress;
import com.lexmark.service.impl.real.domain.LBSAddress;
import com.lexmark.service.impl.real.domain.LBSLocationBuilding;
import com.lexmark.service.impl.real.domain.LBSLocationBuildingType;
import com.lexmark.service.impl.real.domain.LBSLocationFloor;
import com.lexmark.service.impl.real.domain.LBSLocationSite;
import com.lexmark.service.impl.real.domain.LBSLocationZone;

public class AddressListResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6689722415261650266L;
	private int totalCount;
	private List<GenericAddress> addressList  = new ArrayList<GenericAddress>(0);
	private List<LBSAddress> lbsAddressList  = new ArrayList<LBSAddress>();
	private List<LBSLocationSite> lbsLocationSiteList  = new ArrayList<LBSLocationSite>();
	private List<LBSLocationBuilding> lbsLocationBuildingList  = new ArrayList<LBSLocationBuilding>();
	private List<LBSLocationFloor> lbsLocationFloorList  = new ArrayList<LBSLocationFloor>();
	private List<LBSLocationZone> lbsLocationZoneList  = new ArrayList<LBSLocationZone>();
	private List<LBSLocationBuildingType> lbsLocationBuildingType = new ArrayList<LBSLocationBuildingType>();

	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<GenericAddress> getAddressList() {
		return  addressList;
	}
	public void setAddressList(List<GenericAddress>  addressList) {
		this. addressList =  addressList;
	}
	public List<LBSAddress> getLbsAddressList() {
		return lbsAddressList;
	}
	public void setLbsAddressList(List<LBSAddress> lbsAddressList) {
		this.lbsAddressList = lbsAddressList;
	}
	public List<LBSLocationSite> getLbsLocationSiteList() {
		return lbsLocationSiteList;
	}
	public void setLbsLocationSiteList(List<LBSLocationSite> lbsLocationSiteList) {
		this.lbsLocationSiteList = lbsLocationSiteList;
	}
	public List<LBSLocationBuilding> getLbsLocationBuildingList() {
		return lbsLocationBuildingList;
	}
	public void setLbsLocationBuildingList(
			List<LBSLocationBuilding> lbsLocationBuildingList) {
		this.lbsLocationBuildingList = lbsLocationBuildingList;
	}
	public List<LBSLocationFloor> getLbsLocationFloorList() {
		return lbsLocationFloorList;
	}
	public void setLbsLocationFloorList(List<LBSLocationFloor> lbsLocationFloorList) {
		this.lbsLocationFloorList = lbsLocationFloorList;
	}
	public List<LBSLocationZone> getLbsLocationZoneList() {
		return lbsLocationZoneList;
	}
	public void setLbsLocationZoneList(List<LBSLocationZone> lbsLocationZoneList) {
		this.lbsLocationZoneList = lbsLocationZoneList;
	}
	public List<LBSLocationBuildingType> getLbsLocationBuildingType() {
		return lbsLocationBuildingType;
	}
	public void setLbsLocationBuildingType(
			List<LBSLocationBuildingType> lbsLocationBuildingType) {
		this.lbsLocationBuildingType = lbsLocationBuildingType;
	}
		
}
