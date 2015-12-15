package com.lexmark.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.lexmark.domain.GenericAddress;
import com.lexmark.util.ServiceStatusUtil;

public class RequestActionLink extends TagSupport {

	private static final long serialVersionUID = 8301092891556196582L;
	private String activityType;
	private String debriefStatus;
	private String value;
	private String type;

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		if (value == null)
			return SKIP_BODY;

		if("update".equalsIgnoreCase(type)){
			if(ServiceStatusUtil.isRequestAbleToBeUpdate(value,debriefStatus,activityType)){
				return EVAL_BODY_INCLUDE;
			}
		}else if("debrief".equalsIgnoreCase(type)){
			if(ServiceStatusUtil.isRequestAbleToBeDebrief(value,debriefStatus)){
				return EVAL_BODY_INCLUDE;
			}
		}
		return SKIP_BODY;
	}


	public void setValue(String value) {
		this.value = value;
	}

	public void setType(String type) {
		this.type = type;
	}


	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}


	public void setDebriefStatus(String debriefStatus) {
		this.debriefStatus = debriefStatus;
	}

}
