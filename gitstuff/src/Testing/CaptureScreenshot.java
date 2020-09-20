package Testing;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CaptureScreenshot {

	@Test
	public static void captureScreenMethod() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\work\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.softwaretestingmaterial.com");
		driver.navigate().refresh();
		driver.findElement(By.xpath("//*[@id='cse']")).sendKeys("agile");
		/*try {
			driver.get("https://www.softwaretestingmaterial.com");
			driver.navigate().refresh();
			// driver.findElement(By.xpath("//*[@id='cse-search-box']/div/input[4]")).sendKeys("agile");
			// //Statement with correct Xpath
			driver.findElement(By.xpath("//*[@id='cse']")).sendKeys("agile"); // Statement with incorrect Xpath
		} catch (Exception e) {
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(
					"C://Users//pphatank//Desktop//ScreenshotsUsing_Selenium//Testing//SoftwareTestingMaterial_1.png"));
		}
		driver.close();*/
		//driver.quit();
	}
}
