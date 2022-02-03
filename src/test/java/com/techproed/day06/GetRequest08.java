package com.techproed.day06;

import com.techproed.testBase.TestBaseDummy;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/*
    http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
   1) Butun calisanlarin isimlerini consola yazdıralim
   2) 3. calisan kisinin ismini konsola yazdıralim
   3) Ilk 5 calisanin adini konsola yazdiralim
   4) En son calisanin adini konsola yazdiral
 */
public class GetRequest08 extends TestBaseDummy {
    @Test
    public void test(){
        spec03.pathParam("parametre1","employees");
        Response response=given().
                accept("application.json").
                spec(spec03).when().
                get("/{parametre1}");
       response.prettyPrint();
        JsonPath json=response.jsonPath();

        System.out.println(json.getString("data.employee_name"));
        //responsedaki tum employeeleri alır getirir.

        System.out.println(json.getString("data.employee_name[3]"));
        System.out.println(json.getString("data.employee_name[0,1,2,3,4]")); //ilk 5 getirir.
        System.out.println(json.getString("data.employee_name[-1]")); //-1 ile son index aldık.

    }

}
