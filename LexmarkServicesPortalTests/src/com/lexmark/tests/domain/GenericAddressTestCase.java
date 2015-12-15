package com.lexmark.tests.domain;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.domain.GenericAddress;

/**
*
* Auto generated Test Codes 
*
**/

public class GenericAddressTestCase {

   private GenericAddress genericaddress = new GenericAddress();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    genericaddress.setAddressId("addressId");
    genericaddress.setAddressName("addressName");
    genericaddress.setAddressLine1("addressLine1");
    genericaddress.setAddressLine2("addressLine2");
    genericaddress.setAddressLine3("addressLine3");
    genericaddress.setAddressLine4("addressLine4");
    genericaddress.setCity("city");
    genericaddress.setState("state");
    genericaddress.setProvince("province");
    genericaddress.setPostalCode("postalCode");
    genericaddress.setCountry("country");
    genericaddress.setNewAddressFlag(true);
    genericaddress.setUserFavorite(true);


    assertEquals("addressId",genericaddress.getAddressId());
    assertEquals("addressName",genericaddress.getAddressName());
    assertEquals("addressLine1",genericaddress.getAddressLine1());
    assertEquals("addressLine2",genericaddress.getAddressLine2());
    assertEquals("addressLine3",genericaddress.getAddressLine3());
    assertEquals("addressLine4",genericaddress.getAddressLine4());
    assertEquals("city",genericaddress.getCity());
    assertEquals("state",genericaddress.getState());
    assertEquals("province",genericaddress.getProvince());
    assertEquals("postalCode",genericaddress.getPostalCode());
    assertEquals("country",genericaddress.getCountry());
    assertEquals(true,genericaddress.getNewAddressFlag());
    assertEquals(true,genericaddress.getUserFavorite());
   }
}

