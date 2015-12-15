package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.PageCounts;

public class PageCountsResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	List<PageCounts> pageCounts;

	public List<PageCounts> getPageCounts() {
		return pageCounts;
	}

	public void setPageCounts(List<PageCounts> pageCounts) {
		this.pageCounts = pageCounts;
	}

}
