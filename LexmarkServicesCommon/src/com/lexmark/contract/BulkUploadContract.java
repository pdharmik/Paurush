package com.lexmark.contract;

import java.io.InputStream;
import java.io.Serializable;

import com.lexmark.contract.api.ContractBase;

public class BulkUploadContract extends ContractBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3087149236239124716L;
	private String contactId;
	private String mdmId;
	private String mdmLevel;
	private String userFileName;
	private String uploadFileType;
	private InputStream fileStream;
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public String getUserFileName() {
		return userFileName;
	}
	public void setUserFileName(String userFileName) {
		this.userFileName = userFileName;
	}
	public String getUploadFileType() {
		return uploadFileType;
	}
	public void setUploadFileType(String uploadFileType) {
		this.uploadFileType = uploadFileType;
	}
	public InputStream getFileStream() {
		return fileStream;
	}
	public void setFileStream(InputStream fileStream) {
		this.fileStream = fileStream;
	}
}
