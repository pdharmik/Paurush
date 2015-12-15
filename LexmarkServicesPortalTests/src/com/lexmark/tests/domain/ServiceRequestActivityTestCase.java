package com.lexmark.tests.domain;

import org.junit.Test;
import org.junit.Before;

import java.util.Date;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.domain.ServiceRequestActivity;

/**
*
* Auto generated Test Codes 
*
**/

public class ServiceRequestActivityTestCase {

   private ServiceRequestActivity servicerequestactivity = new ServiceRequestActivity();
   private Date date = new Date();
   Locale locale = Locale.US;
   @Test
   public void testContract(){

    servicerequestactivity.setActivityId("activityId");
    servicerequestactivity.setActivityDate(date);
    servicerequestactivity.setActivityStatus("activityStatus");
    servicerequestactivity.setActivityDescription("activityDescription");
    servicerequestactivity.setRecipientEmail("recipientEmail");


    assertEquals("activityId",servicerequestactivity.getActivityId());
    assertNotNull(servicerequestactivity.getActivityDate());
    assertEquals("activityStatus",servicerequestactivity.getActivityStatus());
    assertEquals("activityDescription",servicerequestactivity.getActivityDescription());
    assertEquals("recipientEmail",servicerequestactivity.getRecipientEmail());
   }
}

