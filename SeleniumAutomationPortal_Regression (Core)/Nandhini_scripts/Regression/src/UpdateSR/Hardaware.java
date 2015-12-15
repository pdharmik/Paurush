package UpdateSR;



import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class Hardaware {

	
	
	@Test
	  public void testUpdateHardwareRequest() throws Exception {
		  System.out.println("starting Update Change SR");
	
					
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
	         Thread.sleep(18000);
	         System.out.println("LogIn successful");
	         
	         
	    // Request tab
	         driver.findElement(By.xpath("//.//*[@id='topnavigation']/ul/li[5]/a/span")).click();
	         Thread.sleep(8000);
	    // Update Hardware Request
	    driver.findElement(By.linkText("Hardware Requests")).click();
	    Thread.sleep(10000);
	    driver.findElement(By.linkText("1-54158052071")).click();
	    Thread.sleep(8000); 
	    
	    // Haredware request detailes screen
	    driver.findElement(By.id("updateButton")).click();
	    Thread.sleep(8000);
	    
	    // Inquiry Page
	    driver.findElement(By.id("custReferenceId")).clear();
	    driver.findElement(By.id("custReferenceId")).sendKeys("462356");
	    driver.findElement(By.id("costCenter")).clear();
	    driver.findElement(By.id("costCenter")).sendKeys("3464");
	    driver.findElement(By.id("addtnlDescription")).clear();
	    driver.findElement(By.id("addtnlDescription")).sendKeys("description");
	    driver.findElement(By.id("notesOrComments")).clear();
	    driver.findElement(By.id("notesOrComments")).sendKeys("important notes");
	    driver.findElement(By.cssSelector("button.button.buttonOK")).click();
	    
	    
	 // Review Page
        driver.findElement(By.cssSelector("button.button")).click();
        
     // Inquiry_Confirmation Page
        String Request_Number = driver.findElement(By.xpath("//span[@id='reqNumber']")).getText();
        
        System.out.println(Request_Number);
        System.out.println("storedVars['Request_Number'].replace(\"$\",\"\")");
        
        
        // Verify SR number
  //      driver.findElement(By.xpath("//html/body/div[3]/div[3]/div[1]/div/div/div/div/div/div[2]/div[1]/div/div[11]/ul/li[1]/a/span")).click();
       Thread.sleep(8000);
       driver.findElement(By.xpath("//*[@id='allRequestHistory']/div[1]/table/tbody/tr[3]/td[1]/div/input")).sendKeys(Request_Number);
        Thread.sleep(8000);
        

          
          //Verify details
          driver.findElement(By.xpath("//html/body/div[3]/div[2]/div/div/div/div/div/div/div/div[13]/div/div[8]/div/div[2]/div/div[1]/div[2]/table/tbody/tr[2]/td[1]/a")).click();
          Thread.sleep(7000);
          
          
        
          assertEquals("Update Request", driver.findElement(By.id("area")).getText());
          assertEquals("HW Request", driver.findElement(By.id("subArea")).getText());
          assertEquals("important notes", driver.findElement(By.id("notes")).getText());
          
       //   LogOff
    		driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
    		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    		Thread.sleep(15000);

          //Open siebel application 		
           		
           	  driver.get("https://siebelqa.lexmark.com/service_oui_enu/start.swe?");  
         	   driver.findElement(By.id("s_swepi_1")).clear();
         	    driver.findElement(By.id("s_swepi_1")).sendKeys("alohidasan");
         	    driver.findElement(By.id("s_swepi_2")).clear();
         	    driver.findElement(By.id("s_swepi_2")).sendKeys("Lexmark@123");
         	    driver.findElement(By.id("s_swepi_22")).click();
         	    System.out.println("LogIn successful");
         	    Thread.sleep(25000); 
             
             //Navigating site map
             driver.findElement(By.cssSelector("#tb_2 > img.ToolbarButtonOn")).click();
             Thread.sleep(5000);
             driver.findElement(By.id("s_sma167")).click();
             driver.findElement(By.xpath("(//a[contains(text(),'MPS Service Requests')])[4]")).click();
             Thread.sleep(5000);
             driver.findElement(By.name("s_vis_div")).click();
             Thread.sleep(3000);
             new Select(driver.findElement(By.name("s_vis_div"))).selectByVisibleText("All Service Requests across Organizations");
             driver.findElement(By.xpath("//div[@id='s_vis_div']/select/option[3]")).click();
             Thread.sleep(5000);
             driver.findElement(By.id("s_1_1_13_0_Ctrl")).click();
             Thread.sleep(2000);
             driver.findElement(By.id("1SR_Number")).click();
             driver.findElement(By.id("1_SR_Number")).clear();
             driver.findElement(By.id("1_SR_Number")).sendKeys(Request_Number);
             Thread.sleep(3000);	 
             driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div/div[6]/div/div[1]/div/div[1]/div/div/form/div[1]/span/table/tbody/tr/td[6]/button")).click();
             Thread.sleep(8000);	
             
             assertEquals("Update Request", driver.findElement(By.name("s_2_1_174_0")).getAttribute("value"));
             assertEquals("HW Request", driver.findElement(By.name("s_2_1_175_0")).getAttribute("value"));
             assertEquals("Kuehne + Nagel Limited", driver.findElement(By.name("s_2_1_143_0")).getAttribute("value"));
             
             //LogOff  
             driver.findElement(By.cssSelector("span.ui-button-text")).click();
             driver.findElement(By.xpath("//html/body/div[1]/div/div[1]/div/div[2]/span/li[1]/ul/li[19]/a")).click();	
            	  Thread.sleep(8000);
             
	  }
}
