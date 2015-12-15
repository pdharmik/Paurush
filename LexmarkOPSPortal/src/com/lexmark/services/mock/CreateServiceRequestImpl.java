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
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverId() {
		return receiverId;
	}

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

	public CreateServiceRequestResult createServiceRequestForCHLOthers(
			CreateServiceRequestContract contract,String area,String subArea) throws Exception {
		CreateServiceRequestResult result = new CreateServiceRequestResult();
		result.setServiceRequestNumber("1234567216");
		return result;
	}

}
