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

public class WMS {
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

		Thread.sleep(3000);
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
		//Thread.sleep(2000);
		//driver.findElement(By.xpath("//div[@id='cutOffModal']//img[@class='cross-icon']")).click();
		
		/*WebDriverWait wait8 = new WebDriverWait(driver, 30);
		wait8.until(ExpectedConditions
				.presenceOfElementLocated(By.id("agree-tnc-generic|cb")));
		wait8.until(ExpectedConditions
				.visibilityOfElementLocated(By.id("agree-tnc-generic|cb")));*/
		
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
