package com.extentmanager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentManager{

	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;

	public static void setExtent() {

		spark = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/test-output/ExtentReport/" + "ExtentReport.html");
		
		// config
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Guru99 Extent Report");

		extent = new ExtentReports();
		extent.attachReporter(spark);

	}

	// Extent Reports Create Test
	public static void extentCreateTest(String title, String author, String category, String device) {
		test = extent.createTest(title).assignAuthor(author).assignCategory(category).assignDevice(device);
	}

	// Extent Reports Pass
	public static void extent_pass(String msg) {
		test.pass(msg);
	}

	// Extent Reports Fail
	public static void extent_fail(String msg) {
		test.fail(msg);
	}

	// Extent Reports Info Message
	public static void extent_info(String msg) {
		test.info(msg);
	}
	
	
	public static void endReport() {
		extent.flush();
	}

}
