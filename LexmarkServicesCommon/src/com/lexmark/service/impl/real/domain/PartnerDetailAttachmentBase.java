package com.lexmark.service.impl.real.domain;

import com.amind.common.domain.BaseEntity;
import com.lexmark.util.LangUtil;

public class PartnerDetailAttachmentBase extends BaseEntity {
	private String attachmentName;
	private String activityId;
	private String activityFileExt;
	private String identifier;
	private String type;
	private String displayFileName;
	private String fileSize;
	private String fileSourcePath;
	private String visibility;
	
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public String getActivityFileExt() {
		return activityFileExt;
	}
	public void setActivityFileExt(String activityFileExt) {
		this.activityFileExt = activityFileExt;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setDisplayFileName(String displayFileName) {
		this.displayFileName = displayFileName;
	}
	public String getDisplayFileName() {
		return displayFileName;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileSize() {
		if(LangUtil.isEmpty(fileSize))
		{
			fileSize = "0";
		}
		return fileSize;
	}
	public String getFileSourcePath() {
		return fileSourcePath;
	}
	public void setFileSourcePath(String fileSourcePath) {
		this.fileSourcePath = fileSourcePath;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
}
