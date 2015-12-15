package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.DownloadClaim;

public class DownloadClaimListResult implements Serializable {
    
    private static final long serialVersionUID = 1L;
	
	private int totalcount;
	private List<DownloadClaim> downloadClaimList = new ArrayList<DownloadClaim>();
	
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setActivityList(List<DownloadClaim> activityList) {
		this.downloadClaimList = activityList;
	}
	public List<DownloadClaim> getActivityList() {
		return downloadClaimList;
	}

}
