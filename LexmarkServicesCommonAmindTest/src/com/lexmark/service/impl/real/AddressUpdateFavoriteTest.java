package com.lexmark.service.impl.real;

import static junit.framework.Assert.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.*;
import com.lexmark.contract.FavoriteAddressContract;
import com.lexmark.result.FavoriteResult;

/**
 * 
 * @author pkozlov
 *
 */
@RunWith(Parameterized.class)
public class AddressUpdateFavoriteTest extends RequestServiceStatelessBase {

	private final FavoriteAddressContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] { "1-BGJ7-279", "1-6W9BNU" });
		return list;
	}

	public AddressUpdateFavoriteTest(String contactId, String favoriteContactId) {
		contract = new FavoriteAddressContract();
		contract.setContactId(contactId);
		contract.setFavoriteAddressId(favoriteContactId);
	}
	
	@Test
	public void testCreateUserFavoriteAddress() throws Exception {
		logger.debug("[IN] testCreateUserFavoriteAddress");
		contract.setSessionHandle(null);
		contract.setFavoriteFlag(true);
		FavoriteResult result = service.updateUserFavoriteAddress(contract);
		assertNotNull("result is null!",result);
		assertTrue("result is false!",result.isResult());
		logger.debug("[OUT] testCreateUserFavoriteAddress");
	}
	
	@Test
	public void testDeleteUserFavoriteAddress() throws Exception {
		logger.debug("[IN] testDeleteUserFavoriteAddress");
		contract.setSessionHandle(null);
		contract.setFavoriteFlag(false);
		FavoriteResult	result = service.updateUserFavoriteAddress(contract);
		assertNotNull("result is null!",result);
		assertTrue("result is false!",result.isResult());
		logger.debug("[OUT] testDeleteUserFavoriteAddress");
	}

}