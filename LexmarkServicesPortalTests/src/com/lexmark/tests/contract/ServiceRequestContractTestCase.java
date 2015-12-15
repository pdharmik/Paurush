package com.lexmark.tests.contract;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.contract.ServiceRequestContract;

/**
*
* Auto generated Test Codes 
*
**/

public class ServiceRequestContractTestCase {

   private ServiceRequestContract servicerequestcontract = new ServiceRequestContract();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    servicerequestcontract.setRequestNumber("requestNumber");
    servicerequestcontract.setLocale(locale);


    assertEquals("requestNumber",servicerequestcontract.getRequestNumber());
    assertEquals(locale.US,servicerequestcontract.getLocale());
   }
}

