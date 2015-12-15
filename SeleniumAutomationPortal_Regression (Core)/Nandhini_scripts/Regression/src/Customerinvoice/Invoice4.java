package Customerinvoice;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.ie.InternetExplorerDriver;

public class Invoice4 {
	
	@Test
	  public void testUntitled() throws Exception {
		

	 File objFile = new File("Testdata.xls");
Workbook objWork = Workbook.getWorkbook(objFile);
Sheet objSheet = objWork.getSheet("Invoice"); 



int rowNum = objSheet.getRows();
int colNum = objSheet.getColumns();
System.out.println("No of Rows "+rowNum);
System.out.println("No of Cols "+colNum);

for (int i=1; i<rowNum; i++){	
				

       System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
        WebDriver driver = new InternetExplorerDriver();
		
//		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//		WebDriver driver = new ChromeDriver(); //Chrome*/
		driver.manage().window().maximize();driver.manage().window().maximize();
		
	    // Login Portal
	    driver.get("https://portal-qa.lexmark.com/group/global-services");
	    driver.findElement(By.id("_58_login")).clear();
	    driver.findElement(By.id("_58_login")).sendKeys(objSheet.getCell(0,i).getContents());
	    driver.findElement(By.id("_58_password")).clear();
	    driver.findElement(By.id("_58_password")).sendKeys(objSheet.getCell(1,i).getContents());
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    Thread.sleep(8000);
	    
//Click  Reports Tab
	    
	    driver.findElement(By.xpath("//span[contains(.,'Reports')]")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);

	    //Verify details
	    
	    assertEquals("Reports", driver.findElement(By.cssSelector("div.journal-content-article > h1")).getText());
	    assertEquals("Latest 10 Run", driver.findElement(By.cssSelector("th")).getText());
	    assertEquals("Report", driver.findElement(By.cssSelector("div.hdrcell")).getText());
	    assertEquals("Date", driver.findElement(By.xpath("//div[@id='reportListContainer']/div/table/tbody/tr[2]/td[2]/div")).getText());
	    assertEquals("Status", driver.findElement(By.xpath("//div[@id='reportListContainer']/div/table/tbody/tr[2]/td[3]/div")).getText());
	    assertEquals("Action", driver.findElement(By.xpath("//div[@id='reportListContainer']/div/table/tbody/tr[2]/td[4]/div")).getText());
	    Thread.sleep(8000);
	    
	   //Select Asset Register
	    
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div[1]/div/div/div/div/div/div/div[6]/div[3]/div/div[1]/div[1]/div[4]/ul/li/div/div[1]/a/img")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.linkText("Asset Register Test")).click();
	    
	    //clcik email button
	    
	    driver.findElement(By.cssSelector("img.ui_icon_sprite.email-icon")).click();
	    Thread.sleep(8000);
	    assertEquals("Email Notification", driver.findElement(By.cssSelector("td.orangeSectionTitles")).getText());
	    assertEquals("SEND", driver.findElement(By.cssSelector("#btnSendEmail > span")).getText());
	    assertEquals("CANCEL", driver.findElement(By.cssSelector("a.button_cancel > span")).getText());
	    driver.findElement(By.id("txtToAddress")).clear();
	    driver.findElement(By.id("txtToAddress")).sendKeys(objSheet.getCell(4,i).getContents());
	    Thread.sleep(4000);
	    driver.findElement(By.id("btnSendEmail")).click();
	    Thread.sleep(4000);
	    
	    //Click Delete button
	    
	    driver.findElement(By.xpath("//div[@id='reportListContainer']/div[2]/table/tbody/tr[3]/td[4]/table/tbody/tr/td[4]/a/img")).click();
	    Thread.sleep(4000);
	    driver.switchTo().alert().accept();	
	     


	  //LogOff
		driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(15000);
		
			
  	driver.close();
	

}
}
}


