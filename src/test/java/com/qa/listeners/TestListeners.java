package com.qa.listeners;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.testng.ITestListener;
import org.testng.ITestResult;

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
	}

}
