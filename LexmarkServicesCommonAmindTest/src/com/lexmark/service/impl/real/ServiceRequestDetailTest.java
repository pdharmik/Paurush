package com.lexmark.service.impl.real;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.ServiceRequestContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.ServiceRequestResult;

@RunWith(Parameterized.class)
public class ServiceRequestDetailTest extends RequestServiceStatelessBase {

	private final ServiceRequestContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();

//		list.add(new Object[] { "1-8672990681" });
//		list.add(new Object[] { "1-8794235811" });
		list.add(new Object[] { "1-10149255673" });

		return list;
	}

	public ServiceRequestDetailTest(String requestNumber) {
		contract = new ServiceRequestContract();
		contract.setRequestNumber(requestNumber);
		contract.setSessionHandle(null);
	}

	@Test
	public void testRetrieveServiceRequest() throws Exception {
		ServiceRequestResult result = service.retrieveServiceRequest(contract);
		assertNotNull("result is null!", result);
		ServiceRequest request = result.getServiceRequest();
		assertNotNull("request is null!", request);
		debugServiceRequest(request);
	}
	
	private void debugServiceRequest(ServiceRequest request) {
		// primary contact data
		AccountContact primary = request.getPrimaryContact();
		assertNotNull("primary contact is null!",primary);
		logger.debug("first name: " + primary.getFirstName());
		logger.debug("last name: " + primary.getLastName());
		logger.debug("work phone: " + primary.getWorkPhone());
		logger.debug("email address: " + primary.getEmailAddress());
		logger.debug("Date" +  request.getServiceRequestDate());
		// customer reference id
		logger.debug("customer reference id: " + request.getCustomerReferenceId());
		// cost center
		logger.debug("cost center: " + request.getCostCenter());
		// description
		logger.debug("problemDescription: " + request.getProblemDescription());
		logger.debug("additionalDetails: " + request.getAddtnlDescription());
		// effective date
		logger.debug("requested effective date:" + request.getRequestedEffectiveDate());
		// status
		logger.debug("status:" + request.getServiceRequestStatus());
		
//		logger.debug("serviceRequestNumber:" + request.getServiceRequestNumber());
//		logger.debug("serviceRequestDate:" + request.getServiceRequestDate());
//		logger.debug("serialNumber:" + request.getAsset().getSerialNumber());
//		logger.debug("assetTag:" + request.getAsset().getAssetTag());
//		logger.debug("ipAddress:" + request.getAsset().getIpAddress());
//		
//		logger.debug("area:" + request.getArea());
//		logger.debug("subArea:" + request.getSubArea());
//
//		logger.debug("business phone: " + request.getRequestor().getWorkPhone());
//		logger.debug("first name: " + request.getRequestor().getFirstName());
//		logger.debug("last name: " + request.getRequestor().getLastName());
//		logger.debug("email: " + request.getRequestor().getEmailAddress());
//
//		List<Attachment> attachments = request.getAttachments();
//		logger.debug("attachments list: ");
//		for (Attachment attachment : attachments) {
//			logger.debug("attachmentName: " + attachment.getAttachmentName());
//			logger.debug("comment: " + attachment.getDescription());
//		}
	}
}
