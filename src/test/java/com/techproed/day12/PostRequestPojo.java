package com.techproed.day12;

//POJO Plain Old Java Object anlamındadır.
//Pojolar Encapsulation Yontemi ile oluşturulur.
//OOP inheritance, abstract, encapsulation ve polymorphisimden oluşur.
//API testinde  de OOP kullandığımız yer encapsulationdir.
//Pojo encapsulation yontemi ile oluşturulur.


import com.techproed.pojos.TodosPojo;
import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestPojo extends TestBaseJsonPlaceHolder {
/*
https://jsonplaceholder.typicode.com/todos
    Request body  {
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    }
   Status code is 201
    response body {
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    }


    //1-Json objecten gelen tum keyleri private bir değişken olarak tanımlıyorum.
    //bu ornekte request le response aynı olduğu için tek pojo oluşturcaz.
    //2- Her değişken için getter and setter methodlar oluşturuyoruz.
    //3- Parametrsiz Cons. oluştur.
    //4- Parametreli Cons. oluştur.
    //5- Burdaki değerleri okumak için To-String methodu oluşturuyoruz.
    */

    @Test
    public  void test01() {
        //url oluştur.
        spec01.pathParam("name","todos");

        //Request body oluşturlım
        TodosPojo todos=new TodosPojo(21,201,"Tidy your room",false);

        System.out.println(todos);

        //Request gonderelim
        Response response=given().contentType(ContentType.JSON).
                spec(spec01).auth().
                basic("admin","password123").
                body(todos).
                when().post("/{name}");
        response.prettyPrint();

        //request ile expected aynı olduğu için tek yaptık.
        //actual data içinde aynı kalıbı kullanabiliriz.

        TodosPojo actualdata=response.as(TodosPojo.class); //todospojo clası gibi bana getir.

        //de-serialization Yontemi

        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(todos.getId(),actualdata.getId());
        Assert.assertEquals(todos.getTitle(),actualdata.getTitle());
        Assert.assertEquals(todos.getUserId(),actualdata.getUserId());
        Assert.assertEquals(todos.isCompleted(),actualdata.isCompleted());

        // JsonPath --------------POJO Assertions;
        JsonPath json=response.jsonPath();
        Assert.assertEquals(todos.getId(),json.getInt("id"));
        Assert.assertEquals(todos.getUserId(),json.getInt("userId"));
        Assert.assertEquals(todos.getTitle(),json.getString("title"));
        Assert.assertEquals(todos.isCompleted(),json.getBoolean("completed"));

        //Matchers-------------------POJO

        response.then().assertThat().body("id",equalTo(todos.getId()),
                "userId",equalTo(todos.getUserId()),
                "title",equalTo(todos.getTitle()),
                "completed",equalTo(todos.isCompleted()));


    }

}
