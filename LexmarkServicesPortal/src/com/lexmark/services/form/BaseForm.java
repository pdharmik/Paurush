package com.lexmark.services.form;

import java.io.Serializable;

import com.lexmark.services.util.PortalSessionUtil;
import javax.portlet.PortletRequest;

public class BaseForm implements Serializable {
	private static final long serialVersionUID = -1381572566148315722L;
	private Long submitToken;
	/**
	 * 
	 * @return Long 
	 */
	public Long getSubmitToken() {
		return submitToken;
	}
/**
 * 
 * @param submitToken 
 */
	public void setSubmitToken(Long submitToken) {
		this.submitToken = submitToken;
	}
	/**
	 * 
	 * @param request 
	 */
	public void refreshSubmitToken(PortletRequest request){
		submitToken = PortalSessionUtil.createSubmitToken(request.getPortletSession());		
	}
}