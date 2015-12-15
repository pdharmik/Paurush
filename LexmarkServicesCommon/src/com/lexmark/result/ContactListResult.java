package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.lexmark.domain.AccountContact;

public class ContactListResult implements Serializable {
	private static final long serialVersionUID = -8673714178323375278L;
	private int totalCount;
	private List<AccountContact> contacts  = new ArrayList<AccountContact>(0);
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<AccountContact> getContacts() {
		return contacts;
	}
	public void setContacts(List<AccountContact> contacts) {
		this.contacts = contacts;
	}
	

}
