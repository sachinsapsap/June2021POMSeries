package com.qa.democart.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public static String highlight;
	private OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	/**
	 * This method is used to initialize the driver and launch the browser provided.
	 * @param browserName Pass the browser to be launched.
	 * @return This returns the WebDriver reference.
	 */
	public WebDriver initDriver(Properties prop)
	{
		String browserName=prop.getProperty("browser");
		highlight=prop.getProperty("highlight");
		
		optionsManager=new OptionsManager(prop);
		
		if(browserName.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
//			driver=new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
//			driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase("safari")){
//			driver=new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else
		{
			System.out.println("Please pass the correct browser");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * This method is used to initialize the properties on the basis of any given environment name. 
	 * @return This returns the Properties reference.
	 * @throws Exception 
	 */
	public Properties initProperties() throws Exception {
		Properties prop=null;
		FileInputStream ip=null;
		
		String env=System.getProperty("env");//mvn clean install -Denv="qa".Reading from maven command
		
		try {
		if(env==null)
		{
			System.out.println("Running on Environment: PROD env...");
				ip=new FileInputStream("./src/test/resources/config/config.properties");
		}
		
		else {
			System.out.println("Running on Environment: "+env);
			switch (env) {
			case "qa":
				ip=new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "dev":
				ip=new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "stage":
				ip=new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;

			default:
				System.out.println("No Env found...");
				throw new Exception("NOENVFOUNDEXCEPTION");
			}
		}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			
		
		try {
			
			prop=new Properties();
				prop.load(ip);
			}
		catch (IOException e) {
				e.printStackTrace();
			}
	
		
		return prop;
	}
	
	
	public String getScreenshot() {
		File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);//take srreenshot and store at temporary name
		String path=System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";//create new path
		File destination=new File(path);//move screenshot to new path
		
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		
	}
	
	
	
}
