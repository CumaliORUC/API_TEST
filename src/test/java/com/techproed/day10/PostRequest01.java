package com.techproed.day10;

/*
http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
    "name":"Ahmet Aksoy",
           "salary":"1000",
           "age":"18",
           "profile_image": ""
}
gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
{
   "status": "success",
           "data": {
        “id”:…
   },
   "message": "Successfully! Record has been added."
}
olduğunu test edin
 */

import com.techproed.TestData.Dummy;
import com.techproed.testBase.TestBaseDummy;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends TestBaseDummy {

    @Test
    public void test01 () {
        //url oluştur.
        spec03.pathParam("name", "create");

        //expected datadan once post yaparken request body gonderdiğim için oncelikle onu başka classta
        //oluşturup buraya alalım.

        Dummy dummy=new Dummy();
        HashMap<String,Object> requestbody= dummy.setup3();
        System.out.println(requestbody);
        //expected datayı oluştulım yine dummy clasında oluşturdum
        HashMap<String,Object> expecteData= dummy.setup4expectedData();


        //request oluştur.Burada auotorization yani giriş yetkisi kullanılacak biz basic kullancaz.
        Response response=given().
                accept("application/json").
                spec(spec03).body(requestbody).
                auth().basic("admin",    "password123").when().post("/{name}");
        response.prettyPrint();

        HashMap<String,Object> actualData=response.as(HashMap.class);

        Assert.assertEquals(expecteData.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expecteData.get("status"),actualData.get("status"));
        Assert.assertEquals(expecteData.get("message"),actualData.get("message"));

        //sonuç olarak post yapacağımız iiçin request body oluşturduk.
        //bunun için de once authorization ile yapıyoruz.
        //beklediğimiz response alıp onu expected dataya koyduk.
        //
    }
}
