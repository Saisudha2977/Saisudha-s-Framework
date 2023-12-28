package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage 
{
	@FindBy(className = "dvHeaderText")
	private WebElement contactHeaderTxt;

	public ContactInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	

	public WebElement getContactHeaderTxt() {
		return contactHeaderTxt;
	}
	
	
	//Provide Business LIbrary
	/**
	 * This method will capture Contact header text and return it to caller
	 */
	public String getContactHeader()
	{
		return getContactHeaderTxt().getText();
	}
	

}
