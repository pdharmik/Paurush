package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Attachment;

public class AttachmentListResult implements Serializable {
	private static final long serialVersionUID = -3016658543368691853L;

	private List<Attachment> attachmentList = new ArrayList<Attachment>();
	private int totalCount;

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
