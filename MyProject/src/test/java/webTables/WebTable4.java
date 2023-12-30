package webTables;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTable4 
{
	/** Scenario : 4
	 * navigate to organizations page
	 * click on the last check box
	 *
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
				
				//Click on the last check box
				driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[last()]/td[1]/input[@type='checkbox']")).click();
				
				

		
	}

}
