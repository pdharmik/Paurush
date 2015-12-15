package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.Attachment;

/**
 * mapping file: "do-servicerequestattachment-mapping.xml"
 */
public class ServiceRequestAttachmentDo extends Attachment implements Serializable {
	private static final long serialVersionUID = -7870441654321724466L;

	private String description;
	private String size;
	private String visibility;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSize() {
		return size;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getVisibility() {
		return visibility;
	}
}
