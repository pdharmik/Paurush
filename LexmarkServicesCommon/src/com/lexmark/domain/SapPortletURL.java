package com.lexmark.domain;

import java.io.Serializable;

public class SapPortletURL implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6366839061590438392L;
	private Integer id;
	//private Integer urlId;
	private String functionalityName;
	private String functionalityURL;
	private String language;
	private SapPortlet urls;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/*public Integer getUrlId() {
		return urlId;
	}
	public void setUrlId(Integer urlId) {
		this.urlId = urlId;
	}*/
	public String getFunctionalityName() {
		return functionalityName;
	}
	public void setFunctionalityName(String functionalityName) {
		this.functionalityName = functionalityName;
	}
	public String getFunctionalityURL() {
		return functionalityURL;
	}
	public void setFunctionalityURL(String functionalityURL) {
		this.functionalityURL = functionalityURL;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public SapPortlet getUrls() {
		return urls;
	}
	public void setUrls(SapPortlet urls) {
		this.urls = urls;
	}
}
