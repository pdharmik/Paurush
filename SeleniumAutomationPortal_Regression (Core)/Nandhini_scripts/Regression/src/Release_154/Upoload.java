package Release_154;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;



import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class Upoload {
	
	 @Test
	  public void testUntitled() throws Exception {
		 
		//Open Browser	
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
	            
	            //Page count tab
	            driver.findElement(By.xpath("//div[@id='topnavigation']/ul/li[4]/a/span")).click();
	    	    Thread.sleep(8000);
	
    
    //Upload CSV file
    
    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/table/tbody/tr/td/div/div[3]/div/table/tbody/tr[6]/td[1]/div/button[2]")).click();
    Thread.sleep(8000);   
    
    driver.findElement(By.xpath("//html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[6]/div[4]/form/table/tbody/tr/td/div[2]/div[1]/table/tbody/tr[1]/td/nobr/span[2]/button")).click();
    Thread.sleep(2000);
   
    //put path to your image in a clipboard
    StringSelection ss = new StringSelection("Lexmark.csv.txt");
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
    

}
}
