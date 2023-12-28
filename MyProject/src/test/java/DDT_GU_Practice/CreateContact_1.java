package DDT_GU_Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 *
 * Scenario 1: Launch Browser Login to application with valid credentials
 * Navigate to Contacts link Click on Create contact look Up Image Create
 * Contact with Mandatory fields Save and Verify logout of Application
 */
public class CreateContact_1 {
	public static void main(String[] args) throws InterruptedException {
		// Step1 : Launch the Browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/");

		// Step2 : Login to the application with valid User name and Password
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();

		// Step3 :Navigate to Contacts Link
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();

		// Step4 :Click on Create Contact Look Up Image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step5 :Create Contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("SAISUDHA");

		// Step6 : Save
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();

		// Step7 :Validate
		String contactHeader = driver.findElement(By.cssSelector("span[class='dvHeaderText']")).getText();
		if (contactHeader.contains("SAISUDHA")) {
			System.out.println(contactHeader);
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}

		// Step8 : Logout from the application
		WebElement signOut = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(signOut).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		System.out.println("Logout is successful..");

	}
}
