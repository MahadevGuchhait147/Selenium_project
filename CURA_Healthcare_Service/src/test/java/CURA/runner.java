package CURA;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class runner {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
		driver.get("https://katalon-demo-cura.herokuapp.com/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[.='Make Appointment']")))).click();
		WebElement demo = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		WebElement demo2 = driver.findElement(By.xpath("(//input[@class='form-control'])[2]"));
		WebElement user = driver.findElement(By.xpath("(//input[@class='form-control'])[3]"));
		WebElement pass = driver.findElement(By.xpath("(//input[@class='form-control'])[4]"));

		demo.sendKeys(Keys.CONTROL + "a");
		demo.sendKeys(Keys.CONTROL + "c");

		user.sendKeys(Keys.CONTROL + "v");
		demo2.sendKeys(Keys.CONTROL + "a");
		demo2.sendKeys(Keys.CONTROL + "c");
		pass.sendKeys(Keys.CONTROL + "v");
		driver.findElement(By.xpath("//button[.='Login']")).click();
		WebElement dp = driver.findElement(By.xpath("//select[@id='combo_facility']"));
		Select sc = new Select(dp);
		sc.selectByVisibleText("Seoul CURA Healthcare Center");
		driver.findElement(By.xpath("//input[@name='hospital_readmission']")).click();
		driver.findElement(By.xpath("//input[@id='radio_program_none']")).click();
		driver.findElement(By.xpath("//input[@name='visit_date']")).click();
		driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr[4]/td[4]")).click();
		driver.findElement(By.xpath("//textarea[@placeholder='Comment']")).sendKeys("i...love..selenium...");
		driver.findElement(By.xpath("//button[.='Book Appointment']")).click();

		WebElement home = driver.findElement(By.xpath("//a[.='Go to Homepage']"));
		WebDriverWait hm = new WebDriverWait(driver, 20);
		hm.until(ExpectedConditions.visibilityOf(home));
		home.click();
		driver.quit();

	}

}
