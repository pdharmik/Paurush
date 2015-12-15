package com.lexmark.test.testcases;

import com.thoughtworks.selenium.*;


public class deviceDetailPage extends SeleneseTestCase {
	public void setUp() throws Exception {
		setUp("http://10.2.11.163:8080/", "*chrome");
	}
	public void testUntitled() throws Exception {
		String devicename;
		String assettag;
		String serialnumber;
		String ipaddress;
		String location;
		String model;
		selenium.open("/web/guest");
		selenium.click("link=Sign In");
		selenium.waitForPageToLoad("30000");
		selenium.type("_58_login", "hyzemily@sina.com");
		selenium.type("_58_password", "lexmark");
		selenium.click("//input[@value='Sign In']");
		selenium.waitForPageToLoad("30000");
		
		//Step1. Navigate to "DEVICE FINDER" link from Home Page. 
		selenium.click("//div[@id='navigation']/ul/li[2]/a/span");
		selenium.waitForPageToLoad("30000");	
		
		//Step2. Click "View All" link on the left categories.		
			
        //Get the values of the first row on the list.
		devicename = selenium.getTable("xpath=//div[@id='device_list_container']/div[2]/table.1.2");
		assettag = selenium.getTable("xpath=//div[@id='device_list_container']/div[2]/table.1.3");
		serialnumber = selenium.getTable("xpath=//div[@id='device_list_container']/div[2]/table.1.4");
		ipaddress = selenium.getTable("xpath=//div[@id='device_list_container']/div[2]/table.1.5");
		location = selenium.getTable("xpath=//div[@id='device_list_container']/div[2]/table.1.6");
		model = selenium.getTable("xpath=//div[@id='device_list_container']/div[2]/table.1.7");
		
		//Step3. Click on the device name of the first row.
		//selenium.click("link=deviceName0");
		selenium.click("//div[@id='device_list_container']/div[2]/table/tbody/tr[2]/td[3]/a");
		selenium.waitForPageToLoad("30000");		
		
		//Step4. Verify the detail infomation on the page are the same as them on the list.
	    //verifyEquals(devicename, selenium.getTable("xpath=//div[@id='deviceDetail']/table.0.0"));
		verifyEquals(assettag, selenium.getTable("xpath=//div[@id='deviceDetail']/table/tbody/tr/td[3]/table.4.1"));
		verifyEquals(serialnumber, selenium.getTable("xpath=//div[@id='deviceDetail']/table/tbody/tr/td[3]/table.1.1"));
		verifyEquals(ipaddress, selenium.getTable("xpath=//div[@id='deviceDetail']/table/tbody/tr/td[3]/table.2.1"));
		verifyEquals(location, selenium.getTable("xpath=//div[@id='deviceDetail']/table/tbody/tr/td[3]/table.3.1"));
        verifyEquals(model, selenium.getTable("xpath=//div[@id='deviceDetail']/table.0.0"));
    
		//Step5. Sign out.  
		selenium.click("link=Sign Out");
		selenium.waitForPageToLoad("30000");
	}
}
