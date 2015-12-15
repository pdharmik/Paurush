package com.lexmark.services.form;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lexmark.domain.FileObject;

/**
 * @author wipro
 * @version 2.1
 *
 */

public class FileUploadForm implements Serializable{
	
	private static final long serialVersionUID = -1381572566148315922L;
	private CommonsMultipartFile fileData;
	private int fileCount;
	private String totalFileSize;
	private Map<String, FileObject> attachmentFileMap = new LinkedHashMap<String, FileObject>();
	private String pageType;	
	/**
	 * 
	 * @return Integer 
	 */
	public int getFileCount() {
		return fileCount;
	}
	/**
	 * 
	 * @param fileCount 
	 */
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getTotalFileSize() {
		return totalFileSize;
	}
	/**
	 * 
	 * @param totalFileSize 
	 */
	public void setTotalFileSize(String totalFileSize) {
		this.totalFileSize = totalFileSize;
	}
	/**
	 * 
	 */
	private Map<String, FileObject> fileMap;
	/**
	 * 
	 * @return Map 
	 */
	public Map<String, FileObject> getFileMap() {
		return fileMap;
	}
	/**
	 * 
	 * @param fileMap 
	 */
	public void setFileMap(Map<String, FileObject> fileMap) {
		this.fileMap = fileMap;
	}
	/**
	 * 
	 * @return fileData 
	 */
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	/**
	 * 
	 * @param fileData 
	 */
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	/*public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}*/
	/**
	 * 
	 * @param attachmentFileMap 
	 */
	public void setAttachmentFileMap(Map<String, FileObject> attachmentFileMap) {
		this.attachmentFileMap = attachmentFileMap;
	}
	/**
	 * 
	 * @return Map 
	 */
	public Map<String, FileObject> getAttachmentFileMap() {
		return attachmentFileMap;
	}
	/**
	 * 
	 * @return String  
	 */
	public String getPageType() {
		return pageType;
	}
	/**
	 * 
	 * @param pageType 
	 */
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

}
