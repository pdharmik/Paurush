package com.lexmark.reportScheduler.util;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import com.lexmark.util.report.PropertiesUtil;

public class PropertiesUtilTest {

	@Test
	public void testGet() {
		String value = PropertiesUtil.get("testKey");
		Assert.assertNotNull(value);
		Assert.assertEquals("testValue", value);
		
		String boxiHeader = "https://reporting-prod.lexmark.com";
		
		Assert.assertEquals( boxiHeader, PropertiesUtil.BOXI_URL_HEADER);
		
		String tempFolder = PropertiesUtil.get("tempFolder");
		Assert.assertNull(tempFolder);
	}
	
	

}
