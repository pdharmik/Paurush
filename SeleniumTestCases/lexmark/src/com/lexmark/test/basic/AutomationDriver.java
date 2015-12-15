package com.lexmark.test.basic;

public interface AutomationDriver
{
	public void open(String url);
	public void setSpeed(Integer speed);
	public void windowMaximize();
	public void type(WebObject object, String value);
	public void click(WebObject object);
	public void select(WebObject object, String value);
	public boolean hasText(String value);
	public boolean hasObject(WebObject object);
	public void waitForLoad(Integer timeout);
}
