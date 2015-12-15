import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;


public class ClaimDetailTest extends SeleneseTestCase {

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
    private String listtable = "//table[contains(@class,'obj')]";

    
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
		
		// navigate to claim detail page
		selenium.click("id=filter2ClaimRequests");
		Thread.sleep(sleeptime);
		selenium.click("//div[@id='gridRLVRequestList']/div/table/tbody/tr[3]/td[13]/div/div/div/input");
		selenium.click("//div[2]/div[2]");
		Thread.sleep(sleeptime);
		String value = selenium.getTable(listtable+".1.3");
		selenium.click("link=" + value + "");
		selenium.waitForPageToLoad(loadtime);
	
    }
    
    // ClaimDetail_TC01 - Update Claim button
    @Test
    public void testUpdateClaimButton() throws Exception {
    	selenium.click("//div[@id='claimInformation']/div[2]/div/div/div[1]/a[1]/span");
    	selenium.waitForPageToLoad(loadtime);
    	assertTrue(selenium.isTextPresent("Claim Information"));
    }
    
    // ClaimDetail_TC02 - Close Out Claim button
    @Test
    public void testCloseOutClaimButton() throws Exception {
    	selenium.click("//div[@id='claimInformation']/div[2]/div/div/div[1]/a[2]/span");
    	selenium.waitForPageToLoad(loadtime);
    	assertTrue(selenium.isTextPresent("Close Out Claim"));
    }
    
    
	@After
	public void tearDown() throws Exception {
		selenium.click("link=Sign Out");
	    selenium.waitForPageToLoad(loadtime);
		selenium.stop();
	}
}
