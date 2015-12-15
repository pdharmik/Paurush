package com.lexmark.domain;

import java.util.List;

public class HardwareCatalog {
	private List<Bundle> bundleList;
	private String productModel;
	private String description;
	private String deviceType;
	private String color_mono;
	
	public String getProductModel() {
		return productModel;
	}
	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	public String getColor_mono() {
		return color_mono;
	}
	public void setColor_mono(String color_mono) {
		this.color_mono = color_mono;
	}
	public List<Bundle> getBundleList() {
		return bundleList;
	}
	public void setBundleList(List<Bundle> bundleList) {
		this.bundleList = bundleList;
	}
	@Override
	public String toString() {
		return "HardwareCatalog [bundleList=" + bundleList + ", productModel="
				+ productModel + ", description=" + description
				+ ", deviceType=" + deviceType + ", color_mono=" + color_mono
				+ "]";
	}
	
	
	
}
