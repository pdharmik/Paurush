package Customerinvoice;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.util.concurrent.TimeUnit;
import jxl.Sheet;
import jxl.Workbook;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class Invoice1 {
	
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
				

      //       System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
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
	    
 //Click Invoice Tab
	    
	    driver.findElement(By.xpath("//span[contains(.,'Invoices')]")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    
	    try {
	    	Assert.assertEquals("Select An Account", driver.findElement(By.xpath("//span[contains(.,'Select An Account')]")).getText());
			Thread.sleep(3000);
			driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div[2]/div/div[1]/div[1]/table/tbody/tr[3]/td[5]/input")).sendKeys(objSheet.getCell(2,i).getContents());
			Thread.sleep(2000);
	    	driver.findElement(By.xpath("//*[@id='button1']")).click();
	    	//Select an account
	    	Thread.sleep(2000);

	    } catch(Exception e){
		
		System.out.println("No Accnt Page");
	}

	    
	    //Verify details
		Thread.sleep(3000);
	    assertEquals("Invoices", driver.findElement(By.cssSelector("div.journal-content-article > h1")).getText());
	    assertEquals("Customer Account:", driver.findElement(By.cssSelector("ul.form > li > label")).getText());
	    assertEquals("Sold To :", driver.findElement(By.xpath("//div[@id='content-wrapper']/div[3]/div/div[2]/div/div/div/ul/li[2]/label")).getText());
	    assertEquals("Bill To Address:", driver.findElement(By.xpath("//div[@id='content-wrapper']/div[3]/div/div[2]/div/div[2]/div/ul/li/label")).getText());
		Thread.sleep(3000);
		
		
	    //Searching Invoice
	    driver.findElement(By.xpath("//html/body/div[3]/div[2]/div/div/div/div/div/div/div/div[7]/div[1]/div[3]/div/div[2]/div[5]/div/div/div[1]/div[1]/table/tbody/tr[3]/td[1]/div/input")).clear();
	    driver.findElement(By.xpath("html/body/div[3]/div[2]/div/div/div/div/div/div/div/div[7]/div[1]/div[3]/div/div[2]/div[5]/div/div/div[1]/div[1]/table/tbody/tr[3]/td[1]/div/input")).sendKeys(objSheet.getCell(3,i).getContents());  
		Thread.sleep(15000);
		
		
	    //click invoice
	    
	    driver.findElement(By.xpath("//html/body/div[3]/div[2]/div/div/div/div/div/div/div/div[7]/div[1]/div[3]/div/div[2]/div[5]/div/div/div[1]/div[2]/table/tbody/tr[2]/td[1]/a")).click();
		Thread.sleep(3000);
	    
		   //LogOff
					driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					Thread.sleep(15000);
					
						
			  	driver.close();
				

			}
			}
}

