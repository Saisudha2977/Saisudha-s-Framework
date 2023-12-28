package vtiger.contactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import GenericUtilities.BaseClass;
import objectRepository.ContactInformationPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrganizationsPage;

public class CreateContactWithOrganizationTestUsingBaseClassTest extends BaseClass
{
	@Test(dataProvider = "getContactData")
	public void createContactWithOrganizationTest(String LASTNAME, String ORG) throws InterruptedException
	{
		String ORGNAME = ORG+jUtil.getRandomNumber();

		//Step1 : Click on Organizations Link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLink();
		
		//Step 2 : Click on create organizations look up image
		Thread.sleep(3000);
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnOrganizationsLookUpImg();
		
		//Step 3 : Create Organization with mandatory fields
		CreateNewOrganizationPage cno = new CreateNewOrganizationPage(driver);
		cno.createNewOrganization(ORGNAME);
		
		//Step 4 : Validate
		OrganizationInfoPage orgInfo = new OrganizationInfoPage(driver);
		String orgaHeader= orgInfo.getOrganizationHeader();
		if(orgaHeader.contains(ORGNAME)) {
			System.out.println(ORGNAME);
		}
		else
		{
			System.out.println("FAIL");
			System.out.println("Organization has not been created..");  // If you give any other browser other than this chrome,firefox,edge browser then we bound to get "NULL POINTER EXCEPTION" because the driver value is null..
		}
		
		//Step 5 : Click on Contacts link
		Thread.sleep(3000);
		hp.clickOnContactsLink();
		
		//Step 6 : Click on create contact look up image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactlookUpImg();
		
		//Step 7 : Create contact with mandatory fields using organization
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		Thread.sleep(3000);
		cncp.createNewContact(LASTNAME, ORGNAME, driver);;
		
		//Step 8 : Validate for contacts
		ContactInformationPage cip = new ContactInformationPage(driver);
		String contactHeader = cip.getContactHeader();
		System.out.println("contactHeader is : "+contactHeader);
		if(contactHeader.contains(LASTNAME)) {
			System.out.println("Contact has been created successfully along with Organization name");
			System.out.println("Pass");
		}
		else {
			System.out.println("Contact has not been created ");
		}
	}
	
	@DataProvider
	public Object[][] getContactData() throws EncryptedDocumentException, IOException
	{
		Object[][] data = eUtil.readMultipleDataFromRowAndCell("Sheet3");
		return data;
	}

}
