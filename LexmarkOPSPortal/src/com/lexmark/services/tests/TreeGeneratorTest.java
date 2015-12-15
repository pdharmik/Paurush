package com.lexmark.services.tests;

import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lexmark.domain.CHLNode;
import com.lexmark.domain.GenericAddress;
import com.lexmark.service.impl.mock.DomainMockDataGenerator;
import com.lexmark.services.util.TreeGenerator;

public class TreeGeneratorTest {
	private static Logger logger = LogManager.getLogger(TreeGeneratorTest.class);

	private TreeGenerator target;
	

	@Before
	public void setUp() throws Exception {
		target = new TreeGenerator(Locale.US);
	}

	@After
	public void tearDown() throws Exception {
		target = null;
	}

	@Test
	public void testGenerateReportingHierarchyXML() {
		List<CHLNode> testNodes = DomainMockDataGenerator.getCHLNodeList(null);
		logger.info(target.generateReportingHierarchyXML(testNodes)); 
	}
	
	@Test
	public void testGenerateDeviceLocationXML() {
		List<GenericAddress> addresses = DomainMockDataGenerator.getGenericAddressList();
		logger.info(target.generateDeviceLocationTreeXML(addresses));
	}
	
	@Test
	public void testEmptyGenerateDeviceLocationXML() {
		logger.info(target.generateDeviceLocationTreeXML(null));
	}
}
