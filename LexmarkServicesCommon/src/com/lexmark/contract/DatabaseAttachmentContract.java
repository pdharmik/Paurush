package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.DatabaseAttachment;

public class DatabaseAttachmentContract implements Serializable {

	private static final long serialVersionUID = 4541052907677552702L;
	private DatabaseAttachment databaseAttchment;
	public DatabaseAttachment getDatabaseAttchment() {
		return databaseAttchment;
	}
	public void setDatabaseAttchment(DatabaseAttachment databaseAttchment) {
		this.databaseAttchment = databaseAttchment;
	}
	
}
