Feature: SimCard Activator

  Scenario: Working SIM cards activates successfully
    Given a working SIM Card
    When  a request to activate a SIM card is submitted
    Then the sim card activates and its state is saved to the database
  Scenario: Not working SIM cards fail to activate
    Given a not working SIM Card
    When a request to activate a SIM card is submitted
    Then the sim card fails to activate and its state is saved to the database

