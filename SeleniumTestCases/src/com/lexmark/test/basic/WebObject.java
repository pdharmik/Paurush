package com.lexmark.test.basic;

public class WebObject
{
	private String objectValue = null;
	
	public WebObject(String value) {
		objectValue = value;
	}
	
	public String toString()
	{
		return objectValue;
	}
}
