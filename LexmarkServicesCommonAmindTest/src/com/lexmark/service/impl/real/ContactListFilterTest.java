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

import com.lexmark.contract.ContactListContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.result.ContactListResult;

/**
 * @author pkozlov
 */
@RunWith(Parameterized.class)
public class ContactListFilterTest extends ContactServiceStatefulBase {

	private final Map<String, Object> filterCriteria;

	@Parameters
	public static Collection<Object[]> filterParameters() {
		Collection<Object[]> result = new ArrayList<Object[]>();
		result.add(new Object[] { "firstName", "a" });
		result.add(new Object[] { "lastName", "a" });
		result.add(new Object[] { "emailAddress", "a" });
		result.add(new Object[] { "workPhone", "1" });
		result.add(new Object[] { "alternatePhone", "1" });
		result.add(new Object[] { "addressLine1", "" });
		result.add(new Object[] { "addressLine2", "" });
		result.add(new Object[] { "addressLine3", "" });
		result.add(new Object[] { "city", "" });
		result.add(new Object[] { "state", "" });
		result.add(new Object[] { "province", "a" });
		result.add(new Object[] { "country", "" });
		result.add(new Object[] { "postalCode", "" });
		return result;
	}

	public ContactListFilterTest(String key, String value) {
		filterCriteria = new HashMap<String, Object>();
		filterCriteria.put(key, value);
	}

	@Test
	public void testContactListFilter() throws Exception {
		Iterator<Entry<String, Object>> sortIterator = filterCriteria.entrySet().iterator();
		Entry<String, Object> sortEntryMap = sortIterator.next();
		String criteriaKey = sortEntryMap.getKey();
		String criteriaValue = (String) sortEntryMap.getValue();

		List<AccountContact> contactList = getFilteredContactList(filterCriteria);
		for (AccountContact contact : contactList) {
			StringBuilder debugString = new StringBuilder();
			String value;
			if (criteriaKey.equalsIgnoreCase("firstName")) {
				debugString.append("First Name: ");
				value = contact.getFirstName();
			} else if (criteriaKey.equalsIgnoreCase("lastName")) {
				debugString.append("Last Name: ");
				value = contact.getLastName();
			} else if (criteriaKey.equalsIgnoreCase("emailAddress")) {
				debugString.append("Email Address: ");
				value = contact.getEmailAddress();
			} else if (criteriaKey.equalsIgnoreCase("workPhone")) {
				debugString.append("Work Phone: ");
				value = contact.getWorkPhone();
			} else if (criteriaKey.equalsIgnoreCase("alternatePhone")) {
				debugString.append("Alternate Phone: ");
				value = contact.getAlternatePhone();
			} else if (criteriaKey.equalsIgnoreCase("addressLine1")) {
				debugString.append("Address Line 1: ");
				value = contact.getAddress().getAddressLine1();
			} else if (criteriaKey.equalsIgnoreCase("city")) {
				debugString.append("City: ");
				value = contact.getAddress().getCity();
			} else if (criteriaKey.equalsIgnoreCase("state")) {
				debugString.append("State: ");
				value = contact.getAddress().getState();
			} else if (criteriaKey.equalsIgnoreCase("province")) {
				debugString.append("Province: ");
				value = contact.getAddress().getProvince();
			} else if (criteriaKey.equalsIgnoreCase("country")) {
				debugString.append("Country: ");
				value = contact.getAddress().getCountry();
			} else if (criteriaKey.equalsIgnoreCase("postalCode")) {
				debugString.append("Postal Code: ");
				value = contact.getAddress().getPostalCode();
			} else {
				throw new IllegalArgumentException("Wrong filter parameter:" + criteriaKey);
			}

			debugString.append(value);
			logger.debug(debugString);
			boolean assertCondition = value.toLowerCase().contains(criteriaValue.toLowerCase());

			StringBuilder assertString = new StringBuilder();
			assertString.append("Filter are not working! Parameters: key = \"");
			assertString.append(criteriaKey);
			assertString.append("\"; value = \"");
			assertString.append(criteriaValue);
			assertString.append("\"");
			assertTrue(assertString.toString(), assertCondition);
		}

	}

	private List<AccountContact> getFilteredContactList(Map<String, Object> filterCriteria) throws Exception {
		ContactListContract contract = new ContactListContract();
		contract.setNewQueryIndicator(true);
		contract.setContactId("1-1DTJMA");
		contract.setFavoriteFlag(false);
		contract.setSessionHandle(handle);
		contract.setMdmId("DUN1-L4BTZC");
		contract.setMdmLevel("Global");
		contract.setFilterCriteria(filterCriteria);

		ContactListResult result = service.retrieveContactList(contract);
		List<AccountContact> contactList = result.getContacts();
		assertNotNull("Service return null pointer!", contactList);
		assertTrue("Service return empty list!", contactList.size() > 0);
		return contactList;
	}

}
