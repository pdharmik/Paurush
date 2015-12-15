package com.lexmark.tests.domain;

import org.junit.Test;
import org.junit.Before;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import com.lexmark.domain.OptionExchange;

/**
*
* Auto generated Test Codes 
*
**/

public class OptionExchangeTestCase {

   private OptionExchange optionexchange = new OptionExchange();

   Locale locale = Locale.US;
   @Test
   public void testContract(){

    optionexchange.setOptionExchangeName("optionExchangeName");


    assertEquals("optionExchangeName",optionexchange.getOptionExchangeName());
   }
}

