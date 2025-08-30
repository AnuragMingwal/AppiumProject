package com.qa.tests;

import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.qa.BaseTest;
import com.qa.MenuPage;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;
import com.qa.pages.SettingsPage;

public class LoginTests extends BaseTest{
	
	
	LoginPage loginPage;
	MenuPage menuPage;
	SettingsPage settingsPage;
	ProductPage productPage;
	InputStream datais;
	JSONObject loginUsers;
	
	
	
	
	
		@BeforeClass
		public void beforeClass() {
			
			try {
				String dataFileName = "data/loginUsers.json";
				datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
				JSONTokener tokener = new JSONTokener(datais);
				
				loginUsers = new JSONObject(tokener);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (datais != null) {
						datais.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			

		}
	
  @Test
  public void validuserName() {
	  
	 
	  menuPage.pressMenuBtn();
	  loginPage.pressLogintext();
	  
	  loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
	  loginPage.enterpPassword(loginUsers.getJSONObject("validUser").getString("password"));
	  productPage=  loginPage.pressloginBtn();
	  
	  String actualText = productPage.getTitle();
	  System.out.println(actualText);
	  String expectedtext =  strings.get("product_Title");
	  Assert.assertEquals(actualText, expectedtext);
	  
	  
	  
  }
  
  
  
  @Test
	public void invaliduserName() {
	  	
	    menuPage.pressMenuBtn();
	  	loginPage.pressLogintext();
		loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("username"));
		loginPage.enterpPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
		loginPage.pressloginBtn();

		String actualError = loginPage.getErrorText();
		System.out.println(actualError);
		String expectedError = strings.get("popup_Text");
		Assert.assertEquals(actualError, expectedError);

	}
  
  
  @AfterClass
  
	public void afterMethod() {
	  if (productPage != null) {
	     settingsPage = menuPage.pressMenuBtn();
		loginPage = settingsPage.pressLogoutBtn();
		loginPage = settingsPage.pressLogoutYesBtn();
	  } else {
	        System.out.println("No valid login happened, skipping logout.");
	    }
	}
  
  
  @BeforeMethod
  public void beforeMethod(Method m) {
	  loginPage = new LoginPage(driver);
	  menuPage = new MenuPage(driver);
	  
	  System.out.println("\n"+ "************ Starting Test *********" + m.getName()+"**************"+"\n");
	  
	  
  }



}
