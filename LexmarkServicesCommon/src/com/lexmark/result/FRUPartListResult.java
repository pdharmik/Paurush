package com.lexmark.result;

import java.io.Serializable;
import java.util.List;
import com.lexmark.domain.Part;

public class FRUPartListResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3089050783762218060L;
	private int totalCount;
	private List<Part> partList;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<Part> getPartList() {
		return partList;
	}
	public void setPartList(List<Part> partList) {
		this.partList = partList;
	}
}
