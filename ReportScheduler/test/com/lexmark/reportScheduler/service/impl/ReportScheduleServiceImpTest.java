package com.lexmark.reportScheduler.service.impl;

import static org.junit.Assert.fail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.lexmark.constants.ReportFileType;
import com.lexmark.constants.ReportSelectionFlagEnum;
import com.lexmark.domain.ReportJob;
import com.lexmark.domain.ReportParameters;
import com.lexmark.service.impl.real.jdbc.DBConnection;
import com.lexmark.service.impl.real.jdbc.DBParameter;
import com.lexmark.service.impl.real.jdbc.Int32DBParamter;
import com.lexmark.service.impl.real.jdbc.ReportScheduleServiceImp;
import com.lexmark.service.impl.real.jdbc.StringDBParameter;
import com.lexmark.util.TimezoneUtil;

public class ReportScheduleServiceImpTest {

	@Test
	public void testGetFileDateLink() {
		ReportScheduleServiceImp impl = new ReportScheduleServiceImp();
		String link = impl.getFileDataLink();
		
		Assert.assertNotNull(link);
		
	}

	@Test
	public void testGetJobBatchId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetQulifiedReportJobs() {
		ReportScheduleServiceImp impl = new ReportScheduleServiceImp();
		List<ReportJob> list = impl.getQulifiedReportJobs(new Date(), new Date(),  ReportSelectionFlagEnum.FAILED.getType(), null);
		
//		list = impl.getQulifiedReportJobs(new Date(), new Date(),  ReportSelectionFlagEnum.FAILED.getType(), null);
//		
//		list = impl.getQulifiedReportJobs(new Date(), new Date(),  ReportSelectionFlagEnum.NORMAL.getType(), null);
//		
//		list = impl.getQulifiedReportJobs(new Date(), new Date(),  ReportSelectionFlagEnum.FORCE.getType(), null);
//		
//		list = impl.getQulifiedReportJobs(new Date(), new Date(),  ReportSelectionFlagEnum.UNRUN.getType(), null);
//		
//		list = impl.getQulifiedReportJobs(new Date(), new Date(),  ReportSelectionFlagEnum.SUBMITNOW.getType(), null);
		
		list = impl.getQulifiedReportJobs(new Date(), new Date(),  ReportSelectionFlagEnum.SUBMITNOW.getType(), "1014300");
			
		
		
	}

	@Test
	public void testGetListForThread() {
		ReportScheduleServiceImp impl = new ReportScheduleServiceImp();
		List<ReportJob> list = impl.getReportJobsForThread("1002400", 0);
		Assert.assertEquals(2,  list.size());
	}
	@Test
	public void testGetReportJobsByJobBatchId() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertReportLog() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testInsertRunLogParams() {
		ReportJob job = new ReportJob();
		job.setJobRunLogId(100L);
		List<ReportParameters> list = job.getParameterList();
		ReportParameters p = new ReportParameters();
		p.setDataType("DATE");
		p.setName("beginDate");
		p.setValue("2010,10,13");
		list.add(p);
		
		p = new ReportParameters();
		p.setDataType("STRING");
		p.setName("country");
		p.setValue("this is a test");
		list.add(p);
		
		p = new ReportParameters();
		p.setDataType("BOOLEAN");
		p.setName("isSchedule");
		p.setValue("FALSE");
		list.add(p);
		ReportScheduleServiceImp impl = new ReportScheduleServiceImp();
		impl.insertRunLogParams(job);
	}
	
	@Test
	public void testGetReportSourceId() {
		ReportScheduleServiceImp impl = new ReportScheduleServiceImp();
		String soruceId = impl.getReportSourceId(1);
	}
	
	@Test
	public void testbuilding()  {
		ReportScheduleServiceImp impl = new ReportScheduleServiceImp();
	//	impl.updateJobLoadingStatus(1004200L, 123456L);
	//	impl.updateJobLoadedStatus(1004300L, false);
	//	impl.updateJobLoadedStatus(1004000L, true);
		
	//	impl.loadReportMetaData(1004200L);
		
		impl.logJobErrorMessage(1014200L, "message");
	}
	
	@Test
	public void testDateInsert() throws Exception {
		Integer i = 2;
		
		Calendar c = Calendar.getInstance(TimezoneUtil.getTimeBaseonTimezone(+8));
		Date end = c.getTime();
		
		c.add(Calendar.DATE, -2);
		Date begin = c.getTime();

		String b = formatDateTime(begin,  TimeZone.getDefault());
		
		String e = formatDateTime(end,  TimeZone.getDefault());
		
		
		
		
		insertRecords(i,  begin, end);
		
		getReportJobsForThread(i);
		
	}
	
	@Test
	public void testDateSql() throws Exception {
		//TimeZone.getTimeZone("GMT");
		getReportJobsForThread(2);
	}
	
	private static final String DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
	public static String formatDateTime(Date date, TimeZone timeZone) {
		SimpleDateFormat datetimeFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		datetimeFormatter.setTimeZone(timeZone);
		return datetimeFormatter.format(date);
	}
	
	private String SELECT_SQL = "SELECT * FROM TABLE1 WHERE REPORT_SCHEDULE_ID = ?";
	private void  getReportJobsForThread(Integer id) {
		List<ReportJob> jobs = new ArrayList<ReportJob>();
		
		DBParameter[] params = new DBParameter[1]; 
		int i=0;
		params[i++]= new Int32DBParamter(id, Types.INTEGER);
		
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.queryPrepareStatement(SELECT_SQL, params);
			while(dbconn.rs.next()) {
				Date begin = dbconn.rs.getTimestamp("SCHEDULE_EFFECTIVE_DATE");
				String b = formatDateTime(begin,  TimeZone.getDefault());
				
				Date end = dbconn.rs.getTimestamp("SCHEDULE_EXPIRATION_DATE");
				String e = formatDateTime(end,  TimeZone.getDefault());
				
			}
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}
	}
	
	
	private String insert_sql = "INSERT INTO TABLE1(REPORT_SCHEDULE_ID, SCHEDULE_EFFECTIVE_DATE, SCHEDULE_EXPIRATION_DATE) VALUES (?, ?, ?) ";
	
	private void insertRecords(Integer  id, Date begin, Date end) throws Exception{
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open(false);
			PreparedStatement insertStatement = dbconn.prepareStatement(insert_sql);
	    		insertStatement.setInt(1, id);
	    		insertStatement.setTimestamp(2, Date2SQLDate(begin));
	    		insertStatement.setTimestamp(3, Date2SQLDate(end));
	    		insertStatement.execute();
	    	dbconn.commit();
	    	insertStatement.close();
		} finally {
	    	dbconn.close();
	    }
	    
	}
	
	private java.sql.Timestamp  Date2SQLDate(Date d) {
		return new java.sql.Timestamp(d.getTime());
	}
	
	
	@Test
	public void testPreferedEndTime() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		ReportScheduleServiceImp impl = new ReportScheduleServiceImp();
//		Date b = Calendar.getInstance().getTime();
		Date b = impl.getPreferEndTime(0);
		Date p = impl.getPreferEndTime(9);
		String strB = formatDateTime(b,  TimeZone.getTimeZone("GMT"));
		
		
		String strP = formatDateTime(p,  TimeZone.getTimeZone("GMT"));
		
		
	}
	


}
