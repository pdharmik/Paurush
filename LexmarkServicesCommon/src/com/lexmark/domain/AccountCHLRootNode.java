package com.lexmark.domain;

import java.io.Serializable;
import java.util.List;

public class AccountCHLRootNode implements Serializable {

	private static final long serialVersionUID = -545961095988797648L;
	private String name;
	private String nodeId;
	private List<MDMAccountNode> mdmAccountNodeList;
	private List<SiebelAccountNode> siebelAccountNodeList;
	public List<MDMAccountNode> getMdmAccountNodeList() {
		return mdmAccountNodeList;
	}
	public void setMdmAccountNodeList(List<MDMAccountNode> mdmAccountNodeList) {
		this.mdmAccountNodeList = mdmAccountNodeList;
	}
	public List<SiebelAccountNode> getSiebelAccountNodeList() {
		return siebelAccountNodeList;
	}
	public void setSiebelAccountNodeList(
			List<SiebelAccountNode> siebelAccountNodeList) {
		this.siebelAccountNodeList = siebelAccountNodeList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
}
