package com.lexmark.reportScheduler.server;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.lexmark.constants.ReportFileType;
import com.lexmark.domain.ReportJob;
import com.lexmark.domain.ReportParameters;
import com.lexmark.reportScheduler.service.impl.ReportGenerateServiceImpl;
import com.lexmark.reportScheduler.service.impl.ReportParameterUrlGenerator;

public class ReportGeneratorTest {

	@Test
	public void testGenerate() throws Exception {
//		String url = "https://reporting-prod.lexmark.com/CrystalReports/viewrpt.cwr?id=240943&sReportMode=printlayout&apsuser=lsportal&apspassword=e%40Y$%23aR2c&apsauthtype=secLDAP&cmd=EXPORT&EXPORT_FMT=U2FPDF:0&promptex-AMAssetAccount=%22Lexmark%20International%20Inc%22&promptex-AMAssetAccountSite=%22MPS-US%22&promptex-StartDate=Date(2009,08,01)&promptex-EndDate=Date(2010,09,01)&promptex-lang=%22EN%22&promptex-cntry=%22USA%22";
		ReportGenerateServiceImpl generator = new ReportGenerateServiceImpl();
		ReportJob job = buildReportJob();
		generator.generate(job);
		byte[] content = generator.getReportContent();
		Assert.assertNotNull(content);
	}
	

	@Test
	public void testDownload() throws Exception {
		String url = "https://reporting-qa.lexmark.com/CrystalReports/viewrpt.cwr?id=2722324&sReportMode=printlayout&apsuser=lsportal&apspassword=e%40Y$%23aR2c&apsauthtype=secLDAP&cmd=EXPORT&EXPORT_FMT=U2FPDF:0&sReportMode=printlayout&promptex-cntry=%22US%22&promptex-CHL=%22NOVALUE%22&promptex-Agreement_Type=%22ALL%3DALL%2CCSS%3DCSS%2CMPS%3DMPS%22&promptex-MDM_LEVEL=%22Legal%22&promptex-MDM_ID=%2266538%22&promptex-lang=%22en_US%22";
		ReportGenerateServiceImpl generator = new ReportGenerateServiceImpl();
		generator.downloadReportContent(url);
		byte[] content = generator.getReportContent();
		Assert.assertNotNull(content);
	}
	
	
	@Test
	public void testParameter() {
		
		ReportJob job = buildReportJob();
		
		ReportParameterUrlGenerator generator = new ReportParameterUrlGenerator(job.getParameterList());
		String url = generator.generate();
		
	}
	
	private ReportJob buildReportJob() {
//		&promptex-AMAssetAccount=%22Lexmark%20International%20Inc%22
//		&promptex-AMAssetAccountSite=%22MPS-US%22
//		&promptex-StartDate=Date(2009,08,01)
//		&promptex-EndDate=Date(2010,09,01)
//		&promptex-lang=%22EN%22
//		&promptex-cntry=%22USA%22
		
		ReportJob job = new ReportJob();
		job.setJobRunLogId(1L);
		job.setReportSourceId("240943");
		job.setFileType(ReportFileType.instance("PDF"));
		List<ReportParameters> list = job.getParameterList();

		ReportParameters p = new ReportParameters();
		p = new ReportParameters();
		p.setDataType("STRING");
		p.setName("AMAssetAccount");
		p.setValue("Lexmark International Inc");
		list.add(p);

		p = new ReportParameters();
		p.setDataType("STRING");
		p.setName("AMAssetAccountSite");
		p.setValue("MPS-US");
		list.add(p);
		
		p = new ReportParameters();
		p.setDataType("DATE");
		p.setName("StartDate");
		p.setValue("2009,10,13");
		list.add(p);
		
		p = new ReportParameters();
		p.setDataType("DATE");
		p.setName("EndDate");
		p.setValue("2010,10,13");
		list.add(p);
		
		p = new ReportParameters();
		p.setDataType("STRING");
		p.setName("lang");
		p.setValue("EN");
		list.add(p);

		p = new ReportParameters();
		p.setDataType("STRING");
		p.setName("cntry");
		p.setValue("USA");
		list.add(p);

		return job;
	}

}
