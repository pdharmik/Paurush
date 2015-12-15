package com.lexmark.reportScheduler.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.ReportJob;
import com.lexmark.reportScheduler.constants.ReportConstant;
import com.lexmark.reportScheduler.exception.ReportGenerateException;
import com.lexmark.reportScheduler.server.CheckReports;
import com.lexmark.reportScheduler.service.ReportGenerateService;
import com.lexmark.reportScheduler.util.ResourceUtil;
import com.lexmark.service.api.ReportScheduleService;
import com.lexmark.util.report.PropertiesUtil;

public class ReportGenerateServiceImpl implements ReportGenerateService{
	private static Logger logger = LogManager.getLogger(ReportGenerateServiceImpl.class);
	private ReportScheduleService  reportScheduleService;
	public ReportScheduleService getReportScheduleService() {
		return reportScheduleService;
	}

	public void setReportScheduleService(ReportScheduleService reportScheduleService) {
		this.reportScheduleService = reportScheduleService;
	}
	private byte[] content = null;
	//private static byte[] content = null;
	private String boxiURL = "";
	private boolean fileExistsBoolean = false;
	
	// added for my boxi
	static String folderString;
	

	public boolean generate(ReportJob job) {
		logger.debug(" It is inside the GENERATE---");
		GenerateCrystalSDKReport genReport = new GenerateCrystalSDKReport(job);
		try{
			logger.debug("Before calling the genReport --");
			folderString = genReport.genReport(); 			
			
			logger.debug("After calling the genReport with folder name -->>>>"+folderString);
//			String ReportLocstrExists = "/siebelftp_svcsweb/SWPORTAL/SRIMPORT/" + folderString; // This is for PROD
			//String ReportLocstrExists = "\\\\157.184.96.143\\siebelftp_dev\\SWPORTAL\\SRIMPORT\\"+folderString; // This is for QA
			
			//--------------------------------------- commented start for my boxi -------------------------------------------------
			/*Map<String,String> reportLocationPath = ResourceUtil.getBOAuthenticationDetails();
			String ReportLocstrExists = reportLocationPath.get(ReportConstant.reportMountPointUnix).trim()+"5092"; // This is for QA
			logger.debug("The folder file is--->>>"+ReportLocstrExists);
			//String ReportLocstrExists = "/siebelftp_svcsweb/SWPORTAL/SRIMPORT/53092";			
			File reportFileExists = new File(ReportLocstrExists);
			logger.debug("Waiting for the report to be created--->>>");
			//Thread.sleep(50000);
			logger.debug("Coming out after 50 sec---");
			logger.debug("The file --"+ReportLocstrExists+" == "+reportFileExists.exists());
			if(reportFileExists.exists()){
				logger.debug("The file --"+ReportLocstrExists+" == "+reportFileExists.exists());
				writeReportContent(folderString, job);
				fileExistsBoolean = true;
			}else{
				logger.debug("The file --"+ReportLocstrExists+" == "+reportFileExists.exists());
				fileExistsBoolean= false;
			}
			
			logger.debug("The Content size after the buytes written --->>>>"+content.length);*/	
			
			//----------------------------------------- commented end for my boxi ----------------------------------------------------
		}catch(Exception e){
			e.printStackTrace();
		}
		// will always return false now and hence update status_code as BUILDSUBMIT for JOB_RUNLOG_ID Table
		return fileExistsBoolean;
		}

	public byte[] getReportContent()  {	
		logger.debug("In the gerReportContent------>>>>>");
		logger.debug("The class is getReportContent--->"+content.length);					
		return content;
	}

	// changed for my boxi from private to public access
	public void writeReportContent(String folderName, ReportJob job) throws IOException {
		// TODO Auto-generated method stub
		logger.debug("Inside the write content---->>>>");	
		int i = 0;
		String ReportLocstr = null;
		InputStream ios = null;
		Map<String,String> reportLocationPath = ResourceUtil.getBOAuthenticationDetails();
		try {
			if(job.getFileType().getType().equalsIgnoreCase("XLS")){
				
				ReportLocstr = reportLocationPath.get(ReportConstant.reportMountPointUnix).trim()+folderName+reportLocationPath.get(ReportConstant.reportExcelFileName); // for QA
				//ReportLocstr = "/siebelftp_svcsweb/SWPORTAL/SRIMPORT/" + folderName + "/CrystalReportSDKReport.xls"; // for Prod
			}
			if(job.getFileType().getType().equalsIgnoreCase("PDF")){
				ReportLocstr = reportLocationPath.get(ReportConstant.reportMountPointUnix).trim()+folderName+reportLocationPath.get(ReportConstant.reportPDFFileName); //for QA
				//ReportLocstr = "/siebelftp_svcsweb/SWPORTAL/SRIMPORT/" + folderName + "/CrystalReportSDKReport.pdf"; //for Prod
			}
			//folderName = "CrystalReportFolder2012_10_12_3_19_815";
			//ReportLocstr = "/siebelftp_svcsweb/SRIMPORT/"+folderName+"/CrystalReportSDKReport.pdf";			
			//folderName = "CrystalReportFolder2012_10_12_3_19_815";
			logger.debug("The file exact location: "+ReportLocstr);
			File reportFile = new File(ReportLocstr);
			//File f = new File(folderName);
			logger.debug("File "+reportFile.exists()+" exists at :"+Calendar.getInstance().get(Calendar.MINUTE)
					+":"+Calendar.getInstance().get(Calendar.SECOND));		
			int loopCnt =0;
			int maxThreadHole = 0;
			while(reportFile.exists()){
				logger.debug("The file exists >>>>>>>@ :"+System.currentTimeMillis());	
				// commented for my boxi. No waiting required
				/*Thread.sleep(15000);
				maxThreadHole++;
				logger.debug("The file is NOT exists >>>>>>>@ :"+System.currentTimeMillis());
				if(reportFile.exists())	{*/									
					Long firstCheck = reportFile.lastModified();
					logger.debug("Lst modified :"+reportFile.lastModified());
					Thread.sleep(15000);
					Long secondCheck = reportFile.lastModified();
					if(firstCheck.equals(secondCheck)){
						logger.debug("File modification not happens in last 10 second -->>>>>");
						logger.debug("Lst modified >>>>:"+reportFile.lastModified());
						logger.debug("The file is now exists >>>>>>>"+" exists at :"+Calendar.getInstance().get(Calendar.MINUTE)
								+":"+Calendar.getInstance().get(Calendar.SECOND));
						content = new byte[(int)reportFile.length()];
						logger.debug("The file is now exists >>>>>>> and len >>>>>>>"+(int)reportFile.length());
				        ios = new FileInputStream(reportFile);
				        logger.debug("The file is now exists >>>>>>> #####");
				        ios.read(content);
				        logger.debug("the content is readable and size >>>>>: "+content.length);
				        ios.close();
				        
				        
				        // Delete the file after the bytearray creation
				        reportFile.delete();
				        logger.debug("After the file deletion -->>>>>"+reportFile.exists());
				        CheckReports checkReports = new CheckReports();
				        checkReports.updateDocumentum(job,content);
						break;
					}else{
						if(loopCnt <=3){
							// Check the file last modified before 10sec or not
							// if modified the hold for 15sec and again continue in the While loop
							Thread.sleep(10000);
							logger.debug("File modification happens  -->>>>>"+(10*i)+" seconds");
							i++;
							loopCnt++; //It was not there earlier
							continue;
						}
					}
					logger.debug("Lst modified :"+reportFile.lastModified());					
					//break;				
				//}
				break;
			}
				// commented for my boxi. No waiting for boxi 
				/*if(maxThreadHole > 20){
					// here we give max 18*10 sec to boxi-4 to generate a report
					// if within that time no file appears in the specified folder that 
					// exit the While loop 
					logger.debug("maxThreadHole :"+(maxThreadHole*10));	
					break;
				}else{
					logger.debug("maxThreadHole :"+(maxThreadHole*10));	
					continue;
				}*/
											
			//}
			
			//logger.debug("Lst modified >>>>:"+reportFile.lastModified());
			//Thread.sleep(100000);
			/*logger.debug("Lst modified >>>>:"+reportFile.lastModified());
			logger.debug("The file is now exists >>>>>>>"+" exists at :"+Calendar.getInstance().get(Calendar.MINUTE)
					+":"+Calendar.getInstance().get(Calendar.SECOND));
			content = new byte[(int)reportFile.length()];
			logger.debug("The file is now exists >>>>>>> and len >>>>>>>"+(int)reportFile.length());
	        ios = new FileInputStream(reportFile);
	        logger.debug("The file is now exists >>>>>>> #####");
	        ios.read(content);
	        logger.debug("the content is readable and size >>>>>: "+content.length);
	        ios.close();
	        
	        
	        // Delete the file after the bytearray creation
	        reportFile.delete();
	        logger.debug("After the file deletion -->>>>>"+reportFile.exists());
	        CheckReports checkReports = new CheckReports();
	        checkReports.updateDocumentum(job,content);*/
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//ios.close();
			logger.debug("in report generate service impl finally block");
		}
		
	}

	public void downloadReportContent(String url) {		
		// TODO Auto-generated method stub
		
	}	
}
