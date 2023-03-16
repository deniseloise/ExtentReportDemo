package com.testcases;

import java.io.IOException;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pages.BaseClass;
import com.extentmanager.ExtentManager;


public class SampleTest extends BaseClass{
	@Test
	@Parameters({ "sdet", "category", "device" })
	public void TC_1(@Optional("Abc") String sdet, String category, String device) throws IOException{
		System.out.println("This is Test Case # 1");
		ExtentManager.extentCreateTest("TC1: This is Test Case 1", sdet, category, device);
		ExtentManager.extent_pass("Guru99 Website has been accessed successfully.");
		extent_screenshot();
	}

	@Test
	@Parameters({ "sdet2", "category", "device" })
	public void TC_2(@Optional("Abc") String sdet2, String category, String device) throws IOException{

		System.out.println("This is Test Case # 2");
		ExtentManager.extentCreateTest("TC2: This is Test Case 2", sdet2, category, device);
		ExtentManager.extent_fail("Unexpected Errors in Home Page");
		extent_screenshot();
	}

	@Test
	public void TC_3() {

		System.out.println("This is Test Case # 3");

	}

}
