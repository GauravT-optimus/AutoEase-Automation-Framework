package com.autoease.framework.WebAppSteps;

import junit.framework.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.autoease.framework.WebDriverGenerator;
import cucumber.api.java.en.When;

public class BrowserRelated {
	WebDriver driver =WebDriverGenerator.getWebDriver();
	WebDriverWait wait=WebDriverGenerator.getWaitObject();

		@SuppressWarnings("deprecation")
		
		@When("^I open the URL \"(.*)\"$")
		public void I_open_the_URL(String URL){
			try{
				System.out.println(URL);
				//assert that URL has the protocol in itself or not
				Assert.assertTrue("URL does not contain http",URL.contains("http"));
				driver.get(URL);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		
		}
}