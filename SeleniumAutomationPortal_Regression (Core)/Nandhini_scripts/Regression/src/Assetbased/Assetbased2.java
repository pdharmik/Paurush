package Assetbased;

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
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class Assetbased2 {
	
	
	@Test

	public void testUpdateChangeSR() throws Exception {	 
		
		
		
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
				
				
				//Open Broswer
				
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
	         WebDriver driver = new InternetExplorerDriver();
	         
	         
		
	            
//			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//			WebDriver driver = new ChromeDriver(); //Chrome*/
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
               driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
    
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
   
    
    //Primary contact for this request
    driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div/div/div/div/div/div[16]/div/div/form[1]/div[1]/div[1]/div[1]/div[1]/h4/a")).click();
    Thread.sleep(20000); 
    driver.findElement(By.xpath("//html/body/div[6]/div[2]/div/div/div[4]/div[1]/div/div[1]/table/tbody/tr[3]/td[4]/input")).clear();
    driver.findElement(By.xpath("//html/body/div[6]/div[2]/div/div/div[4]/div[1]/div/div[1]/table/tbody/tr[3]/td[4]/input")).sendKeys(objSheet.getCell(4,i).getContents());
    Thread.sleep(8000);
    driver.findElement(By.xpath("//input[@class='button']")).click();
    Thread.sleep(8000);
    driver.findElement(By.cssSelector("span.checkbox_Span.check_Off")).click();
    Thread.sleep(8000);
    
    
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
    Thread.sleep(4000);
    
  //LogOff
  		driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
  		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  		Thread.sleep(15000);
        System.out.println("Logoff Portal successful");
        
        
      
 	  		
 			
 		  	driver.close();
 			}   
 		    Label lbl1 = new Label(0, 0, "SR"); //Here Label lbl = new Label (colNum,rowNum,String); str value has already defined in the 1st line 
 			writeSheet.addCell(lbl1); //add the Label value in respective Cell
 			
 				
 			for (int i=0; i<2; i++){
 				l[i]=new Label(0,i+1, SRnum[i+1]);
 				
 			
 				
 				writeSheet.addCell(l[i]);
 			}       
 			writeBook.write(); //Write to the workbook
 			writeBook.close(); //Close the workbook



 	}
 	}
