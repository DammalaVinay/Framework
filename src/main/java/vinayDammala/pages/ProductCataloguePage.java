package vinayDammala.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vinayDammala.commons.ReusableMethods;

public class ProductCataloguePage extends ReusableMethods {

	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {

		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(id = "toast-container")
	WebElement toasterMessage;

	By product = By.cssSelector(".mb-3");

	public CartPage addProductToCart(String productName) {

		waitForVisibilityOfElement(product);

		WebElement prod = products.stream().filter(
				product -> product.findElement(By.cssSelector(".mb-3 b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		waitForInvisibilityOfElement(toasterMessage);
		
		CartPage cartPage = new CartPage(driver);
		
		return cartPage;

	}

}
