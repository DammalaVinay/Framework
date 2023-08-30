package vinayDammala.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		ChromeOptions option = new ChromeOptions();

		option.setAcceptInsecureCerts(true);

		option.addArguments("start-maximized");
		
		WebDriver driver = new ChromeDriver(option);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.get("https://rahulshettyacademy.com/client");
		
		String productName = "ZARA COAT 3";
		
		String countryName = "India";

		driver.findElement(By.id("userEmail")).sendKeys("sample@sample.com");

		driver.findElement(By.id("userPassword")).sendKeys("Sample@123");

		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		/*products.stream().filter(
				product -> product.findElement(By.cssSelector(".mb-3 b")).getText().equalsIgnoreCase(productName))
				.forEach(s -> System.out.println(s.findElement(By.cssSelector(".mb-3 b")).getText()));
*/
		WebElement prod = products.stream().filter(
				product -> product.findElement(By.cssSelector(".mb-3 b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//Thread.sleep(3000);
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".infoWrap .cartSection h3"))));
		
		List<WebElement> cartSection = driver.findElements(By.cssSelector(".infoWrap .cartSection h3"));
		
		//cartSection.stream().forEach(s->System.out.println(s.getText()));
		
		boolean match = cartSection.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions act = new Actions(driver);
		
		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), countryName).build().perform();
		
		//driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys(countryName); -> just normally used actions class for sendkeys
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results .ng-star-inserted .fa.fa-search"))));
		
		List<WebElement> countryValues = driver.findElements(By.cssSelector("span[class='ng-star-inserted']"));
				countryValues.stream().filter(s-> s.getText().equalsIgnoreCase(countryName)).forEach(s->s.click());
				
		driver.findElement(By.cssSelector(".actions .btnn")).click();
		
		String orderMessage= driver.findElement(By.cssSelector("h1[class='hero-primary']")).getText();
		
		System.out.println(orderMessage);
		
		Assert.assertTrue(orderMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();		

	}

}
