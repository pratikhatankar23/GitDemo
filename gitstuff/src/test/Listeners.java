package test;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
//ITestListeners interface which implements TestNG listeners

public class Listeners implements ITestListener {

	@Override
	public void onStart(ITestContext arg0) {
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		System.out.println("test is pass");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		//screenshot code
		// rest APi failure

		System.out.println("failed one" + result.getName());
	}

	@Override

	public void onTestSkipped(ITestResult arg0) {

	}

	@Override
	public void onFinish(ITestContext arg0) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

}
