package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import com.qa.BaseTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {


public LoginPage(AppiumDriver driver) {
	//BaseTest.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}

	
	
	@AndroidFindBy (id ="com.saucelabs.mydemoapp.android:id/menuIV") private   WebElement menuBtn;
	
	@AndroidFindBy (accessibility ="Login Menu Item") private   WebElement logintextBtn;
	
	@AndroidFindBy (id ="com.saucelabs.mydemoapp.android:id/nameET") private   WebElement usernameTxt;
	  
	@AndroidFindBy (id ="com.saucelabs.mydemoapp.android:id/passwordET") private   WebElement passwordTxt;
	
	@AndroidFindBy (id ="com.saucelabs.mydemoapp.android:id/loginBtn") private   WebElement loginBtn; 
	
	@AndroidFindBy (id ="com.saucelabs.mydemoapp.android:id/passwordErrorTV") private   WebElement errorText;
	
	



public LoginPage enterUserName(String username) {
	sendkeys(usernameTxt,username);
	return this;
}


public LoginPage enterpPassword(String password) {
	sendkeys(passwordTxt,password);
	return this;
	
	
}



public String getErrorText() {
	return getText(errorText);
}

public ProductPage pressloginBtn() {
	click(loginBtn);
	return new ProductPage(driver);
}



public LoginPage pressMenuBtn() {
	click(menuBtn);
	return this;
}

public LoginPage pressLogintext() {
	click(logintextBtn);
	return this;
}

}