package testNG_OBDX;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WMS_Redeem_1 {
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
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 20);
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.id("skiptour")));
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("skiptour")));
		 * driver.findElement(By.xpath("//a[@id='skiptour']")).click();
		 */
		//Thread.sleep(3000);
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'DEPOSITS')]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'DEPOSITS')]")));
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

	// Method 1: WMS Redeem By Units Operation
	@Test(groups = { "WMS" })
	public void WMSRedeemByUnits() throws InterruptedException, IOException {

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

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_WMS.properties");
		prop.load(fis);

		// enter unit
		driver.findElement(By.xpath("//input[@placeholder='Enter Units']")).sendKeys(prop.getProperty("units"));
		// click on next
		driver.findElement(By.id("next-button")).click();

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
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//WMS//WMS_RedeemByUnits_Verify.png"));

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
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//WMS//WMS_RedeemByUnits_Confirmation.png"));

		// closes current browser
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());
		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());
		driver.close();

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
		// driver.findElement(By.xpath("//input[@placeholder='Enter
		// Units']")).sendKeys(prop.getProperty("units"));

		Thread.sleep(2000);
		// Enter Amount
		driver.findElement(By.xpath("//oj-input-text[@value='{{amount}}']")).sendKeys(prop.getProperty("redeemamount"),
				Keys.TAB, Keys.ENTER);

		// click on next
		// Thread.sleep(2000);
		// driver.findElement(By.className("oj-flex button-container")).click();
		// driver.findElement(By.id("next-button")).click();

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
		FileUtils.copyFile(src1, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//WMS//WMS_RedeemByAmount_Verify.png"));

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

	// Method 3: WMS Redeem By All Operation
	@Test(groups = { "WMS" })
	public void WMSRedeemByAll() throws InterruptedException, IOException {

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

		// Select redeem By All
		driver.findElement(By.xpath("//oj-radioset[1]/div[1]/span[3]/span[1]")).click();

		driver.findElement(By.id("next-button")).click();

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_WMS.properties");
		prop.load(fis);

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
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//WMS//WMS_RedeemByAll_Verify.png"));

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
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//WMS//WMS_RedeemByAll_Confirmation.png"));

		// closes current browser
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());
		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());
		driver.close();

	}

	// Method 4: Redeem by SWP Operation
	@Test(groups = { "WMS" })
	public void WMSRedeemBySWP() throws InterruptedException, IOException {

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

		// Select redeem By SWP
		driver.findElement(By.xpath("//div[@class='oj-radioset-wrapper']/span[4]/span[1]")).click();

		// enter swp amount
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_WMS.properties");
		prop.load(fis);
		driver.findElement(By.xpath("//oj-input-text[@value='{{amount}}']")).sendKeys(prop.getProperty("swpamt"));

		// select Frequency
		driver.findElement(By.xpath("//div[@id='oj-select-choice-frequency-0']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-frequency-0']")).sendKeys(Keys.ARROW_DOWN);
		int i = 1;
		while (i < 3) {
			driver.findElement(By.xpath("//div[@id='oj-select-choice-frequency-0']")).sendKeys(Keys.ARROW_DOWN);
			i++;
		}
		driver.findElement(By.xpath("//div[@id='oj-select-choice-frequency-0']")).sendKeys(Keys.ENTER);

		// select start date
		driver.findElement(By.xpath("//span[@title='Select Date.']")).click();
		driver.findElement(By.xpath("//a[@class='oj-enabled']")).click();

		// select Number of redemptions
		driver.findElement(By.xpath("//div[@id='oj-select-choice-installment-0']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-installment-0']")).sendKeys(Keys.ARROW_DOWN);
		int a = 1;
		while (a < 3) {
			driver.findElement(By.xpath("//div[@id='oj-select-choice-installment-0']")).sendKeys(Keys.ARROW_DOWN);
			a++;
		}
		driver.findElement(By.xpath("//div[@id='oj-select-choice-installment-0']")).sendKeys(Keys.ENTER);

		// Click on next button
		driver.findElement(By.id("next-button")).click();

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
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//WMS//WMS_RedeemBySWP_Verify.png"));

		// click on confirm button
		driver.findElement(By.id("confirmButton")).click();
		// otp
		driver.findElement(By.xpath("//input[@id='otp|input']")).sendKeys(prop.getProperty("otp"));
		// Click on Proceed
		driver.findElement(By.xpath("//span[contains(text(),'PROCEED')]")).click();

		// confirm screen

		Thread.sleep(3000);
		File src2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src2, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//WMS//WMS_RedeemBySWP_Confirmation.png"));

		// closes current browser
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());
		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());
		driver.close();

	}

	// Method 5: WMS Onetime Purchase Operation
	@Test(groups = { "WMS" })
	public void Onetime_Purchase() throws InterruptedException, IOException {

		// wait till wms title
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("comp-title")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("comp-title")));

		// click on transact and then purchase
		Thread.sleep(10000);
		driver.findElement(By.id("side-bar")).click();
		// Thread.sleep(5000);

		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("purchasefunds")));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("purchasefunds")));
		driver.findElement(By.xpath("//span[contains(text(),'Purchase')]")).click();

		// wait till next
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("oj-button[on-click*=initiatePurchase]")));
		wait2.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("oj-button[on-click*=initiatePurchase]")));

		// for selecting investment account
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccount']")).click();
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccount']")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccount']")).sendKeys(Keys.ENTER);

		// for selecting bank account
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("oj-select-one[data-id*='selectedAccount']")).click();
		driver.findElement(By.cssSelector("oj-select-one[data-id*='selectedAccount']")).sendKeys(Keys.ARROW_DOWN);
		int i = 1;
		while (i < 4) {
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("oj-select-one[data-id*='selectedAccount']")).sendKeys(Keys.ARROW_DOWN);
			i++;
		}
		driver.findElement(By.cssSelector("oj-select-one[data-id*='selectedAccount']")).sendKeys(Keys.ENTER);

		// selection of fund house
		driver.findElement(By.name("white-listed")).click();
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

		// consent pop-up
		Thread.sleep(3000);
		driver.findElement(By.id("agree1|cb")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"button2\"]/button")).click();

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

		// Enter Amount
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_WMS.properties");
		prop.load(fis);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//oj-input-text[@value='{{amount}}']"))
				.sendKeys(prop.getProperty("onetimepurchasemamount"));

		// click on NExt
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("oj-button[on-click*=initiatePurchase]")).click();
		WebElement element = driver.findElement(By.cssSelector("oj-button[on-click*=initiatePurchase]"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		// clcik on cross button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='cutOffModal']//img[@class='cross-icon']")).click();

		// verify screen
		Thread.sleep(3000);
		driver.findElement(By.id("agree-tnc-generic|cb")).click();
		// driver.findElement(By.id("Consentagree|cb")).click();

		Thread.sleep(2000); // Take Screenshot in required path - verify screen File
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//WMS//OnetimePurchase_Verify.png"));

		Thread.sleep(2000);
		driver.findElement(By.id("confirmButton")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter OTP']")).sendKeys(prop.getProperty("otp"));

		driver.findElement(By.cssSelector("oj-button[on-click*='OTP']")).click();

		// confirmation screen

		Thread.sleep(3000);
		// Take Screenshot in required path -- confirmation screen
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Purchase//Onetime_Purchase_Confirmation.png"));

		Thread.sleep(2000);
		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());
		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());
		// closes current browser
		Thread.sleep(2000);
		driver.close();

	}

	// Method 6: WMS Onetime Switch Operation
	@Test(groups = { "WMS" })
	public void Onetime_Switch() throws InterruptedException, IOException {

		// wait till wms title
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("comp-title")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("comp-title")));

		// click on transact and then purchase
		Thread.sleep(10000);
		driver.findElement(By.id("side-bar")).click();
		// Thread.sleep(5000);

		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("switchfunds")));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("switchfunds")));
		driver.findElement(By.xpath("//span[contains(text(),'Switch')]")).click();

		// wait till next
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("oj-button[on-click*=initiateSwitch]")));
		wait2.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("oj-button[on-click*=initiateSwitch]")));

		// for selecting investment account
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccounts']")).click();
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccounts']")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccounts']")).sendKeys(Keys.ENTER);

		// Switch-out-Scheme (Fund House)
		// selection of fund house
		driver.findElement(By.id("oj-select-choice-selectFundHouse")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-selectFundHouse")).sendKeys(Keys.ARROW_DOWN);
		int x = 1;
		while (x < 3) {
			driver.findElement(By.id("oj-select-choice-selectFundHouse")).sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.id("oj-select-choice-selectFundHouse")).sendKeys(Keys.ENTER);

		// selection of scheme name
		driver.findElement(By.id("oj-select-choice-scheme")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ARROW_DOWN);
		int a = 1;
		while (a < 2) {
			driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ARROW_DOWN);
			a++;
		}
		driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ENTER);

		// selection of folio
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-folioNumber")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-folioNumber")).sendKeys(Keys.ARROW_DOWN);
		int b = 1;
		while (b < 2) {
			driver.findElement(By.id("oj-select-choice-folioNumber")).sendKeys(Keys.ARROW_DOWN);
			b++;
		}
		driver.findElement(By.id("oj-select-choice-folioNumber")).sendKeys(Keys.ENTER);

		// Switch-in Scheme (Destination)
		// selection of fund category
		driver.findElement(By.id("oj-select-choice-fund-category")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-fund-category")).sendKeys(Keys.ARROW_DOWN);
		int y = 1;
		while (y < 5) {
			driver.findElement(By.id("oj-select-choice-fund-category")).sendKeys(Keys.ARROW_DOWN);
			y++;
		}
		driver.findElement(By.id("oj-select-choice-fund-category")).sendKeys(Keys.ENTER);

		// selection of scheme name
		driver.findElement(By.xpath("//oj-select-one[@value='{{schemeCode}}']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//oj-select-one[@value='{{schemeCode}}']")).sendKeys(Keys.ARROW_DOWN);
		int p = 1;
		while (p < 4) {
			driver.findElement(By.xpath("//oj-select-one[@value='{{schemeCode}}']")).sendKeys(Keys.ARROW_DOWN);
			p++;
		}
		driver.findElement(By.xpath("//oj-select-one[@value='{{schemeCode}}']")).sendKeys(Keys.ENTER);

		// consent pop-up
		Thread.sleep(2000);
		driver.findElement(By.id("agree1|cb")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//oj-validation-group[@id='scheme-consent']//oj-button[@id='button2']")).click();

		// Enter Units
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_WMS.properties");
		prop.load(fis);
		driver.findElement(By.xpath("//oj-input-text[@value='{{units}}']")).sendKeys(prop.getProperty("switchUnits"));

		// click on NExt
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("oj-button[on-click*=initiateSwitch]")).click();
		WebElement element = driver.findElement(By.cssSelector("oj-button[on-click*=initiateSwitch]"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		// clcik on cross button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='cutOffModal']//img[@class='cross-icon']")).click();
		// verify screen

		driver.findElement(By.id("agree-tnc-generic|cb")).click();
		// driver.findElement(By.id("Consentagree|cb")).click();

		Thread.sleep(2000); // Take Screenshot in required path - verify screen File
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//WMS//OnetimeSwitch_Verify.png"));

		Thread.sleep(2000);
		driver.findElement(By.id("confirmButton")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter OTP']")).sendKeys(prop.getProperty("otp"));

		driver.findElement(By.cssSelector("oj-button[on-click*='OTP']")).click();

		// confirmation screen

		Thread.sleep(3000);
		// Take Screenshot in required path -- confirmation screen
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Purchase//OnetimeSwitch_Confirmation.png"));

		Thread.sleep(2000);
		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());
		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());
		// closes current browser
		Thread.sleep(2000);
		driver.close();

	}

	// Method 7: WMS Recurring Switch STP Operation
	@Test(groups = { "WMS" })
	public void Recurring_Switch_STP() throws InterruptedException, IOException {

		// wait till WMS title
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("comp-title")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("comp-title")));

		// click on transact and then switch
		Thread.sleep(10000);
		driver.findElement(By.id("side-bar")).click();
		// Thread.sleep(5000);

		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("switchfunds")));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("switchfunds")));
		driver.findElement(By.xpath("//span[contains(text(),'Switch')]")).click();

		// wait till next
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("oj-button[on-click*=initiateSwitch]")));
		wait2.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("oj-button[on-click*=initiateSwitch]")));

		// Click on recurring
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("oj-option[value='RECURRING']")).click();

		// wait till next
		WebDriverWait wait3 = new WebDriverWait(driver, 20);
		wait3.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("oj-button[on-click*=initiateSwitchRecurring]")));
		wait3.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("oj-button[on-click*=initiateSwitchRecurring]")));

		// for selecting investment account
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccounts']")).click();
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccounts']")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccounts']")).sendKeys(Keys.ENTER);

		// Switch-out-Scheme (Fund House)
		// selection of fund house
		driver.findElement(By.id("oj-select-choice-selectFundHouse")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-selectFundHouse")).sendKeys(Keys.ARROW_DOWN);
		int x = 1;
		while (x < 3) {
			driver.findElement(By.id("oj-select-choice-selectFundHouse")).sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.id("oj-select-choice-selectFundHouse")).sendKeys(Keys.ENTER);

		// selection of scheme name
		driver.findElement(By.id("oj-select-choice-scheme")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ARROW_DOWN);
		int a = 1;
		while (a < 2) {
			driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ARROW_DOWN);
			a++;
		}
		driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ENTER);

		// selection of folio
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-folioNumber")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-folioNumber")).sendKeys(Keys.ARROW_DOWN);
		int b = 1;
		while (b < 2) {
			driver.findElement(By.id("oj-select-choice-folioNumber")).sendKeys(Keys.ARROW_DOWN);
			b++;
		}
		driver.findElement(By.id("oj-select-choice-folioNumber")).sendKeys(Keys.ENTER);

		// Click on next button
		Thread.sleep(2000);
		// driver.findElement(By.xpath("//div[@class='oj-flex
		// button-container']//oj-button[@id='button2']")).click();
		WebElement element = driver
				.findElement(By.xpath("//div[@class='oj-flex button-container']//oj-button[@id='button2']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		// Wait for Next button on set instruction screen

		// wait till next
		WebDriverWait wait4 = new WebDriverWait(driver, 20);
		wait4.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("oj-button[on-click*=SwitchRecurringReview]")));
		wait4.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("oj-button[on-click*=SwitchRecurringReview]")));

		// Switch-in Scheme (Destination) in "set instruction" screen
		// selection of scheme name
		driver.findElement(By.xpath("//oj-select-one[@value='{{schemeCodeSwitchIn}}']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//oj-select-one[@value='{{schemeCodeSwitchIn}}']")).sendKeys(Keys.ARROW_DOWN);
		int p = 1;
		while (p < 2) {
			driver.findElement(By.xpath("//oj-select-one[@value='{{schemeCodeSwitchIn}}']")).sendKeys(Keys.ARROW_DOWN);
			p++;
		}
		driver.findElement(By.xpath("//oj-select-one[@value='{{schemeCodeSwitchIn}}']")).sendKeys(Keys.ENTER);

		// consent pop-up
		Thread.sleep(2000);
		driver.findElement(By.id("agree1|cb")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//oj-validation-group[@id='scheme-consent']//oj-button[@id='button2']")).click();

		// selection of frequency
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-investmentFrequency")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-investmentFrequency")).sendKeys(Keys.ARROW_DOWN);
		int c = 1;
		while (c < 3) {
			driver.findElement(By.id("oj-select-choice-investmentFrequency")).sendKeys(Keys.ARROW_DOWN);
			c++;
		}
		driver.findElement(By.id("oj-select-choice-investmentFrequency")).sendKeys(Keys.ENTER);

		// Enter Units
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_WMS.properties");
		prop.load(fis);
		driver.findElement(By.xpath("//input[@id='txnUnits|input']")).sendKeys(prop.getProperty("switchUnits"));

		// select start date
		driver.findElement(By.cssSelector("span[title='Select Date.']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@class='oj-enabled']")).click();

		// Select No of transactions --
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-installments")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-installments")).sendKeys(Keys.ARROW_DOWN);
		int d = 1;
		while (d < 4) {
			driver.findElement(By.id("oj-select-choice-installments")).sendKeys(Keys.ARROW_DOWN);
			d++;
		}
		driver.findElement(By.id("oj-select-choice-installments")).sendKeys(Keys.ENTER);

		// Click on next button
		driver.findElement(By.xpath("//oj-button[@id='button2']")).click();

		// clcik on cross button
		// Thread.sleep(2000);
		// driver.findElement(By.xpath("//div[@id='cutOffModal']//img[@class='cross-icon']")).click();

		/*
		 * WebDriverWait wait8 = new WebDriverWait(driver, 30);
		 * wait8.until(ExpectedConditions
		 * .presenceOfElementLocated(By.id("agree-tnc-generic|cb")));
		 * wait8.until(ExpectedConditions
		 * .visibilityOfElementLocated(By.id("agree-tnc-generic|cb")));
		 */

		Thread.sleep(3000);
		WebElement ele = driver.findElement(By.id("agree-tnc-generic|cb"));
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", ele);

		Thread.sleep(2000); // Take Screenshot in required path - verify screen File
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//WMS//Recurring_Switch_STP_Verify.png"));

		Thread.sleep(2000);
		driver.findElement(By.id("inputButton")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Enter OTP']")).sendKeys(prop.getProperty("otp"));

		driver.findElement(By.cssSelector("oj-button[on-click*='completeOTP]']")).click();

		// confirmation screen
		Thread.sleep(3000);
		// Take Screenshot in required path -- confirmation screen
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Purchase//Recurring_Switch_STP_Confirmation.png"));

		Thread.sleep(2000);
		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());
		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());
		// closes current browser
		Thread.sleep(2000);
		driver.close();

	}

}
