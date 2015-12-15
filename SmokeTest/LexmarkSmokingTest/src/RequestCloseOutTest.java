import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;


public class  RequestCloseOutTest extends SeleneseTestCase  {
	
	private Selenium selenium;
	
    private String baseurl = "http://localhost:8080/";
    private String user = "_58_login";
    private String password = "_58_password";
    private String browser = "googlechrome";
    private int sleeptime = 30000;
    private String LoadTime = "300000";

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium(
				"localhost", 
				4444,
				browser,   //change browser when necessary
				baseurl);	//change the URL when necessary
		selenium.start();
		selenium.setTimeout("180000");  //the web-page loading time
		
		selenium.open("/");

		selenium.setTimeout("180000");  
		selenium.click("link=Sign In");   //sign in link
		selenium.waitForPageToLoad(LoadTime);
		
		selenium.type(user, "iex006@yahoo.com"); //user-name input box
		selenium.type(password, "lexmark");       //password input box
		selenium.click("//input[@value='Sign In']"); //sign in button
		selenium.waitForPageToLoad(LoadTime);
		
		selenium.click("//div[@id='top-level-navigation']/ul/li[3]/a/span");  // partner-request tab
		selenium.waitForPageToLoad(LoadTime);	
		
		selenium.click("id=filter2ServiceRequests");//filter the accepted Service Request
		Thread.sleep(sleeptime);
		
		selenium.click("//div[@id='gridRLVRequestList']/div/table/tbody/tr[3]/td[13]/div/div/div/input");
		selenium.click("//div[2]/div[2]");
		Thread.sleep(sleeptime);
		
		selenium.type("id=localizedBeginDate", "06/01/2011");
		selenium.click("//table[@id='dateRangeFilterContainer']/tbody/tr[2]/td[2]/div/div[3]/a/span");
		Thread.sleep(sleeptime);
		
		selenium.click("link=Expand All");
		Thread.sleep(sleeptime);
		selenium.click("link=>> Close Out Request");
		selenium.waitForPageToLoad(LoadTime);
	}
	
//	RequestCloseOut-03 - Repaired Device Information
	@Test
	public void testRepairedDeviceInformationField() throws Exception {
		if(selenium.isElementPresent("//div[@id='closeOutActivity1']/div/div/table[2]/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td[2]/select"))
			assertFalse(selenium.isTextPresent("Installed Device Information"));
		else{
			assertTrue(selenium.isTextPresent("Installed Device Information"));
		}
	}	
		
//	ReuqestUpdate-06 - Cancel button
	@Test
	public void testCancelButton() throws Exception {
	       selenium.click("css=a.cancel_button_anchor > span"); //Cancel button//form[@id='requestsDebriefForm']/div/div/div[12]/a[1]/span
	       Thread.sleep(sleeptime);
	       assertTrue(selenium.isTextPresent("You are about to cancel all changes made to this Close Out form. Are you sure?"));	       	
	       selenium.click("css=#popup_ok > span");
	       selenium.waitForPageToLoad(LoadTime);
	       assertTrue(selenium.isElementPresent("id=headerImageButton"));  
	}
			
//	ReuqestUpdate-07 - Update button
	@Test
	public void testUpdateButton() throws Exception  {
		//fill in all the required fields
		selenium.select("id=technicianChoose","index=1");
    	selenium.type("id=actualStartDate", "09/27/2011");
     	selenium.type("id=actualEndDate", "09/28/2011");
		selenium.select("id=problemCodeSelect", "index=1");
		selenium.select("id=resolutionCodeSelect","index=1");
		selenium.type("id=repairDescriptionInput", "As shown in the comments.");
		selenium.select("id=addtionServiceRequiredSelect", "index=1");
		selenium.select("id=nonLexmarkSuppliesUsedSelect", "index=1");
		// selenium.select("id=deviceConditionSelect", "index=1");
		
		if(selenium.isElementPresent("id=assetListSelect")) {    // tests for the presence of the element
				     selenium.select("id=assetListSelect","index=1");        // click at is good for image buttons, but click
			} 

		// click submit button(OK)
		selenium.click("//form[@id='requestsDebriefForm']/div/div/div[12]/a/span");
		Thread.sleep(1000);
		selenium.click("css=#popup_ok > span");
		selenium.waitForPageToLoad(LoadTime);  
		assertTrue(selenium.isTextPresent("Request successfully closed out!"));
	}
	
	
	@After
	public void tearDown() throws Exception {
		selenium.click("link=Sign Out");
	    selenium.waitForPageToLoad(LoadTime);
		selenium.stop();
	}

}

