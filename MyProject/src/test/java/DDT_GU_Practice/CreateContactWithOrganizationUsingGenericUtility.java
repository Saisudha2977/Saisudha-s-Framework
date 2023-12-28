package DDT_GU_Practice;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.github.dockerjava.api.model.Driver;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganizationUsingGenericUtility {
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		WebDriver driver = null;
	    // Step1 : Create objects of all Utility Classes( When we Use Generic Utility Package)
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		
		//Step2 : Read all the required data
		/* read data from Property File*/
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		
		/* read data from Excel File*/        // do not append getRandomNumber() here because here we are searching already created organization not the new organization..
		String LASTNAME = eUtil.readSingleDataFromExcelFile("Contacts", 4, 3);
		String ORGNAME = eUtil.readSingleDataFromExcelFile("Organizations", 4, 2);
		
		//Ste3 : Launch Browser
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
			System.out.println("Invaild Browser choosen..Please Check!");
		}
		
		wUtil.maximizeWindow(driver);
		driver.get(URL);
		wUtil.waitForPageLoad(driver, 15);
		
		//Step2 : Login to application with valid credentials
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		//Step3 : Navigate to Contacts link
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();

		//Step4 : Click on Create contact look up image
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step5 : Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//Step6 : Select the Organization from organization look up image
		
		// Click on Organization lookup Image in Contacts Page
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@title='Select']")).click();
		
		//Switch to Child window
		wUtil.switchToWindow(driver, "Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=");
		
		
		//Search for Organization
		driver.findElement(By.id("search_txt")).sendKeys(ORGNAME);
		driver.findElement(By.name("search")).click();
		
		//Click on the searched Organization
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='javascript:window.close();']")).click();
		
		System.out.println("==================+++++++++++++++++++++++++  Delete Later +++++++++++++++++++++++++++++++++++++++====================+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		//Step1 : Capture all window IDs
		Set<String> allWindowIDs= driver.getWindowHandles();
		
		//Step2 : Navigate to each window 
		for(String eachWindowId:allWindowIDs)
		{
			//Step3 : switch to each window and capture the window title
			String actualWindowTitle = driver.switchTo().window(eachWindowId).getTitle();
			System.out.println("Titles are : "+actualWindowTitle);
		}	
		System.out.println("=================++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=====================");
		

		 //Step7 : Switch back to Parent window
		Thread.sleep(2000);
		wUtil.switchToWindow(driver, "EditView&return_action=DetailView&parenttab=Marketing");
		
		//Step8 : Save
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		
		//Step9 : Verify
		String contactHeader = driver.findElement(By.className("dvHeaderText")).getText();
		System.out.println("contactHeader is : "+contactHeader);
		if(contactHeader.contains(LASTNAME)) {
			System.out.println("Contact has been created successfully along with Organization name");
			System.out.println("Pass");
		}
		else {
			System.out.println("Contact has not been created ");
		}
		
		//Step10 : logout of application
		WebElement signOutMouseHover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverAction(driver, signOutMouseHover);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		System.out.println("Logout is successful..");
		
		//Step 9: Close the Browser
		driver.close();
		
			
	}
}
