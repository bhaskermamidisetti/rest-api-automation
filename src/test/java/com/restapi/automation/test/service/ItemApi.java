package com.restapi.automation.test.service;

import com.restapi.automation.test.stepdefinations.Item;
import io.restassured.response.Response;
import org.json.JSONObject;
import static com.restapi.automation.test.service.Constants.CONTENT_TYPE;
import static com.restapi.automation.test.service.Constants.APPLICATION_JSON;
import static com.restapi.automation.test.service.Constants.ADD_ITEM_API;
import static com.restapi.automation.test.service.Constants.GET_ITEM_BY_ID_API;
import static com.restapi.automation.test.service.Constants.GET_ITEMS_API;
import static com.restapi.automation.test.service.Constants.DELETE_ITEM_API;

import static io.restassured.RestAssured.given;

public class ItemApi {

    public Response createItem(Item item) {
        return given()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .log().all()
                .body(createItemRequestBody(item).toString())
                .post(ADD_ITEM_API);
    }


    public Response getItemById(String itemId) {
        return given()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .log().all()
                .pathParam("id", itemId)
                .get(GET_ITEM_BY_ID_API);

    }

    public Response getItems() {
        return given()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .log().all()
                .get(GET_ITEMS_API);

    }

    public Response deleteItemById(String itemId) {
        return given()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .log().all()
                .pathParam("id", itemId)
                .delete(DELETE_ITEM_API);

    }

    public Response createItemWithoutBody() {
        return given()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .log().all()
                .post(ADD_ITEM_API);
    }

    private static JSONObject createItemRequestBody(Item item) {
        return new JSONObject()
                .put("name", item.getName())
                .put("cpu", item.getCpuModel())
                .put("price", item.getPrice())
                .put("year", item.getYear());
    }

}

