package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.CachingContract;

public class LocalizedSiebelLOVListContract extends CachingContract implements Serializable {

	private static final long serialVersionUID = -5053603801774350293L;
	private String lovListName;
	private String localeName;
	private String partnerType;
	
	public String getLovListName() {
		return lovListName;
	}
	public void setLovListName(String lovListName) {
		this.lovListName = lovListName;
	}
	public String getLocaleName() {
		return localeName;
	}
	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}
	public String getPartnerType() {
		return partnerType;
	}
	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}
	@Override
	public String getCacheKey() {
		StringBuffer sb = new StringBuffer();
		sb.append(localeName);
		sb.append("-");
		sb.append(lovListName == null ? "" : lovListName);
		sb.append("-");
		sb.append(partnerType == null ? "" : partnerType);
		return sb.toString(); 
	}
}
