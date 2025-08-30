package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import com.qa.MenuPage;

import io.appium.java_client.AppiumDriver;

public class ProductDetailsPage extends MenuPage {

	public ProductDetailsPage(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/productTV\"]")private WebElement slbtitle;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/descTV\"]")private WebElement slbTxt;
	//android.widget.TextView[@resource-id="com.saucelabs.mydemoapp.android:id/descTV"]
	
	public String getslbTitle() {
		return getText(slbtitle);
	}
	
	public String getslbText() {
		return getText(slbTxt);
	}
	
	public  ProductDetailsPage  scrollToPrize() {
		scrollToElement();
			return this;
     
    }
	
	public ProductPage backBtn() {
        driver.navigate().back();
        return new ProductPage(driver);
    }

}	
