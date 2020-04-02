Feature: Assign role

  Scenario: Assigning Role and Checking assignment
    Given User is navigate to "Role_Select" screen of IFRS
    Then Assign "AuditManager" role for assigned user
