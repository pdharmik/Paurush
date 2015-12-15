package Assetbased;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.util.concurrent.TimeUnit;
import jxl.Sheet;
import jxl.Workbook;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Assetbased6 {
	
	@Test

	public void testUpdateChangeSR() throws Exception {	 
		
		
		
		 File objFile = new File("Testdata.xls");
		    Workbook objWork = Workbook.getWorkbook(objFile);
			Sheet objSheet = objWork.getSheet("Assetbased"); 
			
		
			
			int rowNum = objSheet.getRows();
			int colNum = objSheet.getColumns();
			System.out.println("No of Rows "+rowNum);
			System.out.println("No of Cols "+colNum);	
			
			for (int i=1; i<rowNum; i++){	
				
				  System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
		        WebDriver driver = new InternetExplorerDriver();
		        	            
		//	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		//	WebDriver driver = new ChromeDriver(); //Chrome*/
			driver.manage().window().maximize();
			
			
    // Login Portal
			 driver.get("https://portal-qa.lexmark.com/group/global-services");
			    driver.findElement(By.id("_58_login")).clear();
			    driver.findElement(By.id("_58_login")).sendKeys(objSheet.getCell(0,i).getContents());
			    driver.findElement(By.id("_58_password")).clear();
			    driver.findElement(By.id("_58_password")).sendKeys(objSheet.getCell(1,i).getContents());
			    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
			    Thread.sleep(8000);
               System.out.println("LogIn successful");
    
 // Request tab
    driver.findElement(By.xpath("//span[contains(.,'Requests')]")).click();
    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
    
    //Click Supplies request
    System.out.println("Clcik Supplies request");
    driver.findElement(By.linkText("Supplies Requests")).click();
    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    
    assertEquals("XLS", driver.findElement(By.linkText("XLS")).getText());
    assertEquals("PDF", driver.findElement(By.linkText("PDF")).getText());
    assertEquals("Print", driver.findElement(By.linkText("Print")).getText());
    assertEquals("Minimize All", driver.findElement(By.linkText("Minimize All")).getText());
    assertEquals("Expand All", driver.findElement(By.linkText("Expand All")).getText());
    assertEquals("Reset", driver.findElement(By.id("resetGridSetting")).getText());
    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    
    
  //Click Customize link to select PO number  
    driver.findElement(By.id("headerMenuButton")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("div.dhx_header_cmenu_item > span.checkbox_Span.check_Off")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("a.ui_icon_sprite.ui-icon-closethick")).click();
    Thread.sleep(3000);
    System.out.println("Select PO number");
    
    
    //Searching PO number
    driver.findElement(By.xpath("(//input[@type='text'])[10]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[10]")).sendKeys(objSheet.getCell(8,i).getContents());
    Thread.sleep(8000);
    driver.findElement(By.xpath("(//input[@type='text'])[10]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[10]")).sendKeys("");
    Thread.sleep(8000);
    
    //click reset click
    
    driver.findElement(By.id("resetGridSetting")).click();
    Thread.sleep(3000);
    driver.findElement(By.id("popup_ok")).click();
    Thread.sleep(5000);
    System.out.println("Clcik Reset Link");
    
    //Click Customize link to select Cost center
    driver.findElement(By.id("headerMenuButton")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("(//span[@onclick='checkboxSpanRebrandFunction(this)'])[11]")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("a.ui_icon_sprite.ui-icon-closethick")).click();
    Thread.sleep(8000);
    System.out.println("Select Cost Centre");
    
    
    
    //Search Cost center
    driver.findElement(By.xpath("(//input[@type='text'])[11]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[11]")).sendKeys(objSheet.getCell(9,i).getContents());
    Thread.sleep(8000);
    
 //click reset click
    
    driver.findElement(By.id("resetGridSetting")).click();
    Thread.sleep(3000);
    driver.findElement(By.id("popup_ok")).click();
    Thread.sleep(3000);
    System.out.println("Click Reset Link");
    
    //LogOff
	driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	Thread.sleep(15000);
	
	
  	driver.close();
	}  

}
}
