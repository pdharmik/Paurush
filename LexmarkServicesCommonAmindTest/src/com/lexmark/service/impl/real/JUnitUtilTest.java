package com.lexmark.service.impl.real;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;


/**
 * @author vpetruchok
 * @version 1.0, 2013-02-20
 */
public class JUnitUtilTest {
    
    @Rule
    public MethodRule rule1 = new JUnitUtil.TestExecutionInfoRule();
    
    @Test
    public void emptyTest() throws Exception {
    }

    @Test
    public void simpleTest() throws Exception {
        System.out.println("hi");
    }
    
    @Test
    public void timeMesure() throws Exception {
        TimeUnit.SECONDS.sleep(3);
//        System.out.println("hi");
    }
    
    @Test
    public void testPrintf() throws Exception {
       assertEquals("3,5", String.format("%.1f", 3.45)); 
       assertEquals("3,45", String.format("%.2f", 3.45)); 
       assertEquals("3,450000", String.format("%f", 3.45));  //  If the precision is not specified then the default value is 6.
       assertEquals("3,4", String.format("%.1f", 3.44)); 
    }
}
