package calendarPopup;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleCalenderForFutureDate
{
	public static void main(String[] args) throws InterruptedException 
	{
		/**Scenario2 : When the date is not available in the current DOM structure (i.e future date)*/
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
//		Actions actions = new Actions(driver);
//		actions.moveByOffset(10, 10).click().perform();
//		Thread.sleep(3000);
		
		                          //or
		//Step5 : Click on advertisement cancle button
		driver.findElement(By.cssSelector("span[class='commonModal__close']")).click();
		
		//Click on Depature
		driver.findElement(By.xpath("//span[text()='Departure']")).click();
		Thread.sleep(3000);
		
		//Select Oct20 2024 from calendar form current DOM (Use infinite for loop along with try catch block becuuse if the date is not available in the current DOM then it will throw "NoSuchElementException")
		     /**
		      * suppose if the required date in available in the current DOM then it will click on the date other it will raise exception saying "NoSuchElementException"
		      *  so when exception raised catch block will get execute. In catch block we are performing click action in the next month button so it will click on next month.
		      *   again the loop will continue until it finds the required date in the DOM.Once it finds the required date then immediate the loop will terminate.. 

		      */
		for(;;)     // while(true){} --> infinite loop
		{
			try 
			{
				driver.findElement(By.xpath("//div[@aria-label='Sat Nov 30 2024']")).click();   // required date...
				break;
			} catch (Exception e) 
			{
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
				Thread.sleep(3000);
			}
		}
		Thread.sleep(3000);

		//Click on Return
		driver.findElement(By.xpath("//span[text()='Return']")).click();
		Thread.sleep(3000);
		
		//select Dec31 2024 from current DOM
		
		for(;;)
		{
			try 
			{
		        driver.findElement(By.xpath("//div[@aria-label='Mon Dec 30 2024']")).click();   // required date...
				break;
			} catch (Exception e) 
			{
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
				Thread.sleep(3000);
			}
		}
		Thread.sleep(10000);
		
		//Close the browser
		//driver.close();


	}

}
