package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Bundle;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.OrderPart;

public class GlobalCatalogListResult implements Serializable {
	private static final long serialVersionUID = 8022882274067805950L;
	//Accessories
	private int accessoriesTotalCount;
	private List<OrderPart> partsList = new ArrayList<OrderPart>();
	private List<OrderPart> accessoriesList = new ArrayList<OrderPart>();
	private List<OrderPart> suppliesList = new ArrayList<OrderPart>();
	//Supplies
	private List<OrderPart> suppliesPartsList = new ArrayList<OrderPart>();
	private int suppliesTotalCount;
	//Bundles
	private List<Bundle> bundleList;
	private int bundlesTotalCount;
	
	
	public List<OrderPart> getPartsList() {
		return partsList;
	}
	public void setPartsList(List<OrderPart> partsList) {
		this.partsList = partsList;
	}
	public List<OrderPart> getAccessoriesList() {
		return accessoriesList;
	}
	public void setAccessoriesList(List<OrderPart> accessoriesList) {
		this.accessoriesList = accessoriesList;
	}
	public List<OrderPart> getSuppliesList() {
		return suppliesList;
	}
	public void setSuppliesList(List<OrderPart> suppliesList) {
		this.suppliesList = suppliesList;
	}
	public List<Bundle> getBundleList() {
		return bundleList;
	}
	public void setBundleList(List<Bundle> bundleList) {
		this.bundleList = bundleList;
	}
	public int getBundlesTotalCount() {
		return bundlesTotalCount;
	}
	public void setBundlesTotalCount(int bundlesTotalCount) {
		this.bundlesTotalCount = bundlesTotalCount;
	}
	public int getAccessoriesTotalCount() {
		return accessoriesTotalCount;
	}
	public void setAccessoriesTotalCount(int accessoriesTotalCount) {
		this.accessoriesTotalCount = accessoriesTotalCount;
	}
	public int getSuppliesTotalCount() {
		return suppliesTotalCount;
	}
	public void setSuppliesTotalCount(int suppliesTotalCount) {
		this.suppliesTotalCount = suppliesTotalCount;
	}
	public List<OrderPart> getSuppliesPartsList() {
		return suppliesPartsList;
	}
	public void setSuppliesPartsList(List<OrderPart> suppliesPartsList) {
		this.suppliesPartsList = suppliesPartsList;
	}
}
