@apitesting
Feature: End to End Tests for provided  Get Endpoints
  Description: The Purpose of these tests are to cover end to end happy flows for endpoints.

  Scenario Outline: Covering Automation tests for provided 3 Get Endpoints
    Given The required information of api's are provided
    When User hits the above uri with resource "<resource>"
    Then User should get the expected response from the api's and should pass all the validations

    Examples:
    |resource |
    |ping     |
    |slow     |
    |error    |