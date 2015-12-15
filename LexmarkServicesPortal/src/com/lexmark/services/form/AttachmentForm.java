package com.lexmark.services.form;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lexmark.domain.Attachment;
import com.lexmark.domain.AttachmentFile;

/**
 * @author wipro
 * @version 2.1
 *
 */
public class AttachmentForm {
	private String listOfFileTypes;
	private String attMaxCount;
	private List<Attachment> attachmentList;
	private List<AttachmentFile> displayAttachmentList;
	private String attachmentDescription;
	private int fileCount;
	private CommonsMultipartFile fileData;
	/**
	 * 
	 * @return String 
	 */
	public String getListOfFileTypes() {
		return listOfFileTypes;
	}
	/**
	 * 
	 * @param listOfFileTypes 
	 */
	public void setListOfFileTypes(String listOfFileTypes) {
		this.listOfFileTypes = listOfFileTypes;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getAttMaxCount() {
		return attMaxCount;
	}
	/**
	 * 
	 * @param attMaxCount 
	 */
	public void setAttMaxCount(String attMaxCount) {
		this.attMaxCount = attMaxCount;
	}
	/**
	 * 
	 * @return List 
	 */
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
	/**
	 * 
	 * @param attachmentList 
	 */
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	/**
	 * 
	 * @return List 
	 */
	public List<AttachmentFile> getDisplayAttachmentList() {
		return displayAttachmentList;
	}
	/**
	 * 
	 * @param displayAttachmentList 
	 */
	public void setDisplayAttachmentList(List<AttachmentFile> displayAttachmentList) {
		this.displayAttachmentList = displayAttachmentList;
	}
	
	/**
	 * 
	 * @return String 
	 */
	public String getAttachmentDescription() {
		return attachmentDescription;
	}
	/**
	 * 
	 * @param attachmentDescription 
	 */
	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}
	/**
	 * 
	 * @return integer 
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
	 * @return CommonsMultipartFile 
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
	

}
