package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.CachingContract;

public class LocalizedSiebelValueContract extends CachingContract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6346486276013303242L;
	private String lovListName;
	private String lovValue;
	private String localeName;
	
	public String getLovListName() {
		return lovListName;
	}
	public void setLovListName(String lovListName) {
		this.lovListName = lovListName;
	}
	public String getLovValue() {
		return lovValue;
	}
	public void setLovValue(String lovValue) {
		this.lovValue = lovValue;
	}
	public String getLocaleName() {
		return localeName;
	}
	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}
	@Override
	public String getCacheKey() {
		StringBuffer sb = new StringBuffer();
		sb.append(localeName);
		sb.append("-");
		sb.append(lovListName == null ? "" : lovListName);
		sb.append("-");
		sb.append(lovValue == null ? "" : lovValue);
		return sb.toString(); 
	}
}
