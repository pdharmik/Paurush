import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;


public class PaymentDetailTest extends SeleneseTestCase  {
	
	private Selenium selenium;
	
    private String baseUrl = "http://localhost:8080/";
    private String user = "_58_login";
    private String password = "_58_password";
    private String browser = "googlechrome";
    private String payListTable="//div[@id='gridPLVPaymentListGrid']/div[2]/table";
    private String payDetailTable="//div[@id='gridPDVPaymentDetails']/div[2]/table";
    private String LoadTime = "300000";
    private int sleepTime = 30000;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium(
				"localhost", 
				4444,
				browser,   //change browser when necessary
				baseUrl);	//change the URL when necessary
		selenium.start();
		selenium.setTimeout("300000");  //the web-page loading time
		
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
		
		selenium.click("link=View Payments");
		selenium.waitForPageToLoad(LoadTime);	
		
		//set date range from date
		selenium.type("id=localizedBeginDate", "06/01/2011");
		selenium.click("//table[@id='dateRangeFilterContainer']/tbody/tr[2]/td[2]/div/div[3]/a/span");
		Thread.sleep(sleepTime);
		
		String value = selenium.getTable(payListTable+".1.1"); // CLICK TO PatmentDetail
		selenium.click("link=" + value + "");
		selenium.waitForPageToLoad(LoadTime);
	}
		
////	PaymentDetail-02 - Return to Payments link
//	@Test
//	public void ReturnToPaymentsLink() {
//	       selenium.isTextPresent("Return to Payments");
//	       selenium.click("link=<<Return to Payments"); //Return to Payment link
//	       selenium.waitForPageToLoad(LoadTime);
//	       selenium.isTextPresent("View Requests");	       	
//	}
			
//	PaymentDetail-03 - Request Number link
	@Test
	public void testRequestNumberLink() throws Exception {
		Thread.sleep(sleepTime);
		String value = selenium.getTable(payDetailTable+".1.1"); // select the first payment number in the grid
		selenium.click("link="+ value + "");
		selenium.waitForPageToLoad(LoadTime);   
		assertTrue(selenium.isTextPresent("Service Information"));
	}
	
	

////	PaymentDetail-01 - Print... icons
//	@Test
//	public void testPrintIcons() throws Exception {
//		selenium.click("css=img[title='Print']");
//		Thread.sleep(sleepTime);
//		assertTrue(selenium.isElementPresent("css=span"));
//		selenium.click("css=span");
//	}
		
	
	@After
	public void tearDown() throws Exception {
		selenium.click("link=Sign Out");
	    selenium.waitForPageToLoad(LoadTime);
		selenium.stop();
	}

}

