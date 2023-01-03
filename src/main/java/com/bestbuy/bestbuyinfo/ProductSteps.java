package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductSteps {

    @Step("Creating product with name : {0},type {1}, price: {2}, shipping: {3}, upc: {4}, description: {5}, manufacture: {6}, model: {7},url: {8}and image: {9}")
    public ValidatableResponse createProduct(String name, String type, Double price, int shipping, String upc, String description,
                                             String manufacture, String model, String url, String image) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacture);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post("/products")
                .then();
    }

    @Step("Verifying product is added : productId {0}")
    public HashMap<String, Object> getProductInfoByProductName(int productID){
        return SerenityRest.given().log().all()
                .when()
                .pathParam("productID", productID)
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");


    }

    @Step("Updating product with name : {0},type {1}, price: {2}, shipping: {3}, upc: {4}, description: {5}, manufacture: {6}, model: {7},url: {8}and image: {9}")
    public ValidatableResponse updateProduct (int productID, String name, String type, Double price, int shipping, String upc, String description,
                                             String manufacture, String model, String url, String image) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacture);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .pathParam("productID", productID)
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();
    }

    @Step
    public ValidatableResponse deleteProductByID(int productID){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .pathParam("productID", productID)
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Getting product information with productID: {0}")
    public ValidatableResponse getProductById(int productID){
        return SerenityRest.given().log().all()
                .pathParam("productID", productID)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }
}
