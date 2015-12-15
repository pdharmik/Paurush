package com.lexmark.service.impl.real;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
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

import com.lexmark.contract.ContactListContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.result.ContactListResult;

/**
 * @author pkozlov
 */
@RunWith(Parameterized.class)
public class ContactListSortTest extends ContactServiceStatefulBase {
	
	private final Map<String, Object> sortCriteria;

	@Parameters
	public static Collection<Object[]> sortParameters() {
		Collection<Object[]> result = new ArrayList<Object[]>();
		result.add(new Object[] { "firstName", "ASCENDING" });
		result.add(new Object[] { "firstName", "DESCENDING" });
		result.add(new Object[] { "lastName", "ASCENDING" });
		result.add(new Object[] { "lastName", "DESCENDING" });
		result.add(new Object[] { "emailAddress", "ASCENDING" });
		result.add(new Object[] { "emailAddress", "DESCENDING" });
		result.add(new Object[] { "workPhone", "ASCENDING" });
		result.add(new Object[] { "workPhone", "DESCENDING" });
		result.add(new Object[] { "alternatePhone", "ASCENDING" });
		result.add(new Object[] { "alternatePhone", "DESCENDING" });
		result.add(new Object[] { "addressLine1", "ASCENDING" });
		result.add(new Object[] { "addressLine1", "DESCENDING" });
		result.add(new Object[] { "addressLine2", "ASCENDING" });
		result.add(new Object[] { "addressLine2", "DESCENDING" });
		result.add(new Object[] { "addressLine3", "ASCENDING" });
		result.add(new Object[] { "addressLine3", "DESCENDING" });
		result.add(new Object[] { "city", "ASCENDING" });
		result.add(new Object[] { "city", "DESCENDING" });
		result.add(new Object[] { "state", "ASCENDING" });
		result.add(new Object[] { "state", "DESCENDING" });
		result.add(new Object[] { "province", "ASCENDING" });
		result.add(new Object[] { "province", "DESCENDING" });
		result.add(new Object[] { "country", "ASCENDING" });
		result.add(new Object[] { "country", "DESCENDING" });
		result.add(new Object[] { "postalCode", "ASCENDING" });
		result.add(new Object[] { "postalCode", "DESCENDING" });
		return result;
	}

	public ContactListSortTest(String key, String value) {
		sortCriteria = new HashMap<String, Object>();
		sortCriteria.put(key, value);
	}

	@Test
	public void testContactListSort() throws Exception {
		Iterator<Entry<String, Object>> sortIterator = sortCriteria.entrySet().iterator();
		Entry<String, Object> sortEntryMap = sortIterator.next();
		String criteriaKey = sortEntryMap.getKey();
		String criteriaValue = (String) sortEntryMap.getValue();
		Comparator<Object> comparator = new ListComparator(criteriaValue);

		List<AccountContact> contactList = getSortedContactList(sortCriteria);

		for (int i = 0; i < contactList.size() - 1; i++) {
			AccountContact firstContact = contactList.get(i);
			AccountContact secondContact = contactList.get(i + 1);
			String first = "";
			String second = "";
			if (criteriaKey.equalsIgnoreCase("firstName")) {
				first = firstContact.getFirstName();
				second = secondContact.getFirstName();
			} else if (criteriaKey.equalsIgnoreCase("lastName")) {
				first = firstContact.getLastName();
				second = secondContact.getLastName();
			} else if (criteriaKey.equalsIgnoreCase("emailAddress")) {
				first = firstContact.getEmailAddress();
				second = secondContact.getEmailAddress();
			} else if (criteriaKey.equalsIgnoreCase("workPhone")) {
				first = firstContact.getWorkPhone();
				second = secondContact.getWorkPhone();
			} else if (criteriaKey.equalsIgnoreCase("alternatePhone")) {
				first = firstContact.getAlternatePhone();
				second = secondContact.getAlternatePhone();
			} else if (criteriaKey.equalsIgnoreCase("addressLine1")) {
				first = firstContact.getAddress().getAddressLine1();
				second = secondContact.getAddress().getAddressLine1();
			} else if (criteriaKey.equalsIgnoreCase("city")) {
				first = firstContact.getAddress().getCity();
				second = secondContact.getAddress().getCity();
			} else if (criteriaKey.equalsIgnoreCase("state")) {
				first = firstContact.getAddress().getState();
				second = secondContact.getAddress().getState();
			} else if (criteriaKey.equalsIgnoreCase("province")) {
				first = firstContact.getAddress().getProvince();
				second = secondContact.getAddress().getProvince();
			} else if (criteriaKey.equalsIgnoreCase("country")) {
				first = firstContact.getAddress().getCountry();
				second = secondContact.getAddress().getCountry();
			} else if (criteriaKey.equalsIgnoreCase("postalCode")) {
				first = firstContact.getAddress().getPostalCode();
				second = secondContact.getAddress().getPostalCode();
			}  else if (criteriaKey.equalsIgnoreCase("addressLine2")) {
				first = firstContact.getAddress().getAddressLine2();
				second = secondContact.getAddress().getAddressLine2();
			}  else if (criteriaKey.equalsIgnoreCase("addressLine3")) {
				first = firstContact.getAddress().getAddressLine3();
				second = secondContact.getAddress().getAddressLine3();
			} 
			else {
				throw new IllegalArgumentException("Wrong sort parameter for : " + criteriaKey);
			}

			comparator.compare(first, second);
		}

	}

	private List<AccountContact> getSortedContactList(Map<String, Object> sortCriteria) throws Exception {
		ContactListContract contract = new ContactListContract();
		contract.setSortCriteria(sortCriteria);
		contract.setContactId("1-2873UPV");
		contract.setFavoriteFlag(false);
		contract.setSessionHandle(handle);
		contract.setMdmId("DUN1-L4BTZC");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(100);
		ContactListResult result = service.retrieveContactList(contract);
		List<AccountContact> contactList = result.getContacts();
		assertNotNull("Service return null pointer!", contactList);
		assertTrue("Service return empty list!", contactList.size() > 0);
		return contactList;
	}
}
