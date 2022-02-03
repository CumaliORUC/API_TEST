package com.techproed.day10;

import com.techproed.TestData.BookerHerookup;
import com.techproed.testBase.TestBaseBookerHerokupp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/*
https://restful-booker.herokuapp.com//booking
{ "firstname": "Selim",
               "lastname": "Ak",
               "totalprice": 11111,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2020-09-09",
                   "checkout": "2020-09-21"
                }
 }gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
 "booking": {
         "firstname": " Selim ",
         "lastname": " Ak ",
         "totalprice":  11111,
         "depositpaid": true,
         "bookingdates": {
             "checkin": "2020-09-01",
              "checkout": " 2020-09-21”
         },
        }
olduğunu test edin
 */
public class PostRequest02 extends TestBaseBookerHerokupp {

    @Test
    public void test() {
        //url oluştur
        spec02.pathParam("name","booking");

        //request body oluştur.

        BookerHerookup testData=new BookerHerookup();
        JSONObject requestBodyJson= testData.setupTestData2();

        System.out.println(requestBodyJson);
        Response response=given().contentType(ContentType.JSON).  //burada   accept("application/json") yerine ContentTypeJson olmalı
                spec(spec02).auth().basic("admin","password123").
                body(requestBodyJson.toString()).when().post("/{name}"); //body içerisinde requestbaody gonderirken eger jsonobject ile
        // oluşturulmuşsa  string yapmamız gerekir. Bu nedenle to String eklendi.
        response.prettyPrint();
        System.out.println("=========================================");
        //requestBodyJson bizim aynı zamanda expected data olarak da kullanabiliriz.
        HashMap<String, Object> actualData=response.as(HashMap.class);

        System.out.println(actualData);

        Assert.assertEquals(requestBodyJson.getString("firstname"),
                ((Map)actualData.get("booking")).get("firstname"));

        Assert.assertEquals(requestBodyJson.getString("lastname"),
                ((Map<?, ?>) actualData.get("booking")).get("lastname"));
        Assert.assertEquals(requestBodyJson.getInt("totalprice"),
                ((Map<?, ?>) actualData.get("booking")).get("totalprice"));
        Assert.assertEquals(requestBodyJson.getBoolean("depositpaid"),
                ((Map<?, ?>) actualData.get("booking")).get("depositpaid"));
        Assert.assertEquals(requestBodyJson.getJSONObject("bookingdates").getString("checkin"),
                ((Map)((Map<?, ?>) actualData.get("booking")).get("bookingdates")).get("checkin"));
        Assert.assertEquals(requestBodyJson.getJSONObject("bookingdates").getString("checkout"),
                //burada da bookings içine girebilmemiz için yine ilk datanın jsonobject olduğunu anlattık.
                //o yuzden satır 74 de bookingsdate olan getJsonObject al ve içine gir dedik.
                ((Map)((Map<?, ?>) actualData.get("booking")).get("bookingdates")).get("checkout"));


    }
}
