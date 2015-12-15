package com.lexmark.service.impl.real;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.AddressListContract;
import com.lexmark.domain.GenericAddress;
import com.lexmark.result.AddressListResult;

/**
 * 
 * @author pkozlov
 *
 */
@RunWith(Parameterized.class)
public class AddressListSortTest extends RequestServiceStatefulBase {

	private final Map<String, Object> sortCriteria;

	@Parameters
	public static Collection<Object[]> sortParameters() {
		Collection<Object[]> result = new ArrayList<Object[]>();
		result.add(new Object[] { "addressName", "ASCENDING" });
		result.add(new Object[] { "addressName", "DESCENDING" });
		result.add(new Object[] { "addressLine1", "ASCENDING" });
		result.add(new Object[] { "addressLine1", "DESCENDING" });
		result.add(new Object[] { "city", "ASCENDING" });
		result.add(new Object[] { "city", "DESCENDING" });
		result.add(new Object[] { "state", "ASCENDING" });
		result.add(new Object[] { "state", "DESCENDING" });
		result.add(new Object[] { "postalCode", "ASCENDING" });
		result.add(new Object[] { "postalCode", "DESCENDING" });
		result.add(new Object[] { "country", "ASCENDING" });
		result.add(new Object[] { "country", "DESCENDING" });
		
//		result.add(new Object[] { "addressLine2", "ASCENDING" });
//		result.add(new Object[] { "addressLine2", "DESCENDING" });
//		result.add(new Object[] { "addressLine3", "ASCENDING" });
//		result.add(new Object[] { "addressLine3", "DESCENDING" });
//		result.add(new Object[] { "storeFrontName", "ASCENDING" });
//		result.add(new Object[] { "storeFrontName", "DESCENDING" });
		return result;
	}

	public AddressListSortTest(String key, String value) {
		sortCriteria = new HashMap<String, Object>();
		sortCriteria.put(key, value);
	}

	@Test
	public void testAddressListSort() throws Exception {
		Iterator<Entry<String, Object>> sortIterator = sortCriteria.entrySet().iterator();
		Entry<String, Object> sortEntryMap = sortIterator.next();
		String criteriaKey = sortEntryMap.getKey();
		String criteriaValue = (String) sortEntryMap.getValue();
		Comparator<Object> comparator = new ListComparator(criteriaValue);
		
		List<GenericAddress> addressList = getSortedAddressList();
		
		int debugSize = addressList.size() >= 5 ? 5 : addressList.size();
		logger.debug("First "+debugSize+" addresses of " + addressList.size());
		
		for (int i = 0; i < addressList.size() - 1; i++) {
			if (debugSize > 0) {
				debugAddress(addressList.get(i));
				debugSize--;
			}
			
			GenericAddress address1 = addressList.get(i);
			GenericAddress address2 = addressList.get(i + 1);
			String first;
			String second;
			if (criteriaKey.equalsIgnoreCase("addressName")) {
				first = address1.getAddressName();
				second = address2.getAddressName();
			} else if (criteriaKey.equalsIgnoreCase("storeFrontName")) {
				first = address1.getStoreFrontName();
				second = address2.getStoreFrontName();
			} else if (criteriaKey.equalsIgnoreCase("addressLine1")) {
				first = address1.getAddressLine1();
				second = address2.getAddressLine1();
			} else if (criteriaKey.equalsIgnoreCase("addressLine2")) {
				first = address1.getAddressLine2();
				second = address2.getAddressLine2();
			} else if (criteriaKey.equalsIgnoreCase("city")) {
				first = address1.getCity();
				second = address2.getCity();
			} else if (criteriaKey.equalsIgnoreCase("state")) {
				first = address1.getState();
				second = address2.getState();
			} else if (criteriaKey.equalsIgnoreCase("postalCode")) {
				first = address1.getPostalCode();
				second = address2.getPostalCode();
			} else if (criteriaKey.equalsIgnoreCase("country")) {
				first = address1.getCountry();
				second = address2.getCountry();
			} else if (criteriaKey.equalsIgnoreCase("addressLine3")) {
				first = address1.getAddressLine3();
				second = address2.getAddressLine3();
			} else {
				throw new IllegalArgumentException("Wrong sort criteria: " + criteriaKey);
			}

			comparator.compare(first, second);
		}
	}

	private List<GenericAddress> getSortedAddressList() throws Exception {
		AddressListContract contract = new AddressListContract();
		// old values
//		contract.setContactId("1-1DTJMA");
//		contract.setMdmId("DUN1-L4BTZC");
//		contract.setMdmLevel("Global");
		
		// new values
		contract.setContactId("1-FJ7YXN");
		contract.setMdmId("275254944");
		contract.setMdmLevel("Global");
		
		contract.setSessionHandle(handle);
		contract.setSortCriteria(sortCriteria);
		contract.setFavoriteFlag(false);
		contract.setNewQueryIndicator(true);

		AddressListResult result = service.retrieveAddressList(contract);
		List<GenericAddress> addressList = result.getAddressList();
		assertNotNull("Service return null pointer", addressList);
		return addressList;
	}
	
}
