package HW;

import static org.junit.Assert.assertEquals;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class HW_Invoice {

	
	 @Test
	  public void testCreateHardwareSRLease() throws Exception {
		 
	        
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			WebDriver driver = new ChromeDriver(); //Chrome*/
			driver.manage().window().maximize();
			
			
			
	 
	    // Login Portal
			 driver.get("https://portal-qa.lexmark.com/group/global-services");
			 driver.findElement(By.id("_58_login")).clear();
			    driver.findElement(By.id("_58_login")).sendKeys("kn@qa.com");
			    driver.findElement(By.id("_58_password")).clear();
			    driver.findElement(By.id("_58_password")).sendKeys("Lexmark15");
			    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
			    Thread.sleep(8000);
	            System.out.println("LogIn successful");
	            
	    
	    //Request tab
	            driver.findElement(By.xpath("//div[@id='topnavigation']/ul/li[5]/a/span")).click();
	    	    Thread.sleep(8000);
	    
	    
	    // Create New Request
	    driver.findElement(By.cssSelector("a.createNewRequestPopUp > span")).click();
	    Thread.sleep(8000);
	    driver.findElement(By.id("hardwareorder")).click();
	    Thread.sleep(15000);
	    
	    //Select Address and payment
	    driver.findElement(By.cssSelector("img.dhx_combo_img")).click();
	    driver.findElement(By.cssSelector("div.comboColor")).click();
	    Thread.sleep(5000);
	    new Select(driver.findElement(By.id("paymentType"))).selectByVisibleText("Invoice");
	    Thread.sleep(5000);
	    driver.findElement(By.id("applyButton")).click();
	    Thread.sleep(8000);
	    new Select(driver.findElement(By.id("productType"))).selectByVisibleText("Laser");
	    Thread.sleep(5000);
	    new Select(driver.findElement(By.id("productModel"))).selectByVisibleText("MS812dn");
	    Thread.sleep(5000);
	    driver.findElement(By.id("noAccButton")).click();
	    Thread.sleep(5000);
	    
	    //Enter the quantity
	 
	    driver.findElement(By.id("partQuantity1-LML6UKF")).click();
	    driver.findElement(By.id("partQuantity1-LML6UKF")).clear();
	    driver.findElement(By.id("partQuantity1-LML6UKF")).sendKeys("1");
	    Thread.sleep(5000);
	//    driver.findElement(By.xpath("//html/body/div[3]/div[2]/div[1]/div/div/div/div/div/div[2]/div[1]/div/div[12]/div[2]/div[1]/div/div[6]/div[2]/div/div[1]/div/div[2]/table/tbody/tr[2]/td[5]/div[2]/span/input").click();
	    Thread.sleep(5000);
	    driver.findElement(By.cssSelector("button.button.relativeI")).click();
	    Thread.sleep(8000);
	    
	    // Request Details Page
	    // additional informaton for this device
	    driver.findElement(By.id("custReferenceId")).clear();
	    driver.findElement(By.id("custReferenceId")).sendKeys("3152");
	    driver.findElement(By.id("costCenter")).clear();
	    driver.findElement(By.id("costCenter")).sendKeys("2351234");
	    driver.findElement(By.id("addtnlDescription")).clear();
	    driver.findElement(By.id("addtnlDescription")).sendKeys("description");
	    
	    
	    // Ship to address
	    driver.findElement(By.id("diffAddressLink")).click();
	    Thread.sleep(25000);
	//    driver.findElement(By.cssSelector("tr..odd_light > td > div.relativeR.autoWidth > input[name=\"btn_select\"]")).click();
	  	    Thread.sleep(8000);
	    driver.findElement(By.id("poNumber")).clear();
	    driver.findElement(By.id("poNumber")).sendKeys("23512345");
	    
	    //HE order
	    driver.findElement(By.cssSelector("span > span.checkbox_Span.check_Off")).click();
	    Thread.sleep(8000);
	    
	 
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div[2]/div/div/div[13]/div/div/div[8]/div[1]/div/div/div/div/form/div[1]/div/ul/li/span/textarea")).clear();
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div[2]/div/div/div[13]/div/div/div[8]/div[1]/div/div/div/div/form/div[1]/div/ul/li/span/textarea")).sendKeys("notes");
	    
	    driver.findElement(By.cssSelector("input.button.")).click();
	    Thread.sleep(2000);
	    
	    //put path to your image in a clipboard
	    StringSelection ss = new StringSelection("HardwareInstallTemplate");
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
        
	    driver.findElement(By.cssSelector("div.buttonContainer > button.button")).click();
	    Thread.sleep(8000);
	    
	    
	    // Review page
	    driver.findElement(By.cssSelector("button.button")).click();
	    Thread.sleep(8000); 
	    
	    // Inquiry_Confirmation Page
	    String Request_Number = driver.findElement(By.xpath("//span[@id='reqNumber']")).getText();
	    Thread.sleep(8000);
	    System.out.println(Request_Number);
	    System.out.println("storedVars['Request_Number'].replace(\"$\",\"\")");
	    
	    //Verify Email and Print button
	    
	    assertEquals("Email this page", driver.findElement(By.linkText("Email this page")).getText());
	    assertEquals("Print this page", driver.findElement(By.linkText("Print this page")).getText());
	    Thread.sleep(4000);
	    
	    // Verify SR number            
	    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/ul/li[1]/a/span")).click();
	    Thread.sleep(4000);
	    driver.findElement(By.xpath("//*[@id='allRequestHistory']/div[1]/table/tbody/tr[3]/td[1]/div/input")).sendKeys(Request_Number);
	    Thread.sleep(8000);
	    
}
}
