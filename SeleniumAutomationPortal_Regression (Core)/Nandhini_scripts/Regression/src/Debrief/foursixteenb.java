package Debrief;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class foursixteenb {
	
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

	    driver.findElement(By.xpath("//span[contains(.,'Payments')]")).click();
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
	 
	    //Verifying Details
	    Thread.sleep(2000);
	    System.out.println("Verifying Details");
	    assertEquals("", driver.findElement(By.xpath("//img[@title='Export to PDF']")).getText());
	    assertEquals("", driver.findElement(By.xpath("//img[@title='Export to Excel']")).getText());
	    assertEquals("", driver.findElement(By.xpath("//img[@title='Print']")).getText());
	    assertEquals("Customize Columns", driver.findElement(By.id("headerMenuButton")).getText());
	    assertEquals("Reset", driver.findElement(By.id("resetGridSetting")).getText());
	    assertEquals("Payment Details", driver.findElement(By.cssSelector("div.hdrcell")).getText());
	    assertEquals("Date/Time Created", driver.findElement(By.xpath("//div[@id='gridRLVPaymentRequestList']/div/table/tbody/tr[2]/td[2]/div")).getText());
	    assertEquals("Request Number", driver.findElement(By.xpath("//div[@id='gridRLVPaymentRequestList']/div/table/tbody/tr[2]/td[3]/div")).getText());
	    assertEquals("Service Type", driver.findElement(By.xpath("//div[@id='gridRLVPaymentRequestList']/div/table/tbody/tr[2]/td[5]/div")).getText());
	    assertEquals("Request Status", driver.findElement(By.xpath("//div[@id='gridRLVPaymentRequestList']/div/table/tbody/tr[2]/td[6]/div")).getText());
	    assertEquals("Payment Status", driver.findElement(By.xpath("//div[@id='gridRLVPaymentRequestList']/div/table/tbody/tr[2]/td[7]/div")).getText());
	    assertEquals("Payment Number", driver.findElement(By.xpath("//div[@id='gridRLVPaymentRequestList']/div/table/tbody/tr[2]/td[8]/div")).getText());
	    assertEquals("Total", driver.findElement(By.xpath("//div[@id='gridRLVPaymentRequestList']/div/table/tbody/tr[2]/td[15]/div")).getText());
	    
	    
	    //searching Request number
	    System.out.println("Searching Request number");
	    driver.findElement(By.xpath("//.//*[@id='gridRLVPaymentRequestList']/div[1]/table/tbody/tr[3]/td[3]/div/input")).clear();
	    driver.findElement(By.xpath("//.//*[@id='gridRLVPaymentRequestList']/div[1]/table/tbody/tr[3]/td[3]/div/input")).sendKeys("1-46870906348");
	    Thread.sleep(8000);	    
	    driver.findElement(By.xpath("//.//*[@id='gridRLVPaymentRequestList']/div[2]/table/tbody/tr[2]/td[3]/a")).click();
	    Thread.sleep(8000);
	    
	    //Verify details
	    System.out.println("Verify details");
	    assertEquals("", driver.findElement(By.xpath("//img[@title='Print']")).getText());
	    assertEquals("", driver.findElement(By.xpath("//img[@title='Export to PDF']")).getText());
	    Thread.sleep(2000);
	    
	    
	    //Click Return to Request
	    driver.findElement(By.xpath("//.//*[@id='p_p_id_paymentRequest_WAR_LexmarkPartnerPortal_']/div/div/table/tbody/tr/td/div/div/div[1]/div[2]/ul/li/a")).click();
	    Thread.sleep(8000);
	    
	  //Logout application
	    driver.findElement(By.xpath("//.//*[@id='top-header-navigation']/ul/li[4]/a")).click();
	    System.out.println("Logoff Portal Successfully");		
	  	driver.close();
	    
	   
	  }

}
