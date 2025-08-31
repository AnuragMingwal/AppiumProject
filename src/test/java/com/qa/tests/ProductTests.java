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
import org.testng.asserts.SoftAssert;

import com.qa.BaseTest;
import com.qa.MenuPage;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductPage;
import com.qa.pages.SettingsPage;

public class ProductTests extends BaseTest{
	
	
	LoginPage loginPage;
	MenuPage menuPage;
	SettingsPage settingsPage;
	ProductPage productPage;
	ProductDetailsPage productDetailsPage;
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
			
			//closeApp();
			//launchApps();

		}
	
 
  
  
  
  @Test
	public void validateProductOnProductsPage() {
	  
	  
	  menuPage.pressMenuBtn();
	  loginPage.pressLogintext();
	  
	  SoftAssert softAssert = new SoftAssert();
	  
	  productPage = loginPage.login(
			    loginUsers.getJSONObject("validUser").getString("username"), 
			    loginUsers.getJSONObject("validUser").getString("password"));
	  
	  String actualslbTitle = productPage.getslbTitle();
	  
	  softAssert.assertEquals(actualslbTitle, getStrings().get("product_page_slb_title"), "SLB Title does not match");
	  String actualslbPrice = productPage.getslbPrice().replaceAll("\\s+", "");
	  softAssert.assertEquals(actualslbPrice, getStrings().get("product_page_slb_price").replaceAll("\\s+", ""), "SLB Price does not match");
	  
	  
	//  settingsPage	= productPage.pressMenuBtn();
	 // loginPage =settingsPage.pressLogoutBtn();
	  
	      settingsPage = productPage.pressMenuBtn();
	    settingsPage.pressLogoutBtn();
	    settingsPage.pressLogoutYesBtn();
	
	  
	  
	  softAssert.assertAll();
	  	
	    

	}
  
  @Test
  public void validProductsOnProductsDetailPage() {
	  
	  
	   menuPage.pressMenuBtn();
	   loginPage.pressLogintext();
	  
	 
 SoftAssert softAssert = new SoftAssert();
	  
	  productPage = loginPage.login(
			    loginUsers.getJSONObject("validUser").getString("username"), 
			    loginUsers.getJSONObject("validUser").getString("password"));
	  
	  productDetailsPage = productPage.presslbTitle();
	  String actualslbTitle = productDetailsPage.getslbTitle();
	  softAssert.assertEquals(actualslbTitle, getStrings().get("product_details_page_slb_title"), "SLB Title does not match");
	  productDetailsPage.scrollToPrize();
	  
	  String actualslbTxt = productDetailsPage.getslbText();
	  softAssert.assertEquals(actualslbTxt, getStrings().get("product_details_page_slb_description"), "SLB Price does not match");
	  
	  
	    settingsPage = productPage.pressMenuBtn();
	    settingsPage.pressLogoutBtn();
	    settingsPage.pressLogoutYesBtn();
	  
	 // productPage = productDetailsPage.backBtn();
	  //loginPage =settingsPage.pressLogoutBtn();
	  softAssert.assertAll();
	 
  }
  
  
  
  @BeforeMethod
  public void beforeMethod(Method m) {
	  loginPage = new LoginPage(getDriver());
	   menuPage = new MenuPage(getDriver());
	  System.out.println("\n"+ "************ Starting Test *********" + m.getName()+"**************"+"\n");
	  
	  
  }



}
