import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleneseTestCase;


public class PaymentListTest extends SeleneseTestCase {
	private Selenium selenium;
	
    private String baseurl = "http://localhost:8080/";
    private String user = "_58_login";
    private String password = "_58_password";
    private String browser = "googlechrome";
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
		selenium.setTimeout(LoadTime);  //the web-page loading time
		
		selenium.open("/");

		selenium.setTimeout(LoadTime);  
		selenium.click("link=Sign In");   //sign in link
		selenium.waitForPageToLoad(LoadTime);
		
		selenium.type(user, "iex006@yahoo.com"); //user-name input box
		selenium.type(password, "lexmark");       //password input box
		selenium.click("//input[@value='Sign In']"); //sign in button
		selenium.waitForPageToLoad(LoadTime);
		
		selenium.click("//div[@id='top-level-navigation']/ul/li[4]/a/span");  // partner-payment tab
		selenium.waitForPageToLoad("500000");	
		
		selenium.click("link=View Payments"); //Click View Payments link
	 	selenium.waitForPageToLoad(LoadTime);

		selenium.type("id=localizedBeginDate", "06/01/2011");//set the date range
		selenium.click("//table[@id='dateRangeFilterContainer']/tbody/tr[2]/td[2]/div/div[3]/a/span");
		Thread.sleep(sleepTime);
		
	}
		
//	PaymentRequestsList-01 - View Request link
	@Test
	public void testViewRequestLink() {
	       selenium.click("link=View Requests"); //Click View Request link
	       selenium.waitForPageToLoad(LoadTime);
	       assertTrue(selenium.isTextPresent("View Requests"));	       	
	}
			
//	PaymentRequestsList-02 - Records in the grid 
	@Test
	public void testGridRecords() throws Exception {
		Thread.sleep(sleepTime);
		assertTrue(selenium.isTextPresent("Records from 1 to"));      	
	}

//	PaymentRequestsList-03 - Links on the page 
	@Test
	public void testPaymentNumberLink() {
		String value1 = selenium.getTable(payListTable+".1.1"); // click the Payment Number link
		selenium.click("link="+ value1 + "");
		
		selenium.waitForPageToLoad(LoadTime);//Verify the Payment detail page
		assertTrue(selenium.isTextPresent("Payment Information"));
	}

//	PaymentRequestsList-04 - Filters
//	@Test
//	public void testFilters() throws Exception {
//		selenium.click("//table[@id='dateRangeFilterContainer']/tbody/tr[2]/td[2]/div/div[3]/a/span");
//		Thread.sleep(sleepTime);
//		assertTrue(selenium.isTextPresent("Records from 1 to"));  
//		
//		grid filter
//		selenium.type("//div[@id='gridPLVPaymentListGrid']/div[1]/table/tbody/tr[3]/td[2]/div/input", "lexmark");
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
