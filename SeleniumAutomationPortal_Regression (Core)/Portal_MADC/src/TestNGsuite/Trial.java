package TestNGsuite;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class Trial {
  @Test
  public void DeviceFinderTest() {
  
	  //WebDriver driver= new FirefoxDriver();
	   WebDriver driver=new InternetExplorerDriver();
	   System.setProperty("webdriver.ie.driver","IEDriverServer.exe");
	 //Launch
		driver.get("https://portal-qa.lexmark.com//web//global-services//login?p_p_id=58&p_p_lifecycle=0&_58_redirect=%2Fgroup%2Fglobal-services%2F");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	//Loin
		driver.findElement(By.id("_58_login")).sendKeys("pd@qa.com");
		driver.findElement(By.id("_58_password")).sendKeys("Lexmark14");		
		driver.findElement(By.xpath("//*[@id='_58_fm']/div/button")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assertEquals("Liferay - Home", driver.getTitle());
	//Device Finder
		driver.findElement(By.xpath("//*[@id='topnavigation']/ul/li[2]/a")).click();
		assertEquals("Liferay - Device Finder", driver.getTitle());
		assertEquals("All Devices", driver.findElement(By.id("allAsset")).getText());
		assertEquals("All Devices", driver.findElement(By.id("linkViewAll")).getText());
		assertEquals("My Bookmarked Assets", driver.findElement(By.id("linkBookmarked")).getText());
		assertEquals("Device Location", driver.findElement(By.id("chlTreeLink_device")).getText());
		assertEquals("Customer Hierarchy", driver.findElement(By.id("chlTreeLink")).getText());
		assertEquals("XLS", driver.findElement(By.cssSelector("li.first")).getText());
		assertEquals("PDF", driver.findElement(By.xpath("//li[@onclick=\"return download('pdf')\"]")).getText());
		assertEquals("Print", driver.findElement(By.xpath("//li[@onclick='return print()']")).getText());
		assertEquals("Minimize All",driver.findElement(By.linkText("Minimize All")).getText());
		assertEquals("Expand All", driver.findElement(By.linkText("Expand All")).getText());
		assertEquals("Customize Columns", driver.findElement(By.id("headerMenuButton")).getText());
		assertEquals("Reset", driver.findElement(By.id("resetGridSetting")).getText());
	//Logoff & Close
		driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]")).click();
		driver.close();
		System.out.println("Test Case Passed");

  
  }
}
