package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage 
{
	//WebElements Identification
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement CreaetOrganizationLookUpImg;
	
	//Web elements Initialization
	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Web elements Utilization - Provide getters

	public WebElement getOrganizationLookUpImg() {
		return CreaetOrganizationLookUpImg;
	}
	
	//Provide Business Library
	/**
	 * This method will click on Organizations Look Up Image
	 */
	public void clickOnOrganizationsLookUpImg()
	{
		CreaetOrganizationLookUpImg.click();
	}
	
	

}
