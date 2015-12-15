package com.lexmark.tests.contract;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.contract.FavoriteContract;

/**
*
* Auto generated Test Codes 
*
**/

public class FavoriteContractTestCase {

   private FavoriteContract favoritecontract = new FavoriteContract();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    favoritecontract.setContactId("contactId");
    favoritecontract.setFavoriteContactId("favoriteContactId");
    favoritecontract.setFavoriteFlag(true);
    favoritecontract.setLocale(locale);


    assertEquals("contactId",favoritecontract.getContactId());
    assertEquals("favoriteContactId",favoritecontract.getFavoriteContactId());
    assertEquals(true,favoritecontract.isFavoriteFlag());
    assertEquals(locale.US,favoritecontract.getLocale());
   }
}

