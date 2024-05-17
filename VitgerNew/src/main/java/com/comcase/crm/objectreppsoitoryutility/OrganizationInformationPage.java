package com.comcase.crm.objectreppsoitoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	WebDriver driver;

	public OrganizationInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "mouseArea_Organization Name")
	private WebElement orgNameVerify;

	@FindBy(id = "mouseArea_Phone")
	private WebElement phonenumVerify;

	@FindBy(id = "mouseArea_Industry")
	private WebElement industryVerify;

	@FindBy(id = "mouseArea_Type")
	private WebElement typeverify;

	public WebElement getPhonenumVerify() {
		return phonenumVerify;
	}

	public WebElement getIndustryVerify() {
		return industryVerify;
	}

	public WebElement getTypeverify() {
		return typeverify;
	}

	public WebElement getOrgNameVerify() {
		return orgNameVerify;
	}

	

}
