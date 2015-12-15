package Release_154;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Devicefinder {
	
	@Test
	  public void testUntitled() throws Exception {
		
		
	
	//Open Browser	
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver(); //Chrome*/
		driver.manage().window().maximize();
		
		
// Login Portal
		 driver.get("https://portal-qa.lexmark.com/group/global-services");
		    driver.findElement(By.id("_58_login")).clear();
		    driver.findElement(By.id("_58_login")).sendKeys("kn@qa.com");
		    driver.findElement(By.id("_58_password")).clear();
		    driver.findElement(By.id("_58_password")).sendKeys("Lexmark15");
		    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		    Thread.sleep(8000);
            System.out.println("LogIn successful");
	
	//Click Device finder tab
	 driver.findElement(By.xpath("//div[@id='topnavigation']/ul/li[2]/a/span")).click();
	  Thread.sleep(8000);
      System.out.println("Navigating Device Finder");
      
      //Search serial number
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/div[3]/div/div[10]/div[2]/div[1]/div[1]/div[1]/table/tbody/tr[3]/td[2]/div/input")).clear();
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/div[3]/div/div[10]/div[2]/div[1]/div[1]/div[1]/table/tbody/tr[3]/td[2]/div/input")).sendKeys("2352422");
	    Thread.sleep(8000);	    
	    driver.findElement(By.linkText("2352422")).click();
	    Thread.sleep(8000);	 
	    
	 
	    //Create a new SR
	    driver.findElement(By.id("linkSR")).click();
	    Thread.sleep(8000);	
	    driver.findElement(By.linkText("Asset")).click();
	    Thread.sleep(8000);	
	    driver.findElement(By.id("button1-3D0ROA3")).click();
	    Thread.sleep(8000);	
	    driver.findElement(By.cssSelector("input[type=\"text\"]")).clear();
	    driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys("2352422");
	    Thread.sleep(8000);	
	    
	    
	    //Create a Decommission SR
	    driver.findElement(By.xpath("//img[@onclick=\"javascript:manageAssetRequestService('decommissionAssetRequest','1-9QDL-10');\"]")).click();
	    Thread.sleep(8000);	
	 // Request detail page
	    driver.findElement(By.id("custReferenceId")).clear();
	    driver.findElement(By.id("custReferenceId")).sendKeys("346326");
	    driver.findElement(By.id("costCenter")).clear();
	    driver.findElement(By.id("costCenter")).sendKeys("34636");
	    driver.findElement(By.id("addtnlDescription")).clear();
	    driver.findElement(By.id("addtnlDescription")).sendKeys("dfgdsfhg");
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div/div/div/div/div/div[11]/form/div/div[4]/div/div/ul/li/span/span[1]")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.linkText("Add Notes & Attachments")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.id("attachmentDescription")).clear();
	    driver.findElement(By.id("attachmentDescription")).sendKeys("dsgfsdg");
	    driver.findElement(By.id("btnContinue")).click();
	    
	    
	    // Review page
	    driver.findElement(By.cssSelector("button.button")).click();
	    
	    
	    // Inquiry_Confirmation Page
	    String Request_Number = driver.findElement(By.xpath("//span[@id='reqNumber']")).getText();
	    Thread.sleep(8000);
	    System.out.println(Request_Number);
	    System.out.println("storedVars['Request_Number'].replace(\"$\",\"\")");
	    
	    
	    // Verify SR number            
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/ul/li[1]/a/span")).click();
	    Thread.sleep(4000);
	    driver.findElement(By.xpath("//*[@id='allRequestHistory']/div[1]/table/tbody/tr[3]/td[1]/div/input")).sendKeys(Request_Number);
	    Thread.sleep(8000);
	    
	    
	   
	 
	    
}
	    }
	    
	
	    
