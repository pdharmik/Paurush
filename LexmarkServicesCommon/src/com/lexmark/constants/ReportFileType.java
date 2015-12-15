package com.lexmark.constants;

import java.util.ArrayList;
import java.util.List;

import com.lexmark.util.report.PropertiesUtil;

public class ReportFileType {
	private static final String BOXICODE_PREFIX= "scheduler.report.types.boxiCode.";
	private static List<ReportFileType> fileTypeList;
	private String type;
	private ReportFileType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	public static List<ReportFileType> getAllReportFileType() {
		if(fileTypeList != null) {
			return fileTypeList;
		}
		fileTypeList = new ArrayList<ReportFileType>();
		String fileTypes = PropertiesUtil.ALL_REPORT_FILE_TYPES;
		if(fileTypes == null) {
			return fileTypeList;
		}
		String[] types = fileTypes.split(",");
		for(String s: types) {
			s = s.trim().toUpperCase();
			ReportFileType fileType = new ReportFileType(s);
			fileTypeList.add(fileType);
		}
		return fileTypeList;
	}
	
	public static ReportFileType instance(String type) {
		for(ReportFileType t: getAllReportFileType()) {
			if(t.getType().equalsIgnoreCase(type)) {
				return t;
			}
		}
		throw new RuntimeException("does not support report file type" + type);
	}
	
	public String getBoxiCode() {
		String code = PropertiesUtil.get(BOXICODE_PREFIX + type);
		if(code == null) {
			throw new RuntimeException("boxi code for " + type + " is not existed in the reportScheduler.properties file");
		}
		code = code.trim();
		return code;
	}
	
}
