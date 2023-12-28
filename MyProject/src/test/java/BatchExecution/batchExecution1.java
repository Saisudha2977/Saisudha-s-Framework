package BatchExecution;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;
import GenericUtilities.BaseClass;
import objectRepository.ContactInformationPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;

public class batchExecution1 extends BaseClass
{
	@Test
	public void createContact() throws EncryptedDocumentException, IOException, InterruptedException
	{
				// Step3 :Navigate to Contacts Link
				HomePage hp = new HomePage(driver);
				hp.clickOnContactsLink();

				// Step4 :Click on Create Contact Look Up Image
				ContactsPage cp = new ContactsPage(driver);
				Thread.sleep(3000);
				cp.clickOnCreateContactlookUpImg();

				// Step5 :Create Contact with mandatory fields
				CreateNewContactPage cncp = new CreateNewContactPage(driver);
				String LASTNAME = eUtil.readSingleDataFromExcelFile("Contacts", 1, 2);
				cncp.createNewContact(LASTNAME);
 
				// Step7 :Validate
				ContactInformationPage cip = new ContactInformationPage(driver);
				String contactHeader = cip.getContactHeader();
				Assert.assertTrue(contactHeader.contains(LASTNAME));
				System.out.println("COntct Header is :"+contactHeader);
				System.out.println("COntact name is :"+LASTNAME);		
	}

}
