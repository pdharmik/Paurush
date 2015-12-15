package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.CHLNode;

public class AssetReportingHierarchyResult implements Serializable {

	private static final long serialVersionUID = 1459478599804798389L;
	private List<CHLNode> chlNodeList;
	public List<CHLNode> getChlNodeList() {
		return chlNodeList;
	}
	public void setChlNodeList(List<CHLNode> chlNodeList) {
		this.chlNodeList = chlNodeList;
	}
}