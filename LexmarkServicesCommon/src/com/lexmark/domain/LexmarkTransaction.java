package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * An immutable class representing a transaction made to the target system.
 *
 *
 * @author Alex Zhou <alexzhou216@gmail.com>
 */
public class LexmarkTransaction implements Serializable {

	private Long id = null;	
	private String invoker;
	private String targetSystem;
	private String webServiceName;
	private String accountID;
	private String loginUser;
	private Date startTime;
	private Date endTime;
	private Long duration;
	

	/**
	 * No-arg constructor for JavaBean tools.
	 */
	public LexmarkTransaction() {
	}

	/**
	 * Full constructor.
	 */
	
	public LexmarkTransaction(String targetSystem,
			String webServiceName, String accountID,String loginUser) {
		Exception e = new Exception();
		this.invoker = e.getStackTrace()[2].getClassName() + "." + e.getStackTrace()[2].getMethodName();
		this.targetSystem = targetSystem;
		this.webServiceName = webServiceName;
		this.accountID = accountID;
		this.loginUser = loginUser;	
		this.startTime = new Date();
	}
	
	
	// ********************** Hash and equal ********************** //
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountID == null) ? 0 : accountID.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((invoker == null) ? 0 : invoker.hashCode());
		result = prime * result
				+ ((loginUser == null) ? 0 : loginUser.hashCode());
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result
				+ ((targetSystem == null) ? 0 : targetSystem.hashCode());
		result = prime * result
				+ ((webServiceName == null) ? 0 : webServiceName.hashCode());
		return result;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LexmarkTransaction other = (LexmarkTransaction) obj;
		if (accountID == null) {
			if (other.accountID != null)
				return false;
		} else if (!accountID.equals(other.accountID))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (invoker == null) {
			if (other.invoker != null)
				return false;
		} else if (!invoker.equals(other.invoker))
			return false;
		if (loginUser == null) {
			if (other.loginUser != null)
				return false;
		} else if (!loginUser.equals(other.loginUser))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (targetSystem == null) {
			if (other.targetSystem != null)
				return false;
		} else if (!targetSystem.equals(other.targetSystem))
			return false;
		if (webServiceName == null) {
			if (other.webServiceName != null)
				return false;
		} else if (!webServiceName.equals(other.webServiceName))
			return false;
		return true;
	}

	// ********************** Accessor Methods ********************** //
	public Long getId() {
		return id;
	}

	

	public String getInvoker() {
		return invoker;
	}

	public void setInvoker(String invoker) {
		this.invoker = invoker;
	}

	public String getTargetSystem() {
		return targetSystem;
	}

	public void setTargetSystem(String targetSystem) {
		this.targetSystem = targetSystem;
	}

	public String getWebServiceName() {
		return webServiceName;
	}

	public void setWebServiceName(String webServiceName) {
		this.webServiceName = webServiceName;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
	public Long getDuration() {
		return duration;
	}
	
	//calculate the duration of webservice call
	public void calculateDuration() {
		Long d;
		d = getEndTime().getTime() - getStartTime().getTime();
		setDuration(d);		
	}

}
