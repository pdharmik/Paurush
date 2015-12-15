package com.lexmark.test.testcases;


import com.thoughtworks.selenium.*;


public class searchgg extends SeleneseTestCase {
	public void setUp() throws Exception {
		setUp("http://www.google.com.hk/", "*chrome");
	}
	public void testUntitled() throws Exception {
		selenium.open("/");
		selenium.type("q", "gg");
		selenium.click("btnG");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("GG Domain - Guernsey"));
		
	}
}
