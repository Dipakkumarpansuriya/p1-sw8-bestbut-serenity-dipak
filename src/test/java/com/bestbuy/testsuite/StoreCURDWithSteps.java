package com.bestbuy.testsuite;

import com.bestbuy.bestbuyinfo.StoreSteps;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoreCURDWithSteps extends TestBase {

    private String name = "Minnetonka";
    private String type = "BigBox";
    private String address = "13513 Ridgedale Dr";
    private String address2 = "";
    private String city = "Hopkins";
    private String state = "MN";
    private String zip = "55305";
    private double lat = 44.969658;
    private double lng = -93.449539;
    private String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";

    static int storeID;
    @Steps
    StoreSteps storeSteps;

    @Title("This will create new store")
    @Test
    public void test001() {
        ValidatableResponse response = storeSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours);
        response.log().all().statusCode(201);
        storeID = response.log().all().extract().path("id");

    }

    @Title("Verify if the product was added to application")
    @Test
    public void test002() {


        HashMap<String, Object> storeMap = storeSteps.getStoreInfoByStoreName(storeID);
        Assert.assertThat(storeMap, hasValue(name));

    }

    @Title("This will update store")
    @Test
    public void test003() {
        name = name + "_Updated";

        storeSteps.updateStore(storeID, name, type, address, address2, city, state, zip, lat, lng, hours);

        HashMap<String, Object> productMap = storeSteps.getStoreInfoByStoreName(storeID);
        Assert.assertThat(productMap, hasValue(name));
    }

    @Title("This will delete product")
    @Test
    public void test004() {

        storeSteps.deleteStoreByID(storeID).statusCode(200);
        storeSteps.getStoreById(storeID).statusCode(404);
    }


}
