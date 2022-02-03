package com.techproed.day7;

import com.techproed.testBase.TestBaseDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends TestBaseDummy {
    /*
    http://dummy.restapiexample.com/api/v1/employees
url ine bir istek gönderildiğinde
Dönen response un
 Status kodunun 200,
 1)10’dan büyük tüm id’leri ekrana yazdırın ve
10’dan büyük 14 id olduğunu,
 2)30’dan küçük tüm yaşları ekrana yazdırın ve
  bu yaşların içerisinde en büyük yaşın 23 olduğunu
 3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
  bunların içerisinde “Charde Marshall” olduğunu test edin
     */

    @Test
    public void test01() {
        spec03.pathParam("name", "employees");

        Response response = given().
                accept("application.json").
                spec(spec03).
                when().get("/{name}");

        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        JsonPath json=response.jsonPath();
        //1)10’dan büyük tüm id’leri ekrana yazdırın ve
        //10’dan büyük 14 id olduğunu,
      /*  List<Integer> idlist01 = json.getList("data.id");
        idlist01.stream().filter(t-> t<10).forEach(System.out::println);
        List<Integer> idliste= idlist01.stream().filter(x->x>10).collect(Collectors.toList());
        Assert.assertEquals(14, idliste.size());

       */

      /*  List<Integer> idList= json.getList("data.id");
        List<Integer> idList2=new ArrayList<Integer>();

        for (Integer each: idList)
              {
                  if ((each)>10) {
                  idList2.add(each);
              }

        }
        Assert.assertEquals(14,idList2.size());

       */

        //3. Yonten Groovy dili olarak belirtilen datanın içerisinde
        // istenen şarta bağlı olan değerleri getirir.findAll {it.ıd>10} şart bolumu
        List<Integer> list02=json.getList("data.findAll{it.id>10}.id");

        System.out.println(list02);

        //2)30’dan küçük tüm yaşları ekrana yazdırın ve
        //  bu yaşların içerisinde en büyük yaşın 23 olduğunu

        List<Integer> yaslist=json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(yaslist);
        Collections.sort(yaslist);
        Assert.assertEquals(23,(int) yaslist.get(yaslist.size()-1));
        //3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
        //  bunların içerisinde “Charde Marshall” olduğunu test edin

        List<String> nameList=json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println(nameList);

        Assert.assertTrue(nameList.contains("Charde Marshall"));

    }
}