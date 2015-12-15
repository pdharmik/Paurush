package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.ContactInformation;

public class ContactInformationResult implements Serializable{
	private static final long serialVersionUID = 1877421328409128079L;
	
	private int totalCount = 0;
	private List<ContactInformation> contactInfoList;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<ContactInformation> getContactInfoList() {
		return contactInfoList;
	}
	public void setContactInfoList(List<ContactInformation> contactInfoList) {
		this.contactInfoList = contactInfoList;
	}
}
