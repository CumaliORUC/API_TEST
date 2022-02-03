package com.techproed.day7;

import com.techproed.TestData.BookerHerookup;
import com.techproed.testBase.TestBaseBookerHerokupp;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends TestBaseBookerHerokupp {
    /*
    https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
 dönen response body nin
  {
   "firstname": "Eric",
   "lastname": "Smith",
   "totalprice": 555,
   "depositpaid": false,
   "bookingdates": {
       "checkin": "2016-09-09",
       "checkout": "2017-09-21"
    }
} gibi olduğunu test edin.
     */

    @Test
    public void test01() {
        spec02.pathParams("name1", "booking","name2",1);

        Response response=given().accept("application/json").spec(spec02).when().get("/{name1}/{name2}");
        response.prettyPrint();
        BookerHerookup expecteddata=new BookerHerookup();


        //De-serialization
        HashMap<String,Object>actualDataMap=response.as(HashMap.class);
        HashMap<String,Object>expectedDataMap=expecteddata.testDataBookerHerok();

        Assert.assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("bookingsdate.checkin"),actualDataMap.get("bookingsdate.checkin"));

        //Çift katmanlı MAP ler de olması gereken yontem
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map) actualDataMap.get("bookingdates")).get("checkin"));
        Assert.assertEquals(   ((Map<?, ?>) expectedDataMap.get("bookingdates")).get("checkout"),
                ((Map)actualDataMap.get("bookingdates")).get("checkout"));
    }
}
