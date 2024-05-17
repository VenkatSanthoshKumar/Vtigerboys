package com.comcast.crm.basetest;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcase.crm.objectreppsoitoryutility.Home;
import com.comcase.crm.objectreppsoitoryutility.Login;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class Baseclass {
	private static final String always = null;
	// creation object
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();
	public DataBaseUtility dblib = new DataBaseUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
	public ExtentReports report;
	public ExtentSparkReporter spark;

	
	@BeforeSuite(groups = {"smoketest","regressiontest"})
	public void configBs() {
		System.out.println("Bs");
		// dblib.getDBConnection();
		spark = new ExtentSparkReporter("./AdanceReport/report.html");
		spark.config().setDocumentTitle("CRm Test Suite Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add enviorment information and create test

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("os", "windows-11");
		report.setSystemInfo("browser", "chrome_121.45");
	}

	// @Parameters("BROWSER")
	@BeforeClass(groups = {"smoketest","regressiontest"})
	public void configBc() throws Throwable {
		// String BROWSER=browser;
		String BROWSER = System.getProperty("browser",flib.getdatafrompropertyfile("browser"));
		if (BROWSER.equals("1")) {
			driver = new FirefoxDriver();
		} else
			driver = new ChromeDriver();
		sdriver = driver;
		//UtilityClassObject.setdriver(driver);
	}

	@BeforeMethod(groups = {"smoketest","regressiontest"})
	public void configBm() throws Throwable {
		Login lp = new Login(driver);
		String URL = System.getProperty("url",flib.getdatafrompropertyfile("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);
		String USERNAME = System.getProperty("un", flib.getdatafrompropertyfile("username"));
		String PASSWORD = System.getProperty("pwd",flib.getdatafrompropertyfile("password"));
	    lp.loginToApp(URL, USERNAME, PASSWORD);
		System.out.println("bm");
	}

	@AfterMethod(groups = {"smoketest","regressiontest"})
	public void configAm() {
		Home hp = new Home(driver);
		hp.logout();
	}

	@AfterClass(groups = {"smoketest","regressiontest"})
	public void configAc() {
		driver.quit();
		System.out.println("AC");
	}

	@AfterSuite(groups = {"smoketest","regressiontest"})
	public void configAs() throws Throwable {
		// dblib.closeConnection();
		System.out.println("AS");
		report.flush();
	}
}
