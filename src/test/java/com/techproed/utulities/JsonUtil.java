package com.techproed.utulities;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    //object mapper kullanarak de_serialization yapma yöntemi
    private  static ObjectMapper mapper;
    static{
        mapper=new ObjectMapper();
    }
    //Methodumuzu oluşturacağız. Stringe çevrilmiş json datasını java objesine dönüştürecek.
    public static <T> T convertJsonToJava(String json,Class<T> cls){
        //T genelde developerların kullandığı generic bir ifadedir.
        // Tum data typlarını kapsar. T olduğunda ister List olsun ister Map olsun.
        // Bize gelecek ifadeyi kendisi tarıyor.Ona gore cevabı veriyor.
        // En uygun yapıda bize donduruyor.


        T javaResult= null;
        try {
            javaResult = mapper.readValue(json, cls);

            //methoda gelen parametreleri okuyor bunu bize mapper tipinde JavaResulta
            //aktarıyor.
            //Eger bir reusable bir metod kullanacaksak try catch kullanmalıyız.
            // Eger throws kullanırsak her kullandığımızda o classta bunu handle etmek için ilava
            // throws eklemek zorudayız. Bu da uygun değildir. Ornek threadsleep gibi...


        } catch (IOException e) {
            System.err.println("json datası javaya dönüştürülemedi"+e.getMessage());
        }
        return javaResult;
    }
}
