package com.techproed.day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01Day01 {
    /*
    GetRequest01:
https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde donecek cevap(response) icin
ØHTTP status kodunun 200
ØContent Type’in Json
ØVe Status Line’in HTTP/1.1 200 OK
Oldugunu test edin.
     */

    @Test
    public void test01() {
        /*
        Backend testi yapacağımızdan drivera ihtiyacımız yok.
         */

        //1- Url oluştur.
        String url="https://restful-booker.herokuapp.com/booking/3";
        //2- expected result oluştur. Burada bizden body de gelen response assert etmemiz beklenmiyor.
        //3- request gonder
        Response response=given().accept("application/json").
                when().get(url);
        response.prettyPrint();
        //4- response al(actual result)
        //5- assertion işlemi uygula
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

        System.out.println("status code:"+response.getStatusCode());
        System.out.println("status line:"+response.getStatusLine());
        System.out.println("status Headers:"+response.getHeaders());
        System.out.println("status content:"+response.getContentType());

    }
}
