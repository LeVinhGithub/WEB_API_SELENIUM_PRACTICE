package Selenium_Plus;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.sql.Driver;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TopicPlus_02_DynamicTable {
  WebDriver driver ;
	
	
	@BeforeClass
	  public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//driver = new FirefoxDriver();
		
	  }
	
	@Test
  public void GetMaxValueDynamicTable() throws Exception {
		//Navigate to website Kendo
		driver.get("http://demo.guru99.com/test/table.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		
		String rowTable = "//tbody/tr";
		
		int max = 0;
		int r=1;
		
		System.out.println("=============================CODE===========================");
		List<WebElement> listRow = driver.findElements(By.xpath(rowTable));
		//Get number of row
		int numberRow = listRow.size();
		for(WebElement row:listRow) {
			List<WebElement> listCell = driver.findElements(By.xpath("//tbody/tr["+r +"]/td"));
			r+=1;
			for(WebElement cell:listCell) {
				if(max<ConStr2Int(cell.getText())) max = ConStr2Int(cell.getText());
			}
		}
		
		System.out.println("Max value in table = " + max);
		

		
		
  }
  

  @AfterClass
  public void afterClass() {
	driver.quit(); 
	  
	  
  }
  
  public int ConStr2Int(String value) {
	  int numberInt;
	  numberInt = Integer.parseInt(value);
	  return numberInt;
  }

}
