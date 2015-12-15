package com.lexmark.services.reports.bean;

import java.util.HashMap;

public class DocumentInformation {
	
	protected String drawerId;
	protected String field1;
	protected String field2;
	protected String field3;
	protected String field4;
	protected String field5;
	protected String Type;
	protected HashMap<String, String> propertyMap = new HashMap<String, String>();
	protected byte[] fileContentInBytes;
	
	public String getDrawerId() {
		return drawerId;
	}

	public void setDrawerId(String drawerId) {
		this.drawerId = drawerId;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public byte[] getFileContentInBytes() {
		return fileContentInBytes;
	}

	public void setFileContentInBytes(byte[] fileContentInBytes) {
		this.fileContentInBytes = fileContentInBytes;
	}

	public HashMap<String, String> getPropertyMap() {
		return propertyMap;
	}

	public void setPropertyMap(HashMap<String, String> propertyMap) {
		this.propertyMap = propertyMap;
	}

		
}
