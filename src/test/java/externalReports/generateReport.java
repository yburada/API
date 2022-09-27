package externalReports;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class generateReport extends TestListenerAdapter {
	
	public ExtentSparkReporter SparkRep;
	public ExtentReports external;
	public ExtentTest Test;
	
	@Override
	public void onStart(ITestContext textContext) {
		String xlPath = System.getProperty("user.dir")+"/test-output/myreport.html";
		SparkRep = new ExtentSparkReporter(xlPath);
		SparkRep.config().setDocumentTitle("Automation Report");
		SparkRep.config().setTheme(Theme.DARK);
		
		external = new ExtentReports();
		external.attachReporter(SparkRep);
		external.setSystemInfo("Hostname", "Localhost");
		external.setSystemInfo("Environment","QA");
		external.setSystemInfo("User", "yogesh");
	}
	
	@Override
	public void onTestSuccess(ITestResult status) {
		Test = external.createTest(status.getName());
		Test.log(Status.PASS, "Test Case passed "+status.getName() );
		
	}
	
	@Override
	public void onTestFailure(ITestResult status) {
		Test = external.createTest(status.getName());
		Test.log(Status.FAIL, "Test Case Failed "+status.getName() );
		
	}
	
	@Override
	public void onTestSkipped(ITestResult status) {
		Test = external.createTest(status.getName());
		Test.log(Status.SKIP, "Test Case Skipped "+status.getName() );
		
	}
	
	@Override
	public void onFinish(ITestContext textContext) {
		external.flush();
		
	}

}
