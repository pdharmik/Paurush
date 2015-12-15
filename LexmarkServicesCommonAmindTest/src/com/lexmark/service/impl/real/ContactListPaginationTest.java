package com.lexmark.service.impl.real;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
public class ContactListPaginationTest extends ContactServiceStatefulBase {
	
	private final int startRecordNumber;
	private final int increment;

	public ContactListPaginationTest(int startRecordNumber, int increment) {
		this.startRecordNumber = startRecordNumber;
		this.increment = increment;
	}

	@Parameters
	public static Collection<Object[]> paginationParameters() {
		Collection<Object[]> result = new ArrayList<Object[]>();
		result.add(new Object[] { 0, 10 });
		result.add(new Object[] { 0, 20 });
		result.add(new Object[] { 0, 30 });
		result.add(new Object[] { 10, 20 });
		result.add(new Object[] { 5, 15 });
		result.add(new Object[] { 5, 20 });
		return result;
	}

	@Test
	public void testContactListPagination() throws Exception {
		ContactListContract contract = new ContactListContract();
		contract.setNewQueryIndicator(true);
		contract.setContactId("1-1DTJMA");
		contract.setFavoriteFlag(false);
		contract.setSessionHandle(handle);
		contract.setMdmId("DUN1-L4BTZC");
		contract.setMdmLevel("Global");
		contract.setStartRecordNumber(startRecordNumber);
		contract.setIncrement(increment);

		ContactListResult result = service.retrieveContactList(contract);
		List<AccountContact> contactList = result.getContacts();
		assertNotNull("Service return null pointer!", contactList);
		assertTrue("Service return empty list!", contactList.size() > 0);
		assertTrue("Service return too long list!", contactList.size() <= increment);
	}

}
