package com.lexmark.services.form;

import java.io.Serializable;

public class RunReportNowForm extends BaseForm implements Serializable {

	private static final long serialVersionUID = -6772937112370122794L;
	private String roleCategoryId;
	private String docDefinitionId;
	/**
	 * 
	 * @return String 
	 */ 
	public String getRoleCategoryId() {
		return roleCategoryId;
	}
	/**
	 * 
	 * @param roleCategoryId 
	 */ 
	public void setRoleCategoryId(String roleCategoryId) {
		this.roleCategoryId = roleCategoryId;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getDocDefinitionId() {
		return docDefinitionId;
	}
	/**
	 * 
	 * @param docDefinitionId 
	 */
	public void setDocDefinitionId(String docDefinitionId) {
		this.docDefinitionId = docDefinitionId;
	}
}
