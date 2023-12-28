package vtiger.contactTests;

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
import objectRepository.ContactInformationPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrganizationsPage;

public class CreateContactWithOrganizationTest
{
	@Test
	public void createOrgWithIndustryTest() throws IOException, InterruptedException 
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
		String INDUSTRYTYPE = eUtil.readSingleDataFromExcelFile("Contacts", 4, 2);
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
		
		//Step5 : Click on Organizations Link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLink();
		
		//Step 6 : Click on create organizations look up image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnOrganizationsLookUpImg();
		
		//Step 7 : Create Organization with mandatory fields
		CreateNewOrganizationPage cno = new CreateNewOrganizationPage(driver);
		cno.createNewOrganization(ORGNAME);
		
		//Step8 : Validate
		OrganizationInfoPage orgInfo = new OrganizationInfoPage(driver);
		String orgaHeader= orgInfo.getOrganizationHeader();
		if(orgaHeader.contains(ORGNAME)) {
			System.out.println(ORGNAME);
			System.out.println("Organization has been created..");
		}
		else
		{
			System.out.println("FAIL");
			System.out.println("Organization has not been created..");  // If you give any other browser other than this chrome,firefox,edge browser then we bound to get "NULL POINTER EXCEPTION" because the driver value is null..
		}
		
		//Step 9 : Click on Contacts link
		Thread.sleep(3000);
		hp.clickOnContactsLink();
		
		//Step10 : Click on create contact look up image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactlookUpImg();
		
		//Step11 : Create contact with mandatory fields using organization
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		Thread.sleep(3000);
		cncp.createNewContact(LASTNAME, ORGNAME, driver);;
		
		//Step12 : Validate for contacts
		ContactInformationPage cip = new ContactInformationPage(driver);
		String contactHeader = cip.getContactHeader();
		System.out.println("contactHeader is : "+contactHeader);
		if(contactHeader.contains(LASTNAME)) {
			System.out.println("Contact has been created successfully along with Organization name");
			System.out.println("Pass");
		}
		else {
			System.out.println("Contact has not been created ");
		}
		
		//Step13 : logout of application
		Thread.sleep(3000);
		hp.logOutOfApp(driver);
		System.out.println("Logout is successful..");
		
		//Step14 : Close the Browser
		driver.close();	
	}
}
