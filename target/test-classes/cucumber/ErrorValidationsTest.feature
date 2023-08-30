@Tag
Feature: This feature is to validate the error messages in the Ecommerce website
 
  @ErrorValidation
  Scenario Outline: To check the error of the Ecommerce page while logging
    When I launch the Ecommerce Page
    Given I logged in to the website with username <userName> and password <password>
    And "Incorrect email or password." error message is displayed
    
  Examples: 
      | userName          |   password     | 
      | sample@sample.com |   Sample@12356 | 