package com.autoease.framework.WebAppSteps;

import static com.autoease.framework.WebDriverGenerator.CONFIG;
import static com.autoease.framework.WebDriverGenerator.OR;

import java.util.Properties;

import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.autoease.framework.WebDriverGenerator;
import com.autoease.framework.utilities.screenshotUtilities;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;

public class TypeAndClickSteps {
	
	WebDriver driver =WebDriverGenerator.getWebDriver();
	WebDriverWait wait=WebDriverGenerator.getWaitObject();
	WebDriverGenerator gen=new WebDriverGenerator();

	/**
	 * Method to highLight a webElement.
	 * @param e WebElement
	 */
	public void highLight(WebElement e) {
		if (driver instanceof JavascriptExecutor) {
			executeJS("arguments[0].style.border='3px solid red'",e);
		}
	}
	
	
	/**
	 * executeJS:(execute the java script in this page). 

	
	
	 * @param script  --the java script we need to execute
	 * @since JDK 1.6
	 * @return Object
	 */
	public Object executeJS(String script) {
		//logger.info("Run the javascript from page ,the java script is:"
			//	+ script);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		return je.executeScript(script);

	}
	/**
	 * Method executeJS.
	 * @param script String
	 * @param e WebElement
	 */
	public void executeJS(String script,WebElement e) {
		//logger.info("Run the javascript from page ,the java script is:"
			//	+ script);
		//highLight(e);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript(script,e);

	}
	
	
	
	
		@Then("^I click on element identifier \"(.*)\"$")
		public void I_click_on_element_identifier(String identifier) {
			try{
				//System.out.println(OR.getProperty(params1));
				driver.findElement(gen.getIdentifierAt(identifier)).click();
			}
			catch(NoSuchElementException e){
				System.out.println(e.getMessage() + "Given Identifier is not found on the page, either the identifier is invalid or changed");
			}
		
		}
		
		@Then("^I click using javascript on element identifier \"(.*)\"$")
		public void I_click_using_javascript_on_element_identifier(String params1) {
		
		
		}

		@Then("^I click using actions on element identifier \"(.*)\"$")
		public void I_click_using_actions_class_on_element_identifier(String params1) throws Throwable {
		//throw new PendingException();
		
		}

		@Then("^I type \"(.*)\" in field identifier \"(.*)\"$")
		public void I_type_in_field_identifier(String text_to_type,String identifier) {
			try{	
				WebElement e=driver.findElement(gen.getIdentifierAt(identifier));
				highLight(e);
				e.clear();
				e.sendKeys(text_to_type);
			}
				catch(Exception e){
					System.out.println("Identifier is not found on the page, either the identifier is invalid or changed");
					Assert.fail(e.getMessage());
				
			}
		
		}

		@Then("^I clear the field identifier \"(.*)\"$")
		public void I_clear_the_field_identifier(String identifier){
			try{
				WebElement e=driver.findElement(gen.getIdentifierAt(OR.getProperty(identifier)));
				highLight(e);
				e.clear();
			} catch (Exception e) {
				//Assert.assertFalse(true);
				Assert.fail(e.getMessage());
			} 
		
		}

		@Then("^I type \"(.*)\" without clearing the field identifier \"(.*)\"$")
		public void I_type_without_clearing_the_field_identifier(String text_to_type,String identifier) {
			try{	
				driver.findElement(By.id(identifier)).sendKeys(text_to_type);
			} catch (Exception e) {
				//Assert.assertFalse(true);
				Assert.fail(e.getMessage());
			} 
		
		
		}
		
		@Then("^I submit the form \"(.*)\"$")
		public void I_submit_the_form(String identifier) {
			try{	
				WebElement e=driver.findElement(gen.getIdentifierAt(identifier));
				highLight(e); //highlightng the form
				e.submit();
			} catch (Exception e) {
				//Assert.assertFalse(true);
				Assert.fail(e.getMessage());
			} 
		
		}
		

		@Then("^I type \"(.*)\" using javascript in field identifier \"(.*)\"$")
		public void I_type_using_javascript_in_field_identifier(String params1,String params2) throws Throwable {
		//throw new PendingException();
		
		}
		
}