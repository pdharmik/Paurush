package com.lexmark.tests.contract;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.contract.ModifyContactContract;
import com.lexmark.domain.AccountContact;

/**
*
* Auto generated Test Codes 
*
**/

public class ModifyContactContractTestCase {

   private ModifyContactContract modifycontactcontract = new ModifyContactContract();
   private AccountContact ac = new AccountContact();
   Locale locale = Locale.US;
   @Test
   public void testContract(){

    modifycontactcontract.setAccountContact(ac);
    modifycontactcontract.setContactId("contactId");
    modifycontactcontract.setLocale(locale);


    assertEquals(ac,modifycontactcontract.getAccountContact());
    assertEquals("contactId",modifycontactcontract.getContactId());
    assertEquals(locale.US,modifycontactcontract.getLocale());
   }
}

