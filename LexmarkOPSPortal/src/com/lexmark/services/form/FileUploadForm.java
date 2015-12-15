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
	
	public int getFileCount() {
		return fileCount;
	}
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	public String getTotalFileSize() {
		return totalFileSize;
	}
	public void setTotalFileSize(String totalFileSize) {
		this.totalFileSize = totalFileSize;
	}
	private Map<String, FileObject> fileMap;
	
	public Map<String, FileObject> getFileMap() {
		return fileMap;
	}
	public void setFileMap(Map<String, FileObject> fileMap) {
		this.fileMap = fileMap;
	}
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	/*public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}*/
	
	public void setAttachmentFileMap(Map<String, FileObject> attachmentFileMap) {
		this.attachmentFileMap = attachmentFileMap;
	}
	
	public Map<String, FileObject> getAttachmentFileMap() {
		return attachmentFileMap;
	}
	public String getPageType() {
		return pageType;
	}
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

}
