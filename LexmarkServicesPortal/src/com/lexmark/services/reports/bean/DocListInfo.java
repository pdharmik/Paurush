package com.lexmark.services.reports.bean;

public class DocListInfo {	
		
	private String definitionId;		
	private String drawerName;
	private String docType;	   
	private String propertyList;
	private String fieldList;

	public String getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(String propertyList) {
		this.propertyList = propertyList;
	}

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public String getDrawerName() {
		return drawerName;
	}

	public void setDrawerName(String drawerName) {
		this.drawerName = drawerName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getFieldList() {
		return fieldList;
	}

	public void setFieldList(String fieldList) {
		this.fieldList = fieldList;
	}
	
}
