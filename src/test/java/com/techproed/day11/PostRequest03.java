package com.techproed.day11;

import com.techproed.TestData.JsonPlaceHolder;
import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequest03 extends TestBaseJsonPlaceHolder {
    /*
    https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
     }
     "userId": 55,
     "title": "Tidy your room",
     "completed": false
   }
Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
   {
     "userId": 55,
     "title": "Tidy your room",
     "completed": false,
     "id": …
    }
     */
@Test
public void test01() {
    spec01.pathParam("name", "todos");

    //Requestbody oluşturalım.
    JsonPlaceHolder testDataJson=new JsonPlaceHolder();
    JSONObject requestBody=testDataJson.setUpPost03();

    //Response gonderelim
    Response response=given().
            contentType(ContentType.JSON).
            spec(spec01).
            auth().basic("admin", "password123").
            body(requestBody.toString()).
            when().post("/{name}");

    response.prettyPrint();
    //de-serialization (GSON) Yontemi Assertion Yontemi
    HashMap<String,Object> actualData=response.as(HashMap.class);

    System.out.println("GSON YONTEMİ ACTUAL DATA"+actualData);
    Assert.assertEquals(testDataJson.statusCode, response.getStatusCode());
    Assert.assertEquals(requestBody.getInt("userId"),actualData.get("userId"));
    Assert.assertEquals(requestBody.getString("title"),actualData.get("title"));
    Assert.assertEquals(requestBody.getBoolean("completed"),actualData.get("completed"));

    //JSONPath Yontemi

    JsonPath json=response.jsonPath();
    Assert.assertEquals(requestBody.getInt("userId"),json.getInt("userId"));
    Assert.assertEquals(requestBody.getString("title"),json.getString("title"));
    Assert.assertEquals(requestBody.getBoolean("completed"),json.getBoolean("completed"));

    //Matchers Yontemi
    response.then().assertThat().statusCode(testDataJson.statusCode).body("userId",equalTo(requestBody.getInt("userId")),
            "title",equalTo(requestBody.getString("title")),
            "comleted",equalTo(requestBody.getBoolean("completed")));

}

}
