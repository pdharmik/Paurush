package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;

public class ActivityNote implements Serializable {
	private static final long serialVersionUID = 9189162946699905958L;
	private String noteId;
	private Date noteDate;
	private AccountContact noteAuthor;
	private String noteDetails;
	private boolean repairCompleteFlag;
	private boolean activityUpdateFlag;
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	public Date getNoteDate() {
		return noteDate;
	}
	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
	}
	public AccountContact getNoteAuthor() {
		return noteAuthor;
	}
	public void setNoteAuthor(AccountContact noteAuthor) {
		this.noteAuthor = noteAuthor;
	}
	public String getNoteDetails() {
		return noteDetails;
	}
	public void setNoteDetails(String noteDetails) {
		this.noteDetails = noteDetails;
	}
	public boolean isRepairCompleteFlag() {
		return repairCompleteFlag;
	}
	public void setRepairCompleteFlag(boolean repairCompleteFlag) {
		this.repairCompleteFlag = repairCompleteFlag;
	}
	public boolean isActivityUpdateFlag() {
		return activityUpdateFlag;
	}
	public void setActivityUpdateFlag(boolean activityUpdateFlag) {
		this.activityUpdateFlag = activityUpdateFlag;
	}
	
	
}
