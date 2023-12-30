package DDT_GU_Practice;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SCG_Tags_CovidCases 
{
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.worldometers.info/coronavirus/country/india/");
		List<WebElement> graphEle = driver.findElements(By.xpath("//*[local-name()='svg' and@class='highcharts-root']//*[name()='text']"));
	   Actions act = new Actions(driver);
		for(WebElement e:graphEle) {
	    	act.moveToElement(e).perform();
	    	System.out.println(e.getText());
	    	}
	    }
}