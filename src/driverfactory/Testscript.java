package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Applicationlayer.AddEmpPage;
import Applicationlayer.Adduserpage;
import Applicationlayer.Loginpage;
import Applicationlayer.Logoutpage;

public class Testscript {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	@BeforeTest
	public void generatereport()
	{
		report=new ExtentReports("./Reports/Pom.html");
	}
	@BeforeMethod
	public void setUp()
	{
	System.setProperty("webdriver.chrome.driver", "D:\\Decemeber_Selenium\\drivers\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().window().maximize();
	//access login page class
	Loginpage login=PageFactory.initElements(driver, Loginpage.class);
	//call login method
	login.verifyLogin("Admin", "Qedge123!@#");
	}
	@Test(priority=1)
	public void usercreation()throws Throwable
	{
		test=report.startTest("User Creation");
		Adduserpage user=PageFactory.initElements(driver, Adduserpage.class);
		user.verifyAdduser("Aarya Santhosh", "Akhi1234567", "Akhilesh@9000", "Akhilesh@9000");
	if(driver.getCurrentUrl().contains("viewSystemUsers"))	
	{
		test.log(LogStatus.PASS, "User Creation is success");
		Reporter
		.log("User Creation is success",true);
	}
	else
	{
		test.log(LogStatus.FAIL, "User Creation is Fail");
		Reporter.log("User Creation is Fail",true);	
	}
	}
	@Test(priority=0)
	public void empCreation()throws Throwable
	{
	test=report.startTest("Emp Creation");
	AddEmpPage emp=PageFactory.initElements(driver, AddEmpPage.class);
	emp.verifyEmp("Rita", "Meta");
	if(driver.getCurrentUrl().contains("empNumber"))
	{
		Reporter.log("Emp Creation Success",true);
		test.log(LogStatus.PASS, "Emp Creation Success");
		
	}
	else
	{
		Reporter.log("Emp Creation Fail",true);
		test.log(LogStatus.FAIL, "Emp Creation Fail");
	}
	}
	@AfterMethod
	public void tearDown()throws Throwable
	{
	report.endTest(test);
	report.flush();
	Logoutpage logout=PageFactory.initElements(driver, Logoutpage.class);
	logout.verifylogout();
	driver.close();

}
}
