package com.lexmark.service.impl.real;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;
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
public class AddressFavoriteListTest extends RequestServiceStatefulBase {

	private final AddressListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		
		// old params
		 list.add(new Object[] { "1-P3YKE", "DUN1-L4BTZC", "Global" });
		
		// new params
//		list.add(new Object[] { "1-FJ7YXN", "275254944", "Global" });
		
		return list;
	}

	public AddressFavoriteListTest(String contactId, String mdmId, String mdmLevel) {
		contract = new AddressListContract();
		contract.setContactId(contactId);
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(handle);
	}

	@Test
	public void testFavoriteAddressList() throws Exception {
		contract.setFavoriteFlag(true);
		doTestAddressList(contract);
	}

	@Test
	public void testAllAddressList() throws Exception {
		contract.setFavoriteFlag(false);
		doTestAddressList(contract);
	}

	private void doTestAddressList(AddressListContract contract) throws Exception {
		AddressListResult result = service.retrieveAddressList(contract);
		assertNotNull("result is null!", result);
		List<GenericAddress> addressList = result.getAddressList();
		assertNotNull("address list is null!", addressList);
		assertFalse("address list is empty!", addressList.isEmpty());

		int size = addressList.size();
		int totalCount = result.getTotalCount();
		logger.debug("Address List size: " + size);
		logger.debug("Total count: " + totalCount);
		assertEquals("Total count incorrectly set", size, totalCount);

		if (contract.isFavoriteFlag()) {
			for (GenericAddress address : addressList) {
				assertTrue("user favorite flag is not set!", address.getUserFavorite());
			}
		}
	}
}
