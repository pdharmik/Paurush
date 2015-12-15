package com.lexmark.test.testcases;

import com.thoughtworks.selenium.*;


public class Login extends SeleneseTestCase {
	public void setUp() throws Exception {
		setUp("http://10.2.11.163:8080", "*chrome");
	}
	public void testUntitled() throws Exception {
		selenium.open("/web/guest/home");
		assertTrue(selenium.isTextPresent("Sign In"));
		selenium.click("link=Sign In");
		selenium.waitForPageToLoad("30000");
		selenium.type("_58_login", "hyzemily@sina.com");
		selenium.type("_58_password", "lexmark");
		selenium.click("//input[@value='Sign In']");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Welcome emily hu!")); //Login successfully
		
		selenium.click("//div[@id='navigation']/ul/li[2]/a/span");//Navigate to Device Finder page
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("View All"));
		
		selenium.click("//div[@id='topnavigation']/ul/li[3]/a/span");//Navigate to Service Request page
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Service Request(s) -> Open Service Requests "));
		
		selenium.click("link=Sign Out");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Sign In"));
		
	}
}