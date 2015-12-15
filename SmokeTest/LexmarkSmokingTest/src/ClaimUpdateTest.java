import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;


public class ClaimUpdateTest extends SeleneseTestCase {
	
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

		// sign in
		selenium.setTimeout(loadtime);  
		selenium.click("link=Sign In");    
		selenium.waitForPageToLoad(loadtime);
		
		selenium.type(user, username);
		selenium.type(pw, password);       
		selenium.click("//input[@value='Sign In']"); 
		selenium.waitForPageToLoad(loadtime);
		
		// navigate to partner-requests tab
		selenium.click(partnerportaltag);  
		selenium.waitForPageToLoad(loadtime);	
		
		// navigate to claim update page
		selenium.click("id=filter2ClaimRequests");
		Thread.sleep(sleeptime);
		//set status detail = Claim Accepted
		selenium.click("//div[@id='gridRLVRequestList']/div/table/tbody/tr[3]/td[13]/div/div/div/img");
		selenium.click("//div[2]/div[2]");
		Thread.sleep(sleeptime);

		selenium.click("link=Expand All");
		Thread.sleep(sleeptime);
		selenium.click("link=>> Update Claim");
		selenium.waitForPageToLoad(loadtime);
		assertTrue(selenium.isTextPresent("Claim Information"));
    }
    
//    // ClaimUpdate-04 - New & Update Primary Contact link
//    @Test
//    public void testNewUpdatePrimaryContactLink() throws Exception{
//    	
//    	// test New Primary Contact link
//    	selenium.click("link=New Primary Contact");
//    	Thread.sleep(sleeptime);
//    	assertTrue(selenium.isTextPresent("New Primary Contact"));
//    	selenium.click("//button[@onclick='popupCloseFun(this);']");
//    	Thread.sleep(sleeptime);
//    	
//    	// test Update Primary Contact link
//    	selenium.click("link=Update Primary Contact");
//    	Thread.sleep(sleeptime);
//    	assertTrue(selenium.isTextPresent("Update Primary Contact"));
//    	selenium.click("//button[@onclick='popupCloseFun(this);']");
//    	Thread.sleep(sleeptime);
//    }
    
    // ClaimUpdate-07 - Update Button
    @Test
    public void testUpdateButton() throws Exception{
    	
    	Thread.sleep(5000);
    	selenium.select("id=technicianChoose", "index=1");
    	Thread.sleep(5000);
    	
    	selenium.click("css=div > a.button_anchor > span");//form[@id='claimUpdateForm']/div/div/table[4]/tbody/tr/td/div/a[1]/span
    	Thread.sleep(sleeptime);
    	assertTrue(selenium.isTextPresent("You are about to submit the claim update. Are you sure?"));
    	selenium.click("id=popup_cancel");
    	Thread.sleep(sleeptime);
    	
    	selenium.click("css=div > a.button_anchor > span");//form[@id='claimUpdateForm']/div/div/table[4]/tbody/tr/td/div/a[1]/span
    	Thread.sleep(sleeptime);
		assertTrue(selenium.isTextPresent("You are about to submit the claim update. Are you sure?"));
    	selenium.click("id=popup_ok");
    	selenium.waitForPageToLoad(loadtime);
    	assertTrue(selenium.isElementPresent("link=Expand All"));
    }
    
    // ClaimUpdate-06 - Cancel button
    @Test
    public void testCancelButton() throws Exception{
    	selenium.click("css=a.cancel_button_anchor > span"); //form[@id='claimUpdateForm']/div/div/table[4]/tbody/tr/td/div/a[2]/span
    	Thread.sleep(sleeptime);
    	assertTrue(selenium.isTextPresent("Are you sure that you want to cancel update of this claim?"));
    	selenium.click("id=popup_cancel");
    	Thread.sleep(sleeptime);
    	
    	selenium.click("css=a.cancel_button_anchor > span"); //form[@id='claimUpdateForm']/div/div/table[4]/tbody/tr/td/div/a[2]/span
    	Thread.sleep(sleeptime);
    	assertTrue(selenium.isTextPresent("Are you sure that you want to cancel update of this claim?"));
    	selenium.click("id=popup_ok");
    	selenium.waitForPageToLoad(loadtime);
    	assertTrue(selenium.isElementPresent("link=Expand All"));
//    	assertTrue(selenium.isTextPresent(""));
    }
    
	@After
	public void tearDown() throws Exception {
		selenium.click("link=Sign Out");
	    selenium.waitForPageToLoad(loadtime);
		selenium.stop();
	}
}
