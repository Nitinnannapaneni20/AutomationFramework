Feature: Basic functionality

  Scenario: Open browser, navigate to URL, and print success
    Given User opens the browser and navigates to the URL
    Then User prints success

  Scenario: open terminal, print a message, and print success
    Given User opens the terminal and prints a message
    Then User prints success message

  Scenario Outline: Navigate to Contact Us page, fill out form, and capture confirmation message
    Given User opens the browser and navigates to the URL
    Given User navigates to the Contact Us page
    Given User fills out the contact form with first name "<firstName>", last name "<lastName>", email "<email>", phone number "<phoneNumber>", and message "<message>"
    Then User submits the form and captures the confirmation message

    Examples:
      | firstName | lastName | email                  | phoneNumber | message                 |
      | John      | Doe      | john.doe@example.com   | 1234567890  | This is a test message. |
      | Jane      | Smith    | jane.smith@example.com | 0987654321  | Another test message.   |
