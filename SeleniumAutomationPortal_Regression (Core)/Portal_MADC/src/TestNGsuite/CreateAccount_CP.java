package TestNGsuite;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.AssertJUnit;

import static org.testng.AssertJUnit.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class CreateAccount_CP {
  @Test
  public void LoginToCP() throws BiffException, IOException {
	  
	 //Read Excel
	 
	  File objFile = new File("data.xls");		
	  Workbook objWork= Workbook.getWorkbook(objFile);
	  Sheet objSheet= objWork.getSheet(1);
	  //get the row & col count
	  int rownum=objSheet.getRows();
	  int colnum=objSheet.getColumns();
	  System.out.println("rowcount = "+ rownum);
	  System.out.println("colcount = "+ colnum);
	  System.out.println("-----------------------");
	  
	  
	  for (int i=0;i<rownum;i++){
		  for (int j=0;j<colnum;j++){
			  Cell objCell=objSheet.getCell(i,j);
			  System.out.println(objCell.getContents());
			  }
	  }
	  	
	  	/*
	  	
	//WebDriver driver= new FirefoxDriver();
	   WebDriver driver=new InternetExplorerDriver();
	   System.setProperty("webdriver.ie.driver","IEDriverServer.exe");
	 //Launch
		driver.get("https://portal-qa.lexmark.com//web//global-services//login?p_p_id=58&p_p_lifecycle=0&_58_redirect=%2Fgroup%2Fglobal-services%2F");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	//Loin
		driver.findElement(By.id("_58_login")).sendKeys("laqa");
		driver.findElement(By.id("_58_password")).sendKeys("Welcome@12");		
		driver.findElement(By.xpath("//*[@id='_58_fm']/div/button")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		AssertJUnit.assertEquals("Liferay - Home", driver.getTitle());
	
	  
  */
}
}