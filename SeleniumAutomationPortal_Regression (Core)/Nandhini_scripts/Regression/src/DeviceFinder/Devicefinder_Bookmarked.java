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
public class Devicefinder_Bookmarked {
	
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
	    
	    
	   //Checking Book marked device
	    
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/div[3]/div/div[10]/div[2]/div[1]/div[1]/div[1]/table/tbody/tr[3]/td[2]/div/input")).clear();
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/div[3]/div/div[10]/div[2]/div[1]/div[1]/div[1]/table/tbody/tr[3]/td[2]/div/input")).sendKeys(objSheet.getCell(2,i).getContents());
	    Thread.sleep(8000);
	    driver.findElement(By.cssSelector("td > img")).click();
	    Thread.sleep(4000);          
	    driver.findElement(By.xpath("//a[contains(.,'Bookmark This Device')]")).click();
	    Thread.sleep(6000);           
	    driver.findElement(By.id("linkBookmarked")).click();
	    Thread.sleep(8000);
	    driver.findElement(By.cssSelector("td > img")).click();
	    Thread.sleep(4000);
	    assertEquals("", driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/div[3]/div/div[10]/div[2]/div[1]/div[1]/div[2]/div/table/tbody/tr/td[2]/ul/li[7]/img")).getText());
	    Thread.sleep(4000);
	    
	    //Checking Unbook marked device
	    
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/div[3]/div/div[10]/div[2]/div[1]/div[1]/div[2]/div/table/tbody/tr/td[2]/ul/li[7]/img")).click();
	    Thread.sleep(6000);
	    driver.findElement(By.id("linkViewAll")).click();
	    Thread.sleep(6000);
	    driver.findElement(By.cssSelector("td > img")).click();
	    Thread.sleep(4000);
	    assertEquals("", driver.findElement(By.xpath("//a[contains(.,'Remove from Bookmark')]")).getText());
	    Thread.sleep(4000);
	    
	    
	    
	    //LogOff
			driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(15000);
			
		
	  	driver.close();
		}   
	    
	}
}
