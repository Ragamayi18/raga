package fb6e;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class Orangehrm {
	
	WebDriver driver;
	ExtentReports reports;
	ExtentTest test;
	@BeforeTest
	  public void beforeTest() {
		reports=new ExtentReports("./Reports/dataprovider.html");
		System.setProperty("webdriver.chrome.driver", "D:\\Decemeber_Selenium\\drivers//chromedriver.exe");
		driver=new ChromeDriver();
	  }
	
  @Test(dataProvider = "dp")
  public void verifylogin (String username,String password) throws Throwable{
	  test=reports.startTest("dataprovider");
	  driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.name("txtPassword")).sendKeys("Qedge123!@#");
		driver.findElement(By.name("Submit")).click();
		Thread.sleep(5000);
		if(driver.getCurrentUrl().contains("dash"))
			
				{
			
			         Reporter.log("login success",true);
			         test.log(LogStatus.PASS, "loginpass");
				}
		else
		{

	         Reporter.log("loginfail",true);
	         test.log(LogStatus.FAIL,"loginfail");
			 
  }
  }
  @DataProvider
  public Object[][] dp() {
	  Object[][] login=new Object[5][2];
	  
	  login[0][0]="Admin";
	  login[0][1]="Qedge123!@#";
	  
	  login[1][0]="Admin";
	  login[1][1]="Qedge123!@#";
	  
	  login[2][0]="Admin";
	  login[2][1]="Admin";
	  
	  login[3][0]="Admin";
	  login[3][1]="Admin";
	  
	  login[4][0]="Admin";
	  login[4][1]="Admin";
	  return login;
	  
    
    };
  
  
  @AfterTest
  public void afterTest() {
	  reports.endTest(test);
	  reports.flush();
	  driver.close();
  }

}
