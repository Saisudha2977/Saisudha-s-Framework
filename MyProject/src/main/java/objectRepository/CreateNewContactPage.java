package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import GenericUtilities.WebDriverUtility;

public class CreateNewContactPage  extends WebDriverUtility
/** IF you get any windows or frames in the current page then we have to add those window/frame web elements also in the current class only so that we will not get any confusion..
 *  Here we are getting window when we click on + that is the window base element is present in this current page then we have to add those window elements also in the current page only*/
{
	//Declaration
	@FindBy(name = "lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@src='themes/softed/images/select.gif']")
	private WebElement orgNameTxtLookUpImg;
	
	@FindBy(name = "search_text")
	private WebElement orgSearchEdt;
	
	@FindBy(xpath = "//input[@type='button']")
	private WebElement orgSearchNowBtn;

	
	@FindBy(xpath = "//a[@href='javascript:window.close();']")
	private WebElement orgSearchOrgLnk;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//Initialization
	public CreateNewContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}

	//Utilization - Provide getters
	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}
	public WebElement getOrgNameTxtLookUpImg() {
		return orgNameTxtLookUpImg;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public WebElement getSearchEdt() {
		return orgSearchEdt;
	}

	public WebElement getSearchNowBtn() {
		return orgSearchNowBtn;
	}

	public WebElement getSearchOrgLnk() {
		return orgSearchOrgLnk;
	}
	
	//Provide Business Library
	/**
	 * This method will create contact with mandatory fields
	 * @param LASTNAME
	 * @throws InterruptedException 
	 */
	public void createNewContact(String LASTNAME) throws InterruptedException
	{
		Thread.sleep(3000);
		lastNameEdt.sendKeys(LASTNAME);
		saveBtn.click();
	}
	
	/**
	 * This method will create contact using organization 
	 * @param LASTNAME
	 * @param ORGNAME
	 * @param driver
	 * @throws InterruptedException 
	 */
	public void createNewContact(String LASTNAME, String ORGNAME, WebDriver driver) throws InterruptedException   // here i need web driver, what ever u want just parameterize it
	{
		lastNameEdt.sendKeys(LASTNAME);
		orgNameTxtLookUpImg.click();
		switchToWindow(driver, "module=Accounts&action");
		orgSearchEdt.sendKeys(ORGNAME);     // here Title and URL is same..
		Thread.sleep(3000);
		orgSearchNowBtn.click();
		driver.findElement(By.xpath("//a[.='"+ORGNAME+"']")).click();
		switchToWindow(driver, "DetailView&parenttab=Marketing");
		saveBtn.click();
	}
	
}
