package com.lexmark.service.impl.real.requestService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.RequestListResult;

@RunWith(Parameterized.class)
public class RequestTypeListFilterTest extends RequestTypeServiceBase {

	private final Map<String, Object> filterCriteria;

	@Parameters
	public static List<Object[]> filterParameters() {
		List<Object[]> list = new ArrayList<Object[]>();

		list.add(new Object[] {"requestNumber", "1-27013747"});
		list.add(new Object[] {"area", "Hardware"});
		list.add(new Object[] {"subArea", "Mono Laser"});
//		list.add(new Object[] {"status" , "Archived" });
		list.add(new Object[] {"serialNumber" , "9401FC4" });
//		list.add(new Object[] {"problemDescription", ""});
		list.add(new Object[] {"serviceAddress.addressLine1", "3033 Shane Bridge Rd."});
		list.add(new Object[] {"serviceAddress.city", "Vienna"});
//		list.add(new Object[] {"serviceAddress.province", ""});
		list.add(new Object[] {"serviceAddress.postalCode", "22185-0001"});
		list.add(new Object[] {"serviceAddress.country", "USA"});
		list.add(new Object[] {"primaryContact.firstName", "Sam"});
		list.add(new Object[] {"primaryContact.lastName", "Hernendez"});
		list.add(new Object[] {"primaryContact.emailAddress", "mp3761@att.com2"});
		list.add(new Object[] {"primaryContact.workPhone", "9544937560"});
		list.add(new Object[] {"requestorContact.firstName", "Sam"});
		list.add(new Object[] {"requestorContact.lastName", "Hernendez"});
		list.add(new Object[] {"requestorContact.emailAddress", "mp3761@att.com2"});
		list.add(new Object[] {"requestorContact.workPhone", "9544937560"});
//		list.add(new Object[] {"poNumber" , "" }); 
		list.add(new Object[] {"requestType" , "Claims" });
		
		list.add(new Object[] {"requestType" , "BreakFix" });
		
		return list;
	}

	public RequestTypeListFilterTest(String key, Object value) {
		filterCriteria = new HashMap<String, Object>(1);
		filterCriteria.put(key, value);
	}
	
	@Test
	public void testRequestTypeListFilter() throws Exception {
		RequestListContract contract = new RequestListContract();
		contract.setMdmId("1-3S-445");
		contract.setMdmLevel("Siebel");
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(handle);
		contract.setFilterCriteria(filterCriteria);

		RequestListResult result = service.retrieveRequestList(contract);
		List<ServiceRequest> list = result.getRequestList();
		int totalCount = result.getTotalCount();

		assertNotNull("List is null!", list);
		assertNotNull("totalCount is null!", totalCount);
		assertFalse("list is empty!", list.isEmpty());

		logger.debug("total count is " + totalCount);
		logger.debug("list size is " + list.size());
		
		if (filterCriteria.isEmpty()) {
			return;
		}

		Iterator<Entry<String, Object>> iterator = filterCriteria.entrySet().iterator();
		Entry<String, Object> sortEntryMap = iterator.next();
		String criteriaKey = sortEntryMap.getKey();
		String criteriaValue = (String) sortEntryMap.getValue();

		for (ServiceRequest request : list) {
			String value;
			if (criteriaKey.equals("requestNumber")) {
				value = request.getServiceRequestNumber();
			} else if (criteriaKey.equals("area")) {
				value = request.getArea().getValue();
			} else if (criteriaKey.equals("subArea")) {
				value = request.getSubArea().getValue();
			} else if (criteriaKey.equals("status")) {
				value = request.getServiceRequestStatus();
			} else if (criteriaKey.equals("serialNumber")) {
				value = request.getAsset().getSerialNumber();
			} else if (criteriaKey.equals("problemDescription")) {
				value = request.getProblemDescription();
			} else if (criteriaKey.equals("serviceAddress.addressLine1")) {
				value = request.getServiceAddress().getAddressLine1();
			} else if (criteriaKey.equals("serviceAddress.city")) {
				value = request.getServiceAddress().getCity();
			} else if (criteriaKey.equals("serviceAddress.province")) {
				value = request.getServiceAddress().getProvince();
			} else if (criteriaKey.equals("serviceAddress.postalCode")) {
				value = request.getServiceAddress().getPostalCode();
			} else if (criteriaKey.equals("serviceAddress.country")) {
				value = request.getServiceAddress().getCountry();
			} else if (criteriaKey.equals("primaryContact.firstName")) {
				value = request.getPrimaryContact().getFirstName();
			} else if (criteriaKey.equals("primaryContact.lastName")) {
				value = request.getPrimaryContact().getLastName();
			} else if (criteriaKey.equals("primaryContact.emailAddress")) {
				value = request.getPrimaryContact().getEmailAddress();
			} else if (criteriaKey.equals("primaryContact.workPhone")) {
				value = request.getPrimaryContact().getWorkPhone();
			} else if (criteriaKey.equals("requestorContact.firstName")) {
				value = request.getRequestor().getFirstName();
			} else if (criteriaKey.equals("requestorContact.lastName")) {
				value = request.getRequestor().getLastName();
			} else if (criteriaKey.equals("requestorContact.emailAddress")) {
				value = request.getRequestor().getEmailAddress();
			} else if (criteriaKey.equals("requestorContact.workPhone")) {
				value = request.getRequestor().getWorkPhone();
			} else if (criteriaKey.equals("poNumber")) {
				value = request.getPoNumber();
			} else if (criteriaKey.equals("requestType")) {
				value = request.getServiceRequestType().getValue();
			} else {
				throw new IllegalArgumentException("wrong filter parameter!");
			}

			assertNotNull(value);
			assertTrue("filtering fails", value.toLowerCase().contains(criteriaValue.toLowerCase()));
		}
		
		debugRequests(list);

	}
}
