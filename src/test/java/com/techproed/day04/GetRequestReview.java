package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestReview {
   /* https://restful-booker.herokuapp.com/booking/5 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve firstname'in “Mary"
    ve totalprice’in 318
    ve checkin date'in 2015-06-12"oldugunu test edin

    */
    @Test
    public void getRequestTest() {
        String url="https://restful-booker.herokuapp.com/booking/5";
        Response response=given()
                .accept("application/json")
                .when()
                .get(url);

        String expected_firstName="Susan";
        int expected_totalprice=510;
        String expected_check_in="2018-07-04";

        response.prettyPrint();

        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("firstname", equalTo(expected_firstName),
                        "totalprice", Matchers.equalTo(expected_totalprice),
                        "bookingdates.checkin",Matchers.equalTo(expected_check_in));
        System.out.println(response.getHeaders());
        System.out.println("======================================================");
        System.out.println(response.getHeader("Server"));

    }
}
