package com.lexmark.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ReplaceRN extends TagSupport {

	private static final long serialVersionUID = -3206895260532209511L;

	private String value = null;

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		String result = value.replaceAll("\r", " ").replaceAll("\n", "<br/>");
		try {
			out.print(result);
		} catch (IOException e) {
			throw new JspException(e);
		}

		return SKIP_BODY;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
