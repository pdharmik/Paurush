package com.lexmark.tests.contract;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.contract.AssetContract;

/**
*
* Auto generated Test Codes 
*
**/

public class AssetContractTestCase {

   private AssetContract assetcontract = new AssetContract();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    assetcontract.setAssetId("assetId");


    assertEquals("assetId",assetcontract.getAssetId());
   }
}

