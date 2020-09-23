package feb5;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Implicit1 {
	WebDriver driver;
	@Test
	public void Login() {
		System.setProperty("webdriver.chrome.driver", "D:\\\\Decemeber_Selenium\\\\drivers\\\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.gmail.com");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.name("identifier")).sendKeys("ragamayigalla13");
		driver.findElement(By.className("CwaK9")).click();
		driver.findElement(By.name("password")).sendKeys("ragamayigalla13");

	}
}
