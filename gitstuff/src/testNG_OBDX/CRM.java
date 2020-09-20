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

public class CRM {
	WebDriver driver;

	@BeforeMethod
	public void setup(ITestContext context) throws IOException, InterruptedException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_CRM.properties");
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
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.id("skiptour")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("skiptour")));
		//driver.findElement(By.xpath("//a[@id='skiptour']")).click();

		Thread.sleep(3000);
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'DEPOSITS')]")));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'DEPOSITS')]")));
		driver.findElement(By.xpath("//span[contains(text(),'DEPOSITS')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'Customer Service')]")).click();

	}

	// Method 1: CRM Other Requests Operation
	@Test(groups = { "CRM" })
	public void CRM_Other_Request() throws InterruptedException, IOException {

		driver.findElement(By.xpath("//span[contains(text(),'Create New Request')]")).click();

		// Thread.sleep(5000);
		// driver.findElement(By.xpath("//span[text()='Credit Card']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(text(),'CATEGORY_001')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("a[class=card-value]")).click();

		Thread.sleep(2000);

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_CRM.properties");
		prop.load(fis);

		driver.findElement(By.xpath("//input[@placeholder='test']")).sendKeys(prop.getProperty("test"));
		driver.findElement(By.xpath("//input[@id='datePicker|input']")).click();

		driver.findElement(By.xpath("//a[contains(text(),'30')]")).click();

		/*
		 * driver.findElement(By.xpath("//input[@value='Mumbai']")).click();
		 * 
		 * driver.findElement(By.id("oj-select-choice-dropList")).click(); int a = 1;
		 * while (a < 2) {
		 * driver.findElement(By.id("oj-select-choice-dropList")).sendKeys(Keys.
		 * ARROW_DOWN); a++; }
		 * driver.findElement(By.id("oj-select-choice-dropList")).sendKeys(Keys.ENTER);
		 */

		driver.findElement(By.xpath("//oj-button[@id='next-button']")).click();

		Thread.sleep(2000);
		/*
		 * // select t and c driver.findElement(By.id("agree-tnc-generic|cb")).click();
		 */

		driver.findElement(By.id("save-button")).click();

		driver.findElement(By.xpath("//input[@placeholder='Enter OTP']")).sendKeys(prop.getProperty("otp"));

		// driver.findElement(By.xpath("//oj-button[@on-click='[[completeOTP]]']")).click();
		// click on proceed

		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();

		Thread.sleep(5000);
		// Take Screenshot in required path -- confirmation screen
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//CRM//CRM_Other_Requests.png"));

		// closes current browser
		Thread.sleep(3000);
		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());
		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());
		
		driver.close();

	}

	// Method 2: CRM Apply for product and services operation
	@Test(groups = { "CRM" })
	public void CRM_ApplyForProductAndServices() throws InterruptedException, IOException {

		driver.findElement(By.xpath("//span[contains(text(),'Apply For Products & Services')]")).click();

		// select product or service
		Thread.sleep(5000);
		driver.findElement(By.xpath("//oj-select-one[@placeholder='Select Product / Service Name']")).click();
		int x = 1;
		while (x < 6) {
			driver.findElement(By.xpath("//oj-select-one[@placeholder='Select Product / Service Name']"))
					.sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.xpath("//oj-select-one[@placeholder='Select Product / Service Name']"))
				.sendKeys(Keys.ENTER);

		// Enter Start date
		/*
		 * driver.findElement(By.xpath("//input[@id='calldate|input']")).click();
		 * 
		 * Thread.sleep(2000);
		 * driver.findElement(By.xpath("//a[contains(text(),'31')]")).click();
		 * 
		 * // select call time
		 * 
		 * Thread.sleep(5000);
		 * driver.findElement(By.xpath("//oj-select-one[@id='calltime']")).click(); int
		 * y = 1; while (y < 6) {
		 * driver.findElement(By.xpath("//oj-select-one[@id='calltime']")).sendKeys(Keys
		 * .ARROW_DOWN); y++; }
		 * driver.findElement(By.xpath("//oj-select-one[@id='calltime']")).sendKeys(Keys
		 * .ENTER);
		 * 
		 * //select Ac_no Properties prop = new Properties(); FileInputStream fis = new
		 * FileInputStream(
		 * "C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_CRM.properties"
		 * ); prop.load(fis);
		 * driver.findElement(By.xpath("//input[@placeholder='Ac_No']")).sendKeys(prop.
		 * getProperty("account"));
		 * 
		 * // select City name
		 * driver.findElement(By.xpath("//input[@value='Mumbai']")).click();
		 * 
		 * // select country
		 * driver.findElement(By.xpath("//input[@value='India']")).click();
		 * 
		 * Thread.sleep(2000); // select profession
		 * driver.findElement(By.xpath("//div[@id='oj-select-choice-dropList']")).click(
		 * ); Thread.sleep(2000); int z = 1; while (z < 3) {
		 * driver.findElement(By.xpath("//div[@id='oj-select-choice-dropList']")).
		 * sendKeys(Keys.ARROW_DOWN); z++; }
		 * driver.findElement(By.xpath("//div[@id='oj-select-choice-dropList']")).
		 * sendKeys(Keys.ENTER);
		 */

		// click on SUBMIT
		driver.findElement(By.xpath("//span[text()='SUBMIT']")).click();

		// Enter OTP

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_CRM.properties");
		prop.load(fis);

		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='otp|input']")).sendKeys(prop.getProperty("otp"));

		// click on proceed

		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();

		Thread.sleep(5000);
		// Take Screenshot in required path -- confirmation screen
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//CRM//CRM_ApplyForProductAndServices.png"));

		// closes current browser
		Thread.sleep(3000);
		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());
		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());
		driver.close();

	}

	// Method 3: CRM Report Fraud
	@Test(groups = { "CRM" })
	public void CRM_Report_Fraud() throws InterruptedException, IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_CRM.properties");
		prop.load(fis);

		driver.findElement(By.xpath("//span[contains(text(),'Report Fraud')]")).click();

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div[class=oj-select-choice]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Debit Card']")).click();
		// Thread.sleep(2000);
		// driver.findElement(By.cssSelector("div[class=oj-select-choice]")).sendKeys(Keys.ARROW_DOWN,Keys.ENTER);

		// select debit card
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-debitCardNumber']")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-debitCardNumber']")).click();
		int y = 1;
		while (y < 3) {
			driver.findElement(By.xpath("//div[@id='oj-select-choice-debitCardNumber']")).sendKeys(Keys.ARROW_DOWN);
			y++;
		}
		driver.findElement(By.xpath("//div[@id='oj-select-choice-debitCardNumber']")).sendKeys(Keys.ENTER);

		// select mode of trxn
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-transactionMode1']")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-transactionMode1']")).click();
		int z = 1;
		while (z < 3) {
			driver.findElement(By.xpath("//div[@id='oj-select-choice-transactionMode1']")).sendKeys(Keys.ARROW_DOWN);
			z++;
		}
		driver.findElement(By.xpath("//div[@id='oj-select-choice-transactionMode1']")).sendKeys(Keys.ENTER);

		// select Dispute reason

		driver.findElement(By.xpath("//oj-select-one[@placeholder='Select Dispute Reason']")).click();
		int a = 1;
		while (a < 3) {
			driver.findElement(By.xpath("//oj-select-one[@placeholder='Select Dispute Reason']"))
					.sendKeys(Keys.ARROW_DOWN);
			a++;
		}
		driver.findElement(By.xpath("//oj-select-one[@placeholder='Select Dispute Reason']")).sendKeys(Keys.ENTER);

		// dispute trxn date
		driver.findElement(By.xpath("//input[@id='datePicker|input']")).click();
		driver.findElement(By.xpath("//a[@class='oj-enabled oj-selected']")).click();

		// Merchent establishment name
		driver.findElement(By.xpath("//input[@placeholder='Merchant establishment name']"))
				.sendKeys(prop.getProperty("mername"));

		// trxn amt
		driver.findElement(By.xpath("//input[@placeholder='Disputed Transaction Amount']"))
				.sendKeys(prop.getProperty("trxnamt"));

		// card status
		driver.findElement(By.cssSelector("span[class=oj-radiocheckbox-icon]")).click();

		// remarks
		driver.findElement(By.id("FFTB12|input")).sendKeys(prop.getProperty("remarks"));

		// submit button

		driver.findElement(By.xpath("//span[text()='SUBMIT']")).click();

		// Enter OTP
		driver.findElement(By.xpath("//input[@placeholder='Enter OTP']")).sendKeys(prop.getProperty("otp"));

		// Click on proceed
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();

		Thread.sleep(5000);
		// Take Screenshot in required path -- confirmation screen
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//CRM//CRM_Report_Fraud.png"));

		// closes current browser
		Thread.sleep(3000);
		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());
		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());
		driver.close();

	}

}