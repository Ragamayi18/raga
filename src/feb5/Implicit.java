package feb5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Implicit {
	WebDriver driver;
	@Test
	public void Login() {
		System.setProperty("webdriver.chrome.driver", "D:\\\\Decemeber_Selenium\\\\drivers\\\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.gmail.com");
		WebDriverWait wait =new WebDriverWait(driver, 500);
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.name("identifier"))));
		driver.findElement(By.name("identifier")).sendKeys("ragamayigalla13@gmail.com");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.className("CwaK9")));
		driver.findElement(By.className("CwaK9")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(((By.name("password")))));
		driver.findElement(By.name("password")).sendKeys("nnghghtg");
		
		
		
		

	}

}

