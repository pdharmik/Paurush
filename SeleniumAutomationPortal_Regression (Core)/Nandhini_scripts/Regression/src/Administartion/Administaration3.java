package Administartion;

import static org.junit.Assert.assertEquals;


import java.io.File;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;



import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Administaration3 {
	
	@Test
	  public void testUntitled() throws Exception {
		

	 File objFile = new File("Testdata.xls");
		    Workbook objWork = Workbook.getWorkbook(objFile);
		Sheet objSheet = objWork.getSheet("Administarion_Notification"); 
			
		
			
			int rowNum = objSheet.getRows();
			int colNum = objSheet.getColumns();
			System.out.println("No of Rows "+rowNum);
			System.out.println("No of Cols "+colNum);
			
					for (int i=1; i<rowNum; i++){	
				

 //        System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
//         WebDriver driver = new InternetExplorerDriver();
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver(); //Chrome*/
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
	    
	    //Click Notification link
	    driver.findElement(By.linkText("Notifications")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);	    
	    assertEquals("ADD A NOTIFICATION", driver.findElement(By.cssSelector("button.button")).getText());
	    System.out.println("Click Notification link");
	    
	    
	    //Clcik Add notification button
	    driver.findElement(By.cssSelector("button.button")).click();
	    driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
	    System.out.println("Click add notification link");
	    
	    
	    //Enter the details
	    driver.findElement(By.id("notificationDetail.notification.adminName")).clear();
	    driver.findElement(By.id("notificationDetail.notification.adminName")).sendKeys(objSheet.getCell(2,i).getContents());
	    driver.findElement(By.id("notificationDetail.notification.displayURL")).clear();
	    driver.findElement(By.id("notificationDetail.notification.displayURL")).sendKeys(objSheet.getCell(3,i).getContents());
	    driver.findElement(By.id("notificationDetail.notificationLocaleList0.displayDescription")).clear();
	    driver.findElement(By.id("notificationDetail.notificationLocaleList0.displayDescription")).sendKeys(objSheet.getCell(4,i).getContents());
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    
	    //Verify languages dispalyed
	    assertEquals("English", driver.findElement(By.xpath("//form[@id='formNotificationDetail']/table[3]/tbody/tr[2]/td")).getText());
	    assertEquals("Spanish", driver.findElement(By.xpath("//form[@id='formNotificationDetail']/table[3]/tbody/tr[3]/td")).getText());
	    assertEquals("German", driver.findElement(By.xpath("//form[@id='formNotificationDetail']/table[3]/tbody/tr[4]/td")).getText());
	    assertEquals("French", driver.findElement(By.xpath("//form[@id='formNotificationDetail']/table[3]/tbody/tr[5]/td")).getText());
	    assertEquals("Chinese Simplified", driver.findElement(By.xpath("//form[@id='formNotificationDetail']/table[3]/tbody/tr[6]/td")).getText());
	    assertEquals("Chinese Traditional", driver.findElement(By.xpath("//form[@id='formNotificationDetail']/table[3]/tbody/tr[7]/td")).getText());
	    assertEquals("Portuguese Brazil", driver.findElement(By.xpath("//form[@id='formNotificationDetail']/table[3]/tbody/tr[8]/td")).getText());
	    assertEquals("Portuguese Portugal", driver.findElement(By.xpath("//form[@id='formNotificationDetail']/table[3]/tbody/tr[9]/td")).getText());
	    assertEquals("Italian", driver.findElement(By.xpath("//form[@id='formNotificationDetail']/table[3]/tbody/tr[10]/td")).getText());
	    assertEquals("Korean", driver.findElement(By.xpath("//form[@id='formNotificationDetail']/table[3]/tbody/tr[11]/td")).getText());
	    assertEquals("Japanese", driver.findElement(By.xpath("//form[@id='formNotificationDetail']/table[3]/tbody/tr[12]/td")).getText());
	    assertEquals("Russian", driver.findElement(By.xpath("//form[@id='formNotificationDetail']/table[3]/tbody/tr[13]/td")).getText());
	    assertEquals("Turkish", driver.findElement(By.xpath("//form[@id='formNotificationDetail']/table[3]/tbody/tr[14]/td")).getText());
	    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	    
	    
	    
	    //Check Cancel button
	    driver.findElement(By.linkText("CANCEL")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    System.out.println("Checking cancel button");
	    
	    
	    //Enter details again
	    driver.findElement(By.cssSelector("button.button")).click();
	    driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
	    driver.findElement(By.id("notificationDetail.notification.adminName")).clear();
	    driver.findElement(By.id("notificationDetail.notification.adminName")).sendKeys(objSheet.getCell(2,i).getContents());
	    driver.findElement(By.id("notificationDetail.notification.displayURL")).clear();
	    driver.findElement(By.id("notificationDetail.notification.displayURL")).sendKeys(objSheet.getCell(3,i).getContents());
	    driver.findElement(By.id("notificationDetail.notificationLocaleList0.displayDescription")).clear();
	    driver.findElement(By.id("notificationDetail.notificationLocaleList0.displayDescription")).sendKeys(objSheet.getCell(4,i).getContents());
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    
	    //Clcik Submit button
	    driver.findElement(By.linkText("SUBMIT")).click();
	    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	    assertEquals("Submit notification successful.", driver.findElement(By.cssSelector("#message_banner_ > li")).getText());
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    System.out.println("Added notification successful");
	    
	    
	    //LogOff
		driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(15000);
		
			
  	driver.close();
	

}
}
}



