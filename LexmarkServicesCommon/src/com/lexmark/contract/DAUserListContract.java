package com.lexmark.contract;

import java.io.Serializable;

public class DAUserListContract implements Serializable {

	private static final long serialVersionUID = -5394546844885488041L;
	private Integer operId;
	private String jSessionNum;
	public String getJSessionNum() {
		return jSessionNum;
	}
	public void setJSessionNum(String sessionNum) {
		jSessionNum = sessionNum;
	}
	public Integer getOperId() {
		return operId;
	}
	public void setOperId(Integer operId) {
		this.operId = operId;
	}
}
