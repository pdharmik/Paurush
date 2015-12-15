package com.lexmark.api.bean;

/**
 * This is the common report service bean to hold the username, password,
 * baseURL for integration server
 *
 */
public class LexmarkReportServiceBean {

	private String username;
	private String password;
	private String baseURL;

	private String sessionCode;
	private int responseStatusCode;
/**
 * 
 * @return String
 */
	public String getUsername() {
		return username;
	}
/**
 * 
 * @param username 
 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 
	 * @return String
	 */
	public String getPassword() {
		return password;
	}
/**
 * 
 * @param password 
 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 
	 * @return String
	 */
	public String getBaseURL() {
		return baseURL;
	}
/**
 * 
 * @param baseURL 
 */
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
	/**
	 * 
	 * @return String
	 */
	public String getSessionCode() {
		return sessionCode;
	}
/**
 * 
 * @param sessionCode 
 */
	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}
	/**
	 * 
	 * @return int 
	 */
	public int getResponseStatusCode() {
		return responseStatusCode;
	}
/**
 * 
 * @param responseStatusCode 
 */
	public void setResponseStatusCode(int responseStatusCode) {
		this.responseStatusCode = responseStatusCode;
	}

}
