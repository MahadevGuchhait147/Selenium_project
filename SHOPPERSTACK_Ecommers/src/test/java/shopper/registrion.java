package shopper;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class registrion {
	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
		driver.get("https://www.shoppersstack.com/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[.='Login']")))).click();
		// --------registration from---------- //
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[.='Create Account']")))).click();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='First Name']"))))
				.sendKeys("raja");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='Last Name']"))))
				.sendKeys("guchhait");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[.='Male']")))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='Phone Number']"))))
				.sendKeys("7602574671");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='Email Address']"))))
				.sendKeys("mahadevgu14@gmail.com");

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='Password']"))))
				.sendKeys("Raja@123");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='Confirm Password']"))))
				.sendKeys("Raja@123");
		driver.findElement(By.xpath("//input[@id='Terms and Conditions']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[.='Register']")))).click();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[.='Login']")))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[.='Login']")))).click();
	}
}
