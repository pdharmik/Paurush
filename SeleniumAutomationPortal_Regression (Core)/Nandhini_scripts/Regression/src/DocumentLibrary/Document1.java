package DocumentLibrary;

import static org.junit.Assert.assertEquals;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import org.openqa.selenium.ie.InternetExplorerDriver;





public class Document1 {
	
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
				
	//			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	//			WebDriver driver = new ChromeDriver(); //Chrome*/
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
			    driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
			    
			    assertEquals("Document Library", driver.findElement(By.cssSelector("div.journal-content-article > h1")).getText());
			    
			    //Click white paper
			    
			    driver.findElement(By.linkText("white papers test")).click();
			    Thread.sleep(8000);

			    //Adding Attachment
			    
			    driver.findElement(By.xpath("//input[@name='fileContent']")).click();
			    
			    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div[2]/div/div/div/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/div/form/table/tbody/tr/td/input[2]")).click();
			    Thread.sleep(3000);
    
    //put path to your image in a clipboard
    StringSelection ss = new StringSelection("Attachment");
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    
	
    //imitate mouse events like ENTER, CTRL+C, CTRL+V:
    Robot robot = new Robot();
    robot.keyRelease(KeyEvent.VK_ENTER);
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);
 
    robot.keyRelease(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_ENTER);
    Thread.sleep(5000);
    
    robot.keyRelease(KeyEvent.VK_ENTER);
    Thread.sleep(5000);
    
    
    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div[2]/div/div/div/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/div/form/table/tbody/tr/td/a[2]")).click();
    Thread.sleep(8000);   
    
    
    //Verify details
    assertEquals("Upload document successful.", driver.findElement(By.cssSelector("td.serviceSuccess")).getText());
    assertEquals("File Name", driver.findElement(By.cssSelector("div.hdrcell")).getText());
    assertEquals("Upload Date", driver.findElement(By.xpath("//div[@id='divDocList']/div/table/tbody/tr[2]/td[2]/div")).getText());
    assertEquals("Size (kb)", driver.findElement(By.xpath("//div[@id='divDocList']/div/table/tbody/tr[2]/td[3]/div")).getText());
    assertEquals("File Format", driver.findElement(By.xpath("//div[@id='divDocList']/div/table/tbody/tr[2]/td[4]/div")).getText());
    assertEquals("Action", driver.findElement(By.xpath("//div[@id='divDocList']/div/table/tbody/tr[2]/td[5]/div")).getText());  
    
    
    //Click Delete button
    
    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div[2]/div/div/div/div[2]/table/tbody/tr/td[2]/table/tbody/tr[5]/td/div/div[3]/div[2]/table/tbody/tr[2]/td[5]/a/img")).click();
    Thread.sleep(4000);
    driver.switchTo().alert().accept();	
    
    
    //Logoff


driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
Thread.sleep(15000);

	
driver.close();


}
}
}






