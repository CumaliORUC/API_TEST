package com.techproed.day11;

import com.techproed.TestData.JsonPlaceHolder;
import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PatchRequest01 extends TestBaseJsonPlaceHolder {
    /*
        https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
   {

      "title": "API calismaliyim"

     }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "userId": 10,
 "title": "API calismaliyim"
 "completed": true,
 "id": 198
}

     */

    @Test
    public void tes01() {

        //url oluşturalım
        spec01.pathParams("name1","todos","name2",198);

        //Request oluşturalım
        JsonPlaceHolder jsonclass=new JsonPlaceHolder();
        JSONObject requestbody= jsonclass.patchrequest();

        //expected Datayı oluştualım

        JSONObject expextedData= jsonclass.patchexpected();

        //Response ile Requesti oluşturalım

        Response response=given().contentType(ContentType.JSON).spec(spec01).
                auth().basic("admin","password123").
                body(requestbody.toString()).when().patch("/{name1}/{name2}");
        response.prettyPrint();
        //De-Serialization=======

        HashMap<String,Object > actualData=response.as(HashMap.class);
        System.out.println("Actual Data===="+actualData);

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expextedData.getBoolean("completed"),actualData.get("completed"));
        Assert.assertEquals(expextedData.getInt("userId"),actualData.get("userId"));
        Assert.assertEquals(expextedData.getString("title"),actualData.get("title"));

    }
}
