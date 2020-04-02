package com.automation.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import com.automation.pageObjects.*;
import com.automation.utilities.PropertiesFile;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class SetUp {
	
public static WebDriver driver;
	
	static IFRS_Role_obj IFRS_Role_obj;
	static IFRS_Home_obj IFRS_Home_obj;
	
	
	public static Actions action;
	public static String message = "";
	FileOutputStream fop = null;
	public static String ScenarioName;
	static Scenario Sc;
	File file;
	public static byte[][] screenshotsArray = new byte[10][10000];
	public static Date date;
	public static String BrowserName = "";
	public static Properties data = null;
	
	static{
		DOMConfigurator.configure("log4j.xml");
	}
	@Before
	
	
	public static void setupTest(Scenario scenario) throws Exception
	{
		Sc = scenario; 
		ScenarioName =scenario.getName();
		data =PropertiesFile.readPropertiesFile();
		
	/////****************************Chrome Setup********************************/////////////////////////////	
		
		if(ScenarioName.endsWith("chrome"))	
		{	
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/exe/chromedriver.exe" );
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--allow-running-insecure-content");
			options.addArguments("--allow-insecure-websocket-from-https-origin");
			options.addArguments("disable-extensions");
			options.addArguments("allow-running-insecure-content");
			options.addArguments("--start-maximized");
			options.addArguments("disable-extensions");			
			options.addArguments("disable-plugins");
			options.addArguments("--enable-precise-memory-info"); 
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-default-apps");
			options.addArguments("test-type=browser");
			options.addArguments("disable-infobars");
			options.setExperimentalOption("useAutomationExtension", false);
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			driver =  new ChromeDriver(cap);
		
		}	
	/////////**************************IE Setup*************************************//////////////////////////		
		
		else
		{	
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/exe/IEDriverServer.exe" );
			
	        DesiredCapabilities capab = DesiredCapabilities.internetExplorer();
	        capab.setCapability(
	            InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
	            true);
	        capab.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
	        capab.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
	        capab.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
	        capab.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	        capab.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	        capab.setCapability(InternetExplorerDriver.IE_SWITCHES, true);
	        driver = new InternetExplorerDriver(capab);
		}  

		
		IFRS_Role_obj=PageFactory.initElements(driver, IFRS_Role_obj.class);
		IFRS_Home_obj=PageFactory.initElements(driver, IFRS_Home_obj.class);
		
		
//		driver.get(data.getProperty("URL"));
		
		driver.manage().deleteAllCookies();
		
		driver.manage().window().maximize();
		
		action = new Actions(driver);
		
		Capabilities cap1 = ((RemoteWebDriver) driver).getCapabilities();
		BrowserName = cap1.getBrowserName().toLowerCase();
		
		System.out.println("BrowserName == "+BrowserName);
		
		//ReportLogging.logger.info("            ");
		
	}
	
	@After()
	public static void tearDown(Scenario result) throws Exception
	{
		if(result.isFailed()){
			result.write(result.toString());
			result.write(result.getStatus());
			result.write("ScreenShot taken for failed step ");
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			result.write(message);
			result.embed(screenshot, "image/png");
			
		
		}
		result.write(message);
		driver.close();
		driver.quit();
		Runtime.getRuntime().exec("taskkill /f /im IEDriver*");
		
		Thread.sleep(1000);
	}
	

}
