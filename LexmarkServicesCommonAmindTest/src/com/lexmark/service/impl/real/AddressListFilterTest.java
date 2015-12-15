package com.lexmark.service.impl.real;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.AddressListContract;
import com.lexmark.domain.GenericAddress;
import com.lexmark.result.AddressListResult;

/**
 * @author pkozlov
 */
@RunWith(Parameterized.class)
public class AddressListFilterTest extends RequestServiceStatefulBase {

	private final Map<String, Object> filterCriteria;

	@Parameters
	public static Collection<Object[]> inputData() {
		Collection<Object[]> result = new ArrayList<Object[]>();
		result.add(new Object[] { "addressName", "A" });
		result.add(new Object[] { "addressLine1", "A" });
		result.add(new Object[] { "city", "ou" });
		result.add(new Object[] { "state", "N" });
		result.add(new Object[] { "postalCode", "91" });
		result.add(new Object[] { "country", "ran" });
//		result.add(new Object[] { "addressLine2", "" });
//		result.add(new Object[] { "addressLine3", "" });
//		result.add(new Object[] { "storeFrontName", "" });
		return result;
	}

	public AddressListFilterTest(String key, String value) {
		filterCriteria = new HashMap<String, Object>();
		filterCriteria.put(key, value);
	}

	@Test
	public void testAddressListFilter() throws Exception {
		Iterator<Entry<String, Object>> sortIterator = filterCriteria.entrySet().iterator();
		Entry<String, Object> sortEntryMap = sortIterator.next();
		String criteriaKey = sortEntryMap.getKey();
		String criteriaValue = (String) sortEntryMap.getValue();

		List<GenericAddress> addressList = getFilteredAddressList();
		assertTrue("Service return empty list!", addressList.size() > 0);
		logger.debug("Address List size: " + addressList.size());

		int debugSize = addressList.size() >= 5 ? 5 : addressList.size();
		logger.debug("First " + debugSize + " addresses of " + addressList.size());

		for (GenericAddress address : addressList) {
			if (debugSize > 0) {
				debugAddress(address);
				debugSize--;
			}

			String value;
			if (criteriaKey.equalsIgnoreCase("addressName")) {
				value = address.getAddressName();
			} else if (criteriaKey.equalsIgnoreCase("storeFrontName")) {
				value = address.getStoreFrontName();
			} else if (criteriaKey.equalsIgnoreCase("addressLine1")) {
				value = address.getAddressLine1();
			} else if (criteriaKey.equalsIgnoreCase("addressLine2")) {
				value = address.getAddressLine2();
			} else if (criteriaKey.equalsIgnoreCase("city")) {
				value = address.getCity();
			} else if (criteriaKey.equalsIgnoreCase("state")) {
				value = address.getState();
			} else if (criteriaKey.equalsIgnoreCase("postalCode")) {
				value = address.getPostalCode();
			} else if (criteriaKey.equalsIgnoreCase("country")) {
				value = address.getCountry();
			} else if (criteriaKey.equalsIgnoreCase("addressLine3")) {
				value = address.getAddressLine3();
			} else {
				throw new IllegalArgumentException("Wrong filter criteria: " + criteriaKey);
			}

			boolean assertCondition = value.toLowerCase().contains(criteriaValue.toLowerCase());

			StringBuilder assertMessage = new StringBuilder();
			assertMessage.append("Filtering failed! Parameters: (\"");
			assertMessage.append(criteriaKey);
			assertMessage.append("\",\"");
			assertMessage.append(criteriaValue);
			assertMessage.append("\"), value: \"");
			assertMessage.append(value);
			assertMessage.append("\"");

			assertTrue(assertMessage.toString(), assertCondition);
		}

	}

	private List<GenericAddress> getFilteredAddressList() throws Exception {
		AddressListContract contract = new AddressListContract();
		
		// old values
//		contract.setContactId("1-1DTJMA");
//		contract.setMdmId("DUN1-L4BTZC");
//		contract.setMdmLevel("Global");
		
		// new values
		contract.setContactId("1-FJ7YXN");
		contract.setMdmId("275254944");
		contract.setMdmLevel("Global");
		
		contract.setFavoriteFlag(false);
		contract.setSessionHandle(handle);
		contract.setFilterCriteria(filterCriteria);
		contract.setNewQueryIndicator(true);

		AddressListResult result = service.retrieveAddressList(contract);
		List<GenericAddress> addressList = result.getAddressList();

		assertNotNull("Service return null pointer", addressList);
		return addressList;
	}

}
