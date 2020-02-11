package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;

public class listeners extends TestListenerAdapter
{
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;

    public void onStart(ITestContext testContext)
    {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/logs/myReport.html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("API testing");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name","kalaskar");
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("user","sandeep");

    }

    public void onTestSuccess(ITestResult result)
    {
        test= extent.createTest(result.getName());
        test.log(Status.PASS, "Test Case Passed is "+result.getName());

    }

    public void onTestFailure(ITestResult result)
    {
        test=extent.createTest(result.getName());
        test.log(Status.FAIL,"Test Case failed is "+result.getName());
        test.log(Status.FAIL,"Test Case failed is "+result.getThrowable());

    }

    public void onTestSkipped(ITestResult result)
    {
        test=extent.createTest(result.getName());
        test.log(Status.SKIP,"TEst Case skipped is "+result.getName());
    }

    public void onFinish(ITestContext testContext)
    {
        extent.flush();
    }
}
