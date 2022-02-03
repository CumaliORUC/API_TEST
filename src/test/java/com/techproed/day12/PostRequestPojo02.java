package com.techproed.day12;


import com.techproed.pojos.ActualBookingPojo;
import com.techproed.pojos.BookingDatesPojo;
import com.techproed.pojos.BookingPojo;
import com.techproed.testBase.TestBaseBookerHerokupp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/*
https://restful-booker.herokuapp.com/booking
    request body {
                   "firstname": "Selim",
                   "lastname": "Ak",
                   "totalprice": 15000,
                   "depositpaid": true,
                   "bookingdates": {
                       "checkin": "2020-09-09",
                       "checkout": "2020-09-21"
                    }
                 }
   Status code is 200
    response body  {
                            "bookingid": 11,
                            "booking": {
                                "firstname": "Selim",
                                "lastname": "Ak",
                                "totalprice": 15000,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2020-09-09",
                                    "checkout": "2020-09-21"
                                }
                            }
                         }
 */
public class PostRequestPojo02 extends TestBaseBookerHerokupp {

    //bu tur sorularda içiçe ise bu durumda iki ayrı pojo oluşturuyoruz bunlar bizim request  pojoumuz
    //bir de gelen cevabı koyacağımız bir pojoya ihtiyaç olacak ou-nu da ayrı oluşturup assertion yapacağız.

        //Request oluşturalım. İç katmandan dış katmana doğru devam ediyoruz.
        //en ic katmandan en dis katmana dogru tanimlamalar yapilir,
        // Dolayisiyla once booking dates tanimladik ve ardindan "booking" adinda olan katmanin son elemani olarak "bookingDatesPojo"
        // diyerek nested yapiyi olusturmus olduk.
        @Test
        public void test(){
            //url oluştur
            spec02. pathParam("parametre1","booking");
            //requestBody oluştur.
            BookingDatesPojo bookingdates=new BookingDatesPojo("2020-09-09","2020-09-21") ;
            BookingPojo booking=new BookingPojo("Selim","Ak",15000,true,bookingdates);
            System.out.println(booking);
            //request oluştur
            Response response=given().
                    contentType(ContentType.JSON).
                    spec(spec02).
                    auth().basic("admin","password123").
                    body(booking).
                    when().
                    post("/{parametre1}");
            response.prettyPrint();
            //De- Serialization
            ActualBookingPojo actualData=response.as(ActualBookingPojo.class);
            System.out.println(actualData);
            Assert.assertEquals(200,response.getStatusCode());
            Assert.assertEquals(booking.getFirstname(),actualData.getBooking().getFirstname());
            Assert.assertEquals(booking.getLastname(),actualData.getBooking().getLastname());
            Assert.assertEquals(booking.getTotalprice(),actualData.getBooking().getTotalprice());
            Assert.assertEquals(booking.isDepositpaid(),actualData.getBooking().isDepositpaid());
            Assert.assertEquals(booking.getBookingdates().getCheckin(),
                    actualData.getBooking().getBookingdates().getCheckin());
            Assert.assertEquals(booking.getBookingdates().getCheckout(),
                    actualData.getBooking().getBookingdates().getCheckout());
        }
}