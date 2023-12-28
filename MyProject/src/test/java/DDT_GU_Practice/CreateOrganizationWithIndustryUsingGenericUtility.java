package DDT_GU_Practice;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationWithIndustryUsingGenericUtility
{
	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException 
	{
		    WebDriver driver = null;
		   /*Note : When we r using Generic Utility package we have to create object of all utility classes at the beginning only*/
		     //Step[1 : Create objects of all utility classes
		     PropertyFileUtility pUtil = new PropertyFileUtility();
		     ExcelFileUtility eUtil = new ExcelFileUtility();
		     JavaUtility jUtil = new JavaUtility();
		     WebDriverUtility wUtil = new WebDriverUtility();
		     
		     //Step2 : Read all the required data
		     
		     /* read data from Property File */
		     String BROWSER = pUtil.readDataFromPropertyFile("browser");
		     String URL = pUtil.readDataFromPropertyFile("url");
		     String PASSWORD = pUtil.readDataFromPropertyFile("password");
		     String USERNAME = pUtil.readDataFromPropertyFile("username");
		     
		     /* read data from Excel file */
		     String ORGNAME = eUtil.readSingleDataFromExcelFile("Organizations", 4, 2)+jUtil.getRandomNumber();
		     String INDUSTRYTYPE = eUtil.readSingleDataFromExcelFile("Organizations", 4, 3);
			 
			 //Step2 : Launch the Browser   
		     if(BROWSER.equalsIgnoreCase("chrome"))
			 {
			    WebDriverManager.chromedriver().setup();
				driver =new ChromeDriver();
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
				 System.out.println("Invalid Browser is given ");
			 }
				wUtil.maximizeWindow(driver);
				wUtil.waitForPageLoad(driver, 10);
				driver.get(URL);
				
				//Step3 : Login to application with valid credentials
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();

				//Step4 : Navigate to Organizations link
				driver.findElement(By.xpath("//a[text()='Organizations']")).click();

				//Step5 : Click on Create Organization look Up Image
				driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

				//Step5 : Create Organization with Mandatory fields
				Thread.sleep(4000);
				driver.findElement(By.name("accountname")).sendKeys(ORGNAME);

				//Step6 : Select "Chemicals" in the industry drop down
				WebElement industryDropdown = driver.findElement(By.name("industry"));
				wUtil.handleDropDown(industryDropdown, INDUSTRYTYPE);
				
				//Step7 : Save 
				driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
				
				//Step8 : Verify
				String OrgHeader = driver.findElement(By.className("dvHeaderText")).getText();
				if(OrgHeader.contains(ORGNAME)) {
					System.out.println(ORGNAME);
					System.out.println("Organization has been created..");
					String industryType = driver.findElement(By.id("dtlview_Industry")).getText();
					if(industryType.equals(INDUSTRYTYPE))
					{
						System.out.println(industryType);
						System.out.println(INDUSTRYTYPE+" is choosen from dropdown..");
					}
					else 
					{
						System.out.println(INDUSTRYTYPE+" is not choosen from dropdown..");
					}
				}else
				{
					System.out.println("Organization has not been created..");  // If you give any other browser other than this chrome,firefox,edge browser then we bound to get "NULL POINTER EXCEPTION" because the driver value is null..
				}
				
				//Step9 : logout of Application
				WebElement signOutMouseHover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				wUtil.mouseHoverAction(driver, signOutMouseHover);
				driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
				System.out.println("Logout is successful..");
				
				//Step10 : Close the Browser
				driver.close();
	}
}
