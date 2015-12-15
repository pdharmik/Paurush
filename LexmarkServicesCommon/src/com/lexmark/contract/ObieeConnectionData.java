package com.lexmark.contract;

import java.io.Serializable;

public class ObieeConnectionData implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String hostName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	
}
