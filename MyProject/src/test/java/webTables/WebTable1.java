package webTables;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTable1
{
	/**
	 * Scenarion1 
	 * navigate to vtiger application
	 * navigate to Organizations page
	 * check on all the check boxes
	 * 
	 */
	public static void main(String[] args) throws InterruptedException
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

		
		//Capture all required web elements and click on all check boxes if already selected now un select the check boxes
		List<WebElement> allElements = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[1]/input[@type='checkbox']"));
		for (WebElement eachElement : allElements) 
		{
			eachElement.click();
			Thread.sleep(1000);
		}	
	}
}
