Feature: Password recovery
  As a user who forgot my password
  I want to request a reset link
  So that I can recover access securely

  Background:
    Given the actor opens the login page
    When the actor navigates to the forgot password page

  Scenario Outline: CP-010 Successful password recovery request for a registered email
    Given an existing registered user with email "<email>" and password "<password>"
    And the actor navigates to the forgot password page
    When the actor requests password recovery for email "<email>"
    Then the actor should see the message "<message>"

    Examples:
      | email                        | password  | message                                                                |
      | qa.recovery.user@testmail.com| Abcd1234! | Se ha enviado un enlace a tu correo para restablecer la contraseña    |

  Scenario Outline: CP-011 / CP-012 / CP-013 Recovery is rejected for invalid inputs
    When the actor requests password recovery for email "<email>"
    Then the recovery outcome should show message "<message>" and validation state "<validationState>"

    Examples:
      | email                          | message                                            | validationState |
      | not.registered.user@testmail.com | No se encontró una cuenta con ese correo           | N/A             |
      | usuariohogar.com               | N/A                                                | typeMismatch    |
      |                                | N/A                                                | valueMissing    |
