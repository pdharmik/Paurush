package Asset;


import static org.junit.Assert.*;
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


public class Decommission {

	
	@Test

	public void testUpdateChangeSR() throws Exception {	 
		
		
		
		 File objFile = new File("Update SR.xls");
		    Workbook objWork = Workbook.getWorkbook(objFile);
			Sheet objSheet = objWork.getSheet("Asset"); 		
		
			
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
		
	//open browser
	            
				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
                WebDriver driver = new InternetExplorerDriver();
                

	
		//	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	//		WebDriver driver = new ChromeDriver(); //Chrome*/
			driver.manage().window().maximize();
			
			
    // Login Portal
			  // Login Portal
		    driver.get("https://portal-qa.lexmark.com/group/global-services");
		    driver.findElement(By.id("_58_login")).sendKeys(objSheet.getCell(0,i).getContents());
		    driver.findElement(By.id("_58_password")).sendKeys(objSheet.getCell(1,i).getContents());
		    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		    Thread.sleep(8000);
		    System.out.println("LogIn successful");
			    
    
		    
		    // Request tab
	    driver.findElement(By.xpath("//span[contains(.,'Requests')]")).click();
	    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    
            // Creating Decommission SR
    driver.findElement(By.cssSelector("a.createNewRequestPopUp > span")).click();
    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    
    // Asset
    driver.findElement(By.linkText("Asset")).click();
    Thread.sleep(18000);
    System.out.println("Clcik Asset Link");
    
    
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

    
    //Verify the correct details in Asset page
    Thread.sleep(15000);
    assertEquals("Change Request", driver.findElement(By.cssSelector("div.journal-content-article > h1")).getText());
    assertEquals("ADD A NEW ASSET", driver.findElement(By.cssSelector("button.button")).getText());
    assertEquals("Asset List", driver.findElement(By.cssSelector("h3.pageTitle")).getText());

    
    //Searching Asset
    
    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[7]/div[3]/div/div[4]/div/div[1]/div[1]/table/tbody/tr[3]/td[4]/div/input")).clear();
    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[7]/div[3]/div/div[4]/div/div[1]/div[1]/table/tbody/tr[3]/td[4]/div/input")).sendKeys(objSheet.getCell(3,i).getContents());
    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    System.out.println("Searching Asset");
    
    
    
    //Checking Bookmark
    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[7]/div[3]/div/div[4]/div/div[1]/div[2]/table/tbody/tr[2]/td[3]/img")).click();
    Thread.sleep(5000);      
    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[7]/div[3]/div/div[4]/div/div[1]/div[2]/div/table/tbody/tr/td[1]/table/tbody/tr[3]/td/img")).click();
    Thread.sleep(5000); 	    
    driver.findElement(By.id("linkBookmarked")).click();
    Thread.sleep(6000);
    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[7]/div[3]/div/div[4]/div/div[1]/div[2]/table/tbody/tr[2]/td[3]/img")).click();
    Thread.sleep(2000);
    assertEquals("Remove from Bookmark", driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[7]/div[3]/div/div[4]/div/div[1]/div[2]/div/table/tbody/tr/td[1]/table/tbody/tr[3]/td/a")).getText());
    Thread.sleep(2000);
    driver.findElement(By.id("linkAllAsset")).click();
    Thread.sleep(5000);
    System.out.println("Checking Bookmark device");
    
    
    //Click Decommission an asset
    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[7]/div[3]/div/div[4]/div/div[1]/div[2]/table/tbody/tr[2]/td[2]/img")).click();
    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    System.out.println("Click Decommission Asset");
    
    
    
    // Request detail page
    assertEquals("Decommission an Asset", driver.findElement(By.cssSelector("h3.pageTitle")).getText());
    driver.findElement(By.id("custReferenceId")).clear();
    driver.findElement(By.id("custReferenceId")).sendKeys("346326");
    driver.findElement(By.id("costCenter")).clear();
    driver.findElement(By.id("costCenter")).sendKeys("34636");
    driver.findElement(By.id("addtnlDescription")).clear();
    driver.findElement(By.id("addtnlDescription")).sendKeys("Testing");
    Thread.sleep(4000);
    driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div/div/div/div/div/div[11]/form/div/div[4]/div/div/ul/li/span/span[1]")).click();    
    Thread.sleep(3000);
    
    
    //Add a Attachment 
    
    
    driver.findElement(By.xpath("//h4[contains(.,'Add Notes & Attachment')]")).click();
    Thread.sleep(8000);
    
    driver.findElement(By.xpath("//input[@onchange='setPath To TextBox();']")).click();
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
    
    //Check Cancel button
    
    
    driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div/div/div/div/div/div[12]/div[3]/div[2]/button[2]")).click();
    Thread.sleep(4000);  
    driver.findElement(By.xpath("//html/body/div[6]/div/div[2]/button[2]")).click();    
    Thread.sleep(4000);
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
    
    
    //Verify service details page 
    
    driver.findElement(By.xpath("//html/body/div[3]/div[2]/div/div/div/div/div/div/div/div[13]/div/div[8]/div/div[2]/div/div[1]/div[2]/table/tbody/tr[2]/td[1]/a")).click();
    Thread.sleep(7000);
    
    assertEquals("Request History", driver.findElement(By.cssSelector("a[title=\"Request History\"] > span")).getText());
    assertEquals("Create New Request", driver.findElement(By.cssSelector("a.createNewRequestPopUp > span")).getText());
    assertEquals("MADC", driver.findElement(By.id("area")).getText());
    assertEquals("Decommission Asset", driver.findElement(By.id("subarea")).getText());
    assertEquals("UPDATE REQUEST", driver.findElement(By.id("updateButton")).getText());
    assertEquals("CANCEL REQUEST", driver.findElement(By.id("CancelButton")).getText());
    assertEquals("BACK", driver.findElement(By.id("backButton")).getText());
     Thread.sleep(7000); 
   
    
    //LogOff
		driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(15000);
		  System.out.println("Logoff Portal Successfully");
	
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


 