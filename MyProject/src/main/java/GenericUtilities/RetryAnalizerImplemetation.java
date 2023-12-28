package GenericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This method will provide implementation for IRetryAnalyzar Interface of TestNG
 * @author Saisudha
 */
public class RetryAnalizerImplemetation implements IRetryAnalyzer{

	
	int count = 0;
	int retryCOunt = 3; // Give retry count after manual analysis
	@Override
	public boolean retry(ITestResult result) {
		while(count<retryCOunt)
		{
			count++;    // increase each run
			return true; // continue execution
		}
		return false;  // stop the execution or stop retrying
	}
	
/** Listeners are applicable at class level because listeners has to applicable for each and every Test Script
 * Where as we apply RetryAnalyzar only to the failed test scripts 
 * we have to give the retryCount after analyzing manually suppose out of 1000 test scripts some 4 to 5 test scripts are getting failed we manually analyzed the reason and after giving couple of runs(after 3 runs) those test scripts are passing in such case we have to give the retryCOunt as 3 
 * retryCount is 3 and normal 1 run total Test runs are 4(3+1)
 * we have to apply retryCount only to the failed test scripts hence RetryAnalyzer is applicable at method level but listeners are applicable at class level because each and every test class and each class each test method has to be monitored  
 * RetryAnalyzer(I) is having only one method i.e, retry() this method will execute only when test script got failed
 * the return type of retry() is boolean and by default it returns false which means stop the execution or stop retrying
 * return true means continue the execution..
 * ITestResult result argument capture the test script status
 */
}
