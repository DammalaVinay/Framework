
@tag
Feature: This feature is to purchase the order from the Ecommerce Website
I want to use this template for my feature file

Background:
	When I launch the Ecommerce Page

  @Regression
  Scenario Outline: Positive test for submitting the Order
    Given I logged in to the website with username <userName> and password <password>
    When I add product <productName> to the cart
    And checkout product <productName> and submit the order
    Then "THANKYOU FOR THE ORDER>" message is displayed on the screen

    Examples: 
      | userName          |   password   | productName  |
      | sample@sample.com |   Sample@123 | ZARA COAT 3  |
     
