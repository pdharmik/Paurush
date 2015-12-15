package com.lexmark.domain;

import java.io.Serializable;
import java.util.List;

public class SiebelAccountNode implements Serializable {

	private static final long serialVersionUID = -2271952643160007311L;
	private String name;
	private String nodeId;
	private List<CHLNode> chlNodeList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CHLNode> getChlNodeList() {
		return chlNodeList;
	}
	public void setChlNodeList(List<CHLNode> chlNodeList) {
		this.chlNodeList = chlNodeList;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}	
}
