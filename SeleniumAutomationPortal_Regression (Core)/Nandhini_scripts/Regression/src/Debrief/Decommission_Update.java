package Debrief;



import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;

public class Decommission_Update {
	
	 @Test
	  public void testDecommissionUpdateRequest() throws Exception {
		 
		 
         
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver(); //Chrome*/
		driver.manage().window().maximize();
		
	 
	    // Login Partner Portal
		     
		 driver.get("https://portal-qa.lexmark.com/web/partner-portal/login?");  
		 driver.findElement(By.id("_58_login")).clear();
		   driver.findElement(By.id("_58_login")).sendKeys("pripoint@gmail.com");
		 driver.findElement(By.id("_58_password")).clear();
	   driver.findElement(By.id("_58_password")).sendKeys("Lexmark13");
		   driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div[1]/div/div/div/div[2]/div/div/form/div/button")).click();
	    Thread.sleep(20000);

	    driver.findElement(By.xpath("//span[contains(.,'Services')]")).click();
	    Thread.sleep(18000);
	   
	    driver.findElement(By.xpath("//div[@id='topnavigation']/ul/li[2]/a/span")).click();
	    Thread.sleep(9000);
	  //  driver.findElement(By.xpath("//html/body/div[13]/div[2]/div[2]/button[2]")).click();
	    Thread.sleep(9000);
	//    driver.findElement(By.xpath("//html/body/div[14]/div[2]/div/div/div/div/div/div/div/div[4]/div[3]/div[1]/div[1]/div[3]/div[2]/div[1]/span")).click();
	    Thread.sleep(8000);
	    
	    
	    
	    // Enter Activity Number
	   
	   // driver.findElement(By.xpath("//html/body/div[17]/div[2]/div/div/div/div/div/div/div/div[4]/div[3]/div[2]/div[2]/div[1]/div[1]/div[1]/table/tbody/tr[3]/td[8]/div/input")).sendKeys("1-P1CU4WG");
	    Thread.sleep(8000);      
	    driver.findElement(By.xpath("//html/body/div[17]/div[2]/div/div/div/div/div/div/div/div[4]/div[3]/div[2]/div[2]/div[1]/div[1]/div[2]/table/tbody/tr[2]/td[2]/img")).click();
	    Thread.sleep(5000);
	   
	   
	    // Click Update Request
	    driver.findElement(By.linkText(">>  Update Request")).click();
	    Thread.sleep(5000);
	    
	    
	    // Projected date

	    //Get main windowHandle
	    String mainWindow = driver.getWindowHandle();
	 
	    driver.findElement(By.xpath("//html/body/div[6]/div[2]/div/div/div/div/div/div/div[2]/div/div/div[4]/div/div[2]/div[2]/form/div[5]/div/div/div[1]/table/tbody/tr[1]/td[2]/img")).click(); //click on from calendar icon
	    
	    //Switch for iframe *************
	    
	    WebElement calendar = driver.findElement(By.className("rc_calendar"));
	    driver.switchTo().frame(calendar); 
	    Thread.sleep(3000);

	    driver.findElement(By.xpath("//*[@id='rc_iframe_table']/tbody/tr[7]/td[4]")).click();  //Select date cell
	    
	    
	    try{ driver.findElement(By.xpath("//*[@id='rc_iframe_table']/tbody/tr[11]/td[1]/button")).click(); //SUBMIT
	    }catch (Exception e){}
	    
	    
	    
	    
	    
	    
	    // Start date
	    driver.findElement(By.xpath("//html/body/div[3]/div[4]/div/div/div/div/div/div[4]/div/div[2]/div[2]/form/div[5]/div/div/div[1]/table/tbody/tr[2]/td[2]/img")).click();
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//html/body/table/tbody/tr[6]/td[5]")).click();
	    driver.findElement(By.xpath("//html/body/table/tbody/tr[11]/td[1]/button")).click();
	    Thread.sleep(5000);
	    
	    
	    
	    // End Date
	    driver.findElement(By.xpath("//html/body/div[3]/div[4]/div/div/div/div/div/div[4]/div/div[2]/div[2]/form/div[5]/div/div/div[1]/table/tbody/tr[3]/td[2]/img")).click();
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//html/body/div[3]/div[4]/div/div/div/div/div/div[4]/div/div[2]/div[2]/form/div[5]/div/div/p[2]")).click();
	    driver.findElement(By.xpath("//html/body/table/tbody/tr[11]/td[1]/button")).click();
	    Thread.sleep(5000);
	    
	    
	    
	    
	    // Technician name
	    driver.findElement(By.xpath("//html/body/div[3]/div[4]/div/div/div/div/div/div[4]/div/div[2]/div[2]/form/div[5]/div/div/div[1]/table/tbody/tr[4]/td[2]/input")).clear();
	    driver.findElement(By.xpath("//html/body/div[3]/div[4]/div/div/div/div/div/div[4]/div/div[2]/div[2]/form/div[5]/div/div/div[1]/table/tbody/tr[4]/td[2]/input")).sendKeys("Joan Robinson");
	  
	    driver.findElement(By.id("serviceRequestStatusDropDown")).click();
	    new Select(driver.findElement(By.id("serviceRequestStatusDropDown"))).selectByVisibleText("Assigned");
	    driver.findElement(By.id("desc")).clear();
	    driver.findElement(By.id("desc")).sendKeys("comments");
	    Thread.sleep(5000);
	    
	    
	    // Configuration details
	    driver.findElement(By.xpath("//html/body/div[3]/div[4]/div/div/div/div/div/div[4]/div/div[2]/div[2]/form/div[5]/div/div/div[3]/div/div[1]/table/tbody/tr[1]/td[2]/img")).click();
	    driver.findElement(By.xpath("//html/body/table/tbody/tr[7]/td[6]")).click();
	    driver.findElement(By.xpath("//html/body/table/tbody/tr[11]/td[1]/button")).click();
	    driver.findElement(By.id("assetSerialNumber")).clear();
	    driver.findElement(By.id("assetSerialNumber")).sendKeys("TST200000001");
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.ipAddress")).clear();
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.ipAddress")).sendKeys("10.102.103.25");
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.ipV6")).clear();
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.ipV6")).sendKeys("ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff");
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.macAddress")).clear();
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.macAddress")).sendKeys("00-15-E9-2B-99-3C");
	    driver.findElement(By.name("userEnteredActivity.serviceRequest.asset.portNumber")).clear();
	    driver.findElement(By.name("userEnteredActivity.serviceRequest.asset.portNumber")).sendKeys("34626");
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.faxConnectedValue")).click();
	    new Select(driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.faxConnectedValue"))).selectByVisibleText("Direct");
	    driver.findElement(By.name("userEnteredActivity.serviceRequest.asset.faxPortNumber")).clear();
	    driver.findElement(By.name("userEnteredActivity.serviceRequest.asset.faxPortNumber")).sendKeys("6748678");
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.printerWorkingCondition")).click();
	    new Select(driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.printerWorkingCondition"))).selectByVisibleText("Working");
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.wiringClosestNetworkPoint")).clear();
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.wiringClosestNetworkPoint")).sendKeys("Wiring");
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.assetField2")).clear();
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.assetField2")).sendKeys("project date2");
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.assetField3")).clear();
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.assetField3")).sendKeys("project date 3");
	    Thread.sleep(5000);
	    
	    // Account Details
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.description")).clear();
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.description")).sendKeys("description");
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.deviceTag")).clear();
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.deviceTag")).sendKeys("device tag");
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.department")).clear();
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.department")).sendKeys("Lexmark");
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.assetCostCenter")).clear();
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.assetCostCenter")).sendKeys("4563474");
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.operatingSystem")).clear();
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.operatingSystem")).sendKeys("windows");
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.operatingSystemVersion")).clear();
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.operatingSystemVersion")).sendKeys("8");
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.firmware")).clear();
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.firmware")).sendKeys("Firmwire");
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.networkTopology")).clear();
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.networkTopology")).sendKeys("yes");
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.specialUsage")).clear();
	    driver.findElement(By.id("userEnteredActivity.serviceRequest.asset.specialUsage")).sendKeys("label");
	    Thread.sleep(5000);
	    
	    // Notes
	    driver.findElement(By.id("addNotes")).click();
	    Thread.sleep(8000);
	    driver.findElement(By.id("notesTextAreaPopup")).clear();
	    driver.findElement(By.id("notesTextAreaPopup")).sendKeys("important notes");
	    driver.findElement(By.cssSelector("#content_notePopUP > div.buttonContainer > button.button")).click();
	    Thread.sleep(5000);
	    
	    
	    //Click Closeout button
	    driver.findElement(By.cssSelector("div.buttonContainer > button.button.button21")).click();
	    Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#popup_cancel > span")).click();
	    
	    
	    // Change Management Request
	    driver.findElement(By.xpath("//html/body/div[6]/div[4]/div/div/div[2]/div/div/div[4]/div[3]/div[2]/div[2]/div[1]/div[1]/div[2]/table/tbody/tr[2]/td[8]/a")).click();
	    
	    
	    // Verify Technician name
	    String B = driver.findElement(By.xpath("//html/body/div[3]/div[4]/div/div/div/div/div/div[4]/div[2]/form/div/div[2]/div[5]/div/div/div[1]/table/tbody/tr[4]/td[2]")).getText();
	    System.out.println(B);
	    System.out.println("storedVars['B'].replace(\"$\",\"\")");
	    

}
}
