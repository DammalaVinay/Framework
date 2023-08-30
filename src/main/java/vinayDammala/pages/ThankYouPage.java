package vinayDammala.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vinayDammala.commons.ReusableMethods;

public class ThankYouPage extends ReusableMethods {

	WebDriver driver;

	public ThankYouPage(WebDriver driver) {

		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "h1[class='hero-primary']")
	WebElement thanksYouText;

	public String getThankYouPageText() {

		return thanksYouText.getText();
	}
}
