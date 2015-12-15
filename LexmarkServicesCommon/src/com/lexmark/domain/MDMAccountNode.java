package com.lexmark.domain;

import java.io.Serializable;
import java.util.List;

public class MDMAccountNode implements Serializable {

	private static final long serialVersionUID = 14674185972762656L;
	private String name;
	private String nodeId;
	private List<SiebelAccountNode> siebelAccountNodeList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SiebelAccountNode> getSiebelAccountNodeList() {
		return siebelAccountNodeList;
	}
	public void setSiebelAccountNodeList(
			List<SiebelAccountNode> siebelAccountNodeList) {
		this.siebelAccountNodeList = siebelAccountNodeList;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
}
