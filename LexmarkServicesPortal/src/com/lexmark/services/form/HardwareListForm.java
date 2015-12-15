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
	/**
	 * 
	 * @return String 
	 */
	public String getHardwareXML() {
		return HardwareXML;
	}
	/**
	 * 
	 * @param hardwareXML 
	 */
	public void setHardwareXML(String hardwareXML) {
		HardwareXML = hardwareXML;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getBundleListXML() {
		return BundleListXML;
	}
	/**
	 * 
	 * @param bundleListXML 
	 */
	public void setBundleListXML(String bundleListXML) {
		BundleListXML = bundleListXML;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getAccessoriesListXML() {
		return AccessoriesListXML;
	}
	/**
	 * 
	 * @param accessoriesListXML 
	 */
	public void setAccessoriesListXML(String accessoriesListXML) {
		AccessoriesListXML = accessoriesListXML;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getSuppliesListXML() {
		return SuppliesListXML;
	}
	/**
	 * 
	 * @param suppliesListXML 
	 */
	public void setSuppliesListXML(String suppliesListXML) {
		SuppliesListXML = suppliesListXML;
	}
	
}
