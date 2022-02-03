package com.techproed.day7;

import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest11 extends TestBaseJsonPlaceHolder {
    /*
    https://jsonplaceholder.typicode.com/api/todos/2 url ‘ine istek gönderildiğinde,
 Dönen response un
 Status kodunun 200, dönen body de,
       "completed": değerinin false
       "title”: değerinin “quis ut nam facilis et officia qui”
       "userId"  sinin 1 ve header değerlerinden
 "Via" değerinin “1.1 vegur” ve
       "Server" değerinin “cloudflare” olduğunu test edin…
     */
    @Test
    public void test01() {
     //1-URL oluştur
        spec01.pathParams("name1", "todos", "name2",2);


        //2-expected datayuı oluştur

        HashMap<String,Object>expectedDataMap=new HashMap<String, Object>();
        expectedDataMap.put("statusCode",200);
        expectedDataMap.put("completed",false);
        expectedDataMap.put("userId",1);
        expectedDataMap.put("title","quis ut nam facilis et officia qui");
        expectedDataMap.put("Via","1.1 vegur");
        expectedDataMap.put("Server", "cloudflare");

        System.out.println("Expected Data=========================");
        System.out.println(expectedDataMap);
        System.out.println("=======================================");
        //3-Request gonder

        Response response=given().
                accept("application.json").
                spec(spec01).
                when().get("/{name1}/{name2}");
        response.prettyPrint();
        //4- Actual Datayı oluştur.

        HashMap<String,Object>actualDataMap=response.as(HashMap.class);

        //burada Json formatında gelecek cevabı, Hasmap formatında belirttiğim Hasmap adresine koy.

        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
        Assert.assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataMap.get("Via"),response.getHeader("via")); //via map içinde olmadığı için headers tan alıyoruz.
        Assert.assertEquals(expectedDataMap.get("Server"),response.getHeader("Server"));

    }
}
