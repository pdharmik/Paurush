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


public class Administration1 {

	
	@Test
	  public void testUntitled() throws Exception {
		

		 File objFile = new File("Testdata.xls");
		    Workbook objWork = Workbook.getWorkbook(objFile);
		Sheet objSheet = objWork.getSheet("Administarion_Report"); 
			
		
			
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
	    
          //Click Administartion tab Tab
	    
	    driver.findElement(By.xpath("//html/body/div[2]/div[1]/ul/li[7]/a/span")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    System.out.println("Click Administartion tab");
	    
	    //click Report link
	    
	    driver.findElement(By.xpath("//a[contains(text(),'Reports')]")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    System.out.println("Click Report link");
	    Thread.sleep(5000);
	    
	    //Verify details
	    assertEquals("ADD A NEW REPORT", driver.findElement(By.linkText("ADD A NEW REPORT")).getText());
	    assertEquals("CATEGORY DISPLAY", driver.findElement(By.linkText("CATEGORY DISPLAY")).getText());
	    assertEquals("SCHEDULER MAINTENANCE", driver.findElement(By.linkText("SCHEDULER MAINTENANCE")).getText()); 
	    
	  //Select category Display  
	    driver.findElement(By.linkText("CATEGORY DISPLAY")).click();
	    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	    System.out.println("Select Category dispaly");
	    
	    
	    //Verify details
	    assertEquals("Administration", driver.findElement(By.cssSelector("h2 > a")).getText());
	    assertEquals("Report Administration - Category Display", driver.findElement(By.cssSelector("span.orangeSectionTitles")).getText());
	    Thread.sleep(5000);
	    
	    
	 //Click Add category button   
	    driver.findElement(By.linkText("ADD CATEGORY")).click();
	    Thread.sleep(8000);
	    System.out.println("Adding Reports");
	    
	    
	    
	    //Enter the details
	    driver.findElement(By.id("category.name")).clear();
	    driver.findElement(By.id("category.name")).sendKeys(objSheet.getCell(2,i).getContents());
	    driver.findElement(By.id("category.localeList0.name")).clear();
	    driver.findElement(By.id("category.localeList0.name")).sendKeys(objSheet.getCell(4,i).getContents());
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("(//span[@onclick='checkboxSpanRebrandFunction(this)'])[4]")).click();
	    driver.findElement(By.xpath("(//span[@onclick='checkboxSpanRebrandFunction(this)'])[8]")).click();
	    driver.findElement(By.xpath("(//span[@onclick='checkboxSpanRebrandFunction(this)'])[5]")).click();
	    driver.findElement(By.xpath("(//span[@onclick='checkboxSpanRebrandFunction(this)'])[14]")).click();
	    driver.findElement(By.xpath("(//span[@onclick='checkboxSpanRebrandFunction(this)'])[15]")).click();
	    Thread.sleep(8000);
	    
	    //Click Submit button
	    driver.findElement(By.cssSelector("a.button.Role_Restrictions_button > span")).click();
	    Thread.sleep(8000);
	    
	    //Verify added Successfully
	    assertEquals("Save category successful.", driver.findElement(By.cssSelector("#message_banner_ > li")).getText());
	    Thread.sleep(3000);
	    System.out.println("Added successful");
	    
	    //LogOff
		driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(15000);
		
			
  	driver.close();
	

}
}
}

