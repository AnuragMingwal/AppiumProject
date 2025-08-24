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
import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;

public class LoginTests extends BaseTest{
	
	
	LoginPage loginPage;
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
	  
	 
	  loginPage.pressMenuBtn();
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
	  	
	  	loginPage.pressMenuBtn();
	  	loginPage.pressLogintext();
		loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("username"));
		loginPage.enterpPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
		loginPage.pressloginBtn();

		String actualError = loginPage.getErrorText();
		System.out.println(actualError);
		String expectedError = strings.get("popup_Text");
		Assert.assertEquals(actualError, expectedError);

	}
  
  
  
  
  
  @BeforeMethod
  public void beforeMethod(Method m) {
	  loginPage = new LoginPage(driver);
	  System.out.println("\n"+ "************ Starting Test *********" + m.getName()+"**************"+"\n");
	  
	  
  }



}
