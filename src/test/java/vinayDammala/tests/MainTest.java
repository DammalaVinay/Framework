package vinayDammala.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vinayDammala.libraries.BaseTest;
import vinayDammala.pages.CartPage;
import vinayDammala.pages.OrdersPage;
import vinayDammala.pages.PaymentPage;
import vinayDammala.pages.ProductCataloguePage;
import vinayDammala.pages.ThankYouPage;

public class MainTest extends BaseTest {

	String productName1 = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> data) throws InterruptedException, IOException {

		String countryName = "India";

		ProductCataloguePage productCatalogue = loginPage.LoginToApplication(data.get("email"),data.get("password"));

		CartPage cartPage = productCatalogue.addProductToCart(data.get("productName"));

		productCatalogue.addToCart();

		boolean match = cartPage.verifyProductDisplay(data.get("productName"));

		Assert.assertTrue(match);

		PaymentPage paymentPage = cartPage.checkOut();

		paymentPage.selectCountryName(countryName);

		ThankYouPage thankYou = paymentPage.placeOrder();

		String text = thankYou.getThankYouPageText();

		Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderPage() throws InterruptedException, IOException {

		loginPage.LoginToApplication("sample@sample.com", "Sample@123");

		OrdersPage orderPage = loginPage.ordersPage();

		boolean match = orderPage.verifyProductDisplay(productName1);

		Assert.assertTrue(match);

	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJSONData(System.getProperty("user.dir")+"//src//main//java//vinayDammala//resources//Data.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
				
	}

}
