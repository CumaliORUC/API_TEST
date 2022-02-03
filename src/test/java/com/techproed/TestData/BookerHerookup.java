package com.techproed.TestData;

import org.json.JSONObject;
import org.codehaus.jackson.map.util.JSONPObject;

import java.util.HashMap;

public class BookerHerookup {

    public HashMap<String,Object> testDataBookerHerok() {

        //bookingsmap içerisinde ayrı bir map olduğu için onun içinde ayrı bir map oluşturulup,
        // expecteddata map in içine put all ile ekleme yapıyoruz. Boylece iç içe map yapmış oluyoruz.

            //bookingdates içerisinde ayrı bir map yapısı vardır. bu sebeple bu kısım ayrı map oluşturulur
            // mapin type i String, String olur çünkü checkin ve checout değerleri Stringtir.
            HashMap<String,String> bookingDatesMap=new HashMap<String, String>();
            bookingDatesMap.put("checkin","2015-09-21");
            bookingDatesMap.put("checkout","2020-12-13");
            HashMap<String,Object> expectedData=new HashMap<String, Object>();
            expectedData.put("firstname","Mary");
            expectedData.put("lastname","Wilson");
            expectedData.put("totalprice",345);
            expectedData.put("depositpaid",true);
            expectedData.put("bookingdates",bookingDatesMap);// ilk oluşturduğumuz mapi ikinci mapin içerisine aldık

        return expectedData;

    }
    //JSONobject tipinde yeni bir metodla request dataları koyuyoruz.
    public JSONObject setupTestData2 () {
   JSONObject bookingDate=new JSONObject();
   bookingDate.put("checkin", "2020-09-09");
   bookingDate.put("checkout", "2020-09-21");

   JSONObject booking=new JSONObject();
        booking.put("firstname","Selim");
        booking.put("lastname", "Ak");
        booking.put("totalprice", 11111);
        booking.put("depositpaid", true);
        booking.put("bookingdates", bookingDate);
       return booking;
    }
}
