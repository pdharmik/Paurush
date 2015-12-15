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
	
	public String getListOfFileTypes() {
		return listOfFileTypes;
	}
	public void setListOfFileTypes(String listOfFileTypes) {
		this.listOfFileTypes = listOfFileTypes;
	}
	public String getAttMaxCount() {
		return attMaxCount;
	}
	public void setAttMaxCount(String attMaxCount) {
		this.attMaxCount = attMaxCount;
	}
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	public List<AttachmentFile> getDisplayAttachmentList() {
		return displayAttachmentList;
	}
	public void setDisplayAttachmentList(List<AttachmentFile> displayAttachmentList) {
		this.displayAttachmentList = displayAttachmentList;
	}
	
	public String getAttachmentDescription() {
		return attachmentDescription;
	}
	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}
	public int getFileCount() {
		return fileCount;
	}
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	

}
