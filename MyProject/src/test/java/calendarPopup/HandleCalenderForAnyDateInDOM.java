package calendarPopup;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleCalenderForAnyDateInDOM
{
	public static void main(String[] args) throws InterruptedException 
	{
		{
			/**Scenario1: When the date is present in the current DOM structure
			 *  (in calendar when we open web page we can only the current month max 1 or 2 months dates
			 */
			
			//Step1 : Launch Browser
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			
			//Step2 : Maximize window
			driver.manage().window().maximize();
			
			//Step3 : Provide implicit time for 10 seconds
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			//Step4 : Load the url into browser/ Navigate to Make my trip website
			driver.get("https://www.makemytrip.com/");
			Thread.sleep(3000);
			
			//Click on login button
			driver.findElement(By.xpath("//p[text()='Login or Create Account']")).click();
			Thread.sleep(3000);
			
			                      
			// We can use Actionn's class moveByOffset(x,y) method to avoid adver
//			Actions actions = new Actions(driver);
//			actions.moveByOffset(10, 10).click().perform();
//			Thread.sleep(3000);
			
			                          //or
			//Step5 : Click on advertisement cancel button
			driver.findElement(By.cssSelector("span[class='commonModal__close']")).click();
			
			//Click on Departure
			driver.findElement(By.xpath("//span[text()='Departure']")).click();
			Thread.sleep(3000);
			
			//Select Jan19 2024 from calendar form current DOM
			driver.findElement(By.xpath("//div[@aria-label='Fri Jan 19 2024']")).click();
			Thread.sleep(3000);
			
			//Click on Return
			driver.findElement(By.xpath("//span[text()='Return']")).click();
			Thread.sleep(3000);
			
			//select Jan31 2024 from current DOM
	        driver.findElement(By.xpath("//div[@aria-label='Wed Jan 31 2024']")).click();
			Thread.sleep(3000);
			
			//Close the browser
			driver.close();
		}
	}
}
	
