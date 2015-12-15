package com.lexmark.tests.contract;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.contract.SearchContractBase;

/**
*
* Auto generated Test Codes 
*
**/

public class SearchContractBaseTestCase {

   private SearchContractBase searchcontractbase = new SearchContractBase();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    searchcontractbase.setStartRecordNumber(10);
    searchcontractbase.setIncrement(1);


    assertEquals(10,searchcontractbase.getStartRecordNumber());
    assertEquals(1,searchcontractbase.getIncrement());
   }
}

