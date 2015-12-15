package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.Attachment;

/**
 * mapping file: "do-attachment-mapping.xml"
 */
public class AttachmentDo extends Attachment implements Serializable {
	private static final long serialVersionUID = -7870441654321724466L;

	private String submittedOn;
	private String completedOn;
	private String status;

	public String getSubmittedOn() {
		return submittedOn;
	}

	public void setSubmittedOn(String submittedOn) {
		this.submittedOn = submittedOn;
	}

	public String getCompletedOn() {
		return completedOn;
	}

	public void setCompletedOn(String completedOn) {
		this.completedOn = completedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
