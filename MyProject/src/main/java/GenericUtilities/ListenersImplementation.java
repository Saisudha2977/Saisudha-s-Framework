package GenericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class will provide implementation to ITestListener interface of TestNG
 * @author saisudha
 */
public class ListenersImplementation implements ITestListener
{


	ExtentReports reports;
	ExtentTest test;
	@Override
	public void onTestStart(ITestResult result)
	{
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+" ========= Test execution started =========");
		
	    test = reports.createTest(methodName);
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		String methodName = result.getMethod().getMethodName();
//		System.out.println(methodName+" ========= Test Pass =========");
		
		test.log(Status.PASS, methodName+" =========  Test Pass ==========");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		//System.out.println(result.getThrowable());      //capture the exception+take screenShot
		String methodName = result.getMethod().getMethodName();
		//System.out.println(methodName+" ========= Test Fail =========");
		
		test.log(Status.FAIL, methodName+" =========  Test Fail ==========");
		test.log(Status.INFO, result.getThrowable());

		
		WebDriverUtility wUtil = new WebDriverUtility();
		JavaUtility jUtil = new JavaUtility();
		
		String screenshotName = methodName+"  "+jUtil.getSystemDateInFormat();
		try // since it is ITestListener is an interface so we can not change method signature so we cant throw the exception to caller so the user must handle exception..by using try catch block..
		{
			String path = wUtil.captureScreenShot(BaseClass.sdriver, screenshotName);
			test.addScreenCaptureFromPath(path);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result)
	{
		System.out.println(result.getThrowable());      //capture the exception
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+" ========= Test Skip=========");
		
		test.log(Status.PASS, methodName+" =========  Test Skip ==========");
		test.log(Status.INFO, result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) 
	{
		
	}

	@Override
	public void onStart(ITestContext context)
	{
		System.out.println(" ========= Suite execution started =========");
		
		/**********  Extent reports configuration********************/
		
		/*Call ExtentSparkReporter class and pass the path of screenshot folder and append the screenshot with current system date
		set basic configuration of extent reports so for this call setters like setDocumentTitle(),setTheme(),setReportName() etc
		Call ExtentReports class and this is the main class to generate the report
		call attatchReporter()for adding all the basic configuration to the system
		If u want to show case/set any system information to the report we can set.. by calling setters methods.. use setSystemInfo()
		once the execution is completed then generate the report here we have to use flush()..and it is an intimation to the extent report that the all test script execution is completed and now it has to create the report
		And extent reports do not that the test execution is started, so we have to tell by using createTest()method and it will accept String parameter
		And based on test script status we have to log the status in onTestSuccess(), onTestFailure(), and onTestSkipped() method.. different log levels we have INFO,PASS,FAIL,SKIP,ERROR etc
		Finally we have to attach the Screen shot to extent reports because extent reports and screenshot both are in different folders..
		*/
		
		ExtentSparkReporter htmlReport = new ExtentSparkReporter(".\\ExtentReports\\Report-"+new JavaUtility().getSystemDateInFormat()+".html");
		htmlReport.config().setDocumentTitle("Extent-Report");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("Automation_ExtentReports");
		
	    reports = new ExtentReports();
		reports.attachReporter(htmlReport);
		reports.setSystemInfo("Base browser", "chrome");
		reports.setSystemInfo("Base platform", "Windows 10");
		reports.setSystemInfo("Base Environment", "Testing");
		reports.setSystemInfo("Base url", "http://localhost:8888");
		reports.setSystemInfo("Reporte Name", "Saisudha Doddi");
	}

	@Override
	public void onFinish(ITestContext context)
	{
		reports.flush();
		System.out.println("========== Suite execution started =========");
	}

}
