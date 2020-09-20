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

public class FundTransferManageRec {
	WebDriver driver;

	@BeforeMethod
	public void setup(ITestContext context) throws IOException, InterruptedException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_FT.properties");
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
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("skiptour")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("skiptour")));
		driver.findElement(By.xpath("//a[@id='skiptour']")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'DEPOSITS')]")).click();

	}

	// Method 3: Add Recipient
	@Test(groups = { "FundTransfer" })
	public void AddRecipent() throws InterruptedException, IOException {

		Thread.sleep(2000);
		// Click on hamburger icon
		driver.findElement(By.cssSelector("a#hamburger")).click();

		// Click on FT option in hamburger menu
		driver.findElement(By.id("FUNDS_TRANSFER_TITLE")).click();

		// Click on Fund Transfer Dashboard
		driver.findElement(By.id("FUNDS_TRANSFER_TITLE~funds-transfer-dashboard")).click();
		Thread.sleep(3000);
		// skip tour
		WebDriverWait wait3 = new WebDriverWait(driver, 20);
		wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("skiptour")));
		wait3.until(ExpectedConditions.visibilityOfElementLocated(By.id("skiptour")));
		driver.findElement(By.xpath("//a[@id='skiptour']")).click();

		Thread.sleep(2000);

		driver.findElement(By.xpath("//img[@title='Manage Recipients']")).click();

		// wait till reg recipient displayed
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//span[contains(text(),'Registered Recipients')]")));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Registered Recipients')]")));

		// Click on Add recipient
		Thread.sleep(2000);
		driver.findElement(By.className("marginCustom")).click();

		// Enter recipient Name
		Thread.sleep(3000);
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_FT.properties");
		prop.load(fis);
		driver.findElement(By.id("recipientname|input")).sendKeys(prop.getProperty("recipientnametoadd"));

		// select other domestic bank
		driver.findElement(By.xpath("//oj-radioset[1]/div[1]/span[2]/span[1]")).click();
		// enter ac no
		driver.findElement(By.id("enterrecipientacctno|input")).sendKeys(prop.getProperty("newbankaccount"));
		// reenter ac no
		driver.findElement(By.id("reenterrecipientacctno|input")).sendKeys(prop.getProperty("newbankaccount"));

		// select Account Type
		driver.findElement(By.id("oj-select-choice-accountType")).click();
		driver.findElement(By.id("oj-select-choice-accountType")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.id("oj-select-choice-accountType")).sendKeys(Keys.ENTER);

		// select name as per Bank account
		driver.findElement(By.id("nameAsPerBank|input")).sendKeys(prop.getProperty("nameasperbankaccount"));

		// select nickname
		driver.findElement(By.id("accountnickname|input")).sendKeys(prop.getProperty("nickname"));

		// enter IFSC
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'I know the IFSC Code')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='enterBankName|input']")).clear();
		driver.findElement(By.xpath("//input[@id='enterBankName|input']")).sendKeys(prop.getProperty("IFSC"));

		Thread.sleep(2000);
		driver.findElement(By.xpath("//oj-button[@id='nextButton']")).click();

		// verify screen

		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.className("verifyheading")));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("verifyheading")));

		Thread.sleep(3000); // Take Screenshot in required path - verify screen File
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//FT//Add_Recipient_Verify.png"));

		// confirm button
		Thread.sleep(3000);

		WebElement element = driver.findElement(By.xpath("//oj-button[@id='button2']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='otp|input']")).sendKeys(prop.getProperty("otp"));

		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		Thread.sleep(10000);
		File src2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src2, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//FT//Add_Recipient_Confirmation.png"));

		// closes current browser
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());
		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());
		driver.close();

	}

}
