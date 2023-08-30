package vinayDammala.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vinayDammala.libraries.BaseTest;
import vinayDammala.pages.CartPage;
import vinayDammala.pages.LoginToApplicationPage;
import vinayDammala.pages.PaymentPage;
import vinayDammala.pages.ProductCataloguePage;
import vinayDammala.pages.ThankYouPage;

public class StepDefinitionClass extends BaseTest{
	
	public LoginToApplicationPage loginPage;
	public ProductCataloguePage productCatalogue;
	public CartPage cartPage;
	public PaymentPage paymentPage;
	
	@When("I launch the Ecommerce Page")
	public void launch_the_Ecommerce_Page() throws IOException {
		
		 loginPage= launchApplication();
		
	}
	
	@Given("^I logged in to the website with username (.+) and password (.+)$")
	public void logged_in_to_the_website_using_username_password(String userName, String password) {
		
		productCatalogue = loginPage.LoginToApplication(userName,password);
		
	}
	
	 @When("^I add product (.+) to the cart$")
	 public void add_product_to_the_cart(String productName) {
		 
		 cartPage = productCatalogue.addProductToCart(productName);

		 
	 }

	 @When("^checkout product (.+) and submit the order$")
	 public void checkout_product_submit_the_order(String productName) {
		 
		 	productCatalogue.addToCart();

			boolean match = cartPage.verifyProductDisplay(productName);

			Assert.assertTrue(match);

			paymentPage = cartPage.checkOut();

			paymentPage.selectCountryName("India");
		 
	 }
	 @Then("{string} message is displayed on the screen")
	 public void message_is_displayed_on_the_screen(String string) {
	     
		 ThankYouPage thankYou = paymentPage.placeOrder();

			String text = thankYou.getThankYouPageText();

			Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
			
			driver.close();
		 
	 }
	 
	 @Then("{string} error message is displayed")
	 public void error_message_is_displayed(String message) {
		 
		 Assert.assertEquals(loginPage.getErrorMessage(), message);
		 
		 driver.close();
		 
		 
	 }
	    
}
