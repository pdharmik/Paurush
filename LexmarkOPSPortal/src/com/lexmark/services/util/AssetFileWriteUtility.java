package com.lexmark.services.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.ServiceRequestDTO;
import com.lexmark.service.impl.real.util.AttachmentProperties;

public class AssetFileWriteUtility {

	private static Logger LOGGER = LogManager.getLogger(AssetFileWriteUtility.class);
	private final static String CSVSEPERATOR = ",";
	private final static String LINESEPERATOR = "\n";
	private final static String APPENDCHAR="_";
	private final static String FILEXTENSION=".csv";

	private final static String ATTACHMENT_REQUEST_TYPE="Service Request";
	private final static String CSVFAILURE = "failure";
	private final static String DECOMMASSET = "DecommAsset";
	
	private static AssetFileWriteUtility fileWriteInstance = null;

	// private static Properties props;

	private AssetFileWriteUtility() {
	};

	// Creating a singleton instance of
	public static synchronized AssetFileWriteUtility getInstance() {
		if (fileWriteInstance == null) {
			fileWriteInstance = new AssetFileWriteUtility();
		}
		return fileWriteInstance;
	}

	/*
	 * CSV creation goes here for Manage Asset, Manage Address & Manage Contact
	 */

	public Attachment createCSVFile(final Map<String, Asset> assetDetailsMap,
			final String workSheetName, final String multiSelectReqType){
		LOGGER.debug("Inside create CSV file ");
		LOGGER.info("Inside create CSV file ");

		AttachmentProperties fileProperties = new AttachmentProperties(ATTACHMENT_REQUEST_TYPE);
		LOGGER.debug("File Upload dest is " + fileProperties.getFileUploadDestination());
		
		final String fileUploadDest= fileProperties.getFileUploadDestination();
		LOGGER.debug("fileUploadDest"+fileUploadDest);
		String actualFName = workSheetName + APPENDCHAR + System.currentTimeMillis()+ FILEXTENSION;
		String displayFileName = workSheetName + FILEXTENSION;
		final String fileName = fileUploadDest + workSheetName + APPENDCHAR + System.currentTimeMillis()+ FILEXTENSION;
		LOGGER.debug("FileName"+fileName);
		
		LOGGER.debug("Display File name is " + displayFileName);
		LOGGER.debug("Actual File name is " + actualFName);
		final StringBuffer stringBuff = new StringBuffer();
		
		Attachment attachFileCsv = new Attachment();

		try {
			OutputStream outputStream=new FileOutputStream((fileName));
            outputStream.write(0xEF);   // 1st byte of BOM
			outputStream.write(0xBB);
			outputStream.write(0xBF);
			OutputStreamWriter outputWriter=new OutputStreamWriter(outputStream, "UTF-8");
			final BufferedWriter bufferedWriter = new BufferedWriter(outputWriter);
			int rowNum = 0;
			Asset assetDetails = new Asset();
			
			writeHeader(stringBuff,multiSelectReqType);
			stringBuff.append(LINESEPERATOR);
			for (Map.Entry<String, Asset> entry : assetDetailsMap.entrySet()) {
				LOGGER.debug("RowNum " + rowNum);

				assetDetails = entry.getValue();
				writeAssetInfo(assetDetails, rowNum, stringBuff,multiSelectReqType);
				//writeAssetContact(assetDetails, rowNum, stringBuff);
				
				stringBuff.append(LINESEPERATOR);
				rowNum++;
			}
			bufferedWriter.write(stringBuff.toString());
			bufferedWriter.flush();
			bufferedWriter.close();
			LOGGER.info("**** CSV Creation Done ****");
			
			FileInputStream  fileinputstream =new FileInputStream(fileName);
			long fSize = fileinputstream.getChannel().size();
			
			attachFileCsv = new Attachment();
			attachFileCsv.setFileName(fileName);
			attachFileCsv.setActualFileName(actualFName);
			attachFileCsv.setAttachmentName(fileName);
			attachFileCsv.setDisplayAttachmentName(displayFileName);
			attachFileCsv.setSize((int) fSize);
			
			LOGGER.debug("attachFileCsv Attachment::"+ attachFileCsv);
		} catch (Exception e) {
			e.printStackTrace();//This is for the time being- to be removed later
			LOGGER.info("**** CSV Creation Error ****",e);
			attachFileCsv.setAttachmentName(CSVFAILURE);
			attachFileCsv.setFileName(CSVFAILURE);
			attachFileCsv.setActualFileName(CSVFAILURE);
			return attachFileCsv;//This string is mandatory because checking done in the controller
		}

		return attachFileCsv;
	}
	
	private void writeHeader(StringBuffer stringBuff,String multiSelectReqType) {
		LOGGER.debug("Inside writeHeader");
		
		if(!"addMultiple".equals(multiSelectReqType))
		{
			stringBuff.append("Asset Id").append(CSVSEPERATOR);
		}
		stringBuff.append("Serial Number").append(CSVSEPERATOR);
		stringBuff.append("Model").append(CSVSEPERATOR);
		
		
		if("addMultiple".equals(multiSelectReqType))
		{
			stringBuff.append("Install Date").append(CSVSEPERATOR);	
		}
		stringBuff.append("IP Address").append(CSVSEPERATOR);
		if(!"addMultiple".equals(multiSelectReqType))
		{
			stringBuff.append("Device Contact Name(First name & Last name)").append(CSVSEPERATOR);
			stringBuff.append("Device Contact Email Address").append(CSVSEPERATOR);
			stringBuff.append("Device Contact Phone Number").append(CSVSEPERATOR);	
		}
		
		stringBuff.append("ChlNodeValue").append(CSVSEPERATOR);
		
		if(!"addMultiple".equals(multiSelectReqType))
		{
			stringBuff.append("Asset Cost Center").append(CSVSEPERATOR);	
		}
		
		stringBuff.append("Device Tag").append(CSVSEPERATOR);
		stringBuff.append("Host Name").append(CSVSEPERATOR);
		stringBuff.append("Building").append(CSVSEPERATOR);
		stringBuff.append("Floor").append(CSVSEPERATOR);
		stringBuff.append("Office").append(CSVSEPERATOR);
		stringBuff.append("Zone").append(CSVSEPERATOR);
		stringBuff.append("Grid-X").append(CSVSEPERATOR);
		stringBuff.append("Grid-Y").append(CSVSEPERATOR);
		stringBuff.append("AccountId").append(CSVSEPERATOR);
	}	
	
	private void writeAssetInfo(Asset assetDetails, int rowNum,
			StringBuffer stringBuff, String multiSelectReqType) {
		
		LOGGER.debug("Inside writeAssetInfo");
		LOGGER.debug("multiSelectReqType::"+multiSelectReqType);
		LOGGER.debug("assetDetails:::"+assetDetails);
		//String assetReqType;
		
		if(!"addMultiple".equals(multiSelectReqType))
		{
			stringBuff.append(
					"\"" + assetDetails.getAssetId()
							+ "\"").append(CSVSEPERATOR);
		}
		LOGGER.debug("assetDetails.getSerialNumber()::"+assetDetails.getSerialNumber());
		stringBuff.append(
				"\"" + assetDetails.getSerialNumber()
						+ "\"").append(CSVSEPERATOR);
		
		if("addMultiple".equals(multiSelectReqType))
		{
			stringBuff.append(
					"\"" + assetDetails.getProductLine()
							+ "\"").append(CSVSEPERATOR);
			stringBuff.append(
					"\"" + assetDetails.getInstallDate()
							+ "\"").append(CSVSEPERATOR);
		}
		else
		{
			stringBuff.append(
					"\"" + assetDetails.getProductTLI()
							+ "\"").append(CSVSEPERATOR);
		}
		
		
		LOGGER.debug("getIpAddress()::"+assetDetails.getIpAddress());
		stringBuff.append(
				"\"" + assetDetails.getIpAddress()
						+ "\"").append(CSVSEPERATOR);
		
		if(!"addMultiple".equals(multiSelectReqType))
		{			
			LOGGER.debug("getAssetContact().getFirstName()::"+assetDetails.getAssetContact().getFirstName());
			stringBuff.append(
					"\"" + assetDetails.getAssetContact().getFirstName()
							+ "\"").append(CSVSEPERATOR);
		
			stringBuff.append(
					"\"" + assetDetails.getAssetContact().getEmailAddress()
							+ "\"").append(CSVSEPERATOR);
		
			stringBuff.append(
					"\"" + assetDetails.getAssetContact().getWorkPhone()
							+ "\"").append(CSVSEPERATOR);
			
		}
		
		LOGGER.debug("getChlNodeValue()::"+assetDetails.getChlNodeValue());
		stringBuff.append(
				"\"" + assetDetails.getChlNodeValue()
						+ "\"").append(CSVSEPERATOR);
	
		if(!"addMultiple".equals(multiSelectReqType))
		{
			stringBuff.append(
					"\"" + assetDetails.getAssetCostCenter()
							+ "\"").append(CSVSEPERATOR);
		}
	
		
		stringBuff.append(
				"\"" + assetDetails.getDeviceTag()
						+ "\"").append(CSVSEPERATOR);
		stringBuff.append(
				"\"" + assetDetails.getHostName()
						+ "\"").append(CSVSEPERATOR);
		
		LOGGER.debug("getInstallAddress()::"+assetDetails.getInstallAddress().getPhysicalLocation1());
		stringBuff.append(
				"\"" + assetDetails.getInstallAddress().getPhysicalLocation1()
						+ "\"").append(CSVSEPERATOR);
		stringBuff.append(
				"\"" + assetDetails.getInstallAddress().getPhysicalLocation2()
						+ "\"").append(CSVSEPERATOR);
		stringBuff.append(
				"\"" + assetDetails.getInstallAddress().getPhysicalLocation3()
						+ "\"").append(CSVSEPERATOR);
		stringBuff.append(
				"\"" + assetDetails.getInstallAddress().getZoneName()
						+ "\"").append(CSVSEPERATOR);
		stringBuff.append(
				"\"" + assetDetails.getInstallAddress().getCoordinatesXPreDebriefRFV()
						+ "\"").append(CSVSEPERATOR);
		stringBuff.append(
				"\"" + assetDetails.getInstallAddress().getCoordinatesYPreDebriefRFV()
						+ "\"").append(CSVSEPERATOR);
	
		
		if(assetDetails.getAccount()!=null)
		{
			stringBuff.append(
					"\"" + assetDetails.getAccount().getAccountId()
							+ "\"").append(CSVSEPERATOR);
		}
		
	}

	private void writeAssetContact(Asset assetDetails, int rowNum, StringBuffer stringBuff) {
		LOGGER.debug("This is a manage asset request, writing move to address");
		
		if (rowNum == 0) {
			stringBuff.append("Asset Contact First Name").append(CSVSEPERATOR);			
		} else {
			stringBuff.append(
					"\"" + assetDetails.getAssetContact().getFirstName() + "\"")
					.append(CSVSEPERATOR);
		}
	
		if (rowNum == 0) {			
			stringBuff.append("Asset Contact Middle Name").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getAssetContact().getMiddleName() + "\"")
					.append(CSVSEPERATOR);
		}
	
		if (rowNum == 0) {			
			stringBuff.append("Asset Contact Last Name").append(CSVSEPERATOR);
		} else {			
			stringBuff.append(
					"\"" + assetDetails.getAssetContact().getLastName() + "\"")
					.append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Asset Contact Email").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getAssetContact().getEmailAddress() + "\"")
					.append(CSVSEPERATOR);

		}
		
		if (rowNum == 0) {
			stringBuff.append("Asset Contact Phone").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getMoveToAddress().getOfficeNumber() + "\"")
					.append(CSVSEPERATOR);
		}	
	}
}
