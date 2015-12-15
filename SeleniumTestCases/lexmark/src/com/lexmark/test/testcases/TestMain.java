package com.lexmark.test.testcases;

import com.lexmark.test.basic.AutomationDriver;
import com.lexmark.test.basic.SeleniumDriver;

public class TestMain
{
	public static void main(String[] args) throws InterruptedException {
		AutomationDriver driver = new SeleniumDriver("http://192.168.1.131:8080/", "*chrome");
		
		driver.open("web/guest");
		
		
		driver.setSpeed(500);
		driver.windowMaximize();

		driver.type(TestMainData.userName, "gehadmin");
		driver.type(TestMainData.password, "password");
		driver.click(TestMainData.loginButton);
		driver.waitForLoad(30000);
		driver.click(TestMainData.menuAdmin);
		Thread.sleep(300);
		driver.click(TestMainData.expandButton);
		driver.waitForLoad(30000);

		if (!driver.hasObject(TestMainData.josephWang))
		{
			addUser(driver);
		}
	}

	private static void addUser(AutomationDriver driver)
	{
		driver.click(TestMainData.addButton);
		driver.type(TestMainData.dataUsername, "joseph.wang");
		driver.type(TestMainData.dataPassword, "P@55w0rd");
		driver.type(TestMainData.dataFirstname, "Joseph");
		driver.type(TestMainData.dataLastname, "Wang");
		driver.type(TestMainData.dataDep, "Test");
		driver.type(TestMainData.dataPos, "Tester");
		driver.type(TestMainData.dataEmail, "joseph.wang@perficientgdc.com.cn");
		driver.select(TestMainData.dataComponent, "label=helllo");
		driver.select(TestMainData.dataProject, "label=Catch-all Project");
		driver.click(TestMainData.createButton);
	}
}
