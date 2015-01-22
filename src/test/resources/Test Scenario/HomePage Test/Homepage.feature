Feature: Second feature
  	 
  	 
@Test
  Scenario: second sample scenario
  	 Given I open the URL "http://www.gmail.com"
  	 #And I wait for 5 seconds
  	 Then I type "gaurav" in field identifier "loginusernameID"
  	 Then I type "password" in field identifier "loginpasswordName"
  	 And I submit the form "GmailLoginFormID"
  	 
  	 
@groupTest
  Scenario: second sample scenario
  	 Given I open the URL "http://www.yahoo.com"
  	 

@VerifySteps
	Scenario: verification scenarios
		Then I should see text "" on element ""
		Then I should see text containing ""on element ""
		Then I should see text starts with "" on element ""
		Then I should see text ends with "" on element ""
		
  	 
  	 		