import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleneseTestCase;


public class PaymentRequestsListTest extends SeleneseTestCase {
	
	private Selenium selenium;
	
    private String baseurl = "http://localhost:8080/";
    private String user = "_58_login";
    private String password = "_58_password";
    private String browser = "googlechrome";
    private String payReqListTable="//div[@id='gridRLVPaymentRequestList']/div[2]/table";
    private String payListTable="//div[@id='gridPLVPaymentListGrid']/div[2]/table";
    private String LoadTime = "300000";
    private int sleepTime = 30000;
    

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
		
		selenium.click("//div[@id='top-level-navigation']/ul/li[4]/a/span");  // partner-payment tab
		selenium.waitForPageToLoad(LoadTime);	
		
	}
		
////	PaymentRequestsList-01 - View Payments link
//	@Test
//	public void testReturnToPaymentsLink() {
//	       selenium.click("link=View Payments"); //Click View Payments link
//	       selenium.waitForPageToLoad(LoadTime);
//	       assertTrue(selenium.isTextPresent("View Requests"));	       	
//	}
			
//	PaymentRequestsList-02 - Records in the grid 
	@Test
	public void testRequestNumberLink() throws Exception {
		Thread.sleep(sleepTime);
		assertTrue(selenium.isTextPresent("Records from 1 to"));      	
	}

//	PaymentRequestsList-03 - Links on the page 
	@Test
	public void testLinkOnThePage() throws Exception {
		Thread.sleep(sleepTime);
		String value = selenium.getTable(payReqListTable+".1.1"); // click the Request Number link
		selenium.click("link="+ value + "");
		
		selenium.waitForPageToLoad(LoadTime);
		assertTrue(selenium.isTextPresent("Request Information"));//Verify the Request detail page
		selenium.click("link=<<Return to Requests");
		
		selenium.waitForPageToLoad(LoadTime);
		selenium.click("link=View Payments");
		Thread.sleep(sleepTime);
		
		//set date range from date
		selenium.type("id=localizedBeginDate", "06/01/2011");
		selenium.click("//table[@id='dateRangeFilterContainer']/tbody/tr[2]/td[2]/div/div[3]/a/span");
		Thread.sleep(sleepTime);

		String value1 = selenium.getTable(payListTable+".1.1"); // click the Payment Number link
		selenium.click("link="+ value1 + "");
		
		selenium.waitForPageToLoad(LoadTime);//Verify the Payment detail page
		assertTrue(selenium.isTextPresent("Payment Information"));
	}

////	PaymentRequestsList-04 - Filters
//	@Test
//	public void testFilters() throws Exception {
//		selenium.click("id=filter1PaidRequests");//filter Paid Requests
//		Thread.sleep(sleepTime);
//		assertTrue(selenium.isTextPresent("Records from 1 to"));
//		
//		selenium.click("id=filter1UnpaidRequests");//filter UnPaid Requests
//		Thread.sleep(sleepTime);
//		assertTrue(selenium.isTextPresent("Records from 1 to"));
//		
//		//grid filter
//		selenium.type("//div[@id='gridRLVPaymentRequestList']/div[1]/table/tbody/tr[3]/td[2]/div/input", "lexmark");
//		Thread.sleep(sleepTime);
//		assertTrue(selenium.isTextPresent("No Records Found"));
//		
//	}
	
	@After
	public void tearDown() throws Exception {
		selenium.click("link=Sign Out");
	    selenium.waitForPageToLoad(LoadTime);
		selenium.stop();
	}

}


