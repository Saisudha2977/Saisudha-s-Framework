package DDT_GU_Practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
Scenario 4:
Launch Browser
Login to application with valid credentials
Navigate to Organizations link
Click on Create Organization look Up Image
Create Organization with Mandatory fields
Select "Energy" in the industry drop down
Select "Customer" in the Type Drop down 
Save and Verify
logout of Application
 */
public class CreateOrganizationBySelectingValueFromIndustryAndTypeDropdown_4 {

	public static void main(String[] args) {
		// Step1: Launch Browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/");

		// Step2: Login to application with valid credentials
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();

		// Step3: Navigate to Organizations link
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();

		// Step4: Click on Create Organization look Up Image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// Step5: Create Organization with Mandatory fields
		String organisationName = "XYZ";
		Random r = new Random();
		int num = r.nextInt(100);
		String orgName = num + organisationName;
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		// Step6: Select "Energy" in the industry drop down
		WebElement IndustryDropdown = driver.findElement(By.name("industry"));
		Select select = new Select(IndustryDropdown);
		select.selectByValue("Energy");

		// Step7: Select "Customer" in the Type Drop down
		WebElement typeDropdown = driver.findElement(By.name("accounttype"));
		Select sel = new Select(typeDropdown);
		sel.selectByValue("Customer");

		// Step8: Save
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();

		// Step9: Verify
		String orgHeader = driver.findElement(By.className("dvHeaderText")).getText();
		if (orgHeader.contains(organisationName)) 
		{
			System.out.println("organisationName : "+organisationName);
			System.out.println("Organization has been created successfully..");
			
			String capturedIndustryType = driver.findElement(By.id("dtlview_Industry")).getText();
			String selectedIndustryType = "Energy.";
			if (selectedIndustryType.equalsIgnoreCase(capturedIndustryType)) 
			{
				System.out.println("capturedIndustryType is :" + capturedIndustryType);
				System.out.println(capturedIndustryType + " is choosen successfully..");
			} 
			else
			{
				System.out.println(capturedIndustryType + " is not choosen from Industry dropdown");
			}
			
			String capturedTypeDropdownValue = driver.findElement(By.xpath("//font[text()='Customer']")).getText();
			String selectedTypeOption = "Customer";
			if (capturedTypeDropdownValue.equals(selectedTypeOption)) 
			{

				System.out.println("capturedTypeDropdownValue is : " + capturedTypeDropdownValue);
				System.out.println(selectedTypeOption + " is choosen successfully from Type dropdown");
			} 
			else 
			{
				System.out.println(selectedTypeOption + " is not choosen successfully from Type dropdown");
			}
		} 
		else 
		{
			System.out.println("Organization has not been created successfully..");
		}
		
		// Step10: logout of Application
		WebElement signOut = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(signOut).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		System.out.println("Logout is successful..");
	}

}
