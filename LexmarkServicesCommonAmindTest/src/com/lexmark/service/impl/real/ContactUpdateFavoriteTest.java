package com.lexmark.service.impl.real;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.FavoriteContract;
import com.lexmark.result.FavoriteResult;

/**
 * @author pkozlov
 */
@RunWith(Parameterized.class)
public class ContactUpdateFavoriteTest extends ContactServiceStatelessBase {

	private final FavoriteContract contract;

	@Parameters
	public static List<Object[]> updateFavoriteParameters() {
		List<Object[]> list = new ArrayList<Object[]>();

		list.add(new Object[] { "1-1G7S", "1-1G7S" });
		// TODO add other parameters

		return list;
	}

	public ContactUpdateFavoriteTest(String favoriteContactId, String contactId) {
		contract = new FavoriteContract();
		// REL_PARTY_ID
		contract.setFavoriteContactId(favoriteContactId);
		// PARTY_ID
		contract.setContactId(contactId);
	}

	@Test
	public void testCreateUserFavoriteContact() throws Exception {
		contract.setSessionHandle(null);
		contract.setFavoriteFlag(true);
		FavoriteResult result = service.updateUserFavoriteContact(contract);
		assertNotNull("result is null!", result);
		assertTrue("result is false!", result.isResult());
	}

	@Test
	public void testDeleteUserFavoriteContact() throws Exception {
		contract.setSessionHandle(null);
		contract.setFavoriteFlag(false);
		FavoriteResult result = service.updateUserFavoriteContact(contract);
		assertNotNull("result is null!", result);
		assertTrue("result is false!", result.isResult());
	}
}
