package Debrief;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class fourseventen {

	
	 @Test
	  public void testDecommissionUpdateRequest() throws Exception {
		 
			
		 File objFile = new File("Testdata.xls");
		    Workbook objWork = Workbook.getWorkbook(objFile);
			Sheet objSheet = objWork.getSheet("Assetbased"); 
			
		
			
			int rowNum = objSheet.getRows();
			int colNum = objSheet.getColumns();
			System.out.println("No of Rows "+rowNum);
			System.out.println("No of Cols "+colNum);
			
			 File writeFlie= new File("D:\\Portal Result.xls"); //Create new File
		     WritableWorkbook writeBook = Workbook.createWorkbook(writeFlie); //Create New Workbook
		     WritableSheet writeSheet = writeBook.createSheet("OutPut", 0); //Create new WorkSheet named as OutPut in 0 th or first position 
		    
		     
		  	String[] SRnum = new String[rowNum];
			Label l[] = new Label[rowNum];
			for (int i=1; i<rowNum; i++){	
				
      
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver(); //Chrome*/
		driver.manage().window().maximize();
		
	 
	    // Login Partner Portal
		     
		 driver.get("https://portal-qa.lexmark.com/web/partner-portal/login?");  
		 driver.findElement(By.id("_58_login")).clear();
		   driver.findElement(By.id("_58_login")).sendKeys("pripoint@gmail.com");
		 driver.findElement(By.id("_58_password")).clear();
	   driver.findElement(By.id("_58_password")).sendKeys("Lexmark13");
		   driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div[1]/div/div/div/div[2]/div/div/form/div/button")).click();
	    Thread.sleep(20000);

	    driver.findElement(By.xpath("//span[contains(.,'Services')]")).click();
	    Thread.sleep(18000);

	    driver.findElement(By.xpath("//span[contains(.,'Customer Requests')]")).click();
	    Thread.sleep(8000);
	    System.out.println("Selct invoice tab");
	    
	    
	    // Creating Assetbased SR
	    driver.findElement(By.cssSelector("a.createNewRequestPopUp > span")).click();
	    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	    System.out.println("Creating Assetbased SR");
	    
	    //Click Request supplies
	       
	    driver.findElement(By.id("consumableOrderLink")).click();
	    Thread.sleep(18000);
	  
	    
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
	    System.out.println("Searching Asset");
	    driver.findElement(By.cssSelector("input[type=\"text\"]")).clear();
	    driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys(objSheet.getCell(3,i).getContents());
	    driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
	    driver.findElement(By.name("btn_select")).click();
	    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);              
	    
	    //Enter the details   
	    
	    assertEquals("Request Details", driver.findElement(By.cssSelector("h3.pageTitle")).getText());
	   

	    
	    
	    //Enter the details
	    
	    driver.findElement(By.id("custReferenceId")).clear();
	    driver.findElement(By.id("custReferenceId")).sendKeys("4576457");
	    driver.findElement(By.id("costCenter")).clear();
	    driver.findElement(By.id("costCenter")).sendKeys("457457");
	    driver.findElement(By.id("addtnlDescription")).clear();
	    driver.findElement(By.id("addtnlDescription")).sendKeys("457435743");
	    assertEquals("Add an Additional Contact for this Request", driver.findElement(By.id("addiContactButton")).getText());
	    
	    //Enter Quantity
	    driver.findElement(By.id("assetPartList[0].orderQuantity")).clear();
	    driver.findElement(By.id("assetPartList[0].orderQuantity")).sendKeys(objSheet.getCell(5,i).getContents());
	    driver.findElement(By.id("assetPartList[1].orderQuantity")).clear();
	    driver.findElement(By.id("assetPartList[1].orderQuantity")).sendKeys(objSheet.getCell(5,i).getContents());
	    driver.findElement(By.id("specialInstruction")).clear();
	    driver.findElement(By.id("specialInstruction")).sendKeys("important");
	    driver.findElement(By.cssSelector("span.checkbox_Span.check_Off")).click();
	    driver.findElement(By.id("poNumber")).clear();
	    driver.findElement(By.id("poNumber")).sendKeys(objSheet.getCell(8,i).getContents());
	    
	    
	    
	 //Add a Attachment 
	    
	    
	    driver.findElement(By.xpath("//4[contains9.,'Add Notes & Attachments']")).click();
	    Thread.sleep(3000);
	    
	    driver.findElement(By.xpath("//input[@name='fileData']")).click();
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
	    
	   
	    driver.findElement(By.xpath(" html/body/div[4]/div[2]/div/div/div/div/div/div/div/div[11]/form/div/div[6]/div/div/div/div/form/button[2]")).click();
	    Thread.sleep(3000);
	    
	    //Verify buttons
	    assertEquals("BACK", driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div/div/div/div/div/div[16]/div/div/form[2]/div/button[1]")).getText());
	    assertEquals("SAVE AS DRAFT", driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div/div/div/div/div/div[16]/div/div/form[2]/div/button[2]")).getText());
	    assertEquals("CANCEL", driver.findElement(By.xpath("(//html/body/div[4]/div[2]/div/div/div/div/div/div/div/div[16]/div/div/form[2]/div/button[3]")).getText());
	    
	    //Checking cancel button
	    driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div/div/div/div/div/div[16]/div/div/form[2]/div/button[3]")).click();
	    Thread.sleep(3000);
	    assertTrue(driver.findElement(By.id("popup_message")).getText().matches("^exact:Do you really want to cancel[\\s\\S] All Data will be lost\\.$"));    
	    driver.findElement(By.xpath("//html/body/div[6]/div/div[2]/button[2]")).click();
	    System.out.println("Checking Cancel button");
	    
	//Click Continue button
	    
	    driver.findElement(By.id("btnContinue")).click();
	    
	    // Review page
	    driver.findElement(By.cssSelector("button.button")).click();
	    
	    
	    // Inquiry_Confirmation Page
	    String Request_Number = driver.findElement(By.xpath("//span[@id='reqNumber']")).getText();
	    Thread.sleep(8000);
	    SRnum[i] = Request_Number;
	    System.out.println(Request_Number); 
	    System.out.println("Created SR successful");
	    
	    // Verify SR number            
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/ul/li[1]/a/span")).click();
	    Thread.sleep(8000);
	    driver.findElement(By.xpath("//*[@id='allRequestHistory']/div[1]/table/tbody/tr[3]/td[1]/div/input")).sendKeys(Request_Number);
	    Thread.sleep(8000);
	    System.out.println("Verify SR# in Request page");
	    
	    
	    //Verify details
	    driver.findElement(By.xpath("//html/body/div[3]/div[2]/div/div/div/div/div/div/div/div[13]/div/div[8]/div/div[2]/div/div[1]/div[2]/table/tbody/tr[2]/td[1]/a")).click();
	    Thread.sleep(7000);
	    
	    assertEquals("Consumables Supplies Request", driver.findElement(By.id("area")).getText());
	    assertEquals("Asset-Specific Request", driver.findElement(By.id("subArea")).getText());
	    assertEquals("Kuehne + Nagel NV", driver.findElement(By.id("accountName")).getText());
	    assertEquals("2361472", driver.findElement(By.id("serialNo")).getText());
	    assertEquals("643574", driver.findElement(By.id("custRefNo")).getText());
	    assertEquals("4574357", driver.findElement(By.id("costCenter")).getText());
	    assertEquals("457345734", driver.findElement(By.id("description")).getText());
	    assertEquals("4563457345", driver.findElement(By.cssSelector("#poNumber > span")).getText());
	    Thread.sleep(7000);
	    
	  //LogOff
	  		driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
	  		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  		Thread.sleep(15000);
	        System.out.println("Logoff Portal successful");
	    
}
}
}
