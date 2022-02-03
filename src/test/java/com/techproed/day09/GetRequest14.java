package com.techproed.day09;

import com.techproed.TestData.Dummy;
import com.techproed.testBase.TestBaseDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends TestBaseDummy {

    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000
olduğunu test edin.
     */
    @Test
    public void test() {

        //oncelikle url oluşturalım.
        spec03.pathParam("parametre","employees");

        //expected datayı oluştur.

        Dummy dummy=new Dummy();
        HashMap<String,Integer> expectedData=dummy.dummySetup2();

        System.out.println(expectedData);

        //request oluşturma response ve sonra given, accept, spec, when ve get param;
        Response response=given().
                accept("application/json").
                spec(spec03).when().get("/{parametre}");
        response.prettyPrint();

        //De-serialization Yontemi;

        HashMap<String,Object> actualData=response.as(HashMap.class);

        Assert.assertEquals(expectedData.get("statusCode"),(Integer) response.getStatusCode()); //int dondğu için Integer wrapper class yaptık
        //En yüksek maaşın 725000 olduğunu,
        //once maaşlardan oluşan bir list oluşturulmalı
        List<Integer> actualmaasListesi=new ArrayList<Integer>();
        //actualdataMapten donen datanın size kadar bir for oluşturcaz ve tek tek maaşları ekleriz

        int maaslistsize=((List)actualData.get("data")).size();

        for (int i=0; i<maaslistsize; i++) {
           actualmaasListesi.add ((Integer)((Map)((List<?>) actualData.get("data")).get(i)).get("employee_salary"));
        }
        Collections.sort(actualmaasListesi);
        Assert.assertEquals(expectedData.get("enYuksekMaas"),actualmaasListesi.get(actualmaasListesi.size()-1));

        //2. enyuksek maas
        Assert.assertEquals(expectedData.get("ikinciYuksekMaas"),actualmaasListesi.get(maaslistsize-2));

        // En küçük yaşın 19 olduğunu,
        List<Integer> actualAgeList = new ArrayList<>();
        for (int i = 0; i < maaslistsize; i++) {
            int age = (int)((Map)((List)actualData.get("data")).get(i)).get("employee_age");
            actualAgeList.add(age);
        }
        Collections.sort(actualAgeList);
        Assert.assertEquals(expectedData.get("enKucukYas"),actualAgeList.get(0));


        //json yontemi ile yapacak olursak;

        JsonPath json=response.jsonPath();

        //en kucuk yaş
        List<Integer>jsonyaslistesi=json.getList("data.employee_age");
       Collections.sort(jsonyaslistesi);
       Assert.assertEquals(expectedData.get("enKucukYas"),jsonyaslistesi.get(0));

        //en yuksek maas
        List<Integer>jsonmaaslistesi=json.getList("data.employee_salary");
        Collections.sort(jsonmaaslistesi);
        Assert.assertEquals(expectedData.get("enYuksekMaas"),jsonmaaslistesi.get(jsonmaaslistesi.size()-1));
        //ikinci buyuk maas
        Assert.assertEquals(expectedData.get("ikinciYuksekMaas"),jsonmaaslistesi.get(jsonmaaslistesi.size()-2));



}
}