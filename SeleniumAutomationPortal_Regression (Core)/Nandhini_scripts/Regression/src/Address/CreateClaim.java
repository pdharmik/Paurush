package Address;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;

public class CreateClaim {
	
	 @Test
	  public void testUntitled() throws Exception {
		 
		 
		 
		 
		 System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			WebDriver driver = new ChromeDriver(); //Chrome*/
		  driver.manage().window().maximize();
		  
	 
      
      //Enter Userid and passsword
        driver.get("https://portal-qa.lexmark.com/web/partner-portal/login?");
	    driver.findElement(By.id("_58_login")).sendKeys("wdp@qa.com");
	    driver.findElement(By.id("_58_password")).clear();
	    driver.findElement(By.id("_58_password")).sendKeys("Lexmark14");
	    driver.findElement(By.xpath("//html/body/div[4]/div[3]/div/div/div[1]/div/div/div/div[2]/div/div/form/div/button")).click();
	    Thread.sleep(15000);
	    
	    //Click service
	    
	  
	    driver.findElement(By.cssSelector("span")).click();
	    Thread.sleep(20000);
	    
	    //Request tab
	    
	    driver.findElement(By.xpath("//div[@id='topnavigation']/ul/li[2]/a/span")).click();
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("(//button[@id='draftPopupOk'])[2]")).click();
	    Thread.sleep(5000);
	    
	    //Click create claim button
	    driver.findElement(By.xpath("//html/body/div[11]/div[3]/div/div/div/div/div/div/div/div[4]/div[2]/div[1]/div[1]/div[1]/div[1]/a")).click();
	    Thread.sleep(10000);
	    driver.findElement(By.id("txtSearchValue")).clear();
	    driver.findElement(By.id("txtSearchValue")).sendKeys("7949WVD");
	    driver.findElement(By.xpath("//html/body/div[6]/div[3]/div/div/div/div/div/div/div/div[4]/div/div[2]/div[2]/table/tbody/tr/td/a")).click();
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("//html/body/div[6]/div[3]/div/div/div/div/div/div/div/div[4]/div/div[2]/div[2]/div[1]/div[1]/div[2]/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr/td/a/span")).click();
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("//html/body/div[6]/div[3]/div/div/div/div/div/div/div/table/tbody/tr/td/div/div/div[3]/div[2]/div/a")).click();
	   Thread.sleep(10000);
	    driver.findElement(By.id("selectPartnerAgreement")).click();
	    new Select(driver.findElement(By.id("selectPartnerAgreement"))).selectByVisibleText("AT WD Printer Service GmbH LESP");
	    driver.findElement(By.cssSelector("option[title=\"AT WD Printer Service GmbH LESP\"]")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.id("customerAccount2")).clear();
	    driver.findElement(By.id("customerAccount2")).sendKeys("Reynolds Service");
	    driver.findElement(By.id("customerAddress2")).clear();
	    driver.findElement(By.id("customerAddress2")).sendKeys("78 ,wipro Techonolgies");
	    driver.findElement(By.id("activity.serviceRequest.primaryContact.firstName")).clear();
	    driver.findElement(By.id("activity.serviceRequest.primaryContact.firstName")).sendKeys("Nandhini");
	    driver.findElement(By.id("activity.serviceRequest.primaryContact.lastName")).clear();
	    driver.findElement(By.id("activity.serviceRequest.primaryContact.lastName")).sendKeys("Thiruvengadam");
	    driver.findElement(By.id("activity.serviceRequest.primaryContact.workPhone")).clear();
	    driver.findElement(By.id("activity.serviceRequest.primaryContact.workPhone")).sendKeys("9159432625");
	    driver.findElement(By.id("activity.serviceRequest.primaryContact.emailAddress")).clear();
	    driver.findElement(By.id("activity.serviceRequest.primaryContact.emailAddress")).sendKeys("nthiruvengad@lexmark.com");
	    Thread.sleep(3000);
	    new Select(driver.findElement(By.id("problemCode"))).selectByVisibleText("No Power");
	    Thread.sleep(3000);  
	    driver.findElement(By.xpath("//html/body/div[9]/div[3]/div/div/div/div/div/div/div/div[5]/form/div/div[3]/div[4]/div[2]/div/div/div/dl/dd[4]/textarea")).sendKeys("testing");
	    Thread.sleep(3000);  
	    driver.findElement(By.xpath("//html/body/div[9]/div[3]/div/div/div/div/div/div/div/div[5]/form/div/div[3]/div[4]/div[2]/div/div/div/dl/dd[6]/div/input")).sendKeys("3462457");
	    new Select(driver.findElement(By.id("technicianChoose"))).selectByVisibleText("Bointner, Reinhard");
	    Thread.sleep(3000);  
	    driver.findElement(By.xpath("//html/body/div[9]/div[3]/div/div/div/div/div/div/div/div[5]/form/div/div[3]/div[9]/div[3]/div/table/tbody/tr/td/a")).click();
	    Thread.sleep(10000);
	    driver.findElement(By.id("noteDetail")).clear();
	    driver.findElement(By.id("noteDetail")).sendKeys("important testing");
	    driver.findElement(By.id("addNoteButton")).click();
	    Thread.sleep(8000);
	    driver.findElement(By.xpath("//.//*[@id='dummyForm']/table/tbody/tr/td/div/a[1]")).click();
	    Thread.sleep(5000);    
	    driver.findElement(By.id("popup_ok")).click();
	    Thread.sleep(5000);    
	    
	    String Request_Number = driver.findElement(By.xpath("//.//*[@id='claim_information']/div[2]/div/div/div/dl/dd[2]/h2/b")).getText();
	    Thread.sleep(8000);
	    System.out.println(Request_Number);

}
}

