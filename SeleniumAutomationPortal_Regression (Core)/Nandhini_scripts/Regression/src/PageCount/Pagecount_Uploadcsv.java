package PageCount;

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



public class Pagecount_Uploadcsv {
	
	
	
	
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
     
     //Click pagecount Tab
	    
	    driver.findElement(By.xpath("//span[contains(.,'Page Counts')]")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    System.out.println("Clcik PageCount tab");
	
	  
	    
        //Upload CSV file
        
        driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/table/tbody/tr/td/div/div[3]/div/table/tbody/tr[6]/td[1]/div/button[2]")).click();
        Thread.sleep(8000);    
        assertEquals("File Import Screen", driver.findElement(By.cssSelector("h2.step")).getText());        
        driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/div[4]/form/table/tbody/tr/td/div[2]/div[1]/table/tbody/tr[1]/td/nobr/span[2]/button")).click();
	    Thread.sleep(2000);
	    System.out.println("Upload CSV file");
	    
	    
	    //attaching other documnet Error message will be displayed	    
	 //   assertEquals("Please make sure the file you selected is a CSV file.", driver.findElement(By.cssSelector("li.portlet-msg-error")).getText());
	  
	   
	    //put path to your image in a clipboard
	    StringSelection ss = new StringSelection("MS");
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
        
        
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/div[4]/form/table/tbody/tr/td/div[2]/div[2]/div[4]/table/tbody/tr[2]/td/button[2]")).click();
        Thread.sleep(8000);   
        
        
        System.out.println("Uploaded successful");    
        
        
        
        //Logoff
    
    
	driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	Thread.sleep(15000);
	
		
	driver.close();


}
}
}
    
    


