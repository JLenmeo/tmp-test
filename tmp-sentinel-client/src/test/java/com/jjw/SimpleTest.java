package com.jjw;

import com.jjw.request.TestRequest;
import org.junit.Test;

import java.lang.reflect.Field;

public class SimpleTest {

    @Test
    public void test() throws Exception{

        String express = "this.content.phone";

        TestRequest request = new TestRequest("111","jjw",new TestRequest.TestContent("1989298443"));

        String[] elements = express.split("\\.");

        Object fieldVal = request;
        for(int i = 1;i < elements.length;i++){
             fieldVal = parse(fieldVal,elements[i]);
        }

        System.out.println(fieldVal);

    }

    private Object parse(Object obj,String paramName) throws Exception{

        Field field = obj.getClass().getDeclaredField(paramName);
        field.setAccessible(true);

        return field.get(obj);

    }

}
