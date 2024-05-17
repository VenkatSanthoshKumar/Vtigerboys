package com.comcase.crm.objectreppsoitoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	WebDriver driver;

	public CreatingNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "lastname")
	private WebElement lastnamEdt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveEdt;

	@FindBy(xpath="//input[@name='support_start_date']")
	private WebElement supportDateEdt;

	@FindBy(xpath ="//input[@name = 'support_end_date']")
	private WebElement supportEnddatEdt;

	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement orgNameEdt;

	@FindBy(id = "search_txt")
	private WebElement OrgnameSearchEdt;

	@FindBy(name = "search")
	private WebElement OrgnamesearchBtn;

	public WebElement getOrgnameSearchEdt() {
		return OrgnameSearchEdt;
	}

	public WebElement getOrgnamesearchBtn() {
		return OrgnamesearchBtn;
	}

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSupportDateEdt() {
		return supportDateEdt;
	}

	public WebElement getSupportEnddatEdt() {
		return supportEnddatEdt;
	}

	public WebElement getSaveEdt() {
		return saveEdt;
	}

	public WebElement getLastnamEdt() {
		return lastnamEdt;
	}

}
