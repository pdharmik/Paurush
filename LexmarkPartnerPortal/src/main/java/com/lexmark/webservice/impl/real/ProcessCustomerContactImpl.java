package com.lexmark.webservice.impl.real;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.lexmark.contract.ProcessCustomerContactContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.result.ProcessCustomerContactResult;
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
import com.lexmark.webservice.api.ProcessCustomerContact;

public class ProcessCustomerContactImpl implements ProcessCustomerContact {

	private String address;
	private String userName;
	private String password;
	private String sourceSystem;
	private String businessProcess;
	private String integrationFrequency;
	private String objectChangeActionType;
	private String synchOrAsynch;
	
	
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

	

	public ProcessCustomerContactResult processCustomerContact(
			ProcessCustomerContactContract contract) throws Exception {

		String customerName = "Lexmark International Inc.";
		String customerType = "Web Employee";
		ProcessCustomerContactWS locator = new ProcessCustomerContactWSLocator();
		ProcessCustomerContactWS_PortType port = locator
				.getLXKCustomerContact_webServices_provider_processCustomerContactWS_Port(new URL(
						getAddress()));

		org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());

		// define the Document Meta Data

		ObjectChangeActionType objectChangeActionType = ObjectChangeActionType
				.fromValue(getObjectChangeActionType());
		IntegrationFrequency integrationFrequency = IntegrationFrequency
				.fromValue(getIntegrationFrequency());

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

		PublishContactsResponse response = port.processCustomerContact(input);

		ProcessCustomerContactResult result = new ProcessCustomerContactResult();
		result.setSiebelContactId(response.getContactReferenceId());

		return result;
	}

	private Calendar getCurrentDateTime() {
		Calendar cal = Calendar.getInstance();
		try {
			final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
			DateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			String currentDate = sdf.format(cal.getTime());
			Date date=null;
			date = (Date) sdf.parse(currentDate);
			cal.setTime(date);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return cal;
	}
}
