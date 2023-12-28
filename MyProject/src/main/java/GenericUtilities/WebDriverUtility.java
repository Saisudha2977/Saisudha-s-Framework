package GenericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class consists of re usable methods related to WebDriver
 * @author saisudha
 */
public class WebDriverUtility {
	/**
	 * This method will maximize the window  (not returning any data to caller)
	 * @param driver
	 */
	// For all WebDriver related actions we should parameterize the WebDriver..Suppose if we declare the WebDriver globally, if we do not initialize the driver/ upcaste the driver the driver value is null. So on null we can not perform any operations.When u parameterize what happens means, when we call this particular method we are giving the updated driver reference so the driver value will never become null. After browser launching only i.e after object creation(WebDriver driver = new ChromeDriver();) only i am giving the updated driver reference ...i am maximizin/minimizing/applying waits etc
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method will minimize the window
	 * @param driver
	 */
	
	// when u declare the WebDriver globally always the driver value will be null if we do not launch the browser. or do not create object for Browser launching so on null we can't perform any operation..So we have to always parameterize the WebDrier for all WebDriver related actions. so that After object created/ Browser launching only we are performing the actions like maximizze, minimize, giving waits..etc when we call this method we are giving the updated driver reference so the driver value will not be null. When u create an object a new session id will be created..i.e, updated driver reference..
	/* WebDriver driver = new ChromeDriver(); --> when we write this line then only a session id will be created for driver.otherwise driver value will be "null"
	 * Initially the driver value is null, after object got created i.e Browser launching only the driver will have a specific session id created. with the help of that session id only we can access all our driver related methods
	 * getSessionId() is used to get session id..
	 * when we call the WebDriver methods we are giving updated driver reference
	 */
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	/**
	 * This method will open the window in full screen mode
	 * @param driver
	 */
	public void fullScreenWindow(WebDriver driver)
	{
		driver.manage().window().fullscreen();
	}

	/**
	 * This method will wait for given specified time for all web elements to load
	 * @param driver
	 * @param seconds
	 */
	public void waitForPageLoad(WebDriver driver, int seconds)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));   // Specify time how long you want it should wait.. max 20 seconds
	}
	
	/**
	 * This method will wait for the specified time for a particular web element to be visible
	 * @param driver
	 * @param seconds
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver driver, int seconds, WebElement element ) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));    
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will wait for specified time for a particular web element to be clickable
	 * @param driver
	 * @param seconds
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver, int seconds, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will handle drop down using Index
	 * @param element
	 * @param index
	 */
	public void handleDropdown(WebElement element, int index)
	{
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	
	/**
	 * This method will handle drop down using value
	 * @param element
	 * @param value
	 */
	public void handleDropDown(WebElement element, String value) 
	{
		Select select = new Select(element);
		select.selectByValue(value);
	}
	
	/**
	 * This method will handle drop down using visible text
	 * @param text
	 * @param element
	 */
	public void handleDropDown(String text, WebElement element)
	{
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	
	/**
	 * This method will perform mouse hover action on a web element
	 * @param driver
	 * @param element
	 */
	public void mouseHoverAction(WebDriver driver, WebElement element)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
	/**
	 * This method will perform right click anywhere on the web page
	 * @param element
	 * @param driver
	 */
	/////// If we pass the web element then on that element it will perform right click. If we do not pass the web element then it is going to perform  the right click where ever the cursor is currently pointing on the web page
	public void rightClickAction(WebDriver driver)
	{
		Actions actions = new Actions(driver);
		actions.contextClick().perform();
	}
	
	/**
	 * This method will perform right click on a particular web element
	 * @param driver
	 * @param element
	 */
	///////////doubleClick () & contextClick() are overlaoded methods in Actions class
	public void rightClickAction(WebDriver driver, WebElement element)
	{
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();	
	}
	

	/**
	 * This method will perform double click anywhere on the webpage (where the cursor is pointing on the current location)
	 * @param driver
	 * @param element
	 */
	public void doubleClickAction(WebDriver driver) 
	{
		Actions actions = new Actions(driver);
		actions.doubleClick().perform();
	}
	
	/**
	 * This method will perform double click on a particular web element
	 * @param driver
	 * @param element
	 */
	public void doubleClickAction(WebDriver driver, WebElement element)
	{
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}
	
	/**
	 * This method will perform drag and drop operation 
	 * @param driver
	 * @param source
	 * @param destination
	 */
	public void dragAndDropAction(WebDriver driver, WebElement src, WebElement target)
	{
		Actions actions = new Actions(driver);
		actions.dragAndDrop(src, target).perform();
	}
	
	/**
	 * This method will move the cursor by offset and click
	 * @param driver
	 * @param x
	 * @param y
	 */
	// if we want to avoid any adds we can move our cursor anywhere in the web page irrespective of particular web element
	public void moveTheCursorAndClick(WebDriver driver, int x, int y)
	{
		Actions actions = new Actions(driver);
		actions.moveByOffset(x, y).click().perform();
	}
	
	/**
	 * This method will scroll down for 500 units
	 * @param driver
	 */
	public void scrollAction(WebDriver driver)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0, 500);", "");
	}
	
	/**
	 * THis method will scroll down until a particular web element (is reached)
	 * @param driver
	 * @param element
	 */
	
	// When we have multiple scroll bars then we can give scroll bar number which we want to handle.. if we give [0] then by default it will handle 1st scroll bar
	public void scrollAction(WebDriver driver, WebElement element)
	{
		JavascriptExecutor jsex = (JavascriptExecutor) driver;
		jsex.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	/**
	 * This method will accept the alert pop up
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver)
	{
	   driver.switchTo().alert().accept();	
	}
	
	/**
	 * This method will cancel the alert pop up
	 * @param driver
	 */
	public void dismissAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method will get the alert text and return it to caller
	 * @param driver
	 * @return
	 */
	public String getAlertText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
		
	}
	
	/**
	 * This method will handle frame using index
	 * @param driver
	 * @param index
	 */
	public void handleFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method will handle frame using frame element
	 * @param driver
	 * @param element
	 */
	public void handleFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method will handle frame using name or Id
	 * @param driver
	 * @param nameOrId
	 */
	public void handleFrame(WebDriver driver, String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	
	/**
	 * This method will switch to immediate parent frame
	 * @param driver
	 */
	public void switchToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	
	/**
	 * This method will switch to default page(i.e. main frame)
	 * @param driver
	 */
	public void switchToDefaultContent(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	//only when 2 windows(parent,child) are there
//	public void switchToWindow(WebDriver driver)
//	{
//		String mainWindowId = driver.getWindowHandle();
//		Set<String> allWindowIds = driver.getWindowHandles();
//		for(String eachId:allWindowIds)
//		{
//			if(!eachId.equals(mainWindowId))
//			{
//				driver.switchTo().window(eachId);
//			}
//		}
//		
//	}
	
	public void switchToWindow(WebDriver driver, String partialWindowTitle)
	{
		//Step1 : Capture all window IDs
		Set<String> allWindowIDs= driver.getWindowHandles();
		
		//Step2 : Navigate to each window 
		for(String eachWindowId:allWindowIDs)
		{
			//Step3 : switch to each window and capture the window title
			String actualWindowTitle = driver.switchTo().window(eachWindowId).getTitle();
			
			//Step4 : compare the actual title with required 
			if(actualWindowTitle.contains(partialWindowTitle)) // if it true that means we are switched to required window so no need to switch to any window so break the loop
			{
				break;
			}
		}		
	}
	
	/**
	 * This method will return screen shot and store it in required folder
	 * @param driver
	 * @param screenShotNmae
	 * @return path
	 * @throws IOException
	 */
	public String captureScreenShot(WebDriver driver, String screenShotNmae) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File(".\\ScreenShots\\"+screenShotNmae+".png");  // if we do not use \\ or / then entire thing will be considered as file name only so i will not getting stored in Screenshot folder it will go and sit inside anywhere in the project..
		Files.copy(src, dst);
		return dst.getAbsolutePath(); // This Absolute path used for extent reporting
		
	//--------------------------------------------------	
		/**
		 * commons IO tool help us to handle all file related actions like copy file, delete file, paste file, edit file..
		 * To handle excel sheet we make use of Apachi POI and Apachi OOXML libraries
		 * getAbsolutePath() method will give complete path.. we want to attach the screen shot to the extent report so directly we cant attatch directly because extent reports are 3rd party tool..and JVM will understood dot(.) but extent reports will not understand dot it want complete path so we need to provide complete path using getAbsolutrPath() so that the extent reports will directly go to that location and get the screen shot and attach to the extent reports..
		 * 
		 *///--------------------------------------------------------------
	}
}
