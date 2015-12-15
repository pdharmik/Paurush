package com.lexmark.test.testcases;

import com.lexmark.test.basic.WebObject;

public class TestMainData
{
/*
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
*/
	
	public static WebObject userName = new WebObject("userName");
	public static WebObject password = new WebObject("password");
	public static WebObject loginButton = new WebObject("//input[@value='Submit']");
	public static WebObject menuAdmin = new WebObject("//td[@onclick='changeMenuState()']");
	public static WebObject expandButton = new WebObject("//div[@onclick=\"submitMenuForm('./adminDispatcher.do')\"]");
	public static WebObject josephWang = new WebObject("//input[@name='userNames' and @value='joseph.wang']");
	public static WebObject addButton = new WebObject("AddNewRow");

	public static WebObject dataUsername = new WebObject("//tbody[@id='entitylistbody']/tr[16]/td[3]/input");
	public static WebObject dataPassword = new WebObject("//tbody[@id='entitylistbody']/tr[16]/td[4]/input");
	public static WebObject dataFirstname = new WebObject("//tbody[@id='entitylistbody']/tr[16]/td[5]/input");
	public static WebObject dataLastname = new WebObject("//tbody[@id='entitylistbody']/tr[16]/td[6]/input");
	public static WebObject dataDep = new WebObject("//tbody[@id='entitylistbody']/tr[16]/td[7]/input");
	public static WebObject dataPos = new WebObject("//tbody[@id='entitylistbody']/tr[16]/td[8]/input");
	public static WebObject dataEmail = new WebObject("//tbody[@id='entitylistbody']/tr[16]/td[9]/input");
	public static WebObject dataComponent = new WebObject("//tbody[@id='entitylistbody']/tr[16]/td[10]/select");
	public static WebObject dataProject = new WebObject("//tbody[@id='entitylistbody']/tr[16]/td[11]/select");
	
	public static WebObject createButton = new WebObject("submit");
}
