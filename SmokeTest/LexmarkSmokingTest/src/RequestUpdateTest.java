import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;


public class RequestUpdateTest extends SeleneseTestCase  {
	
	private Selenium selenium;
	
    private String baseurl = "http://localhost:8080/";
    private String user = "_58_login";
    private String password = "_58_password";
    private String browser = "googlechrome";
    private String LoadTime = "300000";
    private int sleeptime = 30000;

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
		selenium.click("link=>> Update Request");
		selenium.waitForPageToLoad(LoadTime);
	}
		
//	ReuqestUpdate-06 - Cancel button
	@Test
	public void testCancelButton() throws Exception {
	       selenium.click("//form[@id='requestDetailUpdateForm']/div/div/a[2]/span"); //Cancel button
	       Thread.sleep(sleeptime);
	       assertTrue(selenium.isTextPresent("All your change will be lost. Are you sure that you want to cancel update of this request?"));	       	
	       selenium.click("css=#popup_ok > span");
	       selenium.waitForPageToLoad(LoadTime);
	       assertTrue(selenium.isElementPresent("id=headerImageButton"));
	      
	}
			
//	ReuqestUpdate-07 - Update button
	@Test
	public void testRequestUpdateButton() throws Exception  {
		selenium.select("id=technicianChoose","index=1");
		selenium.click("//form[@id='requestDetailUpdateForm']/div/div/a[1]/span");
		Thread.sleep(sleeptime);
		selenium.click("css=#popup_ok > span");
		selenium.waitForPageToLoad("500000");  
	    assertTrue(selenium.isTextPresent("Request successfully updated."));
	}
	
	
	@After
	public void tearDown() throws Exception {
		selenium.click("link=Sign Out");
	    selenium.waitForPageToLoad(LoadTime);
		selenium.stop();
	}

}

