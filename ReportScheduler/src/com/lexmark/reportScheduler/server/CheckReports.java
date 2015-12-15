package com.lexmark.reportScheduler.server;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;

import com.lexmark.constants.ReportFileType;
import com.lexmark.domain.ReportJob;
import com.lexmark.domain.ReportProperties;
import com.lexmark.enums.RunLogStatusEnum;
import com.lexmark.reportScheduler.service.DocumentumService;
import com.lexmark.reportScheduler.service.ReportGenerateService;
import com.lexmark.reportScheduler.service.impl.ReportGenerateServiceImpl;
import com.lexmark.reportScheduler.util.BeanFactoryUtil;
import com.lexmark.reportScheduler.util.ResourceUtil;
import com.lexmark.service.api.ReportScheduleService;
import com.lexmark.service.impl.real.jdbc.DBConnection;
import com.lexmark.reportScheduler.constants.ReportConstant;
import java.io.File;


public class CheckReports extends Thread {
	private ReportGenerateService reportGenerator ;
	private DocumentumService documentumServcice;
	//private ReportGenerateServiceImpl reportGenerateServiceImpl;
	private ReportScheduleService  scheduleService;
	private boolean fileExistsBoolean = false;
	private static Logger logger = LogManager.getLogger(ReportGenerateServiceImpl.class);
	ReportGenerateServiceImpl repGenImpl = new ReportGenerateServiceImpl();
	public void run(){
		logger.debug("----------------------------in check reports----------------------------------");
		
		  DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
			Calendar cal = Calendar.getInstance();
			//Substract one day to current date.
			cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -15);
			logger.debug("current date "+dateFormat.format(cal.getTime()));
		    String query = "select JOB_RUNLOG_ID,FILE_TYPE,FILE_DATA_LINK from JOB_RUNLOG WHERE STATUS_CODE = 'BUILDSUBMIT' AND BUILD_STARTED_ON > ?";
		    logger.debug("query ready");
			
			DBConnection dbconn= new DBConnection();
			try
			{
				dbconn.open();
				logger.debug("IN TRY");
				PreparedStatement pStmt = 
						dbconn.prepareStatement(query);
				// to be uncommented
				pStmt.setString(1, dateFormat.format(cal.getTime()));
				//pStmt.setString(1, "08-DEC-14");  hardcoded for testing purpose
				ResultSet rs = pStmt.executeQuery();
				logger.debug("BEFORE RS.NEXT ");
				
				
				List<ReportJob> jobList = new ArrayList<ReportJob>();
				String JOB_RUNLOG_ID = "";
				while (rs.next()) {
		            JOB_RUNLOG_ID = rs.getString("JOB_RUNLOG_ID");
		            String JOB_FILE_TYPE = rs.getString("FILE_TYPE");
		            String FILE_DATA_LINK = rs.getString("FILE_DATA_LINK");
		            ReportJob job = new ReportJob();
		            job.setJobRunLogId(Long.parseLong(JOB_RUNLOG_ID));
		            job.setFileType(ReportFileType.instance(JOB_FILE_TYPE));	
		            job.setFileDataLink(FILE_DATA_LINK);
		            jobList.add(job);
		            logger.debug("JOB_RUNLOG_ID is -->>>>>>> "+JOB_RUNLOG_ID);
		            logger.debug("JOB_FILE_TYPE is -->>>>>>> "+JOB_FILE_TYPE);
		            
		        }
				
				int i=0;
				for(ReportJob job1 : jobList){
					i++;
					logger.debug("no of loop "+i);
					String Runlog_Id = job1.getJobRunLogId().toString();
					logger.debug("runlog Id in for is --->> "+Runlog_Id);
					logger.debug("fileType in for is --->> "+job1.getFileType().getType());
					Map<String,String> reportLocationPath = ResourceUtil.getBOAuthenticationDetails();
					String ReportLocstrExists = reportLocationPath.get(ReportConstant.reportMountPointUnix).trim(); // This is for QA
					logger.debug("The folder file is--->>>"+ReportLocstrExists);
					//String ReportLocstrExists = "/siebelftp_svcsweb/SWPORTAL/SRIMPORT/53092";			
					File reportFileExists = new File(ReportLocstrExists);
					logger.debug("Waiting for the report to be created--->>>");
					//Thread.sleep(50000);
					logger.debug("Coming out after 50 sec---");
					logger.debug("The file --"+ReportLocstrExists+" == "+reportFileExists.exists());
					if(reportFileExists.exists()){
						logger.debug("The file exist --"+ReportLocstrExists+" == "+reportFileExists.exists());
						
						repGenImpl.writeReportContent(Runlog_Id, job1);
						fileExistsBoolean = true;
					}else{
						logger.debug("The file --"+ReportLocstrExists+" == "+reportFileExists.exists());
						fileExistsBoolean= false;
					}
					
					//logger.debug("The Content size after the buytes written --->>>>"+content.length);
				}
				logger.debug("-------------BEFORE INTERUPT------------- ");
				
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			} finally {
				dbconn.close();
				Thread.currentThread().stop();
			}
			/*try {
				Thread.sleep(10000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
		}
	
	public void updateDocumentum(ReportJob job,byte[] content){
		
		scheduleService = (ReportScheduleService) BeanFactoryUtil.getBean("reportScheduleService");
		reportGenerator = (ReportGenerateService)BeanFactoryUtil.getBean("reportGenerator");
		documentumServcice = (DocumentumService )BeanFactoryUtil.getBean("documentumServcice");
		// ------------------------------- Documentum call started for my boxi----------------------------------------
					// load report to Documentum
		logger.debug("content length in update documnetum "+content.length);
					logger.debug("befor the setLoadstarton in ReportEngine--->>>");
					job.setLoadStartOn(new Date());
					job.setStatusCode(RunLogStatusEnum.LOADSUBMIT.getStatusCode());
					logger.debug("after the setLoadstarton in ReportEngine--->>>");
					
					try {
						logger.debug("before the loadReport in ReportEngine--->>>");
						String objectId = loadReport(job.getJobRunLogId(),content);
						logger.debug("after the loadReport in ReportEngine--->>>");
						job.setFileObjectId(objectId);
						scheduleService.updateJobLoadedStatus(job.getJobRunLogId(), objectId, true, null);
						logger.debug("before the setStatusCode in ReportEngine--->>>");
						job.setLoadFinishOn(new Date());
						job.setStatusCode(RunLogStatusEnum.LOADOK.getStatusCode());
						logger.debug("after the setStatusCode in ReportEngine--->>>");
					}catch(Exception ex) {
						ex.printStackTrace();
						String loadStatusMessage = ex.getMessage();
						logger.error("fail to load report to Documentum for job-" + job.getJobRunLogId().toString());
						scheduleService.updateJobLoadedStatus(job.getJobRunLogId(), null, false, loadStatusMessage);
						
						job.setLoadFinishOn(new Date());
						job.setStatusCode(RunLogStatusEnum.LOADERROR.getStatusCode());
						job.setLoadStatusCodeMessage(loadStatusMessage);
					//	continue;
					}
					// update the report status to wait for pub
					logger.debug("before the updateJobWaitForPushStatus in ReportEngine--->>>");
					scheduleService.updateJobWaitForPushStatus(job.getFileDataLink());
					job.setStatusCode(RunLogStatusEnum.WAITFORPUB.getStatusCode());
					logger.debug("after the updateJobWaitForPushStatus in ReportEngine--->>>");
					//-------------------------------- Documentum Call ended for my boxi -----------------------------------------
	}
	
	public String loadReport(Long jobId,byte[] content) {
		try{
		//byte[] content = repGenImpl.getReportContent();
		
		if(content == null) {
			logger.error("report content stream is null");
			throw new RuntimeException("report content stream is null");
		}
		logger.debug("The Byte array in Reportengine :-->>>>"+content.length);
		Long contentSize = Long.valueOf(content.length);
		scheduleService.updateJobLoadingStatus(jobId, contentSize);
		ReportProperties reportProperties = scheduleService.loadReportMetaData(jobId);
		
		return documentumServcice.createDocument(reportProperties, content);
		}
	
		catch(Exception e){
			e.printStackTrace();
			return "error";
		
	}
	
	
}
}
