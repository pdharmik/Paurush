package com.lexmark.service.impl.real;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.contract.RequestContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.RequestListResult;
import com.lexmark.result.RequestResult;

/**
 * @author Ivan Mdzeluri
 * @version 1.0, 2014-07-22
 */
public class AmindRequestTypeServiceB2BTest extends AmindServiceTest {

	AmindRequestTypeServiceB2B service;
	RequestContract reqContract;
	RequestListContract reqListContract;
	
	@Before
	public void setUp() {
		service = new AmindRequestTypeServiceB2B();
		reqContract = new RequestContract();
		reqListContract = new RequestListContract();
	}

	
	@Test
	public void testRetrieveRequestList_defect12464() throws Exception {
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("009420159");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/21/2014 19:53:44");
		filterMap.put("serviceRequest.startDate", "03/06/2014 09:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix", "Fleet Management"));
		filterMap.put("serviceRequestNumber", "1-19320208981");
		reqListContract.setFilterCriteria(filterMap);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "ASCENDING");
		reqListContract.setSortCriteria(sortCriteria );
		RequestListResult result = service.retrieveRequestListB2B(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		for (ServiceRequest serviceRequest : list) {
			System.out.println("Area: " + serviceRequest.getArea());
		}
		System.out.println("END");
	}
	
	
	@Test
	public void testRetrieveRequestList_B2BRequestHistory() throws Exception {
//		reqListContract.setMdmLevel("Global");
//		reqListContract.setMdmId("009420159");
		reqListContract.setAccountId("1-GW9WAR");
//		reqListContract.setShowAllFlag(true);
//		reqListContract.setHardwareRequestFlag(true);
//		reqListContract.setHardwareRequestsPermission(true);
//		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setIncrement(0);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		reqListContract.setSoldTo("0000169087");
		reqListContract.setVendorFlag(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
//		filterMap.put("serviceRequest.endDate", "04/24/2014 15:30:27");
//		filterMap.put("serviceRequest.startDate", "04/09/2014 04:00:00");
//		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestDate", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria );
		RequestListResult result = service.retrieveRequestListB2B(reqListContract);
		
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
	}
	
        
	@Test
	public void testRetrieveSupplyRequestDetail_defect13824() throws Exception {
		reqContract.setServiceRequestNumber("1-22326891611"); 
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setSessionHandle(crmSessionHandle);
		
		RequestResult result = service.retrieveSupplyRequestDetailB2B(reqContract);
		System.out.println(result.getServiceRequest().getHelpdeskReferenceNumber());
	}
	
	@Test
	public void testRetrieveSupplyRequestDetailB2B_requestDetails_15_7() throws Exception {
		reqContract.setServiceRequestNumber("123456789"); 
		reqContract.setVisibilityRole("visibilityRole");
		reqContract.setCreateChildSR(true);
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setSessionHandle(crmSessionHandle);
		
		RequestResult result = service.retrieveSupplyRequestDetailB2B(reqContract);
		System.out.println(result.getServiceRequest().getHelpdeskReferenceNumber());
	}
	
	@Test
	public void testRetrieveRequestList_B2BRequestHistory15_7() throws Exception {
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setContactId("Contact ID");
		reqListContract.setAccountId("Account ID");
		reqListContract.setSoldTo("Sold To");
		reqListContract.setIncrement(0);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(false);
		RequestListResult result = service.retrieveRequestListB2B(reqListContract);
		
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
	}
	
	@Test
	public void testRetrieveRequestList_B2BRequestHistory_error() throws Exception {
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setMdmId("483419636");
		reqListContract.setMdmLevel("Global");
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setIncrement(0);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(false);
		RequestListResult result = service.retrieveRequestListB2B(reqListContract);
		
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
	}
}
