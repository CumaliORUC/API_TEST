package com.techproed.day04;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest03 {
    /*
    https://restful-booker.herokuapp.com/booking/7 url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 200
ve content type'inin "application/json"
ve firstname'in "Sally"
ve lastname'in "Ericsson"
ve checkin date'in 2018-10-07"
ve checkout date'in 2020-09-30 oldugunu test edin
     */

    @Test
    public void test01() {
        String url="https://restful-booker.herokuapp.com/booking/7";
        Response response= given().accept("application/json").when().get(url);

        response.prettyPrint();

        response.then().assertThat().statusCode(200);
        response.then().assertThat().contentType("application/json");

        response.then().assertThat().body("firstname", equalTo("Sally"));

        response.then().assertThat().body("lastname", equalTo("Smith"));

        response.then().assertThat().body("totalprice", equalTo(388));

        response.then().assertThat().body("depositpaid", equalTo(true));

        response.then().assertThat().body("bookingdates.checkin", equalTo("2015-04-28"));

        response.then().assertThat().body("bookingdates.checkout", equalTo("2018-09-23"));


/* Body içerisinde checkin veya checkouta ulaşmak için map yapısından dolayı buradaki dataya ulaşmak için
bookingdates.checkin ve bookingdates.checkout ile ulaşabiliriz. EEger içinde birden fazla bolum varsa buradaki yapılar index
numaralarına gore sıralanır. Eger ikinci tarihleri almak istyorsak bookingdates[1] olur.
{
    "firstname": "Mary",
    "lastname": "Jackson",
    "totalprice": 262,
    "depositpaid": false,
    "bookingdates": [
        {
        "checkin": "2015-06-26",
        "checkout": "2017-08-09"

        {
        "checkin": "2015-06-26",
        "checkout": "2017-08-09"
         }
}
 */

        //===========================================================================

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
        body("firstname",equalTo("Sally"),
                "lastname", equalTo("Smith"),
                "totalprice", equalTo(388),
                "depositpaid", equalTo(true),
                "bookingdates.checkin", equalTo("2015-04-28"),
                "bookingdates.checkout", equalTo("2018-09-23"));
    }
}
