package com.techproed.day05;

import com.techproed.testBase.TestBaseBookerHerokupp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest07 extends TestBaseBookerHerokupp {

    /*
    https://restful-booker.herokuapp.com/booking/5 url'ine bir request yolladigimda
     	HTTP Status Code'unun 200
     	ve response content type'inin "application/json" oldugunu
			ve response body'sinin asagidaki gibi oldugunu test edin
				{"firstname": Sally,
     			"lastname": "Smith",
     			"totalprice": 789,
     			"depositpaid": false,
     			"bookingdates": { 	"checkin": "2017-12-11",
     	                     						"checkout":"2020-02-20" }
      		}
     */


        @Test
        public void test() {
            spec02.pathParams("parametre1","booking",
                    "parametre2",5 );
            Response response=given().
                    accept("application/json").
                    spec(spec02).
                    when().get("/{parametre1}/{parametre2}");
            System.out.println(response.prettyPrint());

            //        response.then().
//                assertThat().
//                statusCode(200).
//                contentType(ContentType.JSON).
//                body("firstname", equalTo("Eric"),
//                        "lastname" ,equalTo("Smith"),
//                        "totalprice",equalTo(414),
//                        "depositpaid",equalTo(false),
//                        "bookingdates.checkin",equalTo("2016-04-11"),
//                        "bookingdates.checkout",equalTo("2020-03-26") );


        /*
        Body içindeki ifadeleri body("", Matcher.equalTo("")) ile assert etmek
        yerine JsonPath ile direk Assert.assert() edebiliyoruz.
         */

        response.then().
                assertThat().
                contentType(ContentType.JSON).
                statusCode(200);

        JsonPath json=response.jsonPath();
        Assert.assertEquals("Mary",json.getString("firstname"));
        Assert.assertEquals("Ericsson",json.getString("lastname"));
        Assert.assertEquals(366,json.getInt("totalprice"));
        Assert.assertEquals(false,json.getBoolean("depositpaid"));
        Assert.assertEquals("2020-03-14",json.getString("bookingdates.checkin"));
        Assert.assertEquals("2021-03-13",json.getString("bookingdates.checkout"));
        Assert.assertEquals(200,response.getStatusCode());
    }
}
