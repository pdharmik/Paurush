package Contact;

import static org.junit.Assert.*;
import java.io.File;
import java.util.concurrent.TimeUnit;
import jxl.Sheet;
import jxl.Workbook;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



public class AddContact {
	
	@Test
	  public void testAddAContact() throws Exception {
		

		 File objFile = new File("Update SR.xls");
		    Workbook objWork = Workbook.getWorkbook(objFile);
			Sheet objSheet = objWork.getSheet("Contact"); 
			
		
			
			int rowNum = objSheet.getRows();
			int colNum = objSheet.getColumns();
			System.out.println("No of Rows "+rowNum);
			System.out.println("No of Cols "+colNum);		
			
		
			for (int i=1; i<rowNum; i++){	
		
		//			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
	     //           WebDriver driver = new InternetExplorerDriver();	
		
		
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				WebDriver driver = new ChromeDriver(); //Chrome*/
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
	     
	     
	                  
	         		    // Request tab
	         	       driver.findElement(By.xpath("//span[contains(.,'Requests')]")).click();
	         	      driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	         	      
	     
	    // Creating Add Contact
	    driver.findElement(By.cssSelector("a.createNewRequestPopUp > span")).click();
	     Thread.sleep(15000);
	     driver.findElement(By.xpath("//a[@href='managecontacts']")).click(); //click on the address link
		    driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
		   
		    System.out.println("Craeting new contact");
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

		    assertEquals("ADD A NEW CONTACT", driver.findElement(By.cssSelector("button.button")).getText());		   
		    assertEquals("All Account Contacts", driver.findElement(By.id("allContacts")).getText());		  
		    Thread.sleep(20000);
		    
	
		    
		    
		    // Enter the details
		    driver.findElement(By.cssSelector("button.button")).click();
		     Thread.sleep(18000);		 
		    System.out.println("Click Add a new contact");
	 
	    driver.findElement(By.id("firstname")).click();
	    driver.findElement(By.id("firstname")).clear();
	    driver.findElement(By.id("firstname")).sendKeys(objSheet.getCell(3,i).getContents());
	    driver.findElement(By.id("lastname")).click();
	    driver.findElement(By.id("lastname")).clear();
	    driver.findElement(By.id("lastname")).sendKeys(objSheet.getCell(4,i).getContents());
	    driver.findElement(By.id("workphone")).click();
	    driver.findElement(By.id("workphone")).clear();
	    driver.findElement(By.id("workphone")).sendKeys(objSheet.getCell(5,i).getContents());
	    driver.findElement(By.id("emailAddr")).click();
	    driver.findElement(By.id("emailAddr")).clear();
	    driver.findElement(By.id("emailAddr")).sendKeys(objSheet.getCell(6,i).getContents());
	    driver.findElement(By.id("addrLine1")).click();
	    driver.findElement(By.id("addrLine1")).clear();
	    driver.findElement(By.id("addrLine1")).sendKeys(objSheet.getCell(7,i).getContents());
	    driver.findElement(By.id("city")).click();
	    driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys(objSheet.getCell(8,i).getContents());
	      Thread.sleep(3000);                         
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[8]/div[3]/div/form/div[2]/div[2]/div/ul/li[5]/span/select")).click();
	     Thread.sleep(4000);
	     new Select(driver.findElement(By.id("country"))).selectByVisibleText("South Africa");
	    Thread.sleep(5000); 
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[8]/div[3]/div/form/div[2]/div[2]/div/ul/li[6]/span/select")).click();
	    driver.findElement(By.id("state")).click();
	    Thread.sleep(4000);
	    new Select(driver.findElement(By.id("state"))).selectByVisibleText("Gauteng");
	  
	    driver.findElement(By.id("zipCode")).click();
	    driver.findElement(By.id("zipCode")).clear();
	    driver.findElement(By.id("zipCode")).sendKeys(objSheet.getCell(11,i).getContents());
	    driver.findElement(By.id("building")).click();
	    driver.findElement(By.id("building")).clear();
	    driver.findElement(By.id("building")).sendKeys("1");
	    driver.findElement(By.id("floor")).click();
	    driver.findElement(By.id("floor")).clear();
	    driver.findElement(By.id("floor")).sendKeys("2");
	    driver.findElement(By.id("office")).click();
	    driver.findElement(By.id("office")).clear();
	    driver.findElement(By.id("office")).sendKeys("wipro");
	    driver.findElement(By.id("submitButtn")).click();
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[8]/div[3]/div/form/div[6]/div/div/div/p/span")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.id("submitButtn")).click();
	     Thread.sleep(10000);
	    driver.findElement(By.cssSelector("div.buttonContainer > button.button")).click();
	    
	    
	    //Verify details
	    
	    
	    driver.findElement(By.cssSelector("input[type=\"text\"]")).clear();
	    driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys(objSheet.getCell(4,i).getContents());
	    Thread.sleep(15000);
	   
	    
	    
	  //LogOff
  		driver.findElement(By.xpath("//*[@id='top-header-navigation']/ul/li[4]/a")).click();
  		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  		Thread.sleep(15000);

	   
	  		
			
		  	driver.close();
			}   
	}
}
		   


	
	
