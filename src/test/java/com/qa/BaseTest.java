package com.qa;



import com.qa.pages.LoginPage;
import com.qa.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BaseTest {
 
	protected static AppiumDriver driver;
	protected static Properties prop;
	
	protected static HashMap<String, String> strings = new HashMap<String, String>();
	InputStream ip ;
	InputStream stringsis;
	TestUtils utils ;
	
	
	public BaseTest() {
		//PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	//@Optional("Android") @Optional("emulator-5554")
	
	@Parameters({"platformName","deviceName"})
	@BeforeTest
	
	public void beforeTest( String platformName,String deviceName)  {
		
		prop = new Properties();
		String propFileName = "config.properties";
		
		ip = getClass().getClassLoader().getResourceAsStream(propFileName);
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stringsis = getClass().getClassLoader().getResourceAsStream("strings/strings.xml");
		
		utils = new TestUtils();
		strings = utils.parseStringXML(stringsis);

		
		
		UiAutomator2Options androidOptions = new UiAutomator2Options();
	      androidOptions.setPlatformName(platformName);
	      androidOptions.setDeviceName(deviceName);
	      androidOptions.setAppPackage(prop.getProperty("appPackage"));
	      androidOptions.setApp(System.getProperty("user.dir") + prop.getProperty("appLocation"));
	      androidOptions.setAutomationName(prop.getProperty("automationName"));
	      try {
			driver = new AppiumDriver(new URL(prop.getProperty("appiumUrl")), androidOptions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
			
			
		} finally {
			try {
				if (ip != null) {
					ip.close();
				}
				if (stringsis != null) {
					stringsis.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	     
		
	}
	
	
	public void waitforVisibilty(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(TestUtils.Wait_TimeOut));
		wait.until(ExpectedConditions.visibilityOf((WebElement) e));
	}
	
	
	
	public void click(WebElement e ) {
		waitforVisibilty(e);
		e.click();
		
	}
	
	
	public void sendkeys(WebElement e, String text) {
		waitforVisibilty(e);
		e.sendKeys(text);
	}
	
	public String getAttribute(WebElement e , String attribute) {
		waitforVisibilty(e);
		 return e.getAttribute(attribute);
	}
	
	
	public  String getText(WebElement e) {
		return e.getText();
		
	}
	
	
	@AfterTest
	
	public void afterTest() {
		
	}

}
