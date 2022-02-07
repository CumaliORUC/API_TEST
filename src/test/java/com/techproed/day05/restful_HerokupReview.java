package com.techproed.day05;

import com.techproed.testBase.TestBaseHerokup;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class restful_HerokupReview extends TestBaseHerokup {

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
    public void herokUp() {
        spec02.pathParams("parameter","booking","parameter1",5);

        Response response=given().accept("application/json").spec(spec02).when().get("/{parameter}/{parameter1}");

        response.prettyPrint();

        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON);

        JsonPath json=response.jsonPath();

        Assert.assertEquals("Sally",json.getString("firstname"));
        Assert.assertEquals("Smith",json.getString("lastname"));
        Assert.assertEquals(789,json.getInt("totalprice"));
        Assert.assertFalse(json.getBoolean("depositpaid"));
    }
}
