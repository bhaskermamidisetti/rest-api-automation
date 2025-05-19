package com.restapi.automation.test.service;

public class Constants {

    public static final String BASE_URL = "https://api.restful-api.dev/";
    public static final String ADD_ITEM_API = BASE_URL.concat("objects");
    public static final String GET_ITEM_BY_ID_API = BASE_URL.concat("objects/{id}");
    public static final String GET_ITEMS_API = BASE_URL.concat("objects");
    public static final String DELETE_ITEM_API = BASE_URL.concat("objects/{id}");
    public static final String ITEM_ID = "ITEM_ID";
    public static final String RESPONSE = "RESPONSE";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
}
