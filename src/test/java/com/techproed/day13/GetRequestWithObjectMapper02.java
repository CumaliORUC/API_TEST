package com.techproed.day13;

import com.techproed.testBase.TestBaseBookerHerokupp;
import com.techproed.utulities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestWithObjectMapper02 extends TestBaseBookerHerokupp {
    /*
    https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
status kodun 200 ve response body’nin
{
   "firstname": "Mark",
   "lastname": "Wilson",
   "totalprice": 284,
   "depositpaid": false,
   "bookingdates": {
       "checkin": "2016-08-10",
       "checkout": "2018-06-22"
   }
}
     */
    @Test
    public void test01() {
        //url
        spec02.pathParams("name1","booking","name2",2);

        //expected data

        String JsonObjecet="{\n" +
                "   \"firstname\": \"Mark\",\n" +
                "   \"lastname\": \"Wilson\",\n" +
                "   \"totalprice\": 284,\n" +
                "   \"depositpaid\": false,\n" +
                "   \"bookingdates\": {\n" +
                "       \"checkin\": \"2016-08-10\",\n" +
                "       \"checkout\": \"2018-06-22\"\n" +
                "   }\n" +
                "}";

        //datayı koyacağımız map oluştur.
        HashMap<String,Object> expectedData=
                JsonUtil.convertJsonToJava(JsonObjecet,HashMap.class);

        System.out.println("Expected Data========"+expectedData);

        //Response oluşturalım.
        Response response=given().
                contentType(ContentType.JSON).
                spec(spec02).when().get("/{name1}/{name2}");
        response.prettyPrint();

       HashMap<String,Object> actualData=JsonUtil.convertJsonToJava(response.asString(),HashMap.class);

        System.out.println("Actaul Data==="+actualData);


        // Assert.assertEquals(expectedData,actualData); Buda çalışmış Mesut Hocadan

        Assert.assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        Assert.assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),
                ((Map)actualData.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),
                ((Map)actualData.get("bookingdates")).get("checkout"));
    }

}
