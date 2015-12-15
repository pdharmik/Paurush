package com.lexmark.util;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

/**
 * @author vpetruchok
 * @version 1.0, 2012-04-10
 */
public class LangUtilTest {
    
    @Test
    public void isEmpty_collection() {
        assertTrue(LangUtil.isEmpty((ArrayList<Object>) null));
        assertTrue(LangUtil.isEmpty(new ArrayList<Object>()));
        assertFalse(LangUtil.isEmpty(Arrays.asList("1", "b")));
    }

    @Test
    public void isNotEmpty_collection() {
        assertFalse(LangUtil.isNotEmpty((ArrayList<Object>) null));
        assertFalse(LangUtil.isNotEmpty(new ArrayList<Object>()));
        assertTrue(LangUtil.isNotEmpty(Arrays.asList("1", "b")));
    }

    @Test
    public void isEmpty_charSequence() throws Exception {
        assertTrue(LangUtil.isEmpty((String) null));
        assertTrue(LangUtil.isEmpty(""));
        assertFalse(LangUtil.isEmpty("a"));
    }
    
    @Test
    public void testNotNullList() {
    	assertNotNull(LangUtil.notNull(null));
    	assertTrue(LangUtil.notNull(null).isEmpty());
    	
    	List<String> list1 = new ArrayList<String>();
    	list1.add("one");
    	list1.add("two");
    	list1.add("three");
    	List<String> list2 = LangUtil.notNull(list1);
    	assertEquals(list1, list2);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testNotNull_nulls() {
        assertNotNull(LangUtil.notNull(null));
        assertFalse(null instanceof Object);
        assertTrue(LangUtil.notNull((ArrayList) null) instanceof ArrayList);
        assertTrue(LangUtil.notNull((List) null) instanceof List);
        assertFalse(LangUtil.notNull((List) null) instanceof ArrayList);
        assertTrue(LangUtil.notNull((MyArrayList) null) instanceof ArrayList);
    }
    
    @SuppressWarnings("serial")
    private static class MyArrayList<E> extends ArrayList<E> {}
    
    @Test
    public void testHashCode() throws Exception {
        Integer i1 = 11;
        assertFalse(i1.hashCode() == System.identityHashCode(i1));
    }

    @Test
    public void testToInt() throws Exception {
        assertEquals(1, LangUtil.toInt("1"));
        assertEquals(1, LangUtil.toInt("abc", 1));
    }
    

    @Test
    public void testIsBlank() throws Exception {
        assertEquals(false, LangUtil.isBlank("abc"));
        assertEquals(true, LangUtil.isBlank(null));
        assertEquals(true, LangUtil.isBlank(""));
        assertEquals(true, LangUtil.isBlank(" "));
        assertEquals(true, LangUtil.isBlank("\n "));
        assertEquals(true, LangUtil.isBlank(" \r  "));
    }

    @Test
    public void testRtrim() throws Exception {
      assertEquals(null, LangUtil.rtrim(null)); 
      assertEquals("", LangUtil.rtrim("")); 
      assertEquals("a", LangUtil.rtrim("a")); 
      assertEquals(" a", LangUtil.rtrim(" a")); 
      assertEquals(" a", LangUtil.rtrim(" a ")); 
      assertEquals("abc", LangUtil.rtrim("abc \n \r ")); 
      assertEquals("", LangUtil.rtrim("  ")); 
      assertEquals("abc", LangUtil.rtrim("abc")); 
    }
    
    @Test
    public void testLtrim() throws Exception {
      assertEquals(null, LangUtil.ltrim(null)); 
      assertEquals("", LangUtil.ltrim("")); 
      assertEquals("a", LangUtil.ltrim("a")); 
      assertEquals("a", LangUtil.ltrim(" a")); 
      assertEquals("a ", LangUtil.ltrim(" a ")); 
      assertEquals("abc", LangUtil.ltrim(" \n \r abc")); 
      assertEquals("", LangUtil.ltrim("  ")); 
      assertEquals("abc", LangUtil.ltrim("abc")); 
    }
    
    @Test
    public void testString() throws Exception {
        "1".substring(1);
        
        try {
            "1".substring(2);
            fail();
        } catch (StringIndexOutOfBoundsException ok) {
        }
        
        assertEquals(true, "".startsWith(""));
        assertEquals(true, "a".startsWith(""));
    }
    
    @Test
    public void testTrim() throws Exception {
      assertEquals(null, LangUtil.trim(null)); 
      assertEquals("", LangUtil.trim("")); 
      assertEquals("a", LangUtil.trim("a")); 
      assertEquals("a", LangUtil.trim(" a\t")); 
      assertEquals("a", LangUtil.trim(" a ")); 
      assertEquals("abc", LangUtil.trim(" \n \r abc")); 
      assertEquals("", LangUtil.trim("  ")); 
      assertEquals("abc", LangUtil.trim("abc ")); 
    }

    @Test
    public void testStartsWith() throws Exception {
      assertEquals(false, LangUtil.startsWith(null, "a")); 
      assertEquals(false, LangUtil.startsWith("a", null)); 
      assertEquals(false, LangUtil.startsWith(null, null)); 
      assertEquals(true, LangUtil.startsWith("a1", "a")); 
      assertEquals(false, LangUtil.startsWith("a", "a1")); 
    
    }
    
    @Test
    public void testConvertStringToBigDecimal() throws Exception {
        LangUtil.convertStringToBigDecimal("12.111", 2);
    }

    @Test
    public void testEqualIgnoreCase() throws Exception {
        assertEquals(true , LangUtil.equalIgnoreCase(null, null));
        assertEquals(false , LangUtil.equalIgnoreCase(null, ""));
        assertEquals(false , LangUtil.equalIgnoreCase("", null));
        assertEquals(true , LangUtil.equalIgnoreCase("", ""));
        assertEquals(true , LangUtil.equalIgnoreCase(new String("a"), new String("a")));
    }
}