Feature: User registration
  As a visitor
  I want to register an account
  So that I can access the platform securely

  Background:
    Given the actor opens the login page
    When the actor navigates to the registration page

  Scenario Outline: CP-001 Successful registration with valid data
    When the actor registers with a unique valid user
    Then the actor should be redirected to "<expectedPath>"

    Examples:
      | expectedPath |
      | /dashboard   |

  Scenario Outline: CP-002 / CP-003-a / CP-005 Registration is rejected for invalid data combinations
    When the actor attempts to register with name "<name>", email "<email>" and password "<password>"
    Then the registration <field> field should have validation state "<state>"

    Examples:
      | name         | email                     | password  | field    | state        |
      | QA User      | usuariohogar.com          | Abcd1234! | email    | typeMismatch |
      | Boundary User| short-pass-1@testmail.com | Abcd123   | password | tooShort     |
      | Boundary User| short-pass-2@testmail.com | 1234567   | password | tooShort     |
      |              | empty-name@testmail.com   | Abcd1234! | name     | valueMissing |
      | Empty Email  |                           | Abcd1234! | email    | valueMissing |
      | Empty Pass   | empty-pass@testmail.com   |           | password | valueMissing |

  Scenario Outline: CP-003-b Password at minimum boundary is accepted (8 characters)
    When the actor attempts to register with name "<name>", email "<email>" and password "<password>"
    Then the actor should be redirected to "<expectedPath>"

    Examples:
      | name          | email                            | password | expectedPath |
      | Boundary User | boundary-valid-pass@testmail.com | Abcd1234 | /dashboard   |

  Scenario Outline: CP-004 Registration is rejected for duplicated email
    Given an existing registered user with email "<email>" and password "<password>"
    When the actor navigates to the registration page
    And the actor attempts to register with name "<name>", email "<email>" and password "<password>"
    Then the actor should see the message "<message>"

    Examples:
      | name           | email                    | password  | message                     |
      | Duplicate User | duplicate.user@testmail.com | Abcd1234! | El email ya está registrado |
