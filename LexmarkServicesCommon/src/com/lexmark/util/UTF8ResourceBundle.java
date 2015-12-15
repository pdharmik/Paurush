package com.lexmark.util;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class UTF8ResourceBundle extends ResourceBundle{
	PropertyResourceBundle bundle;
	
	public UTF8ResourceBundle(PropertyResourceBundle bundle) {
	    this.bundle = bundle;
	}

	public Enumeration getKeys() {
	  return bundle.getKeys();
	}

    protected Object handleGetObject(String key) {
      String value = (String)bundle.handleGetObject(key);
      if(value != null){
          try {
              return new String (value.getBytes("ISO-8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
              return "Encoding_Unsupported";
            }     	  
      }
      return value;
    }

	
}
