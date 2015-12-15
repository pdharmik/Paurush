package com.lexmark.api.bean;

import java.util.List;

/**
 * This is the bean for ServerActionRequest xml
 *
 */
public class DocSearchCriteria {
	/** This is the mode to send to ImageNow e.g. SCRIPT **/
	private String mode;

	/** This is the iScript filepath that needs to be executed in ImageNow **/
	private String filePath;

	/** The input parameters to be send to ImageNow iScripts **/
	private List<String> inputParams;
/**
 * 
 * @return String 
 */
	public String getMode() {
		return mode;
	}
/**
 * 
 * @param mode 
 */
	public void setMode(String mode) {
		this.mode = mode;
	}
/**
 * 
 * @return String 
 */
	public String getFilePath() {
		return filePath;
	}
/**
 * 
 * @param filePath 
 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
/**
 * 
 * @return String 
 */
	public List<String> getInputParams() {
		return inputParams;
	}
/**
 * 
 * @param value 
 */
	public void setInputParams(List<String> value) {
		this.inputParams = value;
	}
}
