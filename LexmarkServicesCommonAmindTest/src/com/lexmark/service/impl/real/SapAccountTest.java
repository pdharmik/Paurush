package com.lexmark.service.impl.real;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.AddressListContract;
import com.lexmark.domain.GenericAddress;
import com.lexmark.result.AddressListResult;

@RunWith(Parameterized.class)
public class SapAccountTest extends GlobalServiceStatefulBase {

	private final AddressListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();

		list.add(new Object[] { "12345", "2345678", "4123"});

		return list;
	}

	public SapAccountTest(String num1, String num2, String num3) {
		contract = new AddressListContract();
		List<String> list = new ArrayList<String>();
		list.add(num1);
		list.add(num2);
		list.add(num3);
		contract.setSoldToNumbers(list);
		contract.setSessionHandle(handle);
	}

	@Test
	public void testRetrieveEntitelmentFlags() throws Exception {
		AddressListResult result = globalService
				.retrieveBillToAddressList(contract);
		assertNotNull("result is null!", result);
		List<GenericAddress> accounts = result.getAddressList();
		assertNotNull("list is null!", accounts);
	}

}
