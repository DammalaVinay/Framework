package vinayDammala.commons;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import vinayDammala.pages.OrdersPage;

public class ReusableMethods {

	WebDriver driver;

	public ReusableMethods(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartBtn;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersBtn;

	public void waitForVisibilityOfElement(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public void waitForInvisibilityOfElement(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void addToCart() {

		cartBtn.click();
	}
	
	public OrdersPage ordersPage() {

		ordersBtn.click();
		
		OrdersPage  orderPage = new OrdersPage(driver);
		
		return orderPage;
		
	}
	
	public void waitForVisibilityOfElement(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOf(element));

	}
	
	public void sendTextUsingActionsClass(WebElement element, String text){
		
		Actions act = new Actions(driver);
		
		act.sendKeys(element, text).build().perform();
		
	}
	
}
