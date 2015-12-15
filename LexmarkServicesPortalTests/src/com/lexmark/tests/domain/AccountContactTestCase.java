package com.lexmark.tests.domain;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.domain.AccountContact;

/**
*
* Auto generated Test Codes 
*
**/

public class AccountContactTestCase {

   private AccountContact accountcontact = new AccountContact();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    accountcontact.setContactId("contactId");
    accountcontact.setDepartment("department");
    accountcontact.setWorkPhone("workPhone");
    accountcontact.setEmailAddress("emailAddress");
    accountcontact.setUserFavorite(true);
    accountcontact.setFirstName("firstName");
    accountcontact.setLastName("lastName");
    accountcontact.setUpdateContactFlag(true);
    accountcontact.setNewContactFlag(false);


    assertEquals("contactId",accountcontact.getContactId());
    assertEquals("department",accountcontact.getDepartment());
    assertEquals("workPhone",accountcontact.getWorkPhone());
    assertEquals("emailAddress",accountcontact.getEmailAddress());
    assertEquals(true,accountcontact.getUserFavorite());
    assertEquals("firstName",accountcontact.getFirstName());
    assertEquals("lastName",accountcontact.getLastName());
    assertEquals(true,accountcontact.getUpdateContactFlag());
    assertEquals(false,accountcontact.getNewContactFlag());
   }
}

