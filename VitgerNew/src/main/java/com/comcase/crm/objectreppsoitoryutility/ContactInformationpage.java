package com.comcase.crm.objectreppsoitoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationpage {

	WebDriver driver;
	public ContactInformationpage (WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
@FindBy(xpath="//td[@id='mouseArea_Support Start Date']")	
private WebElement supportDateEdt;

public WebElement getSupportDateEdt() {
	return supportDateEdt;
}



}
