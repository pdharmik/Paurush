package com.lexmark.webservice.impl.real;

import java.net.URL;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressCleanseWSInput;
import com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressCleanseWSOutput;
import com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressData;
import com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressData2;
import com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressWSInput;
import com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData11;
import com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestWS;
import com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestWSLocator;
import com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestWS_PortType;
import com.lexmark.domain.GenericAddress;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.webservice.api.AddressCleansingService;

import java.util.logging.Level;


public class AddressCleansingServiceImpl implements AddressCleansingService {
	private static Logger LOGGER = LogManager.getLogger(AddressCleansingServiceImpl.class);
	
	
	private String address;
	private String senderId;
	private String senderName;
	private String receiverId;
	private String userName;
	private String password;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public GenericAddress addressCleanse(GenericAddress addressDataInput, Map<String, String> accountDetails) 
					throws LGSRuntimeException, LGSBusinessException {
		
		AddressData2 addressResponseData=new AddressData2();
		try {
			ServiceRequestWS locator = new ServiceRequestWSLocator();
			ServiceRequestWS_PortType port = locator.getServiceRequest_serviceRequestWS_Port(new URL(getAddress()));
			
			org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
				stub.setUsername(getUserName());
				stub.setPassword(getPassword());
			
			AddressData addressData = new AddressData();
				addressData.setAccountId(accountDetails.get("accountId"));
				addressData.setAddressLine1(addressDataInput.getAddressLine1());
				addressData.setAddressLine2(addressDataInput.getAddressLine2());
				addressData.setCity(addressDataInput.getCity());
				addressData.setCountry(addressDataInput.getCountry());
				addressData.setPostalCode(addressDataInput.getPostalCode());
				if(addressDataInput.getState() ==  null)				{
					addressData.setStateOrProvince(addressDataInput.getProvince());				
				}else{
					addressData.setStateOrProvince(addressDataInput.getState());
				}
			printLogger("1.----AddressData---- Account Id ------------ : "+accountDetails.get("accountId"));
			printLogger("2.----AddressData---- AddressLine1------------ : "+addressDataInput.getAddressLine1());
			printLogger("3.----AddressData---- AddressLine2------------ : "+addressDataInput.getAddressLine2());
			printLogger("4.----AddressData---- City ------------ : "+addressDataInput.getCity());
			printLogger("5.----AddressData---- Country ------------ : "+addressDataInput.getCountry());
			printLogger("6.----AddressData---- PostalCode------------ : "+addressDataInput.getPostalCode());
			printLogger("7.----AddressData---- State ------------ : "+addressDataInput.getState());
			printLogger("8.----AddressData---- Province------------ : "+addressDataInput.getProvince());
			
			DocumentMetaData11 documentMetaData = new DocumentMetaData11();
				documentMetaData.setSenderId(getSenderId());
				documentMetaData.setSenderName(getSenderName());
				documentMetaData.setReceiverId(getReceiverId());
			printLogger("1.----DocumentMetaData1---- SenderId ------------ : "+getSenderId());
			printLogger("2.----DocumentMetaData1---- SenderName------------ : "+getSenderName());
			printLogger("3.----DocumentMetaData1---- ReceiverId------------ : "+getReceiverId());
			printLogger("4.----URL---- ------------ : "+getAddress());
			
			AddressWSInput addressWSInput = new AddressWSInput();
				addressWSInput.setDocumentMetaData(documentMetaData);
				addressWSInput.setAddressData(addressData);
			
			AddressCleanseWSInput addressCleanseInput = new AddressCleanseWSInput();	
				addressCleanseInput.setAddressWSInput(addressWSInput);	

			
			AddressCleanseWSOutput addressCleanseOutput = new AddressCleanseWSOutput();
			printLogger("Cleansing Webservices Start : "+System.currentTimeMillis());
				addressCleanseOutput = port.addressCleanse(addressCleanseInput);	
			printLogger("Cleansing Webservices End : "+System.currentTimeMillis());	
				if(addressCleanseOutput!=null){
					printLogger("Response data is not Null");
					addressResponseData=addressCleanseOutput.getAddressWSOutput().getAddressData();
					
					if(addressResponseData.getAddressId()!= null){	
						printLogger("AddressId is not Null");
						addressDataInput.setAddressId(addressResponseData.getAddressId());
						addressDataInput.setAddressName(addressResponseData.getName());
						addressDataInput.setAddressLine1(addressResponseData.getAddressLine1());
						addressDataInput.setAddressLine2(addressResponseData.getAddressLine2());
						addressDataInput.setCity(addressResponseData.getCity());
						addressDataInput.setCountry(addressResponseData.getCountry());
						addressDataInput.setPostalCode(addressResponseData.getPostalCode());
						addressDataInput.setProvince(addressResponseData.getStateOrProvince());
						addressDataInput.setState(addressResponseData.getStateOrProvince());
					}
					else{
						printLogger("AddressId is Null");
						addressDataInput.setErrorMsgForCleansing(addressResponseData.getErrorMessage());
					}
				}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new LGSBusinessException("There has been an error while cleansing");
		}		
		return addressDataInput;	//This would return the cleansed data 		
	}
	
	private void printLogger(String info){
		//LOGGER.debug(info);
		LOGGER.info(info);
	}
}
