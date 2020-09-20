package testNG_OBDX;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WMS_SWITCH {
	WebDriver driver;
	
	@BeforeMethod
	public void setup(ITestContext context) {
		System.setProperty("webdriver.chrome.driver", "C:\\work\\chromedriver.exe");
		driver = new ChromeDriver();

		// Set implicit wait of 3 seconds
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		context.setAttribute("driver", driver);
		// Maximize Window
		driver.manage().window().maximize();

		// Delete All cookies
		driver.manage().deleteAllCookies();

		// Open OBDX URL
		driver.get("http://mum00bol.in.oracle.com:7781/index.html?module=login");
	}

	// Method 1: WMS Onetime Switch Operation
	@Test(groups= {"WMS"})
	public void WMSOnetimeSwitch() throws InterruptedException, IOException {
		
		driver.findElement(By.id("login_username|input")).sendKeys("DRACULA");
		driver.findElement(By.name("password")).sendKeys("Password@23");
		driver.findElement(By.className("oj-button-text")).click();

		/*
		 * //to get the rid of Error on Login Page Thread.sleep(20000);
		 * driver.findElement(By.xpath(
		 * "//*[@id=\"container\"]/div[3]/div/div[1]/div[5]/div/div/oj-button/button")).
		 * click();
		 */

		// for clicking on hamburger icon
		Thread.sleep(8000);
		driver.findElement(By.xpath("//*[@id=\"dashboard-tab-deposits\"]")).click();
		driver.findElement(By.cssSelector("a#hamburger")).click();

		// landing on WMS dashboard page
		Thread.sleep(5000);
		driver.findElement(By.xpath("//li[@id='WEALTH_MANAGEMENT']")).click();
		driver.findElement(By.xpath("//*[@id=\"WEALTH_MANAGEMENT~wealth-management-dashboard\"]/a")).click();

		// Clicking on "OK" button on pop-up
		Thread.sleep(2000);
		driver.findElement(By.xpath("//oj-button[@id='button1']")).click();

		// this is for clicking Switch button
		driver.findElement(By.xpath("//oj-button[@coachid='coachid_showSwitch']")).click();

		Thread.sleep(3000);
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
		while (x < 2) {
			driver.findElement(By.id("oj-select-choice-selectFundHouse")).sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.id("oj-select-choice-selectFundHouse")).sendKeys(Keys.ENTER);

		// selection of scheme name
		driver.findElement(By.id("oj-select-choice-scheme")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ARROW_DOWN);
		int a = 1;
		while (a < 3) {
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

//Switch-in Scheme (Destination)
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

//consent pop-up
		Thread.sleep(2000);
		driver.findElement(By.id("agree1|cb")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//oj-validation-group[@id='scheme-consent']//oj-button[@id='button2']")).click();

//Select dividend radio button
		driver.findElement(By.xpath("//input[@value='payout']")).click();

		// Enter Amount
		// driver.findElement(By.xpath("//oj-input-text[@value='{{amount}}']")).sendKeys("100");

		// Enter Units
		driver.findElement(By.xpath("//oj-input-text[@value='{{units}}']")).sendKeys("100");

		// purchase date
		driver.findElement(By.id("later|rb")).click();

		// select date
		driver.findElement(By.xpath("//span[@title='Select Date.']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'30')]")).click();

		// Click on next button

		driver.findElement(By.xpath("//*[@id=\"button2\"]/button")).click();

//verify screen
		Thread.sleep(5000);
		driver.findElement(By.id("agree3|cb")).click();
		driver.findElement(By.id("Consentagree|cb")).click();
		driver.findElement(By.id("confirmButton")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter OTP']")).sendKeys("111111");

		driver.findElement(By.cssSelector("oj-button[on-click*='OTP']")).click();

		Thread.sleep(20000);
		// Take Screenshot in required path -- confirmation screen
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Switch//WMSOnetimeSwitch.png"));

		// closes current browser
		Thread.sleep(10000);
		driver.close();

	}

	// Method 2: WMS Recurring Switch STP Operation
	@Test(groups= {"WMS"})
	public void WMSRecurringSwitchStp() throws InterruptedException, IOException {

		driver.findElement(By.id("login_username|input")).sendKeys("DRACULA");
		driver.findElement(By.name("password")).sendKeys("Password@23");
		driver.findElement(By.className("oj-button-text")).click();

		/*
		 * //to get the rid of Error on Login Page Thread.sleep(20000);
		 * driver.findElement(By.xpath(
		 * "//*[@id=\"container\"]/div[3]/div/div[1]/div[5]/div/div/oj-button/button")).
		 * click();
		 */

		// for clicking on hamburger icon
		Thread.sleep(8000);
		driver.findElement(By.xpath("//*[@id=\"dashboard-tab-deposits\"]")).click();
		driver.findElement(By.cssSelector("a#hamburger")).click();

		// landing on WMS dashboard page
		Thread.sleep(5000);
		driver.findElement(By.xpath("//li[@id='WEALTH_MANAGEMENT']")).click();
		driver.findElement(By.xpath("//*[@id=\"WEALTH_MANAGEMENT~wealth-management-dashboard\"]/a")).click();

		// Clicking on "OK" button on pop-up
		Thread.sleep(2000);
		driver.findElement(By.xpath("//oj-button[@id='button1']")).click();

		// this is for clicking Switch button
		driver.findElement(By.xpath("//oj-button[@coachid='coachid_showSwitch']")).click();

		// Click on recurring
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("oj-option[value='RECURRING']")).click();

		Thread.sleep(3000);
		// for selecting investment account
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccounts']")).click();
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccounts']")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccounts']")).sendKeys(Keys.ENTER);

// Switch-out-Scheme (Source)
		// selection of fund house
		driver.findElement(By.id("oj-select-choice-selectFundHouse")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-selectFundHouse")).sendKeys(Keys.ARROW_DOWN);
		int x = 1;
		while (x < 2) {
			driver.findElement(By.id("oj-select-choice-selectFundHouse")).sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.id("oj-select-choice-selectFundHouse")).sendKeys(Keys.ENTER);

		// selection of scheme name
		driver.findElement(By.id("oj-select-choice-scheme")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ARROW_DOWN);
		int a = 1;
		while (a < 3) {
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

		driver.findElement(By.xpath("//*[@id=\"button2\"]/button")).click();

//Switch-in Scheme (Destination) in "set instruction" screen
		// selection of scheme name
		driver.findElement(By.xpath("//oj-select-one[@value='{{schemeCodeSwitchIn}}']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//oj-select-one[@value='{{schemeCodeSwitchIn}}']")).sendKeys(Keys.ARROW_DOWN);
		int p = 1;
		while (p < 4) {
			driver.findElement(By.xpath("//oj-select-one[@value='{{schemeCodeSwitchIn}}']")).sendKeys(Keys.ARROW_DOWN);
			p++;
		}
		driver.findElement(By.xpath("//oj-select-one[@value='{{schemeCodeSwitchIn}}']")).sendKeys(Keys.ENTER);

		// consent pop-up
		Thread.sleep(2000);
		driver.findElement(By.id("agree1|cb")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//oj-validation-group[@id='scheme-consent']//oj-button[@id='button2']")).click();

		// Select dividend radio button
		driver.findElement(By.xpath("//input[@value='Payout']")).click();

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

		// Select Units radio button

		driver.findElement(By.xpath("//input[@value='Units']")).click();

		// Enter Amount
		// driver.findElement(By.xpath("//oj-input-text[@value='{{amount}}']")).sendKeys("100");

		// Enter Units
		driver.findElement(By.id("txnUnits")).sendKeys("50");

		// Select start date
		driver.findElement(By.xpath("//*[@role=\"combobox\"]/span/span")).click();
		driver.findElement(By.xpath("//a[@class='oj-enabled']")).click();

		// Select No of transactions --

		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-installments")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-installments")).sendKeys(Keys.ARROW_DOWN);
		int d = 1;
		while (d < 3) {
			driver.findElement(By.id("oj-select-choice-installments")).sendKeys(Keys.ARROW_DOWN);
			d++;
		}
		driver.findElement(By.id("oj-select-choice-installments")).sendKeys(Keys.ENTER);

		// Click on next button
		driver.findElement(By.xpath("//*[@id=\"button2\"]")).click();

		// verify screen
		Thread.sleep(5000);
		driver.findElement(By.id("agree2|cb")).click();
		driver.findElement(By.id("Consentagree|cb")).click();
		driver.findElement(By.id("inputButton")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter OTP']")).sendKeys("111111");

		// driver.findElement(By.cssSelector("input[placeholder*='OTP']")).click();

		// click on next button
		driver.findElement(By.cssSelector("oj-button[on-click*='OTP']")).click();

		Thread.sleep(20000);
		// Take Screenshot in required path -- confirmation screen
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Switch//WMSRecurringSwitchStp.png"));

		// closes current browser
		Thread.sleep(10000);
		driver.close();
	}

	// Method 3: WMS Recurring Switch PSTP Operation
	@Test(groups= {"WMS"})
	public void WMSRecurringSwitchPstp() throws InterruptedException, IOException {

		driver.findElement(By.id("login_username|input")).sendKeys("DRACULA");
		driver.findElement(By.name("password")).sendKeys("Password@23");
		driver.findElement(By.className("oj-button-text")).click();

		/*
		 * //to get the rid of Error on Login Page Thread.sleep(20000);
		 * driver.findElement(By.xpath(
		 * "//*[@id=\"container\"]/div[3]/div/div[1]/div[5]/div/div/oj-button/button")).
		 * click();
		 */

		// for clicking on hamburger icon
		Thread.sleep(8000);
		driver.findElement(By.xpath("//*[@id=\"dashboard-tab-deposits\"]")).click();
		driver.findElement(By.cssSelector("a#hamburger")).click();

		// landing on WMS dashboard page
		Thread.sleep(5000);
		driver.findElement(By.xpath("//li[@id='WEALTH_MANAGEMENT']")).click();
		driver.findElement(By.xpath("//*[@id=\"WEALTH_MANAGEMENT~wealth-management-dashboard\"]/a")).click();

		// Clicking on "OK" button on pop-up
		Thread.sleep(2000);
		driver.findElement(By.xpath("//oj-button[@id='button1']")).click();

		// this is for clicking Switch button
		driver.findElement(By.xpath("//oj-button[@coachid='coachid_showSwitch']")).click();

		// Click on recurring
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("oj-option[value='RECURRING']")).click();

		// select recurring type as PSTP

		driver.findElement(By.xpath("//input[@value='PSTP']")).click();

		Thread.sleep(3000);
		// for selecting investment account
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccounts']")).click();
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccounts']")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccounts']")).sendKeys(Keys.ENTER);

		// for selecting bank account
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("oj-select-one[data-id='selectedAccount']")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("oj-select-one[data-id='selectedAccount']")).sendKeys(Keys.ARROW_DOWN);
		int i = 1;
		while (i < 2) {
			driver.findElement(By.cssSelector("oj-select-one[data-id='selectedAccount']")).sendKeys(Keys.ARROW_DOWN);
			i++;
		}
		driver.findElement(By.cssSelector("oj-select-one[data-id='selectedAccount']")).sendKeys(Keys.ENTER);

// Initial Purchase Scheme
		// selection of fund house
		driver.findElement(By.id("oj-select-choice-selectFundHouse")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-selectFundHouse")).sendKeys(Keys.ARROW_DOWN);
		int x = 1;
		while (x < 2) {
			driver.findElement(By.id("oj-select-choice-selectFundHouse")).sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.id("oj-select-choice-selectFundHouse")).sendKeys(Keys.ENTER);

		// selection of fund category
		driver.findElement(By.id("oj-select-choice-selectFundCategory")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-selectFundCategory")).sendKeys(Keys.ARROW_DOWN);
		int y = 1;
		while (y < 3) {
			driver.findElement(By.id("oj-select-choice-selectFundCategory")).sendKeys(Keys.ARROW_DOWN);
			y++;
		}
		driver.findElement(By.id("oj-select-choice-selectFundCategory")).sendKeys(Keys.ENTER);

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

		// consent pop-up
		Thread.sleep(2000);
		driver.findElement(By.id("agreePstp|cb")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//oj-validation-group[@id='scheme-consent-pstp']//oj-button[@id='button2']"))
				.click();

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

		// Select dividend radio button
		driver.findElement(By.xpath("//input[@value='Payout']")).click();

		// Enter Amount
		driver.findElement(By.xpath("//oj-input-text[@value='{{amount}}']")).sendKeys("100");
		Thread.sleep(3000);

		// Click on NEXT button
		driver.findElement(By.xpath("//oj-input-text[@value='{{amount}}']")).sendKeys(Keys.TAB);
		driver.findElement(By.cssSelector("oj-button[on-click*='initiateSwitchRecurring']")).sendKeys(Keys.ENTER);

//Set instructions screen
		// Switch in Scheme Destination
		Thread.sleep(3000);
		// selection of scheme name
		driver.findElement(By.xpath("//div[@id='oj-select-choice-scheme']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-scheme']")).sendKeys(Keys.ARROW_DOWN);
		int p = 1;
		while (p < 4) {
			driver.findElement(By.xpath("//div[@id='oj-select-choice-scheme']")).sendKeys(Keys.ARROW_DOWN);
			p++;
		}
		driver.findElement(By.xpath("//div[@id='oj-select-choice-scheme']")).sendKeys(Keys.ENTER);

		// oj-validation-group[@id='scheme-consent']//oj-button[@id='button2']
		// consent pop-up
		Thread.sleep(2000);
		driver.findElement(By.id("agree1|cb")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//oj-validation-group[@id='scheme-consent']//oj-button[@id='button2']")).click();

		// Select dividend radio button
		driver.findElement(By.xpath("//input[@value='Payout']")).click();

//Set Instruction
		// Select Frequency

		// selection of frequency
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-investmentFrequency")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-investmentFrequency")).sendKeys(Keys.ARROW_DOWN);
		int c = 1;
		while (c < 4) {
			driver.findElement(By.id("oj-select-choice-investmentFrequency")).sendKeys(Keys.ARROW_DOWN);
			c++;
		}
		driver.findElement(By.id("oj-select-choice-investmentFrequency")).sendKeys(Keys.ENTER);

		// Enter Amount
		driver.findElement(By.xpath("//oj-input-text[@value='{{amount}}']")).sendKeys("50");

		// Select start date
		driver.findElement(By.xpath("//*[@role=\"combobox\"]/span/span")).click();
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
		driver.findElement(By.xpath("//*[@id=\"button2\"]")).click();

// verify screen
		Thread.sleep(5000);
		driver.findElement(By.id("agree2|cb")).click();
		driver.findElement(By.id("Consentagree|cb")).click();
		driver.findElement(By.id("inputButton")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter OTP']")).sendKeys("111111");

		// driver.findElement(By.cssSelector("input[placeholder*='OTP']")).click();

		// click on next button
		driver.findElement(By.cssSelector("oj-button[on-click*='OTP']")).click();

		Thread.sleep(10000);
// Take Screenshot in required path -- confirmation screen
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Switch//WMSRecurringSwitchStp.png"));

		// closes current browser
		Thread.sleep(3000);
		driver.close();
	}
}