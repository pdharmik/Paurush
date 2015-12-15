package Debrief;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class fourthsixteen {
	
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

	    driver.findElement(By.xpath("//span[contains(.,'Invoices')]")).click();
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
	    
	    //Verify details Account Payable screen
	    System.out.println("Verifying details");
	    Thread.sleep(8000);
	    assertEquals("Accounts Payable", driver.findElement(By.linkText("Accounts Payable")).getText());
	    assertEquals("Accounts Receivable", driver.findElement(By.id("tab2anchor")).getText());
	    assertEquals("Filter by Date Range:", driver.findElement(By.cssSelector("div.left-nav-header > h3")).getText());
	    assertEquals("GO", driver.findElement(By.id("go_btn")).getText());
	    assertEquals("", driver.findElement(By.cssSelector("img.ui-icon.spreadsheet-icon")).getText());
	    assertEquals("", driver.findElement(By.cssSelector("img.ui-icon.pdf-icon")).getText());
	    assertEquals("", driver.findElement(By.cssSelector("img.ui-icon.print-icon")).getText());
	    assertEquals("Payment Information", driver.findElement(By.cssSelector("div.columnInner")).getText());
	    assertEquals("Invoice No.", driver.findElement(By.cssSelector("div.hdrcell")).getText());
	    assertEquals("Invoice Date:", driver.findElement(By.xpath("//.//*[@id='apInvoiceHistoryGrid_container']/div[1]/table/tbody/tr[2]/td[2]/div")).getText());
	    assertEquals("Due Date:", driver.findElement(By.xpath("//.//*[@id='apInvoiceHistoryGrid_container']/div[1]/table/tbody/tr[2]/td[3]/div")).getText());
	    assertEquals("Payment / Check No.:", driver.findElement(By.xpath("//.//*[@id='apInvoiceHistoryGrid_container']/div[1]/table/tbody/tr[2]/td[4]/div")).getText());
	    assertEquals("Paid Date:", driver.findElement(By.xpath("//.//*[@id='apInvoiceHistoryGrid_container']/div[1]/table/tbody/tr[2]/td[5]/div")).getText());
	    assertEquals("Amount USD", driver.findElement(By.xpath("//.//*[@id='apInvoiceHistoryGrid_container']/div[1]/table/tbody/tr[2]/td[6]/div")).getText());
	    
	    //Searching Invoices Number
	    driver.findElement(By.xpath("//.//*[@id='apInvoiceHistoryGrid_container']/div[1]/table/tbody/tr[3]/td[1]/input")).clear();
	    driver.findElement(By.xpath("//.//*[@id='apInvoiceHistoryGrid_container']/div[1]/table/tbody/tr[3]/td[1]/input")).sendKeys("12312014A");
	    Thread.sleep(8000);	    
	    driver.findElement(By.xpath("//.//*[@id='apInvoiceHistoryGrid_container']/div[2]/table/tbody/tr[2]/td[1]/a")).click();
	    Thread.sleep(8000);
	    System.out.println("Searching Invoice number");
	    
	    
	    //Verify Invoice details
	 
	    assertEquals("Payable Details", driver.findElement(By.cssSelector("div.journal-content-article > h1")).getText());
	    assertEquals("Credit Invoice Information :", driver.findElement(By.cssSelector("div.columnInner")).getText());
	    assertEquals("Return to Invoices", driver.findElement(By.linkText("Return to Invoices")).getText());
	    
	    
	    //Clcik return to Invoice
	    driver.findElement(By.xpath("//.//*[@id='content-wrapper']/div[2]/div/div[1]/div/ul/li/a")).click();
	    Thread.sleep(8000);
	    
	    //Logout application
	    driver.findElement(By.xpath("//.//*[@id='top-header-navigation']/ul/li[4]/a")).click();
	    System.out.println("Logoff Portal Successfully");		
	  	driver.close();
	  }
}
