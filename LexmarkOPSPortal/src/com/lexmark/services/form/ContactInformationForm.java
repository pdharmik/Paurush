package com.lexmark.services.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.ContactInformation;

public class ContactInformationForm implements Serializable{
	private static final long serialVersionUID = 6184036239020816141L;
	
	private List<ContactInformation> contactInformations = new ArrayList<ContactInformation>();
	private int totalCount;
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<ContactInformation> getContactInformations() {
		return contactInformations;
	}

	public void setContactInformations(List<ContactInformation> contactInformations) {
		this.contactInformations = contactInformations;
	}
	
}
