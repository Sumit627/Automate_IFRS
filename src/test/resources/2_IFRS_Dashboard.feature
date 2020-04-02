Feature: IFRS dashboard

  Scenario Outline: Creating workpaper
    Given User is navigate to "IFRS_Application" screen of IFRS
    Then click on "Engagement" button
    Then Type engagement number "3000"
    Then verify the search result
    Then click on the search engagement
    Then capture the status and the data
    Then Navigate to the dashboard
    Then verify that Workpaper count is increased by one

  Scenario Outline: Workpaper status check and filter out based on status
    Given User is navigate to "IFRS_Application" screen of IFRS
    Then Verify that Four status label is displayed in dashbaord
      | Status              |
      | Draft               |
      | Return for Revision |
      | Pending Approval    |
      | Approved            |
    Then Verify status based filter"Draft" of workpaper is displayed correctly
    Then Verify status based filter"Return for Revision" of workpaper is displayed correctly
    Then Verify status based filter"Pending Approval" of workpaper is displayed correctly
    Then Verify status based filter"Approved" of workpaper is displayed correctly

  Scenario Outline: Workpaper table column check
    Given User is navigate to "IFRS_Application" screen of IFRS
    Then Verify that following column is displayed Workpaper table
      | Column              |
      | Status              |
      | Engagement Number   |
      | Engagement Name     |
      | Client              |
      | Created by          |
      | Modified            |
      | Pending Approval by |
      | Team                |

  Scenario Outline: Sorting of column data
    Given User is navigate to "IFRS_Application" screen of IFRS
    Then Verify that Sorting functionality is working fine for "Engagement Number" column
    Then Verify that Sorting functionality is working fine for "Engagement Name" column
    Then Verify that Sorting functionality is working fine for "Client" column
    Then Verify that Sorting functionality is working fine for "Created by" column
    Then Verify that Sorting functionality is working fine for "Modified" column
    Then Verify that Sorting functionality is working fine for "Pending Approval by" column
    Then Verify that Sorting functionality is working fine for "Team" column

  Scenario Outline: validating workpaper from database
    Given User is navigate to "IFRS_Application" screen of IFRS
    Then Verify that Sorting functionality is working fine for "Engagement Number" column
    Then Capture Application workpaper table
    Then Capture Database query result "Query1"
    Then Validate the result for column "Status","Engagement Number","Engagement Name","Client","Created by","Modified"
    
 Scenario Outline: Capture workpaperID from URL and changing the query
 		Given User is navigate to "Role_Select" screen of IFRS
    Then Assign "AuditRegionalApprover" role for assigned user
    Given User is navigate to "IFRS_Application" screen of IFRS
    Then Navigate to "2002" "Engagement Number"
    Then capture the Workpaper ID from pagesource
    Then Update the query"Query2" based on captured "Role"
    Then Capture Database query result "Updated_Query"
    Then Validate the result for column "Engagement Number","Status","Engagement Name","Client"
    
  Scenario: Validating Workpaper row from databasewith specific role
 		Given User is navigate to "Role_Select" screen of IFRS
    Then Assign "AuditRegionalApprover" role for assigned user
    Given User is navigate to "IFRS_Application" screen of IFRS
    Then Capture Application workpaper table
    Then Update the query"Query2" based on captured "Role"
    Then Capture Database query result "Updated_Query"
    Then Validate the result for column "Engagement Number","Status","Engagement Name","Client"
