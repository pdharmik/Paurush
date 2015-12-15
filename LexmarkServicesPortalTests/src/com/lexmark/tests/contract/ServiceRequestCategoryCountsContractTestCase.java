package com.lexmark.tests.contract;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.contract.ServiceRequestCategoryCountsContract;

/**
*
* Auto generated Test Codes 
*
**/

public class ServiceRequestCategoryCountsContractTestCase {

   private ServiceRequestCategoryCountsContract servicerequestcategorycountscontract = new ServiceRequestCategoryCountsContract();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    servicerequestcategorycountscontract.setMdmId("mdmId");
    servicerequestcategorycountscontract.setMdmLevel("mdmLevel");
    servicerequestcategorycountscontract.setContactId("contactId");
    servicerequestcategorycountscontract.setChlNodeId("chlNodeId");
    servicerequestcategorycountscontract.setLocale(locale);


    assertEquals("mdmId",servicerequestcategorycountscontract.getMdmId());
    assertEquals("mdmLevel",servicerequestcategorycountscontract.getMdmLevel());
    assertEquals("contactId",servicerequestcategorycountscontract.getContactId());
    assertEquals("chlNodeId",servicerequestcategorycountscontract.getChlNodeId());
    assertEquals(locale.US,servicerequestcategorycountscontract.getLocale());
   }
}

