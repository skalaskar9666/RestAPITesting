package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listen implements ITestListener {

    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    ExtentTest test;

    public void onStart(ITestContext context) {

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/logs/myReport1.html");
        htmlReporter.config().setReportName("One more API Report");
        htmlReporter.config().setDocumentTitle("New API Test Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Project Name","Rest API Test");
        extent.setSystemInfo("Host Name","Kalaskar");
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("user","sandeep");

    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.PASS,"TEst case passed is "+result.getName());
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.FAIL,"TEst case Failed is "+result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.SKIP,"TEst case skipped is "+result.getName());
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
