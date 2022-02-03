package com.techproed.day09;


import com.techproed.TestData.Dummy;
import com.techproed.testBase.TestBaseDummy;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class GetRequest13Matchers extends TestBaseDummy {
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
        response.then().
                assertThat().
                statusCode(200).
                body(
                        "data[4].employee_name", equalTo(expectedData.get("5.çalışan ismi")),
                        "data.id",hasSize((Integer)expectedData.get("çalışan sayisi")) ,
                        "data[-2].employee_salary",equalTo(expectedData.get("sondan2.maaş")) ,
                        "data.employee_age" ,hasItems(
                                ((List)expectedData.get("yaslar")).get(0),
                                ((List)expectedData.get("yaslar")).get(1),
                                ((List)expectedData.get("yaslar")).get(2) )     ,
                        "data[10].id",equalTo(((Map)expectedData.get("employe11")) .get("id")),
                        "data[10].employee_name",equalTo(((Map)expectedData.get("employe11")) .get("employee_name")),
                        "data[10].employee_salary",equalTo(((Map)expectedData.get("employe11")) .get("employee_salary")),
                        "data[10].employee_age",equalTo(((Map)expectedData.get("employe11")) .get("employee_age")),
                        "data[10].profile_image",equalTo(((Map)expectedData.get("employe11")) .get("profile_image"))
                );
    }
}