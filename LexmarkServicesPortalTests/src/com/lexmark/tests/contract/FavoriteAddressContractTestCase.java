package com.lexmark.tests.contract;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.contract.FavoriteAddressContract;

/**
*
* Auto generated Test Codes 
*
**/

public class FavoriteAddressContractTestCase {

   private FavoriteAddressContract favoriteaddresscontract = new FavoriteAddressContract();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    favoriteaddresscontract.setContactId("contactId");
    favoriteaddresscontract.setFavoriteAddressId("favoriteAddressId");
    favoriteaddresscontract.setFavoriteFlag(true);


    assertEquals("contactId",favoriteaddresscontract.getContactId());
    assertEquals("favoriteAddressId",favoriteaddresscontract.getFavoriteAddressId());
    assertEquals(true,favoriteaddresscontract.isFavoriteFlag());
   }
}

