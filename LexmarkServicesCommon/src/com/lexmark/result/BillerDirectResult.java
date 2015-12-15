package com.lexmark.result;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lexmark.domain.BillerDirect;
import com.lexmark.domain.SapPortlet;
import com.lexmark.domain.SupportedLocale;

public class BillerDirectResult {
	private List<BillerDirect> billerDirectList = new ArrayList<BillerDirect>();
	private List<SapPortlet> sapPortletList = new ArrayList<SapPortlet>();
	private Map<String, String> languageMap = new LinkedHashMap<String, String>();
	public List<BillerDirect> getBillerDirectList() {
		return billerDirectList;
	}
	public void setBillerDirectList(List<BillerDirect> billerDirectList) {
		this.billerDirectList = billerDirectList;
	}
	public List<SapPortlet> getSapPortletList() {
		return sapPortletList;
	}
	public void setSapPortletList(List<SapPortlet> sapPortletList) {
		this.sapPortletList = sapPortletList;
	}
	public Map<String, String> getLanguageMap() {
		return languageMap;
	}
	public void setLanguageMap(Map<String, String> languageMap) {
		this.languageMap = languageMap;
	}
}
