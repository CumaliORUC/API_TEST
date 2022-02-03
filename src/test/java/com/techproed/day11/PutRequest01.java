package com.techproed.day11;

import com.techproed.TestData.JsonPlaceHolder;
import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PutRequest01 extends TestBaseJsonPlaceHolder {

    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde
   {
      "userId": 21,
      "title": "Wash the dishes",
      "completed": false
     }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "userId": 21,
 "title": "Wash the dishes",
 "completed": false,
 "id": 198
}
     */

    @Test
    public void test01() {
        spec01.pathParams("name1", "todos", "name2", 198);

        //Expected oluşturalım.
        JsonPlaceHolder jsonRequest=new JsonPlaceHolder();
        JSONObject requestbody= jsonRequest.setupPut01();

        //Request oluşturalım

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).auth().basic("admin", "password123").body(requestbody.toString()).when().put("/{name1}/{name2}");
        response.prettyPrint();
        //JsonPath Yontemi

        JsonPath jsonyontemi=response.jsonPath();

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(requestbody.getInt("userId"),jsonyontemi.getInt("userId"));
        Assert.assertEquals(requestbody.getString("title"),jsonyontemi.getString("title"));
        Assert.assertEquals(requestbody.getBoolean("completed"),jsonyontemi.getBoolean("completed"));
    }

}
