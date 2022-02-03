package com.techproed.day06;

import com.techproed.testBase.TestBaseDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest09 extends TestBaseDummy {
    /*
    http://dummy.restapiexample.com/api/v1/employees
    url ine bir istek gönderildiğinde,
status kodun 200,
gelen body de,
5. çalışanın isminin "Airi Satou" olduğunu ,
6. çalışanın maaşının "372000" olduğunu ,
Toplam 24 tane çalışan olduğunu,
"Rhona Davidson" ın employee lerden biri olduğunu
"21", "23", "61" yaşlarında employeeler olduğunu test edin
     */

    @Test
    public void test01  () {
        spec03.pathParam("name","employees");

        Response response=given().accept("application.json").spec(spec03).when().get("/{name}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        JsonPath json=response.jsonPath();

        //5. çalışanın isminin "Airi Satou" olduğunu
        Assert.assertEquals("Airi Satou",json.getString("data.employee_name[4]"));

        //6. çalışanın maaşının "372000" olduğunu
        Assert.assertEquals(372000,json.getInt("data.employee_salary[5]"));

        //Toplam 24 tane çalışan olduğunu

        Assert.assertEquals(24,json.getList("data.id").size());

        //"Rhona Davidson" ın employee lerden biri olduğunu

        Assert.assertTrue(json.getList("data.employee_name").contains("Rhona Davidson"));

        //"21", "23", "61" yaşlarında employeeler olduğunu test edin


        List<Integer> yaslist1= Arrays.asList(21,23,61);
        Assert.assertTrue(json.getList("data.employee_age").containsAll(yaslist1));
        //veya
        List<Integer> yasListesi=new ArrayList<Integer>();
        yasListesi.add(21);
        yasListesi.add(23);
        yasListesi.add(61);

        Assert.assertTrue(json.getList("data.employee_age").containsAll(yasListesi));


    }
}

