package IRCTC;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TrainBookingTest {
	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 60);
	}

	@Test
	public void bookTrainTicket()
			throws EncryptedDocumentException, IOException, InterruptedException, TesseractException {
		driver.get("https://www.irctc.co.in/nget/");
		driver.manage().window().maximize();

		// Fill 'from' field
		WebElement from = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//input[@class='ng-tns-c57-8 ui-inputtext ui-widget ui-state-default ui-corner-all ui-autocomplete-input ng-star-inserted']")));
		from.sendKeys("MCA");
	    from.sendKeys(Keys.ARROW_DOWN);
		from.sendKeys(Keys.ENTER);

		// Fill 'to' field
		WebElement to = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//input[@class='ng-tns-c57-9 ui-inputtext ui-widget ui-state-default ui-corner-all ui-autocomplete-input ng-star-inserted']")));
		to.sendKeys("ypr");
		to.sendKeys(Keys.ARROW_DOWN);
		to.sendKeys(Keys.ENTER);

		// Select journey quota
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='journeyQuota']/div"))).click();
		List<WebElement> dp = driver
				.findElements(By.xpath("//*[@id=\"journeyQuota\"]/div/div[4]/div/ul/p-dropdownitem/li"));
		for (WebElement element : dp) {
			if (element.getText().equals("GENERAL")) {
				element.click();
				break;
			}
		}

		// Select journey date
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//input[@class='ng-tns-c58-10 ui-inputtext ui-widget ui-state-default ui-corner-all ng-star-inserted']")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='2']"))).click();

		// Select class
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='ui-dropdown-trigger ui-state-default ui-corner-right ng-tns-c65-11']")))
				.click();
		List<WebElement> sl = driver
				.findElements(By.xpath("//*[@id=\"journeyClass\"]/div/div[4]/div/ul/p-dropdownitem/li"));
		for (WebElement element : sl) {
			if (element.getText().equals("Sleeper (SL)")) {
				element.click();
				break;
			}
		}

		// Click Search button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.='Search']"))).click();

		// Click on a train
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@id='divMain']/div/app-train-list/div[4]/div/div[5]/div/div[1]/app-train-avl-enq/div[1]/div[5]/div[1]/table/tr/td[1]/div/div[1]")))
				.click();

		// Click to check availability
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@id=\"divMain\"]/div/app-train-list/div[4]/div/div[5]/div/div[1]/app-train-avl-enq/div[1]/div[7]/div[1]/div[3]/table/tr/td[2]/div/div[2]")))
				.click();

		// Click 'Book Now'
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Book Now']")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Yes']"))).click();

		// Login
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='User Name']")))
				.sendKeys("RAJAGUCHHa");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Password']")))
				.sendKeys("Mahadev@raja78");

		// Captcha handling
		WebElement captchaImage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='captcha-img']")));
		File src = captchaImage.getScreenshotAs(OutputType.FILE);
		String path = "C:\\Users\\MAHADEV\\eclipse-workspace\\IRCTC_TIKIT_BOOKING\\captcha_image\\captcha.png";
		FileHandler.copy(src, new File(path));

		ITesseract image = new Tesseract();
		String captchaText = image.doOCR(new File(path));
		System.out.println(captchaText);

		// Remove unnecessary characters from captcha text
		captchaText = captchaText.replaceAll("\\s", "");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='captcha']")))
				.sendKeys(captchaText);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='SIGN IN']")))
				.click();

		// Fill passenger details
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Passenger Name']")))
				.sendKeys("Mahadev Guchhait");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Age']")))
				.sendKeys("25");

		WebElement gender = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//select[@formcontrolname='passengerGender']")));
		Select selectGender = new Select(gender);
		selectGender.selectByVisibleText("Male");

		WebElement country = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@readonly='mpUser']")));
		Select selectCountry = new Select(country);
		selectCountry.selectByVisibleText("India");

		WebElement berth = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//select[@formcontrolname='passengerBerthChoice']")));
		Select selectBerth = new Select(berth);
		selectBerth.selectByVisibleText("Side Lower");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='autoUpgradation']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"2\"]/div/div[2]"))).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"psgn-form\"]/form/div/div[1]/div[14]/div/button[2]")))
				.click();

		// Second captcha handling
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"review\"]/div[1]/div[5]/div")));
		Thread.sleep(2000);
		WebElement secondCaptchaImage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='captcha-img']")));
		File src2 = secondCaptchaImage.getScreenshotAs(OutputType.FILE);
		String path1 = "C:\\Users\\MAHADEV\\eclipse-workspace\\IRCTC_TIKIT_BOOKING\\second_captcha\\captcha2.png";
		FileHandler.copy(src2, new File(path1));

		ITesseract image2 = new Tesseract();
		String captchaText2 = image2.doOCR(new File(path1));
		System.out.println(captchaText2);

		// Remove unnecessary characters from captcha text
		captchaText2 = captchaText2.replaceAll("\\s", "");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='captcha']")))
				.sendKeys(captchaText2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[.='Continue '])[1]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[.='Pay & Book '])[2]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='vpaCheck']")))
				.sendKeys("7602574671@ybl");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='payment-btn payActive']")))
				.click();
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
