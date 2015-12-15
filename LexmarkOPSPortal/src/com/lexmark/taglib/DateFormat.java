package com.lexmark.taglib;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.util.DateUtil;

public class DateFormat extends TagSupport {

	private static final long serialVersionUID = -3206895260532209578L;
	private static final BigDecimal millsecondsInHour = new BigDecimal(LexmarkConstants.MILLISECONDS_IN_HOUR);

	private Date value = null;
	private String showTime = "false";
	private String showSecond = "true";
	private float timezoneOffset = 0;

	public int doStartTag() throws JspException {
		if (value == null) {
			return SKIP_BODY;
		}

		ServletRequest request = pageContext.getRequest();
		String dateFormat = DateUtil.getDateFormatByLang(request.getLocale().getLanguage());

		if ("true".equals(this.showTime) && "true".equals(this.showSecond)) {
			dateFormat = dateFormat + " HH:mm:ss";
		} else if ("true".equals(this.showTime) && "false".equals(this.showSecond)) {
			dateFormat = dateFormat + " HH:mm";
		}

		JspWriter out = pageContext.getOut();
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
			out.print(simpleDateFormat.format(new Date(value.getTime() - 
					new BigDecimal(timezoneOffset).multiply(millsecondsInHour).intValue())));
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public void setShowSecond(String showSecond) {
		this.showSecond = showSecond;
	}

	public void setTimezoneOffset(float timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}
}
