package GenericUtilities;
/**
 * This is a generic class consisting of all basic configuration annotations of TestNG
 * @author saisudha
 */

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class BaseClass
{
	public PropertyFileUtility pUtil = new PropertyFileUtility();
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public JavaUtility jUtil = new JavaUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver;   //declare sdriver variable as static
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuiteConfig()
	{
		System.out.println("========== Database connection successful ===========");
	}
	
	//@Parameters("browser")
	//@BeforeTest
	@BeforeClass(alwaysRun = true)
	public void beforeClassConfig(/*String BROWSER*/) throws IOException
	{
		/* Read data from Property File*/
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");

		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else
		{
			System.out.println("Invalid browser is given.. Please check..");
		}
		
		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver, 10);
		driver.get(URL);
		
		sdriver = driver; // initializing the sdriver variable with updated driver
		System.out.println(BROWSER+" ======== browser launched =========");

	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() throws IOException
	{
		/* Read data from Property File*/
		String  USERNAME = pUtil.readDataFromPropertyFile("username");
		String  PASSWORD = pUtil.readDataFromPropertyFile("password");

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		System.out.println(" ========= Login successful ========");
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethodConfig() throws InterruptedException
	{
		HomePage hp = new HomePage(driver);
		Thread.sleep(2000);
		hp.logOutOfApp(driver);
		
		System.out.println(" ========= Logout successful ========");
	}
	
	//@AfterTest
	@AfterClass(alwaysRun = true)
	public void afterClassConfig() 
	{
		driver.quit();
		System.out.println(" ========== browser closed ==========");
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuiteConfig()
	{
		System.out.println("========== Database connection closed ==========");
	}

}
