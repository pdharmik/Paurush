package Debrief;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class seveneight {
	
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

	    driver.findElement(By.xpath("//span[contains(.,'Reports')]")).click();
	    Thread.sleep(8000);
	    System.out.println("Selct invoice tab");
	    
	    
	    try {
	    	Assert.assertEquals("Select An Account", driver.findElement(By.xpath("//span[contains(.,'Select An Account')]")).getText());
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='accountListGrid']/div[1]/table/tbody/tr[3]/td[4]/input")).sendKeys("LKSCanada");
			Thread.sleep(2000);
	    	driver.findElement(By.xpath("//*[@name='btn_select']")).click();
	    	//Select an account
	    	Thread.sleep(2000);

	    } catch(Exception e){
		
		System.out.println("No Accnt Page");
	}
	 
         //Verify details
		Thread.sleep(2000);
	    assertEquals("Reports", driver.findElement(By.cssSelector("div.journal-content-article > h1")).getText());
	    assertEquals("Latest 10 Run", driver.findElement(By.cssSelector("th")).getText());
	    assertEquals("Report", driver.findElement(By.cssSelector("div.hdrcell")).getText());
	    assertEquals("Date", driver.findElement(By.xpath("//div[@id='reportListContainer']/div/table/tbody/tr[2]/td[2]/div")).getText());
	    assertEquals("Status", driver.findElement(By.xpath("//div[@id='reportListContainer']/div/table/tbody/tr[2]/td[3]/div")).getText());
	    assertEquals("Action", driver.findElement(By.xpath("//div[@id='reportListContainer']/div/table/tbody/tr[2]/td[4]/div")).getText());
	    System.out.println("Verifying Details");
	    
	    
	    //Verify Email button
	    driver.findElement(By.cssSelector("img.ui_icon_sprite.email-icon")).click();
		Thread.sleep(3000);
	    driver.findElement(By.id("txtToAddress")).clear();
	    driver.findElement(By.id("txtToAddress")).sendKeys("nthiruvengad@lexmark.com");
	    assertEquals("SEND", driver.findElement(By.cssSelector("#btnSendEmail > span")).getText());
	    assertEquals("CANCEL", driver.findElement(By.cssSelector("a.button_cancel > span")).getText());
	    assertEquals("Email Notification", driver.findElement(By.cssSelector("td.orangeSectionTitles")).getText());
	    driver.findElement(By.cssSelector("#btnSendEmail > span")).click();
		Thread.sleep(3000);
		System.out.println("Checking Email button");
		
		
	    //Verify Delete button
	   
		 assertEquals("", driver.findElement(By.cssSelector("img.ui_icon_sprite.trash-icon")).getText());
		Thread.sleep(3000);
		
		
		  //Logout application
	    driver.findElement(By.xpath("//.//*[@id='top-header-navigation']/ul/li[4]/a")).click();
	    System.out.println("Logoff Portal Successfully");		
	  	driver.close();
	    
	   
	  }

}
