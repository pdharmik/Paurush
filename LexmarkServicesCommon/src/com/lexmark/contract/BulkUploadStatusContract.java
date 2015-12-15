package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.service.api.CrmSessionHandle;

public class BulkUploadStatusContract implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1837811144438100139L;
	private String userFileName;
	private CrmSessionHandle sessionHandle;
	private String mdmId;
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public CrmSessionHandle getSessionHandle() {
		return sessionHandle;
	}
	public void setSessionHandle(CrmSessionHandle sessionHandle) {
		this.sessionHandle = sessionHandle;
	}
	public String getUserFileName() {
		return userFileName;
	}
	public void setUserFileName(String userFileName) {
		this.userFileName = userFileName;
	}
}
