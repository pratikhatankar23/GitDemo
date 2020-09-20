package Testing;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class ScreenshotDemo {

	String driverExecutablePath = "C:\\work\\chromedriver.exe";
	WebDriver driver;

	@BeforeTest
	public void setup(ITestContext context) {
		System.setProperty("webdriver.chrome.driver", driverExecutablePath);
		driver = new ChromeDriver();

		// Set implicit wait of 3 seconds
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		context.setAttribute("driver", driver);
	}

	@Test
	// Tests google calculator
	public void googleCalculator() throws IOException {

		// Launch google
		driver.get("https://www.google.co.in");

		// Write 2+2 in google textbox
		WebElement googleTextBox = driver.findElement(By.id("lst-ib"));
		googleTextBox.sendKeys("2+2");

		// Hit enter
		googleTextBox.sendKeys(Keys.ENTER);

		// Get result from calculator
		WebElement calculatorTextBox = driver.findElement(By.id("cwtltblr"));
		String result = calculatorTextBox.getText();

		// Intentionaly checking for wrong calculation of 2+2=5 in order to take
		// screenshot for failing test
		AssertJUnit.assertEquals(result, "5");
	}

	/*@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			System.out.println(testResult.getStatus());
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(
					"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Testing//SoftwareTestingMaterial.png"
							+ testResult.getName() + "-" + Arrays.toString(testResult.getParameters()) + ".jpg"));
		}
	}*/

}