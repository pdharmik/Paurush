package com.lexmark.services.mock;

import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.services.api.CreateServiceRequest;


public class CreateServiceRequestImpl implements CreateServiceRequest {

	private String address;
	private String username;
	private String password;
	private String senderId; 
	private String senderName;
	private String receiverId;
	
	/**
	 * @return address 
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address 
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return username 
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username 
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password 
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return senderId 
	 */
	public String getSenderId() {
		return senderId;
	}

	/**
	 * @param senderId 
	 */
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	/**
	 * @return senderName 
	 */
	public String getSenderName() {
		return senderName;
	}

	/**
	 * @param senderName 
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * @return receiverId 
	 */
	public String getReceiverId() {
		return receiverId;
	}

	/**
	 * @param receiverId 
	 */
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public CreateServiceRequestResult createServiceRequest(
			CreateServiceRequestContract contract) throws Exception {
		CreateServiceRequestResult result = new CreateServiceRequestResult();
		result.setServiceRequestNumber("1234567216");
		result.setServiceRequestRowId("1234567216");		// added for mock test MPS breakfix
		return result;
	}

	/**
	 * @param contract 
	 * @param area 
	 * @param subArea 
	 * @return CreateServiceRequestResult 
	 * @throws Exception 
	 */
	public CreateServiceRequestResult createServiceRequestForCHLOthers(
			CreateServiceRequestContract contract,String area,String subArea) throws Exception {
		CreateServiceRequestResult result = new CreateServiceRequestResult();
		result.setServiceRequestNumber("1234567216");
		return result;
	}

}
