@login
Feature: End to End Tests for Login Page
  Description: The Purpose of these tests are to cover end to end happy flows for Login functionality.

  Scenario Outline: Verify the login Functionality
    Given the user is on the landing page of URl "https://demo.nopcommerce.com"
    When the existing user inputs the username "<username>" and password "<password>"
    Then login functionality should happen based on the provides credentials

    Examples:
    |username                     |password|
#    |arem.hemalatha@gmail.com     |autotest|
    |invalidusername@gmail.com    |autotest|
    |arem.hemalatha@gmail.com     |invalidPwd|
#    |                             |          |

    @forgotPassword
  Scenario Outline:    Verify the forgot password Functionality on Landing Page
      Given the user is on the landing page of URl "https://demo.nopcommerce.com"
      When the existing user clicks on forgot password link
      Then the user should be able to  successfully recover his password for the email "<email>"
      And the user should be able to login successfully with the new password "<newPassword>"

Examples:
      |email                   | newPassword  |
      |arem.hemalatha@gmail.com| autotest     |

  Scenario Outline: Verify the Remember me Functionality on Landing page
    Given the user is on the landing page of URl "https://demo.nopcommerce.com"
    When the existing user inputs the username "<username>" and password "<password>"
    And the user ticks on Remember me checkbox and proceed to login
    Then the user should be able to retrieve his credentials for the next login session
    Examples:
      |username                 |password|
      |arem.hemalatha@gmail.com |autotest|

    Scenario Outline: Verify the Post-Login scenario
      Given the user is on the landing page of URl "https://demo.nopcommerce.com"
      When the existing user inputs the username "<username>" and password "<password>"
      Then the user should be able to login successfully with correct credentials
      And the user should be able to see the post login information
      When the user clicks on logout button
      Then user should be able to logout successfully.

      Examples:
        |username                 |password|
        |arem.hemalatha@gmail.com |autotest|




