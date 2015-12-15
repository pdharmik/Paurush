package com.lexmark.tests.domain;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.domain.ServicesUser;

/**
*
* Auto generated Test Codes 
*
**/

public class ServicesUserTestCase {

   private ServicesUser servicesuser = new ServicesUser();
   private Long lg = new Long(111111L);
   Locale locale = Locale.US;
   @Test
   public void testContract(){

    servicesuser.setServicesUserId(1);
    servicesuser.setEmailAddress("emailAddress");
    servicesuser.setStatus("status");
    servicesuser.setUserType("userType");
    servicesuser.setChlNodeId("chlNodeId");
    servicesuser.setCreditCardToken(lg);
    servicesuser.setNotifyNewProductServices(true);
    servicesuser.setNotifyNewTransactions(true);
    servicesuser.setSiebelContactId(lg);


    assertEquals(1,servicesuser.getServicesUserId());
    assertEquals("emailAddress",servicesuser.getEmailAddress());
    assertEquals("status",servicesuser.getStatus());
    assertEquals("userType",servicesuser.getUserType());
    assertEquals("chlNodeId",servicesuser.getChlNodeId());
    assertEquals(lg,servicesuser.getCreditCardToken());
    assertEquals(true,servicesuser.getNotifyNewProductServices());
    assertEquals(true,servicesuser.getNotifyNewTransactions());
    //assertEquals(lg,servicesuser.getSelectedAccountId());
    //assertEquals("siebelContactId",servicesuser.getSiebelContactId());
   }
}

