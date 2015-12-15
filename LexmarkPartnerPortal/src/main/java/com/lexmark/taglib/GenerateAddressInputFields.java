/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: GenerateAddressInputFields.java
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
import java.lang.reflect.InvocationTargetException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import com.lexmark.domain.GenericAddress;
import com.lexmark.framework.logging.LEXLogger;

/**
 * @author Wipro 
 * @version 2.1 
 */
public class GenerateAddressInputFields extends TagSupport{
	private static final String[] addressFields=
	{"addressId","addressName","addressLine1","addressLine2",
	"storeFrontName","city","state","province",
	"postalCode","country","county","officeNumber",
	"stateCode","stateFullName","district","region",
	"latitude","longitude","savedErrorMessage","countryISOCode",
	"isAddressCleansed"};

	private static final String INPUT_TAG_START_NAME="<input type=\"text\" name=\"";
	private static final String QUOTE="\"";
	private static final String INPUT_TAG_END="/>";
	private static final String VALUE_START=" value=\"";
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(GenerateAddressInputFields.class);  
	private static final long serialVersionUID = 1L;
	
	private GenericAddress value ;
	private String index;
	private String preName;
	private String postName;
	

	
	/**
	 * @return int 
	 * @throws JspException 
	 */
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		
	
		StringBuilder fragment = new StringBuilder();
		for(String fieldName:addressFields){
			fragment.append(INPUT_TAG_START_NAME).append(getPreName()).append(getIndex()).append(getPostName()).append(fieldName).append(QUOTE);
			if(value!=null){
				fragment.append(VALUE_START).append(readProperty(value, fieldName)).append(QUOTE);
			}else{
				
				if(fieldName.equalsIgnoreCase("isAddressCleansed")){
					fragment.append(VALUE_START).append("false").append(QUOTE);
				}else{
					fragment.append(VALUE_START).append(QUOTE);
				}
			}
			fragment.append(INPUT_TAG_END).append("</br>");
			
		}
		

		try {
			out.print(fragment.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}

		return SKIP_BODY;
	}
	/**
	 * @param target 
	 * @param property 
	 * @return valueObject 
	 * */
	private String readProperty(Object target,String property){
		String valueObject="";
		try {
			Object valueProp=PropertyUtils.getProperty(target, property);
			if(valueProp != null){
				if(valueProp instanceof String){
					valueObject=StringUtils.isNotBlank((String)valueProp)==true?(String)valueProp:"";
				}else{
					valueObject=valueProp.toString();
				}
			}
		} catch (IllegalAccessException e) {
			valueObject="";
			LOGGER.error("error occured while reading property "+property+" message ="+e.getMessage());
		} catch (InvocationTargetException e) {
			valueObject="";
			LOGGER.error("error occured while reading property "+property+" message ="+e.getMessage());
		} catch (NoSuchMethodException e) {
			valueObject="";
			LOGGER.error("error occured while reading property "+property+" message ="+e.getMessage());
		}
		return valueObject;
	}
	
	/**
	 * @param value 
	 * */
	public void setValue(GenericAddress value) {
		this.value = value;
	}
	/**
	 * @return value 
	 * */
	public GenericAddress getValue() {
		return value;
	}
	/**
	 * @param index 
	 * */
	public void setIndex(String index) {
		this.index = index;
	}
	/**
	 * @return index 
	 * */
	public String getIndex() {
		return index;
	}
	/**
	 * @param preName 
	 * */
	public void setPreName(String preName) {
		this.preName = preName;
	}
	/**
	 * @return preName 
	 * */
	public String getPreName() {
		return preName;
	}
	/**
	 * @param postName 
	 * */
	public void setPostName(String postName) {
		this.postName = postName;
	}
	/**
	 * @return postName 
	 * */
	public String getPostName() {
		return postName;
	}
	

}
