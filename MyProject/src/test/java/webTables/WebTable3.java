package webTables;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTable3
{
	/** Scenario : 3
	 * navigate to vtiger application
	 * navigate to organizations page
	 * capture all the organizations names and print in console
	 */
	public static void main(String[] args)
	{
		//Step1 : Launch Browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();                                
				
		//Step2 : Maximize window
		driver.manage().window().maximize();
				
		//Step3 : Provide implicit time for 10 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
		//Step4 : Load the url into browser/ Navigate to VTiger Application
		driver.get("http://localhost:8888/");

		// Step5 : Login to the application with valid User name and Password
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//Click on organizations link
		driver.findElement(By.xpath("//a[.='Organizations'][1]")).click();
		
		//Capture all organizations names and print in the console
		List<WebElement> allOrganizations = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[3]/a[@title='Organizations']"));
		int count = 0;
		for (WebElement eachOrg : allOrganizations) 
		{
			count++;
			System.out.println(eachOrg.getText());
			
		}
		System.out.println("Total Organization created are : "+count);
	}
}
