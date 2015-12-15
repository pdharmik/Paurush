package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;

/**
 * mapping file: "do-chlaccountdo.xml"
 */
public class CHLAccountDo extends AccountBasedDo {

	private static final long serialVersionUID = -5476577155452748374L;
	
	private String parentChain;
	private String location;
	private String parentAccountId;
	private ArrayList<CHLAccountCHLDo> chlList = new ArrayList<CHLAccountCHLDo>();
	
	public ArrayList<CHLAccountCHLDo> getChlList() {
		return chlList;
	}

	public void setChlList(ArrayList<CHLAccountCHLDo> chlList) {
		this.chlList = chlList;
	}

	public String getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(String parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getParentChain() {
		return parentChain;
	}

	public void setParentChain(String parentChain) {
		this.parentChain = parentChain;
	}
}
