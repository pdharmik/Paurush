package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DAUserListResult implements Serializable {

	private static final long serialVersionUID = -4989496013954975850L;
	private List<String> mdmIdList;
	private String mdmLevel;
	private List<String> userNumberList = new ArrayList<String>();
	private String nodeId;
	private String userMode;
	
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public List<String> getUserNumberList() {
		return userNumberList;
	}
	public void setUserNumberList(List<String> userNumberList) {
		this.userNumberList = userNumberList;
	}
	public List<String> getMdmIdList() {
		return mdmIdList;
	}
	public void setMdmIdList(List<String> mdmIdList) {
		this.mdmIdList = mdmIdList;
	}
	public void setUserMode(String userMode) {
		this.userMode = userMode;
	}
	public String getUserMode() {
		return userMode;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeId() {
		return nodeId;
	}
}
