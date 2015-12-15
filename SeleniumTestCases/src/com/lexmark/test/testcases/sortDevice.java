package com.lexmark.test.testcases;

import com.thoughtworks.selenium.*;
import java.lang.String;


public class sortDevice extends SeleneseTestCase {
	public void setUp() throws Exception {
		setUp("http://10.2.11.163:8080/", "*chrome");
	}
	public void testUntitled() throws Exception {
		String SRrow1;
		String SRrow2;
		String SRrow3;
		String sortimg;
		int i;   //Click twice to ascend or descend SR.
		int j=2; //Column id, Device: j=2; Asset Tag: j=3; Serial Number: j=4; IP Address: j=5; Location: j=6; Model: j=7.
		int k;   //Temporary value.
		selenium.open("/web/guest");
		selenium.click("link=Sign In");
		selenium.waitForPageToLoad("30000");
		selenium.type("_58_login", "hyzemily@sina.com");
		selenium.type("_58_password", "lexmark");
		selenium.click("//input[@value='Sign In']");
		selenium.waitForPageToLoad("30000");
		
		//Step1. Navigate to "DEVICE FINDER" link from Home Page. 
		selenium.click("//div[@id='navigation']/ul/li[2]/a/span");
		
		//Step2. Click "View All" link on the left categories.
		
		selenium.waitForPageToLoad("30000");	
		
		//Step3. Sort by Column name on View All Device list page from Device column.
		for (j=2; j<8; j++)
		{
			k=j+1;
			for (i=0; i<2;i++) //Click twice on every column.
		    {
		       //selenium.click("//div[@id='device_list_container']/div[1]/table/tbody/tr[2]/td[3]/div");
		       selenium.click("//div[@id='device_list_container']/div[1]/table/tbody/tr[2]/td[" + k + "]/div");
		       sortimg = selenium.getAttribute("//div[@id='device_list_container']/div[1]/img@src");
               
               pause(5000);
						
		       //SRrow1 = selenium.getTable("xpath=//div[@id='device_list_container']/div[2]/table.1.2");
               SRrow1 = selenium.getTable("xpath=//div[@id='device_list_container']/div[2]/table.1." + j);
		       SRrow2 = selenium.getTable("xpath=//div[@id='device_list_container']/div[2]/table.2." + j);
		       SRrow3 = selenium.getTable("xpath=//div[@id='device_list_container']/div[2]/table.3." + j);
		 
		       
	           
		       
        
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
	
		//Step4. Sign out.  
		selenium.click("link=Sign Out");
		selenium.waitForPageToLoad("30000");
	}

}
