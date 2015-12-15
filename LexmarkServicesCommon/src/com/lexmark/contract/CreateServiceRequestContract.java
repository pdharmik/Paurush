package com.lexmark.contract;

import java.io.Serializable;
import java.util.Locale;

import com.lexmark.contract.api.ContractBase;
import com.lexmark.domain.ServiceRequest;

public class CreateServiceRequestContract extends ContractBase implements Serializable {
	private static final long serialVersionUID = -1222866826788365616L;
	private ServiceRequest serviceRequest; 
	private String contactId;
	private Locale locale;
	
	private String mdmId;
	private String mdmLevel;
	//Added for Update of SR
	private String prevSrNo;
	/*private String area;//This is for the web service call for CM
	private String subArea;//This is for the web service call for CM
*/	private String userType;
	
	/* requestedFrom has been added for JAN 
	 * release for cancel request
	 */
	private String requestedFrom;
	private String moveType;
	public boolean isMoveAssetFlag() {
		return moveAssetFlag;
	}
	public void setMoveAssetFlag(boolean moveAssetFlag) {
		this.moveAssetFlag = moveAssetFlag;
	}
	public boolean isMoveContactSelectFlag() {
		return moveContactSelectFlag;
	}
	public void setMoveContactSelectFlag(boolean moveContactSelectFlag) {
		this.moveContactSelectFlag = moveContactSelectFlag;
	}
	//Added for LBS
	private String fleetManagementFlag;
	
	
	private boolean moveAssetFlag;
	private boolean moveContactSelectFlag;

	
	public String getFleetManagementFlag() {
		return fleetManagementFlag;
	}
	public void setFleetManagementFlag(String fleetManagementFlag) {
		this.fleetManagementFlag = fleetManagementFlag;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/*public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSubArea() {
		return subArea;
	}
	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}*/
	public String getPrevSrNo() {
		return prevSrNo;
	}
	public void setPrevSrNo(String prevSrNo) {
		this.prevSrNo = prevSrNo;
	}
	
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public void setRequestedFrom(String requestedFrom) {
		this.requestedFrom = requestedFrom;
	}
	public String getRequestedFrom() {
		return requestedFrom;
	}
	/**
	 * @param moveType the moveType to set
	 */
	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}
	/**
	 * @return the moveType
	 */
	public String getMoveType() {
		return moveType;
	}
	

	
}
