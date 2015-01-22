package com.autoease.framework.WebAppSteps;

import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.autoease.framework.GlobalConstants;
import com.autoease.framework.WebDriverGenerator;

import cucumber.api.java.en.Then;
/*
 * Frame and Window handler related steps, this class has all the steps which can be utilized for all frames and windows steps
 */
public class FrameAndWindowSteps {
		WebDriver driver = WebDriverGenerator.getWebDriver();
		WebDriverGenerator gen;
		
		public static Set<String> windowHandles;
		public static String firstWindowHandle;
		public static String secondWindowHandle;
		public static String thirdWindowHandle;
		public static String fourthWindowHandle;
		
		//method to wait for window to open, it will be called internally
		 public boolean I_wait_for_number_of_windows_tobe(final int numberOfWindows)
		    {
			 try{
				 
		        new WebDriverWait(driver,GlobalConstants.WINDOW_LOAD_TIME){}.until(new ExpectedCondition<Boolean>() 
	            		{
	            @Override public Boolean apply(WebDriver driver) 
	                          {                        
	                return (driver.getWindowHandles().size() == numberOfWindows);
	                          }
	                    });
		        return true;
			 }catch(Exception e){
				 return false;
		        }
			 
		    }
	
	/*
	 * Window switches related steps
	 */
		@Then("^I switch to the second window$")
		public void I_switch_to_the_second_window() throws Throwable {
			
			try{
			if (I_wait_for_number_of_windows_tobe(2))//wait till two windows are open
			{
				windowHandles = driver.getWindowHandles();
			     firstWindowHandle = driver.getWindowHandle();
			     	windowHandles.remove(firstWindowHandle);
			     	
						String windowHandle=windowHandles.iterator().next();
	        	if (windowHandle!=firstWindowHandle){//firstwinhandle is a string variable, already defined as public
	        		secondWindowHandle=windowHandle;//Storing handle of second window handle

	        		driver.switchTo().window(secondWindowHandle);
	        	}
			}
			else
				Assert.fail("Waiting time for appearing the second window is out");
			}catch(Exception e){
				Assert.fail("not able to switch to second window-"+e.getMessage());
			}
		}

		
		
		@Then("^I switch back to previous window$")
		public void I_switch_back_to_previous_window() throws Throwable {
		//throw new PendingException();
		
		}

		
		@Then("^I switch to third window$")
		public void I_switch_to_third_window() throws Throwable {
		//throw new PendingException();
		
		}

		
		@Then("^I switch to fourth window$")
		public void I_switch_to_fourth_window() throws Throwable {
		//throw new PendingException();
		
		}
		
		/*
		 * Frame switches related steps
		 */

		@Then("^I switch to first frame$")
		public void I_switch_to_first_frame() throws Throwable {
		driver.switchTo().frame(0);
		
		}

		@Then("^I switch back to default content$")
		public void I_switch_back_to_default_content() throws Throwable {
		driver.switchTo().defaultContent();
		
		}

		@Then("^I switch to frame having identifier \"(.*)\"$")
		public void I_switch_to_frame_having_identifier(String identifier) throws Throwable {
			driver.switchTo().frame(driver.findElement(gen.getIdentifierAt(identifier)));
		
		}

}