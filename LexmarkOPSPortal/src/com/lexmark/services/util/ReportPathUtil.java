package com.lexmark.services.util;

import java.util.Locale;

import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.URLEncryptUtil;

public class ReportPathUtil {

	public static String getReportFullURL(String filePath){
		String server = PropertiesMessageUtil.getPropertyMessage("com.lexmark.services.resources.reportURL", 
																 "report_server", Locale.US);
		
		if(!server.endsWith("/")){
			server = server + "/";
		}
		
		if(filePath.startsWith("/")){
			filePath = filePath.substring(1);
		}
		
		String reportURL = server + filePath;
		
		return URLEncryptUtil.encrypt(reportURL);
	}
}
