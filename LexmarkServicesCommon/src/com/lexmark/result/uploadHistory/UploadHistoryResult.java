package com.lexmark.result.uploadHistory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.UploadDetail;

public class UploadHistoryResult implements Serializable  {
	
	private static final long serialVersionUID = 1L;
    private int totalCount;
    private List<UploadDetail> uploadDetailList = new ArrayList<UploadDetail>();

    public void setTotalCount(int totalcount) {
        this.totalCount = totalcount;
    }

    public int getTotalCount() {
        return totalCount;
    }

	public void setUploadOrderList(List<UploadDetail> uploadOrderList) {
		this.uploadDetailList = uploadOrderList;
	}

	public List<UploadDetail> getUploadOrderList() {
		return uploadDetailList;
	}

   
}




