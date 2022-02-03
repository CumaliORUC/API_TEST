package com.techproed.day08;

import com.techproed.TestData.Dummy;
import com.techproed.testBase.TestBaseDummy;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends TestBaseDummy {
    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
Sondan 2. çalışanın maaşının 106450 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
11. Çalışan bilgilerinin
  {
 “id”:”11”
 "employee_name": "Jena Gaines",
"employee_salary": "90560",
"employee_age": "30",
"profile_image": "" }
} gibi olduğunu test edin.
     */

    @Test
    public void test() {

        //oncelikle url oluşturalım.
        Dummy dummy=new Dummy();
        spec03.pathParam("parametre","employees");
        //reğuest oluşturma response ve sonra given, accept, spec, when ve get param;
        Response response=given().
                accept("application/json").
                spec(spec03).when().get("/{parametre}");
        response.prettyPrint();

        //burada iki data kıyaslaması yapılacağından oncelikle actual data ve expected datanın oluşturulması gerekiyor.
        //actual için hasmap yapısında ve yapı olarak da response as(HashMap.class) gibi diyoruz.
        //expected data da zaten başka bir classta hazırlıyoruz. ve buraya metod ile çağırıyoruz. Hazırlayacağpımız class
        //return ve hashmap gonderen bir bir yapı olacaktır. Bu nedenle expected data da ne istiyorsak onun tamamı hashmap içinde olmalıdır.
        //oradan gelecek datayı da yine burada oluşturduğumuz expected datanın içine koyuyoruz.
        HashMap<String,Object> actualData=response.as(HashMap.class);
        HashMap<String,Object> expectedData=dummy.dummySetup();

        //actualdata da 5. kişiye ulaşmak için;
        Assert.assertEquals(expectedData.get("status code"),response.getStatusCode());

        //actualdata da 5. kişiye ulaşmak için;
        // git data içindeki bilgileri bir listin içine koy, sonra listin içinde index ile gosterdiğim requesti
        //bana map olarak getir ki ben onu kıyaslama yapabileyim. O nedenle iç parantez list, dış parantez de map ozelliğinde olacak.
        Assert.assertEquals(expectedData.get("5.çalışan ismi"),
                ((Map) ((List)actualData.get("data")).get(4)).get("employee_name"));

        //çalışan sayısının 24 olduğu
        //burada da yine aynı şekilde data yı list halinde getir. Bana o listenin uzunluğunu ver.


        Assert.assertEquals(expectedData.get("çalışan sayisi"),
                ((List) actualData.get("data")).size());

        //Sondan 2. çalışanın maaşının 106450 olduğunu

        //once data-2 inci indexe git onun maaşını bana map olarak getir.

        int dataSize=((List<?>) actualData.get("data")).size();

        Assert.assertEquals(expectedData.get("sondan2.maaş"),
                ((Map)((List) actualData.get("data")).get(dataSize-2)).get("employee_salary"));


        //40,21 ve 19 yaslarında çalışanlar olup olmadığını
        List<Integer> actualyaslistesi=new ArrayList<Integer>();

        for (int i=0; i<dataSize; i++) {
            actualyaslistesi.add((Integer) ((Map)((List)actualData.get("data")).get(i)).get("employee_age"));
        }
    Assert.assertTrue(actualyaslistesi.containsAll((List)expectedData.get("yaslar")));

        //11. Çalışan bilgileri

        Assert.assertEquals(((Map)expectedData.get("employee11")).get("id"),
                ((Map)((List)actualData.get("data")).get(10)).get("id"));

        Assert.assertEquals(((Map)expectedData.get("employee11")).get("employee_name"),
                ((Map)((List)actualData.get("data")).get(10)).get("employee_name"));

        Assert.assertEquals(((Map)expectedData.get("employee11")).get("employee_salary"),
                ((Map)((List)actualData.get("data")).get(10)).get("employee_salary"));

        Assert.assertEquals(((Map)expectedData.get("employee11")).get("age"),
                ((Map)((List)actualData.get("data")).get(10)).get("age"));

        Assert.assertEquals(((Map)expectedData.get("employee11")).get("profile_image"),
                ((Map)((List)actualData.get("data")).get(10)).get("profile_image"));
    }
}
