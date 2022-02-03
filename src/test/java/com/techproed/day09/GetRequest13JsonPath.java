package com.techproed.day09;

import com.techproed.TestData.Dummy;
import com.techproed.testBase.TestBaseDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
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
public class GetRequest13JsonPath extends TestBaseDummy {
    @Test
    public void test() {

        //oncelikle url oluşturalım.
        Dummy dummy=new Dummy();

        HashMap<String,Object> expectedData=dummy.dummySetup();
        spec03.pathParam("parametre","employees");
        //reğuest oluşturma response ve sonra given, accept, spec, when ve get param;
        Response response=given().
                accept("application/json").
                spec(spec03).when().get("/{parametre}");
        response.prettyPrint();

        JsonPath json=response.jsonPath();

        Assert.assertEquals(expectedData.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedData.get("5.çalışan ismi"),json.getString("data[4].employee_name"));
        Assert.assertEquals(expectedData.get("çalışan sayisi"),json.getList("data.id").size());
        Assert.assertEquals(expectedData.get("sondan2.maaş"),json.getInt("data[-2].employee_salary"));
        Assert.assertTrue(json.getList("data.employee_age").containsAll((List)expectedData.get("yaslar")));
        Assert.assertEquals(((Map)expectedData.get("employee11")).get("id"),json.getInt("data[10].id"));
        Assert.assertEquals(((Map)expectedData.get("employee11")).get("employee_name"),json.getString("data[10].employee_name"));
        Assert.assertEquals(((Map)expectedData.get("employee11")).get("employee_salary"),json.getInt("data[10].employee_salary"));
        Assert.assertEquals(((Map)expectedData.get("employee11")).get("employee_age"),json.getInt("data[10].employee_age"));
        Assert.assertEquals(((Map)expectedData.get("employee11")).get("profile_image"),json.getString("data[10].profile_image"));
    }
}