package DocumentLibrary;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.ie.InternetExplorerDriver;

public class Documnet2 {
	
	@Test
	  public void testUntitled() throws Exception {
		
		 File objFile = new File("Testdata.xls");
		    Workbook objWork = Workbook.getWorkbook(objFile);
			Sheet objSheet = objWork.getSheet("Documnetlibrary"); 
			
		
			
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
			    
			    //Click Document Library Tab
			    
			    driver.findElement(By.xpath("//span[contains(.,'Document Library')]")).click();
			    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
			    
			    assertEquals("Document Library", driver.findElement(By.cssSelector("div.journal-content-article > h1")).getText());
			    
			    //Click white paper
			    
			    driver.findElement(By.linkText("white papers test")).click();
			    Thread.sleep(8000);
			    
			    //Searching Document
			    
			    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div[2]/div/div/div/div[2]/table/tbody/tr/td[2]/table/tbody/tr[5]/td/div/div[3]/div[1]/table/tbody/tr[3]/td[1]/input")).clear();
			    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div[2]/div/div/div/div[2]/table/tbody/tr/td[2]/table/tbody/tr[5]/td/div/div[3]/div[1]/table/tbody/tr[3]/td[1]/input")).sendKeys(objSheet.getCell(2,i).getContents());
			    Thread.sleep(8000);
			    
			    //Click Attachment
			    
			    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div[2]/div/div/div/div[2]/table/tbody/tr/td[2]/table/tbody/tr[5]/td/div/div[3]/div[2]/table/tbody/tr[2]/td[1]/a")).click();
			    Thread.sleep(8000);
			    
			    
			    //Logoff


			driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(15000);

				
			driver.close();


			}
			}
}




