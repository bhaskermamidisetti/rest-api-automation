  @restapi
Feature: API Object Management

  Scenario: Create and verify an item
    Given I create an item with name "Apple MacBook Pro 16", cpu "Intel Core i9", price "1849.99", year "2019"
    When I send the create request
    Then the response code should be 200
    And the response should contain name "Apple MacBook Pro 16"

  Scenario: Retrieve correct item details when a valid ID is provided
    Given An item id which is already created in the system
    When I submit the request with item id
    Then the get response code should be 200
    And the response should contain the correct item details including the provided item id

  Scenario: Retrieve all item details
    Given Items already created in the system
    When I submit the request to retrieve all items
    Then the get all items response code should be 200
    And the response should contain the all items

  Scenario: Delete an item by newly created item id
    Given I create an item with name "Apple MacBook Pro 15", cpu "Intel Core i8", price "1817.99", year "2017"
    When I send the create request
    And I submit the delete request to delete an item
    Then the delete item response code should be 200
    And the item should be deleted

