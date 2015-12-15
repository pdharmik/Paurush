package com.lexmark.domain;

import java.io.Serializable;

public class PageCounts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6816900679593919355L;

	private String name;
	private String count;
	private String deinstalledCount;
	private String date;
	private String siebelName;
	private boolean bothValueBlank;
	private String type;
	private String deinstalledType;
	public PageCounts() {};
	
	public PageCounts(String name, String count, String date) {
		this.name = name;
		this.count = count;
		this.date = date;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSiebelName() {
		return siebelName;
	}
	public void setSiebelName(String siebelName) {
		this.siebelName = siebelName;
	}
	public boolean isBothValueBlank() {
		return bothValueBlank;
	}
	public void setBothValueBlank(boolean bothValueBlank) {
		this.bothValueBlank = bothValueBlank;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeinstalledCount() {
		return deinstalledCount;
	}

	public void setDeinstalledCount(String deinstalledCount) {
		this.deinstalledCount = deinstalledCount;
	}

	public String getDeinstalledType() {
		return deinstalledType;
	}

	public void setDeinstalledType(String deinstalledType) {
		this.deinstalledType = deinstalledType;
	}

}
