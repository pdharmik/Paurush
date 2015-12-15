package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Activity;

/**
 * Returned result of retrieving open claim list.
 *
 */
public class OpenClaimListResult implements	Serializable {

	private static final long serialVersionUID = 3892836332100905452L;
	
	private int totalCount;
	
	private List<Activity> claimList = new ArrayList<Activity>(0);

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<Activity> getClaimList() {
		return claimList;
	}

	public void setClaimList(List<Activity> claimList) {
		this.claimList = claimList;
	}

}
