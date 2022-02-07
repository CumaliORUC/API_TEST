package com.techproed.day04;

import groovyjarjarasm.asm.commons.TryCatchBlockSorter;
import groovyjarjarasm.asm.tree.TryCatchBlockNode;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.junit.Assert;
import org.junit.Test;

import static io.restassured.path.json.JsonPath.given;

/*
https://restful-booker.herokuapp.com/booking url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 200
content type'inin "application/json" oldugunu test edin

https://restful-booker.herokuapp.com/booking/1001 url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 404 oldugunu
Responsebodynin
 */
public class GetRequest02 {
    @Test
    public void test01 () {
        String url="https://restful-booker.herokuapp.com/booking ";
        Response response= RestAssured.given().accept("application/json").
                when().get(url);
        response.prettyPrint();
        response.then().assertThat().statusCode(200).contentType("application/json");
        System.out.println(response.getHeaders());
        System.out.println("==============================");
        System.out.println(response.getHeader("Server"));
        System.out.println("Date"+response.getHeader(" Date"));
    }

    @Test
    public void test02() {
         /*
       https://restful-booker.herokuapp.com/booking/1001 url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 404 oldugunu
ve Response body'sinin "Not Found" icerdigini
ve Response body'sinin "API" icermedigini test edin
     */
        String url="https://restful-booker.herokuapp.com/booking/1001";
        Response response=RestAssured.given().
                accept("application/json").
                when().
                get(url);
        response.prettyPrint();
        response.then().assertThat().statusCode(404);
       Assert.assertTrue(response.asString().contains("Not Found"));
       Assert.assertFalse(response.asString().contains("API"));

    }
}
