package com.techproed.day13;

import com.techproed.testBase.TestBaseJsonPlaceHolder;
import com.techproed.utulities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestWithObjectMapper01 extends TestBaseJsonPlaceHolder {
    /*
    https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
Dönen response ‘un status kodunun 200 ve body kısmının
{
   "userId": 10,
   "id": 198,
   "title": "quis eius est sint explicabo",
   "completed": true
}
Olduğunu Object Mapper kullanarak test edin
     */

@Test
    public void test01() {
    //Oncelikle url oluşturalım

    spec01.pathParams("parametre1", "todos", "parametre2",198);

    //eger adreste ardında ? filan varsa bunun adı da query paramdır
    // spec devamında querryparam diye method var o eklenir.

    //expected Data
    //ilk olarak json ifademi bir Stringe atalım

    String jsonData="{\n" +
            "   \"userId\": 10,\n" +
            "   \"id\": 198,\n" +
            "   \"title\": \"quis eius est sint explicabo\",\n" +
            "   \"completed\": true\n" +
            "}";

    //çevirdiğimiz datayı nerde ve hangi tipte saklaycaksak onu belirleyeceğiz.
    Map<String,Object> expectedDataMap= JsonUtil.convertJsonToJava(jsonData,Map.class);
    //Boylece tum datayı buraya koymuş olduk.

    System.out.println("Expeccted Data==========="+expectedDataMap);

    //Request gonderelim.

    Response response=given().
            contentType(ContentType.JSON).
            spec(spec01).when().get("/{parametre1}/{parametre2}");

    response.prettyPrint();

    //burada ikisini karşılaştırmak için response da çevirelim.

    Map<String,Object> actualData=JsonUtil.convertJsonToJava(response.asString(),Map.class);

    System.out.println("actual Data====="+actualData);
    //response içindeki convertJsonToJava methodu string data kabul ettiği için
    // response dayı stringe donuşturmek gerekiyor. Bunedenle as.String ekllendi.

    Assert.assertEquals(200,response.getStatusCode());
    Assert.assertEquals(expectedDataMap.get("userId"),actualData.get("userId"));
    Assert.assertEquals(expectedDataMap.get("id"),actualData.get("id"));
    Assert.assertEquals(expectedDataMap.get("title"),actualData.get("title"));
    Assert.assertEquals(expectedDataMap.get("completed"),actualData.get("completed"));


}
}
