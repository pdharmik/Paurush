package com.lexmark.reportScheduler.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import com.lexmark.domain.ReportJob;
import com.lexmark.domain.ReportProperties;
import com.lexmark.enums.RunLogStatusEnum;
import com.lexmark.reportScheduler.service.DocumentumService;
import com.lexmark.reportScheduler.service.ReportGenerateService;
import com.lexmark.reportScheduler.util.BeanFactoryUtil;
import com.lexmark.service.api.ReportScheduleService;
import com.lexmark.service.impl.real.jdbc.ReportScheduleServiceImp;

public class ReportEngine extends Thread {
	private static Logger logger = LogManager.getLogger(ReportEngine.class);
	private CountDownLatch threadsSignal;  

	private ReportScheduleService  scheduleService;
	
	private List<ReportJob> jobList = new ArrayList<ReportJob>();
	private String jobBatchId;
	private int threadGroupId;
	private ReportGenerateService reportGenerator ;
	private DocumentumService documentumServcice;
	private Date expirationDateTime;
	private boolean jobNeedDeferred = false;
	public ReportEngine(CountDownLatch threadsSignal, String batchId, int threadGroup, Date expirationDateTime) {  
		this.threadsSignal = threadsSignal;  
		this.jobBatchId = batchId;
		this.threadGroupId = threadGroup;
		this.expirationDateTime = expirationDateTime;
		this.setName("report thread " + threadGroupId);
		scheduleService = (ReportScheduleService) BeanFactoryUtil.getBean("reportScheduleService");
		reportGenerator = (ReportGenerateService)BeanFactoryUtil.getBean("reportGenerator");
		documentumServcice = (DocumentumService )BeanFactoryUtil.getBean("documentumServcice");
	}  


	@Override
	public void run() {
		// do the work
		if(logger.isDebugEnabled()) {
			logger.debug(Thread.currentThread().getName() + " starts to run");
		}
		//get the job list which need to run in current thread
		jobList = scheduleService.getReportJobsForThread(jobBatchId, threadGroupId);
		if(logger.isDebugEnabled()) {
			logger.debug("In the thread group " + threadGroupId + " there are " +  jobList.size() + "jobs need to run");
		}
		
		for(ReportJob job : jobList) {
			try {
				logger.debug("------------- in Report Engine Run Try getIsLBSAccount is ---------------"+job.getIsLBSAccount());
				Long jobId = job.getJobRunLogId();
				job.setRunDateTime(new Date());
				// TODO handle deferred
				if(jobNeedDeferred) {
					job.setStatusCode(RunLogStatusEnum.DEFERRED.getStatusCode());
					runtimeLimitExceededHander(job);
					continue;
				}
				// handle the parameter
				handleReportParameters(job, jobList);
				// generate report from boxi
				job.setBuildStartOn(new Date());
				job.setStatusCode(RunLogStatusEnum.BUILDSUBMIT.getStatusCode());
				scheduleService.updateJobBuildingStatus(jobId);

				try {
					// call BOXI server to generate report
					boolean retValueOfFile ;
					retValueOfFile  = generateReport(job);
					if(retValueOfFile == true){
						logger.debug("<<<----retValueOfFile TRUE--->>>");
						logger.debug("before updating the jobbuilding status--->>>"+retValueOfFile);
						scheduleService.updateJobBuiltStatus(jobId, true, null);
						logger.debug("after updating the jobbuilding status--->>>");
						job.setBuildFinishOn(new Date());
						job.setStatusCode(RunLogStatusEnum.BUILDOK.getStatusCode());
						logger.debug("<<<----retValueOfFile TRUE--->>>");
					}else{
						logger.debug("<<<----retValueOfFile Continue--->>>");
						logger.debug("retValueOfFile--->>>"+retValueOfFile);
						logger.debug("before updating the jobbuilding status--->>>"+retValueOfFile);
						//scheduleService.updateJobBuiltStatus(jobId, true, null);
						scheduleService.updateJobBuildingStatus(jobId);
						logger.debug("after updating the jobbuilding status--->>>");
						job.setBuildFinishOn(new Date());
						job.setStatusCode(RunLogStatusEnum.BUILDSUBMIT.getStatusCode());
						logger.debug("<<<----retValueOfFile Continue--->>>");
						//------------------commented for my boxi
						//continue;  
					}
				}catch(Exception ex) {
					String buildStatusMessage = ex.getMessage();
					logger.error("fail to build report for job- " + jobId + " " + buildStatusMessage);
					scheduleService.updateJobBuiltStatus(jobId, false, buildStatusMessage);

					job.setBuildFinishOn(new Date());
					job.setStatusCode(RunLogStatusEnum.BUILDERROR.getStatusCode());
					job.setBuildStatusCodeMessage(buildStatusMessage);
					continue;
				}
				// --------- commented started for my boxi ----------------
				// load report to Documentum
				/*logger.debug("befor the setLoadstarton in ReportEngine--->>>");
				job.setLoadStartOn(new Date());
				job.setStatusCode(RunLogStatusEnum.LOADSUBMIT.getStatusCode());
				logger.debug("after the setLoadstarton in ReportEngine--->>>");

				try {
					logger.debug("before the loadReport in ReportEngine--->>>");
					String objectId = loadReport(jobId);
					logger.debug("after the loadReport in ReportEngine--->>>");
					job.setFileObjectId(objectId);
					scheduleService.updateJobLoadedStatus(jobId, objectId, true, null);
					logger.debug("before the setStatusCode in ReportEngine--->>>");
					job.setLoadFinishOn(new Date());
					job.setStatusCode(RunLogStatusEnum.LOADOK.getStatusCode());
					logger.debug("after the setStatusCode in ReportEngine--->>>");
				}catch(Exception ex) {
					String loadStatusMessage = ex.getMessage();
					logger.error("fail to load report to Documentum for job-" + jobId);
					scheduleService.updateJobLoadedStatus(jobId, null, false, loadStatusMessage);
					
					job.setLoadFinishOn(new Date());
					job.setStatusCode(RunLogStatusEnum.LOADERROR.getStatusCode());
					job.setLoadStatusCodeMessage(loadStatusMessage);
					continue;
				}
				// update the report status to wait for pub
				logger.debug("before the updateJobWaitForPushStatus in ReportEngine--->>>");
				scheduleService.updateJobWaitForPushStatus(job.getFileDataLink());
				job.setStatusCode(RunLogStatusEnum.WAITFORPUB.getStatusCode());
				logger.debug("after the updateJobWaitForPushStatus in ReportEngine--->>>");*/
				
				// --------- commented ended for my boxi ----------------
				
				Date currentDate = new Date();
				if(currentDate.after(this.expirationDateTime)
						&& noOtherReportTypeToHandle(job.getFileDataLink())) {
					// all the following job will be set deferred status
					jobNeedDeferred = true;
					continue;
				}
				
			}
			catch(Exception ex) {
				logger.error(ex);
				job.setErrorMessage(ex.getMessage());
				try {
					scheduleService.logJobErrorMessage(job.getJobRunLogId(), ex.getMessage());
				} catch (Exception e) {
					// if there message is too long , there will be some jdbc exception
				}
			}
		}
		if(logger.isDebugEnabled()) {
			logger.debug(Thread.currentThread().getName() + " finished");
		}
		threadsSignal.countDown();
	}
	
	private boolean noOtherReportTypeToHandle(String fileDataLink) {
		boolean noOtherReportTypeToHandle = true;
		for(ReportJob job : jobList) {
			if(fileDataLink.equals(job.getFileDataLink()) 
					&&!RunLogStatusEnum.WAITFORPUB.getStatusCode().equalsIgnoreCase(job.getStatusCode())) {
				logger.debug("When expriatetion time out, still need to handle report job - " + job.getJobRunLogId());
				return false;
			}
		}
		logger.debug("When expriatetion time out, all the reports with file link - " + fileDataLink + " have been handled");
		return noOtherReportTypeToHandle;
	}
	
	public void runtimeLimitExceededHander(ReportJob job) {
		logger.debug("job: " + job.getJobRunLogId() + " need changed to deferred status");
		scheduleService.updateDeferredStatus(job.getJobRunLogId());
	}
	
	public void handleReportParameters(ReportJob job, List<ReportJob> jobList) {
		logger.debug("query and back parameter for job: " + job.getJobRunLogId());
		scheduleService.handleReportParameters(job, jobList);
	}
	
	public boolean generateReport(ReportJob job) {
		String reportSourceId = scheduleService.getReportSourceId(job.getReportScheduleId());
		job.setReportSourceId(reportSourceId);
		boolean retValue ;
		retValue = reportGenerator.generate(job);
		return retValue;
	}
	
	// -------------------------- commented start for my boxi ----------------------------------
	/*public String loadReport(Long jobId) {
		byte[] content = reportGenerator.getReportContent();
		if(content == null) {
			logger.error("report content stream is null");
			throw new RuntimeException("report content stream is null");
		}
		logger.debug("The Byte array in Reportengine :-->>>>"+content.length);
		Long contentSize = Long.valueOf(content.length);
		scheduleService.updateJobLoadingStatus(jobId, contentSize);
		ReportProperties reportProperties = scheduleService.loadReportMetaData(jobId);
		
		return documentumServcice.createDocument(reportProperties, content);
	}*/
	// -------------------------- commented end for my boxi ----------------------------------

	public int getThreadGroupId() {
		return threadGroupId;
	}

	public List<ReportJob> getJobList() {
		return jobList;
	}
}
