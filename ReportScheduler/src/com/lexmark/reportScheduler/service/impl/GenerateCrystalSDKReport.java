package com.lexmark.reportScheduler.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import oracle.sql.DATE;

import com.crystaldecisions.sdk.exception.SDKException;
import com.crystaldecisions.sdk.framework.CrystalEnterprise;
import com.crystaldecisions.sdk.framework.IEnterpriseSession;
import com.crystaldecisions.sdk.occa.infostore.CeScheduleType;
import com.crystaldecisions.sdk.occa.infostore.IDestination;
import com.crystaldecisions.sdk.occa.infostore.IDestinationPlugin;
import com.crystaldecisions.sdk.occa.report.data.IParameterFieldDiscreteValue;
import com.crystaldecisions.sdk.occa.infostore.IInfoObjects;
import com.crystaldecisions.sdk.occa.infostore.IInfoStore;
import com.crystaldecisions.sdk.occa.infostore.ISchedulingInfo;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase;
import com.crystaldecisions.sdk.plugin.desktop.common.IReportFormatOptions;
import com.crystaldecisions.sdk.plugin.desktop.common.IReportParameter;
import com.crystaldecisions.sdk.plugin.desktop.common.IReportParameterSingleValue;
import com.crystaldecisions.sdk.plugin.desktop.common.IReportParameterValue.DateFormat;
import com.crystaldecisions.sdk.plugin.desktop.report.IReport;
import com.crystaldecisions.xml.serialization.IXMLSerializable;
import com.crystaldecisions.sdk.plugin.destination.diskunmanaged.IDiskUnmanagedOptions;
import com.lexmark.domain.ReportJob;
import com.lexmark.domain.ReportParameters;
import com.lexmark.reportScheduler.constants.ReportConstant;
import com.lexmark.reportScheduler.util.ResourceUtil;
import com.lexmark.util.report.PropertiesUtil;


public class GenerateCrystalSDKReport {

	private static Logger logger = LogManager.getLogger(GenerateCrystalSDKReport.class);
	private ReportJob job;	
	/*private static final String BO_USERNAME = "lsportal";
	private static final String BO_PASSWORD = "e@Y$#aR2c";
	private static final String BO_CMS_NAME = "qlexwbobj003.na.ds.lexmark.com:6400";*/
//	private static final String BO_CMS_NAME = "https://business-intelligence-qa.lexmark.com/BOE/BI";  Commented as URL is changed
	/*private static final String BO_AUTH_TYPE = "secLDAP";*/
	private static final String BO_REPORT_NAME = null;
	private static final String BO_FOLDER_NAME = null;
	private static String folderName_RunlogID = null;
	static String folderNameToPass = null;
	IReportParameter oReportParameter, oReportParameterValue;
	List<ReportParameters> jobList = null;

	protected GenerateCrystalSDKReport(ReportJob job){		
		this.job = job;
		logger.debug("The jobs size in GenerateCrystalSDKReport constuctor >>>>>>:"+job.getParameterList().size());
		//
		folderName_RunlogID = Long.toString(job.getJobRunLogId());
		logger.debug("The Source Id is >>>>>>:"+job.getReportSourceId());
		logger.debug("The MDM Id is >>>>>>:"+job.getMdmId());
		logger.debug("The MDM Level is >>>>>>:"+job.getMdmLevel());	
		logger.debug("The ReportRunKey is >>>>>>:"+job.getJobRunLogId());	
		logger.debug("The FILE TYPE IS :>>>>>>>>>>>>>>>>"+job.getFileType().getType());
		logger.debug("The LBSACCOUNT FLAG IS :>>>>>>>>>>>>>>>>"+job.getIsLBSAccount());
		
		int listSize = job.getParameterList().size();
		jobList = job.getParameterList();
		for(ReportParameters rptparam : jobList){
			logger.debug("ParameterName : ParameterValue - "+rptparam.getName()+":"+rptparam.getValue());			
		}
		//getJobParameter(job);
		//genReport(); // added extra
	}
	protected List<ReportParameters> getJobParameter(ReportJob job){
		logger.debug("The parameter size is:"+job.getParameterList().size());
		return job.getParameterList();
	} 
	public String genReport() throws IOException, ReportSDKExceptionBase, SQLException {
		logger.debug("---It is inside the genReport---");
		// TODO Auto-generated method stub
				/*String boUser = "lsportal";
				String boPassword = "e@Y$#aR2c";
				String boCmsName = "qlexwbobj003.na.ds.lexmark.com:6400";*/ //for QA  Commented as URL is changed
//				String boCmsName = "plexwbobj003.na.ds.lexmark.com:6400"; // for Prod
//				String boCmsName = "https://business-intelligence-qa.lexmark.com/BOE/BI"; //for QA  Commented as URL is changed
				//String boAuthType = "secLDAP";
				//added by sbag
				Map<String,String> boAuthenticationDetails = ResourceUtil.getBOAuthenticationDetails();
				logger.debug("Username is--->>>"+boAuthenticationDetails.get("boUser"));
				String boUser = boAuthenticationDetails.get(ReportConstant.boUser);
				String boPassword = boAuthenticationDetails.get(ReportConstant.boPassword);
				String boCmsName = boAuthenticationDetails.get(ReportConstant.boCmsName);
				String boAuthType = boAuthenticationDetails.get(ReportConstant.boAuthType);
				logger.debug("The authentication details are as below--->>>"+boPassword+"----"+boCmsName+"---"+boAuthType);
				//ends here
				String reportName = null;
				String destinationFolder = null;
				try {
					
					IEnterpriseSession enterpriseSession = CrystalEnterprise.getSessionMgr().logon( boUser, boPassword, boCmsName, 
							boAuthType);
				    //Obtain the InfoStore.
				    IInfoStore infoStore = (IInfoStore)enterpriseSession.getService("", "InfoStore");
				    logger.debug("job.getReportSourceId()--------->>>>"+job.getReportSourceId());
				    //Query for the report object in the CMS.  See the Developer Reference guide for more information the query language.  
				    IInfoObjects oInfoObjects = (IInfoObjects)infoStore.query("SELECT TOP 1 * " + 
				    										     			  "FROM CI_INFOOBJECTS " + 
				    										     			  "WHERE SI_PROGID = 'CrystalEnterprise.Report' AND SI_INSTANCE=0 AND SI_ID='"+
				    										     			  job.getReportSourceId()+"'"
				    										     			  //148767+"'"
				    										     			  //+"AND SI_KIND='CeKind.CRYSTAL_REPORT' " 
				    										     			  );
				
				    if (oInfoObjects.size() > 0) {
				    	
					    //Call local utility function to carry out the scheduling work.  
				    	destinationFolder = scheduleReports(oInfoObjects, infoStore);    	
					    logger.debug( " Report has been Created .");			    
			    	}
			    	else {			    		
			    		logger.debug("Report " +" not found.");	
			    	}

			    }
			    catch(SDKException sdkEx) {
			    	logger.debug(sdkEx);   
			    }
				return destinationFolder;
	}
	
	private String scheduleReports(IInfoObjects oInfoObjects, IInfoStore infoStore) throws SDKException, IOException, SQLException {	
		//Grab the first object in the collection, this will be the object that will be scheduled.tring 
		String desFolderName = null;
		try {
			logger.debug("iNSIDE THE SCHEDULErEPORT");
			IReport oReport = (IReport)oInfoObjects.get(0);
			logger.debug("iNSIDE THE SCHEDULErEPORT----111");
			IReportFormatOptions reportFormat = oReport.getReportFormatOptions(); 
			logger.debug("iNSIDE THE SCHEDULErEPORT---122222");
			//Set report format.
			// new edition
			int formatType = 0;
			job.getFileType().getType();
			if(job.getFileType().getType().equalsIgnoreCase("XLS")){
				formatType = IReportFormatOptions.CeReportFormat.EXCEL_DATA_ONLY;				
				logger.debug("HERE THE REPORT FORMAT IS XLS >>>>>>");
			}
			if(job.getFileType().getType().equalsIgnoreCase("PDF")){
				formatType = IReportFormatOptions.CeReportFormat.PDF;
				logger.debug("HERE THE REPORT FORMAT IS PDF >>>>>>");
			}
			//end of new edition
			//int formatType = IReportFormatOptions.CeReportFormat.PDF;
			reportFormat.setFormat(formatType);
			logger.debug("The report formt option is :"+reportFormat.getFormat());
			
			//Set parameters for this report.  
			setReportParameters(oReport);
			logger.debug(" ======================== End of setReportParamerts ========================");
			ISchedulingInfo schedInfo = oReport.getSchedulingInfo();
			logger.debug("iNSIDE THE SCHEDULErEPORT---444444");
			schedInfo.setRightNow(true);
			logger.debug("iNSIDE THE SCHEDULErEPORT-----444455555");
			schedInfo.setType(CeScheduleType.ONCE);
			logger.debug(" The schedule runs from" + schedInfo.getBeginDate()+" at time zone : "+schedInfo.getTimeZone());
			IDestinationPlugin destinationPlugin = (IDestinationPlugin) getDestinationPlugin(infoStore, formatType);	    
			//Schedule the InfoObjects.
			//infoStore.schedule(oInfoObjects);
			IDestination destination = schedInfo.getDestination();
			logger.debug("INSIDE THE SCHEDULEREPORT");
			destination.setFromPlugin(destinationPlugin);
			logger.debug("<----- Before the casting from IDestinationPlugin to IDestinationPluginObject ------>");
			//IDestinationPluginObject iDestinationPluginObject = (IDestinationPluginObject)destinationPlugin;
			logger.debug("--***** folderNameToPass *****--: ");
			logger.debug("The des is -----: "+folderNameToPass);
			//Schedule the InfoObjects.
			//desFolderName = iDestinationPluginObject.getFolderName();
			infoStore.schedule(oInfoObjects);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("Exception in scheduleReports"+e.getMessage());
			e.printStackTrace();
		}
		//return oReport.getID();
		return folderNameToPass;
		} 

	private void setReportParameters(IReport oReport) throws SDKException, IOException {
		// Retrieve the lsit of parameters on the report
		try {
			logger.debug("The inside params set values --->");
			List paramList = oReport.getReportParameters();
			logger.debug("The inside params set values --->"+paramList.size());
			// Create an IReportParameter interface
			//oReportParameter = new IReportParameter();
			IReportParameterSingleValue currentValue = null;

			//Values that will be set for each type of parameter.  All values are specified as strings and converted internally
			//to the appropriate Crystal type.			
			   	
			/*String lang = "en_US";
			String cntry = "US";
			String MDM_LEVEL = "Global";
			String MDM_ID = "006961700";  // Aditya - 148767   // old workingone -165679 // 6681 
			String CHL = "NOVALUE";
			String Agreement_Type = "ALL";  //MPS
*/			String LifeCycle = " ALL";
			
			for (int i=0; i < paramList.size(); i++) {
				IReportParameter oParameter = (IReportParameter)paramList.get(i);	
			    for(ReportParameters rptParam : jobList){
			    	if(oParameter.getParameterName().equalsIgnoreCase(rptParam.getName())){
			    		logger.debug("The oParameter ---->>>>:"+oParameter.getParameterName());
			    		if(oParameter.getParameterName().equalsIgnoreCase("LifeCycle")){
			    			currentValue = oParameter.getCurrentValues().addSingleValue();
						    currentValue.setValue(LifeCycle);
						    logger.debug("The Parameter set for >>>>"+rptParam.getName()+" is "+rptParam.getValue());
			    		}else if(oParameter.getParameterName().equalsIgnoreCase("StartDate")
			    				|| oParameter.getParameterName().equalsIgnoreCase("EndDate")){
			    			currentValue = oParameter.getCurrentValues().addSingleValue();
						    currentValue.setValue("Date("+rptParam.getValue()+")");
						    logger.debug("The Parameter set for Date >>>>"+rptParam.getName()+" is "+"Date("+rptParam.getValue()+")");
			    		}			    		
			    		else{
				    	  	currentValue = oParameter.getCurrentValues().addSingleValue();
						    currentValue.setValue(rptParam.getValue());
						    logger.debug("The Parameter set for "+rptParam.getName()+" is "+rptParam.getValue());
			    		} 			    	
			    	}
			    }
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("error in setReportparam..");
			e.printStackTrace();
		}
	}
	
	private static IDestinationPlugin getDestinationPlugin(
		IInfoStore infoStore, int formatType) throws SDKException {
		
		//Retrieve the UnmanagedDisk Destination plugin from the InfoStore
			    //this should be cast as an IDestinationPlugin *DON'T FORGET THE get(0) AT THE END**
		logger.debug("Inside the idestinationplugin :"+folderName_RunlogID);
		String fileExt = getFileExtensionByType(formatType);
		logger.debug("outside the idestinationplugin");
		String folder ="\\CrystalReportSDKReport";
		Map<String,String> reportLocationPath = ResourceUtil.getBOAuthenticationDetails();
		//String folderPathWithoutIP = "\\\\uslexna1t2\\siebelftp_dev\\SWPORTAL\\SRIMPORT\\";
		//String folderPathWithoutIP = "\\\\157.184.96.143\\siebelftp_dev\\SWPORTAL\\SRIMPORT\\"; // for QA
		String folderPathWithoutIP = reportLocationPath.get(ReportConstant.reportLocationPath).trim();
//		String folderPathWithoutIP = "\\\\PLEXWVNXCIFS02.na.ds.lexmark.com\\siebel_fs_a_siebelftp\\SWPORTAL\\SRIMPORT\\"; // for prod
		
		logger.debug("Setting the reporename and folderpath --");
		IDestinationPlugin destinationPlugin = null;
		try {			
			logger.debug("Inside the idestinationplugin----");
			destinationPlugin = (IDestinationPlugin)infoStore.query("SELECT TOP 1 * " + 
																					   "FROM CI_SYSTEMOBJECTS " +
																					   "WHERE SI_NAME='CrystalEnterprise.DiskUnmanaged'").get(0);

			//Retrieve the Scheduling Options and cast it as IDiskUnmanagedOptions.
			//This interface is the one which allows us to add the file location for the scheduling.
			IDiskUnmanagedOptions diskUnmanagedOptions = (IDiskUnmanagedOptions) destinationPlugin.getScheduleOptions();
			List listDestination = diskUnmanagedOptions.getDestinationFiles();
		    String username = "";
		    String password = "";
		    logger.debug("The username"+username+"/"+password);
		    diskUnmanagedOptions.setUserName(username);
		    diskUnmanagedOptions.setPassword(password);	
		    Calendar now = Calendar.getInstance();
		    
		    /*String rptFolderName = "CrystalReportFolder"+now.get(Calendar.YEAR)+"_"+now.get(Calendar.MONDAY)+"_"
		    		+now.get(Calendar.DATE)+"_"+now.get(Calendar.HOUR)+"_"+now.get(Calendar.MINUTE)+
		    		"_"+now.get(Calendar.MILLISECOND);*/
		    
		    String rptFolderName = folderName_RunlogID;
		    folderNameToPass = rptFolderName;
		    logger.debug("The des folder is :"+rptFolderName);
			logger.debug(" the ext is :"+fileExt+"\n");
			
			//String location = "\\\\10.188.123.102\\d$\\test_adityaUrl" + fileExt;
			String location = folderPathWithoutIP+rptFolderName+folder+ fileExt;
			logger.debug(" location is :"+location);
			try{
				listDestination.add(location);
			}catch (Exception ex)
			{
				logger.debug(">>>ERROR IN DESTINATION FOLDER:"+ex.getMessage());	
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("getDestinationPlugin method"+e.getMessage());
			e.printStackTrace();
		}

	    return  destinationPlugin;     
		// TODO Auto-generated method stub
		//return null;
	
	}
	private static String getFileExtensionByType(int reportType) {
		
		logger.debug("This is inside the getFileExtensionByType  --->");
		String fileExt = "";
		
		switch (reportType) {
				case IReportFormatOptions.CeReportFormat.CRYSTAL_REPORT:
	                fileExt = ".rpt";
	                break;
	            case IReportFormatOptions.CeReportFormat.EXCEL:
	                fileExt = ".xls";
	                break;
	            case IReportFormatOptions.CeReportFormat.EXCEL_DATA_ONLY:
	                fileExt = ".xls";
	                break;     
	            case IReportFormatOptions.CeReportFormat.PDF:
	                fileExt = ".pdf";
	                break;
	            case IReportFormatOptions.CeReportFormat.RTF:
	                fileExt = ".rtf";
	                break;
	            case IReportFormatOptions.CeReportFormat.RTF_EDITABLE:
	                fileExt = ".rtf";
	                break;
	 			case IReportFormatOptions.CeReportFormat.TEXT_CHARACTER_SEPARATED:
	                fileExt = ".txt";
	                break;
	            case IReportFormatOptions.CeReportFormat.TEXT_PAGINATED:
	                fileExt = ".txt";
	                break;
	            case IReportFormatOptions.CeReportFormat.TEXT_PLAIN:
	                fileExt = ".txt";
	                break;
	            case IReportFormatOptions.CeReportFormat.TEXT_TAB_SEPARATED:
	                fileExt = ".txt";
	                break;
	            case IReportFormatOptions.CeReportFormat.TEXT_TAB_SEPARATED_TEXT:
	                fileExt = ".txt";
	                break;
	            case IReportFormatOptions.CeReportFormat.USER_DEFINED:
	                fileExt = "";
	                break;                            
	            case IReportFormatOptions.CeReportFormat.WORD:
	                fileExt = ".doc";
	                break;
	            default:
	                fileExt = ".rpt";
	                break;
	         }
		logger.debug("The file ext is :"+fileExt);
		
		return fileExt;
		
	}


}
