package com.lexmark.tests.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import com.lexmark.domain.Entitlement;
import com.lexmark.domain.EntitlementServiceDetail;

/**
*
* Auto generated Test Codes 
*
**/

public class EntitlementTestCase {

   private Entitlement entitlement = new Entitlement();
   private List<EntitlementServiceDetail>  detail = new ArrayList<EntitlementServiceDetail> ();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    entitlement.setEntitlementId("entitlementId");
    entitlement.setEntitlementName("entitlementName");
    entitlement.setServiceDetails(detail);


    assertEquals("entitlementId",entitlement.getEntitlementId());
    assertEquals("entitlementName",entitlement.getEntitlementName());
    assertEquals(detail,entitlement.getServiceDetails());
   }
}

