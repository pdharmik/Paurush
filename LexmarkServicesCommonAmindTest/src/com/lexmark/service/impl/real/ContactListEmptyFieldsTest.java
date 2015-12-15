package com.lexmark.service.impl.real;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.lexmark.contract.ContactListContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.result.ContactListResult;

/**
 * @author pkozlov
 */
public class ContactListEmptyFieldsTest extends ContactServiceStatefulBase {

	@Test
	public void debugEmptyFields() throws Exception {
		ContactListContract contract = new ContactListContract();
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(10);
		contract.setMdmId("DUN1-L4BTZC");
		contract.setMdmLevel("Global");
		contract.setContactId("1-1DTJMA");
		contract.setFavoriteFlag(false);
		contract.setSessionHandle(handle);

		ContactListResult result = service.retrieveContactList(contract);
		assertNotNull("result is null!", result);
		List<AccountContact> contactList = result.getContacts();
		assertNotNull("Service return null pointer!", contactList);
		assertFalse("Service return empty list!", contactList.isEmpty());

		for (AccountContact contact : contactList) {
			debugContact(contact);
		}
	}

}
