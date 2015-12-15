package com.lexmark.service.impl.real;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
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
public class ContactListFavoriteTest extends ContactServiceStatefulBase {

	private final ContactListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		
		list.add(new Object[] { "1-P3YKE", "DUN1-L4BTZC", "Global" });
		
		return list;
	}

	public ContactListFavoriteTest(String contactId, String mdmId, String mdmLevel) {
		contract = new ContactListContract();
		contract.setContactId(contactId);
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(handle);
	}

	@Test
	public void testFavoriteContactList() throws Exception {
		contract.setFavoriteFlag(true);
		doTestContactList(contract);
	}

	@Test
	public void testAllContactList() throws Exception {
		contract.setFavoriteFlag(false);
		doTestContactList(contract);
	}

	private void doTestContactList(ContactListContract contract) throws Exception {
		ContactListResult result = service.retrieveContactList(contract);
		assertNotNull("result is null!", result);
		List<AccountContact> contactList = result.getContacts();
		assertNotNull("contact list is null!", contactList);
		assertFalse("contact list is empty!", contactList.isEmpty());

		int size = contactList.size();
		int totalCount = result.getTotalCount();
		logger.debug("Contact List size: " + size);
		logger.debug("Total count: " + totalCount);
		assertEquals("Total count incorrectly set", size, totalCount);

		if (contract.isFavoriteFlag()) {
			for (AccountContact contact : contactList) {
				assertTrue("user favorite flag is not set!", contact.getUserFavorite());
			}
		}
	}

}
