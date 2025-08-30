package com.qa.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import com.google.common.collect.ImmutableMap;
import com.qa.BaseTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {

private AppiumDriver driver;
	

public LoginPage(AppiumDriver driver) {
	this.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}

	
	
	@AndroidFindBy (id ="com.saucelabs.mydemoapp.android:id/menuIV")
	@iOSXCUITFindBy(iOSNsPredicate  = "name == 'More-tab-item'")  private   WebElement menuBtn;
	
	
	
	
	@AndroidFindBy (xpath ="//android.widget.TextView[@content-desc=\"Login Menu Item\"]") 
	
	@iOSXCUITFindBy (id ="LogOut-menu-item") private   WebElement logintextBtn;
	
	@AndroidFindBy (xpath ="//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/nameET\"]") 
	@iOSXCUITFindBy (iOSNsPredicate=" type == \"XCUIElementTypeTextField\"") private   WebElement usernameTxt;
	  
	@AndroidFindBy (xpath ="//android.widget.EditText[@resource-id=\"com.saucelabs.mydemoapp.android:id/passwordET\"]") 
	@iOSXCUITFindBy (iOSNsPredicate="type == \"XCUIElementTypeSecureTextField\"") private   WebElement passwordTxt;
	
	@AndroidFindBy (id ="com.saucelabs.mydemoapp.android:id/loginBtn") 
	
	@iOSXCUITFindBy (iOSNsPredicate="name == \"Login\" AND label == \"Login\" AND type == \"XCUIElementTypeButton\"") private   WebElement loginBtn; 
	
	@AndroidFindBy (id ="com.saucelabs.mydemoapp.android:id/passwordErrorTV") 
	
	@iOSXCUITFindBy (iOSNsPredicate="name == \"Validation Error!\" AND label == \"Validation Error!\" AND value == \"Validation Error!") private   WebElement errorText;
    
	
	@AndroidFindBy (xpath ="//android.widget.TextView[@content-desc=\"Logout Menu Item\"]") private WebElement logouttextBtn;
	
 

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
	
	
	// switch (platformName) {
    // case "iOS":
         // Hide keyboard if blocking button
//    	 JavascriptExecutor js = (JavascriptExecutor) driver;
//    	 Map<String, Object> params = new HashMap<>();
//    	 params.put("action", "done");
//    	 //params.put("strategy", "tapOutside");  // tries to tap outside keyboard
//    	 js.executeScript("mobile: performEditorAction", params);
    	 
    	 
    	// ((IOSDriver) driver).executeScript("mobile: performEditorAction", ImmutableMap.of("action", "done"));
         
    	
//    	 IOSDriver driver = (IOSDriver) this.driver;
//    	 
//
//    	    if (driver.isKeyboardShown()) {
//    	        try {
//    	        	driver.hideKeyboard(); // works if keyboard has "Done" or "Hide"
//    	        } catch (Exception e) {
//    	            System.out.println("Keyboard not dismissed with hideKeyboard()");
//    	        }
//    	    }

    	 
	click(loginBtn);
	//break;

	
//case "Android":
//	// For Android, you might want to add a back press or other logic if needed
//	click(loginBtn);
//	break;
//default:
//	throw new IllegalArgumentException("Invalid platform: " + platformName);
//}
	return new ProductPage(driver);
}





public LoginPage pressLogintext() {
	click(logintextBtn);
	return this;
}


public ProductPage login(String username, String password) {
    enterUserName(username);
    enterpPassword(password);
    return pressloginBtn();

}
}