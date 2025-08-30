package com.qa.listeners;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.BaseTest;

public class TestListeners implements ITestListener {
	
	
	public void onTestFailure(ITestResult result) {
		
		if (result.getThrowable() != null) {
			
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			result.getThrowable().printStackTrace(pw);
			
			
			
			System.out.println("Test Failed - Exception: " + result.getThrowable().getMessage());
			System.out.println("Stack Trace: " + sw.toString());
		}
		
		System.out.println("Test Failed - Taking Screenshot");
		// Code to take screenshot can be added here
		
		
		BaseTest testClass = new BaseTest();
		File file = testClass.getDriver().getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date(Calendar.getInstance().getTimeInMillis()));

		try {
		    String path = System.getProperty("user.dir") + File.separator + "screenshots"
		                 + File.separator + result.getName() + "_" + timestamp + ".png";
		    File destFile = new File(path);
		    destFile.getParentFile().mkdirs(); // make sure folder exists
		    FileUtils.copyFile(file, destFile);
		    System.out.println("Screenshot saved at: " + path);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
	}
    
	
	
	
}
