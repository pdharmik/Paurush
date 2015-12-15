package com.lexmark.api.bean;


/**
 * This is the bean for fetching the document list
 *
 */

public class DocListInfo extends LexmarkReportServiceBean {

	private String definitionId;

	private String drawerName;

	private String docType;

	/** This element is to have the custom properties in ImageNow **/
	private String propertyList;

	/** This element is to have the fields in ImageNow **/
	private String fieldList;
/**
 * 
 * @return String 
 */
	public String getPropertyList() {
		return propertyList;
	}
/**
 * 
 * @param propertyList 
 */
	public void setPropertyList(String propertyList) {
		this.propertyList = propertyList;
	}
/**
 * 
 * @return String 
 */
	public String getDefinitionId() {
		return definitionId;
	}
/**
 * 
 * @param definitionId 
 */
	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}
/**
 * 
 * @return String 
 */
	public String getDrawerName() {
		return drawerName;
	}
/**
 * 
 * @param drawerName 
 */
	public void setDrawerName(String drawerName) {
		this.drawerName = drawerName;
	}
/**
 * 
 * @return String 
 */
	public String getDocType() {
		return docType;
	}
/**
 * 
 * @param docType 
 */
	public void setDocType(String docType) {
		this.docType = docType;
	}
/**
 * 
 * @return String 
 */
	public String getFieldList() {
		return fieldList;
	}
/**
 * 
 * @param fieldList 
 */
	public void setFieldList(String fieldList) {
		this.fieldList = fieldList;
	}

}
