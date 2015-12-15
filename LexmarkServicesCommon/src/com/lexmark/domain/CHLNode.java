package com.lexmark.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Business object to construct customer hierarchy which is located
 * in the left navigation of device page.
 * @author roger.lin
 *
 */
public class CHLNode implements Serializable {

	private static final long serialVersionUID = 8039563097272756393L;
	
	private String chlNodeName;
	private String chlNodeId;
	private String chlParentId;
	private boolean hasChild;
	
	private List<CHLNode> childNodeList = new ArrayList<CHLNode>();
	public String getChlNodeName() {
		return chlNodeName;
	}
	public void setChlNodeName(String chlNodeName) {
		this.chlNodeName = chlNodeName;
	}
	public String getCHLNodeId() {
		return chlNodeId;
	}
	public void setCHLNodeId(String chlNodeId) {
		this.chlNodeId = chlNodeId;
	}
	
	public List<CHLNode> getChildNodeList() {
		return childNodeList;
	}
	
	public void setChildNodeList(List<CHLNode> childNodeList) {
		if (childNodeList != null) {
			this.childNodeList = childNodeList;
		}
		else {
			this.childNodeList = new ArrayList<CHLNode>();
		}
	}
	
	public void addChildNode(CHLNode childNode) {
		if (childNode == null) {
			return;
		}
		this.childNodeList.add(childNode);
	}
	
	public void setChlParentId(String chlParentId) {
		this.chlParentId = chlParentId;
	}
	public String getChlParentId() {
		return chlParentId;
	}
	
	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}
	public boolean getHasChild() {
		return hasChild;
	}
}
