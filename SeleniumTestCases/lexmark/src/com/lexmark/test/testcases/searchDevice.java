package com.lexmark.test.testcases;

import com.thoughtworks.selenium.*;


public class searchDevice extends SeleneseTestCase {
	public void setUp() throws Exception {
		setUp("http://10.2.11.163:8080/", "*chrome");
	}
	public void testUntitled() throws Exception {
		selenium.open("/web/guest");
		selenium.click("link=Sign In");
		selenium.waitForPageToLoad("30000");
		selenium.type("_58_login", "hyzemily@sina.com");
		selenium.type("_58_password", "lexmark");
		selenium.click("//input[@value='Sign In']");
		selenium.waitForPageToLoad("30000");
		
		//Step1. Navigate to "DEVICE FINDER" link from Home Page. 
		selenium.click("//div[@id='navigation']/ul/li[2]/a/span");
		
		//Step2. Click "View All" link on the left categories.
		
		selenium.waitForPageToLoad("30000");
		
		//STEP3. Click Search drop down arrow. Check the drop down list.
		verifyEquals("Serial Number,IP Address,Host Name,Asset Tag,Customer Device Tag,Product Line (Model),Location",selenium.getSelectOptions("xpath=//select[@id='ddl_search_field']"));
		
		//Step4. Select Serial Number from the drop down list, enter any Serial Number existing in the list and click Search button.
		selenium.select("ddl_search_field", "label=Serial Number"); //Search device by Serial Number
		selenium.type("txt_search_value", "79233X9999");
		selenium.click("a11");
		pause(5000);
		
		assertTrue(selenium.isTextPresent("Records from 1 to 1 of 1"));		
	    verifyEquals("79233X9999", selenium.getTable("xpath=//div[@id='device_list_container']/div[2]/table.1.4"));
	    
		//Step5. Select Asset Tag from the drop down list, enter any Asset Tag existing in the list and click Search button.
		selenium.select("ddl_search_field", "label=Asset Tag"); 
		selenium.type("txt_search_value", "234313251350");
		selenium.click("a11");
		pause(5000);
		assertTrue(selenium.isTextPresent("Records from 1 to 1 of 1"));
		verifyEquals("234313251350", selenium.getTable("xpath=//div[@id='device_list_container']/div[2]/table.1.3"));
		
		selenium.select("ddl_search_field", "label=Serial Number"); //Search device by Serial Number
		selenium.type("txt_search_value", "79233x99");
		selenium.click("a11");
		pause(5000);
		assertTrue(selenium.isTextPresent("Records from 1 to 20 of 111"));	
		
		//Step6. Sign out.  
		
		selenium.click("link=Sign Out");
		selenium.waitForPageToLoad("30000");
	}
}
