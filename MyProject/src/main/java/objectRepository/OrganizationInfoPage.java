
package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage 
{
	@FindBy(className = "dvHeaderText")
	private WebElement orgHeaderTetx;
	
	public OrganizationInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrgHeader() {
		return orgHeaderTetx;
	}
	
	//Provide Business Library
	/**
	 * This method will get organization header and return it to caller
	 * @param driver
	 */
	public String getOrganizationHeader()
	{
		return orgHeaderTetx.getText();
	}

}
