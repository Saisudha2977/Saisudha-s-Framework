package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility
// for my scenarios i need the following elements in the Home page
{
	// Identify Web Elements
	@FindBy(xpath = "//a[@href='index.php?module=Accounts&action=index']")
	private WebElement organizationLnk;
	
	@FindBy(xpath = "//a[@href='index.php?module=Contacts&action=index']")
	private WebElement contactsLnk;
	
	@FindBy(xpath = "//a[@href='index.php?module=Products&action=index']")
	private WebElement productsLnk;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminisrtatorImg;
	
	@FindBy(xpath = "//a[@href='index.php?module=Users&action=Logout']")
	private WebElement signOutLnk;
	
	//Initialize WebElements
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilize Web Elements by providing getters()
	
	public WebElement getOrganizationLnk() {
		return organizationLnk;
	}

	public WebElement getContactsLnk() {
		return contactsLnk;
	}

	public WebElement getProductsLnk() {
		return productsLnk;
	}

	public WebElement getAdminisrtatorImg() {
		return adminisrtatorImg;
	}

	public WebElement getSignOutLnk() {
		return signOutLnk;
	}
	
	//Provide Business Library
	/**
	 * This method will Logout from Application
	 * @param driver
	 * @throws InterruptedException 
	 */
	public void logOutOfApp(WebDriver driver) throws InterruptedException // i need driver reference so provide the driver reference..
	{
		mouseHoverAction(driver, adminisrtatorImg);
		Thread.sleep(3000);
		signOutLnk.click();
	}
	
	// it may be single line or multiple lines we can cretae Business Library so that we can hide the actions that we are performing on Web elements..there is no rule that Business Library should have 1 line, multiple lines.. it wont worry about no of lines
	/**
	 * This method will click on Organization Link
	 */
	public void clickOnOrganizationLink()
	{
		organizationLnk.click();
	}
	
	/**
	 * This method will click on Contacts Link
	 */
	public void clickOnContactsLink()
	{
		contactsLnk.click();
	}	
}
