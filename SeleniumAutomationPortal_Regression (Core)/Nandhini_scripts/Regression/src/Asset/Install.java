package Asset;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import org.openqa.selenium.support.ui.Select;

public class Install { 
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
		     
		     
//Open Browser
				
				
			//	System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
            //    WebDriver driver = new InternetExplorerDriver();
	            
				

	            
	            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	            WebDriver driver = new ChromeDriver(); 
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
   	      driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
   	      
	            
	         // Creating Install SR
	            driver.findElement(By.cssSelector("a.createNewRequestPopUp > span")).click();
	            driver.manage().timeouts().implicitlyWait(9,TimeUnit.SECONDS);
	            
	            // Asset
	            driver.findElement(By.linkText("Asset")).click();
	            driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);
	            System.out.println("Click Asset link");
	            
	            
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
	            
	            // Select Add new button
	            driver.findElement(By.cssSelector("//button[@class='button'])")).click();
	            Thread.sleep(8000);
	            System.out.println("Creating add a new Asset");
	            
	            
	            //Verify same as Requester check box
	            assertTrue(driver.findElement(By.cssSelector("h3.pageTitle")).getText().matches("^exact:Add a New Asset \\[Fields marked[\\s\\S]* are required\\]$"));
	            driver.findElement(By.linkText("Select a different contact")).click();
	            Thread.sleep(25000);	            
	            driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div[4]/div[1]/div/div[1]/table/tbody/tr[3]/td[4]/input")).clear();
	            driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div[4]/div[1]/div/div[1]/table/tbody/tr[3]/td[4]/input")).sendKeys(objSheet.getCell(5,i).getContents());
	            Thread.sleep(4000);
	            driver.findElement(By.cssSelector("//input[contains(@id,'1-8MN7WFK')]")).click();
	            Thread.sleep(4000);
	            driver.findElement(By.cssSelector("span.checkbox_Span.check_Off")).click();
	            Thread.sleep(4000);         
	            System.out.println("Checking same as requestor check box");
	            
	            
	            //enter details
	            driver.findElement(By.id("custReferenceId")).click();
	            driver.findElement(By.id("custReferenceId")).clear();
	            driver.findElement(By.id("custReferenceId")).sendKeys("46457");
	            driver.findElement(By.id("costCenter")).click();
	            driver.findElement(By.id("costCenter")).clear();
	            driver.findElement(By.id("costCenter")).sendKeys("56316");
	            driver.findElement(By.id("addtnlDescription")).click();
	            driver.findElement(By.id("addtnlDescription")).clear();
	            driver.findElement(By.id("addtnlDescription")).sendKeys("Testing");
	            driver.findElement(By.id("serialNumber")).click();
	            driver.findElement(By.id("serialNumber")).clear();
	            driver.findElement(By.id("serialNumber")).sendKeys(objSheet.getCell(6,i).getContents());          
	            driver.findElement(By.id("productType")).click();
	            Thread.sleep(10000);
	            driver.findElement(By.id("productType")).sendKeys(objSheet.getCell(7,i).getContents());
	            Thread.sleep(4000); 
	           driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[17]/div[1]/form/div[3]/div[1]/div/ul/li[4]/span[1]/img[1]")).click();
	          Thread.sleep(8000);       
	          
	          
	  	    //Get main windowHandle
	  	    String mainWindow = driver.getWindowHandle();
	  	 
	  	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[17]/div[1]/form/div[3]/div[1]/div/ul/li[4]/span[1]/img[1]")).click(); //click on from calendar icon
	  	    
	  	    //Switch for iframe *************
	  	    
	  	    WebElement calendar = driver.findElement(By.className("rc_calendar"));
	  	    driver.switchTo().frame(calendar); 
	  	    Thread.sleep(3000);

	  	    driver.findElement(By.xpath("//*[@id='rc_iframe_table']/tbody/tr[6]/td[4]")).click();  //Select date cell
	  	    
	  	         driver.findElement(By.id("ipAddress")).click();
	            driver.findElement(By.id("ipAddress")).clear();
	            driver.findElement(By.id("ipAddress")).sendKeys("10.102.103.25");
	            driver.findElement(By.id("hostName")).click();
	            driver.findElement(By.id("hostName")).clear();
	            driver.findElement(By.id("hostName")).sendKeys("Nandhini");
	            driver.findElement(By.id("customerAssetTag")).clear();
	            driver.findElement(By.id("customerAssetTag")).sendKeys("1234567");
	            Thread.sleep(4000); 
	            driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[17]/div[1]/form/div[5]/div/ul/li/span/span[1]")).click();	            
	            Thread.sleep(4000);	 
	            
	            //selecting Address
	            driver.findElement(By.id("diffAddressLink")).click();
	            Thread.sleep(28000);
	            driver.findElement(By.xpath("//html/body/div[5]/div[2]/div/div/div/div[4]/div/div[1]/div[1]/table/tbody/tr[3]/td[3]/div/input")).clear();
	            driver.findElement(By.xpath("//html/body/div[5]/div[2]/div/div/div/div[4]/div/div[1]/div[1]/table/tbody/tr[3]/td[3]/div/input")).sendKeys(objSheet.getCell(8,i).getContents());
	            Thread.sleep(8000);
	            driver.findElement(By.xpath("//input[@name='btn_Select']")).click();
	            Thread.sleep(8000);         
	           
	            driver.findElement(By.cssSelector("#buttonContactFirst > button.button")).click();
	            Thread.sleep(4000);
	            new Select(driver.findElement(By.id("deviceContactType1"))).selectByVisibleText("Governance Specialist");
	            Thread.sleep(4000);
	            driver.findElement(By.id("deviceContactLink1")).click();
	            Thread.sleep(20000);
	            driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div[4]/div[1]/div/div[2]/table/tbody/tr[4]/td[2]/input")).click();
	            Thread.sleep(8000);
	            
	            
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
	            
	            System.out.println("Checking cancel button");
	            driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div/div/div/div/div/div/div[12]/div[3]/div[2]/button[2]")).click();
	            Thread.sleep(4000);  
	            driver.findElement(By.xpath("//html/body/div[6]/div/div[2]/button[2]")).click();	            
	            Thread.sleep(4000);
	            System.out.println("Checking Cancel button");
	            
	            
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
	    	   
	            
	            // Verify SR number            
	            driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/ul/li[1]/a/span")).click();
	            Thread.sleep(4000);
	            driver.findElement(By.xpath("//*[@id='allRequestHistory']/div[1]/table/tbody/tr[3]/td[1]/div/input")).sendKeys(Request_Number);
	            Thread.sleep(8000);
	            System.out.println("Verify SR# in Request page");
	            
	    	    driver.findElement(By.xpath("//html/body/div[3]/div[2]/div/div/div/div/div/div/div/div[13]/div/div[8]/div/div[2]/div/div[1]/div[2]/table/tbody/tr[2]/td[1]/a")).click();
	    	    Thread.sleep(7000);
	    	    
	            assertEquals("MADC", driver.findElement(By.id("area")).getText());
	            assertEquals("Install Asset", driver.findElement(By.id("subarea")).getText());	           
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




