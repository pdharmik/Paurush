package com.lexmark.domain;

import java.io.Serializable;

public class UserFieldsViewSetting implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1569984543528543332L;
	private String userNumber;
	private String fieldsDisplayed;
	private String prefId;
	private String portalName;//Internal or ServicesPortal
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
	/**
	 * @param portalName the portalName to set
	 */
	public void setPortalName(String portalName) {
		this.portalName = portalName;
	}
	/**
	 * @return the portalName
	 */
	public String getPortalName() {
		return portalName;
	}

	public String toString(){
		return String.format("[userNumber = %s , fieldsDisplayed = %s , prefId = %s , portalName = %s]",this.userNumber,this.fieldsDisplayed,this.prefId,this.portalName);
	}
}
