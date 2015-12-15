/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: FileUploadForm.java
 * Package     		: com.lexmark.form
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 *
 */
package com.lexmark.form;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author wipro 
 * @version 2.1 
 */
public class FileUploadForm implements Serializable{
	
	private static final long serialVersionUID = -1381572566148315922L;
	private CommonsMultipartFile fileData;
	private int fileCount;
	private String totalFileSize;
	private int fileIndex;//Index of the file to be deleted.
	private String pageType;//Requested from which page.	
	private String sessionFileKey;//This is the key using which the file would be saved to session
	
	/**
	 * @return fileCount 
	 */
	public int getFileCount() {
		return fileCount;
	}
	/**
	 * @param fileCount 
	 */
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	/**
	 * @return totalFileSize 
	 */
	public String getTotalFileSize() {
		return totalFileSize;
	}
	/**
	 * @param totalFileSize 
	 */
	public void setTotalFileSize(String totalFileSize) {
		this.totalFileSize = totalFileSize;
	}
	/**
	 * @return fileData 
	 */
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	/**
	 * @param fileData 
	 */
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	
	/**
	 * @param pageType 
	 */
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
	/**
	 * @return pageType 
	 */
	public String getPageType() {
		return pageType;
	}
	/**
	 * @param sessionFileKey 
	 */
	public void setSessionFileKey(String sessionFileKey) {
		this.sessionFileKey = sessionFileKey;
	}
	/**
	 * @return string 
	 */
	public String getSessionFileKey() {
		return sessionFileKey;
	}
	/**
	 * @param fileIndex 
	 */
	public void setFileIndex(int fileIndex) {
		this.fileIndex = fileIndex;
	}
	/**
	 * @return int 
	 */
	public int getFileIndex() {
		return fileIndex;
	}
	

}
