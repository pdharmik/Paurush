package com.lexmark.tests;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lexmark.util.PropertiesMessageUtil;



public class PropertiesUtilTestCase {

	protected static PropertiesMessageUtil propertiesMessageUtil;
	private static final String bundleName = "com.lexmark.services.resources.countryStateProvince";


	@BeforeClass
	public static void setUp() throws Exception{
		
	}
	
	@Test
	public void testGetMessage(){
		String message = PropertiesMessageUtil.getErrorMessage("com.lexmark.services.resources.messages", "serviceSuccessMessage", null, Locale.US);
		assertNotNull(message);
		Assert.assertEquals("Processed Successfully", message);
	}
	
	@Test
	public void testGetCountries(){
		List<String> countries = new ArrayList<String>();
		countries = PropertiesMessageUtil.getLocationList(bundleName, "country",  Locale.US);
		assertNotNull(countries);
	}

}
