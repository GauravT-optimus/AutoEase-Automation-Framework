package com.autoease.framework.customizedsteps;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import com.autoease.framework.WebDriverGenerator;
import com.autoease.framework.WebAppSteps.Reusable;

import cucumber.api.java.en.Given;

public class LoginSteps {

	//WebDriver driver =WebDriverGenerator.getWebDriver();
	//static Logger log = Logger.getLogger(LoginSteps.class.getName());
	
	
	// common class object for accessiing all the web specific methods 
	Reusable reuse=new Reusable();


		@Given("^I am on the Login page$")
		public void I_am_on_the_Login_page() {
				//Reusing the pre-implemented methods of the 
				reuse.br.I_open_the_URL("http://www.gmail.com");
				reuse.tf.I_type_in_field_identifier("gaurav", "Email");
				reuse.tf.I_type_in_field_identifier("password", "Pass");
				reuse.tf.I_submit_the_form("gaia_loginform");
		}

}