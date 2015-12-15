package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * @author i.prikhodko
 *
 * do-mapping file: do-requestattachmentdetailsdo-mapping.xml
 */

public class RequestAttachmentDetailsDo extends BaseEntity implements
		Serializable {

	private static final long serialVersionUID = -1421973259660756954L;
	
	private String attachmentId;
	private String type;
	private String fileName;
	
	
	public String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
