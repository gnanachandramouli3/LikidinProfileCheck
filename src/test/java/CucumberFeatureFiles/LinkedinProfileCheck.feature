Feature: Linkedin profile check on open to work field
 

  @OpenToWork
  Scenario: Linkedin profile check on open to work field using excel 
    Given Intial set up for browser
    And Log in to site "first"
    When User get the profile links and checks status and writes info to excel
    And Tear Down
   

 