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
			
			writeHeader(stringBuff);
			stringBuff.append(LINESEPERATOR);
			for (Map.Entry<String, Asset> entry : assetDetailsMap.entrySet()) {
				LOGGER.debug("RowNum " + rowNum);

				assetDetails = entry.getValue();
				writeAssetInfo(assetDetails, rowNum, stringBuff);
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
	
	private void writeHeader(StringBuffer stringBuff) {
		LOGGER.debug("Inside writeHeader");
		
		stringBuff.append("Asset Id").append(CSVSEPERATOR);
		stringBuff.append("Serial Number").append(CSVSEPERATOR);
		stringBuff.append("Model").append(CSVSEPERATOR);
		stringBuff.append("IP Address").append(CSVSEPERATOR);
		stringBuff.append("Device Contact Name(First name & Last name)").append(CSVSEPERATOR);
		stringBuff.append("Device Contact Email Address").append(CSVSEPERATOR);
		stringBuff.append("Device Contact Phone Number").append(CSVSEPERATOR);
		stringBuff.append("ChlNodeValue").append(CSVSEPERATOR);
		stringBuff.append("Asset Cost Center").append(CSVSEPERATOR);
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

	private void writeInstalledAddrForAsset(Asset assetDetails,
			int rowNum, StringBuffer stringBuff, String assetReqType) {
		
		LOGGER.debug("This is a manage asset request, writing address");
		LOGGER.debug("------------ASSET REQUEST TYPE iS---------->>"+assetReqType);
		
		if(!DECOMMASSET.equals(assetReqType)){//For Decommission asset, I will not write installed address name
			if (rowNum == 0) {
				stringBuff.append("Asset Installed Address Name").append(CSVSEPERATOR);			
			} else {
				stringBuff.append(
						"\"" + assetDetails.getInstallAddress().getAddressName() + "\"")
						.append(CSVSEPERATOR);
			}
		}
	
		if (rowNum == 0) {			
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up Address StoreFront Name").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed Address StoreFront Name").append(CSVSEPERATOR);
			}
		} else {
			LOGGER.info("Store Front Name ---------> "+assetDetails.getInstallAddress().getStoreFrontName());
			stringBuff.append(
					"\"" + assetDetails.getInstallAddress().getStoreFrontName() + "\"")
					.append(CSVSEPERATOR);
		}
	
		if (rowNum == 0) {			
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up Address Line1").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed Address Line1").append(CSVSEPERATOR);
			}
		} else {			
			stringBuff.append(
					"\"" + assetDetails.getInstallAddress().getAddressLine1() + "\"")
					.append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up Address Line2").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed Address Line2").append(CSVSEPERATOR);
			}
		} else {
			stringBuff.append(
					"\"" + assetDetails.getInstallAddress().getAddressLine2() + "\"")
					.append(CSVSEPERATOR);

		}
		
		if (rowNum == 0) {
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up House Number").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed House Number").append(CSVSEPERATOR);
			}
		} else {
			stringBuff.append(
					"\"" + assetDetails.getInstallAddress().getOfficeNumber() + "\"")
					.append(CSVSEPERATOR);
		}	
		
		if (rowNum == 0) {
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up City").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed City").append(CSVSEPERATOR);
			}
		} else {
			stringBuff.append(
					"\"" + assetDetails.getInstallAddress().getCity() + "\"").append(
					CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up County").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed County").append(CSVSEPERATOR);
			}
		} else {
			stringBuff.append(
					"\"" + assetDetails.getInstallAddress().getCounty() + "\"")
					.append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up State").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed State").append(CSVSEPERATOR);
			}
		} else {
			stringBuff.append(
					"\"" + assetDetails.getInstallAddress().getState() + "\"")
					.append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up Province").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed Province").append(CSVSEPERATOR);
			}
		} else {
			stringBuff.append(
					"\"" + assetDetails.getInstallAddress().getProvince() + "\"")
					.append(CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up District").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed District").append(CSVSEPERATOR);
			}
		} else {
			stringBuff.append(
					"\"" + assetDetails.getInstallAddress().getDistrict() + "\"")
					.append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up Postal Code").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed Postal Code").append(CSVSEPERATOR);
			}
		} else {
			stringBuff.append(
					"\"" + assetDetails.getInstallAddress().getPostalCode() + "\"")
					.append(CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up Country").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed Country").append(CSVSEPERATOR);
			}
		} else {
			stringBuff.append(
					"\"" + assetDetails.getInstallAddress().getCountry() + "\"")
					.append(CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			stringBuff.append("Asset Installed Building").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getInstallAddress().getPhysicalLocation1()
							+ "\"").append(CSVSEPERATOR);
		}	
		
		if (rowNum == 0) {
			stringBuff.append("Asset Installed Office").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getInstallAddress().getPhysicalLocation3()
							+ "\"").append(CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			stringBuff.append("Asset Installed Floor").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getInstallAddress().getPhysicalLocation2()
							+ "\"").append(CSVSEPERATOR);
		}
		
		if (assetDetails.getInstallationOnlyFlag()) {
			if (rowNum == 0) {
				stringBuff.append("Install Asset Flag").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
					"\"" + assetDetails.getInstallationOnlyFlag()
							+ "\"").append(CSVSEPERATOR);
			}	
		}
	}
	
	private void writeMoveToAddrForAsset(Asset assetDetails,
			int rowNum, StringBuffer stringBuff, String assetReqType) {
		
		LOGGER.debug("This is a manage asset request, writing move to address");
		LOGGER.debug("------------ASSET REQUEST TYPE iS---------->>"+assetReqType);
		
		if (rowNum == 0) {
			stringBuff.append("Move Address Name").append(CSVSEPERATOR);			
		} else {
			stringBuff.append(
					"\"" + assetDetails.getMoveToAddress().getAddressName() + "\"")
					.append(CSVSEPERATOR);
		}
	
		if (rowNum == 0) {			
			stringBuff.append("Move Address StoreFront Name").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getMoveToAddress().getStoreFrontName() + "\"")
					.append(CSVSEPERATOR);
		}
	
		if (rowNum == 0) {			
			stringBuff.append("Move Address Line1").append(CSVSEPERATOR);
		} else {			
			stringBuff.append(
					"\"" + assetDetails.getMoveToAddress().getAddressLine1() + "\"")
					.append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Move Address Line2").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getMoveToAddress().getAddressLine2() + "\"")
					.append(CSVSEPERATOR);

		}
		
		if (rowNum == 0) {
			stringBuff.append("Move House Number").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getMoveToAddress().getOfficeNumber() + "\"")
					.append(CSVSEPERATOR);
		}	
		
		if (rowNum == 0) {
			stringBuff.append("Move Address City").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getMoveToAddress().getCity() + "\"").append(
					CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			stringBuff.append("Move Address County").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getMoveToAddress().getCounty() + "\"")
					.append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Move Address State").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getMoveToAddress().getState() + "\"")
					.append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Move Address Province").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getMoveToAddress().getProvince() + "\"")
					.append(CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			stringBuff.append("Move Address District").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getMoveToAddress().getDistrict() + "\"")
					.append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Move Address Postal Code").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getMoveToAddress().getPostalCode() + "\"")
					.append(CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			stringBuff.append("Move Address Country").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getMoveToAddress().getCountry() + "\"")
					.append(CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			stringBuff.append("Move Address Building").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getMoveToAddress().getPhysicalLocation1()
							+ "\"").append(CSVSEPERATOR);
		}	
		
		if (rowNum == 0) {
			stringBuff.append("Move Address Office").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getMoveToAddress().getPhysicalLocation3()
							+ "\"").append(CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			stringBuff.append("Move Address Floor").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getMoveToAddress().getPhysicalLocation2()
							+ "\"").append(CSVSEPERATOR);
		}
	}

	private void writeAssetInfo(Asset assetDetails, int rowNum,
			StringBuffer stringBuff) {
		//String assetReqType;
		
		LOGGER.debug("Inside writeAssetInfo");
		
		stringBuff.append(
				"\"" + assetDetails.getAssetId()
						+ "\"").append(CSVSEPERATOR);
		stringBuff.append(
				"\"" + assetDetails.getSerialNumber()
						+ "\"").append(CSVSEPERATOR);
		stringBuff.append(
				"\"" + assetDetails.getProductTLI()
						+ "\"").append(CSVSEPERATOR);
		stringBuff.append(
				"\"" + assetDetails.getIpAddress()
						+ "\"").append(CSVSEPERATOR);
		stringBuff.append(
				"\"" + assetDetails.getAssetContact().getFirstName()
						+ "\"").append(CSVSEPERATOR);
		stringBuff.append(
				"\"" + assetDetails.getAssetContact().getEmailAddress()
						+ "\"").append(CSVSEPERATOR);
		stringBuff.append(
				"\"" + assetDetails.getAssetContact().getWorkPhone()
						+ "\"").append(CSVSEPERATOR);
		stringBuff.append(
				"\"" + assetDetails.getChlNodeValue()
						+ "\"").append(CSVSEPERATOR);
		stringBuff.append(
				"\"" + assetDetails.getAssetCostCenter()
						+ "\"").append(CSVSEPERATOR);
		stringBuff.append(
				"\"" + assetDetails.getDeviceTag()
						+ "\"").append(CSVSEPERATOR);
		stringBuff.append(
				"\"" + assetDetails.getHostName()
						+ "\"").append(CSVSEPERATOR);
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
		stringBuff.append(
				"\"" + assetDetails.getAccount().getAccountId()
						+ "\"").append(CSVSEPERATOR);
		
		/*if (rowNum == 0) {
			stringBuff.append("Serial Number").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getSerialNumber()
							+ "\"").append(CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			stringBuff.append("Product").append(CSVSEPERATOR);
			
		} else {
			stringBuff.append(
					"\"" + assetDetails.getProductLine()
							+ "\"").append(CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			stringBuff.append("IP Address").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getIpAddress()
							+ "\"").append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Host Name").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getHostName()
							+ "\"").append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Customer Asset tag").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getDeviceTag() 
							+ "\"").append(CSVSEPERATOR);
		}
			
		if (rowNum == 0) {
			stringBuff.append("Asset Cost Center").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getAssetCostCenter() 
							+ "\"").append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("CHL Node").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + assetDetails.getChlNodeValue()
							+ "\"").append(CSVSEPERATOR);
		}
		
		LOGGER.debug("This is an change asset request, so setting the type of req to change asset");
		assetReqType="ChangeAsset";
		writeInstalledAddrForAsset(assetDetails, rowNum, stringBuff, assetReqType);
		writeMoveToAddrForAsset(assetDetails, rowNum, stringBuff, assetReqType);*/
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
