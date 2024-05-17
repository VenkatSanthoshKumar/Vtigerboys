package com.comcast.crm.listenerUility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.Baseclass;


public class ListenerImp implements ITestListener, ISuiteListener {

	public ExtentTest test;
	public static ExtentReports report;
	public ExtentSparkReporter spark;
	
	public void onStart(ISuite suite) {
		System.out.println("report confifguration");
		String time= new Date().toString().replace(" ", "_").replace(":","_");
		spark=new ExtentSparkReporter("./AdanceReport/report"+time+".html");
		  spark.config().setDocumentTitle("CRm Test Suite Result");
		  spark.config().setReportName("CRM Report");
		  spark.config().setTheme(Theme.DARK);
		  
		  //add enviorment information and create test
		  
		  report=new ExtentReports();
		  report.attachReporter(spark);
		  report.setSystemInfo("os", "windows-11");
		  report.setSystemInfo("browser", "chrome_121.45");
	}

	public void onFinish(ISuite suite) {
		System.out.println("report backup");
		 report.flush();

	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("=====" + result.getMethod().getMethodName() + "=======");
		  ExtentTest test=report.createTest(result.getMethod().getMethodName()+"===>started<====");

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("=====" + result.getMethod().getMethodName() + "===End====");
	}

	public void onTestFailure(ITestResult result) {
		
		String testName = result.getMethod().getMethodName();
		TakesScreenshot edriver=(TakesScreenshot)Baseclass.sdriver;
		String filepath=edriver.getScreenshotAs(OutputType.BASE64);
		
		String time= new Date().toString().replace(" ", "_").replace(":","_");
		

		  test.addScreenCaptureFromBase64String(filepath, testName+""+time);  
	  
		
		//EventFiringWebDriver edriver = new EventFiringWebDriver(Baseclass.sdriver);
		//File src = edriver.getScreenshotAs(OutputType.FILE);
		//try {
		//	FileUtils.copyFile(src, new File("./Screenshot/" + testName+"+"+time+ " .png"));
		//} catch (IOException e) {
			//e.printStackTrace();
		}
	

	public void onTestSkipped(ITestResult result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
	}

}
