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

public class TopicPlus_01_DatePicker {
  WebDriver driver ;
	
	
	@BeforeClass
	  public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//driver = new FirefoxDriver();
		
	  }
	
	@Test
  public void DatePicker() throws Exception {
		//Navigate to website Kendo
		driver.get("https://demos.telerik.com/kendo-ui/datetimepicker/index");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		WebElement btn_acceptCookies = driver.findElement(By.xpath("//a[text() = 'Accept Cookies']"));
		if(btn_acceptCookies.isDisplayed()) btn_acceptCookies.click(); // CLick accept Cookies
		
		//Handle when click accept cookies
		String windowParent = driver.getWindowHandle();
		Set<String> listWindow = driver.getWindowHandles();
		for(String current:listWindow) {
			if(!current.equals(windowParent)) driver.switchTo().window(current);
		}
		

		//Declare WebElement
		WebElement btn_datePicker = driver.findElement(By.xpath("//span[@aria-controls= 'datetimepicker_dateview']"));
		WebElement btn_timePicker = driver.findElement(By.xpath("//span[@aria-controls= 'datetimepicker_timeview']"));

		//CODE
		//Declare expected time
		String originalTime = "14/02/2023 08:00 AM";
		String groupTime[] = originalTime.split(" ");
		String groupDate[] = groupTime[0].split("/");


		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int diffYear = Integer.parseInt(groupDate[2])- currentYear;
		System.out.println(diffYear);

		//Select Date
		btn_datePicker.click(); //click date button
		WebElement btn_midCalendar = driver.findElement(By.xpath("//a[contains(@class, 'k-link k-nav-fast')]"));
		WebElement btn_previousYear = driver.findElement(By.xpath("//a[@aria-label = 'Previous']"));
		WebElement btn_nextYear = driver.findElement(By.xpath("//a[@aria-label = 'Next']"));
		btn_midCalendar.click(); //click middle panel of table

		if(diffYear>0){//Set year
			for(int i=0; i<diffYear;i++) btn_nextYear.click();
			Thread.sleep(1000);
		}
		else if(diffYear<0){
			for(int i=0; i<diffYear;i++) btn_previousYear.click();
			Thread.sleep(1000);
		}

		List<WebElement> list_Month = driver.findElements(By.xpath("//table[contains(@class,'year')]//td[@role= 'gridcell']"));
		for(WebElement expMonth:list_Month){//Select month and click
			int monthOrder = Integer.parseInt(groupDate[1])- 1;
			list_Month.get(monthOrder).click();
			Thread.sleep(1000);
			break;
		}

		List<WebElement> list_Date = driver.findElements(By.xpath("//table[contains(@class,'month')]//td[@role= 'gridcell' and not(contains(@class,'other-month'))]"));
		for(WebElement expDate:list_Date){//Select date and click
			int dateOrder = Integer.parseInt(groupDate[0])- 1;
			list_Date.get(dateOrder).click();
			Thread.sleep(1000);
			break;
		}

		btn_timePicker.click(); // Click time button
		Thread.sleep(1000);
		
		List<WebElement> list_Time = driver.findElements(By.xpath("//ul[@id = 'datetimepicker_timeview']/li"));
		String exp_Time = groupTime[1] + groupTime[2];
		for(WebElement expTime:list_Time){//Select date and click
			expTime.getText().equals(exp_Time);
			expTime.click();
			Thread.sleep(10000);
			break;
		}

		
		
  }
  

  @AfterClass
  public void afterClass() {
	driver.quit(); 
	  
	  
  }

}
