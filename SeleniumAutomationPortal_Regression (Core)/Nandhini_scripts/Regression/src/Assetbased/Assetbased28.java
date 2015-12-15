package Assetbased;

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

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;


public class Assetbased28 {
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
    
 // Creating Inquiry SR
    driver.findElement(By.cssSelector("a.createNewRequestPopUp > span")).click();
    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
   
    //select inquiry
    driver.findElement(By.id("others")).click();
    driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
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
    
    
    //verify details
    Thread.sleep(8000);
    assertEquals("Change Request", driver.findElement(By.cssSelector("div.journal-content-article > h1")).getText());
    assertEquals("Inquiry", driver.findElement(By.cssSelector("h2.step")).getText());
    driver.findElement(By.id("custReferenceId")).clear();
    driver.findElement(By.id("custReferenceId")).sendKeys("562346");
    driver.findElement(By.id("costCenter")).clear();
    driver.findElement(By.id("costCenter")).sendKeys("3462346");
    driver.findElement(By.id("addtnlDescription")).clear();
    driver.findElement(By.id("addtnlDescription")).sendKeys("3462346");
    new Select(driver.findElement(By.id("cmArea"))).selectByVisibleText("Inquiry");
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("img.dhx_combo_img")).click();
    Thread.sleep(8000);
    driver.findElement(By.xpath("//div[9]")).click();
    Thread.sleep(5000);
    driver.findElement(By.id("notesOrComments")).clear();
    driver.findElement(By.id("notesOrComments")).sendKeys("important");
    Thread.sleep(5000);
    
    
    //Add a Attachment 
      
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
       
      
       driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[7]/div[6]/div/div[1]/form/nobr/button[2]")).click();
       Thread.sleep(3000);
       
       //Click continue button
    driver.findElement(By.cssSelector("button.button.buttonOK")).click();
    Thread.sleep(3000);
    
    //Review page
    assertEquals("Inquiry - Review", driver.findElement(By.cssSelector("h3.pageTitle")).getText());
    driver.findElement(By.cssSelector("button.button")).click();
    
    
    // Inquiry_Confirmation Page
    String Request_Number = driver.findElement(By.xpath("//span[@id='reqNumber']")).getText();
    Thread.sleep(8000);
    SRnum[i] = Request_Number;
    System.out.println(Request_Number); 
    
    
    // Verify SR number            
    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/ul/li[1]/a/span")).click();
    Thread.sleep(8000);
    driver.findElement(By.xpath("//*[@id='allRequestHistory']/div[1]/table/tbody/tr[3]/td[1]/div/input")).sendKeys(Request_Number);
    Thread.sleep(8000);
    
    
    //LogOff
	driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	Thread.sleep(15000);
	
	
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