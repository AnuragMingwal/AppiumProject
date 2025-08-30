package com.qa;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.pages.SettingsPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class MenuPage extends BaseTest {
	
	 protected AppiumDriver driver; 
	
	public MenuPage(AppiumDriver driver) {
		
		this.driver = driver;
	    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy (xpath ="//android.widget.ImageView[@content-desc=\"View menu\"]") private   WebElement menuBtn;
	

	
	public SettingsPage pressMenuBtn() {
		click(menuBtn);
		return new SettingsPage(driver);
	}
	
	
}
