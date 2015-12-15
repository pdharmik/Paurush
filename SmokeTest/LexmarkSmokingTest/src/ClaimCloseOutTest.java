import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;

public class ClaimCloseOutTest extends SeleneseTestCase {
	
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
		
		
		// navigate to close out claim page
		selenium.click("id=filter2ClaimRequests");
		Thread.sleep(sleeptime);
		selenium.click("//div[@id='gridRLVRequestList']/div/table/tbody/tr[3]/td[13]/div/div/div/img");//set status detail = Claim Accepted
		selenium.click("//div[2]/div[2]");
		Thread.sleep(sleeptime);
		
		selenium.click("link=Expand All");
		Thread.sleep(sleeptime);
		selenium.click("link=>> Close Out Claim");
		selenium.waitForPageToLoad(loadtime);
		assertTrue(selenium.isTextPresent("Close Out Claim"));
    }
    
    // ClaimCloseOut-05 - Cancel button
    @Test
    public void testCancelButton() throws Exception{
    	selenium.click("//form[@id='claimDebriefForm']/div/div/div[13]/a[2]/span");
    	Thread.sleep(sleeptime);
//    	selenium.waitForPopUp("popup_content", loadtime);
    	assertTrue(selenium.isTextPresent("You are about to cancel all changes made to this Close Out form. Are you sure?"));
    	selenium.click("css=#popup_cancel > span");
    	Thread.sleep(sleeptime);
    	
    	selenium.click("//form[@id='claimDebriefForm']/div/div/div[13]/a[2]/span");
    	Thread.sleep(sleeptime);
//    	selenium.waitForPopUp("popup_content", loadtime);
    	assertTrue(selenium.isTextPresent("You are about to cancel all changes made to this Close Out form. Are you sure?"));
    	selenium.click("css=#popup_ok > span");
    	selenium.waitForPageToLoad(loadtime);
    	assertTrue(selenium.isTextPresent("Expand All"));
    }
    
    // ClaimCloseOut-06 - Submit button
    @Test
    public void testSubmitButton() throws Exception{
    	
    	// fill in all required fields
    	selenium.select("id=problemCodeSelect", "index=1");
    	selenium.select("id=technicianChoose", "index=1");
    	selenium.type("id=repairDescriptionInput", "description");
    	selenium.select("id=resolutionCodeSelect", "index=1");
    	selenium.type("id=serviceStartDate", "09/27/2011");
    	selenium.type("id=serviceEndDate", "09/28/2011");
		selenium.select("id=deviceConditionSelect", "index=1");
		
		for(int i=2; i<100; i++){
			if (selenium.isElementPresent("//div[@id='gridCCVPartDebrief']/div[2]/table/tr["+i+"]")){
				selenium.select("//div[@id='gridCCVPartDebrief']/div[2]/table/tr["+i+"]/td[5]/table/tbody/tr/td/select", "index=1");
			}else{
				break;
			}
		}
		
		
		// click submit button(cancel)
		selenium.click("//form[@id='claimDebriefForm']/div/div/div[13]/a[1]/span");
		Thread.sleep(sleeptime);
		assertTrue(selenium.isTextPresent("You are about to submit this claim. Are you sure?"));
		selenium.click("id=popup_cancel");
		Thread.sleep(sleeptime);
		
		// click submit button(OK)
		selenium.click("//form[@id='claimDebriefForm']/div/div/div[13]/a[1]/span");
		Thread.sleep(sleeptime);
		assertTrue(selenium.isTextPresent("You are about to submit this claim. Are you sure?"));
		selenium.click("css=#popup_ok > span");
		selenium.waitForPageToLoad(loadtime);
    	assertTrue(selenium.isElementPresent("link=Expand All"));
    }
    
    @After
	public void tearDown() throws Exception {
		selenium.click("link=Sign Out");
	    selenium.waitForPageToLoad(loadtime);
		selenium.stop();
	}

}
