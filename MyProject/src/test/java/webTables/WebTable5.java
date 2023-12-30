package webTables;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTable5 
{
	/** Scenario: 5
	 * navigate to organizations page
	 * click on 8th check box and capture the organizations name and delete the organization
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
        
		//click on 8th check box
		driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[10]/td[1]/input[@type='checkbox']")).click();
		
		//capture organization name of 8th check box
		String orgName = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[10]/td[3]/a[@title='Organizations']")).getText();
		System.out.println("Organization name is : "+orgName);
		
		//delete the organization
		driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[10]/td[8]/a[.='del']")).click();
		driver.switchTo().alert().accept();
		List<WebElement> allOrganizations = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[3]/a[@title='Organizations']"));
		int count = 0;
		for (WebElement eachOrg : allOrganizations) 
		{
			count++;
			if(!eachOrg.equals(orgName))
			{
				System.out.println(eachOrg.getText());
			}
			else
			{
				System.out.println(eachOrg.getText()+" is not deleted..Pleae check once");
			}
		}
		System.out.println("Total no. of organizations are : "+count);
	}
}
