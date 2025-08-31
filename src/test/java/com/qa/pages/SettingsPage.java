package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SettingsPage extends BaseTest {

	
	
	
	public SettingsPage(AppiumDriver driver) {
	    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy (accessibility  ="Logout Menu Item") private   WebElement logout;
	
	@AndroidFindBy (xpath="//android.widget.Button[@resource-id=\"android:id/button1\"]") private   WebElement logoutYes;
	
	public LoginPage  pressLogoutBtn() {
		click(logout);
		return new LoginPage(getDriver());
	}	
	public LoginPage  pressLogoutYesBtn() {
		click(logoutYes);
		return new LoginPage(getDriver());
	}
	
}
