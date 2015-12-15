package com.lexmark.result.partnerPayments;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.SRDetail;

public class SRDetailResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private int totalCount;
	private List<SRDetail> srDetailsList;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<SRDetail> getSrDetailsList() {
		return srDetailsList;
	}
	public void setSrDetailsList(List<SRDetail> srDetailsList) {
		this.srDetailsList = srDetailsList;
	}
	
}
