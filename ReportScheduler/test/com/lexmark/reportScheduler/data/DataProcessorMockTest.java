package com.lexmark.reportScheduler.data;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lexmark.service.impl.real.jdbc.DBConnection;

public class DataProcessorMockTest {

	@Test
	public void testGetQulifiedReportJobs() throws Exception {
		String sql = "select * from  report_definition";
		DBConnection dbconn= new DBConnection();
		dbconn.open();
		dbconn.openQueryRS(sql);

		List<String> mdmIds = new ArrayList<String>();

		while(dbconn.rs.next()) {
			String id = dbconn.rs.getString("MDM_ID");
			mdmIds.add(id);
		}
		dbconn.closeQueryRS();
		dbconn.close();
		for(String s: mdmIds) {
			
		}
		
	}

	@Test
	public void testGetJobBatchId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReportJobsByJobBatchId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFileDateLink() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertReportLog() {
		fail("Not yet implemented");
	}



}
