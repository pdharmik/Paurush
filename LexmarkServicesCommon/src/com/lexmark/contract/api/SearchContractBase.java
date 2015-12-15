package com.lexmark.contract.api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.lexmark.util.LangUtil;

public abstract class SearchContractBase extends ContractBase implements Serializable {

	private static final long serialVersionUID = -9159133078896691649L;
	private Map<String, Object> filterCriteria = new HashMap<String, Object>();
	private Map<String, Object> searchCriteria = new HashMap<String, Object>();
	private int startRecordNumber;
	private int increment;
	private boolean newQueryIndicator;
	private Map<String, Object> sortCriteria = new HashMap<String, Object>();
	private String accountName;
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Map<String, Object> getFilterCriteria() {
		return filterCriteria;
	}

	public void setFilterCriteria(Map<String, Object> filterCriteria) {
		this.filterCriteria = filterCriteria;
	}

	public Map<String, Object> getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(Map<String, Object> searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public int getStartRecordNumber() {
		return startRecordNumber;
	}

	public void setStartRecordNumber(int startRecordNumber) {
		this.startRecordNumber = startRecordNumber;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	public Map<String, Object> getSortCriteria() {
		return sortCriteria;
	}

	public void setSortCriteria(Map<String, Object> sortCriteria) {
		this.sortCriteria = sortCriteria;
	}
	
	public boolean isNewQueryIndicator() {
		return newQueryIndicator;
	}
	
	public void setNewQueryIndicator(boolean newQueryIndicator) {
		this.newQueryIndicator = newQueryIndicator;
	}
	
    public boolean hasSortCriteria() {
        return LangUtil.isNotEmpty(this.sortCriteria);
    } 
    
    public boolean hasFilterCriteria() {
        return LangUtil.isNotEmpty(this.filterCriteria);
    }  
}