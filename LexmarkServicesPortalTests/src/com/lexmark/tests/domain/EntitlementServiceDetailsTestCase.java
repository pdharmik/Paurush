package com.lexmark.tests.domain;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.domain.EntitlementServiceDetails;

/**
*
* Auto generated Test Codes 
*
**/

public class EntitlementServiceDetailsTestCase {

   private EntitlementServiceDetails entitlementservicedetails = new EntitlementServiceDetails();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    entitlementservicedetails.setServiceDetailId("serviceDetailId");
    entitlementservicedetails.setServiceDetailsDescription("serviceDetailsDescription");


    assertEquals("serviceDetailId",entitlementservicedetails.getServiceDetailId());
    assertEquals("serviceDetailsDescription",entitlementservicedetails.getServiceDetailsDescription());
   }
}

