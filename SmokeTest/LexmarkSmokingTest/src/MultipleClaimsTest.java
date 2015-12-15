import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleneseTestCase;

public class MultipleClaimsTest extends SeleneseTestCase {
	private Selenium selenium;

	private String baseurl = "http://localhost:8080/";
    private String browser = "googlechrome";
    private String loadtime = "300000";
    private int sleeptime = 30000;
    
    private String user = "_58_login";
    private String username = "iex006@yahoo.com";
    private String pw = "_58_password";
    private String password = "lexmark";
    private String partnerportaltag = "//div[@id='top-level-navigation']/ul/li[3]/a/span";
    private String searchserialnumber = "pw00001";
    
    @Before
    public void setUp() throws Exception {
		selenium = new DefaultSelenium(
				"localhost", 
				4444,
				browser, 
				baseurl);
		selenium.start();
		selenium.setTimeout(loadtime); 
		
		selenium.open("/");

		selenium.setTimeout(loadtime); 
		selenium.click("link=Sign In");   //sign in link
		selenium.waitForPageToLoad(loadtime);
		
		// sign in
		selenium.type(user, username); //user-name input box
		selenium.type(pw, password);       //password input box
		selenium.click("//input[@value='Sign In']"); //sign in button
		selenium.waitForPageToLoad(loadtime);
		
		// navigate to partner-requests tab
		selenium.click(partnerportaltag);  
		selenium.waitForPageToLoad(loadtime);	
		
		// navigate to device selection page
		selenium.click("//div[@id='filterContainer']/div[2]/div[1]/a/span");
		selenium.waitForPageToLoad(loadtime);	
		
		// navigate to multiple claim page
    	selenium.type("id=txtSearchValue", searchserialnumber);
    	selenium.click("//div[@id='portlet-wrapper-claimRequest_WAR_LexmarkPartnerPortal']/div[2]/div/div/div[4]/div/div[2]/div[2]/table/tbody/tr/td/a/span");
	    Thread.sleep(sleeptime);
	    selenium.click("//div[@id='gridDSDeviceList']/div[2]/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/a/span");
		selenium.waitForPageToLoad(loadtime);	
		assertTrue(selenium.isTextPresent("Open Claim Exists"));	
    }

	// MultipleClaims_TC01 - Return to Create Claim link
    @Test
    public void testReturnToCreateClaimLink() throws Exception{
    	selenium.click("link=<< Return to Requests");
		selenium.waitForPageToLoad(loadtime);	
		assertTrue(selenium.isElementPresent("link=Expand All"));	
//		selenium.waitForCondition(selenium.isElementPresent("class=dhx_sub_row")+"", waittime);
	    Thread.sleep(sleeptime);
    }
    
    // MultipleClaims_TC02 - Select another printer link
    @Test
    public void testSelectAnotherPrinterLink() throws Exception{
    	selenium.click("link=Select another printer");
//    	selenium.waitForCondition(selenium.isElementPresent("id=popup_content")+"", waittime);
    	Thread.sleep(sleeptime);
    	assertTrue(selenium.isElementPresent("id=popup_message"));
    	selenium.click("id=popup_ok");
		selenium.waitForPageToLoad(loadtime);	
		assertTrue(selenium.isTextPresent("Device Selection"));
    }
    
    // MultipleClaims_TC05 - Select button
    @Test
    public void testSelectButton() throws Exception{
    	selenium.click("name=btn_select");
    	selenium.waitForPageToLoad(loadtime);	
    	assertTrue(selenium.isTextPresent("Claim Information"));
    }
    
    // MultipleClaims_TC06 - Create New Claim button
    @Test
    public void testCreateNewClaimButton() throws Exception{
    	selenium.click("//div[@id='portlet-wrapper-claimRequest_WAR_LexmarkPartnerPortal']/div[2]/div/div/table/tbody/tr/td/div/div/div[3]/div[2]/div/a/span");
       	selenium.waitForPageToLoad(loadtime);	
       	assertTrue(selenium.isTextPresent("Submit a Warranty Claim"));
    }
    
	@After
	public void tearDown() throws Exception {
		selenium.click("link=Sign Out");
	    selenium.waitForPageToLoad(loadtime);
		selenium.stop();
	}
    
}
