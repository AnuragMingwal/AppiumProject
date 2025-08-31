package com.qa;



import com.qa.pages.LoginPage;
import com.qa.utils.TestUtils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BaseTest {
 
	protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	protected static  ThreadLocal<Properties> prop = new ThreadLocal<Properties>();
	protected static ThreadLocal <String> platform = new ThreadLocal<String>();
	
	
	
	protected static ThreadLocal <HashMap<String, String>> strings = new ThreadLocal< HashMap<String, String>>();
	
	TestUtils utils ;
	
	public AppiumDriver getDriver() {
		System.out.println("Driver value is: " + driver);
		
		return driver.get();
	}
	
	
	public Properties getProp() {
		return prop.get();
	}
	
	public void setProp(Properties prop2) {
		prop.set(prop2);
	}
	
	public void setDriver(AppiumDriver driver2) {
		driver.set(driver2);
	}
	
	
	
	public HashMap<String, String> getStrings() {
		return strings.get();
	}
	
	
	public void setStrings(HashMap<String, String> strings2) {
		strings.set(strings2);
	}
	
	
	
	public String getPlatform() {
		return platform.get();
	}
	
	public void  setPlatform(String platform2) {
		platform.set(platform2);
	}
	
	public BaseTest() {
		//PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	//@Optional("Android") @Optional("emulator-5554")
	
	@Parameters({"platformName","deviceName"})
	@BeforeTest
	
	public void beforeTest( String platformName,String deviceName) throws Exception {
		setPlatform(platformName);
		InputStream ip ;
		InputStream stringsis = null;
		Properties prop = new Properties();
		AppiumDriver driver;
		prop = new Properties();
		String propFileName = "config.properties";
		
		
		ip = getClass().getClassLoader().getResourceAsStream(propFileName);
		try {
			prop.load(ip);
            setProp(prop);
		stringsis = getClass().getClassLoader().getResourceAsStream("strings/strings.xml");
		
		utils = new TestUtils();
		setStrings(utils.parseStringXML(stringsis));

		
		
		UiAutomator2Options androidOptions = new UiAutomator2Options();
	      androidOptions.setPlatformName(platformName);
	      androidOptions.setDeviceName(deviceName);
	      
	      switch (platformName) {
	              case "Android":
	            	  androidOptions.setAppPackage(prop.getProperty("appPackage"));
	        	      androidOptions.setApp(System.getProperty("user.dir") + prop.getProperty("appLocation"));
	        	      androidOptions.setAutomationName(prop.getProperty("automationName"));
	        	     // androidOptions.setAppActivity(".view.activities.MainActivity");
	        	     
	        			driver = new AppiumDriver(new URL(prop.getProperty("appiumUrl")), androidOptions);
                   break;
                  case "iOS":
                      // iOS specific code here
                	  
                	  androidOptions.setAppPackage(prop.getProperty("iOSBundleId"));
	        	      androidOptions.setApp(System.getProperty("user.dir") + prop.getProperty("iOSAppLocation"));
	        	      androidOptions.setAutomationName(prop.getProperty("iOSAutomationName"));
	        	     // androidOptions.setUdid(prop.getProperty("udid"));
	        	      //androidOptions.setCapability("connectHardwareKeyboard", true);

	        	      
	        	      driver = new IOSDriver(new URL(prop.getProperty("appiumUrl")), androidOptions);
                	  
                      break;
                  default:
                	  
                	  throw new Exception("Invalid platform! Please enter 'Android' or 'iOS'." +platformName);
                     
                     
	            	  
	      }  
	            	  setDriver(driver);
	      }catch (Exception e) {
	    	  e.printStackTrace();
	      }
	      
		finally {
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
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(TestUtils.Wait_TimeOut));
		wait.until(ExpectedConditions.visibilityOf((WebElement) e));
	}
	
	
	
	public void waitforClickablity(WebElement e) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TestUtils.Wait_TimeOut));
		wait.until(ExpectedConditions.elementToBeClickable((WebElement) e));
	}
	
	
	public void click(WebElement e ) {
		waitforClickablity(e);
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
	
//	public void scrollToText(String visibleText) {
//         driver.findElement(AppiumBy.androidUIAutomator(
//            "new UiScrollable(new UiSelector().scrollable(true))" +
//            ".scrollTextIntoView(\"" + visibleText + "\")"));
//    }

	
	public void scrollToElement() {
		

		Map<String, Object> params = new HashMap<>();
	    params.put("strategy", "-android uiautomator");
	    params.put("selector", "new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/productHeightLightsTV\")");
	    params.put("maxScrolls", 5);

	    getDriver().executeScript("mobile: scroll", params);
	    
		
	}
	
	

	
	
	
	
	public  String getText(WebElement e) {
		switch (getPlatform()) {
		case "Android":
			return getAttribute(e, "text");
			
			case "iOS":
				return getAttribute(e, "label");

			default:
				throw new IllegalArgumentException("Invalid platform: " + platform);
		}
		
	}
	
	

public void closeApp() {
	
	
	if (driver != null) {
	((InteractsWithApps) driver).terminateApp("com.saucelabs.mydemoapp.android");

	
	} else {
        System.out.println("Driver is null. App not terminated.");
    }

}
	
	
	public void launchApps() {
		
		((InteractsWithApps) driver).activateApp("com.saucelabs.mydemoapp.android");
	
	}
	
	@AfterTest
	
	public void afterTest() {
		getDriver().quit();
		
	}

}
