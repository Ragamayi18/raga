package Cato;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class sample {

	public static void main(String[] args) {
		// TODO Auto-generated method 
        WebDriver driver=new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		driver.findElement(By.name("email")).sendKeys("chinnarigalla@gmail.com");
		driver.findElement(By.name("pass")).sendKeys("nagamani1318");
	}

}
