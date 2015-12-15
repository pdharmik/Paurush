package com.lexmark.service.impl.real.domain;

/**
 * mapping file: "do-globalcontactdo-mapping.xml"
 */
public class GlobalContactDO extends AccountBasedDo {
	private static final long serialVersionUID = -1030290889993644740L;
	
	private String noteType;
	private String note;
	private String accountLevel;

	public String getAccountLevel() {
		return accountLevel;
	}

	public void setAccountLevel(String accountLevel) {
		this.accountLevel = accountLevel;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNoteType() {
		return noteType;
	}

	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}

}
