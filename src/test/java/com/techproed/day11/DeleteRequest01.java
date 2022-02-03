package com.techproed.day11;

import com.techproed.TestData.Dummy;
import com.techproed.testBase.TestBaseDummy;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/*
http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde

Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
}
 */
public class DeleteRequest01 extends TestBaseDummy {

    @Test
    public void test01() {

        //Url oluşturalım
        spec03.pathParams("name1","delete","name2",2);

        //Expected data oluştur.

        Dummy expectedmethod=new Dummy ();
        JSONObject expectedData=expectedmethod.deleteExpected();

        //Response oluştur

        Response response=given().
                spec(spec03).auth().
                basic("admin","password123").when().delete("/{name1}/{name2}");

        response.prettyPrint();

        //Matchers ile Assertion

        response.then().assertThat().statusCode(expectedData.getInt("statusCode")).
                body("status", equalTo(expectedData.getString("status")),
                        "data",equalTo(expectedData.getString("data")),
                        "message",equalTo(expectedData.getString("message")));

    }

}
