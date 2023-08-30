package vinayDammala.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import vinayDammala.libraries.BaseTest;
import vinayDammala.pages.CartPage;
import vinayDammala.pages.ProductCataloguePage;

public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups="Error Validations", retryAnalyzer = vinayDammala.libraries.Retry.class)
	public void loginPageErrorValidation() throws InterruptedException, IOException {

		loginPage.LoginToApplication("vinaydammala@vinay.com", "Test@564");

		Assert.assertEquals(loginPage.getErrorMessage(), "Incorrect email password.");
		
	}
	
	
	@Test
	public void cartPageErrorValidation() throws InterruptedException, IOException {
		
		String productName = "ZARA COAT 3";

		ProductCataloguePage productCatalogue =loginPage.LoginToApplication("vinaydammala@vinay.com", "Test@123");

		CartPage cartPage =productCatalogue.addProductToCart(productName);

		productCatalogue.addToCart();

		boolean match = cartPage.verifyProductDisplay("ZARA COAT 4");
		
		Assert.assertFalse(match);
	
	}

}
