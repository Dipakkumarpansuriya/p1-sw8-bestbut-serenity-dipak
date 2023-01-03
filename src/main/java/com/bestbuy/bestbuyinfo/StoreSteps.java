package com.bestbuy.bestbuyinfo;


import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoreSteps {
    @Step("Creating store with name : {0},type {1}, address: {2}, address2: {3}, city: {4}, state: {5}, zip: {6}, lat: {7},lng: {8}and hours: {9}")
    public ValidatableResponse createStore(String name, String type, String address, String address2, String city, String state,
                                             String zip, Double lat, Double lng, String hours) {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post("/stores")
                .then();
    }

    @Step("Verifying store is added : storeId {0}")
    public HashMap<String, Object> getStoreInfoByStoreName(int storeID){
        return SerenityRest.given().log().all()
                .when()
                .pathParam("storeID", storeID)
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");


    }
    @Step("Updating store with Id: {0},name: {1},type: {2}, address: {3}, address2: {4}, city: {5}, state: {6}, zip: {7}, lat: {8},lng: {9}and hours: {10}")
    public ValidatableResponse updateStore(int storeID,String name, String type, String address, String address2, String city, String state,
                                           String zip, Double lat, Double lng, String hours) {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .pathParam("storeID", storeID)
                .put(EndPoints.UPDATE_STORE_BY_ID)
                .then();
    }
    @Step
    public ValidatableResponse deleteStoreByID(int storeID){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("storeID", storeID)
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then();
    }

    @Step("Getting store information with storeID: {0}")
    public ValidatableResponse getStoreById(int storeID){
        return SerenityRest.given().log().all()
                .pathParam("storeID", storeID)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();
    }


}
