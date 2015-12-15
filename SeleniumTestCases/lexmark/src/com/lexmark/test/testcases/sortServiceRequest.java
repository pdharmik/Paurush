package com.lexmark.test.testcases;

import com.thoughtworks.selenium.*;
import java.lang.String;

public class sortServiceRequest extends SeleneseTestCase {
	public void setUp() throws Exception {
		setUp("http://10.2.11.163:8080/", "*chrome");
	}
	public void testUntitled() throws Exception {
		String SRrow1;
		String SRrow2;
		String SRrow3;
		String sortimg;
		int i;   //Click twice to ascend or descend SR.
		int j=0; //Column id, Date: j=0; Request#: j=1; Serial Number: j=2; Model: j=3; Location: j=4; Status: j=5.
		int k;   //Temporary value.
		selenium.open("/web/guest");
		selenium.click("link=Sign In");
		selenium.waitForPageToLoad("30000");
		selenium.type("_58_login", "hyzemily@sina.com");
		selenium.type("_58_password", "lexmark");
		selenium.click("//input[@value='Sign In']");
		selenium.waitForPageToLoad("30000");
		
		//Step1. Navigate to "SERVICE REQUEST" link from Home Page. 
		selenium.click("//div[@id='navigation']/ul/li[3]/a/span");	
		selenium.waitForPageToLoad("30000");	
		
		//Step2. Sort by Column name on Open Service Request page.
		for (j=0; j<6; j++)  
		{
			k=j+1;
			for (i=0; i<2;i++) //Click twice on every column.
		    {
		       //selenium.click("//div[@id='gridbox']/div[1]/table/tbody/tr[2]/td[2]/div");	
		       selenium.click("//div[@id='gridbox']/div[1]/table/tbody/tr[2]/td[" + k + "]/div");
		       pause(5000);	            
						
		       //SRrow1 = selenium.getTable("xpath=//div[@id='gridbox']/div[2]/table.1.1");
               SRrow1 = selenium.getTable("xpath=//div[@id='gridbox']/div[2]/table.1." + j);
		       SRrow2 = selenium.getTable("xpath=//div[@id='gridbox']/div[2]/table.2." + j);
		       SRrow3 = selenium.getTable("xpath=//div[@id='gridbox']/div[2]/table.3." + j);
		 
		       sortimg = selenium.getAttribute("//div[@id='gridbox']/div[1]/img@src");
               if (sortimg.equals("/LexmarkServicesPortal/images//gridImgs/sort_asc.gif")) 	        	               
	           {	        	   
	        	   	    	   
 		      	   verifyTrue(SRrow1.compareTo(SRrow2) <= 0);
	        	   verifyTrue(SRrow2.compareTo(SRrow3) <= 0);
	           }
	           else 
	           {	        	 
	        	   		        	   	        	   
	        	   verifyTrue(SRrow1.compareTo(SRrow2) >= 0);
	        	   verifyTrue(SRrow2.compareTo(SRrow3) >= 0);	
	           }	
		   }			
		}		
		//Step3. Sign out.  
		selenium.click("link=Sign Out");
		selenium.waitForPageToLoad("30000");
	}

}
