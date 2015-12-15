/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: AddressCleansingServiceImpl
 * Package     		: com.lexmark.services.webService.cm
 * Creation Date 	: 30th July 2012
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Sourav		30th July 2012 		1.0             Initial Version
 *
 */
package com.lexmark.service.impl.real.AddressCleansing;

import java.net.URL;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.input.DataSet;
import com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.input.DataSetRecord;
import com.businessobjects.www.DataServices_Server;
import com.businessobjects.www.DataServices_ServerLocator;
import com.businessobjects.www.RealTime_Services;
import com.lexmark.domain.GenericAddress;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.service.api.AddressCleansingService;

/**
 * @author wipro  
 * @version 1.0
 */
public class AddressCleansingServiceImpl implements AddressCleansingService {

	private static Logger LOGGER = LogManager.getLogger(AddressCleansingServiceImpl.class);
	
	private String address;
	private String senderId;
	private String senderName;
	private String receiverId;
	private String userName;
	private String password;
	
	/**
	 * @return String 
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
	 * @return String 
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
	 * @return String 
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
	 * @return String 
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
	
	/**
	 * @return String 
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return String 
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
	 * This method is used for cleasing of address provided by the user 
	 * @param addressDataInput 
	 * @param accountDetails 
	 * @return GenericAddress 
	 * @throws LGSRuntimeException 
	 * @throws LGSBusinessException 
	 */
	public GenericAddress addressCleanse(GenericAddress addressDataInput,
			Map<String, String> accountDetails) throws LGSRuntimeException,
			LGSBusinessException {
		
		//AddressData2 addressResponseData=new AddressData2();
		
		try {
			LOGGER.debug("Address cleansing start ------- > " + System.currentTimeMillis());
			LOGGER.debug("Address cleansing start ------- > getAddress:" + getAddress());
			DataServices_Server locator = new DataServices_ServerLocator();
			//RealTime_Services services = locator.getRealTime_Services(new URL("http://157.184.94.18:8080/DataServices/servlet/webservices"));			
			RealTime_Services services = locator.getRealTime_Services(new URL(getAddress()));
			//RealTime_Services services = locator.getRealTime_Services(new URL("http://dlexwbods001:8080/DataServices/servlet/webservices?ver=2.1")); Does not work with hostname
			
			DataSet inputBody = new DataSet();
			DataSetRecord inputBodyRecord = new DataSetRecord();
			
			LOGGER.debug("Address cleansing ws Inputs ------- > Country:: " + addressDataInput.getCountry() + "State:: "
							+ addressDataInput.getState()+" Province:: "+ addressDataInput.getProvince() + " City:: " 
							+ addressDataInput.getCity()+"office no ::"+addressDataInput.getOfficeNumber());
			
			//Please refer the BODS-Web Portal Address Cleansing Input-Output Mappings.xsl sheet in Google drive while filling the input fields
			inputBodyRecord.setStreetAddress(addressDataInput.getAddressLine1());//Mandatory
			inputBodyRecord.setBusiness_spcAddressStreet_spcAddress_spc2(addressDataInput.getAddressLine2());
			inputBodyRecord.setCity(addressDataInput.getCity());//Mandatory
		
			if(addressDataInput.getState()==null)
			{
				inputBodyRecord.setBusiness_spcAddressState(addressDataInput.getProvince());//Mandatory
			}else{
				inputBodyRecord.setBusiness_spcAddressState(addressDataInput.getState());//Mandatory
				

			}
						
			
			inputBodyRecord.setBusiness_spcAddressCountry(addressDataInput.getCountry());//Mandatory & will come from pick list
			inputBodyRecord.setPostalCode(addressDataInput.getPostalCode());//Mandatory
			
			inputBodyRecord.setOfficeNo(addressDataInput.getOfficeNumber());//Unknown fields are blank
			inputBodyRecord.setBusiness_spcAddressProvince("");//Unknown fields are blank
			inputBodyRecord.setBusiness_spcAddressCounty("");//Unknown fields are blank
			inputBodyRecord.setISOCountryCode("");//Unknown fields are blank
			inputBodyRecord.setRegionCode("");//Unknown fields are blank
			inputBodyRecord.setSavedErrorMessage("");//Unknown fields are blank
			inputBodyRecord.setStateFullName("");//Unknown fields are blank
			inputBodyRecord.setBusiness_spcAddressDistrict("");//Unknown fields are blank
			inputBodyRecord.setLatitude("");//Unknown fields are blank
			inputBodyRecord.setLongitude("");//Unknown fields are blank
			
						
			inputBody.setRecord(inputBodyRecord);
			LOGGER.debug("Address cleansing ws call start ------- > " + System.currentTimeMillis());
			com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.output.DataSet outputBody = services.service_Realtime_DQ_Siebel_business_address_datacleanse(inputBody);
			LOGGER.debug("Address cleansing ws call end ------- > " + System.currentTimeMillis());
			com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.output.DataSetRecord outputBodyRecord = outputBody.getRecord();

			
			
			
			if(outputBodyRecord.getSavedErrorMessage().equalsIgnoreCase("Exact Match"))
				{	
					//addressDataInput.setAddressId(addressResponseData.getAddressId()); //Not received by BODS
					//addressDataInput.setAddressName(addressResponseData.getName()); //Not received by BODS
					addressDataInput.setAddressLine1(outputBodyRecord.getStreetAddress());
					addressDataInput.setAddressLine2(outputBodyRecord.getBusiness_spcAddressStreet_spcAddress_spc2());
					addressDataInput.setCity(outputBodyRecord.getCity());
					addressDataInput.setCountry(outputBodyRecord.getBusiness_spcAddressCountry());
					addressDataInput.setPostalCode(outputBodyRecord.getPostalCode());
					addressDataInput.setProvince(outputBodyRecord.getBusiness_spcAddressProvince());
					addressDataInput.setState(outputBodyRecord.getBusiness_spcAddressState());
					addressDataInput.setLatitude(outputBodyRecord.getLatitude());
					addressDataInput.setLongitude(outputBodyRecord.getLongitude());
					addressDataInput.setCounty(outputBodyRecord.getBusiness_spcAddressCounty());
					addressDataInput.setDistrict(outputBodyRecord.getBusiness_spcAddressDistrict());
					addressDataInput.setCountryISOCode(outputBodyRecord.getISOCountryCode());
					addressDataInput.setRegion(outputBodyRecord.getRegionCode());
					addressDataInput.setStateCode(outputBodyRecord.getBusiness_spcAddressState());
					addressDataInput.setStateFullName(outputBodyRecord.getStateFullName());
					addressDataInput.setOfficeNumber(outputBodyRecord.getOfficeNo());
					addressDataInput.setSavedErrorMessage(outputBodyRecord.getSavedErrorMessage());//Message also needs to be passed to Siebel
				}
				else{
					//If any error with the wrong data, show the error to the user
					addressDataInput.setErrorMsgForCleansing(outputBodyRecord.getSavedErrorMessage());
				}
			
			
			
		
			LOGGER.debug("Address cleansing end ------- > " + System.currentTimeMillis());
			
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			throw new LGSBusinessException("There has been an error while cleansing");
			
		}		
		return addressDataInput;	//This would return the cleansed data 		
	}
}