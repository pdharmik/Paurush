package com.lexmark.test.testcases;

import com.thoughtworks.selenium.*;


public class googlesearch extends SeleneseTestCase {
	public void setUp() throws Exception {
		setUp("http://www.google.com.hk/", "*chrome");
	}
	public void testUntitled() throws Exception {
		selenium.open("/");
		selenium.type("q", "perficient");
		
		selenium.click("btnG");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Perficient, Inc. | Experts in delivering business-driven"));
		selenium.click("link=Perficient, Inc. | Experts in delivering business-driven ...");
	}
}
