package testNG_OBDX;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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

public class DEPOSITS {
	WebDriver driver;

	@BeforeMethod
	public void setup(ITestContext context) throws IOException, InterruptedException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_DEPOSITS.properties");
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
		driver.findElement(By.xpath("//a[@id='skiptour']")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'DEPOSITS')]")).click();



	}

	// Method 1: Break Fixed Deposit Operation
	@Test(groups = { "Deposits" })
	public void BreakFD() throws InterruptedException, IOException {

		// Click on hamburger icon
		driver.findElement(By.cssSelector("a#hamburger")).click();

		// Click on Deposit option in hamburger menu
		driver.findElement(By.xpath("//span[text()='Deposits']")).click();
		
		// Click on Deposit Details
		driver.findElement(By.xpath("//*[@id=\"DEPOSITS_TITLE~deposits-details\"]/a")).click();

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_DEPOSITS.properties");
		prop.load(fis);

		// Select Type FD
		driver.findElement(By.id("oj-combobox-choice-selectType")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-combobox-choice-selectType")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.id("oj-combobox-choice-selectType")).sendKeys(Keys.ENTER);

		// click on submit button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='oj-button-button oj-component-initnode']")).click();

		Thread.sleep(3000);

		// Click on View More
		driver.findElement(By.xpath(
				"//*[@id=\"maincontent\"]/div[2]/div/div/obdx-component/div[2]/div/div[1]/div[2]/div/div/div/div/a"))
				.click();
		int a = 1;
		while (a < 4) {
			driver.findElement(By.xpath(
					"//*[@id=\"maincontent\"]/div[2]/div/div/obdx-component/div[2]/div/div[1]/div[2]/div/div/div/div/a"))
					.click();
			a++;
		}

		Thread.sleep(2000);
		// Click on FD Details
		driver.findElement(By.cssSelector("#accordianHeader_38")).click();

		Thread.sleep(2000);
		// Click on Break FD
		driver.findElement(By.xpath("//span[contains(text(),'Break FD')]")).click();

		// Click on Yes bank tab

		driver.findElement(By.xpath("//span[@class='oj-button-text'][contains(text(),'YES BANK')]")).click();
		// driver.findElement(By.xpath("//*[@id=\"selectedItemDetails\"]/span[1]/label/oj-option/span")).click();

		// select account
		driver.findElement(By.cssSelector("div[class=oj-select-choice]")).click();

		// Thread.sleep(3000);
		// driver.findElement(By.cssSelector("div[aria-label='SHERLOCK -
		// 000390100017207']")).click();
		/*
		 * driver.findElement(By.cssSelector("div[class=oj-select-choice]")).click();
		 * int x = 1; while (x < 5) {
		 * driver.findElement(By.cssSelector("div[class=oj-select-choice]")).sendKeys(
		 * Keys.ARROW_DOWN); x++; }
		 * 
		 * Thread.sleep(2000);
		 * driver.findElement(By.cssSelector("div[class=oj-select-choice]")).sendKeys(
		 * Keys.ENTER);
		 */

		Thread.sleep(2000);
		driver.findElement(By.id("inputButton")).click();

		Thread.sleep(3000);
		// Take Screenshot in required path - verify screen
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Deposits//BREAK_FD_Verify.png"));

		Thread.sleep(5000);

		driver.findElement(By.id("inputButton")).click();

		driver.findElement(By.xpath("//input[@id='otp|input']")).sendKeys(prop.getProperty("otp"));
		;

		driver.findElement(By.cssSelector("oj-button[on-click='[[completeOTP]]']")).click();

		Thread.sleep(30000);
		// Take Screenshot in required path -- confirmation screen
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Deposits//BREAK_FD_Confirmation.png"));
		// closes current browser
		Thread.sleep(5000);
		driver.close();

	}

	// Method 2: Break Recurring Deposits Operation
	@Test(groups = { "Deposits" })
	public void BreakRD() throws InterruptedException, IOException {
		
		// Click on hamburger icon
		driver.findElement(By.cssSelector("a#hamburger")).click();

		// Click on Deposit option in hamburger menu
		driver.findElement(By.xpath("//span[text()='Deposits']")).click();

		// Click on Deposit Details
		driver.findElement(By.xpath("//*[@id=\"DEPOSITS_TITLE~deposits-details\"]/a")).click();

		// Select Type RD
		driver.findElement(By.id("oj-combobox-choice-selectType")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-combobox-choice-selectType")).sendKeys(Keys.ARROW_DOWN);
		int a = 1;
		while (a < 2) {
			driver.findElement(By.id("oj-combobox-choice-selectType")).sendKeys(Keys.ARROW_DOWN);
			a++;
		}
		driver.findElement(By.id("oj-combobox-choice-selectType")).sendKeys(Keys.ENTER);

		// click on submit button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='oj-button-button oj-component-initnode']")).click();

		Thread.sleep(3000);
		// Click on View More
		driver.findElement(By.xpath(
				"//*[@id=\"maincontent\"]/div[2]/div/div/obdx-component/div[2]/div/div[1]/div[2]/div/div/div/div/a"))
				.click();
		int b = 1;
		while (b < 3) {
			driver.findElement(By.xpath(
					"//*[@id=\"maincontent\"]/div[2]/div/div/obdx-component/div[2]/div/div[1]/div[2]/div/div/div/div/a"))
					.click();
			b++;
		}

		// Click on RD Details
		driver.findElement(By.xpath("//*[@id=\"accordianHeader_34\"]")).click();

		// Click on Break RD
		driver.findElement(By.xpath("//span[contains(text(),'Break RD')]")).click();

		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='deposit-details-item enter-detail-form']")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='oj-select-choice']")).click();

		Thread.sleep(3000);
		// select and Click on Account
		driver.findElement(By.xpath("//div[contains(text(),'00390100017207')]")).click();

		// Click on next
		driver.findElement(By.id("inputButton")).click();

		Thread.sleep(2000);
		// delink goal
		driver.findElement(By.xpath("//oj-button[@id='save']")).click();

		// Click on check-box for T&C
		// driver.findElement(By.xpath("//*[@id=\"agree-tnc-generic|cb\"]")).click();

		// Click on Proceed button
		driver.findElement(By.xpath("//*[@id=\"inputButton\"]/button")).click();

		// input OTP
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\FrameworkProject\\src\\test\\java\\OBDX_Testing\\datadrive_DEPOSITS.properties");
		prop.load(fis);
		driver.findElement(By.xpath("//*[@id=\"otp|input\"]")).sendKeys(prop.getProperty("otp"));

		Thread.sleep(3000);
		// Take Screenshot in required path - verify screen
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Deposits//BREAK_RD_Verify.png"));

		Thread.sleep(5000);
		// Click on proceed after entering OTP
		driver.findElement(By.xpath("//*[@class='otp-screen']/div/div/div/oj-button/button/div/span")).click();

		Thread.sleep(10000);
		// Take Screenshot in required path -- confirmation screen
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Deposits//BREAK_RD_Confirmation.png"));
		// closes current browser
		Thread.sleep(5000);
		driver.close();
	}

	// Method 3: Open Fixed Deposit Operation
	@Test(groups = { "Deposits" })
	public void OpenRegFD() throws InterruptedException, IOException {
		
		// Click on hamburger icon
		driver.findElement(By.cssSelector("a#hamburger")).click();

		// Click on Deposit option in hamburger menu
		driver.findElement(By.xpath("//span[text()='Deposits']")).click();

		// Click on Deposit Details
		driver.findElement(By.id("DEPOSITS_TITLE~fixed-deposits")).click();

		Thread.sleep(3000);

		// driver.findElement(By.className("oj-select-choice")).click();

		// click on Next Button
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//oj-button[@on-click='[[next]]']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//oj-button[@on-click='[[next]]']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='form-details cssfdformPadding']/div[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//oj-button[@on-click='[[next]]']")).click();

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_DEPOSITS.properties");
		prop.load(fis);

		// enter deposit amount
		driver.findElement(By.id("depositAmount")).sendKeys(prop.getProperty("fddepositamt"));

		// click on Next button on enter-details screen
		driver.findElement(By.id("inputButton")).click();

		Thread.sleep(3000);
		// Select Tenure year
		driver.findElement(By.id("tenureYear|input")).clear();
		driver.findElement(By.id("tenureYear")).sendKeys(prop.getProperty("tenureYR"));
		// select Months
		driver.findElement(By.id("tenureMonth|input")).clear();
		driver.findElement(By.id("tenureMonth")).sendKeys(prop.getProperty("tenureMM"));
		// select days
		driver.findElement(By.id("tenureDay|input")).clear();
		driver.findElement(By.id("tenureDay")).sendKeys(prop.getProperty("tenureDD"));
		// click next on tenure screen
		driver.findElement(By.id("inputButton")).click();

		// click on continue in tenure screen
		driver.findElement(By.cssSelector("oj-button[on-click*=continueWithTenure]")).click();

		// check box on verify screen
		Thread.sleep(3000);
		driver.findElement(By.id("agree-tnc-generic|cb")).click();

		// Take Screenshot in required path - verify screen
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Deposits//Open_FD_Verify.png"));

		Thread.sleep(5000);
		// next button
		driver.findElement(By.id("inputButton2")).click();
		// otp
		driver.findElement(By.xpath("//input[@placeholder='Enter OTP']")).sendKeys(prop.getProperty("otp"));
		// Click on proceed
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		Thread.sleep(30000);
		// Take Screenshot in required path -- confirmation screen
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Deposits//Open_FD_Confirmation.png"));
		// closes current browser
		Thread.sleep(5000);
		driver.close();

	}

	// Method 4: Open Recurring Deposit Operation
	@Test(groups = { "Deposits" })
	public void OpenRD() throws InterruptedException, IOException {

		// Click on hamburger icon
		driver.findElement(By.cssSelector("a#hamburger")).click();

		// Click on Deposit option in hamburger menu
		driver.findElement(By.xpath("//span[text()='Deposits']")).click();
		
		// Click on Deposit Details
		driver.findElement(By.id("DEPOSITS_TITLE~recurring-deposit")).click();
		
		Thread.sleep(3000);

		// click on Next Button
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("requestButton")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("requestButton")));
		// Thread.sleep(2000);
		Thread.sleep(3000);
		driver.findElement(By.id("requestButton")).click();

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_DEPOSITS.properties");
		prop.load(fis);

		// enter deposit amount
		driver.findElement(By.id("fromAmount|input")).sendKeys(prop.getProperty("rdamount"));

		// how many months
		driver.findElement(By.id("oj-select-choice-installmentMonth")).click();
		int x = 1;
		while (x < 2) {
			driver.findElement(By.id("oj-select-choice-installmentMonth")).sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.id("oj-select-choice-installmentMonth")).sendKeys(Keys.ENTER);

		// click on Next Button
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("requestButton")));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("requestButton")));
		// Thread.sleep(2000);
		Thread.sleep(3000);
		driver.findElement(By.id("requestButton")).click();

		// click on Proceed in verify screen Button
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.presenceOfElementLocated(By.id("inputButton")));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputButton")));

		// Take Screenshot in required path - verify screen
		Thread.sleep(2000);
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Deposits//Open_RD_Verify.png"));
		Thread.sleep(2000);

		driver.findElement(By.id("inputButton")).click();

		driver.findElement(By.xpath("//input[@placeholder='Enter OTP']")).sendKeys(prop.getProperty("otp"));
		// Click on proceed
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();

		Thread.sleep(30000);

		// Take Screenshot in required path -- confirmation screen
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Deposits//Open_RD_Confirmation.png"));
		// closes current browser
		Thread.sleep(2000);

		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());

		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());

		driver.close();

	}

	// Method 5: Open Quick Fixed Deposit Operation
	@Test(groups = { "Deposits" })
	public void OpenQuickFD() throws InterruptedException, IOException {
		
		// Click on hamburger icon
		driver.findElement(By.cssSelector("a#hamburger")).click();

		// Click on Deposit option in hamburger menu
		driver.findElement(By.xpath("//span[text()='Deposits']")).click();

		Thread.sleep(2000);
		// Click on QuickFIxedDeposit
		driver.findElement(By.id("DEPOSITS_TITLE~quick-fixed-deposits")).click();

		Thread.sleep(3000);

		// click on Next Button
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputButton")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputButton")));

		Thread.sleep(2000);

		// enter deposit amount
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_DEPOSITS.properties");
		prop.load(fis);
		driver.findElement(By.id("depositAmount")).sendKeys(prop.getProperty("quickfddepositamt"));

		Thread.sleep(3000);
		// Select Tenure year
		driver.findElement(By.id("tenureYear|input")).clear();
		driver.findElement(By.id("tenureYear")).sendKeys(prop.getProperty("tenureYR"));
		// select Months
		driver.findElement(By.id("tenureMonth|input")).clear();
		driver.findElement(By.id("tenureMonth")).sendKeys(prop.getProperty("tenureMM"));
		// select days
		driver.findElement(By.id("tenureDay|input")).clear();
		driver.findElement(By.id("tenureDay")).sendKeys(prop.getProperty("tenureDD"));

		// click on next
		driver.findElement(By.id("inputButton")).click();

		// check box on verify screen
		WebDriverWait wait1 = new WebDriverWait(driver, 40);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("agree-tnc-generic|cb")));
		Thread.sleep(2000);
		driver.findElement(By.id("agree-tnc-generic|cb")).click();

		// Take Screenshot in required path - verify screen
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Deposits//Open_QuickFD_Verify.png"));
		Thread.sleep(5000);

		// Click on proceed on verify
		driver.findElement(By.xpath("//oj-button[1]//button[1]")).click();

		// otp
		driver.findElement(By.xpath("//input[@placeholder='Enter OTP']")).sendKeys(prop.getProperty("otp"));
		// Click on proceed
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		Thread.sleep(30000);
		// Take Screenshot in required path -- confirmation screen
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Deposits//Open_QuickFD_Confirmation.png"));

		// closes current browser
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());
		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());

		driver.close();

	}

}