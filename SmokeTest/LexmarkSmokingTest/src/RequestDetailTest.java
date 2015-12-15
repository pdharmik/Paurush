import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleneseTestCase;

public class RequestDetailTest extends SeleneseTestCase{

private Selenium selenium;
	
    private String baseurl = "http://localhost:8080/";
    private String user = "_58_login";
    private String password = "_58_password";
    private String browser = "googlechrome";
    private String reqListTable="//table[contains(@class,'obj')]";
//    private String reqListheadTable="//table[contains(@class,'hdr')]";
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
		selenium.click("id=filter2ServiceRequests");
		Thread.sleep(sleeptime);
		
		selenium.click("//div[@id='gridRLVRequestList']/div/table/tbody/tr[3]/td[13]/div/div/div/input");
		selenium.click("//div[2]/div[2]");
		Thread.sleep(sleeptime);
		
		selenium.type("id=localizedBeginDate", "06/01/2011");
		selenium.click("//table[@id='dateRangeFilterContainer']/tbody/tr[2]/td[2]/div/div[3]/a/span");
		Thread.sleep(sleeptime);
		
		String valueb = selenium.getTable(reqListTable+".1.3"); // CLICK TO RequestDetail
		selenium.click("link=" + valueb + "");
		selenium.waitForPageToLoad(LoadTime);
	}
		
//	RequestDetail_TC01 - Update Request button
	@Test
	public void testUpdatRequestButton() {
	       selenium.click("//div[@id='requestInformation']/div[2]/div/div/div[1]/dl/dt[1]/a/span"); //Update Request button
	       selenium.waitForPageToLoad(LoadTime);
//	       assertTrue(selenium.isTextPresent("View Requests"));	       	
	}
			
//	RequestDetail_TC02 - Close Out Request button
	@Test
	public void testCloseOutRequestButton() {
		selenium.click("//div[@id='requestInformation']/div[2]/div/div/div[1]/dl/dt[2]/a/span");
		selenium.waitForPageToLoad(LoadTime);   
//		assertTrue(selenium.isTextPresent("View Requests"));	 
	}
 
////	PaymentDetail-05 - Print... icons
//	@Test
//	public void testPrintIcons() throws Exception {
//		selenium.click("css=img[title='Print']");
//		Thread.sleep(sleeptime);
//		assertTrue(selenium.isElementPresent("css=span"));
//	}
		
	
	@After
	public void tearDown() throws Exception {
		selenium.click("link=Sign Out");
	    selenium.waitForPageToLoad(LoadTime);
		selenium.stop();
	}

}


