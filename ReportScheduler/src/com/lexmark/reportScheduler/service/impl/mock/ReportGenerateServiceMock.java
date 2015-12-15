package com.lexmark.reportScheduler.service.impl.mock;

import com.lexmark.domain.ReportJob;
import com.lexmark.reportScheduler.service.ReportGenerateService;

public class ReportGenerateServiceMock implements ReportGenerateService {

	@Override
	public boolean generate(ReportJob job) {
		return false;
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] getReportContent() {
		byte[]  content = new byte[1024];
		for(int i=0; i< 1024; i++) {
			content[i] = 1;
		}
		return content;
	}
}
