package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.DatabaseAttachment;

public class DatabaseAttachmentResult implements Serializable {

	private static final long serialVersionUID = 1990335098014213828L;
	private List<DatabaseAttachment> databaseAttachmentList;
	public List<DatabaseAttachment> getDatabaseAttachmentList() {
		return databaseAttachmentList;
	}
	public void setDatabaseAttachmentList(
			List<DatabaseAttachment> databaseAttachmentList) {
		this.databaseAttachmentList = databaseAttachmentList;
	}	
}
