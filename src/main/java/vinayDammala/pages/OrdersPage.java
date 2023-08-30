package vinayDammala.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vinayDammala.commons.ReusableMethods;

public class OrdersPage extends ReusableMethods {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {

		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> ordersList;

	public boolean verifyProductDisplay(String productName) throws InterruptedException {
		
		waitForVisibilityOfElement(By.cssSelector("tr td:nth-child(3)"));

		boolean match = ordersList.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));

		return match;

	}
}