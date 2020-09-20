package OBDX_Testing;

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

public class DEPOSITS {
	WebDriver driver;
	
	@BeforeMethod
	public void setup(ITestContext context) throws IOException, InterruptedException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\FrameworkProject\\src\\test\\java\\OBDX_Testing\\datadrive_DEPOSITS.properties");
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

		// OTP during Login
		driver.findElement(By.id("otp|input")).sendKeys(prop.getProperty("otp"));
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		
		Thread.sleep(5000);
		// Click on Dashboard Tab
		driver.findElement(By.xpath("//*[@id=\'dashboard-tab-deposits\']/a/span")).click();
		// driver.findElement(By.xpath("//div[@class='header-container-yes
		// fixed-header']/div/div/a/img")).click();

		// Click on hamburger icon
		driver.findElement(By.cssSelector("a#hamburger")).click();

		// Click on Deposit option in hamburger menu
		driver.findElement(By.xpath("//span[text()='Deposits']")).click();

		// Click on Deposit Details
		driver.findElement(By.xpath("//*[@id=\"DEPOSITS_TITLE~deposits-details\"]/a")).click();

	}

	// Method 1: Break Fixed Deposit Operation
	@Test(groups= {"Deposits"})
	public void BreakFD() throws InterruptedException, IOException {

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

		// Click on FD Details
		driver.findElement(By.xpath("//*[@id=\"accordianHeader_38\"]")).click();

		Thread.sleep(2000);
		// Click on Break FD
		driver.findElement(
				By.xpath("//span[contains(text(),'Break FD')]")).click();

		// Click on Yes bank tab
		driver.findElement(By.xpath("//*[@id=\"selectedItemDetails\"]/span[1]/label/oj-option/span")).click();

		Thread.sleep(5000);
		// Click on Dropdown

		driver.findElement(By.xpath("//div[@role='combobox']")).click();
		// driver.findElement(By.xpath("//*[@class='oj-flex
		// oj-flex-items-pad']/div/account-input/obdx-component/div/div/oj-select-one/div/div/span")).click();

		Thread.sleep(5000);
		// select and Click on Account
		driver.findElement(By.xpath("//*[@class='oj-listbox-results']/li[2]/ul/li/ul/li")).click();

		Thread.sleep(5000);
		// Click on Next
		driver.findElement(By.xpath("//*[@id=\"inputButton\"]/button")).click();

		// Click on check-box for T&C
		driver.findElement(By.xpath("//*[@id=\"agree-tnc-generic|cb\"]")).click();

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
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Deposits//BREAK_FD_Verify.png"));
		
		Thread.sleep(10000);
		// Click on proceed after entering OTP
		driver.findElement(By.xpath("//*[@class='otp-screen']/div/div/div/oj-button/button/div/span")).click();

		Thread.sleep(20000);
		// Take Screenshot in required path -- confirmation screen
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Deposits//BREAK_FD_Confirmation.png"));

		// closes current browser
		Thread.sleep(10000);
		driver.close();

	}

	// Method 2: Break Recurring Deposits Operation
	@Test(groups= {"Deposits"})
	public void BreakRD() throws InterruptedException, IOException {

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
		while (b < 4) {
		driver.findElement(By.xpath(
				"//*[@id=\"maincontent\"]/div[2]/div/div/obdx-component/div[2]/div/div[1]/div[2]/div/div/div/div/a"))
				.click();
		b++;
		}
		
		
		// Click on RD Details
		driver.findElement(By.xpath("//*[@id=\"accordianHeader_38\"]")).click();

		// Click on Break RD
		driver.findElement(By.xpath("//span[contains(text(),'Break RD')]")).click();

		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='deposit-details-item enter-detail-form']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='oj-select-choice']")).click();
		
		
		Thread.sleep(3000);
		// select and Click on Account
		driver.findElement(By.xpath("//div[contains(text(),'063190100001003')]")).click();

		// Click on next
		driver.findElement(By.id("inputButton")).click();

		// Click on check-box for T&C
		driver.findElement(By.xpath("//*[@id=\"agree-tnc-generic|cb\"]")).click();

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

		Thread.sleep(10000);
		// Click on proceed after entering OTP
		driver.findElement(By.xpath("//*[@class='otp-screen']/div/div/div/oj-button/button/div/span")).click();

		Thread.sleep(30000);
		// Take Screenshot in required path -- confirmation screen
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Deposits//BREAK_RD_Confirmation.png"));
		// closes current browser
		Thread.sleep(10000);
		driver.close();
	}

}