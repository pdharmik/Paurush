import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;


public class SubmitClaimTest extends SeleneseTestCase {
	
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
    private String historytable="//table[contains(@class,'obj')]";

    
    
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
		
		// navigate to device selection page
		selenium.click("css=a.button_anchor > span");//click Create Claim button//div[@id='filterContainer']/div[2]/div[1]/a/sapn
		selenium.waitForPageToLoad(loadtime);	
		
		// navigate to multiple claim page
    	selenium.type("id=txtSearchValue", searchserialnumber);
    	selenium.click("//div[@id='portlet-wrapper-claimRequest_WAR_LexmarkPartnerPortal']/div[2]/div/div/div[4]/div/div[2]/div[2]/table/tbody/tr/td/a/span");
	    Thread.sleep(sleeptime);
	    selenium.click("//div[@id='gridDSDeviceList']/div[2]/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/a/span");
		selenium.waitForPageToLoad(loadtime);	
		assertTrue(selenium.isTextPresent("Open Claim Exists"));	
	    
	    // navigate to submit warranty claim page
    	selenium.click("//div[@id='portlet-wrapper-claimRequest_WAR_LexmarkPartnerPortal']/div[2]/div/div/table/tbody/tr/td/div/div/div[3]/div[2]/div/a/span");
       	selenium.waitForPageToLoad(loadtime);	
       	assertTrue(selenium.isTextPresent("Submit a Warranty Claim"));
	}
	
	
    //	SubmitClaim_TC01 - Return to Request link
	@Test
	public void testReturnToRequestLink() throws Exception {
		selenium.click("id=headerImageButton");
       	selenium.waitForPageToLoad(loadtime);	
       	assertTrue(selenium.isElementPresent("link=Expand All"));		
	}
			
	//	SubmitClaim_TC02 - Select another printer link
	@Test
	public void testSelectAnotherPrinterLink() throws Exception {
       	selenium.click("link=Select another printer");
	    Thread.sleep(sleeptime);  
	    assertTrue(selenium.isTextPresent("Do you want to select another printer?"));
	    selenium.click("css=#popup_ok > span");
       	selenium.waitForPageToLoad(loadtime);	
       	assertTrue(selenium.isTextPresent("Device Selection"));
	}
	
	//  SubmitClaim_TC03 - Choose a different account link
//	@Test
//	public void testChooseADifferentAccountLink() throws Exception{
//		selenium.click("//div[@id='partnerAddressDiv']/table/tbody/tr[2]/td/a");
//	    Thread.sleep(sleeptime);
//	    assertTrue(selenium.isTextPresent("Customer Account Information"));
//	    selenium.click("link=X");
//	    Thread.sleep(sleeptime);
//	}
	
	//  SubmitClaim_TC04 - Create new customer account link
//	@Test
//	public void testCreateNewCustomerAccountLink() throws Exception{
//		selenium.click("link=Create new customer account");
//		Thread.sleep(sleeptime);
//		assertTrue(selenium.isElementPresent("id=selectPartnerAgreement"));
//	}
	
	//  SubmitClaim_TC05 - Requests Number link
//	@Test
//	public void testRequestsNumberLink() throws Exception{
//		selenium.click("id=showHideServiceHistoryImg");
//		Thread.sleep(sleeptime);
//		String value = selenium.getTable(historytable+".1.1");
//		selenium.click("link=" + value + "");
//		Thread.sleep(sleeptime);
//		assertTrue(selenium.isTextPresent("Service Request Detail"));
//		selenium.click("class=ui-dialog-titlebar-close");
//		Thread.sleep(sleeptime);
//	}
	
	//  SubmitClaim_TC06 - Service repair complete
//	@Test
//	public void testServiceRepairComplete() throws Exception{
//		selenium.type("id=repairCompleteFlag","true");
//		Thread.sleep(sleeptime);
//		assertTrue(selenium.isTextPresent("Close Out Claim"));
//	}
	
	//  SubmitClaim_TC10,11 - Add Note, Edit button
//	@Test
//	public void testAddNoteAndEditButton() throws Exception{
//	
//		//  Add Note button
//		selenium.click("//div[@id='notes']/div[2]/div/table/tbody/tr/td/a/span");
//		Thread.sleep(sleeptime);
//		assertTrue(selenium.isTextPresent("Update Note"));
//		selenium.type("id=noteDetail", "123456");
//		selenium.click("//button[@onclick='addRow(this);']");
//		Thread.sleep(sleeptime);
//	
//		//  Edit button
//		selenium.click(" //div[@id='gridCCSNotes']/div[2]/table/tbody/tr[2]/td[5]/button");
//		Thread.sleep(sleeptime);
//		assertTrue(selenium.isTextPresent("Update Note"));	
//		selenium.typeKeys("id=noteDetail", "789");
//		selenium.click("//button[@onclick='addRow(this);']");
//		Thread.sleep(sleeptime);
//	}
	
	
	//  SubmitClaim_TC12 - Submit button
	@Test
	public void testSubmitButton() throws Exception{
		selenium.type("id=activity.serviceRequest.primaryContact.firstName", "First");
		selenium.type("id=activity.serviceRequest.primaryContact.lastName", "Last");
		selenium.type("id=activity.serviceRequest.primaryContact.workPhone", "13811110000");
		selenium.select("id=problemCode", "index=1");
		
    	Thread.sleep(5000);
    	selenium.select("id=technicianChoose", "index=1");
    	Thread.sleep(5000);
		
		selenium.click("//form[@id='createClaimRequestForm']/div/div/div[4]/a[1]/span");
		Thread.sleep(sleeptime);
	
		assertTrue(selenium.isTextPresent("You are about to submit this claim. Are you sure?"));
		selenium.click("css=#popup_ok > span");
		selenium.waitForPageToLoad(loadtime);
		assertTrue(selenium.isTextPresent("Your claim request has been submitted."));
	}
	
	//  SubmitClaim_TC13 - Cancel button
	@Test
	public void testCancelButton() throws Exception{
		selenium.click("css=#cancel_button > span");
		Thread.sleep(sleeptime);
		assertTrue(selenium.isTextPresent("All your change will be lost. Are you sure that you want to cancel submission of this claim?"));
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
