package com.lexmark.result;

import java.util.List;

import com.lexmark.domain.Bundle;

public class BundleListResult {
	private List<Bundle> bundleList;
    private int totalCount;
    
	public List<Bundle> getBundleList() {
		return bundleList;
	}
	public void setBundleList(List<Bundle> bundleList) {
		this.bundleList = bundleList;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
    
}
