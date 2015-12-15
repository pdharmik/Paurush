package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * @author i.prikhodko
 * 
 * do-mapping: "do-offlinemodeservicerequestattachmentdo-mapping.xml"
 */


public class OfflineModeServiceRequestAttachmentDO extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String fileDerefFlg;
	private String fileDockStatFlg;
	private String fileName;
	private String fileExtension;
	private String fileSourcePath;
	private String activityId;
	private String fileSourceType;
	
	public String getFileDerefFlg() {
		return fileDerefFlg;
	}
	public void setFileDerefFlg(String fileDerefFlg) {
		this.fileDerefFlg = fileDerefFlg;
	}
	public String getFileDockStatFlg() {
		return fileDockStatFlg;
	}
	public void setFileDockStatFlg(String fileDockStatFlg) {
		this.fileDockStatFlg = fileDockStatFlg;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getFileSourcePath() {
		return fileSourcePath;
	}
	public void setFileSourcePath(String fileSourcePath) {
		this.fileSourcePath = fileSourcePath;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getFileSourceType() {
		return fileSourceType;
	}
	public void setFileSourceType(String fileSourceType) {
		this.fileSourceType = fileSourceType;
	}

}
