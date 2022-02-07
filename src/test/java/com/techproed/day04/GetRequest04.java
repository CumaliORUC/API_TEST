package com.techproed.day04;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/*
https://restful-booker.herokuapp.com/booking/5 url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 200
ve content type'inin "application/json"
ve firstname'in “Jim"
ve totalprice’in 600
ve checkin date'in 2015-06-12"oldugunu test edin
 */
public class GetRequest04 {
    @Test
        public void test01() {
        String url="https://restful-booker.herokuapp.com/booking/5 ";
        Response response=given().accept("application/json").when().get(url);

        response.prettyPrint();

        response.then().assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname", equalTo("Mary"),
                        "totalprice", equalTo(318),
                        "bookingdates.checkin", equalTo("2015-06-09"));
       //Matcher class API da response ları verfiy etme yontemlerinden biridir.En temel olanı matcher clastır.
    }
    @Test
        public void asStringBody() {
        /*
        https://restful-booker.herokuapp.com/booking/1001 url'ine
        accept type'i "application/json" olan GET request'i yolladigimda
        gelen response'un
        status kodunun 404 oldugunu
        Responsebodynin "Not Found" içerdiğini ve "API" içermediğini kontrol edin

         */
        String url="https://restful-booker.herokuapp.com/booking/1001";

        Response response= RestAssured.given()
                .accept("application/json")
                .when()
                .get(url);

        response.prettyPrint();

       Assert.assertTrue(response.asString().contains("Not Found"));

       Assert.assertFalse(response.asString().contains("API"));



    }


}