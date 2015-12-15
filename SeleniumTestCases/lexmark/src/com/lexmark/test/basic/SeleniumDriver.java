package com.lexmark.test.basic;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class SeleniumDriver extends AutomationDriverImpl
{
	private Selenium selenium;
	
	public SeleniumDriver(String baseURL, String browser)
	{
		super(baseURL, browser);
		selenium = new DefaultSelenium("localhost", 4444, browser, baseURL);
		selenium.start();
	}

	@Override
	public void click(WebObject object)
	{
		selenium.click(object.toString());
	}

	@Override
	public boolean hasObject(WebObject object)
	{
		return selenium.isElementPresent(object.toString());
	}

	@Override
	public boolean hasText(String value)
	{
		return selenium.isTextPresent(value);
	}

	@Override
	public void open(String url)
	{
		selenium.open(url);
	}

	@Override
	public void select(WebObject object, String value)
	{
		selenium.select(object.toString(), value);
	}

	@Override
	public void setSpeed(Integer speed)
	{
		selenium.setSpeed(speed.toString());
	}

	@Override
	public void type(WebObject object, String value)
	{
		selenium.type(object.toString(), value);
	}

	@Override
	public void waitForLoad(Integer timeout)
	{
		selenium.waitForPageToLoad(timeout.toString());
	}

	@Override
	public void windowMaximize()
	{
		selenium.windowMaximize();
	}

}
