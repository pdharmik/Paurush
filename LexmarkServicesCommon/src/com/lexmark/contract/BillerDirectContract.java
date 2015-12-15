package com.lexmark.contract;

import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.SapPortlet;

public class BillerDirectContract {
	private List<SapPortlet> sapPortletList = new ArrayList<SapPortlet>();
	private String deleteURL;
	private String deleteSubRowURLId;
	public String getDeleteURL() {
		return deleteURL;
	}
	public void setDeleteURL(String deleteURL) {
		this.deleteURL = deleteURL;
	}
	public String getDeleteSubRowURLId() {
		return deleteSubRowURLId;
	}
	public void setDeleteSubRowURLId(String deleteSubRowURLId) {
		this.deleteSubRowURLId = deleteSubRowURLId;
	}
	public List<SapPortlet> getSapPortletList() {
		return sapPortletList;
	}
	public void setSapPortletList(List<SapPortlet> sapPortletList) {
		this.sapPortletList = sapPortletList;
	}
}
