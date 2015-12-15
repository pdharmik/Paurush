package com.lexmark.util;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class GridXmlBuilderTest {
	private final String xml = "<?xml version=\"1.0\" ?><rows total_count=\"2\" pos=\"0\"><row id=\"1\"><cell><![CDATA[cell1]]></cell><cell><![CDATA[cell2]]></cell></row><row id=\"2\"><cell><![CDATA[cell3]]></cell><cell><![CDATA[cell4]]></cell></row></rows>";
	private final String xmlString = "<?xml version=\\\"1.0\\\" ?><rows total_count=\\\"2\\\" pos=\\\"0\\\"><row id=\\\"1\\\"><cell><![CDATA[cell1]]></cell><cell><![CDATA[cell2]]></cell></row><row id=\\\"2\\\"><cell><![CDATA[cell3]]></cell><cell><![CDATA[cell4]]></cell></row></rows>";
	private final String xmlPosition = "<?xml version=\"1.0\" ?><rows total_count=\"2\" pos=\"2\"><row id=\"1\"><cell><![CDATA[cell1]]></cell><cell><![CDATA[cell2]]></cell></row><row id=\"2\"><cell><![CDATA[cell3]]></cell><cell><![CDATA[cell4]]></cell></row></rows>";
	
	@Test
	public void testGetGridXml(){
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(2);
		gridXmlBuilder.nextRow("1");
		gridXmlBuilder.addCells("cell1","cell2");
		gridXmlBuilder.nextRow("2");
		gridXmlBuilder.addCells("cell3","cell4");
		assertTrue(xml.equals(gridXmlBuilder.getGridXml()));
	}
	
	
	@Test
	public void testGetGridXmlString(){
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(2);
		gridXmlBuilder.nextRow("1");
		List<String> list = new ArrayList<String>();
		list.add("cell1");
		list.add("cell2");
		gridXmlBuilder.addCells(list);
		gridXmlBuilder.nextRow("2");
		list = new ArrayList<String>();
		list.add("cell3");
		list.add("cell4");
		gridXmlBuilder.addCells(list);
		assertTrue(xmlString.equals(gridXmlBuilder.getGridXmlString()));
	}
	
	@Test
	public void testGridXmlPosition(){
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(2,2);
		gridXmlBuilder.nextRow("1");
		gridXmlBuilder.addCells("cell1","cell2");
		gridXmlBuilder.nextRow("2");
		gridXmlBuilder.addCells("cell3","cell4");
		assertTrue(xmlPosition.equals(gridXmlBuilder.getGridXml()));
	}
	
	@Test
	public void testGridCell(){
		GridXmlBuilder.Cell cell = new GridXmlBuilder.Cell("value");
		cell.setValue("value2");
		assertTrue("value2".equals(cell.getValue()));
	}
	
	@Test
	public void testGridRow(){
		GridXmlBuilder.Row row = new GridXmlBuilder.Row("1");
		row.setId("1");
		assertTrue("1".equals(row.getId()));
	}
}
