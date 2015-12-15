package DeviceFinder;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;





public class DeviceFinder_Viewdetails {
	
	@Test
	  public void testUntitled() throws Exception {
		

		 File objFile = new File("Testdata.xls");
		    Workbook objWork = Workbook.getWorkbook(objFile);
			Sheet objSheet = objWork.getSheet("Device Finder"); 
			
		
			
			int rowNum = objSheet.getRows();
			int colNum = objSheet.getColumns();
			System.out.println("No of Rows "+rowNum);
			System.out.println("No of Cols "+colNum);
			
			for (int i=1; i<rowNum; i++){	
		
		
	
	//Open Browser	
		
		System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		
		
	//	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	//	WebDriver driver = new ChromeDriver(); //Chrome*/
		driver.manage().window().maximize();
		
		
//Login Portal
		 driver.get("https://portal-qa.lexmark.com/group/global-services");
		    driver.findElement(By.id("_58_login")).clear();
		    driver.findElement(By.id("_58_login")).sendKeys(objSheet.getCell(0,i).getContents());
		    driver.findElement(By.id("_58_password")).clear();
		    driver.findElement(By.id("_58_password")).sendKeys(objSheet.getCell(1,i).getContents());
		    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		    Thread.sleep(8000);
          System.out.println("LogIn successful");

          
          //Click Device Finder Tab
  	    
  	    driver.findElement(By.xpath("//span[contains(.,'Device Finder')]")).click();
  	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
  		
  	
  	//Verify Device Details
  	    assertEquals("Device Finder", driver.findElement(By.cssSelector("div.journal-content-article > h1")).getText());
  	    assertEquals("All Devices", driver.findElement(By.id("allAsset")).getText());
  	    assertEquals("Minimize All", driver.findElement(By.linkText("Minimize All")).getText());
  	    assertEquals("Expand All", driver.findElement(By.linkText("Expand All")).getText());
  	    assertEquals("Customize Columns", driver.findElement(By.id("headerMenuButton")).getText());
  	    assertEquals("Reset", driver.findElement(By.id("resetGridSetting")).getText());
  	    
  	    //Searching Serial number
  	    
     	  driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/div[3]/div/div[10]/div[2]/div[1]/div[1]/div[1]/table/tbody/tr[3]/td[2]/div/input")).clear();
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/div[3]/div/div[10]/div[2]/div[1]/div[1]/div[1]/table/tbody/tr[3]/td[2]/div/input")).sendKeys(objSheet.getCell(2,i).getContents());
	    Thread.sleep(8000);
	    
	   //Click serial number
	    driver.findElement(By.xpath("// html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/div[3]/div/div[10]/div[2]/div[1]/div[1]/div[2]/table/tbody/tr[2]/td[2]/a")).click();
	    Thread.sleep(8000);
	    
	    
	    //verify device details page
	    assertEquals("Device Detail", driver.findElement(By.cssSelector("h2.step")).getText());
	    assertEquals("Control Panel", driver.findElement(By.id("linkCP")).getText());
	    assertEquals("Support and Downloads", driver.findElement(By.id("linkPC")).getText());
	    assertEquals("Create a Request", driver.findElement(By.id("linkSR")).getText());
	    assertEquals("Update Page Counts", driver.findElement(By.id("updatePgCntsLnk")).getText());
	    assertEquals("Installed Address", driver.findElement(By.xpath("//div[@id='tabs-1']/div[3]/div/div/h4")).getText());
	    assertEquals("Primary Contact", driver.findElement(By.xpath("//div[@id='tabs-1']/div[3]/div[2]/div/h4")).getText());
	    
	    //Click Request History link
	    driver.findElement(By.id("tab2anchor")).click();
	    Thread.sleep(8000);
	    assertEquals("Customize Columns", driver.findElement(By.id("headerMenuButton")).getText());
	    assertEquals("Reset", driver.findElement(By.linkText("Reset")).getText());	    
	    driver.findElement(By.xpath("//html/body/div[3]/div[2]/div/div/div/div/div/div/div/div[6]/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[1]/div[2]/table/tbody/tr[2]/td[1]/a")).click();
	    Thread.sleep(18000);
	    driver.findElement(By.id("backButton")).click();
	    Thread.sleep(10000);
	    driver.findElement(By.cssSelector("button.button")).click();
	    Thread.sleep(10000);
	    
	    
	    //LogOff
			driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(15000);
			
		
	  	driver.close();
	  	
	  	
		}      
}
}

	
	

