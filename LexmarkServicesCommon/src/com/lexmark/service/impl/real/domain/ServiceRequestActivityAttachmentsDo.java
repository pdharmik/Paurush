package com.lexmark.service.impl.real.domain;


/**
 * The mapping file: do-servicerequestactivityattachmentsdo-mapping.xml
 */
public class ServiceRequestActivityAttachmentsDo extends PartnerActivityDo {
	private static final long serialVersionUID = -4588416746863898854L;
	
	private String type;
	private String activityFileExt;
	private String activityFileSrcPath;
	private String visibility;
	private String attachmentName;
	private String activityFileDate;
	private String activityFileSize;
	private String activityId;
	private String attachmentId;
	private String activityFileName;
	private String activityComments;
	private String actualFileName;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getActivityFileExt() {
		return activityFileExt;
	}
	public void setActivityFileExt(String activityFileExt) {
		this.activityFileExt = activityFileExt;
	}
	public String getActivityFileSrcPath() {
		return activityFileSrcPath;
	}
	public void setActivityFileSrcPath(String activityFileSrcPath) {
		this.activityFileSrcPath = activityFileSrcPath;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getActivityFileDate() {
		return activityFileDate;
	}
	public void setActivityFileDate(String activityFileDate) {
		this.activityFileDate = activityFileDate;
	}
	public String getActivityFileSize() {
		return activityFileSize;
	}
	public void setActivityFileSize(String activityFileSize) {
		this.activityFileSize = activityFileSize;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String getActivityFileName() {
		return activityFileName;
	}
	public void setActivityFileName(String activityFileName) {
		this.activityFileName = activityFileName;
	}
	public String getActivityComments() {
		return activityComments;
	}
	public void setActivityComments(String activityComments) {
		this.activityComments = activityComments;
	}
	public String getActualFileName() {
		return actualFileName;
	}
	public void setActualFileName(String actualFileName) {
		this.actualFileName = actualFileName;
	}
	
}
