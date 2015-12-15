import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;


public class RequestListTest extends SeleneseTestCase {
	
	private Selenium selenium;
	
    private String baseurl = "http://localhost:8080/";  
    private String user = "_58_login";
    private String password = "_58_password";
    private String browser = "googlechrome";
    private String reqListTable="//table[contains(@class,'obj')]";
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
		selenium.setTimeout(LoadTime);  //the web-page loading time
		
		selenium.open("/");

		selenium.setTimeout(LoadTime); 
		selenium.click("link=Sign In");   //sign in link
		selenium.waitForPageToLoad(LoadTime);
		
		selenium.type(user, "iex006@yahoo.com"); //**attention: the user must be both direct and indirect user
		selenium.type(password, "lexmark");       //password input box
		selenium.click("//input[@value='Sign In']"); //sign in button
		selenium.waitForPageToLoad(LoadTime);
		
		selenium.click("//div[@id='top-level-navigation']/ul/li[3]/a/span");  // partner-requests tab
		selenium.waitForPageToLoad(LoadTime);	
	}
	
	
//	RequestList_TC02 - Create Claim Button
	@Test
	public void testCreateClaimButton() {
	       assertTrue(selenium.isTextPresent("Create Claim"));
	       selenium.click("//div[@id='filterContainer']/div[2]/div[1]/a/span"); //create claim button
	       selenium.waitForPageToLoad(LoadTime);
	       assertTrue(selenium.isTextPresent("Device Selection"));	       	
	}
			
//	RequestList_TC03 - Request number link
	@Test
	public void testRequestNumberLink()  {
		selenium.click("id=filter2ClaimRequests"); //click radiobutton:Claim Requests in left-filter	
		
		String value1 = selenium.getTable(reqListTable+".2.3"); //get the value of the request number in request list grid
		selenium.click("link=" + value1 + "");   // click the claim request number
	    selenium.waitForPageToLoad(LoadTime);

	    assertTrue(selenium.isTextPresent("Claim Information"));  //verify the page is claim detail page
//		selenium.click("headerImageButton");   //Return to Requests link
//		selenium.waitForPageToLoad(LoadTime);
//		
//		selenium.check("id=filter2ServiceRequests"); //click radiobutton:service Requests in left-filter	
//		
//		String value2 = selenium.getTable(reqListTable+".2.3"); //get the value of the request number in request list grid
//		selenium.click("link=" + value2 + "");   // click the service request number
//	    selenium.waitForPageToLoad(LoadTime);
//	
//	    assertTrue(selenium.isTextPresent("Request Information"));		
	}
		
//	RequestList_TC04 - Assign to Me link
	@Test
	public void testAssignToMeLink() throws Exception {	
		selenium.click("id=filter1UnassignedRequests"); //unassigned requests
		Thread.sleep(sleeptime);
		selenium.click("id=filter2ServiceRequests"); //click radiobutton:service Requests in left-filter
		Thread.sleep(sleeptime);
		selenium.click("link=Expand All");
		Thread.sleep(sleeptime);
		selenium.click("link=Assign to Me");
		Thread.sleep(sleeptime);
		assertTrue(selenium.isTextPresent("Request successfully assigned to Technician"));							
	}
	
// RequestList_TC05 - Update Claim link
	@Test
	public void testUpdateClaimLink() throws Exception {

		selenium.click("id=filter2ClaimRequests"); //click radiobutton:Claim Requests in left-filter	
		selenium.click("id=filter2AllRequests"); //click radiobutton:all Requests in left-filter	
		Thread.sleep(sleeptime);
		selenium.click("//div[@id='gridRLVRequestList']/div/table/tbody/tr[3]/td[13]/div/div/div/img");
		selenium.click("//div[2]/div[2]");
		//selenium.type("//div[@id='gridRLVRequestList']/div[1]/table/tbody/tr[3]/td[13]/div/div/div/input[1]", "Accepted");  //set status detail = Claim Accepted
		Thread.sleep(sleeptime);
		selenium.click("link=Expand All");
		Thread.sleep(sleeptime);
		selenium.click("link=>> Update Claim");
		selenium.waitForPageToLoad(LoadTime);	
		assertTrue(selenium.isTextPresent("Customer Information"));							
	}
	
// RequestList_TC06 - Close Out Claim link
	@Test
	public void testCloseOutClaimLink() throws Exception {

		selenium.click("id=filter2ClaimRequests"); //click radiobutton:Claim Requests in left-filter	
		selenium.click("id=filter2AllRequests"); //click radiobutton:all Requests in left-filter	
		Thread.sleep(sleeptime);
		selenium.click("//div[@id='gridRLVRequestList']/div/table/tbody/tr[3]/td[13]/div/div/div/img");
		selenium.click("//div[2]/div[2]");
		//selenium.type("//div[@id='gridRLVRequestList']/div[1]/table/tbody/tr[3]/td[13]/div/div/div/input[1]", "Accepted");  //set status detail = Claim Accepted
		Thread.sleep(sleeptime);
		selenium.click("link=Expand All");
		Thread.sleep(sleeptime);
		selenium.click("link=>> Close Out Claim");
		selenium.waitForPageToLoad(LoadTime);	
		assertTrue(selenium.isTextPresent("Close Out Claim"));							
	}
	
	
// RequestList_TC07 - Update Request link
	@Test
	public void testRequestUpdateLink() throws Exception {

		selenium.click("id=filter2ServiceRequests"); //click radiobutton:service Requests in left-filter
		selenium.click("id=filter2AllRequests"); //click radiobutton:all Requests in left-filter	
		Thread.sleep(sleeptime);
		selenium.click("//div[@id='gridRLVRequestList']/div/table/tbody/tr[3]/td[13]/div/div/div/img");
		selenium.click("//div[2]/div[2]");
		//selenium.type("//div[@id='gridRLVRequestList']/div[1]/table/tbody/tr[3]/td[13]/div/div/div/input[1]", "Accepted");  //set status detail = Claim Accepted
		Thread.sleep(sleeptime);
		selenium.click("link=Expand All");
		Thread.sleep(sleeptime);
		selenium.click("link=>> Update Request");
		selenium.waitForPageToLoad(LoadTime);	
		assertTrue(selenium.isTextPresent("Request Information"));							
	}
	
	
// RequestList_TC08 - Close Out Request link
	@Test
	public void testCloseOutRequestLink() throws Exception {

		selenium.click("id=filter2ServiceRequests"); //click radiobutton:service Requests in left-filter
		selenium.click("id=filter2AllRequests"); //click radiobutton:all Requests in left-filter	
		Thread.sleep(sleeptime);
		selenium.click("//div[@id='gridRLVRequestList']/div/table/tbody/tr[3]/td[13]/div/div/div/img");
		selenium.click("//div[2]/div[2]");
		//selenium.type("//div[@id='gridRLVRequestList']/div[1]/table/tbody/tr[3]/td[13]/div/div/div/input[1]", "Accepted");  //set status detail = Claim Accepted
		Thread.sleep(sleeptime);
		selenium.click("link=Expand All");
		Thread.sleep(sleeptime);
		selenium.click("link=>> Close Out Request");
		selenium.waitForPageToLoad(LoadTime);	
		assertTrue(selenium.isTextPresent("Close Out Activity"));							
	}
	
	@After
	public void tearDown() throws Exception {
    	selenium.click("link=Sign Out");
	    selenium.waitForPageToLoad(LoadTime );
		selenium.stop();
	}

}
