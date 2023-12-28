package AssertionsPractice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(GenericUtilities.ListenersImplementation.class)
public class SampleAssertionEx 
{
//	@Test
//	public void sampleAssertion()
//	{
//		System.out.println("Execution is started..");
//		Assert.assertEquals("a", "a", "a and b are not same");
//		System.out.println("Hiii.....");
//	}
//	
	//@Test
	public void softAssert()
	{
	/*SoftAssert sa = new SoftAssert();
	System.out.println("step1");
	System.out.println("step2");
	sa.assertEquals("sai", "sudha", "data not matched");
	System.out.println("step3");
	
	System.out.println("step4");
	sa.assertAll();
	Assert.assertEquals("a", "b");
	System.out.println("step5");
	*/
	
	}
	@Test(groups = "SmokeSuite")
	public void sampleSmoke()
	{
		System.out.println("SmokeSuite2");
	}
	
	@Test(groups = {"RegressionSuite","SmokeSuite"})
	public void sampleRegression()
	{
		System.out.println("RegressionSuite2");
	}
	
	@Test(groups = "SmokeSuite")
	public void sampleSmoke1()
	{
		System.out.println("SmokeSuite3");
	}
}
