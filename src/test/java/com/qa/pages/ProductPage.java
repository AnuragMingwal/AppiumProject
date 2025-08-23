package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductPage extends BaseTest {

	
	
	public ProductPage(AppiumDriver driver) {
	//	BaseTest.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
	
	@AndroidFindBy (xpath ="//android.widget.TextView[@content-desc='title']") private   WebElement title; 
	
	
	
	
	public String getTitle() {
		return getText(title);
	}

}
