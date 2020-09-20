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

public class FundTransfer {
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
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("skiptour")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("skiptour")));
		driver.findElement(By.xpath("//a[@id='skiptour']")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'DEPOSITS')]")).click();

		// Click on hamburger icon
		driver.findElement(By.cssSelector("a#hamburger")).click();

		// Click on Deposit option in hamburger menu
		driver.findElement(By.id("FUNDS_TRANSFER_TITLE")).click();
		// Click on Fund Transfer Dashboard
		driver.findElement(By.id("FUNDS_TRANSFER_TITLE~funds-transfer-dashboard")).click();
		Thread.sleep(3000);
		// skip tour
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("skiptour")));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("skiptour")));
		driver.findElement(By.xpath("//a[@id='skiptour']")).click();
	}

	// Method 1: IMPS FT Operation
	@Test(groups = { "FundTransfer" })
	public void IMPS() throws InterruptedException, IOException {

		Thread.sleep(2000);

		driver.findElement(By.xpath("//img[@title='Funds Transfer']")).click();

		// wait till Next Button
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("button2")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button2")));

		Thread.sleep(5000);
		driver.findElement(By.id("oj-select-choice-recepientName")).click();
		int x = 1;
		while (x < 5) {
			driver.findElement(By.id("oj-select-choice-recepientName")).sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.id("oj-select-choice-recepientName")).sendKeys(Keys.ENTER);

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_FT.properties");
		prop.load(fis);

		// enter FT amount
		driver.findElement(By.id("fromAmount|input")).sendKeys(prop.getProperty("ftamt"));

		// select date
		driver.findElement(By.cssSelector("oj-input-date[value*=valuedate]")).click();
		// driver.findElement(By.xpath("//a[@class='oj-enabled'][contains(text(),'30')]")).click();
		driver.findElement(By.xpath("//td[@data-handler='selectDay']//a[@class='oj-enabled oj-selected']")).click();

		// click on IMPS // wait till IMPS Button
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'NEFT')]")));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'NEFT')]")));
		driver.findElement(By.xpath("//span[contains(text(),'NEFT')]")).click();

		// Actions a=new Actions(driver);
		// moves to specific element
		// WebElement
		// move=driver.findElement(By.xpath("//span[contains(text(),'IMPS')]"));
		// a.moveToElement(move).doubleClick().build().perform();
		// a.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().keyDown(Keys.SHIFT).sendKeys("hello").doubleClick().build().perform();
		// a.moveToElement(move).contextClick().build().perform();

		// add description
		driver.findElement(By.id("description|input")).sendKeys(prop.getProperty("desc"));
		// click on Next
		driver.findElement(By.id("button2")).click();

		Thread.sleep(2000);
		// Verify Screen and // click on Next Button
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("inputButton")));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputButton")));

		Thread.sleep(2000); // Take Screenshot in required path - verify screen File
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//FT//FT_To_Reg_Acc_IMPS_Verify.png"));

		Thread.sleep(2000);
		driver.findElement(By.id("inputButton")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='otp|input']")).sendKeys(prop.getProperty("otp"));
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();

		Thread.sleep(10000);
		File src2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src2, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//FT//FT_To_Reg_Acc_IMPS_Confirmation.png"));

		// closes current browser
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());
		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());
		driver.close();
		
		

	}

	// Method 2: NEFT FT Operation
	@Test(groups = { "FundTransfer" })
	public void NEFT() throws InterruptedException, IOException {

		Thread.sleep(2000);

		driver.findElement(By.xpath("//img[@title='Funds Transfer']")).click();

		// wait till Next Button
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("button2")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button2")));

		Thread.sleep(5000);
		driver.findElement(By.id("oj-select-choice-recepientName")).click();
		int x = 1;
		while (x < 5) {
			driver.findElement(By.id("oj-select-choice-recepientName")).sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.id("oj-select-choice-recepientName")).sendKeys(Keys.ENTER);

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_FT.properties");
		prop.load(fis);

		// enter FT amount
		driver.findElement(By.id("fromAmount|input")).sendKeys(prop.getProperty("ftamt"));

		// select date
		driver.findElement(By.cssSelector("oj-input-date[value*=valuedate]")).click();
		// driver.findElement(By.xpath("//a[@class='oj-enabled'][contains(text(),'30')]")).click();
		driver.findElement(By.xpath("//td[@data-handler='selectDay']//a[@class='oj-enabled oj-selected']")).click();

		// click on NEFT // wait till NEFT Button
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'IMPS')]")));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'IMPS')]")));
		driver.findElement(By.xpath("//span[contains(text(),'NEFT')]")).click();

		// Actions a=new Actions(driver);
		// moves to specific element
		// WebElement
		// move=driver.findElement(By.xpath("//span[contains(text(),'IMPS')]"));
		// a.moveToElement(move).doubleClick().build().perform();
		// a.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().keyDown(Keys.SHIFT).sendKeys("hello").doubleClick().build().perform();
		// a.moveToElement(move).contextClick().build().perform();

		// add description
		driver.findElement(By.id("description|input")).sendKeys(prop.getProperty("desc"));
		// click on Next
		driver.findElement(By.id("button2")).click();

		Thread.sleep(2000);
		// Verify Screen and // click on Next Button
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("inputButton")));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputButton")));

		Thread.sleep(2000); // Take Screenshot in required path - verify screen File
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//FT//FT_To_Reg_Acc_NEFT_Verify.png"));

		Thread.sleep(2000);
		driver.findElement(By.id("inputButton")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='otp|input']")).sendKeys(prop.getProperty("otp"));
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();

		Thread.sleep(10000);
		File src2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src2, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//FT//FT_To_Reg_Acc_NEFT_Confirmation.png"));

		// closes current browser
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());
		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());
		driver.close();

	}

}
