package calendarPopup;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleCalenderForCurrentDate 
{
	public static void main(String[] args) throws InterruptedException 
	{
		/**Scenario3 : When ever i run my script the date should take (Always should take only current date)*/
		
		//Capture system date/current date
		/*
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy");
		System.out.println("System date : "+date);
		System.out.println("Required date format : "+formatter.format(date));
		*/
		
		                 //or
		Date date = new Date();
		String curDate = date.toString();
		System.out.println(curDate);
		String[] dateArray = curDate.split(" ");
		System.out.println("Lingth is : "+dateArray.length);
//		for (String eachString : dateArray)
//		{
//			System.out.println(eachString);
//			
//		}
		String currentDate =dateArray[0]+" "+dateArray[1]+" "+dateArray[2]+" "+dateArray[5];
		System.out.println(currentDate);

		
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
		//Step5 : Click on advertisement cancel button
		driver.findElement(By.cssSelector("span[class='commonModal__close']")).click();
		
		//Click on Departure
		driver.findElement(By.xpath("//span[text()='Departure']")).click();
		Thread.sleep(3000);
		
		//select current date  or navigate to current date
		for(;;)     // while(true){} --> infinite loop
		{
			try 
			{                                                   // dynamic Xpath
				driver.findElement(By.xpath("//div[@aria-label='"+currentDate+"']")).click();   
				break;
			} catch (Exception e) 
			{
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
				Thread.sleep(3000);
			}
		}
	}
}

