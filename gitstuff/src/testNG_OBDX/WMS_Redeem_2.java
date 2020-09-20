package testNG_OBDX;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WMS_Redeem_2 {
	WebDriver driver;

	@BeforeMethod
	public void setup(ITestContext context) throws IOException, InterruptedException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_WMS.properties");
		prop.load(fis);

		// get browser as chrome
		if (prop.getProperty("browser").equals("chrome")) {
			// WebDriver
			System.setProperty("webdriver.chrome.driver", "C:\\work\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (prop.getProperty("browser").equals("firefox")) {
			// WebDriver
			System.setProperty("webdriver.gecko.driver", "C:\\work\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else {
			// WebDriver
			driver = new InternetExplorerDriver();
		}

		// Set implicit wait of 3 seconds
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		context.setAttribute("driver", driver);
		// Maximize Window
		driver.manage().window().maximize();

		// Delete All cookies
		driver.manage().deleteAllCookies();

		// Open OBDX URL
		driver.get(prop.getProperty("url"));

		// Enter Username
		driver.findElement(By.id("login_username|input")).sendKeys(prop.getProperty("username"));

		// Enter password
		driver.findElement(By.name("password")).sendKeys(prop.getProperty("password"));

		// Click on Login Button
		driver.findElement(By.className("oj-button-text")).click();

		/*
		 * // OTP during Login
		 * driver.findElement(By.id("otp|input")).sendKeys(prop.getProperty("otp"));
		 * driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		 */

		Thread.sleep(3000);
		// skip tour
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("skiptour")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("skiptour")));
		driver.findElement(By.xpath("//a[@id='skiptour']")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'DEPOSITS')]")).click();

		Thread.sleep(2000);
		// Click on hamburger icon
		driver.findElement(By.cssSelector("a#hamburger")).click();

		Thread.sleep(3000);
		// Click on WMS option in hamburger menu
		driver.findElement(By.id("WEALTH_MANAGEMENT")).click();
		Thread.sleep(2000);
		// Click on WMS Dashboard
		driver.findElement(By.id("WEALTH_MANAGEMENT~wealth-management-dashboard")).click();

		// skip tour
		// WebDriverWait wait1 = new WebDriverWait(driver, 20);
		// wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("skiptour")));
		// wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("skiptour")));
		// driver.findElement(By.xpath("//a[@id='skiptour']")).click();

	}

	// Method 2: WMS Redeem By Amount Operation
	@Test(groups = { "WMS" })
	public void WMSRedeemByAmount() throws InterruptedException, IOException {

		// wait till wms title
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("comp-title")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("comp-title")));

		// click on transact and then purchase
		Thread.sleep(10000);
		driver.findElement(By.id("side-bar")).click();
		// Thread.sleep(5000);

		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("redeemfunds")));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("redeemfunds")));
		driver.findElement(By.xpath("//span[contains(text(),'Redeem')]")).click();

		// wait till submit
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.presenceOfElementLocated(By.id("inputButton")));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputButton")));

		// for selecting investment account
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccount']")).click();
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccount']")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccount']")).sendKeys(Keys.ENTER);

		// selection of fund house
		Thread.sleep(2000);
		driver.findElement(By.id("oj-select-choice-fund-houses")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("oj-select-choice-fund-houses")).sendKeys(Keys.ARROW_DOWN);
		int x = 1;
		while (x < 2) {
			driver.findElement(By.id("oj-select-choice-fund-houses")).sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.id("oj-select-choice-fund-houses")).sendKeys(Keys.ENTER);

		// scheme name
		Thread.sleep(2000);
		driver.findElement(By.id("oj-select-choice-scheme")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ARROW_DOWN);
		int y = 1;
		while (y < 2) {
			driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ARROW_DOWN);
			y++;
		}
		driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ENTER);

		// select folio
		Thread.sleep(2000);
		driver.findElement(By.id("oj-select-choice-folio-number")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ARROW_DOWN);
		int z = 1;
		while (z < 2) {
			driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ARROW_DOWN);
			z++;
		}
		driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ENTER);

		// click on Submit
		Thread.sleep(2000);
		driver.findElement(By.id("inputButton")).click();

		// select check-box of the scheme
		driver.findElement(By.cssSelector("input[type='checkbox']")).click();
		// click on next
		driver.findElement(By.id("next-button")).click();

		// wait till next
		WebDriverWait wait3 = new WebDriverWait(driver, 20);
		wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("next-button")));
		wait3.until(ExpectedConditions.visibilityOfElementLocated(By.id("next-button")));
		
		// Select redeem By Amount
		driver.findElement(By.xpath("//div[@class='oj-radioset-wrapper']/span[2]/span[1]")).click();
		
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_WMS.properties");
		prop.load(fis);

		// enter unit
		//driver.findElement(By.xpath("//input[@placeholder='Enter Units']")).sendKeys(prop.getProperty("units"));
		
		Thread.sleep(2000);
		// Enter Amount
		driver.findElement(By.xpath("//oj-input-text[@value='{{amount}}']")).sendKeys(prop.getProperty("redeemamount"),Keys.TAB,Keys.ENTER);
		
		// click on next
		//Thread.sleep(2000);
		//driver.findElement(By.className("oj-flex button-container")).click();
		//driver.findElement(By.id("next-button")).click();

		// close popup
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='cutOffModal']//img[@class='cross-icon']")).click();

		// wait till confirm on verify
		WebDriverWait wait4 = new WebDriverWait(driver, 20);
		wait4.until(ExpectedConditions.presenceOfElementLocated(By.id("confirmButton")));
		wait4.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmButton")));

		// checkbox for tnc
		Thread.sleep(2000);
		driver.findElement(By.id("agree-tnc-generic|cb")).click();

		Thread.sleep(2000); // Take Screenshot in required path - verify screen File
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//WMS//WMS_RedeemByAmount_Verify.png"));

		// click on confirm button
		driver.findElement(By.id("confirmButton")).click();
		// otp
		driver.findElement(By.xpath("//input[@id='otp|input']")).sendKeys(prop.getProperty("otp"));
		// Click on Proceed
		driver.findElement(By.xpath("//span[contains(text(),'PROCEED')]")).click();

		// confirm screen

		Thread.sleep(10000);
		File src2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src2, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//WMS//WMS_RedeemByAmount_Confirmation.png"));

		// closes current browser
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());
		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());
		driver.close();

	}

}
