package com.lexmark.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lexmark.contract.ServiceRequestListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.ServiceRequestListResult;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.service.impl.mock.ServiceRequestServiceImpl;



public class ServiceRequestTestCase {

	protected static ServiceRequestService serviceRequestService;
	protected static ServiceRequestListContract serviceRequestListContract;
	protected static ServiceRequestListResult serviceRequestListResult;

	@BeforeClass
	public static void setUp() throws Exception{
		serviceRequestService = new ServiceRequestServiceImpl();
		serviceRequestListContract = new ServiceRequestListContract();
		serviceRequestListContract.setContactID("600171");
		serviceRequestListContract.getFilterCriteria().put("getCertainServiceRequest", "select criteria");
		serviceRequestListContract.setLocale(Locale.US);		
		serviceRequestListResult = serviceRequestService.retrieveServiceRequestList(serviceRequestListContract);
	}
	
	@Test
	public void checkServiceRequestsValues(){
    	for (ServiceRequest sr: serviceRequestListResult.getServiceRequests())
    	{
		
//		assertNotNull("Carrier is null", sr.getCarrier());
//		assertTrue("Carrier is blank", sr.getCarrier() != "");

//		assertNotNull("optionExchangeList is null", sr.getOptionExchangeList());
//		assertNotNull("optionExchangeList is blank", sr.getOptionExchangeList());

		assertNotNull("OptionExchangeOtherDescription is null", sr.getOptionExchangeOtherDescription());
		assertTrue("OptionExchangeOtherDescription is blank", sr.getOptionExchangeOtherDescription() != "");

		assertNotNull("ProblemDescription is null", sr.getProblemDescription());
		assertTrue("ProblemDescription is blank", sr.getProblemDescription() != "");

		assertNotNull("ReferenceNumber is null", sr.getReferenceNumber());		
    	}
//    	JsonGenerator jg = new JsonGenerator();

	}
	
	@Test
	public void checkTotalCount() {
		assertNotNull("serviceRequest List is null", serviceRequestListResult.getTotalCount());
		
	}

}
