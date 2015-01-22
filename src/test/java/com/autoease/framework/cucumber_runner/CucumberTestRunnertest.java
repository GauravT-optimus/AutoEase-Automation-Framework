package com.autoease.framework.cucumber_runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.autoease.framework.WebDriverGenerator;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:Test Scenario/",
		tags={"@Test"},
		glue ={"com.autoease.framework.customizedsteps","com.autoease.framework.MobileAppSteps","com.autoease.framework.WebAppSteps"},
		monochrome = true, 
		format={"pretty",
				"json:target/cucumber-json-report/cucumber.json",
				"html:target/cucumber-html-report",
				"rerun:target/cucumber-failed-report/faile,d-rerun.txt"}
		)

//,tags={"@groupTest"}
public class CucumberTestRunnertest {
	
	@BeforeClass
	public static void setUp(){
		System.out.println("*******************************************************************");
		System.out.println("*****************************Welcome to****************************");
		System.out.println("************************Auto-Ease Framework************************");
		System.out.println("*******************************************************************");
	}

	
	@AfterClass
	public static void shutDown(){
		
		WebDriverGenerator.getWebDriver().quit();
		//System.out.println("\n\n\nThanks for using the AutoEase framework, We hope you like it, please send your feedback on gauravtiwari91@yahoo.com");
		System.out.println("*******************************************************************");
		System.out.println("*****************************Thank You*****************************");
		System.out.println("************************For using the Framework********************");
		System.out.println("*************************Send your feedback on*********************");
		System.out.println("***********************GAURAVTIWARI91@YAHOO.COM********************");
		System.out.println("*******************************************************************");
	
	}
}
