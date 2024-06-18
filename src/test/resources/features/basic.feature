
Feature: Basic functionality

  Scenario: Open browser, navigate to URL, and print success
    Given User opens the browser and navigates to the URL
    Then User prints success

  Scenario: open terminal, print a message, and print success
    Given User opens the terminal and prints a message
    Then User prints success message