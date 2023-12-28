package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage 
{
	//Declaration
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createContactLookUpImg;
	
	//Initialization
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization - Provide getters
	public WebElement getCreateContactLookUpImg() {
		return createContactLookUpImg;
	}
	
	//Provide Business Library
	/**
	 * This method will click on create contact lookUp image
	 */
	public void clickOnCreateContactlookUpImg()
	{
		createContactLookUpImg.click();
	}
	
	

}
