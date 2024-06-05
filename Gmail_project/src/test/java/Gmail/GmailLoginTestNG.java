package Gmail;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GmailLoginTestNG {
	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeTest
	public void stup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("https://mail.google.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 60);

	}

	@Test
	public void gmail() throws IOException {

		// Excel file paths
		String filePath = "C:\\Users\\MAHADEV\\OneDrive\\Desktop\\xcel.xlsx";
		String sheetName = "sheet1";

		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum();

		for (int i = 1; i <= rowCount; i++) {

			XSSFRow row = sheet.getRow(i);
			String username = row.getCell(0).getStringCellValue();
			String password = row.getCell(1).getStringCellValue();

			try {
				// Open Gmail

				// Login
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='identifierId']")))
						.sendKeys(username);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Next']")))
						.click();

				WebElement passwordInput = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Passwd']")));
				passwordInput.sendKeys(password);
				driver.findElement(By.xpath("//span[.='Next']")).click();

				// Check if login is successful
				boolean success = true;
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Compose')]")));
				} catch (Exception e) {
					success = false;
				}

				// Write result to Excel
				if (success) {
					row.createCell(2).setCellValue("Success");

					// Compose email and send
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Compose')]"))).click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class=\"agP aFw\"]")))
							.sendKeys("mahadevguchhait1999@gmail.com");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='aoT']")))
							.sendKeys("rose");

					driver.findElement(By.xpath("//input[@type='file']"))
							.sendKeys("C:\\Users\\MAHADEV\\Downloads\\Mahadev-Guchhait-FlowCV-Resume-20240416.pdf");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[.='Send'])[3]")));
					//send button
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[.='Send'])[3]"))).click();
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gb\"]/div[2]/div[2]/div[3]/div[3]")));
	
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, 'SignOut')]"))).click();

					WebElement myframe = driver.findElement(By.xpath("//iframe[@name='account']"));
					
                     
                     
					driver.switchTo().frame(myframe);
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//a[@aria-label='Important recommended actions available (opens a new tab)']")));
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Sign out')]"))).click();

					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();
					} catch (Exception e) {
					}

					driver.get("https://accounts.google.com/ServiceLogin");

					wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Use another account']")))
							.click();

				} else {
					row.createCell(2).setCellValue("Failed");
				}

			} catch (Exception e) {
				System.out.println("Error during login or email composition: " + e.getMessage());
				row.createCell(2).setCellValue("Failed ");

			}

		}

		FileOutputStream fos = new FileOutputStream(filePath);
		workbook.write(fos);
		fos.close();
		workbook.close();
		fis.close();
	}

	@AfterTest
	public void end() {
		if (driver != null) {
			driver.quit();
		}
	}
}