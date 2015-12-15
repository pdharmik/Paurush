package com.lexmark.util;

import java.io.IOException;
import java.util.Properties;


public class PropsFileLoadUtility {

	public Properties getProperty(String path) //throws MPSCheckedException 
	{
		Properties prop = new Properties();
		try {
			prop.load(getClass().getResourceAsStream("../resources/" + path));
			
		} catch (IOException ioe) {
			//throw new MPSProcessException("Error getting property file", ioe);
		}
		return prop;
	}
	
	public static Properties getConfigurationFile(String path) //throws MPSCheckedException 
	{
		Properties prop = new Properties();
		try {
			prop.load(PropsFileLoadUtility.class.getResourceAsStream(path));
			
		} catch (IOException ioe) {
			//throw new MPSProcessException("Error getting property file", ioe);
		}
		return prop;
	}
}
