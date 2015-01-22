package com.autoease.framework;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;




/**
* @ClassName: SeleniumCore
* @Description: This class has all the browser capabilities related methods like, setCapabilities etc. TODO
* @author GAURAV TIWARI [gauravtiwari91@yahoo.com]
* @date Jan 18, 2015 12:32:46 AM
* 
*/

public class SeleniumCore {

	  private static Logger logger = Logger.getLogger(SeleniumCore.class);
	  
	  
	
		/**
		 * getBrowserType:(get the current running browser type and version number). 
		
		 * @param driver  ---the web driver instance
		
		 * @since JDK 1.6
		 * @return String --- the browser type and version number */
		public static String getBrowserType(WebDriver driver){
			Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = caps.getBrowserName();
			String browserVersion = caps.getVersion();
			//String platform=caps.getPlatform().toString();
			logger.info("Get the current running browser, which is:"+browserName+",the browser version is:"+browserVersion);
			return browserName+" "+browserVersion;
		}
	   
		
		
		/** 
		* @Title: browserCommonSettings 
		* @Description: This method has the comman settings which are applicable to all the browser which are supported
		* by selenium
		* @author GAURAV TIWARI [gauravtiwari91@yahoo.com]
		* @param capability object of Desired Capabilities   
		* @return void    return type
		* @throws 
		*/
		public static void browserCommonSettings(DesiredCapabilities capability){
			capability.setCapability("cssSelectorsEnabled", true);
			capability.setCapability("takesScreenshot", true);
			capability.setCapability("javascriptEnabled", true);
			capability.setCapability("ignoreZoomSetting",true);
			capability.setCapability("ignoreProtectedModeSettings", true);
			capability.setCapability("enablePersistentHover", false); // prevent
			capability.setCapability("EnableNativeEvents", false);
			
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capability.setCapability("acceptSslCerts", true); //accept the securty ssl url
			capability.setCapability("unexpectedAlertBehaviour", "accept");
		}
	
	    /** 
	    * @Title: browserProxySettings 
	    * @Description: This method set all browser proxy e.g. Http, SSL, no proxy etc.
	    * @author GAURAV TIWARI[gauravtiwari@yahoo.com]
	    * @param @param capability
	    * @param @param proxysettings
	    * @return void    return type
	    * @throws 
	    */ 
	    
	    public static void browserProxySettings(DesiredCapabilities capability,String proxysettings){
	    	org.openqa.selenium.Proxy httpproxy = new org.openqa.selenium.Proxy();  
			httpproxy.setHttpProxy(proxysettings); 
			httpproxy.setSslProxy(proxysettings);
		
			httpproxy.setNoProxy("localhost");
			capability.setCapability(CapabilityType.PROXY,httpproxy);
	    }
		
	    /** 
	    * @Title: BrowserIESettings 
	    * @Description: TODO
	    * @author GUARAV TIWARI[gauravtiwari91@yahoo.com]
	    * @param @param capability    
	    * @return void    return type
	    * @throws 
	    * @refer http://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/ie/InternetExplorerDriver.html 
	    */ 
	    
	    public static void browserIESettings(DesiredCapabilities capability){
	    	
	    	String iedriver=null;	 
	    	if (System.getProperties().getProperty("os.arch").toLowerCase().equals("x86_64") || System.getProperties().getProperty("os.arch").toLowerCase().equals("amd64")) {
	    		iedriver = GlobalConstants.SELENIUM_DRIVER_PATH+"/windows/ie/2.40.0/64bit/IEDriverServer.exe";
            } else {
            	iedriver =GlobalConstants.SELENIUM_DRIVER_PATH+ "/windows/ie/2.40.0/32bit/IEDriverServer.exe";
            }
	    	
	    	
			System.setProperty("webdriver.ie.driver",iedriver);
			logger.info("Set IE Driver in this location:"+iedriver);
	    	//SSL url setting 
	    	capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	    	capability.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
			capability.setCapability(InternetExplorerDriver.NATIVE_EVENTS,false);
			capability.setCapability("requireWindowFocus", true);
			/*should set to true :
			 * http://jimevansmusic.blogspot.com/2012/08/youre-doing-it-wrong-protected-mode-and.html
			 */		
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);	
			capability.setCapability(InternetExplorerDriver.LOG_LEVEL, "TRACE");
			capability.setCapability(InternetExplorerDriver.LOG_FILE, GlobalConstants.Log4J_LOG_DIR);	
			//clear session
			capability.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		
	    }
	    
	    /**
		 * set the firefox profile ,like download file automatically .
		
		
		 * @return FirefoxProfile
		 * @throws IOException 
		 * 
		 * */
		public static FirefoxProfile browserFirefoxSettings(DesiredCapabilities capability) throws IOException{
			FirefoxProfile fp=new FirefoxProfile();
			//fp.addExtension(extensionToInstall);
			//http://stackoverflow.com/questions/15292972/auto-download-pdf-files-in-firefox
			//http://www.webmaster-toolkit.com/mime-types.shtml
			
			fp.setAcceptUntrustedCertificates(true);
			fp.setAssumeUntrustedCertificateIssuer(true);
			fp.setEnableNativeEvents(false);
			//fp.setProxyPreferences(proxy);
			// for the download bar to automatically download it
			
			fp.setPreference("browser.download.manager.showWhenStarting", false);	
			String downloaddir=GlobalConstants.BROWSER_DOWNLOAD_DIR;	
			fp.setPreference("browser.download.useDownloadDir", true);
			fp.setPreference("browser.download.dir", downloaddir);
			fp.setPreference("browser.download.lastDir", downloaddir);
			fp.setPreference("browser.download.folderList", 2);
			
			//fp.setPreference("browser.helperApps.alwaysAsk.force", false);
			fp.setPreference("security.default_personal_cert", "Select Automatically");
			fp.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream,application/x-compressed,application/x-zip-compressed,application/zip,multipart/x-zip");
			//File sslerror=new File(SeleniumCore.getProjectWorkspace()+"resources"+File.separator+"remember_certificate_exception-1.0.0-fx.xpi");
			//fp.addExtension(sslerror);
			capability.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
			capability.setCapability(FirefoxDriver.PROFILE, fp);
			logger.info("Had set the firefox profile for current selenium run session");
			
			return fp;
		}
		
		/** 
		* @Title: browserChromeSettings 
		* @Description: TODO
		* @author GUARAV TIWARI[gauravtiwari91@yahoo.com]
		* @param @return    
		* @return ChromeOptions    return type
		* @throws 
		*/ 
		
		public static ChromeOptions browserChromeSettings(DesiredCapabilities capability){
			
		      String chromedriver=new File("chromedriver.exe").getAbsolutePath();	 
			/* System.setProperty("webdriver.chrome.driver",chromedriver);*/
			 
			 if (System.getProperties().getProperty("os.arch").toLowerCase().equals("x86_64") || System.getProperties().getProperty("os.arch").toLowerCase().equals("amd64")) {
                 if (System.getProperties().getProperty("os.name").toLowerCase().contains("windows")) {
                	 chromedriver= GlobalConstants.SELENIUM_DRIVER_PATH+"/windows/chrome/2.13/64bit/chromedriver.exe";
                 } else if (System.getProperties().getProperty("os.name").toLowerCase().contains("mac")) {
                	 chromedriver= GlobalConstants.SELENIUM_DRIVER_PATH+"/osx/chrome/2.13/64bit/chromedriver";
                 } else if (System.getProperties().getProperty("os.name").toLowerCase().contains("linux")) {
                	 chromedriver= GlobalConstants.SELENIUM_DRIVER_PATH+"/linux/chrome/2.13/64bit/chromedriver";
                 }
             } else { //32 BIT
                 if (System.getProperties().getProperty("os.name").toLowerCase().contains("windows")) {
                	 chromedriver=  GlobalConstants.SELENIUM_DRIVER_PATH+"/windows/chrome/2.13/32bit/chromedriver.exe";
                 } else if (System.getProperties().getProperty("os.name").toLowerCase().contains("mac")) {
                	 chromedriver=  GlobalConstants.SELENIUM_DRIVER_PATH+"/osx/chrome/2.13/32bit/chromedriver";
                 } else if (System.getProperties().getProperty("os.name").toLowerCase().contains("linux")) {
                	 chromedriver=  GlobalConstants.SELENIUM_DRIVER_PATH+"/linux/chrome/2.13/32bit/chromedriver";
                 }
             }
			 System.setProperty("webdriver.chrome.driver",chromedriver);
			 logger.info("Set the chrome driver in this location :"+chromedriver);
			 Map<String, Object> prefs = new HashMap<String, Object>();		 
				
			 prefs.put("profile.default_content_settings.popups", 0);		 
			 String downloaddir=GlobalConstants.BROWSER_DOWNLOAD_DIR;
			 prefs.put("download.default_directory",downloaddir);
			 prefs.put("download.directory_upgrade", true);
			 prefs.put("download.extensions_to_open", "");
			 
			 ChromeOptions options=new ChromeOptions();
			 options.setExperimentalOption("prefs", prefs);
			 options.addArguments("start-maximized");
			 
			 capability.setCapability(ChromeOptions.CAPABILITY,options);
			 logger.debug("the chrome Configuration driver path is:"+chromedriver);
			 return options;
			
		}
		
		public static void browserSafariSettings(DesiredCapabilities capability){
			capability.setCapability("safari.cleanSession", true);
		}
		
	   public static void browserOperaSettings(DesiredCapabilities capability){
		    capability.setCapability("opera.arguments", "-nowin -nomail");
			
		} 
	    /** 
	    * @Title: seleniumManager 
	    * @Description: TODO
	    * @author GAURAV TIWARI [gauravtiwari91@yahoo.com]
	    * @param @param driver    
	    * @return void    return type
	    * @throws 
	    */ 
	    
	    public static void seleniumManager(WebDriver driver){
	    	try
			{
	    		driver.manage().deleteAllCookies();
	    		
				//page load time
				driver.manage().timeouts().pageLoadTimeout(GlobalConstants.PAGE_LOADING_TIME, TimeUnit.SECONDS);
				//the web element to find time we need to wait 
			    driver.manage().timeouts().implicitlyWait(GlobalConstants.WEBELEMENT_LOADING_TIME, TimeUnit.SECONDS);
			    // the js executor timeout
			    driver.manage().timeouts().setScriptTimeout(GlobalConstants.JS_TIMEOUT, TimeUnit.SECONDS);
			   
			    driver.manage().window().maximize();
			 }
			catch(TimeoutException e){
				logger.error("The page or the webelment had been waited for 120 second,it cannot showed ,so the test failed"+",Exception:"+e.getMessage());
				Assert.fail("Current Web Page, webelment or JAVAScript loading timeout");
			}
	    	
	    }
	    
	    
	   // capture the error screenshot if ocurred the error
		/**
		 * Method captureErrorScreenshot.
		 * @param result ITestResult
		 * @param errortype String
		 * @throws IOException
		 */
	    
	    /*
		public void captureErrorScreenshot(WebDriver driver)
				 {
			String filename = new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(Calendar
							.getInstance().getTime()) + ".png";
			logger.debug("we met the error,we will generate a screenshot file for this error, file name is "
					+ filename);
            String screenshotDestination=GlobalDefinition.REPORTRESULT_DIR+File.separator+filename;
			//File scrFile = ((TakesScreenshot) new Augmenter().augment( driver ))
		    File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			
		
			try {
				logger.debug("the source screenshot file path is :"+ scrFile.getCanonicalPath());
				FileUtils.copyFile(scrFile, new File(screenshotDestination));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.debug("the screenshot file saved in this file path: "+screenshotDestination);
			//Reporter.setCurrentTestResult(result);
			
			StringBuilder sb=new StringBuilder();
			Document doc;
			doc = Jsoup.parseBodyFragment(FilesUtils.returnFileContents(GlobalDefinition.REPORTRESULT_DIR+File.separator+GlobalDefinition.DAILY_REPORT_FILE));
			Element imagetbodynode = doc.select("table.MsoNormalTable").get(4).select("tbody").first();
			Elements imagerow=doc.select("table.MsoNormalTable").get(4).select("tbody").first().select("tr");
			System.out.println("the image node is :" + imagetbodynode.text());
			int rownumber=imagerow.size();
			String imagecode=""
							+ "<tr><td><p>"+filename +"</p></td><td><span> <img src=\"cid:image"+rownumber+"@selenium\" alt=\" "
							+ "this image cannot show,maybe blocked by your email setting \"><br></span></td></tr>";
			imagetbodynode.append(imagecode);
			// generate the html report
/*************************************************************************************************************
			sb.append(doc.body().html());
			FilesUtils.writeContents(GlobalDefinition.REPORTRESULT_DIR+File.separator+GlobalDefinition.DAILY_REPORT_FILE, sb.toString()); 
		}
	*/
}