package com.techproed.TestData;

import org.json.JSONObject;

import java.util.HashMap;

public class JsonPlaceHolder {
public int statusCode=201;
    public HashMap<String,Object> setupTestData() {
        //1- Oncelikle bir adet map oluşturuyoruz.
        HashMap<String,Object> expectedDataMap=new HashMap<String, Object>();
        expectedDataMap.put("statusCode",200);
        expectedDataMap.put("completed",false);
        expectedDataMap.put("userId",1);
        expectedDataMap.put("title","quis ut nam facilis et officia qui");
        expectedDataMap.put("Via","1.1 vegur");
        expectedDataMap.put("Server", "cloudflare");

        return expectedDataMap;
    }
    public JSONObject setUpPost03(){
     /*
      }
  "userId": 55,
  "title": "Tidy your room",
  "completed": false
}
      */
        JSONObject requestBody=new JSONObject();
        requestBody.put("userId",55);
        requestBody.put("title","Tidy your room");
        requestBody.put("completed",false);
        return requestBody;
    }
    public JSONObject setupPut01 () {
     /*   {
            "userId": 21,
                "title": "Wash the dishes",
                "completed": false
        }

      */
        JSONObject requestBodyPut=new JSONObject();
        requestBodyPut.put("userId",21);
        requestBodyPut.put("title","Wash the dishes");
        requestBodyPut.put("completed",false);

        return requestBodyPut;
    }
    /*
    {
        "userId": 10,
            "title": "API calismaliyim"
        "completed": true,
            "id": 198
    }
*/
    public JSONObject patchrequest() {
        JSONObject requestbody=new JSONObject();
        requestbody.put(    "title", "API calismaliyim");

        return requestbody;
    }
    /*
    {
    "userId": 10,
            "title": "API calismaliyim"
        "completed": true,
            "id": 198
    }
    */

    public JSONObject patchexpected() {
        JSONObject patchexpected=new JSONObject();
        patchexpected.put(    "title", "API calismaliyim");
        patchexpected.put(    "completed", true);
        patchexpected.put(    "userId", 10);
        return patchexpected;
    }

    }

