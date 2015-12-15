package com.lexmark.util;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TreeXmlBuilderTest {
	private final String treeString = "<tree id=\"0\"><item id=\"null\" text=\"null\" open=\"1\"><item id=\"1\" text=\"itemB\" open=\"1\"><item id=\"1\" text=\"itemC\" open=\"1\"></item></item></item></tree>";
	
	@Test
	public void testTreeXmlBuilder(){
		TreeXmlBuilder treeXmlBuilder = new TreeXmlBuilder();
		
		TreeXmlBuilder.Item itemA = new TreeXmlBuilder.Item();
		TreeXmlBuilder.Item itemB = new TreeXmlBuilder.Item("1","itemB",1);
		TreeXmlBuilder.Item itemC = new TreeXmlBuilder.Item("1","itemC");
		itemA.addChildItem(itemB);
		itemB.getChildItems().add(itemC);
		treeXmlBuilder.addItem(itemA);
		assertTrue(treeString.equals(treeXmlBuilder.toXML()));
	}
}
