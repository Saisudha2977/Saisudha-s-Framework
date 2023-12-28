package retryAnalyzerPractice;


import org.testng.Assert;
import org.testng.annotations.Test;


public class RretryAnalyzerPractice 
{
	@Test(retryAnalyzer = GenericUtilities.RetryAnalizerImplemetation.class)
	public void sample1()
	{
		Assert.fail();
		System.out.println("Hi..");
	}
	
	@Test(retryAnalyzer = GenericUtilities.RetryAnalizerImplemetation.class)
	public void sample2()
	{
		Assert.fail();
		System.out.println("Bye..");
	}
}
