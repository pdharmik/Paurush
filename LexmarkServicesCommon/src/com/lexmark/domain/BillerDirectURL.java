package com.lexmark.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BillerDirectURL implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8049599852508073408L;
	private List<BillerDirect> billerDirectList = new ArrayList<BillerDirect>();

	public List<BillerDirect> getBillerDirectList() {
		return billerDirectList;
	}

	public void setBillerDirectList(List<BillerDirect> billerDirectList) {
		this.billerDirectList = billerDirectList;
	}
}
