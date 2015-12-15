package com.lexmark.api.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author AN312454 This bean is used for creating json for create and download
 *         document.
 */
public class InDocument {
	private InInfo info = new InInfo();
	private Set<InProperty> properties = new HashSet<InProperty>();
	private List<InPage> pages;

	/**
	 * 
	 * @return info 
	 */
	public InInfo getInfo() {
		return info;
	}

	/**
	 * @param info 
	 */
	public void setInfo(InInfo info) {
		this.info = info;
	}

	/**
	 * 
	 * @return properties 
	 */
	public Set<InProperty> getProperties() {
		return properties;
	}

	/**
	 * 
	 * @param properties 
	 */
	public void setProperties(Set<InProperty> properties) {
		this.properties = properties;
	}

	/**
	 * @return pages 
	 */
	public List<InPage> getPages() {
		if (this.pages == null) {
			this.pages = new ArrayList<InPage>();
		}
		return this.pages;
	}

	/**
	 * 
	 * @param pages 
	 */
	public void setPages(List<InPage> pages) {
		if (this.pages == null) {
			this.pages = new ArrayList<InPage>();
		}
		this.pages = pages;
	}
}
