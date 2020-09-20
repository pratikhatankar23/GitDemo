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

public class WMS_REDEEM {
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

	// Method 1: WMS Redeem by Units
	@Test(groups= {"WMS"})
	public void WMSRedeemByUnits() throws InterruptedException, IOException {

		driver.findElement(By.id("login_username|input")).sendKeys("DRACULA");
		driver.findElement(By.name("password")).sendKeys("Password@23");
		driver.findElement(By.className("oj-button-text")).click();

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
		driver.findElement(By.xpath("//oj-button[@coachid='coachid_showRedeem']")).click();

		Thread.sleep(3000);

		// Select Fund Scheme name
		driver.findElement(By.id("oj-select-choice-scheme")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ARROW_DOWN);
		int x = 1;
		while (x < 2) {
			driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ENTER);

		// Select Folio No
		driver.findElement(By.id("oj-select-choice-folio-number")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ARROW_DOWN);
		int y = 1;
		while (y < 2) {
			driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ARROW_DOWN);
			y++;
		}
		driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ENTER);

		// Click on SUBMIT
		driver.findElement(By.xpath("//oj-button[@id='inputButton']")).click();

		// select check-box of the scheme

		driver.findElement(By.cssSelector("input[type='checkbox']")).click();

		// Click on next button

		driver.findElement(By.id("next-button")).click();

		// Enter Units

		driver.findElement(By.xpath("//input[@class='oj-inputtext-input oj-component-initnode']")).sendKeys("100");

		// Click on next button
		driver.findElement(By.id("next-button")).click();

		Thread.sleep(4000);
		// Click on cross button of popup
		driver.findElement(By.xpath("//div[@id='cutOffModal']//img[@class='cross-icon']")).click();

		Thread.sleep(3000);
		// Click on T&C checkbox
		driver.findElement(By.id("agree-tnc-generic|cb")).click();

		// click on consent chekcbox
		driver.findElement(By.id("Consentagree|cb")).click();

		// click on confirm button
		driver.findElement(By.id("confirmButton")).click();

		Thread.sleep(2000);
		// enter OTP
		driver.findElement(By.id("otp|input")).sendKeys("111111");

		// Click on Proceed
		driver.findElement(By.xpath("//span[contains(text(),'PROCEED')]")).click();

		Thread.sleep(5000);
		// Take Screenshot in required path -- confirmation screen
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Redeem//RedeemByUnits.png"));

		// closes current browser
		Thread.sleep(5000);
		driver.close();

	}

	// Method 2: WMS Redeem by Amount
	@Test(groups= {"WMS"})
	public void WMSRedeemByAmount() throws InterruptedException, IOException {

		driver.findElement(By.id("login_username|input")).sendKeys("DRACULA");
		driver.findElement(By.name("password")).sendKeys("Password@23");
		driver.findElement(By.className("oj-button-text")).click();

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

		// this is for clicking Redeem button
		driver.findElement(By.xpath("//oj-button[@coachid='coachid_showRedeem']")).click();

		Thread.sleep(3000);

		// Select Fund Scheme name
		driver.findElement(By.id("oj-select-choice-scheme")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ARROW_DOWN);
		int x = 1;
		while (x < 2) {
			driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ENTER);

		// Select Folio No
		driver.findElement(By.id("oj-select-choice-folio-number")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ARROW_DOWN);
		int y = 1;
		while (y < 2) {
			driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ARROW_DOWN);
			y++;
		}
		driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ENTER);

		// Click on SUBMIT
		driver.findElement(By.xpath("//oj-button[@id='inputButton']")).click();

		// select check-box of the scheme

		driver.findElement(By.cssSelector("input[type='checkbox']")).click();

		// Click on next button

		driver.findElement(By.id("next-button")).click();

		// Select redeem By Amount
		driver.findElement(By.xpath("//input[@value='amount']")).click();

		Thread.sleep(2000);
		// Enter Amount
		driver.findElement(By.xpath("//oj-input-text[@value='{{amount}}']")).sendKeys("100");

		Thread.sleep(2000);
		// Click on next button
		driver.findElement(By.xpath("//span[text()='NEXT']")).click();

		Thread.sleep(4000);
		// Click on cross button of popup
		driver.findElement(By.xpath("//div[@id='cutOffModal']//img[@class='cross-icon']")).click();

		Thread.sleep(3000);
		// Click on T&C checkbox
		driver.findElement(By.id("agree-tnc-generic|cb")).click();

		// click on consent chekcbox
		driver.findElement(By.id("Consentagree|cb")).click();

		// click on confirm button
		driver.findElement(By.id("confirmButton")).click();

		Thread.sleep(2000);
		// enter OTP
		driver.findElement(By.id("otp|input")).sendKeys("111111");

		// Click on Proceed
		driver.findElement(By.xpath("//span[contains(text(),'PROCEED')]")).click();

		Thread.sleep(5000);
		// Take Screenshot in required path -- confirmation screen
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Redeem//RedeemByAmount.png"));

		// closes current browser
		Thread.sleep(5000);
		driver.close();

	}

	// Method 3: WMS Redeem All
	@Test(groups= {"WMS"})
	public void WMSRedeemAll() throws InterruptedException, IOException {

		driver.findElement(By.id("login_username|input")).sendKeys("DRACULA");
		driver.findElement(By.name("password")).sendKeys("Password@23");
		driver.findElement(By.className("oj-button-text")).click();

		// for clicking on hamburger icon
		Thread.sleep(8000);
		driver.findElement(By.xpath("//*[@id=\"dashboard-tab-deposits\"]")).click();
		driver.findElement(By.cssSelector("a#hamburger")).click();

		// landing on WMS Dashboard page
		Thread.sleep(5000);
		driver.findElement(By.xpath("//li[@id='WEALTH_MANAGEMENT']")).click();
		driver.findElement(By.xpath("//*[@id=\"WEALTH_MANAGEMENT~wealth-management-dashboard\"]/a")).click();

		// Clicking on "OK" button on pop-up
		Thread.sleep(2000);
		driver.findElement(By.xpath("//oj-button[@id='button1']")).click();

		// this is for clicking Redeem button
		driver.findElement(By.xpath("//oj-button[@coachid='coachid_showRedeem']")).click();

		Thread.sleep(3000);

		// Select Fund Scheme name
		driver.findElement(By.id("oj-select-choice-scheme")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ARROW_DOWN);
		int x = 1;
		while (x < 2) {
			driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ENTER);

		// Select Folio No
		driver.findElement(By.id("oj-select-choice-folio-number")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ARROW_DOWN);
		int y = 1;
		while (y < 2) {
			driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ARROW_DOWN);
			y++;
		}
		driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ENTER);

		// Click on SUBMIT
		driver.findElement(By.xpath("//oj-button[@id='inputButton']")).click();

		// select check-box of the scheme

		driver.findElement(By.cssSelector("input[type='checkbox']")).click();

		// Click on next button

		driver.findElement(By.id("next-button")).click();

		// Select redeem By All
		driver.findElement(By.xpath("//input[@value='redeemall']")).click();

		// Click on next button
		driver.findElement(By.id("next-button")).click();

		Thread.sleep(4000);
		// Click on cross button of popup
		driver.findElement(By.xpath("//div[@id='cutOffModal']//img[@class='cross-icon']")).click();

		Thread.sleep(3000);
		// Click on T&C checkbox
		driver.findElement(By.id("agree-tnc-generic|cb")).click();

		// click on consent checkbox
		driver.findElement(By.id("Consentagree|cb")).click();

		// click on confirm button
		driver.findElement(By.id("confirmButton")).click();

		Thread.sleep(2000);
		// enter OTP
		driver.findElement(By.id("otp|input")).sendKeys("111111");

		// Click on Proceed
		driver.findElement(By.xpath("//span[contains(text(),'PROCEED')]")).click();

		Thread.sleep(5000);
		// Take Screenshot in required path -- confirmation screen
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Redeem//RedeemAll.png"));

		// closes current browser
		Thread.sleep(5000);
		driver.close();

	}

	// Method 4: WMS Redeem By SWP
	@Test(groups= {"WMS"})
	public void WMSRedeemBySwp() throws InterruptedException, IOException {

		driver.findElement(By.id("login_username|input")).sendKeys("DRACULA");
		driver.findElement(By.name("password")).sendKeys("Password@23");
		driver.findElement(By.className("oj-button-text")).click();

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

		// this is for clicking Redeem button
		driver.findElement(By.xpath("//oj-button[@coachid='coachid_showRedeem']")).click();

		Thread.sleep(3000);

		// Select Fund Scheme name
		driver.findElement(By.id("oj-select-choice-scheme")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ARROW_DOWN);
		int x = 1;
		while (x < 2) {
			driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.id("oj-select-choice-scheme")).sendKeys(Keys.ENTER);

		// Select Folio No
		driver.findElement(By.id("oj-select-choice-folio-number")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ARROW_DOWN);
		int y = 1;
		while (y < 2) {
			driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ARROW_DOWN);
			y++;
		}
		driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ENTER);

		// Click on SUBMIT
		driver.findElement(By.xpath("//oj-button[@id='inputButton']")).click();

		// select check-box of the scheme

		driver.findElement(By.cssSelector("input[type='checkbox']")).click();

		// Click on next button

		driver.findElement(By.id("next-button")).click();

		// Select redeem By All
		driver.findElement(By.xpath("//input[@value='swp']")).click();

		// Enter SWP amount
		driver.findElement(By.xpath("//oj-input-text[@value='{{amount}}']")).sendKeys("100");

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

		Thread.sleep(4000);
		// Click on cross button of popup
		driver.findElement(By.xpath("//div[@id='cutOffModal']//img[@class='cross-icon']")).click();

		Thread.sleep(3000);
		// Click on T&C checkbox
		driver.findElement(By.id("agree-tnc-generic|cb")).click();

		// click on consent chekcbox
		driver.findElement(By.id("Consentagree|cb")).click();

		// click on confirm button
		driver.findElement(By.id("confirmButton")).click();

		Thread.sleep(2000);
		// enter OTP
		driver.findElement(By.id("otp|input")).sendKeys("111111");

		// Click on Proceed
		driver.findElement(By.xpath("//span[contains(text(),'PROCEED')]")).click();

		Thread.sleep(5000);
		// Take Screenshot in required path -- confirmation screen
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Redeem//WMSRedeemBySwp.png"));

		// closes current browser
		Thread.sleep(5000);
		driver.close();

	}

}
