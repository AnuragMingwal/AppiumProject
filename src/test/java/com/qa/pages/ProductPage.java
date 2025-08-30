package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;
import com.qa.MenuPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductPage extends MenuPage {

	
	public ProductPage(AppiumDriver driver) {
		
		super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
	
	@AndroidFindBy (xpath ="//android.widget.TextView[@content-desc='title']") 
	 @iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"title\"]")  private   WebElement title; 
	
	
	@AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Product Image\"])[1]")private WebElement slbtitle;
	
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"Product Price\"])[1]")private WebElement slbPrice;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Product Title\" and @text=\"Sauce Labs Backpack\"]")private WebElement sltitle;
	
	
	
	public String getTitle() {
		return getText(title);
	}

	public String getslbTitle() {
		return getText(sltitle);
	}
	
	
	public String getslbPrice() {
		return getText(slbPrice);
	}
	
	public ProductDetailsPage presslbTitle() {
		click(slbtitle);
		return new ProductDetailsPage(driver);
	}
	
	
}
