package com.lexmark.reportScheduler.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.lexmark.domain.ReportParameters;
import com.lexmark.enums.ReportParameterTypeEnum;

public  class ReportParameterUrlGenerator {
	private static final String PROMPTEX = "&promptex-";
	private List<ReportParameters> paramList;
	public ReportParameterUrlGenerator(List<ReportParameters> params) {
		this.paramList = params;
	}
	
	public String generate() {
		StringBuilder sbUrl = new StringBuilder();
		for(ReportParameters param: paramList) {
			if(param.getValue()== null ||param.getValue().isEmpty()) {
				continue;
			}
			sbUrl.append("&promptex-");
			sbUrl.append(param.getName());
			sbUrl.append("=");
			sbUrl.append(getEncodedValue(param.getValue(), param.getDataType()));
		}
		return sbUrl.toString();
	}
	
	private String getEncodedValue(String value, String type) {
		StringBuilder sbValue = new StringBuilder();
		if(ReportParameterTypeEnum.DATE.getType().equalsIgnoreCase(type)) {
			sbValue.append("\"DATE(")
				.append(value)
				.append(")\"");
		} else {
			sbValue.append("\"")
				.append(value)
				.append("\"");
		}
		
		String url = sbValue.toString(); 
		String encodedUrl = url; 
		try { 
			encodedUrl = URLEncoder.encode(url, "UTF-8");
		}catch(UnsupportedEncodingException ex) {
			throw new RuntimeException(ex);
		}
		return encodedUrl;
	}
}