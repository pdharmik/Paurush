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
import com.lexmark.framework.logging.LEXLogger;

/**
 * @author wipro
 * @version 2.1
 *
 */

public class GenericAddressOutput extends TagSupport {
	
	private static final LEXLogger LOGGER = LEXLogger.getLEXLogger(GenericAddressOutput.class);

	private static final long serialVersionUID = -3206895260532209578L;

	private GenericAddress value = null;
	
	private String addressString;//This value will be set when commentFieldAddress is true.
	private String pageContextAttr;
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
	
	public void genenarteAddressHTML(StringBuilder fragment){
		if (StringUtils.isNotBlank(value.getAddressName())){
			fragment.append("<div>").append(value.getAddressName()).append("</div>");
		}
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
					LOGGER.debug("Illegal Access Exception ");
				} catch (InvocationTargetException e) {
					LOGGER.debug("Invocation Target Exception ");
				} catch (NoSuchMethodException e) {
					LOGGER.debug("No Such Method Exception ");
				}				
		}
		pageContext.setAttribute(pageContextAttr, value);
		
	}
	public void setValue(GenericAddress value) {
		this.value = value;
	}
	

	public void setAddressString(String addressString) {
		this.addressString = addressString;
	}

	public String getAddressString() {
		return addressString;
	}

	public void setPageContextAttr(String pageContextAttr) {
		this.pageContextAttr = pageContextAttr;
	}

	public String getPageContextAttr() {
		return pageContextAttr;
	}
	

}
