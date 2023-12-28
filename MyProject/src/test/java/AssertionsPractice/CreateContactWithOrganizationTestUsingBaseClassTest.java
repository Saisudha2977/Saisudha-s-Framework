package AssertionsPractice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
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
		
		//Step 4 : Validate for Organization
		OrganizationInfoPage orgInfo = new OrganizationInfoPage(driver);
		String orgaHeader= orgInfo.getOrganizationHeader();
		Assert.assertTrue(orgaHeader.contains(ORGNAME));
		System.out.println("Organization Header is "+orgaHeader);
		
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
		Assert.assertTrue(contactHeader.contains(LASTNAME));
		System.out.println("Contacts Header is :"+contactHeader);
		
	}
	
	@DataProvider
	public Object[][] getContactData() throws EncryptedDocumentException, IOException
	{
		Object[][] data = eUtil.readMultipleDataFromRowAndCell("Sheet5");
		return data;
	}
	
	@Test(groups = {"SmokeSuite", "RegressionSuite"})
	public void sampleSmoke()
	{
		System.out.println("SmokeSuite1");
	}
	
	@Test(groups = "RegressionSuite")
	public void sampleRegression()
	{
		System.out.println("RegressionSuite2");
	}
	

}
