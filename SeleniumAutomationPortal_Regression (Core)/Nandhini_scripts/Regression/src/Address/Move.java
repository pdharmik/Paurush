package Address;

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

public class Move {
	
	@Test
	  public void testMoveAnAddress() throws Exception {
		
		File objFile = new File("Update SR.xls");
	    Workbook objWork = Workbook.getWorkbook(objFile);
		Sheet objSheet = objWork.getSheet("Address"); 	
	
		
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
	    System.out.println("Login Successfully");
	    
	    
		    // Request tab
	       driver.findElement(By.xpath("//span[contains(.,'Requests')]")).click();
	      driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	      
	    
	    // Creating Move an Address
	    driver.findElement(By.cssSelector("a.createNewRequestPopUp > span")).click();
	    driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);
	    driver.findElement(By.xpath("//a[@href='manageaddresses']")).click(); //click on the address link
	    driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
	    System.out.println("Creating Move an Address");
	    
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
    
//Verify the correct details in Address page
	    Thread.sleep(15000);
	    assertEquals("ADD A NEW ADDRESS", driver.findElement(By.cssSelector("button.button")).getText());
	    assertEquals("Address List", driver.findElement(By.cssSelector("h3.pageTitle")).getText());
	    assertEquals("All Account Addresses", driver.findElement(By.id("alladdress")).getText());	  
	    Thread.sleep(20000);
	    
 //Searching Asset
	    System.out.println("Search move an address");
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[13]/div[3]/div/div[3]/div[1]/div/div[1]/table/tbody/tr[3]/td[4]/div/input")).clear();
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[13]/div[3]/div/div[3]/div[1]/div/div[1]/table/tbody/tr[3]/td[4]/div/input")).sendKeys(objSheet.getCell(10,i).getContents());
	    Thread.sleep(8000);
	    System.out.println("Searching Move an Address");
	    
	    
	    //Checking Bookmark
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[13]/div[3]/div/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/td[2]/img")).click();
	    Thread.sleep(6000);
	    driver.findElement(By.linkText("My Bookmarked Addresses")).click();
	    Thread.sleep(6000);
	    driver.findElement(By.linkText("All Account Addresses")).click();
	    Thread.sleep(6000);
	    System.out.println("Checking bookmark device");
	    
	    //Select an Move an address
	    
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[13]/div[3]/div/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/td[1]/img[1]")).click();
	    Thread.sleep(6000);
	    System.out.println("Select an Address");
	    
	    // Enter the details
	    assertEquals("Change an Address", driver.findElement(By.cssSelector("h3.pageTitle")).getText());
	    driver.findElement(By.id("custReferenceId")).click();
	    driver.findElement(By.id("custReferenceId")).clear();
	    driver.findElement(By.id("custReferenceId")).sendKeys("4574637");
	    driver.findElement(By.id("costCenter")).click();
	    driver.findElement(By.id("costCenter")).clear();
	    driver.findElement(By.id("costCenter")).sendKeys("24573678");
	    driver.findElement(By.id("addtnlDescription")).click();
	    driver.findElement(By.id("addtnlDescription")).clear();
	    driver.findElement(By.id("addtnlDescription")).sendKeys("45735678");
	    driver.findElement(By.id("addressName")).click();
	    driver.findElement(By.id("addressName")).clear();
	    driver.findElement(By.id("addressName")).sendKeys(objSheet.getCell(3,i).getContents());
	    driver.findElement(By.id("addrLine1")).click();
	    driver.findElement(By.id("addrLine1")).clear();
	    driver.findElement(By.id("addrLine1")).sendKeys(objSheet.getCell(4,i).getContents());
	    driver.findElement(By.id("city")).click();
	    driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys(objSheet.getCell(5,i).getContents());
	    Thread.sleep(4000);
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/form[2]/div/div[4]/div/div[6]/div/div/ul/li[6]/span/select")).click();
	    Thread.sleep(3000);         
	   driver.findElement(By.id("country")).sendKeys(objSheet.getCell(7,i).getContents());
	   new Select(driver.findElement(By.id("country"))).selectByVisibleText("South Africa");
	    Thread.sleep(3000);
	    driver.findElement(By.id("state")).click();
	    new Select(driver.findElement(By.id("state"))).selectByVisibleText("Gauteng");    
	    driver.findElement(By.id("zipCode")).click();
	    driver.findElement(By.id("zipCode")).clear();
	    driver.findElement(By.id("zipCode")).sendKeys(objSheet.getCell(8,i).getContents());
	    Thread.sleep(8000);
	    
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
		    
		                                    
		    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/form[2]/div/div[4]/div/div[10]/div/div/div/div/form/button[2]")).click();
		    Thread.sleep(10000);
	     
	    
		    driver.findElement(By.cssSelector("button.button")).click();
		    Thread.sleep(10000);
	    
		    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/form[2]/div/div[4]/div/div[8]/div/div/div/p/span")).click();
		    Thread.sleep(3000);
	    
	    
	    //Check Cancel button
		    
		    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/form[2]/div[2]/div[4]/div/div[8]/button[1]")).click();
		    Thread.sleep(4000);		
		    driver.findElement(By.xpath("//html/body/div[4]/div/div[2]/button[2]")).click();		    
		    Thread.sleep(4000);
		    System.out.println("Checking Cancel button");
		    
		    //Click Continue button
		    
		    driver.findElement(By.cssSelector("button.button")).click();
		    Thread.sleep(8000);
		    
	       // Review page
	    driver.findElement(By.xpath("//html/body/div[2]/div[3]/div/div/div/div/div/div/div/div[7]/div[3]/div/form/div[3]/button[3]")).click();
	    
	    
	    // Inquiry_Confirmation Page
        String Request_Number = driver.findElement(By.xpath("//span[@id='reqNumber']")).getText();
        Thread.sleep(8000);
        SRnum[i] = Request_Number;
	    System.out.println(Request_Number); 
	    System.out.println("SR created Successfully");
        
      
        // Verify SR number
        driver.findElement(By.xpath("//div[@id='topnavigation']/ul/li[4]/a/span")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id='allRequestHistory']/div[1]/table/tbody/tr[3]/td[1]/div/input")).sendKeys(Request_Number);
        Thread.sleep(8000);
        System.out.println("Verify SR# in Request page");

        //Verify service details page 
          
          driver.findElement(By.xpath("//html/body/div[3]/div[2]/div/div/div/div/div/div/div/div[13]/div/div[8]/div/div[2]/div/div[1]/div[2]/table/tbody/tr[2]/td[1]/a")).click();
          Thread.sleep(7000);
          assertEquals("Data Management", driver.findElement(By.id("area")).getText());
          assertEquals("Change Address", driver.findElement(By.id("subArea")).getText());         
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