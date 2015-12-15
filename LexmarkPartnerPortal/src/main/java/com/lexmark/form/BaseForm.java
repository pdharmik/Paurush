package com.lexmark.form;

import java.io.Serializable;
import javax.portlet.PortletRequest;
import com.lexmark.util.PortalSessionUtil;

public class BaseForm implements Serializable {
	private static final long serialVersionUID = -1381572566148315722L;
	private Long submitToken;
	
	public Long getSubmitToken() {
		return submitToken;
	}

	public void setSubmitToken(Long submitToken) {
		this.submitToken = submitToken;
	}
	
	public void refreshSubmitToken(PortletRequest request){
		submitToken = PortalSessionUtil.createSubmitToken(request);		
	}
}