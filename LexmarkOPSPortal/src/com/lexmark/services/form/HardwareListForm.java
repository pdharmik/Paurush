package com.lexmark.services.form;

import java.io.Serializable;

/**
 * @author wipro
 * @version 2.1
 *
 */
public class HardwareListForm implements Serializable{
	private static final long serialVersionUID = 1L;
	private String HardwareXML;
	private String BundleListXML;
	private String AccessoriesListXML;
	private String SuppliesListXML;
	public String getHardwareXML() {
		return HardwareXML;
	}
	public void setHardwareXML(String hardwareXML) {
		HardwareXML = hardwareXML;
	}
	public String getBundleListXML() {
		return BundleListXML;
	}
	public void setBundleListXML(String bundleListXML) {
		BundleListXML = bundleListXML;
	}
	public String getAccessoriesListXML() {
		return AccessoriesListXML;
	}
	public void setAccessoriesListXML(String accessoriesListXML) {
		AccessoriesListXML = accessoriesListXML;
	}
	public String getSuppliesListXML() {
		return SuppliesListXML;
	}
	public void setSuppliesListXML(String suppliesListXML) {
		SuppliesListXML = suppliesListXML;
	}
	
}
