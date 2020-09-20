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

public class TaxPayment {
	WebDriver driver;

	@BeforeMethod
	public void setup(ITestContext context) throws IOException, InterruptedException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_TaxPayment.properties");
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

		
		// skip tour
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("skiptour")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("skiptour")));
		driver.findElement(By.xpath("//a[@id='skiptour']")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'DEPOSITS')]")).click();
		
		
	}

	// Method 1: RegProfDirTaxPayment
	@Test(groups = { "TaxPayment" })
	public void RegProfDirTaxPayment() throws InterruptedException, IOException {
		
		Thread.sleep(3000);
		// Click on hamburger icon
		driver.findElement(By.cssSelector("a#hamburger")).click();

		// Click on Deposit option in hamburger menu
		driver.findElement(By.id("tax-payment")).click();
		
		//tax payment dashboard
		driver.findElement(By.id("tax-payment~tax-payment-dashboard")).click();
		
		// skip tour
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='skiptour']")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@data-intro=\"Register the tax payer’s details with PAN/TAN\"]")).click();
		
		// Verify on Next Button
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("button2")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button2")));
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\datadrive_TaxPayment.properties");
		prop.load(fis);
		
		//pan
		driver.findElement(By.id("pantannumber")).sendKeys(prop.getProperty("pan"));
		//nickname
		driver.findElement(By.id("profileid|input")).sendKeys(prop.getProperty("profID"));
		//firstName
		driver.findElement(By.id("firstname|input")).sendKeys(prop.getProperty("firstName"));
		//lastname
		driver.findElement(By.id("surname|input")).sendKeys(prop.getProperty("lastName"));
		//flat/unit/block
		driver.findElement(By.cssSelector("input[id*='address1']")).sendKeys(prop.getProperty("flatUnitBlock"));
		//building
		driver.findElement(By.cssSelector("input[id*='address2']")).sendKeys(prop.getProperty("buildingVillage"));
		//area //loaclity // taluka
		driver.findElement(By.cssSelector("oj-input-text[value*='line3']")).sendKeys(prop.getProperty("areaLocality"));
		//landmark
		driver.findElement(By.cssSelector("oj-input-text[value*='line4']")).sendKeys(prop.getProperty("landmark"));
		//city
		driver.findElement(By.cssSelector("oj-input-text[value*='city']")).sendKeys(prop.getProperty("city"));
		driver.findElement(By.id("zipCode|input")).sendKeys(prop.getProperty("zipCode"));
			
		//states
		driver.findElement(By.cssSelector("div[id*=oj-select-choice-states]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div[id*=oj-select-choice-states]")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.cssSelector("div[id*=oj-select-choice-states]")).sendKeys(Keys.ENTER);
		
		//email
		driver.findElement(By.id("emailid")).sendKeys(prop.getProperty("email"));
		//mobile no
		driver.findElement(By.id("mobileno")).sendKeys(prop.getProperty("mobile"));
		//click on next
		driver.findElement(By.id("button2")).click();
		
			
		Thread.sleep(2000);
		// Verify Screen and click on Next Button
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("inputButton")));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputButton")));
		
		// Take Screenshot in required path - verify screen File
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1, new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//TaxPayment//RegProfDtaxPay_Verify.png"));

		driver.findElement(By.id("inputButton")).click();
		
		//Thread.sleep(4000);
		//driver.findElement(By.xpath("//span[text()='CONFIRM']")).click();

		//Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='otp|input']")).sendKeys(prop.getProperty("otp"));
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();

		Thread.sleep(10000);
		File src2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src2, new File(
				"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//TaxPayment//RegProfDtaxPay_Confirmation.png"));

		// closes current browser
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.className("confirm-screen-header-text__message")).getText());
		System.out.println(driver.findElement(By.className("confirm-screen-header__successMessage")).getText());
		driver.close();
	}

}
