package DDT_GU_Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;



public class CreateOrganizationWithIndustryUsingDDT {
	
    static WebDriver driver = null;
	public static void main(String[] args) throws IOException, InterruptedException {
		// When ever we are using DDT for developing our TestScripts make sure first we have to read all the data in the beginning itself..
		//SO that the data will be read and keep in ready by JVM
		
		//Step1: Read all required data from external resources
		/* Read data from Property file */
		
		//Open the document in Java readable format
		FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		
		//Create an Object for Properties Class
		Properties p = new Properties();
		
		//Load the document into the Properties class
		p.load(fisp);
		
		//Provide key and read the value
		 String USERNAME = p.getProperty("username");
		 String PASSWORD = p.getProperty("password");
		 String BROWSER = p.getProperty("browser");
		 String URL = p.getProperty("url");
		 
		 /* Read data from Excel Sheet */
		/* //Open the document in java readable format
		 FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx.xlsx");
		 
		 //Create WorkBook
		 Workbook wb = WorkbookFactory.create(fise);
		 
		 //Navigate to required Sheet
		 Sheet sheet = wb.getSheet("Organizations");
		
		 //Navigate to required Row
		 Row row = sheet.getRow(4);
		 
		 //Navigate required Cell
		 Cell cell = row.getCell(2);
		 
		 //Read data from the cell
		 cell.getStringCellValue();
		 
		 //Close the WorkBook
		 wb.close();
		 */
		 
		 
		 /* Use Method chaining to void unnecessary storage/usage of variables */
		 Random r = new Random();
		 int num = r.nextInt(1000);
		 
		 FileInputStream fispp = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx.xlsx");
		 Workbook wb = WorkbookFactory.create(fispp);
		 String ORGNAME = wb.getSheet("Organizations").getRow(4).getCell(2).getStringCellValue()+num;  // ORganization name should be unique
		 String INDUSTRY = wb.getSheet("Organizations").getRow(4).getCell(3).getStringCellValue();
		 wb.close();
		 
		 //Step2 : Launch the Browser   ---> Runtime Polymorphism here driver is exhibiting multiple behaviors based on the run time data that is coming from our Property file..
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
			 
		    driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
			Select select = new Select(industryDropdown);
			select.selectByValue(INDUSTRY);
			
			//Step7 : Save 
			driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
			
			//Step8 : Verify
			String OrgHeader = driver.findElement(By.className("dvHeaderText")).getText();
			if(OrgHeader.contains(ORGNAME)) {
				System.out.println(ORGNAME);
				System.out.println("Organization has been created..");
				String industryType = driver.findElement(By.id("dtlview_Industry")).getText();
				if(industryType.equals(INDUSTRY))
				{
					System.out.println(industryType);
					System.out.println(INDUSTRY+" is choosen from dropdown..");
				}
				else 
				{
					System.out.println(INDUSTRY+" is not choosen from dropdown..");
				}
			}else
			{
				System.out.println("Organization has not been created..");  // If you give any other browser other than this chrome,firefox,edge browser then we bound to get "NULL POINTER EXCEPTION" because the driver value is null..
			}
			
			//Step9 : logout of Application
			WebElement signOut = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			Actions actions = new Actions(driver);
			actions.moveToElement(signOut).perform();
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
			System.out.println("Logout is successful..");
	}
}
