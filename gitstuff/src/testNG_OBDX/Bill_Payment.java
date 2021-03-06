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

public class Bill_Payment {
	WebDriver driver;

	@BeforeMethod
	public void setup(ITestContext context) throws IOException, InterruptedException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_Bill_Payment.properties");
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
		//driver.findElement(By.xpath("//input[@placeholder='Enter OTP']")).sendKeys(prop.getProperty("otp"));
		//driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		
		// skip tour
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("skiptour")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("skiptour")));
		driver.findElement(By.xpath("//a[@id='skiptour']")).click();

		Thread.sleep(2000);
		// Click on Dashboard Tab
		driver.findElement(By.xpath("//*[@id=\'dashboard-tab-deposits\']/a/span")).click();
		// driver.findElement(By.xpath("//div[@class='header-container-yes
		// fixed-header']/div/div/a/img")).click();



	}

	// Method 1: Add Biller
	@Test(groups = { "BillPayment" })
	public void AddBiller() throws InterruptedException, IOException {
		
		Thread.sleep(2000);
		// Click on hamburger icon
		driver.findElement(By.cssSelector("a#hamburger")).click();

		// Click on Bill Payment option in hamburger menu
		driver.findElement(By.xpath("//span[contains(text(),'Bill Payments')]")).click();

		// Click on Add Biller
		driver.findElement(By.xpath("//span[contains(text(),'Add Billers')]")).click();
		Thread.sleep(5000);

		// Select Type Bill category
		driver.findElement(By.xpath("//div[@id='oj-select-choice-billerCategory']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-billerCategory']")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-billerCategory']")).sendKeys(Keys.ENTER);

		// Select Type Biller Location
		driver.findElement(By.xpath("//div[@id='oj-combobox-choice-billerLocation']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='oj-combobox-choice-billerLocation']")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//div[@id='oj-combobox-choice-billerLocation']")).sendKeys(Keys.ENTER);

		// Select Type Biller Name
		driver.findElement(By.xpath("//div[@id='oj-combobox-choice-biller']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='oj-combobox-choice-biller']")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//div[@id='oj-combobox-choice-biller']")).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		// Click on Next Button
		driver.findElement(By.xpath("//div[@id='oj-combobox-choice-biller']")).click();
		driver.findElement(By.xpath("//input[@id='biller|input']")).sendKeys(Keys.TAB);
		driver.findElement(
				By.xpath("//oj-button[@id='button2']//button[@class='oj-button-button oj-component-initnode']"))
				.sendKeys(Keys.ENTER);

		// input Consumer Number or Mobile Number or others|input
		Thread.sleep(3000);
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_Bill_Payment.properties");
		prop.load(fis);

		driver.findElement(By.xpath("//input[@id='Consumer Number or Mobile Number or others|input']"))
				.sendKeys(prop.getProperty("ConsumerOrMobileNumber"));

		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='EnterContribution Scheme']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Health Care')]")).click();

		driver.findElement(By.xpath("//input[@id='nickName|input']")).sendKeys(prop.getProperty("nickname"));

		// Click on NEXT
		driver.findElement(By.xpath("//span[text()='NEXT']")).click();

		Thread.sleep(2000);
		// Verify Screen and // click on Next Button
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputButton")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputButton")));
		
		Thread.sleep(2000); // Take Screenshot in required path - verify screen File
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1, new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//BillPayment//Add_Biller_Verify.png"));

		driver.findElement(By.id("inputButton")).click();
		
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='otp|input']")).sendKeys(prop.getProperty("otp"));
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();

		Thread.sleep(10000);
		File src2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src2, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//BillPayment//Add_Biller_Confirmation.png"));

		// closes current browser
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());
		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());
		driver.close();
	}

}
