package com.comcast.crm.ContactTest;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcase.crm.objectreppsoitoryutility.ContactInformationpage;
import com.comcase.crm.objectreppsoitoryutility.ContactPage;
import com.comcase.crm.objectreppsoitoryutility.CreatingNewContactPage;
import com.comcase.crm.objectreppsoitoryutility.CreatingNewOrganizationPage;
import com.comcase.crm.objectreppsoitoryutility.Home;
import com.comcase.crm.objectreppsoitoryutility.Login;
import com.comcase.crm.objectreppsoitoryutility.OrganizationInformationPage;
import com.comcase.crm.objectreppsoitoryutility.OrginazationPage;
import com.comcast.crm.basetest.Baseclass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

/**
 * @author venkat
 */

public class CreateContactTest extends Baseclass {
	/**
	 * 
	 * Scenario login()===>navigateContact==>create contact()===>verify
	 */

	@Test(groups = "smoketest")
	public void createContactTest() throws Throwable, IOException {
		/*step 1:- get data from excel*/
		//UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		String lastname = elib.getDataFromExcel("Sheet3", 1, 2) + jlib.getRandonNum();
		
		
		/*step 2:-Navigate to contact module */
		//UtilityClassObject.getTest().log(Status.INFO, "navegating to contact module");
		Home hm = new Home(driver);
		hm.getContactlink().click();
		
		/*step :-Navigate to create new contact */
		//UtilityClassObject.getTest().log(Status.INFO,"clicking on create contact button");
		ContactPage cp = new ContactPage(driver);
		cp.getCreatecontactEdt().click();
		
		/*step 4:-Enter data into the text feild and click on save button */
		//UtilityClassObject.getTest().log(Status.INFO, "pass data");
		CreatingNewContactPage cop = new CreatingNewContactPage(driver);
		cop.getLastnamEdt().sendKeys(lastname);
		cop.getSaveEdt().click();
         
		/*step 2:-validate actual last name with last name */
		//UtilityClassObject.getTest().log(Status.INFO, "valaditing");
		String actlastname = driver.findElement(By.id("mouseArea_Last Name")).getText();
		Assert.assertEquals(true, actlastname.contains(lastname));
	}

	@Test(groups = "regressiontest")
	public void createContacWithOrgTest() throws Throwable, IOException {

		String orgname = elib.getDataFromExcel("Sheet3", 7, 2) + jlib.getRandonNum();
		String lastname = elib.getDataFromExcel("Sheet3", 1, 2) + jlib.getRandonNum();

		Home hm = new Home(driver);
		Thread.sleep(3000);
		hm.getOrglink().click();

		OrginazationPage op = new OrginazationPage(driver);
		op.getCreateNewOrgBtn().click();

		CreatingNewOrganizationPage cp = new CreatingNewOrganizationPage(driver);
		cp.createorg(orgname);

		OrganizationInformationPage ofp = new OrganizationInformationPage(driver);
		String actorgname = ofp.getOrgNameVerify().toString();
		//Assert.assertEquals(true, actorgname.contains(orgname));
		
		hm.getContactlink().click();

		ContactPage cp1 = new ContactPage(driver);
		cp1.getCreatecontactEdt().click();

		cp1.getCreatecontactEdt().click();

		CreatingNewContactPage cop = new CreatingNewContactPage(driver);
		cop.getLastnamEdt().sendKeys(lastname);
		cop.getOrgNameEdt().click();

		String partailTitle = driver.getTitle();
		wlib.switchToTabOnTitle(driver, partailTitle);

		cop.getOrgnameSearchEdt().sendKeys(orgname);
		cop.getOrgnamesearchBtn().click();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		String particalurl = "Contacts&action";
		wlib.switchToTabOnUrl(driver, particalurl);
		cop.getSaveEdt().click();

		String resorgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		Assert.assertEquals(true, resorgname.contains(orgname));

	}

	@Test
	public void createContactWithSupportDateTest() throws Throwable, IOException {

		String lastname = elib.getDataFromExcel("Sheet3", 1, 2).toString() + jlib.getRandonNum();
		Home hm = new Home(driver);
		hm.getContactlink().click();

		ContactPage cp = new ContactPage(driver);
		cp.getCreatecontactEdt().click();

		CreatingNewContactPage cop = new CreatingNewContactPage(driver);
		cop.getLastnamEdt().sendKeys(lastname);
	
		String startdate = jlib.getSystemDateYYYYDDMM();
		//String requireddate = jlib.getRequiredDateYYYYDDMM(30);
		
		cop.getSupportDateEdt().clear();
		cop.getSupportDateEdt().sendKeys(startdate);
		cop.getSupportEnddatEdt().clear();
		cop.getSupportEnddatEdt().sendKeys(startdate);
		cop.getSaveEdt().click();

		ContactInformationpage cip = new ContactInformationpage(driver);
		String actsupportstartdate = cip.getSupportDateEdt().getText();
		Assert.assertEquals(true, actsupportstartdate.contains(startdate));	
	}
}
