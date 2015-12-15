package com.lexmark.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SapPortlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3557458731552199243L;
	private Integer id;
	private String headerType;
	private String maxLinksAllowed;
	private String height;
	private String width;
	private String languageSupport;
	private String gridFunctionalityName;
	private String gridFunctionalityURL;
	private List<SapPortletURL> sapPortletURLs = new ArrayList<SapPortletURL>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHeaderType() {
		return headerType;
	}
	public void setHeaderType(String headerType) {
		this.headerType = headerType;
	}
	public String getMaxLinksAllowed() {
		return maxLinksAllowed;
	}
	public void setMaxLinksAllowed(String maxLinksAllowed) {
		this.maxLinksAllowed = maxLinksAllowed;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getLanguageSupport() {
		return languageSupport;
	}
	public void setLanguageSupport(String languageSupport) {
		this.languageSupport = languageSupport;
	}
	public List<SapPortletURL> getSapPortletURLs() {
		return sapPortletURLs;
	}
	public void setSapPortletURLs(List<SapPortletURL> sapPortletURLs) {
		this.sapPortletURLs = sapPortletURLs;
	}
	public String getGridFunctionalityName() {
		return gridFunctionalityName;
	}
	public void setGridFunctionalityName(String gridFunctionalityName) {
		this.gridFunctionalityName = gridFunctionalityName;
	}
	public String getGridFunctionalityURL() {
		return gridFunctionalityURL;
	}
	public void setGridFunctionalityURL(String gridFunctionalityURL) {
		this.gridFunctionalityURL = gridFunctionalityURL;
	}
}
