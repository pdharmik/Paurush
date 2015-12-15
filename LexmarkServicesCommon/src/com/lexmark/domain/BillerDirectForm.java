package com.lexmark.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BillerDirectForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9222732189356470888L;
	private String sno;
	private String funcName;
	private String funcUrl;
	private String languageSupport;
	private String funcURLId;
	private String deletedURL;
	private String deleteSubRowURLId;
	private List<BillerDirectURL> billerDirectURLList = new ArrayList<BillerDirectURL>();
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getFuncUrl() {
		return funcUrl;
	}
	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}
	public String getLanguageSupport() {
		return languageSupport;
	}
	public void setLanguageSupport(String languageSupport) {
		this.languageSupport = languageSupport;
	}
	public List<BillerDirectURL> getBillerDirectURLList() {
		return billerDirectURLList;
	}
	public void setBillerDirectURLList(List<BillerDirectURL> billerDirectURLList) {
		this.billerDirectURLList = billerDirectURLList;
	}
	public String getFuncURLId() {
		return funcURLId;
	}
	public void setFuncURLId(String funcURLId) {
		this.funcURLId = funcURLId;
	}
	public String getDeletedURL() {
		return deletedURL;
	}
	public void setDeletedURL(String deletedURL) {
		this.deletedURL = deletedURL;
	}
	public String getDeleteSubRowURLId() {
		return deleteSubRowURLId;
	}
	public void setDeleteSubRowURLId(String deleteSubRowURLId) {
		this.deleteSubRowURLId = deleteSubRowURLId;
	}
}
