package com.autoease.framework.MobileAppSteps;

import junit.framework.Assert;

import org.openqa.selenium.WebDriver;

import com.autoease.framework.WebDriverGenerator;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GestureSteps {

	WebDriver driver =WebDriverGenerator.getWebDriver();
	
	@Then("^I deselect \"(.*)\" from dropdown identifier \"(.*)\"$")
	public void I_deselect_from_dropdown_identifier(String params1,String params2) throws Throwable {
		//throw new PendingException();
		//Assert.assertFalse(true);

	}

	@When("^I select \"(.*)\" from dropdown identifier \"(.*)\"$")
	public void I_select_from_dropdown_identifier(String params1,String params2) throws Throwable {
		//throw new PendingException();

	}
}
