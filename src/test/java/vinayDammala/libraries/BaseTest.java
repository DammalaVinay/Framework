package vinayDammala.libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import vinayDammala.pages.LoginToApplicationPage;

public class BaseTest {

	public WebDriver driver;

	public LoginToApplicationPage loginPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\vinayDammala\\resources\\GlobalData.properties");

		prop.load(fis);

		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");

		if (browserName.contains("Chrome")) {

			WebDriverManager.chromedriver().setup();

			ChromeOptions option = new ChromeOptions();
			
			if(browserName.contains("headless")) {
				
				option.addArguments("headless");
			}
			

			option.setAcceptInsecureCerts(true);

			option.addArguments("start-maximized");

			driver = new ChromeDriver(option);
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			
			System.setProperty("webdriver.gecko.drive",System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
			
			 driver = new FirefoxDriver();
			
			 driver.manage().window().maximize();
			

		} else if (browserName.equalsIgnoreCase("Edge")) {

		}
		
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public LoginToApplicationPage launchApplication() throws IOException {

		driver = initializeDriver();

		loginPage = new LoginToApplicationPage(driver);

		loginPage.goToURL();

		return loginPage;

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		driver.close();
	}

	// Method to get the data from JSON file
	public List<HashMap<String, String>> getJSONData(String filePath) throws IOException {

		// Read JSON to String
		String JSONcontent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to Hashmap-> we need to use external jar Jackson DataBind

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(JSONcontent,
				new TypeReference<List<HashMap<String, String>>>() {

				});

		return data;

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

		/*
		 * TakesScreenshot ts = (TakesScreenshot) driver;
		 * 
		 * File source = ts.getScreenshotAs(OutputType.FILE);
		 * 
		 * //File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		 * //FileUtils.copyFile(src, new
		 * File(System.getProperty("user.dir")+"//reports"+testCaseName+".png"));
		 * 
		 * File file = new
		 * File(System.getProperty("user.dir")+"//reports"+testCaseName+".png");
		 * 
		 * FileUtils.copyFile(source, file);
		 */

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "//reports//new.png"));

		return System.getProperty("user.dir") + "//reports" + testCaseName + ".png";
	}
}
