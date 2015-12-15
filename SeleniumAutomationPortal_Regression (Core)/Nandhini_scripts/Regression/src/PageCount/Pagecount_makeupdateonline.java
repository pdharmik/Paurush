package PageCount;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Pagecount_makeupdateonline {
	
	 @Test
	  public void testUntitled() throws Exception {
		 
		 
		 File objFile = new File("Testdata.xls");
		    Workbook objWork = Workbook.getWorkbook(objFile);
			Sheet objSheet = objWork.getSheet("Pagecount"); 
			
		
			
			int rowNum = objSheet.getRows();
			int colNum = objSheet.getColumns();
			System.out.println("No of Rows "+rowNum);
			System.out.println("No of Cols "+colNum);
			
			for (int i=1; i<rowNum; i++){	
				
				
			
		//Open Browser	
		 
		// System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
		//	WebDriver driver = new InternetExplorerDriver();
			
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			WebDriver driver = new ChromeDriver(); //Chrome*/
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
	            
	            //Click page count Tab
	    	    
	    	    driver.findElement(By.xpath("//span[contains(.,'Page Counts')]")).click();
	    	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    	    System.out.println("Clcik PageCount tab");
	    	    
	    	    //Click Make Update Online
	    	    driver.findElement(By.xpath("//button[@onclick='makeUpdatesOnline();']")).click();
	    	    Thread.sleep(8000);
	    	    
	    	    //Verify the details
	    	    assertEquals("Page Counts", driver.findElement(By.cssSelector("div.journal-content-article > h1")).getText());
	    	    assertEquals("Online Updates", driver.findElement(By.cssSelector("h2.step")).getText());
	    	    assertEquals("Minimize All", driver.findElement(By.linkText("Minimize All")).getText());
	    	    assertEquals("Expand All", driver.findElement(By.linkText("Expand All")).getText());
	    	    assertEquals("Customize Columns", driver.findElement(By.id("headerMenuButton")).getText());
	    	    assertEquals("Reset", driver.findElement(By.id("resetGridSetting")).getText());
	    	    Thread.sleep(5000);
	    	    
	    	    //Click Manual reads
	    	    driver.findElement(By.id("Manual")).click();
	    	    Thread.sleep(8000);
	    	    
	    	    //Searching serial number
	    	    driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div/div/div/div/div/div[6]/div[3]/div/div[9]/div[2]/div[1]/div[1]/div[1]/table/tbody/tr[3]/td[2]/div/input")).clear();
	    	    driver.findElement(By.xpath("html/body/div[4]/div[2]/div/div/div/div/div/div/div/div[6]/div[3]/div/div[9]/div[2]/div[1]/div[1]/div[1]/table/tbody/tr[3]/td[2]/div/input")).sendKeys(objSheet.getCell(2,i).getContents());
	    	    Thread.sleep(8000);
	    	    
	    	    //Enter the new values
	    	    driver.findElement(By.id("input_count0")).click();
	    	    driver.findElement(By.id("input_count0")).clear();
	    	    driver.findElement(By.id("input_count0")).sendKeys(objSheet.getCell(3,i).getContents());
	    	    Thread.sleep(5000);
	    	    driver.findElement(By.id("btn_save0")).click();
	    	    Thread.sleep(8000);
	    	    
	    	    //verify details in popup
	    	    assertEquals("Serial Number", driver.findElement(By.cssSelector("td > h4")).getText());
	    	    assertEquals("Read Date", driver.findElement(By.xpath("//html/body/div[6]/div[2]/table/tbody/tr/td/table/tbody/tr[2]/td[2]/h4")).getText());	    
	    	    assertEquals("New Page Count", driver.findElement(By.xpath("//html/body/div[6]/div[2]/table/tbody/tr/td/table/tbody/tr[2]/td[3]/h4")).getText());
	    	    assertEquals("New Color Page Count", driver.findElement(By.xpath("html/body/div[6]/div[2]/table/tbody/tr/td/table/tbody/tr[2]/td[4]/h4")).getText());
	    	    assertEquals("Response", driver.findElement(By.xpath("//html/body/div[6]/div[2]/table/tbody/tr/td/table/tbody/tr[2]/td[5]/h4")).getText());
	    	    Thread.sleep(5000);
	    	    
	    	    //click YES button
	    	    
	    	    
	    	    driver.findElement(By.xpath("//html/body/div[6]/div[2]/table/tbody/tr/td/table/tbody/tr[3]/td[5]/table/tbody/tr[6]/td/div[1]/button[2]")).click();
	    	    Thread.sleep(5000);
	    	    
	    	    //Click ok button
	    	    
	    	    driver.findElement(By.xpath("//    html/body/div[6]/div[2]/table/tbody/tr/td/table/tbody/tr[3]/td[5]/table/tbody/tr[6]/td/div[2]/button")).click();
	    	    Thread.sleep(5000);
	    	    
	    	    //Click no button
	    	    
	    	    driver.findElement(By.xpath("//html/body/div[6]/div[2]/table/tbody/tr/td/table/tbody/tr[3]/td[5]/table/tbody/tr[6]/td/div[1]/button[1]")).click();
	    	    Thread.sleep(5000);
	    	
	    	    driver.findElement(By.id("BookMarked")).click();
	    	    Thread.sleep(5000);
	    	    driver.findElement(By.id("Manual")).click();
	    	    
	    	    //Logoff
	    	    
	    	    
	    		driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
	    		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    		Thread.sleep(15000);
	    		
	    			
	    		driver.close();


	    	}
	    	}
}

	    	
	    	  


