package com.lexmark.tests.contract;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.contract.AssetListContract;

/**
*
* Auto generated Test Codes 
*
**/

public class AssetListContractTestCase {

   private AssetListContract assetlistcontract = new AssetListContract();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    assetlistcontract.setMdmId("mdmId");
    assetlistcontract.setMdmLevel("mdmLevel");
    assetlistcontract.setFavoriteContactId("contactId");
    assetlistcontract.setChlNodeId("chlNodeId");
    assetlistcontract.setLocale(locale);
    assetlistcontract.setLoadAllFlag(true);


    assertEquals("mdmId",assetlistcontract.getMdmId());
    assertEquals("mdmLevel",assetlistcontract.getMdmLevel());
    assertEquals("contactId",assetlistcontract.getFavoriteContactId());
    assertEquals("chlNodeId",assetlistcontract.getChlNodeId());
    assertEquals(locale.US,assetlistcontract.getLocale());
    assertEquals(true,assetlistcontract.getLoadAllFlag());
   }
}

