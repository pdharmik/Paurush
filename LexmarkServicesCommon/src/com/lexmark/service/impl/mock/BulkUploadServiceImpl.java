package com.lexmark.service.impl.mock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.BulkUploadContract;
import com.lexmark.contract.BulkUploadStatusContract;
import com.lexmark.domain.BulkUploadStatus;
import com.lexmark.result.BulkUploadFileResult;
import com.lexmark.result.BulkUploadStatusFileResult;
import com.lexmark.result.BulkUploadStatusListResult;
import com.lexmark.service.api.BulkUploadService;

public class BulkUploadServiceImpl implements BulkUploadService {
	
	
	private static Logger LOGGER = LogManager.getLogger(BulkUploadService.class);

		
	private static List<BulkUploadStatus> bulkUploadStatusList =  new ArrayList<BulkUploadStatus>();
	static{
		bulkUploadStatusList =  DomainMockDataGenerator.getBulkUploadStatusList();
	}
	
	@Override
	public BulkUploadFileResult bulkUploadFile(BulkUploadContract contract)
			throws Exception {
		BulkUploadFileResult result = new BulkUploadFileResult();
		BulkUploadStatus bulkUploadStatus = new BulkUploadStatus();
		
		File file =new File("c:\\temp-" + new Date().getTime() +".csv");
		//if file doesnt exists, then create it
		if(!file.exists()){
		   file.createNewFile();
		}

		FileOutputStream fop=new FileOutputStream(file);
		BufferedWriter out
		   = new BufferedWriter(new OutputStreamWriter(fop));
		InputStreamReader read = new InputStreamReader(contract.getFileStream());
		BufferedReader reader=new BufferedReader(read);
		
		  try {
			String line = null;
			while ((line = reader.readLine()) != null) {
			    	out.write(line);
			    	out.newLine();
			}
			out.flush();
		  }
		  finally {
			out.close();
			fop.close();
			read.close();
			reader.close();
	  }
		  
		bulkUploadStatus.setAttachmentName(contract.getUserFileName());
		bulkUploadStatus.setSubmittedOn(formatDate());
		bulkUploadStatus.setCompletedOn(formatDate());
		bulkUploadStatus.setSize(5500);
		bulkUploadStatus.setStatus("Succesful");
		bulkUploadStatus.setComment("File imported successfully");
		bulkUploadStatusList.add(bulkUploadStatus);
		result.setUpDateSuccess(true);
		LOGGER.info("Returning setUpDateSuccess as true ");
		return result;
	}

	@Override
	public BulkUploadStatusListResult retrieveBulkUploadStatusList(
			BulkUploadStatusContract contract) throws Exception {
		BulkUploadStatusListResult result = new BulkUploadStatusListResult();
		result.setBulkUploadStatusList(bulkUploadStatusList);
		return result;
	}
	@Override
	public BulkUploadStatusFileResult retrieveBulkUploadStatusFile(
			BulkUploadStatusContract contract) throws Exception {
		BulkUploadStatusFileResult result = new BulkUploadStatusFileResult();
		try{
			result.setFileStream(new FileInputStream("c:\\temp-1317849724390.csv"));
		}catch (Exception e) {
			return null;
		}
		return result;
	}
	private  String formatDate(){
		Calendar currentDate = GregorianCalendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    return dateFormat.format(currentDate.getTime());
	}

}
