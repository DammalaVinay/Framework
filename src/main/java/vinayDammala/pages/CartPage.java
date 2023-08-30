package vinayDammala.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vinayDammala.commons.ReusableMethods;

public class CartPage extends ReusableMethods {

	WebDriver driver;

	public CartPage(WebDriver driver) {

		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "infoWrap .cartSection h3")
	WebElement waitForElement;

	@FindBy(css = ".infoWrap .cartSection h3")
	List<WebElement> cartProducts;

	@FindBy(css = ".totalRow button")
	WebElement checkoutBtn;

	By product = By.cssSelector(".mb-3");

	public boolean verifyProductDisplay(String productName) {

		//waitForVisibilityOfElement(waitForElement);

		boolean match = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));

		return match;

	}

	public PaymentPage checkOut() {

		checkoutBtn.click();
		
		PaymentPage paymentPage = new PaymentPage(driver);
		
		return paymentPage;
	}

}
