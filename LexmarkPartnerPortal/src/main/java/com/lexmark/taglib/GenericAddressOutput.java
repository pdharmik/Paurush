/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: GenericAddressOutput.java
 * Package     		: com.lexmark.taglib
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 *
 */
package com.lexmark.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.lexmark.domain.GenericAddress;


/**
 * @author wipro 
 * @version 2.1 
 */
public class GenericAddressOutput extends TagSupport {

	private static final long serialVersionUID = -3206895260532209578L;

	private GenericAddress value;
	/**Added for MPS 2.1 this field is 
	 * used to display address in different format as 
	 * business needs this particular format
	 * */
	private boolean displayInDivs;

	/**
	 * @return int 
	 * @throws JspException 
	 */
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		if (value == null){
			return SKIP_BODY;
		}
		/*
		 * changes MPS 2.1 added for displaying address
		 * in constant format.
		 * */
		StringBuffer fragment = new StringBuffer();
		if(displayInDivs){
			displayAddressInDivs(fragment);
		}else{
			
			fragment.append(value.getAddressLine1()).append("<br>");
			if (StringUtils.isNotEmpty(value.getAddressLine2())){
				fragment.append(value.getAddressLine2()).append("<br>");
			}
			if (StringUtils.isNotEmpty(value.getAddressLine3())){
				fragment.append(value.getAddressLine3()).append("<br>");
			}
			if(StringUtils.isNotEmpty(value.getCity())){
				fragment.append(value.getCity()).append(",&nbsp;");
			}

			if (StringUtils.isNotEmpty(value.getState())) {
				fragment.append(value.getState());
				fragment.append(",&nbsp;");
			} else {
				fragment.append(value.getProvince());
				fragment.append(",&nbsp;");
			}
			
			if(StringUtils.isNotEmpty(value.getCountry())){
				fragment.append(value.getCountry()).append(",&nbsp;");
			}
			
			fragment.append(value.getPostalCode());
			
		}
		
		

		try {
			out.print(fragment.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}

		return SKIP_BODY;
	}

	/**
	 * This method will append the address format in Divs
	 * Requirement for MPS 2.1
	 * @param fragment 
	 */
	private void displayAddressInDivs(StringBuffer fragment){
		
		
		fragment.append("<div>").append(getBeanValue(value.getStoreFrontName())).append("</div>");
		fragment.append("<div>").append(getBeanValue(value.getAddressLine1())).append("</div>");
		
		if (StringUtils.isNotBlank(value.getOfficeNumber())){
			fragment.append("<div>").append(value.getOfficeNumber()).append("</div>");
		}
		
		if (StringUtils.isNotBlank(value.getAddressLine2())){
			fragment.append("<div>").append(value.getAddressLine2()).append("</div>");
		}
		

		fragment.append("<div>");
		if(StringUtils.isNotBlank(value.getCity())){
			fragment.append(value.getCity());
		}
		if(StringUtils.isNotBlank(value.getCounty())){
			fragment.append(",&nbsp;").append(value.getCounty());
		}
		if (StringUtils.isNotBlank(value.getState())) {
			fragment.append(",&nbsp;").append(value.getState());
			
		}
		if(StringUtils.isNotBlank(value.getProvince())) {
			fragment.append(",&nbsp;").append(value.getProvince());
			
		}
		if(StringUtils.isNotBlank(value.getDistrict())) {
			fragment.append(",&nbsp;").append(value.getDistrict());			
		}
		fragment.append("</div><div>").append(getBeanValue(value.getPostalCode())).append("</div>");
		if(StringUtils.isNotBlank(value.getCountry())){
			fragment.append("<div>").append(value.getCountry()).append("</div>");
		}
		
		
	}
	/**
	 * @param val 
	 * @return string 
	 */
	private String getBeanValue(String val){
		if(StringUtils.isNotBlank(val)){
			return val;
		}else{
			return "";
		}
	}

	/**
	 * @param value 
	 */
	public void setValue(GenericAddress value) {
		this.value = value;
	}

	/**
	 * @param displayInDivs 
	 */
	public void setDisplayInDivs(boolean displayInDivs) {
		this.displayInDivs = displayInDivs;
	}

	/**
	 * @return boolean 
	 */
	public boolean isDisplayInDivs() {
		return displayInDivs;
	}

}
