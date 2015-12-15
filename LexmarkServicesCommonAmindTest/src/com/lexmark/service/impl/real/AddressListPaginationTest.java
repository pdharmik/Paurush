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

import com.lexmark.contract.AddressListContract;
import com.lexmark.domain.GenericAddress;
import com.lexmark.result.AddressListResult;

/**
 * 
 * @author pkozlov
 *
 */
@RunWith(Parameterized.class)
public class AddressListPaginationTest extends RequestServiceStatefulBase {
	private final int startRecordNumber;
	private final int increment;

	@Parameters
	public static Collection<Object[]> inputData() {
		Collection<Object[]> result = new ArrayList<Object[]>();
		result.add(new Object[] { 0, 10 });
		result.add(new Object[] { 0, 20 });
		result.add(new Object[] { 0, 30 });
		result.add(new Object[] { 10, 20 });
		result.add(new Object[] { 5, 15 });
		result.add(new Object[] { 5, 20 });
		return result;
	}

	public AddressListPaginationTest(int startRecordNumber, int increment) {
		this.startRecordNumber = startRecordNumber;
		this.increment = increment;
	}

	@Test
	public void testAddressListPagination() throws Exception {
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
		contract.setStartRecordNumber(startRecordNumber);
		contract.setIncrement(increment);
		contract.setNewQueryIndicator(true);
		contract.setFavoriteFlag(false);

		AddressListResult result = service.retrieveAddressList(contract);
		List<GenericAddress> addressList = result.getAddressList();
		assertNotNull("Service return null pointer", addressList);
		assertTrue("Pagination don't work!",addressList.size() <= increment);
	}
}
