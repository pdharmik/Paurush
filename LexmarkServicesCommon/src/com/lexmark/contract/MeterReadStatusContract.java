package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.service.api.CrmSessionHandle;

// TODO (pkozlov) make it extends ContractBase.class
// and remove the sessionHandle field?
public class MeterReadStatusContract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9063143173265931852L;
	private String serviceSessionId;
	private CrmSessionHandle sessionHandle;
	private String mdmId;
	private String userFileName;
	private boolean alliancePartner;                    //flag created for Dell/Boeing split calls
	
	
	public boolean isAlliancePartner() {
		return alliancePartner;
	}
	public void setAlliancePartner(boolean alliancePartner) {
		this.alliancePartner = alliancePartner;
	}
	
	public String getUserFileName() {
		return userFileName;
	}
	public void setUserFileName(String userFileName) {
		this.userFileName = userFileName;
	}
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	@Deprecated
	public String getServiceSessionId() {
		return serviceSessionId;
	}
	@Deprecated
	public void setServiceSessionId(String serviceSessionId) {
		this.serviceSessionId = serviceSessionId;
	}
	
	/**
	 * Session handle used by service maintain siebel session
	 * @param sessionHandle handle object
	 */
	public void setSessionHandle(CrmSessionHandle sessionHandle) {
		this.sessionHandle = sessionHandle;
	}
	/**
	 * Session handle used by service maintain siebel session
	 * @return sessionHandle
	 */
	public CrmSessionHandle getSessionHandle() {
		return sessionHandle;
	}
}
