package com.lexmark.test.testcases;

import com.thoughtworks.selenium.*;


public class filterDevicelist extends SeleneseTestCase {
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
		
		//Step3. Filter by Device column.
		
		selenium.type("//div[@id='device_list_container']/div[1]/table/tbody/tr[3]/td[3]/div/input", "deviceName0");
		selenium.keyDown("//div[@id='device_list_container']/div[1]/table/tbody/tr[3]/td[3]/div/input", "0");
		pause(5000);
		
		assertTrue(selenium.isTextPresent("Records from 1 to 1 of 1"));		
        verifyEquals("deviceName0", selenium.getTable("xpath=//div[@id='device_list_container']/div[2]/table.1.2"));
    
        //Step4. Filter by Device column, include * and non-sensitive.  
    
        selenium.type("//div[@id='device_list_container']/div[1]/table/tbody/tr[3]/td[3]/div/input", "name");
		selenium.keyDown("//div[@id='device_list_container']/div[1]/table/tbody/tr[3]/td[3]/div/input", "0");
		pause(5000);
		
		assertTrue(selenium.isTextPresent("Records from 1 to 20 of 10000"));		
       	
		//Step5. Filter by Device column with invalid data.  
		
        selenium.type("//div[@id='device_list_container']/div[1]/table/tbody/tr[3]/td[3]/div/input", "@");
		selenium.keyDown("//div[@id='device_list_container']/div[1]/table/tbody/tr[3]/td[3]/div/input", "0");
		pause(5000);
		
		assertTrue(selenium.isTextPresent("No Records Found"));	
		
		//Step6. Sign out.  
		selenium.click("link=Sign Out");
		selenium.waitForPageToLoad("30000");
	}
}
