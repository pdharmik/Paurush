package com.lexmark.portlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lexmark.util.TimezoneUtil;
//import com.lexmark.util.CSVErrorHandler;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.BulkUploadContract;
import com.lexmark.contract.BulkUploadStatusContract;
import com.lexmark.contract.GlobalLegalEntityContract;
import com.lexmark.domain.BulkUploadStatus;
import com.lexmark.domain.LexmarkTransaction;
import com.lexmark.form.BulkUploadForm;
import com.lexmark.result.BulkUploadFileResult;
import com.lexmark.result.BulkUploadStatusFileResult;
import com.lexmark.result.BulkUploadStatusListResult;
import com.lexmark.result.GlobalLegalEntityResult;
import com.lexmark.service.api.BulkUploadService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.util.CSV2AssetConverter;
import com.lexmark.util.CSVError;
import com.lexmark.util.CSVErrorHanlder;
import com.lexmark.util.ColumnInfo;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.PerformanceTracker;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.ResourceResponseUtil;
import com.lexmark.util.ServiceStatusUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.TimezoneUtil;
import com.lexmark.util.XmlOutputGenerator;

@Controller
@RequestMapping("VIEW")
public class BulkUploadController extends BaseController {
	private static Logger logger = LogManager.getLogger(BulkUploadController.class);
	private static final String EXCEPTION_BULK_UPLOAD_FILE_NOT_FOUND = "exception.bulkUpload.fileNotFound";
	private static final String MESSAGE_BULK_UPLOAD_UPLOAD_CSV = "message.bulkUpload.upLoadCSVFile";
	private static final String EXCEPTION_BULK_UPLOAD_UPLOAD_FAILED = "exception.bulkUpload.uploadFailed";
	
	@Autowired
    private GlobalService  globalService;
	@Autowired
	private BulkUploadService bulkUploadService;
	@RequestMapping
	public String showBulkUploadPage(RenderRequest request, RenderResponse response, Model model) throws Exception {
		ResourceURL resOutPutURL = response.createResourceURL();
        resOutPutURL.setResourceID("outPutFileURL");
        model.addAttribute("outPutFileURL", resOutPutURL.toString());
		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID("bulkUploadLandingPageURL");
		model.addAttribute("bulkUploadLandingPageURL", resURL.toString());
		
		return "upload/bulkUploadLandingPage";
	}
	
	@RequestMapping(params = "action=importBulkUpload")
	public String importPageCounts(RenderRequest request, RenderResponse response, Model model) throws Exception{
		
		BulkUploadForm bulkUploadForm = new BulkUploadForm();
		bulkUploadForm.refreshSubmitToken(request);
		model.addAttribute("bulkUploadForm", bulkUploadForm);
		return "upload/importBulkUploadPage";
	}
	
	@RequestMapping(params = "action=doCSVUpload")
	public void doFileUpload(ActionRequest request, ActionResponse response, 
			@RequestParam("fileContent") CommonsMultipartFile content,@ModelAttribute("bulkUploadForm") BulkUploadForm bulkUploadForm,
			Model model) throws Exception {
		logger.debug("------------- Step 1---doFileUpload started---------["+System.nanoTime()+"]");
		PortletSession session = request.getPortletSession();
		BulkUploadContract contract = new BulkUploadContract();
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		float timezoneOffset = 0;
		
		
		
		
		logger.info("1111111111111111111111 timezoneOffset value is "+timezoneOffset);
		 String timezoneOffsetStr = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		 
		if (timezoneOffsetStr != null)
			timezoneOffset = Float.valueOf(timezoneOffsetStr).floatValue();
			
         logger.info("1111111111111111111111 timezoneOffset value is "+timezoneOffset);
		
		
		String userId = PortalSessionUtil.getUserNumber(session);
		logger.debug(" userId From PortalUtil::::::::::::::::::::::::::; "+userId);
		
		try {
			logger.debug("Inside the try bloack");
			contract.setSessionHandle(crmSessionHandle);
			String topLevelMdmId = null;
			String topLevelMdmLevel = null;
			if (PortalSessionUtil.getMdmLevel(session).equalsIgnoreCase(LexmarkConstants.GLOBAL)){
				//Already at the top level
				topLevelMdmId = PortalSessionUtil.getMdmIdOrDunsBasedOnLevel(session);
				topLevelMdmLevel = PortalSessionUtil.getMdmLevel(session);
				logger.debug("Already at the top level");
			}else{
				//Need to get the top level for this person's MDM ID and Level
				GlobalLegalEntityContract globalLegalEntityContract = new GlobalLegalEntityContract();
				globalLegalEntityContract.setMdmId(PortalSessionUtil.getMdmId(session));
				globalLegalEntityContract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));

				LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveGlobalLegalEntity", 
						PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
				GlobalLegalEntityResult globalLegalEntityResult = globalService.retrieveGlobalLegalEntity(globalLegalEntityContract);
				PerformanceTracker.endTracking(lexmarkTran);

				topLevelMdmId = globalLegalEntityResult.getAccount().getMdmId();
				topLevelMdmLevel = globalLegalEntityResult.getAccount().getMdmLevel();
				logger.debug("------------- Retrieve Global Legal Entity---------["+topLevelMdmId+"]");
			}
			
			// This userId is set to filter the import history with user uploaded files rather then MDMID
			if (userId!=null){
				contract.setMdmId(userId);
			}else{
				contract.setMdmId(topLevelMdmId);
			}

			String fileName = StringUtil.removeSpaces(content.getOriginalFilename().trim());
			logger.debug("------------- Original File Name ---------"+ fileName);
			fileName=fileName.replaceAll("\\(", "").replaceAll("\\)", "");
			logger.debug("------------- Final Upload File Name ---------"+ fileName);
			// Fix for SP2 defect 2671 
			contract.setUserFileName(fileName);
			
			//NOTE:  The topLevelMdmId will be part of the filename that will be sent to Siebel.  
			// The filename will be topLevelMdmId~originalfilename~yyyymmddhhmmssmmm.csv
			boolean success = false;
			String targetPage = "";
			CSV2AssetConverter csv2AssetConverter = null;
			CSVErrorHanlder cSVErrorHandller = null;
			
			if (PortalSessionUtil.isDuplicatedSubmit(request,
					bulkUploadForm)) {
				logger.debug("duplicated submit, do nothing!");			
			}else if(content.isEmpty()){
				ServiceStatusUtil.checkServiceStatus(model, EXCEPTION_BULK_UPLOAD_FILE_NOT_FOUND, request.getLocale(), true);
				targetPage = "importBulkUpload";
				logger.debug("EXCEPTION_BULK_UPLOAD_FILE_NOT_FOUND");	
			}else{
				InputStreamReader read=null;
				BufferedReader reader=null;
				try{
					
					read = new InputStreamReader(content.getInputStream());
					reader=new BufferedReader(read);
					TimezoneUtil Timezone = new TimezoneUtil();
					csv2AssetConverter = new CSV2AssetConverter(reader, request.getLocale());
					
					
					success=csv2AssetConverter.processData(session);//Here it is making all the validation
					//success=csv2AssetConverter.processData();//Removing all validation and making all validation as success 
					logger.debug("Removing all validation and making all validation as success");
				}catch (Exception e) {
					logger.debug("Got Exception in the method");
					success = false;
				}
				finally{
					if(read!=null)
						read.close();
					 if(reader!=null)
						 reader.close();
				}
				String errorCode;
				logger.info(";;Value of Success::"+success);
				if(!success){
					
					for(CSVError error :csv2AssetConverter.getErrors()){
						if(error.getErrorValue()!=null){
							Object[] locations = new Object[]{error.getErrorValue() ,error.getRow() ,error.getColumn()};
							errorCode = "exception.bulkUpload."+error.geterrorCode();
							ServiceStatusUtil.checkServiceStatus(model, errorCode, request.getLocale(), locations,true);
						}
						else {
							errorCode = "exception.bulkUpload."+error.geterrorCode();
							ServiceStatusUtil.checkServiceStatus(model, errorCode, request.getLocale(), true);
						}

					}
					targetPage = "importBulkUpload";
				}
				else{
					logger.debug("We are about to call mock implementation");
					//Here you should bring the type of upload (Activity or Claims) and according to that assign the header_one and header_two
					String fileType = (String) session.getAttribute(CSV2AssetConverter.UPLOAD_FILE_TYPE);
                    String uploadType = "";
                    logger.info("Getting following value from the sessionfor filetype is "+fileType);
                    String upload_header_one = "";
                    String upload_header_two = "";
                    if (fileType.equalsIgnoreCase(CSV2AssetConverter.CLAIMS)){
                            //uploadtype is claims and set the header accordingly
                            upload_header_one = CSV2AssetConverter.CLAIMS_HEADER_ONE;
                            upload_header_two = CSV2AssetConverter.CLAIMS_HEADER_TWO;
                            uploadType = "Claim Upload";
                    }else{
                            //uploadtype is activity and set the header accordingly
                            upload_header_one = CSV2AssetConverter.ACTIVITY_HEADER_ONE;
                            upload_header_two = CSV2AssetConverter.ACTIVITY_HEADER_TWO;
                            uploadType = "Request Upload";
                    }
                    logger.info("--------is is comong here--------");
                    

					InputStream insertedStream =  insertUploadHeaderAndMdmIdAndLevelInStream(content.getInputStream(), 
							topLevelMdmId, topLevelMdmLevel,upload_header_one,upload_header_two,uploadType,timezoneOffset);
					contract.setFileStream(insertedStream);
					contract.setUploadFileType(uploadType);
					BulkUploadFileResult result= new BulkUploadFileResult();
					try{
						LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "doFileUpload", 
								PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
						result = bulkUploadService.bulkUploadFile(contract);
						PerformanceTracker.endTracking(lexmarkTran);
					}catch (Exception e) {
						logger.debug("**********"+EXCEPTION_BULK_UPLOAD_FILE_NOT_FOUND+"*****************");
					}
					if(result.isUpDateSuccess()){
						errorCode = MESSAGE_BULK_UPLOAD_UPLOAD_CSV;
						ServiceStatusUtil.checkServiceStatus(model, errorCode, request.getLocale(), true);
					}else{
						errorCode = EXCEPTION_BULK_UPLOAD_UPLOAD_FAILED;
						ServiceStatusUtil.checkServiceStatus(model, errorCode, request.getLocale(), true);
						targetPage = "importBulkUpload";
					}
				}
			}
			response.setRenderParameter("action", targetPage);
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		
		logger.debug("------------- Step 1---doFileUpload ended---------["+System.nanoTime()+"]");
		
	}
	
	@ResourceMapping("bulkUploadLandingPageURL")
	public void retrieveMeterReadStatusList(ResourceRequest request, ResourceResponse response) throws Exception{
		logger.debug("------------- Step 1---bulkUploadLandingPageURL started---------["+System.nanoTime()+"]");
		PortletSession session = request.getPortletSession();
		String topLevelMdmId = null;
		if (PortalSessionUtil.getMdmLevel(session).equalsIgnoreCase(LexmarkConstants.GLOBAL)){
			//Already at the top level
			topLevelMdmId = PortalSessionUtil.getMdmId(session);
		}else{
			//Need to get the top level for this person's MDM ID and Level
			GlobalLegalEntityContract globalLegalEntityContract = new GlobalLegalEntityContract();
			logger.debug("------------- MDM ID ---------["+PortalSessionUtil.getMdmId(session)+"]");
			logger.debug("------------- MDM LEVEL ---------["+PortalSessionUtil.getMdmLevel(session)+"]");
			globalLegalEntityContract.setMdmId(PortalSessionUtil.getMdmId(session));
			globalLegalEntityContract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
			
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveGlobalLegalEntity", 
					PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			GlobalLegalEntityResult globalLegalEntityResult = globalService.retrieveGlobalLegalEntity(globalLegalEntityContract);
			PerformanceTracker.endTracking(lexmarkTran);
			
			topLevelMdmId = globalLegalEntityResult.getAccount().getMdmId();
			logger.debug("------------- Retrieve Global Legal Entity---------["+topLevelMdmId+"]");
		}
		
		BulkUploadStatusContract contract = ContractFactory.getBulkUploadStatusContract();
		
		
		String userID = PortalSessionUtil.getUserNumber(session);
		logger.debug(" retrieveMeterReadStatusList userID:::::::::::::::::::::::::::::::: "+userID);
		// This userId is set to filter the import history with user uploaded files rather then MDMID
		if (userID!=null){
			contract.setMdmId(userID);
		}else{
			contract.setMdmId(topLevelMdmId);
		}

		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try {
		contract.setSessionHandle(crmSessionHandle);
		
		session.setAttribute("bulkUploadLandingPageContract", contract);
		LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "bulkUploadStatusList", 
				PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
		BulkUploadStatusListResult result = bulkUploadService.retrieveBulkUploadStatusList(contract);
		PerformanceTracker.endTracking(lexmarkTran);
		List<BulkUploadStatus>  bulkUploadStatusList = result.getBulkUploadStatusList();
		String content = new XmlOutputGenerator(request.getLocale()).convertBulkUploadStatusToXML(bulkUploadStatusList);
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		
		logger.debug("------------- Step 1---bulkUploadLandingPageURL started---------["+System.nanoTime()+"]");
	}
	
	@ResourceMapping("outPutFileURL")
	public void outPutCSVFile(ResourceRequest request,
			ResourceResponse response, Model model)throws Exception{
		logger.debug("------------- --outPutCSVFile start---------["+System.nanoTime()+"]");
		BulkUploadStatusContract contract = new BulkUploadStatusContract();
		contract.setUserFileName(request.getParameter("fileName"));
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		float timezoneOffset = 0;
		String timezoneOffsetStr = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		if (timezoneOffsetStr != null)
			timezoneOffset = Float.valueOf(timezoneOffsetStr).floatValue();
		
		
        logger.info("3333333333333333333333333333333 timezoneOffset value is "+timezoneOffset);
		try{
			PortletSession session = request.getPortletSession();
			String fileType = (String) session.getAttribute(CSV2AssetConverter.UPLOAD_FILE_TYPE);
			
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveBulkUploadStatusFile", 
			PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			BulkUploadStatusFileResult result = bulkUploadService.retrieveBulkUploadStatusFile(contract);
			PerformanceTracker.endTracking(lexmarkTran);
			InputStreamReader read = new InputStreamReader(result.getFileStream());
			BufferedReader reader=new BufferedReader(read);
			String fileStr = "";
			String line = "";
			int count  = 0;
			String nextLine = "";
			int startDateIndex = 0;
			int endDateIndex = 0;
			int indexOfCustomerRequestResponse = 0;
			int indeOfEstTimeArrival = 0;
			int indeOfStatusAsOf = 0;
			
			
			while((line = reader.readLine())!=null){
				boolean firstHeader = false;
				boolean secondHeader = true;
				if(!firstHeader){
				fileStr = fileStr + line +"\n";
				firstHeader = true;
				continue;
				}
				   			
				 if(firstHeader && secondHeader){
					StringTokenizer stokenizer = new StringTokenizer(line, "\\" );
		    		String columnValue = stokenizer.nextToken();
		    		String[] cellArray =  columnValue.split("[,]");
		    		count = cellArray.length;
		    		startDateIndex = indexOfStartDateColumn(cellArray,fileType);
		    		endDateIndex = indexOfEndDateColumn(cellArray,fileType);
		    		indexOfCustomerRequestResponse = indexofCustomerRequestResponse(cellArray);
		    		indeOfEstTimeArrival = indeofEstTimeArrival(cellArray);
		    		indeOfStatusAsOf = indeofStatusAsOf(cellArray);
					fileStr = fileStr + line +"\n";
					firstHeader = true;
					secondHeader = false;
					continue;
					}
				else {
					
					nextLine = TimeZoneConversion(line,fileType,0-timezoneOffset,startDateIndex,endDateIndex,indexOfCustomerRequestResponse,indeOfEstTimeArrival,indeOfStatusAsOf,count);
					
					fileStr = fileStr + nextLine +"\n";
				}
		}
			
			response.setProperty("Content-disposition", "attachment; filename="
					+ request.getParameter("fileName"));
			response.setContentType("text/csv");
			PrintWriter out = ResourceResponseUtil.getUTF8PrintWrtierWithBOM(response);
			out.print(fileStr);
			out.flush();
			out.close();
		}catch (Exception e) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.print("<h1>" +
					PropertiesMessageUtil.getPropertyMessage(null,
							EXCEPTION_BULK_UPLOAD_FILE_NOT_FOUND,
							request.getLocale()) +
					"</h1>");
			out.flush();
			out.close();
		}
		logger.debug("----------------outPutCSVFile ended---------["+System.nanoTime()+"]");
	}
	
	private InputStream  insertUploadHeaderAndMdmIdAndLevelInStream(InputStream inputStream, String mdmId, String mdmLevel, String headerOne, String headerTwo,String fileType, float timezoneOffsetStr) throws IOException {
			
			InputStreamReader read = new InputStreamReader(inputStream);
			BufferedReader reader=new BufferedReader(read);
			
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			BufferedWriter out
			   = new BufferedWriter(new OutputStreamWriter(bout));
			int i = 0;
			int j = 0;
			int k = 0;
			int l = 0;
			int m = 0;
			
			String prefix = (mdmId==null?"," : mdmId + ",");
			prefix = prefix + (mdmLevel==null? "" : mdmLevel + ",");
			try {
				
				boolean headerLine = true;
				String line = null;
				boolean handler_up_header_one = true;
				boolean handler_up_header_two = false;
				int count = 0;
				
				while ((line = reader.readLine()) != null) {
					
					    if(handler_up_header_one) {
				    		handler_up_header_one = false;
					    	handler_up_header_two = true;
					    			    	
					    	out.write(line);
					    	out.newLine();
					    	logger.info("headerOne::"+headerOne);
					    	continue;
					    }
					    StringTokenizer stokenizer = new StringTokenizer(line, "\\" );
		    			String columnValue = stokenizer.nextToken();
		    			
		    			String[] cellArray =  columnValue.split("[,]");
		    			
		    			
		    			
				    	if(CSV2AssetConverter.HAS_HEADER_LINE && handler_up_header_two ==true){
				    		 
				    			
				    			i = indexOfStartDateColumn(cellArray,fileType);
				    			j = indexOfEndDateColumn(cellArray,fileType);
				    			k =  indexofCustomerRequestResponse(cellArray);
					    		l  = indeofEstTimeArrival(cellArray);
					    		m  = indeofStatusAsOf(cellArray);
				    			count = cellArray.length;
				    			logger.info("count of columns::"+count);
				    			out.write(line);
				    			logger.info("headerTwo::"+headerTwo);				    			
				    			out.newLine();
				    			handler_up_header_two = false;
				    			continue;
				    	}
				    	Pattern pat = Pattern.compile(",(\".+?\")");
				    	 Matcher matcher = pat.matcher(line);
					        StringBuffer sb = new StringBuffer();
					        while (matcher.find()){
					            matcher.appendReplacement(sb,","+matcher.group(1).replaceAll(",","&comm"));
					        }
					        matcher.appendTail(sb);
					        String cellArray1[] = sb.toString().split(",");
					       
					        String cellArray2[] = new String[count];
					        
					        
					        
					    for(int p=0;p<cellArray1.length;p++){
					    	
					    	if(cellArray1[p]!=null&&cellArray1[p].length()>0)
					    		cellArray2[p]  = cellArray[p]; 
					    	
					    	else
					    	cellArray2[p] ="";
					    }
					    			    	
				    	  	if(isArrayIsBlank(cellArray2))
                            continue;
				    	out.write(TimeZoneConversion(line,fileType,timezoneOffsetStr,i,j,k,l,m,count));
				    	out.newLine();
				    	
				    	
				}
				
				out.flush();
			 byte[] buff = bout.toByteArray();
			 ByteArrayInputStream bin = new ByteArrayInputStream(buff);
			 return bin;
	
			} catch(IOException ex) {
				throw ex;
			}

			finally {
				read.close();
				reader.close();
				bout.close();
				out.close();
			}
		}
		
	private int indexOfStartDateColumn(String cellArray[],String fileType){
		int indexOfStartDateColumn = 0;
		logger.info("fileType:"+fileType);
		if(fileType.equals("Request Upload")){
			for(int i=0;i<cellArray.length;i++){
				if(cellArray[i].equals("Action.ActualStart")){
					indexOfStartDateColumn = i;
				}
			}
			logger.info("indexOfStartDateColumn::"+indexOfStartDateColumn);
		}
		
		if(fileType.equals("Claim Upload")){
			logger.info("*****claims*****");
			for(int j=0;j<cellArray.length;j++){
					if(cellArray[j].equals("Claims.SrvcStartDate")){
					indexOfStartDateColumn = j;
					
				}
					
				}
			
		}
		
		return indexOfStartDateColumn;
	}
	private int indexOfEndDateColumn(String cellArray[],String fileType){
		int indexOfEndDateColumn = 0;
		if(fileType.equals("Request Upload")){
			for(int i=0;i<cellArray.length;i++){
				if(cellArray[i].equals("Action.ActualEnd")){
					indexOfEndDateColumn = i;
				}
			}
			
		}
		
		if(fileType.equals("Claim Upload")){
			for(int j=0;j<cellArray.length;j++){
				logger.info("+++cellArray[j]+++:"+cellArray[j]);
				if(cellArray[j].equals("Claims.SrvcEndDate")){
					indexOfEndDateColumn = j;
				}
				
					
				}
			
		}
		return indexOfEndDateColumn;
	}
	
	private int indexofCustomerRequestResponse(String cellArray[]){
		int indexOfColumn = 0;
		
			for(int i=0;i<cellArray.length;i++){
				if(cellArray[i].equals("Action.CustomerRequestResponse")){
					indexOfColumn = i;
				}
			}
			
			return indexOfColumn;
		}
	private int indeofEstTimeArrival(String cellArray[]){
		int indexOfColumn = 0;
		
			for(int i=0;i<cellArray.length;i++){
				if(cellArray[i].equals("Action.EstTimeArrival")){
					indexOfColumn = i;
				}
			}
			
			return indexOfColumn;
		}
	private int indeofStatusAsOf(String cellArray[]){
		int indexOfColumn = 0;
		
			for(int i=0;i<cellArray.length;i++){
				if(cellArray[i].equals("Action.StatusAsOf")){
					indexOfColumn = i;
				}
			}
			
			return indexOfColumn;
		}
	
	 public boolean isArrayIsBlank(String[] inputArray){
         boolean isArrayIsBlank=false;
         
         for(int i=0;i<inputArray.length;i++){
                 if(inputArray[i]==null){
                         isArrayIsBlank=true;
                         
                 }else if(inputArray[i].trim().length()==0){
                         isArrayIsBlank=true;
                 }        
                 else{
                         isArrayIsBlank=false;
                         break;
                 }
         }
         
         return isArrayIsBlank;
 }

	@SuppressWarnings("deprecation")
	private String TimeZoneConversion(String line,String fileType,float timezone,int i,int j,int k,int l,int m,int count) {
		
		CSV2AssetConverter csv2AssetConverter = null;
		logger.info("-----timezone--------"+timezone);
		Date strDate = null;
		Date endDate = null;
		Date responseDate = null;
		Date estimateDate = null;
		Date statusAsOf = null;
		int indexforStartDate= i;
		int indexforEndDate = j;
		int indexOfCustomerRequestResponse = k;
		int indexOfEstTimeArrival = l;
		int indexOfStatusAsOf = m;
		
		
		StringTokenizer sokenizer = new StringTokenizer(line, "\\" );
		String columnValue = sokenizer.nextToken();
		logger.info("next line" +columnValue);
		 Pattern pat = Pattern.compile(",(\".+?\")");
		 Matcher matcher = pat.matcher(line);
	        StringBuffer sb = new StringBuffer();
	        while (matcher.find()){
	            matcher.appendReplacement(sb,","+matcher.group(1).replaceAll(",","&comm"));
	        }
	        matcher.appendTail(sb);
	        String cellArray1[] = sb.toString().split(",");
	       
	        String cellArray2[] = new String[count];
	        
	        
	        
	    for(int p=0;p<cellArray1.length;p++){
	    	
	    	if(cellArray1[p]!=null&&cellArray1[p].length()>0)
	    		cellArray2[p]  = cellArray1[p]; 
	    	
	    	else
	    	cellArray2[p] ="";
	    }
	    
	   		
		 String strStartDate = cellArray2[indexforStartDate];
		 String CustomerRequestResponse = cellArray2[indexOfCustomerRequestResponse];
		 String EstTimeArrival = cellArray2[indexOfEstTimeArrival];
		 String StatusAsOf = cellArray2[indexOfStatusAsOf];
		 String strEndDate = cellArray2[indexforEndDate];
		 
		 logger.info("strEndDate:::"+strEndDate);
		 logger.info("strStartDate:::"+strStartDate);
		 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		 
		            
		  if((strStartDate!=null)&&(strStartDate.length()>0)){
			 
		 strDate=new Date(strStartDate);
		  try {
				logger.info("timezoneOffset:"+timezone);
				 TimezoneUtil.adjustDate(strDate,timezone);
				 cellArray2[indexforStartDate]= dateFormat.format(strDate).toString();
				 logger.info("cellArray2[indexforStartDate] is:"+cellArray2[indexforStartDate]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug("In Exception");
			} 
		  }
			
			 
		 
		 if((strEndDate!=null)&&(strEndDate.length()>0)){
			 endDate =new Date(strEndDate);
			 try {
					
					logger.info("timezoneOffset:"+timezone);
					 TimezoneUtil.adjustDate(endDate,timezone);
					 logger.info("endDate::"+endDate);
					 
					 cellArray2[indexforEndDate]= dateFormat.format(endDate).toString();
					 logger.info("cellArray2[indexforEndDate] is:"+cellArray2[indexforEndDate]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
		
			}
		 }
		 if(fileType.equals("Request Upload")){
			 if((CustomerRequestResponse!=null)&&(CustomerRequestResponse.length()>0)){
				 responseDate =new Date(CustomerRequestResponse);
				 try {
						
						logger.info("timezoneOffset:"+timezone);
						 TimezoneUtil.adjustDate(responseDate,timezone);
						 
						 cellArray2[indexOfCustomerRequestResponse]= dateFormat.format(responseDate).toString();
						 logger.info("cellArray2[indexOfCustomerRequestResponse] is:"+cellArray2[indexOfCustomerRequestResponse]);
					} catch (Exception e) {
						// TODO Auto-generated catch block
			
				}
			 }
			 if((EstTimeArrival!=null)&&(EstTimeArrival.length()>0)){
				 estimateDate =new Date(EstTimeArrival);
				 try {
						
						logger.info("timezoneOffset:"+timezone);
						 TimezoneUtil.adjustDate(estimateDate,timezone);
						 
						 cellArray2[indexOfEstTimeArrival]= dateFormat.format(estimateDate).toString();
						 logger.info("cellArray2[indexOfEstTimeArrival] is:"+cellArray2[indexOfEstTimeArrival]);
					} catch (Exception e) {
						// TODO Auto-generated catch block
			
				}
			 }
			 if((StatusAsOf!=null)&&(StatusAsOf.length()>0)){
				 statusAsOf =new Date(StatusAsOf);
				 try {
						
						logger.info("timezoneOffset:"+timezone);
						 TimezoneUtil.adjustDate(statusAsOf,timezone);
						 
						 cellArray2[indexOfStatusAsOf]= dateFormat.format(statusAsOf).toString();
						 logger.info("cellArray2[indexOfStatusAsOf] is:"+cellArray2[indexOfStatusAsOf]);
					} catch (Exception e) {
						// TODO Auto-generated catch block
			
				}
			 }
			 
		 }
				
			String s1 = cellArray2[indexforStartDate];
			String s2 = cellArray2[indexforEndDate];
			logger.info("Before strStartDate:"+strStartDate);
			
			logger.info("After conversion s1:"+s1);
			
			logger.info("Before strEndDate:"+strEndDate);
			logger.info("After conversion s2:"+s2);
			
			
			if(strStartDate!=null)
			line = line.replace(strStartDate, s1);
			logger.info("--line 1--"+line);
			if(strEndDate!=null)
			line = line.replace(strEndDate, s2);
			logger.info("--line 2--"+line);
			
			if(fileType.equals("Request Upload")){
			String s3 = cellArray2[indexOfCustomerRequestResponse];
			String s4 = cellArray2[indexOfEstTimeArrival];
			String s5 = cellArray2[indexOfStatusAsOf];
			
			
			logger.info(" Before conversion CustomerRequestResponse:::"+CustomerRequestResponse);
			logger.info(" After conversion CustomerRequestResponse::::"+s3);
			
			
			logger.info("Before Conversion EstTimeArrival:::"+EstTimeArrival);
			logger.info("After Conversion EstTimeArrival::::"+s4);
			
			
			logger.info("Before Conversion StatusAsOf:::"+StatusAsOf);
			logger.info("Before Conversion StatusAsOf:::"+s5);
			
			
			if(CustomerRequestResponse!=null)
				line = line.replace(CustomerRequestResponse, s3);
			if(EstTimeArrival!=null)
				line = line.replace(EstTimeArrival, s4);
			if(StatusAsOf!=null)
				line = line.replace(StatusAsOf, s5);
			}
			
			
			
			
			
			
		
		
		return line.toString();
}
}



	
