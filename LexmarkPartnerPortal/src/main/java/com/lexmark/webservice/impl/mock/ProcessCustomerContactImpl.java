package com.lexmark.webservice.impl.mock;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.ProcessCustomerContactContract;
import com.lexmark.result.ProcessCustomerContactResult;
import com.lexmark.webservice.api.ProcessCustomerContact;

public class ProcessCustomerContactImpl implements ProcessCustomerContact {
	private static Logger logger = LogManager.getLogger(ProcessCustomerContactImpl.class);
	private String address;
	private String userName;
	private String password;
	private String sourceSystem;
	private String businessProcess;
	private String integrationFrequency;
	private String objectChangeActionType;
	private String synchOrAsynch;

	public String getSynchOrAsynch() {
		return synchOrAsynch;
	}

	public void setSynchOrAsynch(String synchOrAsynch) {
		this.synchOrAsynch = synchOrAsynch;
	}

	public String getObjectChangeActionType() {
		return objectChangeActionType;
	}

	public void setObjectChangeActionType(String objectChangeActionType) {
		this.objectChangeActionType = objectChangeActionType;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getBusinessProcess() {
		return businessProcess;
	}

	public void setBusinessProcess(String businessProcess) {
		this.businessProcess = businessProcess;
	}

	public String getIntegrationFrequency() {
		return integrationFrequency;
	}

	public void setIntegrationFrequency(String integrationFrequency) {
		this.integrationFrequency = integrationFrequency;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

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

	public ProcessCustomerContactImpl() {

	}

	public ProcessCustomerContactImpl(String address, String userName,
			String password, String sourceSystem, String businessProcess,
			String integrationFrequency, String objectChangeActionType, String synchOrAsynch) {

		this.address = address;
		this.userName = userName;
		this.password = password;
		this.sourceSystem = sourceSystem;
		this.businessProcess = businessProcess;
		this.integrationFrequency = integrationFrequency;
		this.objectChangeActionType = objectChangeActionType;
		this.synchOrAsynch = synchOrAsynch;
	}

	public ProcessCustomerContactResult processCustomerContact(
			ProcessCustomerContactContract contract) throws Exception {
		
		ProcessCustomerContactResult result = new ProcessCustomerContactResult();
		result.setSiebelContactId("1-BGJ7-129");
		logger.info("Start of the thread");
		
		/*Thread.currentThread().sleep(10000);
		throw new SocketTimeoutException();*/
		try {
			logger.debug("values are "+contract.getEmployeeContact().getCountry()+" "+contract.getEmployeeContact().getContactId()+" "+contract.getEmployeeContact().getFirstName()+" "+contract.getEmployeeContact().getLastName()
					+" "+contract.getEmployeeContact().getEmailAddress()+" "+contract.getEmployeeContact().getWorkPhone());
			Thread.currentThread().sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		logger.info("Exit from the thread");
		return result;
	
	}
}
