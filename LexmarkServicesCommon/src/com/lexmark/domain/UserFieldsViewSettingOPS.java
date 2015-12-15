package com.lexmark.domain;

import java.io.Serializable;

public class UserFieldsViewSettingOPS implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1569984543528543332L;
	private String userNumber;
	private String fieldsDisplayed;
	private String prefId;
	
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public void setFieldsDisplayed(String fieldsDisplayed) {
		this.fieldsDisplayed = fieldsDisplayed;
	}
	public String getFieldsDisplayed() {
		return fieldsDisplayed;
	}
	/**
	 * @param prefId the prefId to set
	 */
	public void setPrefId(String prefId) {
		this.prefId = prefId;
	}
	/**
	 * @return the prefId
	 */
	public String getPrefId() {
		return prefId;
	}

	
}
