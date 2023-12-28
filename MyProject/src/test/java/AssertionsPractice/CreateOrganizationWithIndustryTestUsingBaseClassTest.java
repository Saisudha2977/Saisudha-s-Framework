package AssertionsPractice;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import GenericUtilities.BaseClass;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrganizationsPage;

public class CreateOrganizationWithIndustryTestUsingBaseClassTest extends BaseClass
{
	
	@Test(dataProvider = "getOrgData")
	public  void  createOrganizationWithIndustryTest(String ORG, String INDUSTRYTYPE) throws IOException, InterruptedException
	{	
		//Step1 : Click on Organizations link
		Thread.sleep(3000);
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLink();

		//Step2 : Click on Create Organization look Up Image
		OrganizationsPage orgPage = new OrganizationsPage(driver);
		orgPage.clickOnOrganizationsLookUpImg();

		//Step3 : Create Organization with Mandatory fields
		String ORGNAME = ORG+jUtil.getRandomNumber();
		Thread.sleep(4000);
		CreateNewOrganizationPage newOrgName = new CreateNewOrganizationPage(driver);
		newOrgName.createNewOrganization(ORGNAME, INDUSTRYTYPE);
					
		//Step4 : Verify
		OrganizationInfoPage orgInfo = new OrganizationInfoPage(driver);
		String orgaHeader= orgInfo.getOrganizationHeader();
		Assert.assertTrue(orgaHeader.contains(ORGNAME));
		System.out.println("Org Header is :"+orgaHeader);
		
	}

	@DataProvider
	public Object[][] getOrgData() throws EncryptedDocumentException, IOException
	{
		Object[][] data = eUtil.readMultipleDataFromRowAndCell("Sheet4");
		return data;
	}
	
	@Test(groups = "SmokeSuite")
	public void sampleSmoke()
	{
		System.out.println("SmokeSuite5");
	}
	
	@Test(groups = "RegressionSuite")
	public void sampleRegression()
	{
		System.out.println("RegressionSuite5");
	}
}
