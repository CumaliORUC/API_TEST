package com.techproed.TestData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
"employee_salary": 90560,
"employee_age": 30,
"profile_image": "" }
} gibi olduğunu test edin.
     */
public class Dummy {
    public HashMap<String,Object> dummySetup() {
        HashMap<String,Object> expectedData=new HashMap<String,Object>();
        //yaslar için bir list oluşturalım.
        List<Integer>yaslar=new ArrayList<Integer>();
        yaslar.add(19);
        yaslar.add(21);
        yaslar.add(40);

        //İÇ MAP i oluşturalım.

        HashMap<String,Object> person11=new HashMap<String,Object>();
        person11.put("id",11);
        person11.put( "employee_name","Jena Gaines");
        person11.put( "employee_salary", 90560);
        person11.put( "employee_age", 30);
        person11.put( "profile_image", "");

        expectedData.put("yaslar",yaslar);
        expectedData.put("employee11",person11);
        expectedData.put("status code",200);
        expectedData.put("5.çalışan ismi","Airi Satou");
        expectedData.put("çalışan sayisi",24);
        expectedData.put("sondan2.maaş",106450);


        return expectedData;
    }
    public HashMap<String,Integer> dummySetup2(){
        HashMap<String,Integer> expectedDataMap = new HashMap<>();
        expectedDataMap.put("statusCode",200);
        expectedDataMap.put("enYuksekMaas",725000);
        expectedDataMap.put("enKucukYas",19);
        expectedDataMap.put("ikinciYuksekMaas",675000);

        return expectedDataMap;


}
public HashMap<String,Object> setup3() {
        //bu metod request body için hazırlanımıştır.
        HashMap<String,Object> requestBodyMap=new HashMap<String,Object>();
        requestBodyMap.put("name","Ahmet Aksoy");
        requestBodyMap.put("salary",1000);
        requestBodyMap.put("age",18);
        requestBodyMap.put("profile_image","");
    return requestBodyMap;
}

public  HashMap<String,Object> setup4expectedData() {
    HashMap<String,Object> expectedData=new HashMap<String, Object>();
    expectedData.put("statusCode",200);
    expectedData.put("status","success");
    expectedData.put("message","Successfully! Record has been added.");
    return expectedData;

}

public JSONObject deleteExpected() {
        JSONObject deleteExpected=new JSONObject();
        deleteExpected.put("statusCode",200);
        deleteExpected.put("status","success");
        deleteExpected.put("data","2");
        deleteExpected.put("message","Successfully! Record has been deleted");
        return deleteExpected;
}
}