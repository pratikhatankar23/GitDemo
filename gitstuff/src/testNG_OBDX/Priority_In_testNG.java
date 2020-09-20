package testNG_OBDX;

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
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Priority_In_testNG {
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
	
	// Method 1: WMS Recurring Purchase SIP
	@Test
	public void WMSRecurringPurchaseSip() throws InterruptedException, IOException {

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
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"dashboard-tab-deposits\"]")).click();
		driver.findElement(By.cssSelector("a#hamburger")).click();

		// landing on WMS page 
		Thread.sleep(5000);
		driver.findElement(By.xpath("//li[@id='WEALTH_MANAGEMENT']")).click();
		driver.findElement(By.xpath("//*[@id=\"WEALTH_MANAGEMENT~wealth-management-dashboard\"]/a")).click();
		
		//Clicking on "OK" button on pop-up
		Thread.sleep(2000);
		driver.findElement(By.xpath("//oj-button[@id='button1']")).click();
		
		//Click on purchase button
		driver.findElement(By.xpath("//*[@id=\"next-button\"]/button/div")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("oj-option[value='recurring']")).click();

		// for selecting investment account
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccount']")).click();
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccount']")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccount']")).sendKeys(Keys.ENTER);

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

		// selection of fund house
		driver.findElement(By.name("white-listed")).click();
		driver.findElement(By.id("oj-select-choice-fund-houses")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-fund-houses")).sendKeys(Keys.ARROW_DOWN);
		int x = 1;
		while (x < 3) {
			driver.findElement(By.id("oj-select-choice-fund-houses")).sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.id("oj-select-choice-fund-houses")).sendKeys(Keys.ENTER);

		// selection of fund category
		driver.findElement(By.id("oj-select-choice-fund-category")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-fund-category")).sendKeys(Keys.ARROW_DOWN);
		int y = 1;
		while (y < 3) {
			driver.findElement(By.id("oj-select-choice-fund-category")).sendKeys(Keys.ARROW_DOWN);
			y++;
		}
		driver.findElement(By.id("oj-select-choice-fund-category")).sendKeys(Keys.ENTER);

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

		// consent pop-up
		Thread.sleep(3000);
		driver.findElement(By.id("agree1|cb")).click();
		driver.findElement(By.xpath("//*[@id=\"button2\"]/button")).click();

		// selection of folio
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-folio-number")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ARROW_DOWN);
		int b = 1;
		while (b < 2) {
			driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ARROW_DOWN);
			b++;
		}
		driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ENTER);

		// Select dividend radio button / dividend
		driver.findElement(By.xpath("//input[@value='Payout']")).click();

		// selection of frequency
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-frequency")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-frequency")).sendKeys(Keys.ARROW_DOWN);
		int c = 1;
		while (c < 2) {
			driver.findElement(By.id("oj-select-choice-frequency")).sendKeys(Keys.ARROW_DOWN);
			c++;
		}
		driver.findElement(By.id("oj-select-choice-frequency")).sendKeys(Keys.ENTER);

		// Enter Amount
		driver.findElement(By.xpath("//oj-input-text[@value='{{amount}}']")).sendKeys("600");

		// Select start date
		driver.findElement(By.id("startTransfer|input")).click();
		driver.findElement(By.xpath("//a[@class='oj-enabled']")).click();

		// select number of installment
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-installments")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-installments")).sendKeys(Keys.ARROW_DOWN);
		int d = 1;
		while (d < 3) {
			driver.findElement(By.id("oj-select-choice-frequency")).sendKeys(Keys.ARROW_DOWN);
			d++;
		}
		driver.findElement(By.id("oj-select-choice-frequency")).sendKeys(Keys.ENTER);

		// Click on next button
		driver.findElement(By.cssSelector("oj-button[on-click*='initiatePurchase']")).click();

		// click on cross button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='cutOffModal']//img[@class='cross-icon']")).click();

		// verify screen
		driver.findElement(By.id("agree3|cb")).click();
		driver.findElement(By.id("Consentagree|cb")).click();
		driver.findElement(By.id("confirmButton")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter OTP']")).sendKeys("111111");

		driver.findElement(By.cssSelector("oj-button[on-click*='OTP']")).click();

		Thread.sleep(30000);
		// Take Screenshot in required path -- confirmation screen
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Recurring_Purchase_SIP_Confirmation.png"));


	}

	// Method 2: WMS Recurring Purchase SI
	@Test		
    public void WMSRecurringPurchaseSi() throws InterruptedException, IOException {	

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
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"dashboard-tab-deposits\"]")).click();
		driver.findElement(By.cssSelector("a#hamburger")).click();

		// landing on WMS page and Click on purchase button
		Thread.sleep(5000);
		driver.findElement(By.xpath("//li[@id='WEALTH_MANAGEMENT']")).click();
		driver.findElement(By.xpath("//*[@id=\"WEALTH_MANAGEMENT~wealth-management-dashboard\"]/a")).click();

		// Clicking on "OK" button on pop-up
		Thread.sleep(2000);
		driver.findElement(By.xpath("//oj-button[@id='button1']")).click();

		// Clicking on Purchase then on recurring tab
		driver.findElement(By.xpath("//*[@id=\"next-button\"]/button/div")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("oj-option[value='recurring']")).click();

		Thread.sleep(2000);
		// Select recurring type as SI
		driver.findElement(By.xpath("//input[@value='SI']")).click();

		// for selecting investment account
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccount']")).click();
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccount']")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccount']")).sendKeys(Keys.ENTER);

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

		// selection of fund house
		driver.findElement(By.name("white-listed")).click();
		driver.findElement(By.id("oj-select-choice-fund-houses")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-fund-houses")).sendKeys(Keys.ARROW_DOWN);
		int x = 1;
		while (x < 3) {
			driver.findElement(By.id("oj-select-choice-fund-houses")).sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.id("oj-select-choice-fund-houses")).sendKeys(Keys.ENTER);

		// selection of fund category
		driver.findElement(By.id("oj-select-choice-fund-category")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-fund-category")).sendKeys(Keys.ARROW_DOWN);
		int y = 1;
		while (y < 3) {
			driver.findElement(By.id("oj-select-choice-fund-category")).sendKeys(Keys.ARROW_DOWN);
			y++;
		}
		driver.findElement(By.id("oj-select-choice-fund-category")).sendKeys(Keys.ENTER);

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

		// consent pop-up
		Thread.sleep(3000);
		driver.findElement(By.id("agree1|cb")).click();
		driver.findElement(By.xpath("//*[@id=\"button2\"]/button")).click();

		// selection of folio
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-folio-number")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ARROW_DOWN);
		int b = 1;
		while (b < 2) {
			driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ARROW_DOWN);
			b++;
		}
		driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ENTER);

		// Select dividend radio button / dividend
		driver.findElement(By.xpath("//input[@value='Payout']")).click();

//Set Instructions
		// selection of frequency
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-frequency")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-frequency")).sendKeys(Keys.ARROW_DOWN);
		int c = 1;
		while (c < 2) {
			driver.findElement(By.id("oj-select-choice-frequency")).sendKeys(Keys.ARROW_DOWN);
			c++;
		}
		driver.findElement(By.id("oj-select-choice-frequency")).sendKeys(Keys.ENTER);

		// Enter Amount
		driver.findElement(By.xpath("//oj-input-text[@value='{{amount}}']")).sendKeys("600");

		// Select start date
		driver.findElement(By.id("startTransfer|input")).click();
		driver.findElement(By.xpath("//a[@class='oj-enabled']")).click();

		// enter end date
		driver.findElement(By.xpath("//input[@id='endDate|input']")).click();
		driver.findElement(By.xpath("//input[@id='endDate|input']")).sendKeys("31 DEC 2020");

		// Click on next button
		driver.findElement(By.cssSelector("oj-button[on-click*='initiatePurchase']")).click();

		// click on cross button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='cutOffModal']//img[@class='cross-icon']")).click();

		// verify screen
		driver.findElement(By.id("agree3|cb")).click();
		driver.findElement(By.id("Consentagree|cb")).click();
		driver.findElement(By.id("confirmButton")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter OTP']")).sendKeys("111111");

		driver.findElement(By.cssSelector("oj-button[on-click*='OTP']")).click();

		Thread.sleep(30000);
		// Take Screenshot in required path -- confirmation screen
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Recurring_Purchase_SI_Confirmation.png"));
        
	}
        
     // Method 3: WMS Onetime Purchase Operation			
	@Test
	public void WMSOnetimePurchase() throws InterruptedException, IOException {

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

		// landing on WMS page and click on Purchase Button
		Thread.sleep(5000);
		driver.findElement(By.xpath("//li[@id='WEALTH_MANAGEMENT']")).click();
		driver.findElement(By.xpath("//*[@id=\"WEALTH_MANAGEMENT~wealth-management-dashboard\"]/a")).click();

		// Clicking on "OK" button on pop-up
		Thread.sleep(2000);
		driver.findElement(By.xpath("//oj-button[@id='button1']")).click();

		// this is for clicking purchase button
		driver.findElement(By.xpath("//*[@id=\"next-button\"]/button/div")).click();

		// for selecting investment account
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccount']")).click();
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccount']")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-investmentAccount']")).sendKeys(Keys.ENTER);

		// for selecting bank account
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/obdx-component[1]/div[1]/div[1]/div[1]/main[1]/div[2]/div[1]/div[1]/obdx-component[1]/div[1]/div[1]/div[2]/div[1]/div[1]/page-section[1]/obdx-component[1]/div[1]/div[1]/div[1]/div[1]/oj-validation-group[1]/div[1]/div[3]/div[1]/account-input[1]/obdx-component[1]/div[1]/div[2]/oj-select-one[1]/div[1]/div[1]"))
				.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/obdx-component[1]/div[1]/div[1]/div[1]/main[1]/div[2]/div[1]/div[1]/obdx-component[1]/div[1]/div[1]/div[2]/div[1]/div[1]/page-section[1]/obdx-component[1]/div[1]/div[1]/div[1]/div[1]/oj-validation-group[1]/div[1]/div[3]/div[1]/account-input[1]/obdx-component[1]/div[1]/div[2]/oj-select-one[1]/div[1]/div[1]"))
				.sendKeys(Keys.ARROW_DOWN);
		int i = 1;
		while (i < 2) {
			driver.findElement(By.xpath(
					"/html[1]/body[1]/div[2]/obdx-component[1]/div[1]/div[1]/div[1]/main[1]/div[2]/div[1]/div[1]/obdx-component[1]/div[1]/div[1]/div[2]/div[1]/div[1]/page-section[1]/obdx-component[1]/div[1]/div[1]/div[1]/div[1]/oj-validation-group[1]/div[1]/div[3]/div[1]/account-input[1]/obdx-component[1]/div[1]/div[2]/oj-select-one[1]/div[1]/div[1]"))
					.sendKeys(Keys.ARROW_DOWN);
			i++;
		}
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/obdx-component[1]/div[1]/div[1]/div[1]/main[1]/div[2]/div[1]/div[1]/obdx-component[1]/div[1]/div[1]/div[2]/div[1]/div[1]/page-section[1]/obdx-component[1]/div[1]/div[1]/div[1]/div[1]/oj-validation-group[1]/div[1]/div[3]/div[1]/account-input[1]/obdx-component[1]/div[1]/div[2]/oj-select-one[1]/div[1]/div[1]"))
				.sendKeys(Keys.ENTER);

		// selection of fund house
		driver.findElement(By.name("white-listed")).click();
		driver.findElement(By.id("oj-select-choice-fund-houses")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-fund-houses")).sendKeys(Keys.ARROW_DOWN);
		int x = 1;
		while (x < 2) {
			driver.findElement(By.id("oj-select-choice-fund-houses")).sendKeys(Keys.ARROW_DOWN);
			x++;
		}
		driver.findElement(By.id("oj-select-choice-fund-houses")).sendKeys(Keys.ENTER);

		// selection of fund category
		driver.findElement(By.id("oj-select-choice-fund-category")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-fund-category")).sendKeys(Keys.ARROW_DOWN);
		int y = 1;
		while (y < 3) {
			driver.findElement(By.id("oj-select-choice-fund-category")).sendKeys(Keys.ARROW_DOWN);
			y++;
		}
		driver.findElement(By.id("oj-select-choice-fund-category")).sendKeys(Keys.ENTER);

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

		// consent pop-up
		Thread.sleep(3000);
		driver.findElement(By.id("agree1|cb")).click();
		driver.findElement(By.xpath("//*[@id=\"button2\"]/button")).click();

		// selection of folio
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-folio-number")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ARROW_DOWN);
		int b = 1;
		while (b < 2) {
			driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ARROW_DOWN);
			b++;
		}
		driver.findElement(By.id("oj-select-choice-folio-number")).sendKeys(Keys.ENTER);

		// Select dividend radio button
		driver.findElement(By.xpath("//input[@value='Payout']")).click();

		// Enter Amount
		driver.findElement(By.xpath("//oj-input-text[@value='{{amount}}']")).sendKeys("100");

		// purchase date
		driver.findElement(By.id("later|rb")).click();

		// select date
		driver.findElement(By.xpath("//span[@title='Select Date.']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'30')]")).click();

		// Click on next button
		driver.findElement(By.xpath("//*[@id=\"oneTimeTransaction\"]/div/oj-button[1]/button")).click();

		// clcik on cross button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='cutOffModal']//img[@class='cross-icon']")).click();

		// verify screen
		driver.findElement(By.id("agree3|cb")).click();
		driver.findElement(By.id("Consentagree|cb")).click();
		driver.findElement(By.id("confirmButton")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter OTP']")).sendKeys("111111");

		driver.findElement(By.cssSelector("oj-button[on-click*='OTP']")).click();

		Thread.sleep(30000);
		// Take Screenshot in required path -- confirmation screen
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File("C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Onetime_Purchase_Confirmation.png"));
	}
}