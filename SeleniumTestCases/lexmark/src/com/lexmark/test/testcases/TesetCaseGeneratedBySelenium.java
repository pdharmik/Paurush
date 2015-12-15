package com.lexmark.test.testcases;

import com.thoughtworks.selenium.SeleneseTestCase;

public class TesetCaseGeneratedBySelenium extends SeleneseTestCase
{

	public void setUp() throws Exception
	{
		setUp("http://192.168.1.113:9080/", "*chrome");
	}

	public void testUntitled() throws Exception
	{
		selenium.open("/geh-main/");
		selenium.setSpeed("500");
		
		selenium.windowMaximize();
		selenium.type("userName", "gehadmin");
		selenium.type("password", "password");
		selenium.click("//input[@value='Submit']");
		selenium.waitForPageToLoad("30000");
		selenium.click("//td[@onclick='changeMenuState()']");
		Thread.sleep(300);
		selenium.click("//div[@onclick=\"submitMenuForm('./adminDispatcher.do')\"]");
		selenium.waitForPageToLoad("30000");

		if (!selenium.isElementPresent("//input[@name='userNames' and @value='joseph.wang']"))
		{
			selenium.click("AddNewRow");
			selenium.type("//tbody[@id='entitylistbody']/tr[16]/td[3]/input", "joseph.wang");
			selenium.type("//tbody[@id='entitylistbody']/tr[16]/td[4]/input", "P@55w0rd");
			selenium.type("//tbody[@id='entitylistbody']/tr[16]/td[5]/input", "Joseph");
			selenium.type("//tbody[@id='entitylistbody']/tr[16]/td[6]/input", "Wang");
			selenium.type("//tbody[@id='entitylistbody']/tr[16]/td[7]/input", "Test");
			selenium.type("//tbody[@id='entitylistbody']/tr[16]/td[8]/input", "Tester");
			selenium.type("//tbody[@id='entitylistbody']/tr[16]/td[9]/input", "joseph.wang@perficientgdc.com.cn");
			selenium.select("//tbody[@id='entitylistbody']/tr[16]/td[10]/select", "label=helllo");
			selenium.select("//tbody[@id='entitylistbody']/tr[16]/td[11]/select", "label=Catch-all Project");
			selenium.click("//tr[16]/td[11]/select/option[2]");
			selenium.click("submit");
			verifyTrue(selenium.isElementPresent("//input[@name='userNames' and @value='joseph.wang']"));
		}
	}

}
