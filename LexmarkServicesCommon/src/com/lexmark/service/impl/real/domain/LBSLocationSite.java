package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

public class LBSLocationSite implements Serializable {
	private static final long serialVersionUID = 6483553507743496750L;

	private String site;
	private String siteId;
	
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	
}
