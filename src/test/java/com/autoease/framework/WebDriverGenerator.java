package com.autoease.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.autoease.framework.SeleniumCore;
import com.autoease.framework.utilities.HostUtils;

import cucumber.api.Scenario;
import cucumber.api.java.After;

/**
 * WebDriverGenerator class.  It is a core of framework, implements a private constructor which provides current driver object
 * to each step definition class, always make an instance of this class whenever you create a customized step definition 
 * 
 * 
 * 
 * 
 * @author Gaurav Tiwari
 * 
 * @todo It is not tested yet 
 *
 * Copyright [2015] [Gaurav Tiwari]
 * 
 * 
 */
		public class WebDriverGenerator {
			
			static WebDriver driver =null;
			static WebDriverWait wait=null;
			public static Properties OR=null;
			public static Properties CONFIG=null;
			Logger APP_LOGS=Logger.getLogger("devpinoyLogger");
			
			public WebDriverGenerator() {
				//This constructor does not implement anything as of now
			}
			
			public static WebDriverWait getWaitObject(){
				wait=new WebDriverWait(driver,30);
				return wait;
			}
			
/**
  * returns a new driver object, if driver is already initialized 
  * then returns the existing object for using this method make an object of current class and call.
  * 
  *
  */		
			
			public static WebDriver getWebDriver(){
			
				if (driver==null){
					//Initialize the OR
					OR=new Properties();
					
					//Initialize the Config
					CONFIG=new Properties();
					
					try{
					FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\test-configs\\or.properties");
					//System.out.println(System.getProperty("user.dir"));
					OR.load(fs);
					
					fs=new FileInputStream(System.getProperty("user.dir")+"\\test-configs\\config.properties");
					CONFIG.load(fs);
					}
					
					catch(Exception e){
						System.out.println("Error in initiatilizing property file");
					
					}
					
				
					//logger.info("browser is :"+browsername+",proxy is :"+proxy);
					
					//logger.info("browser is :"+browsername+",proxy is :"+proxy);
					DesiredCapabilities capability=new DesiredCapabilities();
					SeleniumCore.browserCommonSettings(capability);
					
					//code for selecting the preferred browser and launching it with preferred capabilities
							if(CONFIG.getProperty("browserType").equalsIgnoreCase("Mozilla")){	
								try{
									try {
										SeleniumCore.browserFirefoxSettings(capability);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								driver = new FirefoxDriver(capability);
								
								}catch(WebDriverException e){
									  if(e.getMessage().contains("Cannot find firefox binary in PATH")){
										   System.out.println("Current execution host:"+HostUtils.getFQDN()+" not installed Firefox Browser ,please install it firstly!");
									   }else{
										   System.out.println("Firefox Driver met unexpected error:"+e);
									   }
									   System.exit(1);
								  }
								   
								  }
								  else if(CONFIG.getProperty("browserType").equalsIgnoreCase("Chrome")){
									  SeleniumCore.browserChromeSettings(capability);
									  try{
										  driver=new ChromeDriver(capability);
										  }catch(WebDriverException exception){
											  if(exception.getMessage().contains("unknown error: cannot find Chrome binary")){
												  System.out.println("Current execution host:"+HostUtils.getFQDN()+" not installed Chrome Browser ,please install it firstly!");					  
											   }else{
												   System.out.println("Chrome Driver met unexpected error:"+exception);
											   }
											  System.exit(1);
										  }
										
								  }
								  else if(CONFIG.getProperty("browserType").equalsIgnoreCase("IE")){
									  SeleniumCore.browserIESettings(capability);
									
										driver=new InternetExplorerDriver(capability);
								  }		
							
							
								  else if(CONFIG.getProperty("browserType").trim().equalsIgnoreCase("safari")){
										SeleniumCore.browserSafariSettings(capability);
										try{
										 driver=new SafariDriver(capability);
										   }catch(IllegalStateException exception){
											   if(exception.getMessage().contains("The expected Safari data directory does not exist")){
												   System.out
														.println("Current execution host:"+HostUtils.getFQDN()+" not installed Safari Browser ,please install it firstly!");
											   }else{
												   System.out
													.println("Safari Driver met unexpected error:"+exception);
											   }
											   System.exit(1);
										   }catch(WebDriverException exception){
											   System.out
												.println("Current execution host:"+HostUtils.getFQDN()+" not installed safari Browser ,please install it firstly!");
											       System.exit(1);
										   }
									}
					
				}
				//set the implicit wait, visit the selenium manager method implementation
				SeleniumCore.seleniumManager(driver);
					return driver;	
			}
			
			

			/** 
			* @Title: catchAnyExceptions 
			* @Description: TODO
			* @author GAURAV TIWARI [gauravtiwari91@yahoo.com]
			* @param @param result
			* @param @param context
			* @param @throws Exception    
			* @return void    return type
			* @throws 
			*/ 
			
			/*
			@AfterMethod(description="this method will capture any Exception when the testing is running")
			public void catchAnyExceptions(ITestResult result, ITestContext context)throws Exception {
					final Throwable t = result.getThrowable();
				    if(t instanceof IOException){
				    	
				    }

			}
			
			/**
			  * returns a new driver object, if driver is already initialized 
			  * then returns the existing object for using this method make an object of current class and call.
			  * 
			  * @param WebDriver	The driver object to be used 
			  * @param By	selector to find the element
			  * @param String	Value attribute of the option tag of drop down
			  *
			  */
			public By getIdentifierAt(String propertyValue){
				By objectValue = null;
				
				String lowercasepropertyValue =propertyValue.toLowerCase();
				System.out.println(propertyValue);
				
				
				try{
					if(lowercasepropertyValue.endsWith(GlobalConstants.CSS)){
					objectValue=By.cssSelector(OR.getProperty(propertyValue));
					}
					
					else if(lowercasepropertyValue.endsWith(GlobalConstants.ID)){
						objectValue=By.id(OR.getProperty(propertyValue));
						}
					
					else if(lowercasepropertyValue.endsWith(GlobalConstants.NAME)){
						objectValue=By.name(OR.getProperty(propertyValue));
						}
					else if(lowercasepropertyValue.endsWith(GlobalConstants.TAG_NAME)){
						objectValue=By.tagName(OR.getProperty(propertyValue));
						}
					else if(lowercasepropertyValue.endsWith(GlobalConstants.CLASS_NAME)){
						objectValue=By.className(OR.getProperty(propertyValue));
						}
					
					else if(lowercasepropertyValue.endsWith(GlobalConstants.LINK_TEXT)){
						objectValue=By.linkText(OR.getProperty(propertyValue));
						}
					else if(lowercasepropertyValue.endsWith(GlobalConstants.PARTIAL_LINK_TEXT)){
						objectValue=By.partialLinkText(OR.getProperty(propertyValue));
						}
					
					else if(lowercasepropertyValue.endsWith(GlobalConstants.XPATH)){
						objectValue=By.xpath(OR.getProperty(propertyValue));
						}
					else
					{
						 System.out.println("Object identifier is not named properly, Please append one of the following text for each identifer in OR properties file accordingly");
						 System.out.println("---css, id, name, tagname, classname, linktxt, prtlinktxt or xpath---");
					}				
				}
				catch(Exception e){
								System.out.println("error in getting the object"+e.getMessage());
							}
								
					
				return objectValue;
			}
			
			
		}
