package test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonTest {

	public static void main(String[] args) throws IOException {
		// Setup
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://amazon.in");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);

		// Search 'Samsung' and GO
		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys("Samsung mobile");
		WebElement go = driver.findElement(By.id("nav-search-submit-button"));
		go.click();

		List<WebElement> Name = driver.findElements(By.xpath("//h2/a/span[contains(text(),'Samsung')]"));
		System.out.println("Total items: " + Name.size());
		List<WebElement> Symbol = driver.findElements(By.xpath("//span[@class='a-price-symbol']"));
		System.out.println("Total items: " + Symbol.size());
		List<WebElement> Price = driver.findElements(By.xpath("//span[@class='a-price']/span[2]"));
		System.out.println("Total items: " + Price.size());

		for (int i = 0; i < Name.size(); i++) {
			System.out.println(Price.get(i).getText() + " " + Name.get(i).getText());
		}

		/*
		 * For each loop to find all device names for(WebElement Samsung: Name) {
		 * System.out.println("List: " + Samsung.getText()); }
		 */

		// Screenshot
		TakesScreenshot TsObj = (TakesScreenshot) driver;
		File fileobj = TsObj.getScreenshotAs(OutputType.FILE);
		File screenshotObj = new File("image.png");
		FileUtils.copyFile(fileobj, screenshotObj);

		driver.quit();
	}
}