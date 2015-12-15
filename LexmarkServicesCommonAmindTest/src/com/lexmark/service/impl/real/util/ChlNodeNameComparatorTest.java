package com.lexmark.service.impl.real.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.lexmark.domain.CHLNode;


/**
 * @author vpetruchok
 * @version 1.0, Mar 6, 2012
 */
public class ChlNodeNameComparatorTest {

	
	@Test
    public void testChlNodeNameComparator() {
      	ChlNodeNameComparator c = new ChlNodeNameComparator();
      	assertEquals(0, c.compare(null, null));
      	assertEquals(-1, c.compare(null, new CHLNode()));
      	assertEquals(1, c.compare(new CHLNode(), null));
      	
      	{
            CHLNode n1 = new CHLNode();
            CHLNode n2 = new CHLNode();
		    assertEquals(0, c.compare(n1, n2));
      	}
      	
      	{
            CHLNode n1 = new CHLNode();
            CHLNode n2 = new CHLNode();
            n1.setChlNodeName("a");
            n2.setChlNodeName(null);
		    assertEquals(1, c.compare(n1, n2));
      	}
      	
      	{
            CHLNode n1 = new CHLNode();
            CHLNode n2 = new CHLNode();
            n1.setChlNodeName(null);
            n2.setChlNodeName("a");
		    assertEquals(-1, c.compare(n1, n2));
      	}
      	
      	{
            CHLNode n1 = new CHLNode();
            CHLNode n2 = new CHLNode();
            n1.setChlNodeName("a");
            n2.setChlNodeName(null);
		    assertEquals(1, c.compare(n1, n2));
      	}
      	
      	{
            CHLNode n1 = new CHLNode();
            CHLNode n2 = new CHLNode();
            n1.setChlNodeName("a");
            n2.setChlNodeName("a");
		    assertEquals(0, c.compare(n1, n2));
      	}
      	
      	{
            CHLNode n1 = new CHLNode();
            CHLNode n2 = new CHLNode();
            n1.setChlNodeName("A");
            n2.setChlNodeName("a");
		    assertEquals(0, c.compare(n1, n2));
      	}
    }
	
}
