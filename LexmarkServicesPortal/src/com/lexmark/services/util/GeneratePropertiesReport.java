package com.lexmark.services.util;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class GeneratePropertiesReport {
	private static Logger LOGGER = LogManager.getLogger(GeneratePropertiesReport.class);

	private String  prefix = "/com/lexmark/services/resources/";
	private String  excelFile = "c:\\report.xls";
	private String[] messageFileNames = new String[] {
			"messages.properties",
			"messages_de.properties",
			"messages_es.properties",
			"messages_fr.properties",
			"messages_it.properties",
			"messages_ja.properties",
			"messages_ko.properties",
			"messages_pt_BR.properties",
			"messages_pt_PT.properties",
			"messages_ru.properties",
			"messages_zh_CN.properties",
			"messages_zh_TW.properties"
		}; 

	private Properties[] allLanguages;
	private HashMap<String, HashMap<String, Integer>> missingKeys = new HashMap<String, HashMap<String, Integer>>();

	/**
	 * @param args 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		new GeneratePropertiesReport().report();

	}


	/**
	 * @throws Exception 
	 */
	public void report() throws Exception {

		allLanguages = new Properties[messageFileNames.length];
		int msgFileNamesLen = messageFileNames.length;
		for(int i=0; i< msgFileNamesLen; i++) {
			InputStream inputSteam = GeneratePropertiesReport.class.getResourceAsStream(prefix + messageFileNames[i]);
			allLanguages[i] = new Properties();
			allLanguages[i].load(inputSteam);
		}

		for(Object key : allLanguages[0].keySet()) {
			HashMap<String, Integer> missingMap = new HashMap<String, Integer>();
			for(int i=1; i< msgFileNamesLen; i++) {
				if(!allLanguages[i].containsKey(key)) {
					missingMap.put(getLanguageName(messageFileNames[i]), i);
				}
			}
			// 
			if(!missingMap.isEmpty()) {
				missingKeys.put((String)key, missingMap);
			}
		}

		generateExcelFile();
		LOGGER.info("successfully report");
	}

	/**
	 * @throws IOException 
	 */
	private  void generateExcelFile() throws IOException {
		FileOutputStream fileOut = null;

		try
		{
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();
			HSSFRow headerRow = sheet.createRow(0);
			if (headerRow != null) {
				HSSFCell cell = headerRow.getCell(0);
				if (cell == null){
					cell = headerRow.createCell(0);
				}cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue("en");

				cell = headerRow.getCell(1);
				if (cell == null){
					cell = headerRow.createCell(1);
				}cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue("value");

				int msgFileNamesLen = messageFileNames.length;
				for(int i=1; i< msgFileNamesLen; i++) {	

					cell = headerRow.getCell(i+1);
					if (cell == null){
						cell = headerRow.createCell(i+1);
					}cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(getLanguageName(messageFileNames[i]));
				}
			}
			int rowNum =1;
			// sort the keys
			ArrayList<String> list = new ArrayList<String>();
			list.addAll(missingKeys.keySet());
			Collections.sort(list);

			for(String key : list) {
				HSSFRow keyRow = sheet.createRow(rowNum);
				if (keyRow != null) {
					HSSFCell cell = keyRow.getCell(0);
					if (cell == null){
						cell = keyRow.createCell(0);
					}cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(key);

					cell = keyRow.getCell(1);
					if (cell == null){
						cell = keyRow.createCell(1);
					}cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(allLanguages[0].getProperty(key));

					HashMap<String, Integer> missingMap = missingKeys.get(key);
					for(Integer lanIndex: missingMap.values()) {
						cell = keyRow.getCell(lanIndex+1);
						if (cell == null){
							cell = keyRow.createCell(lanIndex+1);
						}cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue("Yes");
					}
				}
				rowNum++;
			}
			// Write the output to a file
			fileOut = new FileOutputStream(excelFile);
			wb.write(fileOut);
		}
		finally
		{
			if (fileOut != null){
				fileOut.close();
			}
		}
	}

	/**
	 * @param propertiesName 
	 * @return String 
	 */
	private String getLanguageName(String propertiesName) {
		propertiesName = propertiesName.replace("messages_", "");
		propertiesName = propertiesName.replace(".properties", "");
		return propertiesName;
	}
}
