package com.lexmark.taglib;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.GenericAddress;

public class ConvertToGenericAddress extends TagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String addressString;
	

	private String attrName;
	
	public int doStartTag() throws JspException {
		if(StringUtils.isBlank(addressString)){
			return SKIP_BODY;
		}
		pageContext.setAttribute(attrName, readAddressFromCommentField());
		return SKIP_BODY;
	}
	
	public GenericAddress readAddressFromCommentField(){
		GenericAddress address = new GenericAddress();
		String[] splitAddress=addressString.split("\\|");
		int i=0;
		for(String param:LexmarkConstants.addressArr){
			if(i==splitAddress.length){
				break;
			}
				try {
					
					PropertyUtils.setProperty(address, param, splitAddress[i++]);
				} catch (IllegalAccessException e) {
				} catch (InvocationTargetException e) {
				} catch (NoSuchMethodException e) {
				}				
		}
		return address;
		
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	
	
	public void setAddressString(String addressString) {
		this.addressString = addressString;
	}

}
