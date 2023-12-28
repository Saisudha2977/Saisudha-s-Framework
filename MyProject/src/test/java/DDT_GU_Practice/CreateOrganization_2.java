package DDT_GU_Practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Scenario 2: Launch Browser Login to application with valid credentials
 * Navigate to Organizations link Click on Create Organization look Up Image
 * Create Organization with Mandatory fields Save and Verify logout of
 * Application
 */
public class CreateOrganization_2 {
	public static void main(String[] args) {

		// Step1: Launch the browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/");

		// Step2 : Login to the application with valid credentials
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();

		// Step3: Navigate to Organizations Link
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();

		// Step4: Click on Create Organizations Look up Image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// Step5: Create Organization with mandatory fields
		String organisationName = "XYZ";
		Random r = new Random();
		int num = r.nextInt(100);
		String orgName = num + organisationName;
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		// Step6: Save
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();

		// Step7: Verify
		String ogrHeader = driver.findElement(By.className("dvHeaderText")).getText();
		if (ogrHeader.contains(orgName)) {
			System.out.println(ogrHeader);
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}

		// Step8: Logout from the application
		WebElement signOut = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(signOut).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		System.out.println("Logout is successful..");

	}
}
