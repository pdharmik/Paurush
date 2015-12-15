package com.lexmark.service.impl.real.requestService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Comparator;
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
import com.lexmark.service.impl.real.ListComparator;

@RunWith(Parameterized.class)
public class RequestTypeListSortTest extends RequestTypeServiceBase {

	private final Map<String, Object> sortCriteria;

	@Parameters
	public static List<Object[]> sortParameters() {
		List<Object[]> list = new ArrayList<Object[]>();

		/*list.add(new Object[] { "requestNumber", "ASCENDING" });
		list.add(new Object[] { "requestNumber", "DESCENDING" });
		list.add(new Object[] { "area", "ASCENDING" });*/
		list.add(new Object[] { "area", "DESCENDING" });
		//list.add(new Object[] { "subArea", "ASCENDING" });
		list.add(new Object[] { "subArea", "DESCENDING" });
		/*list.add(new Object[] { "status", "ASCENDING" });
		list.add(new Object[] { "status", "DESCENDING" });*/
		list.add(new Object[] { "serialNumber", "ASCENDING" });
		list.add(new Object[] { "serialNumber", "DESCENDING" });
	/*	list.add(new Object[] { "problemDescription", "ASCENDING" });
		list.add(new Object[] { "problemDescription", "DESCENDING" });
		list.add(new Object[] { "serviceAddress.addressLine1", "ASCENDING" });
		list.add(new Object[] { "serviceAddress.addressLine1", "DESCENDING" });
		list.add(new Object[] { "serviceAddress.city", "ASCENDING" });
		list.add(new Object[] { "serviceAddress.city", "DESCENDING" });*/
		list.add(new Object[] { "serviceAddress.province", "ASCENDING" });
	/*	list.add(new Object[] { "serviceAddress.province", "DESCENDING" });
		list.add(new Object[] { "serviceAddress.postalCode", "ASCENDING" });
		list.add(new Object[] { "serviceAddress.postalCode", "DESCENDING" });
		list.add(new Object[] { "serviceAddress.country", "ASCENDING" });
		list.add(new Object[] { "serviceAddress.country", "DESCENDING" });
		list.add(new Object[] { "primaryContact.firstName", "ASCENDING" });*/
		list.add(new Object[] { "primaryContact.firstName", "DESCENDING" });
		//list.add(new Object[] { "primaryContact.lastName", "ASCENDING" });
		list.add(new Object[] { "primaryContact.lastName", "DESCENDING" });
	/*	list.add(new Object[] { "primaryContact.emailAddress", "ASCENDING" });
		list.add(new Object[] { "primaryContact.emailAddress", "DESCENDING" });
		list.add(new Object[] { "primaryContact.workPhone", "ASCENDING" });*/
		list.add(new Object[] { "primaryContact.workPhone", "DESCENDING" });
		list.add(new Object[] { "requestor.firstName", "ASCENDING" });
		list.add(new Object[] { "requestor.firstName", "DESCENDING" });
		list.add(new Object[] { "requestor.lastName", "ASCENDING" });
		list.add(new Object[] { "requestor.lastName", "DESCENDING" });
		list.add(new Object[] { "requestor.emailAddress", "ASCENDING" });
		list.add(new Object[] { "requestor.emailAddress", "DESCENDING" });
		list.add(new Object[] { "requestor.workPhone", "ASCENDING" });
		list.add(new Object[] { "requestor.workPhone", "DESCENDING" });
		/*list.add(new Object[] { "poNumber", "ASCENDING" });
		list.add(new Object[] { "poNumber", "DESCENDING" });
		list.add(new Object[] { "requestType", "ASCENDING" });
		list.add(new Object[] { "requestType", "DESCENDING" });*/

		return list;
	}

	public RequestTypeListSortTest(String key, Object value) {
		sortCriteria = new HashMap<String, Object>(1);
		sortCriteria.put(key, value);
	}

	@Test
	public void testRequestTypeListSort() throws Exception {
		RequestListContract contract = new RequestListContract();
		contract.setMdmId("236295");
		contract.setMdmLevel("Legal");
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(handle);
		contract.setSortCriteria(sortCriteria);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		Map<String,Object> filter = new HashMap<String, Object>();
		filter.put("serviceRequest.startDate","06/25/2012 00:00:00");
		filter.put("serviceRequest.endDate","07/25/2012 21:23:43");
		contract.setFilterCriteria(filter);
		RequestListResult result = service.retrieveRequestList(contract);
		List<ServiceRequest> list = result.getRequestList();
		int totalCount = result.getTotalCount();

		assertNotNull("List is null!", list);
		assertNotNull("totalCount is null!", totalCount);
		assertFalse("list is empty!", list.isEmpty());

		logger.debug("total count is " + totalCount);
		logger.debug("list size is " + list.size());

		Iterator<Entry<String, Object>> iterator = sortCriteria.entrySet().iterator();
		Entry<String, Object> sortEntryMap = iterator.next();
		String criteriaKey = sortEntryMap.getKey();
		String criteriaValue = (String) sortEntryMap.getValue();
		Comparator<Object> comparator = new ListComparator(criteriaValue);

		for (int i = 0; i < list.size() - 1; i++) {
			ServiceRequest firstRequest = list.get(i);
			ServiceRequest secondRequest = list.get(i + 1);
			String first;
			String second;
			if (criteriaKey.equals("requestNumber")) {
				first = firstRequest.getServiceRequestNumber();
				second = secondRequest.getServiceRequestNumber();
			} else if (criteriaKey.equals("area")) {
				first = firstRequest.getArea().getValue();
				second = secondRequest.getArea().getValue();
			} else if (criteriaKey.equals("subArea")) {
				first = firstRequest.getSubArea().getValue();
				second = secondRequest.getSubArea().getValue();
			} else if (criteriaKey.equals("status")) {
				first = firstRequest.getServiceRequestStatus();
				second = secondRequest.getServiceRequestStatus();
			} else if (criteriaKey.equals("serialNumber")) {
				first = firstRequest.getAsset().getSerialNumber();
				second = secondRequest.getAsset().getSerialNumber();
			} else if (criteriaKey.equals("problemDescription")) {
				first = firstRequest.getProblemDescription();
				second = secondRequest.getProblemDescription();
			} else if (criteriaKey.equals("serviceAddress.addressLine1")) {
				first = firstRequest.getServiceAddress().getAddressLine1();
				second = secondRequest.getServiceAddress().getAddressLine1();
			} else if (criteriaKey.equals("serviceAddress.city")) {
				first = firstRequest.getServiceAddress().getCity();
				second = secondRequest.getServiceAddress().getCity();
			} else if (criteriaKey.equals("serviceAddress.province")) {
				first = firstRequest.getServiceAddress().getProvince();
				second = secondRequest.getServiceAddress().getProvince();
			} else if (criteriaKey.equals("serviceAddress.postalCode")) {
				first = firstRequest.getServiceAddress().getPostalCode();
				second = secondRequest.getServiceAddress().getPostalCode();
			} else if (criteriaKey.equals("serviceAddress.country")) {
				first = firstRequest.getServiceAddress().getCountry();
				second = secondRequest.getServiceAddress().getCountry();
			} else if (criteriaKey.equals("primaryContact.firstName")) {
				first = firstRequest.getPrimaryContact().getFirstName();
				second = secondRequest.getPrimaryContact().getFirstName();
			} else if (criteriaKey.equals("primaryContact.lastName")) {
				first = firstRequest.getPrimaryContact().getLastName();
				second = secondRequest.getPrimaryContact().getLastName();
			} else if (criteriaKey.equals("primaryContact.emailAddress")) {
				first = firstRequest.getPrimaryContact().getEmailAddress();
				second = secondRequest.getPrimaryContact().getEmailAddress();
			} else if (criteriaKey.equals("primaryContact.workPhone")) {
				first = firstRequest.getPrimaryContact().getWorkPhone();
				second = secondRequest.getPrimaryContact().getWorkPhone();
			} else if (criteriaKey.equals("requestor.firstName")) {
				first = firstRequest.getRequestor().getFirstName();
				second = secondRequest.getRequestor().getFirstName();
			} else if (criteriaKey.equals("requestor.lastName")) {
				first = firstRequest.getRequestor().getLastName();
				second = secondRequest.getRequestor().getLastName();
			} else if (criteriaKey.equals("requestor.emailAddress")) {
				first = firstRequest.getRequestor().getEmailAddress();
				second = secondRequest.getRequestor().getEmailAddress();
			} else if (criteriaKey.equals("requestor.workPhone")) {
				first = firstRequest.getRequestor().getWorkPhone();
				second = secondRequest.getRequestor().getWorkPhone();
			} else if (criteriaKey.equals("poNumber")) {
				first = firstRequest.getPoNumber();
				second = secondRequest.getPoNumber();
			} else if (criteriaKey.equals("requestType")) {
				first = firstRequest.getServiceRequestType().getValue();
				second = secondRequest.getServiceRequestType().getValue();
			} else {
				throw new IllegalArgumentException("wrong filter parameter!");
			}

			comparator.compare(first, second);
		}

	}
}
