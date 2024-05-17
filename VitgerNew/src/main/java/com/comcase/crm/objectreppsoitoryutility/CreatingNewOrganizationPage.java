package com.comcase.crm.objectreppsoitoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {


	WebDriver driver;
	public CreatingNewOrganizationPage (WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "industry")
	private WebElement industryEdt;
	
	@FindBy(name="accountname")
	private WebElement orgnameEdt;
	
	@FindBy(name = "accounttype")
	private WebElement accountTypeEdt;
	
	@FindBy(id = "phone")
	private WebElement phoneEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDrp;
	
	
	
	
	public WebElement getAccountTypeEdt() {
		return accountTypeEdt;
	}

	public WebElement getIndustryEdt() {
		return industryEdt;
	}

	public WebElement getPhoneEdt() {
		return phoneEdt;
	}

	public WebElement getOrgname() {
		return orgnameEdt;
	}

	public WebElement getIndustryDrp() {
		return industryDrp;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public void createorg(String orgname) {
		orgnameEdt.sendKeys(orgname);
		saveBtn.click();
	}
	public void createorg(String orgname, String industry) {
		orgnameEdt.sendKeys(orgname);
		Select sel=new Select(industryDrp);
		sel.selectByVisibleText(industry);
		saveBtn.click();
	}
	
	
	
}
