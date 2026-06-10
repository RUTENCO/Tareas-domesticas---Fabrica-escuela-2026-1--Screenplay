Feature: User login
  As a registered user
  I want to sign in
  So that I can access my dashboard

  Background:
    Given an existing registered user with email "qa.login.user@testmail.com" and password "Abcd1234!"
    And the actor opens the login page

  Scenario Outline: CP-006 Successful login with valid credentials
    When the actor logs in with email "<email>" and password "<password>"
    Then the actor should be redirected to "<expectedPath>"

    Examples:
      | email                      | password  | expectedPath |
      | qa.login.user@testmail.com | Abcd1234! | /dashboard   |

  Scenario Outline: CP-007 Login fails with wrong credentials
    When the actor logs in with email "<email>" and password "<password>"
    Then the actor should see the message "<message>"

    Examples:
      | email                      | password   | message                           |
      | qa.login.user@testmail.com | WrongPass1!| Correo o contraseña incorrectos   |

  Scenario Outline: CP-008 Login is rejected for invalid or empty inputs
    When the actor logs in with email "<email>" and password "<password>"
    Then the login <field> field should have validation state "<state>"

    Examples:
      | email                      | password  | field    | state        |
      | invalid-format-email       | Abcd1234! | email    | typeMismatch |
      |                            | Abcd1234! | email    | valueMissing |
      | qa.login.user@testmail.com |           | password | valueMissing |
