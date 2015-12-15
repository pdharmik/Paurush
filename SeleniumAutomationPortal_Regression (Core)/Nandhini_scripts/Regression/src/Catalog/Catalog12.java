package Catalog;

import static org.junit.Assert.assertEquals;
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

import org.openqa.selenium.support.ui.Select;

public class Catalog12 {
	
	
	@Test

	public void testUpdateChangeSR() throws Exception {	 
	
	
	 File objFile = new File("Update SR.xls");
	    Workbook objWork = Workbook.getWorkbook(objFile);
		Sheet objSheet = objWork.getSheet("Catalog"); 
		
	
		
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
			
			
			//Open Browser
			
			
	//		System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
    //        WebDriver driver = new InternetExplorerDriver();	
         
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver(); //Chrome*/
		driver.manage().window().maximize();
	
		
		
		// Login Portal
				 driver.get("https://portal-qa.lexmark.com/group/global-services");
				    driver.findElement(By.id("_58_login")).clear();
				    driver.findElement(By.id("_58_login")).sendKeys(objSheet.getCell(0, i).getContents());
				    driver.findElement(By.id("_58_password")).clear();
				    driver.findElement(By.id("_58_password")).sendKeys(objSheet.getCell(1, i).getContents());
				    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
				    Thread.sleep(8000);
		        System.out.println("LogIn successful");

        // Request tab
        driver.findElement(By.xpath("//span[contains(.,'Requests')]")).click();
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);

         // Creating catalog based SR
         driver.findElement(By.cssSelector("a.createNewRequestPopUp > span")).click();
          driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
           System.out.println("Creating catalogbased");
           
           //Click Request supplies
       	Thread.sleep(5000);
           driver.findElement(By.id("consumableOrderLink")).click();
           driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
           assertEquals("Supplies Request", driver.findElement(By.cssSelector("#journal > h1")).getText());
           assertEquals("Select from All Assets", driver.findElement(By.id("allAsset")).getText());
           driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
           
           
           //Verify Pagination
           
           assertEquals("", driver.findElement(By.cssSelector("div.ui_icon_sprite.next-page-icon")).getText());
           assertEquals("", driver.findElement(By.cssSelector("div.ui_icon_sprite.last-page-icon")).getText());
           assertEquals("1", driver.findElement(By.cssSelector("div.dhx_page.dhx_page_active > div.dhx_page_active")).getText());
           
            
           //Click Catalog link
           
           driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[7]/div[3]/div/div[4]/div[2]/div[3]/a/span")).click();
           driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS); 
           
           
           
           
           try {
           	Assert.assertEquals("Select An Account", driver.findElement(By.xpath("//span[contains(.,'Select An Account')]")).getText());
       		Thread.sleep(3000);
       		driver.findElement(By.xpath("//*[@id='accountListGrid']/div[1]/table/tbody/tr[3]/td[4]/input")).sendKeys(objSheet.getCell(2,i).getContents());
       		Thread.sleep(5000);
        //   	driver.findElement(By.xpath("//input[@id='button1-2ROURJ43']")).click();
           	//Select an account
           	Thread.sleep(2000);

           } catch(Exception e){
       	
       	System.out.println("No Accnt Page");
       }
           
          //Verify details 
          
           assertEquals("Supplies Request", driver.findElement(By.cssSelector("h2")).getText());
           driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
           
           
           
           //select customer Address
           driver.findElement(By.cssSelector("img.dhx_combo_img")).click();
           driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
           driver.findElement(By.cssSelector("div.comboAlterColor")).click();
           driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
           
           
           //Select Payments
           driver.findElement(By.xpath("//select[@name='paymentType']")).click();  
           new Select(driver.findElement(By.xpath("//select[@name='paymentType']"))).selectByVisibleText("Invoice");
           driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
           
           
           //Searching Products
           driver.findElement(By.id("applyButton")).click();           
           driver.manage().timeouts().implicitlyWait(22,TimeUnit.SECONDS);  
                                        
          driver.findElement(By.id("productType")).click();  
         new Select(driver.findElement(By.id("productType"))).selectByVisibleText("Laser");
           driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
           
           
           driver.findElement(By.id("productModel")).click();  
          new Select(driver.findElement(By.id("productModel"))).selectByVisibleText("C546dtn");
           driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
           
           
           driver.findElement(By.id("partType")).click();
           new Select(driver.findElement(By.id("partType"))).selectByVisibleText("Waste Bottle");
           driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
           
           Thread.sleep(30000);
           driver.findElement(By.id("findCatalogList")).click();
           driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
           
           
           //Enter Quantity
           driver.findElement(By.id("partQuantity1-4ULQU15")).click();
           driver.findElement(By.id("partQuantity1-4ULQU15")).clear();
           driver.findElement(By.id("partQuantity1-4ULQU15")).sendKeys(objSheet.getCell(3, i).getContents());
           driver.findElement(By.cssSelector("input.button")).click();
           driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
           
           
           //Verify order
           assertEquals("Order:1Items", driver.findElement(By.linkText("Order:1Items")).getText());
           driver.findElement(By.cssSelector("#buttonListDiv > button.button")).click();
           driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
           
           driver.findElement(By.linkText("Order:1Items")).click();
           driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
           assertEquals("UPDATE CART", driver.findElement(By.cssSelector("#content-wrapper > div.portletBodyWrap > div.buttonContainer > button.button_cancel")).getText());
           assertEquals("CONTINUE SHOPPING", driver.findElement(By.cssSelector("div.portletBodyWrap > div.buttonContainer > button.button")).getText());
           assertEquals("CHECKOUT", driver.findElement(By.xpath("//button[@onclick='retriveDetailCatalogPart();']")).getText());
           driver.findElement(By.cssSelector("#content-wrapper > div.portletBodyWrap > div.buttonContainer > button.button_cancel")).click();
           Thread.sleep(3000);
           driver.findElement(By.id("assetPartList[0].orderQuantity")).clear();
           driver.findElement(By.id("assetPartList[0].orderQuantity")).sendKeys(objSheet.getCell(3, i).getContents());
           driver.findElement(By.cssSelector("div.portletBodyWrap > div.buttonContainer > button.button")).click();
           Thread.sleep(4000);
           
           //Request details page_Entering details
           
           driver.findElement(By.id("custReferenceId")).clear();
           driver.findElement(By.id("custReferenceId")).sendKeys("45262456");
           driver.findElement(By.id("costCenter")).clear();
           driver.findElement(By.id("costCenter")).sendKeys("4364576");
           driver.findElement(By.id("addtnlDescription")).clear();
           driver.findElement(By.id("addtnlDescription")).sendKeys("important testing");
           
           //Select ship to address
           driver.findElement(By.id("diffAddressLink")).click();
           driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);   
           Thread.sleep(20000);                            
           driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div/div[4]/div/div[1]/div[1]/table/tbody/tr[3]/td[3]/div/input")).clear();
           driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div/div[4]/div/div[1]/div[1]/table/tbody/tr[3]/td[3]/div/input")).sendKeys(objSheet.getCell(4, i).getContents());
           Thread.sleep(8000);
           driver.findElement(By.xpath("//input[@name='btn_select']")).click();
           Thread.sleep(8000);   
           driver.findElement(By.id("physicalLocation1")).clear();
           driver.findElement(By.id("physicalLocation1")).sendKeys("1");
           driver.findElement(By.id("physicalLocation2")).clear();
           driver.findElement(By.id("physicalLocation2")).sendKeys("2");
           driver.findElement(By.id("physicalLocation3")).clear();
           driver.findElement(By.id("physicalLocation3")).sendKeys("Lexmark");
           
           driver.findElement(By.id("poNumber")).clear();
           driver.findElement(By.id("poNumber")).sendKeys(objSheet.getCell(5, i).getContents());
     
           //Add a Attachment 
           
           
           driver.findElement(By.xpath("//h4[contains(.,'Add Notes & Attachment')]")).click();
           Thread.sleep(8000);
           
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
           
                                         
           driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[13]/div/div/div[8]/div[1]/div/div/div/div/form/button[2]")).click();
           Thread.sleep(3000);
           
          
           //Check Cancel button
           
           System.out.println("Checking cancel button");
          
           driver.findElement(By.xpath("// html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[13]/div/div/div[8]/form[2]/div/button[3]")).click();
           Thread.sleep(4000);  
           driver.findElement(By.xpath("//html/body/div[4]/div/div[2]/button[2]")).click();	            
           Thread.sleep(4000);
          
           
           //Click Continue button
           driver.findElement(By.id("btnContinue")).click();
           
           // Review page
           driver.findElement(By.cssSelector("button.button")).click();
           Thread.sleep(5000);
           
           // Inquiry_Confirmation Page
           String Request_Number = driver.findElement(By.xpath("//span[@id='reqNumber']")).getText();
           Thread.sleep(8000);
           SRnum[i] = Request_Number;
   	    System.out.println(Request_Number); 
   	    System.out.println("SR created Successfully");
   	    
   	 //Verify Email and Print button
   	    
   	    assertEquals("Email this page", driver.findElement(By.linkText("Email this page")).getText());
   	    assertEquals("Print this page", driver.findElement(By.linkText("Print this page")).getText());
   	    Thread.sleep(4000);
   	   
           
           // Verify SR number            
           driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/ul/li[1]/a/span")).click();
           Thread.sleep(4000);
           driver.findElement(By.xpath("//*[@id='allRequestHistory']/div[1]/table/tbody/tr[3]/td[1]/div/input")).sendKeys(Request_Number);
           Thread.sleep(8000);
           System.out.println("Verify SR# in Request page");
           
           
           
           //LogOff  
           driver.findElement(By.cssSelector("span.ui-button-text")).click();
           driver.findElement(By.xpath("//html/body/div[1]/div/div[1]/div/div[2]/span/li[1]/ul/li[19]/a")).click();	
          	  Thread.sleep(8000);
         	driver.close();
		}   
	    Label lbl1 = new Label(0, 0, "SR"); //Here Label lbl = new Label (colNum,rowNum,String); str value has already defined in the 1st line 
		writeSheet.addCell(lbl1); //add the Label value in respective Cell
		
			
		for (int i=0; i<4; i++){
			l[i]=new Label(0,i+1, SRnum[i+1]);
			
		
			
			writeSheet.addCell(l[i]);
		}       
		writeBook.write(); //Write to the workbook
		writeBook.close(); //Close the workbook



	}
	}