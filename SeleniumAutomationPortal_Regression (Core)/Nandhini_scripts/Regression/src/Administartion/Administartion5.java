package Administartion;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.ie.InternetExplorerDriver;

public class Administartion5 {
	
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
	    
	    
	    //Click Document Library
	 driver.findElement(By.xpath("//a[contains(text(),'Document Library')]")).click();
	 driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS); 
	    assertEquals("ADD A NEW DOCUMENT NAME", driver.findElement(By.linkText("ADD A NEW DOCUMENT NAME")).getText());
	    assertEquals("CATEGORY DISPLAY", driver.findElement(By.linkText("CATEGORY DISPLAY")).getText());
	    
	    //Click Category display
	    driver.findElement(By.linkText("CATEGORY DISPLAY")).click();
	    driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS); 
	    
	    //Click Add category
	    driver.findElement(By.cssSelector("a.button > span")).click();
	    driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS); 
	    
	    //Enter the details
	    driver.findElement(By.id("category.name")).clear();
	    driver.findElement(By.id("category.name")).sendKeys(objSheet.getCell(2,i).getContents());
	    driver.findElement(By.id("category.localeList0.name")).clear();
	    driver.findElement(By.id("category.localeList0.name")).sendKeys(objSheet.getCell(4,i).getContents());
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS); 
	    driver.findElement(By.cssSelector("span.checkbox_Span.check_Off")).click();
	    driver.findElement(By.cssSelector("span.checkbox_Span.check_Off")).click();
	    driver.findElement(By.cssSelector("span.checkbox_Span.check_Off")).click();
	    driver.findElement(By.cssSelector("span.checkbox_Span.check_Off")).click();
	    driver.findElement(By.xpath("(//span[@onclick='checkboxSpanRebrandFunction(this)'])[6]")).click();
	    driver.findElement(By.xpath("(//span[@onclick='checkboxSpanRebrandFunction(this)'])[7]")).click();
	    driver.findElement(By.xpath("(//span[@onclick='checkboxSpanRebrandFunction(this)'])[8]")).click();
	    driver.findElement(By.xpath("(//span[@onclick='checkboxSpanRebrandFunction(this)'])[9]")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS); 
	    
	    
	    //Click Submit button
	    driver.findElement(By.id("select_Library_Administration")).click();
	    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
	    
	    //Click Edit button
	    driver.findElement(By.linkText("Edit")).click();
	    driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS); 
	    driver.findElement(By.xpath("(//span[@onclick='checkboxSpanRebrandFunction(this)'])[11]")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS); 
	    driver.findElement(By.id("select_Library_Administration")).click();
	    driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS); 
	    
	    //Click Delete button
	    
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div[2]/div/div/div/div/div[6]/div/div[3]/div[1]/div[2]/table/tbody/tr[2]/td[3]/img[3]")).click();
	    driver.switchTo().alert().accept();	
	    Thread.sleep(6000); 
	    assertEquals("Category delete successfully.", driver.findElement(By.cssSelector("li.portlet-msg-success")).getText());

	    //LogOff
		driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(15000);
		
			
  	driver.close();
	

}
}
}
