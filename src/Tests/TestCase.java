package Tests;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCase extends BaseClass{
	public static String title;
	//private static String url=BaseClass.url;
	//private static String username=BaseClass.username;
	//private static String password=BaseClass.password;
	//private static String brand=BaseClass.brand;
	//private static String phone_model=BaseClass.phone_model;
	
	public static void main(String arg[]) throws Exception {
		BaseClass.getValues();
		System.out.println(url);
		System.setProperty("webdriver.chrome.driver",".\\Browser File\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		
		WebDriverWait wait=new WebDriverWait(driver,20);
		title="Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!";
		assert title.equalsIgnoreCase(driver.getTitle());
		driver.findElement(By.cssSelector("._2zrpKA._1dBPDZ")).sendKeys(username);
		driver.findElement(By.cssSelector("._2zrpKA._3v41xv._1dBPDZ")).sendKeys(password);
		driver.findElement(By.cssSelector("._2AkmmA._1LctnI._7UHT_c")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".LM6RPg"))).sendKeys(brand);
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("._1va75j")));
		BaseClass.getTime();
		driver.findElement(By.cssSelector(".vh79eN")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='"+phone_model+"']"))).click();
		Set<String> windows=driver.getWindowHandles();
		for (String window : windows) {
			if(window!=driver.getWindowHandle())
				driver.switchTo().window(window);
		}
		title=driver.findElement(By.cssSelector("._35KyD6")).getText();
		assert title.contains(phone_model);
	}
}
