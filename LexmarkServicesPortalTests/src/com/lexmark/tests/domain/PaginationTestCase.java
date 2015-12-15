package com.lexmark.tests.domain;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.domain.Pagination;

/**
*
* Auto generated Test Codes 
*
**/

public class PaginationTestCase {

   private Pagination pagination = new Pagination();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    pagination.setOrderBy("orderBy");
    pagination.setDirection("direction");
    //pagination.setDEFAULT_START_POSITION("DEFAULT_START_POSITION");
    //pagination.setDEFAULT_COUNT("DEFAULT_COUNT");


    assertEquals("orderBy",pagination.getOrderBy());
    assertEquals("direction",pagination.getDirection());
    //assertEquals("DEFAULT_START_POSITION",pagination.getDEFAULT_START_POSITION());
    //assertEquals("DEFAULT_COUNT",pagination.getDEFAULT_COUNT());
   }
}

