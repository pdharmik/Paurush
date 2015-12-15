package com.lexmark.tests.contract;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.contract.ServiceRequestListContract;

/**
*
* Auto generated Test Codes 
*
**/

public class ServiceRequestListContractTestCase {

   private ServiceRequestListContract servicerequestlistcontract = new ServiceRequestListContract();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    servicerequestlistcontract.setMdmID("mdmID");
    servicerequestlistcontract.setMdmLevel("mdmLevel");
    servicerequestlistcontract.setStatus("status");
    servicerequestlistcontract.setContactID("contactID");
    servicerequestlistcontract.setStartRecordNumber(10000);
    servicerequestlistcontract.setIncrement(50);
    servicerequestlistcontract.setLocale(locale);


    assertEquals("mdmID",servicerequestlistcontract.getMdmID());
    assertEquals("mdmLevel",servicerequestlistcontract.getMdmLevel());
    assertEquals("status",servicerequestlistcontract.getStatus());
    assertEquals("contactID",servicerequestlistcontract.getContactID());
    assertEquals("startRecordNumber",servicerequestlistcontract.getStartRecordNumber());
    assertEquals("increment",servicerequestlistcontract.getIncrement());
    assertEquals(locale.US,servicerequestlistcontract.getLocale());
   }
}

