package com.techproed.day05;

import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class MatchesClass extends TestBaseJsonPlaceHolder {
    /*
      /*
    http://dummy.restapiexample.com/api/v1/employees url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200    ve content type'inin "application/json"
   ve employees sayisinin 24
   ve employee'lerden birinin "Ashton Cox"
   ve girilen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin
     */

    @Test
    public void MatchersMethodsTest() {
        String url="http://dummy.restapiexample.com/api/v1/employees";

        Response response=given().accept("application/json").when().get(url);

        response.prettyPrint();

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("data.id", Matchers.hasSize(24),
                        "data.employee_name",Matchers.hasItem("Ashton Cox"),
                        "data.employee_age",Matchers.hasItems(21,61,23));
    }
    /*
     https://jsonplaceholder.typicode.com/todos/123 url'ine
  accept type'i "application/json" olan GET request'i yolladigimda
 gelen response’un
 status kodunun 200
 ve content type'inin "application/json"
 ve Headers'daki "Server" in "cloudflare"
  ve response body'deki "userId"'nin 7
 ve "title" in "esse et quis iste est earum aut impedit"
  ve "completed" bolumunun false oldugunu test edin
      */
    @Test

    public void JsonSpecs() {
        spec01.pathParams("name","todos","id",123);

        Response response=given().accept("application/json").spec(spec01).when().get("/{name}/{id}");

        response.prettyPrint();

        response.then().statusCode(200).contentType(ContentType.JSON);

        response.then().body("userId", Matchers.equalTo(7)).header("Server",Matchers.equalTo("cloudflare"));

        System.out.println(response.body().asString());

        Assert.assertTrue(response.header("Server").contains("cloudflare"));

        Assert.assertTrue(response.body().asString().contains("esse et quis iste est earum aut impedit"));

        response.then().body("completed",Matchers.equalTo(false));

    }
}
