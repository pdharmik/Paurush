package Assetbased;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.util.concurrent.TimeUnit;
import jxl.Sheet;
import jxl.Workbook;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Assetbased27 {

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
				
				//Open Browser
	            
				
				  System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
	         WebDriver driver = new InternetExplorerDriver();
	         

				
	//		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	//		WebDriver driver = new ChromeDriver(); //Chrome*/
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
    
 // Creating Assetbased SR
    driver.findElement(By.cssSelector("a.createNewRequestPopUp > span")).click();
    driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
    System.out.println("Creating Assetbased SR");
    
    //Click Request supplies
       
    driver.findElement(By.id("consumableOrderLink")).click();
    driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
  
    
    try {
    	Assert.assertEquals("Select An Account", driver.findElement(By.xpath("//span[contains(.,'Select An Account')]")).getText());
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='accountListGrid']/div[1]/table/tbody/tr[3]/td[4]/input")).sendKeys(objSheet.getCell(2,i).getContents());
		Thread.sleep(2000);
    	driver.findElement(By.xpath("//*[@name='btn_select']")).click();
    	//Select an account
    	Thread.sleep(2000);

    } catch(Exception e){
	
	System.out.println("No Accnt Page");
}
    
    
    assertEquals("Supplies Request", driver.findElement(By.cssSelector("#journal > h1")).getText());
    assertEquals("Select from All Assets", driver.findElement(By.id("allAsset")).getText());
    
    //Select asset
    System.out.println("Selcting Asset");
    driver.findElement(By.cssSelector("input[type=\"text\"]")).clear();
    driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys(objSheet.getCell(3,i).getContents());
    driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
    driver.findElement(By.name("btn_select")).click();
    Thread.sleep(18000);       
    
//Enter the details
    
    driver.findElement(By.id("custReferenceId")).clear();
    driver.findElement(By.id("custReferenceId")).sendKeys("4576457");
    driver.findElement(By.id("costCenter")).clear();
    driver.findElement(By.id("costCenter")).sendKeys("457457");
    driver.findElement(By.id("addtnlDescription")).clear();
    driver.findElement(By.id("addtnlDescription")).sendKeys("457435743");
    assertEquals("Add an Additional Contact for this Request", driver.findElement(By.id("addiContactButton")).getText());
    
    //Enter The quantity
    driver.findElement(By.id("assetPartList[0].orderQuantity")).clear();
    driver.findElement(By.id("assetPartList[0].orderQuantity")).sendKeys(objSheet.getCell(5,i).getContents());
    driver.findElement(By.id("assetPartList[1].orderQuantity")).clear();
    driver.findElement(By.id("assetPartList[1].orderQuantity")).sendKeys(objSheet.getCell(5,i).getContents());
    driver.findElement(By.id("specialInstruction")).clear();
    driver.findElement(By.id("specialInstruction")).sendKeys("important");
    driver.findElement(By.cssSelector("span.checkbox_Span.check_Off")).click();
    driver.findElement(By.id("poNumber")).clear();
    driver.findElement(By.id("poNumber")).sendKeys("1234567");
    Thread.sleep(8000);
    
    //Click save as draft button
    driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div/div/div/div/div/div[16]/div/div/form[2]/div/button[2]")).click();
    Thread.sleep(4000);
    assertEquals("", driver.findElement(By.cssSelector("div.columnsTwo.w50 > img")).getText());
    assertEquals("OK", driver.findElement(By.id("draftPopupOk")).getText()); 
    driver.findElement(By.id("draftPopupOk")).click();
    Thread.sleep(4000);
    System.out.println("Click Save as draft");
    
    //LogOff
	driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	Thread.sleep(15000);
	
	
			}
	}
}
	
	
	
	
	
	
