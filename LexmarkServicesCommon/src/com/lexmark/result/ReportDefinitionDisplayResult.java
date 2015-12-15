package com.lexmark.result;

import java.io.Serializable;

public class ReportDefinitionDisplayResult  implements Serializable{
	
	private String definitionId;
	private String definitionName;
	private String definitionType;
	private String definitionDescription;
	private String definitionURL;
	
	
	public String getDefinitionId() {
		return definitionId;
	}
	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}
	public String getDefinitionName() {
		return definitionName;
	}
	public void setDefinitionName(String definitionName) {
		this.definitionName = definitionName;
	}
	public String getDefinitionType() {
		return definitionType;
	}
	public void setDefinitionType(String definitionType) {
		this.definitionType = definitionType;
	}
	public String getDefinitionDescription() {
		return definitionDescription;
	}
	public void setDefinitionDescription(String definitionDescription) {
		this.definitionDescription = definitionDescription;
	}
	public String getDefinitionURL() {
		return definitionURL;
	}
	public void setDefinitionURL(String definitionURL) {
		this.definitionURL = definitionURL;
	}

}
