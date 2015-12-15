package com.lexmark.service.impl.real.domain;

import java.util.Date;

import com.amind.common.domain.BaseEntity;

public class PartnerDetailNoteBase extends BaseEntity  {
	
	private Date noteDate;
	private String authorFirstName;
	private String authorLastName;
	private String details;
	private String contactId;
	
	public Date getNoteDate() {
		return noteDate;
	}
	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
	}
	public String getAuthorFirstName() {
		return authorFirstName;
	}
	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}
	public String getAuthorLastName() {
		return authorLastName;
	}
	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getContactId() {
		return contactId;
	}
	
	
}
