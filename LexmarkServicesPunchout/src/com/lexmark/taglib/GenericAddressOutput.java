package com.lexmark.taglib;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.GenericAddress;

/**
 * @author wipro
 * @version 2.1
 *
 */

public class GenericAddressOutput extends TagSupport {

	private static final long serialVersionUID = -3206895260532209578L;

	private GenericAddress value;
	
	private String addressString;//This value will be set when commentFieldAddress is true.
	private String pageContextAttr;
	/**
	 * @return int 
	 * @throws JspException 
	 */
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		
		StringBuilder fragment = new StringBuilder();
		if(StringUtils.isNotBlank(addressString)){
			readAddressFromCommentField();
		}
		if (value == null){
			return SKIP_BODY;
		}
		genenarteAddressHTML(fragment);

		try {
			out.print(fragment.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}

		return SKIP_BODY;
	}
	
	/**
	 * @param fragment 
	 */
	public void genenarteAddressHTML(StringBuilder fragment){
		
		fragment.append("<div>").append(value.getAddressLine1()).append("</div>");
		
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
		fragment.append("</div><div>").append(StringUtils.isNotBlank(value.getPostalCode())==true?value.getPostalCode():"").append("</div>");
		if(StringUtils.isNotBlank(value.getCountry())){
			fragment.append("<div>").append(value.getCountry()).append("</div>");
		}
	}
	/**
	 *  Read Address
	 */
	public void readAddressFromCommentField(){
		value = new GenericAddress();
		String[] splitAddress=addressString.split("\\|");
		int i=0;
		for(String param:LexmarkConstants.addressArr){
			if(i==splitAddress.length){
				break;
			}
				try {
					
					PropertyUtils.setProperty(value, param, splitAddress[i++]);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}				
		}
		pageContext.setAttribute(pageContextAttr, value);
		
	}
	/**
	 * @param value 
	 */
	public void setValue(GenericAddress value) {
		this.value = value;
	}
	

	/**
	 * @param addressString 
	 */
	public void setAddressString(String addressString) {
		this.addressString = addressString;
	}

	/**
	 * @return String 
	 */
	public String getAddressString() {
		return addressString;
	}

	/**
	 * @param pageContextAttr 
	 */
	public void setPageContextAttr(String pageContextAttr) {
		this.pageContextAttr = pageContextAttr;
	}

	/**
	 * @return String 
	 */
	public String getPageContextAttr() {
		return pageContextAttr;
	}
	

}
