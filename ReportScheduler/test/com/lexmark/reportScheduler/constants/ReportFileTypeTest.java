package com.lexmark.reportScheduler.constants;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.lexmark.constants.ReportFileType;

public class ReportFileTypeTest {

	@Test
	public void testGetAllReportFileType() {
		List<ReportFileType> list = ReportFileType.getAllReportFileType();
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void testGetBoxiCode() {
		List<ReportFileType> list = ReportFileType.getAllReportFileType();
		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 2);
		for(ReportFileType type : list) {
			if(type.getType().equals("PDF")) {
				String code = type.getBoxiCode();
				Assert.assertEquals("U2FPDF:0", code);
			}
		}
	}

}
