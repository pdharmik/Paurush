package com.lexmark.tests.domain;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.domain.AssetIdentifier;

/**
*
* Auto generated Test Codes 
*
**/

public class AssetIdentifierTestCase {

   private AssetIdentifier assetidentifier = new AssetIdentifier();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    assetidentifier.setAssetIdentifierId("assetIdentifierId");
    assetidentifier.setAssetIdentifierName("assetIdentifierName");


    assertEquals("assetIdentifierId",assetidentifier.getAssetIdentifierId());
    assertEquals("assetIdentifierName",assetidentifier.getAssetIdentifierName());
   }
}

