package com.lexmark.service.impl.real.requestService;

import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.AccountContact;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.service.impl.real.domain.MadcRequestDo;

public class AmindMadcRequestConversionUtil {
    
	public  static List<ServiceRequest> convertMadcRequestList(List<MadcRequestDo> requestDoList) {
		
		List<ServiceRequest> requestList = new ArrayList<com.lexmark.domain.ServiceRequest>();

		if (requestDoList == null) {
			return requestList;
		}

		for (MadcRequestDo madcRequestDo : requestDoList) {
			ServiceRequest request = new ServiceRequest();
			
			request.setServiceRequestNumber(madcRequestDo.getRequestNumber());
			request.setCostCenter(madcRequestDo.getCostCenter());
			request.setDateCreated(madcRequestDo.getDateCreated());
			request.setArea(createListOfValues(madcRequestDo.getArea()));
			request.setSubArea(createListOfValues(madcRequestDo.getSubarea()));
			request.setSerialNumber(madcRequestDo.getSerialNumber());
			request.setCustomerDeviceTag(madcRequestDo.getDeviceTag());
			request.setServiceRequestStatus(madcRequestDo.getStatus());
			request.setProductModel(madcRequestDo.getProductModel());
			request.setHelpdeskReferenceNumber(madcRequestDo.getReferenceNumber());
			request.setCostCenter(madcRequestDo.getCostCenter());
			request.setAccountName(madcRequestDo.getAccountName());
			request.setAddressName(madcRequestDo.getAddressName());
			request.setCoveredService(madcRequestDo.getCoveredServices());
			
			request.setServiceAddress(populateServiceAddress(madcRequestDo));
			request.setPrimaryContact(populatePrimaryContact(madcRequestDo));
			request.setRequestor(populateRequestor(madcRequestDo));
		
			requestList.add(request);
		}
		
		return requestList;
	}
	
	private static AccountContact populateRequestor(MadcRequestDo madcRequestDo) {
		AccountContact requestor = new AccountContact();
		requestor.setFirstName(madcRequestDo.getRequesterFirstName());
		requestor.setLastName(madcRequestDo.getRequesterLastName());
		requestor.setEmailAddress(madcRequestDo.getRequesterContactEmail());
		requestor.setHomePhone(madcRequestDo.getRequesterContactPhone());
		return requestor;
	}

	private static AccountContact populatePrimaryContact(MadcRequestDo madcRequestDo) {
		AccountContact primaryContact = new AccountContact();
		primaryContact.setFirstName(madcRequestDo.getPrimaryContactFirstName());
		primaryContact.setLastName(madcRequestDo.getPrimaryContactLastName());
		primaryContact.setEmailAddress(madcRequestDo.getPrimaryContactEmail());
		primaryContact.setHomePhone(madcRequestDo.getPrimaryContactPhone());
		return primaryContact;
	}

	private static GenericAddress populateServiceAddress(MadcRequestDo madcRequestDo) {
		GenericAddress serviceAddress = new GenericAddress();
		serviceAddress.setAddressLine1(madcRequestDo.getAddressLine1());
		serviceAddress.setOfficeNumber(madcRequestDo.getHouseNumber());
		serviceAddress.setCity(madcRequestDo.getCity());
		serviceAddress.setState(madcRequestDo.getState());
		serviceAddress.setProvince(madcRequestDo.getProvince());
		serviceAddress.setCounty(madcRequestDo.getCounty());
		serviceAddress.setDistrict(madcRequestDo.getDistrict());
		serviceAddress.setCountry(madcRequestDo.getCountry());
		serviceAddress.setPostalCode(madcRequestDo.getZipCode());
		return serviceAddress;
	}

	private static ListOfValues createListOfValues(String value) {
		ListOfValues lov = new ListOfValues();
		lov.setValue(value);
		return lov;
	}
	
}
