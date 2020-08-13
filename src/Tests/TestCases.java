package Tests;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.BeforeMethod;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class TestCases extends BaseClass{
	WebDriver driver;
	public static String title;
	static ExtentTest test;
	static ExtentReports report;
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		System.setProperty("webdriver.chrome.driver",".\\Browser File\\chromedriver.exe");
		driver = new ChromeDriver();
		report = new ExtentReports(".\\Report\\ExtentReportResults.html");
		test = report.startTest("Redmi note 8 search");
		BaseClass.getValues();
	}
	
	@Test
	public void testMethod() throws Exception {
		driver.get(url);
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,20);
		title="Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!";
		try {
			assert title.equalsIgnoreCase(driver.getTitle());
			test.log(LogStatus.PASS, "Navigated to the specified URL");
		}catch(AssertionError a){
			test.log(LogStatus.FAIL, "Navigation failed");
		}
		driver.findElement(By.cssSelector("._2zrpKA._1dBPDZ")).sendKeys(username);
		driver.findElement(By.cssSelector("._2zrpKA._3v41xv._1dBPDZ")).sendKeys(password);
		driver.findElement(By.cssSelector("._2AkmmA._1LctnI._7UHT_c")).click();
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//a[text()='Login']"))));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".LM6RPg")));
		driver.findElement(By.cssSelector(".LM6RPg")).sendKeys(brand);
		driver.findElement(By.cssSelector(".LM6RPg")).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.titleContains("Redmi"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='"+phone_model+"']"))).click();
		Set<String> windows=driver.getWindowHandles();
		for (String window : windows) {
			if(window!=driver.getWindowHandle())
				driver.switchTo().window(window);
		}
		title=driver.findElement(By.cssSelector("._35KyD6")).getText();
		try {
			assert title.contains(phone_model);
			test.log(LogStatus.PASS, phone_model+" Selected");
		}catch(AssertionError a){
			test.log(LogStatus.FAIL, "Wrong product selected");
		}
	}

	@AfterMethod
	public void afterMethod() {
		report.endTest(test);
		report.flush();
		driver.quit();
	}

}
