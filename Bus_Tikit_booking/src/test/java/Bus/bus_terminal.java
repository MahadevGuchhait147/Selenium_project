package Bus;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class bus_terminal {

	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.busonlineticket.com/booking/bus-tickets.aspx");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 60);
	}

	@Test
	public void bookBusTicket() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='txtOriginGeneral']"))).click();
		List<WebElement> sl = driver.findElements(By.xpath("//div[@class='form-group flex-grow-1 m-0']//li[1]//ul/li"));
		for (WebElement element : sl) {
			if (element.getText().equals("Cameron Highlands")) {
				element.click();
				break;
			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='txtDestinationGeneral']")))
				.click();
		List<WebElement> sl2 = driver.findElements(By.xpath("//*[@id=\"divToList\"]/ul/li[1]/ul/li"));
		for (WebElement element : sl2) {
			if (element.getText().equals("Kuala Lumpur")) {
				element.click();
				break;
			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"txtDepartDateBooking\"]")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[5]/a")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"btnBusSearchNewGeneral\"]")))
				.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='cheapest']")));

		List<WebElement> busList = driver.findElements(By.xpath("//*[@id=\"subtab1\"]/table/tbody/tr"));
		WebElement cheapestBus = busList.get(0);
		double minPrice = Double.MAX_VALUE;

		for (WebElement bus : busList) {
			String priceText = bus.findElement(By.className("busprice1")).getText().replace("RM", "").trim();
			double price = Double.parseDouble(priceText);
			if (price < minPrice) {
				minPrice = price;
				cheapestBus = bus;
			}
		}

		// Select the cheapest bus
		cheapestBus.findElement(By.xpath("//a[@class=\"btn btn-orange selectseatbutton\"]")).click();

		// Wait for seat layout to load
		 // Adjust the sleep time as necessary

		// Select seats (maximum 3 seats or all if less than 3 available)
		List<WebElement> availableSeats = driver.findElements(
				By.xpath("//*[@id=\"subtab1\"]/table/tbody/tr[2]/td/div/table/tbody/tr/td[1]/div/table/tbody/tr/td"));
		int seatsToSelect = Math.min(availableSeats.size(), 12);
		for (int i = 5; i < seatsToSelect; i++) {
			availableSeats.get(i).click();
		}

		// Click on Proceed
		driver.findElement(By.xpath("//input[@class='seatproceed']")).click();
		

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@class='payment_textName form-control pay-form-control']")))
				.sendKeys("Mahadev Guchhait");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@class='payment_txtPhoneLogin form-control pay-form-control']")))
				.sendKeys("9382418624");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@class='payment_txtEmail form-control pay-form-control']")))
				.sendKeys("mahadevguchhait1999@gmail.com");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.='Next']"))).click();

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='payment_btnProceedPayment']")))
				.click();

		
		WebElement pop = driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]"));
		String text = pop.getText();
		System.out.println(text);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[.='OK'])[2]"))).click();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
