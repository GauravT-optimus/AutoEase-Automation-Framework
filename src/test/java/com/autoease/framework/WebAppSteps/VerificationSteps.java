package com.autoease.framework.WebAppSteps;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.WebDriver;

import com.autoease.framework.WebDriverGenerator;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;

public class VerificationSteps {
	WebDriver driver = WebDriverGenerator.getWebDriver();
	WebDriverGenerator gen;
	 @Rule
	 public ErrorCollector collector = new ErrorCollector();
//	 
//	    @Test
//	    public void myTest() {
//	        collector.checkThat("a", equals("b"));
//	        collector.checkThat(1, equals(2));
//	    }
//	    
//	    
//		@Then("^I should see text \"(.*)\" on element \"(.*)\"$")
//		public void I_should_see_text_on_element(String expected_Text,String identifier) throws Throwable {
//		
//			collector.checkThat(expected_Text, equalTo(gen.getIdentifierAt(identifier)));
//		
//		}


	 
}