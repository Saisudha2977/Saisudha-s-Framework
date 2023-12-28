package DDT_GU_Practice;

import java.time.Duration;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
Scenario 5: - end to end - Integration
Launch Browser
Login to application with valid credentials
Navigate to Contacts link
Click on Create contact look up image
Create contact with manadatory fields
Select the Organization from organization look up image
Save and verify
logout of app
 */
public class CreateContactWithOrganization_5 {
	public static void main(String[] args) throws InterruptedException {
		//Step1 : Launch Browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/");

		//Step2 : Login to application with valid credentials
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//Step3 : Navigate to Contacts link
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();


		//Step4 : Click on Create contact look up image
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		//Step5 : Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("SAISUDHA");
		//driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();


		//Step6 : Select the Organization from organization look up image
	
		// Click on Organization lookup Image in Contacts Page
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@title='Select']")).click();
		
		//Switch the control to Child Window
		String mainWindowId = driver.getWindowHandle();
		System.out.println("Main window is : "+mainWindowId);
		Set<String> allWindowIds = driver.getWindowHandles();
		for(String eachWindowId:allWindowIds) {
			if(!eachWindowId.equals(mainWindowId)) {
				driver.switchTo().window(eachWindowId);
				System.out.println("The control has switched to Child window");
			}
		}
		
		//Search for Organization
		driver.findElement(By.id("search_txt")).sendKeys("Infosys8258");
		driver.findElement(By.name("search")).click();
		
		//Click on the searched Organization
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='javascript:window.close();']")).click();

		 //Step7 : Switch back the control from parent window to  main window in order to perform the action on main window elements
		Thread.sleep(2000);
		driver.switchTo().window(mainWindowId);
		
		//Step8 : Save
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();

		//Step9 : Verify
		String contactHeader = driver.findElement(By.className("dvHeaderText")).getText();
		System.out.println("contactHeader is : "+contactHeader);
		if(contactHeader.contains("SAISUDHA")) {
			System.out.println("Contact has been created successfully along with Organization name");
			System.out.println("Pass");
		}
		else {
			System.out.println("Contact has not been created ");
		}
		
		//Step10 : logout of application
		WebElement signOut = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(signOut).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		System.out.println("Logout is successful..");

	}
}
/*
 package vtiger.Practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrg_Scenario5 {
	public static void main(String[] args) throws InterruptedException {

		// Step 1: Launch The Browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888");

		// Step 2: Login To Application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();

		// Step 3: click on contacts Link
		driver.findElement(By.linkText("Contacts")).click();

		// Step 4: click on create contact look up Image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		// step 5: create contact using mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("spiderMan");

		// Step 6: click on Organizations look Up image
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@title='Select']")).click();

		// Step 7: Switch the control to child window
		String mainWinID = driver.getWindowHandle();

		Set<String> allWinIds = driver.getWindowHandles();

		for (String ID : allWinIds) {
			if (!ID.equals(mainWinID)) {
				driver.switchTo().window(ID);
				System.out.println("window swicthed to child");
			}
		}

		// Step 8: search for Organization
		driver.findElement(By.name("search_text")).sendKeys("ch399");
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[.='Reliance223']")).click();

		// Step 9: Switch the control back to main window
		driver.switchTo().window(mainWinID);

		// Step 10 : save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 11: Validate
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (contactHeader.contains("spiderMan")) {
			System.out.println("PASS");
			System.out.println(contactHeader);
		} else {
			System.out.println("FAIL");
		}

		// step 12: logout
		WebElement mouseHover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(mouseHover).perform();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Logout successfull");

		// Step 13: Close the browser
		driver.quit();

	}

}

 * */
