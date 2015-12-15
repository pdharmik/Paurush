package com.lexmark.tests.contract;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.domain.ServiceRequest;

/**
*
* Auto generated Test Codes 
*
**/

public class CreateServiceRequestContractTestCase {

   private CreateServiceRequestContract createservicerequestcontract = new CreateServiceRequestContract();
   private ServiceRequest sr = new ServiceRequest();
   Locale locale = Locale.US;
   @Test
   public void testContract(){

    createservicerequestcontract.setServiceRequest(sr);
    createservicerequestcontract.setContactId("contactId");
    createservicerequestcontract.setLocale(locale);


    assertEquals(sr,createservicerequestcontract.getServiceRequest());
    assertEquals("contactId",createservicerequestcontract.getContactId());
    assertEquals(locale.US,createservicerequestcontract.getLocale());
   }
}

