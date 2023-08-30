package vinayDammala.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vinayDammala.commons.ReusableMethods;

public class PaymentPage extends ReusableMethods {

	WebDriver driver;

	public PaymentPage(WebDriver driver) {

		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement countryNameInput;

	@FindBy(css = ".ta-results .ng-star-inserted .fa.fa-search")
	WebElement countryNameWait;

	@FindBy(css = "span[class='ng-star-inserted']")
	List<WebElement> countryNamesResult;

	@FindBy(css = ".actions .btnn")
	WebElement placeOrder;

	public void selectCountryName(String countryName) {

		sendTextUsingActionsClass(countryNameInput, countryName);

		waitForVisibilityOfElement(countryNameWait);

		countryNamesResult.stream().filter(s -> s.getText().equalsIgnoreCase(countryName)).forEach(s -> s.click());

	}

	public ThankYouPage placeOrder() {

		placeOrder.click();
		
		ThankYouPage thankYou = new ThankYouPage(driver);
		
		return thankYou;
	}
}
// driver.findElement(By.cssSelector("[placeholder='Select
// Country']")).sendKeys(countryName); -> just normally used actions class for
// sendkeys

// wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results
// .ng-star-inserted .fa.fa-search"))));

/*
 * List<WebElement> countryValues =
 * driver.findElements(By.cssSelector("span[class='ng-star-inserted']"));
 * countryValues.stream().filter(s->
 * s.getText().equalsIgnoreCase(countryName)).forEach(s->s.click());
 * 
 * driver.findElement(By.cssSelector(".actions .btnn")).click();
 * 
 * }
 */