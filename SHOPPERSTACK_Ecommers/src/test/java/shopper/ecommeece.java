package shopper;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ecommeece {
	private WebDriver driver;
	private WebDriverWait wait;
	@BeforeTest
	public void setup() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
		driver.get("https://www.shoppersstack.com/");
		driver.manage().window().maximize();
		 wait = new WebDriverWait(driver, 5);
	}
		@Test
		public void ecm() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[.='Login']")))).click();
		// --------registration from---------- //
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[.='Create Account']")))).click();
//
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='First Name']"))))
//				.sendKeys("raja");
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='Last Name']"))))
//				.sendKeys("guchhait");
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[.='Male']")))).click();
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='Phone Number']"))))
//				.sendKeys("7602574671");
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='Email Address']"))))
//				.sendKeys("mahadevgu14@gmail.com");
//
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='Password']")))).sendKeys("Raja@123");
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='Confirm Password']")))).sendKeys("Raja@123");
//		driver.findElement(By.xpath("//input[@id='Terms and Conditions']")).click();
//
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[.='Register']")))).click();

		// wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[.='Login']")))).click();
		// wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[.='Login']")))).click();
		// ------ login-----//
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
				"//h5[@class='MuiTypography-root MuiTypography-h5 MuiTypography-alignCenter css-stv47j']/../div[1]/div[1]/input"))))
				.sendKeys("mahadevgu14@gmail.com");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
				"//h5[@class='MuiTypography-root MuiTypography-h5 MuiTypography-alignCenter css-stv47j']/../div[2]/div[1]/input"))))
				.sendKeys("Raja@123");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[.='Login']")))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='search']"))))
				.sendKeys("forever21");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchBtn\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[.='add to cart'])[2]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("	//*[@id=\"cartIcon\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='buynow_fl']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='33102']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.='Proceed']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Cash On Delivery (COD)']")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.='Proceed']"))).click();
		WebElement ele = driver
				.findElement(By.xpath("//div[@id=\"root\"]/section[1]/article/div[3]/div[1]/button/span[1]/div"));
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", ele);
		}
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[.='My Orders']/../li[4]/div"))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[.='Invoice']")))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[.='Cancel Order']")))).click();
		
		WebElement elw = driver.findElement(By.xpath("//h3[.='Cancel Order']/../../div[2]/button[2]"));
         WebDriverWait wait1=new WebDriverWait(driver, 5);
         wait1.until(ExpectedConditions.visibilityOf(elw));
         elw.click();
         WebElement ele2 = driver
 				.findElement(By.xpath("//div[@id=\"root\"]/section[1]/article/div[3]/div[1]/button/span[1]/div"));
 		{
 			JavascriptExecutor js = (JavascriptExecutor) driver;
 			js.executeScript("arguments[0].click();", ele2);
 		}
 		WebElement log = driver.findElement(By.xpath("//div[@id=\"account-menu\"]/div[3]/ul/li[7]"));
 		  WebDriverWait stop=new WebDriverWait(driver, 5);
 		  stop.until(ExpectedConditions.visibilityOf(log));
 		  log.click();
		
		
	}
		@AfterTest
		public void end() {
			driver.quit();
		}

}
