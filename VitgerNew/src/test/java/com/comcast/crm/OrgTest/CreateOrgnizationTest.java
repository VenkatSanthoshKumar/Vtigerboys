package com.comcast.crm.OrgTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcase.crm.objectreppsoitoryutility.CreatingNewOrganizationPage;
import com.comcase.crm.objectreppsoitoryutility.Home;
import com.comcase.crm.objectreppsoitoryutility.Login;
import com.comcase.crm.objectreppsoitoryutility.OrganizationInformationPage;
import com.comcase.crm.objectreppsoitoryutility.OrginazationPage;
import com.comcast.crm.basetest.Baseclass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrgnizationTest extends Baseclass {

	@Test(groups = "smoketest")
	public void createOrgTest() throws EncryptedDocumentException, IOException, Throwable {

		String orgname = elib.getDataFromExcel("Sheet3", 7, 2) + jlib.getRandonNum();

		Home hm = new Home(driver);
		hm.getOrglink().click();

		OrginazationPage op = new OrginazationPage(driver);
		op.getCreateNewOrgBtn().click();

		CreatingNewOrganizationPage cp = new CreatingNewOrganizationPage(driver);
		cp.getOrgname().sendKeys(orgname);
		cp.getSaveBtn().click();

		OrganizationInformationPage ofp = new OrganizationInformationPage(driver);
		String actorgname = ofp.getOrgNameVerify().getText();
		Assert.assertEquals(true, actorgname.contains(orgname));
	}

	@Test(groups = "regressiontest")
	public void createOrgTestWithMobileNo() throws Throwable, IOException {

		String orgname = elib.getDataFromExcel("Sheet2", 7, 2) + jlib.getRandonNum();
		String phonenum=elib.getDataFromExcel("Sheet2", 7, 3);
		Home hm = new Home(driver);
		hm.getOrglink().click();

		OrginazationPage op = new OrginazationPage(driver);
		op.getCreateNewOrgBtn().click();

		CreatingNewOrganizationPage cp = new CreatingNewOrganizationPage(driver);
		cp.getOrgname().sendKeys(orgname);
		cp.getPhoneEdt().sendKeys(phonenum);
		cp.getSaveBtn().click();

		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actphoneno = oip.getPhonenumVerify().toString();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(phonenum, actphoneno);
	}

	@Test
	public void createOrgtestWithIndustires() throws Throwable, IOException {
		String orgname = elib.getDataFromExcel("Sheet2", 4, 2) + jlib.getRandonNum();
		String industries = elib.getDataFromExcel("Sheet2", 4, 3);
		Home hm = new Home(driver);
		hm.getOrglink().click();

		OrginazationPage op = new OrginazationPage(driver);
		op.getCreateNewOrgBtn().click();

		CreatingNewOrganizationPage cp = new CreatingNewOrganizationPage(driver);
		cp.getOrgname().sendKeys(orgname);
		WebElement ele1 = cp.getIndustryDrp();
		wlib.select(ele1, industries);
		//WebElement ele2 = cp.getAccountTypeEdt();
	    //wlib.select(ele2, 1);

		cp.getSaveBtn().click();

		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actindustires = oip.getIndustryVerify().getText();
		Assert.assertEquals(industries, actindustires);
	}
}