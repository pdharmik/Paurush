package com.lexmark.service.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lexmark.domain.ReportJob;
import com.lexmark.domain.ReportNotifierTO;
import com.lexmark.domain.ReportProperties;

public interface ReportScheduleService {
	/*
	 * get all the report needs to run
	 * @param startDateTime   start time to select
	 * @param endDateTime  end time to select
	 * @param type   SUBMITNOW, SCHEDULED
	 */
	public List<ReportJob>  getQulifiedReportJobs(Date startDateTime, Date endDateTime,  String selectFlag, String specificRunLogId);
	public String  getJobBatchId();

	/*
	 * Update the RERUN_FLAG = "X" for all the joblog status code in ('DEFERRED','LOADERROR','BUILDERROR','SELECTED',
	 *	'BUILDSUBMIT','BUILDOK','LOADOK','LOADSUBMIT')
	 */
	public void updateReRunFlag();
	
	public void updateDeferredStatus(Long runLogId);
	/*
	 * get file data link
	 */
	public String getFileDataLink();
	
	public Integer getReportUniqueId();
	
	public void insertReportLog(ReportJob job);
	
	public Map<Integer, Integer>  getStandardLeadTimes();
	
	public List<ReportJob> getReportJobsForThread(String batchId, Integer threadId);
	/*
	 *  This method will select report parameters from report paramters table and insert them into 
	 *  the REPORT_INSTANCE_PARAMETER table, the parameters will add into the job paramList
	 */
	public ReportJob handleReportParameters(ReportJob job, List<ReportJob> jobList);
	
	public  String getReportSourceId(Integer reportSchduleId);
	
	public void updateJobBuildingStatus(Long logId);
	
	public void updateJobBuildingURL(Long logId, String boxiURL);
	
	public void updateJobBuiltStatus(Long logId, boolean isSuccess, String message);
	
	public void updateJobLoadingStatus(Long logId, Long reportSize);
	
	public void updateJobLoadedStatus(Long logId, String objectId, boolean isSuccess, String message);
	
	public void updateJobWaitForPushStatus(String fileDataLink);
	
	/*
	 * Get report meta data , used when load report file to documentum
	 */
	public  ReportProperties loadReportMetaData(Long runLogId);
	
	public  void logJobErrorMessage(Long logId, String message);
	
	public List<ReportNotifierTO>  queryPublishedReports();
	
	public String getLocalizedReportName(Integer reportDefinitionId, String localeCode);
	
	public void updateJobPublishedStatus(Long logId);
	
}
