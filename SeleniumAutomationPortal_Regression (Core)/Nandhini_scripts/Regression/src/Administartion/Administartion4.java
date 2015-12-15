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
import org.openqa.selenium.support.ui.Select;

public class Administartion4 {
	
	
	@Test
	  public void testUntitled() throws Exception {
		

	 File objFile = new File("Testdata.xls");
		    Workbook objWork = Workbook.getWorkbook(objFile);
		Sheet objSheet = objWork.getSheet("Administarion_Add document"); 
			
		
			
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
	    
	    //Click Add a new Document
	    driver.findElement(By.linkText("ADD A NEW DOCUMENT NAME")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    System.out.println("Adding new document");
	    
	    
	    //enter the details
	    driver.findElement(By.id("definitionName")).clear();
	    driver.findElement(By.id("definitionName")).sendKeys(objSheet.getCell(2,i).getContents());
	    driver.findElement(By.cssSelector("span.checkbox_Span.check_Off")).click();
	    driver.manage().timeouts().implicitlyWait(9,TimeUnit.SECONDS);
	    driver.findElement(By.cssSelector("input.dhx_combo_input")).click();
	    driver.findElement(By.cssSelector("input.dhx_combo_input")).clear();
	    driver.findElement(By.cssSelector("input.dhx_combo_input")).sendKeys(objSheet.getCell(3,i).getContents());
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	   
	    driver.findElement(By.id("categoryId")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    new Select(driver.findElement(By.id("categoryId"))).selectByVisibleText("White Papers");
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    driver.findElement(By.id("displayValues_1")).clear();
	    driver.findElement(By.id("displayValues_1")).sendKeys(objSheet.getCell(4,i).getContents());
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    
	    //Check Cancel button
	    driver.findElement(By.id("Cancel_Library_Administration")).click();
	    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	    System.out.println("Checking cancel button");
	    
	    //enter details again
	    
	    driver.findElement(By.linkText("ADD A NEW DOCUMENT NAME")).click();
	    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	    
	    driver.findElement(By.id("definitionName")).clear();
	    driver.findElement(By.id("definitionName")).sendKeys(objSheet.getCell(2,i).getContents());
	    driver.findElement(By.cssSelector("span.checkbox_Span.check_Off")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    driver.findElement(By.cssSelector("input.dhx_combo_input")).click();
	    driver.findElement(By.cssSelector("input.dhx_combo_input")).clear();
	    driver.findElement(By.cssSelector("input.dhx_combo_input")).sendKeys(objSheet.getCell(3,i).getContents());
	    driver.findElement(By.id("categoryId")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    new Select(driver.findElement(By.id("categoryId"))).selectByVisibleText("White Papers");
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    driver.findElement(By.id("displayValues_1")).clear();
	    driver.findElement(By.id("displayValues_1")).sendKeys(objSheet.getCell(4,i).getContents());
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    
	 //click Submit button
	    driver.findElement(By.id("select_Library_Administration")).click();
	    driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
	    driver.switchTo().alert().accept();	
	    Thread.sleep(6000); 
	    System.out.println("Added successfully");
	    
	    
	    
	    //Edit details
	    driver.findElement(By.linkText("unimportant document")).click();
	    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	    driver.findElement(By.id("displayValues_2")).clear();
	    driver.findElement(By.id("displayValues_2")).sendKeys(objSheet.getCell(4,i).getContents());
	    System.out.println("Editing document");
	    
	    
	    //Click submit button
	    driver.findElement(By.id("select_Library_Administration")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    
	    //Delete created document    
	    driver.findElement(By.cssSelector("img.ui_icon_sprite.trash-icon")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    driver.switchTo().alert().accept();	
	    Thread.sleep(6000); 
	    assertEquals("Delete document name successful.", driver.findElement(By.cssSelector("li.portlet-msg-success")).getText());
	    System.out.println("Delete document successfully");
	    
	    //LogOff
		driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(15000);
		
			
  	driver.close();
	

}
}
}
