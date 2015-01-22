Feature: This feature file is for demo
  	 
  	 
@groupTest
  Scenario: sample scenario title,you can put any title for it
  	 Given I open the URL "://www.rediff.com"
  	 Then I type "gaurav" in field identifier "Email"
  	 
@Login
Scenario: Sample login scenario
     When I open the URL "http://www.gmail.com"
     Then I type "smit" in field identifier "loginusernameID"
  	 
Scenario: sample wait steps
		Given I wait for the page to load
		Then I wait for visibility of element ""
		And I wait for presence of element ""
		And I wait for element "" to be clickable
		And I wait for element "" to appear on the refreshed web-page
	    And I Wait for the Text "" to be present in the element ""
	    And I Wait for the JavaScript condition "javaScript" to be true
	    And I Wait for the completion of Ajax jQuery processing by checking return jQuery.active == 0 condition
	    And I reset the implicit wait to zero
	    And I reset the implicit wait to default implicit wait
	    And I reset the implicit wait to 6
	    
	    

	    
	    
  	 
  	 
  	 		