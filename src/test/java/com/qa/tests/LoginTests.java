package com.qa.tests;

import java.lang.reflect.Method;

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
	
  @Test
  public void validuserName() {
	  
	  loginPage.pressMenuBtn();
	  loginPage.pressLogintext();
	  
	  loginPage.enterUserName("anurag");
	  loginPage.enterpPassword("Mingwal");
	  productPage=  loginPage.pressloginBtn();
	  
	  String actualText = productPage.getTitle();
	  System.out.println(actualText);
	  String expectedtext =  "Products";
	  Assert.assertEquals(actualText, expectedtext);
	  
	  
	  
  }
  @BeforeMethod
  public void beforeMethod(Method m) {
	  loginPage = new LoginPage(driver);
	  System.out.println("\n"+ "************ Starting Test *********" + m.getName()+"**************"+"\n");
	  
	  
  }



}
