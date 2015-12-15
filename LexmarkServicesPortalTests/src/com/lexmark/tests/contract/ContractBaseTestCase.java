package com.lexmark.tests.contract;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.contract.ContractBase;

/**
*
* Auto generated Test Codes 
*
**/

public class ContractBaseTestCase {

   private ContractBase contractbase = new ContractBase();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    contractbase.setServiceSessionId("11111111111");


    assertEquals("11111111111",contractbase.getServiceSessionId());
   }
}

