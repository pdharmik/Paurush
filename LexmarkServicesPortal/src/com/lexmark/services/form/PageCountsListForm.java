/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: PageCountsListForm.java
 * Package     		: com.lexmark.services.form
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro		2013 		2.1             Initial Version
 * 
 */

package com.lexmark.services.form;

/**
 * This class is called in PageCountsController.java
 * @author wipro
 * @version 2.1
 */
public class PageCountsListForm {
	private String deviceLocationTreeXMLURL;
	private String chlTreeXMLURL;
	private String downloadMeterReadURL;
	/**
	 * 
	 * @return currentDateStr 
	 */
	public String getDownloadMeterReadURL() {
		
		return downloadMeterReadURL;
	}
	/**
	 * 
	 * @param downloadMeterReadURL 
	 */
	public void setDownloadMeterReadURL(String downloadMeterReadURL) {
		this.downloadMeterReadURL = downloadMeterReadURL;
	}
	/**
	 * 
	 * @return deviceLocationTreeXMLURL 
	 */
	public String getDeviceLocationTreeXMLURL() {
		return deviceLocationTreeXMLURL;
	}
	/**
	 * 
	 * @param deviceLocationTreeXMLURL 
	 */
	public void setDeviceLocationTreeXMLURL(String deviceLocationTreeXMLURL) {
		this.deviceLocationTreeXMLURL = deviceLocationTreeXMLURL;
	}
	/**
	 * 
	 * @return chlTreeXMLURL 
	 */
	public String getChlTreeXMLURL() {
		return chlTreeXMLURL;
	}
	/**
	 * 
	 * @param chlTreeXMLURL 
	 */
	public void setChlTreeXMLURL(String chlTreeXMLURL) {
		this.chlTreeXMLURL = chlTreeXMLURL;
	}
	
	
}
