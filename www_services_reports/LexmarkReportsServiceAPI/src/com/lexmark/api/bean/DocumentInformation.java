/**
 * 
 */
package com.lexmark.api.bean;

import java.util.HashMap;

/**
 * This is the bean for creating document in ImageNow
 *
 */
public class DocumentInformation extends LexmarkReportServiceBean {

	private String drawerId;
	private String field1;
	private String field2;
	private String field3;
	private String field4;
	private String field5;

	/** The document type e.g. SR_SecuredDocs **/
	private String Type;

	/** Map containing the custom property name and it's value **/
	private HashMap<String, String> propertyMap = new HashMap<String, String>();

	private byte[] fileContentInBytes;

	/** The location of newly created document **/
	private String location;
/**
 * 
 * @return String 
 */
	public String getDrawerId() {
		return drawerId;
	}
/**
 * 
 * @param drawerId 
 */
	public void setDrawerId(String drawerId) {
		this.drawerId = drawerId;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getField1() {
		return field1;
	}
/**
 * 
 * @param field1 
 */
	public void setField1(String field1) {
		this.field1 = field1;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getField2() {
		return field2;
	}
/**
 * 
 * @param field2 
 */
	public void setField2(String field2) {
		this.field2 = field2;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getField3() {
		return field3;
	}
/**
 * 
 * @param field3 
 */
	public void setField3(String field3) {
		this.field3 = field3;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getField4() {
		return field4;
	}
/**
 * 
 * @param field4 
 */
	public void setField4(String field4) {
		this.field4 = field4;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getField5() {
		return field5;
	}
/**
 * 
 * @param field5 
 */
	public void setField5(String field5) {
		this.field5 = field5;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getType() {
		return Type;
	}
/**
 * 
 * @param type 
 */
	public void setType(String type) {
		Type = type;
	}
	/**
	 * 
	 * @return HashMap<String, String>  
	 */
	public HashMap<String, String> getPropertyMap() {
		return propertyMap;
	}
/**
 * 
 * @param propertyMap 
 */
	public void setPropertyMap(HashMap<String, String> propertyMap) {
		this.propertyMap = propertyMap;
	}
	/**
	 * 
	 * @return byte[]  
	 */
	public byte[] getFileContentInBytes() {
		//return (fileContentInBytes)secret.clone();
		return fileContentInBytes;
	}
/**
 * 
 * @param fileContentInBytes 
 */
	public void setFileContentInBytes(byte[] fileContentInBytes) {
		this.fileContentInBytes = fileContentInBytes;
	}
/**
 * 
 * @return String 
 */
	public String getLocation() {
		return location;
	}
/**
 * 
 * @param location 
 */
	public void setLocation(String location) {
		this.location = location;
	}
}
