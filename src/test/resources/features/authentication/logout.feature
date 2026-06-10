Feature: User logout
  As an authenticated user
  I want to log out
  So that my session is closed safely

  Background:
    Given an existing registered user with email "qa.logout.user@testmail.com" and password "Abcd1234!"
    And the actor opens the login page
    And the actor logs in with email "qa.logout.user@testmail.com" and password "Abcd1234!"

  Scenario Outline: CP-009 Successful logout redirects to login
    When the actor logs out from the dashboard
    Then the actor should be redirected to "<expectedPath>"

    Examples:
      | expectedPath |
      | /login       |
