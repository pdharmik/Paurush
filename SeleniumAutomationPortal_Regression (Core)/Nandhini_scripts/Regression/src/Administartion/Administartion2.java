package Administartion;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class Administartion2 {
	
	@Test
	  public void testUntitled() throws Exception {
		

		 File objFile = new File("Testdata.xls");
		    Workbook objWork = Workbook.getWorkbook(objFile);
		Sheet objSheet = objWork.getSheet("Administarion_schedular"); 
			
		
			
			int rowNum = objSheet.getRows();
			int colNum = objSheet.getColumns();
			System.out.println("No of Rows "+rowNum);
			System.out.println("No of Cols "+colNum);
			
		for (int i=1; i<rowNum; i++){	
				

         System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
         WebDriver driver = new InternetExplorerDriver();
		
	//	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	//	WebDriver driver = new ChromeDriver(); //Chrome*/
		driver.manage().window().maximize();driver.manage().window().maximize();
		
	    // Login Portal
	    driver.get("https://portal-qa.lexmark.com/group/global-services");
	    driver.findElement(By.id("_58_login")).clear();
	    driver.findElement(By.id("_58_login")).sendKeys(objSheet.getCell(0,i).getContents());
	    driver.findElement(By.id("_58_password")).clear();
	    driver.findElement(By.id("_58_password")).sendKeys(objSheet.getCell(1,i).getContents());
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    Thread.sleep(8000);
	    System.out.println("LogIn successful");
	    
        //Click Aministartion tab Tab
	    
	    driver.findElement(By.xpath("//html/body/div[2]/div[1]/ul/li[7]/a/span")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    System.out.println("Click Administartion tab");
	    
	    //click Report link
	    
	    driver.findElement(By.xpath("//a[contains(text(),'Reports')]")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    System.out.println("Click Report link");
	    
	    //Verify details
	    assertEquals("ADD A NEW REPORT", driver.findElement(By.linkText("ADD A NEW REPORT")).getText());
	    assertEquals("CATEGORY DISPLAY", driver.findElement(By.linkText("CATEGORY DISPLAY")).getText());
	    assertEquals("SCHEDULER MAINTENANCE", driver.findElement(By.linkText("SCHEDULER MAINTENANCE")).getText());
	    Thread.sleep(5000);
	    
	    
	    //Click Scheduler Maintaence
	    driver.findElement(By.linkText("SCHEDULER MAINTENANCE")).click();
	    Thread.sleep(8000);
	    System.out.println("Click Schedular Maintence");
	    
	    
	    //Search date 
	    //Get main windowHandle
  	    String mainWindow = driver.getWindowHandle();
  	 
  	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div[2]/div/div/div/div/div[6]/div/div[4]/div/img[1]")).click(); //click on  calendar icon
  	    
  	    //Switch for iframe *************
  	    
  	    WebElement calendar = driver.findElement(By.className("rc_calendar"));
  	    driver.switchTo().frame(calendar); 
  	    Thread.sleep(3000);

  	    driver.findElement(By.xpath("//*[@id='rc_iframe_table']/tbody/tr[4]/td[6]")).click();  //Select date cell  
  	    
  
  	    
  	     driver.findElement(By.linkText("SEARCH")).click();
	    Thread.sleep(8000);
	    System.out.println("Searching report");
	    
	    
	    //Verify details
	    
	    assertEquals("Report Run Date And Time(GMT)", driver.findElement(By.cssSelector("div.hdrcell")).getText());
	    assertEquals("Email", driver.findElement(By.xpath("//div[@id='div_reports_container']/div/table/tbody/tr[2]/td[2]/div")).getText());
	    assertEquals("Report Name", driver.findElement(By.xpath("//div[@id='div_reports_container']/div/table/tbody/tr[2]/td[3]/div")).getText());
	    assertEquals("Status", driver.findElement(By.xpath("//div[@id='div_reports_container']/div/table/tbody/tr[2]/td[4]/div")).getText());
	    assertEquals("Error Msg", driver.findElement(By.xpath("//div[@id='div_reports_container']/div/table/tbody/tr[2]/td[5]/div")).getText());
	    assertEquals("Parameters Passed", driver.findElement(By.xpath("//div[@id='div_reports_container']/div/table/tbody/tr[2]/td[6]/div")).getText());
	    assertEquals("Action", driver.findElement(By.xpath("//div[@id='div_reports_container']/div/table/tbody/tr[2]/td[7]/div")).getText());
	    Thread.sleep(5000);
	    
	    //search Report name 
	      
	    driver.findElement(By.xpath("(//input[@type='text'])[5]")).clear();
	    driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys(objSheet.getCell(2,i).getContents());
	     Thread.sleep(5000);
	    
	    //Clcik Details link
	    System.out.println("Click Detail link");
	    driver.findElement(By.linkText("Details")).click();
	    Thread.sleep(5000);
	    assertEquals("Report Admin - Parameter Details", driver.findElement(By.cssSelector("td.orangeSectionTitles")).getText());
	    assertEquals("CLOSE", driver.findElement(By.linkText("CLOSE")).getText());
	    Thread.sleep(3000);
	    driver.findElement(By.cssSelector("a.button > span")).click();
	    Thread.sleep(5000);
	    
	    //Click Rerun button
	    driver.findElement(By.cssSelector("button.button")).click();
	    Thread.sleep(5000);
	    System.out.println("Click Rerun Button");
	    
	    //verify RErun button
	    assertEquals("Rerun report successfully.", driver.findElement(By.cssSelector("li.portlet-msg-success")).getText());
	    Thread.sleep(5000);
	    System.out.println("Rerun the report successfully");
	    
	    //LogOff
		driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(15000);
		
			
  	driver.close();
	

}
}
}

