package com.restapi.automation.test.stepdefinations;

import com.restapi.automation.test.service.AbstractSteps;
import com.restapi.automation.test.service.ItemApi;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static com.restapi.automation.test.service.Constants.ITEM_ID;
import static com.restapi.automation.test.service.Constants.RESPONSE;
import static org.junit.jupiter.api.Assertions.*;

public class ItemSteps extends AbstractSteps {
    private Response response;
    private final ItemApi itemApi;
    private Item item;

    public ItemSteps() {
        itemApi = new ItemApi();
    }

    @Before
    public void beforeEachScenario() {
        super.beforeEachScenario();
    }

    @Given("I create an item with name {string}, cpu {string}, price {string}, year {string}")
    public void createItem(String name, String cpu, String price, String year) {
        item = new Item();
        item.setCpuModel(cpu);
        item.setName(name);
        item.setPrice(Double.valueOf(price));
        item.setYear(Integer.parseInt(year));
    }

    @When("I send the create request")
    public void iSendTheCreateRequest() {
        response = itemApi.createItem(item);
        response
                .then()
                .log().all()
                .statusCode(200);

        String itemId = response.jsonPath().getString("id");
        assertNotNull(itemId);
        getScenarioContext().set(ITEM_ID, itemId);
    }

    @Then("the response code should be {int}")
    public void theResponseCodeShouldBe(int statusCode) {
        assertNotNull(response);
        assertEquals(response.statusCode(), statusCode);

    }

    @And("the response should contain name {string}")
    public void theResponseShouldContainName(String itemName) {
        assertNotNull(response);
        assertEquals(response.jsonPath().getString("name"), itemName);

        getScenarioContext().set(ITEM_ID, response.jsonPath().getString("id"));
    }


    @Given("An item id which is already created in the system")
    public void getItemById() {
        String itemId = getScenarioContext().getString(ITEM_ID);

        assertNotNull(itemId);
        getScenarioContext().set(ITEM_ID, itemId);
    }


    @When("I submit the request with item id")
    public void iSubmitTheRequestWithItemId() {
        String itemId = getScenarioContext().getString(ITEM_ID);
        Response response = itemApi.getItemById(itemId);
        response
                .then()
                .log().all()
                .statusCode(200);


        assertNotNull(response);
        getScenarioContext().setResponse(response);
    }

    @Then("the get response code should be {int}")
    public void theGetResponseCodeShouldBe(int statusCode) {
        Response itemResponse = (Response) getScenarioContext().get(RESPONSE);
        assertNotNull(itemResponse);
        assertEquals(itemResponse.statusCode(), statusCode);
        getScenarioContext().set(RESPONSE, itemResponse.jsonPath());
    }

    @And("the response should contain the correct item details including the provided item id")
    public void theResponseShouldContainTheCorrectItemDetailsIncludingTheProvidedItemId() {
        JsonPath expectedResponse = JsonPath.from(getClass().getClassLoader().getResourceAsStream("json/item-response.json"));
        JsonPath actualResponse = getScenarioContext().getResponse(RESPONSE);

        assertNotNull(expectedResponse.getString("id"));
        assertEquals(expectedResponse.getString("name"), actualResponse.getString("name"));

        getScenarioContext().set(ITEM_ID, actualResponse.getString("id"));
    }


    @Given("Items already created in the system")
    public void itemsAlreadyCreatedInTheSystem() {

        assertNotNull(getScenarioContext().getString(ITEM_ID));
    }

    @When("I submit the request to retrieve all items")
    public void iSubmitTheRequestToRetrieveAllItems() {
        Response itemsResponse = itemApi.getItems();
        itemsResponse
                .then()
                .log().all();

        getScenarioContext().setResponse(itemsResponse);

    }

    @Then("the get all items response code should be {int}")
    public void theGetAllItemsResponseCodeShouldBe(int statusCode) {
        Response itemsResponse = (Response) getScenarioContext().get(RESPONSE);

        assertNotNull(itemsResponse);
        assertEquals(itemsResponse.statusCode(), statusCode);

        getScenarioContext().setResponse(itemsResponse);
    }

    @And("the response should contain the all items")
    public void theResponseShouldContainTheAllItems() {

        Response response = (Response) getScenarioContext().get(RESPONSE);
        JsonPath actualResponse = response.getBody().jsonPath();
        JsonPath expectedResponse = JsonPath.from(getClass().getClassLoader().getResourceAsStream("json/all-items-response.json"));

        assertEquals(expectedResponse.getString("[0].id"), actualResponse.getString("[0].id"));
        assertEquals(expectedResponse.getString("[0].name"), actualResponse.getString("[0].name"));
        assertEquals(expectedResponse.getMap("[0].data"), actualResponse.getMap("[0].data"));

        getScenarioContext().set(ITEM_ID, actualResponse.getString("[0].id"));
    }

    @And("I submit the delete request to delete an item")
    public void iSubmitTheDeleteRequestToDeleteAnItem() {

        Response deleteItemResponse = itemApi.deleteItemById(getScenarioContext().getString(ITEM_ID));
        deleteItemResponse
                .then()
                .log().all();

        getScenarioContext().setResponse(deleteItemResponse);

    }

    @Then("the delete item response code should be {int}")
    public void theDeleteItemResponseCodeShouldBe(int statusCode) {
        Response response = (Response) getScenarioContext().get(RESPONSE);

        assertNotNull(response);
        assertEquals(response.statusCode(), statusCode);

        getScenarioContext().setResponse(response);
    }

    @And("the item should be deleted")
    public void theItemShouldBeDeleted() {
        Response response = (Response) getScenarioContext().get(RESPONSE);
        String actualMessage = response.jsonPath().getString("message");
        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains("has been deleted."));
    }

    @Given("Invalid payload to create an Item")
    public void invalidPayloadToCreateAnItem() {
        Response actualResponse = itemApi.createItemWithoutBody();
        actualResponse
                .then()
                .log()
                .all();

        assertNotNull(actualResponse);
        getScenarioContext().setResponse(actualResponse);

    }

    @Then("I should see {int} status code in response")
    public void iShouldSeeAStatusCodeInResponse(int statusCode) {
        Response actualResponse = (Response) getScenarioContext().get(RESPONSE);
        assertEquals(statusCode, actualResponse.statusCode());

        getScenarioContext().setResponse(actualResponse);
    }

    @And("I should see error message in response")
    public void iShouldSeeMessageInResponse() {
        String expectedErrorMessage = "400 Bad Request. If you are trying to create or update the data, potential issue" +
                " is that you are sending incorrect body json or it is missing at all.";

        Response actualResponse = (Response) getScenarioContext().get(RESPONSE);
        assertEquals(expectedErrorMessage, actualResponse.jsonPath().getString("error"));

    }


}
