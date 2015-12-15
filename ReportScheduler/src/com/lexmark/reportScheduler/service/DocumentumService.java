package com.lexmark.reportScheduler.service;

import com.lexmark.domain.ReportProperties;

public interface DocumentumService {
	
	public String authenticate(); 
	public String createDocument(ReportProperties reportProperties,  byte[] content);
}
