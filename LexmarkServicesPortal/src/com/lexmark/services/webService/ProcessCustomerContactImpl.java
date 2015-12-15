package com.lexmark.services.webService;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lexmark.contract.ProcessCustomerContactContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.ProcessCustomerContactResult;
import com.lexmark.services.api.ProcessCustomerContact;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.webServices.processCustomerContact.client.CustomerContact2;
import com.lexmark.webServices.processCustomerContact.client.Data;
import com.lexmark.webServices.processCustomerContact.client.DocumentMetaData;
import com.lexmark.webServices.processCustomerContact.client.Envelope;
import com.lexmark.webServices.processCustomerContact.client.IntegrationFrequency;
import com.lexmark.webServices.processCustomerContact.client.ObjectChangeActionType;
import com.lexmark.webServices.processCustomerContact.client.PhysicalAddress;
import com.lexmark.webServices.processCustomerContact.client.ProcessCustomerContactWS;
import com.lexmark.webServices.processCustomerContact.client.ProcessCustomerContactWSLocator;
import com.lexmark.webServices.processCustomerContact.client.ProcessCustomerContactWS_PortType;
import com.lexmark.webServices.processCustomerContact.client.PublishContacts;
import com.lexmark.webServices.processCustomerContact.client.PublishContactsResponse;

public class ProcessCustomerContactImpl implements ProcessCustomerContact {
	
	private static final LEXLogger LEXLOGGER = LEXLogger.getLEXLogger(CustomerInvoiceServiceImpl.class);
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging

	private String address;
	private String userName;
	private String password;
	private String sourceSystem;
	private String businessProcess;
	private String integrationFrequency;
	private String objectChangeActionType;
	private String synchOrAsynch;
/**
 * 
 * @return String 
 */
	public String getSynchOrAsynch() {
		return synchOrAsynch;
	}
/**
 * 
 * @param synchOrAsynch 
 */
	public void setSynchOrAsynch(String synchOrAsynch) {
		this.synchOrAsynch = synchOrAsynch;
	}
/**
 * 
 * @return String 
 */
	public String getObjectChangeActionType() {
		return objectChangeActionType;
	}
/**
 * 
 * @param objectChangeActionType 
 */
	public void setObjectChangeActionType(String objectChangeActionType) {
		this.objectChangeActionType = objectChangeActionType;
	}
/**
 * |
 * @return String 
 */
	public String getSourceSystem() {
		return sourceSystem;
	}
/**
 * 
 * @param sourceSystem 
 */
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}
/**
 * 
 * @return String 
 */
	public String getBusinessProcess() {
		return businessProcess;
	}
/**
 * 
 * @param businessProcess 
 */
	public void setBusinessProcess(String businessProcess) {
		this.businessProcess = businessProcess;
	}
/**
 * 
 * @return String 
 */
	public String getIntegrationFrequency() {
		return integrationFrequency;
	}
/**
 * 
 * @param integrationFrequency 
 */
	public void setIntegrationFrequency(String integrationFrequency) {
		this.integrationFrequency = integrationFrequency;
	}
/**
 * 
 * @return String 
 */
	public String getAddress() {
		return address;
	}
/**
 * 
 * @param address 
 */
	public void setAddress(String address) {
		this.address = address;
	}
/**
 * 
 * @return String 
 */
	public String getUserName() {
		return userName;
	}
/**
 * 
 * @param userName 
 */
	public void setUserName(String userName) {
		this.userName = userName;
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
 */
	public ProcessCustomerContactImpl() {

	}

	/**
	 * @param address 
	 * @param userName 
	 * @param password 
	 * @param sourceSystem 
	 * @param businessProcess 
	 * @param integrationFrequency 
	 * @param objectChangeActionType 
	 * @param synchOrAsynch 
	 */
	public ProcessCustomerContactImpl(String address, String userName,
			String password, String sourceSystem, String businessProcess,
			String integrationFrequency, String objectChangeActionType, String synchOrAsynch) {
		LEXLOGGER.debug("Inside ProcessCustomerContactImpl Constructor");
		this.address = address;
		this.userName = userName;
		this.password = password;
		this.sourceSystem = sourceSystem;
		this.businessProcess = businessProcess;
		this.integrationFrequency = integrationFrequency;
		this.objectChangeActionType = objectChangeActionType;
		this.synchOrAsynch = synchOrAsynch;
	}

	/* (non-Javadoc)
	 * @see com.lexmark.services.api.ProcessCustomerContact#processCustomerContact(com.lexmark.contract.ProcessCustomerContactContract)
	 */
	/**
	 * 
	 * @param contract 
	 * @return ProcessCustomerContactResult 
	 * @throws Exception 
	 */
	public ProcessCustomerContactResult processCustomerContact(
			ProcessCustomerContactContract contract) throws Exception {
		
		LEXLOGGER.debug("Begin ProcessCustomerContactImpl processCustomerContact method");
		String customerName = "Lexmark International Inc.";
		String customerType = "Web Employee";
		ProcessCustomerContactWS locator = new ProcessCustomerContactWSLocator();
		ProcessCustomerContactWS_PortType port = locator
				.getLXKCustomerContact_webServices_provider_processCustomerContactWS_Port(new URL(
						getAddress()));

		org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());

		ObjectChangeActionType objectChangeActionType = ObjectChangeActionType
				.fromValue(getObjectChangeActionType());
		IntegrationFrequency integrationFrequency = IntegrationFrequency
				.fromValue(getIntegrationFrequency());

		// define the Document Meta Data
		DocumentMetaData documentMetaData = new DocumentMetaData(null,
				getSourceSystem(), getBusinessProcess(), null, null,
				objectChangeActionType, getCurrentDateTime(),
				integrationFrequency, null, null, null, null, null);
		// Define Employee Data
		AccountContact contact = contract.getEmployeeContact();
		PhysicalAddress physicalAddress = new PhysicalAddress(null, null, null,
				null, null, null, null, null, null, contact.getCountry(), null,
				null, null);
			
		Data employeeContact = new Data(contact.getContactId(), customerName, null, null, contact.getShortId(), customerType,
				contact.getFirstName(), contact.getLastName(), contact
						.getEmailAddress(), contact.getWorkPhone(), null,
				null, null, null, null, physicalAddress, null);
		// Merge the Document meta data and employee data
		CustomerContact2 contactData = new CustomerContact2(documentMetaData,
				employeeContact);
		// Merge the customer data document with its envelope
		com.lexmark.webServices.processCustomerContact.client.Envelope envelope = new Envelope();
		com.lexmark.webServices.processCustomerContact.client.CustomerContact contactDataWithEnvelope = new com.lexmark.webServices.processCustomerContact.client.CustomerContact(
				contactData, envelope);
		// create the input signature for the web service
		PublishContacts input = new PublishContacts(contactDataWithEnvelope,
				getSynchOrAsynch());
		
		long timeBeforeCall = System.currentTimeMillis();
		PublishContactsResponse response = port.processCustomerContact(input);
		long timeAfterCall=System.currentTimeMillis();
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.PROCESSCUSTOMERCONTACT_MSG_PROCESSCUSTOMERCONTACT, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,contract);
		//LEXLOGGER.logTime("** MPS PERFORMANCE TESTING PROCESS CUSTOMER CONTACT SERVICE CALL ==>: " + (System.currentTimeMillis() - timeBeforeCall) / 1000.0);

		ProcessCustomerContactResult result = new ProcessCustomerContactResult();
		result.setSiebelContactId(response.getContactReferenceId());
		LEXLOGGER.debug("End ProcessCustomerContactImpl processCustomerContact method");
		return result;
	}

	/**
	 * Get the current Date and Time
	 * @return Calendar 
	 */
	private Calendar getCurrentDateTime() {
		Calendar cal = Calendar.getInstance();
		try {
			final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
			DateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			String currentDate = sdf.format(cal.getTime());
			Date date;
			date = (Date) sdf.parse(currentDate);
			cal.setTime(date);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return cal;
	}
}
