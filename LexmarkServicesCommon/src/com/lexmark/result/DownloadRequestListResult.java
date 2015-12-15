package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.DownloadRequest;

public class DownloadRequestListResult implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private int totalcount;
	private List<DownloadRequest> downloadRequestList = new ArrayList<DownloadRequest>();
	
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getTotalcount() {
		return totalcount;
	}
	
	public void setActivityList(List<DownloadRequest> activityList) {
		this.downloadRequestList = activityList;
	}
	public List<DownloadRequest> getActivityList() {
		return downloadRequestList;
	}

}
