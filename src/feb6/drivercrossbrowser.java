package feb6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class drivercrossbrowser {
	WebDriver driver;

	String url="http://orangehrm.qedgetech.com/";
	String node="http://localhost:5550/wd/hub";
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String brw)throws Throwable
	{
		if(brw.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Decemeber_Selenium\\drivers\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.get(url);
			driver.manage().window().maximize();
		}
		else if(brw.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.firefox.driver", "D:\\Decemeber_Selenium\\drivers\\firefoxdriver.exe");
			driver=new FirefoxDriver();
			driver.get(url);	
		}
	}
	@Test
	public void login() throws Throwable
	{
		driver.findElement(By.name("txtUsername")).sendKeys("Admin");
		driver.findElement(By.name("txtPassword")).sendKeys("Qedge123!@#");
		driver.findElement(By.name("Submit")).click();
		Thread.sleep(5000);
		if(driver.getCurrentUrl().contains("dash"))
		{
			System.out.println("Login Success");
		}
		else

		{
			System.out.println("Login Fail");
		}
	}
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}

}
