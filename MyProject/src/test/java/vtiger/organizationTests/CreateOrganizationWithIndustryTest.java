package vtiger.organizationTests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrganizationsPage;

public class CreateOrganizationWithIndustryTest
{
	@Test
	public  void  createOrganizationWithIndustryTest() throws IOException, InterruptedException
	{
		WebDriver driver = null;
		//Step1 : Create all Utility Classes objects
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		
		//Step2 : Read all data from external resources
		/* Read data from Property File */
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		String URL = pUtil.readDataFromPropertyFile("url");
		String BROWSER = pUtil.readDataFromPropertyFile("browser");

		/* Read data from Exce file*/
		String LASTNAME = eUtil.readSingleDataFromExcelFile("Contacts", 4, 3);
		String INDUSTRYTYPE = eUtil.readSingleDataFromExcelFile("Organizations", 4, 3);
		String ORGNAME = eUtil.readSingleDataFromExcelFile("Organizations", 4, 2)+jUtil.getRandomNumber();
		
		//Step3 : Launch Browser
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
		
		//Step4: Login to Application with valid credentials
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//Step4 : Click on Organizations link
		Thread.sleep(3000);
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLink();

		//Step5 : Click on Create Organization look Up Image
		OrganizationsPage orgPage = new OrganizationsPage(driver);
		orgPage.clickOnOrganizationsLookUpImg();

		//Step5 : Create Organization with Mandatory fields
		Thread.sleep(4000);
		CreateNewOrganizationPage newOrgName = new CreateNewOrganizationPage(driver);
		newOrgName.createNewOrganization(ORGNAME, INDUSTRYTYPE);
		System.out.println("Type of Industyr : "+INDUSTRYTYPE);
					
		//Step7 : Verify
		OrganizationInfoPage orgInfo = new OrganizationInfoPage(driver);
		String orgaHeader= orgInfo.getOrganizationHeader();
		if(orgaHeader.contains(ORGNAME))
		{
			System.out.println(ORGNAME);
			System.out.println("Organization has been created..");
		}else
		{
			System.out.println("Organization has not been created..");  // If you give any other browser other than this chrome,firefox,edge browser then we bound to get "NULL POINTER EXCEPTION" because the driver value is null..
		}
		
		//Step9 : logout of Application
		hp.logOutOfApp(driver);
		System.out.println("Logout is successful..");
		
		//Step10 : Close the Browser
		driver.close();
	}

}
