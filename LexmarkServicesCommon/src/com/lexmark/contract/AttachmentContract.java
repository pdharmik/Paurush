package com.lexmark.contract;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lexmark.contract.api.SearchContractBase;
import com.lexmark.domain.Attachment;

public class AttachmentContract extends SearchContractBase implements Serializable {
	private static final long serialVersionUID = -8222073174319331185L;
	
	private String identifier;
	private String requestType;
	private List<Attachment> attachments;
	private String userFileName;

	// last added for retrieveAttachmentList
	private String parentId;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getUserFileName() {
		return userFileName;
	}

	public void setUserFileName(String userFileName) {
		this.userFileName = userFileName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}
}
