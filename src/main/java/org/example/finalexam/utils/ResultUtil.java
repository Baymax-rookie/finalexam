package org.example.finalexam.utils;


import com.google.gson.Gson;

/**
 * 搞这么多返回类型的类，其实很冗余
 * @author 12589
 */
public class ResultUtil {
    public static String result(Object object){
        return new Gson().toJson(object);
    }
}
