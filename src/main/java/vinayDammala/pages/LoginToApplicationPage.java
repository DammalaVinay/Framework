package vinayDammala.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vinayDammala.commons.ReusableMethods;

public class LoginToApplicationPage extends ReusableMethods{

	WebDriver driver;

	public LoginToApplicationPage(WebDriver driver) {

		super(driver);
		
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement loginBtn;
	
	@FindBy(css="[class*='toast-message']")
	WebElement errorMessage;

	public ProductCataloguePage LoginToApplication(String emailID, String password) {

		userEmail.sendKeys(emailID);

		userPassword.sendKeys(password);

		loginBtn.click();

		ProductCataloguePage productCatalogue = new ProductCataloguePage(driver);

		return productCatalogue;

	}

	public void goToURL() {

		driver.get("https://rahulshettyacademy.com/client");
	}

	public String getErrorMessage() {
		
		waitForVisibilityOfElement(errorMessage);
		
		return errorMessage.getText();
	}
}
