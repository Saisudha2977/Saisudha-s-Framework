package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage  //Step1 : Create a separate Class for each web page and class name web page name should be same
{
	//Rule2 : Identify all the wb elements using @FindBy, @FindAll, @FindBys and declare them as private
	//Element declaration
	@FindBy(name ="user_name")
	private WebElement userNameEdt;   //m storing the  locator in side the WebDriver
	
	@FindBy(name = "user_password")
	private WebElement passwordEdt;
	
	@FindBy(id = "submitButton")
	private WebElement loginBtn;
	
	//Rule3 : Create Constructor to initialize those Identified web elements
	//Initialization of web elements
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule4 : Provide getters to initialize these web elements outside of the class *****************(( return type of getters() is WebElement))
	//Utilization
	
	//getters are mandatory for accessing web elements

	public WebElement getUserNameEdt() {
		return userNameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	
	//Step5 : Provide Business Library(OPtional)
	          // Business Library a generic method created using web elements present ONLY in current page
	         // here to perform login operation we need username,password,login button so we are havin all these in the same page so we can write business library for this Login page..
	/**
	 * This method will Login to Application
	 * @param USERNAME
	 * @param PASSWORD
	 */
	public void loginToApp(String USERNAME, String PASSWORD)
	{
		userNameEdt.sendKeys(USERNAME);
		passwordEdt.sendKeys(PASSWORD);
		loginBtn.click();
	}


}
