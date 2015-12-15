package com.lexmark.test.testcases;

import com.thoughtworks.selenium.*;


public class ServiceRequestView extends SeleneseTestCase {
	public void setUp() throws Exception {
		setUp("http://10.2.11.163:8080/", "*chrome");
	}
	public void testUntitled() throws Exception {
		
		// Step1. Login  Lexmark as hyzzemily@sina.com/Lexmark
		selenium.open("/web/guest");
		selenium.click("link=Sign In");
		selenium.waitForPageToLoad("30000");
		selenium.type("_58_login", "hyzemily@sina.com");
		selenium.type("_58_password", "lexmark");
		selenium.click("//input[@value='Sign In']");
		selenium.waitForPageToLoad("30000");
		
		//Step2. Navigate to "SERVICE REQUEST" link from Home Page. 
		selenium.click("//div[@id='navigation']/ul/li[3]/a/span");		
		pause(5000);
		verifyTrue(selenium.isTextPresent("Service Request(s) -> Open Service Requests"));
		verifyTrue(selenium.isTextPresent("Date"));
		verifyTrue(selenium.isTextPresent("Request#"));
		verifyTrue(selenium.isTextPresent("Serial Number"));
		verifyTrue(selenium.isTextPresent("Model"));
		verifyTrue(selenium.isTextPresent("Location"));
		verifyTrue(selenium.isTextPresent("Status"));
		assertTrue(selenium.isTextPresent("Records from 1 to 20 of 2500"));
		verifyTrue(selenium.isTextPresent("Advanced Search"));
		selenium.click("link=Advanced Search");
		pause(15000);
		verifyTrue(selenium.isTextPresent("Reference #"));
		selenium.click("btnCancel");
		verifyTrue(selenium.isTextPresent("Search"));
		
		//Step3. Navigate to "My Service Request" page
		selenium.click("link=My Service Request");
		pause(5000);
		assertTrue(selenium.isTextPresent("Records from 1 to 20 of 2000"));
		
		//Step4. Navigate to "Service Request History" page
		selenium.click("link=Service Request History");
		selenium.waitForPageToLoad("30000");
		pause(5000);
		verifyTrue(selenium.isTextPresent("Delivered"));
		verifyTrue(selenium.isTextPresent("Complete"));
		verifyTrue(selenium.isTextPresent("Scheduled"));
		verifyTrue(selenium.isTextPresent("Shipped"));
		assertTrue(selenium.isTextPresent("Records from 1 to 20 of 10000"));
	}
}
