package com.autoease.framework.WebAppSteps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.autoease.framework.Constants;
import com.autoease.framework.WebDriverGenerator;

import static com.autoease.framework.WebDriverGenerator.OR;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


/**
 * Wait tool class.  Provides Wait methods for an elements, and AJAX elements to load.  
 * It uses WebDriverWait (explicit wait) for waiting an element or javaScript.  
 * 
 * To use implicitlyWait() and WebDriverWait() in the same test, 
 * we would have to nullify implicitlyWait() before calling WebDriverWait(), 
 * and reset after it.  This class takes care of it. 
 * 
 * 
 * Generally relying on implicitlyWait slows things down 
 * so use WaitTool�s explicit wait methods as much as possible.
 * Also, consider (DEFAULT_WAIT_4_PAGE = 0) for not using implicitlyWait 
 * for a certain test.
 * 
 * @author Gaurav Tiwari
 * 
 * @todo check FluentWait
 *
 * Copyright [2015] [Gaurav Tiwari]
 * 
 * 
 */
public class WaitSteps {

	WebDriver driver =WebDriverGenerator.getWebDriver();
	WebDriverGenerator wd;
	
	/** define the Default wait time for an element in Constants.java file */ 
	public static final int DEFAULT_WAIT_4_ELEMENT = Constants.DEFAULT_WAIT_4_ELEMENT;
	/** Define default wait time for a page to be displayed in Constants.java file.  
	 * The average webpage load time is 10 seconds in 2015. 
	 * Based on your tests, please set this value in Constant.java. 
	 * "0" will nullify implicitlyWait and speed up a test. */ 
	public static final int DEFAULT_WAIT_4_PAGE = Constants.DEFAULT_WAIT_4_PAGE;




	@Given("^I wait for the page to load$")
	public void I_wait_for_the_page_to_load() throws Throwable {
	//throw new PendingException();
	
	}

	

	/**
	  * Wait for the element to be present in the DOM, and displayed on the page. 
	  * And returns the first WebElement using the given method.
	  * 
	  * @param identifier	selector to find the element
	  * @return
	  */
	@Then("^I wait for visibility of element \"(.*)\"$")
	public void I_wait_for_visibility_of_element(String identifier) throws Throwable {
		WebElement element; 
		try{	
			//To use WebDriverWait(), we would have to nullify implicitlyWait(). 
			//Because implicitlyWait time also set "driver.findElement()" wait time.  
			//info from: https://groups.google.com/forum/?fromgroups=#!topic/selenium-users/6VO_7IXylgY
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 

			WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT_4_TIMEOUT); 
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(wd.getIdentifierAt(OR.getProperty(identifier))));
			wait.until(ExpectedConditions.visibilityOfElementLocated(wd.getIdentifierAt(OR.getProperty(identifier))));
			driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS); //reset implicitlyWait
			Assert.assertTrue(element.getSize().height!=0); //return the element	
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	
	}

	@And("^I wait for presence of element \"(.*)\"$")
	public void I_wait_for_presence_of_element(String params1) throws Throwable {
	//throw new PendingException();
	
	}
	
	@And("^I wait for (\\d+) seconds$")
	public void I_wait_for_seconds(int waittime) throws Throwable {
		System.out.println("waiting for "+waittime +"seconds");
		Long wait=(long) (waittime*1000);
	Thread.sleep(wait);
	}

	@And("^I wait for element \"(.*)\" to be clickable$")
	public void I_wait_for_element_to_be_clickable(String params1) throws Throwable {
	//throw new PendingException();
	
	}

	@And("^I wait for element \"(.*)\" to appear on the refreshed web-page$")
	public void I_wait_for_element_to_appear_on_the_refreshed_web_page(String params1) throws Throwable {
	//throw new PendingException();
	
	}

	/**
	  * Wait for the Text to be present in the given element, regardless of being displayed or not.
	  *
	  * @param identifer	selector of the given element, which should contain the text
	  * @param expected_Text	The text we are looking
	  * 
	  * @return test steps would be marked as fail if expected text is not found for the given identifier with in defined explicit wait time
	  */
	@And("^I Wait for the Text \"(.*)\" to be present in the element \"(.*)\"$")
	public void I_Wait_for_the_Text_to_be_present_in_the_element(final String expected_Text,final String identifer) throws Throwable {
		boolean isPresent = false; 
		try{	
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 
	        new WebDriverWait(driver, Constants.EXPLICIT_WAIT_4_TIMEOUT) {
	        }.until(new ExpectedCondition<Boolean>() {

	            @Override
	            public Boolean apply(WebDriver driverObject) {
	            	return isTextPresent(wd.getIdentifierAt(OR.getProperty(identifer)), expected_Text); //is the Text in the DOM
	            }
	        });
	        isPresent = isTextPresent(wd.getIdentifierAt(OR.getProperty(identifer)), expected_Text);
			driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS); //reset implicitlyWait
			Assert.assertTrue(isPresent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	/** 
	 * Waits for the Condition of JavaScript.  
	 *
	 *
	 * @param String	The javaScript condition we are waiting. e.g. "return (xmlhttp.readyState >= 2 && xmlhttp.status == 200)" 
	 * @return test steps would be marked as fail if jquery condition does not get true with in the explicit wait time
	 **/
	@And("^I Wait for the JavaScript condition \"(.*)\" to be true$")
	public void I_Wait_for_the_JavaScript_condition_to_be_true(final String jsCode) throws Throwable {
		boolean jscondition = false; 
		try{	
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 
	        new WebDriverWait(driver, Constants.EXPLICIT_WAIT_4_TIMEOUT) {
	        }.until(new ExpectedCondition<Boolean>() {

	            @Override
	            public Boolean apply(WebDriver driverObject) {
	            	return (Boolean) ((JavascriptExecutor) driverObject).executeScript(jsCode);
	            }
	        });
	        jscondition =  (Boolean) ((JavascriptExecutor) driver).executeScript(jsCode); 
			driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS); //reset implicitlyWait
			Assert.assertTrue(jscondition);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	
	/** Waits for the completion of Ajax jQuery processing by checking "return jQuery.active == 0" condition.  
	 *
	 * 
	 * @return test steps would be marked as fail if jquery condition does not get true with in the explicit wait time
	 * */
	@And("^I Wait for the completion of Ajax jQuery processing by checking return jQuery.active == 0 condition$")
	public void I_Wait_for_the_completion_of_Ajax_jQuery_processing_by_checking_return_jQuery_active_condition() throws Throwable {
		boolean jQcondition = false; 
		try{	
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 
	        new WebDriverWait(driver, Constants.EXPLICIT_WAIT_4_TIMEOUT) {
	        }.until(new ExpectedCondition<Boolean>() {

	            @Override
	            public Boolean apply(WebDriver driver) {
	            	return (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
	            }
	        });
	        jQcondition = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
			driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS); //reset implicitlyWait
			//return jQcondition;
			Assert.assertTrue(jQcondition);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		} 
	
	}

	
	/**
	 * Coming to implicit wait, If you have set it once then you would have to explicitly set it to zero to nullify it -
	 */
	@And("^I reset the implicit wait to zero$")
	public void I_set_the_implicit_wait_to_zero() throws Throwable {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 
	
	}

	/** 
	 * To reset ImplicitWait time you would have to explicitly 
	 * set it to zero to nullify it before setting it with a default time value. 
	 */
	@And("^I reset the implicit wait to default implicit wait$")
	public void I_set_the_implicit_wait_to_default_implicit_wait() throws Throwable {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 
		driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS); //reset implicitlyWait
	
	}

	@And("^I reset the implicit wait to (\\d+)$")
	public void I_set_the_implicit_wait_to_6(int param1) throws Throwable {
	//throw new PendingException();
		System.out.println("implicit wait is set");
	
	}

	
	
	




	/**
	  * Wait for the element to be present in the DOM, regardless of being displayed or not.
	  * And returns the first WebElement using the given method.
	  *
	  * @param WebDriver	The driver object to be used 
	  * @param By	selector to find the element
	  * @param int	The time in seconds to wait until returning a failure
	  * 
	  * @return WebElement	the first WebElement using the given method, or null (if the timeout is reached)
	  */
	public WebElement waitForElementPresent(final By by, int timeOutInSeconds) {
		WebElement element; 
		try{
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 

			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds); 
			element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

			driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS); //reset implicitlyWait
			return element; //return the element
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null; 
	}


	/**
	  * Wait for the List<WebElement> to be present in the DOM, regardless of being displayed or not.
	  * Returns all elements within the current page DOM. 
	  * 
	  * @param WebDriver	The driver object to be used 
	  * @param By	selector to find the element
	  * @param int	The time in seconds to wait until returning a failure
	  *
	  * @return List<WebElement> all elements within the current page DOM, or null (if the timeout is reached)
	  */
	public List<WebElement> waitForListElementsPresent(final By by, int timeOutInSeconds) {
		List<WebElement> elements; 
		try{	
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 

			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds); 
			wait.until((new ExpectedCondition<Boolean>() {
	            @Override
	            public Boolean apply(WebDriver driverObject) {
	                return areElementsPresent(by);
	            }
	        }));

			elements = driver.findElements(by); 
			driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS); //reset implicitlyWait
			return elements; //return the element	
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null; 
	}

	/**
	  * Wait for an element to appear on the refreshed web-page.
	  * And returns the first WebElement using the given method.
	  *
	  * This method is to deal with dynamic pages.
	  * 
	  * Some sites I (Mark) have tested have required a page refresh to add additional elements to the DOM.  
	  * Generally you (Chon) wouldn't need to do this in a typical AJAX scenario.
	  * 
	  * @param WebDriver	The driver object to use to perform this element search
	  * @param locator	selector to find the element
	  * @param int	The time in seconds to wait until returning a failure
	  * 
	  * @return WebElement	the first WebElement using the given method, or null(if the timeout is reached)
	  * 
	  * @author Mark Collin 
	  */
	 public WebElement waitForElementRefresh(final By by, 
			                           int timeOutInSeconds) {
		WebElement element; 
		try{	
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 
		        new WebDriverWait(driver, timeOutInSeconds) {
		        }.until(new ExpectedCondition<Boolean>() {

		            @Override
		            public Boolean apply(WebDriver driverObject) {
		                driverObject.navigate().refresh(); //refresh the page ****************
		                return isElementPresentAndDisplay(by);
		            }
		        });
		    element = driver.findElement(by);
			driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS); //reset implicitlyWait
			return element; //return the element
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null; 
	 }

	

	/**
	 * Set driver implicitlyWait() time. 
	 */
	public void setImplicitWait(int waitTime_InSeconds) {
		driver.manage().timeouts().implicitlyWait(waitTime_InSeconds, TimeUnit.SECONDS);  
	} 


	/**
	 * Reset ImplicitWait.  
	 * @param int - a new wait time in seconds
	 */
	public void resetImplicitWait(int newWaittime_InSeconds) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 
		driver.manage().timeouts().implicitlyWait(newWaittime_InSeconds, TimeUnit.SECONDS); //reset implicitlyWait
	} 


     /**
	   * Checks if the text is present in the element. 
       * 
	   * @param driver - The driver object to use to perform this element search
	   * @param by - selector to find the element that should contain text
	   * @param text - The Text element you are looking for
	   * @return true or false
	   */
	private boolean isTextPresent(By by, String text)
	{
		try {
				return driver.findElement(by).getText().contains(text);
		} catch (NullPointerException e) {
				return false;
		}
	}


	/**
	 * Checks if the elment is in the DOM, regardless of being displayed or not.
	 * 
	 * @param driver - The driver object to use to perform this element search
	 * @param by - selector to find the element
	 * @return boolean
	 */
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);//if it does not find the element throw NoSuchElementException, which calls "catch(Exception)" and returns false; 
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}


	/**
	 * Checks if the List<WebElement> are in the DOM, regardless of being displayed or not.
	 * 
	 * @param driver - The driver object to use to perform this element search
	 * @param by - selector to find the element
	 * @return boolean
	 */
	private boolean areElementsPresent(By by) {
		try {
			driver.findElements(by); 
			return true; 
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Checks if the elment is in the DOM and displayed. 
	 * 
	 * @param driver - The driver object to use to perform this element search
	 * @param by - selector to find the element
	 * @return boolean
	 */
	private boolean isElementPresentAndDisplay(By by) {
		try {			
			return driver.findElement(by).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}


}