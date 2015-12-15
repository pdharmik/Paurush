package com.lexmark.service.impl.real.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.constants.JobTypeEnum;
import com.lexmark.constants.ReportConstants;
import com.lexmark.constants.ReportFileType;
import com.lexmark.constants.ReportSelectionFlagEnum;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.domain.ReportJob;
import com.lexmark.domain.ReportNotifierTO;
import com.lexmark.domain.ReportParameters;
import com.lexmark.domain.ReportProperties;
import com.lexmark.domain.ServicesUser;
import com.lexmark.enums.ReportParameterTypeEnum;
import com.lexmark.enums.ReportRunFrequencyEnum;
import com.lexmark.enums.RunLogStatusEnum;
import com.lexmark.service.api.GlobalServiceFacade;
import com.lexmark.service.api.ReportScheduleService;
import com.lexmark.service.api.UserInfoService;
import com.lexmark.util.DBUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.TimezoneUtil;
import com.lexmark.util.report.PropertiesUtil;

public class ReportScheduleServiceImp implements ReportScheduleService {
	private static Logger logger = LogManager.getLogger(ReportScheduleServiceImp.class);
	private static final SimpleDateFormat  FILE_CONTENT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private Map<Integer, Integer>  standardLeadTimes = null;
	private GlobalServiceFacade  globalService;
	private UserInfoService  userInfoService;
	
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}


	public GlobalServiceFacade getGlobalService() {
		return globalService;
	}


	public void setGlobalService(GlobalServiceFacade globalService) {
		this.globalService = globalService;
	}

	private static final String QUERY_ACTIVE_SCHEDULED_REPORT_SCHEDULE = 
		"select rd.MDM_ID, rd.MDM_LEVEL, rs.*, GOAL_FINISHED_ON from report_schedule rs, " +
		"(select report_schedule_id, job_type, max(run_date_time) as GOAL_FINISHED_ON from job_runlog " +
		" where job_type = ''{2}'' and  ( status_code = ''WAITFORPUB'' or status_code = ''PUBLISHOK'') group by report_schedule_id, job_type )  runlog, " +
		"report_definition rd  " + 
		" where rs.report_definition_id = rd.report_definition_id " +
		" and rs.report_schedule_id = runlog.report_schedule_id(+) " +
		" and ( rs.SCHEDULE_EFFECTIVE_DATE IS NULL OR  rs.SCHEDULE_EFFECTIVE_DATE <= to_date(''{0, date,yyyy/MM/dd}'', ''yyyy/mm/dd'') ) " + 
		" and ( rs.SCHEDULE_EXPIRATION_DATE IS NULL OR  rs.SCHEDULE_EXPIRATION_DATE >= to_date(''{1, date,yyyy/MM/dd}'', ''yyyy/mm/dd'') ) " + 
		" and rs.RUN_FREQUENCY <> ''O'' and rd.is_deleted = ''F''";

	private static final String QUERY_ACTIVE_RUNNOW_REPORT_SCHEDULE = 
		"select rd.MDM_ID, rd.MDM_LEVEL, rs.*, GOAL_FINISHED_ON from report_schedule rs, " +
		"(select report_schedule_id, job_type, max(created_dt) as GOAL_FINISHED_ON from job_runlog " +
		" where job_type = 'SUBMITNOW' group by report_schedule_id, job_type )  runlog, " +
		" report_definition rd   where rs.report_definition_id = rd.report_definition_id  and rs.report_schedule_id = runlog.report_schedule_id(+) " + 
		" and rs.RUN_FREQUENCY = 'O'  and rd.is_deleted = 'F' " +
		" and (runlog.GOAL_FINISHED_ON IS NULL OR runlog.GOAL_FINISHED_ON < rs.SCHEDULE_EFFECTIVE_DATE )";

	
	private static final String GET_FILE_DATA_LINK = "select FILE_DATA_LINK_SEQ.nextval as FILE_DATD_LINK from dual";
	private static final String GET_JOB_RUNLOG_ID = "select JOB_RUNLOG_ID_SEQ.nextval as JOB_RUNLOG_ID from dual";
	private static final String GET_JOB_BATCH_ID = "select JOB_BATCH_ID_SEQ.nextval as JOB_BATCH_ID from dual";

	
	private static final String INSERT_JOB_RUNLOG = "INSERT INTO JOB_RUNLOG(JOB_RUNLOG_ID, MDM_ID, MDM_LEVEL, LEGAL_NAME, REPORT_SCHEDULE_ID, " + 
		" OVERDUE_MINUTES, STATUS_CODE, FILE_TYPE, FILE_DATA_LINK, BATCH_ID, THREAD_GROUP, CREATED_DT, RUN_DATE_TIME, LAST_UPDATED_ON, JOB_TYPE) "  
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	// ISLBSACCOUNT added
	private static final String QUERY_JOB_RUNLOG_FOR_THREAD = "SELECT RUNLOG.*, RS.USERNUMBER, RS.RECEPIENT_EMAIL, RD.IS_MDM AS IS_SEND_MDM, RS.ISLBSACCOUNT " + 
		"FROM JOB_RUNLOG RUNLOG, REPORT_SCHEDULE RS, REPORT_DEFINITION RD" +
		" WHERE RS.REPORT_DEFINITION_ID = RD.REPORT_DEFINITION_ID" +
		" AND RUNLOG.REPORT_SCHEDULE_ID = RS.REPORT_SCHEDULE_ID AND STATUS_CODE= 'SELECTED'" +
		" AND BATCH_ID = ?  AND THREAD_GROUP = ? " +
		" ORDER BY OVERDUE_MINUTES DESC, FILE_DATA_LINK";
	
	private static final String UPDATE_BUILDING_STATUS = 
		"UPDATE JOB_RUNLOG SET STATUS_CODE = ?, BUILD_STARTED_ON = ?, LAST_UPDATED_ON = ? WHERE JOB_RUNLOG_ID = ?";

	private static final String UPDATE_BUILDING_URL = 
		"UPDATE JOB_RUNLOG SET CMD_INVOKED = ?  WHERE JOB_RUNLOG_ID = ?";

	private static final String UPDATE_BUILT_STATUS = 
		"UPDATE JOB_RUNLOG SET STATUS_CODE = ?, BUILD_FINISHED_ON = ?,  LAST_UPDATED_ON = ?, BUILD_STATUS_MSG = ?, ERROR_LOG = ?  WHERE JOB_RUNLOG_ID = ?";
	
	private static final String UPDATE_LOADING_STATUS = 
		"UPDATE JOB_RUNLOG SET STATUS_CODE = ?, LOAD_STARTED_ON = ?,  LAST_UPDATED_ON = ?, FILE_SIZE = ? WHERE JOB_RUNLOG_ID = ?";
	
	private static final String UPDATE_LOADED_STATUS = 
		"UPDATE JOB_RUNLOG SET STATUS_CODE = ?, LOAD_FINISHED_ON = ?,  LAST_UPDATED_ON = ?, FILE_OBJECT_ID = ?, LOAD_STATUS_MSG = ?, ERROR_LOG = ? WHERE JOB_RUNLOG_ID = ?";
	
	private static final String UPDATE_WAIT_FOR_PUBLISH_STATUS = 
		"UPDATE JOB_RUNLOG SET STATUS_CODE = ? " +
		"WHERE FILE_DATA_LINK = ? " +
		" AND (SELECT COUNT(*) FROM JOB_RUNLOG WHERE FILE_DATA_LINK = ? AND STATUS_CODE <> ? )=0 ";
	
	
	private static final String LOAD_REPORT_META_DATA = 
		"SELECT RS.COUNTRY,  RL.JOB_RUNLOG_ID, RL.FILE_DATA_LINK, RL.LEGAL_NAME, RL.FILE_TYPE ,RL.LEGAL_NAME," +
		" RL.MDM_ID, RL.MDM_LEVEL, RS.RUN_FREQUENCY,  RS.REPORT_DEFINITION_ID, RS.USERNUMBER, RD.NAME from JOB_RUNLOG RL, " +
		" REPORT_SCHEDULE RS , REPORT_DEFINITION RD WHERE RS.REPORT_SCHEDULE_ID = RL.REPORT_SCHEDULE_ID " +
		" AND  RS.REPORT_DEFINITION_ID = RD.REPORT_DEFINITION_ID AND RL.JOB_RUNLOG_ID = ? ";

	
	private static final String UPDATE_RERUN_FLAG = "UPDATE JOB_RUNLOG SET RERUN_FLAG = 'X' " +
		" WHERE STATUS_CODE IN  ('DEFERRED','LOADERROR','BUILDERROR','SELECTED', 'BUILDSUBMIT','BUILDOK','LOADOK','LOADSUBMIT') " +
		" AND RERUN_FLAG is NULL";
	
	private static final String UPDATE_DEFERRED_STATUS = 
		"UPDATE JOB_RUNLOG SET STATUS_CODE = ?, LAST_UPDATED_ON = ? WHERE JOB_RUNLOG_ID = ?";
	
	private static final String GET_ONE_SPECIFIC_JOB_TO_RERUN = 
		"select rd.MDM_ID, rd.MDM_LEVEL, rs.* from report_schedule rs, job_runlog  runlog, report_definition rd " +  
		" where rs.report_definition_id = rd.report_definition_id  and rs.report_schedule_id = runlog.report_schedule_id " +
		" and runlog.job_runlog_id = ? " ;
	
	private static final String GET_PREVIOUS_ERROR_JOB_TO_RERUN = 
		"select rd.MDM_ID, rd.MDM_LEVEL, rs.* from report_schedule rs, " +
		"(select report_schedule_id, job_type from job_runlog " +
		" where STATUS_CODE IN ('DEFERRED','LOADERROR','BUILDERROR','SELECTED', " +
		" 'BUILDSUBMIT','BUILDOK','LOADOK','LOADSUBMIT') " +
		" AND RERUN_FLAG is NULL group by report_schedule_id, job_type ) runlog, report_definition rd " +  
		" where rs.report_definition_id = rd.report_definition_id   and rd.is_deleted = 'F' " +
		" and rs.report_schedule_id = runlog.report_schedule_id and runlog.job_type = ?";

	private static final String GET_PREVIOUS_FORCE_JOB_TO_RERUN = 
		"select rd.MDM_ID, rd.MDM_LEVEL, rs.* from report_schedule rs, " +
		"(select report_schedule_id, job_type from job_runlog " +
		" where RERUN_FLAG is NULL group by report_schedule_id ) runlog, report_definition rd " +  
		" where rs.report_definition_id = rd.report_definition_id  and rd.is_deleted = 'F' " +
		" and rs.report_schedule_id = runlog.report_schedule_id and runlog.job_type = ?";

	private static final String QUERY_REPORT_DEFINITION_PARAMETERS = "SELECT RS.REPORT_SCHEDULE_ID, RP.* FROM REPORT_SCHEDULE RS ," +
		" REPORT_DEFINITION RD, REPORT_PARAMETER  RP " +
		" WHERE RS.REPORT_DEFINITION_ID = RD.REPORT_DEFINITION_ID " +
		" AND RP.REPORT_DEFINITION_ID = RD.REPORT_DEFINITION_ID " +
		" AND RS.REPORT_SCHEDULE_ID = ?";	

	private static final String QUERY_REPORT_SCHEDULE_PARAMETERS = "SELECT * FROM REPORT_SCHEDULE_PARAMETERS " +
		" WHERE REPORT_SCHEDULE_ID = ?";

	private static final String INSERT_INSTANCE_PARAMETERS = 
		"INSERT INTO REPORT_INSTANCE_PARAMETERS(JOB_RUNLOG_ID, NAME, VALUE) VALUES (?, ?, ?)";

	private static final String GET_REPORT_SOURCE_ID = 
		"SELECT REPORT_SOURCE_ID FROM REPORT_DEFINITION D, REPORT_SCHEDULE S " + 
		" WHERE D.REPORT_DEFINITION_ID = S.REPORT_DEFINITION_ID AND REPORT_SCHEDULE_ID = ?";

	private static final String UPDATE_JOB_ERROR_MESSAGE = 
		"UPDATE JOB_RUNLOG SET LAST_UPDATED_ON = ?, ERROR_LOG = ? WHERE JOB_RUNLOG_ID = ?";
	
	/*private static final String GET_REPORT_SCHEDULE_MDMID = 
		" select MDM_ID FROM report_schedule where report_schedule_id = ";	// added by nelson for employee reports
	 */
	private static final String GET_REPORT_SCHEDULE_MDMID =
        " select MDM_ID, MDM_LEVEL FROM report_schedule where report_schedule_id = ";        // added by nelson for employee reports
	
	@Override
	public String getFileDataLink() {
		
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.openQueryRS(GET_FILE_DATA_LINK);
			dbconn.rs.next();
			Integer id = dbconn.rs.getInt("FILE_DATD_LINK");
			return id.toString();
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}
	}
	
	
	private Integer getJobRunLogId() {
		
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.openQueryRS(GET_JOB_RUNLOG_ID);
			dbconn.rs.next();
			Integer id = dbconn.rs.getInt("JOB_RUNLOG_ID");
			return id;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}
	}

	@Override
	public String getJobBatchId() {
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.openQueryRS(GET_JOB_BATCH_ID);
			dbconn.rs.next();
			Integer id = dbconn.rs.getInt("JOB_BATCH_ID");
			return id.toString();
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}
	}

	private List<ReportJob> getActiveScheduleReportJobs(Date startDateTime,
			Date endDateTime,   String jobType) {
		String sql;
		
		if(jobType.equalsIgnoreCase(JobTypeEnum.SCHEDULED.getType())) {
			sql = MessageFormat.format(QUERY_ACTIVE_SCHEDULED_REPORT_SCHEDULE , startDateTime, endDateTime, jobType);
		} else {
			sql = QUERY_ACTIVE_RUNNOW_REPORT_SCHEDULE;
		}

		List<ReportJob> jobs =  new ArrayList<ReportJob>();
		logger.debug("QUERY_ACTIVE_REPORT_SCHEDULE :" + sql);
		
		DBConnection dbconn= new DBConnection();
		try {
			dbconn.open();
			dbconn.openQueryRS(sql);
			while(dbconn.rs.next()) {
				Date plannedJobStartDatetime;
				
				ReportJob job = new ReportJob();
				
				job.setReportDefinitionId(dbconn.rs.getString("REPORT_DEFINITION_ID"));
				job.setMdmId(dbconn.rs.getString("MDM_ID"));
				job.setMdmLevel(dbconn.rs.getString("MDM_LEVEL"));
				job.setUserNumber(dbconn.rs.getString("USERNUMBER"));
				job.setUserEmail(dbconn.rs.getString("RECEPIENT_EMAIL"));
				job.setReportScheduleId(dbconn.rs.getInt("REPORT_SCHEDULE_ID"));
				
				job.setJobType(JobTypeEnum.instance(jobType));
				String  freqency =  dbconn.rs.getString("RUN_FREQUENCY");
				job.setRunFrequency(ReportRunFrequencyEnum.instance(freqency));
				// get preferred time zone
				int zoneId = dbconn.rs.getInt("PREFERRED_TIMEZONE");
				Integer stanardLeadTime = getStandardLeadTimes().get(zoneId);
				if(stanardLeadTime == null) {
					stanardLeadTime = 0;
				}
				Integer customeLeadTime = dbconn.rs.getInt("CUSTOM_LEAD_MINUTES"); 
				// set job overdue 
				job.setOverdueMinutes(stanardLeadTime + customeLeadTime);
				
				if(job.getRunFrequency().equals(ReportRunFrequencyEnum.ONDEMAND)) {
					jobs.add(job);
					continue;
				}
				
				// begin for scheduled report , need to decide if we run it today.
				Date goalFinishedOn = (Date) dbconn.rs.getDate("GOAL_FINISHED_ON"); 
				if(goalFinishedOn != null) {
					Integer interval = dbconn.rs.getInt("RUN_INTERVAL");
					plannedJobStartDatetime = stepUpDate(zoneId, goalFinishedOn, interval, job.getRunFrequency(), job.getReportScheduleId());
				} else {
					plannedJobStartDatetime = getPreferEndTime(zoneId);
					if(job.getRunFrequency().equals(ReportRunFrequencyEnum.WEEKLY)) {
						int dayOfWeek =  dbconn.rs.getInt("DAY_OF_WEEK");
						if(!isTodayMatchDOW(zoneId, dayOfWeek)) {
							continue;
						}
						
					} else if(job.getRunFrequency().equals(ReportRunFrequencyEnum.MONTHLY)) {
						int dayOfMonth =  dbconn.rs.getInt("DAY_OF_MONTH");
						if(!isTodayMatchDOM(zoneId, dayOfMonth)) {
							continue;
						}						
					}
					
				}
				
				Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
				calendar.setTime(plannedJobStartDatetime);
				//subtract standard lead time
				calendar.add(Calendar.MINUTE,  - stanardLeadTime);
				// subtract custom lead time
				calendar.add(Calendar.MINUTE,  - customeLeadTime);
				
				plannedJobStartDatetime = calendar.getTime();
				
				logger.debug("report " + job.getReportScheduleId() + " is planned to run at " + plannedJobStartDatetime);
				// NOT falls within the P_StartDateTime or P_EndDateTime,  DOT NOT run this report
				if(plannedJobStartDatetime.before(startDateTime) || plannedJobStartDatetime.after(endDateTime)) {
					logger.debug("report " + job.getReportScheduleId() + "not fall in stard and end time, it is planned to at " + plannedJobStartDatetime);
					continue;
				}
				
				// end for scheduled report , need to decide if we run it today.
				jobs.add(job);
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}
		
		return jobs;
	}
	
	@Override
	public List<ReportJob> getQulifiedReportJobs(Date startDateTime,
			Date endDateTime,   String selectFlag, String specificRunLogId) {
		String jobType = ReportSelectionFlagEnum.SUBMITNOW.getType().equalsIgnoreCase(selectFlag)?
			JobTypeEnum.SUBMITNOW.getType() :
			JobTypeEnum.SCHEDULED.getType();
		
        if(specificRunLogId != null) {
        	return mapValueToList(getPreviousReRunJob(GET_ONE_SPECIFIC_JOB_TO_RERUN, jobType, specificRunLogId));
        }

        if(selectFlag.equals(ReportSelectionFlagEnum.FAILED.getType())) {
        	return mapValueToList(getPreviousReRunJob(GET_PREVIOUS_ERROR_JOB_TO_RERUN, jobType, null));
        }
        	
        HashMap<Integer, ReportJob> jobMaps = new HashMap<Integer, ReportJob>();
        
        if(selectFlag.equals(ReportSelectionFlagEnum.NORMAL.getType())){
        	jobMaps = getPreviousReRunJob(GET_PREVIOUS_ERROR_JOB_TO_RERUN, jobType, null);
        
        } else 
        
        if(selectFlag.equals(ReportSelectionFlagEnum.FORCE.getType())) {
        	jobMaps = getPreviousReRunJob(GET_PREVIOUS_FORCE_JOB_TO_RERUN, jobType, null);
        }
        // add the rerun jobs
        List<ReportJob> jobList =  mapValueToList(jobMaps);
        
        List<ReportJob> activeJobs = getActiveScheduleReportJobs(startDateTime, endDateTime, jobType);
        for(ReportJob job : activeJobs) {
        	// schedule id already in rerun job
        	if(jobMaps.get(job.getReportScheduleId())!= null) {
        		continue;
        	}
        	jobList.add(job);
        }
		return jobList;
	}
	
	private List<ReportJob> mapValueToList(HashMap<Integer, ReportJob> maps) {
		List<ReportJob> jobs =  new ArrayList<ReportJob>();
		for(ReportJob j :maps.values()) {
			jobs.add(j);
		}
		logger.debug("get rerun " + jobs.size() + " jobs!");
		return jobs;
	}
	


	private HashMap<Integer, ReportJob> getPreviousReRunJob(String sql, String jobType, String specificRunLogId) {
		DBParameter[] params;
		if(specificRunLogId != null) {
			params = new DBParameter[1]; 
			params[0]= new StringDBParameter(specificRunLogId, Types.VARCHAR);
		} else {
			params = new DBParameter[1];
			params[0]= new StringDBParameter(jobType, Types.VARCHAR);
		}
		
		logger.debug("QUERY previous run job by using sql:" + sql);
		
		HashMap<Integer, ReportJob> jobs =  new HashMap<Integer, ReportJob>();
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.queryPrepareStatement(sql, params);
			while(dbconn.rs.next()) {
				Date plannedJobStartDatetime;
				
				Integer scheduleId = dbconn.rs.getInt("REPORT_SCHEDULE_ID");
				if(jobs.get(scheduleId) != null) {
					continue;
				}

				ReportJob job = new ReportJob();
				
				job.setReportDefinitionId(dbconn.rs.getString("REPORT_DEFINITION_ID"));
				job.setMdmId(dbconn.rs.getString("MDM_ID"));
				job.setMdmLevel(dbconn.rs.getString("MDM_LEVEL"));
				job.setUserNumber(dbconn.rs.getString("USERNUMBER"));
				job.setUserEmail(dbconn.rs.getString("RECEPIENT_EMAIL"));
				job.setReportScheduleId(dbconn.rs.getInt("REPORT_SCHEDULE_ID"));
				
				job.setJobType(JobTypeEnum.instance(jobType));
				String  freqency =  dbconn.rs.getString("RUN_FREQUENCY");
				job.setRunFrequency(ReportRunFrequencyEnum.instance(freqency));
				// get preferred time zone
				int zoneId = dbconn.rs.getInt("PREFERRED_TIMEZONE");
				
					plannedJobStartDatetime = getPreferEndTime(zoneId);

					
				Integer stanardLeadTime = getStandardLeadTimes().get(zoneId);
				if(stanardLeadTime == null) {
					stanardLeadTime = 0;
				}
				Integer customeLeadTime = dbconn.rs.getInt("CUSTOM_LEAD_MINUTES"); 
				
				Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
				calendar.setTime(plannedJobStartDatetime);
				//subtract standard lead time
				calendar.add(Calendar.MINUTE,  - stanardLeadTime);
				// subtract custom lead time
				calendar.add(Calendar.MINUTE,  - customeLeadTime);
				
				plannedJobStartDatetime = calendar.getTime();
				
				// set job overdue 
				job.setOverdueMinutes(stanardLeadTime + customeLeadTime);
				
				jobs.put(scheduleId, job);
			}

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}
		return jobs;
	}
	
	
	
	public Date getPreferEndTime(int timezoneId) {
		Calendar calendar = Calendar.getInstance(TimezoneUtil.getTimeBaseonTimezone(timezoneId));
		calendar.set(Calendar.HOUR, PropertiesUtil.getDefaultSchedulerFinishHour());
		calendar.set(Calendar.MINUTE, PropertiesUtil.getDefaultSchedulerFinishMinute());
		return calendar.getTime();
	}
	
	private boolean isTodayMatchDOW(int timezoneId,  int dayOfWeek) {
		Calendar calendar = Calendar.getInstance(TimezoneUtil.getTimeBaseonTimezone(timezoneId));
		if(calendar.get(Calendar.DAY_OF_WEEK) == dayOfWeek) {
			return true;
		}
		return false;
	}
	
	private boolean isTodayMatchDOM(int timezoneId,  int dayOfMonth) {
		Calendar calendar = Calendar.getInstance(TimezoneUtil.getTimeBaseonTimezone(timezoneId));
		if(calendar.get(Calendar.DAY_OF_MONTH) == dayOfMonth) {
			return true;
		}
		return false;
	}
	
	private Date  stepUpDate(int timezoneId, Date baseDate, Integer interval,  ReportRunFrequencyEnum frequency, Integer scheduleId) {
		Calendar calendar = Calendar.getInstance(TimezoneUtil.getTimeBaseonTimezone(timezoneId));
		calendar.setTime(baseDate);
		if(interval == null) {
			interval = 1;
		}
		switch(frequency) {
			case DAILY:
				calendar.add(Calendar.DATE, interval);
			break;
			case MONTHLY:
				calendar.add(Calendar.MONTH, interval);
			break;
			case WEEKLY:
				calendar.add(Calendar.MONTH, interval);
			break;
		}
		Date  plannedStartDateTime = calendar.getTime();
		Date  deadLineTime = getPreferEndTime(timezoneId);
		
		//  there is some error happen, this report missed some running.
		if(plannedStartDateTime.before(deadLineTime)) {
			logger.debug("report " + scheduleId + " is planned to run at " + plannedStartDateTime + " but too late");
			return deadLineTime;
		} else {
			return plannedStartDateTime; 
		}
	}

	@Override
	public void insertReportLog(ReportJob job) {
		String fileDataLink = getFileDataLink();
		logger.debug("report " + job.getReportDefinitionId() + "is setting file data link :" + fileDataLink);
		job.setFileDataLink(fileDataLink);
		 
		//get the global mdmId, mdmLevel and legal name for report
		// THE MdmId, MdmLevel already top level
		getMdmIDandLevelRuntime(job);
		for(ReportFileType fileType : ReportFileType.getAllReportFileType()) {
			insertOneReportType(job, fileType.getType());
		}
	}
	
	private void getMdmIDandLevelRuntime(ReportJob job) {
		try {
			ServicesUser userInfo = userInfoService.getUserInfo(job.getUserNumber(), job.getUserEmail());
			// If Mdm_Id is empty, we just keep use the mdm_id in the report definition
			if(!StringUtil.isStringEmpty(userInfo.getMdmId())) {
				job.setMdmId(userInfo.getMdmId());
				logger.debug(" set the mdm_id = " +  userInfo.getMdmId() + " runtime for job which file_link = " + job.getFileDataLink());
			}
			// If Mdm_Level is empty, we just keep use the mdm_Level in the report definition
			if(!StringUtil.isStringEmpty(userInfo.getMdmLevel())) {
				job.setMdmLevel(userInfo.getMdmLevel());
				logger.debug(" set the mdm_level = " +  userInfo.getMdmLevel() + " runtime for job which file_link = " + job.getFileDataLink());
			}
			//added by nelson for employee reports
            if(null == job.getMdmId() || "".equals(job.getMdmId())) {
            		logger.debug("### mdmId not present in db n ldap");
                    Map<String, String> rsMap = getReportScheduleMdmIdAndLevel(job.getReportScheduleId());
                    job.setMdmId(rsMap.get("MDM_ID"));
                    job.setMdmLevel(rsMap.get("MDM_LEVEL"));
                    logger.debug("new mdmid is: " + job.getMdmId() + " and level: " + job.getMdmLevel());
            }
            //end of addition by nelson for employee reports
		}catch(Exception e) {
			logger.error("error to get global level mdmid and mdmLevel for user " + job.getUserNumber(), e);
			throw new RuntimeException(e);
		}
	}
	
	//added by nelson for employee reports
	/*private String getReportScheduleMdmId(Integer reportScheduleId) {
		logger.debug("looking for mdmId for reportschid: " + reportScheduleId);
		String query = GET_REPORT_SCHEDULE_MDMID + reportScheduleId;
		logger.debug("new query is: " + query);
		String mdmID = null;
		DBConnection dbconn= new DBConnection();
		try {
			dbconn.open();
			dbconn.openQueryRS(query);
			while(dbconn.rs.next()) {
				mdmID = dbconn.rs.getString("MDM_ID");
			}
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}
		logger.debug("new mdmid is: " + mdmID);
		
		return mdmID;
	}*/
	
	private Map<String, String> getReportScheduleMdmIdAndLevel(Integer reportScheduleId) {
        Map<String, String> reportSchMdm = new HashMap<String, String>();
        logger.debug("looking for mdmId for reportschid: " + reportScheduleId);
        String query = GET_REPORT_SCHEDULE_MDMID + reportScheduleId;
        logger.debug("new query is: " + query);

        DBConnection dbconn= new DBConnection();
        try {
                dbconn.open();
                dbconn.openQueryRS(query);
                while(dbconn.rs.next()) {
                        reportSchMdm.put("MDM_ID", dbconn.rs.getString("MDM_ID"));
                        reportSchMdm.put("MDM_LEVEL", dbconn.rs.getString("MDM_LEVEL"));
                }
        }
        catch (Exception exception) {
                throw new RuntimeException(exception);
        }
        finally {
                dbconn.closeQueryRS();
                dbconn.close();
        }
        logger.debug("new mdmid is: " + reportSchMdm.get("MDM_ID"));
        logger.debug("new mdmlevel is: " + reportSchMdm.get("MDM_LEVEL"));
        
        return reportSchMdm;
	}
	//end of addition by nelson for employee reports
	
	
	
	private void insertOneReportType(ReportJob job, String fileType) {
		DBParameter[] params = new DBParameter[15]; 
		int i=0;
		
		Integer runLogId = getJobRunLogId();
		params[i++]= new Int32DBParamter(runLogId, Types.INTEGER);
		params[i++]= new StringDBParameter(job.getMdmId(), Types.VARCHAR);
		params[i++]= new StringDBParameter(job.getMdmLevel(), Types.VARCHAR);
		params[i++]= new StringDBParameter(job.getLegalName(), Types.VARCHAR);
		params[i++]= new Int32DBParamter(job.getReportScheduleId(), Types.INTEGER);
		params[i++]= new Int32DBParamter(job.getOverdueMinutes(), Types.INTEGER);

		params[i++]= new StringDBParameter(RunLogStatusEnum.SELECTED.getStatusCode(), Types.VARCHAR);
		params[i++]= new StringDBParameter(fileType, Types.VARCHAR);
		params[i++]= new StringDBParameter(job.getFileDataLink(), Types.VARCHAR);
		params[i++]= new StringDBParameter(job.getJobBatchId(), Types.VARCHAR);
		
		params[i++]= new Int32DBParamter(job.getThreadGroup(), Types.INTEGER);
		params[i++]= new DatetimeDBParameter(new Date(), Types.DATE);
		params[i++]= new DatetimeDBParameter(new Date(), Types.DATE);
		params[i++]= new DatetimeDBParameter(new Date(), Types.DATE);
		params[i++]= new StringDBParameter(job.getJobType().getType(), Types.VARCHAR);
		DBConnection conn=new DBConnection();
		conn.open(); //do this before you use a new DBConnection..
		try
		{
			conn.executePrepareStatement(INSERT_JOB_RUNLOG, params);
		} catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
		finally {
			conn.close(); //always do this when finished using the DBConnection
		}
	}

	@Override
	public Map<Integer, Integer> getStandardLeadTimes() {
		if(standardLeadTimes != null) {
			return standardLeadTimes;
		}
		String sql = "select TIMEZONE, STANDARD_LEAD_MINUTES from  JOB_LEADTIME";
		standardLeadTimes = new  HashMap<Integer, Integer>(); 
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.openQueryRS(sql);
			while(dbconn.rs.next()) {
				Integer id = dbconn.rs.getInt("TIMEZONE");
				Integer leadTime = dbconn.rs.getInt("STANDARD_LEAD_MINUTES");
				standardLeadTimes.put(id, leadTime);
			}
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}
		return standardLeadTimes;
	}


	@Override
	public List<ReportJob> getReportJobsForThread(String batchId,
			Integer threadId) {
		List<ReportJob> jobs = new ArrayList<ReportJob>();
		
		DBParameter[] params = new DBParameter[2]; 
		int i=0;
		params[i++]= new StringDBParameter(batchId, Types.VARCHAR);
		params[i++]= new Int32DBParamter(threadId, Types.INTEGER);
		
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			
			// this is the query
			
			dbconn.queryPrepareStatement(QUERY_JOB_RUNLOG_FOR_THREAD, params);
			while(dbconn.rs.next()) {
				ReportJob job = new ReportJob();
				Integer jobId = dbconn.rs.getInt("JOB_RUNLOG_ID");
				job.setJobRunLogId(jobId.longValue());
				job.setReportScheduleId(dbconn.rs.getInt("REPORT_SCHEDULE_ID"));
				job.setFileType(ReportFileType.instance(dbconn.rs.getString("FILE_TYPE")));
				job.setFileDataLink(dbconn.rs.getString("FILE_DATA_LINK"));
				job.setJobType(JobTypeEnum.valueOf(dbconn.rs.getString("JOB_TYPE")));
				job.setOverdueMinutes(dbconn.rs.getInt("OVERDUE_MINUTES"));
				job.setJobBatchId(batchId);
				job.setThreadGroup(threadId);
				job.setUserNumber(dbconn.rs.getString("USERNUMBER"));
				job.setUserEmail(dbconn.rs.getString("RECEPIENT_EMAIL"));
				
				String isSendMdm = dbconn.rs.getString("IS_SEND_MDM");
				job.setMdmId(dbconn.rs.getString("MDM_ID"));
				job.setMdmLevel(dbconn.rs.getString("MDM_LEVEL"));
				if(isSendMdm != null && isSendMdm.equalsIgnoreCase("T")) {
					logger.debug("isSendMdm is T");
					job.setSendMDMParameter(true);
				} else {
					logger.debug("isSendMdm is not T");
					job.setSendMDMParameter(false);
				}
				
				// IS LBS ACCOUNT FLAG SET
				logger.debug("JobRunLogId VALUE is --------------- "+jobId.longValue());
				logger.debug("REPORT_SCHEDULE_ID VALUE is --------------- "+dbconn.rs.getInt("REPORT_SCHEDULE_ID"));
				logger.debug("USERNUMBER VALUE is --------------- "+dbconn.rs.getString("USERNUMBER"));
				logger.debug("RECEPIENT_EMAIL VALUE is --------------- "+dbconn.rs.getString("RECEPIENT_EMAIL"));
				logger.debug("setMdmId VALUE is --------------- "+isSendMdm);
				logger.debug("setMdmId VALUE is --------------- "+isSendMdm);
				logger.debug("LBS ACCOUNT FLAG VALUE is -------------------- "+dbconn.rs.getString("ISLBSACCOUNT"));
				
				job.setIsLBSAccount(dbconn.rs.getString("ISLBSACCOUNT"));
				
				// set the flag here
				
				jobs.add(job);
			}
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}
		return jobs;
	}


	@Override
	public ReportJob handleReportParameters(ReportJob job, List<ReportJob> jobList) {
		// If we can get go throght all the jobList and get the paramter from another job with same link,
		List<ReportParameters> parameterList = getParamtersFromSameReportJob(job, jobList);
		if(parameterList != null && parameterList.size() > 0){
			for(ReportParameters p : parameterList) {
				job.getParameterList().add(p);
			}
		} else {
			HashMap<String, ReportParameters> paramMap = new HashMap<String, ReportParameters>();

			getParamtersFromSchedule(job, paramMap);

			// handle the meta parameters   MDM_ID, MDM_LEVEL,  COUNTRY, LANGUAGE
			logger.debug("handle the meta paramters   MDM_ID, MDM_LEVEL,  COUNTRY, LANGUAGE for job " + job.getJobRunLogId());
			handleAutomaticParameters(paramMap, job);
			// handle the sliding date 
			logger.debug("handle slide date for job " + job.getJobRunLogId());
			handleSlideDateParameters(paramMap, job);

			for(ReportParameters p : paramMap.values()) {
				job.getParameterList().add(p);
			}
		}
		//added for LBS ACCOUNT start
		int listSize = job.getParameterList().size();
		int j=0;
		for(int i=0;i<listSize;i++){			
			if("LBS ACCOUNT".equalsIgnoreCase(job.getParameterList().get(i).getName())){
				j=j+1;
			}
		}
		if(j==0){
		ReportParameters rp = new ReportParameters();
		rp.setName("LBS ACCOUNT");
		
		if(job.getIsLBSAccount()==null){
			rp.setValue("false");
		}else if(("T").equalsIgnoreCase(job.getIsLBSAccount())){
			rp.setValue("true");
		}else{
			rp.setValue("false");
		}
		job.getParameterList().add(rp);
		}
		//added for LBS ACCOUNT end
		
		// insert into instance parameters table
		insertRunLogParams(job);
		return job;
	}
	
	private List<ReportParameters> getParamtersFromSameReportJob(ReportJob currentJob, List<ReportJob> jobList) {
		String fileDataLink = currentJob.getFileDataLink();
		for(ReportJob job : jobList) {
			if(fileDataLink.equals(job.getFileDataLink()) && !currentJob.getJobRunLogId().equals(job.getJobRunLogId()) 
					&& job.getParameterList()!= null && job.getParameterList().size() > 0) {
				logger.debug("Find parameters value for current job  from another job - " + job.getJobRunLogId());
				return job.getParameterList();
			}
		}
		logger.debug("Can not Find parameters value for current job");
		return new ArrayList<ReportParameters>();
	}
	
	private void getParamtersFromSchedule(ReportJob job,
			HashMap<String, ReportParameters> paramMap) {
		DBParameter[] params = new DBParameter[1]; 
		params[0]= new Int32DBParamter(job.getReportScheduleId(), Types.INTEGER);
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.queryPrepareStatement(QUERY_REPORT_DEFINITION_PARAMETERS, params);
			while(dbconn.rs.next()) {
				String paramName = dbconn.rs.getString("NAME");
				String paramValue = dbconn.rs.getString("DEFAULT_VALUES");
				String paramType = dbconn.rs.getString("DATA_TYPE");
				ReportParameters param = new ReportParameters();
				param.setDataType(paramType);
				param.setName(paramName);
				param.setValue(paramValue);
				paramMap.put(paramName, param);
			}
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}
		
		try
		{
			dbconn.open();
			dbconn.queryPrepareStatement(QUERY_REPORT_SCHEDULE_PARAMETERS, params);
			while(dbconn.rs.next()) {
				String paramName = dbconn.rs.getString("NAME");
				String paramValue = dbconn.rs.getString("VALUE");
				ReportParameters param = paramMap.get(paramName);
				if(param != null) {
					param.setValue(paramValue);
				}
			}
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}
	}
	
	public void handleAutomaticParameters(HashMap<String, ReportParameters> paramMap, ReportJob job) {
		ServicesUser serviceUser = userInfoService.getUserInfo(job.getUserNumber(), job.getUserEmail());
		if(job.isSendMDMParameter()) {
			logger.debug("job " + job.getJobRunLogId() + " need automatically send MDM_ID and Mdm_level from user " + job.getUserNumber());
			handleSendMdmParameters(paramMap, job.getMdmId(), job.getMdmLevel(), serviceUser.getChlNodeId());
		} else {
			logger.debug("job " + job.getJobRunLogId() + " do NOT need automatically send MDM_ID and Mdm_level");
		}
		
		// country
		String country = serviceUser.getCountry();
		if(country != null && !country.isEmpty()) {
			ReportParameters paramCountry = getParamterFromMap(paramMap, ReportConstants.COUNTRY_PARAMTER_NAME); 
			if(paramCountry == null) {	
				paramCountry = new ReportParameters();
				paramCountry.setDataType(ReportParameterTypeEnum.STRING.getType());
				paramCountry.setName(ReportConstants.COUNTRY_PARAMTER_NAME);
				paramMap.put(ReportConstants.COUNTRY_PARAMTER_NAME, paramCountry);
			}
			paramCountry.setValue(country);
			logger.debug("automatically set paramter country :" + country + "job " + job.getJobRunLogId());
		}
		// languge
		String language = serviceUser.getLanguage();
		if(language != null && !language.isEmpty()) {
			ReportParameters paramLanguage = getParamterFromMap(paramMap, ReportConstants.LANGUAGE_PARAMTER_NAME);  
			if(paramLanguage == null) {
				paramLanguage = new ReportParameters();
				paramLanguage.setDataType(ReportParameterTypeEnum.STRING.getType());
				paramLanguage.setName(ReportConstants.LANGUAGE_PARAMTER_NAME);
				paramMap.put(ReportConstants.LANGUAGE_PARAMTER_NAME, paramLanguage);
			}
			paramLanguage.setValue(language);
			logger.debug("automatically set paramter language :" + language + "job " + job.getJobRunLogId());
		}
	}
	
	
	private static final String GET_LAST_INSTANCE_PARAMETERS = 
		   "select * from report_instance_parameters p join ( " + 
		   " select * from ( " + 
		   " select jr.JOB_RUNLOG_ID,  published_on from job_runlog jr " +
		   " where jr.REPORT_SCHEDULE_ID = ?  and published_on is not null " +
		   " order by published_on desc " +
		   " ) where rownum = 1 " +
		   " ) j on p.job_runlog_id = j.job_runlog_id ";
	
	private static final SimpleDateFormat dateParameterFormater = new SimpleDateFormat("yyyy,MM,dd");

	
	public void handleSlideDateParameters(HashMap<String, ReportParameters> paramMap, ReportJob job) {
		if(job.getJobType().equals(JobTypeEnum.SUBMITNOW)) {
			logger.debug("Run now do not need slide paramters , job id = " + job.getJobRunLogId());
			return;
		}
		
		DBParameter[] params = new DBParameter[1]; 
		params[0]= new Int32DBParamter(job.getReportScheduleId(), Types.INTEGER);
        Date lastPublishedOn = null;
        String strStartDate = null;
        String strEndDate = null;
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.queryPrepareStatement(GET_LAST_INSTANCE_PARAMETERS, params);
			while(dbconn.rs.next()) {
				if(lastPublishedOn == null) {
					 lastPublishedOn = (Date) dbconn.rs.getDate("PUBLISHED_ON");
				}
				
				String paramName = dbconn.rs.getString("NAME");
				if(paramName != null) {
					if(paramName.equalsIgnoreCase(ReportConstants.START_DATE_PARAMTER_NAME)) {
						strStartDate = dbconn.rs.getString("VALUE");
					}else if(paramName.equalsIgnoreCase(ReportConstants.END_DATE_PARAMTER_NAME)) {
						strEndDate = dbconn.rs.getString("VALUE");
					}
				}
			}
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}
		
		if(lastPublishedOn == null)  {
			logger.debug("Run now do not need slide paramters , job id = " + job.getJobRunLogId());
			return;
		}
		
		// handle sliding parameters
		Calendar nowCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		Date now = nowCalendar.getTime();
		
		try 
		{
			lastPublishedOn = dateParameterFormater.parse(dateParameterFormater.format(lastPublishedOn));
			now = dateParameterFormater.parse(dateParameterFormater.format(now));
		}catch(ParseException e){
			// ingnore
		}
   	    int  daysGap = (int)(now.getTime() - lastPublishedOn.getTime()) / (24* 3600 * 1000);
   	    if(daysGap <= 0) {
		  logger.debug("daysGap is 0, do not need slide paramters , job id = " + job.getJobRunLogId());
		  return; 
   	    }  else {
  		  logger.debug("daysGaps is " + daysGap +  " , job id = " + job.getJobRunLogId());
   	    }

   	    if(strStartDate != null && !strStartDate.isEmpty()) {
			ReportParameters paramBeginDate = getParamterFromMap(paramMap, ReportConstants.START_DATE_PARAMTER_NAME);  
			if(paramBeginDate != null) {
					String slidedBeginDate = addDaysToDateofString(strStartDate, daysGap); 
					paramBeginDate.setValue(slidedBeginDate);			
					logger.debug("automatically set paramter StartDate :" + slidedBeginDate + "job " + job.getJobRunLogId());
			}
		}
		
		if(strEndDate != null && !strEndDate.isEmpty()) {
			ReportParameters paramEndDate = getParamterFromMap(paramMap, ReportConstants.END_DATE_PARAMTER_NAME);  
			if(paramEndDate != null) {
				String slidedEndDate = addDaysToDateofString(strEndDate, daysGap); 
				paramEndDate.setValue(slidedEndDate);			
				logger.debug("automatically set paramter EndDate :" + slidedEndDate + "job " + job.getJobRunLogId());
			}
		}
	}
	
	private static String addDaysToDateofString(String baseDate, int daysToAdd) {
		try { 
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		Date date =  dateParameterFormater.parse(baseDate);
		calendar.setTime(date);
		calendar.add(Calendar.DATE, daysToAdd);
		return dateParameterFormater.format(calendar.getTime());
		}catch(ParseException e){
			// ingnore
			return baseDate;
		}	
	}
	
	private void handleSendMdmParameters(HashMap<String, ReportParameters> paramMap, String mdmId, String mdmLevel, String chlNode) {
		
		ReportParameters paramId = getParamterFromMap(paramMap, ReportConstants.MDM_ID_PARAMTER_NAME); 
		if(paramId == null) {	
			paramId = new ReportParameters();
			paramId.setDataType(ReportParameterTypeEnum.STRING.getType());
			paramId.setName(ReportConstants.MDM_ID_PARAMTER_NAME);
			paramMap.put(ReportConstants.MDM_ID_PARAMTER_NAME, paramId);
		}
		paramId.setValue(mdmId);
		ReportParameters paramLevel = getParamterFromMap(paramMap, ReportConstants.MDM_LEVEL_PARAMTER_NAME);  
		if(paramLevel == null) {
			paramLevel = new ReportParameters();
			paramLevel.setDataType(ReportParameterTypeEnum.STRING.getType());
			paramLevel.setName(ReportConstants.MDM_LEVEL_PARAMTER_NAME);
			paramMap.put(ReportConstants.MDM_LEVEL_PARAMTER_NAME, paramLevel);
		}
		paramLevel.setValue(mdmLevel);
		
		if(chlNode == null || chlNode.isEmpty()) {
			chlNode = ReportConstants.CHLNODE_PARAMTER_DEFAULT_VALUE;
		}
		ReportParameters paramChlNode = getParamterFromMap(paramMap, ReportConstants.CHLNODE_PARAMTER_NAME);  
		if(paramChlNode == null) {
			paramChlNode = new ReportParameters();
			paramChlNode.setDataType(ReportParameterTypeEnum.STRING.getType());
			paramChlNode.setName(ReportConstants.CHLNODE_PARAMTER_NAME);
			paramMap.put(ReportConstants.CHLNODE_PARAMTER_NAME, paramChlNode);
		}
		paramChlNode.setValue(chlNode);
		
	}
	private ReportParameters  getParamterFromMap(HashMap<String, ReportParameters> paramMap, String paramName) {
		for(String name : paramMap.keySet()) {
			if(paramName.equalsIgnoreCase(name)) {
				return paramMap.get(name);
			}
		}
		return null;
	}
	
	public void insertRunLogParams(ReportJob job) {
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open(false);
			PreparedStatement insertStatement = dbconn.prepareStatement(INSERT_INSTANCE_PARAMETERS);
	    	for(ReportParameters param : job.getParameterList()) {
	    		insertStatement.setInt(1, job.getJobRunLogId().intValue());
	    		insertStatement.setString(2, param.getName());
	    		insertStatement.setString(3, param.getValue());
	    		insertStatement.addBatch();
	    	}
	    	insertStatement.executeBatch();
	    	dbconn.commit();
	    	insertStatement.clearBatch();
	    	insertStatement.close();
	    	if(logger.isDebugEnabled()) {
	    		logger.debug("report job :" + job.getJobRunLogId() + " insert into " + job.getParameterList().size() + " instance parameters");
	    	}
		}catch (SQLException e) {
	    		logger.error("SQLException caught: (executing update )"+ e.getMessage());
	    } finally {
	    	dbconn.close();
	    }
	    
	}
	
	public  String getReportSourceId(Integer reportSchduleId) {
		DBParameter[] params = new DBParameter[1]; 
		params[0]= new Int32DBParamter(reportSchduleId, Types.INTEGER);
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.queryPrepareStatement(GET_REPORT_SOURCE_ID, params);
			dbconn.rs.next();
			String id = dbconn.rs.getString("REPORT_SOURCE_ID");
			logger.debug("Report shechdule - " +  reportSchduleId + " get report source id: " + id);
			return id;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}
	}

	public void updateJobBuildingStatus(Long logId) {
		DBParameter[] params = new DBParameter[4]; 
		int i=0;
		params[i++]= new StringDBParameter(RunLogStatusEnum.BUILDSUBMIT.getStatusCode(), Types.VARCHAR);
		params[i++]= new DatetimeDBParameter(new Date(), Types.DATE);
		params[i++]= new DatetimeDBParameter(new Date(), Types.DATE);
		params[i++]= new Int32DBParamter(logId, Types.INTEGER);
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.executePrepareStatement(UPDATE_BUILDING_STATUS, params);
			logger.debug("Report job " + logId + " set BUILDSUBMIT");
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.close();
		}
	}
	
	public void updateJobBuildingURL(Long logId, String boxiURL) {
		DBParameter[] params = new DBParameter[2]; 
		int i=0;
		params[i++]= new StringDBParameter(boxiURL, Types.VARCHAR);
		params[i++]= new Int32DBParamter(logId, Types.INTEGER);
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.executePrepareStatement(UPDATE_BUILDING_URL, params);
			logger.debug("Report job " + logId + " save build url- " + boxiURL);
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.close();
		}		
	}


	public void updateJobBuiltStatus(Long logId, boolean isSuccess, String message){
		DBParameter[] params = new DBParameter[6]; 
		String status = isSuccess? RunLogStatusEnum.BUILDOK.getStatusCode(): RunLogStatusEnum.BUILDERROR.getStatusCode();
		int i=0;
		params[i++]= new StringDBParameter(status, Types.VARCHAR);
		params[i++]= new DatetimeDBParameter(new Date(), Types.DATE);
		params[i++]= new DatetimeDBParameter(new Date(), Types.DATE);
		params[i++]= new StringDBParameter(message, Types.VARCHAR);
		params[i++]= new StringDBParameter(message, Types.VARCHAR);
		params[i++]= new Int32DBParamter(logId, Types.INTEGER);
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.executePrepareStatement(UPDATE_BUILT_STATUS, params);
			logger.debug("Report job " + logId + " set built " + status);
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.close();
		}
	}
	
	public void updateJobLoadingStatus(Long logId, Long reportSize) {
		DBParameter[] params = new DBParameter[5]; 
		int i=0;
		params[i++]= new StringDBParameter(RunLogStatusEnum.LOADSUBMIT.getStatusCode(), Types.VARCHAR);
		params[i++]= new DatetimeDBParameter(new Date(), Types.DATE);
		params[i++]= new DatetimeDBParameter(new Date(), Types.DATE);
		params[i++]= new Int32DBParamter(reportSize, Types.INTEGER);
		params[i++]= new Int32DBParamter(logId, Types.INTEGER);
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.executePrepareStatement(UPDATE_LOADING_STATUS, params);
			logger.debug("Report job " + logId + " set LOADSUBMIT");
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.close();
		}		
	}
	public void updateJobLoadedStatus(Long logId, String objectId,  boolean isSuccess, String message) {
		DBParameter[] params = new DBParameter[7]; 
		String status = isSuccess? RunLogStatusEnum.LOADOK.getStatusCode(): RunLogStatusEnum.LOADERROR.getStatusCode();
		int i=0;
		params[i++]= new StringDBParameter(status, Types.VARCHAR);
		params[i++]= new DatetimeDBParameter(new Date(), Types.DATE);
		params[i++]= new DatetimeDBParameter(new Date(), Types.DATE);
		params[i++]= new StringDBParameter(objectId, Types.VARCHAR);
		params[i++]= new StringDBParameter(message, Types.VARCHAR);
		params[i++]= new StringDBParameter(message, Types.VARCHAR);
		params[i++]= new Int32DBParamter(logId, Types.INTEGER);
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.executePrepareStatement(UPDATE_LOADED_STATUS, params);
			logger.debug("Report job " + logId + " set load " + status);
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.close();
		}		
	}
	
	public void updateJobWaitForPushStatus(String fileDataLink) {
		DBParameter[] params = new DBParameter[4]; 
		int i=0;
		params[i++]= new StringDBParameter(RunLogStatusEnum.WAITFORPUB.getStatusCode(), Types.VARCHAR);
		params[i++]= new StringDBParameter(fileDataLink, Types.VARCHAR);
		params[i++]= new StringDBParameter(fileDataLink, Types.VARCHAR);
		params[i++]= new StringDBParameter(RunLogStatusEnum.LOADOK.getStatusCode(), Types.VARCHAR);
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.executePrepareStatement(UPDATE_WAIT_FOR_PUBLISH_STATUS, params);
			logger.debug("UPDATE Report jobs with file data link of  " + fileDataLink + "to wait for publish!");
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.close();
		}
	}
		
	public  ReportProperties loadReportMetaData(Long runLogId) {
		DBParameter[] params = new DBParameter[1]; 
		params[0]= new Int32DBParamter(runLogId, Types.INTEGER);
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.queryPrepareStatement(LOAD_REPORT_META_DATA, params);
			dbconn.rs.next();
			ReportProperties metaData = new ReportProperties();
			// documentum needed
			metaData.setLocale(dbconn.rs.getString("COUNTRY"));
			metaData.setAContentType(dbconn.rs.getString("FILE_TYPE"));
			
			metaData.setFileName(dbconn.rs.getString("NAME") +  "." + metaData.getAContentType());
			// objectName need to be unique;
			String fileContentDate = FILE_CONTENT_DATE_FORMAT.format(new Date());
			String objectName = fileContentDate + " " + dbconn.rs.getString("NAME") + "-" +  runLogId + "." + metaData.getAContentType();
			metaData.setObjectName(objectName);
			metaData.setRFolderPath(PropertiesUtil.DOCUMENTUM_REPORTFOLDER);
            // metaData.setRObjectType  default in the ReportProperties class definition
			
			// Report mandetory
			metaData.setRunLogId(runLogId.toString());
			metaData.setDefinitionId(dbconn.rs.getString("REPORT_DEFINITION_ID"));
			metaData.setFileDataLink(dbconn.rs.getString("FILE_DATA_LINK"));
			metaData.setLegalName(dbconn.rs.getString("LEGAL_NAME"));
			metaData.setMdmid(dbconn.rs.getString("MDM_ID"));
			metaData.setMdmlevel(dbconn.rs.getString("MDM_LEVEL"));
			metaData.setScheduleFrequency(dbconn.rs.getString("RUN_FREQUENCY"));
			metaData.setUsernumber(dbconn.rs.getString("USERNUMBER"));
			
			if(logger.isDebugEnabled()) {
				logger.debug("Report shechdule - " +  runLogId + " get meta data: + \n" + metaData.toString());
			}
			return metaData;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}
	}

	@Override
	public void updateReRunFlag() {
		DBParameter[] params = new DBParameter[0]; 
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.executePrepareStatement(UPDATE_RERUN_FLAG, params);
			logger.debug("UPDATE Report jobs, which are privious failed, set rerun flag with X");
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.close();
		}
	}


	@Override
	public void updateDeferredStatus(Long runLogId) {
		DBParameter[] params = new DBParameter[3]; 
		int i=0;
		params[i++]= new StringDBParameter(RunLogStatusEnum.DEFERRED.getStatusCode(), Types.VARCHAR);
		params[i++]= new DatetimeDBParameter(new Date(), Types.DATE);
		params[i++]= new Int32DBParamter(runLogId, Types.INTEGER);
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.executePrepareStatement(UPDATE_DEFERRED_STATUS, params);
			logger.debug("Report job " + runLogId + " set DEFERRED " + runLogId);
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.close();
		}		
	}
	
	
	@Override
	public  void logJobErrorMessage(Long logId, String message) {
		DBParameter[] params = new DBParameter[3]; 
		int i=0;
		params[i++]= new DatetimeDBParameter(new Date(), Types.DATE);
		params[i++]= new StringDBParameter(message, Types.VARCHAR);
		params[i++]= new Int32DBParamter(logId, Types.INTEGER);
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.open();
			dbconn.executePrepareStatement(UPDATE_JOB_ERROR_MESSAGE, params);
			logger.debug("Report job " + logId + " log error message: " + message);
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.close();
		}
	}
	
	//TODO: make sure SCHEDULE.COUNTRY is the locale code
	private static final String QUERY_PUBLISHED_REPORTS =  
		"SELECT RUNLOG.JOB_RUNLOG_ID,  RUNLOG.REPORT_SCHEDULE_ID, RUNLOG.FILE_TYPE, RUNLOG.FILE_DATA_LINK, " +  
		" DOC.A_WEBC_URL, SCHEDULE.REPORT_DEFINITION_ID, SCHEDULE.COUNTRY AS LOCALE_CODE, SCHEDULE.USERNUMBER, SCHEDULE.RECEPIENT_EMAIL " +
		"FROM JOB_RUNLOG  RUNLOG,  LXK_PORTAL_DOC_VIEW DOC, REPORT_SCHEDULE SCHEDULE " + 
		"WHERE RUNLOG.FILE_OBJECT_ID = DOC.R_OBJECT_ID AND " + 
		"RUNLOG.REPORT_SCHEDULE_ID = SCHEDULE.REPORT_SCHEDULE_ID  " +
		"AND RUNLOG.STATUS_CODE = 'WAITFORPUB'  AND RUNLOG.PUBLISHED_ON IS NULL";

	@Override 
	public List<ReportNotifierTO>  queryPublishedReports(){
		 List<ReportNotifierTO> reportsList = new ArrayList<ReportNotifierTO>();
			DBParameter[] params = new DBParameter[0]; 
			DBConnection dbconn= new DBConnection();
			try
			{
				dbconn.getConnection(DBUtil.getPortalDBConnection());
				dbconn.queryPrepareStatement(QUERY_PUBLISHED_REPORTS, params);
				
				while(dbconn.rs.next()) {
					ReportNotifierTO to = new ReportNotifierTO();
					to.setRunlogId(dbconn.rs.getString("JOB_RUNLOG_ID"));
					to.setDefinitionId(dbconn.rs.getInt("REPORT_DEFINITION_ID"));
					to.setScheduleId(dbconn.rs.getInt("REPORT_SCHEDULE_ID"));
					to.setAWebUrl(dbconn.rs.getString("A_WEBC_URL"));
					to.setLocaleCode(dbconn.rs.getString("LOCALE_CODE"));
					to.setUserNumber(dbconn.rs.getString("USERNUMBER"));
					to.setUserEmail(dbconn.rs.getString("RECEPIENT_EMAIL"));
					to.setFileDataLink(dbconn.rs.getString("FILE_DATA_LINK"));
					to.setFileType(dbconn.rs.getString("FILE_TYPE"));
					reportsList.add(to);
				}
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			} finally {
				dbconn.closeQueryRS();
				dbconn.close();
			}
		 return reportsList;
	}
	
	private static final String QUERY_LOCALIZED_REPORT_NAME =
		"SELECT LOCALE.NAME AS LOCALIZED_NAME FROM DEFINITION_LOCALE LOCALE ," + 
		"SUPPORTED_LOCALE SL " +
		"WHERE LOCALE.SUPPORTED_LOCALE_ID = SL.SUPPORTED_LOCALE_ID " +
		"AND SL.SUPPORTED_LOCALE_CODE = ? OR SL.SUPPORTED_LOCALE_CODE = 'en' " + 
		"AND LOCALE.REPORT_DEFINITION_ID = ? " +
		"ORDER BY DECODE(SL.supported_locale_code, 'en',2,1)";
	@Override
	public String getLocalizedReportName(Integer reportDefinitionId, String localeCode) {
		DBParameter[] params = new DBParameter[2]; 
		params[0]= new StringDBParameter(localeCode, Types.VARCHAR);
		params[1]= new Int32DBParamter(reportDefinitionId, Types.INTEGER);
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.getConnection(DBUtil.getPortalDBConnection());
			dbconn.queryPrepareStatement(QUERY_LOCALIZED_REPORT_NAME, params);
			dbconn.rs.next();
			String reportName = dbconn.rs.getString("LOCALIZED_NAME");
			logger.debug("Report  - " +  reportDefinitionId + " get report name: " + reportName);
			return reportName;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}
	}
	
	private static final String UPDATE_PUBLISHED_STATUS = 
		"UPDATE JOB_RUNLOG SET STATUS_CODE = ?, PUBLISHED_ON  = ?,  LAST_UPDATED_ON = ? WHERE JOB_RUNLOG_ID = ?";
	
	public void updateJobPublishedStatus(Long logId) {
		DBParameter[] params = new DBParameter[4]; 
		int i=0;
		params[i++]= new StringDBParameter(RunLogStatusEnum.PUBLISHOK.getStatusCode(), Types.VARCHAR);
		params[i++]= new DatetimeDBParameter(new Date(), Types.DATE);
		params[i++]= new DatetimeDBParameter(new Date(), Types.DATE);
		params[i++]= new Int32DBParamter(logId, Types.INTEGER);
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.getConnection(DBUtil.getPortalDBConnection());
			dbconn.executePrepareStatement(UPDATE_PUBLISHED_STATUS, params);
			logger.debug("Report job " + logId + " set published" + RunLogStatusEnum.PUBLISHOK.getStatusCode());
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.close();
		}
	}


	@Override
	public Integer getReportUniqueId() {
		DBConnection dbconn= new DBConnection();
		try
		{
			dbconn.getConnection(DBUtil.getPortalDBConnection());
			dbconn.openQueryRS(GET_JOB_RUNLOG_ID);
			dbconn.rs.next();
			Integer id = dbconn.rs.getInt("JOB_RUNLOG_ID");
			return id;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.closeQueryRS();
			dbconn.close();
		}		
	}
}
