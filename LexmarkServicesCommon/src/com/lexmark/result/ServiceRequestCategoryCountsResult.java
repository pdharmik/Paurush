package com.lexmark.result;

import java.io.Serializable;

public class ServiceRequestCategoryCountsResult implements Serializable {
	private static final long serialVersionUID = -2124141508510823870L;
	private int totalCount;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}
