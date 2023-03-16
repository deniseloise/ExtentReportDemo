package com.pages;

import com.extentmanager.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

public class BaseClass {

	public static WebDriver driver;


	@BeforeTest
	@Parameters({"guru99Url","browser"})
	public void setup(@Optional("Abc") String guru99Url, String browser) {
		
		switch (browser.toLowerCase()) {
		case "chrome":
			driver = getChromeDriver();
			driver.get(guru99Url);
			driver.manage().window().maximize();
			ExtentManager.setExtent();
			break;
		case "firefox":
			driver = getFirefoxDriver();
			driver.get(guru99Url);
			driver.manage().window().maximize();
			ExtentManager.setExtent();
			break;
		case "":
			driver = getEdgeDriver();
			driver.get(guru99Url);
			driver.manage().window().maximize();
			ExtentManager.setExtent();
			break;
		default:
			throw new IllegalArgumentException("Match case not found for browser: " + browser);
		}
	}
	
	private static WebDriver getChromeDriver() {
		WebDriverManager.chromedriver().clearResolutionCache().setup();
		return new ChromeDriver();
	}

	private static WebDriver getFirefoxDriver() {
		WebDriverManager.firefoxdriver().clearResolutionCache().setup();
		return new FirefoxDriver();
	}

	private static WebDriver getEdgeDriver() {
		WebDriverManager.edgedriver().clearResolutionCache().setup();
		return new EdgeDriver();
	}
	
	
	public static void extent_screenshot() throws IOException {

		ExtentManager.test.addScreenCaptureFromPath(capturescreenshot(driver));
	}
	
	
	//Capture Screenshot and Store it
	public static String capturescreenshot(WebDriver driver) throws IOException{
		
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destinationfilepath = new File(System.getProperty("user.dir") + "/Screenshots/screenshot" + System.currentTimeMillis() + ".png");
		
		String absolutepathlocation = destinationfilepath.getAbsolutePath();
		
		FileUtils.copyFile(srcfile, destinationfilepath); 
		
		return absolutepathlocation;
		
	}
	
	public void sleep(long duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
		ExtentManager.endReport();
	}

}
