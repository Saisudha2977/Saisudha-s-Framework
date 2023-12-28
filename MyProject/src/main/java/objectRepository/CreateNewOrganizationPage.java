package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility
{
	//Declaration
	@FindBy(name ="accountname")
	private WebElement organizationNameTxt;
	
	@FindBy(name ="industry")
	private WebElement IndustryDpw;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//Initialization
	public CreateNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getOrganizationNameTxt() {
		return organizationNameTxt;
	}

	public WebElement getIndustryDpw() {
		return IndustryDpw;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	//Provide Business Library
	
	// createNewOrganization() method is overloading method
	
	/**
	 * This method will create organization using mandatory fields
	 * @param ORGNAME
	 */
	public void createNewOrganization(String ORGNAME)
	{
		organizationNameTxt.sendKeys(ORGNAME);
		saveBtn.click();
	}
	
	/**
	 * This method will create new organization with Industry
	 * @param driver
	 * @param ORGNAME
	 * @throws InterruptedException 
	 */
	public void createNewOrganization(String ORGNAME, String INDUSTRYTYPE ) throws InterruptedException
	{
		organizationNameTxt.sendKeys(ORGNAME);
		Thread.sleep(3000);
		handleDropDown(IndustryDpw, INDUSTRYTYPE);
		saveBtn.click();
	}
}
