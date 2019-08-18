package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListeners implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println("********* Test " + result.getName() + "  started at " + result.getTestContext().getStartDate());
		
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("******** Test " + result.getName() + " Succeded");
		
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("******** Test " + result.getName() + " Failed");
		
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("******** Test " + result.getName() + " Skipped");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		System.out.println("********* Test " + context.getName() + "  finished at " + context.getEndDate());
		
	}

}
