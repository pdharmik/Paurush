package com.lexmark.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;

import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.util.ErrorMessageUtil;

public class ExceptionUtil {

	public static String getLocalizedExceptionMessage(String msgKey, Locale locale, Exception e){
		String errorMessage = ErrorMessageUtil.getErrorMessage(LexmarkPPConstants.ERROR_MESSAGE_BUNDLE, msgKey, null, locale);
		String sysErrorMsg = getTrace(e);
		return(errorMessage+"^"+sysErrorMsg.replaceAll("\n", "<br/>").replaceAll("\r", ""));
	}

	private static String getTrace(Exception e) {
		StringWriter writer = new StringWriter(1024);
		PrintWriter pWriter = new PrintWriter(writer);
		e.printStackTrace(pWriter);
		String msg = writer.getBuffer().toString();
		return msg.substring(0, msg.length() > 1024 ? 1024 : msg.length());
	}
}
