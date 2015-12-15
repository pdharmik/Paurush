package com.lexmark.reportScheduler.service;

import com.lexmark.domain.ReportJob;

public interface ReportGenerateService {
	/*
	 * 
	 */
	public boolean generate(ReportJob job) ;

	/*
	 * 
	 */
	public byte[] getReportContent();
	
}
