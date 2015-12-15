package com.lexmark.tests.domain;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.AssetIdentifier;
import com.lexmark.domain.Entitlement;
import com.lexmark.domain.GenericAddress;

/**
*
* Auto generated Test Codes 
*
**/

public class AccountTestCase {

   private Account account = new Account();
   private Entitlement entitlements = new Entitlement();
   private AccountContact ac = new AccountContact();
   private GenericAddress ga = new GenericAddress();
   private AssetIdentifier ai = new AssetIdentifier();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    account.setAccountId("accountId");
    account.setAccountName("accountName");
    account.setAddress(ga);
    account.setManualMeterRead(true);
    account.setCreateServiceRequest(true);
    account.setUserConsumables(true);
    account.setPrimaryAssetIdentifier(ai);


    assertEquals("accountId",account.getAccountId());
    assertEquals("accountName",account.getAccountName());
    assertEquals(ga,account.getAddress());
    assertEquals(true,account.getManualMeterRead());
    assertEquals(true,account.getCreateServiceRequest());
    assertEquals(true,account.getUserConsumables());
    assertEquals(ai,account.getPrimaryAssetIdentifier());
   }
}

